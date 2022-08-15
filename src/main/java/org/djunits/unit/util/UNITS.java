package org.djunits.unit.util;

import javax.annotation.Generated;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.AbsorbedDoseUnit;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AmountOfSubstanceUnit;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.EquivalentDoseUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.IlluminanceUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.MomentumUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.PressureUnit;
import org.djunits.unit.RadioActivityUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.unit.TorqueUnit;
import org.djunits.unit.VolumeUnit;

/**
 * Easy access methods for many units. Can be used to create DoubleScalar, DoubleVector, DoubleMatrix classes and their Float
 * equivalents. Instead of <br>
 * <i>DoubleScalar.Rel&lt;LengthUnit&gt; margin = new DoubleScalar.Rel&lt;LengthUnit&gt;(0.2, LengthUnit.METER);</i><br>
 * we can now write <br>
 * <i>Length margin = new Length(0.2, METER);</i><br>
 * The compiler will automatically recognize which units belong to which quantity, and whether the quantity type and the unit
 * used are compatible.
 * <p>
 * To use these easy access methods, put a <code><b>implements</b> UNITS</code> at the end of your <code><b>class</b></code>
 * line.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@SuppressWarnings({"checkstyle:interfaceistype", "checkstyle:javadocmethod", "checkstyle:javadoctype",
        "checkstyle:javadocvariable", "checkstyle:linelength", "checkstyle:leftcurly", "checkstyle:rightcurly"})
@Generated(value = "org.djunits.generator.GenerateStaticUNITS", date = "2021-02-01T10:50:00.000+0100")
public interface UNITS
{
    // @formatter:off

    /****************************************************************************************************************/
    /******************************************** ABSOLUTETEMPERATURE  **********************************************/
    /****************************************************************************************************************/

    /** */ AbsoluteTemperatureUnit KELVIN_ABS            = AbsoluteTemperatureUnit.KELVIN;
    /** */ AbsoluteTemperatureUnit DEGREE_CELSIUS_ABS    = AbsoluteTemperatureUnit.DEGREE_CELSIUS;
    /** */ AbsoluteTemperatureUnit DEGREE_FAHRENHEIT_ABS = AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT;
    /** */ AbsoluteTemperatureUnit DEGREE_RANKINE_ABS    = AbsoluteTemperatureUnit.DEGREE_RANKINE;
    /** */ AbsoluteTemperatureUnit DEGREE_REAUMUR_ABS    = AbsoluteTemperatureUnit.DEGREE_REAUMUR;

    /****************************************************************************************************************/
    /************************************************ ACCELERATION **************************************************/
    /****************************************************************************************************************/

    /** */ AccelerationUnit METER_PER_SECOND_2           = AccelerationUnit.METER_PER_SECOND_2;
    /** */ AccelerationUnit KM_PER_HOUR_2                = AccelerationUnit.KM_PER_HOUR_2;
    /** */ AccelerationUnit FOOT_PER_SECOND_2            = AccelerationUnit.FOOT_PER_SECOND_2;
    /** */ AccelerationUnit INCH_PER_SECOND_2            = AccelerationUnit.INCH_PER_SECOND_2;
    /** */ AccelerationUnit MILE_PER_HOUR_2              = AccelerationUnit.MILE_PER_HOUR_2;
    /** */ AccelerationUnit MILE_PER_SECOND_2            = AccelerationUnit.MILE_PER_SECOND_2;
    /** */ AccelerationUnit KNOT_PER_SECOND              = AccelerationUnit.KNOT_PER_SECOND;
    /** */ AccelerationUnit MILE_PER_HOUR_PER_SECOND     = AccelerationUnit.MILE_PER_HOUR_PER_SECOND;
    /** */ AccelerationUnit STANDARD_GRAVITY             = AccelerationUnit.STANDARD_GRAVITY;
    /** */ AccelerationUnit GAL                          = AccelerationUnit.GAL;

    /****************************************************************************************************************/
    /*************************************************** ANGLE  *****************************************************/
    /****************************************************************************************************************/

    /** */ AngleUnit RADIAN                              = AngleUnit.RADIAN;
    /** */ AngleUnit PERCENT                             = AngleUnit.PERCENT;
    /** */ AngleUnit DEGREE                              = AngleUnit.DEGREE;
    /** */ AngleUnit ARCMINUTE                           = AngleUnit.ARCMINUTE;
    /** */ AngleUnit ARCSECOND                           = AngleUnit.ARCSECOND;
    /** */ AngleUnit GRAD                                = AngleUnit.GRAD;
    /** */ AngleUnit CENTESIMAL_ARCMINUTE                = AngleUnit.CENTESIMAL_ARCMINUTE;
    /** */ AngleUnit CENTESIMAL_ARCSECOND                = AngleUnit.CENTESIMAL_ARCSECOND;

    /****************************************************************************************************************/
    /******************************************** ANGULARACCELERATION  **********************************************/
    /****************************************************************************************************************/

    /** */ AngularAccelerationUnit RADIAN_PER_SECOND_SQUARED = AngularAccelerationUnit.RADIAN_PER_SECOND_SQUARED;
    /** */ AngularAccelerationUnit DEGREE_PER_SECOND_SQUARED = AngularAccelerationUnit.DEGREE_PER_SECOND_SQUARED;
    /** */ AngularAccelerationUnit ARCMINUTE_PER_SECOND_SQUARED = AngularAccelerationUnit.ARCMINUTE_PER_SECOND_SQUARED;
    /** */ AngularAccelerationUnit ARCSECOND_PER_SECOND_SQUARED = AngularAccelerationUnit.ARCSECOND_PER_SECOND_SQUARED;
    /** */ AngularAccelerationUnit GRAD_PER_SECOND_SQUARED = AngularAccelerationUnit.GRAD_PER_SECOND_SQUARED;
    /** */ AngularAccelerationUnit CENTESIMAL_ARCMINUTE_SQUARED = AngularAccelerationUnit.CENTESIMAL_ARCMINUTE_SQUARED;
    /** */ AngularAccelerationUnit CENTESIMAL_ARCSECOND_PER_SECOND_SQUARED = AngularAccelerationUnit.CENTESIMAL_ARCSECOND_PER_SECOND_SQUARED;

    /****************************************************************************************************************/
    /********************************************** ANGULARVELOCITY  ************************************************/
    /****************************************************************************************************************/

    /** */ AngularVelocityUnit RADIAN_PER_SECOND         = AngularVelocityUnit.RADIAN_PER_SECOND;
    /** */ AngularVelocityUnit DEGREE_PER_SECOND         = AngularVelocityUnit.DEGREE_PER_SECOND;
    /** */ AngularVelocityUnit ARCMINUTE_PER_SECOND      = AngularVelocityUnit.ARCMINUTE_PER_SECOND;
    /** */ AngularVelocityUnit ARCSECOND_PER_SECOND      = AngularVelocityUnit.ARCSECOND_PER_SECOND;
    /** */ AngularVelocityUnit GRAD_PER_SECOND           = AngularVelocityUnit.GRAD_PER_SECOND;
    /** */ AngularVelocityUnit CENTESIMAL_ARCMINUTE_PER_SECOND = AngularVelocityUnit.CENTESIMAL_ARCMINUTE_PER_SECOND;
    /** */ AngularVelocityUnit CENTESIMAL_ARCSECOND_PER_SECOND = AngularVelocityUnit.CENTESIMAL_ARCSECOND_PER_SECOND;

    /****************************************************************************************************************/
    /**************************************************** AREA ******************************************************/
    /****************************************************************************************************************/

    /** */ AreaUnit SQUARE_METER                         = AreaUnit.SQUARE_METER;
    /** */ AreaUnit SQUARE_MILLIMETER                    = AreaUnit.SQUARE_MILLIMETER;
    /** */ AreaUnit SQUARE_CENTIMETER                    = AreaUnit.SQUARE_CENTIMETER;
    /** */ AreaUnit SQUARE_DECIMETER                     = AreaUnit.SQUARE_DECIMETER;
    /** */ AreaUnit SQUARE_DECAMETER                     = AreaUnit.SQUARE_DECAMETER;
    /** */ AreaUnit SQUARE_HECTOMETER                    = AreaUnit.SQUARE_HECTOMETER;
    /** */ AreaUnit SQUARE_KILOMETER                     = AreaUnit.SQUARE_KILOMETER;
    /** */ AreaUnit CENTIARE                             = AreaUnit.CENTIARE;
    /** */ AreaUnit ARE                                  = AreaUnit.ARE;
    /** */ AreaUnit HECTARE                              = AreaUnit.HECTARE;
    /** */ AreaUnit SQUARE_MILE                          = AreaUnit.SQUARE_MILE;
    /** */ AreaUnit SQUARE_NAUTICAL_MILE                 = AreaUnit.SQUARE_NAUTICAL_MILE;
    /** */ AreaUnit SQUARE_FOOT                          = AreaUnit.SQUARE_FOOT;
    /** */ AreaUnit SQUARE_INCH                          = AreaUnit.SQUARE_INCH;
    /** */ AreaUnit SQUARE_YARD                          = AreaUnit.SQUARE_YARD;
    /** */ AreaUnit ACRE                                 = AreaUnit.ACRE;

    /****************************************************************************************************************/
    /************************************************** DENSITY  ****************************************************/
    /****************************************************************************************************************/

    /** */ DensityUnit KG_PER_METER_3                    = DensityUnit.KG_PER_METER_3;
    /** */ DensityUnit GRAM_PER_CENTIMETER_3             = DensityUnit.GRAM_PER_CENTIMETER_3;

    /****************************************************************************************************************/
    /*********************************************** DIMENSIONLESS  *************************************************/
    /****************************************************************************************************************/

    /** */ DimensionlessUnit UNIT                        = DimensionlessUnit.SI;

    /****************************************************************************************************************/
    /************************************************* DIRECTION  ***************************************************/
    /****************************************************************************************************************/

    /** */ DirectionUnit EAST_RADIAN                     = DirectionUnit.EAST_RADIAN;
    /** */ DirectionUnit EAST_DEGREE                     = DirectionUnit.EAST_DEGREE;
    /** */ DirectionUnit NORTH_RADIAN                    = DirectionUnit.NORTH_RADIAN;
    /** */ DirectionUnit NORTH_DEGREE                    = DirectionUnit.NORTH_DEGREE;

    /****************************************************************************************************************/
    /************************************************** DURATION ****************************************************/
    /****************************************************************************************************************/

    /** */ DurationUnit SECOND                           = DurationUnit.SECOND;
    /** */ DurationUnit MICROSECOND                      = DurationUnit.MICROSECOND;
    /** */ DurationUnit MILLISECOND                      = DurationUnit.MILLISECOND;
    /** */ DurationUnit MINUTE                           = DurationUnit.MINUTE;
    /** */ DurationUnit HOUR                             = DurationUnit.HOUR;
    /** */ DurationUnit DAY                              = DurationUnit.DAY;
    /** */ DurationUnit WEEK                             = DurationUnit.WEEK;

    /****************************************************************************************************************/
    /********************************************** ELECTRICALCHARGE ************************************************/
    /****************************************************************************************************************/

    /** */ ElectricalChargeUnit COULOMB                  = ElectricalChargeUnit.COULOMB;
    /** */ ElectricalChargeUnit MICROCOULOMB             = ElectricalChargeUnit.MICROCOULOMB;
    /** */ ElectricalChargeUnit MILLICOULOMB             = ElectricalChargeUnit.MILLICOULOMB;
    /** */ ElectricalChargeUnit AMPERE_HOUR              = ElectricalChargeUnit.AMPERE_HOUR;
    /** */ ElectricalChargeUnit MILLIAMPERE_HOUR         = ElectricalChargeUnit.MILLIAMPERE_HOUR;
    /** */ ElectricalChargeUnit MILLIAMPERE_SECOND       = ElectricalChargeUnit.MILLIAMPERE_SECOND;
    /** */ ElectricalChargeUnit KILOAMPERE_HOUR          = ElectricalChargeUnit.KILOAMPERE_HOUR;
    /** */ ElectricalChargeUnit MEGAAMPERE_HOUR          = ElectricalChargeUnit.MEGAAMPERE_HOUR;
    /** */ ElectricalChargeUnit FARADAY                  = ElectricalChargeUnit.FARADAY;
    /** */ ElectricalChargeUnit ATOMIC_UNIT              = ElectricalChargeUnit.ATOMIC_UNIT;
    /** */ ElectricalChargeUnit STATCOULOMB              = ElectricalChargeUnit.STATCOULOMB;
    /** */ ElectricalChargeUnit FRANKLIN                 = ElectricalChargeUnit.FRANKLIN;
    /** */ ElectricalChargeUnit ESU                      = ElectricalChargeUnit.ESU;
    /** */ ElectricalChargeUnit ABCOULOMB                = ElectricalChargeUnit.ABCOULOMB;
    /** */ ElectricalChargeUnit EMU                      = ElectricalChargeUnit.EMU;

    /****************************************************************************************************************/
    /********************************************* ELECTRICALCURRENT  ***********************************************/
    /****************************************************************************************************************/

    /** */ ElectricalCurrentUnit AMPERE                  = ElectricalCurrentUnit.AMPERE;
    /** */ ElectricalCurrentUnit MICROAMPERE             = ElectricalCurrentUnit.MICROAMPERE;
    /** */ ElectricalCurrentUnit MILLIAMPERE             = ElectricalCurrentUnit.MILLIAMPERE;
    /** */ ElectricalCurrentUnit KILOAMPERE              = ElectricalCurrentUnit.KILOAMPERE;
    /** */ ElectricalCurrentUnit MEGAAMPERE              = ElectricalCurrentUnit.MEGAAMPERE;
    /** */ ElectricalCurrentUnit STATAMPERE              = ElectricalCurrentUnit.STATAMPERE;
    /** */ ElectricalCurrentUnit ABAMPERE                = ElectricalCurrentUnit.ABAMPERE;

    /****************************************************************************************************************/
    /******************************************** ELECTRICALPOTENTIAL  **********************************************/
    /****************************************************************************************************************/

    /** */ ElectricalPotentialUnit VOLT                  = ElectricalPotentialUnit.VOLT;
    /** */ ElectricalPotentialUnit MICROVOLT             = ElectricalPotentialUnit.MICROVOLT;
    /** */ ElectricalPotentialUnit MILLIVOLT             = ElectricalPotentialUnit.MILLIVOLT;
    /** */ ElectricalPotentialUnit KILOVOLT              = ElectricalPotentialUnit.KILOVOLT;
    /** */ ElectricalPotentialUnit MEGAVOLT              = ElectricalPotentialUnit.MEGAVOLT;
    /** */ ElectricalPotentialUnit GIGAVOLT              = ElectricalPotentialUnit.GIGAVOLT;
    /** */ ElectricalPotentialUnit STATVOLT              = ElectricalPotentialUnit.STATVOLT;
    /** */ ElectricalPotentialUnit ABVOLT                = ElectricalPotentialUnit.ABVOLT;

    /****************************************************************************************************************/
    /******************************************** ELECTRICALRESISTANCE **********************************************/
    /****************************************************************************************************************/

    /** */ ElectricalResistanceUnit OHM                  = ElectricalResistanceUnit.OHM;
    /** */ ElectricalResistanceUnit MICROOHM             = ElectricalResistanceUnit.MICROOHM;
    /** */ ElectricalResistanceUnit MILLIOHM             = ElectricalResistanceUnit.MILLIOHM;
    /** */ ElectricalResistanceUnit KILOOHM              = ElectricalResistanceUnit.KILOOHM;
    /** */ ElectricalResistanceUnit MEGAOHM              = ElectricalResistanceUnit.MEGAOHM;
    /** */ ElectricalResistanceUnit GIGAOHM              = ElectricalResistanceUnit.GIGAOHM;
    /** */ ElectricalResistanceUnit ABOHM                = ElectricalResistanceUnit.ABOHM;
    /** */ ElectricalResistanceUnit STATOHM              = ElectricalResistanceUnit.STATOHM;

    /****************************************************************************************************************/
    /*************************************************** ENERGY *****************************************************/
    /****************************************************************************************************************/

    /** */ EnergyUnit JOULE                              = EnergyUnit.JOULE;
    /** */ EnergyUnit MICROJOULE                         = EnergyUnit.MICROJOULE;
    /** */ EnergyUnit MILLIJOULE                         = EnergyUnit.MILLIJOULE;
    /** */ EnergyUnit KILOJOULE                          = EnergyUnit.KILOJOULE;
    /** */ EnergyUnit MEGAJOULE                          = EnergyUnit.MEGAJOULE;
    /** */ EnergyUnit GIGAJOULE                          = EnergyUnit.GIGAJOULE;
    /** */ EnergyUnit TERAJOULE                          = EnergyUnit.TERAJOULE;
    /** */ EnergyUnit PETAJOULE                          = EnergyUnit.PETAJOULE;
    /** */ EnergyUnit FOOT_POUND_FORCE                   = EnergyUnit.FOOT_POUND_FORCE;
    /** */ EnergyUnit INCH_POUND_FORCE                   = EnergyUnit.INCH_POUND_FORCE;
    /** */ EnergyUnit BTU_ISO                            = EnergyUnit.BTU_ISO;
    /** */ EnergyUnit BTU_IT                             = EnergyUnit.BTU_IT;
    /** */ EnergyUnit CALORIE_IT                         = EnergyUnit.CALORIE_IT;
    /** */ EnergyUnit CALORIE                            = EnergyUnit.CALORIE;
    /** */ EnergyUnit KILOCALORIE                        = EnergyUnit.KILOCALORIE;
    /** */ EnergyUnit WATT_HOUR                          = EnergyUnit.WATT_HOUR;
    /** */ EnergyUnit MICROWATT_HOUR                     = EnergyUnit.MICROWATT_HOUR;
    /** */ EnergyUnit MILLIWATT_HOUR                     = EnergyUnit.MILLIWATT_HOUR;
    /** */ EnergyUnit KILOWATT_HOUR                      = EnergyUnit.KILOWATT_HOUR;
    /** */ EnergyUnit MEGAWATT_HOUR                      = EnergyUnit.MEGAWATT_HOUR;
    /** */ EnergyUnit GIGAWATT_HOUR                      = EnergyUnit.GIGAWATT_HOUR;
    /** */ EnergyUnit TERAWATT_HOUR                      = EnergyUnit.TERAWATT_HOUR;
    /** */ EnergyUnit PETAWATT_HOUR                      = EnergyUnit.PETAWATT_HOUR;
    /** */ EnergyUnit ENERGY_ELECTRONVOLT                = EnergyUnit.ELECTRONVOLT;
    /** */ EnergyUnit ENERGY_MICROELECTRONVOLT           = EnergyUnit.MICROELECTRONVOLT;
    /** */ EnergyUnit ENERGY_MILLIELECTRONVOLT           = EnergyUnit.MILLIELECTRONVOLT;
    /** */ EnergyUnit ENERGY_KILOELECTRONVOLT            = EnergyUnit.KILOELECTRONVOLT;
    /** */ EnergyUnit ENERGY_MEGAELECTRONVOLT            = EnergyUnit.MEGAELECTRONVOLT;
    /** */ EnergyUnit ENERGY_GIGAELECTRONVOLT            = EnergyUnit.GIGAELECTRONVOLT;
    /** */ EnergyUnit STHENE_METER                       = EnergyUnit.STHENE_METER;
    /** */ EnergyUnit ERG                                = EnergyUnit.ERG;

    /****************************************************************************************************************/
    /************************************************** FLOWMASS ****************************************************/
    /****************************************************************************************************************/

    /** */ FlowMassUnit KILOGRAM_PER_SECOND              = FlowMassUnit.KILOGRAM_PER_SECOND;
    /** */ FlowMassUnit POUND_PER_SECOND                 = FlowMassUnit.POUND_PER_SECOND;

    /****************************************************************************************************************/
    /************************************************* FLOWVOLUME ***************************************************/
    /****************************************************************************************************************/

    /** */ FlowVolumeUnit CUBIC_METER_PER_SECOND         = FlowVolumeUnit.CUBIC_METER_PER_SECOND;
    /** */ FlowVolumeUnit CUBIC_METER_PER_MINUTE         = FlowVolumeUnit.CUBIC_METER_PER_MINUTE;
    /** */ FlowVolumeUnit CUBIC_METER_PER_HOUR           = FlowVolumeUnit.CUBIC_METER_PER_HOUR;
    /** */ FlowVolumeUnit CUBIC_METER_PER_DAY            = FlowVolumeUnit.CUBIC_METER_PER_DAY;
    /** */ FlowVolumeUnit LITER_PER_SECOND               = FlowVolumeUnit.LITER_PER_SECOND;
    /** */ FlowVolumeUnit LITER_PER_MINUTE               = FlowVolumeUnit.LITER_PER_MINUTE;
    /** */ FlowVolumeUnit LITER_PER_HOUR                 = FlowVolumeUnit.LITER_PER_HOUR;
    /** */ FlowVolumeUnit LITER_PER_DAY                  = FlowVolumeUnit.LITER_PER_DAY;
    /** */ FlowVolumeUnit CUBIC_FEET_PER_SECOND          = FlowVolumeUnit.CUBIC_FEET_PER_SECOND;
    /** */ FlowVolumeUnit CUBIC_FEET_PER_MINUTE          = FlowVolumeUnit.CUBIC_FEET_PER_MINUTE;
    /** */ FlowVolumeUnit CUBIC_INCH_PER_SECOND          = FlowVolumeUnit.CUBIC_INCH_PER_SECOND;
    /** */ FlowVolumeUnit CUBIC_INCH_PER_MINUTE          = FlowVolumeUnit.CUBIC_INCH_PER_MINUTE;
    /** */ FlowVolumeUnit GALLON_US_PER_SECOND           = FlowVolumeUnit.GALLON_US_PER_SECOND;
    /** */ FlowVolumeUnit GALLON_US_PER_MINUTE           = FlowVolumeUnit.GALLON_US_PER_MINUTE;
    /** */ FlowVolumeUnit GALLON_US_PER_HOUR             = FlowVolumeUnit.GALLON_US_PER_HOUR;
    /** */ FlowVolumeUnit GALLON_US_PER_DAY              = FlowVolumeUnit.GALLON_US_PER_DAY;

    /****************************************************************************************************************/
    /*************************************************** FORCE  *****************************************************/
    /****************************************************************************************************************/

    /** */ ForceUnit NEWTON                              = ForceUnit.NEWTON;
    /** */ ForceUnit DYNE                                = ForceUnit.DYNE;
    /** */ ForceUnit KILOGRAM_FORCE                      = ForceUnit.KILOGRAM_FORCE;
    /** */ ForceUnit OUNCE_FORCE                         = ForceUnit.OUNCE_FORCE;
    /** */ ForceUnit POUND_FORCE                         = ForceUnit.POUND_FORCE;
    /** */ ForceUnit TON_FORCE                           = ForceUnit.TON_FORCE;
    /** */ ForceUnit STHENE                              = ForceUnit.STHENE;

    /****************************************************************************************************************/
    /************************************************* FREQUENCY  ***************************************************/
    /****************************************************************************************************************/

    /** */ FrequencyUnit HERTZ                           = FrequencyUnit.HERTZ;
    /** */ FrequencyUnit KILOHERTZ                       = FrequencyUnit.KILOHERTZ;
    /** */ FrequencyUnit MEGAHERTZ                       = FrequencyUnit.MEGAHERTZ;
    /** */ FrequencyUnit GIGAHERTZ                       = FrequencyUnit.GIGAHERTZ;
    /** */ FrequencyUnit TERAHERTZ                       = FrequencyUnit.TERAHERTZ;
    /** */ FrequencyUnit RPM                             = FrequencyUnit.RPM;
    /** */ FrequencyUnit PER_SECOND                      = FrequencyUnit.PER_SECOND;
    /** */ FrequencyUnit PER_MICROSECOND                 = FrequencyUnit.PER_MICROSECOND;
    /** */ FrequencyUnit PER_MILLISECOND                 = FrequencyUnit.PER_MILLISECOND;
    /** */ FrequencyUnit PER_MINUTE                      = FrequencyUnit.PER_MINUTE;
    /** */ FrequencyUnit PER_HOUR                        = FrequencyUnit.PER_HOUR;
    /** */ FrequencyUnit PER_DAY                         = FrequencyUnit.PER_DAY;
    /** */ FrequencyUnit PER_WEEK                        = FrequencyUnit.PER_WEEK;

    /****************************************************************************************************************/
    /*************************************************** LENGTH *****************************************************/
    /****************************************************************************************************************/

    /** */ LengthUnit METER                              = LengthUnit.METER;
    /** */ LengthUnit NANOMETER                          = LengthUnit.NANOMETER;
    /** */ LengthUnit MICROMETER                         = LengthUnit.MICROMETER;
    /** */ LengthUnit MILLIMETER                         = LengthUnit.MILLIMETER;
    /** */ LengthUnit CENTIMETER                         = LengthUnit.CENTIMETER;
    /** */ LengthUnit DECIMETER                          = LengthUnit.DECIMETER;
    /** */ LengthUnit DECAMETER                          = LengthUnit.DECAMETER;
    /** */ LengthUnit HECTOMETER                         = LengthUnit.HECTOMETER;
    /** */ LengthUnit KILOMETER                          = LengthUnit.KILOMETER;
    /** */ LengthUnit FOOT                               = LengthUnit.FOOT;
    /** */ LengthUnit INCH                               = LengthUnit.INCH;
    /** */ LengthUnit YARD                               = LengthUnit.YARD;
    /** */ LengthUnit MILE                               = LengthUnit.MILE;
    /** */ LengthUnit NAUTICAL_MILE                      = LengthUnit.NAUTICAL_MILE;
    /** */ LengthUnit ASTRONOMICAL_UNIT                  = LengthUnit.ASTRONOMICAL_UNIT;
    /** */ LengthUnit LIGHTYEAR                          = LengthUnit.LIGHTYEAR;
    /** */ LengthUnit PARSEC                             = LengthUnit.PARSEC;
    /** */ LengthUnit ANGSTROM                           = LengthUnit.ANGSTROM;

    /****************************************************************************************************************/
    /*********************************************** LINEARDENSITY  *************************************************/
    /****************************************************************************************************************/

    /** */ LinearDensityUnit PER_METER                   = LinearDensityUnit.PER_METER;
    /** */ LinearDensityUnit PER_MICROMETER              = LinearDensityUnit.PER_MICROMETER;
    /** */ LinearDensityUnit PER_MILLIMETER              = LinearDensityUnit.PER_MILLIMETER;
    /** */ LinearDensityUnit PER_CENTIMETER              = LinearDensityUnit.PER_CENTIMETER;
    /** */ LinearDensityUnit PER_DECIMETER               = LinearDensityUnit.PER_DECIMETER;
    /** */ LinearDensityUnit PER_DECAMETER               = LinearDensityUnit.PER_DECAMETER;
    /** */ LinearDensityUnit PER_HECTOMETER              = LinearDensityUnit.PER_HECTOMETER;
    /** */ LinearDensityUnit PER_KILOMETER               = LinearDensityUnit.PER_KILOMETER;
    /** */ LinearDensityUnit PER_FOOT                    = LinearDensityUnit.PER_FOOT;
    /** */ LinearDensityUnit PER_INCH                    = LinearDensityUnit.PER_INCH;
    /** */ LinearDensityUnit PER_YARD                    = LinearDensityUnit.PER_YARD;
    /** */ LinearDensityUnit PER_MILE                    = LinearDensityUnit.PER_MILE;
    /** */ LinearDensityUnit PER_NAUTICAL_MILE           = LinearDensityUnit.PER_NAUTICAL_MILE;
    /** */ LinearDensityUnit PER_ASTRONOMICAL_UNIT       = LinearDensityUnit.PER_ASTRONOMICAL_UNIT;
    /** */ LinearDensityUnit PER_LIGHTYEAR               = LinearDensityUnit.PER_LIGHTYEAR;
    /** */ LinearDensityUnit PER_PARSEC                  = LinearDensityUnit.PER_PARSEC;
    /** */ LinearDensityUnit PER_ANGSTROM                = LinearDensityUnit.PER_ANGSTROM;

    /****************************************************************************************************************/
    /**************************************************** MASS ******************************************************/
    /****************************************************************************************************************/

    /** */ MassUnit KILOGRAM                             = MassUnit.KILOGRAM;
    /** */ MassUnit GRAM                                 = MassUnit.GRAM;
    /** */ MassUnit MICROGRAM                            = MassUnit.MICROGRAM;
    /** */ MassUnit MILLIGRAM                            = MassUnit.MILLIGRAM;
    /** */ MassUnit POUND                                = MassUnit.POUND;
    /** */ MassUnit OUNCE                                = MassUnit.OUNCE;
    /** */ MassUnit TON_LONG                             = MassUnit.TON_LONG;
    /** */ MassUnit TON_SHORT                            = MassUnit.TON_SHORT;
    /** */ MassUnit TON_METRIC                           = MassUnit.TON_METRIC;
    /** */ MassUnit TONNE                                = MassUnit.TONNE;
    /** */ MassUnit DALTON                               = MassUnit.DALTON;
    /** */ MassUnit MASS_ELECTRONVOLT                    = MassUnit.ELECTRONVOLT;
    /** */ MassUnit MASS_MICROELECTRONVOLT               = MassUnit.MICROELECTRONVOLT;
    /** */ MassUnit MASS_MILLIELECTRONVOLT               = MassUnit.MILLIELECTRONVOLT;
    /** */ MassUnit MASS_KILOELECTRONVOLT                = MassUnit.KILOELECTRONVOLT;
    /** */ MassUnit MASS_MEGAELECTRONVOLT                = MassUnit.MEGAELECTRONVOLT;
    /** */ MassUnit MASS_GIGAELECTRONVOLT                = MassUnit.GIGAELECTRONVOLT;

    /****************************************************************************************************************/
    /************************************************** MOMENTUM ****************************************************/
    /****************************************************************************************************************/

    /** */ MomentumUnit KILOGRAM_METER_PER_SECOND        = MomentumUnit.KILOGRAM_METER_PER_SECOND;

    /****************************************************************************************************************/
    /************************************************** POSITION ****************************************************/
    /****************************************************************************************************************/

    /** */ PositionUnit METER_ABS                        = PositionUnit.METER;
    /** */ PositionUnit NANOMETER_ABS                    = PositionUnit.NANOMETER;
    /** */ PositionUnit MICROMETER_ABS                   = PositionUnit.MICROMETER;
    /** */ PositionUnit MILLIMETER_ABS                   = PositionUnit.MILLIMETER;
    /** */ PositionUnit CENTIMETER_ABS                   = PositionUnit.CENTIMETER;
    /** */ PositionUnit DECIMETER_ABS                    = PositionUnit.DECIMETER;
    /** */ PositionUnit DECAMETER_ABS                    = PositionUnit.DECAMETER;
    /** */ PositionUnit HECTOMETER_ABS                   = PositionUnit.HECTOMETER;
    /** */ PositionUnit KILOMETER_ABS                    = PositionUnit.KILOMETER;
    /** */ PositionUnit FOOT_ABS                         = PositionUnit.FOOT;
    /** */ PositionUnit INCH_ABS                         = PositionUnit.INCH;
    /** */ PositionUnit YARD_ABS                         = PositionUnit.YARD;
    /** */ PositionUnit MILE_ABS                         = PositionUnit.MILE;
    /** */ PositionUnit NAUTICAL_MILE_ABS                = PositionUnit.NAUTICAL_MILE;
    /** */ PositionUnit ASTRONOMICAL_UNIT_ABS            = PositionUnit.ASTRONOMICAL_UNIT;
    /** */ PositionUnit LIGHTYEAR_ABS                    = PositionUnit.LIGHTYEAR;
    /** */ PositionUnit PARSEC_ABS                       = PositionUnit.PARSEC;
    /** */ PositionUnit ANGSTROM_ABS                     = PositionUnit.ANGSTROM;

    /****************************************************************************************************************/
    /*************************************************** POWER  *****************************************************/
    /****************************************************************************************************************/

    /** */ PowerUnit WATT                                = PowerUnit.WATT;
    /** */ PowerUnit MICROWATT                           = PowerUnit.MICROWATT;
    /** */ PowerUnit MILLIWATT                           = PowerUnit.MILLIWATT;
    /** */ PowerUnit KILOWATT                            = PowerUnit.KILOWATT;
    /** */ PowerUnit MEGAWATT                            = PowerUnit.MEGAWATT;
    /** */ PowerUnit GIGAWATT                            = PowerUnit.GIGAWATT;
    /** */ PowerUnit TERAWATT                            = PowerUnit.TERAWATT;
    /** */ PowerUnit PETAWATT                            = PowerUnit.PETAWATT;
    /** */ PowerUnit FOOT_POUND_FORCE_PER_HOUR           = PowerUnit.FOOT_POUND_FORCE_PER_HOUR;
    /** */ PowerUnit FOOT_POUND_FORCE_PER_MINUTE         = PowerUnit.FOOT_POUND_FORCE_PER_MINUTE;
    /** */ PowerUnit FOOT_POUND_FORCE_PER_SECOND         = PowerUnit.FOOT_POUND_FORCE_PER_SECOND;
    /** */ PowerUnit HORSEPOWER_METRIC                   = PowerUnit.HORSEPOWER_METRIC;
    /** */ PowerUnit STHENE_METER_PER_SECOND             = PowerUnit.STHENE_METER_PER_SECOND;
    /** */ PowerUnit ERG_PER_SECOND                      = PowerUnit.ERG_PER_SECOND;

    /****************************************************************************************************************/
    /************************************************** PRESSURE ****************************************************/
    /****************************************************************************************************************/

    /** */ PressureUnit PASCAL                           = PressureUnit.PASCAL;
    /** */ PressureUnit HECTOPASCAL                      = PressureUnit.HECTOPASCAL;
    /** */ PressureUnit KILOPASCAL                       = PressureUnit.KILOPASCAL;
    /** */ PressureUnit ATMOSPHERE_STANDARD              = PressureUnit.ATMOSPHERE_STANDARD;
    /** */ PressureUnit TORR                             = PressureUnit.TORR;
    /** */ PressureUnit ATMOSPHERE_TECHNICAL             = PressureUnit.ATMOSPHERE_TECHNICAL;
    /** */ PressureUnit BARYE                            = PressureUnit.BARYE;
    /** */ PressureUnit BAR                              = PressureUnit.BAR;
    /** */ PressureUnit MILLIBAR                         = PressureUnit.MILLIBAR;
    /** */ PressureUnit CENTIMETER_MERCURY               = PressureUnit.CENTIMETER_MERCURY;
    /** */ PressureUnit MILLIMETER_MERCURY               = PressureUnit.MILLIMETER_MERCURY;
    /** */ PressureUnit FOOT_MERCURY                     = PressureUnit.FOOT_MERCURY;
    /** */ PressureUnit INCH_MERCURY                     = PressureUnit.INCH_MERCURY;
    /** */ PressureUnit KGF_PER_SQUARE_MM                = PressureUnit.KGF_PER_SQUARE_MM;
    /** */ PressureUnit POUND_PER_SQUARE_FOOT            = PressureUnit.POUND_PER_SQUARE_FOOT;
    /** */ PressureUnit POUND_PER_SQUARE_INCH            = PressureUnit.POUND_PER_SQUARE_INCH;
    /** */ PressureUnit PIEZE                            = PressureUnit.PIEZE;

    /****************************************************************************************************************/
    /************************************************* SOLIDANGLE ***************************************************/
    /****************************************************************************************************************/

    /** */ SolidAngleUnit STERADIAN                      = SolidAngleUnit.STERADIAN;
    /** */ SolidAngleUnit SQUARE_DEGREE                  = SolidAngleUnit.SQUARE_DEGREE;

    /****************************************************************************************************************/
    /*************************************************** SPEED  *****************************************************/
    /****************************************************************************************************************/

    /** */ SpeedUnit METER_PER_SECOND                    = SpeedUnit.METER_PER_SECOND;
    /** */ SpeedUnit METER_PER_HOUR                      = SpeedUnit.METER_PER_HOUR;
    /** */ SpeedUnit KM_PER_SECOND                       = SpeedUnit.KM_PER_SECOND;
    /** */ SpeedUnit KM_PER_HOUR                         = SpeedUnit.KM_PER_HOUR;
    /** */ SpeedUnit INCH_PER_SECOND                     = SpeedUnit.INCH_PER_SECOND;
    /** */ SpeedUnit INCH_PER_MINUTE                     = SpeedUnit.INCH_PER_MINUTE;
    /** */ SpeedUnit INCH_PER_HOUR                       = SpeedUnit.INCH_PER_HOUR;
    /** */ SpeedUnit FOOT_PER_SECOND                     = SpeedUnit.FOOT_PER_SECOND;
    /** */ SpeedUnit FOOT_PER_MINUTE                     = SpeedUnit.FOOT_PER_MINUTE;
    /** */ SpeedUnit FOOT_PER_HOUR                       = SpeedUnit.FOOT_PER_HOUR;
    /** */ SpeedUnit MILE_PER_SECOND                     = SpeedUnit.MILE_PER_SECOND;
    /** */ SpeedUnit MILE_PER_MINUTE                     = SpeedUnit.MILE_PER_MINUTE;
    /** */ SpeedUnit MILE_PER_HOUR                       = SpeedUnit.MILE_PER_HOUR;
    /** */ SpeedUnit KNOT                                = SpeedUnit.KNOT;

    /****************************************************************************************************************/
    /************************************************ TEMPERATURE  **************************************************/
    /****************************************************************************************************************/

    /** */ TemperatureUnit KELVIN                        = TemperatureUnit.KELVIN;
    /** */ TemperatureUnit DEGREE_CELSIUS                = TemperatureUnit.DEGREE_CELSIUS;
    /** */ TemperatureUnit DEGREE_FAHRENHEIT             = TemperatureUnit.DEGREE_FAHRENHEIT;
    /** */ TemperatureUnit DEGREE_RANKINE                = TemperatureUnit.DEGREE_RANKINE;
    /** */ TemperatureUnit DEGREE_REAUMUR                = TemperatureUnit.DEGREE_REAUMUR;

    /****************************************************************************************************************/
    /**************************************************** TIME ******************************************************/
    /****************************************************************************************************************/

    /** */ TimeUnit BASE_SECOND                          = TimeUnit.BASE_SECOND;
    /** */ TimeUnit BASE_MICROSECOND                     = TimeUnit.BASE_MICROSECOND;
    /** */ TimeUnit BASE_MILLISECOND                     = TimeUnit.BASE_MILLISECOND;
    /** */ TimeUnit BASE_MINUTE                          = TimeUnit.BASE_MINUTE;
    /** */ TimeUnit BASE_HOUR                            = TimeUnit.BASE_HOUR;
    /** */ TimeUnit BASE_DAY                             = TimeUnit.BASE_DAY;
    /** */ TimeUnit BASE_WEEK                            = TimeUnit.BASE_WEEK;
    /** */ TimeUnit EPOCH_SECOND                         = TimeUnit.EPOCH_SECOND;
    /** */ TimeUnit EPOCH_MICROSECOND                    = TimeUnit.EPOCH_MICROSECOND;
    /** */ TimeUnit EPOCH_MILLISECOND                    = TimeUnit.EPOCH_MILLISECOND;
    /** */ TimeUnit EPOCH_MINUTE                         = TimeUnit.EPOCH_MINUTE;
    /** */ TimeUnit EPOCH_HOUR                           = TimeUnit.EPOCH_HOUR;
    /** */ TimeUnit EPOCH_DAY                            = TimeUnit.EPOCH_DAY;
    /** */ TimeUnit EPOCH_WEEK                           = TimeUnit.EPOCH_WEEK;
    /** */ TimeUnit EPOCH_YEAR1_SECOND                   = TimeUnit.EPOCH_YEAR1_SECOND;
    /** */ TimeUnit EPOCH_J2000_SECOND                   = TimeUnit.EPOCH_J2000_SECOND;

    /****************************************************************************************************************/
    /*************************************************** TORQUE *****************************************************/
    /****************************************************************************************************************/

    /** */ TorqueUnit NEWTON_METER                       = TorqueUnit.NEWTON_METER;
    /** */ TorqueUnit METER_KILOGRAM_FORCE               = TorqueUnit.METER_KILOGRAM_FORCE;
    /** */ TorqueUnit POUND_FOOT                         = TorqueUnit.POUND_FOOT;
    /** */ TorqueUnit POUND_INCH                         = TorqueUnit.POUND_INCH;

    /****************************************************************************************************************/
    /*************************************************** VOLUME *****************************************************/
    /****************************************************************************************************************/

    /** */ VolumeUnit CUBIC_METER                        = VolumeUnit.CUBIC_METER;
    /** */ VolumeUnit CUBIC_MILLIMETER                   = VolumeUnit.CUBIC_MILLIMETER;
    /** */ VolumeUnit CUBIC_CENTIMETER                   = VolumeUnit.CUBIC_CENTIMETER;
    /** */ VolumeUnit CUBIC_DECIMETER                    = VolumeUnit.CUBIC_DECIMETER;
    /** */ VolumeUnit CUBIC_DECAMETER                    = VolumeUnit.CUBIC_DECAMETER;
    /** */ VolumeUnit CUBIC_HECTOMETER                   = VolumeUnit.CUBIC_HECTOMETER;
    /** */ VolumeUnit CUBIC_KILOMETER                    = VolumeUnit.CUBIC_KILOMETER;
    /** */ VolumeUnit CUBIC_MILE                         = VolumeUnit.CUBIC_MILE;
    /** */ VolumeUnit CUBIC_NAUTICAL_MILE                = VolumeUnit.CUBIC_NAUTICAL_MILE;
    /** */ VolumeUnit CUBIC_FOOT                         = VolumeUnit.CUBIC_FOOT;
    /** */ VolumeUnit CUBIC_INCH                         = VolumeUnit.CUBIC_INCH;
    /** */ VolumeUnit CUBIC_YARD                         = VolumeUnit.CUBIC_YARD;
    /** */ VolumeUnit LITER                              = VolumeUnit.LITER;
    /** */ VolumeUnit GALLON_US                          = VolumeUnit.GALLON_US;
    /** */ VolumeUnit GALLON_IMP                         = VolumeUnit.GALLON_IMP;
    /** */ VolumeUnit QUART_US                           = VolumeUnit.QUART_US;
    /** */ VolumeUnit QUART_IMP                          = VolumeUnit.QUART_IMP;
    /** */ VolumeUnit PINT_US                            = VolumeUnit.PINT_US;
    /** */ VolumeUnit PINT_IMP                           = VolumeUnit.PINT_IMP;
    /** */ VolumeUnit FLUID_OUNCE_US                     = VolumeUnit.FLUID_OUNCE_US;
    /** */ VolumeUnit FLUID_OUNCE_IMP                    = VolumeUnit.FLUID_OUNCE_IMP;
    /** */ VolumeUnit CUBIC_LIGHTYEAR                    = VolumeUnit.CUBIC_LIGHTYEAR;
    /** */ VolumeUnit CUBIC_PARSEC                       = VolumeUnit.CUBIC_PARSEC;

    /****************************************************************************************************************/
    /************************************************ ABSORBEDDOSE **************************************************/
    /****************************************************************************************************************/

    /** */ AbsorbedDoseUnit GRAY                         = AbsorbedDoseUnit.GRAY;
    /** */ AbsorbedDoseUnit MILLIGRAY                    = AbsorbedDoseUnit.MILLIGRAY;
    /** */ AbsorbedDoseUnit MICROGRAY                    = AbsorbedDoseUnit.MICROGRAY;
    /** */ AbsorbedDoseUnit ERG_PER_GRAM                 = AbsorbedDoseUnit.ERG_PER_GRAM;
    /** */ AbsorbedDoseUnit RAD                          = AbsorbedDoseUnit.RAD;

    /****************************************************************************************************************/
    /********************************************* AMOUNTOFSUBSTANCE  ***********************************************/
    /****************************************************************************************************************/

    /** */ AmountOfSubstanceUnit MOLE                    = AmountOfSubstanceUnit.MOLE;
    /** */ AmountOfSubstanceUnit MILLIMOLE               = AmountOfSubstanceUnit.MILLIMOLE;
    /** */ AmountOfSubstanceUnit MICROMOLE               = AmountOfSubstanceUnit.MICROMOLE;
    /** */ AmountOfSubstanceUnit NANOMOLE                = AmountOfSubstanceUnit.NANOMOLE;

    /****************************************************************************************************************/
    /********************************************* CATALYTICACTIVITY  ***********************************************/
    /****************************************************************************************************************/

    /** */ CatalyticActivityUnit KATAL                   = CatalyticActivityUnit.KATAL;
    /** */ CatalyticActivityUnit MILLIKATAL              = CatalyticActivityUnit.MILLIKATAL;
    /** */ CatalyticActivityUnit MICROKATAL              = CatalyticActivityUnit.MICROKATAL;
    /** */ CatalyticActivityUnit NANOKATAL               = CatalyticActivityUnit.NANOKATAL;

    /****************************************************************************************************************/
    /******************************************* ELECTRICALCAPACITANCE  *********************************************/
    /****************************************************************************************************************/

    /** */ ElectricalCapacitanceUnit FARAD               = ElectricalCapacitanceUnit.FARAD;
    /** */ ElectricalCapacitanceUnit MILLIFARAD          = ElectricalCapacitanceUnit.MILLIFARAD;
    /** */ ElectricalCapacitanceUnit MICROFARAD          = ElectricalCapacitanceUnit.MICROFARAD;
    /** */ ElectricalCapacitanceUnit NANOFARAD           = ElectricalCapacitanceUnit.NANOFARAD;
    /** */ ElectricalCapacitanceUnit PICOFARAD           = ElectricalCapacitanceUnit.PICOFARAD;

    /****************************************************************************************************************/
    /******************************************* ELECTRICALCONDUCTANCE  *********************************************/
    /****************************************************************************************************************/

    /** */ ElectricalConductanceUnit SIEMENS             = ElectricalConductanceUnit.SIEMENS;
    /** */ ElectricalConductanceUnit MILLISIEMENS        = ElectricalConductanceUnit.MILLISIEMENS;
    /** */ ElectricalConductanceUnit MICROSIEMENS        = ElectricalConductanceUnit.MICROSIEMENS;
    /** */ ElectricalConductanceUnit NANOSIEMENS         = ElectricalConductanceUnit.NANOSIEMENS;

    /****************************************************************************************************************/
    /******************************************** ELECTRICALINDUCTANCE **********************************************/
    /****************************************************************************************************************/

    /** */ ElectricalInductanceUnit HENRY                = ElectricalInductanceUnit.HENRY;
    /** */ ElectricalInductanceUnit MILLIHENRY           = ElectricalInductanceUnit.MILLIHENRY;
    /** */ ElectricalInductanceUnit MICROHENRY           = ElectricalInductanceUnit.MICROHENRY;
    /** */ ElectricalInductanceUnit NANOHENRY            = ElectricalInductanceUnit.NANOHENRY;

    /****************************************************************************************************************/
    /*********************************************** EQUIVALENTDOSE *************************************************/
    /****************************************************************************************************************/

    /** */ EquivalentDoseUnit SIEVERT                    = EquivalentDoseUnit.SIEVERT;
    /** */ EquivalentDoseUnit MILLISIEVERT               = EquivalentDoseUnit.MILLISIEVERT;
    /** */ EquivalentDoseUnit MICROSIEVERT               = EquivalentDoseUnit.MICROSIEVERT;
    /** */ EquivalentDoseUnit REM                        = EquivalentDoseUnit.REM;

    /****************************************************************************************************************/
    /************************************************ ILLUMINANCE  **************************************************/
    /****************************************************************************************************************/

    /** */ IlluminanceUnit LUX                           = IlluminanceUnit.LUX;
    /** */ IlluminanceUnit MILLILUX                      = IlluminanceUnit.MILLILUX;
    /** */ IlluminanceUnit MICROLUX                      = IlluminanceUnit.MICROLUX;
    /** */ IlluminanceUnit KILOLUX                       = IlluminanceUnit.KILOLUX;
    /** */ IlluminanceUnit PHOT                          = IlluminanceUnit.PHOT;
    /** */ IlluminanceUnit NOX                           = IlluminanceUnit.NOX;

    /****************************************************************************************************************/
    /************************************************ LUMINOUSFLUX **************************************************/
    /****************************************************************************************************************/

    /** */ LuminousFluxUnit LUMEN                        = LuminousFluxUnit.LUMEN;

    /****************************************************************************************************************/
    /********************************************* LUMINOUSINTENSITY  ***********************************************/
    /****************************************************************************************************************/

    /** */ LuminousIntensityUnit CANDELA                 = LuminousIntensityUnit.CANDELA;

    /****************************************************************************************************************/
    /******************************************** MAGNETICFLUXDENSITY  **********************************************/
    /****************************************************************************************************************/

    /** */ MagneticFluxDensityUnit TESLA                 = MagneticFluxDensityUnit.TESLA;
    /** */ MagneticFluxDensityUnit MILLITESLA            = MagneticFluxDensityUnit.MILLITESLA;
    /** */ MagneticFluxDensityUnit MICROTESLA            = MagneticFluxDensityUnit.MICROTESLA;
    /** */ MagneticFluxDensityUnit NANOTESLA             = MagneticFluxDensityUnit.NANOTESLA;
    /** */ MagneticFluxDensityUnit GAUSS                 = MagneticFluxDensityUnit.GAUSS;

    /****************************************************************************************************************/
    /************************************************ MAGNETICFLUX **************************************************/
    /****************************************************************************************************************/

    /** */ MagneticFluxUnit WEBER                        = MagneticFluxUnit.WEBER;
    /** */ MagneticFluxUnit MILLIWEBER                   = MagneticFluxUnit.MILLIWEBER;
    /** */ MagneticFluxUnit MICROWEBER                   = MagneticFluxUnit.MICROWEBER;
    /** */ MagneticFluxUnit NANOWEBER                    = MagneticFluxUnit.NANOWEBER;
    /** */ MagneticFluxUnit MAXWELL                      = MagneticFluxUnit.MAXWELL;

    /****************************************************************************************************************/
    /*********************************************** RADIOACTIVITY  *************************************************/
    /****************************************************************************************************************/

    /** */ RadioActivityUnit BECQUEREL                   = RadioActivityUnit.BECQUEREL;
    /** */ RadioActivityUnit KILOBECQUEREL               = RadioActivityUnit.KILOBECQUEREL;
    /** */ RadioActivityUnit MEGABECQUEREL               = RadioActivityUnit.MEGABECQUEREL;
    /** */ RadioActivityUnit GIGABECQUEREL               = RadioActivityUnit.GIGABECQUEREL;
    /** */ RadioActivityUnit TERABECQUEREL               = RadioActivityUnit.TERABECQUEREL;
    /** */ RadioActivityUnit PETABECQUEREL               = RadioActivityUnit.PETABECQUEREL;
    /** */ RadioActivityUnit CURIE                       = RadioActivityUnit.CURIE;
    /** */ RadioActivityUnit MILLICURIE                  = RadioActivityUnit.MILLICURIE;
    /** */ RadioActivityUnit MICROCURIE                  = RadioActivityUnit.MICROCURIE;
    /** */ RadioActivityUnit NANOCURIE                   = RadioActivityUnit.NANOCURIE;
    /** */ RadioActivityUnit RUTHERFORD                  = RadioActivityUnit.RUTHERFORD;

    // @formatter:on

}
