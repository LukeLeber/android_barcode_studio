package com.lukeleber.barcodestudio.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;

import java.io.Serializable;

/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 * <p/>
 * Copyright Luke <LukeLeber@gmail.com> 8/18/2014.
 */
public class IntentHelper
{

    /// TODO:
    ///  Extend butterknife to generate this at compile-time
    @SafeVarargs
    public static Intent createIntentWithSerializableExtras(Context thiz, Class<? extends Activity> clazz, Pair<String, ? extends Serializable>... extras)
    {
        Intent intent = new Intent(thiz, clazz);
        for(Pair<String, ? extends Serializable> extra : extras)
        {
            intent.putExtra(extra.first, extra.second);
        }
        return intent;
    }
}
