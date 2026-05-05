package org.djunits.formatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Area;
import org.djunits.quantity.Energy;
import org.djunits.quantity.Temperature;
import org.djunits.quantity.TemperatureDifference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests all {@link QuantityFormat} settings that have an effect on the formatting of the unit.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class UnitFormatTest
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
     * Test display unit.
     */
    @Test
    public void testDisplayUnit()
    {
        Energy energy = new Energy(1200.345, Energy.Unit.J);

        String s1 = energy.format(Energy.Unit.kJ);
        assertTrue(s1.contains("1.2"));
        assertTrue(s1.endsWith(" kJ"));

        String s2 = energy.format(QuantityFormat.defaults().setDisplayUnit(Energy.Unit.kJ));
        assertTrue(s2.contains("1.2"));
        assertTrue(s2.endsWith(" kJ"));

        String s3 = energy.format(QuantityFormat.defaults().setDisplayUnit("kJ"));
        assertTrue(s3.contains("1.2"));
        assertTrue(s3.endsWith(" kJ"));

        String s4 = energy.format(QuantityFormat.defaults().setDisplayUnit("xyz"));
        assertTrue(s4.contains("1200"));
        assertTrue(s4.endsWith(" J"));

        String s5 = energy.format(QuantityFormat.defaults().setDisplayUnit(Area.Unit.m2));
        assertTrue(s5.contains("1200"));
        assertTrue(s5.endsWith(" J"));
    }

    /**
     * Test display / textual format.
     */
    @Test
    public void testDisplayTextual()
    {
        TemperatureDifference temp = new TemperatureDifference(21.0, Temperature.Unit.degC);

        String s1 = temp.format(QuantityFormat.defaults().setDisplay());
        assertTrue(s1.contains("21"));
        assertTrue(s1.endsWith(" \u00B0C"));

        String s2 = temp.format(QuantityFormat.defaults().setTextual());
        assertTrue(s2.contains("21"));
        assertTrue(s2.endsWith(" degC"));

        String s3 = temp.format(QuantityFormat.defaults().setTextual(false));
        assertTrue(s3.contains("21"));
        assertTrue(s3.endsWith(" \u00B0C"));

        String s4 = temp.format(QuantityFormat.defaults().setTextual(true));
        assertTrue(s4.contains("21"));
        assertTrue(s4.endsWith(" degC"));
    }

    /**
     * Test SI unit format.
     */
    @Test
    public void testSIUnits()
    {
        Energy energy = new Energy(1200.345, Energy.Unit.J);

        String s1 = energy.format(QuantityFormat.defaults().setSiUnits());
        assertTrue(s1.contains("1200.345"));
        assertTrue(s1.endsWith(" kgm2/s2"));

        String s2 = energy.format(QuantityFormat.defaults().setSiUnits().setDivider(true));
        assertTrue(s2.contains("1200.345"));
        assertTrue(s2.endsWith(" kgm2/s2"));

        String s3 = energy.format(QuantityFormat.defaults().setSiUnits().setDivider(false));
        assertTrue(s3.contains("1200.345"));
        assertTrue(s3.endsWith(" kgm2s-2"));

        String s4 = energy.format(QuantityFormat.defaults().setSiUnits().setDotSeparator("."));
        assertTrue(s4.contains("1200.345"));
        assertTrue(s4.endsWith(" kg.m2/s2"));

        String s5 = energy.format(QuantityFormat.defaults().setSiUnits().setPowerPrefix("^"));
        assertTrue(s5.contains("1200.345"));
        assertTrue(s5.endsWith(" kgm^2/s^2"));

        String s6 = energy.format(QuantityFormat.defaults().setSiUnits().setPowerPrefix("<sup>").setPowerPostfix("</sup>"));
        assertTrue(s6.contains("1200.345"));
        assertTrue(s6.endsWith(" kgm<sup>2</sup>/s<sup>2</sup>"));

        String s7 = energy.format(QuantityFormat.defaults().setSiUnits().setPowerPrefix("<sup>").setPowerPostfix("</sup>"));
        assertTrue(s7.contains("1200.345"));
        assertTrue(s7.endsWith(" kgm<sup>2</sup>/s<sup>2</sup>"));
    }
}
