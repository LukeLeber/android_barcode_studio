<<<<<<< HEAD
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
=======
/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 *
 * Copyright Luke <LukeLeber@gmail.com> 8/10/2014.
 *
 */
package com.lukeleber.barcodestudio;

/**
 * An exception that is thrown when a symbology fails to decode a provided Sequence.
 */
public class DecoderException
        extends RuntimeException
{
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    public DecoderException(String message)
    {
        super(message);
    }
}
