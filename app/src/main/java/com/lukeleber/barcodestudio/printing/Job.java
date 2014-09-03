// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________
package com.lukeleber.barcodestudio.printing;

import com.lukeleber.barcodestudio.Barcode;
import com.lukeleber.barcodestudio.Symbology;

import java.io.Serializable;

/**
 * A {@link Job} represents a group of one or more {@link Barcode barcodes} together with
 * the {@link Media} on which they are to be printed.
 *
 */
public abstract class Job implements Iterable<Barcode>, Serializable
{
    /// The {@link PrintingMedia} that this {@link Job} is to be printed on
    private final Media media;

    /**
     * Constructs a {@link Job} with the provided {@link Media}
     *
     * @param media The {@link Media} that this {@link Job} is to be printed on
     */
    protected Job(Media media)
    {
        this.media = media;
    }

    /**
     * Retrieves the {@link Media} that this {@link Job} is to be printed on
     * @return the {@link Media} that this {@link Job} is to be printed on
     */
    public final Media getMedia()
    {
        return media;
    }

    /**
     * Retrieves the total number of {@link Barcode barcodes} to be printed
     *
     * @return the total number of {@link Barcode barcodes} to be printed
     */
    public abstract int size();

    /**
     * Retrieves the {@link Symbology} of the {@link Barcode barcodes} to be printed
     *
     * @return the {@link Symbology} of the {@link Barcode barcodes} to be printed
     */
    public abstract Symbology getSymbology();
}
