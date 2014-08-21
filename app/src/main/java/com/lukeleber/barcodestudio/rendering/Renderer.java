/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 * <p/>
 * Copyright Luke <LukeLeber@gmail.com> 8/19/2014.
 */
package com.lukeleber.barcodestudio.rendering;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.lukeleber.barcodestudio.Barcode;
import com.lukeleber.barcodestudio.Job;
import com.lukeleber.barcodestudio.Module;

import java.util.Iterator;

public class Renderer
{
    private final static Paint BLACK = new Paint(Color.BLACK);
    private final static Paint WHITE = new Paint(Color.WHITE);

    static
    {
        WHITE.setARGB(255, 255, 255, 255);
    }
    private final Job job;

    public Renderer(Job job)
    {
        this.job = job;
    }

    private void renderBarcode(Canvas canvas, Barcode barcode, float x, float y, float w, float h)
    {
        if(barcode.getEncoding().size() > 0)
        {
            /// Calculate the 'x' factor (to see if we can even print this barcode)
            float xFactor = 0.0f;
            for (Module module : barcode.getEncoding())
            {
                xFactor += module.getWeight();
            }
            xFactor = w / xFactor;
            if(xFactor < 1.0f)
            {
                throw new PrecisionException();
            }
            for(Module module : barcode.getEncoding())
            {
                float diff = module.getWeight() * xFactor;
                if(module.isOn())
                {
                    canvas.drawRect(x, y, x + diff, y + h, BLACK);
                }
                x += diff;
            }
        }
    }

    public void render(Canvas canvas)
    {
        int preserved = canvas.save();
        PrintingMedia media = job.getMedia();
        canvas.drawRect(media.marginLeft, media.marginTop, media.pageWidth + media.marginLeft, media.pageHeight + media.marginTop, WHITE);

        float docStartX = media.marginLeft * 2;
        float docStartY = media.marginTop * 2;
        int row = 0, column = 0;
        Iterator<Barcode> iter = job.iterator();
        while(iter.hasNext())
        {
            if(column >= media.numHorizontalBarCodes)
            {
                column = 0;
                ++row;
            }
            /// TODO: Finish Renderng?
            Barcode barcode = iter.next();
            try
            {
                renderBarcode(
                        canvas,
                        barcode,
                        docStartX + (column * media.barcodeWidth) + ((column) * media.columnPadding),
                        docStartY + (row * media.barcodeHeight) + ((row) * media.rowPadding),
                        media.barcodeWidth,
                        media.barcodeHeight);
                ++column;
            }
            catch(PrecisionException pe)
            {
                /// *undo*
                canvas.restoreToCount(preserved);
                throw pe;
            }
        }
    }
}
