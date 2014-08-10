/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lukeleber.barcodestudio.symbologies.Code39;
import com.lukeleber.barcodestudio.util.PrettyClassWrapper;

import java.io.IOException;

import butterknife.InjectView;


public class BarcodeStudio
        extends
        Activity
{

    @InjectView(R.id.symbologies)
    private Spinner symbologies;

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
        } catch (IOException ioe)
        {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_studio);
        populateSymbologies();

        Barcode demo = new Barcode("123", new Code39(false, false));

    }


}
