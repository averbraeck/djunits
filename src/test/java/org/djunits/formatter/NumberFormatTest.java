package org.djunits.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Length;
import org.djunits.quantity.Speed;
import org.junit.jupiter.api.BeforeEach;
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
     * Setup correct locale for test.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test variable-length formatting.
     */
    @SuppressWarnings("checkstyle:needbraces")
    @Test
    public void testVariableLength()
    {
        Length l1 = new Length(1234.5678, Length.Unit.m);
        String s1 = l1.format(QuantityFormat.instance().setVariableLength().setGroupingSeparator(true));
        assertTrue(s1.contains("1,234.5678"));

        Length l2 = new Length(12_345_678.90123, Length.Unit.km);
        String s2 = l2.format(QuantityFormat.instance().setVariableLength().setGroupingSeparator(true).setMaxSigDigits(20));
        assertTrue(s2.contains("12,345,678.90123"));

        // maxSigDigits = 10. Length has already 4 that need to be printed.
        Length length = new Length(1234.567890123456789, Length.Unit.m);
        for (int i = 0; i < 10; i++)
        {
            String s = length.format(QuantityFormat.instance().setVariableLength().setGroupingSeparator(false).setUpperE(true));
            String t =
                    length.format(QuantityFormat.instance().setVariableLength().setGroupingSeparator(false).setUpperE(false));
            if (i <= 6)
                assertFalse(s.contains("E"), String.format("Not false for i=%d, s=%s", i, s));
            else
            {
                assertTrue(s.contains("E+" + (i + 3)), String.format("Not true for i=%d, s=%s", i, s));
                assertTrue(t.contains("e+" + (i + 3)), String.format("Not true for i=%d, s=%s", i, s));
            }
            length = length.scaleBy(10.0);
        }

        // sciFallback = -3. We start at 1 digit.
        length = new Length(1.234567890123456789, Length.Unit.m);
        for (int i = 0; i < 10; i++)
        {
            String s = length.format(QuantityFormat.instance().setVariableLength().setGroupingSeparator(false).setUpperE(true));
            String t =
                    length.format(QuantityFormat.instance().setVariableLength().setGroupingSeparator(false).setUpperE(false));
            if (i <= 3)
                assertFalse(s.contains("E"), String.format("Not false for i=%d, s=%s", i, s));
            else
            {
                assertTrue(s.contains("E-"), String.format("Not true for i=%d, s=%s", i, s));
                assertTrue(t.contains("e-"), String.format("Not true for i=%d, s=%s", i, s));
            }
            length = length.divideBy(10.0);
        }

        // Test 0.0 and -0.0
        Length lp0 = Length.ofSi(0.0);
        assertEquals("0 m", lp0.format(QuantityFormat.instance().setVariableLength()));
        Length lm0 = lp0.negate();
        assertEquals("0 m", lm0.format(QuantityFormat.instance().setVariableLength()));

        // Test NaN
        Length lnan = Length.ofSi(Double.NaN);
        assertEquals("NaN m", lnan.format(QuantityFormat.instance().setVariableLength()));

        // test inf and -inf
        Length linf = Length.ofSi(Double.POSITIVE_INFINITY);
        assertEquals("Inf m", linf.format(QuantityFormat.instance().setVariableLength()));
        Length minf = Length.ofSi(Double.NEGATIVE_INFINITY);
        assertEquals("-Inf m", minf.format(QuantityFormat.instance().setVariableLength()));

        // test underflow with sciThreashold
        Length l03 = Length.ofSi(0.001234321);
        assertEquals("1.234E-03 m", l03.format(QuantityFormat.instance().setMaxSigDigits(4).setSciThreshold(-1)));
        assertEquals("1.234E-03 m", l03.format(QuantityFormat.instance().setMaxSigDigits(4).setSciThreshold(-2)));
        assertEquals("0.001234 m", l03.format(QuantityFormat.instance().setMaxSigDigits(4).setSciThreshold(-3)));
        assertEquals("0.001234 m", l03.format(QuantityFormat.instance().setMaxSigDigits(4).setSciThreshold(-4)));
    }

    /**
     * Test fixed float formatting.
     */
    @Test
    public void testFixedFloat()
    {
        Length l = new Length(1.23456, Length.Unit.m);
        String s = l.format(QuantityFormat.instance().setFixedFloat().setDecimals(2).setWidth(8));
        assertEquals("    1.23 m", s);
    }

    /**
     * Test scientific formatting.
     */
    @Test
    public void testScientificAlways()
    {
        Length l = new Length(1234.0, Length.Unit.m);
        String s = l.format(QuantityFormat.instance().setScientific().setDecimals(2));
        assertTrue(s.contains("E"));
    }

    /**
     * Test engineering formatting.
     */
    @Test
    public void testEngineeringAlways()
    {
        Length l = new Length(1234.0, Length.Unit.mi);
        String s1 = l.format(QuantityFormat.instance().setEngineering().setWidth(9).setDecimals(1).setUpperE(false));
        assertEquals("  1.2e+03 mi", s1);
        String s2 = l.format(QuantityFormat.instance().setEngineering().setWidth(9).setDecimals(1).setUpperE(true));
        assertEquals("  1.2E+03 mi", s2);

        Length l5 = new Length(12345.6789, Length.Unit.mi);
        String s5 = l5.format(QuantityFormat.instance().setEngineering().setWidth(12).setDecimals(2).setUpperE(true));
        assertEquals("   12.35E+03 mi", s5);

        Length l6 = new Length(123456.7891, Length.Unit.mi);
        String s6 = l6.format(QuantityFormat.instance().setEngineering().setWidth(12).setDecimals(2).setUpperE(true));
        assertEquals("  123.46E+03 mi", s6);

        Length l7 = new Length(1234567.8912, Length.Unit.mi);
        String s7 = l7.format(QuantityFormat.instance().setEngineering().setWidth(12).setDecimals(3).setUpperE(true));
        assertEquals("   1.235E+06 mi", s7);
    }

    /**
     * Test grouping separator.
     */
    @Test
    public void testGroupingSeparator()
    {
        Length l = new Length(12345.0, Length.Unit.m);
        String s1 = l.format(QuantityFormat.instance().setGroupingSeparator(false));
        assertFalse(s1.contains(","));
        String s2 = l.format(QuantityFormat.instance().setGroupingSeparator(true));
        assertTrue(s2.contains(","));
    }

    /**
     * Test uppercase and lowercase scientific exponent.
     */
    @Test
    public void testUpperLowerExponent()
    {
        Length l = new Length(1_000_000.0, Length.Unit.in);

        String s1S = l.format(QuantityFormat.instance().setScientific().setUpperE(false).setDecimals(1));
        assertTrue(s1S.contains("e"));

        String s2S = l.format(QuantityFormat.instance().setScientific().setUpperE(true).setDecimals(1));
        assertTrue(s2S.contains("E"));

        String s1E = l.format(QuantityFormat.instance().setEngineering().setUpperE(false).setDecimals(1));
        assertTrue(s1E.contains("e"));

        String s2E = l.format(QuantityFormat.instance().setEngineering().setUpperE(true).setDecimals(1));
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
            String s = length.format(QuantityFormat.instance().setFixedWithSciFallback().setGroupingSeparator(false)
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
            String s = length.format(QuantityFormat.instance().setFixedWithEngFallback().setGroupingSeparator(false)
                    .setWidth(10).setDecimals(3).setUpperE(true));
            if (i < 3)
                assertFalse(s.contains("E"), String.format("Not false for i=%d, s=%s", i, s));
            else
            {
                if (i < 6)
                    assertTrue(s.contains("E+06"), String.format("Not true for i=%d, s=%s", i, s));
                else if (i < 9)
                    assertTrue(s.contains("E+09"), String.format("Not true for i=%d, s=%s", i, s));
                else
                    assertTrue(s.contains("E+12"), String.format("Not true for i=%d, s=%s", i, s));
            }
            length = length.scaleBy(10.0);
        }

        length = new Length(1.234567890123456789, Length.Unit.m);
        for (int i = 0; i < 10; i++)
        {
            String s = length.format(QuantityFormat.instance().setFixedWithEngFallback().setGroupingSeparator(false)
                    .setWidth(10).setDecimals(3).setUpperE(true));
            if (i <= 3)
                assertFalse(s.contains("E"), String.format("Not false for i=%d, s=%s", i, s));
            else
            {
                if (i < 6)
                    assertTrue(s.contains("E-03"), String.format("Not true for i=%d, s=%s", i, s));
                else if (i < 9)
                    assertTrue(s.contains("E-06"), String.format("Not true for i=%d, s=%s", i, s));
                else
                    assertTrue(s.contains("E-09"), String.format("Not true for i=%d, s=%s", i, s));
            }
            length = length.divideBy(10.0);
        }
        
        // value = 0.0
        Length l0 = Length.ofSi(0.0);
        assertEquals("       0.000 m", l0.format(QuantityFormat.instance().setFixedWithEngFallback()));
    }

    /**
     * Test format string.
     */
    @Test
    public void testFormatString()
    {
        Speed speed = new Speed(90.0, Speed.Unit.km_h);
        String s = speed.format(QuantityFormat.instance().setFormatString("%6.2f"));
        assertEquals(" 90.00 km/h", s);
    }

}
