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
     */
    protected AbstractSymbology(Configuration config)
    {
        this.configuration = config;
    }

    @Override
    public final Configuration getConfig()
    {
        return configuration;
    }
}
