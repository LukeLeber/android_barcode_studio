// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.gui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import com.lukeleber.app.EnhancedActivity;
import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.printing.Media;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.lukeleber.barcodestudio.printing.Media.Orientation.PORTRAIT;

/**
 * An {@link Activity} that shows a graphical layout of a {@link com.lukeleber.barcodestudio.printing.Media}.
 */
public class MediaPreview
        extends EnhancedActivity
{

    /// The conversion factor between inches and postscript units
    private final static float POSTSCRIPT_CONVERSION_FACTOR = 72.0f;

    /// ButterKnife -->
    /// The Paint that is used to etch out the preview boundaries
    private final static Paint BLACK;

    private final static Paint WHITE;

    /// Reasonable Defaults
    static
    {
        BLACK = new Paint(Color.BLACK);
        BLACK.setStrokeWidth(2.0f);
        BLACK.setPathEffect(new DashPathEffect(new float[]{30, 10}, 1));
        BLACK.setStyle(Paint.Style.STROKE);

        WHITE = new Paint(Color.WHITE);
        WHITE.setARGB(255, 255, 255, 255);
        WHITE.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    /// <-- ButterKnife


    /// The {@link ImageView} that we display the layout on
    @InjectView(R.id.pageSettingsPreview)
    ImageView preview;

    /// ButterKnife -->

    /**
     * {@inheritDoc}
     */
    @TargetApi(18)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_setup_preview);
        ButterKnife.inject(this);

        shortToast(R.string.printing_media_preview_toast);

        /// Scale the bitmap from inches to postscript units
        Media pageSetup =
                ((Media) getIntent()
                        .getSerializableExtra(
                                super.getString(R.string.print_media_settings_extra_key)))
                        .scale(POSTSCRIPT_CONVERSION_FACTOR);

        int width =
            (int)(pageSetup.orientation == PORTRAIT ? pageSetup.pageWidth : pageSetup.pageHeight);

        int height =
            (int)(pageSetup.orientation == PORTRAIT ? pageSetup.pageHeight : pageSetup.pageWidth);


        preview.setBackgroundColor(Color.BLACK);

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        {
            Canvas canvas = new Canvas(bitmap);

            /// Draw the 'page'
            canvas.drawRect(0, 0, bitmap.getWidth(), bitmap.getHeight(), WHITE);

            /// Draw empty dashed boxes where the barcodes are expected to be
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
        /// Apply the image to our view
        preview.setImageBitmap(bitmap);
    }
}
