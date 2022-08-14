package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.EquivalentDoseUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatEquivalentDose FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatEquivalentDose extends AbstractFloatScalarRel<EquivalentDoseUnit, FloatEquivalentDose>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatEquivalentDose ZERO = new FloatEquivalentDose(0.0f, EquivalentDoseUnit.SI);

    /** Constant with value one. */
    public static final FloatEquivalentDose ONE = new FloatEquivalentDose(1.0f, EquivalentDoseUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatEquivalentDose NaN = new FloatEquivalentDose(Float.NaN, EquivalentDoseUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatEquivalentDose POSITIVE_INFINITY =
            new FloatEquivalentDose(Float.POSITIVE_INFINITY, EquivalentDoseUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatEquivalentDose NEGATIVE_INFINITY =
            new FloatEquivalentDose(Float.NEGATIVE_INFINITY, EquivalentDoseUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatEquivalentDose POS_MAXVALUE = new FloatEquivalentDose(Float.MAX_VALUE, EquivalentDoseUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatEquivalentDose NEG_MAXVALUE = new FloatEquivalentDose(-Float.MAX_VALUE, EquivalentDoseUnit.SI);

    /**
     * Construct FloatEquivalentDose scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatEquivalentDose(final float value, final EquivalentDoseUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatEquivalentDose scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatEquivalentDose(final FloatEquivalentDose value)
    {
        super(value);
    }

    /**
     * Construct FloatEquivalentDose scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatEquivalentDose(final double value, final EquivalentDoseUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatEquivalentDose instantiateRel(final float value, final EquivalentDoseUnit unit)
    {
        return new FloatEquivalentDose(value, unit);
    }

    /**
     * Construct FloatEquivalentDose scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatEquivalentDose instantiateSI(final float value)
    {
        return new FloatEquivalentDose(value, EquivalentDoseUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatEquivalentDose interpolate(final FloatEquivalentDose zero, final FloatEquivalentDose one,
            final float ratio)
    {
        return new FloatEquivalentDose(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatEquivalentDose max(final FloatEquivalentDose r1, final FloatEquivalentDose r2)
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
    public static FloatEquivalentDose max(final FloatEquivalentDose r1, final FloatEquivalentDose r2,
            final FloatEquivalentDose... rn)
    {
        FloatEquivalentDose maxr = r1.gt(r2) ? r1 : r2;
        for (FloatEquivalentDose r : rn)
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
    public static FloatEquivalentDose min(final FloatEquivalentDose r1, final FloatEquivalentDose r2)
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
    public static FloatEquivalentDose min(final FloatEquivalentDose r1, final FloatEquivalentDose r2,
            final FloatEquivalentDose... rn)
    {
        FloatEquivalentDose minr = r1.lt(r2) ? r1 : r2;
        for (FloatEquivalentDose r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatEquivalentDose representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatEquivalentDose
     * @return FloatEquivalentDose; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatEquivalentDose valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatEquivalentDose: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatEquivalentDose: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            EquivalentDoseUnit unit = EquivalentDoseUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatEquivalentDose(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatEquivalentDose from " + text);
    }

    /**
     * Returns a FloatEquivalentDose based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatEquivalentDose; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatEquivalentDose of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatEquivalentDose: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatEquivalentDose: empty unitString");
        EquivalentDoseUnit unit = EquivalentDoseUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatEquivalentDose(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatEquivalentDose with unit " + unitString);
    }

    /**
     * Calculate the division of FloatEquivalentDose and FloatEquivalentDose, which results in a FloatDimensionless scalar.
     * @param v FloatEquivalentDose; scalar
     * @return FloatDimensionless; scalar as a division of FloatEquivalentDose and FloatEquivalentDose
     */
    public final FloatDimensionless divide(final FloatEquivalentDose v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
