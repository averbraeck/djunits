package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * AccelerationTest tests the Acceleration quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
class AccelerationTest
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
    void testAcceleration()
    {
        Acceleration q0 = new Acceleration(0.0, Acceleration.Unit.m_s2);
        assertEquals(Acceleration.ZERO, q0);
        assertEquals(0.0, Acceleration.ZERO.si());
        Acceleration q1 = new Acceleration(1.0, Acceleration.Unit.m_s2);
        assertEquals(Acceleration.ONE, q1);
        assertEquals(1.0, Acceleration.ONE.si());
        assertEquals(Double.NaN, Acceleration.NaN.si());
        assertEquals(Double.POSITIVE_INFINITY, Acceleration.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Acceleration.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Acceleration.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Acceleration.NEG_MAXVALUE.si());

        var q2 = new Acceleration(2.0, Acceleration.Unit.km_h2);
        assertEquals(2.0 * 1000.0 / (3600.0 * 3600.0), q2.si(), 1E-10);
        var q3 = new Acceleration(q2);
        assertEquals(q2.si(), q3.si());
        var q4 = new Acceleration(4.0, "m/s2");
        assertEquals(4.0, q4.si());
        assertEquals(-10.1, q4.instantiate(-10.1).si());
        assertEquals("m/s2", q4.siUnit().toString(true, false));

        var q5 = Acceleration.valueOf("12.1 mi/s2");
        assertEquals(12.1 * Length.Unit.CONST_MI, q5.si(), 1E-6);
        var q6 = Acceleration.of(-12.1, "mi/h2");
        assertEquals(-12.1 * Length.Unit.CONST_MI / (3600.0 * 3600.0), q6.si(), 1E-6);
        var q7 = Acceleration.ofSi(-2.0);
        assertEquals(-2.0, q7.si());
    }

    /**
     * Test multiply and divide.
     */
    @Test
    void testMultiplyDivide()
    {
        var d1 = Acceleration.ONE.divide(Acceleration.ONE.scaleBy(2.0));
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(0.5, d1.si(), 1E-10);
        
        var m2 = Acceleration.ONE.scaleBy(2.0).multiply(Duration.ONE.scaleBy(3.0));
        assertTrue(m2 instanceof Speed);
        assertEquals(6.0, m2.si(), 1E-10);
        
        var m3 = Acceleration.ONE.scaleBy(2.0).multiply(Mass.ONE.scaleBy(3.0));
        assertTrue(m3 instanceof Force);
        assertEquals(6.0, m3.si(), 1E-10);
        
        var m4 = Acceleration.ONE.scaleBy(2.0).multiply(Momentum.ONE.scaleBy(3.0));
        assertTrue(m4 instanceof Power);
        assertEquals(6.0, m4.si(), 1E-10);
        
        var d5 = Acceleration.ONE.scaleBy(3.0).divide(Frequency.ONE.scaleBy(2.0));
        assertTrue(d5 instanceof Speed);
        assertEquals(1.5, d5.si(), 1E-10);
        
        var d6 = Acceleration.ONE.scaleBy(3.0).divide(Speed.ONE.scaleBy(2.0));
        assertTrue(d6 instanceof Frequency);
        assertEquals(1.5, d6.si(), 1E-10);
    }

}
