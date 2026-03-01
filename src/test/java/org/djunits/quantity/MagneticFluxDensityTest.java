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
 * MagneticFluxDensityTest tests the MagneticFluxDensity quantity class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information https://djutils.org. The DJUTILS project is distributed under a https://djutils.org/docs/license.html
 * three-clause BSD-style license.
 * @author Alexander
 */
class MagneticFluxDensityTest
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
    void testMagneticFluxDensityBasics()
    {
        // Construct with unit
        MagneticFluxDensity b0 = new MagneticFluxDensity(0.0, MagneticFluxDensity.Unit.T);
        assertEquals(MagneticFluxDensity.ZERO, b0);
        assertEquals(0.0, MagneticFluxDensity.ZERO.si(), 1E-12);

        MagneticFluxDensity b1 = new MagneticFluxDensity(1.0, MagneticFluxDensity.Unit.T);
        assertEquals(MagneticFluxDensity.ONE, b1);
        assertEquals(1.0, MagneticFluxDensity.ONE.si(), 1E-12);

        // Constants sanity
        assertTrue(Double.isNaN(MagneticFluxDensity.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, MagneticFluxDensity.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, MagneticFluxDensity.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, MagneticFluxDensity.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, MagneticFluxDensity.NEG_MAXVALUE.si());

        // Copy constructor preserves SI and display unit
        MagneticFluxDensity two = new MagneticFluxDensity(2.0, MagneticFluxDensity.Unit.T);
        MagneticFluxDensity copy = new MagneticFluxDensity(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Construct with abbreviation string
        MagneticFluxDensity fromStr = new MagneticFluxDensity(1.5, "T");
        assertEquals(1.5, fromStr.si(), 1E-12);

        // Parsing via valueOf and of
        MagneticFluxDensity p1 = MagneticFluxDensity.valueOf("2 T");
        assertEquals(2.0, p1.si(), 1E-12);

        MagneticFluxDensity p2 = MagneticFluxDensity.of(500.0, "T");
        assertEquals(500.0, p2.si(), 1E-12);

        // instantiate (delegates to ofSi)
        assertEquals(-10.1, fromStr.instantiate(-10.1).si(), 1E-12);

        // siUnit textual representation must match the SIUnit.of string used in Unit ("kg/s2A")
        assertEquals("kg/s2A", fromStr.siUnit().toString(true, false));

        // ofSi
        MagneticFluxDensity neg = MagneticFluxDensity.ofSi(-2.0);
        assertEquals(-2.0, neg.si(), 1E-12);
    }

    /**
     * Test parsing and numeric correctness with prefixed SI units and CGS Gauss.
     */
    @Test
    void testPrefixedAndCgsUnits()
    {
        // Prefixed SI units should resolve (registered via generateSiPrefixes on T)
        MagneticFluxDensity.Unit mT = MagneticFluxDensity.Unit.mT;
        MagneticFluxDensity.Unit muT = MagneticFluxDensity.Unit.muT; // micro tesla
        MagneticFluxDensity.Unit nT = MagneticFluxDensity.Unit.nT;

        // 1 mT == 1e-3 T
        MagneticFluxDensity oneMilli = new MagneticFluxDensity(1.0, mT);
        assertEquals(1e-3, oneMilli.si(), 1e-12);

        // 1 muT == 1e-6 T
        MagneticFluxDensity oneMicro = new MagneticFluxDensity(1.0, muT);
        assertEquals(1e-6, oneMicro.si(), 1e-18);

        // 1 nT == 1e-9 T
        MagneticFluxDensity oneNano = new MagneticFluxDensity(1.0, nT);
        assertEquals(1e-9, oneNano.si(), 1e-21);

        // Registry-based parsing
        MagneticFluxDensity v1 = MagneticFluxDensity.valueOf("3 mT"); // 3e-3 T
        assertEquals(3e-3, v1.si(), 1e-12);

        MagneticFluxDensity v2 = MagneticFluxDensity.of(2.5, "muT"); // 2.5e-6 T
        assertEquals(2.5e-6, v2.si(), 1e-18);

        // CGS "gauss": 1 G = 1e-4 T
        MagneticFluxDensity.Unit g = MagneticFluxDensity.Unit.G;
        MagneticFluxDensity oneGauss = new MagneticFluxDensity(1.0, g);
        assertEquals(1e-4, oneGauss.si(), 1e-16);

        // Resolve T from registry (sanity)
        MagneticFluxDensity.Unit resolved = Units.resolve(MagneticFluxDensity.Unit.class, "T");
        assertEquals(MagneticFluxDensity.Unit.T, resolved);
    }

    /**
     * Test error branches of valueOf and of (null, empty, unknown unit, malformed number).
     */
    @Test
    void testParsingErrorBranches()
    {
        // valueOf null
        assertThrows(NullPointerException.class, () -> MagneticFluxDensity.valueOf(null));

        // valueOf empty
        assertThrows(IllegalArgumentException.class, () -> MagneticFluxDensity.valueOf(""));

        // valueOf with unknown unit
        assertThrows(IllegalArgumentException.class, () -> MagneticFluxDensity.valueOf("10 blargh"));

        // valueOf with garbage number; NumberParser will throw => wrapped in IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> MagneticFluxDensity.valueOf("not-a-number T"));

        // of null unit
        assertThrows(NullPointerException.class, () -> MagneticFluxDensity.of(1.0, null));

        // of empty unit
        assertThrows(IllegalArgumentException.class, () -> MagneticFluxDensity.of(1.0, ""));

        // of unknown unit
        assertThrows(UnitRuntimeException.class, () -> MagneticFluxDensity.of(1.0, "blargh"));
    }

    /**
     * Test arithmetic operations.
     */
    @Test
    void testMagneticFluxDensityOperations()
    {
        // T / T -> Dimensionless
        var d1 = MagneticFluxDensity.ONE.divide(MagneticFluxDensity.ONE);
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(1.0, d1.si(), 1E-12);

        var d2 = MagneticFluxDensity.ofSi(1.0).divide(MagneticFluxDensity.ofSi(2.0));
        assertEquals(0.5, d2.si(), 1E-12);

        // Division by zero -> +Infinity (numeric sanity)
        var dInf = MagneticFluxDensity.ofSi(1.0).divide(MagneticFluxDensity.ofSi(0.0));
        assertTrue(Double.isInfinite(dInf.si()));
        assertEquals(Double.POSITIVE_INFINITY, dInf.si());

        // T * m^2 -> Wb (MagneticFlux)
        var flux = MagneticFluxDensity.ofSi(4.0).multiply(Area.ofSi(2.0));
        assertTrue(flux instanceof MagneticFlux);
        assertEquals(8.0, flux.si(), 1E-12);
    }

    /**
     * Test the Unit behavior: base/si unit, Unit.ofSi delegation, deriving linear units, non-linear derivation exception
     * branch, and registry of prefixed units.
     */
    @Test
    void testUnitBehavior()
    {
        // siUnit and base unit links
        assertEquals(MagneticFluxDensity.Unit.SI_UNIT, MagneticFluxDensity.Unit.T.siUnit());
        assertEquals(MagneticFluxDensity.Unit.SI, MagneticFluxDensity.Unit.T.getBaseUnit());

        // Unit.ofSi delegates to MagneticFluxDensity.ofSi
        MagneticFluxDensity fromUnit = MagneticFluxDensity.Unit.T.ofSi(2.5);
        assertEquals(2.5, fromUnit.si(), 1E-12);

        // Derive from a linear unit (T) -> kT (scale 1000)
        MagneticFluxDensity.Unit kT =
                MagneticFluxDensity.Unit.T.deriveUnit("kT", "kT", "kilotesla", 1000.0, UnitSystem.SI_DERIVED);
        MagneticFluxDensity x = new MagneticFluxDensity(1.0, kT); // 1 kT == 1000 T
        assertEquals(1000.0, x.si(), 1E-12);

        // Derive from a non-linear unit -> should throw UnitRuntimeException
        MagneticFluxDensity.Unit nonLinear = new MagneticFluxDensity.Unit("gT", "gT", "grade-like tesla (nonlinear)",
                new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { nonLinear.deriveUnit("bad", "bad", "nonlinear derived", 2.0, UnitSystem.OTHER); });

        // Registry sanity for some prefixed units
        assertEquals(MagneticFluxDensity.Unit.mT, Units.resolve(MagneticFluxDensity.Unit.class, "mT"));
        assertEquals(MagneticFluxDensity.Unit.muT, Units.resolve(MagneticFluxDensity.Unit.class, "muT"));
        assertEquals(MagneticFluxDensity.Unit.nT, Units.resolve(MagneticFluxDensity.Unit.class, "nT"));
    }
}
