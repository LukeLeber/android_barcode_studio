/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 *
 * Copyright Luke <LukeLeber@gmail.com> 8/10/2014.
 *
 */
package com.lukeleber.barcodestudio;

/**
 * An exception that is thrown when a symbology fails to encode a provided text string.
 */
public class EncoderException
        extends RuntimeException
{
    public EncoderException(String message)
    {
        super(message);
    }
}
