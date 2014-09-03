// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.gui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.print.PrintManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.lukeleber.app.ActivityResultListener;
import com.lukeleber.app.EnhancedActivity;
import com.lukeleber.app.IntentHelper;
import com.lukeleber.barcodestudio.Barcode;
import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.Symbology;
import com.lukeleber.barcodestudio.printing.BarcodeDocumentAdapter;
import com.lukeleber.barcodestudio.printing.BatchJob;
import com.lukeleber.barcodestudio.printing.Job;
import com.lukeleber.barcodestudio.printing.Media;
import com.lukeleber.barcodestudio.printing.RangeJob;
import com.lukeleber.barcodestudio.symbologies.Symbologies;
import com.lukeleber.text.filter.CharsetInputFilter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;

/**
 * The main {@link Activity} in the Barcode Studio App.
 *
 */
public final class BarcodeStudio
        extends
        EnhancedActivity
{

    /// The conversion factor between postscript units and inches
    private final static float POSTSCRIPT_CONVERSION_FACTOR = 72.0f;

    /// <-- ButterKnife

    /// Spinner view that holds all supported symbologies
    @InjectView(R.id.symbologies)
    Spinner symbologies;

    /// Are we in batch mode?
    @InjectView(R.id.batch_mode_radio)
    RadioButton batchModeRadio;

    /// What should the text of this batch be?
    @InjectView(R.id.batch_mode_text)
    EditText batchModeText;

    /// How many barcodes should be included in this batch?
    @InjectView(R.id.batch_mode_count)
    EditText batchModeCount;

    /// What is the start of the range?
    @InjectView(R.id.range_mode_start)
    EditText rangeModeStart;

    /// What is the end of the range?
    @InjectView(R.id.range_mode_end)
    EditText rangeModeEnd;

    /// The 'print preview' button
    @InjectView(R.id.print_preview)
    Button printPreviewButton;

    /// The 'print' button
    @InjectView(R.id.print)
    Button printButton;

    /**
     * Fired when the batch text is changed, this input validation callback enables or disables
     * the printing buttons and adjusts text colors
     * @hide
     */
    @OnTextChanged(R.id.batch_mode_text)
    void onBatchTextChanged()
    {
        Editable count = this.batchModeCount.getText();
        boolean enabled = !TextUtils.isEmpty(this.batchModeText.getText()) && !TextUtils.isEmpty(count);
        if(enabled)
        {
            enabled = Integer.parseInt(count.toString()) != 0;
            if(enabled)
            {
                this.batchModeCount.setTextColor(Color.BLACK);
            }
            else
            {
                this.batchModeCount.setTextColor(Color.RED);
            }
        }
        setPrintingEnabled(enabled);
    }

    /**
     * Fired when the batch count is changed, this input validation callback enables or disables
     * the printing buttons and adjusts text colors
     * @hide
     */
    @OnTextChanged(R.id.batch_mode_count)
    void onBatchSizeChanged()
    {
        Editable count = this.batchModeCount.getText();
        boolean enabled = !TextUtils.isEmpty(this.batchModeText.getText()) && !TextUtils.isEmpty(count);
        if(enabled)
        {
            enabled = Integer.parseInt(count.toString()) != 0;
            if(enabled)
            {
                this.batchModeCount.setTextColor(Color.BLACK);
            }
            else
            {
                this.batchModeCount.setTextColor(Color.RED);
            }
        }
        setPrintingEnabled(enabled);
    }

    /**
     * Fired when the range start is changed, this input validation callback enables or disables
     * the printing buttons and adjusts text colors
     * @hide
     */
    @OnTextChanged(R.id.range_mode_start)
    void onRangeStartChanged()
    {
        Editable begin = this.rangeModeStart.getText();
        Editable end = this.rangeModeEnd.getText();
        boolean enabled = !TextUtils.isEmpty(begin) && !TextUtils.isEmpty(end);
        if(enabled)
        {
            enabled = Integer.parseInt(end.toString()) >= Integer.parseInt(begin.toString());
            if(!enabled)
            {
                rangeModeStart.setTextColor(Color.RED);
                rangeModeEnd.setTextColor(Color.RED);
            }
            else
            {
                rangeModeStart.setTextColor(Color.BLACK);
                rangeModeEnd.setTextColor(Color.BLACK);
            }
        }
        setPrintingEnabled(enabled);
    }

    /**
     * Fired when the range start is changed, this input validation callback enables or disables
     * the printing buttons and adjusts text colors
     * @hide
     */
    @OnTextChanged(R.id.range_mode_end)
    void onRangeEndChanged()
    {
        Editable begin = this.rangeModeStart.getText();
        Editable end = this.rangeModeEnd.getText();
        boolean enabled = !TextUtils.isEmpty(begin) && !TextUtils.isEmpty(end);
        if(enabled)
        {
            enabled = Integer.parseInt(end.toString()) >= Integer.parseInt(begin.toString());
            if(!enabled)
            {
                rangeModeStart.setTextColor(Color.RED);
                rangeModeEnd.setTextColor(Color.RED);
            }
            else
            {
                rangeModeStart.setTextColor(Color.BLACK);
                rangeModeEnd.setTextColor(Color.BLACK);
            }
        }
        setPrintingEnabled(enabled);
    }

    /**
     * Fired when a new symbology is selected from the spinner.  This method updates the
     * input filtering mechanism of various GUI components.
     * @hide
     */
    @OnItemSelected(R.id.symbologies)
    void onNewSymbologySelected()
    {
        InputFilter filter = new CharsetInputFilter(((Symbology) symbologies.getSelectedItem()).getCharset());

        /// Make sure existing garbage is cleaned up
        String text = batchModeText.getText().toString();
        if(filter.filter(text, 0, text.length(), null, -1, -1) == null)
        {
            batchModeText.getText().clear();
        }

        /// and apply the new filter
        batchModeText.setFilters(new InputFilter[]{filter});
    }

    /**
     * Fired when the batch mode radio is toggled on or off.  This method updates the
     * enabled-status of the batch mode radio button group.
     * @param selected was the radio checked or unchecked?
     * @hide
     */
    @OnCheckedChanged(R.id.batch_mode_radio)
    void onBatchModeToggle(boolean selected)
    {
        batchModeText.setEnabled(selected);
        batchModeCount.setEnabled(selected);
        if(selected)
        {
            setPrintingEnabled(!TextUtils.isEmpty(this.batchModeText.getText()) && !TextUtils.isEmpty(this.batchModeCount.getText()));
        }
    }

    /**
     * Fired when the range mode radio is toggled on or off.  This method updates the
     * enabled-status of the range mode radio button group.
     *
     * @param selected was the radio checked or unchecked?
     * @hide
     */
    @OnCheckedChanged(R.id.range_mode_radio)
    void onRangeModeToggle(boolean selected)
    {
        rangeModeStart.setEnabled(selected);
        rangeModeEnd.setEnabled(selected);
        if(selected)
        {
            setPrintingEnabled(!TextUtils.isEmpty(this.rangeModeStart.getText()) && !TextUtils.isEmpty(this.rangeModeEnd.getText()));
        }
    }

    /**
     * Fired when the "configure symbologies" button is clicked, this method starts
     * a new {@link SymbologyEditor} and then waits for its result.
     * @hide
     */
    @OnClick(R.id.configure_symbology)
    void onConfigureSymbologyClick()
    {
        super.startActivityForResult(
                IntentHelper.createIntent(
                        this,
                        SymbologyEditor.class,
                        new Pair<String, Symbology>(
                                super.getString(R.string.symbology_settings_extra_key),
                                (Symbology) symbologies.getSelectedItem())
                ),
                symbologySettingsRequestHandle
        );
    }

    /**
     * Fired when the "configure page" button is clicked, this method starts
     * a new {@link MediaEditor} and then waits for its result.
     * @hide
     */
    @OnClick(R.id.configure_page)
    void onConfigurePageClick()
    {
        super.startActivityForResult(
                IntentHelper.createIntent(
                        this,
                        MediaEditor.class,
                        new Pair<String, Media>(
                                super.getString(R.string.print_media_settings_extra_key),
                                pageSetup)
                ),
                pageSettingsRequestHandle
        );
    }

    /**
     * Fired when the "print preview" button is clicked, this method starts
     * a new {@link PrintPreview}.
     */
    @OnClick(R.id.print_preview)
    void onPreviewButtonClick()
    {
        super.startActivity(
            IntentHelper.createIntent(
                    this,
                    PrintPreview.class,
                    new Pair<String, Job>("job",
                            batchModeRadio.isChecked() ?
                                    new BatchJob(pageSetup.scale(POSTSCRIPT_CONVERSION_FACTOR),
                                            new Barcode(batchModeText.getText().toString(),
                                                    (Symbology) symbologies.getSelectedItem()),
                                            Integer.parseInt(batchModeCount.getText().toString())
                                    ) :
                                    new RangeJob(pageSetup.scale(POSTSCRIPT_CONVERSION_FACTOR),
                                            (Symbology) symbologies.getSelectedItem(),
                                            Integer.parseInt(rangeModeStart.getText().toString()),
                                            Integer.parseInt(rangeModeEnd.getText().toString()))
                    )
            )
        );
    }

    /**
     * Fired when the "print" button is clicked, this method starts
     * a new {@link PrintManager}.
     */
    @TargetApi(19)
    @OnClick(R.id.print)
    void onPrintClick()
    {
        PrintManager printManager = (PrintManager)getSystemService(Context.PRINT_SERVICE);

        String jobName = getString(R.string.app_name) + " Document";

        printManager.print(jobName, new BarcodeDocumentAdapter(this, "Barcodes.pdf",
                        batchModeRadio.isChecked() ?
                new BatchJob(pageSetup.scale(POSTSCRIPT_CONVERSION_FACTOR),
                        new Barcode(batchModeText.getText().toString(),
                                (Symbology)symbologies.getSelectedItem()),
                        Integer.parseInt(batchModeCount.getText().toString())) :
                new RangeJob(pageSetup.scale(POSTSCRIPT_CONVERSION_FACTOR),
                        (Symbology)symbologies.getSelectedItem(),
                        Integer.parseInt(rangeModeStart.getText().toString()),
                        Integer.parseInt(rangeModeEnd.getText().toString()))),
                null);
    }

    /// ButterKnife -->

    private void setPrintingEnabled(boolean enabled)
    {
        printPreviewButton.setEnabled(enabled);
        printButton.setEnabled(enabled);
    }


    /**
     * An {@link ActivityResultListener} for handling a page settings update
     *
     */
    private final class PageSettingsRequestHandler
            implements ActivityResultListener
    {
        @Override
        public void onActivityResult(int result, Intent intent)
        {
            BarcodeStudio.this.pageSetup =
                    (Media) intent.getSerializableExtra(
                            BarcodeStudio.super.getString(R.string.print_media_settings_extra_key));
            shortToast(R.string.media_settings_update_confirmation);
        }
    }

    /**
     * An {@link ActivityResultListener} for handling a symbology settings update
     */
    private final class SymbologySettingsRequestHandler
            implements ActivityResultListener
    {
        @Override
        public void onActivityResult(int result, Intent intent)
        {
            ((Symbology)symbologies.getSelectedItem())
                    .getConfig().apply(
                    ((Symbology)intent.getSerializableExtra(
                            BarcodeStudio.super.getString(R.string.symbology_settings_extra_key)))
                            .getConfig());
            shortToast(R.string.symbology_settings_update_confirmation);
        }
    }


    /// The request handle for a page settings activity result
    private final int pageSettingsRequestHandle;

    /// The request handle for a symbology settings activity result
    private final int symbologySettingsRequestHandle;

    /// The printing media configuration that is being used
    private Media pageSetup = new Media(new Media.Configuration());

    /**
     * Default public constructor
     */
    public BarcodeStudio()
    {
        this.pageSettingsRequestHandle =
                super.addResultListener(new PageSettingsRequestHandler());
        this.symbologySettingsRequestHandle =
                super.addResultListener(new SymbologySettingsRequestHandler());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_barcode_studio);
        ButterKnife.inject(this);
        symbologies.setAdapter(
                new ArrayAdapter<Symbology>(
                        this,
                        R.layout.dummy_dropdown_list_item,
                        Symbologies.getSymbologies()));
    }
}
