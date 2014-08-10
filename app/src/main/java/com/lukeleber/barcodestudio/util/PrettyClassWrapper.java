/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 *
 * Copyright Luke <LukeLeber@gmail.com> 8/10/2014.
 *
 *
 */
package com.lukeleber.barcodestudio.util;

/**
 * A wrapper for Classes that overrides the {@link java.lang.Object#toString} method in
 * order to return a "prettier" version of the class name than the fully qualified package.
 * <p/>
 * For example:
 * <code>
 * Class<Industrial_2_of_5>.toString() --> "com.lukeleber.barcodestudio.symbologies.Industrial_2_of_5"
 * PrettyClassWrapper<Industrial_2_of_5> --> "Industrial 2 of 5"
 * </code>
 *
 * @param <T> the type to wrap
 */
public class PrettyClassWrapper<T>
{
    /// The wrapped type
    private final Class<T> clazz;

    /**
     * Constructs a PrettyClassWrapper with the provided type
     *
     * @param clazz an instance of the class to wrap
     */
    public PrettyClassWrapper(Class<T> clazz)
    {
        this.clazz = clazz;
    }

    /**
     * Formally, this method returns {@link Class#getSimpleName} with all underscores replaced
     * with spaces.
     *
     * @return the string representation of this PrettyClassWrapper
     *
     */
    @Override
    public final String toString()
    {
        return clazz.getSimpleName().replace('_', ' ');
    }

    /**
     * Retrieves the underlying class
     *
     * @return the underlying class
     *
     */
    public Class<T> unwrap()
    {
        return clazz;
    }
}
