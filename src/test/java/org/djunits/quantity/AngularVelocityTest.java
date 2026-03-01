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
 * AngularVelocityTest tests the AngularVelocity quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class AngularVelocityTest
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
    void testAngularVelocityBasics()
    {
        // Construct with unit
        AngularVelocity w0 = new AngularVelocity(0.0, AngularVelocity.Unit.rad_s);
        assertEquals(AngularVelocity.ZERO, w0);
        assertEquals(0.0, AngularVelocity.ZERO.si());

        AngularVelocity w1 = new AngularVelocity(1.0, AngularVelocity.Unit.rad_s);
        assertEquals(AngularVelocity.ONE, w1);
        assertEquals(1.0, AngularVelocity.ONE.si());

        // Constants sanity
        assertTrue(Double.isNaN(AngularVelocity.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, AngularVelocity.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, AngularVelocity.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, AngularVelocity.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, AngularVelocity.NEG_MAXVALUE.si());

        // Copy constructor preserves SI (and display unit of source)
        AngularVelocity degPerS = new AngularVelocity(180.0, AngularVelocity.Unit.deg_s);
        AngularVelocity copy = new AngularVelocity(degPerS);
        assertEquals(degPerS.si(), copy.si(), 1E-12);

        // Construct with abbreviation string
        AngularVelocity wDeg = new AngularVelocity(180.0, "deg/s");
        assertEquals(Math.PI, wDeg.si(), 1E-12);

        // Arcminute per second: 60'/s == 1 deg/s
        AngularVelocity wArcmin = new AngularVelocity(60.0, AngularVelocity.Unit.arcmin_s);
        assertEquals(Math.PI / 180.0, wArcmin.si(), 1E-12);

        // Arcsecond per second: 3600"/s == 1 deg/s
        AngularVelocity wArcsec = new AngularVelocity(3600.0, AngularVelocity.Unit.arcsec_s);
        assertEquals(Math.PI / 180.0, wArcsec.si(), 1E-12);

        // Gradian per second: 200 grad/s == π rad/s
        AngularVelocity wGrad = new AngularVelocity(200.0, AngularVelocity.Unit.grad_s);
        assertEquals(Math.PI, wGrad.si(), 1E-12);

        // Centesimal arcminute per second: 100 cdm/s == 1 grad/s
        AngularVelocity wCdm = new AngularVelocity(100.0, AngularVelocity.Unit.cdm_s);
        assertEquals(2.0 * Math.PI / 400.0, wCdm.si(), 1E-12);

        // Centesimal arcsecond per second: 10000 cds/s == 1 grad/s
        AngularVelocity wCds = new AngularVelocity(10000.0, AngularVelocity.Unit.cds_s);
        assertEquals(2.0 * Math.PI / 400.0, wCds.si(), 1E-12);

        // Parsing valueOf and of(value, unitString)
        AngularVelocity p1 = AngularVelocity.valueOf("180 deg/s");
        assertEquals(Math.PI, p1.si(), 1E-12);

        AngularVelocity p2 = AngularVelocity.valueOf("1 rad/s");
        assertEquals(1.0, p2.si(), 1E-12);

        AngularVelocity p3 = AngularVelocity.of(30.0, "deg/s");
        assertEquals(Math.PI / 6.0, p3.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, wDeg.instantiate(-10.1).si(), 1E-12);

        // siUnit (of the quantity) and textual representation
        assertEquals("rad/s", wDeg.siUnit().toString(true, false));

        // ofSi
        AngularVelocity neg = AngularVelocity.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test divide(AngularVelocity) -> Dimensionless.
     */
    @Test
    void testDivideDimensionless()
    {
        var d1 = AngularVelocity.ONE.divide(AngularVelocity.ONE.scaleBy(2.0));
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(0.5, d1.si(), 1E-12);
    }

    /**
     * Test the AngularVelocity.Unit behavior: base/si unit, deriving linear units, and exception branch for non-linear scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(AngularVelocity.Unit.SI_UNIT, AngularVelocity.Unit.rad_s.siUnit());
        assertEquals(AngularVelocity.Unit.SI, AngularVelocity.Unit.rad_s.getBaseUnit());

        // Unit.ofSi delegates to AngularVelocity.ofSi
        AngularVelocity fromUnit = AngularVelocity.Unit.rad_s.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (deg/s) -> should succeed
        AngularVelocity.Unit twoDegPerS =
                AngularVelocity.Unit.deg_s.deriveUnit("deg2/s", "deg2/s", "two degrees per second", 2.0, UnitSystem.OTHER);
        AngularVelocity x = new AngularVelocity(1.0, twoDegPerS); // 1 * 2 deg/s == 2 deg/s
        assertEquals(2.0 * Math.PI / 180.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        AngularVelocity.Unit nonLinear = new AngularVelocity.Unit("g%/s", "g%/s", "grade-like per second (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("g2%/s", "g2%/s", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
