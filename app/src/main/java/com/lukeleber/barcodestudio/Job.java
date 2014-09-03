/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 * <p/>
 * Copyright Luke <LukeLeber@gmail.com> 8/19/2014.
 */
package com.lukeleber.barcodestudio;

import com.lukeleber.barcodestudio.rendering.PrintingMedia;

import java.io.Serializable;

public abstract class Job implements Iterable<Barcode>, Serializable
{
    protected final PrintingMedia media;

    protected Job(PrintingMedia media)
    {
        this.media = media;
    }

    public final PrintingMedia getMedia()
    {
        return media;
    }

    public abstract int size();

    public abstract Symbology getSymbology();
}
