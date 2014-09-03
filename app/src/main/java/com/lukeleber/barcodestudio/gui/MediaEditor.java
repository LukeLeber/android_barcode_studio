// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.printing.Media;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnFocusChange;

import static com.lukeleber.barcodestudio.printing.Media.Orientation.LANDSCAPE;
import static com.lukeleber.barcodestudio.printing.Media.Orientation.PORTRAIT;

/**
 * GUI for configuring printing media settings
 * TODO: Grunt Work.  Docs are lowest priority.
 */
public class MediaEditor
        extends Activity
{

    /// <-- ButterKnife

    @InjectView(R.id.pageWidth)
    EditText pageWidth;

    @OnFocusChange(R.id.pageWidth)
    void pageWidth_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> float -> string) and update the map
                preservedInput.put(pageWidth, String.valueOf(Float.parseFloat(pageWidth.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                pageWidth.setText(preservedInput.get(pageWidth));
            }
        }
    }

    @InjectView(R.id.pageHeight)
    EditText pageHeight;

    @OnFocusChange(R.id.pageHeight)
    void pageHeight_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> float -> string) and update the map
                preservedInput.put(pageHeight, String.valueOf(Float.parseFloat(pageHeight.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                pageHeight.setText(preservedInput.get(pageHeight));
            }
        }
    }

    @InjectView(R.id.portrait)
    RadioButton portrait;

    @InjectView(R.id.landscape)
    RadioButton landscape;

    @InjectView(R.id.marginLeft)
    EditText marginLeft;

    @OnFocusChange(R.id.marginLeft)
    void marginLeft_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> float -> string) and update the map
                preservedInput.put(marginLeft, String.valueOf(Float.parseFloat(marginLeft.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                marginLeft.setText(preservedInput.get(marginLeft));
            }
        }
    }
    
    
    @InjectView(R.id.marginRight)
    EditText marginRight;

    @OnFocusChange(R.id.marginRight)
    void marginRight_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> float -> string) and update the map
                preservedInput.put(marginRight, String.valueOf(Float.parseFloat(marginRight.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                marginRight.setText(preservedInput.get(marginRight));
            }
        }
    }
    
    @InjectView(R.id.marginTop)
    EditText marginTop;

    @OnFocusChange(R.id.marginTop)
    void marginTop_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> float -> string) and update the map
                preservedInput.put(marginTop, String.valueOf(Float.parseFloat(marginTop.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                marginTop.setText(preservedInput.get(marginTop));
            }
        }
    }
    
    @InjectView(R.id.marginBottom)
    EditText marginBottom;

    @OnFocusChange(R.id.marginBottom)
    void marginBottom_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> float -> string) and update the map
                preservedInput.put(marginBottom, String.valueOf(Float.parseFloat(marginBottom.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                marginBottom.setText(preservedInput.get(marginBottom));
            }
        }
    }
    
    @InjectView(R.id.columnPadding)
    EditText columnPadding;

    @OnFocusChange(R.id.columnPadding)
    void columnPadding_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> float -> string) and update the map
                preservedInput.put(columnPadding, String.valueOf(Float.parseFloat(columnPadding.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                columnPadding.setText(preservedInput.get(columnPadding));
            }
        }
    }
    
    @InjectView(R.id.rowPadding)
    EditText rowPadding;

    @OnFocusChange(R.id.rowPadding)
    void rowPadding_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> float -> string) and update the map
                preservedInput.put(rowPadding, String.valueOf(Float.parseFloat(rowPadding.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                rowPadding.setText(preservedInput.get(rowPadding));
            }
        }
    }

    @InjectView(R.id.horizontalBarcodeCount)
    EditText horizontalBarcodeCount;

    @OnFocusChange(R.id.horizontalBarcodeCount)
    void horizontalBarcodeCount_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> int -> string) and update the map
                preservedInput.put(horizontalBarcodeCount, String.valueOf(Integer.parseInt(horizontalBarcodeCount.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                horizontalBarcodeCount.setText(preservedInput.get(horizontalBarcodeCount));
            }
        }
    }
    
    @InjectView(R.id.verticalBarcodeCount)
    EditText verticalBarcodeCount;

    @OnFocusChange(R.id.verticalBarcodeCount)
    void verticalBarcodeCount_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> int -> string) and update the map
                preservedInput.put(verticalBarcodeCount, String.valueOf(Integer.parseInt(verticalBarcodeCount.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                verticalBarcodeCount.setText(preservedInput.get(verticalBarcodeCount));
            }
        }
    }
    
    @InjectView(R.id.barcodeWidth)
    EditText barcodeWidth;

    @OnFocusChange(R.id.barcodeWidth)
    void barcodeWidth_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> float -> string) and update the map
                preservedInput.put(barcodeWidth, String.valueOf(Float.parseFloat(barcodeWidth.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                barcodeWidth.setText(preservedInput.get(barcodeWidth));
            }
        }
    }
    
    @InjectView(R.id.barcodeHeight)
    EditText barcodeHeight;

    @OnFocusChange(R.id.barcodeHeight)
    void barcodeHeight_onFocusChanged(boolean focused)
    {
        if (!focused)
        {
            try
            {
                /// Perform a triple conversion (string -> float -> string) and update the map
                preservedInput.put(barcodeHeight, String.valueOf(Float.parseFloat(barcodeHeight.getText().toString())));
            }
            catch (NumberFormatException nfe)
            {
                barcodeHeight.setText(preservedInput.get(barcodeHeight));
            }
        }
    }

    @OnClick(R.id.apply)
    void onApplyButtonClicked()
    {
        this.pageSetup = new Media(new Media.Configuration()
                .setPageWidth(Float.parseFloat(pageWidth.getText().toString()))
                .setPageHeight(Float.parseFloat(pageHeight.getText().toString()))
                .setOrientation(portrait.isChecked() ? PORTRAIT : LANDSCAPE)
                .setMarginLeft(Float.parseFloat(marginLeft.getText().toString()))
                .setMarginRight(Float.parseFloat(marginRight.getText().toString()))
                .setMarginTop(Float.parseFloat(marginTop.getText().toString()))
                .setMarginBottom(Float.parseFloat(marginBottom.getText().toString()))
                .setColumnPadding(Float.parseFloat(columnPadding.getText().toString()))
                .setRowPadding(Float.parseFloat(rowPadding.getText().toString()))
                .setNumHorizontalBarCodes(Integer.parseInt(horizontalBarcodeCount.getText().toString()))
                .setNumVerticalBarCodes(Integer.parseInt(verticalBarcodeCount.getText().toString()))
                .setBarCodeWidth(Float.parseFloat(barcodeWidth.getText().toString()))
                .setBarCodeHeight(Float.parseFloat(barcodeHeight.getText().toString())));
        Toast.makeText(this, R.string.printing_media_settings_configuration_saved, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed()
    {
        Intent result = new Intent();
        result.putExtra(super.getString(R.string.print_media_settings_extra_key), pageSetup);
        setResult(Activity.RESULT_OK, result);

        super.onBackPressed();
    }

    @OnClick(R.id.preview)
    void onPreviewButtonClicked()
    {
        Intent intent = new Intent(this, MediaPreview.class);
        intent.putExtra(super.getString(R.string.print_media_settings_extra_key), pageSetup);
        startActivity(intent);
    }

    @OnClick(R.id.reset)
    void onResetButtonClicked()
    {
        pageSetup = new Media(new Media.Configuration());
        updateGUI();
    }

    /// ButterKnife -->

    /// A mapping of the last known valid inputs for each view
    private final Map<View, String> preservedInput = new HashMap<View, String>();

    /// The printing media that is being configured
    private Media pageSetup;

    /**
     * Brings the GUI up to speed with the PrintingMedia model
     */
    private void updateGUI()
    {
        pageWidth.setText(Float.toString(pageSetup.pageWidth));
        pageHeight.setText(Float.toString(pageSetup.pageHeight));
        portrait.setChecked(pageSetup.orientation == PORTRAIT);
        landscape.setChecked(pageSetup.orientation == LANDSCAPE);
        marginLeft.setText(Float.toString(pageSetup.marginLeft));
        marginRight.setText(Float.toString(pageSetup.marginRight));
        marginTop.setText(Float.toString(pageSetup.marginTop));
        marginBottom.setText(Float.toString(pageSetup.marginBottom));
        columnPadding.setText(Float.toString(pageSetup.columnPadding));
        rowPadding.setText(Float.toString(pageSetup.rowPadding));
        horizontalBarcodeCount.setText(Integer.toString(pageSetup.numHorizontalBarCodes));
        verticalBarcodeCount.setText(Integer.toString(pageSetup.numVerticalBarCodes));
        barcodeWidth.setText(Float.toString(pageSetup.barcodeWidth));
        barcodeHeight.setText(Float.toString(pageSetup.barcodeHeight));
    }

    /**
     * Populates the preserved input mapping model
     */
    private void initPreservedInput()
    {
        preservedInput.put(pageWidth, String.valueOf(pageSetup.pageWidth));
        preservedInput.put(pageHeight, String.valueOf(pageSetup.pageHeight));
        preservedInput.put(marginLeft, String.valueOf(pageSetup.marginLeft));
        preservedInput.put(marginRight, String.valueOf(pageSetup.marginRight));
        preservedInput.put(marginTop, String.valueOf(pageSetup.marginTop));
        preservedInput.put(marginBottom, String.valueOf(pageSetup.marginBottom));
        preservedInput.put(rowPadding, String.valueOf(pageSetup.rowPadding));
        preservedInput.put(columnPadding, String.valueOf(pageSetup.columnPadding));
        preservedInput.put(barcodeWidth, String.valueOf(pageSetup.barcodeWidth));
        preservedInput.put(barcodeHeight, String.valueOf(pageSetup.barcodeHeight));
    }

    /**
     * @see Activity#onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_setup);

        ButterKnife.inject(this);

        this.pageSetup = (Media) getIntent().getSerializableExtra(super.getString(R.string.print_media_settings_extra_key));
        initPreservedInput();
        updateGUI();
    }
}
