/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.util.Pair;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.lukeleber.barcodestudio.Barcode;
import com.lukeleber.barcodestudio.BatchJob;
import com.lukeleber.barcodestudio.Job;
import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.RangeJob;
import com.lukeleber.barcodestudio.Symbology;
import com.lukeleber.barcodestudio.rendering.PrintingMedia;
import com.lukeleber.barcodestudio.util.CharsetInputFilter;
import com.lukeleber.barcodestudio.util.DexClassLoader;
import com.lukeleber.barcodestudio.util.IntentHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;


public class BarcodeStudio
        extends
        Activity
{

    /// Request code symbolic constant for navigating to {@link PrintingMediaSettings}
    private final static int PAGE_SETTINGS_REQUEST_CODE = 0x1;

    /// Request code symbolic constant for navigating to {@link SymbologySettings}
    private final static int SYMBOLOGY_SETTINGS_REQUEST_CODE = 0x2;

    /// <-- ButterKnife

    /// Spinner view that holds all supported symbologies
    @InjectView(R.id.symbologies)
    Spinner symbologies;

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

    @OnCheckedChanged(R.id.single)
    void onSingleOptionSelected(boolean selected)
    {
        barcodeText.setEnabled(selected);
        barcodeCount.setEnabled(selected);
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

    @OnClick(R.id.pageSetup)
    void setupPage()
    {
        super.startActivityForResult(
                IntentHelper.createIntentWithSerializableExtras(
                        this,
                        PrintingMediaSettings.class,
                        new Pair<String, PrintingMedia>(
                                super.getString(R.string.print_media_settings_extra_key),
                                pageSetup)),
                PAGE_SETTINGS_REQUEST_CODE);
    }

    @OnClick(R.id.print_preview)
    void onPreviewButtonClick()
    {
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
    }

    /**
     * Utilize reflection to load any and all supported symbologies into the GUI spinner view
     */
    private void populateSymbologies()
    {
        ArrayAdapter<Symbology> adapter
                = new ArrayAdapter<Symbology>(this,
                R.layout.dummy_dropdown_list_item); // HACK! :(
        try
        {
            for (Class<Symbology> c : new DexClassLoader(this)
                    .findClasses(super.getString(R.string.symbologies_package), Symbology.class))
            {
                adapter.add(c.newInstance());
            }
            symbologies.setAdapter(adapter);
        }
        catch (Throwable t)
        {
            /// Eat it.
            /// TODO: Try to get this to happen?
            Log.wtf("[internal]", "Unable to open \"this\" DEX file for reading", t);
        }
    }
}
