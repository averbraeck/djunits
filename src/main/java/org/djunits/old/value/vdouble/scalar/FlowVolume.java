package org.djunits.old.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.old.unit.AreaUnit;
import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.FlowMassUnit;
import org.djunits.old.unit.FlowVolumeUnit;
import org.djunits.old.unit.FrequencyUnit;
import org.djunits.old.unit.SpeedUnit;
import org.djunits.old.unit.VolumeUnit;
import org.djunits.old.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FlowVolume DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FlowVolume extends DoubleScalarRel<FlowVolumeUnit, FlowVolume>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final FlowVolume ZERO = new FlowVolume(0.0, FlowVolumeUnit.SI);

    /** Constant with value one. */
    public static final FlowVolume ONE = new FlowVolume(1.0, FlowVolumeUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FlowVolume NaN = new FlowVolume(Double.NaN, FlowVolumeUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FlowVolume POSITIVE_INFINITY = new FlowVolume(Double.POSITIVE_INFINITY, FlowVolumeUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FlowVolume NEGATIVE_INFINITY = new FlowVolume(Double.NEGATIVE_INFINITY, FlowVolumeUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FlowVolume POS_MAXVALUE = new FlowVolume(Double.MAX_VALUE, FlowVolumeUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FlowVolume NEG_MAXVALUE = new FlowVolume(-Double.MAX_VALUE, FlowVolumeUnit.SI);

    /**
     * Construct FlowVolume scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public FlowVolume(final double value, final FlowVolumeUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FlowVolume scalar.
     * @param value Scalar from which to construct this instance
     */
    public FlowVolume(final FlowVolume value)
    {
        super(value);
    }

    @Override
    public final FlowVolume instantiateRel(final double value, final FlowVolumeUnit unit)
    {
        return new FlowVolume(value, unit);
    }

    /**
     * Construct FlowVolume scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final FlowVolume ofSI(final double value)
    {
        return new FlowVolume(value, FlowVolumeUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FlowVolume at the given ratio between 0 and 1
     */
    public static FlowVolume interpolate(final FlowVolume zero, final FlowVolume one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FlowVolume(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FlowVolume max(final FlowVolume r1, final FlowVolume r2)
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
    public static FlowVolume max(final FlowVolume r1, final FlowVolume r2, final FlowVolume... rn)
    {
        FlowVolume maxr = r1.gt(r2) ? r1 : r2;
        for (FlowVolume r : rn)
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
    public static FlowVolume min(final FlowVolume r1, final FlowVolume r2)
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
    public static FlowVolume min(final FlowVolume r1, final FlowVolume r2, final FlowVolume... rn)
    {
        FlowVolume minr = r1.lt(r2) ? r1 : r2;
        for (FlowVolume r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FlowVolume representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FlowVolume
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FlowVolume valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FlowVolume: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FlowVolume: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            FlowVolumeUnit unit = FlowVolumeUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity FlowVolume", unitString);
            return new FlowVolume(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FlowVolume from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FlowVolume based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FlowVolume of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FlowVolume: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FlowVolume: empty unitString");
        FlowVolumeUnit unit = FlowVolumeUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FlowVolume with unit %s", unitString);
        return new FlowVolume(value, unit);
    }

    /**
     * Calculate the division of FlowVolume and FlowVolume, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FlowVolume and FlowVolume
     */
    public final Dimensionless divide(final FlowVolume v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FlowVolume and Duration, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a multiplication of FlowVolume and Duration
     */
    public final Volume times(final Duration v)
    {
        return new Volume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of FlowVolume and Frequency, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a division of FlowVolume and Frequency
     */
    public final Volume divide(final Frequency v)
    {
        return new Volume(this.si / v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of FlowVolume and Volume, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of FlowVolume and Volume
     */
    public final Frequency divide(final Volume v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of FlowVolume and Area, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a division of FlowVolume and Area
     */
    public final Speed divide(final Area v)
    {
        return new Speed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of FlowVolume and Speed, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a division of FlowVolume and Speed
     */
    public final Area divide(final Speed v)
    {
        return new Area(this.si / v.si, AreaUnit.SI);
    }

    /**
     * Calculate the multiplication of FlowVolume and Density, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a multiplication of FlowVolume and Density
     */
    public final FlowMass times(final Density v)
    {
        return new FlowMass(this.si * v.si, FlowMassUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FlowVolume.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FlowVolume
     */
    public static FlowVolume multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(FlowVolumeUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FlowVolume",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FlowVolume(scalar1.si * scalar2.si, FlowVolumeUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FlowVolume.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FlowVolume
     */
    public static FlowVolume divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(FlowVolumeUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FlowVolume",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FlowVolume(scalar1.si / scalar2.si, FlowVolumeUnit.SI);
    }

}
