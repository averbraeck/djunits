package org.djunits.value.formatter;

import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * Test the Engineering formatter
 * <p>
 * Copyright (c) 2015-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @version $Revision: 954 $, $LastChangedDate: 2022-01-10 03:42:57 +0100 (Mon, 10 Jan 2022) $, by $Author: averbraeck $,
 *          initial version 11 sep. 2015 <br>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class EngineeringFormatterTest
{

    /**
     * See if we can increase the code coverage to include the private constructor.
     * @throws InstantiationException on reflection error
     * @throws IllegalAccessException on reflection error
     * @throws IllegalArgumentException on reflection error
     * @throws InvocationTargetException on reflection error
     */
    @Test
    public final void constructorTest()
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        Constructor<?>[] cons = EngineeringFormatter.class.getDeclaredConstructors();
        cons[0].setAccessible(true);
        cons[0].newInstance((Object[]) null);
    }

    /**
     * Run ulpTest with both values of the parameter.
     */
    @Test
    public final void ulpTest()
    {
        EngineeringFormatter.setUpperCaseFormat(true);
        Locale.setDefault(Locale.US); // Uses a dot as radix symbol
        doULPTest();
        Locale.setDefault(Locale.GERMAN); // Uses a comma as radix symbol
        doULPTest();
        EngineeringFormatter.setUpperCaseFormat(false);
        Locale.setDefault(Locale.US); // Uses a dot as radix symbol
        doULPTest();
        Locale.setDefault(Locale.GERMAN); // Uses a comma as radix symbol
        doULPTest();
        Locale.setDefault(Locale.US); // RESTORE THE DEFAULT LOCALE
    }

    /**
     * Test cleverly chosen values plus and minus one ULP.
     */
    public final void doULPTest()
    {
        // system.out.println("");
        // system.out.println("Convert a strategically chosen set of values plus or minus one ULP:");
        int width = 10;
        ArrayList<Double> values = new ArrayList<Double>();
        values.add(0d);
        values.add(Double.NaN);
        values.add(Double.NEGATIVE_INFINITY);
        values.add(Double.MIN_VALUE);
        values.add(Double.MAX_VALUE);
        values.add(Double.POSITIVE_INFINITY);
        int[] powers = {-100, -99, 98, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 99,
                100, 101, 102};
        double[] bases = {1.0 / 3, 2.0 / 3, 1};
        for (int power : powers)
        {
            for (double base : bases)
            {
                values.add(base * Math.pow(10, power));
            }
        }
        for (Double d : values)
        {
            // System.out.print(String.format("%30.30s:", d));
            testD(d, width);
            // System.out.print(" ");
            testD(-d, width);
            // system.out.println("");
        }
    }

    /**
     * Run widthTest with both values of the parameter.
     */
    @Test
    public final void widthTest()
    {
        EngineeringFormatter.setUpperCaseFormat(true);
        Locale.setDefault(Locale.US); // Uses a dot as radix symbol
        doWidthTest();
        Locale.setDefault(Locale.GERMAN); // Uses a comma as radix symbol
        doWidthTest();
        EngineeringFormatter.setUpperCaseFormat(false);
        Locale.setDefault(Locale.US); // Uses a dot as radix symbol
        doWidthTest();
        Locale.setDefault(Locale.GERMAN); // Uses a comma as radix symbol
        doWidthTest();
        Locale.setDefault(Locale.US); // RESTORE THE DEFAULT LOCALE
    }

    /**
     * Check that values are formatted at optimal precision when the room is varied.
     */
    public final void doWidthTest()
    {
        // system.out.println("");
        // system.out.println("That that rounding and zero-filling are performed to fill the available room:");
        for (int exp = -10; exp < 24; exp++)
        {
            double d = 5.0 / 9 * Math.pow(10, exp);
            // System.out.print(String.format("%30.30s: ", d));
            for (int sign : new int[] {1, -1})
            {
                for (int digits : new int[] {10, 12, 14, 24})
                {
                    // System.out.print(" |" + convertAndVerify(d * sign, digits) + "|");
                }
            }
            // system.out.println("");
        }

    }

    /**
     * Print result of main value and value plus or minus one ULP.
     * @param d double; the value
     * @param width int; width parameter of the converter
     */
    public static void testD(final double d, final int width)
    {
        String plain = convertAndVerify(d, width);
        String minusULP = Double.isFinite(d) ? convertAndVerify(d - Math.ulp(d), width) : plain;
        String plusULP = Double.isFinite(d) ? convertAndVerify(d + Math.ulp(d), width) : plain;
        // System.out.print(String.format(" %s|%s| |%s| |%s|%s ", minusULP.equals(plain) || 0 == d ? " " : "***", minusULP,
        // plain, plusULP, plusULP.equals(plain) || 0 == d ? " " : "***"));
    }

    /**
     * Call convert and then verify that the result is an accurate representation of the value.
     * @param val double; value to convert
     * @param room int; width of the output of convert
     * @return String; the converted value
     */
    public static String convertAndVerify(final double val, final int room)
    {
        String result = EngineeringFormatter.format(val, room);
        verifyResult(val, result, room);
        return result;
    }

    /**
     * Check that a double value is represented by a string as accurately as possible; i.e. the last digit in the mantissa is
     * rounded correctly.
     * @param value double; the value
     * @param text String; the textual representation of value
     * @param room int; the expected length of the text
     */
    public static void verifyResult(final double value, final String text, final int room)
    {
        if (room != text.length())
        {
            fail("text has wrong length (got " + text.length() + ", expected " + room + ")");
        }
        if (!text.contains("NaN") & !text.contains("Infinity") && !text.equalsIgnoreCase(text.trim()))
        {
            System.err.println("Text starts or ends with white space (" + text + ")");
        }
        // ParseDouble always uses the "." as radix symbol
        int commaPos = text.indexOf(",");
        String parseText = text;
        if (commaPos > 0)
        {
            parseText = text.substring(0, commaPos) + "." + text.substring(commaPos + 1);
        }
        double parsedValue = Double.parseDouble(parseText);
        // Count the number of digits in the mantissa
        int mantissaDigits = 0;
        int digitsBeforeRadix = 0;
        int exponentValue = 0;
        boolean foundNonZero = false;
        for (int pos = 0; pos < text.length(); pos++)
        {
            String symbol = text.substring(pos, pos + 1);
            if (symbol.equalsIgnoreCase("e"))
            {
                exponentValue = Integer.parseInt(text.substring(pos + 1));
                break;
            }
            else if (Character.isDigit(symbol.charAt(0)))
            {
                if (foundNonZero || !symbol.equals("0"))
                {
                    mantissaDigits++;
                    foundNonZero = true;
                }
            }
            else if (symbol.equals(".") || symbol.equals(","))
            {
                digitsBeforeRadix = mantissaDigits;
            }
        }
        double margin = 0.5 / Math.pow(10, mantissaDigits - exponentValue - digitsBeforeRadix);
        if (parsedValue < value - margin)
        {
            System.err.println("Too small: " + text + " -> " + parsedValue + " << " + value + " margin is " + margin
                    + " error is " + (parsedValue - value));
        }
        if (parsedValue > value + margin)
        {
            System.err.println("Too big: " + text + " -> " + parsedValue + " >> " + value + " margin is " + margin
                    + " error is " + (parsedValue - value));
        }
    }

}
