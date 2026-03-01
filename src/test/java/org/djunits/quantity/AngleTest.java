package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.Direction.Reference;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * AngleTest tests the Angle quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
class AngleTest
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
     * Test the basic features: constructors, constants, parsing, SI conversions, instantiate, and siUnit.
     */
    @Test
    void testAngleBasics()
    {
        // Construct with unit
        Angle a0 = new Angle(0.0, Angle.Unit.rad);
        assertEquals(Angle.ZERO, a0);
        assertEquals(0.0, Angle.ZERO.si());

        Angle a1 = new Angle(1.0, Angle.Unit.rad);
        assertEquals(Angle.ONE, a1);
        assertEquals(1.0, Angle.ONE.si());

        // Constants
        assertEquals(Math.PI, Angle.PI.si(), 1E-12);
        assertEquals(Math.PI / 2.0, Angle.HALF_PI.si(), 1E-12);
        assertEquals(2.0 * Math.PI, Angle.TWO_PI.si(), 1E-12);
        assertEquals(2.0 * Math.PI, Angle.TAU.si(), 1E-12); // tau == 2π

        assertTrue(Double.isNaN(Angle.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Angle.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Angle.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Angle.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Angle.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Angle deg90 = new Angle(90.0, Angle.Unit.deg);
        Angle copy = new Angle(deg90);
        assertEquals(deg90.si(), copy.si(), 1E-12);
        // display unit equality is handled by Quantity base class; SI is sufficient

        // Construct with abbreviation
        Angle aDeg = new Angle(180.0, "deg");
        assertEquals(Math.PI, aDeg.si(), 1E-12);

        // Unicode degree sign as display abbreviation should also parse via Units.resolve used by constructor/of
        Angle aDegSymbol = Angle.of(180.0, "deg");
        assertEquals(Math.PI, aDegSymbol.si(), 1E-12);

        // Arcminute (60' == 1°)
        Angle aArcmin = new Angle(60.0, Angle.Unit.arcmin);
        assertEquals(Math.PI / 180.0, aArcmin.si(), 1E-12);

        // Arcsecond (3600" == 1°)
        Angle aArcsec = new Angle(3600.0, Angle.Unit.arcsec);
        assertEquals(Math.PI / 180.0, aArcsec.si(), 1E-12);

        // Gradians (200 grad == π)
        Angle aGrad = new Angle(200.0, Angle.Unit.grad);
        assertEquals(Math.PI, aGrad.si(), 1E-12);

        // Percent grade: 100% slope equals 45° -> π/4
        Angle aPercent = new Angle(100.0, Angle.Unit.percent);
        assertEquals(Math.PI / 4.0, aPercent.si(), 1E-12);

        // Parsing valueOf (with textual unit) and of(value, unitString)
        Angle p1 = Angle.valueOf("180 deg");
        assertEquals(Math.PI, p1.si(), 1E-12);

        Angle p2 = Angle.valueOf("100 %");
        assertEquals(Math.PI / 4.0, p2.si(), 1E-12);

        Angle p3 = Angle.of(30.0, "deg");
        assertEquals(Math.PI / 6.0, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, aDeg.instantiate(-10.1).si(), 1E-12);

        // siUnit and its textual representation
        assertEquals("rad", aDeg.siUnit().toString(true, false));

        // ofSi
        Angle neg = Angle.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test normalize for both double and Angle overloads.
     */
    @Test
    void testNormalize()
    {
        // Value > 2π
        double fivePi = 5.0 * Math.PI; // normalize -> π
        assertEquals(Math.PI, Angle.normalize(fivePi), 1E-12);

        // Negative value
        double minusHalfPi = -Math.PI / 2.0; // normalize -> 3π/2
        assertEquals(1.5 * Math.PI, Angle.normalize(minusHalfPi), 1E-12);

        // Zero and exactly 2π
        assertEquals(0.0, Angle.normalize(0.0), 1E-12);
        assertEquals(0.0, Angle.normalize(2.0 * Math.PI), 1E-12);

        // Overload with Angle
        Angle a = new Angle(5.0 * Math.PI, Angle.Unit.rad);
        Angle n = Angle.normalize(a);
        assertEquals(Math.PI, n.si(), 1E-12);

        Angle b = new Angle(-Math.PI / 2.0, Angle.Unit.rad);
        Angle m = Angle.normalize(b);
        assertEquals(1.5 * Math.PI, m.si(), 1E-12);
        assertEquals("rad", m.siUnit().toString(true, false));
    }

    /**
     * Test multiply and divide methods specific to Angle.
     */
    @Test
    void testMultiplyDivide()
    {
        // Divide Angle by Angle -> Dimensionless
        var d1 = Angle.ONE.divide(Angle.ONE.scaleBy(2.0));
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(0.5, d1.si(), 1E-12);

        // Angle * Frequency -> AngularVelocity
        var w2 = Angle.ONE.scaleBy(2.0).multiply(Frequency.ONE.scaleBy(3.0));
        assertTrue(w2 instanceof AngularVelocity);
        assertEquals(6.0, w2.si(), 1E-12);

        // Angle / Duration -> AngularVelocity
        var w3 = Angle.ONE.scaleBy(3.0).divide(Duration.ONE.scaleBy(2.0));
        assertTrue(w3 instanceof AngularVelocity);
        assertEquals(1.5, w3.si(), 1E-12);

        // Angle / AngularVelocity -> Duration
        var t4 = Angle.ONE.scaleBy(6.0).divide(AngularVelocity.ONE.scaleBy(2.0));
        assertTrue(t4 instanceof Duration);
        assertEquals(3.0, t4.si(), 1E-12);
    }

    /**
     * Test add(Direction): result SI equals direction + angle; display unit propagation is indirect in Direction, so we
     * validate SI arithmetic here.
     */
    @Test
    void testAddDirection()
    {
        var a = new Angle(30.0, Angle.Unit.deg); // π/6
        var dir = new Direction(Math.PI / 2.0, Angle.Unit.rad, Reference.EAST); // 90°
        var out = a.add(dir);
        assertTrue(out instanceof Direction);
        assertEquals(Math.PI / 2.0 + Math.PI / 6.0, out.si(), 1E-12);
    }

    /**
     * Test the Angle.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Angle.Unit.SI_UNIT, Angle.Unit.rad.siUnit());
        assertEquals(Angle.Unit.SI, Angle.Unit.rad.getBaseUnit());

        // Unit.ofSi delegates to Angle.ofSi
        Angle fromUnit = Angle.Unit.rad.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (deg) -> should succeed
        Angle.Unit twoDeg = Angle.Unit.deg.deriveUnit("deg2", "deg2", "two degrees", 2.0, UnitSystem.OTHER);
        Angle x = new Angle(1.0, twoDeg); // 1 * 2 deg == 2 deg
        assertEquals(2.0 * Math.PI / 180.0, x.si(), 1E-12);

        // Derive from a non-linear unit (percent with GradeScale) -> should throw UnitRuntimeException
        assertThrows(UnitRuntimeException.class, () ->
        { Angle.Unit.percent.deriveUnit("p2", "p2", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
