package org.djunits.generator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * GenerateCliConverters.java. <br>
 * <p>
 * Copyright (c) 2003-2018 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.<br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class GenerateCliConvertersTest
{
    /** */
    private static List<TestRecord> testRecords = new ArrayList<>();

    static
    {
        // @formatter:off
        testRecords.add(new TestRecord("AbsoluteTemperature", 200.0, "AbsoluteTemperatureUnit.KELVIN", "200.0K", 
                100.0, "AbsoluteTemperatureUnit.DEGREE_CELSIUS", "100.0C"));
        testRecords.add(new TestRecord("AbsorbedDose", 200.0, "AbsorbedDoseUnit.GRAY", "200Gy", 
                100.0, "AbsorbedDoseUnit.ERG_PER_GRAM", "100.0erg/g"));
        testRecords.add(new TestRecord("Acceleration", 2.0, "AccelerationUnit.METER_PER_SECOND_2", "2.0m/s^2", 
                1.0, "AccelerationUnit.KM_PER_HOUR_2", "1.0km/h^2"));
        testRecords.add(new TestRecord("AmountOfSubstance", 200.0, "AmountOfSubstanceUnit.MOLE", "200.0mol",
                10.0, "AmountOfSubstanceUnit.MILLIMOLE", "10mmol"));
        testRecords.add(new TestRecord("Angle", 2.0, "AngleUnit.RADIAN", "2.0rad", 
                10.0, "AngleUnit.DEGREE", "10.0deg"));
        testRecords.add(new TestRecord("AngularAcceleration", 2.0, "AngularAccelerationUnit.RADIAN_PER_SECOND_SQUARED", "2.0rad/s2", 
                10.0, "AngularAccelerationUnit.CENTESIMAL_ARCSECOND_PER_SECOND_SQUARED", "10.0c\\\"/sec2"));
        testRecords.add(new TestRecord("AngularVelocity", 2.0, "AngularVelocityUnit.RADIAN_PER_SECOND", "2.0rad/s", 
                10.0, "AngularVelocityUnit.CENTESIMAL_ARCSECOND_PER_SECOND", "10.0c\\\"/sec"));
        testRecords.add(new TestRecord("Area", 2.0, "AreaUnit.SQUARE_METER", "2.0m^2", 
                1.0, "AreaUnit.ACRE", "1.0ac"));
        testRecords.add(new TestRecord("CatalyticActivity", 2.0, "CatalyticActivityUnit.KATAL", "2.0kat",
                1.0, "CatalyticActivityUnit.MILLIKATAL", "1.0mkat"));
        testRecords.add(new TestRecord("Density", 2.0, "DensityUnit.KG_PER_METER_3", "2.0kg/m^3", 
                1.0, "DensityUnit.GRAM_PER_CENTIMETER_3", "1.0g/cm^3"));
        testRecords.add(new TestRecord("Dimensionless", 2.0, "DimensionlessUnit.SI", "2.0", 
                1.0, "DimensionlessUnit.SI", "1.0"));
        testRecords.add(new TestRecord("Direction", 2.0, "DirectionUnit.EAST_RADIAN", "2.0rad(E)", 
                1.0, "DirectionUnit.NORTH_DEGREE", "1.0deg(N)"));
        testRecords.add(new TestRecord("Duration", 2.0, "DurationUnit.SECOND", "2.0s", 
                1.0, "DurationUnit.DAY", "1.0day"));
        testRecords.add(new TestRecord("ElectricalCapacitance", 2.0, "ElectricalCapacitanceUnit.FARAD", "2.0F", 
                1.0, "ElectricalCapacitanceUnit.MICROFARAD", "1.0uF"));
        testRecords.add(new TestRecord("ElectricalCharge", 2.0, "ElectricalChargeUnit.COULOMB", "2.0C", 
                1.0, "ElectricalChargeUnit.MILLIAMPERE_HOUR", "1.0mAh"));
        testRecords.add(new TestRecord("ElectricalConductance", 2.0, "ElectricalConductanceUnit.SIEMENS", "2.0S", 
                1.0, "ElectricalConductanceUnit.MILLISIEMENS", "1.0mS"));
        testRecords.add(new TestRecord("ElectricalCurrent", 2.0, "ElectricalCurrentUnit.AMPERE", "2.0A", 
                1.0, "ElectricalCurrentUnit.STATAMPERE", "1.0statA"));
        testRecords.add(new TestRecord("ElectricalInductance", 2.0, "ElectricalInductanceUnit.HENRY", "2.0H", 
                1.0, "ElectricalInductanceUnit.MILLIHENRY", "1.0mH"));
        testRecords.add(new TestRecord("ElectricalPotential", 2.0, "ElectricalPotentialUnit.VOLT", "2.0V", 
                1.0, "ElectricalPotentialUnit.ABVOLT", "1.0abV"));
        testRecords.add(new TestRecord("ElectricalResistance", 2.0, "ElectricalResistanceUnit.OHM", "2.0ohm", 
                1.0, "ElectricalResistanceUnit.STATOHM", "1.0stohm"));
        testRecords.add(new TestRecord("Energy", 2.0, "EnergyUnit.JOULE", "2.0J", 
                1.0, "EnergyUnit.KILOWATT_HOUR", "1.0kWh"));
        testRecords.add(new TestRecord("EquivalentDose", 2.0, "EquivalentDoseUnit.SIEVERT", "2.0Sv", 
                1.0, "EquivalentDoseUnit.REM", "1.0rem"));
        testRecords.add(new TestRecord("FlowMass", 2.0, "FlowMassUnit.KILOGRAM_PER_SECOND", "2.0kg/s", 
                1.0, "FlowMassUnit.POUND_PER_SECOND", "1.0lb/s"));
        testRecords.add(new TestRecord("FlowVolume", 2.0, "FlowVolumeUnit.CUBIC_METER_PER_SECOND", "2.0m^3/s", 
                1.0, "FlowVolumeUnit.GALLON_US_PER_DAY", "1.0gal(US)/day"));
        testRecords.add(new TestRecord("Force", 2.0, "ForceUnit.NEWTON", "2.0N", 
                1.0, "ForceUnit.KILOGRAM_FORCE", "1.0kgf"));
        testRecords.add(new TestRecord("Frequency", 2.0, "FrequencyUnit.PER_SECOND", "2.0/s", 
                1.0, "FrequencyUnit.KILOHERTZ", "1.0kHz"));
        testRecords.add(new TestRecord("Illuminance", 2.0, "IlluminanceUnit.LUX", "2.0lx", 
                1.0, "IlluminanceUnit.NOX", "1.0nx"));
        testRecords.add(new TestRecord("Length", 2.0, "LengthUnit.METER", "2.0m", 
                1.0, "LengthUnit.INCH", "1.0in"));
        testRecords.add(new TestRecord("LinearDensity", 2.0, "LinearDensityUnit.PER_METER", "2.0/m", 
                1.0, "LinearDensityUnit.PER_YARD", "1.0/yd"));
        testRecords.add(new TestRecord("LuminousFlux", 2.0, "LuminousFluxUnit.LUMEN", "2.0lm", 
                1.0, "LuminousFluxUnit.LUMEN", "1.0srcd"));
        testRecords.add(new TestRecord("LuminousIntensity", 2.0, "LuminousIntensityUnit.CANDELA", "2.0cd", 
                1.0, "LuminousIntensityUnit.SI", "1.0cd"));
        testRecords.add(new TestRecord("MagneticFlux", 2.0, "MagneticFluxUnit.WEBER", "2.0Wb", 
                1.0, "MagneticFluxUnit.MAXWELL", "1.0Mx"));
        testRecords.add(new TestRecord("MagneticFluxDensity", 2.0, "MagneticFluxDensityUnit.TESLA", "2.0T", 
                1.0, "MagneticFluxDensityUnit.GAUSS", "1.0G"));
        testRecords.add(new TestRecord("Mass", 2.0, "MassUnit.KILOGRAM", "2.0kg", 
                1.0, "MassUnit.GIGAELECTRONVOLT", "1.0GeV"));
        testRecords.add(new TestRecord("Momentum", 2.0, "MomentumUnit.SI", "2.0kgm/s", 
                1.0, "MomentumUnit.KILOGRAM_METER_PER_SECOND", "1.0kgm/sec"));
        testRecords.add(new TestRecord("Position", 2.0, "PositionUnit.ASTRONOMICAL_UNIT", "2.0AU", 
                1.0, "PositionUnit.DECIMETER", "1.0dm"));
        testRecords.add(new TestRecord("Power", 2.0, "PowerUnit.WATT", "2.0W", 
                1.0, "PowerUnit.FOOT_POUND_FORCE_PER_HOUR", "1.0ft.lbf/h"));
        testRecords.add(new TestRecord("Pressure", 2.0, "PressureUnit.PASCAL", "2.0Pa", 
                1.0, "PressureUnit.MILLIMETER_MERCURY", "1.0mmHg"));
        testRecords.add(new TestRecord("RadioActivity", 2.0, "RadioActivityUnit.BECQUEREL", "2.0Bq", 
                1.0, "RadioActivityUnit.CURIE", "1.0Ci"));
        testRecords.add(new TestRecord("SolidAngle", 2.0, "SolidAngleUnit.STERADIAN", "2.0sr", 
                1.0, "SolidAngleUnit.SQUARE_DEGREE", "1.0sq.deg"));
        testRecords.add(new TestRecord("Speed", 2.0, "SpeedUnit.METER_PER_SECOND", "2.0m/s", 
                1.0, "SpeedUnit.MILE_PER_HOUR", "1.0mi/h"));
        testRecords.add(new TestRecord("Temperature", 2.0, "TemperatureUnit.KELVIN", "2.0K", 
                1.0, "TemperatureUnit.DEGREE_CELSIUS", "1.0degC"));
        testRecords.add(new TestRecord("Time", 2.0, "TimeUnit.BASE_HOUR", "2.0h", 
                1.0, "TimeUnit.EPOCH_DAY", "1.0day"));
        testRecords.add(new TestRecord("Torque", 2.0, "TorqueUnit.NEWTON_METER", "2.0N.m", 
                1.0, "TorqueUnit.POUND_FOOT", "1.0lbf.ft"));
        testRecords.add(new TestRecord("Volume", 2.0, "VolumeUnit.CUBIC_METER", "2.0m^3", 
                1.0, "VolumeUnit.LITER", "1.0L"));
        // @formatter:on
    }

    /**
     * @param args String[]; blank
     * @throws IOException on i/o error
     * @throws URISyntaxException on i/o error
     */
    public static void main(String[] args) throws IOException, URISyntaxException
    {
        /*-
         * Create this:
            @Option(names = {"--acceleration"}, description = "Acceleration", defaultValue = "10.0m/s^2")
            protected Acceleration acceleration;
         */

        for (TestRecord r : testRecords)
        {
            // @formatter:off
            System.out.println("    /** */\n" + 
                "    @Option(names = {\"--" + r.type.toLowerCase() + "\"}, description = \"" + r.type + "\", defaultValue = \"" + r.value1 + "\")\n" + 
                "    protected " + r.type + " " + r.type.toLowerCase() + ";\n"); 
            // @formatter:on
        }

        System.out.println("\n\n");

        /*-
         Create this:
            args = new String[] {};
            options = new Options();
            CliUtil.execute(options, args);
            assertEquals(new Acceleration(10.0, AccelerationUnit.METER_PER_SECOND_2), options.acceleration);
            args = new String[] {"--acceleration", "30.0km/h^2"};
            CliUtil.execute(options, args);
            assertEquals(new Acceleration(30.0, AccelerationUnit.KM_PER_HOUR_2), options.acceleration);
         */

        for (TestRecord r : testRecords)
        {
            // @formatter:off
            System.out.println("args = new String[] {};");
            System.out.println("options = new Options();");
            System.out.println("CliUtil.execute(options, args);");
            System.out.println("assertEquals(new " + r.type + "(" + r.amount1 + ", " + r.unit1 + "), options." + r.type.toLowerCase() + ");"); 
            System.out.println("args = new String[] {\"--" + r.type.toLowerCase() + "\", \"" + r.value2 + "\"};"); 
            System.out.println("CliUtil.execute(options, args);"); 
            System.out.println("assertEquals(new " + r.type + "(" + r.amount2 + ", " + r.unit2 + "), options." + r.type.toLowerCase() + ");\n"); 
            // @formatter:on
        }
    }

    /** */
    private static class TestRecord
    {
        /** */
        protected String type;

        /** */
        protected double amount1;

        /** */
        protected String unit1;

        /** */
        protected String value1;

        /** */
        protected double amount2;

        /** */
        protected String unit2;

        /** */
        protected String value2;

        /**
         * @param type String; type, e.g. "Length"
         * @param amount1 double; initial amount, e.g. 10.0
         * @param unit1 String; initial unit, e.g. LengthUnit.METER
         * @param value1 String; initial String value, e.g. "10.0m"
         * @param amount2 double; changed amount, e.g. 5.0
         * @param unit2 String; changed unit, e.g. LengthUnit.KILOMETER
         * @param value2 String; changed String amount, e.g. 5.0km
         */
        public TestRecord(String type, double amount1, String unit1, String value1, double amount2, String unit2, String value2)
        {
            this.type = type;
            this.amount1 = amount1;
            this.unit1 = unit1;
            this.value1 = value1;
            this.amount2 = amount2;
            this.unit2 = unit2;
            this.value2 = value2;
        }
    }
}
