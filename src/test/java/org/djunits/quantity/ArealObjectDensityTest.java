package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ArealObjectDensityTest tests the ArealObjectDensity quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class ArealObjectDensityTest
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
     * Test the basic features: constructors, constants, parsing, instantiate, siUnit, and ofSi.
     */
    @Test
    void testArealObjectDensityBasics()
    {
        // Construct with unit
        ArealObjectDensity d0 = new ArealObjectDensity(0.0, ArealObjectDensity.Unit.per_m2);
        assertEquals(ArealObjectDensity.ZERO, d0);
        assertEquals(0.0, ArealObjectDensity.ZERO.si());

        ArealObjectDensity d1 = new ArealObjectDensity(1.0, ArealObjectDensity.Unit.per_m2);
        assertEquals(ArealObjectDensity.ONE, d1);
        assertEquals(1.0, ArealObjectDensity.ONE.si());

        // Constants sanity
        assertTrue(Double.isNaN(ArealObjectDensity.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, ArealObjectDensity.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, ArealObjectDensity.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, ArealObjectDensity.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, ArealObjectDensity.NEG_MAXVALUE.si());

        // Copy constructor preserves SI value and display unit
        ArealObjectDensity d2 = new ArealObjectDensity(2.5, ArealObjectDensity.Unit.per_m2);
        ArealObjectDensity copy = new ArealObjectDensity(d2);
        assertEquals(d2.si(), copy.si(), 1E-12);

        // Construct with abbreviation string
        ArealObjectDensity dStr = new ArealObjectDensity(3.0, "/m2");
        assertEquals(3.0, dStr.si(), 1E-12);

        // Parsing valueOf and of(value, unitString)
        ArealObjectDensity p1 = ArealObjectDensity.valueOf("4 /m2");
        assertEquals(4.0, p1.si(), 1E-12);

        ArealObjectDensity p2 = ArealObjectDensity.of(5.0, "/m2");
        assertEquals(5.0, p2.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, dStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation
        assertEquals("1/m2", dStr.siUnit().toString(true, false));

        // ofSi
        ArealObjectDensity neg = ArealObjectDensity.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic operations and reciprocal.
     */
    @Test
    void testArealObjectDensityOperations()
    {
        // Divide by ArealObjectDensity -> Dimensionless
        var r1 = ArealObjectDensity.ONE.divide(ArealObjectDensity.ONE.scaleBy(2.0));
        assertTrue(r1 instanceof Dimensionless);
        assertEquals(0.5, r1.si(), 1E-12);

        // Multiply by Area -> Dimensionless (count)
        var count = ArealObjectDensity.ofSi(2.0).multiply(Area.ofSi(3.0));
        assertTrue(count instanceof Dimensionless);
        assertEquals(6.0, count.si(), 1E-12);

        // Multiply by Length -> LinearObjectDensity
        var lod = ArealObjectDensity.ofSi(2.0).multiply(Length.ofSi(3.0));
        assertTrue(lod instanceof LinearObjectDensity);
        assertEquals(6.0, lod.si(), 1E-12);

        // Divide by Length -> VolumetricObjectDensity
        var vod = ArealObjectDensity.ofSi(6.0).divide(Length.ofSi(2.0));
        assertTrue(vod instanceof VolumetricObjectDensity);
        assertEquals(3.0, vod.si(), 1E-12);

        // Divide by VolumetricObjectDensity -> Length
        var len = ArealObjectDensity.ofSi(6.0).divide(VolumetricObjectDensity.ofSi(2.0));
        assertTrue(len instanceof Length);
        assertEquals(3.0, len.si(), 1E-12);

        // reciprocal -> Area
        var area = ArealObjectDensity.ofSi(4.0).reciprocal();
        assertTrue(area instanceof Area);
        assertEquals(0.25, area.si(), 1E-12);
    }

    /**
     * Test the ArealObjectDensity.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear
     * scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(ArealObjectDensity.Unit.SI_UNIT, ArealObjectDensity.Unit.per_m2.siUnit());
        assertEquals(ArealObjectDensity.Unit.SI, ArealObjectDensity.Unit.per_m2.getBaseUnit());

        // Unit.ofSi delegates to ArealObjectDensity.ofSi
        ArealObjectDensity fromUnit = ArealObjectDensity.Unit.per_m2.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (per m2) -> should succeed
        ArealObjectDensity.Unit twoPerM2 =
                ArealObjectDensity.Unit.per_m2.deriveUnit("/m2x2", "/m2x2", "twice per square meter", 2.0, UnitSystem.OTHER);
        ArealObjectDensity x = new ArealObjectDensity(1.0, twoPerM2); // 1 * 2 (/m2) == 2 (/m2)
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        ArealObjectDensity.Unit nonLinear = new ArealObjectDensity.Unit("gA", "gA", "grade-like areal density (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("gA2", "gA2", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
