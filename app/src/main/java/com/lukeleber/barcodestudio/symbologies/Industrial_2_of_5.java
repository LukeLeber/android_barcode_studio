/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio.symbologies;

import com.lukeleber.barcodestudio.AbstractSymbology;
import com.lukeleber.barcodestudio.ChecksumNotSupportedException;
import com.lukeleber.barcodestudio.DecoderException;
import com.lukeleber.barcodestudio.EncoderException;
import com.lukeleber.barcodestudio.Sequence;


public class Industrial_2_of_5
        extends AbstractSymbology
{
    /**
     * Is this symbology fixed-length?
     *
     * @return true if this symbology is fixed-length, otherwise false
     */
    @Override
    public boolean isFixedLength()
    {
        return false;
    }

    /**
     * Is this symbology variable-length?
     *
     * @return true if this symbology is variable-length, otherwise false
     */
    @Override
    public boolean isVariableLength()
    {
        return false;
    }

    /**
     * Is this a self-checking symbology?
     *
     * @return true if this symbology is self-checking, otherwise false
     */
    @Override
    public boolean isSelfChecking()
    {
        return false;
    }

    /**
     * Is a checksum digit available for this symbology?
     *
     * @return true if a checksum digit is available, otherwise false
     */
    @Override
    public boolean isChecksumAvailable()
    {
        return true;
    }

    /**
     * Is the use of a checksum digit required for this symbology?
     *
     * @return true if a checksum digit must be used, otherwise false
     */
    @Override
    public boolean isChecksumRequired()
    {
        return false;
    }

    /**
     * Is this a discrete symbology?
     *
     * @return true if this is a discrete symbology, otherwise false
     */
    @Override
    public boolean isDiscrete()
    {
        return false;
    }

    /**
     * Is this a continuous symbology?
     *
     * @return true if this is a continuous symbology, otherwise false
     */
    @Override
    public boolean isContinuous()
    {
        return false;
    }

    /**
     * Does this symbology offer an extended character set?
     *
     * @return true if an extended character set is offered, otherwise false
     */
    @Override
    public boolean hasExtendedCharset()
    {
        return false;
    }

    /**
     * Retrieves the character set that is used in this Symbology.  Note that the
     * character set that is returned may depend on the configuration options of a
     * particular symbology (IE. some symbologies offer an extended charset).
     *
     * @return the character set that is used in this Symbology
     */
    @Override
    public CharSequence getCharset()
    {
        return null;
    }

    /**
     * Retrieves the start sequence of this symbology
     *
     * @return the start sequence of this symbology
     */
    @Override
    public Sequence getStartSequence()
    {
        return null;
    }

    /**
     * Retrieves the stop sequence of this symbology
     *
     * @return the stop sequence of this symbology
     */
    @Override
    public Sequence getStopSequence()
    {
        return null;
    }

    /**
     * Calculates a checksum digit for the provided text.
     *
     * @param text the text to evaluate
     * @return a checksum digit calculated from the provided text
     * @throws ChecksumNotSupportedException if a checksum is not supported by this symbology
     */
    @Override
    public char calculateChecksum(String text)
    {
        return 0;
    }

    /**
     * Encodes the provided text into a Sequence
     *
     * @param text the text to encode
     * @return a Sequence encoded from the provided text
     * @throws EncoderException if the encoding process fails for any reason
     */
    @Override
    public Sequence encode(String text)
    {
        return null;
    }

    /**
     * Decodes the provided Sequence into a text string
     *
     * @param sequence the Sequence to decode
     * @return a text string decoded from the provided Sequence
     * @throws DecoderException if the decoding process fails for any reason
     */
    @Override
    public String decode(Sequence sequence)
    {
        return null;
    }

    public Industrial_2_of_5()
    {
        super(new Configuration());
    }

    @Override
    public String toString()
    {
        return "Industrial 2 of 5";
    }
}
