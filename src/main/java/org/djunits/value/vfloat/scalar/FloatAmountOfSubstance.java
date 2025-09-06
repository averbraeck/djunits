package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AmountOfSubstanceUnit;
import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatAmountOfSubstance FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatAmountOfSubstance extends FloatScalarRel<AmountOfSubstanceUnit, FloatAmountOfSubstance>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatAmountOfSubstance ZERO = new FloatAmountOfSubstance(0.0f, AmountOfSubstanceUnit.SI);

    /** Constant with value one. */
    public static final FloatAmountOfSubstance ONE = new FloatAmountOfSubstance(1.0f, AmountOfSubstanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatAmountOfSubstance NaN = new FloatAmountOfSubstance(Float.NaN, AmountOfSubstanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatAmountOfSubstance POSITIVE_INFINITY =
            new FloatAmountOfSubstance(Float.POSITIVE_INFINITY, AmountOfSubstanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatAmountOfSubstance NEGATIVE_INFINITY =
            new FloatAmountOfSubstance(Float.NEGATIVE_INFINITY, AmountOfSubstanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatAmountOfSubstance POS_MAXVALUE =
            new FloatAmountOfSubstance(Float.MAX_VALUE, AmountOfSubstanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatAmountOfSubstance NEG_MAXVALUE =
            new FloatAmountOfSubstance(-Float.MAX_VALUE, AmountOfSubstanceUnit.SI);

    /**
     * Construct FloatAmountOfSubstance scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatAmountOfSubstance(final float value, final AmountOfSubstanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatAmountOfSubstance scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatAmountOfSubstance(final FloatAmountOfSubstance value)
    {
        super(value);
    }

    /**
     * Construct FloatAmountOfSubstance scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatAmountOfSubstance(final double value, final AmountOfSubstanceUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatAmountOfSubstance instantiateRel(final float value, final AmountOfSubstanceUnit unit)
    {
        return new FloatAmountOfSubstance(value, unit);
    }

    /**
     * Construct FloatAmountOfSubstance scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatAmountOfSubstance ofSI(final float value)
    {
        return new FloatAmountOfSubstance(value, AmountOfSubstanceUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatAmountOfSubstance at the given ratio between 0 and 1
     */
    public static FloatAmountOfSubstance interpolate(final FloatAmountOfSubstance zero, final FloatAmountOfSubstance one,
            final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatAmountOfSubstance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatAmountOfSubstance max(final FloatAmountOfSubstance r1, final FloatAmountOfSubstance r2)
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
    public static FloatAmountOfSubstance max(final FloatAmountOfSubstance r1, final FloatAmountOfSubstance r2,
            final FloatAmountOfSubstance... rn)
    {
        FloatAmountOfSubstance maxr = r1.gt(r2) ? r1 : r2;
        for (FloatAmountOfSubstance r : rn)
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
    public static FloatAmountOfSubstance min(final FloatAmountOfSubstance r1, final FloatAmountOfSubstance r2)
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
    public static FloatAmountOfSubstance min(final FloatAmountOfSubstance r1, final FloatAmountOfSubstance r2,
            final FloatAmountOfSubstance... rn)
    {
        FloatAmountOfSubstance minr = r1.lt(r2) ? r1 : r2;
        for (FloatAmountOfSubstance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatAmountOfSubstance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatAmountOfSubstance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAmountOfSubstance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAmountOfSubstance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAmountOfSubstance: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AmountOfSubstanceUnit unit = AmountOfSubstanceUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity AmountOfSubstance",
                    unitString);
            return new FloatAmountOfSubstance(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatAmountOfSubstance from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatAmountOfSubstance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAmountOfSubstance of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAmountOfSubstance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAmountOfSubstance: empty unitString");
        AmountOfSubstanceUnit unit = AmountOfSubstanceUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatAmountOfSubstance with unit %s",
                unitString);
        return new FloatAmountOfSubstance(value, unit);
    }

    /**
     * Calculate the division of FloatAmountOfSubstance and FloatAmountOfSubstance, which results in a FloatDimensionless
     * scalar.
     * @param v scalar
     * @return scalar as a division of FloatAmountOfSubstance and FloatAmountOfSubstance
     */
    public final FloatDimensionless divide(final FloatAmountOfSubstance v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of FloatAmountOfSubstance and FloatCatalyticActivity, which results in a FloatDuration scalar.
     * @param v scalar
     * @return scalar as a division of FloatAmountOfSubstance and FloatCatalyticActivity
     */
    public final FloatDuration divide(final FloatCatalyticActivity v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the division of FloatAmountOfSubstance and FloatDuration, which results in a FloatCatalyticActivity scalar.
     * @param v scalar
     * @return scalar as a division of FloatAmountOfSubstance and FloatDuration
     */
    public final FloatCatalyticActivity divide(final FloatDuration v)
    {
        return new FloatCatalyticActivity(this.si / v.si, CatalyticActivityUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatAmountOfSubstance.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatAmountOfSubstance
     */
    public static FloatAmountOfSubstance multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(AmountOfSubstanceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class,
                "Multiplying %s by %s does not result in instance of type FloatAmountOfSubstance", scalar1.toDisplayString(),
                scalar2.toDisplayString());
        return new FloatAmountOfSubstance(scalar1.si * scalar2.si, AmountOfSubstanceUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatAmountOfSubstance.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatAmountOfSubstance
     */
    public static FloatAmountOfSubstance divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(AmountOfSubstanceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class,
                "Dividing %s by %s does not result in an instance of type FloatAmountOfSubstance", scalar1.toDisplayString(),
                scalar2.toDisplayString());
        return new FloatAmountOfSubstance(scalar1.si / scalar2.si, AmountOfSubstanceUnit.SI);
    }

}
