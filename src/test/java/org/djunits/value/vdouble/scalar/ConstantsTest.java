package org.djunits.value.vdouble.scalar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.djunits.value.vdouble.scalar.base.Constants;
import org.djunits.value.vfloat.scalar.base.FloatConstants;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Constants class. <br>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers">Peter Knoppers</a>
 */
public class ConstantsTest
{

    /**
     * Test the Constants class.
     */
    @Test
    public void constantsTest()
    {
        // These constants are exact; tolerance is 0 for double values.
        // Tolerance is lower (1 ulp) for float values.
        assertEquals(9192631770d, Constants.CESIUM133_FREQUENCY.si, 0, "Cesium frequency");
        assertEquals(9192631770f, FloatConstants.CESIUM133_FREQUENCY.si, Math.ulp(9192631770f), "Cesium frequency");
        assertEquals(299792458d, Constants.LIGHTSPEED.si, 0, "light speed");
        assertEquals(299792458f, FloatConstants.LIGHTSPEED.si, Math.ulp(299792458f), "light speed");
        assertEquals(6.62607015e-34, Constants.PLANCK.si, 0, "Planck");
        assertEquals(6.62607015e-34f, FloatConstants.PLANCK.si, Math.ulp(6.62607015e-34f), "Planck");
        assertEquals(1.380649e-23, Constants.BOLTZMANN.si, 0, "Boltzmann");
        assertEquals(1.380649e-23f, FloatConstants.BOLTZMANN.si, Math.ulp(1.380649e-23f), "Boltzmann");
        assertEquals(-1.602176634e-19, Constants.ELECTRONCHARGE.si, 0, "Charge of electron");
        assertEquals(-1.602176634e-19f, FloatConstants.ELECTRONCHARGE.si, Math.ulp(-1.602176634e-19f), "Charge of electron");
        assertEquals(1.602176634e-19, Constants.PROTONCHARGE.si, 0, "Charge of proton");
        assertEquals(1.602176634e-19f, FloatConstants.PROTONCHARGE.si, Math.ulp(1.602176634e-19f), "Charge of proton");
        assertEquals(6.02214076e23, Constants.AVOGADRO.si, 0, "Avogadro");
        assertEquals(6.02214076e23f, FloatConstants.AVOGADRO.si, Math.ulp(6.02214076e23f), "Avogadro");
        assertEquals(683, Constants.LUMINOUS_EFFICACY_540THZ.si, 0, "Base for the Lumen");
        assertEquals(683, FloatConstants.LUMINOUS_EFFICACY_540THZ.si, 0, "Base for the Lumen");
        assertEquals(Math.PI, Constants.PI.si, 0, "Pi");
        assertEquals(Math.PI, FloatConstants.PI.si, Math.ulp((float) Math.PI), "Pi");
        assertEquals(Math.PI * 2, Constants.TAU.si, 0, "Tau");
        assertEquals(Math.PI * 2.0f, FloatConstants.TAU.si, Math.ulp((float) Math.PI * 2.0f), "Tau");
        assertEquals(Math.E, Constants.E.si, 0, "E");
        assertEquals(Math.E, FloatConstants.E.si, Math.ulp((float) Math.E * 1.0f), "E");
        assertEquals(1.61803398874989484820, Constants.PHI.si, Math.ulp(1.61803398874989484820), "Phi");
        assertEquals(1.61803398874989484820, FloatConstants.PHI.si, Math.ulp(1.61803398874989484820f), "Phi");
        assertEquals(1.0 + 1.0 / Constants.PHI.si, Constants.PHI.si, Math.ulp(1.61803398874989484820), "Phi");
        assertEquals(1.0f + 1.0f / FloatConstants.PHI.si, FloatConstants.PHI.si, Math.ulp(1.61803398874989484820f), "Phi");
        assertEquals(Constants.PLANCK.si / 2 / Math.PI, Constants.PLANCKREDUCED.si, 0, "Reduced Planck");
        assertEquals(Constants.PLANCK.si / 2f / Math.PI, FloatConstants.PLANCKREDUCED.si,
                Math.ulp(FloatConstants.PLANCK.si / 2.0f / (float) Math.PI), "Reduced Planck");
        // These constants have a non-zero tolerance. The tolerances used are the precision at which these constants are
        // currently known. This should ensure that these test will still succeed when the values are refined in the future.
        assertEquals(9.1093837015e-31, Constants.ELECTRONMASS.si, 2.8e-40, "Mass of electron");
        assertEquals(9.1093837015e-31f, FloatConstants.ELECTRONMASS.si, Math.ulp(9.1093837015e-31f), "Mass of electron");
        assertEquals(1.67262192369e-27, Constants.PROTONMASS.si, 5.1e-37, "Mass of proton");
        assertEquals(1.67262192369e-27f, FloatConstants.PROTONMASS.si, Math.ulp(1.67262192369e-27f), "Mass of proton");
        assertEquals(1.674927471e-27, Constants.NEUTRONMASS.si, 2.1e-35, "Mass of neutron");
        assertEquals(1.674927471e-27f, FloatConstants.NEUTRONMASS.si, Math.ulp(1.674927471e-27f), "Mass of neutron");
        assertEquals(1.25663706212e-6, Constants.VACUUMPERMEABILITY.si, 1.9e-15, "Permeability of vacuum");
        assertEquals(1.25663706212e-6f, FloatConstants.VACUUMPERMEABILITY.si, Math.ulp(1.25663706212e-6f),
                "Permeability of vacuum");
        assertEquals(8.8541878128e-12, Constants.VACUUMPERMITTIVITY.si, 1.5e-20, "Permittivity of vacuum");
        assertEquals(8.8541878128e-12f, FloatConstants.VACUUMPERMITTIVITY.si, Math.ulp(8.8541878128e-12f),
                "Permittivity of vacuum");
        assertEquals(376.730313667d, Constants.VACUUMIMPEDANCE.si, 0.000000057, "Impedance of vacuum");
        assertEquals(376.730313667f, FloatConstants.VACUUMIMPEDANCE.si, Math.ulp(376.730313667f), "Impedance of vacuum");
    }

}
