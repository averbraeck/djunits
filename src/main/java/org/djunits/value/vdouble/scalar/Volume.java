package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Volume DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class Volume extends DoubleScalarRel<VolumeUnit, Volume>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Volume ZERO = new Volume(0.0, VolumeUnit.SI);

    /** Constant with value one. */
    public static final Volume ONE = new Volume(1.0, VolumeUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Volume NaN = new Volume(Double.NaN, VolumeUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Volume POSITIVE_INFINITY = new Volume(Double.POSITIVE_INFINITY, VolumeUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Volume NEGATIVE_INFINITY = new Volume(Double.NEGATIVE_INFINITY, VolumeUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Volume POS_MAXVALUE = new Volume(Double.MAX_VALUE, VolumeUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Volume NEG_MAXVALUE = new Volume(-Double.MAX_VALUE, VolumeUnit.SI);

    /**
     * Construct Volume scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public Volume(final double value, final VolumeUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Volume scalar.
     * @param value Scalar from which to construct this instance
     */
    public Volume(final Volume value)
    {
        super(value);
    }

    @Override
    public final Volume instantiateRel(final double value, final VolumeUnit unit)
    {
        return new Volume(value, unit);
    }

    /**
     * Construct Volume scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Volume ofSI(final double value)
    {
        return new Volume(value, VolumeUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Volume at the given ratio between 0 and 1
     */
    public static Volume interpolate(final Volume zero, final Volume one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new Volume(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Volume max(final Volume r1, final Volume r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @param rn the other scalars
     * @return the maximum value of more than two relative scalars
     */
    public static Volume max(final Volume r1, final Volume r2, final Volume... rn)
    {
        Volume maxr = r1.gt(r2) ? r1 : r2;
        for (Volume r : rn)
        {
            if (r.gt(maxr))
            {
                maxr = r;
            }
        }
        return maxr;
    }

    /**
     * Return the minimum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the minimum value of two relative scalars
     */
    public static Volume min(final Volume r1, final Volume r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @param rn the other scalars
     * @return the minimum value of more than two relative scalars
     */
    public static Volume min(final Volume r1, final Volume r2, final Volume... rn)
    {
        Volume minr = r1.lt(r2) ? r1 : r2;
        for (Volume r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Volume representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Volume
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Volume valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Volume: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Volume: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            VolumeUnit unit = VolumeUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Volume", unitString);
            return new Volume(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Volume from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Volume based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Volume of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Volume: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Volume: empty unitString");
        VolumeUnit unit = VolumeUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Volume with unit %s", unitString);
        return new Volume(value, unit);
    }

    /**
     * Calculate the division of Volume and Volume, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Volume and Volume
     */
    public final Dimensionless divide(final Volume v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Volume and Density, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Volume and Density
     */
    public final Mass times(final Density v)
    {
        return new Mass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the multiplication of Volume and Pressure, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Volume and Pressure
     */
    public final Energy times(final Pressure v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of Volume and Length, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a division of Volume and Length
     */
    public final Area divide(final Length v)
    {
        return new Area(this.si / v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of Volume and Area, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Volume and Area
     */
    public final Length divide(final Area v)
    {
        return new Length(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of Volume and LinearDensity, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a multiplication of Volume and LinearDensity
     */
    public final Area times(final LinearDensity v)
    {
        return new Area(this.si * v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of Volume and Duration, which results in a FlowVolume scalar.
     * @param v scalar
     * @return scalar as a division of Volume and Duration
     */
    public final FlowVolume divide(final Duration v)
    {
        return new FlowVolume(this.si / v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the division of Volume and FlowVolume, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Volume and FlowVolume
     */
    public final Duration divide(final FlowVolume v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type Volume.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of Volume
     */
    public static Volume multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(VolumeUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type Volume",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Volume(scalar1.si * scalar2.si, VolumeUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type Volume.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of Volume
     */
    public static Volume divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(VolumeUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type Volume",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Volume(scalar1.si / scalar2.si, VolumeUnit.SI);
    }

}
