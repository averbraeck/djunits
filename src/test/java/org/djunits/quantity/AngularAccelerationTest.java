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
 * AngularAccelerationTest tests the AngularAcceleration quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class AngularAccelerationTest
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
     * Test the basic features: constructors, constants, parsing, SI conversions, instantiate, siUnit, and ofSi.
     */
    @Test
    void testAngularAccelerationBasics()
    {
        // Construct with unit
        AngularAcceleration a0 = new AngularAcceleration(0.0, AngularAcceleration.Unit.rad_s2);
        assertEquals(AngularAcceleration.ZERO, a0);
        assertEquals(0.0, AngularAcceleration.ZERO.si());

        AngularAcceleration a1 = new AngularAcceleration(1.0, AngularAcceleration.Unit.rad_s2);
        assertEquals(AngularAcceleration.ONE, a1);
        assertEquals(1.0, AngularAcceleration.ONE.si());

        // Constants sanity
        assertTrue(Double.isNaN(AngularAcceleration.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, AngularAcceleration.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, AngularAcceleration.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, AngularAcceleration.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, AngularAcceleration.NEG_MAXVALUE.si());

        // Copy constructor: SI value and display unit
        AngularAcceleration degAA = new AngularAcceleration(180.0, AngularAcceleration.Unit.deg_s2);
        AngularAcceleration copy = new AngularAcceleration(degAA);
        assertEquals(degAA.si(), copy.si(), 1E-12);

        // Construct with abbreviation string
        AngularAcceleration aDeg = new AngularAcceleration(180.0, "deg/s2");
        assertEquals(Math.PI, aDeg.si(), 1E-12);

        // Unicode degree sign for display abbreviation via of(value, unitString)
        AngularAcceleration aDegSymbol = AngularAcceleration.of(180.0, "deg/s2");
        assertEquals(Math.PI, aDegSymbol.si(), 1E-12);

        // Arcminute per s^2 (60'/s^2 == 1 deg/s^2)
        AngularAcceleration aArcmin = new AngularAcceleration(60.0, AngularAcceleration.Unit.arcmin_s2);
        assertEquals(Math.PI / 180.0, aArcmin.si(), 1E-12);

        // Arcsecond per s^2 (3600"/s^2 == 1 deg/s^2)
        AngularAcceleration aArcsec = new AngularAcceleration(3600.0, AngularAcceleration.Unit.arcsec_s2);
        assertEquals(Math.PI / 180.0, aArcsec.si(), 1E-12);

        // Gradian per s^2 (200 grad/s^2 == π rad/s^2)
        AngularAcceleration aGrad = new AngularAcceleration(200.0, AngularAcceleration.Unit.grad_s2);
        assertEquals(Math.PI, aGrad.si(), 1E-12);

        // Centesimal arcminute per s^2 (100 cdm/s^2 == 1 grad/s^2)
        AngularAcceleration aCdm = new AngularAcceleration(100.0, AngularAcceleration.Unit.cdm_s2);
        assertEquals(2.0 * Math.PI / 400.0, aCdm.si(), 1E-12);

        // Centesimal arcsecond per s^2 (10000 cds/s^2 == 1 grad/s^2)
        AngularAcceleration aCds = new AngularAcceleration(10000.0, AngularAcceleration.Unit.cds_s2);
        assertEquals(2.0 * Math.PI / 400.0, aCds.si(), 1E-12);

        // Parsing valueOf and of(value, unitString)
        AngularAcceleration p1 = AngularAcceleration.valueOf("180 deg/s2");
        assertEquals(Math.PI, p1.si(), 1E-12);

        AngularAcceleration p2 = AngularAcceleration.valueOf("1 rad/s2");
        assertEquals(1.0, p2.si(), 1E-12);

        AngularAcceleration p3 = AngularAcceleration.of(30.0, "deg/s2");
        assertEquals(Math.PI / 6.0, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, aDeg.instantiate(-10.1).si(), 1E-12);

        // siUnit (of the quantity) and textual representation
        assertEquals("rad/s2", aDeg.siUnit().toString(true, false));

        // ofSi
        AngularAcceleration neg = AngularAcceleration.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test multiply and divide methods specific to AngularAcceleration.
     */
    @Test
    void testMultiplyDivide()
    {
        // Divide AngularAcceleration by AngularAcceleration -> Dimensionless
        var d1 = AngularAcceleration.ONE.divide(AngularAcceleration.ONE.scaleBy(2.0));
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(0.5, d1.si(), 1E-12);

        // AngularAcceleration * Duration -> AngularVelocity
        var w2 = AngularAcceleration.ONE.scaleBy(2.0).multiply(Duration.ONE.scaleBy(3.0));
        assertTrue(w2 instanceof AngularVelocity);
        assertEquals(6.0, w2.si(), 1E-12);

        // AngularAcceleration / Frequency -> AngularVelocity
        var w3 = AngularAcceleration.ONE.scaleBy(3.0).divide(Frequency.ONE.scaleBy(2.0));
        assertTrue(w3 instanceof AngularVelocity);
        assertEquals(1.5, w3.si(), 1E-12);

        // AngularAcceleration / AngularVelocity -> Frequency
        var f4 = AngularAcceleration.ONE.scaleBy(6.0).divide(AngularVelocity.ONE.scaleBy(2.0));
        assertTrue(f4 instanceof Frequency);
        assertEquals(3.0, f4.si(), 1E-12);
    }

    /**
     * Test the AngularAcceleration.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear
     * scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(AngularAcceleration.Unit.SI_UNIT, AngularAcceleration.Unit.rad_s2.siUnit());
        assertEquals(AngularAcceleration.Unit.SI, AngularAcceleration.Unit.rad_s2.getBaseUnit());

        // Unit.ofSi delegates to AngularAcceleration.ofSi
        AngularAcceleration fromUnit = AngularAcceleration.Unit.rad_s2.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (deg/s2) -> should succeed
        AngularAcceleration.Unit twoDegS2 = AngularAcceleration.Unit.deg_s2.deriveUnit("deg2/s2", "deg2/s2",
                "two degrees per second squared", 2.0, UnitSystem.OTHER);
        AngularAcceleration x = new AngularAcceleration(1.0, twoDegS2); // 1 * 2 deg/s2 == 2 deg/s2
        assertEquals(2.0 * Math.PI / 180.0, x.si(), 1E-12);

        // Derive from a non-linear scale -> should throw UnitRuntimeException
        AngularAcceleration.Unit nonLinear =
                new AngularAcceleration.Unit("g%", "g%", "grade-like (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2%", "g2%", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
