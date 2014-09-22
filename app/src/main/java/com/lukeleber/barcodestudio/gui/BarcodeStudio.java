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
<<<<<<< HEAD
import android.print.PrintManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
=======
<<<<<<< HEAD
import android.print.PrintManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
=======
import android.text.InputFilter;
import android.text.Spanned;
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
import android.util.Log;
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
import android.util.Pair;
import android.widget.ArrayAdapter;
<<<<<<< HEAD
import android.widget.Button;
=======
<<<<<<< HEAD
import android.widget.Button;
=======
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

<<<<<<< HEAD
import com.lukeleber.app.ActivityResultListener;
import com.lukeleber.app.EnhancedActivity;
import com.lukeleber.barcodestudio.Barcode;
=======
<<<<<<< HEAD
import com.lukeleber.app.ActivityResultListener;
import com.lukeleber.app.EnhancedActivity;
import com.lukeleber.app.IntentHelper;
import com.lukeleber.barcodestudio.Barcode;
=======
import com.lukeleber.barcodestudio.Barcode;
import com.lukeleber.barcodestudio.BatchJob;
import com.lukeleber.barcodestudio.Job;
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.RangeJob;
import com.lukeleber.barcodestudio.Symbology;
<<<<<<< HEAD
import com.lukeleber.barcodestudio.printing.BarcodeDocumentAdapter;
import com.lukeleber.barcodestudio.printing.BatchJob;
import com.lukeleber.barcodestudio.printing.Job;
import com.lukeleber.barcodestudio.printing.Media;
import com.lukeleber.barcodestudio.printing.RangeJob;
import com.lukeleber.barcodestudio.symbologies.Symbologies;
import com.lukeleber.text.filter.CharsetInputFilter;
=======
<<<<<<< HEAD
import com.lukeleber.barcodestudio.printing.BarcodeDocumentAdapter;
import com.lukeleber.barcodestudio.printing.BatchJob;
import com.lukeleber.barcodestudio.printing.Job;
import com.lukeleber.barcodestudio.printing.Media;
import com.lukeleber.barcodestudio.printing.RangeJob;
import com.lukeleber.barcodestudio.symbologies.Symbologies;
import com.lukeleber.text.filter.CharsetInputFilter;
=======
import com.lukeleber.barcodestudio.rendering.PrintingMedia;
import com.lukeleber.barcodestudio.util.CharsetInputFilter;
import com.lukeleber.barcodestudio.util.DexClassLoader;
import com.lukeleber.barcodestudio.util.IntentHelper;
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;

<<<<<<< HEAD
import static com.lukeleber.app.IntentHelper.*;

=======
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
/**
 * The main {@link Activity} in the Barcode Studio App.
 *
 */
public final class BarcodeStudio
        extends
        EnhancedActivity
{

<<<<<<< HEAD
    /// The conversion factor between postscript units and inches
    private final static float POSTSCRIPT_CONVERSION_FACTOR = 72.0f;
=======
<<<<<<< HEAD
    /// The conversion factor between postscript units and inches
    private final static float POSTSCRIPT_CONVERSION_FACTOR = 72.0f;
=======
    /// Request code symbolic constant for navigating to {@link PrintingMediaSettings}
    private final static int PAGE_SETTINGS_REQUEST_CODE = 0x1;

    /// Request code symbolic constant for navigating to {@link SymbologySettings}
    private final static int SYMBOLOGY_SETTINGS_REQUEST_CODE = 0x2;
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git

    /// <-- ButterKnife

    /// Spinner view that holds all supported symbologies
    @InjectView(R.id.symbologies)
    Spinner symbologies;

<<<<<<< HEAD
    /// Are we in batch mode?
    @InjectView(R.id.batch_mode_radio)
    RadioButton batchModeRadio;
=======
<<<<<<< HEAD
    /// Are we in batch mode?
    @InjectView(R.id.batch_mode_radio)
    RadioButton batchModeRadio;
=======
    @InjectView(R.id.single)
    RadioButton generateSingle;

    @InjectView(R.id.encodingText)
    EditText barcodeText;

    @InjectView(R.id.barcodeCount)
    EditText barcodeCount;

    @InjectView(R.id.range1)
    EditText minRange;

    @InjectView(R.id.range2)
    EditText maxRange;

    @OnItemSelected(R.id.symbologies)
    void onNewSymbologySelected()
    {
        applyInputFilters(barcodeText,
                new CharsetInputFilter(((Symbology) symbologies.getSelectedItem()).getCharset()));
    }
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git

<<<<<<< HEAD
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
     */
    @OnTextChanged(R.id.batch_mode_text)
    void onBatchTextChanged()
=======
    @OnCheckedChanged(R.id.single)
    void onSingleOptionSelected(boolean selected)
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    {
<<<<<<< HEAD
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
=======
        barcodeText.setEnabled(selected);
        barcodeCount.setEnabled(selected);
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    }

    @OnCheckedChanged(R.id.range)
    void onRangeOptionSelected(boolean selected)
    {
        minRange.setEnabled(selected);
        maxRange.setEnabled(selected);
    }

    @OnClick(R.id.symbologySettings)
    void onConfigureSymbologyClicked()
    {
        super.startActivityForResult(
                IntentHelper.createIntentWithSerializableExtras(
                        this,
                        SymbologySettings.class,
                        new Pair<String, Symbology>(
                                super.getString(R.string.symbology_settings_extra_key),
                                (Symbology)symbologies.getSelectedItem())),
                SYMBOLOGY_SETTINGS_REQUEST_CODE);
    }
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git

<<<<<<< HEAD
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
<<<<<<< HEAD
     * Fired when the batch count is changed, this input validation callback enables or disables
     * the printing buttons and adjusts text colors
=======
     * Fired when the batch text is changed, this input validation callback enables or disables
     * the printing buttons and adjusts text colors
     * @hide
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
     */
<<<<<<< HEAD
    @OnTextChanged(R.id.batch_mode_count)
    void onBatchSizeChanged()
=======
    @OnTextChanged(R.id.batch_mode_text)
    void onBatchTextChanged()
=======
    @OnClick(R.id.pageSetup)
    void setupPage()
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
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
<<<<<<< HEAD
    }

    /**
     * Fired when the range start is changed, this input validation callback enables or disables
     * the printing buttons and adjusts text colors
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
=======
=======
        super.startActivityForResult(
                IntentHelper.createIntentWithSerializableExtras(
                        this,
                        PrintingMediaSettings.class,
                        new Pair<String, PrintingMedia>(
                                super.getString(R.string.print_media_settings_extra_key),
                                pageSetup)),
                PAGE_SETTINGS_REQUEST_CODE);
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    }

<<<<<<< HEAD
    /**
     * Fired when the range mode radio is toggled on or off.  This method updates the
     * enabled-status of the range mode radio button group.
     *
     * @param selected was the radio checked or unchecked?
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
     */
    @SuppressWarnings("unchecked")
    @OnClick(R.id.configure_symbology)
    void onConfigureSymbologyClick()
    {
        super.startActivityForResult(
                createIntent(
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
     */
    @SuppressWarnings("unchecked")
    @OnClick(R.id.configure_page)
    void onConfigurePageClick()
    {
        super.startActivityForResult(
                createIntent(
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
    @SuppressWarnings("unchecked")
    @OnClick(R.id.print_preview)
    void onPreviewButtonClick()
    {
        super.startActivity(
            createIntent(
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
=======
    @OnClick(R.id.print_preview)
    void onPreviewButtonClick()
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    {
<<<<<<< HEAD
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_barcode_studio);
        ButterKnife.inject(this);
        symbologies.setAdapter(
                new ArrayAdapter<Symbology>(
                        this,
                        R.layout.dummy_dropdown_list_item,
                        Symbologies.getSymbologies()));
=======
        super.startActivity(
            IntentHelper.createIntentWithSerializableExtras(
                this,
                PrintPreview.class,
                new Pair<String, Job>("job",
                        generateSingle.isChecked() ?
                                new BatchJob(pageSetup.scale(100.0f),
                                             new Barcode(barcodeText.getText().toString(),
                                                         (Symbology)symbologies.getSelectedItem()),
                                             Integer.parseInt(barcodeCount.getText().toString())) :
                                new RangeJob(pageSetup.scale(100.0f),
                                             (Symbology)symbologies.getSelectedItem(),
                                             Integer.parseInt(minRange.getText().toString()),
                                             Integer.parseInt(maxRange.getText().toString())))
                )
        );
    }

    /// ButterKnife -->

    /// The printing media configuration that is being used
    private PrintingMedia pageSetup = new PrintingMedia(new PrintingMedia.Configuration());

    /**
     * @see Activity#onActivityResult
     */
    @Override
    protected void onActivityResult(int code, int result, Intent intent)
    {
        switch (code)
        {
            case PAGE_SETTINGS_REQUEST_CODE:
                this.pageSetup =
                        (PrintingMedia) intent.getSerializableExtra(
                                super.getString(R.string.print_media_settings_extra_key));
                shortToast(R.string.media_settings_update_confirmation);
                break;
            case SYMBOLOGY_SETTINGS_REQUEST_CODE:
                ((Symbology)symbologies.getSelectedItem())
                        .getConfig().apply(
                        ((Symbology)intent.getSerializableExtra(
                            super.getString(R.string.symbology_settings_extra_key)))
                                .getConfig());
                shortToast(R.string.symbology_settings_update_confirmation);
                break;
            default:
                /// Although it would be useful, the code isn't concatenated
                /// on the end of the diagnostic because it would incur a
                /// runtime performance hit even with logging disabled.
                Log.wtf("[internal]", "Unknown response code");
        }
        super.onActivityResult(code, result, intent);
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    }

    /**
     * @see Activity#onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_barcode_studio);
        ButterKnife.inject(this);
        populateSymbologies();
    }

    private void applyInputFilters(EditText view, InputFilter... filters)
    {
        /// Make sure existing garbage is cleaned up
        String text = view.getText().toString();
        for(InputFilter filter : filters)
        {
            if(filter.filter(text, 0, text.length(), null, -1, -1) == null)
            {
                view.getText().clear();
                break;
            }
        }
        /// and apply the new filter
        view.setFilters(filters);
    }

    /// TODO: move this to a base class (not yet created) to avoid boilerplate.
    /**
     * Shows a short toast with the text found with the provided string resource ID
     *
     * @param resourceID the resource ID (IE R.string.XXX) to show in the toast
     */
    private void shortToast(int resourceID)
    {
        Toast.makeText(this, super.getString(resourceID), Toast.LENGTH_SHORT).show();
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    }

    /**
     * Fired when the batch count is changed, this input validation callback enables or disables
     * the printing buttons and adjusts text colors
     * @hide
     */
    @OnTextChanged(R.id.batch_mode_count)
    void onBatchSizeChanged()
    {
<<<<<<< HEAD
        Editable count = this.batchModeCount.getText();
        boolean enabled = !TextUtils.isEmpty(this.batchModeText.getText()) && !TextUtils.isEmpty(count);
        if(enabled)
=======
        ArrayAdapter<Symbology> adapter
                = new ArrayAdapter<Symbology>(this,
                R.layout.dummy_dropdown_list_item); // HACK! :(
        try
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
        {
<<<<<<< HEAD
            enabled = Integer.parseInt(count.toString()) != 0;
            if(enabled)
=======
            for (Class<Symbology> c : new DexClassLoader(this)
                    .findClasses(super.getString(R.string.symbologies_package), Symbology.class))
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
            {
<<<<<<< HEAD
                this.batchModeCount.setTextColor(Color.BLACK);
=======
                adapter.add(c.newInstance());
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
            }
            else
            {
                this.batchModeCount.setTextColor(Color.RED);
            }
        }
<<<<<<< HEAD
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
=======
        catch (Throwable t)
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
        {
<<<<<<< HEAD
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
=======
            /// Eat it.
            /// TODO: Try to get this to happen?
            Log.wtf("[internal]", "Unable to open \"this\" DEX file for reading", t);
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
        }
<<<<<<< HEAD
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
=======
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    }
}
