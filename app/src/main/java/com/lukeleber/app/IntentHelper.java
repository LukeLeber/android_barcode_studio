// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;

import java.io.Serializable;

/**
 * Essentially just a collection of useful {@link Intent} factories.
 */
public class IntentHelper
{

    /**
     * Creates an intent with the provided parent context, target class, and serializable extras.
     * <p/>
     * Essentially, this factory replaces this:
     * <pre>
     *     Intent intent = new Intent(this, SomeClass.class);
     *     intent.putExtra("some_extra", someSerializableExtra);
     *     startActivity(intent);
     * </pre>
     * with this:
     * <pre>
     *     startActivity(IntentHelper.createIntent(this, SomeClass.class,
     *              new Pair<String, Serializable>("some_extra", someSerializableExtra));
     * </pre>
     *
     * @param thiz
     *         the context from which this {@link Intent} originated
     * @param clazz
     *         the target class
     * @param extras
     *         a variable-length list of extras
     *
     * @return the Intent created from the provided arguments
     */
    @SafeVarargs
    public static Intent createIntent(Context thiz, Class<? extends Activity> clazz,
                                      Pair<String, ? extends Serializable>... extras)
    {
        Intent intent = new Intent(thiz, clazz);
        for (Pair<String, ? extends Serializable> extra : extras)
        {
            intent.putExtra(extra.first, extra.second);
        }
        return intent;
    }
}
