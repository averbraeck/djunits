package org.djunits.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Locale;

import org.djunits.quantity.Energy;
import org.djunits.quantity.LinearObjectDensity;
import org.djunits.quantity.Mass;
import org.djunits.quantity.def.PerMass;
import org.djunits.unit.si.SIPrefixes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests all {@link QuantityFormat} settings that are related to {@link QuantityFormat#setAutoSiPrefix()}.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class SiPrefixFormatTest
{
    /**
     * Setup correct locale for test.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
        QuantityFormat.changeDefaults().setFixedWithSciFallback().setDecimals(3).setDisplay();
    }

    /**
     * Setup correct locale for test.
     */
    @AfterEach
    final void reset()
    {
        QuantityFormat.resetDefaults();
    }

    /**
     * Test {@link QuantityFormat#setAutoSiPrefix()} for a unit quantity.
     */
    @Test
    @SuppressWarnings("checkstyle:needbraces")
    public void testAutoSiPrefixUnit()
    {
        Energy energy = Energy.ONE;
        String unit = "J";
        for (int i = -40; i <= 40; i++)
        {
            int p = i;
            String s = energy.scaleBy(Math.pow(10.0, i)).format(QuantityFormat.instance().setAutoSiPrefix());
            int f3 = (int) (3 * Math.floor(p / 3.0));
            if (!SIPrefixes.FACTORS.containsKey(f3))
            {
                if (p < 0)
                    assertEquals(" 1.000E" + p + " " + unit, s);
                else if (p > 0)
                    assertEquals(" 1.000E+" + p + " " + unit, s);
            }
            else
            {
                String unitStr = SIPrefixes.FACTORS.get(f3).getDefaultDisplayPrefix() + unit;
                if (p == 0)
                    assertEquals("     1.000 " + unit, s);
                else if (p < 0 && Math.abs(p) % 3 == 0)
                    assertEquals("     1.000 " + unitStr, s);
                else if (p < 0 && Math.abs(p) % 3 == 2)
                    assertEquals("    10.000 " + unitStr, s);
                else if (p < 0 && Math.abs(p) % 3 == 1)
                    assertEquals("   100.000 " + unitStr, s);
                else if (p > 0 && p % 3 == 0)
                    assertEquals("     1.000 " + unitStr, s);
                else if (p > 0 && p % 3 == 1)
                    assertEquals("    10.000 " + unitStr, s);
                else
                    assertEquals("   100.000 " + unitStr, s);
            }
        }
    }

    /**
     * Test {@link QuantityFormat#setAutoSiPrefix()} for a unit quantity, and a number that is not a multiple of 1.
     */
    @Test
    @SuppressWarnings("checkstyle:needbraces")
    public void testAutoSiPrefixUnit95()
    {
        Energy energy = Energy.ONE.scaleBy(0.95);
        String unit = "J";
        for (int i = -40; i <= 40; i++)
        {
            int p = i - 1;
            String s = energy.scaleBy(Math.pow(10.0, i)).format(QuantityFormat.instance().setAutoSiPrefix());
            int f3 = (int) (3 * Math.floor(p / 3.0));
            if (!SIPrefixes.FACTORS.containsKey(f3))
            {
                if (p < 0)
                    assertEquals(" 9.500E" + p + " " + unit, s);
                else if (p > 0)
                    assertEquals(" 9.500E+" + p + " " + unit, s);
            }
            else
            {
                String unitStr = SIPrefixes.FACTORS.get(f3).getDefaultDisplayPrefix() + unit;
                if (p == 0)
                    assertEquals("     9.500 " + unit, s);
                else if (p < 0 && Math.abs(p) % 3 == 0)
                    assertEquals("     9.500 " + unitStr, s);
                else if (p < 0 && Math.abs(p) % 3 == 2)
                    assertEquals("    95.000 " + unitStr, s);
                else if (p < 0 && Math.abs(p) % 3 == 1)
                    assertEquals("   950.000 " + unitStr, s);
                else if (p > 0 && p % 3 == 0)
                    assertEquals("     9.500 " + unitStr, s);
                else if (p > 0 && p % 3 == 1)
                    assertEquals("    95.000 " + unitStr, s);
                else
                    assertEquals("   950.000 " + unitStr, s);
            }
        }
    }

    /**
     * Test {@link QuantityFormat#setAutoSiPrefix()} for a unit quantity, and a number that is not a multiple of 1.
     */
    @Test
    @SuppressWarnings("checkstyle:needbraces")
    public void testAutoSiPrefixUnitM123()
    {
        Energy energy = Energy.ONE.scaleBy(-1.23);
        String unit = "J";
        for (int i = -40; i <= 40; i++)
        {
            int p = i;
            String s = energy.scaleBy(Math.pow(10.0, i)).format(QuantityFormat.instance().setAutoSiPrefix());
            int f3 = (int) (3 * Math.floor(p / 3.0));
            if (!SIPrefixes.FACTORS.containsKey(f3))
            {
                if (p < 0)
                    assertEquals("-1.230E" + p + " " + unit, s);
                else if (p > 0)
                    assertEquals("-1.230E+" + p + " " + unit, s);
            }
            else
            {
                String unitStr = SIPrefixes.FACTORS.get(f3).getDefaultDisplayPrefix() + unit;
                if (p == 0)
                    assertEquals("    -1.230 " + unit, s);
                else if (p < 0 && Math.abs(p) % 3 == 0)
                    assertEquals("    -1.230 " + unitStr, s);
                else if (p < 0 && Math.abs(p) % 3 == 2)
                    assertEquals("   -12.300 " + unitStr, s);
                else if (p < 0 && Math.abs(p) % 3 == 1)
                    assertEquals("  -123.000 " + unitStr, s);
                else if (p > 0 && p % 3 == 0)
                    assertEquals("    -1.230 " + unitStr, s);
                else if (p > 0 && p % 3 == 1)
                    assertEquals("   -12.300 " + unitStr, s);
                else
                    assertEquals("  -123.000 " + unitStr, s);
            }
        }
    }

    /**
     * Test {@link QuantityFormat#setAutoSiPrefix()} for a kilo quantity.
     */
    @Test
    @SuppressWarnings("checkstyle:needbraces")
    public void testAutoSiPrefixKilo()
    {
        Mass mass = Mass.ONE; // 1 kg
        String unit = "g";
        for (int i = -40; i <= 40; i++)
        {
            int p = i + 3;
            String s = mass.scaleBy(Math.pow(10.0, i)).format(QuantityFormat.instance().setAutoSiPrefix());
            int f3 = (int) (3 * Math.floor(p / 3.0));
            if (!SIPrefixes.FACTORS.containsKey(f3))
            {
                if (p < 0)
                    assertEquals(" 1.000E" + i + " " + "k" + unit, s);
                else if (p > 0)
                    assertEquals(" 1.000E+" + i + " " + "k" + unit, s);
            }
            else
            {
                String unitStr = SIPrefixes.FACTORS.get(f3).getDefaultDisplayPrefix() + unit;
                if (p == 0)
                    assertEquals("     1.000 " + unit, s);
                else if (p < 0 && Math.abs(p) % 3 == 0)
                    assertEquals("     1.000 " + unitStr, s);
                else if (p < 0 && Math.abs(p) % 3 == 2)
                    assertEquals("    10.000 " + unitStr, s);
                else if (p < 0 && Math.abs(p) % 3 == 1)
                    assertEquals("   100.000 " + unitStr, s);
                else if (p > 0 && p % 3 == 0)
                    assertEquals("     1.000 " + unitStr, s);
                else if (p > 0 && p % 3 == 1)
                    assertEquals("    10.000 " + unitStr, s);
                else
                    assertEquals("   100.000 " + unitStr, s);
            }
        }
    }

    /**
     * Test {@link QuantityFormat#setAutoSiPrefix()} for a "per" quantity.
     */
    @Test
    @SuppressWarnings("checkstyle:needbraces")
    public void testAutoSiPrefixPer()
    {
        // Test two values to show the principle. lod 5 = 10^5/m = 100/mm. lod3 = 0.01/m = 10/km
        LinearObjectDensity lod5 = new LinearObjectDensity(100_000, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity lod3 = new LinearObjectDensity(0.01, LinearObjectDensity.Unit.per_m);
        assertEquals("    10.000 /km", lod3.format(QuantityFormat.instance().setAutoSiPrefix()));
        assertEquals("   100.000 /mm", lod5.format(QuantityFormat.instance().setAutoSiPrefix()));

        // now test 81 exponents
        LinearObjectDensity lod = LinearObjectDensity.ONE; // 1.0 /m
        for (int i = -40; i <= 40; i++)
        {
            int p = -i;
            String s = lod.scaleBy(Math.pow(10.0, i)).format(QuantityFormat.instance().setAutoSiPrefix());
            int f3 = (int) (3 * Math.ceil(p / 3.0));
            if (!SIPrefixes.FACTORS.containsKey(f3))
            {
                // scientific notation
                String exp = (i >= 0 ? "+" + i : Integer.toString(i));
                String expected = String.format("%6.3fE%s /m", 1.0, exp);
                assertEquals(expected, s);
            }
            else
            {
                String unitStr = "/" + SIPrefixes.FACTORS.get(f3).getDefaultDisplayPrefix() + "m";

                String expected;
                if (i == 0)
                    expected = String.format("%10.3f %s", 1.0, "/m");
                else if (i < 0 && (-i) % 3 == 0)
                    expected = String.format("%10.3f %s", 1.0, unitStr);
                else if (i < 0 && (-i) % 3 == 1)
                    expected = String.format("%10.3f %s", 100.0, unitStr);
                else if (i < 0 && (-i) % 3 == 2)
                    expected = String.format("%10.3f %s", 10.0, unitStr);
                else if (i >= 0 && i % 3 == 0)
                    expected = String.format("%10.3f %s", 1.0, unitStr);
                else if (i >= 0 && i % 3 == 1)
                    expected = String.format("%10.3f %s", 10.0, unitStr);
                else if (i >= 0 && i % 3 == 2)
                    expected = String.format("%10.3f %s", 100.0, unitStr);
                else
                    expected = "WRONG";
                assertEquals(expected, s);
            }
        }
    }

    /**
     * Test {@link QuantityFormat#setAutoSiPrefix()} for a "per kilo" quantity.
     */
    @Test
    @SuppressWarnings("checkstyle:needbraces")
    public void testAutoSiPrefixPerKilo()
    {
        // Test two values to show the principle. pm5 = 10^5/kg = 100/g. pm3 = 0.01/kg = 10/Mg
        PerMass pm5 = new PerMass(100_000, PerMass.Unit.per_kg);
        PerMass pm3 = new PerMass(0.01, PerMass.Unit.per_kg);
        assertEquals("    10.000 /Mg", pm3.format(QuantityFormat.instance().setAutoSiPrefix()));
        assertEquals("   100.000 /g", pm5.format(QuantityFormat.instance().setAutoSiPrefix()));

        // now test 81 exponents
        PerMass pm = PerMass.ONE; // 1.0 /kg
        for (int i = -40; i <= 40; i++)
        {
            int p = -i + 3;
            String s = pm.scaleBy(Math.pow(10.0, i)).format(QuantityFormat.instance().setAutoSiPrefix());
            int f3 = (int) (3 * Math.ceil(p / 3.0));
            if (!SIPrefixes.FACTORS.containsKey(f3))
            {
                // scientific notation
                String exp = (i >= 0 ? "+" + i : Integer.toString(i));
                String expected = String.format("%6.3fE%s /kg", 1.0, exp);
                assertEquals(expected, s);
            }
            else
            {
                String unitStr = "/" + SIPrefixes.FACTORS.get(f3).getDefaultDisplayPrefix() + "g";

                String expected;
                if (i == 0)
                    expected = String.format("%10.3f %s", 1.0, "/kg");
                else if (i < 0 && (-i) % 3 == 0)
                    expected = String.format("%10.3f %s", 1.0, unitStr);
                else if (i < 0 && (-i) % 3 == 1)
                    expected = String.format("%10.3f %s", 100.0, unitStr);
                else if (i < 0 && (-i) % 3 == 2)
                    expected = String.format("%10.3f %s", 10.0, unitStr);
                else if (i >= 0 && i % 3 == 0)
                    expected = String.format("%10.3f %s", 1.0, unitStr);
                else if (i >= 0 && i % 3 == 1)
                    expected = String.format("%10.3f %s", 10.0, unitStr);
                else if (i >= 0 && i % 3 == 2)
                    expected = String.format("%10.3f %s", 100.0, unitStr);
                else
                    expected = "WRONG";
                assertEquals(expected, s);
            }
        }
    }

    /**
     * Test exception when min &gt; max in {@link QuantityFormat#setAutoSiPrefix()} method.
     */
    @Test
    public void testAutoSiException()
    {
        assertThrows(IllegalArgumentException.class, () -> Energy.ONE.format(QuantityFormat.instance().setAutoSiPrefix(3, -3)));
        assertThrows(IllegalArgumentException.class, () -> Mass.ONE.format(QuantityFormat.instance().setAutoSiPrefix(3, -3)));
        assertThrows(IllegalArgumentException.class,
                () -> LinearObjectDensity.ONE.format(QuantityFormat.instance().setAutoSiPrefix(3, -3)));
        assertThrows(IllegalArgumentException.class,
                () -> PerMass.ONE.format(QuantityFormat.instance().setAutoSiPrefix(3, -3)));

        assertThrows(IllegalArgumentException.class, () -> Energy.ONE.format(QuantityFormat.instance().setAutoSiPrefix(2, 1)));
        assertThrows(IllegalArgumentException.class, () -> Mass.ONE.format(QuantityFormat.instance().setAutoSiPrefix(2, 1)));
        assertThrows(IllegalArgumentException.class,
                () -> LinearObjectDensity.ONE.format(QuantityFormat.instance().setAutoSiPrefix(2, 1)));
        assertThrows(IllegalArgumentException.class, () -> PerMass.ONE.format(QuantityFormat.instance().setAutoSiPrefix(2, 1)));
    }
}
