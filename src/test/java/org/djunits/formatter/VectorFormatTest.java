package org.djunits.formatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Angle;
import org.djunits.quantity.Direction;
import org.djunits.quantity.Energy;
import org.djunits.quantity.Temperature;
import org.djunits.quantity.TemperatureDifference;
import org.djunits.vecmat.d2.AbsVector2;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.AbsVector3;
import org.djunits.vecmat.d3.Vector3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests all {@link VectorFormat} settings and their effect on vector formatting.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class VectorFormatTest
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
     * Test format settings for the row vector.
     */
    @Test
    public void testRowVectorFormat()
    {
        Vector3.Row<Energy> ev = Vector3.Row.of(1200.345, 123.456, 5432.104, Energy.Unit.J);

        String s1 = ev.toString();
        assertTrue(s1.contains("1200.345"));
        assertTrue(s1.contains("123.456"));
        assertTrue(s1.contains("5432.10"));
        assertTrue(s1.contains(","));
        assertTrue(s1.startsWith("["));
        assertTrue(s1.endsWith("] J"));

        String s2 = ev.format(VectorFormat.Row.defaults());
        assertTrue(s2.contains("1200.345"));
        assertTrue(s2.contains("123.456"));
        assertTrue(s2.contains("5432.10"));
        assertTrue(s2.contains(","));
        assertTrue(s2.startsWith("["));
        assertTrue(s2.endsWith("] J"));

        String s3 = ev.format(
                VectorFormat.Row.defaults().setVectorPrefix("R").setSeparatorSymbol(" ").setStartSymbol("(").setEndSymbol(")"));
        assertTrue(s3.contains("1200.345"));
        assertTrue(s3.contains("123.456"));
        assertTrue(s3.contains("5432.10"));
        assertFalse(s3.contains(","));
        assertTrue(s3.startsWith("R("));
        assertTrue(s3.endsWith(") J"));

        String s4 = ev.format(VectorFormat.Row.defaults().setVectorPrefix("R").setSeparatorSymbol(" ").setStartSymbol("(")
                .setEndSymbol(")").setDisplayUnit("kJ"));
        assertTrue(s4.contains("1.200"));
        assertTrue(s4.contains("0.123"));
        assertTrue(s4.contains("5.432"));
        assertFalse(s4.contains(","));
        assertTrue(s4.startsWith("R("));
        assertTrue(s4.endsWith(") kJ"));
    }

    /**
     * Test format settings for the column vector.
     */
    @Test
    public void testColVectorFormat()
    {
        Vector3.Col<Energy> ev = Vector3.Col.of(1200.345, 123.456, 5432.104, Energy.Unit.J);

        String s1 = ev.toString();
        assertTrue(s1.contains("1200.345"));
        assertTrue(s1.contains("123.456"));
        assertTrue(s1.contains("5432.10"));
        assertFalse(s1.contains(","));
        assertTrue(s1.startsWith("["));
        assertTrue(s1.endsWith("] J"));
        assertTrue(s1.contains("\n"));

        String s2 = ev.format(VectorFormat.Col.defaults());
        assertTrue(s2.contains("1200.345"));
        assertTrue(s2.contains("123.456"));
        assertTrue(s2.contains("5432.10"));
        assertFalse(s2.contains(","));
        assertTrue(s2.startsWith("["));
        assertTrue(s2.endsWith("] J"));
        assertTrue(s2.contains("\n"));

        String s3 = ev.format(
                VectorFormat.Col.defaults().setVectorPrefix("C").setSeparatorSymbol(" ").setStartSymbol("(").setEndSymbol(")"));
        assertTrue(s3.contains("1200.345"));
        assertTrue(s3.contains("123.456"));
        assertTrue(s3.contains("5432.10"));
        assertFalse(s3.contains(","));
        assertTrue(s3.startsWith("C("));
        assertTrue(s3.endsWith(") J"));
        assertFalse(s3.contains("\n"));

        String s4 = ev.format(VectorFormat.Col.defaults().setVectorPrefix("C").setSeparatorSymbol(" ").setStartSymbol("(")
                .setEndSymbol(")").setDisplayUnit("kJ"));
        assertTrue(s4.contains("1.200"));
        assertTrue(s4.contains("0.123"));
        assertTrue(s4.contains("5.432"));
        assertFalse(s4.contains(","));
        assertTrue(s4.startsWith("C("));
        assertTrue(s4.endsWith(") kJ"));
        assertFalse(s4.contains("\n"));
    }

    /**
     * Test format settings for the column-as-row vector.
     */
    @Test
    public void testColAsRowVectorFormat()
    {
        Vector3.Col<Energy> ev = Vector3.Col.of(1200.345, 123.456, 5432.104, Energy.Unit.J);

        String s2 = ev.format(VectorFormat.Row.defaults().setVectorPrefix("Col"));
        assertTrue(s2.contains("1200.345"));
        assertTrue(s2.contains("123.456"));
        assertTrue(s2.contains("5432.10"));
        assertTrue(s2.contains(","));
        assertTrue(s2.startsWith("Col["));
        assertTrue(s2.endsWith("] J"));

        String s3 = ev.format(
                VectorFormat.Row.defaults().setVectorPrefix("C").setSeparatorSymbol(" ").setStartSymbol("(").setEndSymbol(")"));
        assertTrue(s3.contains("1200.345"));
        assertTrue(s3.contains("123.456"));
        assertTrue(s3.contains("5432.10"));
        assertFalse(s3.contains(","));
        assertTrue(s3.startsWith("C("));
        assertTrue(s3.endsWith(") J"));

        String s4 = ev.format(VectorFormat.Row.defaults().setVectorPrefix("C").setSeparatorSymbol(" ").setStartSymbol("(")
                .setEndSymbol(")").setDisplayUnit("kJ"));
        assertTrue(s4.contains("1.200"));
        assertTrue(s4.contains("0.123"));
        assertTrue(s4.contains("5.432"));
        assertFalse(s4.contains(","));
        assertTrue(s4.startsWith("C("));
        assertTrue(s4.endsWith(") kJ"));
    }

    /**
     * Test format settings for the row-as-column vector.
     */
    @Test
    public void testRowAsColVectorFormat()
    {
        Vector3.Row<Energy> ev = Vector3.Row.of(1200.345, 123.456, 5432.104, Energy.Unit.J);

        String s2 = ev.format(VectorFormat.Col.defaults().setVectorPrefix("Row"));
        assertTrue(s2.contains("1200.345"));
        assertTrue(s2.contains("123.456"));
        assertTrue(s2.contains("5432.10"));
        assertFalse(s2.contains(","));
        assertTrue(s2.contains("\n"));
        assertTrue(s2.startsWith("Row["));
        assertTrue(s2.endsWith("] J"));

        String s3 = ev.format(
                VectorFormat.Col.defaults().setVectorPrefix("R").setSeparatorSymbol(" ").setStartSymbol("(").setEndSymbol(")"));
        assertTrue(s3.contains("1200.345"));
        assertTrue(s3.contains("123.456"));
        assertTrue(s3.contains("5432.10"));
        assertFalse(s2.contains(","));
        assertTrue(s2.contains("\n"));
        assertTrue(s3.startsWith("R("));
        assertTrue(s3.endsWith(") J"));

        String s4 = ev.format(VectorFormat.Col.defaults().setVectorPrefix("R").setSeparatorSymbol(" ").setStartSymbol("(")
                .setEndSymbol(")").setDisplayUnit("kJ"));
        assertTrue(s4.contains("1.200"));
        assertTrue(s4.contains("0.123"));
        assertTrue(s4.contains("5.432"));
        assertFalse(s2.contains(","));
        assertTrue(s2.contains("\n"));
        assertTrue(s4.startsWith("R("));
        assertTrue(s4.endsWith(") kJ"));
    }

    /**
     * Test prefix separator between number and unit, and postfix after the unit for an absolute column vector.
     */
    @Test
    public void testAbsVectorColPrefixPostfix()
    {
        AbsVector3.Col<Direction, Angle> dir =
                AbsVector3.Col.of(new double[] {1.2345, 0.23456, 0.55404}, Angle.Unit.rad, Direction.Reference.EAST);

        String s1 = dir.toString();
        assertTrue(s1.contains("1.235"));
        assertTrue(s1.endsWith(" rad"));

        String s2 = dir.format(Angle.Unit.deg);
        double deg = 1.2345 * 180.0 / Math.PI;
        assertTrue(s2.contains(String.valueOf(deg).substring(0, 5)));
        assertTrue(s2.endsWith(Angle.Unit.deg.getDisplayAbbreviation()));

        String s3 = dir.format(VectorFormat.Col.defaults().setUnitPrefix("  "));
        assertTrue(s3.contains("1.235"));
        assertTrue(s3.endsWith("  rad"));

        String s4 = dir.format(VectorFormat.Col.defaults().setUnitPrefix(", unit="));
        assertTrue(s4.contains("1.235"));
        assertTrue(s4.endsWith(", unit=rad"));

        String s5 = dir.format(VectorFormat.Col.defaults().setUnitPrefix(" (").setUnitPostfix(")"));
        assertTrue(s5.contains("1.235"));
        assertTrue(s5.endsWith(" (rad)"));

        String s6 =
                dir.format(VectorFormat.Col.defaults().setPrintReference().setReferencePrefix(" (").setReferencePostfix(")"));
        assertTrue(s6.contains("1.235"));
        assertTrue(s6.endsWith(" rad (EAST)"));
    }

    /**
     * Test prefix separator between number and unit, and postfix after the unit for an absolute row vector.
     */
    @Test
    public void testAbsVectorRowPrefixPostfix()
    {
        AbsVector3.Row<Direction, Angle> dir =
                AbsVector3.Row.of(new double[] {1.2345, 0.23456, 0.55404}, Angle.Unit.rad, Direction.Reference.EAST);

        String s1 = dir.toString();
        assertTrue(s1.contains("1.2345"));
        assertTrue(s1.endsWith(" rad"));

        String s2 = dir.format(Angle.Unit.deg);
        double deg = 1.2345 * 180.0 / Math.PI;
        assertTrue(s2.contains(String.valueOf(deg).substring(0, 7)));
        assertTrue(s2.endsWith(Angle.Unit.deg.getDisplayAbbreviation()));

        String s3 = dir.format(VectorFormat.Row.defaults().setUnitPrefix("  "));
        assertTrue(s3.contains("1.2345"));
        assertTrue(s3.endsWith("  rad"));

        String s4 = dir.format(VectorFormat.Row.defaults().setUnitPrefix(", unit="));
        assertTrue(s4.contains("1.2345"));
        assertTrue(s4.endsWith(", unit=rad"));

        String s5 = dir.format(VectorFormat.Row.defaults().setUnitPrefix(" (").setUnitPostfix(")"));
        assertTrue(s5.contains("1.2345"));
        assertTrue(s5.endsWith(" (rad)"));

        String s6 =
                dir.format(VectorFormat.Row.defaults().setPrintReference().setReferencePrefix(" (").setReferencePostfix(")"));
        assertTrue(s6.contains("1.2345"));
        assertTrue(s6.endsWith(" rad (EAST)"));
    }

    /**
     * Test absolute column vector formatting with prefix separator between number and reference, and postfix after the
     * reference.
     */
    @Test
    public void testAbsReferencePrefixPostfixCol()
    {
        AbsVector3.Col<Direction, Angle> n =
                AbsVector3.Col.of(new double[] {30, 40, 50}, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsVector3.Col<Direction, Angle> e =
                AbsVector3.Col.of(new double[] {30, 40, 50}, Angle.Unit.deg, Direction.Reference.EAST);

        String s1a = n.format(VectorFormat.Col.defaults().setTextual());
        assertTrue(s1a.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s1a.endsWith(" deg"));

        String s1b = n.format(VectorFormat.Col.defaults().setTextual());
        assertTrue(s1b.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s1b.endsWith(" deg"));

        String s2 = n.format(VectorFormat.Col.defaults().setNoReference().setTextual());
        assertTrue(s2.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s2.endsWith(" deg"));

        String s3 = n.format(VectorFormat.Col.defaults().setPrintReference(false).setTextual());
        assertTrue(s3.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s3.endsWith(" deg"));

        String s4n = n.format(VectorFormat.Col.defaults().setPrintReference().setTextual());
        assertTrue(s4n.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s4n.contains(" deg"));
        assertTrue(s4n.endsWith(" (NORTH)"));

        String s4e = e.format(VectorFormat.Col.defaults().setPrintReference(true).setTextual());
        assertTrue(s4e.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s4e.contains(" deg"));
        assertTrue(s4e.endsWith(" (EAST)"));

        String s5 = e.format(
                VectorFormat.Col.defaults().setPrintReference().setReferencePrefix(" [").setReferencePostfix("]").setTextual());
        assertTrue(s5.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s5.contains(" deg"));
        assertTrue(s5.endsWith(" [EAST]"));
    }

    /**
     * Test absolute row vector formatting with prefix separator between number and reference, and postfix after the reference.
     */
    @Test
    public void testAbsReferencePrefixPostfixRow()
    {
        AbsVector3.Row<Direction, Angle> n =
                AbsVector3.Row.of(new double[] {30, 40, 50}, Angle.Unit.deg, Direction.Reference.NORTH);
        AbsVector3.Row<Direction, Angle> e =
                AbsVector3.Row.of(new double[] {30, 40, 50}, Angle.Unit.deg, Direction.Reference.EAST);

        String s1a = n.format(VectorFormat.Row.defaults().setTextual());
        assertTrue(s1a.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s1a.endsWith(" deg"));

        String s1b = n.format(VectorFormat.Row.defaults().setTextual());
        assertTrue(s1b.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s1b.endsWith(" deg"));

        String s2 = n.format(VectorFormat.Row.defaults().setNoReference().setTextual());
        assertTrue(s2.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s2.endsWith(" deg"));

        String s3 = n.format(VectorFormat.Row.defaults().setPrintReference(false).setTextual());
        assertTrue(s3.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s3.endsWith(" deg"));

        String s4n = n.format(VectorFormat.Row.defaults().setPrintReference().setTextual());
        assertTrue(s4n.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s4n.contains(" deg"));
        assertTrue(s4n.endsWith(" (NORTH)"));

        String s4e = e.format(VectorFormat.Row.defaults().setPrintReference(true).setTextual());
        assertTrue(s4e.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s4e.contains(" deg"));
        assertTrue(s4e.endsWith(" (EAST)"));

        String s5 = e.format(
                VectorFormat.Row.defaults().setPrintReference().setReferencePrefix(" [").setReferencePostfix("]").setTextual());
        assertTrue(s5.contains("30") || s1a.contains("29.9999999"));
        assertTrue(s5.contains(" deg"));
        assertTrue(s5.endsWith(" [EAST]"));
    }

    /**
     * Test formatting as SI for column vector.
     */
    @Test
    public void testSiFormattingCol()
    {
        Vector2.Col<Energy> energy = Vector2.Col.of(1.2345, 6.789, Energy.Unit.kJ);
        AbsVector2.Col<Temperature, TemperatureDifference> temp =
                AbsVector2.Col.of(20.0, 30.0, Temperature.Unit.degC, Temperature.Reference.CELSIUS);

        String s1 = energy.format(VectorFormat.Col.defaults().setSiUnits().setEndSymbol(" |"));
        assertTrue(s1.endsWith(" | kgm2/s2"));

        String s2C = temp.format(VectorFormat.Col.defaults().setSiUnits().setEndSymbol(" |"));
        assertTrue(s2C.endsWith(" | K"));

        String s3C = temp.format(VectorFormat.Col.defaults().setSiUnits().setEndSymbol(" |").setPrintReference()
                .setReferencePrefix(" (").setReferencePostfix(")"));
        assertTrue(s3C.endsWith(" | K (CELSIUS)"));
    }

    /**
     * Test formatting as SI for row vector.
     */
    @Test
    public void testSiFormattingRow()
    {
        Vector2.Row<Energy> energy = Vector2.Row.of(1.2345, 6.789, Energy.Unit.kJ);
        AbsVector2.Row<Temperature, TemperatureDifference> temp =
                AbsVector2.Row.of(20.0, 30.0, Temperature.Unit.degC, Temperature.Reference.CELSIUS);

        String s1 = energy.format(VectorFormat.Row.defaults().setSiUnits().setEndSymbol(" |"));
        assertTrue(s1.endsWith(" | kgm2/s2"));

        String s2C = temp.format(VectorFormat.Row.defaults().setSiUnits().setEndSymbol(" |"));
        assertTrue(s2C.endsWith(" | K"));

        String s3C = temp.format(VectorFormat.Row.defaults().setSiUnits().setEndSymbol(" |").setPrintReference()
                .setReferencePrefix(" (").setReferencePostfix(")"));
        assertTrue(s3C.endsWith(" | K (CELSIUS)"));
    }

    /**
     * Test the setting and resetting of defaults for a Row vector.
     */
    @Test
    public void testDefaultsRow()
    {
        Vector3.Row<Energy> evRow = Vector3.Row.of(1200.345, 123.456, 5432.104, Energy.Unit.J);
        Vector3.Col<Energy> evCol = Vector3.Col.of(1200.345, 123.456, 5432.104, Energy.Unit.J);

        try
        {
            // VARIABLE_LENGTH
            String s1 = evRow.toString();
            assertTrue(s1.contains("1200.345"));
            assertTrue(s1.contains("123.456"));
            assertTrue(s1.contains("5432.10"));
            assertTrue(s1.contains(","));
            assertTrue(s1.startsWith("["));
            assertTrue(s1.endsWith("] J"));

            VectorFormat.Row.changeDefaults().setFixedWithSciFallback().setDecimals(1).setWidth(10).setGroupingSeparator(true)
                    .setVectorPrefix("Row");

            s1 = evRow.toString();
            assertTrue(s1.contains("1,200.3,"));
            assertTrue(s1.contains("123.5,"));
            assertTrue(s1.contains("5,432.1"));
            assertTrue(s1.startsWith("Row["));
            assertTrue(s1.endsWith("] J"));

            // Col vector should be unaffected
            String s2 = evCol.toString();
            assertTrue(s2.contains("1200.345"));
            assertTrue(s2.contains("123.456"));
            assertTrue(s2.contains("5432.10"));
            assertTrue(s2.startsWith("["));
            assertTrue(s2.endsWith("] J"));

            VectorFormat.Row.resetDefaults();
            s1 = evRow.toString();
            assertTrue(s1.contains("1200.345"));
            assertTrue(s1.contains("123.456"));
            assertTrue(s1.contains("5432.10"));
            assertTrue(s1.contains(","));
            assertTrue(s1.startsWith("["));
            assertTrue(s1.endsWith("] J"));
        }
        finally
        {
            VectorFormat.Row.resetDefaults();
        }
    }

    /**
     * Test the setting and resetting of defaults for a Col vector.
     */
    @Test
    public void testDefaultsCol()
    {
        Vector3.Row<Energy> evRow = Vector3.Row.of(1200.345, 123.456, 5432.104, Energy.Unit.J);
        Vector3.Col<Energy> evCol = Vector3.Col.of(1200.345, 123.456, 5432.104, Energy.Unit.J);

        try
        {
            // VARIABLE_LENGTH
            String s1 = evCol.toString();
            assertTrue(s1.contains("1200.345\n"));
            assertTrue(s1.contains("123.456\n"));
            assertTrue(s1.contains("5432.10"));
            assertFalse(s1.contains(","));
            assertTrue(s1.contains("\n"));
            assertTrue(s1.startsWith("["));
            assertTrue(s1.endsWith("] J"));

            VectorFormat.Col.changeDefaults().setFixedWithSciFallback().setDecimals(1).setWidth(10).setGroupingSeparator(true)
                    .setVectorPrefix("Col");

            s1 = evCol.toString();
            assertTrue(s1.contains("1,200.3\n"));
            assertTrue(s1.contains("123.5\n"));
            assertTrue(s1.contains("5,432.1"));
            assertTrue(s1.contains("\n"));
            assertTrue(s1.startsWith("Col["));
            assertTrue(s1.endsWith("] J"));

            // Row vector should be unaffected
            String s2 = evRow.toString();
            assertTrue(s2.contains("1200.345"));
            assertTrue(s2.contains("123.456"));
            assertTrue(s2.contains("5432.10"));
            assertTrue(s2.contains(","));
            assertFalse(s2.contains("\n"));
            assertTrue(s2.startsWith("["));
            assertTrue(s2.endsWith("] J"));

            VectorFormat.Col.resetDefaults();
            s1 = evCol.toString();
            assertTrue(s1.contains("1200.345\n"));
            assertTrue(s1.contains("123.456\n"));
            assertTrue(s1.contains("5432.10"));
            assertFalse(s1.contains(","));
            assertTrue(s1.contains("\n"));
            assertTrue(s1.startsWith("["));
            assertTrue(s1.endsWith("] J"));
        }
        finally
        {
            VectorFormat.Col.resetDefaults();
        }
    }

}
