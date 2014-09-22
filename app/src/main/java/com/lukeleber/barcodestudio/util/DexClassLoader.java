/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio.util;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

/**
 * A quick and dirty ClassLoader that scans a DEX file for classes of a certain type
 */
public class DexClassLoader
        extends ClassLoader
{
    /// The DexFile of the currently executing application
    private final DexFile dex;

    /**
     * Constructs a DexClassLoader with the provided Context
     *
     * @param context a reference to the Context of the current Activity
     * @throws IOException if the DEX file could not be opened
     */
    public DexClassLoader(Context context)
            throws
            IOException
    {
        this.dex = new DexFile(context.getApplicationInfo().sourceDir);
    }

    /**
     * Retrieves a collection of all classes in the provided package (or sub-packages therein)
     * that are assignable to the provided class.
     *
     * @param pkg    the "root package" to search in
     * @param <T>    the type to search for
     * @param toFind an instance of the generic type
     * @return a collection of all classes that match the search criteria
     */
    @SuppressWarnings("unchecked")
    public <T> Collection<Class<T>> findClasses(String pkg, Class<T> toFind)
    {
        List<Class<T>> classes = new ArrayList<Class<T>>();
        Enumeration<String> entries = dex.entries();
        while (entries.hasMoreElements())
        {
            String entry = entries.nextElement();
            if (entry.startsWith(pkg))
            {
                try
                {
                    Class<?> c = Class.forName(entry);
                    if (toFind.isAssignableFrom(c))
                    {
                        classes.add((Class<T>) c);
                    }
                } catch (ClassNotFoundException cnfe)
                {
                    /// Eat it.  No idea why this might happen right now.
                    /// TODO: investigate further
                    Log.wtf("[internal]", cnfe);
                }
            }
        }
        return classes;
    }
}
