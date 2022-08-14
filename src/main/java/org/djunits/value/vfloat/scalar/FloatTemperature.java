package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRelWithAbs;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatTemperature FloatScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatTemperature extends
        AbstractFloatScalarRelWithAbs<AbsoluteTemperatureUnit, FloatAbsoluteTemperature, TemperatureUnit, FloatTemperature>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatTemperature ZERO = new FloatTemperature(0.0f, TemperatureUnit.SI);

    /** Constant with value one. */
    public static final FloatTemperature ONE = new FloatTemperature(1.0f, TemperatureUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatTemperature NaN = new FloatTemperature(Float.NaN, TemperatureUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatTemperature POSITIVE_INFINITY = new FloatTemperature(Float.POSITIVE_INFINITY, TemperatureUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatTemperature NEGATIVE_INFINITY = new FloatTemperature(Float.NEGATIVE_INFINITY, TemperatureUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatTemperature POS_MAXVALUE = new FloatTemperature(Float.MAX_VALUE, TemperatureUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatTemperature NEG_MAXVALUE = new FloatTemperature(-Float.MAX_VALUE, TemperatureUnit.SI);

    /**
     * Construct FloatTemperature scalar.
     * @param value float; the float value
     * @param unit TemperatureUnit; unit for the float value
     */
    public FloatTemperature(final float value, final TemperatureUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatTemperature scalar.
     * @param value FloatTemperature; Scalar from which to construct this instance
     */
    public FloatTemperature(final FloatTemperature value)
    {
        super(value);
    }

    /**
     * Construct FloatTemperature scalar using a double value.
     * @param value double; the double value
     * @param unit TemperatureUnit; unit for the resulting float value
     */
    public FloatTemperature(final double value, final TemperatureUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatTemperature instantiateRel(final float value, final TemperatureUnit unit)
    {
        return new FloatTemperature(value, unit);
    }

    /**
     * Construct FloatTemperature scalar.
     * @param value float; the float value in SI units
     * @return FloatTemperature; the new scalar with the SI value
     */
    public static final FloatTemperature instantiateSI(final float value)
    {
        return new FloatTemperature(value, TemperatureUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatAbsoluteTemperature instantiateAbs(final float value, final AbsoluteTemperatureUnit unit)
    {
        return new FloatAbsoluteTemperature(value, unit);
    }

    /**
     * Interpolate between two values.
     * @param zero FloatTemperature; the low value
     * @param one FloatTemperature; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return FloatTemperature; a Scalar at the ratio between
     */
    public static FloatTemperature interpolate(final FloatTemperature zero, final FloatTemperature one, final float ratio)
    {
        return new FloatTemperature(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 FloatTemperature; the first scalar
     * @param r2 FloatTemperature; the second scalar
     * @return FloatTemperature; the maximum value of two relative scalars
     */
    public static FloatTemperature max(final FloatTemperature r1, final FloatTemperature r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 FloatTemperature; the first scalar
     * @param r2 FloatTemperature; the second scalar
     * @param rn FloatTemperature...; the other scalars
     * @return FloatTemperature; the maximum value of more than two relative scalars
     */
    public static FloatTemperature max(final FloatTemperature r1, final FloatTemperature r2, final FloatTemperature... rn)
    {
        FloatTemperature maxr = r1.gt(r2) ? r1 : r2;
        for (FloatTemperature r : rn)
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
     * @param r1 FloatTemperature; the first scalar
     * @param r2 FloatTemperature; the second scalar
     * @return FloatTemperature; the minimum value of two relative scalars
     */
    public static FloatTemperature min(final FloatTemperature r1, final FloatTemperature r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 FloatTemperature; the first scalar
     * @param r2 FloatTemperature; the second scalar
     * @param rn FloatTemperature...; the other scalars
     * @return FloatTemperature; the minimum value of more than two relative scalars
     */
    public static FloatTemperature min(final FloatTemperature r1, final FloatTemperature r2, final FloatTemperature... rn)
    {
        FloatTemperature minr = r1.lt(r2) ? r1 : r2;
        for (FloatTemperature r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatTemperature representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatTemperature
     * @return FloatTemperature; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatTemperature valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatTemperature: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatTemperature: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            TemperatureUnit unit = TemperatureUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatTemperature(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatTemperature from " + text);
    }

    /**
     * Returns a FloatTemperature based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatTemperature; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatTemperature of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatTemperature: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatTemperature: empty unitString");
        TemperatureUnit unit = TemperatureUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatTemperature(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatTemperature with unit " + unitString);
    }

    /**
     * Calculate the division of FloatTemperature and FloatTemperature, which results in a FloatDimensionless scalar.
     * @param v FloatTemperature; scalar
     * @return FloatDimensionless; scalar as a division of FloatTemperature and FloatTemperature
     */
    public final FloatDimensionless divide(final FloatTemperature v)
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
