package org.djunits.old.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.ForceUnit;
import org.djunits.old.unit.FrequencyUnit;
import org.djunits.old.unit.LengthUnit;
import org.djunits.old.unit.LinearDensityUnit;
import org.djunits.old.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the LinearDensity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class LinearDensity extends DoubleScalarRel<LinearDensityUnit, LinearDensity>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final LinearDensity ZERO = new LinearDensity(0.0, LinearDensityUnit.SI);

    /** Constant with value one. */
    public static final LinearDensity ONE = new LinearDensity(1.0, LinearDensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LinearDensity NaN = new LinearDensity(Double.NaN, LinearDensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LinearDensity POSITIVE_INFINITY = new LinearDensity(Double.POSITIVE_INFINITY, LinearDensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LinearDensity NEGATIVE_INFINITY = new LinearDensity(Double.NEGATIVE_INFINITY, LinearDensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final LinearDensity POS_MAXVALUE = new LinearDensity(Double.MAX_VALUE, LinearDensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final LinearDensity NEG_MAXVALUE = new LinearDensity(-Double.MAX_VALUE, LinearDensityUnit.SI);

    /**
     * Construct LinearDensity scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public LinearDensity(final double value, final LinearDensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct LinearDensity scalar.
     * @param value Scalar from which to construct this instance
     */
    public LinearDensity(final LinearDensity value)
    {
        super(value);
    }

    @Override
    public final LinearDensity instantiateRel(final double value, final LinearDensityUnit unit)
    {
        return new LinearDensity(value, unit);
    }

    /**
     * Construct LinearDensity scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final LinearDensity ofSI(final double value)
    {
        return new LinearDensity(value, LinearDensityUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a LinearDensity at the given ratio between 0 and 1
     */
    public static LinearDensity interpolate(final LinearDensity zero, final LinearDensity one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new LinearDensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static LinearDensity max(final LinearDensity r1, final LinearDensity r2)
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
    public static LinearDensity max(final LinearDensity r1, final LinearDensity r2, final LinearDensity... rn)
    {
        LinearDensity maxr = r1.gt(r2) ? r1 : r2;
        for (LinearDensity r : rn)
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
    public static LinearDensity min(final LinearDensity r1, final LinearDensity r2)
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
    public static LinearDensity min(final LinearDensity r1, final LinearDensity r2, final LinearDensity... rn)
    {
        LinearDensity minr = r1.lt(r2) ? r1 : r2;
        for (LinearDensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a LinearDensity representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a LinearDensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LinearDensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing LinearDensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing LinearDensity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            LinearDensityUnit unit = LinearDensityUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity LinearDensity",
                    unitString);
            return new LinearDensity(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing LinearDensity from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a LinearDensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LinearDensity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing LinearDensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing LinearDensity: empty unitString");
        LinearDensityUnit unit = LinearDensityUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing LinearDensity with unit %s", unitString);
        return new LinearDensity(value, unit);
    }

    /**
     * Calculate the division of LinearDensity and LinearDensity, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of LinearDensity and LinearDensity
     */
    public final Dimensionless divide(final LinearDensity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of LinearDensity and Length, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearDensity and Length
     */
    public final Dimensionless times(final Length v)
    {
        return new Dimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of LinearDensity and Area, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearDensity and Area
     */
    public final Length times(final Area v)
    {
        return new Length(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of LinearDensity and Energy, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearDensity and Energy
     */
    public final Force times(final Energy v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of LinearDensity and Speed, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a multiplication of LinearDensity and Speed
     */
    public final Frequency times(final Speed v)
    {
        return new Frequency(this.si * v.si, FrequencyUnit.SI);
    }

    @Override
    public Length reciprocal()
    {
        return Length.ofSI(1.0 / this.si);
    }

    /**
     * Multiply two scalars that result in a scalar of type LinearDensity.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of LinearDensity
     */
    public static LinearDensity multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(LinearDensityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type LinearDensity",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new LinearDensity(scalar1.si * scalar2.si, LinearDensityUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type LinearDensity.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of LinearDensity
     */
    public static LinearDensity divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(LinearDensityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type LinearDensity",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new LinearDensity(scalar1.si / scalar2.si, LinearDensityUnit.SI);
    }

}
