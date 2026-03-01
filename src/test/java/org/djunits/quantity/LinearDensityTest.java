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
 * LinearDensityTest tests the LinearDensity quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class LinearDensityTest
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
    void testLinearDensityBasics()
    {
        // Construct with unit
        LinearDensity ld0 = new LinearDensity(0.0, LinearDensity.Unit.kg_m);
        assertEquals(LinearDensity.ZERO, ld0);
        assertEquals(0.0, LinearDensity.ZERO.si(), 1E-12);

        LinearDensity ld1 = new LinearDensity(1.0, LinearDensity.Unit.kg_m);
        assertEquals(LinearDensity.ONE, ld1);
        assertEquals(1.0, LinearDensity.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(LinearDensity.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, LinearDensity.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, LinearDensity.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, LinearDensity.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, LinearDensity.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        LinearDensity two = new LinearDensity(2.0, LinearDensity.Unit.kg_m);
        LinearDensity copy = new LinearDensity(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        LinearDensity fromStr = new LinearDensity(1.5, "kg/m");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf
        LinearDensity p1 = LinearDensity.valueOf("2 kg/m");
        assertEquals(2.0, p1.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation (like FrequencyTest style)
        assertEquals("kg/m", fromStr.siUnit().toString(true, false));

        // ofSi
        LinearDensity neg = LinearDensity.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test valueOf/of with a derived linear unit (register unit first) and unit resolution.
     */
    @Test
    void testParsingWithDerivedUnits()
    {
        // Derive g/m = 1e-3 kg/m and register it
        LinearDensity.Unit gPerM =
                LinearDensity.Unit.kg_m.deriveUnit("g/m", "g/m", "gram per meter", 1e-3, UnitSystem.SI_DERIVED);

        // valueOf with derived unit
        LinearDensity threeKgPerM = LinearDensity.valueOf("3000 g/m"); // 3000 g/m == 3 kg/m
        assertEquals(3.0, threeKgPerM.si(), 1E-12);

        // of(value, unitString) with derived unit
        LinearDensity fiveGramsPerM = LinearDensity.of(5.0, "g/m");
        assertEquals(0.005, fiveGramsPerM.si(), 1E-12);

        // Also resolve explicit abbreviation via Units (sanity)
        LinearDensity.Unit resolved = Units.resolve(LinearDensity.Unit.class, "g/m");
        assertEquals(gPerM, resolved);
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, and general parse error surface).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> LinearDensity.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> LinearDensity.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> LinearDensity.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> LinearDensity.valueOf("not-a-number kg/m"));

        // of null unit
        assertThrows(NullPointerException.class, () -> LinearDensity.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> LinearDensity.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> LinearDensity.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testLinearDensityOperations()
    {
        // Divide by LinearDensity -> Dimensionless
        var d1 = LinearDensity.ONE.divide(LinearDensity.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = LinearDensity.ofSi(1.0).divide(LinearDensity.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (sanity)
        var dInf = LinearDensity.ofSi(1.0).divide(LinearDensity.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // (kg/m) / kg -> 1/m (LinearObjectDensity)
        var lod = LinearDensity.ofSi(10.0).divide(Mass.ofSi(2.0));
        assertTrue(lod instanceof LinearObjectDensity);
        assertEquals(5.0, lod.si(), 1E-12);

        // (kg/m) / (1/m) -> kg (Mass)
        var m = LinearDensity.ofSi(12.0).divide(LinearObjectDensity.ofSi(3.0));
        assertTrue(m instanceof Mass);
        assertEquals(4.0, m.si(), 1E-12);

        // (kg/m) * m -> kg (Mass)
        var m2 = LinearDensity.ofSi(7.0).multiply(Length.ofSi(2.0));
        assertTrue(m2 instanceof Mass);
        assertEquals(14.0, m2.si(), 1E-12);

        // (kg/m) * (m/s) -> kg/s (FlowMass)
        var q = LinearDensity.ofSi(8.0).multiply(Speed.ofSi(0.5));
        assertTrue(q instanceof FlowMass);
        assertEquals(4.0, q.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, deriving linear units, Unit.ofSi delegation, and exception branch for non-linear
     * scale.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(LinearDensity.Unit.SI_UNIT, LinearDensity.Unit.kg_m.siUnit());
        assertEquals(LinearDensity.Unit.SI, LinearDensity.Unit.kg_m.getBaseUnit());

        // Unit.ofSi delegates to LinearDensity.ofSi
        LinearDensity fromUnit = LinearDensity.Unit.kg_m.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (kg/m) -> e.g., t/m (ton per meter), scale 1000
        LinearDensity.Unit tPerM =
                LinearDensity.Unit.kg_m.deriveUnit("t/m", "t/m", "ton per meter", 1000.0, UnitSystem.SI_DERIVED);
        LinearDensity x = new LinearDensity(1.0, tPerM); // 1 t/m == 1000 kg/m
        assertEquals(1000.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        LinearDensity.Unit nonLinear = new LinearDensity.Unit("gLd", "gLd", "grade-like linear density (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });
    }
}
