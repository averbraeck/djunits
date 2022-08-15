package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatSolidAngle FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatSolidAngle extends AbstractFloatScalarRel<SolidAngleUnit, FloatSolidAngle>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatSolidAngle ZERO = new FloatSolidAngle(0.0f, SolidAngleUnit.SI);

    /** Constant with value one. */
    public static final FloatSolidAngle ONE = new FloatSolidAngle(1.0f, SolidAngleUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatSolidAngle NaN = new FloatSolidAngle(Float.NaN, SolidAngleUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatSolidAngle POSITIVE_INFINITY = new FloatSolidAngle(Float.POSITIVE_INFINITY, SolidAngleUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatSolidAngle NEGATIVE_INFINITY = new FloatSolidAngle(Float.NEGATIVE_INFINITY, SolidAngleUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatSolidAngle POS_MAXVALUE = new FloatSolidAngle(Float.MAX_VALUE, SolidAngleUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatSolidAngle NEG_MAXVALUE = new FloatSolidAngle(-Float.MAX_VALUE, SolidAngleUnit.SI);

    /**
     * Construct FloatSolidAngle scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatSolidAngle(final float value, final SolidAngleUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatSolidAngle scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatSolidAngle(final FloatSolidAngle value)
    {
        super(value);
    }

    /**
     * Construct FloatSolidAngle scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatSolidAngle(final double value, final SolidAngleUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatSolidAngle instantiateRel(final float value, final SolidAngleUnit unit)
    {
        return new FloatSolidAngle(value, unit);
    }

    /**
     * Construct FloatSolidAngle scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatSolidAngle instantiateSI(final float value)
    {
        return new FloatSolidAngle(value, SolidAngleUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatSolidAngle interpolate(final FloatSolidAngle zero, final FloatSolidAngle one, final float ratio)
    {
        return new FloatSolidAngle(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatSolidAngle max(final FloatSolidAngle r1, final FloatSolidAngle r2)
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
    public static FloatSolidAngle max(final FloatSolidAngle r1, final FloatSolidAngle r2, final FloatSolidAngle... rn)
    {
        FloatSolidAngle maxr = r1.gt(r2) ? r1 : r2;
        for (FloatSolidAngle r : rn)
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
    public static FloatSolidAngle min(final FloatSolidAngle r1, final FloatSolidAngle r2)
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
    public static FloatSolidAngle min(final FloatSolidAngle r1, final FloatSolidAngle r2, final FloatSolidAngle... rn)
    {
        FloatSolidAngle minr = r1.lt(r2) ? r1 : r2;
        for (FloatSolidAngle r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatSolidAngle representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatSolidAngle
     * @return FloatSolidAngle; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatSolidAngle valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatSolidAngle: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatSolidAngle: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            SolidAngleUnit unit = SolidAngleUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatSolidAngle(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatSolidAngle from " + text);
    }

    /**
     * Returns a FloatSolidAngle based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatSolidAngle; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatSolidAngle of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatSolidAngle: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatSolidAngle: empty unitString");
        SolidAngleUnit unit = SolidAngleUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatSolidAngle(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatSolidAngle with unit " + unitString);
    }

    /**
     * Calculate the division of FloatSolidAngle and FloatSolidAngle, which results in a FloatDimensionless scalar.
     * @param v FloatSolidAngle; scalar
     * @return FloatDimensionless; scalar as a division of FloatSolidAngle and FloatSolidAngle
     */
    public final FloatDimensionless divide(final FloatSolidAngle v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatSolidAngle and FloatLuminousIntensity, which results in a FloatLuminousFlux scalar.
     * @param v FloatSolidAngle; scalar
     * @return FloatLuminousFlux; scalar as a multiplication of FloatSolidAngle and FloatLuminousIntensity
     */
    public final FloatLuminousFlux times(final FloatLuminousIntensity v)
    {
        return new FloatLuminousFlux(this.si * v.si, LuminousFluxUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
