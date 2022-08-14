package org.djunits.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * CLASSNAMES.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public final class CLASSNAMES
{
    /** The classes that are absolute (name = class name). */
    private static final String[] ABS = new String[] { "AbsoluteTemperature", "Direction", "Position", "Time" };

    /** List of absolute classes. */
    public static final List<String> ABS_LIST;

    /** The relative classes that mirror the absolute ones (name = class name). */
    private static final String[] REL_WITH_ABS = new String[] { "Temperature", "Angle", "Length", "Duration" };

    /** List of relative-with-absolute classes. */
    public static final List<String> REL_WITH_ABS_LIST;

    /** The classes that are just relative (name = class name). */
    private static final String[] REL = new String[] { "AbsorbedDose", "Acceleration", "AngularAcceleration", "AngularVelocity",
            "AmountOfSubstance", "SolidAngle", "Area", "CatalyticActivity", "Density", "Dimensionless", "ElectricalCapacitance",
            "ElectricalCharge", "ElectricalConductance", "ElectricalCurrent", "ElectricalInductance", "ElectricalPotential",
            "ElectricalResistance", "Energy", "EquivalentDose", "FlowMass", "FlowVolume", "Force", "Frequency", "Illuminance",
            "LinearDensity", "LuminousFlux", "LuminousIntensity", "MagneticFluxDensity", "MagneticFlux", "Mass", "Momentum",
            "Power", "Pressure", "RadioActivity", "Speed", "Torque", "Volume" };

    /** List of relative classes, except SI. */
    public static final List<String> REL_LIST;

    /** List of all relative classes, except SI. */
    public static final List<String> REL_ALL_LIST;

    /** List of all classes, except SI. */
    public static final List<String> ALL_LIST;

    /** List of all classes, except Dimensionless and SI. */
    public static final List<String> ALL_NODIM_LIST;

    static
    {
        ABS_LIST = Arrays.asList(ABS);
        REL_WITH_ABS_LIST = Arrays.asList(REL_WITH_ABS);
        REL_LIST = Arrays.asList(REL);
        List<String> ral = new ArrayList<>(Arrays.asList(REL));
        ral.addAll(REL_WITH_ABS_LIST);
        REL_ALL_LIST = Collections.unmodifiableList(ral);
        List<String> all = new ArrayList<>(REL_ALL_LIST);
        all.addAll(ABS_LIST);
        ALL_LIST = Collections.unmodifiableList(new ArrayList<>(all));
        all.remove("Dimensionless");
        ALL_NODIM_LIST = Collections.unmodifiableList(new ArrayList<>(all));
    }

    /** */
    private CLASSNAMES()
    {
        // Utility class
    }

}
