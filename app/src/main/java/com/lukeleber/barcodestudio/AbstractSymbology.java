/**
 * This file is protected under the KILLGPL.
 * For more information visit https://www.github.com/lukeleber/KILLGPL
 *
 * Copyright Luke <LukeLeber@gmail.com> 8/10/2014.
 *
 */

package com.lukeleber.barcodestudio;

/**
 * A skeletal base class that acts to minimize boilerplate code common to all linear barcodes.
 */
public abstract class AbstractSymbology
        implements Symbology
{

    /// Should this Symbology use a checksum digit?
    protected final boolean useChecksum;

    /// Should this Symbology use an extended character set?
    protected final boolean useExtendedCharset;

    /**
     * A helper class for building symbology configurations
     */
    public static class Configuration
    {


        /// Should this Symbology use a checksum digit?
        private boolean useChecksum;

        /// Should this Symbology use an extended character set?
        private boolean useExtendedCharset;

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
     * Constructs an AbstractSymbology with the provided configuration
     *
     * @param config the configuration object that defines the options of this symbology
     */
    protected AbstractSymbology(Configuration config)
    {
        this.useChecksum = config.useChecksum;
        this.useExtendedCharset = config.useExtendedCharset;
    }
}
