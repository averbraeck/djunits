package org.djunits.value.formatter;

/**
 * Formatter of values with width and precision.
 * <p>
 * Copyright (c) 2015-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @version $Revision: 954 $, $LastChangedDate: 2022-01-10 03:42:57 +0100 (Mon, 10 Jan 2022) $, by $Author: averbraeck $,
 *          initial version 11 sep. 2015 <br>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class Formatter
{
    /**
     * This class shall never be instantiated.
     */
    private Formatter()
    {
        // Prevent instantiation of this class
    }

    /** Default number of fraction digits. */
    public static final int DEFAULTPRECISION = 3;

    /**
     * Build a format string.
     * @param width int; the number of characters in the result
     * @param precision int; the number of fractional digits in the result
     * @param converter String; the format conversion specifier
     * @return String; suitable for formatting a float or double
     */
    static String formatString(final int width, final int precision, final String converter)
    {
        return String.format("%%%d.%d%s", width, precision, converter);
    }

    /**
     * Format a floating point value.
     * @param value float; the value to format
     * @param width int; the number of characters in the result
     * @param precision int; the number of fractional digits in the result
     * @return String; the formatted floating point value
     */
    public static String format(final float value, final int width, final int precision)
    {
        if (0 == value || Math.abs(value) > 0.01 && Math.abs(value) < 9999.0)
        {
            return String.format(formatString(width, precision, "f"), value);
        }
        return String.format(formatString(width, precision, "e"), value);
    }

    /**
     * Format a floating point value.
     * @param value float; the value to format
     * @param size int; the number of characters in the result
     * @return String; the formatted floating point value
     */
    public static String format(final float value, final int size)
    {
        return Formatter.format(value, size, Formatter.DEFAULTPRECISION);
    }

    /**
     * Format a floating point value.
     * @param value float; the value to format
     * @return String; the formatted floating point value
     */
    public static String format(final float value)
    {
        return format(value, Format.DEFAULTSIZE, Formatter.DEFAULTPRECISION);
    }

    /**
     * Format a floating point value.
     * @param value double; the value to format
     * @param width int; the number of characters in the result
     * @param precision int; the number of fractional digits in the result
     * @return String; the formatted floating point value
     */
    public static String format(final double value, final int width, final int precision)
    {
        if (0 == value || Math.abs(value) > 0.01 && Math.abs(value) < 9999.0)
        {
            return String.format(formatString(width, precision, "f"), value);
        }
        return String.format(formatString(width, precision, "e"), value);
    }

    /**
     * Format a floating point value.
     * @param value double; the value to format
     * @param size int; the number of characters in the result
     * @return String; the formatted floating point value
     */
    public static String format(final double value, final int size)
    {
        return format(value, size, Formatter.DEFAULTPRECISION);
    }

}
