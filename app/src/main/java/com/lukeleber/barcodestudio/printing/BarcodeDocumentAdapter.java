// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.printing;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.pdf.PdfDocument;

import com.lukeleber.barcodestudio.Barcode;
import com.lukeleber.barcodestudio.rendering.Renderer;
import com.lukeleber.printing.pdf.PDFDocumentAdapter;

/**
 * A {@link PDFDocumentAdapter} specific to rendering barcodes.
 */
@TargetApi(19)
public class BarcodeDocumentAdapter
        extends PDFDocumentAdapter
{

    /// The individual page {@link Renderer renderers}
    private final Renderer[] renderers;

    /**
     * Constructs a {@link com.lukeleber.barcodestudio.printing.BarcodeDocumentAdapter}
     *
     * @param context
     *         the context that invoked this service
     * @param defaultFileName
     *         the default file name that is shown in the save-file dialog
     * @param job
     *         the {@link Job} that is being printed
     */
    public BarcodeDocumentAdapter(Context context, String defaultFileName, Job job)
    {
        super(context, defaultFileName);
        this.renderers = pagify(job);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int calculatePageCount()
    {
        return renderers.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void drawPage(PdfDocument.Page page)
    {
        renderers[page.getInfo().getPageNumber()].render(page.getCanvas());
    }

    /**
     * Splits the provided {@link Job} into individual page {@link Renderer renderers}
     *
     * @param job
     *         the monolithic {@link Job} to break down
     *
     * @return an array of individual page {@link Renderer renderers}
     */
    private Renderer[] pagify(Job job)
    {
        Media media = job.getMedia();
        int perPage = media.numHorizontalBarCodes * media.numVerticalBarCodes;
        Renderer[] rv = new Renderer[job.size() / perPage];
        if (job instanceof BatchJob)
        {
            int remaining = job.size();
            Barcode barcode = job.iterator().next();
            for (int i = 0; i < rv.length; ++i)
            {
                rv[i] = new Renderer(new BatchJob(media, barcode, remaining >= perPage ? perPage : remaining));
                remaining -= perPage;
            }
        }
        else
        {
            int start = Integer.parseInt(job.iterator().next().getText());
            int end = start + job.size();
            for (int i = 0; i < rv.length; ++i)
            {
                rv[i] = new Renderer(new RangeJob(media, job.getSymbology(), start, start + ((end - start) > perPage ? perPage : (end - start))));
                start += perPage;
            }
        }
        return rv;
    }
}
