package org.djunits.value.vfloat.scalar.base;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.value.vdouble.scalar.ElectricalCharge;
import org.djunits.value.vfloat.scalar.FloatDimensionless;
import org.djunits.value.vfloat.scalar.FloatElectricalCharge;
import org.djunits.value.vfloat.scalar.FloatFrequency;
import org.djunits.value.vfloat.scalar.FloatMass;
import org.djunits.value.vfloat.scalar.FloatSIScalar;
import org.djunits.value.vfloat.scalar.FloatSpeed;

/**
 * Various physical constants. For many, see
 * <a href="https://en.wikipedia.org/wiki/2019_redefinition_of_the_SI_base_units">Wikipedia 2019 redefinition of the SI base
 * units</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers">Peter Knoppers</a>
 */
public final class FloatConstants
{
    /** Utility class. */
    private FloatConstants()
    {
        // utility class
    }

    /**
     * Number of constituent particles in a Mole; a.k.a. Avogadro's number. This value is exact since the 2019 redefinition of
     * the SI base units.
     */
    public static final FloatSIScalar AVOGADRO = FloatSIScalar.of(6.02214076e23f, "1/mol");

    /**
     * Boltzmann constant in Joule per Kelvin (this value is exact since the 2019 redefinition of SI base units). See
     * <a href="https://en.wikipedia.org/wiki/Boltzmann_constant">Wikipedia: Boltzmann constant</a>
     */
    public static final FloatSIScalar BOLTZMANN = FloatSIScalar.of(1.380649e-23f, "kgm2/s2K");

    /**
     * The Cesium 133 ground state hyperfine structure transition frequency. This value is exact since the 2006 redefinition of
     * the SI base units.
     */
    public static final FloatFrequency CESIUM133_FREQUENCY = FloatFrequency.of(9192631770f, "1/s");

    /** Electrical charge of one electron. This value is exact since the 2019 redefinition of the SI base units. */
    public static final FloatElectricalCharge ELECTRONCHARGE = new FloatElectricalCharge(-1.0f, ElectricalChargeUnit.ATOMIC_UNIT);

    /**
     * Mass of an electron. See <a href="https://en.wikipedia.org/wiki/Physical_constant">Wikipedia Physical constant</a>. This
     * value of this physical constant has an uncertainty of 2.8e-40 kg.
     */
    public static final FloatMass ELECTRONMASS = FloatMass.of(9.1093837015e-31f, "kg");

    /** ElectricalCharge of one proton. */
    public static final ElectricalCharge PROTONCHARGE = new ElectricalCharge(1, ElectricalChargeUnit.ATOMIC_UNIT);

    /**
     * Mass of a neutron. See <a href="https://en.wikipedia.org/wiki/List_of_physical_constants">Wikipedia List of physical
     * constants</a>. This value of this physical constant has an uncertainty of 9.5e-37 kg.
     */
    public static final FloatMass NEUTRONMASS = FloatMass.of(1.6749274714e-27f, "kg");

    /**
     * Mass of a proton. See <a href="https://en.wikipedia.org/wiki/Physical_constant">Wikipedia Physical constant</a>. This
     * value of this physical constant has an uncertainty of 5.1e-37.
     */
    public static final FloatMass PROTONMASS = FloatMass.of(1.67262192369e-27f, "kg");

    /**
     * Gravitational constant, a.k.a. Newtonian constant of gravitation. This is the 2018 best known approximation, which has an
     * uncertainty 1.5e-15 m^3/kgs^2
     */
    public static final FloatSIScalar G = FloatSIScalar.of(6.67430e-11f, "m3/kgs2");

    /** Speed of light in vacuum. This value is exact (since the 2006 redefinition of the SI base units). */
    public static final FloatSpeed LIGHTSPEED = FloatSpeed.of(299792458f, "m/s");

    /**
     * Permeability of vacuum. See <a href="https://en.wikipedia.org/wiki/Vacuum_permeability">Wikipedia, Vacuum
     * permeability</a>. The uncertainty of this value is 1.9e-16N/A^2.
     */
    public static final FloatSIScalar VACUUMPERMEABILITY = FloatSIScalar.of(1.25663706212e-6f, "kgm/s2A2");

    /**
     * Permittivity of vacuum. See <a href="https://en.wikipedia.org/wiki/Vacuum_permittivity">Wikipedia Vacuum
     * permittivity</a>. The uncertainty of this value is 1.3e-21 F/m.
     */
    public static final FloatSIScalar VACUUMPERMITTIVITY = FloatSIScalar.of(8.8541878128e-12f, "s4A2/kgm3");

    /** Impedance of vacuum. */
    public static final FloatSIScalar VACUUMIMPEDANCE = VACUUMPERMEABILITY.times(LIGHTSPEED);

    /**
     * Planck constant; ratio of a photon's energy and its frequency. This value is exact since the 2019 redefinition of the SI
     * base units.
     */
    public static final FloatSIScalar PLANCK = FloatSIScalar.of(6.62607015e-34f, "kgm2/s");

    /**
     * The luminous efficacy Kcd of monochromatic radiation of frequency 540×10^12 Hz (540 THz). This is the frequency of a
     * green-colored light at approximately the peak sensitivity of the human eye. This value is exact since the 2006
     * redefinition of the SI base units.
     */
    public static final FloatSIScalar LUMINOUS_EFFICACY_540THZ = FloatSIScalar.of(683f, "cdsrs3/kg");

    /** Ratio of a half circumference of a circle and its radius. */
    public static final FloatDimensionless PI = new FloatDimensionless(Math.PI, DimensionlessUnit.SI);

    /** Ratio of circumference of circle and its radius. */
    public static final FloatDimensionless TAU = new FloatDimensionless(Math.PI * 2, DimensionlessUnit.SI);

    /** Euler's constant. */
    public static final FloatDimensionless E = new FloatDimensionless(Math.E, DimensionlessUnit.SI);

    /** Golden ratio. */
    public static final FloatDimensionless PHI = new FloatDimensionless(0.5 + 0.5 * Math.sqrt(5.0), DimensionlessUnit.SI);


    /** Reduced Planck constant, a.k.a. angular Planck constant; Planck constant divided by 2 pi. */
    public static final FloatSIScalar PLANKREDUCED = PLANCK.divide(TAU);

}
