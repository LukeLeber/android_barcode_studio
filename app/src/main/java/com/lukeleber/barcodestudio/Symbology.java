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
 * The ultimate base interface for all linear barcode symbologies
 */
public interface Symbology
        extends Serializable
{

    /**
     * A helper class for building symbology configurations
     */
    public static class Configuration
            implements Serializable
    {
        /// Should this Symbology use a checksum digit?
        protected boolean useChecksum;

        /// Should this Symbology use an extended character set?
        protected boolean useExtendedCharset;

        /**
         * Applies the options of the provided Configuration to this one.
         *
         * @param other the Configuration to copy
         */
        public void apply(Configuration other)
        {
            this.useChecksum = other.useChecksum;
            this.useExtendedCharset = other.useExtendedCharset;
        }

        /**
         * Does this configuration use a checksum?
         *
         * @return true if this configuration uses a checksum, otherwise false
         */
        public boolean useChecksum()
        {
            return useChecksum;
        }

        /**
         * Does this configuration use an extended character set?
         *
         * @return true if this configuration uses an extended character set, otherwise false
         *
         */
        public boolean useExtendedCharset()
        {
            return useExtendedCharset;
        }

        /**
         * Sets the extended charset flag
         *
         * @param enabled should the extended charset be enabled?
         * @return this
         */
        public Configuration setExtendedCharsetEnabled(boolean enabled)
        {
            this.useExtendedCharset = enabled;
            return this;
        }

        /**
         * Sets the checksum flag
         *
         * @param enabled should the checksum be enabled?
         * @return this
         */
        public Configuration setChecksumEnabled(boolean enabled)
        {
            this.useChecksum = enabled;
            return this;
        }
    }

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
    String getCharset();

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

    /**
     * Retrieves the Configuration of this Symbology
     *
     * @return the Configuration of this Symbology
     */
    Configuration getConfig();
}
