// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.printing.pdf;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * A slightly-less-skeletal adapter class than the stock adapter provided by the Android SDK.
 * <p/>
 * This should help to eliminate boilerplate associated with printing to PDF files.
 * <p/>
 * <strong>This implementation is not thread-safe.</strong>
 */
@TargetApi(19)
public abstract class PDFDocumentAdapter
        extends PrintDocumentAdapter
{
    /// The context associated with this adapter
    private final Context context;

    /// The default file name to show in the save-file dialog
    private final String defaultFileName;

    /// The current PDF document that is being operated on
    private PrintedPdfDocument doc;

    /// The current page count
    private int pageCount;

    /**
     * Constructs a PDFDocumentAdapter with the provided associated Context and default file name
     *
     * @param context
     *         the parent Context
     * @param defaultFileName
     *         the default file name to display in the save-file dialog
     */
    protected PDFDocumentAdapter(Context context, String defaultFileName)
    {
        this.context = context;
        this.defaultFileName = defaultFileName;
    }

    /**
     * Constructs a PDFDocumentAdapter with the provided Context.  Note that the default file name
     * is set to the number of milliseconds that have elapsed since the epoch.
     *
     * @param context
     *         the parent Context
     */
    @SuppressWarnings("unused")
    protected PDFDocumentAdapter(Context context)
    {
        this(context, new Date().getTime() + ".pdf");
    }

    /**
     * Does the target page exist within any of the provided ranges?
     *
     * @param target
     *         the target page
     * @param ranges
     *         the ranges to search
     *
     * @return true if the target page exists within any provided range, otherwise false
     */
    private static boolean inRange(int target, PageRange... ranges)
    {
        for (PageRange range : ranges)
        {
            if (range.getStart() <= target && range.getEnd() >= target)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the number of pages that shall be created
     *
     * @return the number of pages contained in this document
     */
    protected abstract int calculatePageCount();

    /**
     * Performs the actual drawing to the requested Page
     *
     * @param page
     *         the Page that is being drawn
     */
    protected abstract void drawPage(PdfDocument.Page page);

    /**
     * {@inheritDoc}
     */
    @SuppressLint("Override")
    @Override
    public void onLayout(PrintAttributes oldAttributes,
                         PrintAttributes newAttributes,
                         CancellationSignal cancellationSignal,
                         LayoutResultCallback callback,
                         Bundle metadata)
    {
        if (cancellationSignal.isCanceled())
        {
            callback.onLayoutCancelled();
            return;
        }
        this.doc = new PrintedPdfDocument(context, newAttributes);
        this.pageCount = calculatePageCount();
        callback.onLayoutFinished(
                new PrintDocumentInfo.Builder(defaultFileName)
                        .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                        .setPageCount(this.pageCount).build(), true
        );
    }

    /**
     * {@inheritDoc}
     */
    @SuppressLint("Override")
    @Override
    public void onWrite(final PageRange[] pageRanges,
                        final ParcelFileDescriptor destination,
                        final CancellationSignal cancellationSignal,
                        final WriteResultCallback callback)
    {
        for (int i = 0; i < pageCount; i++)
        {
            if (!inRange(i, pageRanges))
            {
                continue;
            }
            PdfDocument.Page page = doc.startPage(i);
            if (cancellationSignal.isCanceled())
            {
                callback.onWriteCancelled();
                doc.close();
                return;
            }
            drawPage(page);
            doc.finishPage(page);
        }
        try
        {
            doc.writeTo(new FileOutputStream(destination.getFileDescriptor()));
        }
        catch (IOException ioe)
        {
            callback.onWriteFailed(ioe.toString());
            return;
        }
        finally
        {
            doc.close();
        }
        callback.onWriteFinished(pageRanges);
    }
}
