/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 *
 * Copyright Luke <LukeLeber@gmail.com> 8/10/2014.
 *
 */
package com.lukeleber.barcodestudio.rendering;

import java.io.Serializable;

public class PageSetup
        implements Serializable
{
    public final float pageWidth;
    public final float pageHeight;
    public final Orientation orientation;

    public final float marginLeft;
    public final float marginRight;
    public final float marginTop;
    public final float marginBottom;

    ///barcode metrics
    public final int numVerticalBarCodes;
    public final int numHorizontalBarCodes;
    public final float barCodeWidth;
    public final float barCodeHeight;
    public final float columnPadding;
    public final float rowPadding;

    public PageSetup(Configuration config)
    {
        this.pageWidth = config.pageWidth;
        this.pageHeight = config.pageHeight;
        this.orientation = config.orientation;
        this.marginLeft = config.marginLeft;
        this.marginRight = config.marginRight;
        this.marginTop = config.marginTop;
        this.marginBottom = config.marginBottom;
        this.numVerticalBarCodes = config.numVerticalBarCodes;
        this.numHorizontalBarCodes = config.numHorizontalBarCodes;
        this.barCodeHeight = config.barCodeHeight;
        this.barCodeWidth = config.barCodeWidth;
        this.columnPadding = config.columnPadding;
        this.rowPadding = config.rowPadding;

    }


    public enum Orientation
    {
        PORTRAIT,
        LANDSCAPE;
    }

    public static class Configuration
            implements Serializable
    {
        private float pageWidth = 8.5f;
        private float pageHeight = 11.0f;
        private Orientation orientation = Orientation.PORTRAIT;
        private float marginLeft = 0.25f;
        private float marginRight = 0.25f;
        private float marginTop = 0.25f;
        private float marginBottom = 0.25f;
        private int numVerticalBarCodes = 5;
        private int numHorizontalBarCodes = 8;
        private float barCodeWidth = 1.0f;
        private float barCodeHeight = 1.0f;
        private float columnPadding = 0.25f;
        private float rowPadding = 0.25f;

        public Configuration setPageWidth(float pageWidth)
        {
            this.pageWidth = pageWidth;
            return this;

        }

        public Configuration setPageHeight(float pageHeight)
        {
            this.pageHeight = pageHeight;
            return this;

        }

        public Configuration setOrientation(Orientation orientation)
        {
            this.orientation = orientation;
            return this;

        }

        public Configuration setMarginLeft(float marginLeft)
        {
            this.marginLeft = marginLeft;
            return this;

        }

        public Configuration setMarginRight(float marginRight)
        {
            this.marginRight = marginRight;
            return this;

        }

        public Configuration setMarginTop(float marginTop)
        {
            this.marginTop = marginTop;
            return this;

        }

        public Configuration setMarginBottom(float marginBottom)
        {
            this.marginBottom = marginBottom;
            return this;

        }

        public Configuration setNumVerticalBarCodes(int numVerticalBarCodes)
        {
            this.numVerticalBarCodes = numVerticalBarCodes;
            return this;

        }

        public Configuration setNumHorizontalBarCodes(int numHorizontalBarCodes)
        {
            this.numHorizontalBarCodes = numHorizontalBarCodes;
            return this;

        }

        public Configuration setBarCodeWidth(float barCodeWidth)
        {
            this.barCodeWidth = barCodeWidth;
            return this;

        }

        public Configuration setBarCodeHeight(float barCodeHeight)
        {
            this.barCodeHeight = barCodeHeight;
            return this;

        }

        public Configuration setColumnPadding(float columnPadding)
        {
            this.columnPadding = columnPadding;
            return this;

        }

        public Configuration setRowPadding(float rowPadding)
        {
            this.rowPadding = rowPadding;
            return this;

        }

    }
}
