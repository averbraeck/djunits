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
 * SolidAngleTest tests the SolidAngle quantity class.<p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
class SolidAngleTest
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
    void testSolidAngleBasics()
    {
        // Construct with unit
        SolidAngle w0 = new SolidAngle(0.0, SolidAngle.Unit.sr);
        assertEquals(SolidAngle.ZERO, w0);
        assertEquals(0.0, SolidAngle.ZERO.si(), 1E-12);

        SolidAngle w1 = new SolidAngle(1.0, SolidAngle.Unit.sr);
        assertEquals(SolidAngle.ONE, w1);
        assertEquals(1.0, SolidAngle.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(SolidAngle.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, SolidAngle.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, SolidAngle.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, SolidAngle.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, SolidAngle.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        SolidAngle two = new SolidAngle(2.0, SolidAngle.Unit.sr);
        SolidAngle copy = new SolidAngle(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        SolidAngle fromStr = new SolidAngle(1.5, "sr");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of
        SolidAngle p1 = SolidAngle.valueOf("2 sr");
        assertEquals(2.0, p1.si(), 1E-12);

        SolidAngle p2 = SolidAngle.of(500.0, "sr");
        assertEquals(500.0, p2.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiateSi(-10.1).si(), 1E-12);

        // siUnit textual representation must match the SIUnit.of literal ("sr")
        assertEquals("sr", fromStr.siUnit().format(true, false));

        // ofSi
        SolidAngle neg = SolidAngle.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, malformed number).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> SolidAngle.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> SolidAngle.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> SolidAngle.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> SolidAngle.valueOf("not-a-number sr"));

        // of null unit
        assertThrows(NullPointerException.class, () -> SolidAngle.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> SolidAngle.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> SolidAngle.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testSolidAngleOperations()
    {
        // sr / sr -> Dimensionless
        var d1 = SolidAngle.ONE.divide(SolidAngle.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = SolidAngle.ofSi(1.0).divide(SolidAngle.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = SolidAngle.ofSi(1.0).divide(SolidAngle.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // sr * cd -> lm (LuminousFlux)
        var flux = SolidAngle.ofSi(4.0).multiply(LuminousIntensity.ofSi(3.0));
        assertTrue(flux instanceof LuminousFlux);
        assertEquals(12.0, flux.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units, non-linear derivation exception
     * branch, and registry sanity.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(SolidAngle.Unit.SI_UNIT, SolidAngle.Unit.sr.siUnit());
        assertEquals(SolidAngle.Unit.SI, SolidAngle.Unit.sr.getBaseUnit());

        // Unit.ofSi delegates to SolidAngle.ofSi
        SolidAngle fromUnit = SolidAngle.Unit.sr.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Verify square degree scale: (π/180)^2 sr
        var sqDegUnit = SolidAngle.Unit.sq_deg;
        double expectedSqDeg = (Math.PI / 180.0) * (Math.PI / 180.0);
        SolidAngle oneSqDeg = new SolidAngle(1.0, sqDegUnit);
        assertEquals(expectedSqDeg, oneSqDeg.si(), 1E-12);

        // Derive from a linear unit (sr) -> e.g., "2sr" (scale 2.0)
        SolidAngle.Unit twoSr = SolidAngle.Unit.sr.deriveUnit("2sr", "2sr", "two steradian", 2.0, UnitSystem.OTHER);
        SolidAngle x = new SolidAngle(1.0, twoSr); // 1 * 2 sr == 2 sr
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        SolidAngle.Unit nonLinear =
                new SolidAngle.Unit("gSr", "gSr", "grade-like steradian (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });

        // Registry sanity
        SolidAngle.Unit resolved = Units.resolve(SolidAngle.Unit.class, "sr");
        assertEquals(SolidAngle.Unit.sr, resolved);
        SolidAngle.Unit resolvedSqDeg = Units.resolve(SolidAngle.Unit.class, "sq.deg");
        assertEquals(SolidAngle.Unit.sq_deg, resolvedSqDeg);
    }
}
