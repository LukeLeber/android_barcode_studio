/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 *
 * Copyright Luke <LukeLeber@gmail.com> 8/10/2014.
 *
 *
 */
package com.lukeleber.barcodestudio.util;

public class PrettyClassWrapper<T>
{
    private final Class<T> clazz;

    public PrettyClassWrapper(Class<T> clazz)
    {
        this.clazz = clazz;
    }

    @Override
    public final String toString()
    {
        return clazz.getSimpleName().replace('_', ' ');
    }

    public Class<T> unwrap()
    {
        return clazz;
    }

}
