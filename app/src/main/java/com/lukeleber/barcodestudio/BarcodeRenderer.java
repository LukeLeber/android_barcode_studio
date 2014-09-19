/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BarcodeRenderer
        extends ImageView
{
    private Barcode barcode;

    public BarcodeRenderer(Context context)
    {
        super(context);
    }

    public BarcodeRenderer(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public BarcodeRenderer(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public void setBarcode(Barcode barcode)
    {
        this.barcode = barcode;
        super.invalidate();
    }

    @Override
    public final void onDraw(Canvas c)
    {
        Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        if (barcode != null)
        {
            int x = 2;
            Paint black = new Paint(Color.BLACK);
            float startX = 50;
            float startY = 50;
            for (Module module : barcode.getEncoding())
            {
                if (module.isOn())
                {
                    c.drawRect(startX, startY, startX + module.getWeight() * x, startY + 50, black);
                }
                startX += (module.getWeight() * x);
            }
            c.drawText(barcode.getText(), startX, startY + 55, black);
        }
    }
}
