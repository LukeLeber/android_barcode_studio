<<<<<<< HEAD
// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio;

import java.io.Serializable;

/**
 * Barcodes are basically classes that hold and maintain the relationship between a text string and
 * its encoding based upon a specified symbology.
 */
public final class Barcode
        implements Serializable
{
    /// The symbology to use with this barcode
    private final Symbology symbology;

    /// The text content of this barcode
    private String text;

    /// The encoding of the text content of this barcode
    private Sequence encoding = new Sequence();

    /**
     * Constructs a Barcode with the provided text and symbology
     *
     * @param text
     *         the text content of this Barcode
     * @param symbology
     *         the Symbology of this Barcode
     *
     * @throws EncoderException
     *         if the provided text cannot be encoded by the provided Symbology
     */
    public Barcode(String text, Symbology symbology)
    {
        this.symbology = symbology;
        this.text = text;
        this.encoding = symbology.encode(text);
    }

    /**
     * Constructs a Barcode with the provided sequence and symbology
     *
     * @param encoding
     *         the encoding of this Barcode
     * @param symbology
     *         the Symbology of this Barcode
     *
     * @throws DecoderException
     *         if the provided Sequence cannot be decoded by the provided Symbology
     */
    @SuppressWarnings("unused")
=======
<<<<<<< HEAD
// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio;

import java.io.Serializable;

/**
 * Barcodes are basically classes that hold and maintain the relationship between a text string and
 * its encoding based upon a specified symbology.
 */
public final class Barcode
        implements Serializable
{
    /// The symbology to use with this barcode
    private final Symbology symbology;

    /// The text content of this barcode
    private String text;

    /// The encoding of the text content of this barcode
    private Sequence encoding = new Sequence();

    /**
     * Constructs a Barcode with the provided text and symbology
     *
     * @param text
     *         the text content of this Barcode
     * @param symbology
     *         the Symbology of this Barcode
     *
     * @throws EncoderException
     *         if the provided text cannot be encoded by the provided Symbology
     */
    public Barcode(String text, Symbology symbology)
    {
        this.symbology = symbology;
        this.text = text;
        this.encoding = symbology.encode(text);
    }

    /**
     * Constructs a Barcode with the provided sequence and symbology
     *
     * @param encoding
     *         the encoding of this Barcode
     * @param symbology
     *         the Symbology of this Barcode
     *
     * @throws DecoderException
     *         if the provided Sequence cannot be decoded by the provided Symbology
     */
    @SuppressWarnings("unused")
=======
/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio;

import java.io.Serializable;

/**
 * Barcodes are basically classes that hold and maintain the relationship between a text string
 * and its encoding based upon a specified symbology.
 */
public final class Barcode implements Serializable
{
    /// The symbology to use with this barcode
    private final Symbology symbology;

    /// The text content of this barcode
    private String text;

    /// The encoding of the text content of this barcode
    private Sequence encoding = new Sequence();

    /**
     * Constructs a Barcode with the provided text and symbology
     *
     * @param text      the text content of this Barcode
     * @param symbology the Symbology of this Barcode
     * @throws EncoderException if the provided text cannot be encoded by the provided
     *                          Symbology
     */
    public Barcode(String text, Symbology symbology)
    {
        this.symbology = symbology;
        this.text = text;
        this.encoding = symbology.encode(text);
    }

    /**
     * Constructs a Barcode with the provided sequence and symbology
     *
     * @param encoding  the encoding of this Barcode
     * @param symbology the Symbology of this Barcode
     * @throws DecoderException if the provided Sequence cannot be decoded by the provided
     *                          Symbology
     */
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
>>>>>>> branch 'master' of https://github.com/LukeLeber/android_barcode_studio.git
    public Barcode(Sequence encoding, Symbology symbology)
    {
        this.symbology = symbology;
        this.text = symbology.decode(encoding);
        this.encoding = encoding;
    }

    /**
     * Retrieves the Symbology of this Barcode
     *
     * @return the Symbology of this Barcode
     */
    public Symbology getSymbology()
    {
        return symbology;
    }

    /**
     * Retrieves the encoding Sequence of this Barcode
     *
     * @return the encoding Sequence of this Barcode
     */
    public Sequence getEncoding()
    {
        return this.encoding;
    }

    /**
     * Retrieves the text of this Barcode
     *
     * @return the text of this Barcode
     */
    public String getText()
    {
        return text;
    }
}
