package org.djunits.generator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class GenerateStaticUNITS
{
    /** the standard units. */
    private static final String[] STANDARD_UNITS = new String[] { "AbsoluteTemperatureUnit", "AccelerationUnit", "AngleUnit",
            "AngularAccelerationUnit", "AngularVelocityUnit", "AreaUnit", "DensityUnit", "DimensionlessUnit", "DirectionUnit",
            "DurationUnit", "ElectricalChargeUnit", "ElectricalCurrentUnit", "ElectricalPotentialUnit",
            "ElectricalResistanceUnit", "EnergyUnit", "FlowMassUnit", "FlowVolumeUnit", "ForceUnit", "FrequencyUnit",
            "LengthUnit", "LinearDensityUnit", "MassUnit", "MomentumUnit", "PositionUnit", "PowerUnit", "PressureUnit",
            "SolidAngleUnit", "SpeedUnit", "TemperatureUnit", "TimeUnit", "TorqueUnit", "VolumeUnit", "AbsorbedDoseUnit",
            "AmountOfSubstanceUnit", "CatalyticActivityUnit", "ElectricalCapacitanceUnit", "ElectricalConductanceUnit",
            "ElectricalInductanceUnit", "EquivalentDoseUnit", "IlluminanceUnit", "LuminousFluxUnit", "LuminousIntensityUnit",
            "MagneticFluxDensityUnit", "MagneticFluxUnit", "RadioActivityUnit" };

    /**
     * 
     */
    private GenerateStaticUNITS()
    {
        // utility class
    }

    /**
     * @param args String[]; should be empty
     */
    public static void main(String[] args)
    {
        for (String className : STANDARD_UNITS)
        {
            System.out.println();
            System.out.println(
                    "    /****************************************************************************************************************/");
            String s =
                    "    /******************************************************XX********************************************************/";
            String cs = className.toUpperCase().replace("UNIT", "");
            int i = cs.length() + 2;
            cs = (i % 2 == 1) ? cs + " " : cs;
            i = (i % 2 == 1) ? i + 2 : i;
            i = i / 2;
            s = s.replace("********************".substring(0, i - 1) + "X", " " + cs + " ");
            s = s.replace("X********************".substring(0, i), "");
            System.out.println(s);
            System.out.println(
                    "    /****************************************************************************************************************/");
            System.out.println();

            @SuppressWarnings("rawtypes")
            Class c;
            try
            {
                c = Class.forName("org.djunits.unit." + className);
            }
            catch (Exception exception)
            {
                System.err.println("Could not find unit " + className);
                System.exit(-1);
                return;
            }

            int l = 0;
            for (Field f : c.getFields())
            {
                if (Modifier.isPublic(f.getModifiers()) && Modifier.isStatic(f.getModifiers())
                        && Modifier.isFinal(f.getModifiers()) && !f.getName().equals("SI")
                        && !f.getName().equals("STANDARD_UNITS"))
                {
                    String n = f.getName();
                    if (f.getName().contains("ELECTRONVOLT"))
                    {
                        n = cs.trim() + "_" + n;
                    }
                    l = Math.max(l, n.length());
                }
            }

            for (Field f : c.getFields())
            {
                if (Modifier.isPublic(f.getModifiers()) && Modifier.isStatic(f.getModifiers())
                        && Modifier.isFinal(f.getModifiers()) && !f.getName().equals("SI")
                        && !f.getName().equals("STANDARD_UNITS"))
                {
                    String n = f.getName();
                    if (f.getName().contains("ELECTRONVOLT"))
                    {
                        n = cs.trim() + "_" + n;
                    }

                    if (!n.equals("BASE") && !n.equals("DEFAULT"))
                    {
                        if (c.getSimpleName().equals("AbsoluteTemperatureUnit") || c.getSimpleName().equals("PositionUnit"))
                            n = n + "_ABS";
                        System.out.println(String.format("    /** */ %-45s = %s;", c.getSimpleName() + " " + n,
                                c.getSimpleName() + "." + f.getName()));
                    }
                }
            }
            if (className.contains("Dimensionless"))
            {
                System.out.println(String.format("    /** */ %-45s = %s;", c.getSimpleName() + " UNIT", c.getSimpleName() + ".SI"));
            }
        }
    }

}
