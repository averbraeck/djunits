package org.djunits.unit.scale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.djunits.unit.util.UnitException;
import org.junit.Test;

/**
 * ScaleTest.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class ScaleTest
{
    /**
     * Test the correct implementation of scales.
     * @throws UnitException on (unexpected) error
     */
    @Test
    public void testScale() throws UnitException
    {
        IdentityScale sscale = IdentityScale.SCALE;
        assertEquals(1.0, sscale.getConversionFactorToStandardUnit(), 0.0001);
        assertEquals(1.0, sscale.toStandardUnit(1.0), 0.0001);
        assertEquals(1.0, sscale.fromStandardUnit(1.0), 0.0001);
        assertEquals(2.5, sscale.toStandardUnit(2.5), 0.0001);
        assertEquals("IdentityScale []", sscale.toString());

        IdentityScale s1 = IdentityScale.SCALE;
        IdentityScale s2 = IdentityScale.SCALE;
        assertEquals(s1, s1);
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
        assertFalse(s1.equals(null));

        LinearScale kiloscale = new LinearScale(1000.0); // kilo
        assertEquals(1000.0, kiloscale.getConversionFactorToStandardUnit(), 0.0001);
        assertEquals(1000.0, kiloscale.toStandardUnit(1.0), 0.0001);
        assertEquals(1.0E-3, kiloscale.fromStandardUnit(1.0), 0.0001);
        assertEquals(2500.0, kiloscale.toStandardUnit(2.5), 0.0001);
        assertTrue(kiloscale.toString().contains("LinearScale"));
        assertTrue(kiloscale.toString().contains("1000.0"));

        LinearScale l1 = new LinearScale(123.0);
        LinearScale l2 = new LinearScale(123.0);
        assertEquals(l1, l1);
        assertEquals(l1, l2);
        assertEquals(l1.hashCode(), l2.hashCode());
        assertNotEquals(l1, kiloscale);
        assertNotEquals(l1, sscale);
        assertNotEquals(l1.hashCode(), kiloscale.hashCode());
        assertNotEquals(l1.hashCode(), sscale.hashCode());
        assertFalse(l1.equals(null));

        OffsetLinearScale cscale = new OffsetLinearScale(1.0, 273.15); // C-K
        assertEquals(1.0, cscale.getConversionFactorToStandardUnit(), 0.0001);
        assertEquals(273.15, cscale.getOffsetToStandardUnit(), 0.0001);
        assertEquals(273.15, cscale.toStandardUnit(0.0), 0.0001);
        assertEquals(0.0, cscale.toStandardUnit(-273.15), 0.0001);
        assertEquals(-273.15, cscale.fromStandardUnit(0.0), 0.0001);
        assertEquals(0.0, cscale.fromStandardUnit(273.15), 0.0001);
        assertTrue(cscale.toString().contains("OffsetLinearScale"));
        assertTrue(cscale.toString().contains("273.15"));
        assertTrue(cscale.toString().contains("1.0"));

        OffsetLinearScale o1 = new OffsetLinearScale(123.0, 456.0);
        OffsetLinearScale o2 = new OffsetLinearScale(123.0, 456.0);
        assertEquals(o1, o1);
        assertEquals(o1, o2);
        assertEquals(o1.hashCode(), o2.hashCode());
        assertNotEquals(o1, kiloscale);
        assertNotEquals(o1, sscale);
        assertNotEquals(o1, cscale);
        assertFalse(o1.equals(new OffsetLinearScale(123.0, 1.0)));
        assertFalse(o1.equals(new OffsetLinearScale(1.0, 456.0)));
        assertNotEquals(kiloscale, o2);
        assertNotEquals(sscale, o2);
        assertNotEquals(cscale, o2);
        assertNotEquals(o1.hashCode(), kiloscale.hashCode());
        assertNotEquals(o1.hashCode(), sscale.hashCode());
        assertNotEquals(o1.hashCode(), cscale.hashCode());
        assertFalse(o1.equals(null));

        GradeScale gscale = new GradeScale(1.0); // fraction -> angle
        assertEquals(1.0, gscale.getConversionFactorToGrade(), 0.0001);
        assertEquals(Math.PI / 4, gscale.toStandardUnit(1), 0.0001);
        assertEquals(0.0, gscale.toStandardUnit(0.0), 0.0001);
        assertEquals(0.1, gscale.fromStandardUnit(Math.toRadians(5.71)), 0.01); // 10% = 5.71 degree
        assertEquals(0.0, gscale.fromStandardUnit(0.0), 0.0001);
        assertTrue(gscale.toString().contains("GradeScale"));
        assertTrue(gscale.toString().contains("1.0"));

        GradeScale g1 = new GradeScale(0.01); // percent
        GradeScale g2 = new GradeScale(0.01); // percent
        assertEquals(g1, g1);
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
        assertNotEquals(g1, kiloscale);
        assertNotEquals(g1, sscale);
        assertNotEquals(g1, cscale);
        assertNotEquals(g1, gscale);
        assertNotEquals(kiloscale, g2);
        assertNotEquals(sscale, g2);
        assertNotEquals(cscale, g2);
        assertNotEquals(g1.hashCode(), kiloscale.hashCode());
        assertNotEquals(g1.hashCode(), sscale.hashCode());
        assertNotEquals(g1.hashCode(), cscale.hashCode());
        assertNotEquals(g1.hashCode(), gscale.hashCode());
        assertFalse(g1.equals(null));
    }

    /**
     * Test the OffsetScale for correctness and reciprocity.
     * @throws UnitException on (unexpected) error
     */
    @Test
    public void testOffsetLinearScale() throws UnitException
    {
        OffsetLinearScale fahrenheitScale = new OffsetLinearScale(5.0 / 9.0, 459.67);
        assertEquals(255.372, fahrenheitScale.toStandardUnit(0.0), 0.01); // 0 F = 255.372 K
        assertEquals(273.15, fahrenheitScale.toStandardUnit(32.0), 0.01); // 32 F = 273.15 K
        assertEquals(0.0, fahrenheitScale.toStandardUnit(-459.67), 0.01); // -459.67 F = 0 K
        assertEquals(0.0, fahrenheitScale.fromStandardUnit(255.372), 0.01); // 0 F = 255.372 K
        assertEquals(32.0, fahrenheitScale.fromStandardUnit(273.15), 0.01); // 32 F = 273.15 K
        assertEquals(-459.67, fahrenheitScale.fromStandardUnit(0.0), 0.01); // -459.67 F = 0 K

        OffsetLinearScale std = new OffsetLinearScale(1.0, 0.0);
        OffsetLinearScale ols = new OffsetLinearScale(2.0, 10.0);

        assertEquals(123.0, std.fromStandardUnit(std.toStandardUnit(123.0)), 0.00001);
        assertEquals(123.0, std.toStandardUnit(std.fromStandardUnit(123.0)), 0.00001);

        assertEquals(123.0, ols.fromStandardUnit(ols.toStandardUnit(123.0)), 0.00001);
        assertEquals(123.0, ols.toStandardUnit(ols.fromStandardUnit(123.0)), 0.00001);
    }

}
