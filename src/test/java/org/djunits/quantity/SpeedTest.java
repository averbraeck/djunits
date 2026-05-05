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
 * SpeedTest tests the Speed quantity class.<p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
class SpeedTest
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
    void testSpeedBasics()
    {
        // Construct with unit
        Speed v0 = new Speed(0.0, Speed.Unit.m_s);
        assertEquals(Speed.ZERO, v0);
        assertEquals(0.0, Speed.ZERO.si(), 1E-12);

        Speed v1 = new Speed(1.0, Speed.Unit.m_s);
        assertEquals(Speed.ONE, v1);
        assertEquals(1.0, Speed.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(Speed.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Speed.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Speed.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Speed.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Speed.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Speed two = new Speed(2.0, Speed.Unit.m_s);
        Speed copy = new Speed(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        Speed fromStr = new Speed(1.5, "m/s");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of; valueOf is class-specific (NumberParser)
        Speed p1 = Speed.valueOf("2 m/s");
        assertEquals(2.0, p1.si(), 1E-12);

        Speed p2 = Speed.valueOf("  12.5   km/h  "); // trailing/lenient parsing
        assertEquals(12.5 * 1000.0 / 3600.0, p2.si(), 1E-12);

        Speed p3 = Speed.of(500.0, "km/s");
        assertEquals(500.0 * 1000.0, p3.si(), 1E-9);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiateSi(-10.1).si(), 1E-12);

        // siUnit textual representation must match SIUnit.of literal ("m/s")
        assertEquals("m/s", fromStr.siUnit().format(true, false));

        // ofSi
        Speed neg = Speed.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test valueOf/of error branches with the custom Throw/NumberParser logic.
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null -> NPE
        assertThrows(NullPointerException.class, () -> Speed.valueOf(null));

        // valueOf empty -> IAE
        assertThrows(IllegalArgumentException.class, () -> Speed.valueOf(""));

        // valueOf with unknown unit -> IAE
        assertThrows(IllegalArgumentException.class, () -> Speed.valueOf("10 blargh"));

        // valueOf with garbage number -> IAE (wrapped)
        assertThrows(IllegalArgumentException.class, () -> Speed.valueOf("not-a-number m/s"));

        // of null unit -> NPE
        assertThrows(NullPointerException.class, () -> Speed.of(1.0, null));

        // of empty unit -> IAE
        assertThrows(IllegalArgumentException.class, () -> Speed.of(1.0, ""));

        // of unknown unit -> IAE (Speed.of uses Throw.when for IAE)
        assertThrows(UnitRuntimeException.class, () -> Speed.of(1.0, "blargh"));
    }

    /**
     * Test unit conversions for a selection of defined units.
     */
    @Test
    void testUnitConversions()
    {
        // SI base unit
        assertEquals(Speed.Unit.SI_UNIT, Speed.Unit.m_s.siUnit());
        assertEquals(Speed.Unit.SI, Speed.Unit.m_s.getBaseUnit());

        // m/h
        assertEquals(1.0 / 3600.0, new Speed(1.0, Speed.Unit.m_h).si(), 1E-12);

        // km/s
        assertEquals(1000.0, new Speed(1.0, Speed.Unit.km_s).si(), 1E-9);

        // km/h
        assertEquals(1000.0 / 3600.0, new Speed(1.0, Speed.Unit.km_h).si(), 1E-12);

        // inch-based
        assertEquals(Length.Unit.CONST_IN, new Speed(1.0, Speed.Unit.in_s).si(), 1E-12);
        assertEquals(Length.Unit.CONST_IN / 60.0, new Speed(1.0, Speed.Unit.in_min).si(), 1E-12);
        assertEquals(Length.Unit.CONST_IN / 3600.0, new Speed(1.0, Speed.Unit.in_h).si(), 1E-12);

        // foot-based
        assertEquals(Length.Unit.CONST_FT, new Speed(1.0, Speed.Unit.ft_s).si(), 1E-12);
        assertEquals(Length.Unit.CONST_FT / 60.0, new Speed(1.0, Speed.Unit.ft_min).si(), 1E-12);
        assertEquals(Length.Unit.CONST_FT / 3600.0, new Speed(1.0, Speed.Unit.ft_h).si(), 1E-12);

        // mile-based
        assertEquals(Length.Unit.CONST_MI, new Speed(1.0, Speed.Unit.mi_s).si(), 1E-6);
        assertEquals(Length.Unit.CONST_MI / 60.0, new Speed(1.0, Speed.Unit.mi_min).si(), 1E-6);
        assertEquals(Length.Unit.CONST_MI / 3600.0, new Speed(1.0, Speed.Unit.mi_h).si(), 1E-9);

        // knot (NM/h)
        assertEquals(Length.Unit.CONST_NM / 3600.0, new Speed(1.0, Speed.Unit.kt).si(), 1E-12);

        // Registry sanity
        assertEquals(Speed.Unit.m_s, Units.resolve(Speed.Unit.class, "m/s"));
        assertEquals(Speed.Unit.km_h, Units.resolve(Speed.Unit.class, "km/h"));
        assertEquals(Speed.Unit.mi_h, Units.resolve(Speed.Unit.class, "mi/h"));
        assertEquals(Speed.Unit.kt, Units.resolve(Speed.Unit.class, "kt"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    @SuppressWarnings("checkstyle:localvariablename")
    void testSpeedOperations()
    {
        // (m/s) / (m/s) -> Dimensionless
        var d1 = Speed.ONE.divide(Speed.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = Speed.ofSi(1.0).divide(Speed.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = Speed.ofSi(1.0).divide(Speed.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // v * A -> Qv (FlowVolume)
        var qv = Speed.ofSi(3.0).multiply(Area.ofSi(4.0));
        assertTrue(qv instanceof FlowVolume);
        assertEquals(12.0, qv.si(), 1E-12);

        // v * F -> P (Power)
        var pwr = Speed.ofSi(5.0).multiply(Force.ofSi(2.0));
        assertTrue(pwr instanceof Power);
        assertEquals(10.0, pwr.si(), 1E-12);

        // v * f -> a (Acceleration)
        var a1 = Speed.ofSi(6.0).multiply(Frequency.ofSi(2.0));
        assertTrue(a1 instanceof Acceleration);
        assertEquals(12.0, a1.si(), 1E-12);

        // v / L -> f (Frequency)
        var f1 = Speed.ofSi(10.0).divide(Length.ofSi(2.0));
        assertTrue(f1 instanceof Frequency);
        assertEquals(5.0, f1.si(), 1E-12);

        // v / f -> L (Length)
        var L1 = Speed.ofSi(8.0).divide(Frequency.ofSi(4.0));
        assertTrue(L1 instanceof Length);
        assertEquals(2.0, L1.si(), 1E-12);

        // v * LOD -> f (Frequency)
        var f2 = Speed.ofSi(7.0).multiply(LinearObjectDensity.ofSi(0.5));
        assertTrue(f2 instanceof Frequency);
        assertEquals(3.5, f2.si(), 1E-12);

        // v * t -> L (Length)
        var L2 = Speed.ofSi(9.0).multiply(Duration.ofSi(3.0));
        assertTrue(L2 instanceof Length);
        assertEquals(27.0, L2.si(), 1E-12);

        // v / t -> a (Acceleration)
        var a2 = Speed.ofSi(12.0).divide(Duration.ofSi(4.0));
        assertTrue(a2 instanceof Acceleration);
        assertEquals(3.0, a2.si(), 1E-12);

        // v / a -> t (Duration)
        var t1 = Speed.ofSi(15.0).divide(Acceleration.ofSi(5.0));
        assertTrue(t1 instanceof Duration);
        assertEquals(3.0, t1.si(), 1E-12);

        // v * ṁ -> F (Force)
        var F1 = Speed.ofSi(2.0).multiply(FlowMass.ofSi(3.0));
        assertTrue(F1 instanceof Force);
        assertEquals(6.0, F1.si(), 1E-12);

        // v * m -> p (Momentum)
        var p1 = Speed.ofSi(4.0).multiply(Mass.ofSi(2.0));
        assertTrue(p1 instanceof Momentum);
        assertEquals(8.0, p1.si(), 1E-12);

        // v * p -> E (Energy)
        var e1 = Speed.ofSi(5.0).multiply(Momentum.ofSi(6.0));
        assertTrue(e1 instanceof Energy);
        assertEquals(30.0, e1.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units, non-linear derivation exception
     * branch.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Speed.Unit.SI_UNIT, Speed.Unit.m_s.siUnit());
        assertEquals(Speed.Unit.SI, Speed.Unit.m_s.getBaseUnit());

        // Unit.ofSi delegates to Speed.ofSi
        Speed fromUnit = Speed.Unit.m_s.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from linear unit (m/s) -> "2m/s" (scale 2.0)
        Speed.Unit twoMs = Speed.Unit.m_s.deriveUnit("2m/s", "2m/s", "two meter per second", 2.0, UnitSystem.OTHER);
        Speed x = new Speed(1.0, twoMs); // 1 * 2 m/s == 2 m/s
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Speed.Unit nonLinear =
                new Speed.Unit("gSpeed", "gSpeed", "grade-like speed (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
