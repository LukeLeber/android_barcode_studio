/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 * <p/>
 * Copyright Luke <LukeLeber@gmail.com> 8/19/2014.
 */

package com.lukeleber.barcodestudio;

import com.lukeleber.barcodestudio.rendering.PrintingMedia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RangeJob extends Job implements Iterable<Barcode>
{
    private List<Barcode> barcodes;
    private final Symbology symbology;

    public RangeJob(PrintingMedia media, Symbology symbology, int begin, int end)
    {
        super(media);
        this.symbology = symbology;
        this.barcodes = new ArrayList<Barcode>(end - begin);
        do
        {
            barcodes.add(new Barcode(String.valueOf(begin++), symbology));
        }
        while(begin < end);
    }

    @Override
    public final Symbology getSymbology()
    {
        return symbology;
    }

    @Override
    public int size()
    {
        return barcodes.size();
    }

    @Override
    public Iterator<Barcode> iterator()
    {
        return barcodes.iterator();
    }
}
