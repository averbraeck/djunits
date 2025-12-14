package org.djunits.old.value.formatter;

/**
 * Format a value in Engineering notation, or normal floating point notation if that can represent the value more accurately.
 * <p>
 * Copyright (c) 2015-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @version $Revision: 954 $, $LastChangedDate: 2022-01-10 03:42:57 +0100 (Mon, 10 Jan 2022) $, by $Author: averbraeck $,
 *          initial version 11 sep. 2015 <br>
 * @author Peter Knoppers
 */
public final class EngineeringFormatter
{
    /**
     * This class shall never be instantiated.
     */
    private EngineeringFormatter()
    {
        // Prevent instantiation of this class
    }

    /**
     * Switch to/from upper case E for exponent indicator. The default is to use upper case.
     * @param upper if true; an upper case E will be used; if false; a lower case e will be used
     */
    public static void setUpperCaseFormat(final boolean upper)
    {
        exponentFormat = upper ? "%%%d.%dE" : "%%%d.%de";
    }

    /** Format constructor for mantissa plus exponent notation. */
    private static String exponentFormat = "%%%d.%dE";

    /** Format constructor for mantissa-only notation. */
    private static final String FLOATFORMAT = "%%%d.%df";

    /**
     * Minimum value to display in non-scientific / non-engineering notation. The value 0.0001 ensures that we switch to
     * mantissa + exponent notation around the point were we're about to lose precision due to underflow. This is the point
     * where the width of the zeros before the first non-zero digit starts to exceed the width of a E + exponent value string.
     */
    private static final double EFORMATLIMIT = 0.0001;

    /**
     * Format a double in Engineering format using <code>DEFAULTSIZE</code> room.
     * @param val the value to format
     * @return the formatted value
     */
    public static String format(final double val)
    {
        return format(val, Format.DEFAULTSIZE);
    }

    /**
     * Format a double in Engineering format.
     * @param val the value to format
     * @param room the width in characters of the result (minimum value is 10; values below this limit will be treated as 10)
     * @return the formatted value
     */
    public static String format(final double val, final int room)
    {
        if (room < 10)
        {
            return format(val, 10); // The EngineeringFormatter needs at least 10 positions
        }
        if (Double.isNaN(val) || 0d == val || Double.isInfinite(val))
        {
            String format = String.format(FLOATFORMAT, room, room);
            return padd(String.format(format, val), room);
        }
        double absVal = Math.abs(val);
        int roomForSignAndFraction = val > 0 ? 2 : 3;
        // Floating point values should show at least one digit after the radix symbol (dot)
        // max is the maximum value to display in non-scientific / non-engineering notation
        double max = Math.pow(10, room - roomForSignAndFraction);
        if (absVal < max - 0.5 && absVal > EFORMATLIMIT)
        {
            // Express as floating point number.
            String format = String.format(FLOATFORMAT, room, room - 2);
            String result = String.format(format, val);
            int length = result.length();
            if (length > room)
            {
                format = String.format(FLOATFORMAT, room, room - 2 + room - length);
                result = String.format(format, val);
            }
            return result;
        }
        // Express in scientific notation using at least 2 digits for the exponent.
        int roomForSignRadixAndExponent = val > 0 ? 6 : 7;
        String format = String.format(exponentFormat, room, room - roomForSignRadixAndExponent);
        String result = String.format(format, val);
        int length = result.length();
        if (length > room) // 3-digit exponent?
        {
            format = String.format(exponentFormat, room, room - length + room - roomForSignRadixAndExponent);
            result = String.format(format, val);
        }
        result = convertToEngineering(result);
        if (result.length() < room) // Exponent 100, or 101 was reduced to 99 which is one digit shorter
        {
            format = String.format(exponentFormat, room, 1 + room - length + room - roomForSignRadixAndExponent);
            result = convertToEngineering(String.format(format, val));
        }
        return result;
    }

    /**
     * Make the exponent of a floating point value a multiple of 3. Assumes that the first dot or comma is the radix symbol and
     * 'e' or 'E' is used at the exponent symbol.
     * @param in String representation of a floating point value
     * @return The engineering formatted value
     */
    public static String convertToEngineering(final String in)
    {
        int positionOfE = in.indexOf("E");
        if (positionOfE < 0)
        {
            positionOfE = in.indexOf("e");
            if (positionOfE < 0)
            {
                return in;
            }
        }
        StringBuilder result = new StringBuilder();
        int exponent = Integer.parseInt(in.substring(positionOfE + 1));
        if (0 == exponent % 3)
        {
            return in;
        }

        String radix = null;
        int pos;
        for (pos = 0; pos < positionOfE; pos++)
        {
            String symbol = in.substring(pos, pos + 1);
            Character c = symbol.charAt(0);
            if (c != '.' && c != ',' || null != radix)
            {
                result.append(symbol);
            }
            else
            {
                radix = symbol;
                break;
            }
        }
        if (null == radix)
        {
            return in; // No radix symbol encountered
        }
        pos++;
        // Shift the radix to the right
        while (0 != exponent % 3)
        {
            String symbol = in.substring(pos, pos + 1);
            result.append(symbol);
            pos++;
            exponent--;
        }
        result.append(radix);
        for (; pos < positionOfE; pos++)
        {
            result.append(in.substring(pos, pos + 1));
        }
        result.append(in.substring(positionOfE, positionOfE + 1));
        String exponentString = String.format("%+03d", exponent);
        result.append(exponentString);
        return result.toString();
    }

    /**
     * Extend a String with spaces, or trim it to reach a specified length.
     * @param in input string
     * @param width length of the result
     * @return the extended or trimmed input string
     */
    public static String padd(final String in, final int width)
    {
        String format = String.format("%%%d.%ds", width, width);
        return String.format(format, in);
    }

}
