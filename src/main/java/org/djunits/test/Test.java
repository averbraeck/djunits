package org.djunits.test;

import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

import org.djunits.quantity.AbsorbedDose;
import org.djunits.quantity.Acceleration;
import org.djunits.quantity.AmountOfSubstance;
import org.djunits.quantity.Angle;
import org.djunits.quantity.AngularAcceleration;
import org.djunits.quantity.AngularVelocity;
import org.djunits.quantity.Area;
import org.djunits.quantity.ArealObjectDensity;
import org.djunits.quantity.CatalyticActivity;
import org.djunits.quantity.Density;
import org.djunits.quantity.Duration;
import org.djunits.quantity.ElectricCharge;
import org.djunits.quantity.ElectricCurrent;
import org.djunits.quantity.ElectricPotential;
import org.djunits.quantity.ElectricalCapacitance;
import org.djunits.quantity.ElectricalConductance;
import org.djunits.quantity.ElectricalInductance;
import org.djunits.quantity.ElectricalResistance;
import org.djunits.quantity.Energy;
import org.djunits.quantity.EquivalentDose;
import org.djunits.quantity.FlowMass;
import org.djunits.quantity.FlowVolume;
import org.djunits.quantity.Force;
import org.djunits.quantity.Frequency;
import org.djunits.quantity.Illuminance;
import org.djunits.quantity.Length;
import org.djunits.quantity.LinearDensity;
import org.djunits.quantity.LinearObjectDensity;
import org.djunits.quantity.LuminousFlux;
import org.djunits.quantity.LuminousIntensity;
import org.djunits.quantity.MagneticFlux;
import org.djunits.quantity.MagneticFluxDensity;
import org.djunits.quantity.Mass;
import org.djunits.quantity.Momentum;
import org.djunits.quantity.Power;
import org.djunits.quantity.Pressure;
import org.djunits.quantity.RadioActivity;
import org.djunits.quantity.SolidAngle;
import org.djunits.quantity.Speed;
import org.djunits.quantity.TemperatureDifference;
import org.djunits.quantity.Torque;
import org.djunits.quantity.Volume;
import org.djunits.quantity.VolumetricObjectDensity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;

/**
 * Test.java.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class Test
{
    /** */
    private Test()
    {
        length();
        temp();
        duration();
        printUnits();
        localization();
    }

    /** */
    private void length()
    {
        System.out.println("\nLENGTH");
        Length length = new Length(5.0, "m");
        System.out.println(length);

        Length l2 = new Length(5.0, Length.Unit.km);
        System.out.println(l2.toDisplayString());
        System.out.println("SI = " + l2.si() + " [" + l2.getDisplayUnit().getBaseUnit().getId() + "]");

        Length l3 = new Length(10.0, "Qm");
        System.out.println(l3);
        System.out.println(l3.si());

        Length l4 = new Length(Math.PI, "mum");
        System.out.println(l4);
        System.out.println(l4.add(length));
        System.out.println(length.add(l4));
    }

    /** */
    private void temp()
    {
        System.out.println("\nTEMPERATURE");
        System.out.println("Temperature");
        var t1 = TemperatureDifference.valueOf("10 degC");
        System.out.println(t1 + "  si=" + t1.si());
        System.out.println(TemperatureDifference.of(10, "K"));
    }

    /** */
    private void duration()
    {
        System.out.println("\nDURATION");
        var d1 = Duration.of(42.0, "ms");
        System.out.println("42 ms: " + d1);
        var f1 = d1.reciprocal();
        System.out.println("reciprocal: " + f1);
        var d2 = Duration.valueOf("1 day");
        var d2s = d2;
        d2s.setDisplayUnit("s");
        System.out.println("1 day: " + d2 + " = " + d2s + " seconds");
    }

    /** */
    private void printUnits()
    {
        System.out.println("\n\nUNITS");
        registerStandardUnits();
        var unitMap = Units.registeredUnits();
        unitMap.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey())).forEach(entry ->
        {
            Map<String, UnitInterface<?, ?>> inner = entry.getValue();
            System.out.printf("%n%s%n", entry.getKey());
            System.out.printf("%-15s %-10s %-40s = %s%n", "Textual", "Display", "Name", "Convert to base value");
            System.out.println("-".repeat(97));

            inner.entrySet().stream()
                    // sort by key; change to Comparator.comparing(e -> e.getValue().getNr()) if you prefer sorting by Nr
                    .sorted(Comparator.comparing(e -> e.getValue().toBaseValue(1.0))).forEach(e ->
                    {
                        UnitInterface<?, ?> v = e.getValue();
                        System.out.printf("%-15s %-10s %-40s = %-22s %s%n", e.getKey(), v.getDisplayAbbreviation(), v.getName(),
                                v.toBaseValue(1.0), v.getBaseUnit().getDisplayAbbreviation());
                    });
        });
    }

    /** */
    private void localization()
    {
        System.out.println("\nLOCALIZATION");
        System.out.println(AbsorbedDose.ONE.getName());
        var speed = Speed.of(50.0, "km/h");
        System.out.println("50 km/h = " + speed);
        System.out.println(Units.localizedQuantityName(Acceleration.Unit.class));
        Locale nl = Locale.forLanguageTag("nl");
        System.out.println(Units.localizedQuantityName(nl, "AbsorbedDose"));
        System.out.println(Units.localizedQuantityName(nl, "AbsoluteTemperature"));
        System.out.println("loc = std getName() : " + AbsorbedDose.ONE.getName());
        Locale.setDefault(nl);
        System.out.println("loc = nl. getName() : " + AbsorbedDose.ONE.getName());
        System.out.println(Units.localizedQuantityName(Acceleration.Unit.class));
        System.out.println(Units.localizedUnitDisplayAbbr(Acceleration.Unit.class, "mi/h2"));
        System.out.println(Units.localizedUnitName(Acceleration.Unit.class, "mi/h2"));
        System.out.println("parse 3 dag " + Duration.valueOf("3 dag"));
        System.out.println("parse 3 day " + Duration.valueOf("3 day") + " [fallback]");
        var d3du = Duration.valueOf("3 dag");
        d3du.setDisplayUnit("u");
        System.out.println("3 dagen in uren: " + d3du);
        System.out.println("50 km/h = " + speed);
    }

    /**
     * @param args not used
     */
    public static void main(final String[] args)
    {
        Locale.setDefault(Locale.forLanguageTag("de"));
        new Test();
    }

    /**
     * Touch all unit classes.
     */
    private void registerStandardUnits()
    {
        AbsorbedDose.Unit.SI_UNIT.getId();
        Acceleration.Unit.SI_UNIT.getId();
        AmountOfSubstance.Unit.SI_UNIT.getId();
        Angle.Unit.SI_UNIT.getId();
        AngularAcceleration.Unit.SI_UNIT.getId();
        AngularVelocity.Unit.SI_UNIT.getId();
        Area.Unit.SI_UNIT.getId();
        ArealObjectDensity.Unit.SI_UNIT.getId();
        CatalyticActivity.Unit.SI_UNIT.getId();
        Density.Unit.SI_UNIT.getId();
        Unitless.SI_UNIT.getId();
        Duration.Unit.SI_UNIT.getId();
        ElectricalCapacitance.Unit.SI_UNIT.getId();
        ElectricalConductance.Unit.SI_UNIT.getId();
        ElectricalInductance.Unit.SI_UNIT.getId();
        ElectricalResistance.Unit.SI_UNIT.getId();
        ElectricCharge.Unit.SI_UNIT.getId();
        ElectricCurrent.Unit.SI_UNIT.getId();
        ElectricPotential.Unit.SI_UNIT.getId();
        Energy.Unit.SI_UNIT.getId();
        EquivalentDose.Unit.SI_UNIT.getId();
        FlowMass.Unit.SI_UNIT.getId();
        FlowVolume.Unit.SI_UNIT.getId();
        Force.Unit.SI_UNIT.getId();
        Frequency.Unit.SI_UNIT.getId();
        Illuminance.Unit.SI_UNIT.getId();
        Length.Unit.SI_UNIT.getId();
        LinearDensity.Unit.SI_UNIT.getId();
        LinearObjectDensity.Unit.SI_UNIT.getId();
        LuminousFlux.Unit.SI_UNIT.getId();
        LuminousIntensity.Unit.SI_UNIT.getId();
        MagneticFlux.Unit.SI_UNIT.getId();
        MagneticFluxDensity.Unit.SI_UNIT.getId();
        Mass.Unit.SI_UNIT.getId();
        Momentum.Unit.SI_UNIT.getId();
        Power.Unit.SI_UNIT.getId();
        Pressure.Unit.SI_UNIT.getId();
        RadioActivity.Unit.SI_UNIT.getId();
        SolidAngle.Unit.SI_UNIT.getId();
        Speed.Unit.SI_UNIT.getId();
        TemperatureDifference.Unit.SI_UNIT.getId();
        Torque.Unit.SI_UNIT.getId();
        Volume.Unit.SI_UNIT.getId();
        VolumetricObjectDensity.Unit.SI_UNIT.getId();
    }
}
