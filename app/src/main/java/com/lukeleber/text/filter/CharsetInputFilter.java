// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.text.filter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * An {@link InputFilter} that limits input based on a specific character set.
 */
/// TODO: Wonky results with google's keyboard & autocomplete.  Terminal IDE keyboard works.
public class CharsetInputFilter
        implements InputFilter
{
    /// The character set that is allowed to slip through this {@link InputFilter}
    private final String charset;

    /**
     * Constructs a CharsetInputFilter with the provided character set
     *
     * @param charset
     *         the character set that is allowed to slip through this {@link InputFilter}
     */
    public CharsetInputFilter(String charset)
    {
        this.charset = charset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend)
    {
        /// TODO: Research regex escape all...HACK'D!
        return source.toString().replaceAll("[^" + charset.replace("\\", "\\\\").replace("-", "\\-").replace("[", "\\[").replace("]", "\\]").replace("(", "\\(").replace(")", "\\)").replace(".", "\\.") + "]", "");
    }
}
