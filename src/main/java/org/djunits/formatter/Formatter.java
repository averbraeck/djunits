package org.djunits.formatter;

/**
 * Formatter of values with width and precision.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @author Peter Knoppers
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
     * @param width the number of characters in the result
     * @param precision the number of fractional digits in the result
     * @param converter the format conversion specifier
     * @return suitable for formatting a float or double
     */
    static String formatString(final int width, final int precision, final String converter)
    {
        return String.format("%%%d.%d%s", width, precision, converter);
    }

    /**
     * Format a floating point value.
     * @param value the value to format
     * @param width the number of characters in the result
     * @param precision the number of fractional digits in the result
     * @return the formatted floating point value
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
     * @param value the value to format
     * @param size the number of characters in the result
     * @return the formatted floating point value
     */
    public static String format(final float value, final int size)
    {
        return Formatter.format(value, size, Formatter.DEFAULTPRECISION);
    }

    /**
     * Format a floating point value.
     * @param value the value to format
     * @return the formatted floating point value
     */
    public static String format(final float value)
    {
        return format(value, Format.DEFAULTSIZE, Formatter.DEFAULTPRECISION);
    }

    /**
     * Format a floating point value.
     * @param value the value to format
     * @param width the number of characters in the result
     * @param precision the number of fractional digits in the result
     * @return the formatted floating point value
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
     * @param value the value to format
     * @param size the number of characters in the result
     * @return the formatted floating point value
     */
    public static String format(final double value, final int size)
    {
        return format(value, size, Formatter.DEFAULTPRECISION);
    }

}
