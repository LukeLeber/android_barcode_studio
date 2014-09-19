// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.rendering;

import com.lukeleber.util.EpsilonEquals;

/**
 * A {@link RuntimeException} that is thrown when a particular {@link
 * com.lukeleber.barcodestudio.printing.Media} configuration and {@link
 * com.lukeleber.barcodestudio.Barcode} encoding come together in such a way that there is not
 * enough room for printers to reliably print with enough precision that scanning hardware can
 * distinguish the resulting barcode.
 * <p/>
 * More or less, this exception exists to prevent wasting expensive printing media.
 * <p/>
 * TODO: Externalize strings
 */
public class PrecisionException
        extends RuntimeException
{

    private final int requiredExtension;

    /**
     * Constructs a {@link PrecisionException} with the provided <tt>x</tt> factor
     *
     * @param x
     *         the <tt>x</tt> factor of the setup
     */
    public PrecisionException(float x)
    {
        super("To ensure precision, make the barcode at least ~" +
                (int) Math.ceil(((1 / x) * 100.0f) - 100.0f) +
                "% wider or try a more compact symbology.");
        this.requiredExtension =
                EpsilonEquals.equals(x, 0) ? 0 : (int) Math.ceil(((1 / x) * 100.0f) - 100.0f);

    }

    /**
     * Retrieves the percentage that the barcode must be extended in order to successfully render an
     * encoding to.
     *
     * @return the percentage that the barcode must be extended
     */
    public final int getRequiredExtension()
    {
        return requiredExtension;
    }
}

