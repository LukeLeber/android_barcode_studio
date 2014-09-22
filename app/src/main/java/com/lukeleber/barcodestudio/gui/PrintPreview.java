<<<<<<< HEAD
// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.gui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukeleber.app.EnhancedActivity;
import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.printing.BatchJob;
import com.lukeleber.barcodestudio.printing.Job;
import com.lukeleber.barcodestudio.printing.RangeJob;
import com.lukeleber.barcodestudio.printing.Media;
import com.lukeleber.barcodestudio.rendering.PrecisionException;
import com.lukeleber.barcodestudio.rendering.Renderer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * An {@link android.app.Activity} that shows a graphical layout of a {@link Job}.
 */
public class PrintPreview
        extends EnhancedActivity
{

    /// <-- ButterKnife

    /// The 'next page' button
    @InjectView(R.id.nextButton)
    Button nextButton;

    /// The 'previous page' button
    @InjectView(R.id.previousButton)
    Button previousButton;

    /// The current page that is being viewed
    @InjectView(R.id.currentPage)
    TextView currentPage;

    /// The total number of pages in the {@link Job}
    @InjectView(R.id.pageCount)
    TextView pageCount;

    /// The {@link ImageView} to render the preview to
    @InjectView(R.id.surfaceView)
    ImageView iv;
    /// The individual{@link Job jobs} (one per page) that make up the entire {@link Job}.
    private Job[] jobs;

    /**
     * Fired when the 'next page' button is clicked.  Advances the current page and updates the GUI
     * to reflect the change by augmenting the current page display and by enabling or disabling
     * buttons.
     *
     */
    @OnClick(R.id.nextButton)
    void onNextPageClicked()
    {
        int next = Integer.parseInt(currentPage.getText().toString()) + 1;
        currentPage.setText(String.valueOf(next));
        if (next == Integer.parseInt(pageCount.getText().toString()))
        {
            nextButton.setEnabled(false);
        }
        previousButton.setEnabled(true);
        updateUI();
    }

    /// ButterKnife -->

    /**
     * Fired when the 'previous page' button is clicked.  Rewinds the current page and updates the
     * GUI to reflect the change by augmenting the current page display and by enabling or disabling
     * buttons.
     *
     */
    @OnClick(R.id.previousButton)
    void onPreviousPageClicked()
    {
        int previous = Integer.parseInt(currentPage.getText().toString()) - 1;
        currentPage.setText(String.valueOf(previous));
        if (previous == 1)
        {
            previousButton.setEnabled(false);
        }
        nextButton.setEnabled(true);
        updateUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_preview);
        ButterKnife.inject(this);
        iv.setBackgroundColor(Color.BLACK);
        Job job = (Job) super.getIntent().getSerializableExtra("job");
        /// Run each barcode to see if we get any PrecisionExceptions
        try
        {
            new Renderer(job).render(new Canvas());
        }
        catch(PrecisionException pe)
        {
            super.longToast("The requested barcode is too big for the provided printing media.\n" + pe.getMessage());
            super.finish();
            return;
        }
        this.jobs = pagify(job);
        this.currentPage.setText("1");
        this.pageCount.setText(String.valueOf(this.jobs.length));
        updateUI();
        if (jobs.length > 1)
        {
            nextButton.setEnabled(true);
        }
        super.shortToast(R.string.print_preview_return_to_studio_toast);
    }

    /**
     * Updates the GUI to reflect the current page
     */
    private void updateUI()
    {
        int page = Integer.parseInt(currentPage.getText().toString()) - 1;
        Media media = jobs[page].getMedia();
        Bitmap bmp = Bitmap.createBitmap((int) media.pageWidth, (int) media.pageHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Renderer renderer = new Renderer(jobs[page]);
        renderer.render(canvas);
        iv.setImageBitmap(bmp);
        iv.refreshDrawableState();
        iv.invalidate();
    }

    /**
     * Breaks up the provided monolithic {@link Job} into an array of smaller {@link Job jobs}; one
     * job per page.
     *
     * @param job
     *         the monolithic job to split up
     *
     * @return an array of smaller {@link Job jobs} (where one job represents one page)
     */
    private Job[] pagify(Job job)
    {
        Media media = job.getMedia();
        int perPage = media.numHorizontalBarCodes * media.numVerticalBarCodes;
        int count = (int)Math.ceil((float)job.size() / (float)perPage);
        Job[] rv;
        if (job instanceof BatchJob)
        {
            rv = new BatchJob[count];
            int remaining = job.size();
            for (int i = 0; i < rv.length; ++i)
            {
                rv[i] = new BatchJob(media, job.iterator().next(), remaining >= perPage ? perPage : remaining);
                remaining -= perPage;
            }
        }
        else
        {
            rv = new RangeJob[count];
            int start = Integer.parseInt(job.iterator().next().getText());
            int end = start + job.size() - 1;
            for (int i = 0; i < rv.length; ++i)
            {
                rv[i] = new RangeJob(media, job.getSymbology(), start, start + ((end - start) >= perPage ? perPage - 1 : (end - start)));
                start += perPage;
            }
        }
        return rv;
=======
<<<<<<< HEAD
// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.gui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukeleber.app.EnhancedActivity;
import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.printing.BatchJob;
import com.lukeleber.barcodestudio.printing.Job;
import com.lukeleber.barcodestudio.printing.RangeJob;
import com.lukeleber.barcodestudio.printing.Media;
import com.lukeleber.barcodestudio.rendering.PrecisionException;
import com.lukeleber.barcodestudio.rendering.Renderer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * An {@link android.app.Activity} that shows a graphical layout of a {@link Job}.
 */
public class PrintPreview
        extends EnhancedActivity
{

    /// <-- ButterKnife

    /// The 'next page' button
    @InjectView(R.id.nextButton)
    Button nextButton;

    /// The 'previous page' button
    @InjectView(R.id.previousButton)
    Button previousButton;

    /// The current page that is being viewed
    @InjectView(R.id.currentPage)
    TextView currentPage;

    /// The total number of pages in the {@link Job}
    @InjectView(R.id.pageCount)
    TextView pageCount;

    /// The {@link ImageView} to render the preview to
    @InjectView(R.id.surfaceView)
    ImageView iv;
    /// The individual{@link Job jobs} (one per page) that make up the entire {@link Job}.
    private Job[] jobs;

    /**
     * Fired when the 'next page' button is clicked.  Advances the current page and updates the GUI
     * to reflect the change by augmenting the current page display and by enabling or disabling
     * buttons.
     *
     * @hide
     */
    @OnClick(R.id.nextButton)
    void onNextPageClicked()
    {
        int next = Integer.parseInt(currentPage.getText().toString()) + 1;
        currentPage.setText(String.valueOf(next));
        if (next == Integer.parseInt(pageCount.getText().toString()))
        {
            nextButton.setEnabled(false);
        }
        previousButton.setEnabled(true);
        updateUI();
    }

    /// ButterKnife -->

    /**
     * Fired when the 'previous page' button is clicked.  Rewinds the current page and updates the
     * GUI to reflect the change by augmenting the current page display and by enabling or disabling
     * buttons.
     *
     * @hide
     */
    @OnClick(R.id.previousButton)
    void onPreviousPageClicked()
    {
        int previous = Integer.parseInt(currentPage.getText().toString()) - 1;
        currentPage.setText(String.valueOf(previous));
        if (previous == 1)
        {
            previousButton.setEnabled(false);
        }
        nextButton.setEnabled(true);
        updateUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_preview);
        ButterKnife.inject(this);
        iv.setBackgroundColor(Color.BLACK);
        Job job = (Job) super.getIntent().getSerializableExtra("job");
        /// Run each barcode to see if we get any PrecisionExceptions
        try
        {
            new Renderer(job).render(new Canvas());
        }
        catch(PrecisionException pe)
        {
            super.longToast("The requested barcode is too big for the provided printing media.\n" + pe.getMessage());
            super.finish();
            return;
        }
        this.jobs = pagify(job);
        this.currentPage.setText("1");
        this.pageCount.setText(String.valueOf(this.jobs.length));
        updateUI();
        if (jobs.length > 1)
        {
            nextButton.setEnabled(true);
        }
        super.shortToast(R.string.print_preview_return_to_studio_toast);
    }

    /**
     * Updates the GUI to reflect the current page
     *
     * @hide
     */
    private void updateUI()
    {
        int page = Integer.parseInt(currentPage.getText().toString()) - 1;
        Media media = jobs[page].getMedia();
        Bitmap bmp = Bitmap.createBitmap((int) media.pageWidth, (int) media.pageHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Renderer renderer = new Renderer(jobs[page]);
        renderer.render(canvas);
        iv.setImageBitmap(bmp);
        iv.refreshDrawableState();
        iv.invalidate();
    }

    /**
     * Breaks up the provided monolithic {@link Job} into an array of smaller {@link Job jobs}; one
     * job per page.
     *
     * @param job
     *         the monolithic job to split up
     *
     * @return an array of smaller {@link Job jobs} (where one job represents one page)
     */
    private Job[] pagify(Job job)
    {
        Media media = job.getMedia();
        int perPage = media.numHorizontalBarCodes * media.numVerticalBarCodes;
        int count = (int)Math.ceil((float)job.size() / (float)perPage);
        Job[] rv = null;
        if (job instanceof BatchJob)
        {
            rv = new BatchJob[count];
            int remaining = job.size();
            for (int i = 0; i < rv.length; ++i)
            {
                rv[i] = new BatchJob(media, job.iterator().next(), remaining >= perPage ? perPage : remaining);
                remaining -= perPage;
            }
        }
        else
        {
            rv = new RangeJob[count];
            int start = Integer.parseInt(job.iterator().next().getText());
            int end = start + job.size() - 1;
            for (int i = 0; i < rv.length; ++i)
            {
                rv[i] = new RangeJob(media, job.getSymbology(), start, start + ((end - start) >= perPage ? perPage - 1 : (end - start)));
                start += perPage;
            }
        }
        return rv;
=======
package com.lukeleber.barcodestudio.gui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.printservice.PrintService;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukeleber.barcodestudio.Barcode;
import com.lukeleber.barcodestudio.BatchJob;
import com.lukeleber.barcodestudio.Job;
import com.lukeleber.barcodestudio.R;
import com.lukeleber.barcodestudio.RangeJob;
import com.lukeleber.barcodestudio.rendering.PrintingMedia;
import com.lukeleber.barcodestudio.rendering.Renderer;
import com.lukeleber.barcodestudio.symbologies.Code39;

import java.util.Iterator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PrintPreview extends Activity {

    @InjectView(R.id.nextButton)
    Button nextButton;

    @InjectView(R.id.previousButton)
    Button previousButton;

    @InjectView(R.id.currentPage)
    TextView currentPage;

    @InjectView(R.id.pageCount)
    TextView pageCount;

    @InjectView(R.id.surfaceView)
    ImageView iv;

    @OnClick(R.id.nextButton)
    void onNextPageClicked()
    {
        int next = Integer.parseInt(currentPage.getText().toString()) + 1;
        currentPage.setText(String.valueOf(next));
        if(next == Integer.parseInt(pageCount.getText().toString()))
        {
            nextButton.setEnabled(false);
        }
        previousButton.setEnabled(true);
        updateUI();
    }

    @OnClick(R.id.previousButton)
    void onPreviousPageClicked()
    {
        int previous = Integer.parseInt(currentPage.getText().toString()) - 1;
        currentPage.setText(String.valueOf(previous));
        if(previous == 1)
        {
            previousButton.setEnabled(false);
        }
        nextButton.setEnabled(true);
        updateUI();
    }

    private void updateUI()
    {
        int page = Integer.parseInt(currentPage.getText().toString()) - 1;
        PrintingMedia media = jobs[page].getMedia();
        Bitmap bmp = Bitmap.createBitmap((int)media.pageWidth, (int)media.pageHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Renderer renderer = new Renderer(jobs[page]);
        renderer.render(canvas);
        iv.setImageBitmap(bmp);
        iv.refreshDrawableState();
        iv.invalidate();
    }

    private Job[] jobs;

    private Job[] pagify(Job job)
    {
        PrintingMedia media = job.getMedia();
        int perPage = media.numHorizontalBarCodes * media.numVerticalBarCodes;
        Job[] rv = null;
        if(job instanceof BatchJob)
        {
            rv = new BatchJob[job.size() / perPage];
            int remaining = job.size();
            for(int i = 0; i < rv.length; ++i)
            {
                rv[i] = new BatchJob(media, job.iterator().next(), remaining >= perPage ? perPage : remaining);
                remaining -= perPage;
            }
        }
        else
        {
            rv = new RangeJob[job.size() / perPage];
            Iterator<Barcode> iter = job.iterator();
            int start = Integer.parseInt(iter.next().getText());
            int end = start + job.size();
            for(int i = 0; i < rv.length; ++i)
            {
                rv[i] = new RangeJob(media, job.getSymbology(), start, start + ((end - start) > perPage ? perPage : (end - start)));
                start += perPage;
            }
        }
        return rv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_preview);
        ButterKnife.inject(this);
        iv.setBackgroundColor(Color.BLACK);
        /// Monolithic job -- let's break it up.
        Job job = (Job)super.getIntent().getSerializableExtra("job");
        PrintingMedia media = job.getMedia();

        this.jobs = pagify(job);
        this.currentPage.setText("1");
        this.pageCount.setText(String.valueOf(this.jobs.length));

        updateUI();
        if(jobs.length > 1)
        {
            nextButton.setEnabled(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.print_preview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    }
}
