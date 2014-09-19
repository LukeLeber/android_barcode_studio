// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________
package com.lukeleber.barcodestudio;

/**
 * An exception that is thrown when a checksum calculation is attempted, but not supported by a
 * particular symbology.
 */
public class ChecksumNotSupportedException
        extends RuntimeException
{
    /**
     * Constructs a {@link com.lukeleber.barcodestudio.ChecksumNotSupportedException}
     */
    public ChecksumNotSupportedException()
    {
        super("A checksum is not supported for this Symbology.");
    }
}
