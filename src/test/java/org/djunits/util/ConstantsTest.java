package org.djunits.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.djunits.quantity.Dimensionless;
import org.djunits.quantity.ElectricCharge;
import org.djunits.quantity.SIQuantity;
import org.djunits.unit.Unitless;
import org.djunits.unit.si.SIUnit;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Constants}. Although it is a constants holder, we can still verify:
 * <ul>
 * <li>Numerical values are as declared (within appropriate tolerances)</li>
 * <li>Dimensionality (SI units) of each constant</li>
 * <li>Key identities/relations, e.g., μ0·ε0·c² ≈ 1 and ħ = h / (2π)</li>
 * <li>Sign and units of elementary-charge based quantities</li>
 * </ul>
 * <p>
 * <strong>Notes on tolerances:</strong> Some constants include an uncertainty in the comment; relations like μ0·ε0·c² = 1 are
 * tested with a small tolerance to account for those stated uncertainties. Exact definitions (e.g., c, h, N_A) are still
 * subject to floating-point representation; we therefore use a tiny absolute/relative tolerance. Copyright (c) 2025-2026 Delft
 * University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See for project information
 * <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is distributed under a
 * <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class ConstantsTest
{
    /** Absolute comparison tolerance for floating-point equality. */
    private static final double ABS_EPS = 1e-20;

    /** Relative comparison tolerance for values with uncertainties / chained arithmetic. */
    private static final double REL_EPS = 1e-10;

    // ---------------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------------

    /**
     * Assert two doubles are close, using a hybrid absolute/relative tolerance.
     * @param expected expected value
     * @param actual actual value
     * @param relTol relative tolerance (fraction, e.g. 1e-12)
     * @param absTol absolute fallback tolerance
     */
    private static void assertClose(final double expected, final double actual, final double relTol, final double absTol)
    {
        if (expected == 0.0)
        {
            assertEquals(expected, actual, absTol);
        }
        else
        {
            double rel = Math.abs((actual - expected) / expected);
            if (Double.isFinite(rel))
            {
                assertTrue(rel <= relTol, () -> "relative error=" + rel + " exceeds " + relTol);
            }
            else
            {
                // Fallback if divide by ~0 created non-finite relative error
                assertEquals(expected, actual, absTol);
            }
        }
    }

    /**
     * Assert that a quantity has the expected SI unit (by dimensionality).
     * @param expectedUnitString SI unit string, e.g. "m3/kgs2"
     * @param q the quantity (any subclass of Quantity with an SIUnit backing)
     */
    private static void assertSiUnitEquals(final String expectedUnitString, final Object q)
    {
        SIUnit expected = SIUnit.of(expectedUnitString);
        SIUnit actual;
        if (q instanceof SIQuantity siq)
        {
            actual = siq.siUnit();
        }
        else if (q instanceof org.djunits.quantity.def.Quantity<?> qq)
        {
            actual = qq.siUnit();
        }
        else
        {
            fail("Unsupported type for SI-unit assertion: " + q.getClass());
            return;
        }
        assertEquals(expected, actual, "SI dimensionality mismatch for unit: " + expectedUnitString);
    }

    // ---------------------------------------------------------------------
    // Dimensionless mathematical constants
    // ---------------------------------------------------------------------

    /**
     * Verifies PI, TAU, E, PHI are {@link Dimensionless} with {@link Unitless#BASE} and correct values.
     */
    @Test
    void dimensionlessMathConstants()
    {
        // Types and units
        assertTrue(Constants.PI instanceof Dimensionless);
        assertTrue(Constants.TAU instanceof Dimensionless);
        assertTrue(Constants.E instanceof Dimensionless);
        assertTrue(Constants.PHI instanceof Dimensionless);
        assertTrue(Constants.PI.getDisplayUnit() instanceof Unitless);
        assertTrue(Constants.TAU.getDisplayUnit() instanceof Unitless);
        assertTrue(Constants.E.getDisplayUnit() instanceof Unitless);
        assertTrue(Constants.PHI.getDisplayUnit() instanceof Unitless);

        // Values
        assertClose(Math.PI, ((Dimensionless) Constants.PI).si(), REL_EPS, ABS_EPS);
        assertClose(2.0 * Math.PI, ((Dimensionless) Constants.TAU).si(), REL_EPS, ABS_EPS);
        assertClose(Math.E, ((Dimensionless) Constants.E).si(), REL_EPS, ABS_EPS);
        assertClose((1.0 + Math.sqrt(5.0)) / 2.0, ((Dimensionless) Constants.PHI).si(), REL_EPS, ABS_EPS);
    }

    // ---------------------------------------------------------------------
    // Constants with SI units: values and dimensionality
    // ---------------------------------------------------------------------

    /**
     * Verifies dimensionality and representative numeric values for fundamental constants.
     */
    @Test
    void siConstantsDimensionalityAndValues()
    {
        // Avogadro's constant: 6.02214076e23 1/mol (exact by SI definition)
        assertSiUnitEquals("1/mol", Constants.AVOGADRO);
        assertClose(6.02214076e23, Constants.AVOGADRO.si(), 1e-15, 1e-6);

        // Boltzmann constant: J/K = kg m^2 / (s^2 K)
        assertSiUnitEquals("kgm2/s2K", Constants.BOLTZMANN);
        assertClose(1.380649e-23, Constants.BOLTZMANN.si(), 1e-15, 1e-30);

        // Cesium-133 hyperfine frequency: Hz = 1/s
        assertSiUnitEquals("1/s", Constants.CESIUM133_FREQUENCY);
        assertClose(9_192_631_770d, Constants.CESIUM133_FREQUENCY.si(), 1e-15, 1e-9);

        // Elementary charge sign in 'e'
        assertEquals(-1.0, Constants.ELECTRONCHARGE.getInUnit(ElectricCharge.Unit.e), 0.0, "electron charge should be -1 e");
        assertEquals(+1.0, Constants.PROTONCHARGE.getInUnit(ElectricCharge.Unit.e), 0.0, "proton charge should be +1 e");

        // Electron/proton/neutron masses: kg
        assertSiUnitEquals("kg", Constants.ELECTRONMASS);
        assertSiUnitEquals("kg", Constants.PROTONMASS);
        assertSiUnitEquals("kg", Constants.NEUTRONMASS);
        assertTrue(Constants.ELECTRONMASS.si() > 0 && Constants.PROTONMASS.si() > 0 && Constants.NEUTRONMASS.si() > 0);

        // Gravitational constant: m^3 / (kg s^2)
        assertSiUnitEquals("m3/kgs2", Constants.G);

        // Speed of light: m/s (exact definition)
        assertSiUnitEquals("m/s", Constants.LIGHTSPEED);
        assertClose(299_792_458d, Constants.LIGHTSPEED.si(), 1e-15, 1e-9);

        // Vacuum permeability μ0: N/A^2 = kg m / (s^2 A^2)
        assertSiUnitEquals("kgm/s2A2", Constants.VACUUMPERMEABILITY);

        // Vacuum permittivity ε0: A^2 s^4 / (kg m^3)
        assertSiUnitEquals("s4A2/kgm3", Constants.VACUUMPERMITTIVITY);

        // Vacuum impedance Z0 should be of Ohm: kg m^2 / (s^3 A^2)
        assertSiUnitEquals("kgm2/s3A2", Constants.VACUUMIMPEDANCE);

        // Planck constant: J·s = kg m^2 / s
        assertSiUnitEquals("kgm2/s", Constants.PLANCK);

        // ħ = h / (2π)
        SIQuantity hbarExpected = Constants.PLANCK.divide(Constants.TAU);
        assertSiUnitEquals("kgm2/s", Constants.PLANCKREDUCED);
        assertClose(hbarExpected.si(), Constants.PLANCKREDUCED.si(), 1e-15, 1e-30);
    }

    /**
     * Verifies the physical identity μ0 · ε0 · c² ≈ 1 (dimensionless). Uses a modest tolerance due to stated uncertainties in
     * μ0 and ε0.
     */
    @Test
    void mu0TimesEpsilon0TimesCSquaredIsOne()
    {
        SIQuantity prod = Constants.VACUUMPERMEABILITY.multiply(Constants.VACUUMPERMITTIVITY).multiply(Constants.LIGHTSPEED)
                .multiply(Constants.LIGHTSPEED);
        // Dimensionality should collapse to dimensionless
        assertEquals(SIUnit.DIMLESS, prod.siUnit(), "μ0·ε0·c² must be dimensionless");
        // Numerically ~ 1
        assertClose(1.0, prod.si(), 1e-8, 1e-12);
    }

    /**
     * Verifies luminous efficacy dimensionality after fixing the unit string in Constants to include m^2 in the denominator.
     * <p>
     * <strong>Enable this test once you change</strong>
     * 
     * <pre>
     *  "cdsrs3/kg"  →  "cds3sr/kgm2"
     * </pre>
     */
    @Test
    void luminousEfficacyDimensionality()
    {
        // Expected units: (cd·sr) / W = cd·sr·s^3 / (kg·m^2)
        assertSiUnitEquals("cds3sr/kgm2", Constants.LUMINOUS_EFFICACY_540THZ);
        assertClose(683.0, Constants.LUMINOUS_EFFICACY_540THZ.si(), 1e-15, 1e-12);
    }
}
