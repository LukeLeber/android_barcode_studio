/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio;

// todo: continue docs...
public final class Module
{
    private final boolean on;
    private final int weight;

    public Module(boolean on, int weight)
    {
        this.on = on;
        this.weight = weight;
    }

    public boolean isOn()
    {
        return on;
    }

    public int getWeight()
    {
        return weight;
    }
}
