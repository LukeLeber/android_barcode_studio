// This file is protected under the KILLGPL.
// For more information visit <insert_valid_link_to_killgpl_here>
// <p/>
// Copyright (c) Luke A. Leber <LukeLeber@gmail.com> 2014
//__________________________________________________________________________________________________

package com.lukeleber.barcodestudio.symbologies;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;

import com.lukeleber.barcodestudio.AbstractSymbology;
import com.lukeleber.barcodestudio.DecoderException;
import com.lukeleber.barcodestudio.EncoderException;
import com.lukeleber.barcodestudio.Module;
import com.lukeleber.barcodestudio.Sequence;

/**
 * Code-128 is high density symbology that is capable of encoding the (0 - 128) ASCII character 
 * set.  Several variations of Code-128 include EAN-128, ISBT-128, GS1-128, UCC-128, and USS-128.
 *
 * Perhaps the most common usage of Code-128 is by the United States Postal Service (USPS-EAN-128).
 *
 * This class can be used as the base for implementing more specialized variations.
 *
 * TODO: Optimize algorithms for runtime considerations...as it stands this is quite slow :\
 *
 */
/*package*/ class Code128
        extends AbstractSymbology
{

    ///
    /// Internal Documentation (Code-128 Character Set)
    ///
    /// Code-128 offers three character sets (or "character modes")
    ///
    ///  Charset-A supports alphanumeric data (capitals only) and many unprintable characters
    ///  Charset-B supports alphanumeric data and few unprintable characters
    ///  Charset-C supports numeric data, effectively achieving double density.
    ///
    /// Character sets A and B can be used for both forward and reverse lookup.
    ///  Forward Lookup:
    ///   <code>
    ///     char c = ...
    ///     int encodingIndex = CHARSET_X.indexOf(c);
    ///   </code>
    ///  Reverse Lookup:
    ///   <code>
    ///     int encodingIndex = ...
    ///     char c = CHARSET_X.charAt(encodingIndex);
    ///   </code>
    ///
    /// The values for character set C can be used directly (00 - 99) with the following extras:
    /// 100 - SHIFT_B
    /// 101 - SHIFT_A
    /// 102 - FNC1
    /// 103 - START_A
    /// 104 - START_B
    /// 105 - START_C

    ///
    /// ASCII Control Characters
    ///

    /// Null Control Character
    protected final static char NUL = 0x0000;
    
    /// Start of Header Control Character
    protected final static char SOH = 0x0001;

    /// Start of Text Control Character
    protected final static char STX = 0x0002;

    /// End of Text Control Character
    protected final static char ETX = 0x0003;

    /// End of Transmission Control Character
    protected final static char EOT = 0x0004;

    /// Enquiry Control Character
    protected final static char ENQ = 0x0005;

    /// Acknowledge Control Character
    protected final static char ACK = 0x0006;

    /// Bell Control Character
    protected final static char BEL = 0x0007;

    /// BackSpace Control Character
    protected final static char BS = 0x0008;

    /// Horizontal Tabulation Control Character
    protected final static char HT = 0x0009;

    /// Line Feed Control Character
    protected final static char LF = 0x000A;

    /// Vertical Tabulation Control Character
    protected final static char VT = 0x000B;

    /// Form Feed Control Character
    protected final static char FF = 0x000C;

    /// Carriage Return Control Character
    protected final static char CR = 0x000D;

    /// Shift Out Control Character
    protected final static char SO = 0x000E;

    /// Shift In Control Character
    protected final static char SI = 0x000F;

    /// Data Link Escape Control Character
    protected final static char DLE = 0x0010;

    /// Device Control 1 Control Character
    protected final static char DC1 = 0x0011;

    /// Device Control 2 Control Character
    protected final static char DC2 = 0x0012;

    /// Device Control 3 Control Character
    protected final static char DC3 = 0x0013;

    /// Device Control 4 Control Character
    protected final static char DC4 = 0x0014;

    /// Negative Acknowledge Control Character
    protected final static char NAK = 0x0015;

    /// Synchronous Idle Control Character
    protected final static char SYN = 0x0016;

    /// End of Transmission Block Control Character
    protected final static char ETB = 0x0017;

    /// Cancel Control Character
    protected final static char CAN = 0x0018;

    /// End of Medium Control Character
    protected final static char EM = 0x0019;

    /// Substitute Control Character
    protected final static char SUB = 0x001A;

    /// Escape Control Character
    protected final static char ESC = 0x001B;

    /// File Separator Control Character
    protected final static char FS = 0x001C;

    /// Group Separator Control Character
    protected final static char GS = 0x001D;

    /// Record Separator Control Character
    protected final static char RS = 0x001E;

    /// Unit Separator Control Character
    protected final static char US = 0x001F;

    /// Delete Control Character
    protected final static char DEL = 0x007F;

    ///
    /// The following are not ASCII control characters, but rather signals unique to Code-128
    /// Note: Some variations of this symbology may map these signals to different character(s)!
    ///       Treat the values as loosely as feasible in the future!
    ///

    /// Special Function 1 Signal
    protected final static char FNC1 = 0x00CA;

    /// Special Function 2 Signal
    protected final static char FNC2 = 0x00C5;

    /// Special Function 3 Signal
    protected final static char FNC3 = 0x00C4;

    /// Special Function 4 Signal
    protected final static char FNC4 = 0x00C8;

    /// Shift Signal
    protected final static char SHIFT = 0x00C6;


    /// Note - The following start / shift characters do not have a printable representation and
    /// the values chosen for them are arbitrary (not defined by any specification / standard).
    /// Having these arbitrary 'sentinels' is useful for injecting optimized shifting
    /// strategies during the encoding process that were not present in the input text.

    /// Arbitrary value for the Mode-A start character
    protected final static char START_A = 0x00DA;

    /// Arbitrary value for the Mode-B start character
    protected final static char START_B = 0x00DB;

    /// Arbitrary value for the Mode-C start character
    protected final static char START_C = 0x00DC;

    /// Arbitrary value for the "shift-to-mode-a" character
    protected final static char SHIFT_A = 0x00DD;

    /// Arbitrary value for the "shift-to-mode-b" character
    protected final static char SHIFT_B = 0x00DE;

    /// Arbitrary value for the "shift-to-mode-c" character
    protected final static char SHIFT_C = 0x00DF;

    /// The fugly character set for mode A
    protected final static String CHARSET_A = " !\"#$%&\'()*+,-./0123456789:;<=>?@" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_" + NUL + SOH + STX + ETX + EOT + ENQ + ACK + BEL +
            BS + HT + LF + VT + FF + CR + SO + SI + DLE + DC1 + DC2 + DC3 + DC4 + NAK + SYN +
            ETB + CAN + EM + SUB + ESC + FS + GS + RS + US + FNC3 + FNC2 + SHIFT +
            SHIFT_C + SHIFT_B + FNC4 + FNC1 + START_A + START_B + START_C;

    /// The fugly character set for mode B
    protected final static String CHARSET_B = " !\"#$%&\'()*+,-./0123456789:;<=>?@" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~" + DEL +
            FNC3 + FNC2 + SHIFT + SHIFT_C + FNC4 + SHIFT_A + FNC1 + START_A + START_B + START_C;

    /// The not-quite-so-fugly character set for mode C
    protected final static String CHARSET_C = "0123456789" +
            FNC1 + SHIFT_B + SHIFT_A + START_A + START_B + START_C;

    /// The ultra-fugly code-128 character set to be used for input validation.  Essentially,
    /// this string is a combination of all 3 character modes minus shift and start codes.
    protected final static String CHARSET = " !\"#$%&\'()*+,-./0123456789:;<=>?@" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_" + NUL + SOH + STX + ETX + EOT + ENQ + ACK + BEL +
            BS + HT + LF + VT + FF + CR + SO + SI + DLE + DC1 + DC2 + DC3 + DC4 + NAK + SYN +
            ETB + CAN + EM + SUB + ESC + FS + GS + RS + US + DEL + FNC1 + FNC2 + FNC3 + FNC4 +
            SHIFT;

    /**
     * Retrieves the character set for the provided
     * {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode}.
     * <br> <strong>Precondition: <i>mode</i> must be one of <i>MODE_A</i> <i>MODE_B</i>, or
     * <i>MODE_C</i></strong> lest a runtime assertion be triggered
     *
     * @param mode
     *         the {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode} whose
     *         character set should be retrieved.
     *
     * @return the character set of the provided
     * {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode}
     */
    @SuppressLint("Assert")
    protected final String getCharsetFor(CharacterMode mode)
    {
        switch (mode)
        {
            case MODE_A:
                return CHARSET_A;
            case MODE_B:
                return CHARSET_B;
            case MODE_C:
                return CHARSET_C;
            default:
                assert false : "Check to make sure only MODE_A, " +
                        "MODE_B, and MODE_C exist within the Code128.CharacterMode enumeration.";
                return null;
        }
    }

    ///
    /// Internal Documentation (Code-128 Encoding)
    ///
    /// The general Code-128 structure is as follows:
    /// [QUIET_ZONE][START-MODE][DATA][CHECK-DIGIT][STOP][TERMINATION][QUIET ZONE]
    ///
    /// where:
    ///
    ///  The leading quiet zone is "     "
    ///  The start-mode is one of three start mode sequences
    ///   A: "|| |    |  "
    ///   B: "|| |  |    "
    ///   C: "|| |  |||  "
    ///  The data is the optimized encoding (including shift strategies)
    ///  The checksum digit is calculated as follows:
    ///     <code>
    ///       int checksum = START_A ? 103 : (START_B ? 104 : 105);
    ///       for(int i = 0; i < data.length; ++i)
    ///       {
    ///         checksum += ((i + 1) * CHARSET[data[i]]);
    ///       }
    ///       checksum %= 103;
    ///     </code>
    ///  The stop sequence is "||   ||| | "
    ///  The termination sequence is "||";
    ///  The trailing quiet zone is "     "
    ///

    /// The quiet zone of the Code-128 symbology
    protected final static Sequence QUIET_ZONE = new Sequence(false, 5);

    /// The encoding of Start Mode A
    protected final static Sequence START_A_ENCODING = new Sequence(2, 1, 1, 4, 1, 2);

    /// The encoding of Start Mode B
    protected final static Sequence START_B_ENCODING = new Sequence(2, 1, 1, 2, 1, 4);

    /// The encoding of Start Mode C
    protected final static Sequence START_C_ENCODING = new Sequence(2, 1, 1, 2, 3, 2);

    /// The encoding startIndex of Start Mode A
    protected final static int START_A_INDEX = 103;

    /// The encoding startIndex of Start Mode B
    protected final static int START_B_INDEX = 104;

    /// The encoding startIndex of Start Mode C
    protected final static int START_C_INDEX = 105;

    /// The stop-sequence of the Code-128 symbology
    protected final static Sequence STOP_SEQUENCE = new Sequence(2, 3, 3, 1, 1, 1);

    /// The termination-sequence of the Code-128 symbology
    protected final static Module TERMINATION_BAR = new Module(true, 2);

    /// Lookup table of all possible Code-128 encodings
    protected final static Sequence[] ENCODING_TABLE = new Sequence[]
    {
            new Sequence(2, 1, 2, 2, 2, 2), // 0
            new Sequence(2, 2, 2, 1, 2, 2), // 1
            new Sequence(2, 2, 2, 2, 2, 1), // 2
            new Sequence(1, 2, 1, 2, 2, 3), // 3
            new Sequence(1, 2, 1, 3, 2, 2), // 4
            new Sequence(1, 3, 1, 2, 2, 2), // 5
            new Sequence(1, 2, 2, 2, 1, 3), // 6
            new Sequence(1, 2, 2, 3, 1, 2), // 7
            new Sequence(1, 3, 2, 2, 1, 2), // 8
            new Sequence(2, 2, 1, 2, 1, 3), // 9
            new Sequence(2, 2, 1, 3, 1, 2), // 10
            new Sequence(2, 3, 1, 2, 1, 2), // 11
            new Sequence(1, 1, 2, 2, 3, 2), // 12
            new Sequence(1, 2, 2, 1, 3, 2), // 13
            new Sequence(1, 2, 2, 2, 3, 1), // 14
            new Sequence(1, 1, 3, 2, 2, 2), // 15
            new Sequence(1, 2, 3, 1, 2, 2), // 16
            new Sequence(1, 2, 3, 2, 2, 1), // 17
            new Sequence(2, 2, 3, 2, 1, 1), // 18
            new Sequence(2, 2, 1, 1, 3, 2), // 19
            new Sequence(2, 2, 1, 2, 3, 1), // 20
            new Sequence(2, 1, 3, 2, 1, 2), // 21
            new Sequence(2, 2, 3, 1, 1, 2), // 22
            new Sequence(3, 1, 2, 1, 3, 1), // 23
            new Sequence(3, 1, 1, 2, 2, 2), // 24
            new Sequence(3, 2, 1, 1, 2, 2), // 25
            new Sequence(3, 2, 1, 2, 2, 1), // 26
            new Sequence(3, 1, 2, 2, 1, 2), // 27
            new Sequence(3, 2, 2, 1, 1, 2), // 28
            new Sequence(3, 2, 2, 2, 1, 1), // 29
            new Sequence(2, 1, 2, 1, 2, 3), // 30
            new Sequence(2, 1, 2, 1, 2, 3), // 31
            new Sequence(2, 3, 2, 1, 2, 1), // 32
            new Sequence(1, 1, 1, 3, 2, 3), // 33
            new Sequence(1, 3, 1, 1, 2, 3), // 34
            new Sequence(1, 3, 1, 3, 2, 1), // 35
            new Sequence(1, 1, 2, 3, 1, 3), // 36
            new Sequence(1, 3, 2, 1, 1, 3), // 37
            new Sequence(1, 3, 2, 3, 1, 1), // 38
            new Sequence(2, 1, 1, 3, 1, 3), // 39
            new Sequence(2, 3, 1, 1, 1, 3), // 40
            new Sequence(2, 3, 1, 3, 1, 1), // 41
            new Sequence(1, 1, 2, 1, 3, 3), // 42
            new Sequence(1, 1, 2, 3, 3, 1), // 43
            new Sequence(1, 3, 2, 1, 3, 1), // 44
            new Sequence(1, 1, 3, 1, 2, 3), // 45
            new Sequence(1, 1, 3, 3, 2, 1), // 46
            new Sequence(1, 3, 3, 1, 2, 1), // 47
            new Sequence(3, 1, 3, 1, 2, 1), // 48
            new Sequence(2, 1, 1, 3, 3, 1), // 49
            new Sequence(2, 3, 1, 1, 3, 1), // 50
            new Sequence(2, 1, 3, 1, 1, 3), // 51
            new Sequence(2, 1, 3, 3, 1, 1), // 52
            new Sequence(2, 1, 3, 1, 3, 1), // 53
            new Sequence(3, 1, 1, 1, 2, 3), // 54
            new Sequence(3, 1, 1, 3, 2, 1), // 55
            new Sequence(3, 3, 1, 1, 2, 1), // 56
            new Sequence(3, 1, 2, 1, 1, 3), // 57
            new Sequence(3, 1, 2, 3, 1, 1), // 58
            new Sequence(3, 3, 2, 1, 1, 1), // 59
            new Sequence(3, 1, 4, 1, 1, 1), // 60
            new Sequence(2, 2, 1, 4, 1, 1), // 61
            new Sequence(4, 3, 1, 1, 1, 1), // 62
            new Sequence(1, 1, 1, 2, 2, 4), // 63
            new Sequence(1, 1, 1, 4, 2, 2), // 64
            new Sequence(1, 2, 1, 1, 2, 4), // 65
            new Sequence(1, 2, 1, 4, 2, 1), // 66
            new Sequence(1, 4, 1, 1, 2, 2), // 67
            new Sequence(1, 4, 1, 2, 2, 1), // 68
            new Sequence(1, 1, 2, 2, 1, 4), // 69
            new Sequence(1, 1, 2, 4, 1, 2), // 70
            new Sequence(1, 2, 2, 1, 1, 4), // 71
            new Sequence(1, 2, 2, 4, 1, 1), // 72
            new Sequence(1, 4, 2, 1, 1, 2), // 73
            new Sequence(1, 4, 2, 2, 1, 1), // 74
            new Sequence(2, 4, 1, 2, 1, 1), // 75
            new Sequence(2, 2, 1, 1, 1, 4), // 76
            new Sequence(4, 1, 3, 1, 1, 1), // 77
            new Sequence(2, 4, 1, 1, 1, 2), // 78
            new Sequence(1, 3, 4, 1, 1, 1), // 79
            new Sequence(1, 1, 1, 2, 4, 2), // 80
            new Sequence(1, 2, 1, 1, 4, 2), // 81
            new Sequence(1, 2, 1, 2, 4, 1), // 82
            new Sequence(1, 1, 4, 2, 1, 2), // 83
            new Sequence(1, 2, 4, 1, 1, 2), // 84
            new Sequence(1, 2, 4, 2, 1, 1), // 85
            new Sequence(4, 1, 1, 2, 1, 2), // 86
            new Sequence(4, 2, 1, 1, 1, 2), // 87
            new Sequence(4, 2, 1, 2, 1, 1), // 88
            new Sequence(2, 1, 2, 1, 4, 1), // 89
            new Sequence(2, 1, 4, 1, 2, 1), // 90
            new Sequence(4, 1, 2, 1, 2, 1), // 91
            new Sequence(1, 1, 1, 1, 4, 3), // 92
            new Sequence(1, 1, 1, 3, 4, 1), // 93
            new Sequence(1, 3, 1, 1, 4, 1), // 94
            new Sequence(1, 1, 4, 1, 1, 3), // 95
            new Sequence(1, 1, 4, 3, 1, 1), // 96
            new Sequence(4, 1, 1, 1, 1, 3), // 97
            new Sequence(4, 1, 1, 3, 1, 1), // 98
            new Sequence(1, 1, 3, 1, 4, 1), // 99
            new Sequence(1, 1, 4, 1, 3, 1), // 100
            new Sequence(3, 1, 1, 1, 4, 1), // 101
            new Sequence(4, 1, 1, 1, 3, 1), // 102
            START_A_ENCODING,
            START_B_ENCODING,
            START_C_ENCODING
    };

    /**
     * Retrieves the start sequence for the provided
     * {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode}.
     * <br> <strong>Precondition: <i>mode</i> must be one of <i>MODE_A</i> <i>MODE_B</i>, or
     * <i>MODE_C</i></strong> lest a runtime assertion be triggered
     *
     * @param mode
     *         the {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode} whose
     *         start sequence should be retrieved.
     *
     * @return the start sequence of the provided
     * {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode}
     */
    @SuppressLint("Assert")
    protected final Sequence getStartSequenceFor(CharacterMode mode)
    {
        switch(mode)
        {
            case MODE_A:
                return ENCODING_TABLE[START_A_INDEX];
            case MODE_B:
                return ENCODING_TABLE[START_B_INDEX];
            case MODE_C:
                return ENCODING_TABLE[START_C_INDEX];
            default:
                assert false : "Check to make sure only MODE_A, " +
                        "MODE_B, and MODE_C exist within the Code128.CharacterMode enumeration.";
                return null;
        }
    }

    /**
     * Default constructor for Code-128
     */
    /*package*/ Code128()
    {
        super(new Configuration().setChecksumEnabled(true).setExtendedCharsetEnabled(false));
    }

    /**
     * Retrieves the optimum mode for the provided offset in the provided text string based
     * upon the encoding rules and a preferred encoding mode.  This method does not look
     * ahead like its "pseudo-recursive" partner.
     *
     * @param text the text to analyze
     *
     * @param offset the offset at which to analyze
     *
     * @param preferred the {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode}
     *                  that should be returned if the option exists
     *
     * @return the optimized {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode}
     *
     */
    @TargetApi(19)
    protected final CharacterMode getMode0(String text, int offset, CharacterMode preferred)
    {
        final char c = text.charAt(offset);
        if(Character.isLowerCase(c) || 
                c == '`' || c == '{' || c == '|' || c == '}' || c == '~' || c == DEL)
        {
            return CharacterMode.MODE_B;
        }
        else if(!Character.isAlphabetic(c))
        {
            return CharacterMode.MODE_A;
        }
        else if(preferred != CharacterMode.MODE_C)
        {
            return preferred;
        }
        return CharacterMode.MODE_A;
    }

    /**
     * Retrieves the optimized
     * {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode} for the provided
     * text string at the provided offset, favoring the provided preferred mode if there are
     * two equally optimized modes.
     *
     * @param text the text to analyze
     *
     * @param offset the offset at which to analyze
     *
     * @return the optimized {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode}
     */
    @TargetApi(19)
    protected final CharacterMode getMode(String text, int offset, CharacterMode preferredMode)
    {
        final char c = text.charAt(offset++);
        if((c != FNC1 && !(Character.isDigit(c) && 
                (offset < text.length() && Character.isDigit(text.charAt(offset))))))
        {
            if(Character.isLowerCase(c) || 
                    c == '`' || c == '{' || c == '|' || c == '}' || c == '~' || c == DEL)
            {
                return CharacterMode.MODE_B;
            }
            else if(!Character.isAlphabetic(c))
            {
                return CharacterMode.MODE_A;
            }
            else if(offset < text.length())
            {
                return getMode0(text, offset, preferredMode);
            }
            else if(preferredMode != CharacterMode.MODE_C)
            {
                return preferredMode;
            }
            return CharacterMode.MODE_A;
        }
        else
        {
            return CharacterMode.MODE_C;
        }
    }

    /**
     * Retrieves the optimized
     * {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode} for the provided
     * text string at the provided offset.
     *
     * @param text the text to analyze
     *
     * @param offset the offset at which to analyze
     *
     * @return the optimized {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode}
     */
    @TargetApi(19)
    protected final CharacterMode getMode(String text, int offset)
    {
        final char c = text.charAt(offset++);
        if((c != FNC1 && !(Character.isDigit(c) && 
                (offset < text.length() && Character.isDigit(text.charAt(offset))))))
        {
            if(Character.isLowerCase(c) || 
                    c == '`' || c == '{' || c == '|' || c == '}' || c == '~' || c == DEL)
            {
                return CharacterMode.MODE_B;
            }
            else if(!Character.isAlphabetic(c))
            {
                return CharacterMode.MODE_A;
            }
            else if(offset < text.length())
            {
                return getMode(text, offset, CharacterMode.MODE_A);
            }
            return CharacterMode.MODE_A;
        }
        else
        {
            return CharacterMode.MODE_C;
        }
    }

    /**
     * Performs an encoding optimization on the provided input text that
     * injects mode shifts to ensure the most dense encoding possible.
     *
     * @param text
     *         the text to encode
     *
     * @return the optimized version of the provided input text
     */
    protected String optimize(String text)
    {
        CharacterMode mode = getMode(text, 0);
        StringBuilder rv = new StringBuilder(new String(new char[] {mode.start}));
        for (int i = 0; i < text.length(); ++i)
        {
            final char c = text.charAt(i);
            CharacterMode shift = getMode(text, i, mode);
            if (mode != shift)
            {
                rv.append(shift.shift);
                mode = shift;
            }
            if(mode == CharacterMode.MODE_C)
            {
                if(c == FNC1)
                {
                    rv.append(FNC1);
                }
                else
                {
                    rv.append(c).append(text.charAt(++i));
                }
            }
            else
            {
                rv.append(c);
            }
        }
        return rv.toString();
    }

    @Override
    public String toString()
    {
        return "Code 128";
    }

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
        return true;
    }

    /**
     * Is this a self-checking symbology?
     *
     * @return true if this symbology is self-checking, otherwise false
     */
    @Override
    public boolean isSelfChecking()
    {
        return true;
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
        return true;
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
        return true;
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
     * Retrieves the character set that is used in this Symbology.  Note that the character set
     * that is returned may depend on the configuration options of a particular symbology (IE. some
     * symbologies offer an extended charset).
     *
     * @return the character set that is used in this Symbology
     */
    @Override
    public String getCharset()
    {
        return CHARSET;
    }

    /**
     * Calculates a checksum digit for the provided text.
     * <p/>
     * <strong>Note - it is assumed that the starting mode is Mode-A.</strong>
     *
     * @param text
     *         the text to evaluate
     *
     * @return a checksum digit calculated from the provided text
     *
     * @throws com.lukeleber.barcodestudio.ChecksumNotSupportedException
     *         if a checksum is not supported by this symbology
     */
    @Override
    public char calculateChecksum(String text)
    {
        /// TODO: Rethink public API
        return 0;
    }

    /**
     * Calculates a checksum digit for the provided text and performs a reverse-lookup to retrieve
     * its startIndex into the encoding table.
     *
     * @param text the text to calculate a checksum startIndex for
     *
     * @return the startIndex of the checksum into the encoding table
     *
     */
    protected int calculateChecksumIndex(String text)
    {
        String optimizedEncoding = optimize(text);
        CharacterMode mode = CharacterMode.startModeOf(optimizedEncoding.charAt(0));
        int checksum = startModeIndexOf(optimizedEncoding.charAt(0));
        for(int i = 1; i < optimizedEncoding.length(); ++i)
        {
            char c = optimizedEncoding.charAt(i);
            CharacterMode shift = CharacterMode.shiftModeOf(c, mode);

            if(mode == CharacterMode.MODE_C)
            {
                if(c == FNC1)
                {
                    checksum += (i * 102);
                }
                else if(c == SHIFT_B)
                {
                    checksum += (i * 100);
                }
                else if(c == SHIFT_A)
                {
                    checksum += (i * 101);
                }
                else
                {
                    checksum += (i * Integer.parseInt(
                            new String(new char[]{c, optimizedEncoding.charAt(i + 1)})));
                    ++i;
                }
            }
            else
            {
                checksum += (i * getCharsetFor(mode).indexOf(c));
            }
            if(shift != mode)
            {
                mode = shift;
            }

        }
        return checksum % 103;
    }

    /**
     * Encodes the provided text into a Sequence
     *
     * @param text
     *         the text to encode
     *
     * @return a Sequence encoded from the provided text
     *
     * @throws EncoderException
     *         if the encoding process fails for any reason
     */
    @Override
    public Sequence encode(String text)
    {
        String optimizedEncoding = optimize(text);
        CharacterMode currentMode =
                CharacterMode.startModeOf(optimizedEncoding.charAt(0));
        optimizedEncoding = optimizedEncoding.substring(1);
        Sequence rv = new Sequence(QUIET_ZONE).append(getStartSequenceFor(currentMode));
        for(int i = 0; i < optimizedEncoding.length(); ++i)
        {
            char c = optimizedEncoding.charAt(i);
            if(currentMode == CharacterMode.MODE_C)
            {
                if(c == FNC1)
                {
                    rv.append(ENCODING_TABLE[102]);
                }
                else if(c == SHIFT_A)
                {
                    rv.append(ENCODING_TABLE[101]);
                    currentMode = CharacterMode.MODE_A;
                }
                else if(c == SHIFT_B)
                {
                    rv.append(ENCODING_TABLE[100]);
                    currentMode = CharacterMode.MODE_B;
                }
                else
                {
                    rv.append(ENCODING_TABLE[Integer.parseInt(
                            new String(new char[] {c, optimizedEncoding.charAt(++i)}))]);
                }
            }
            else
            {
                int index = getCharsetFor(currentMode).indexOf(c);
                if (index != -1)
                {
                    rv.append(ENCODING_TABLE[index]);
                    currentMode = CharacterMode.shiftModeOf(c, currentMode);
                }
                else
                {
                    throw new EncoderException(
                            "Character \"" + c + "\" is not supported by code-128");
                }
            }
        }
        return rv.append(ENCODING_TABLE[calculateChecksumIndex(text)])
                .append(STOP_SEQUENCE)
                .append(TERMINATION_BAR)
                .append(QUIET_ZONE);

    }

    /**
     * Retrieves the startIndex of the provided start mode character
     *
     * @param startModeChar the character whose startIndex to look up
     *
     * @return the startIndex of the provided start mode character
     *
     * @throws EncoderException if the provided character is not a valid start mode character
     *
     */
    protected final int startModeIndexOf(char startModeChar)
    {
        switch (startModeChar)
        {
            case START_A:
                return START_A_INDEX;
            case START_B:
                return START_B_INDEX;
            case START_C:
                return START_C_INDEX;
            default:
                throw new EncoderException("An invalid start sequence was found");
        }
    }

    /**
     * Decodes the provided Sequence into a text string
     *
     * @param sequence
     *         the Sequence to decode
     *
     * @return a text string decoded from the provided Sequence
     *
     * @throws DecoderException if the decoding process fails for any reason
     *
     */
    @Override
    public String decode(Sequence sequence)
    {
        StringBuilder rv = new StringBuilder();
        CharacterMode currentMode = CharacterMode.modeOf(sequence.slice(1, 7));
        for(int i = 7; i < sequence.size() - 14; i += 6)
        {
            Sequence slice = sequence.slice(i, i + 6);
            int index = -1;
            for(int j = 0; j < ENCODING_TABLE.length; ++j)
            {
                if(ENCODING_TABLE[j].equals(slice))
                {
                    index = j;
                    break;
                }
            }
            if(index == -1)
            {
                throw new DecoderException("An invalid encoding sequence was encountered.");
            }
            if(currentMode == CharacterMode.MODE_C)
            {
                if(index < 100)
                {
                    rv.append(String.format("%02d", index));
                }
                else if(index == 100)
                {
                    currentMode = CharacterMode.MODE_B;
                }
                else if(index == 101)
                {
                    currentMode = CharacterMode.MODE_A;
                }
                else
                {
                    rv.append(FNC1);
                }
            }
            else
            {
                char c = getCharsetFor(currentMode).charAt(index);
                if(c == SHIFT_A)
                {
                    currentMode = CharacterMode.MODE_A;
                }
                else if(c == SHIFT_B)
                {
                    currentMode = CharacterMode.MODE_B;
                }
                else if(c == SHIFT_C)
                {
                    currentMode = CharacterMode.MODE_C;
                }
                else
                {
                    rv.append(getCharsetFor(currentMode).charAt(index));
                }
            }
        }
        return rv.toString();
    }

    /**
     * An enumeration that represents a character encoding mode of Code-128
     *
     */
    static protected enum CharacterMode
    {
        /** Mode-A */
        MODE_A(START_A_INDEX, START_A, SHIFT_A),
        /** Mode-B */
        MODE_B(START_B_INDEX, START_B, SHIFT_B),
        /** Mode-C */
        MODE_C(START_C_INDEX, START_C, SHIFT_C);

        /// The start character startIndex of this encoding mode
        final int startIndex;

        /// The shift character of this encoding mode
        final char shift;

        /// The start character of this encoding mode
        final char start;

        /**
         * Retrieves the encoding mode of the provided shift character, defaulting to the provided
         * <i>default_</i> argument if <i>c</i> is not a shift character.
         *
         * @param c the shift character whose mode to look up
         *
         * @param default_ the default return value should lookup fail
         *
         * @return the encoding mode of the provided shift character, or <i>default_</i> if no
         * such mode exists
         *
         */
        public static CharacterMode shiftModeOf(char c, CharacterMode default_)
        {
            switch(c)
            {
                case SHIFT_A:
                    return MODE_A;
                case SHIFT_B:
                    return MODE_B;
                case SHIFT_C:
                    return MODE_C;
                default:
                    return default_;
            }
        }

        /**
         * Retrieves the encoding mode of the provided start character
         *
         * @param c the start character whose mode to lookup
         *
         * @return the encoding mode of the provided start character
         *
         * @throws EncoderException if the provided character is not a start character
         *
         */
        public static CharacterMode startModeOf(char c)
        {
            switch(c)
            {
                case START_A:
                    return MODE_A;
                case START_B:
                    return MODE_B;
                case START_C:
                    return MODE_C;
                default:
                    throw new EncoderException("An invalid start character \"" + c +
                            "\" was encountered");
            }
        }

        /**
         * Retrieves the character mode of the provided start
         * {@link com.lukeleber.barcodestudio.Sequence}
         *
         * @param startSequence the {@link com.lukeleber.barcodestudio.Sequence} whose mode to
         *                      look up
         *
         * @return the character mode of the provided start
         * {@link com.lukeleber.barcodestudio.Sequence}
         *
         * @throws DecoderException if the provided {@link com.lukeleber.barcodestudio.Sequence}
         * is not a start {@link com.lukeleber.barcodestudio.Sequence}
         *
         */
        public static CharacterMode modeOf(Sequence startSequence)
        {
            if(startSequence.equals(START_A_ENCODING))
            {
                return MODE_A;
            }
            else if(startSequence.equals(START_B_ENCODING))
            {
                return MODE_B;
            }
            else if(startSequence.equals(START_C_ENCODING))
            {
                return MODE_C;
            }
            throw new DecoderException("An invalid start sequence was encountered");
        }

        /**
         * Constructs a {@link com.lukeleber.barcodestudio.symbologies.Code128.CharacterMode}
         * with the provided
         *
         * @param startIndex the index of the start character encoding into the encoding table
         *
         * @param start the start character of this mode
         *
         * @param shift the shift character of this mode
         *
         */
        private CharacterMode(int startIndex, char start, char shift)
        {
            this.startIndex = startIndex;
            this.start = start;
            this.shift = shift;
        }
    }
}
