package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * MomentumTest tests the Momentum quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class MomentumTest
{
    /** Standard locale for the tests. */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test constructors, constants, parsing, SI conversions, instantiate, siUnit, and ofSi.
     */
    @Test
    void testMomentumBasics()
    {
        // Construct with unit
        Momentum p0 = new Momentum(0.0, Momentum.Unit.kgm_s);
        assertEquals(Momentum.ZERO, p0);
        assertEquals(0.0, Momentum.ZERO.si(), 1E-12);

        Momentum p1 = new Momentum(1.0, Momentum.Unit.kgm_s);
        assertEquals(Momentum.ONE, p1);
        assertEquals(1.0, Momentum.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(Momentum.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Momentum.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Momentum.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Momentum.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Momentum.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Momentum two = new Momentum(2.0, Momentum.Unit.kgm_s);
        Momentum copy = new Momentum(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        Momentum fromStr = new Momentum(1.5, "kgm/s");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of
        Momentum pv1 = Momentum.valueOf("2 kgm/s");
        assertEquals(2.0, pv1.si(), 1E-12);

        Momentum pv2 = Momentum.of(500.0, "kgm/s");
        assertEquals(500.0, pv2.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation must match the SIUnit.of string used in Unit ("kgm/s")
        assertEquals("kgm/s", fromStr.siUnit().toString(true, false));

        // ofSi
        Momentum neg = Momentum.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, malformed number).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> Momentum.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> Momentum.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> Momentum.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> Momentum.valueOf("not-a-number kgm/s"));

        // of null unit
        assertThrows(NullPointerException.class, () -> Momentum.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> Momentum.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> Momentum.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testMomentumOperations()
    {
        // (kgm/s) / (kgm/s) -> Dimensionless
        var d1 = Momentum.ONE.divide(Momentum.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = Momentum.ofSi(1.0).divide(Momentum.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = Momentum.ofSi(1.0).divide(Momentum.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // (kgm/s) / (m/s) -> kg (Mass)
        var m = Momentum.ofSi(10.0).divide(Speed.ofSi(2.0));
        assertTrue(m instanceof Mass);
        assertEquals(5.0, m.si(), 1E-12);

        // (kgm/s) / kg -> m/s (Speed)
        var v = Momentum.ofSi(12.0).divide(Mass.ofSi(3.0));
        assertTrue(v instanceof Speed);
        assertEquals(4.0, v.si(), 1E-12);

        // (kgm/s) / m -> kg/s (FlowMass)
        var q = Momentum.ofSi(20.0).divide(Length.ofSi(4.0));
        assertTrue(q instanceof FlowMass);
        assertEquals(5.0, q.si(), 1E-12);

        // (kgm/s) / (kg/s) -> m (Length)
        var l = Momentum.ofSi(18.0).divide(FlowMass.ofSi(3.0));
        assertTrue(l instanceof Length);
        assertEquals(6.0, l.si(), 1E-12);

        // (kgm/s) * (m/s) -> kg m^2 / s^2 (Energy)
        var e = Momentum.ofSi(7.0).multiply(Speed.ofSi(2.0));
        assertTrue(e instanceof Energy);
        assertEquals(14.0, e.si(), 1E-12);

        // (kgm/s) * (m/s^2) -> kg m^2 / s^3 (Power)
        var pwr = Momentum.ofSi(9.0).multiply(Acceleration.ofSi(0.5));
        assertTrue(pwr instanceof Power);
        assertEquals(4.5, pwr.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units, and non-linear derivation exception
     * branch.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Momentum.Unit.SI_UNIT, Momentum.Unit.kgm_s.siUnit());
        assertEquals(Momentum.Unit.SI, Momentum.Unit.kgm_s.getBaseUnit());

        // Unit.ofSi delegates to Momentum.ofSi
        Momentum fromUnit = Momentum.Unit.kgm_s.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit -> e.g., "2kgm/s" (scale = 2.0)
        Momentum.Unit twoKgmPerS =
                Momentum.Unit.kgm_s.deriveUnit("2kgm/s", "2kgm/s", "two kilogram meter per second", 2.0, UnitSystem.OTHER);
        Momentum x = new Momentum(1.0, twoKgmPerS); // 1 * 2 kgm/s == 2 kgm/s
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Momentum.Unit nonLinear =
                new Momentum.Unit("gMom", "gMom", "grade-like momentum (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });

        // Registry sanity for base abbreviation
        Momentum.Unit resolved = Units.resolve(Momentum.Unit.class, "kgm/s");
        assertEquals(Momentum.Unit.kgm_s, resolved);
    }
}
