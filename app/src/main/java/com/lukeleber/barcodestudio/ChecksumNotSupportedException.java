/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 *
 * Copyright Luke <LukeLeber@gmail.com> 8/10/2014.
 *
 */
package com.lukeleber.barcodestudio;

/**
 * An exception that is thrown when a checksum calculation is attempted, but not supported
 * by a particular symbology.
 */
public class ChecksumNotSupportedException
        extends RuntimeException
{
    public ChecksumNotSupportedException()
    {
        super("A checksum is not supported for this Symbology.");
    }
}
