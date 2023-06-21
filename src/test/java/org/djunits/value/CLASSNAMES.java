package org.djunits.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.djunits.value.vdouble.matrix.base.DoubleMatrix;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.vector.base.DoubleVector;
import org.djunits.value.vfloat.matrix.base.FloatMatrix;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.vector.base.FloatVector;

/**
 * CLASSNAMES.java.
 * <p>
 * Copyright (c) 2019-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public final class CLASSNAMES
{
    /** The classes that are absolute (name = class name). */
    private static final String[] ABS = new String[] {"AbsoluteTemperature", "Direction", "Position", "Time"};

    /** List of absolute class names. */
    public static final List<String> ABS_LIST;

    /** The relative classes that mirror the absolute ones (name = class name). */
    private static final String[] REL_WITH_ABS = new String[] {"Temperature", "Angle", "Length", "Duration"};

    /** List of relative-with-absolute class names. */
    public static final List<String> REL_WITH_ABS_LIST;

    /** The classes that are just relative (name = class name). */
    private static final String[] REL = new String[] {"AbsorbedDose", "Acceleration", "AngularAcceleration", "AngularVelocity",
            "AmountOfSubstance", "SolidAngle", "Area", "CatalyticActivity", "Density", "Dimensionless", "ElectricalCapacitance",
            "ElectricalCharge", "ElectricalConductance", "ElectricalCurrent", "ElectricalInductance", "ElectricalPotential",
            "ElectricalResistance", "Energy", "EquivalentDose", "FlowMass", "FlowVolume", "Force", "Frequency", "Illuminance",
            "LinearDensity", "LuminousFlux", "LuminousIntensity", "MagneticFluxDensity", "MagneticFlux", "Mass", "Momentum",
            "Power", "Pressure", "RadioActivity", "Speed", "Torque", "Volume"};

    /** List of relative classes, except SI. */
    public static final List<String> REL_LIST;

    /** List of all relative class names, except SI. */
    public static final List<String> REL_ALL_LIST;

    /** List of all class names, except SI. */
    public static final List<String> ALL_LIST;

    /** List of all class names, except Dimensionless and SI. */
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
        // utility class
    }

    /**
     * Return the double scalar classes for the quantity name
     * @param qName quantity name
     * @return double scalar class for the quantity name
     */
    @SuppressWarnings("unchecked")
    public static Class<? extends DoubleScalar<?, ?>> doubleScalarClass(final String qName)
    {
        try
        {
            return (Class<? extends DoubleScalar<?, ?>>) Class.forName("org.djunits.value.vdouble.scalar." + qName);
        }
        catch (ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Return the float scalar classes for the quantity name
     * @param qName quantity name
     * @return list of float scalar classes for the quantity name
     */
    @SuppressWarnings("unchecked")
    public static Class<? extends FloatScalar<?, ?>> floatScalarClass(final String qName)
    {
        try
        {
            return (Class<? extends FloatScalar<?, ?>>) Class.forName("org.djunits.value.vfloat.scalar.Float" + qName);
        }
        catch (ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Return the double vector classes for the quantity name
     * @param qName quantity name
     * @return list of double vector classes for the quantity name
     */
    @SuppressWarnings("unchecked")
    public static Class<? extends DoubleVector<?, ?, ?>> doubleVectorClass(final String qName)
    {
        try
        {
            return (Class<? extends DoubleVector<?, ?, ?>>) Class
                    .forName("org.djunits.value.vdouble.vector." + qName + "Vector");
        }
        catch (ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Return the float vector classes for the quantity name
     * @param qName quantity name
     * @return list of float vector classes for the quantity name
     */
    @SuppressWarnings("unchecked")
    public static Class<? extends FloatVector<?, ?, ?>> floatVectorClass(final String qName)
    {
        try
        {
            return (Class<? extends FloatVector<?, ?, ?>>) Class
                    .forName("org.djunits.value.vfloat.vector.Float" + qName + "Vector");
        }
        catch (ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Return the double matrix classes for the quantity name
     * @param qName quantity name
     * @return list of double matrix classes for the quantity name
     */
    @SuppressWarnings("unchecked")
    public static Class<? extends DoubleMatrix<?, ?, ?, ?>> doubleMatrixClass(final String qName)
    {
        try
        {
            return (Class<? extends DoubleMatrix<?, ?, ?, ?>>) Class
                    .forName("org.djunits.value.vdouble.matrix." + qName + "Matrix");
        }
        catch (ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Return the float matrix classes for the quantity name
     * @param qName quantity name
     * @return list of float matrix classes for the quantity name
     */
    @SuppressWarnings("unchecked")
    public static Class<? extends FloatMatrix<?, ?, ?, ?>> floatMatrixClass(final String qName)
    {
        try
        {
            return (Class<? extends FloatMatrix<?, ?, ?, ?>>) Class
                    .forName("org.djunits.value.vfloat.matrix.Float" + qName + "Matrix");
        }
        catch (ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

}
