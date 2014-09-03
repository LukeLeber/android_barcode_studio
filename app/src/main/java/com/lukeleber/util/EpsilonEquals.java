// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.util;

/**
 * Only since *everything* has to be in a class...
 */
public class EpsilonEquals
{

    final static double DEFAULT_EPSILON = 0.000000001f;

    public static boolean equals(double a, double b, double epsilon)
    {
        return Math.abs(a - b) < epsilon;
    }

    public static boolean equals(double a, double b)
    {
        return equals(a, b, DEFAULT_EPSILON);
    }
}
