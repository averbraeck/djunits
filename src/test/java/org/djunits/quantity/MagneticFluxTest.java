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
 * MagneticFluxTest tests the MagneticFlux quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class MagneticFluxTest
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
    void testMagneticFluxBasics()
    {
        // Construct with unit
        MagneticFlux mf0 = new MagneticFlux(0.0, MagneticFlux.Unit.Wb);
        assertEquals(MagneticFlux.ZERO, mf0);
        assertEquals(0.0, MagneticFlux.ZERO.si(), 1E-12);

        MagneticFlux mf1 = new MagneticFlux(1.0, MagneticFlux.Unit.Wb);
        assertEquals(MagneticFlux.ONE, mf1);
        assertEquals(1.0, MagneticFlux.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(MagneticFlux.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, MagneticFlux.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, MagneticFlux.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, MagneticFlux.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, MagneticFlux.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        MagneticFlux two = new MagneticFlux(2.0, MagneticFlux.Unit.Wb);
        MagneticFlux copy = new MagneticFlux(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        MagneticFlux fromStr = new MagneticFlux(1.5, "Wb");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of
        MagneticFlux p1 = MagneticFlux.valueOf("2 Wb");
        assertEquals(2.0, p1.si(), 1E-12);

        MagneticFlux p2 = MagneticFlux.of(500.0, "Wb");
        assertEquals(500.0, p2.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation must match the SIUnit.of string used in Unit ("kgm2/s2A")
        assertEquals("kgm2/s2A", fromStr.siUnit().toString(true, false));

        // ofSi
        MagneticFlux neg = MagneticFlux.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test parsing and numeric correctness with prefixed SI units and CGS unit "Mx".
     */
    @Test
    void testPrefixedAndCgsUnits()
    {
        // Prefixed SI units should resolve (registered via generateSiPrefixes on Wb)
        MagneticFlux.Unit mWb = MagneticFlux.Unit.mWb;
        MagneticFlux.Unit muWb = MagneticFlux.Unit.muWb; // micro weber (mu prefix)
        MagneticFlux.Unit nWb = MagneticFlux.Unit.nWb;

        // 1 mWb == 1e-3 Wb
        MagneticFlux oneMilli = new MagneticFlux(1.0, mWb);
        assertEquals(1e-3, oneMilli.si(), 1e-12);

        // 1 muWb == 1e-6 Wb
        MagneticFlux oneMicro = new MagneticFlux(1.0, muWb);
        assertEquals(1e-6, oneMicro.si(), 1e-18);

        // 1 nWb == 1e-9 Wb
        MagneticFlux oneNano = new MagneticFlux(1.0, nWb);
        assertEquals(1e-9, oneNano.si(), 1e-21);

        // Registry-based parsing
        MagneticFlux v1 = MagneticFlux.valueOf("3 mWb"); // 3e-3 Wb
        assertEquals(3e-3, v1.si(), 1e-12);

        MagneticFlux v2 = MagneticFlux.of(2.5, "muWb"); // 2.5e-6 Wb
        assertEquals(2.5e-6, v2.si(), 1e-18);

        // CGS "Maxwell": 1 Mx = 1e-8 Wb
        MagneticFlux.Unit mx = MagneticFlux.Unit.Mx;
        MagneticFlux oneMx = new MagneticFlux(1.0, mx);
        assertEquals(1e-8, oneMx.si(), 1e-20);

        // Resolve Wb from registry (sanity)
        MagneticFlux.Unit resolved = Units.resolve(MagneticFlux.Unit.class, "Wb");
        assertEquals(MagneticFlux.Unit.Wb, resolved);
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, malformed number).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> MagneticFlux.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> MagneticFlux.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> MagneticFlux.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> MagneticFlux.valueOf("not-a-number Wb"));

        // of null unit
        assertThrows(NullPointerException.class, () -> MagneticFlux.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> MagneticFlux.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> MagneticFlux.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testMagneticFluxOperations()
    {
        // Wb / Wb -> Dimensionless
        var d1 = MagneticFlux.ONE.divide(MagneticFlux.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = MagneticFlux.ofSi(1.0).divide(MagneticFlux.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = MagneticFlux.ofSi(1.0).divide(MagneticFlux.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // Wb / V -> s (Duration)
        var t = MagneticFlux.ofSi(10.0).divide(ElectricPotential.ofSi(2.0));
        assertTrue(t instanceof Duration);
        assertEquals(5.0, t.si(), 1E-12);

        // Wb / s -> V (ElectricPotential)
        var v = MagneticFlux.ofSi(12.0).divide(Duration.ofSi(3.0));
        assertTrue(v instanceof ElectricPotential);
        assertEquals(4.0, v.si(), 1E-12);

        // Wb / m^2 -> T (MagneticFluxDensity)
        var b = MagneticFlux.ofSi(20.0).divide(Area.ofSi(4.0));
        assertTrue(b instanceof MagneticFluxDensity);
        assertEquals(5.0, b.si(), 1E-12);

        // Wb / T -> m^2 (Area)
        var a = MagneticFlux.ofSi(18.0).divide(MagneticFluxDensity.ofSi(3.0));
        assertTrue(a instanceof Area);
        assertEquals(6.0, a.si(), 1E-12);

        // Wb / A -> H (ElectricalInductance)
        var ind = MagneticFlux.ofSi(15.0).divide(ElectricCurrent.ofSi(3.0));
        assertTrue(ind instanceof ElectricalInductance);
        assertEquals(5.0, ind.si(), 1E-12);

        // Wb / H -> A (ElectricCurrent)
        var i = MagneticFlux.ofSi(21.0).divide(ElectricalInductance.ofSi(7.0));
        assertTrue(i instanceof ElectricCurrent);
        assertEquals(3.0, i.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units, non-linear derivation exception
     * branch, and registry of prefixed units.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(MagneticFlux.Unit.SI_UNIT, MagneticFlux.Unit.Wb.siUnit());
        assertEquals(MagneticFlux.Unit.SI, MagneticFlux.Unit.Wb.getBaseUnit());

        // Unit.ofSi delegates to MagneticFlux.ofSi
        MagneticFlux fromUnit = MagneticFlux.Unit.Wb.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (Wb) -> kWb (scale 1000)
        MagneticFlux.Unit kWb = MagneticFlux.Unit.Wb.deriveUnit("kWb", "kWb", "kiloweber", 1000.0, UnitSystem.SI_DERIVED);
        MagneticFlux x = new MagneticFlux(1.0, kWb); // 1 kWb == 1000 Wb
        assertEquals(1000.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        MagneticFlux.Unit nonLinear =
                new MagneticFlux.Unit("gWb", "gWb", "grade-like weber (nonlinear)", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });

        // Registry sanity for some prefixed units
        assertEquals(MagneticFlux.Unit.mWb, Units.resolve(MagneticFlux.Unit.class, "mWb"));
        assertEquals(MagneticFlux.Unit.muWb, Units.resolve(MagneticFlux.Unit.class, "muWb"));
        assertEquals(MagneticFlux.Unit.nWb, Units.resolve(MagneticFlux.Unit.class, "nWb"));
    }
}
