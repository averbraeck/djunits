package org.djunits.formatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Energy;
import org.djunits.vecmat.table.QuantityTable;
import org.junit.jupiter.api.Test;

/**
 * Tests all {@link TableFormat} settings and their effect on matrix formatting.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class TableFormatTest
{
    /**
     * Test format settings for the matrix.
     */
    @Test
    public void testTableFormat()
    {
        QuantityTable<Energy> ev =
                QuantityTable.of(new double[][] {{1200.345, 123.456, 5432.104}, {1, 2, 3}, {4, 5, 6}}, Energy.Unit.J);

        String s1 = ev.toString();
        assertTrue(s1.contains("1200.345"));
        assertTrue(s1.contains("123.456"));
        assertTrue(s1.contains("5432.10"));
        assertFalse(s1.contains(","));
        assertTrue(s1.startsWith("|"));
        assertTrue(s1.endsWith("| J"));

        String s2 = ev.format(TableFormat.defaults());
        assertTrue(s2.contains("1200.345"));
        assertTrue(s2.contains("123.456"));
        assertTrue(s2.contains("5432.10"));
        assertFalse(s2.contains(","));
        assertTrue(s2.startsWith("|"));
        assertTrue(s2.endsWith("| J"));

        String s3 =
                ev.format(TableFormat.defaults().setTablePrefix("Mat ").setColSeparatorSymbol(" , ").setFirstRowStartSymbol("/")
                        .setFirstRowEndSymbol(" \\\n").setMiddleRowStartSymbol("    |").setMiddleRowEndSymbol(" | \n")
                        .setLastRowStartSymbol("    \\").setLastRowEndSymbol(" /").setTablePostfix(" unit ="));
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

        String s4 = ev.format(TableFormat.defaults().setColSeparatorSymbol(" ").setDisplayUnit("kJ"));
        assertTrue(s4.contains("1.200"));
        assertTrue(s4.contains("0.123"));
        assertTrue(s4.contains("5.432"));
        assertFalse(s4.contains(","));
        assertTrue(s4.endsWith("| kJ"));
    }

    /**
     * Test the setting and resetting of defaults.
     */
    @Test
    public void testDefaults()
    {
        QuantityTable<Energy> ev =
                QuantityTable.of(new double[][] {{1200.345, 123.456, 5432.104}, {1, 2, 3}, {4, 5, 6}}, Energy.Unit.J);

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

            TableFormat.changeDefaults().fixedWithSciFallback().setDecimals(1).setWidth(10).setGroupingSeparator(true)
                    .setFirstRowStartSymbol("/ ").setFirstRowEndSymbol(" \\\n").setLastRowStartSymbol("\\ ")
                    .setLastRowEndSymbol(" /");
            s1 = ev.toString();
            assertTrue(s1.contains("1,200.3 "));
            assertTrue(s1.contains("123.5 "));
            assertTrue(s1.contains("5,432.1 "));
            assertTrue(s1.contains("\\"));
            assertTrue(s1.startsWith("/"));
            assertTrue(s1.endsWith("/ J"));

            TableFormat.resetDefaults();
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
            TableFormat.resetDefaults();
        }
    }
    
}
