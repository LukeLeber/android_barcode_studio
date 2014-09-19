package com.lukeleber.barcodestudio.gui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.rendering.PrintingMedia;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.lukeleber.barcodestudio.rendering.PrintingMedia.Orientation.PORTRAIT;

public class PrintingMediaPreview
        extends Activity
{

    /// <-- ButterKnife

    @InjectView(R.id.pageSettingsPreview)
    ImageView preview;

    /// ButterKnife -->

    /// Default 100x magnification
    private final static float MAGNIFICATION = 100.0f;

    /// The Paint that is used to etch out the preview boundaries
    private final static Paint BLACK = new Paint(Color.BLACK);

    /// Hard-coded configuration of the {@link DashPathEffect} of the preview
    private final static float DASH_LENGTH = 30;
    private final static float BLANK_LENGTH = 10;
    private final static int INTERVAL_PHASE = 1;

    static
    {
        BLACK.setPathEffect(new DashPathEffect(new float[]
                {DASH_LENGTH,
                        BLANK_LENGTH}, INTERVAL_PHASE));
        BLACK.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_setup_preview);
        ButterKnife.inject(this);

        Toast.makeText(this, R.string.printing_media_preview_toast, Toast.LENGTH_SHORT).show();

        /// Pretty much just magnify the page config by 100x (8 x 11 --> 800 x 1100 resolution).
        /// The page units are fairly meaningless at this point, all we care about are the ratios.
        ///
        /// Also, if >100x magnification is required, then the scanner tolerance will certainly
        /// be exceeded at this point.
        PrintingMedia pageSetup = ((PrintingMedia)getIntent().getSerializableExtra(super.getString(R.string.print_media_settings_extra_key)))
                                    .scale(MAGNIFICATION);

        Bitmap bitmap = Bitmap.createBitmap(
                                    pageSetup.orientation == PORTRAIT
                                            ? (int)pageSetup.pageWidth : (int)pageSetup.pageHeight,
                                    pageSetup.orientation == PORTRAIT
                                            ? (int)pageSetup.pageHeight : (int)pageSetup.pageWidth,
                                    Bitmap.Config.ARGB_8888);
        {
            Canvas canvas = new Canvas(bitmap);

            /// Simply draw empty dashed boxes where the barcodes are expected to be
            float x = pageSetup.marginLeft;
            float y = pageSetup.marginTop;
            for (int i = 0; i < pageSetup.numHorizontalBarCodes; ++i)
            {
                for (int j = 0; j < pageSetup.numVerticalBarCodes; ++j)
                {
                    canvas.drawRect(x,
                            y,
                            x + pageSetup.barcodeWidth,
                            y + pageSetup.barcodeHeight, BLACK);
                    y += (pageSetup.barcodeHeight + pageSetup.columnPadding);
                }
                y = pageSetup.marginTop;
                x += (pageSetup.barcodeWidth + pageSetup.rowPadding);
            }
        }
        preview.setImageBitmap(bitmap);
    }
}
