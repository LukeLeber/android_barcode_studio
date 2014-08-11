package com.lukeleber.barcodestudio.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.Symbology;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;

public class SymbologySettings
        extends Activity
{

    /// The symbology that is being configured
    private Symbology symbology;

    @InjectView(R.id.useExtendedCharset)
    CheckBox useExtendedCharset;

    @InjectView(R.id.useChecksum)
    CheckBox useChecksum;

    @OnCheckedChanged(R.id.useExtendedCharset)
    void onExtendedCharsetToggled(boolean checked)
    {
        symbology.getConfig().setExtendedCharsetEnabled(checked);
        Toast.makeText(this, "Configuration Saved - Press back to return to the studio", Toast.LENGTH_SHORT).show();
    }

    @OnCheckedChanged(R.id.useChecksum)
    void onChecksumToggled(boolean checked)
    {
        symbology.getConfig().setChecksumEnabled(checked);
        Toast.makeText(this, "Configuration Saved - Press back to return to the studio", Toast.LENGTH_SHORT).show();
    }

    // Hack ;)
    @Override
    public void onBackPressed()
    {

        Intent intent = new Intent(this, BarcodeStudio.class);
        intent.putExtra("symbology", symbology);
        setResult(Activity.RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }

    /*
       Intent result = new Intent();
        result.putExtra("page_setup",

        );
        setResult(Activity.RESULT_OK, result);
        finish();
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symbology_settings);
        ButterKnife.inject(this);
        this.symbology = (Symbology) getIntent().getExtras().getSerializable("symbology");

        if (!symbology.isChecksumAvailable())
        {
            useChecksum.setChecked(false);
            useChecksum.setEnabled(false);
        } else if (symbology.isChecksumRequired())
        {
            useChecksum.setChecked(true);
            useChecksum.setEnabled(false);
        } else
        {
            useChecksum.setChecked(symbology.getConfig().useChecksum());
            useChecksum.setEnabled(true);
        }
        if (!symbology.hasExtendedCharset())
        {
            useExtendedCharset.setChecked(false);
            useExtendedCharset.setEnabled(false);
        } else
        {
            useExtendedCharset.setChecked(symbology.getConfig().useExtendedCharset());
            useExtendedCharset.setEnabled(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.symbology_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
