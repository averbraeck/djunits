package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * CatalyticActivityTest tests the CatalyticActivity quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class CatalyticActivityTest
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
     * Test the basic features: constructors, constants, parsing, SI conversions (including prefixes), instantiate, siUnit, and
     * ofSi.
     */
    @Test
    void testCatalyticActivityBasics()
    {
        // Construct with unit
        CatalyticActivity c0 = new CatalyticActivity(0.0, CatalyticActivity.Unit.kat);
        assertEquals(CatalyticActivity.ZERO, c0);
        assertEquals(0.0, CatalyticActivity.ZERO.si());

        CatalyticActivity c1 = new CatalyticActivity(1.0, CatalyticActivity.Unit.kat);
        assertEquals(CatalyticActivity.ONE, c1);
        assertEquals(1.0, CatalyticActivity.ONE.si());

        // Constants
        assertTrue(Double.isNaN(CatalyticActivity.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, CatalyticActivity.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, CatalyticActivity.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, CatalyticActivity.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, CatalyticActivity.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        CatalyticActivity milli = new CatalyticActivity(2.0, CatalyticActivity.Unit.mkat); // 2 mkat = 2e-3 kat
        CatalyticActivity copy = new CatalyticActivity(milli);
        assertEquals(milli.si(), copy.si(), 1E-12);

        // Construct with abbreviation string
        CatalyticActivity cStr = new CatalyticActivity(1.5, "kat");
        assertEquals(1.5, cStr.si(), 1E-12);

        // SI prefixes via resolved units
        CatalyticActivity mkat = new CatalyticActivity(3.0, CatalyticActivity.Unit.mkat); // milli
        assertEquals(3.0e-3, mkat.si(), 1E-15);

        CatalyticActivity mukat = new CatalyticActivity(4.0, CatalyticActivity.Unit.mukat); // micro
        assertEquals(4.0e-6, mukat.si(), 1E-18);

        CatalyticActivity nkat = new CatalyticActivity(5.0, CatalyticActivity.Unit.nkat); // nano
        assertEquals(5.0e-9, nkat.si(), 1E-21);

        // Parsing valueOf and of(value, unitString)
        CatalyticActivity p1 = CatalyticActivity.valueOf("2 kat");
        assertEquals(2.0, p1.si(), 1E-12);

        CatalyticActivity p2 = CatalyticActivity.valueOf("300 mkat");
        assertEquals(300.0e-3, p2.si(), 1E-12);

        CatalyticActivity p3 = CatalyticActivity.of(750.0, "nkat");
        assertEquals(750.0e-9, p3.si(), 1E-21);

        // instantiate
        assertEquals(-10.1, cStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation
        assertEquals("mol/s", cStr.siUnit().toString(true, false));

        // ofSi
        CatalyticActivity neg = CatalyticActivity.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic: divide/multiply combinations.
     */
    @Test
    void testCatalyticActivityOperations()
    {
        // Divide by CatalyticActivity -> Dimensionless
        var d1 = CatalyticActivity.ONE.divide(CatalyticActivity.ONE.scaleBy(2.0));
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(0.5, d1.si(), 1E-12);

        // CatalyticActivity * Duration -> AmountOfSubstance
        var n2 = CatalyticActivity.ONE.scaleBy(2.0).multiply(Duration.ONE.scaleBy(3.0));
        assertTrue(n2 instanceof AmountOfSubstance);
        assertEquals(6.0, n2.si(), 1E-12);

        // CatalyticActivity / AmountOfSubstance -> Frequency
        var f3 = CatalyticActivity.ONE.scaleBy(6.0).divide(AmountOfSubstance.ONE.scaleBy(2.0));
        assertTrue(f3 instanceof Frequency);
        assertEquals(3.0, f3.si(), 1E-12);

        // CatalyticActivity / Frequency -> AmountOfSubstance
        var n4 = CatalyticActivity.ONE.scaleBy(6.0).divide(Frequency.ONE.scaleBy(2.0));
        assertTrue(n4 instanceof AmountOfSubstance);
        assertEquals(3.0, n4.si(), 1E-12);
    }

    /**
     * Test the CatalyticActivity.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(CatalyticActivity.Unit.SI_UNIT, CatalyticActivity.Unit.kat.siUnit());
        assertEquals(CatalyticActivity.Unit.SI, CatalyticActivity.Unit.kat.getBaseUnit());

        // Unit.ofSi delegates to CatalyticActivity.ofSi
        CatalyticActivity fromUnit = CatalyticActivity.Unit.kat.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (kat) -> should succeed
        CatalyticActivity.Unit twoKat =
                CatalyticActivity.Unit.kat.deriveUnit("2kat", "2kat", "two katal", 2.0, UnitSystem.OTHER);
        CatalyticActivity x = new CatalyticActivity(1.0, twoKat); // 1 * 2 kat == 2 kat
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        CatalyticActivity.Unit nonLinear = new CatalyticActivity.Unit("gkat", "gkat", "grade-like katal (nonlinear)",
                new org.djunits.unit.scale.GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2kat", "g2kat", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
