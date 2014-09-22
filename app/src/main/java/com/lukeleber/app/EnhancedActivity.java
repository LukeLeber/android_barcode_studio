// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.app;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link Activity} with more built-in conveniences and features than the stock SDK class. Among
 * the enhancements are a scalable listener-based activity result handling mechanism, short-hand
 * {@link android.widget.Toast} creation, and more. (Well, not really more right now... but I'm sure
 * it'll be expanded very soon!)
 */
public abstract class EnhancedActivity
        extends Activity
{
    /// The list of {@link ActivityResultListener listeners} currently registered
    private final List<ActivityResultListener> resultListeners;

    /**
     * Protected default constructor
     */
    protected EnhancedActivity()
    {
        this.resultListeners = new ArrayList<ActivityResultListener>();
    }

    /**
     * Adds the provided {@link ActivityResultListener} to this <code>EnhancedActivity</code>
     *
     * @param listener
     *         the listener to add
     *
     * @return the request code that was generated for this listener
     */
    protected int addResultListener(ActivityResultListener listener)
    {
        int code = resultListeners.size();
        resultListeners.add(listener);
        return code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void onActivityResult(int code, int result, Intent intent)
    {
        ActivityResultListener listener = resultListeners.get(code);
        if (listener != null)
        {
            listener.onActivityResult(result, intent);
        }
        else
        {
            Log.wtf("[internal]", "unknown result code");
        }
    }

    /**
     * Shows a short toast with the text found with the provided string resource ID
     *
     * @param resourceID
     *         the resource ID (IE R.string.XXX) to show in the toast
     */
    protected final void shortToast(int resourceID)
    {
        Toast.makeText(this, super.getString(resourceID), Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a long toast with the text found with the provided string resource ID
     *
     * @param resourceID
     *         the resource ID (IE R.string.XXX) to show in the toast
     */
<<<<<<< HEAD
    @SuppressWarnings("unused")
    protected final void longToast(int resourceID)
    {
        Toast.makeText(this, super.getString(resourceID), Toast.LENGTH_LONG).show();
    }

    @SuppressWarnings("unused")
=======
    protected final void longToast(int resourceID)
    {
        Toast.makeText(this, super.getString(resourceID), Toast.LENGTH_LONG).show();
    }

>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    protected final void shortToast(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected final void longToast(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
