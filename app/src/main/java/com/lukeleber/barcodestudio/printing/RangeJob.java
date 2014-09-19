// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.printing;

import com.lukeleber.barcodestudio.Barcode;
import com.lukeleber.barcodestudio.Symbology;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A type of {@link Job} that consists of a continuous numerical range of {@link Barcode barcodes}.
 *
 */
public class RangeJob extends Job implements Iterable<Barcode>
{
    /**
     * The list of {@link Barcode barcodes} that make up this {@link Job}
     */
    private List<Barcode> barcodes;

    /**
     * Constructs a {@link RangeJob} with the provided {@link Media},
     * {@link Symbology}, and range
     *
     * @param media the {@link Media} that this {@link Job} is to be printed on
     *
     * @param symbology the {@link Symbology} of the {@link Barcode barcodes} to print
     *
     * @param begin the beginning of the range to print
     *
     * @param end the end of the range to print
     *
     */
    public RangeJob(Media media, Symbology symbology, int begin, int end)
    {
        super(media);
        this.barcodes = new ArrayList<Barcode>(end - begin);
        do
        {
            barcodes.add(new Barcode(String.valueOf(begin), symbology));
        }
        while(begin++ < end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Symbology getSymbology()
    {
        return barcodes.get(0).getSymbology();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size()
    {
        return barcodes.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Barcode> iterator()
    {
        return barcodes.iterator();
    }
}
