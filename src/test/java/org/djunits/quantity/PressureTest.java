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
 * PressureTest tests the Pressure quantity class.<br>
 * <br>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class PressureTest
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
    void testPressureBasics()
    {
        // Construct with unit
        Pressure p0 = new Pressure(0.0, Pressure.Unit.Pa);
        assertEquals(Pressure.ZERO, p0);
        assertEquals(0.0, Pressure.ZERO.si(), 1E-12);

        Pressure p1 = new Pressure(1.0, Pressure.Unit.Pa);
        assertEquals(Pressure.ONE, p1);
        assertEquals(1.0, Pressure.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(Pressure.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Pressure.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Pressure.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Pressure.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Pressure.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Pressure two = new Pressure(2.0, Pressure.Unit.Pa);
        Pressure copy = new Pressure(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        Pressure fromStr = new Pressure(1.5, "Pa");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of
        Pressure pv1 = Pressure.valueOf("2 Pa");
        assertEquals(2.0, pv1.si(), 1E-12);

        Pressure pv2 = Pressure.of(500.0, "Pa");
        assertEquals(500.0, pv2.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation must match SIUnit.of literal ("kg/ms2")
        assertEquals("kg/ms2", fromStr.siUnit().toString(true, false));

        // ofSi
        Pressure neg = Pressure.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test SI prefixes and alternate units (bar/mbar, atm/torr, at, Ba, mercury heights, imperial/US, MTS).
     */
    @Test
    void testUnitsAndPrefixes()
    {
        // SI prefixes
        Pressure.Unit hPa = Pressure.Unit.hPa;
        Pressure.Unit kPa = Pressure.Unit.kPa;

        // 1 hPa == 100 Pa ; 1 kPa == 1000 Pa
        assertEquals(100.0, new Pressure(1.0, hPa).si(), 1e-9);
        assertEquals(1000.0, new Pressure(1.0, kPa).si(), 1e-9);

        // bar and mbar
        Pressure.Unit bar = Pressure.Unit.bar;
        Pressure.Unit mbar = Pressure.Unit.mbar;
        assertEquals(1.0e5, new Pressure(1.0, bar).si(), 1e-4);
        assertEquals(100.0, new Pressure(1.0, mbar).si(), 1e-9);

        // Cross-check: 1 mbar == 1 hPa
        assertEquals(new Pressure(1.0, mbar).si(), new Pressure(1.0, hPa).si(), 1e-12);

        // Atmospheres & Torr
        Pressure.Unit atm = Pressure.Unit.atm;
        Pressure.Unit torr = Pressure.Unit.torr;
        assertEquals(101_325.0, new Pressure(1.0, atm).si(), 1e-6);
        assertEquals(101_325.0 / 760.0, new Pressure(1.0, torr).si(), 1e-9);

        // Technical atmosphere at (kgf/cm^2): 9.80665e4 Pa
        Pressure.Unit at = Pressure.Unit.at;
        assertEquals(9.80665e4, new Pressure(1.0, at).si(), 1e-2);

        // Barye (Ba) = dyne/cm^2 = 0.1 Pa
        Pressure.Unit Ba = Pressure.Unit.Ba;
        assertEquals(0.1, new Pressure(1.0, Ba).si(), 1e-12);

        // Mercury column units
        Pressure.Unit cmHg = Pressure.Unit.cmHg;
        Pressure.Unit mmHg = Pressure.Unit.mmHg;
        assertEquals(1333.224, new Pressure(1.0, cmHg).si(), 1e-6);
        assertEquals(133.3224, new Pressure(1.0, mmHg).si(), 1e-6);

        // inHg and ftHg
        Pressure.Unit inHg = Pressure.Unit.inHg;
        Pressure.Unit ftHg = Pressure.Unit.ftHg;
        assertEquals(3386.389, new Pressure(1.0, inHg).si(), 1e-3);
        assertEquals(40_636.66, new Pressure(1.0, ftHg).si(), 1e-2);

        // Imperial/US: lbf/ft^2 and lbf/in^2 (psi)
        Pressure.Unit lbfft2 = Pressure.Unit.lbf_ft2;
        Pressure.Unit psi = Pressure.Unit.psi;
        Pressure.Unit lbfin2 = Pressure.Unit.lbf_in2;

        double ft = Length.Unit.CONST_FT; // 0.3048 m
        double in = Length.Unit.CONST_IN; // 0.0254 m
        double lb = Mass.Unit.CONST_LB; // 0.45359237 kg
        double g = Acceleration.Unit.CONST_GRAVITY; // 9.80665 m/s^2

        double oneLbf = lb * g; // N
        double oneLbfPerFt2 = oneLbf / (ft * ft);
        double oneLbfPerIn2 = oneLbf / (in * in);

        assertEquals(oneLbfPerFt2, new Pressure(1.0, lbfft2).si(), 1e-6);
        assertEquals(oneLbfPerIn2, new Pressure(1.0, lbfin2).si(), 1e-6);
        assertEquals(oneLbfPerIn2, new Pressure(1.0, psi).si(), 1e-6);

        // MTS: pièze
        Pressure.Unit pz = Pressure.Unit.pz;
        assertEquals(1000.0, new Pressure(1.0, pz).si(), 1e-9);

        // Registry sanity for a few canonical abbreviations
        assertEquals(Pressure.Unit.Pa, Units.resolve(Pressure.Unit.class, "Pa"));
        assertEquals(Pressure.Unit.kPa, Units.resolve(Pressure.Unit.class, "kPa"));
        assertEquals(Pressure.Unit.hPa, Units.resolve(Pressure.Unit.class, "hPa"));
        assertEquals(Pressure.Unit.bar, Units.resolve(Pressure.Unit.class, "bar"));
        assertEquals(Pressure.Unit.mbar, Units.resolve(Pressure.Unit.class, "mbar"));
        assertEquals(Pressure.Unit.torr, Units.resolve(Pressure.Unit.class, "torr"));
        assertEquals(Pressure.Unit.lbf_in2, Units.resolve(Pressure.Unit.class, "lbf/in2"));
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, malformed number).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> Pressure.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> Pressure.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> Pressure.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> Pressure.valueOf("not-a-number Pa"));

        // of null unit
        assertThrows(NullPointerException.class, () -> Pressure.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> Pressure.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> Pressure.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testPressureOperations()
    {
        // Pa / Pa -> Dimensionless
        var d1 = Pressure.ONE.divide(Pressure.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = Pressure.ofSi(1.0).divide(Pressure.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = Pressure.ofSi(1.0).divide(Pressure.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // Pa * m^2 -> N (Force)
        var f = Pressure.ofSi(12.0).multiply(Area.ofSi(3.0));
        assertTrue(f instanceof Force);
        assertEquals(36.0, f.si(), 1E-12);

        // Pa * m^3 -> J (Energy)
        var e = Pressure.ofSi(20.0).multiply(Volume.ofSi(4.0));
        assertTrue(e instanceof Energy);
        assertEquals(80.0, e.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units, non-linear derivation exception
     * branch, and registry sanity.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Pressure.Unit.SI_UNIT, Pressure.Unit.Pa.siUnit());
        assertEquals(Pressure.Unit.SI, Pressure.Unit.Pa.getBaseUnit());

        // Unit.ofSi delegates to Pressure.ofSi
        Pressure fromUnit = Pressure.Unit.Pa.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (Pa) -> "2Pa" (scale 2.0)
        Pressure.Unit twoPa = Pressure.Unit.Pa.deriveUnit("2Pa", "2Pa", "two pascal", 2.0, UnitSystem.OTHER);
        Pressure x = new Pressure(1.0, twoPa); // 1 * 2 Pa == 2 Pa
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Pressure.Unit nonLinear =
                new Pressure.Unit("gPa", "gPa", "grade-like pascal (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });

        // Registry sanity already partly checked above; verify a few more
        assertEquals(Pressure.Unit.cmHg, Units.resolve(Pressure.Unit.class, "cmHg"));
        assertEquals(Pressure.Unit.mmHg, Units.resolve(Pressure.Unit.class, "mmHg"));
        assertEquals(Pressure.Unit.inHg, Units.resolve(Pressure.Unit.class, "inHg"));
        assertEquals(Pressure.Unit.ftHg, Units.resolve(Pressure.Unit.class, "ftHg"));
        assertEquals(Pressure.Unit.at, Units.resolve(Pressure.Unit.class, "at"));
        assertEquals(Pressure.Unit.Ba, Units.resolve(Pressure.Unit.class, "Ba"));
    }
}
