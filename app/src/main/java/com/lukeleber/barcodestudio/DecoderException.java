// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________
package com.lukeleber.barcodestudio;

/**
 * An exception that is thrown when a symbology fails to decode a provided Sequence.
 */
public class DecoderException
        extends RuntimeException
{
    /**
     * Constructs a {@link DecoderException}
     *
     * @param message
     *         a brief description of the decoding error
     */
    public DecoderException(String message)
    {
        super(message);
    }
}
