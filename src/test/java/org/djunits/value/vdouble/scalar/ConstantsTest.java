package org.djunits.value.vdouble.scalar;

import static org.junit.Assert.assertEquals;

import org.djunits.value.vdouble.scalar.base.Constants;
import org.djunits.value.vfloat.scalar.base.FloatConstants;
import org.junit.Test;

/**
 * Tests for the Constants class.
 * <p>
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
        assertEquals("Cesium frequency", 9192631770d, Constants.CESIUM133_FREQUENCY.si, 0);
        assertEquals("Cesium frequency", 9192631770f, FloatConstants.CESIUM133_FREQUENCY.si, Math.ulp(9192631770f));
        assertEquals("light speed", 299792458d, Constants.LIGHTSPEED.si, 0);
        assertEquals("light speed", 299792458f, FloatConstants.LIGHTSPEED.si, Math.ulp(299792458f));
        assertEquals("Planck", 6.62607015e-34, Constants.PLANCK.si, 0);
        assertEquals("Planck", 6.62607015e-34f, FloatConstants.PLANCK.si, Math.ulp(6.62607015e-34f));
        assertEquals("Boltzmann", 1.380649e-23, Constants.BOLTZMANN.si, 0);
        assertEquals("Boltzmann", 1.380649e-23f, FloatConstants.BOLTZMANN.si, Math.ulp(1.380649e-23f));
        assertEquals("Charge of electron", -1.602176634e-19, Constants.ELECTRONCHARGE.si, 0);
        assertEquals("Charge of electron", -1.602176634e-19f, FloatConstants.ELECTRONCHARGE.si, Math.ulp(-1.602176634e-19f));
        assertEquals("Charge of proton", 1.602176634e-19, Constants.PROTONCHARGE.si, 0);
        assertEquals("Charge of proton", 1.602176634e-19f, FloatConstants.PROTONCHARGE.si, Math.ulp(1.602176634e-19f));
        assertEquals("Avogadro", 6.02214076e23, Constants.AVOGADRO.si, 0);
        assertEquals("Avogadro", 6.02214076e23f, FloatConstants.AVOGADRO.si, Math.ulp(6.02214076e23f));
        assertEquals("Base for the Lumen", 683, Constants.LUMINOUS_EFFICACY_540THZ.si, 0);
        assertEquals("Base for the Lumen", 683, FloatConstants.LUMINOUS_EFFICACY_540THZ.si, 0);
        assertEquals("Pi", Math.PI, Constants.PI.si, 0);
        assertEquals("Pi", Math.PI, FloatConstants.PI.si, Math.ulp((float) Math.PI));
        assertEquals("Tau", Math.PI * 2, Constants.TAU.si, 0);
        assertEquals("Tau", Math.PI * 2.0f, FloatConstants.TAU.si, Math.ulp((float) Math.PI * 2.0f));
        assertEquals("E", Math.E, Constants.E.si, 0);
        assertEquals("E", Math.E, FloatConstants.E.si, Math.ulp((float) Math.E * 1.0f));
        assertEquals("Phi", 1.0 + 0.5 * Math.sqrt(5.0), Constants.PHI.si, 0);
        assertEquals("Phi", 1.0 + 0.5 * Math.sqrt(5.0), FloatConstants.PHI.si, Math.ulp((float) (1.0 + 0.5 * Math.sqrt(5.0))));
        assertEquals("Reduced Planck", Constants.PLANCK.si / 2 / Math.PI, Constants.PLANKREDUCED.si, 0);
        assertEquals("Reduced Planck", Constants.PLANCK.si / 2f / Math.PI, FloatConstants.PLANKREDUCED.si,
                Math.ulp(FloatConstants.PLANCK.si / 2.0f / (float) Math.PI));
        // These constants have a non-zero tolerance. The tolerances used are the precision at which these constants are
        // currently known. This should ensure that these test will still succeed when the values are refined in the future.
        assertEquals("Mass of electron", 9.1093837015e-31, Constants.ELECTRONMASS.si, 2.8e-40);
        assertEquals("Mass of electron", 9.1093837015e-31f, FloatConstants.ELECTRONMASS.si, Math.ulp(9.1093837015e-31f));
        assertEquals("Mass of proton", 1.67262192369e-27, Constants.PROTONMASS.si, 5.1e-37);
        assertEquals("Mass of proton", 1.67262192369e-27f, FloatConstants.PROTONMASS.si, Math.ulp(1.67262192369e-27f));
        assertEquals("Mass of neutron", 1.674927471e-27, Constants.NEUTRONMASS.si, 2.1e-35);
        assertEquals("Mass of neutron", 1.674927471e-27f, FloatConstants.NEUTRONMASS.si, Math.ulp(1.674927471e-27f));
        assertEquals("Permeability of vacuum", 1.25663706212e-6, Constants.VACUUMPERMEABILITY.si, 1.9e-15);
        assertEquals("Permeability of vacuum", 1.25663706212e-6f, FloatConstants.VACUUMPERMEABILITY.si, Math.ulp(1.25663706212e-6f));
        assertEquals("Permittivity of vacuum", 8.8541878128e-12, Constants.VACUUMPERMITTIVITY.si, 1.5e-20);
        assertEquals("Permittivity of vacuum", 8.8541878128e-12f, FloatConstants.VACUUMPERMITTIVITY.si, Math.ulp(8.8541878128e-12f));
        assertEquals("Impedance of vacuum", 376.730313667d, Constants.VACUUMIMPEDANCE.si, 0.000000057);
        assertEquals("Impedance of vacuum", 376.730313667f, FloatConstants.VACUUMIMPEDANCE.si, Math.ulp(376.730313667f));
    }

}
