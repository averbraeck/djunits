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
 * FrequencyTest tests the Frequency quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class FrequencyTest
{
    /**
     * Standard locale for the tests.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test constructors, constants, parsing, SI conversions, instantiate, siUnit, and ofSi.
     */
    @Test
    void testFrequencyBasics()
    {
        // Construct with unit
        Frequency f0 = new Frequency(0.0, Frequency.Unit.Hz);
        assertEquals(Frequency.ZERO, f0);
        assertEquals(0.0, Frequency.ZERO.si(), 1E-12);

        Frequency f1 = new Frequency(1.0, Frequency.Unit.Hz);
        assertEquals(Frequency.ONE, f1);
        assertEquals(1.0, Frequency.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(Frequency.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Frequency.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Frequency.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Frequency.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Frequency.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Frequency kilo = new Frequency(2.0, Frequency.Unit.kHz); // 2 kHz = 2000 Hz
        Frequency copy = new Frequency(kilo);
        assertEquals(kilo.si(), copy.si(), 1E-9);
        assertEquals(kilo.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        Frequency fStr = new Frequency(1.5, "Hz");
        assertEquals(1.5, fStr.si(), 1E-12);

        // SI prefixes via resolved units
        assertEquals(1E3, new Frequency(1.0, Frequency.Unit.kHz).si(), 1E-9);
        assertEquals(1E6, new Frequency(1.0, Frequency.Unit.MHz).si(), 1E-6);
        assertEquals(1E9, new Frequency(1.0, Frequency.Unit.GHz).si(), 1E-3);
        assertEquals(1E12, new Frequency(1.0, Frequency.Unit.THz).si(), 1E3);

        // rpm: 1 rpm = 1/60 Hz
        assertEquals(1.0 / 60.0, new Frequency(1.0, Frequency.Unit.rpm).si(), 1E-12);

        // Parsing valueOf and of(value, unitString)
        Frequency p1 = Frequency.valueOf("2 Hz");
        assertEquals(2.0, p1.si(), 1E-12);

        Frequency p2 = Frequency.valueOf("3 kHz"); // 3000 Hz
        assertEquals(3000.0, p2.si(), 1E-9);

        Frequency p3 = Frequency.of(500.0, "MHz"); // 5e8 Hz
        assertEquals(5e8, p3.si(), 1E-3);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation for frequency
        assertEquals("1/s", fStr.siUnit().toString(true, false));

        // ofSi
        Frequency neg = Frequency.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, and general parse error surface).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> Frequency.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> Frequency.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> Frequency.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> Frequency.valueOf("not-a-number Hz"));

        // of null unit
        assertThrows(NullPointerException.class, () -> Frequency.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> Frequency.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> Frequency.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testFrequencyOperations()
    {
        // Divide by Frequency -> Dimensionless
        var d1 = Frequency.ONE.divide(Frequency.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = Frequency.ofSi(1.0).divide(Frequency.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Frequency * Duration -> Dimensionless
        var dim = Frequency.ofSi(2.0).multiply(Duration.ofSi(3.0));
        assertTrue(dim instanceof Dimensionless);
        assertEquals(6.0, dim.si(), 1E-12);

        // Frequency * Length -> Speed
        var v = Frequency.ofSi(2.0).multiply(Length.ofSi(3.0));
        assertTrue(v instanceof Speed);
        assertEquals(6.0, v.si(), 1E-12);

        // Frequency * Speed -> Acceleration
        var a = Frequency.ofSi(2.0).multiply(Speed.ofSi(3.0));
        assertTrue(a instanceof Acceleration);
        assertEquals(6.0, a.si(), 1E-12);

        // Frequency * Energy -> Power
        var p = Frequency.ofSi(2.0).multiply(Energy.ofSi(3.0));
        assertTrue(p instanceof Power);
        assertEquals(6.0, p.si(), 1E-12);

        // Frequency * Angle -> AngularVelocity
        var w = Frequency.ofSi(2.0).multiply(Angle.ofSi(3.0));
        assertTrue(w instanceof AngularVelocity);
        assertEquals(6.0, w.si(), 1E-12);

        // Frequency * AngularVelocity -> AngularAcceleration
        var alpha = Frequency.ofSi(2.0).multiply(AngularVelocity.ofSi(3.0));
        assertTrue(alpha instanceof AngularAcceleration);
        assertEquals(6.0, alpha.si(), 1E-12);

        // reciprocal -> Duration
        var tau = Frequency.ofSi(4.0).reciprocal();
        assertTrue(tau instanceof Duration);
        assertEquals(0.25, tau.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Frequency.Unit.SI_UNIT, Frequency.Unit.Hz.siUnit());
        assertEquals(Frequency.Unit.SI, Frequency.Unit.Hz.getBaseUnit());

        // Unit.ofSi delegates to Frequency.ofSi
        Frequency fromUnit = Frequency.Unit.Hz.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (Hz) -> should succeed
        Frequency.Unit twoHz = Frequency.Unit.Hz.deriveUnit("2Hz", "2Hz", "two hertz", 2.0, UnitSystem.OTHER);
        Frequency x = new Frequency(1.0, twoHz); // 1 * 2 Hz == 2 Hz
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Frequency.Unit nonLinear =
                new Frequency.Unit("gHz", "gHz", "grade-like hertz (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2Hz", "g2Hz", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
