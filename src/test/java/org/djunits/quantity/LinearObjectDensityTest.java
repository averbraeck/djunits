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
 * LinearObjectDensityTest tests the LinearObjectDensity quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander Verbraeck
 */
class LinearObjectDensityTest
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
    void testLinearObjectDensityBasics()
    {
        // Construct with unit
        LinearObjectDensity lod0 = new LinearObjectDensity(0.0, LinearObjectDensity.Unit.per_m);
        assertEquals(LinearObjectDensity.ZERO, lod0);
        assertEquals(0.0, LinearObjectDensity.ZERO.si(), 1E-12);

        LinearObjectDensity lod1 = new LinearObjectDensity(1.0, LinearObjectDensity.Unit.per_m);
        assertEquals(LinearObjectDensity.ONE, lod1);
        assertEquals(1.0, LinearObjectDensity.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(LinearObjectDensity.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, LinearObjectDensity.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, LinearObjectDensity.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, LinearObjectDensity.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, LinearObjectDensity.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        LinearObjectDensity two = new LinearObjectDensity(2.0, LinearObjectDensity.Unit.per_m);
        LinearObjectDensity copy = new LinearObjectDensity(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        LinearObjectDensity fromStr = new LinearObjectDensity(1.5, "/m");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf
        LinearObjectDensity p1 = LinearObjectDensity.valueOf("2 /m");
        assertEquals(2.0, p1.si(), 1E-12);

        // ofSi
        LinearObjectDensity neg = LinearObjectDensity.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation must match the SIUnit.of string used in Unit ("/m")
        assertEquals("1/m", fromStr.siUnit().toString(true, false));
    }

    /**
     * Test parsing with SI-prefixed and imperial units resolved via Units registry.
     */
    @Test
    void testParsingWithDerivedAndPrefixedUnits()
    {
        // SI prefixes should be registered by generateSiPrefixes(false, true)
        LinearObjectDensity.Unit perKm = LinearObjectDensity.Unit.per_km;
        LinearObjectDensity.Unit resolvedPerKm = Units.resolve(LinearObjectDensity.Unit.class, "/km");
        assertEquals(perKm, resolvedPerKm);

        // 1 /km == 1e-3 /m
        LinearObjectDensity onePerKm = new LinearObjectDensity(1.0, perKm);
        assertEquals(1.0e-3, onePerKm.si(), 1e-12);

        // valueOf with prefixed unit
        LinearObjectDensity v = LinearObjectDensity.valueOf("3000 /km"); // 3.0 /m
        assertEquals(3.0, v.si(), 1e-12);

        // of(value, unitString) with prefixed unit
        LinearObjectDensity w = LinearObjectDensity.of(0.5, "/km"); // 5e-4 /m
        assertEquals(5e-4, w.si(), 1e-12);

        // Imperial: 1 /in == 1/0.0254 /m ≈ 39.37007874 /m
        LinearObjectDensity onePerInch = new LinearObjectDensity(1.0, LinearObjectDensity.Unit.per_in);
        assertEquals(39.37007874015748, onePerInch.si(), 1e-10);
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, and general parse error surface).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> LinearObjectDensity.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> LinearObjectDensity.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> LinearObjectDensity.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> LinearObjectDensity.valueOf("not-a-number /m"));

        // of null unit
        assertThrows(NullPointerException.class, () -> LinearObjectDensity.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> LinearObjectDensity.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> LinearObjectDensity.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testLinearObjectDensityOperations()
    {
        // Divide by LinearObjectDensity -> Dimensionless
        var d1 = LinearObjectDensity.ONE.divide(LinearObjectDensity.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = LinearObjectDensity.ofSi(1.0).divide(LinearObjectDensity.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = LinearObjectDensity.ofSi(1.0).divide(LinearObjectDensity.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // (1/m) * m -> 1 (Dimensionless)
        var dim = LinearObjectDensity.ofSi(2.0).multiply(Length.ofSi(3.0));
        assertTrue(dim instanceof Dimensionless);
        assertEquals(6.0, dim.si(), 1E-12);

        // (1/m) * m^2 -> m (Length)
        var l = LinearObjectDensity.ofSi(4.0).multiply(Area.ofSi(2.0));
        assertTrue(l instanceof Length);
        assertEquals(8.0, l.si(), 1E-12);

        // (1/m) * J -> N (Force)
        var force = LinearObjectDensity.ofSi(5.0).multiply(Energy.ofSi(2.0));
        assertTrue(force instanceof Force);
        assertEquals(10.0, force.si(), 1E-12);

        // (1/m) * (m/s) -> 1/s (Frequency)
        var f = LinearObjectDensity.ofSi(3.0).multiply(Speed.ofSi(2.0));
        assertTrue(f instanceof Frequency);
        assertEquals(6.0, f.si(), 1E-12);

        // reciprocal -> Length
        var r = LinearObjectDensity.ofSi(4.0).reciprocal();
        assertTrue(r instanceof Length);
        assertEquals(0.25, r.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units, non-linear derivation exception
     * branch, and prefix-generated units.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(LinearObjectDensity.Unit.SI_UNIT, LinearObjectDensity.Unit.per_m.siUnit());
        assertEquals(LinearObjectDensity.Unit.SI, LinearObjectDensity.Unit.per_m.getBaseUnit());

        // Unit.ofSi delegates to LinearObjectDensity.ofSi
        LinearObjectDensity fromUnit = LinearObjectDensity.Unit.per_m.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive a linear unit from /m -> e.g., "/2m" meaning "per 2 meters" (scale 0.5)
        LinearObjectDensity.Unit per2m =
                LinearObjectDensity.Unit.per_m.deriveUnit("/2m", "/2m", "per two meters", 0.5, UnitSystem.OTHER);
        LinearObjectDensity x = new LinearObjectDensity(1.0, per2m); // 1 * 0.5 /m == 0.5 /m
        assertEquals(0.5, x.si(), 1E-12);

        // Non-linear scale derivation must throw
        LinearObjectDensity.Unit nonLinear = new LinearObjectDensity.Unit("gLOD", "gLOD", "grade-like LOD (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });

        // Prefix-generated unit sanity already tested above for /km; also assert several are resolvable
        assertEquals(LinearObjectDensity.Unit.per_mm, Units.resolve(LinearObjectDensity.Unit.class, "/mm"));
        assertEquals(LinearObjectDensity.Unit.per_cm, Units.resolve(LinearObjectDensity.Unit.class, "/cm"));
        assertEquals(LinearObjectDensity.Unit.per_dm, Units.resolve(LinearObjectDensity.Unit.class, "/dm"));
        assertEquals(LinearObjectDensity.Unit.per_dam, Units.resolve(LinearObjectDensity.Unit.class, "/dam"));
        assertEquals(LinearObjectDensity.Unit.per_hm, Units.resolve(LinearObjectDensity.Unit.class, "/hm"));
        assertEquals(LinearObjectDensity.Unit.per_km, Units.resolve(LinearObjectDensity.Unit.class, "/km"));
    }
}
