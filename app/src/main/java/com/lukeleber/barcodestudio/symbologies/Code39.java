/**
 * This file is protected under the KILLGPL.
 * For more information visit https://github.com/LukeLeber/KILLGPL
 *
 * Copyright Luke A. Leber <LukeLeber@gmail.com> 8.10.2014
 *
 */

package com.lukeleber.barcodestudio.symbologies;

import com.lukeleber.barcodestudio.AbstractSymbology;
import com.lukeleber.barcodestudio.Module;
import com.lukeleber.barcodestudio.Sequence;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of the Code39 Specification (http://en.wikipedia.org/wiki/Code_39)
 */
public class Code39
        extends AbstractSymbology
{

    /// The quiet zone to be used with Code39
    private final static Module QUIET_ZONE = new Module(false, 10);

    /// The start / stop sequence of Code39 (informally represented as ASCII "*")
    private final static Sequence START_STOP = new Sequence(1, 2, 1, 1, 2, 1, 2, 1, 1);

    /// The padding module that is inserted between discrete sequences
    private final static Module PAD = new Module(false, 1);

    /// The base character set of Code39
    private final static String CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";

    /// The extended character set of Code39 (TODO)
    private final static String EXTENDED_CHARSET = "";

    /// The base encoding table of Code39
    private final static Map<Character, Sequence> ENCODING_TABLE
            = new HashMap<Character, Sequence>();

    /// The extended encoding table of Code39
    private final static Map<Character, Sequence> EXTENDED_ENCODING_TABLE
            = new HashMap<Character, Sequence>();

    /// The static population of the base encoding table
    static
    {
        ENCODING_TABLE.put('0', new Sequence(1, 1, 1, 2, 2, 1, 2, 1, 1));
        ENCODING_TABLE.put('1', new Sequence(2, 1, 1, 2, 1, 1, 1, 1, 2));
        ENCODING_TABLE.put('2', new Sequence(1, 1, 2, 2, 1, 1, 1, 1, 2));
        ENCODING_TABLE.put('3', new Sequence(2, 1, 2, 2, 1, 1, 1, 1, 1));
        ENCODING_TABLE.put('4', new Sequence(1, 1, 1, 2, 2, 1, 1, 1, 2));
        ENCODING_TABLE.put('5', new Sequence(2, 1, 1, 1, 2, 2, 1, 1, 1));
        ENCODING_TABLE.put('6', new Sequence(1, 1, 2, 2, 2, 1, 1, 1, 1));
        ENCODING_TABLE.put('7', new Sequence(1, 1, 1, 2, 1, 1, 2, 1, 2));
        ENCODING_TABLE.put('8', new Sequence(2, 1, 1, 2, 1, 1, 2, 1, 1));
        ENCODING_TABLE.put('9', new Sequence(1, 1, 2, 2, 1, 1, 2, 1, 1));
        // todo: recheck encoding for 'A'
        ENCODING_TABLE.put('A', new Sequence(2, 1, 1, 1, 1, 2, 1, 1, 2));
        ENCODING_TABLE.put('B', new Sequence(1, 1, 2, 1, 1, 2, 1, 1, 2));
        ENCODING_TABLE.put('C', new Sequence(2, 1, 2, 1, 1, 2, 1, 1, 1));
        ENCODING_TABLE.put('D', new Sequence(1, 1, 1, 1, 2, 2, 1, 1, 2));
        ENCODING_TABLE.put('E', new Sequence(2, 1, 1, 2, 2, 2, 1, 1, 1));
        ENCODING_TABLE.put('F', new Sequence(1, 1, 2, 1, 2, 2, 1, 1, 1));
        ENCODING_TABLE.put('G', new Sequence(1, 1, 1, 1, 1, 2, 2, 1, 2));
        ENCODING_TABLE.put('H', new Sequence(2, 1, 1, 1, 1, 2, 2, 1, 1));
        ENCODING_TABLE.put('I', new Sequence(1, 1, 2, 1, 1, 2, 2, 1, 1));
        ENCODING_TABLE.put('J', new Sequence(1, 1, 1, 1, 2, 2, 2, 1, 1));
        ENCODING_TABLE.put('K', new Sequence(2, 1, 1, 1, 1, 1, 1, 2, 2));
        ENCODING_TABLE.put('L', new Sequence(1, 1, 2, 1, 1, 1, 1, 2, 2));
        ENCODING_TABLE.put('M', new Sequence(2, 1, 2, 1, 1, 1, 1, 2, 1));
        ENCODING_TABLE.put('N', new Sequence(1, 1, 1, 1, 2, 1, 1, 2, 2));
        ENCODING_TABLE.put('O', new Sequence(2, 1, 1, 1, 2, 1, 1, 2, 1));
        ENCODING_TABLE.put('P', new Sequence(1, 1, 2, 1, 2, 1, 1, 2, 1));
        ENCODING_TABLE.put('Q', new Sequence(1, 1, 1, 1, 1, 1, 2, 2, 2));
        ENCODING_TABLE.put('R', new Sequence(2, 1, 1, 1, 1, 1, 2, 2, 1));
        ENCODING_TABLE.put('S', new Sequence(1, 1, 2, 1, 1, 1, 2, 2, 1));
        ENCODING_TABLE.put('T', new Sequence(1, 1, 1, 1, 2, 1, 2, 2, 1));
        ENCODING_TABLE.put('U', new Sequence(2, 2, 1, 1, 1, 1, 1, 1, 2));
        ENCODING_TABLE.put('V', new Sequence(1, 2, 2, 1, 1, 1, 1, 1, 2));
        ENCODING_TABLE.put('W', new Sequence(2, 2, 2, 1, 1, 1, 1, 1, 1));
        ENCODING_TABLE.put('X', new Sequence(1, 2, 1, 1, 2, 1, 1, 1, 2));
        ENCODING_TABLE.put('Y', new Sequence(2, 2, 1, 1, 2, 1, 1, 1, 1));
        ENCODING_TABLE.put('Z', new Sequence(1, 2, 2, 1, 2, 1, 1, 1, 1));
        ENCODING_TABLE.put('-', new Sequence(1, 2, 1, 1, 1, 1, 2, 1, 2));
        ENCODING_TABLE.put('.', new Sequence(2, 2, 1, 1, 1, 1, 2, 1, 1));
        ENCODING_TABLE.put(' ', new Sequence(1, 2, 2, 1, 1, 1, 2, 1, 1));
        ENCODING_TABLE.put('$', new Sequence(1, 2, 1, 2, 1, 2, 1, 1, 1));
        ENCODING_TABLE.put('/', new Sequence(1, 2, 1, 2, 1, 1, 1, 2, 1));
        ENCODING_TABLE.put('+', new Sequence(1, 2, 1, 1, 1, 2, 1, 2, 1));
        ENCODING_TABLE.put('%', new Sequence(1, 1, 1, 2, 1, 2, 1, 2, 1));
    }

    /// The static population of the extended encoding table
    static
    {
        // TODO
    }


    @Override
    public boolean hasExtendedCharset()
    {
        return true;
    }

    @Override
    public CharSequence getCharset()
    {
        return CHARSET;
    }

    @Override
    public boolean isFixedLength()
    {
        return false;
    }

    @Override
    public boolean isVariableLength()
    {
        return true;
    }

    @Override
    public boolean isSelfChecking()
    {
        return true;
    }

    @Override
    public boolean isChecksumAvailable()
    {
        return true;
    }

    @Override
    public boolean isDiscrete()
    {
        return true;
    }

    @Override
    public boolean isContinuous()
    {
        return false;
    }

    @Override
    public boolean isChecksumRequired()
    {
        return false;
    }

    @Override
    public Sequence encode(String text)
    {
        Map<Character, Sequence> encodingTable
                = super.getConfig().useExtendedCharset() ? EXTENDED_ENCODING_TABLE : ENCODING_TABLE;
        Sequence rv = new Sequence(QUIET_ZONE).append(START_STOP).append(PAD);
        for (char c : text.toCharArray())
        {
            rv.append(encodingTable.get(c));
            rv.append(PAD);
        }
        if (super.getConfig().useChecksum())
        {
            rv.append(encodingTable.get(calculateChecksum(text))).append(PAD);
        }
        return rv.append(START_STOP).append(QUIET_ZONE);
    }

    @Override
    public String decode(Sequence sequence)
    {
        Map<Character, Sequence> encodingTable
                = super.getConfig().useExtendedCharset() ? EXTENDED_ENCODING_TABLE : ENCODING_TABLE;

        /// Traditionally, start / stop sequences are represented as "*"
        String text = "*";
        for (int i = 9; i < sequence.size() - (super.getConfig().useChecksum() ? 18 : 9); i += 9)
        {
            Sequence slice = sequence.slice(i, i + 8);
            for (Map.Entry<Character, Sequence> e : encodingTable.entrySet())
            {
                if (e.getValue().equals(slice))
                {
                    text += e.getKey();
                    break;
                }
            }
        }
        text += '*';
        return text;
    }

    @Override
    public Sequence getStartSequence()
    {
        return START_STOP;
    }

    @Override
    public Sequence getStopSequence()
    {
        return START_STOP;
    }

    @Override
    public char calculateChecksum(String text)
    {
        String charset = super.getConfig().useExtendedCharset() ? EXTENDED_CHARSET : CHARSET;
        int checksum = 0;
        for (char c : text.toCharArray())
        {
            checksum += charset.indexOf(c);
        }
        return charset.charAt(checksum % 43);
    }

    /**
     * Constructs a Code39 symbology with the provided configuration parameters
     *
     * @param useChecksum        Should a checksum digit be included in encoding and discarded
     *                           in decoding?
     * @param useExtendedCharset Should the extended character set be used?
     */
    public Code39(boolean useChecksum, boolean useExtendedCharset)
    {
        super(new Configuration().setChecksumEnabled(useChecksum)
                .setExtendedCharsetEnabled(useExtendedCharset));
    }

    /**
     * Constructs a Code39 with a default configuration
     */
    public Code39()
    {
        super(new Configuration());
    }
}
