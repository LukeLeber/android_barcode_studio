<<<<<<< HEAD
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
 * An exception that is thrown when a checksum calculation is attempted, but not supported
 * by a particular symbology.
 */
public class ChecksumNotSupportedException
        extends RuntimeException
{
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    public ChecksumNotSupportedException()
    {
        super("A checksum is not supported for this Symbology.");
    }
}
