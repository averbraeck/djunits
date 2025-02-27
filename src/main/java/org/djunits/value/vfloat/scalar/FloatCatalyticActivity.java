package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AmountOfSubstanceUnit;
import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatCatalyticActivity FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatCatalyticActivity extends FloatScalarRel<CatalyticActivityUnit, FloatCatalyticActivity>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatCatalyticActivity ZERO = new FloatCatalyticActivity(0.0f, CatalyticActivityUnit.SI);

    /** Constant with value one. */
    public static final FloatCatalyticActivity ONE = new FloatCatalyticActivity(1.0f, CatalyticActivityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatCatalyticActivity NaN = new FloatCatalyticActivity(Float.NaN, CatalyticActivityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatCatalyticActivity POSITIVE_INFINITY =
            new FloatCatalyticActivity(Float.POSITIVE_INFINITY, CatalyticActivityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatCatalyticActivity NEGATIVE_INFINITY =
            new FloatCatalyticActivity(Float.NEGATIVE_INFINITY, CatalyticActivityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatCatalyticActivity POS_MAXVALUE =
            new FloatCatalyticActivity(Float.MAX_VALUE, CatalyticActivityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatCatalyticActivity NEG_MAXVALUE =
            new FloatCatalyticActivity(-Float.MAX_VALUE, CatalyticActivityUnit.SI);

    /**
     * Construct FloatCatalyticActivity scalar.
     * @param value the float value
     * @param unit unit for the float value
     */
    public FloatCatalyticActivity(final float value, final CatalyticActivityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatCatalyticActivity scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatCatalyticActivity(final FloatCatalyticActivity value)
    {
        super(value);
    }

    /**
     * Construct FloatCatalyticActivity scalar using a double value.
     * @param value the double value
     * @param unit unit for the resulting float value
     */
    public FloatCatalyticActivity(final double value, final CatalyticActivityUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatCatalyticActivity instantiateRel(final float value, final CatalyticActivityUnit unit)
    {
        return new FloatCatalyticActivity(value, unit);
    }

    /**
     * Construct FloatCatalyticActivity scalar.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatCatalyticActivity instantiateSI(final float value)
    {
        return new FloatCatalyticActivity(value, CatalyticActivityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatCatalyticActivity interpolate(final FloatCatalyticActivity zero, final FloatCatalyticActivity one,
            final float ratio)
    {
        return new FloatCatalyticActivity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatCatalyticActivity max(final FloatCatalyticActivity r1, final FloatCatalyticActivity r2)
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
    public static FloatCatalyticActivity max(final FloatCatalyticActivity r1, final FloatCatalyticActivity r2,
            final FloatCatalyticActivity... rn)
    {
        FloatCatalyticActivity maxr = r1.gt(r2) ? r1 : r2;
        for (FloatCatalyticActivity r : rn)
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
    public static FloatCatalyticActivity min(final FloatCatalyticActivity r1, final FloatCatalyticActivity r2)
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
    public static FloatCatalyticActivity min(final FloatCatalyticActivity r1, final FloatCatalyticActivity r2,
            final FloatCatalyticActivity... rn)
    {
        FloatCatalyticActivity minr = r1.lt(r2) ? r1 : r2;
        for (FloatCatalyticActivity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatCatalyticActivity representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatCatalyticActivity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatCatalyticActivity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatCatalyticActivity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatCatalyticActivity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            CatalyticActivityUnit unit = CatalyticActivityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit == null)
                throw new IllegalArgumentException("Unit " + unitString + " not found");
            return new FloatCatalyticActivity(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatCatalyticActivity from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatCatalyticActivity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatCatalyticActivity of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatCatalyticActivity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatCatalyticActivity: empty unitString");
        CatalyticActivityUnit unit = CatalyticActivityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatCatalyticActivity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatCatalyticActivity with unit " + unitString);
    }

    /**
     * Calculate the division of FloatCatalyticActivity and FloatCatalyticActivity, which results in a FloatDimensionless
     * scalar.
     * @param v scalar
     * @return scalar as a division of FloatCatalyticActivity and FloatCatalyticActivity
     */
    public final FloatDimensionless divide(final FloatCatalyticActivity v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatCatalyticActivity and FloatDuration, which results in a FloatAmountOfSubstance
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatCatalyticActivity and FloatDuration
     */
    public final FloatAmountOfSubstance times(final FloatDuration v)
    {
        return new FloatAmountOfSubstance(this.si * v.si, AmountOfSubstanceUnit.SI);
    }

    /**
     * Calculate the division of FloatCatalyticActivity and FloatAmountOfSubstance, which results in a FloatFrequency scalar.
     * @param v scalar
     * @return scalar as a division of FloatCatalyticActivity and FloatAmountOfSubstance
     */
    public final FloatFrequency divide(final FloatAmountOfSubstance v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of FloatCatalyticActivity and FloatFrequency, which results in a FloatAmountOfSubstance scalar.
     * @param v scalar
     * @return scalar as a division of FloatCatalyticActivity and FloatFrequency
     */
    public final FloatAmountOfSubstance divide(final FloatFrequency v)
    {
        return new FloatAmountOfSubstance(this.si / v.si, AmountOfSubstanceUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
