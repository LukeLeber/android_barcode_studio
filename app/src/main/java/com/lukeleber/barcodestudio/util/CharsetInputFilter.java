/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 * <p/>
 * Copyright Luke <LukeLeber@gmail.com> 8/19/2014.
 */

package com.lukeleber.barcodestudio.util;

import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;

public class CharsetInputFilter implements InputFilter
{
    private final String charset;

    public CharsetInputFilter(String charset)
    {
        this.charset = charset;
    }

    /// TODO: Wonky results with google's keyboard & autocomplete.
    /// Very different from Swing's input filtering event firing...
    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend)
    {
        String filtered = "";
        for(int i = start; i < end; ++i)
        {
            char c= source.charAt(i);
            if(charset.indexOf(c) != -1)
            {
                filtered += c;
            }
        }
            return filtered;
    }
}
