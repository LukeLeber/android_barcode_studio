// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;

import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.Symbology;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;

/**
 * GUI for configuring symbology settings.
 * TODO: Grunt Work.  Docs are lowest priority.
 */
public class SymbologyEditor
        extends Activity
{

    /// <-- ButterKnife

    @InjectView(R.id.useExtendedCharset)
    CheckBox useExtendedCharset;

    @InjectView(R.id.useChecksum)
    CheckBox useChecksum;

    /// The symbology that is being configured
    private Symbology symbology;

    @OnCheckedChanged(R.id.useExtendedCharset)
    void onExtendedCharsetToggled(boolean checked)
    {
        symbology.getConfig().setExtendedCharsetEnabled(checked);
        Toast.makeText(this, R.string.symbology_settings_activity_configuration_saved, Toast.LENGTH_SHORT).show();
    }

    /// ButterKnife -->

    @OnCheckedChanged(R.id.useChecksum)
    void onChecksumToggled(boolean checked)
    {
        symbology.getConfig().setChecksumEnabled(checked);
        Toast.makeText(this, R.string.symbology_settings_activity_configuration_saved, Toast.LENGTH_SHORT).show();
    }

    /**
     * @see android.app.Activity#onBackPressed()
     */
    @Override
    public void onBackPressed()
    {
        /// Java's serialization mechanism results in a different object
        /// being created during the intent passing, so simply updating
        /// the object that was passed in is insufficient.  To remedy
        /// this, we just pass the updated object back to the calling
        /// activity and process it there.
        Intent intent = new Intent(this, BarcodeStudio.class);
        intent.putExtra(super.getString(R.string.symbology_settings_extra_key), symbology);
        setResult(Activity.RESULT_OK, intent);

        /// Now we can finish() this activity as normal
        super.onBackPressed();
    }

    /**
     * @see Activity#onCreate(android.os.Bundle)
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symbology_settings);

        ButterKnife.inject(this);

        this.symbology = (Symbology) getIntent().getExtras().getSerializable(super.getString(R.string.symbology_settings_extra_key));

        /// Should never happen
        assert this.symbology != null : "Symbology should not have been null.";

        if (!symbology.isChecksumAvailable()) // Unavailable? un-check & disable
        {
            useChecksum.setChecked(false);
            useChecksum.setEnabled(false);
        }
        else if (symbology.isChecksumRequired()) // Required? check & disable
        {
            useChecksum.setChecked(true);
            useChecksum.setEnabled(false);
        }
        else // else load previous configuration
        {
            useChecksum.setChecked(symbology.getConfig().useChecksum());
            useChecksum.setEnabled(true);
        }
        if (!symbology.hasExtendedCharset()) // Unavailable? un-check & disable
        {
            useExtendedCharset.setChecked(false);
            useExtendedCharset.setEnabled(false);
        }
        else // else load previous configuration
        {
            useExtendedCharset.setChecked(symbology.getConfig().useExtendedCharset());
            useExtendedCharset.setEnabled(true);
        }
    }
}
