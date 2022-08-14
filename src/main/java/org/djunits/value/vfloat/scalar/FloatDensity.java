package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.MassUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatDensity FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatDensity extends AbstractFloatScalarRel<DensityUnit, FloatDensity>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatDensity ZERO = new FloatDensity(0.0f, DensityUnit.SI);

    /** Constant with value one. */
    public static final FloatDensity ONE = new FloatDensity(1.0f, DensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatDensity NaN = new FloatDensity(Float.NaN, DensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatDensity POSITIVE_INFINITY = new FloatDensity(Float.POSITIVE_INFINITY, DensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatDensity NEGATIVE_INFINITY = new FloatDensity(Float.NEGATIVE_INFINITY, DensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatDensity POS_MAXVALUE = new FloatDensity(Float.MAX_VALUE, DensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatDensity NEG_MAXVALUE = new FloatDensity(-Float.MAX_VALUE, DensityUnit.SI);

    /**
     * Construct FloatDensity scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatDensity(final float value, final DensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatDensity scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatDensity(final FloatDensity value)
    {
        super(value);
    }

    /**
     * Construct FloatDensity scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatDensity(final double value, final DensityUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatDensity instantiateRel(final float value, final DensityUnit unit)
    {
        return new FloatDensity(value, unit);
    }

    /**
     * Construct FloatDensity scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatDensity instantiateSI(final float value)
    {
        return new FloatDensity(value, DensityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatDensity interpolate(final FloatDensity zero, final FloatDensity one, final float ratio)
    {
        return new FloatDensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatDensity max(final FloatDensity r1, final FloatDensity r2)
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
    public static FloatDensity max(final FloatDensity r1, final FloatDensity r2, final FloatDensity... rn)
    {
        FloatDensity maxr = r1.gt(r2) ? r1 : r2;
        for (FloatDensity r : rn)
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
    public static FloatDensity min(final FloatDensity r1, final FloatDensity r2)
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
    public static FloatDensity min(final FloatDensity r1, final FloatDensity r2, final FloatDensity... rn)
    {
        FloatDensity minr = r1.lt(r2) ? r1 : r2;
        for (FloatDensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatDensity representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatDensity
     * @return FloatDensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatDensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatDensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatDensity: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            DensityUnit unit = DensityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatDensity(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatDensity from " + text);
    }

    /**
     * Returns a FloatDensity based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatDensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatDensity of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatDensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatDensity: empty unitString");
        DensityUnit unit = DensityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatDensity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatDensity with unit " + unitString);
    }

    /**
     * Calculate the division of FloatDensity and FloatDensity, which results in a FloatDimensionless scalar.
     * @param v FloatDensity; scalar
     * @return FloatDimensionless; scalar as a division of FloatDensity and FloatDensity
     */
    public final FloatDimensionless divide(final FloatDensity v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDensity and FloatVolume, which results in a FloatMass scalar.
     * @param v FloatDensity; scalar
     * @return FloatMass; scalar as a multiplication of FloatDensity and FloatVolume
     */
    public final FloatMass times(final FloatVolume v)
    {
        return new FloatMass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatDensity and FloatFlowVolume, which results in a FloatFlowMass scalar.
     * @param v FloatDensity; scalar
     * @return FloatFlowMass; scalar as a multiplication of FloatDensity and FloatFlowVolume
     */
    public final FloatFlowMass times(final FloatFlowVolume v)
    {
        return new FloatFlowMass(this.si * v.si, FlowMassUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
