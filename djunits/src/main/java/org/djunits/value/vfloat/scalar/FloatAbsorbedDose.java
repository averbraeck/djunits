package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AbsorbedDoseUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatAbsorbedDose FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAbsorbedDose extends AbstractFloatScalarRel<AbsorbedDoseUnit, FloatAbsorbedDose>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatAbsorbedDose ZERO = new FloatAbsorbedDose(0.0f, AbsorbedDoseUnit.SI);

    /** Constant with value one. */
    public static final FloatAbsorbedDose ONE = new FloatAbsorbedDose(1.0f, AbsorbedDoseUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatAbsorbedDose NaN = new FloatAbsorbedDose(Float.NaN, AbsorbedDoseUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatAbsorbedDose POSITIVE_INFINITY =
            new FloatAbsorbedDose(Float.POSITIVE_INFINITY, AbsorbedDoseUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatAbsorbedDose NEGATIVE_INFINITY =
            new FloatAbsorbedDose(Float.NEGATIVE_INFINITY, AbsorbedDoseUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatAbsorbedDose POS_MAXVALUE = new FloatAbsorbedDose(Float.MAX_VALUE, AbsorbedDoseUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatAbsorbedDose NEG_MAXVALUE = new FloatAbsorbedDose(-Float.MAX_VALUE, AbsorbedDoseUnit.SI);

    /**
     * Construct FloatAbsorbedDose scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatAbsorbedDose(final float value, final AbsorbedDoseUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatAbsorbedDose scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatAbsorbedDose(final FloatAbsorbedDose value)
    {
        super(value);
    }

    /**
     * Construct FloatAbsorbedDose scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatAbsorbedDose(final double value, final AbsorbedDoseUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatAbsorbedDose instantiateRel(final float value, final AbsorbedDoseUnit unit)
    {
        return new FloatAbsorbedDose(value, unit);
    }

    /**
     * Construct FloatAbsorbedDose scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatAbsorbedDose instantiateSI(final float value)
    {
        return new FloatAbsorbedDose(value, AbsorbedDoseUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatAbsorbedDose interpolate(final FloatAbsorbedDose zero, final FloatAbsorbedDose one, final float ratio)
    {
        return new FloatAbsorbedDose(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatAbsorbedDose max(final FloatAbsorbedDose r1, final FloatAbsorbedDose r2)
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
    public static FloatAbsorbedDose max(final FloatAbsorbedDose r1, final FloatAbsorbedDose r2, final FloatAbsorbedDose... rn)
    {
        FloatAbsorbedDose maxr = r1.gt(r2) ? r1 : r2;
        for (FloatAbsorbedDose r : rn)
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
    public static FloatAbsorbedDose min(final FloatAbsorbedDose r1, final FloatAbsorbedDose r2)
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
    public static FloatAbsorbedDose min(final FloatAbsorbedDose r1, final FloatAbsorbedDose r2, final FloatAbsorbedDose... rn)
    {
        FloatAbsorbedDose minr = r1.lt(r2) ? r1 : r2;
        for (FloatAbsorbedDose r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatAbsorbedDose representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatAbsorbedDose
     * @return FloatAbsorbedDose; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAbsorbedDose valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAbsorbedDose: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatAbsorbedDose: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AbsorbedDoseUnit unit = AbsorbedDoseUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatAbsorbedDose(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatAbsorbedDose from " + text);
    }

    /**
     * Returns a FloatAbsorbedDose based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatAbsorbedDose; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAbsorbedDose of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAbsorbedDose: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAbsorbedDose: empty unitString");
        AbsorbedDoseUnit unit = AbsorbedDoseUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatAbsorbedDose(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatAbsorbedDose with unit " + unitString);
    }

    /**
     * Calculate the division of FloatAbsorbedDose and FloatAbsorbedDose, which results in a FloatDimensionless scalar.
     * @param v FloatAbsorbedDose; scalar
     * @return FloatDimensionless; scalar as a division of FloatAbsorbedDose and FloatAbsorbedDose
     */
    public final FloatDimensionless divide(final FloatAbsorbedDose v)
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
