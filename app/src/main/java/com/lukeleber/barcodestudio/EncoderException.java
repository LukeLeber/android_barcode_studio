// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________
package com.lukeleber.barcodestudio;

/**
 * An exception that is thrown when a symbology fails to encode a provided text string.
 */
public class EncoderException
        extends RuntimeException
{
    /**
     * Constructs an {@link com.lukeleber.barcodestudio.EncoderException}
     *
     * @param message
     *         a brief description of the encoding error
     */
    public EncoderException(String message)
    {
        super(message);
    }
}
