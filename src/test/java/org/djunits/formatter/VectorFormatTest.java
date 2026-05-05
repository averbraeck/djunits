package org.djunits.formatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Energy;
import org.djunits.vecmat.d3.Vector3;
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
