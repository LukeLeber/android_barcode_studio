// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.symbologies;

import com.lukeleber.barcodestudio.Symbology;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Symbologies
{
    public static List<Symbology> getSymbologies()
    {
        List<Symbology> rv = Arrays.<Symbology>asList(
                /// TODO: Add implementations here as the project evolves
                new Code39(),
                new Code128()
        );
        Collections.sort(
                rv,
                new Comparator<Symbology>()
                {
                    @Override
                    public int compare(Symbology arg0, Symbology arg1)
                    {
                        return arg0.toString().compareTo(arg1.toString());
                    }
                }
        );
        return rv;
    }
}
