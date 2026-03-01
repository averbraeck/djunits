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
 * PowerTest tests the Power quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class PowerTest
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
    void testPowerBasics()
    {
        // Construct with unit
        Power p0 = new Power(0.0, Power.Unit.W);
        assertEquals(Power.ZERO, p0);
        assertEquals(0.0, Power.ZERO.si(), 1E-12);

        Power p1 = new Power(1.0, Power.Unit.W);
        assertEquals(Power.ONE, p1);
        assertEquals(1.0, Power.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(Power.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Power.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Power.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Power.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Power.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Power two = new Power(2.0, Power.Unit.W);
        Power copy = new Power(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        Power fromStr = new Power(1.5, "W");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of
        Power pv1 = Power.valueOf("2 W");
        assertEquals(2.0, pv1.si(), 1E-12);

        Power pv2 = Power.of(500.0, "W");
        assertEquals(500.0, pv2.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation must match SIUnit.of string ("kgm2/s3")
        assertEquals("kgm2/s3", fromStr.siUnit().toString(true, false));

        // ofSi
        Power neg = Power.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test SI prefixes and legacy/alternate systems units.
     */
    @Test
    void testUnitsAndPrefixes()
    {
        // SI prefixes (registered by generateSiPrefixes on W)
        Power.Unit muW = Power.Unit.muW; // micro
        Power.Unit mW = Power.Unit.mW; // milli
        Power.Unit kW = Power.Unit.kW; // kilo
        Power.Unit pMW = Power.Unit.MW; // mega
        Power.Unit pGW = Power.Unit.GW; // giga
        Power.Unit pTW = Power.Unit.TW; // tera
        Power.Unit pPW = Power.Unit.PW; // peta

        // Check numeric conversions
        assertEquals(1e-6, new Power(1.0, muW).si(), 1e-18);
        assertEquals(1e-3, new Power(1.0, mW).si(), 1e-12);
        assertEquals(1e3, new Power(1.0, kW).si(), 1e-9);
        assertEquals(1e6, new Power(1.0, pMW).si(), 1e-6);
        assertEquals(1e9, new Power(1.0, pGW).si(), 1e-3);
        assertEquals(1e12, new Power(1.0, pTW).si(), 1e0);
        assertEquals(1e15, new Power(1.0, pPW).si(), 1e3);

        // Parsing prefixed units
        Power threeKw = Power.valueOf("3 kW");
        assertEquals(3000.0, threeKw.si(), 1e-9);

        // Legacy/other systems
        // foot-pound-force per second: ft * lb * g = W
        double ft = Length.Unit.CONST_FT; // 0.3048 m
        double lb = Mass.Unit.CONST_LB; // 0.45359237 kg
        double g = Acceleration.Unit.CONST_GRAVITY; // 9.80665 m/s^2
        double ftLbfPerS = ft * lb * g; // ≈ 1.3558179483314004 W
        Power oneFtLbfPerS = new Power(1.0, Power.Unit.ft_lbf_s);
        assertEquals(ftLbfPerS, oneFtLbfPerS.si(), 1e-12);

        // per minute and per hour variants
        Power oneFtLbfPerMin = new Power(1.0, Power.Unit.ft_lbf_min);
        assertEquals(ftLbfPerS / 60.0, oneFtLbfPerMin.si(), 1e-12);

        Power oneFtLbfPerH = new Power(1.0, Power.Unit.ft_lbf_h);
        assertEquals(ftLbfPerS / 3600.0, oneFtLbfPerH.si(), 1e-12);

        // metric horsepower (hp(M)) == 735.49875 W (by definition here)
        Power oneHpMetric = new Power(1.0, Power.Unit.hp_M);
        assertEquals(735.49875, oneHpMetric.si(), 1e-8);

        // MTS: sthene·meter per second: 1 sn = 1000 N --> 1000 W
        Power oneSnMS = new Power(1.0, Power.Unit.sn_m_s);
        assertEquals(1000.0, oneSnMS.si(), 1e-9);

        // CGS: erg/s = 1e-7 W
        Power oneErgPerS = new Power(1.0, Power.Unit.erg_s);
        assertEquals(1e-7, oneErgPerS.si(), 1e-16);

        // Registry sanity
        assertEquals(Power.Unit.W, Units.resolve(Power.Unit.class, "W"));
        assertEquals(Power.Unit.kW, Units.resolve(Power.Unit.class, "kW"));
        assertEquals(Power.Unit.mW, Units.resolve(Power.Unit.class, "mW"));
        assertEquals(Power.Unit.muW, Units.resolve(Power.Unit.class, "muW"));
        assertEquals(Power.Unit.MW, Units.resolve(Power.Unit.class, "MW"));
        assertEquals(Power.Unit.ft_lbf_s, Units.resolve(Power.Unit.class, "ft.lbf/s"));
        assertEquals(Power.Unit.hp_M, Units.resolve(Power.Unit.class, "hp(M)"));
        assertEquals(Power.Unit.erg_s, Units.resolve(Power.Unit.class, "erg/s"));
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, malformed number).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> Power.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> Power.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> Power.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> Power.valueOf("not-a-number W"));

        // of null unit
        assertThrows(NullPointerException.class, () -> Power.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> Power.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> Power.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testPowerOperations()
    {
        // W / W -> Dimensionless
        var d1 = Power.ONE.divide(Power.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = Power.ofSi(1.0).divide(Power.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = Power.ofSi(1.0).divide(Power.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // W * s -> J (Energy)
        var e1 = Power.ofSi(12.0).multiply(Duration.ofSi(3.0));
        assertTrue(e1 instanceof Energy);
        assertEquals(36.0, e1.si(), 1E-12);

        // W / Hz -> J (Energy)
        var e2 = Power.ofSi(9.0).divide(Frequency.ofSi(3.0));
        assertTrue(e2 instanceof Energy);
        assertEquals(3.0, e2.si(), 1E-12);

        // W / J -> Hz (Frequency)
        var f = Power.ofSi(20.0).divide(Energy.ofSi(4.0));
        assertTrue(f instanceof Frequency);
        assertEquals(5.0, f.si(), 1E-12);

        // W / (m/s) -> N (Force)
        var force = Power.ofSi(18.0).divide(Speed.ofSi(6.0));
        assertTrue(force instanceof Force);
        assertEquals(3.0, force.si(), 1E-12);

        // W / N -> m/s (Speed)
        var v = Power.ofSi(21.0).divide(Force.ofSi(7.0));
        assertTrue(v instanceof Speed);
        assertEquals(3.0, v.si(), 1E-12);

        // W / V -> A (ElectricCurrent)
        var i = Power.ofSi(24.0).divide(ElectricPotential.ofSi(12.0));
        assertTrue(i instanceof ElectricCurrent);
        assertEquals(2.0, i.si(), 1E-12);

        // W / A -> V (ElectricPotential)
        var ep = Power.ofSi(30.0).divide(ElectricCurrent.ofSi(10.0));
        assertTrue(ep instanceof ElectricPotential);
        assertEquals(3.0, ep.si(), 1E-12);

        // W / (m/s^2) -> kg·m/s (Momentum)
        var p = Power.ofSi(14.0).divide(Acceleration.ofSi(2.0));
        assertTrue(p instanceof Momentum);
        assertEquals(7.0, p.si(), 1E-12);

        // W / (kg·m/s) -> m/s^2 (Acceleration)
        var a = Power.ofSi(16.0).divide(Momentum.ofSi(4.0));
        assertTrue(a instanceof Acceleration);
        assertEquals(4.0, a.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units,
     * non-linear derivation exception branch, and registry sanity.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Power.Unit.SI_UNIT, Power.Unit.W.siUnit());
        assertEquals(Power.Unit.SI, Power.Unit.W.getBaseUnit());

        // Unit.ofSi delegates to Power.ofSi
        Power fromUnit = Power.Unit.W.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (W) -> e.g., "2W" (scale 2.0)
        Power.Unit twoW = Power.Unit.W.deriveUnit("2W", "2W", "two watt", 2.0, UnitSystem.OTHER);
        Power x = new Power(1.0, twoW); // 1 * 2 W == 2 W
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Power.Unit nonLinear = new Power.Unit("gWatt", "gWatt", "grade-like watt (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        {
            nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER);
        });

        // Registry sanity for prefixes and specials
        assertEquals(Power.Unit.kW, Units.resolve(Power.Unit.class, "kW"));
        assertEquals(Power.Unit.muW, Units.resolve(Power.Unit.class, "muW"));
        assertEquals(Power.Unit.ft_lbf_s, Units.resolve(Power.Unit.class, "ft.lbf/s"));
        assertEquals(Power.Unit.hp_M, Units.resolve(Power.Unit.class, "hp(M)"));
        assertEquals(Power.Unit.sn_m_s, Units.resolve(Power.Unit.class, "sn.m/s"));
        assertEquals(Power.Unit.erg_s, Units.resolve(Power.Unit.class, "erg/s"));
    }
}
