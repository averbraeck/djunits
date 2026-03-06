package org.djunits.formatter;

/**
 * Format a floating point number in a reasonable way.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
public final class Format
{
    /**
     * This class shall never be instantiated.
     */
    private Format()
    {
        // Prevent instantiation of this class
    }

    /** Default total width of formatted value. */
    public static final int DEFAULTSIZE = 10;

    /**
     * Format a floating point value.
     * @param value the value to format
     * @return the formatted floating point value
     */
    public static String format(final double value)
    {
        return EngineeringFormatter.format(value, DEFAULTSIZE);
    }

}
