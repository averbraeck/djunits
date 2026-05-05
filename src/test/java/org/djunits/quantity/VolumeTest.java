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
 * VolumeTest tests the Volume quantity class.<p>
 * This test suite verifies:
 * <ul>
 * <li>Constructors, constants, copy behavior, and SI conversions</li>
 * <li>Class-specific String parsing behavior (success + error branches)</li>
 * <li>Arithmetic operations producing correct result quantities</li>
 * <li>Unit conversions for SI, SI-derived, imperial/US customary, and astronomical units</li>
 * <li>Unit derivation behavior (linear + non-linear exception path)</li>
 * <li>Units registry resolution sanity</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
class VolumeTest
{
    /**
     * Set a predictable locale before each test.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test constructors, constants, parsing, SI conversions, instantiate, siUnit(), and ofSi().
     */
    @Test
    void testVolumeBasics()
    {
        // Construct with unit
        Volume v0 = new Volume(0.0, Volume.Unit.m3);
        assertEquals(Volume.ZERO, v0);
        assertEquals(0.0, Volume.ZERO.si(), 1E-12);

        Volume v1 = new Volume(1.0, Volume.Unit.m3);
        assertEquals(Volume.ONE, v1);
        assertEquals(1.0, Volume.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(Volume.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Volume.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Volume.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Volume.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Volume.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Volume two = new Volume(2.0, Volume.Unit.m3);
        Volume copy = new Volume(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        Volume fromStr = new Volume(1.5, "m3");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of (class-specific NumberParser)
        Volume p1 = Volume.valueOf("2 m3");
        assertEquals(2.0, p1.si(), 1E-12);

        Volume p2 = Volume.valueOf("   12.5   L  "); // liters are cubic decimeters
        assertEquals(12.5e-3, p2.si(), 1E-12);

        Volume p3 = Volume.of(500.0, "cm3"); // 500 cm^3 = 5e-4 m^3
        assertEquals(5e-4, p3.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiateSi(-10.1).si(), 1E-12);

        // siUnit textual representation
        assertEquals("m3", fromStr.siUnit().format(true, false));

        // ofSi
        Volume neg = Volume.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test valueOf/of error branches with the custom Throw/NumberParser logic.
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null -> NPE
        assertThrows(NullPointerException.class, () -> Volume.valueOf(null));

        // valueOf empty -> IAE
        assertThrows(IllegalArgumentException.class, () -> Volume.valueOf(""));

        // valueOf unknown unit -> IAE
        assertThrows(IllegalArgumentException.class, () -> Volume.valueOf("10 blargh"));

        // valueOf malformed number -> IAE (wrapped)
        assertThrows(IllegalArgumentException.class, () -> Volume.valueOf("not-a-number m3"));

        // of null unit -> NPE
        assertThrows(NullPointerException.class, () -> Volume.of(1.0, null));

        // of empty unit -> IAE
        assertThrows(IllegalArgumentException.class, () -> Volume.of(1.0, ""));

        // of unknown unit -> IAE (Volume.of uses Throw.when for IAE)
        assertThrows(UnitRuntimeException.class, () -> Volume.of(1.0, "blargh"));
    }

    /**
     * Test a selection of unit conversions (SI, SI-derived, imperial/US customary, astronomical), and registry resolution
     * sanity.
     */
    @Test
    void testUnitConversionsAndRegistry()
    {
        // Base unit checks
        assertEquals(Volume.Unit.SI_UNIT, Volume.Unit.m3.siUnit());
        assertEquals(Volume.Unit.SI, Volume.Unit.m3.getBaseUnit());

        // SI-derived cubic prefixes
        assertEquals(1.0e-9, new Volume(1.0, Volume.Unit.mm3).si(), 1E-21);
        assertEquals(1.0e-6, new Volume(1.0, Volume.Unit.cm3).si(), 1E-18);
        assertEquals(1.0e-3, new Volume(1.0, Volume.Unit.dm3).si(), 1E-12);
        assertEquals(1.0e3, new Volume(1.0, Volume.Unit.dam3).si(), 1E-9);
        assertEquals(1.0e6, new Volume(1.0, Volume.Unit.hm3).si(), 1E-6);
        assertEquals(1.0e9, new Volume(1.0, Volume.Unit.km3).si(), 1E-3);

        // Liter = dm^3
        assertEquals(1.0e-3, new Volume(1.0, Volume.Unit.L).si(), 1E-12);

        // Imperial/US customary via constants
        double in3 = Volume.Unit.CONST_CUBIC_INCH;
        double ft3 = Volume.Unit.CONST_CUBIC_FOOT;
        double yd3 = Volume.Unit.CONST_CUBIC_YARD;

        assertEquals(in3, new Volume(1.0, Volume.Unit.in3).si(), 1E-18);
        assertEquals(ft3, new Volume(1.0, Volume.Unit.ft3).si(), 1E-12);
        assertEquals(yd3, new Volume(1.0, Volume.Unit.yd3).si(), 1E-9);

        // Gallons, quarts, pints, fluid ounces
        double galUS = Volume.Unit.CONST_GALLON_US;
        double galImp = Volume.Unit.CONST_GALLON_IMP;
        double ozUS = Volume.Unit.CONST_OZ_US;
        double ozImp = Volume.Unit.CONST_OZ_IMP;

        assertEquals(galUS, new Volume(1.0, Volume.Unit.gal_US).si(), 1E-12);
        assertEquals(galImp, new Volume(1.0, Volume.Unit.gal_imp).si(), 1E-15);
        assertEquals(0.25 * galUS, new Volume(1.0, Volume.Unit.qt_US).si(), 1E-12);
        assertEquals(0.25 * galImp, new Volume(1.0, Volume.Unit.qt_imp).si(), 1E-15);
        assertEquals(0.5 * 0.25 * galUS, new Volume(1.0, Volume.Unit.pt_US).si(), 1E-12);
        assertEquals(0.5 * 0.25 * galImp, new Volume(1.0, Volume.Unit.pt_imp).si(), 1E-15);
        assertEquals(ozUS, new Volume(1.0, Volume.Unit.fl_oz_US).si(), 1E-15);
        assertEquals(ozImp, new Volume(1.0, Volume.Unit.fl_oz_imp).si(), 1E-18);

        // Astronomical
        double ly3 = Math.pow(Length.Unit.CONST_LY, 3);
        double pc3 = Math.pow(Length.Unit.CONST_PC, 3);
        assertEquals(ly3, new Volume(1.0, Volume.Unit.ly3).si(), Math.abs(ly3) * 1E-12);
        assertEquals(pc3, new Volume(1.0, Volume.Unit.pc3).si(), Math.abs(pc3) * 1E-12);

        // Registry sanity
        assertEquals(Volume.Unit.m3, Units.resolve(Volume.Unit.class, "m3"));
        assertEquals(Volume.Unit.cm3, Units.resolve(Volume.Unit.class, "cm3"));
        assertEquals(Volume.Unit.L, Units.resolve(Volume.Unit.class, "L"));
        assertEquals(Volume.Unit.gal_US, Units.resolve(Volume.Unit.class, "gal(US)"));
        assertEquals(Volume.Unit.gal_imp, Units.resolve(Volume.Unit.class, "gal(imp)"));
        assertEquals(Volume.Unit.in3, Units.resolve(Volume.Unit.class, "in3"));
    }

    /**
     * Test arithmetic operations:
     * <ul>
     * <li>divide(Volume) → Dimensionless (incl. divide-by-zero)</li>
     * <li>multiply(Density) → Mass</li>
     * <li>multiply(Pressure) → Energy</li>
     * <li>divide(Length) → Area</li>
     * <li>divide(Area) → Length</li>
     * <li>multiply(LinearObjectDensity) → Area</li>
     * <li>divide(Duration) → FlowVolume</li>
     * <li>divide(FlowVolume) → Duration</li>
     * <li>reciprocal() → VolumetricObjectDensity</li>
     * </ul>
     */
    @Test
    @SuppressWarnings("checkstyle:localvariablename")
    void testVolumeOperations()
    {
        // V / V -> 1
        var d1 = Volume.ONE.divide(Volume.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = Volume.ofSi(1.0).divide(Volume.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // V / 0 -> +Infinity
        var dInf = Volume.ofSi(1.0).divide(Volume.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // V * ρ -> m
        var m = Volume.ofSi(3.0).multiply(Density.ofSi(2.0));
        assertTrue(m instanceof Mass);
        assertEquals(6.0, m.si(), 1E-12);

        // V * p -> E
        var e = Volume.ofSi(4.0).multiply(Pressure.ofSi(5.0));
        assertTrue(e instanceof Energy);
        assertEquals(20.0, e.si(), 1E-12);

        // V / L -> A
        var A1 = Volume.ofSi(12.0).divide(Length.ofSi(3.0));
        assertTrue(A1 instanceof Area);
        assertEquals(4.0, A1.si(), 1E-12);

        // V / A -> L
        var L1 = Volume.ofSi(18.0).divide(Area.ofSi(6.0));
        assertTrue(L1 instanceof Length);
        assertEquals(3.0, L1.si(), 1E-12);

        // V * LOD -> A
        var A2 = Volume.ofSi(10.0).multiply(LinearObjectDensity.ofSi(0.5));
        assertTrue(A2 instanceof Area);
        assertEquals(5.0, A2.si(), 1E-12);

        // V / t -> Qv
        var qv = Volume.ofSi(9.0).divide(Duration.ofSi(3.0));
        assertTrue(qv instanceof FlowVolume);
        assertEquals(3.0, qv.si(), 1E-12);

        // V / Qv -> t
        var t = Volume.ofSi(20.0).divide(FlowVolume.ofSi(5.0));
        assertTrue(t instanceof Duration);
        assertEquals(4.0, t.si(), 1E-12);

        // reciprocal -> VolumetricObjectDensity
        var vod = Volume.ofSi(2.0).reciprocal();
        assertTrue(vod instanceof VolumetricObjectDensity);
        assertEquals(0.5, vod.si(), 1E-12);
    }

    /**
     * Test unit derivation behavior. This includes:
     * <ul>
     * <li>Linear derivation from m3</li>
     * <li>Non-linear derivation exception path</li>
     * <li>Unit.ofSi delegation</li>
     * </ul>
     */
    @Test
    void testUnitBehavior()
    {
        // Unit.ofSi delegates to Volume.ofSi
        Volume fromUnit = Volume.Unit.m3.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive linear: "2m3"
        Volume.Unit twoM3 = Volume.Unit.m3.deriveUnit("2m3", "two cubic meters", 2.0, UnitSystem.OTHER);
        Volume x = new Volume(1.0, twoM3); // 1 * 2 m^3 == 2 m^3
        assertEquals(2.0, x.si(), 1E-12);

        // Non-linear derive should throw
        Volume.Unit bad = new Volume.Unit("gM3", "gM3", "grade-like cubic meter", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { bad.deriveUnit("bad", "bad", "nonlinear derived", 3.0, UnitSystem.OTHER); });
    }
}
