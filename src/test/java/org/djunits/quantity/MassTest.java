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
 * MassTest tests the Mass quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license. author Alexander
 */
class MassTest
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
    void testMassBasics()
    {
        // Construct with unit
        Mass m0 = new Mass(0.0, Mass.Unit.kg);
        assertEquals(Mass.ZERO, m0);
        assertEquals(0.0, Mass.ZERO.si(), 1E-12);

        Mass m1 = new Mass(1.0, Mass.Unit.kg);
        assertEquals(Mass.ONE, m1);
        assertEquals(1.0, Mass.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(Mass.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, Mass.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, Mass.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, Mass.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, Mass.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        Mass two = new Mass(2.0, Mass.Unit.kg);
        Mass copy = new Mass(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        Mass fromStr = new Mass(1.5, "kg");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of
        Mass p1 = Mass.valueOf("2 kg");
        assertEquals(2.0, p1.si(), 1E-12);

        Mass p2 = Mass.of(500.0, "g"); // 0.5 kg
        assertEquals(0.5, p2.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation must match the SIUnit.of string used in Unit ("kg")
        assertEquals("kg", fromStr.siUnit().toString(true, false));

        // ofSi
        Mass neg = Mass.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test prefixed SI units and selected special/imperial units.
     */
    @Test
    void testUnitsAndPrefixes()
    {
        // SI prefixes registered off kg (handling the kg special-case): g, mg, mug(µg)
        Mass.Unit g = Mass.Unit.g;
        Mass.Unit mg = Mass.Unit.mg;
        Mass.Unit mug = Mass.Unit.mug;

        // 1 g == 1e-3 kg
        Mass oneGram = new Mass(1.0, g);
        assertEquals(1e-3, oneGram.si(), 1e-12);

        // 1 mg == 1e-6 kg
        Mass oneMilliGram = new Mass(1.0, mg);
        assertEquals(1e-6, oneMilliGram.si(), 1e-18);

        // 1 µg (mug) == 1e-9 kg
        Mass oneMicroGram = new Mass(1.0, mug);
        assertEquals(1e-9, oneMicroGram.si(), 1e-21);

        // Imperial/US units
        Mass.Unit lb = Mass.Unit.lb;
        Mass.Unit oz = Mass.Unit.oz;

        // 1 lb == 0.45359237 kg
        Mass onePound = new Mass(1.0, lb);
        assertEquals(0.45359237, onePound.si(), 1e-12);

        // 1 oz == 1/16 lb == 0.028349523125 kg
        Mass oneOunce = new Mass(1.0, oz);
        assertEquals(0.45359237 / 16.0, oneOunce.si(), 1e-12);

        // Tons
        Mass.Unit longTon = Mass.Unit.long_tn;
        Mass.Unit shortTon = Mass.Unit.sh_tn;
        Mass.Unit metricTon = Mass.Unit.t;
        Mass.Unit metricTonMts = Mass.Unit.t_mts;

        // long ton = 2240 lb
        Mass longTonMass = new Mass(1.0, longTon);
        assertEquals(2240.0 * 0.45359237, longTonMass.si(), 1e-6);

        // short ton = 2000 lb
        Mass shortTonMass = new Mass(1.0, shortTon);
        assertEquals(2000.0 * 0.45359237, shortTonMass.si(), 1e-6);

        // metric ton = 1000 kg
        Mass oneMetricTon = new Mass(1.0, metricTon);
        assertEquals(1000.0, oneMetricTon.si(), 1e-12);

        Mass oneMetricTonMts = new Mass(1.0, metricTonMts);
        assertEquals(1000.0, oneMetricTonMts.si(), 1e-12);

        // Dalton and eV/c^2 mass units
        Mass.Unit da = Mass.Unit.Da;
        Mass oneDa = new Mass(1.0, da);
        assertEquals(1.66053906660E-27, oneDa.si(), 1e-37);

        Mass.Unit eV = Mass.Unit.eV;
        Mass.Unit mueV = Mass.Unit.mueV;
        Mass.Unit meV = Mass.Unit.meV;
        Mass.Unit keV = Mass.Unit.keV;
        Mass.Unit mMeV = Mass.Unit.MeV;
        Mass.Unit mGeV = Mass.Unit.GeV;

        // 1 eV == 1.782661907E-36 kg (as defined in this class)
        Mass oneEv = new Mass(1.0, eV);
        assertEquals(1.782661907E-36, oneEv.si(), 1e-46);

        // prefixed eV mass units
        assertEquals(1.782661907E-42, new Mass(1.0, mueV).si(), 1e-52); // micro
        assertEquals(1.782661907E-39, new Mass(1.0, meV).si(), 1e-49); // milli
        assertEquals(1.782661907E-33, new Mass(1.0, keV).si(), 1e-43); // kilo
        assertEquals(1.782661907E-30, new Mass(1.0, mMeV).si(), 1e-40); // mega
        assertEquals(1.782661907E-27, new Mass(1.0, mGeV).si(), 1e-37); // giga

        // Registry sanity
        assertEquals(Mass.Unit.kg, Units.resolve(Mass.Unit.class, "kg"));
        assertEquals(Mass.Unit.g, Units.resolve(Mass.Unit.class, "g"));
        assertEquals(Mass.Unit.mg, Units.resolve(Mass.Unit.class, "mg"));
        assertEquals(Mass.Unit.mug, Units.resolve(Mass.Unit.class, "mug"));
        assertEquals(Mass.Unit.lb, Units.resolve(Mass.Unit.class, "lb"));
        assertEquals(Mass.Unit.oz, Units.resolve(Mass.Unit.class, "oz"));
        assertEquals(Mass.Unit.t, Units.resolve(Mass.Unit.class, "t"));
        assertEquals(Mass.Unit.eV, Units.resolve(Mass.Unit.class, "eV"));
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, malformed number).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> Mass.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> Mass.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> Mass.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> Mass.valueOf("not-a-number kg"));

        // of null unit
        assertThrows(NullPointerException.class, () -> Mass.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> Mass.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> Mass.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testMassOperations()
    {
        // kg / kg -> Dimensionless
        var d1 = Mass.ONE.divide(Mass.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = Mass.ofSi(1.0).divide(Mass.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = Mass.ofSi(1.0).divide(Mass.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // kg / (kg/s) -> s (Duration)
        var t = Mass.ofSi(10.0).divide(FlowMass.ofSi(2.0));
        assertTrue(t instanceof Duration);
        assertEquals(5.0, t.si(), 1E-12);

        // kg / s -> (kg/s) (FlowMass)
        var q = Mass.ofSi(12.0).divide(Duration.ofSi(3.0));
        assertTrue(q instanceof FlowMass);
        assertEquals(4.0, q.si(), 1E-12);

        // kg * (m/s^2) -> N (Force)
        var f = Mass.ofSi(7.0).multiply(Acceleration.ofSi(2.0));
        assertTrue(f instanceof Force);
        assertEquals(14.0, f.si(), 1E-12);

        // kg * (1/s) -> (kg/s) (FlowMass)
        var q2 = Mass.ofSi(9.0).multiply(Frequency.ofSi(0.5));
        assertTrue(q2 instanceof FlowMass);
        assertEquals(4.5, q2.si(), 1E-12);

        // kg / (kg/m^3) -> m^3 (Volume)
        var vol = Mass.ofSi(20.0).divide(Density.ofSi(4.0));
        assertTrue(vol instanceof Volume);
        assertEquals(5.0, vol.si(), 1E-12);

        // kg / m^3 -> kg/m^3 (Density)
        var rho = Mass.ofSi(18.0).divide(Volume.ofSi(6.0));
        assertTrue(rho instanceof Density);
        assertEquals(3.0, rho.si(), 1E-12);

        // kg * (m/s) -> kg·m/s (Momentum)
        var p = Mass.ofSi(5.0).multiply(Speed.ofSi(3.0));
        assertTrue(p instanceof Momentum);
        assertEquals(15.0, p.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units, non-linear derivation exception
     * branch, and registry sanity.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(Mass.Unit.SI_UNIT, Mass.Unit.kg.siUnit());
        assertEquals(Mass.Unit.SI, Mass.Unit.kg.getBaseUnit());

        // Unit.ofSi delegates to Mass.ofSi
        Mass fromUnit = Mass.Unit.kg.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive a simple linear unit from kg -> "2kg" (scale 2.0)
        Mass.Unit twoKg = Mass.Unit.kg.deriveUnit("2kg", "2kg", "two kilogram", 2.0, UnitSystem.OTHER);
        Mass x = new Mass(1.0, twoKg); // 1 * 2 kg == 2 kg
        assertEquals(2.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        Mass.Unit nonLinear =
                new Mass.Unit("gMass", "gMass", "grade-like mass (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });

        // Registry sanity already partly checked; verify a few more
        assertEquals(Mass.Unit.long_tn, Units.resolve(Mass.Unit.class, "long tn"));
        assertEquals(Mass.Unit.sh_tn, Units.resolve(Mass.Unit.class, "sh tn"));
        assertEquals(Mass.Unit.t_mts, Units.resolve(Mass.Unit.class, "t(mts)"));
        assertEquals(Mass.Unit.Da, Units.resolve(Mass.Unit.class, "Da"));
    }
}
