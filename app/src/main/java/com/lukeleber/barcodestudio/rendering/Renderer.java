<<<<<<< HEAD
// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________
package com.lukeleber.barcodestudio.rendering;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.lukeleber.barcodestudio.Barcode;
import com.lukeleber.barcodestudio.Module;
import com.lukeleber.barcodestudio.printing.Job;
import com.lukeleber.barcodestudio.printing.Media;

/**
 * {@link com.lukeleber.barcodestudio.rendering.Renderer Renderers} are tasked with projecting a
 * {@link com.lukeleber.barcodestudio.printing.Job} onto a {@link android.graphics.Canvas}. There
 * really isn't much more to say.  Just draws barcodes or fails with a {@link
 * com.lukeleber.barcodestudio.rendering.PrecisionException} upon failure.
 * <p/>
 * One thing worth noting is the protected method {@link Renderer#renderExtras(android.graphics.Canvas,
 * com.lukeleber.barcodestudio.Barcode, float, float, float, float)} which can be overridden in
 * order to render "extras" specific to whatever particular symbology is being worked with.  One
 * example that comes to mind is EAN-13 (a subset of UPC-A) that historically prints plain-text
 * region codes and checksum digits.
 */
public class Renderer
{
    /// White {@link android.graphics.Paint}
    private final static Paint WHITE = new Paint(Color.WHITE);

    /// Black {@link android.graphics.Paint}
    private final static Paint BLACK = new Paint(Color.BLACK);

    /// TODO: (Android Bug?) Color.WHITE giving unexpected results without tweaking it...
    static
    {
        WHITE.setARGB(255, 255, 255, 255);
    }

    /// The {@link com.lukeleber.barcodestudio.printing.Job} to render
    private final Job job;

    /**
     * Constructs a {@link com.lukeleber.barcodestudio.rendering.Renderer} with the provided {@link
     * com.lukeleber.barcodestudio.printing.Job}.
     *
     * @param job
     *         the {@link com.lukeleber.barcodestudio.printing.Job} to render
     */
    public Renderer(Job job)
    {
        this.job = job;
    }

    /**
     * Renders any "extras" associated with the provided {@link com.lukeleber.barcodestudio.Barcode}
     * to the provided {@link android.graphics.Canvas} <br> <strong>IMPORTANT: Any overrides of this
     * method are only permitted to throw exceptions that derive from {@link
     * com.lukeleber.barcodestudio.rendering.PrecisionException}.</strong>
     *
     * @param canvas
     *         the {@link android.graphics.Canvas} to render to
     * @param barcode
     *         the {@link com.lukeleber.barcodestudio.Barcode} to render extras for
     * @param x
     *         the x-coordinate of the upper-left hand corner of the rendering region
     * @param y
     *         the y-coordinate of the upper-left hand corner of the rendering region
     * @param w
     *         the width of the rendering region
     * @param h
     *         the height of the rendering region
     *
     * @throws PrecisionException
     *         if the provided rendering region is too small to precisely render the "extras"
     *         inside.
     */
    protected /*abstract*/ void renderExtras(
            @SuppressWarnings("unused")Canvas canvas,
            @SuppressWarnings("unused")Barcode barcode,
            @SuppressWarnings("unused")float x,
            @SuppressWarnings("unused")float y,
            @SuppressWarnings("unused")float w,
            @SuppressWarnings("unused")float h)
    {
        /* Intentionally Empty */
    }

    /**
     * Renders the provided {@link com.lukeleber.barcodestudio.Barcode} to the provided {@link
     * android.graphics.Canvas}
     *
     * @param canvas
     *         the {@link android.graphics.Canvas} to render to
     * @param barcode
     *         the {@link com.lukeleber.barcodestudio.Barcode} to render
     * @param x
     *         the x-coordinate of the upper-left hand corner of the rendering region
     * @param y
     *         the y-coordinate of the upper-left hand corner of the rendering region
     * @param w
     *         the width of the rendering region
     * @param h
     *         the height of the rendering region
     *
     * @throws PrecisionException
     *         if the provided rendering region is too small to precisely render the provided {@link
     *         com.lukeleber.barcodestudio.Barcode} inside.
     */
    private void renderBarcode(Canvas canvas, Barcode barcode, float x, float y, float w, float h)
    {
        if (barcode.getEncoding().size() > 0)
        {
            /// Calculate the 'x' factor (to see if we can even print this barcode)
            float xFactor = 0.0f;
            for (Module module : barcode.getEncoding())
            {
                xFactor += module.getWeight();
            }
            xFactor = w / xFactor;
            if (xFactor < 1.0f)
            {
                throw new PrecisionException(xFactor);
            }
            for (Module module : barcode.getEncoding())
            {
                float diff = module.getWeight() * xFactor;
                if (module.isOn())
                {
                    canvas.drawRect(x, y, x + diff, y + h, BLACK);
                }
                x += diff;
            }
        }
    }

    /**
     * Renders this {@link Renderer renderer's} {@link com.lukeleber.barcodestudio.printing.Job} to
     * the provided {@link android.graphics.Canvas}
     *
     * @param canvas
     *         the {@link android.graphics.Canvas} to render to
     *
     * @throws PrecisionException
     *         if the provided rendering region is too small to precisely render the provided {@link
     *         com.lukeleber.barcodestudio.Barcode} inside.
     */
    public final void render(Canvas canvas)
    {
        int preserved = canvas.save();
        Media media = job.getMedia();
        canvas.drawRect(
                media.marginLeft,
                media.marginTop,
                media.pageWidth + media.marginLeft,
                media.pageHeight + media.marginTop,
                WHITE);

        float docStartX = media.marginLeft * 2;
        float docStartY = media.marginTop * 2;
        int row = 0, column = 0;
        for (Barcode barcode : job)
        {
            if (column >= media.numHorizontalBarCodes)
            {
                column = 0;
                ++row;
            }
            try
            {
                float x = docStartX + (column * media.barcodeWidth) + ((column) * media.columnPadding);
                float y = docStartY + (row * media.barcodeHeight) + ((row) * media.rowPadding);
                renderBarcode(
                        canvas,
                        barcode,
                        x,
                        y,
                        media.barcodeWidth,
                        media.barcodeHeight);
                renderExtras(
                        canvas,
                        barcode,
                        x,
                        y,
                        media.barcodeWidth,
                        media.barcodeHeight);
                ++column;
            }
            catch (PrecisionException pe)
=======
<<<<<<< HEAD
// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________
package com.lukeleber.barcodestudio.rendering;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.lukeleber.barcodestudio.Barcode;
import com.lukeleber.barcodestudio.Module;
import com.lukeleber.barcodestudio.printing.Job;
import com.lukeleber.barcodestudio.printing.Media;

/**
 * {@link com.lukeleber.barcodestudio.rendering.Renderer Renderers} are tasked with projecting a
 * {@link com.lukeleber.barcodestudio.printing.Job} onto a {@link android.graphics.Canvas}. There
 * really isn't much more to say.  Just draws barcodes or fails with a {@link
 * com.lukeleber.barcodestudio.rendering.PrecisionException} upon failure.
 * <p/>
 * One thing worth noting is the protected method {@link Renderer#renderExtras(android.graphics.Canvas,
 * com.lukeleber.barcodestudio.Barcode, float, float, float, float)} which can be overridden in
 * order to render "extras" specific to whatever particular symbology is being worked with.  One
 * example that comes to mind is EAN-13 (a subset of UPC-A) that historically prints plain-text
 * region codes and checksum digits.
 */
public class Renderer
{
    /// White {@link android.graphics.Paint}
    private final static Paint WHITE = new Paint(Color.WHITE);

    /// Black {@link android.graphics.Paint}
    private final static Paint BLACK = new Paint(Color.BLACK);

    /// TODO: (Android Bug?) Color.WHITE giving unexpected results without tweaking it...
    static
    {
        WHITE.setARGB(255, 255, 255, 255);
    }

    /// The {@link com.lukeleber.barcodestudio.printing.Job} to render
    private final Job job;

    /**
     * Constructs a {@link com.lukeleber.barcodestudio.rendering.Renderer} with the provided {@link
     * com.lukeleber.barcodestudio.printing.Job}.
     *
     * @param job
     *         the {@link com.lukeleber.barcodestudio.printing.Job} to render
     */
    public Renderer(Job job)
    {
        this.job = job;
    }

    /**
     * Renders any "extras" associated with the provided {@link com.lukeleber.barcodestudio.Barcode}
     * to the provided {@link android.graphics.Canvas} <br> <strong>IMPORTANT: Any overrides of this
     * method are only permitted to throw exceptions that derive from {@link
     * com.lukeleber.barcodestudio.rendering.PrecisionException}.</strong>
     *
     * @param canvas
     *         the {@link android.graphics.Canvas} to render to
     * @param barcode
     *         the {@link com.lukeleber.barcodestudio.Barcode} to render extras for
     * @param x
     *         the x-coordinate of the upper-left hand corner of the rendering region
     * @param y
     *         the y-coordinate of the upper-left hand corner of the rendering region
     * @param w
     *         the width of the rendering region
     * @param h
     *         the height of the rendering region
     *
     * @throws PrecisionException
     *         if the provided rendering region is too small to precisely render the "extras"
     *         inside.
     */
    protected /*abstract*/ void renderExtras(
            @SuppressWarnings("unused")Canvas canvas,
            @SuppressWarnings("unused")Barcode barcode,
            @SuppressWarnings("unused")float x,
            @SuppressWarnings("unused")float y,
            @SuppressWarnings("unused")float w,
            @SuppressWarnings("unused")float h)
    {
        /* Intentionally Empty */
    }

    /**
     * Renders the provided {@link com.lukeleber.barcodestudio.Barcode} to the provided {@link
     * android.graphics.Canvas}
     *
     * @param canvas
     *         the {@link android.graphics.Canvas} to render to
     * @param barcode
     *         the {@link com.lukeleber.barcodestudio.Barcode} to render
     * @param x
     *         the x-coordinate of the upper-left hand corner of the rendering region
     * @param y
     *         the y-coordinate of the upper-left hand corner of the rendering region
     * @param w
     *         the width of the rendering region
     * @param h
     *         the height of the rendering region
     *
     * @throws PrecisionException
     *         if the provided rendering region is too small to precisely render the provided {@link
     *         com.lukeleber.barcodestudio.Barcode} inside.
     */
    private void renderBarcode(Canvas canvas, Barcode barcode, float x, float y, float w, float h)
    {
        if (barcode.getEncoding().size() > 0)
        {
            /// Calculate the 'x' factor (to see if we can even print this barcode)
            float xFactor = 0.0f;
            for (Module module : barcode.getEncoding())
            {
                xFactor += module.getWeight();
            }
            xFactor = w / xFactor;
            if (xFactor < 1.0f)
            {
                throw new PrecisionException(xFactor);
            }
            for (Module module : barcode.getEncoding())
            {
                float diff = module.getWeight() * xFactor;
                if (module.isOn())
                {
                    canvas.drawRect(x, y, x + diff, y + h, BLACK);
                }
                x += diff;
            }
        }
    }

    /**
     * Renders this {@link Renderer renderer's} {@link com.lukeleber.barcodestudio.printing.Job} to
     * the provided {@link android.graphics.Canvas}
     *
     * @param canvas
     *         the {@link android.graphics.Canvas} to render to
     *
     * @throws PrecisionException
     *         if the provided rendering region is too small to precisely render the provided {@link
     *         com.lukeleber.barcodestudio.Barcode} inside.
     */
    public final void render(Canvas canvas)
    {
        int preserved = canvas.save();
        Media media = job.getMedia();
        canvas.drawRect(
                media.marginLeft,
                media.marginTop,
                media.pageWidth + media.marginLeft,
                media.pageHeight + media.marginTop,
                WHITE);

        float docStartX = media.marginLeft * 2;
        float docStartY = media.marginTop * 2;
        int row = 0, column = 0;
        for (Barcode barcode : job)
        {
            if (column >= media.numHorizontalBarCodes)
            {
                column = 0;
                ++row;
            }
            try
            {
                float x = docStartX + (column * media.barcodeWidth) + ((column) * media.columnPadding);
                float y = docStartY + (row * media.barcodeHeight) + ((row) * media.rowPadding);
                renderBarcode(
                        canvas,
                        barcode,
                        x,
                        y,
                        media.barcodeWidth,
                        media.barcodeHeight);
                renderExtras(
                        canvas,
                        barcode,
                        x,
                        y,
                        media.barcodeWidth,
                        media.barcodeHeight);
                ++column;
            }
            catch (PrecisionException pe)
=======
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
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
            {
                /// *undo*
                canvas.restoreToCount(preserved);
                throw pe;
            }
        }
    }
}
