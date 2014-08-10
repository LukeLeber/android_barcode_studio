/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio;

/**
 * The ultimate base interface for all linear barcode symbologies
 */
public interface Symbology
{

    /**
     * Is this symbology fixed-length?
     *
     * @return true if this symbology is fixed-length, otherwise false
     */
    boolean isFixedLength();

    /**
     * Is this symbology variable-length?
     *
     * @return true if this symbology is variable-length, otherwise false
     */
    boolean isVariableLength();

    /**
     * Is this a self-checking symbology?
     *
     * @return true if this symbology is self-checking, otherwise false
     */
    boolean isSelfChecking();

    /**
     * Is a checksum digit available for this symbology?
     *
     * @return true if a checksum digit is available, otherwise false
     */
    boolean isChecksumAvailable();

    /**
     * Is the use of a checksum digit required for this symbology?
     *
     * @return true if a checksum digit must be used, otherwise false
     */
    boolean isChecksumRequired();

    /**
     * Is this a discrete symbology?
     *
     * @return true if this is a discrete symbology, otherwise false
     */
    boolean isDiscrete();

    /**
     * Is this a continuous symbology?
     *
     * @return true if this is a continuous symbology, otherwise false
     */
    boolean isContinuous();

    /**
     * Does this symbology offer an extended character set?
     *
     * @return true if an extended character set is offered, otherwise false
     */
    boolean hasExtendedCharset();

    /**
     * Retrieves the character set that is used in this Symbology.  Note that the
     * character set that is returned may depend on the configuration options of a
     * particular symbology (IE. some symbologies offer an extended charset).
     *
     * @return the character set that is used in this Symbology
     */
    CharSequence getCharset();

    /**
     * Retrieves the start sequence of this symbology
     *
     * @return the start sequence of this symbology
     */
    Sequence getStartSequence();

    /**
     * Retrieves the stop sequence of this symbology
     *
     * @return the stop sequence of this symbology
     */
    Sequence getStopSequence();

    /**
     * Calculates a checksum digit for the provided text.
     *
     * @param text the text to evaluate
     * @return a checksum digit calculated from the provided text
     * @throws ChecksumNotSupportedException if a checksum is not supported by this symbology
     */
    char calculateChecksum(String text);

    /**
     * Encodes the provided text into a Sequence
     *
     * @param text the text to encode
     * @return a Sequence encoded from the provided text
     * @throws EncoderException if the encoding process fails for any reason
     */
    Sequence encode(String text);

    /**
     * Decodes the provided Sequence into a text string
     *
     * @param sequence the Sequence to decode
     * @return a text string decoded from the provided Sequence
     */
    String decode(Sequence sequence);
}
