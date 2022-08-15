package org.djunits.value.formatter;

/**
 * Format a floating point number in a reasonable way. <br>
 * I've experienced problems with the %g conversions that caused array bounds violations. Those versions of the JRE that do
 * <b>not</b> throw such Exceptions use one digit less than specified in the %g conversions. <br >
 * TODO check how to always format numbers corresponding to the Locale used.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
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
     * @param value double; the value to format
     * @return String; the formatted floating point value
     */
    public static String format(final double value)
    {
        return EngineeringFormatter.format(value, DEFAULTSIZE);
    }

}
