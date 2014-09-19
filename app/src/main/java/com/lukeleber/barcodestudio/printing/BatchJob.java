// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.printing;

import com.lukeleber.barcodestudio.Barcode;
import com.lukeleber.barcodestudio.Symbology;

import java.util.Iterator;

/**
 * A type of {@link Job} that consists of a single barcode repeated an arbitrary number of times.
 */
public class BatchJob
        extends Job
{
    /// The {@link Barcode} to use for this batch
    private final Barcode barcode;

    /// The size of this batch
    private final int count;

    /**
     * Constructs a {@link BatchJob} with the provided media, barcode, and count
     *
     * @param media
     *         the {@link Media} on which this {@link Job} is to be printed
     * @param barcode
     *         the {@link Barcode} to use
     * @param count
     *         the size of the batch
     */
    public BatchJob(Media media, Barcode barcode, int count)
    {
        super(media);
        System.out.println("Batch job with barcode: " + barcode);
        this.barcode = barcode;
        this.count = count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int size()
    {
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Symbology getSymbology()
    {
        return barcode.getSymbology();
    }

    /**
     * {@inheritDoc}
     */
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
