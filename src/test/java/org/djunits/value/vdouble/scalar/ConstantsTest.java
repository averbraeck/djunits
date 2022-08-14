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
        // These constants are exact; tolerance is 0.
        assertEquals("Cesium frequency", 9192631770d, Constants.CESIUM133_FREQUENCY.si, 0);
        assertEquals("Cesium frequency", 9192631770d, FloatConstants.CESIUM133_FREQUENCY.si, 0);
        assertEquals("light speed", 299792458d, Constants.LIGHTSPEED.si, 0);
        assertEquals("light speed", 299792458d, FloatConstants.LIGHTSPEED.si, 0);
        assertEquals("Planck", 6.62607015e-34, Constants.PLANCK.si, 0);
        assertEquals("Planck", 6.62607015e-34, FloatConstants.PLANCK.si, 0);
        assertEquals("Boltzmann", 1.380649e-23, Constants.BOLTZMANN.si, 0);
        assertEquals("Boltzmann", 1.380649e-23, FloatConstants.BOLTZMANN.si, 0);
        assertEquals("Charge of electron", -1.602176634e-19, Constants.ELECTRONCHARGE.si, 0);
        assertEquals("Charge of electron", -1.602176634e-19, FloatConstants.ELECTRONCHARGE.si, 0);
        assertEquals("Charge of proton", 1.602176634e-19, Constants.PROTONCHARGE.si, 0);
        assertEquals("Charge of proton", 1.602176634e-19, FloatConstants.PROTONCHARGE.si, 0);
        assertEquals("Avogadro", 6.02214076e23, Constants.AVOGADRO.si, 0);
        assertEquals("Avogadro", 6.02214076e23, FloatConstants.AVOGADRO.si, 0);
        assertEquals("Base for the Lumen", 683, Constants.LUMINOUS_EFFICACY_540THZ.si, 0);
        assertEquals("Base for the Lumen", 683, FloatConstants.LUMINOUS_EFFICACY_540THZ.si, 0);
        assertEquals("Tau", Math.PI * 2, Constants.TAU.si, 0);
        assertEquals("Reduced Planck", Constants.PLANCK.si / 2 / Math.PI, Constants.PLANKREDUCED.si, 0);
        assertEquals("Reduced Planck", Constants.PLANCK.si / 2 / Math.PI, FloatConstants.PLANKREDUCED.si, 0);
        // These constants have a non-zero tolerance. The tolerances used are the precision at which these constants are
        // currently known. This should ensure that these test will still succeed when the values are refined in the future.
        assertEquals("Mass of electron", 9.1093837015e-31, Constants.ELECTRONMASS.si, 2.8e-40);
        assertEquals("Mass of electron", 9.1093837015e-31, FloatConstants.ELECTRONMASS.si, 2.8e-40);
        assertEquals("Mass of proton", 1.67262192369e-27, Constants.PROTONMASS.si, 5.1e-37);
        assertEquals("Mass of proton", 1.67262192369e-27, FloatConstants.PROTONMASS.si, 5.1e-37);
        assertEquals("Mass of neutron", 1.674927471e-27, Constants.NEUTRONMASS.si, 2.1e-35);
        assertEquals("Permeability of vacuum", 1.25663706212e-6, Constants.VACUUMPERMEABILITY.si, 1.9e-15);
        assertEquals("Permeability of vacuum", 1.25663706212e-6, FloatConstants.VACUUMPERMEABILITY.si, 1.9e-15);
        assertEquals("Permittivity of vacuum", 8.8541878128e-12, Constants.VACUUMPERMITTIVITY.si, 1.5e-20);
        assertEquals("Permittivity of vacuum", 8.8541878128e-12, FloatConstants.VACUUMPERMITTIVITY.si, 1.5e-20);
        assertEquals("Impedance of vacuum", 376.730313667d, Constants.VACUUMIMPEDANCE.si, 0.000000057);
        assertEquals("Impedance of vacuum", 376.730313667d, FloatConstants.VACUUMIMPEDANCE.si, 0.000000057);
    }

}
