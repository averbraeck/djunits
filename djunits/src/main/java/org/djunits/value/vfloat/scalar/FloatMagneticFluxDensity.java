package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatMagneticFluxDensity FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatMagneticFluxDensity extends AbstractFloatScalarRel<MagneticFluxDensityUnit, FloatMagneticFluxDensity>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatMagneticFluxDensity ZERO = new FloatMagneticFluxDensity(0.0f, MagneticFluxDensityUnit.SI);

    /** Constant with value one. */
    public static final FloatMagneticFluxDensity ONE = new FloatMagneticFluxDensity(1.0f, MagneticFluxDensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatMagneticFluxDensity NaN = new FloatMagneticFluxDensity(Float.NaN, MagneticFluxDensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatMagneticFluxDensity POSITIVE_INFINITY =
            new FloatMagneticFluxDensity(Float.POSITIVE_INFINITY, MagneticFluxDensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatMagneticFluxDensity NEGATIVE_INFINITY =
            new FloatMagneticFluxDensity(Float.NEGATIVE_INFINITY, MagneticFluxDensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatMagneticFluxDensity POS_MAXVALUE =
            new FloatMagneticFluxDensity(Float.MAX_VALUE, MagneticFluxDensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatMagneticFluxDensity NEG_MAXVALUE =
            new FloatMagneticFluxDensity(-Float.MAX_VALUE, MagneticFluxDensityUnit.SI);

    /**
     * Construct FloatMagneticFluxDensity scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatMagneticFluxDensity(final float value, final MagneticFluxDensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatMagneticFluxDensity scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatMagneticFluxDensity(final FloatMagneticFluxDensity value)
    {
        super(value);
    }

    /**
     * Construct FloatMagneticFluxDensity scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatMagneticFluxDensity(final double value, final MagneticFluxDensityUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMagneticFluxDensity instantiateRel(final float value, final MagneticFluxDensityUnit unit)
    {
        return new FloatMagneticFluxDensity(value, unit);
    }

    /**
     * Construct FloatMagneticFluxDensity scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatMagneticFluxDensity instantiateSI(final float value)
    {
        return new FloatMagneticFluxDensity(value, MagneticFluxDensityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatMagneticFluxDensity interpolate(final FloatMagneticFluxDensity zero, final FloatMagneticFluxDensity one,
            final float ratio)
    {
        return new FloatMagneticFluxDensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatMagneticFluxDensity max(final FloatMagneticFluxDensity r1, final FloatMagneticFluxDensity r2)
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
    public static FloatMagneticFluxDensity max(final FloatMagneticFluxDensity r1, final FloatMagneticFluxDensity r2,
            final FloatMagneticFluxDensity... rn)
    {
        FloatMagneticFluxDensity maxr = r1.gt(r2) ? r1 : r2;
        for (FloatMagneticFluxDensity r : rn)
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
    public static FloatMagneticFluxDensity min(final FloatMagneticFluxDensity r1, final FloatMagneticFluxDensity r2)
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
    public static FloatMagneticFluxDensity min(final FloatMagneticFluxDensity r1, final FloatMagneticFluxDensity r2,
            final FloatMagneticFluxDensity... rn)
    {
        FloatMagneticFluxDensity minr = r1.lt(r2) ? r1 : r2;
        for (FloatMagneticFluxDensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatMagneticFluxDensity representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatMagneticFluxDensity
     * @return FloatMagneticFluxDensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatMagneticFluxDensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatMagneticFluxDensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatMagneticFluxDensity: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            MagneticFluxDensityUnit unit = MagneticFluxDensityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatMagneticFluxDensity(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatMagneticFluxDensity from " + text);
    }

    /**
     * Returns a FloatMagneticFluxDensity based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatMagneticFluxDensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatMagneticFluxDensity of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatMagneticFluxDensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatMagneticFluxDensity: empty unitString");
        MagneticFluxDensityUnit unit = MagneticFluxDensityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatMagneticFluxDensity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatMagneticFluxDensity with unit " + unitString);
    }

    /**
     * Calculate the division of FloatMagneticFluxDensity and FloatMagneticFluxDensity, which results in a FloatDimensionless
     * scalar.
     * @param v FloatMagneticFluxDensity; scalar
     * @return FloatDimensionless; scalar as a division of FloatMagneticFluxDensity and FloatMagneticFluxDensity
     */
    public final FloatDimensionless divide(final FloatMagneticFluxDensity v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatMagneticFluxDensity and FloatArea, which results in a FloatMagneticFlux scalar.
     * @param v FloatMagneticFluxDensity; scalar
     * @return FloatMagneticFlux; scalar as a multiplication of FloatMagneticFluxDensity and FloatArea
     */
    public final FloatMagneticFlux times(final FloatArea v)
    {
        return new FloatMagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
