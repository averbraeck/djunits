package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * AmountOfSubstanceTest tests the AmountOfSubstance quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
class AmountOfSubstanceTest
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
    void testAmountOfSubstance()
    {
        AmountOfSubstance q0 = new AmountOfSubstance(0.0, AmountOfSubstance.Unit.mol);
        assertEquals(AmountOfSubstance.ZERO, q0);
        assertEquals(0.0, AmountOfSubstance.ZERO.si());
        AmountOfSubstance q1 = new AmountOfSubstance(1.0, AmountOfSubstance.Unit.mol);
        assertEquals(AmountOfSubstance.ONE, q1);
        assertEquals(1.0, AmountOfSubstance.ONE.si());
        assertEquals(Double.NaN, AmountOfSubstance.NaN.si());
        assertEquals(Double.POSITIVE_INFINITY, AmountOfSubstance.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, AmountOfSubstance.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, AmountOfSubstance.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, AmountOfSubstance.NEG_MAXVALUE.si());

        var q2 = new AmountOfSubstance(2.0, AmountOfSubstance.Unit.mmol);
        assertEquals(0.002, q2.si(), 1E-10);
        var q3 = new AmountOfSubstance(q2);
        assertEquals(q2.si(), q3.si());
        var q4 = new AmountOfSubstance(4.0, "mol");
        assertEquals(4.0, q4.si());
        assertEquals(-10.1, q4.instantiate(-10.1).si());
        assertEquals("mol", q4.siUnit().toString(true, false));
        
        var q5 = AmountOfSubstance.valueOf("12.1 mmol");
        assertEquals(12.1 * 0.001, q5.si(), 1E-6);
        var q6 = AmountOfSubstance.of(-12.1, "mol");
        assertEquals(-12.1, q6.si(), 1E-6);
        var q7 = AmountOfSubstance.ofSi(-2.0);
        assertEquals(-2.0, q7.si());
    }

    /**
     * Test multiply and divide.
     */
    @Test
    void testMultiplyDivide()
    {
        var d1 = AmountOfSubstance.ONE.divide(AmountOfSubstance.ONE.scaleBy(2.0));
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(0.5, d1.si(), 1E-10);

        var d5 = AmountOfSubstance.ONE.scaleBy(3.0).divide(CatalyticActivity.ONE.scaleBy(2.0));
        assertTrue(d5 instanceof Duration);
        assertEquals(1.5, d5.si(), 1E-10);

        var d6 = AmountOfSubstance.ONE.scaleBy(3.0).divide(Duration.ONE.scaleBy(2.0));
        assertTrue(d6 instanceof CatalyticActivity);
        assertEquals(1.5, d6.si(), 1E-10);
    }

}
