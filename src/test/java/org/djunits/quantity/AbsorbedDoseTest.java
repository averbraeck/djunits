package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * AbsorbedDoseTest tests the AbsorbedDose quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
class AbsorbedDoseTest
{
    /**
     * Set the locale to "US" so we know what texts should be retrieved from the resources.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test the basic features.
     */
    @Test
    void testAbsorbedDose()
    {
        AbsorbedDose q0 = new AbsorbedDose(0.0, AbsorbedDose.Unit.Gy);
        assertEquals(AbsorbedDose.ZERO, q0);
        assertEquals(0.0, AbsorbedDose.ZERO.si());
        AbsorbedDose q1 = new AbsorbedDose(1.0, AbsorbedDose.Unit.Gy);
        assertEquals(AbsorbedDose.ONE, q1);
        assertEquals(1.0, AbsorbedDose.ONE.si());
        assertEquals(Double.NaN, AbsorbedDose.NaN.si());
        assertEquals(Double.POSITIVE_INFINITY, AbsorbedDose.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, AbsorbedDose.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, AbsorbedDose.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, AbsorbedDose.NEG_MAXVALUE.si());

        var q2 = new AbsorbedDose(2.0, AbsorbedDose.Unit.mGy);
        assertEquals(0.002, q2.si());
        var q3 = new AbsorbedDose(q2);
        assertEquals(0.002, q3.si());
        var q4 = new AbsorbedDose(4.0, "muGy");
        assertEquals(4.0E-6, q4.si());
        assertEquals(-10.1, q4.instantiate(-10.1).si());
        assertEquals("m2/s2", q4.siUnit().toString(true, false));
        
        var q5 = AbsorbedDose.valueOf("12.1 mGy");
        assertEquals(0.0121, q5.si());
        var q6 = AbsorbedDose.of(-12.1, "mGy");
        assertEquals(-0.0121, q6.si());
        var q7 = AbsorbedDose.ofSi(-2.0);
        assertEquals(-2.0, q7.si());
    }
    
    /**
     * Test multiply and divide.
     */
    @Test
    void testMultiplyDivide()
    {
        var d1 = AbsorbedDose.ONE.divide(AbsorbedDose.ONE.scaleBy(2.0));
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(0.5, d1.si(), 1E-10);
    }
    
}
