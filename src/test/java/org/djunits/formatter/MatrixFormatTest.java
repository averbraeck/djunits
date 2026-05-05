package org.djunits.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Angle;
import org.djunits.quantity.Direction;
import org.djunits.quantity.Energy;
import org.djunits.quantity.Temperature;
import org.djunits.quantity.TemperatureDifference;
import org.djunits.vecmat.d1.AbsMatrix1x1;
import org.djunits.vecmat.d1.Matrix1x1;
import org.djunits.vecmat.d3.AbsMatrix3x3;
import org.djunits.vecmat.d3.Matrix3x3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests all {@link MatrixFormat} settings and their effect on matrix formatting.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class MatrixFormatTest
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
     * Test format settings for the matrix.
     */
    @Test
    public void testMatrixFormat()
    {
        Matrix3x3<Energy> ev =
                Matrix3x3.of(new double[][] {{1200.345, 123.456, 5432.104}, {1, 2, 3}, {4, 5, 6}}, Energy.Unit.J);

        String s1 = ev.toString();
        assertTrue(s1.contains("1200.345"));
        assertTrue(s1.contains("123.456"));
        assertTrue(s1.contains("5432.10"));
        assertFalse(s1.contains(","));
        assertTrue(s1.startsWith("|"));
        assertTrue(s1.endsWith("| J"));

        String s2 = ev.format(MatrixFormat.defaults());
        assertTrue(s2.contains("1200.345"));
        assertTrue(s2.contains("123.456"));
        assertTrue(s2.contains("5432.10"));
        assertFalse(s2.contains(","));
        assertTrue(s2.startsWith("|"));
        assertTrue(s2.endsWith("| J"));

        String s3 = ev
                .format(MatrixFormat.defaults().setMatrixPrefix("Mat ").setColSeparatorSymbol(" , ").setFirstRowStartSymbol("/")
                        .setFirstRowEndSymbol(" \\\n").setMiddleRowStartSymbol("    |").setMiddleRowEndSymbol(" | \n")
                        .setLastRowStartSymbol("    \\").setLastRowEndSymbol(" /").setMatrixPostfix(" unit ="));
        String[] s3Arr = s3.split("\\R");
        assertTrue(s3Arr[0].contains("1200.345"));
        assertTrue(s3Arr[0].contains("123.456"));
        assertTrue(s3Arr[0].contains("5432.10"));
        assertTrue(s3Arr[0].contains(","));
        assertTrue(s3Arr[0].startsWith("Mat /"));
        assertTrue(s3Arr[0].endsWith("\\"));
        assertTrue(s3Arr[1].startsWith("    |"));
        assertTrue(s3Arr[1].endsWith("| "));
        assertTrue(s3Arr[2].startsWith("    \\"));
        assertTrue(s3Arr[2].endsWith("/ unit = J"));

        String s4 = ev.format(MatrixFormat.defaults().setColSeparatorSymbol(" ").setDisplayUnit("kJ"));
        assertTrue(s4.contains("1.200"));
        assertTrue(s4.contains("0.123"));
        assertTrue(s4.contains("5.432"));
        assertFalse(s4.contains(","));
        assertTrue(s4.endsWith("| kJ"));
    }

    /**
     * Test prefix separator between number and unit, and postfix after the unit for an absolute matrix.
     */
    @Test
    public void testAbsMatrixPrefixPostfix()
    {
        AbsMatrix3x3<Direction, Angle> dir = AbsMatrix3x3.of(new double[][] {{1.2345, 0.23456, 0.55404}, {1, 2, 3}, {4, 5, 6}},
                Angle.Unit.rad, Direction.Reference.EAST);

        String s1 = dir.toString();
        assertTrue(s1.contains("1.235"));
        assertTrue(s1.endsWith(" rad"));

        String s2 = dir.format(Angle.Unit.deg);
        double deg = 1.2345 * 180.0 / Math.PI;
        assertTrue(s2.contains(String.valueOf(deg).substring(0, 5)));
        assertTrue(s2.endsWith(Angle.Unit.deg.getDisplayAbbreviation()));

        String s3 = dir.format(MatrixFormat.defaults().setUnitPrefix("  "));
        assertTrue(s3.contains("1.235"));
        assertTrue(s3.endsWith("  rad"));

        String s4 = dir.format(MatrixFormat.defaults().setUnitPrefix(", unit="));
        assertTrue(s4.contains("1.235"));
        assertTrue(s4.endsWith(", unit=rad"));

        String s5 = dir.format(MatrixFormat.defaults().setUnitPrefix(" (").setUnitPostfix(")"));
        assertTrue(s5.contains("1.235"));
        assertTrue(s5.endsWith(" (rad)"));

        String s6 = dir.format(MatrixFormat.defaults().setPrintReference().setReferencePrefix(" (").setReferencePostfix(")"));
        assertTrue(s6.contains("1.235"));
        assertTrue(s6.endsWith(" rad (EAST)"));
    }

    /**
     * Test absolute matrix formatting with prefix separator between number and reference, and postfix after the reference.
     */
    @Test
    public void testAbsReferencePrefixPostfix()
    {
        AbsMatrix3x3<Direction, Angle> n = AbsMatrix3x3.of(new double[][] {{30, 40, 50}, {10, 11, 12}, {40, 50, 60}},
                Angle.Unit.deg, Direction.Reference.NORTH);
        AbsMatrix3x3<Direction, Angle> e = AbsMatrix3x3.of(new double[][] {{30, 40, 50}, {10, 11, 12}, {40, 50, 60}},
                Angle.Unit.deg, Direction.Reference.EAST);

        String s1a = n.format(MatrixFormat.defaults().setTextual());
        assertTrue(s1a.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s1a.endsWith(" deg"));

        String s1b = n.format(MatrixFormat.defaults().setTextual());
        assertTrue(s1b.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s1b.endsWith(" deg"));

        String s2 = n.format(MatrixFormat.defaults().setNoReference().setTextual());
        assertTrue(s2.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s2.endsWith(" deg"));

        String s3 = n.format(MatrixFormat.defaults().setPrintReference(false).setTextual());
        assertTrue(s3.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s3.endsWith(" deg"));

        String s4n = n.format(MatrixFormat.defaults().setPrintReference().setTextual());
        assertTrue(s4n.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s4n.contains(" deg"));
        assertTrue(s4n.endsWith(" (NORTH)"));

        String s4e = e.format(MatrixFormat.defaults().setPrintReference(true).setTextual());
        assertTrue(s4e.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s4e.contains(" deg"));
        assertTrue(s4e.endsWith(" (EAST)"));

        String s5 = e.format(
                MatrixFormat.defaults().setPrintReference().setReferencePrefix(" [").setReferencePostfix("]").setTextual());
        assertTrue(s5.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s5.contains(" deg"));
        assertTrue(s5.endsWith(" [EAST]"));
    }

    /**
     * Test formatting as SI.
     */
    @Test
    public void testSiFormatting()
    {
        Matrix1x1<Energy> energy = Matrix1x1.of(1.2345, Energy.Unit.kJ);
        AbsMatrix1x1<Temperature, TemperatureDifference> temp =
                AbsMatrix1x1.of(20.0, Temperature.Unit.degC, Temperature.Reference.CELSIUS);

        String s1 = energy.format(MatrixFormat.defaults().setSiUnits().setFirstRowEndSymbol(" |"));
        assertEquals("|   1234.500 | kgm2/s2", s1);

        String s2C = temp.format(MatrixFormat.defaults().setSiUnits().setFirstRowEndSymbol(" |"));
        assertEquals("|     20.000 | K", s2C);

        String s3C = temp.format(MatrixFormat.defaults().setSiUnits().setFirstRowEndSymbol(" |").setPrintReference()
                .setReferencePrefix(" (").setReferencePostfix(")"));
        assertEquals("|     20.000 | K (CELSIUS)", s3C);
    }

    /**
     * Test the setting and resetting of defaults.
     */
    @Test
    public void testDefaults()
    {
        Matrix3x3<Energy> ev =
                Matrix3x3.of(new double[][] {{1200.345, 123.456, 5432.104}, {1, 2, 3}, {4, 5, 6}}, Energy.Unit.J);

        try
        {
            // VARIABLE_LENGTH
            String s1 = ev.toString();
            assertTrue(s1.contains("1200.345"));
            assertTrue(s1.contains("123.456"));
            assertTrue(s1.contains("5432.10"));
            assertFalse(s1.contains(","));
            assertTrue(s1.startsWith("|"));
            assertTrue(s1.endsWith("| J"));

            MatrixFormat.changeDefaults().setFixedWithSciFallback().setDecimals(1).setWidth(10).setGroupingSeparator(true)
                    .setFirstRowStartSymbol("/ ").setFirstRowEndSymbol(" \\\n").setLastRowStartSymbol("\\ ")
                    .setLastRowEndSymbol(" /");
            s1 = ev.toString();
            assertTrue(s1.contains("1,200.3 "));
            assertTrue(s1.contains("123.5 "));
            assertTrue(s1.contains("5,432.1 "));
            assertTrue(s1.contains("\\"));
            assertTrue(s1.startsWith("/"));
            assertTrue(s1.endsWith("/ J"));

            MatrixFormat.resetDefaults();
            s1 = ev.toString();
            assertTrue(s1.contains("1200.345"));
            assertTrue(s1.contains("123.456"));
            assertTrue(s1.contains("5432.10"));
            assertFalse(s1.contains(","));
            assertTrue(s1.startsWith("|"));
            assertTrue(s1.endsWith("| J"));
        }
        finally
        {
            MatrixFormat.resetDefaults();
        }
    }

}
