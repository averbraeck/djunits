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
 * ForceTest tests the Force quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class ForceTest
{
    /**
     * Setup uniform locale before the tests.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test the basic features: constructors, constants, parsing, SI conversions, instantiate, siUnit, and ofSi.
     */
    @Test
    void testForceBasics()
    {
        // Construct with unit
        Force f0 = new Force(0.0, Force.Unit.N);
        assertEquals(Force.ZERO, f0);
        assertEquals(0.0, Force.ZERO.si(), 1E-12);

        Force f1 = new Force(1.0, Force.Unit.N);
        assertEquals(Force.ONE, f1);
        assertEquals(1.0, Force.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(Force.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Force.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Force.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Force.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Force.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Force kilo = new Force(2.0, "kN"); // 2 kN = 2000 N
        Force copy = new Force(kilo);
        assertEquals(kilo.si(), copy.si(), 1E-9);
        assertEquals(kilo.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        Force fStr = new Force(1.5, "N");
        assertEquals(1.5, fStr.si(), 1E-12);

        // SI prefixes via generated units (resolved by abbreviation)
        assertEquals(1E-3, new Force(1.0, "mN").si(), 1E-12); // milli-newton
        assertEquals(1E3, new Force(1.0, "kN").si(), 1E-9); // kilo-newton
        assertEquals(1E6, new Force(1.0, "MN").si(), 1E-6); // mega-newton

        // Special units
        // 1 dyn = 1e-5 N
        assertEquals(1E-5, new Force(1.0, Force.Unit.dyn).si(), 1E-12);
        // 1 kgf = g N
        assertEquals(Acceleration.Unit.CONST_GRAVITY, new Force(1.0, Force.Unit.kgf).si(), 1E-9);
        // 1 ozf = ounce * g N
        assertEquals(Mass.Unit.CONST_OUNCE * Acceleration.Unit.CONST_GRAVITY, new Force(1.0, Force.Unit.ozf).si(), 1E-9);
        // 1 lbf = lb * g N
        assertEquals(Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY, new Force(1.0, Force.Unit.lbf).si(), 1E-9);
        // 1 tnf = ton(short) * g N ; ratio to lbf is 2000
        double tnfSi = new Force(1.0, Force.Unit.tnf).si();
        double lbfSi = new Force(1.0, Force.Unit.lbf).si();
        assertEquals(2000.0, tnfSi / lbfSi, 1E-12);
        // 1 sn = 1000 N
        assertEquals(1000.0, new Force(1.0, Force.Unit.sn).si(), 1E-9);

        // Parsing valueOf and of(value, unitString)
        Force p1 = Force.valueOf("2 N");
        assertEquals(2.0, p1.si(), 1E-12);

        Force p2 = Force.valueOf("2 kN"); // 2000 N
        assertEquals(2000.0, p2.si(), 1E-9);

        Force p3 = Force.of(500.0, "mN"); // 0.5 N
        assertEquals(0.5, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, fStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (dimension string)
        assertEquals("kgm/s2", fStr.siUnit().toString(true, false));

        // ofSi
        Force neg = Force.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic operations with related quantities.
     */
    @Test
    void testForceOperations()
    {
        // Divide by Force -> Dimensionless
        var d1 = Force.ONE.divide(Force.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = Force.ofSi(1.0).divide(Force.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Force * Length -> Energy
        var e1 = Force.ofSi(2.0).multiply(Length.ofSi(3.0));
        assertTrue(e1 instanceof Energy);
        assertEquals(6.0, e1.si(), 1E-12);

        // Force / LinearObjectDensity -> Energy
        var e2 = Force.ofSi(6.0).divide(LinearObjectDensity.ofSi(2.0));
        assertTrue(e2 instanceof Energy);
        assertEquals(3.0, e2.si(), 1E-12);

        // Force / Energy -> LinearObjectDensity
        var lod = Force.ofSi(6.0).divide(Energy.ofSi(2.0));
        assertTrue(lod instanceof LinearObjectDensity);
        assertEquals(3.0, lod.si(), 1E-12);

        // Force * Speed -> Power
        var p1 = Force.ofSi(2.0).multiply(Speed.ofSi(3.0));
        assertTrue(p1 instanceof Power);
        assertEquals(6.0, p1.si(), 1E-12);

        // Force / Mass -> Acceleration
        var a1 = Force.ofSi(6.0).divide(Mass.ofSi(2.0));
        assertTrue(a1 instanceof Acceleration);
        assertEquals(3.0, a1.si(), 1E-12);

        // Force / Acceleration -> Mass
        var m1 = Force.ofSi(6.0).divide(Acceleration.ofSi(2.0));
        assertTrue(m1 instanceof Mass);
        assertEquals(3.0, m1.si(), 1E-12);

        // Force / Area -> Pressure
        var pr1 = Force.ofSi(6.0).divide(Area.ofSi(2.0));
        assertTrue(pr1 instanceof Pressure);
        assertEquals(3.0, pr1.si(), 1E-12);

        // Force / Pressure -> Area
        var ar1 = Force.ofSi(6.0).divide(Pressure.ofSi(2.0));
        assertTrue(ar1 instanceof Area);
        assertEquals(3.0, ar1.si(), 1E-12);
    }

    /**
     * Test the Force.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Force.Unit.SI_UNIT, Force.Unit.N.siUnit());
        assertEquals(Force.Unit.SI, Force.Unit.N.getBaseUnit());

        // Unit.ofSi delegates to Force.ofSi
        Force fromUnit = Force.Unit.N.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (N) -> should succeed
        Force.Unit twoN = Force.Unit.N.deriveUnit("2N", "2N", "two newton", 2.0, UnitSystem.OTHER);
        Force x = new Force(1.0, twoN); // 1 * 2 N == 2 N
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Force.Unit nonLinear =
                new Force.Unit("gN", "gN", "grade-like newton (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2N", "g2N", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
