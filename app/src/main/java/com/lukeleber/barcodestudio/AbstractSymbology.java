<<<<<<< HEAD
// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio;

/**
 * A skeletal base class that acts to minimize boilerplate code common to all linear barcodes.
 */
public abstract class AbstractSymbology
        implements Symbology
{

    private final Configuration configuration;

    /**
     * Constructs an AbstractSymbology with the provided configuration
     *
     * @param config
     *         the configuration object that defines the options of this symbology
=======
<<<<<<< HEAD
// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio;

/**
 * A skeletal base class that acts to minimize boilerplate code common to all linear barcodes.
 */
public abstract class AbstractSymbology
        implements Symbology
{

    private final Configuration configuration;

    /**
     * Constructs an AbstractSymbology with the provided configuration
     *
     * @param config
     *         the configuration object that defines the options of this symbology
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
 * A skeletal base class that acts to minimize boilerplate code common to all linear barcodes.
 */
public abstract class AbstractSymbology
        implements Symbology
{

    private final Configuration configuration;

    /**
     * Constructs an AbstractSymbology with the provided configuration
     *
     * @param config the configuration object that defines the options of this symbology
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
     */
    protected AbstractSymbology(Configuration config)
    {
        this.configuration = config;
    }

    /**
     * Retrieves the {@link Configuration} of this symbology.
     *
     * @return the Configuration of this symbology.
     */
    @Override
    public final Configuration getConfig()
    {
        return configuration;
    }
}
