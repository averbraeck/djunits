package org.djunits.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Angle;
import org.djunits.quantity.Direction;
import org.djunits.quantity.Energy;
import org.djunits.quantity.LinearObjectDensity;
import org.djunits.quantity.Mass;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.PerMass;
import org.djunits.unit.si.SIUnit;
import org.junit.jupiter.api.Test;

/**
 * Tests all {@link QuantityFormat} settings and their effect on quantity formatting.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class QuantityFormatTest
{
    /**
     * Test prefix separator between number and unit, and postfix after the unit.
     */
    @Test
    public void testPrefixPostfix()
    {
        Energy energy = new Energy(1200.345, Energy.Unit.J);

        String s1 = energy.toString();
        assertTrue(s1.contains("1200"));
        assertTrue(s1.endsWith(" J"));

        String s2 = energy.format(Energy.Unit.kJ);
        assertTrue(s2.contains("1.2"));
        assertTrue(s2.endsWith(" kJ"));

        String s3 = energy.format(QuantityFormat.defaults().setUnitPrefix("  "));
        assertTrue(s3.contains("1200"));
        assertTrue(s3.endsWith("  J"));

        String s4 = energy.format(QuantityFormat.defaults().setUnitPrefix(", unit="));
        assertTrue(s4.contains("1200"));
        assertTrue(s4.endsWith(", unit=J"));

        String s5 = energy.format(QuantityFormat.defaults().setUnitPrefix(" (").setUnitPostfix(")"));
        assertTrue(s5.contains("1200"));
        assertTrue(s5.endsWith(" (J)"));
    }

    /**
     * Test SI scaling (e.g., 1200 J becomes 1.2 kJ).
     */
    @Test
    public void testSiScalingUnit()
    {
        Energy e4 = new Energy(1200.345, Energy.Unit.J);
        Energy e5 = new Energy(12003.45, Energy.Unit.J);
        Energy e6 = new Energy(120034.5, Energy.Unit.J);
        Energy e7 = new Energy(1_200_345.678, Energy.Unit.J);
        Energy e8 = new Energy(12_003_456.78, Energy.Unit.J);
        Energy em2 = new Energy(0.01234567, Energy.Unit.J);
        Energy em3 = new Energy(0.001234567, Energy.Unit.J);
        Energy em4 = new Energy(0.0001234567, Energy.Unit.J);
        Energy em5 = new Energy(0.00001234567, Energy.Unit.J);
        Energy em6 = new Energy(0.000001234567, Energy.Unit.J);
        Energy em7 = new Energy(0.0000001234567, Energy.Unit.J);

        String s4 = e4.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s4.contains("1.2"));
        assertTrue(s4.endsWith(" kJ"));

        String s5 = e5.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s5.contains("12.003"));
        assertTrue(s5.endsWith(" kJ"));

        String s6 = e6.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s6.contains("120.03"));
        assertTrue(s6.endsWith(" kJ"));

        String s7 = e7.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s7.contains("1.2"));
        assertTrue(s7.endsWith(" MJ"));

        String s8 = e8.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s8.contains("12.003"));
        assertTrue(s8.endsWith(" MJ"));

        String m2 = em2.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m2.contains("12.34"));
        assertTrue(m2.endsWith(" mJ"));

        String m3 = em3.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m3.contains("1.2"));
        assertTrue(m3.endsWith(" mJ"));

        String m4 = em4.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(m4.contains("123.45"));
        assertTrue(m4.endsWith(" muJ"));

        String m5 = em5.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(m5.contains("12.34"));
        assertTrue(m5.endsWith(" muJ"));

        String m6 = em6.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(m6.contains("1.23"));
        assertTrue(m6.endsWith(" muJ"));

        String m7 = em7.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m7.contains("123.45"));
        assertTrue(m7.endsWith(" nJ"));

        // test with limits
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 0)).endsWith(" J"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 1)).endsWith(" J"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 2)).endsWith(" J"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 3)).endsWith(" kJ"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(0, 3)).endsWith(" J"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-1, 3)).endsWith(" J"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-2, 3)).endsWith(" J"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-3, 3)).endsWith(" mJ"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-4, 3)).endsWith(" mJ"));
        assertTrue(em7.format(QuantityFormat.defaults().setScaleSiPrefixes(-8, 3)).endsWith(" J"));
        assertTrue(em7.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 3)).endsWith(" nJ"));
    }

    /**
     * Test SI scaling (e.g., 1200 kg becomes 1.2 Mg).
     */
    @Test
    public void testSiScalingKilo()
    {
        Mass e4 = new Mass(1200.345, Mass.Unit.kg);
        Mass e5 = new Mass(12003.45, Mass.Unit.kg);
        Mass e6 = new Mass(120034.5, Mass.Unit.kg);
        Mass e7 = new Mass(1_200_345.678, Mass.Unit.kg);
        Mass e8 = new Mass(12_003_456.78, Mass.Unit.kg);
        Mass em2 = new Mass(0.01234567, Mass.Unit.kg);
        Mass em3 = new Mass(0.001234567, Mass.Unit.kg);
        Mass em4 = new Mass(0.0001234567, Mass.Unit.kg);
        Mass em5 = new Mass(0.00001234567, Mass.Unit.kg);
        Mass em6 = new Mass(0.000001234567, Mass.Unit.kg);
        Mass em7 = new Mass(0.0000001234567, Mass.Unit.kg);

        String s4 = e4.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s4.contains("1.2"));
        assertTrue(s4.endsWith(" Mg"));

        String s5 = e5.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s5.contains("12.003"));
        assertTrue(s5.endsWith(" Mg"));

        String s6 = e6.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s6.contains("120.03"));
        assertTrue(s6.endsWith(" Mg"));

        String s7 = e7.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s7.contains("1.2"));
        assertTrue(s7.endsWith(" Gg"));

        String s8 = e8.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s8.contains("12.003"));
        assertTrue(s8.endsWith(" Gg"));

        String m2 = em2.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m2.contains("12.34"));
        assertTrue(m2.endsWith(" g"));

        String m3 = em3.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m3.contains("1.2"));
        assertTrue(m3.endsWith(" g"));

        String m4 = em4.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m4.contains("123.45"));
        assertTrue(m4.endsWith(" mg"));

        String m5 = em5.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m5.contains("12.34"));
        assertTrue(m5.endsWith(" mg"));

        String m6 = em6.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m6.contains("1.23"));
        assertTrue(m6.endsWith(" mg"));

        String m7 = em7.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(m7.contains("123.45"));
        assertTrue(m7.endsWith(" mug"));

        // test with limits
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 0)).endsWith(" kg"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 1)).endsWith(" kg"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 2)).endsWith(" kg"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 3)).endsWith(" Mg"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(0, 3)).endsWith(" kg"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-1, 3)).endsWith(" kg"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-2, 3)).endsWith(" kg"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-3, 3)).endsWith(" g"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-4, 3)).endsWith(" g"));
        assertTrue(em7.format(QuantityFormat.defaults().setScaleSiPrefixes(-8, 3)).endsWith(" kg"));
        assertTrue(em7.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 3).setTextual()).endsWith(" mug"));
    }

    /**
     * Test SI scaling (e.g., 1200 /m becomes 1.2 /mm).
     */
    @Test
    public void testSiScalingPer()
    {
        LinearObjectDensity e4 = new LinearObjectDensity(1200.345, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity e5 = new LinearObjectDensity(12003.45, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity e6 = new LinearObjectDensity(120034.5, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity e7 = new LinearObjectDensity(1_200_345.678, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity e8 = new LinearObjectDensity(12_003_456.78, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity em0 = new LinearObjectDensity(1.234567, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity em1 = new LinearObjectDensity(0.1234567, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity em2 = new LinearObjectDensity(0.01234567, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity em3 = new LinearObjectDensity(0.001234567, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity em4 = new LinearObjectDensity(0.0001234567, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity em5 = new LinearObjectDensity(0.00001234567, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity em6 = new LinearObjectDensity(0.000001234567, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity em7 = new LinearObjectDensity(0.0000001234567, LinearObjectDensity.Unit.per_m);

        String s4 = e4.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s4.contains("1.2"));
        assertTrue(s4.endsWith(" /mm"));

        String s5 = e5.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(s5.contains("12.003"));
        assertTrue(s5.endsWith(" /mm"));

        String s6 = e6.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(s6.contains("120.03"));
        assertTrue(s6.endsWith(" /mm"));

        String s7 = e7.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(s7.contains("1.2"));
        assertTrue(s7.endsWith(" /mum"));

        String s8 = e8.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(s8.contains("12.003"));
        assertTrue(s8.endsWith(" /mum"));

        String m0 = em0.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m0.contains("1.23"));
        assertTrue(m0.endsWith(" /m"));

        String m1 = em1.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m1.contains("123.45"));
        assertTrue(m1.endsWith(" /km"));

        String m2 = em2.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(m2.contains("12.34"));
        assertTrue(m2.endsWith(" /km"));

        String m3 = em3.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(m3.contains("1.23"));
        assertTrue(m3.endsWith(" /km"));

        String m4 = em4.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m4.contains("123.45"));
        assertTrue(m4.endsWith(" /Mm"));

        String m5 = em5.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m5.contains("12.34"));
        assertTrue(m5.endsWith(" /Mm"));

        String m6 = em6.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m6.contains("1.23"));
        assertTrue(m6.endsWith(" /Mm"));

        String m7 = em7.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(m7.contains("123.45"));
        assertTrue(m7.endsWith(" /Gm"));

        // test with limits
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 0)).endsWith(" /m"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 1)).endsWith(" /m"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 2)).endsWith(" /m"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 3)).endsWith(" /mm"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(0, 3)).endsWith(" /m"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-1, 3)).endsWith(" /m"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-2, 3)).endsWith(" /m"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-3, 3)).endsWith(" /km"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-4, 3)).endsWith(" /km"));
        assertTrue(em7.format(QuantityFormat.defaults().setScaleSiPrefixes(-8, 3)).endsWith(" /m"));
        assertTrue(em7.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 3)).endsWith(" /Gm"));
    }

    /**
     * Test SI scaling (e.g., 1200 /kg becomes 1.2 /g).
     */
    @Test
    public void testSiScalingPerKilo()
    {
        PerMass e4 = new PerMass(1200.345, PerMass.Unit.per_kg);
        PerMass e5 = new PerMass(12003.45, PerMass.Unit.per_kg);
        PerMass e6 = new PerMass(120034.5, PerMass.Unit.per_kg);
        PerMass e7 = new PerMass(1_200_345.678, PerMass.Unit.per_kg);
        PerMass e8 = new PerMass(12_003_456.78, PerMass.Unit.per_kg);
        PerMass em0 = new PerMass(1.234567, PerMass.Unit.per_kg);
        PerMass em1 = new PerMass(0.1234567, PerMass.Unit.per_kg);
        PerMass em2 = new PerMass(0.01234567, PerMass.Unit.per_kg);
        PerMass em3 = new PerMass(0.001234567, PerMass.Unit.per_kg);
        PerMass em4 = new PerMass(0.0001234567, PerMass.Unit.per_kg);
        PerMass em5 = new PerMass(0.00001234567, PerMass.Unit.per_kg);
        PerMass em6 = new PerMass(0.000001234567, PerMass.Unit.per_kg);
        PerMass em7 = new PerMass(0.0000001234567, PerMass.Unit.per_kg);

        String s4 = e4.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s4.contains("1.2"));
        assertTrue(s4.endsWith(" /g"));

        String s5 = e5.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s5.contains("12.003"));
        assertTrue(s5.endsWith(" /g"));

        String s6 = e6.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s6.contains("120.03"));
        assertTrue(s6.endsWith(" /g"));

        String s7 = e7.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s7.contains("1.2"));
        assertTrue(s7.endsWith(" /mg"));

        String s8 = e8.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s8.contains("12.003"));
        assertTrue(s8.endsWith(" /mg"));

        String m0 = em0.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m0.contains("1.23"));
        assertTrue(m0.endsWith(" /kg"));

        String m1 = em1.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m1.contains("123.45"));
        assertTrue(m1.endsWith(" /Mg"));

        String m2 = em2.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(m2.contains("12.34"));
        assertTrue(m2.endsWith(" /Mg"));

        String m3 = em3.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(m3.contains("1.23"));
        assertTrue(m3.endsWith(" /Mg"));

        String m4 = em4.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m4.contains("123.45"));
        assertTrue(m4.endsWith(" /Gg"));

        String m5 = em5.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m5.contains("12.34"));
        assertTrue(m5.endsWith(" /Gg"));

        String m6 = em6.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(m6.contains("1.23"));
        assertTrue(m6.endsWith(" /Gg"));

        String m7 = em7.format(QuantityFormat.defaults().setScaleSiPrefixes().setTextual());
        assertTrue(m7.contains("123.45"));
        assertTrue(m7.endsWith(" /Tg"));

        // test with limits
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 0)).endsWith(" /kg"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 1)).endsWith(" /kg"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 2)).endsWith(" /kg"));
        assertTrue(e4.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 3)).endsWith(" /g"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(0, 3)).endsWith(" /kg"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-1, 3)).endsWith(" /kg"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-2, 3)).endsWith(" /kg"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-3, 3)).endsWith(" /Mg"));
        assertTrue(em3.format(QuantityFormat.defaults().setScaleSiPrefixes(-4, 3)).endsWith(" /Mg"));
        assertTrue(em7.format(QuantityFormat.defaults().setScaleSiPrefixes(-8, 3)).endsWith(" /kg"));
        assertTrue(em7.format(QuantityFormat.defaults().setScaleSiPrefixes(-9, 3)).endsWith(" /Tg"));
    }

    /**
     * Test SIPrefix not applicable to SI units.
     */
    @Test
    public void testSiPrefixToSiUnit()
    {
        SIQuantity siq = new SIQuantity(1200.345, SIUnit.of("m/s3"));

        String s1 = siq.format(QuantityFormat.defaults().setScaleSiPrefixes());
        assertTrue(s1.contains("1200"));
        assertTrue(s1.endsWith(" m/s3"));
    }

    /**
     * Test prefix separator between number and unit, and postfix after the unit for an absolute quantity.
     */
    @Test
    public void testAbsQuantityPrefixPostfix()
    {
        Direction dir = new Direction(1.23456, Angle.Unit.rad, Direction.Reference.EAST);

        String s1 = dir.toString();
        assertTrue(s1.contains("1.23456"));
        assertTrue(s1.endsWith(" rad"));

        String s2 = dir.format(Angle.Unit.deg);
        double deg = 1.23456 * 180.0 / Math.PI;
        assertTrue(s2.contains(String.valueOf(deg).substring(0, 7)));
        assertTrue(s2.endsWith(Angle.Unit.deg.getDisplayAbbreviation()));

        String s3 = dir.format(QuantityFormat.defaults().setUnitPrefix("  "));
        assertTrue(s3.contains("1.23456"));
        assertTrue(s3.endsWith("  rad"));

        String s4 = dir.format(QuantityFormat.defaults().setUnitPrefix(", unit="));
        assertTrue(s4.contains("1.23456"));
        assertTrue(s4.endsWith(", unit=rad"));

        String s5 = dir.format(QuantityFormat.defaults().setUnitPrefix(" (").setUnitPostfix(")"));
        assertTrue(s5.contains("1.23456"));
        assertTrue(s5.endsWith(" (rad)"));

        String s6 = dir.format(QuantityFormat.defaults().setPrintReference().setReferencePrefix(" (").setReferencePostfix(")"));
        assertTrue(s6.contains("1.23456"));
        assertTrue(s6.endsWith(" rad (EAST)"));
    }

    /**
     * Test absolute quantity formatting with prefix separator between number and reference, and postfix after the reference.
     */
    @Test
    public void testAbsReferencePrefixPostfix()
    {
        Direction n = new Direction(30.0, Angle.Unit.deg, Direction.Reference.NORTH);
        Direction e = new Direction(30.0, Angle.Unit.deg, Direction.Reference.EAST);

        String s1a = n.format(QuantityFormat.defaults().setTextual());
        assertTrue(s1a.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s1a.endsWith(" deg"));

        String s1b = n.format(QuantityFormat.defaults().setTextual());
        assertTrue(s1b.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s1b.endsWith(" deg"));

        String s2 = n.format(QuantityFormat.defaults().setNoReference().setTextual());
        assertTrue(s2.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s2.endsWith(" deg"));

        String s3 = n.format(QuantityFormat.defaults().setPrintReference(false).setTextual());
        assertTrue(s3.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s3.endsWith(" deg"));

        String s4n = n.format(QuantityFormat.defaults().setPrintReference().setTextual());
        assertTrue(s4n.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s4n.contains(" deg"));
        assertTrue(s4n.endsWith(" (NORTH)"));

        String s4e = e.format(QuantityFormat.defaults().setPrintReference(true).setTextual());
        assertTrue(s4e.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s4e.contains(" deg"));
        assertTrue(s4e.endsWith(" (EAST)"));

        String s5 = e.format(
                QuantityFormat.defaults().setPrintReference().setReferencePrefix(" [").setReferencePostfix("]").setTextual());
        assertTrue(s5.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s5.contains(" deg"));
        assertTrue(s5.endsWith(" [EAST]"));
    }

    /**
     * Test the setting and resetting of defaults.
     */
    @Test
    public void testDefaults()
    {
        Energy e1 = new Energy(1200.345, Energy.Unit.J);
        Energy e2 = new Energy(1_200_345.678, Energy.Unit.J);

        try
        {
            // VARIABLE_LENGTH
            String s1 = e1.toString();
            assertEquals("1200.345 J", s1);
            String s2 = e2.toString();
            assertEquals("1200345.678 J", s2);

            QuantityFormat.changeDefaults().setFixedWithSciFallback().setDecimals(3).setWidth(10).setGroupingSeparator(true);
            s1 = e1.toString();
            assertEquals(" 1,200.345 J", s1);
            s2 = e2.toString();
            assertEquals(" 1.200E+06 J", s2);

            QuantityFormat.resetDefaults();
            s1 = e1.toString();
            assertEquals("1200.345 J", s1);
            s2 = e2.toString();
            assertEquals("1200345.678 J", s2);
        }
        finally
        {
            QuantityFormat.resetDefaults();
        }
    }
}
