package com.lukeleber.barcodestudio.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import com.lukeleber.barcodestudio.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PageSetup
        extends Activity
{
    private com.lukeleber.barcodestudio.rendering.PageSetup pageSetup;

    @InjectView(R.id.pageWidth)
    EditText pageWidth;

    @InjectView(R.id.pageHeight)
    EditText pageHeight;

    @InjectView(R.id.portrait)
    RadioButton portrait;

    @InjectView(R.id.landscape)
    RadioButton landscape;

    @InjectView(R.id.marginLeft)
    EditText leftMargin;

    @InjectView(R.id.marginRight)
    EditText rightMargin;

    @InjectView(R.id.marginTop)
    EditText topMargin;

    @InjectView(R.id.marginBottom)
    EditText bottomMargin;

    @InjectView(R.id.columnPadding)
    EditText columnPadding;

    @InjectView(R.id.rowPadding)
    EditText rowPadding;

    @InjectView(R.id.horizontalBarcodeCount)
    EditText horizontalBarcodeCount;

    @InjectView(R.id.verticalBarcodeCount)
    EditText verticalBarcodeCount;

    @InjectView(R.id.barcodeWidth)
    EditText barcodeWidth;

    @InjectView(R.id.barcodeHeight)
    EditText barcodeHeight;

    private void updateGUI()
    {
        pageWidth.setText(Float.toString(pageSetup.pageWidth));
        pageHeight.setText(Float.toString(pageSetup.pageHeight));
        portrait.setChecked(pageSetup.orientation == com.lukeleber.barcodestudio.rendering.PageSetup.Orientation.PORTRAIT);
        landscape.setChecked(pageSetup.orientation == com.lukeleber.barcodestudio.rendering.PageSetup.Orientation.LANDSCAPE);
        leftMargin.setText(Float.toString(pageSetup.marginLeft));
        rightMargin.setText(Float.toString(pageSetup.marginRight));
        topMargin.setText(Float.toString(pageSetup.marginTop));
        bottomMargin.setText(Float.toString(pageSetup.marginBottom));
        columnPadding.setText(Float.toString(pageSetup.columnPadding));
        rowPadding.setText(Float.toString(pageSetup.rowPadding));
        horizontalBarcodeCount.setText(Integer.toString(pageSetup.numHorizontalBarCodes));
        verticalBarcodeCount.setText(Integer.toString(pageSetup.numVerticalBarCodes));
        barcodeWidth.setText(Float.toString(pageSetup.barCodeWidth));
        barcodeHeight.setText(Float.toString(pageSetup.barCodeHeight));
    }

    @OnClick(R.id.apply)
    void onApplyButtonClicked()
    {
        Intent result = new Intent();
        result.putExtra("page_setup",
                new com.lukeleber.barcodestudio.rendering.PageSetup(
                        new com.lukeleber.barcodestudio.rendering.PageSetup.Configuration()
                                .setPageWidth(Float.parseFloat(pageWidth.getText().toString()))
                                .setPageHeight(Float.parseFloat(pageHeight.getText().toString()))
                                .setOrientation(portrait.isChecked() ? com.lukeleber.barcodestudio.rendering.PageSetup.Orientation.PORTRAIT : com.lukeleber.barcodestudio.rendering.PageSetup.Orientation.LANDSCAPE)
                                .setMarginLeft(Float.parseFloat(leftMargin.getText().toString()))
                                .setMarginRight(Float.parseFloat(rightMargin.getText().toString()))
                                .setMarginTop(Float.parseFloat(topMargin.getText().toString()))
                                .setMarginBottom(Float.parseFloat(bottomMargin.getText().toString()))
                                .setColumnPadding(Float.parseFloat(columnPadding.getText().toString()))
                                .setRowPadding(Float.parseFloat(rowPadding.getText().toString()))
                                .setNumHorizontalBarCodes(Integer.parseInt(horizontalBarcodeCount.getText().toString()))
                                .setNumVerticalBarCodes(Integer.parseInt(verticalBarcodeCount.getText().toString()))
                                .setBarCodeWidth(Float.parseFloat(barcodeWidth.getText().toString()))
                                .setBarCodeHeight(Float.parseFloat(barcodeHeight.getText().toString()))
                )
        );
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @OnClick(R.id.preview)
    void onPreviewButtonClicked()
    {

    }

    @OnClick(R.id.reset)
    void onResetButtonClicked()
    {
        pageSetup = new com.lukeleber.barcodestudio.rendering.PageSetup(new com.lukeleber.barcodestudio.rendering.PageSetup.Configuration());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_setup);
        ButterKnife.inject(this);
        this.pageSetup = (com.lukeleber.barcodestudio.rendering.PageSetup) getIntent().getSerializableExtra("config");
        updateGUI();
    }

}
