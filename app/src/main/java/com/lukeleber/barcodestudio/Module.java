// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio;

import java.io.Serializable;

/**
 * Modules are the individual bars or spaces that make up the smallest data units of a barcode.
 */
public final class Module
        implements Serializable
{
    /// Is this a bar or a space?
    private final boolean on;

    /// The weight (in units of 'x') of this module
    private final int weight;

    /**
     * Constructs a Module with the provided 'on' flag and weight
     *
     * @param on
     *         is this Module a bar or a space?
     * @param weight
     *         the weight (in units of 'x') of this Module
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
     */
    public boolean isOn()
    {
        return on;
    }

    /**
     * Retrieves the weight (in units of 'x') of this Module
     *
     * @return the weight of this Module
     */
    public int getWeight()
    {
        return weight;
    }

    @Override
    public final boolean equals(Object rhs)
    {
        if(!(rhs instanceof Module))
        {
            return false;
        }
        Module other = (Module)rhs;
        return on == other.on && weight == other.weight;
    }
}
