/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.Symbology;
import com.lukeleber.barcodestudio.util.DexClassLoader;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class BarcodeStudio
        extends
        Activity
{
    private final static int PAGE_SETTINGS_RESULT_ID = 0x1;
    private final static int SYMBOLOGY_SETTINGS_RESULT_ID = 0x2;

    com.lukeleber.barcodestudio.rendering.PageSetup pageSetup = new com.lukeleber.barcodestudio.rendering.PageSetup(new com.lukeleber.barcodestudio.rendering.PageSetup.Configuration());

    /// Spinner view that holds all supported symbologies
    @InjectView(R.id.symbologies)
    Spinner symbologies;
    @InjectView(R.id.symbologySettings)
    Button symbologySettings;

    @OnClick(R.id.symbologySettings)
    public void onConfigureSymbologyClicked()
    {
        Intent intent = new Intent(this, SymbologySettings.class);
        intent.putExtra("symbology", (Symbology) symbologies.getSelectedItem());
        super.startActivityForResult(intent, SYMBOLOGY_SETTINGS_RESULT_ID);
    }

    @OnClick(R.id.pageSetup)
    public void setupPage()
    {
        Intent intent = new Intent(this, PageSetup.class);
        intent.putExtra("config", pageSetup);
        super.startActivityForResult(intent, PAGE_SETTINGS_RESULT_ID);
    }

    /**
     * Utilize reflection to load any and all supported symbologies into the GUI spinner view
     */
    private void populateSymbologies()
    {
        ArrayAdapter<Symbology> adapter
                = new ArrayAdapter<Symbology>(this,
                R.layout.dummy_dropdown_list_item);
        try
        {
            for (Class<Symbology> c :
                    new DexClassLoader(this)
                            .findClasses("com.lukeleber.barcodestudio.symbologies",
                                    Symbology.class))
            {
                try
                {
                    adapter.add(c.newInstance());
                }
                catch (InstantiationException ie)
                {
                    // todo
                }
                catch (IllegalAccessException iae)
                {
                    // todo
                }
            }
            symbologies.setAdapter(adapter);
        }
        catch (IOException ioe)
        {
            Toast.makeText(BarcodeStudio.this, "Unable to load symbologies", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        System.out.println("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_studio);
        ButterKnife.inject(this);
        populateSymbologies();
    }

    @Override
    protected void onActivityResult(int code, int result, Intent intent)
    {
        switch (code)
        {
            case PAGE_SETTINGS_RESULT_ID:
                this.pageSetup = (com.lukeleber.barcodestudio.rendering.PageSetup) intent.getSerializableExtra("page_setup");
                Toast.makeText(this, "Page settings have been updated.", Toast.LENGTH_SHORT).show();
                break;
            case SYMBOLOGY_SETTINGS_RESULT_ID:
                Symbology modified = (Symbology) intent.getSerializableExtra("symbology");
                Symbology symbology = (Symbology) symbologies.getSelectedItem();
                symbology.getConfig().setChecksumEnabled(modified.getConfig().useChecksum());
                symbology.getConfig().setExtendedCharsetEnabled(modified.getConfig().useExtendedCharset());
                Toast.makeText(this, "Symbology settings have been updated.", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Unknown response code: " + code, Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(code, result, intent);
    }
}
