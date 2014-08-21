package com.lukeleber.barcodestudio;

import com.lukeleber.barcodestudio.rendering.PrintingMedia;

import java.util.Iterator;

/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 * <p/>
 * Copyright Luke <LukeLeber@gmail.com> 8/19/2014.
 */
public class BatchJob extends Job
{
    private final Barcode barcode;
    private final int count;

    public BatchJob(PrintingMedia media, Barcode barcode, int count)
    {
        super(media);
        this.barcode = barcode;
        this.count = count;
    }

    public final Barcode getBarcode()
    {
        return barcode;
    }

    public final int size()
    {
        return count;
    }

    public final Symbology getSymbology()
    {
        return barcode.getSymbology();
    }

    @Override
    public Iterator<Barcode> iterator()
    {
        /// Fake iterator that spins on a single barcode (Elegant hack, no?)
        return new Iterator<Barcode>()
        {
            private int count;

            @Override
            public boolean hasNext()
            {
                return this.count < BatchJob.this.count;
            }

            @Override
            public Barcode next()
            {
                ++this.count;
                return BatchJob.this.barcode;
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }
}
