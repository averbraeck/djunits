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
        assertTrue(s1.startsWith("Row["));
        assertTrue(s1.endsWith("] J"));

        String s2 = ev.toString(VectorFormat.defaults());
        assertTrue(s2.contains("1200.345"));
        assertTrue(s2.contains("123.456"));
        assertTrue(s2.contains("5432.10"));
        assertTrue(s2.contains(","));
        assertTrue(s2.startsWith("Row["));
        assertTrue(s2.endsWith("] J"));

        String s3 = ev.toString(VectorFormat.defaults().setRowVectorPrefix("R").setRowSeparatorSymbol(" ")
                .setRowStartSymbol("(").setRowEndSymbol(")"));
        assertTrue(s3.contains("1200.345"));
        assertTrue(s3.contains("123.456"));
        assertTrue(s3.contains("5432.10"));
        assertFalse(s3.contains(","));
        assertTrue(s3.startsWith("R("));
        assertTrue(s3.endsWith(") J"));

        String s4 = ev.toString(VectorFormat.defaults().setRowVectorPrefix("R").setRowSeparatorSymbol(" ")
                .setRowStartSymbol("(").setRowEndSymbol(")").setDisplayUnit("kJ"));
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
        assertTrue(s1.contains(","));
        assertTrue(s1.startsWith("Col["));
        assertTrue(s1.endsWith("] J"));
        assertFalse(s1.contains("\n"));

        String s2 = ev.toString(VectorFormat.defaults().setColAsRow(false));
        assertTrue(s2.contains("1200.345"));
        assertTrue(s2.contains("123.456"));
        assertTrue(s2.contains("5432.10"));
        assertFalse(s2.contains(","));
        assertTrue(s2.startsWith("Col["));
        assertTrue(s2.endsWith("] J"));
        assertTrue(s2.contains("\n"));

        String s3 = ev.toString(VectorFormat.defaults().setColAsRow(false).setColVectorPrefix("C").setColSeparatorSymbol(" ")
                .setColStartSymbol("(").setColEndSymbol(")"));
        assertTrue(s3.contains("1200.345"));
        assertTrue(s3.contains("123.456"));
        assertTrue(s3.contains("5432.10"));
        assertFalse(s3.contains(","));
        assertTrue(s3.startsWith("C("));
        assertTrue(s3.endsWith(") J"));
        assertFalse(s3.contains("\n"));

        String s4 = ev.toString(VectorFormat.defaults().setColAsRow(false).setColVectorPrefix("C").setColSeparatorSymbol(" ")
                .setColStartSymbol("(").setColEndSymbol(")").setDisplayUnit("kJ"));
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

        String s2 = ev.toString(VectorFormat.defaults().setColAsRow(true));
        assertTrue(s2.contains("1200.345"));
        assertTrue(s2.contains("123.456"));
        assertTrue(s2.contains("5432.10"));
        assertTrue(s2.contains(","));
        assertTrue(s2.startsWith("Col["));
        assertTrue(s2.endsWith("] J"));

        String s3 = ev.toString(VectorFormat.defaults().setColAsRow(true).setColVectorPrefix("C").setRowSeparatorSymbol(" ")
                .setRowStartSymbol("(").setRowEndSymbol(")"));
        assertTrue(s3.contains("1200.345"));
        assertTrue(s3.contains("123.456"));
        assertTrue(s3.contains("5432.10"));
        assertFalse(s3.contains(","));
        assertTrue(s3.startsWith("C("));
        assertTrue(s3.endsWith(") J"));

        String s4 = ev.toString(VectorFormat.defaults().setColAsRow(true).setColVectorPrefix("C").setRowSeparatorSymbol(" ")
                .setRowStartSymbol("(").setRowEndSymbol(")").setDisplayUnit("kJ"));
        assertTrue(s4.contains("1.200"));
        assertTrue(s4.contains("0.123"));
        assertTrue(s4.contains("5.432"));
        assertFalse(s4.contains(","));
        assertTrue(s4.startsWith("C("));
        assertTrue(s4.endsWith(") kJ"));
    }

}
