package org.djunits.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Length;
import org.djunits.quantity.Speed;
import org.junit.jupiter.api.Test;

/**
 * Tests all {@link QuantityFormat} settings that are related to formating the number.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class NumberFormatTest
{
    /**
     * Test variable-length formatting.
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testVariableLength()
    {
        Length l1 = new Length(1234.5678, Length.Unit.m);
        String s1 = l1.format(QuantityFormat.defaults().setVariableLength().setGroupingSeparator(true));
        assertTrue(s1.contains("1,234.5678"));

        Length l2 = new Length(12_345_678.90123, Length.Unit.km);
        String s2 = l2.format(QuantityFormat.defaults().setVariableLength().setGroupingSeparator(true));
        assertTrue(s2.contains("12,345,678.90123"));

        Length length = new Length(1234.567890123456789, Length.Unit.m);
        for (int i = 0; i < 10; i++)
        {
            String s = length.format(QuantityFormat.defaults().setVariableLength().setGroupingSeparator(false).setUpperE(true));
            if (i <= 6)
                assertFalse(s.contains("E"), String.format("Not false for i=%d, s=%s", i, s));
            else
                assertTrue(s.contains("E" + (i + 3)), String.format("Not true for i=%d, s=%s", i, s));
            length = length.scaleBy(10.0);
        }

        length = new Length(1.234567890123456789, Length.Unit.m);
        for (int i = 0; i < 10; i++)
        {
            String s = length.format(QuantityFormat.defaults().setVariableLength().setGroupingSeparator(false).setUpperE(true));
            if (i <= 7)
                assertFalse(s.contains("E"), String.format("Not false for i=%d, s=%s", i, s));
            else
                assertTrue(s.contains("E-"), String.format("Not true for i=%d, s=%s", i, s));
            length = length.divideBy(10.0);
        }
    }

    /**
     * Test fixed float formatting.
     */
    @Test
    public void testFixedFloat()
    {
        Length l = new Length(1.23456, Length.Unit.m);
        String s = l.format(QuantityFormat.defaults().setFixedFloat().setDecimals(2).setWidth(8));
        assertEquals("    1.23 m", s);
    }

    /**
     * Test scientific formatting.
     */
    @Test
    public void testScientificAlways()
    {
        Length l = new Length(1234.0, Length.Unit.m);
        String s = l.format(QuantityFormat.defaults().setScientific().setDecimals(2));
        assertTrue(s.contains("E"));
    }

    /**
     * Test engineering formatting.
     */
    @Test
    public void testEngineeringAlways()
    {
        Length l = new Length(1234.0, Length.Unit.mi);
        String s1 = l.format(QuantityFormat.defaults().setEngineering().setDecimals(1).setUpperE(false));
        assertTrue(s1.contains("e+3"));
        String s2 = l.format(QuantityFormat.defaults().setEngineering().setDecimals(1).setUpperE(true));
        assertTrue(s2.contains("E+3"));

        Length l5 = new Length(12345.6789, Length.Unit.mi);
        String s5 = l5.format(QuantityFormat.defaults().setEngineering().setDecimals(2).setUpperE(true));
        assertTrue(s5.contains("E+3"));
        assertTrue(s5.contains("12.35"));

        Length l6 = new Length(123456.7891, Length.Unit.mi);
        String s6 = l6.format(QuantityFormat.defaults().setEngineering().setDecimals(2).setUpperE(true));
        assertTrue(s6.contains("E+3"));
        assertTrue(s6.contains("123.46"));

        Length l7 = new Length(1234567.8912, Length.Unit.mi);
        String s7 = l7.format(QuantityFormat.defaults().setEngineering().setDecimals(2).setUpperE(true));
        assertTrue(s7.contains("E+6"));
        assertTrue(s7.contains("1.23"));
    }

    /**
     * Test grouping separator.
     */
    @Test
    public void testGroupingSeparator()
    {
        Length l = new Length(12345.0, Length.Unit.m);
        String s1 = l.format(QuantityFormat.defaults().setGroupingSeparator(false));
        assertFalse(s1.contains(","));
        String s2 = l.format(QuantityFormat.defaults().setGroupingSeparator(true));
        assertTrue(s2.contains(","));
    }

    /**
     * Test uppercase and lowercase scientific exponent.
     */
    @Test
    public void testUpperLowerExponent()
    {
        Length l = new Length(1_000_000.0, Length.Unit.in);

        String s1S = l.format(QuantityFormat.defaults().setScientific().setUpperE(false).setDecimals(1));
        assertTrue(s1S.contains("e"));

        String s2S = l.format(QuantityFormat.defaults().setScientific().setUpperE(true).setDecimals(1));
        assertTrue(s2S.contains("E"));

        String s1E = l.format(QuantityFormat.defaults().setEngineering().setUpperE(false).setDecimals(1));
        assertTrue(s1E.contains("e"));

        String s2E = l.format(QuantityFormat.defaults().setEngineering().setUpperE(true).setDecimals(1));
        assertTrue(s2E.contains("E"));
    }

    /**
     * Test fixed formatting with scientific fallback.
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testFixedScientificFallback()
    {
        Length length = new Length(1234.567890123456789, Length.Unit.m);
        for (int i = 0; i < 10; i++)
        {
            String s = length.format(QuantityFormat.defaults().setFixedWithSciFallback().setGroupingSeparator(false)
                    .setWidth(10).setDecimals(3).setUpperE(true));
            if (i < 3)
                assertFalse(s.contains("E"), String.format("Not false for i=%d, s=%s", i, s));
            else
            {
                if (i < 7)
                    assertTrue(s.contains("E+0" + (i + 3)), String.format("Not true for i=%d, s=%s", i, s));
                else
                    assertTrue(s.contains("E+" + (i + 3)), String.format("Not true for i=%d, s=%s", i, s));
            }
            length = length.scaleBy(10.0);
        }
    }

    /**
     * Test fixed formatting with engineering fallback.
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testFixedEngineeringFallback()
    {
        Length length = new Length(1234.567890123456789, Length.Unit.m);
        for (int i = 0; i < 10; i++)
        {
            String s = length.format(QuantityFormat.defaults().setFixedWithEngFallback().setGroupingSeparator(false)
                    .setWidth(10).setDecimals(3).setUpperE(true));
            if (i < 3)
                assertFalse(s.contains("E"), String.format("Not false for i=%d, s=%s", i, s));
            else
            {
                if (i < 6)
                    assertTrue(s.contains("E+6"), String.format("Not true for i=%d, s=%s", i, s));
                else if (i < 9)
                    assertTrue(s.contains("E+9"), String.format("Not true for i=%d, s=%s", i, s));
                else
                    assertTrue(s.contains("E+12"), String.format("Not true for i=%d, s=%s", i, s));
            }
            length = length.scaleBy(10.0);
        }
    }

    /**
     * Test format string.
     */
    @Test
    public void testFormatString()
    {
        Speed speed = new Speed(90.0, Speed.Unit.km_h);
        String s = speed.format(QuantityFormat.defaults().setFormatString("%6.2f"));
        assertEquals(" 90.00 km/h", s);
    }

}
