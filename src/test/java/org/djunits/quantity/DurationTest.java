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
 * DurationTest tests the Duration quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class DurationTest
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
    void testDurationBasics()
    {
        // Construct with unit
        Duration d0 = new Duration(0.0, Duration.Unit.s);
        assertEquals(Duration.ZERO, d0);
        assertEquals(0.0, Duration.ZERO.si());

        Duration d1 = new Duration(1.0, Duration.Unit.s);
        assertEquals(Duration.ONE, d1);
        assertEquals(1.0, Duration.ONE.si());

        // Constants sanity
        assertTrue(Double.isNaN(Duration.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Duration.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Duration.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Duration.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Duration.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Duration twoMin = new Duration(2.0, Duration.Unit.min);
        Duration copy = new Duration(twoMin);
        assertEquals(twoMin.si(), copy.si(), 1E-12);

        // Construct with abbreviation string
        Duration dStr = new Duration(1.5, "s");
        assertEquals(1.5, dStr.si(), 1E-12);

        // SI prefixes via generated units
        assertEquals(1E-12, new Duration(1.0, Duration.Unit.ps).si(), 1E-24);
        assertEquals(1E-9, new Duration(1.0, Duration.Unit.ns).si(), 1E-21);
        assertEquals(1E-6, new Duration(1.0, Duration.Unit.mus).si(), 1E-18);
        assertEquals(1E-3, new Duration(1.0, Duration.Unit.ms).si(), 1E-15);

        // Accepted / other units
        assertEquals(60.0, new Duration(1.0, Duration.Unit.min).si(), 1E-12);
        assertEquals(3600.0, new Duration(1.0, Duration.Unit.h).si(), 1E-12);
        assertEquals(86400.0, new Duration(1.0, Duration.Unit.day).si(), 1E-9);
        assertEquals(604800.0, new Duration(1.0, Duration.Unit.wk).si(), 1E-6);

        // Parsing valueOf and of(value, unitString)
        Duration p1 = Duration.valueOf("90 s");
        assertEquals(90.0, p1.si(), 1E-12);

        Duration p2 = Duration.valueOf("2 h");
        assertEquals(7200.0, p2.si(), 1E-9);

        Duration p3 = Duration.of(500.0, "ms");
        assertEquals(0.5, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, dStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation
        assertEquals("s", dStr.siUnit().toString(true, false));

        // ofSi
        Duration neg = Duration.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test arithmetic (divide, multiply, reciprocal) and add(Time) behavior.
     */
    @Test
    void testDurationOperations()
    {
        // Divide by Duration -> Dimensionless
        var r1 = Duration.ONE.divide(Duration.ONE.scaleBy(2.0));
        assertTrue(r1 instanceof Dimensionless);
        assertEquals(0.5, r1.si(), 1E-12);

        // Add(Time) -> Time; display unit propagation to result
        Time t0 = Time.ofSi(100.0, Time.Reference.UNIX); // absolute instant at 100 s
        Duration dur = new Duration(30.0, Duration.Unit.s); // 30 s
        Time t1 = dur.add(t0); // time + duration
        assertTrue(t1 instanceof Time);
        assertEquals(130.0, t1.si(), 1E-12);
        assertEquals(dur.getDisplayUnit(), t1.getDisplayUnit());

        // Multiplications
        // Duration * ElectricCurrent -> ElectricCharge (Coulomb)
        var q = Duration.ofSi(2.0).multiply(ElectricCurrent.ofSi(3.0));
        assertTrue(q instanceof ElectricCharge);
        assertEquals(6.0, q.si(), 1E-12);

        // Duration * FlowMass -> Mass
        var m = Duration.ofSi(2.0).multiply(FlowMass.ofSi(3.0));
        assertTrue(m instanceof Mass);
        assertEquals(6.0, m.si(), 1E-12);

        // Duration * FlowVolume -> Volume
        var vol = Duration.ofSi(2.0).multiply(FlowVolume.ofSi(3.0));
        assertTrue(vol instanceof Volume);
        assertEquals(6.0, vol.si(), 1E-12);

        // Duration * Acceleration -> Speed
        var v = Duration.ofSi(2.0).multiply(Acceleration.ofSi(3.0));
        assertTrue(v instanceof Speed);
        assertEquals(6.0, v.si(), 1E-12);

        // Duration * Power -> Energy
        var e = Duration.ofSi(2.0).multiply(Power.ofSi(3.0));
        assertTrue(e instanceof Energy);
        assertEquals(6.0, e.si(), 1E-12);

        // Duration * Speed -> Length
        var l = Duration.ofSi(2.0).multiply(Speed.ofSi(3.0));
        assertTrue(l instanceof Length);
        assertEquals(6.0, l.si(), 1E-12);

        // Duration * ElectricPotential -> MagneticFlux
        var phi = Duration.ofSi(2.0).multiply(ElectricPotential.ofSi(3.0));
        assertTrue(phi instanceof MagneticFlux);
        assertEquals(6.0, phi.si(), 1E-12);

        // Duration * ElectricalResistance -> ElectricalInductance
        var ind = Duration.ofSi(2.0).multiply(ElectricalResistance.ofSi(3.0));
        assertTrue(ind instanceof ElectricalInductance);
        assertEquals(6.0, ind.si(), 1E-12);

        // Duration * ElectricalConductance -> ElectricalCapacitance
        var cap = Duration.ofSi(2.0).multiply(ElectricalConductance.ofSi(3.0));
        assertTrue(cap instanceof ElectricalCapacitance);
        assertEquals(6.0, cap.si(), 1E-12);

        // Duration * AngularVelocity -> Angle
        var ang = Duration.ofSi(2.0).multiply(AngularVelocity.ofSi(3.0));
        assertTrue(ang instanceof Angle);
        assertEquals(6.0, ang.si(), 1E-12);

        // Duration * AngularAcceleration -> AngularVelocity
        var w = Duration.ofSi(2.0).multiply(AngularAcceleration.ofSi(3.0));
        assertTrue(w instanceof AngularVelocity);
        assertEquals(6.0, w.si(), 1E-12);

        // reciprocal -> Frequency
        var f = Duration.ofSi(4.0).reciprocal();
        assertTrue(f instanceof Frequency);
        assertEquals(0.25, f.si(), 1E-12);
    }

    /**
     * Test the Duration.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Duration.Unit.SI_UNIT, Duration.Unit.s.siUnit());
        assertEquals(Duration.Unit.SI, Duration.Unit.s.getBaseUnit());

        // Unit.ofSi delegates to Duration.ofSi
        Duration fromUnit = Duration.Unit.s.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (s) -> should succeed
        Duration.Unit twoS = Duration.Unit.s.deriveUnit("2s", "2s", "two seconds", 2.0, UnitSystem.OTHER);
        Duration x = new Duration(1.0, twoS); // 1 * 2 s == 2 s
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Duration.Unit nonLinear =
                new Duration.Unit("gs", "gs", "grade-like second (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2s", "g2s", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
