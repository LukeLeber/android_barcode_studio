/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio;

/**
 * Modules are the individual bars or spaces that make up the smallest data units of a barcode.
 */
public final class Module
{
    /// Is this a bar or a space?
    private final boolean on;

    /// The weight (in units of 'x') of this module
    private final int weight;

    /**
     * Constructs a Module with the provided 'on' flag and weight
     *
     * @param on     is this Module a bar or a space?
     * @param weight the weight (in units of 'x') of this Module
     */
    public Module(boolean on, int weight)
    {
        this.on = on;
        this.weight = weight;
    }

    /**
     * Does this Module represent a bar or a space?
     *
     * @return true if this Module represents a bar, otherwise false
     *
     */
    public boolean isOn()
    {
        return on;
    }

    /**
     * Retrieves the weight (in units of 'x') of this Module
     *
     * @return the weight of this Module
     *
     */
    public int getWeight()
    {
        return weight;
    }
}
