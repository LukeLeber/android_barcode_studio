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
import com.lukeleber.barcodestudio.util.PrettyClassWrapper;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemSelected;


public class BarcodeStudio
        extends
        Activity
{
    /// Spinner view that holds all supported symbologies
    @InjectView(R.id.symbologies)
    Spinner symbologies;
    @InjectView(R.id.symbologySettings)
    Button symbologySettings;
    /// The currently selected Symbology
    private Symbology selectedSymbology;

    @OnItemSelected(R.id.symbologies)
    void onItemSelected(int position)
    {
        if (selectedSymbology == null || position != getCurrentSymbologyPosition())
        {
            PrettyClassWrapper<Symbology> wrapper =
                    (PrettyClassWrapper<Symbology>) symbologies.getItemAtPosition(position);
            try
            {
                selectedSymbology = wrapper.unwrap().newInstance();
            }
            catch (InstantiationException eiie)
            {
                eiie.printStackTrace();
            }
            catch (IllegalAccessException iae)
            {
                iae.printStackTrace();
            }
        }
    }

    @OnClick(R.id.symbologySettings)
    public void onConfigureSymbologyClicked()
    {
        Intent intent = new Intent(this, SymbologySettings.class);
        intent.putExtra("symbology", selectedSymbology);
        super.startActivity(intent);
    }

    @OnClick(R.id.pageSetup)
    public void setupPage()
    {
        super.finish();
        Intent intent = new Intent(this, PageSetup.class);
        super.startActivity(intent);
    }

    /**
     * Retrieves the spinner position of the currently selected Symbology
     *
     * @return the spinner position of the currently selected Symbology
     */
    private int getCurrentSymbologyPosition()
    {
        return ((ArrayAdapter<PrettyClassWrapper<Symbology>>) symbologies.getAdapter())
                .getPosition(new PrettyClassWrapper(selectedSymbology.getClass()));
    }

    /**
     * Utilize reflection to load any and all supported symbologies into the GUI spinner view
     */
    private void populateSymbologies()
    {
        ArrayAdapter<PrettyClassWrapper<Symbology>> adapter
                = new ArrayAdapter<PrettyClassWrapper<Symbology>>(this,
                R.layout.dummy_dropdown_list_item);
        try
        {
            for (Class<Symbology> c :
                    new DexClassLoader(this)
                            .findClasses("com.lukeleber.barcodestudio.symbologies",
                                    Symbology.class))
            {
                adapter.add(new PrettyClassWrapper(c));
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_studio);
        ButterKnife.inject(this);
        populateSymbologies();

        Symbology symbology = (Symbology) getIntent().getSerializableExtra("symbology");
        if (symbology != null)
        {
            this.selectedSymbology = symbology;
            this.symbologies.setSelection(getCurrentSymbologyPosition());
        }
    }
}
