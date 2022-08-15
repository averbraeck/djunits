package org.djunits.value;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.djunits.value.formatter.EngineeringFormatter;
import org.djunits.value.formatter.Format;
import org.djunits.value.formatter.Formatter;
import org.junit.Test;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class FormatTest
{
    /**
     * Test that size, precision and accuracy are OK.
     */
    @Test
    public final void format()
    {
        Locale.setDefault(Locale.US);
        double[] baseValues = { 1, (float) (1 / 3d) };
        for (int width = 8; width <= 20; width++)
        {
            for (int precision = 0; precision <= 10; precision++)
            {
                for (int power = -20; power <= 20; power++)
                {
                    if (width - precision <= 6)
                    {
                        continue; // can't be done
                    }
                    for (double baseValue : baseValues)
                    {
                        float value = (float) (baseValue * Math.pow(10, power));
                        // System.out.print("Trying " + width + ", " + precision + ", " + value);
                        String result = Formatter.format(value, width, precision);
                        // System.out.println(": \"" + result + "\"");
                        assertEquals("Length of result should equal specified width", width, result.length());
                        double reverseValue = Double.parseDouble(result);
                        int expectedPrecision = precision - 2;
                        if (expectedPrecision > 6)
                        {
                            expectedPrecision = 6;
                        }
                        double tolerance = Math.abs(value / Math.pow(10, expectedPrecision));
                        assertEquals("Parsed result should equal original value within tolerance " + tolerance, value,
                                reverseValue, tolerance);
                    }
                    for (double baseValue : baseValues)
                    {
                        double value = baseValue * Math.pow(10, power);
                        // System.out.print("Trying " + width + ", " + precision + ", " + value);
                        String result = Formatter.format(value, width, precision);
                        // System.out.println(": \"" + result + "\"");
                        assertEquals("Length of result should equal specified width", width, result.length());
                        double reverseValue = Double.parseDouble(result);
                        int expectedPrecision = precision - 2;
                        if (expectedPrecision > 15)
                        {
                            expectedPrecision = 15;
                        }
                        double tolerance = Math.abs(value / Math.pow(10, expectedPrecision));
                        assertEquals("Parsed result should equal original value within tolerance " + tolerance, value,
                                reverseValue, tolerance);
                    }
                }
            }
        }
        // Directly call Format en check that the result has the expected length
        String result = EngineeringFormatter.format(123.456);
        assertEquals("Width of result of format should be " + Format.DEFAULTSIZE, Format.DEFAULTSIZE, result.length());
        for (int len = -2; len <= 10; len++)
        {
            // Check that widths less than the minimum are treated as the minimum
            assertEquals("With should be at least 10 characters", 10, EngineeringFormatter.format(12.3, len).length());
        }
        String input = "78757587585858.55873468764";
        assertEquals("String with no E or e is returned unaltered", input, EngineeringFormatter.convertToEngineering(input));
        input = "123e4";
        assertEquals("String with no dot or comma is returned unaltered", input,
                EngineeringFormatter.convertToEngineering(input));
    }
}
