package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRelWithAbs;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatAngle FloatScalar.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FloatAngle extends FloatScalarRelWithAbs<DirectionUnit, FloatDirection, AngleUnit, FloatAngle>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatAngle ZERO = new FloatAngle(0.0f, AngleUnit.SI);

    /** Constant with value one. */
    public static final FloatAngle ONE = new FloatAngle(1.0f, AngleUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatAngle NaN = new FloatAngle(Float.NaN, AngleUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatAngle POSITIVE_INFINITY = new FloatAngle(Float.POSITIVE_INFINITY, AngleUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatAngle NEGATIVE_INFINITY = new FloatAngle(Float.NEGATIVE_INFINITY, AngleUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatAngle POS_MAXVALUE = new FloatAngle(Float.MAX_VALUE, AngleUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatAngle NEG_MAXVALUE = new FloatAngle(-Float.MAX_VALUE, AngleUnit.SI);

    /**
     * Construct FloatAngle scalar.
     * @param value the float value
     * @param unit unit for the float value
     */
    public FloatAngle(final float value, final AngleUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatAngle scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatAngle(final FloatAngle value)
    {
        super(value);
    }

    /**
     * Construct FloatAngle scalar using a double value.
     * @param value the double value
     * @param unit unit for the resulting float value
     */
    public FloatAngle(final double value, final AngleUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatAngle instantiateRel(final float value, final AngleUnit unit)
    {
        return new FloatAngle(value, unit);
    }

    /**
     * Construct FloatAngle scalar.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatAngle instantiateSI(final float value)
    {
        return new FloatAngle(value, AngleUnit.SI);
    }

    @Override
    public final FloatDirection instantiateAbs(final float value, final DirectionUnit unit)
    {
        return new FloatDirection(value, unit);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatAngle interpolate(final FloatAngle zero, final FloatAngle one, final float ratio)
    {
        return new FloatAngle(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatAngle max(final FloatAngle r1, final FloatAngle r2)
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
    public static FloatAngle max(final FloatAngle r1, final FloatAngle r2, final FloatAngle... rn)
    {
        FloatAngle maxr = r1.gt(r2) ? r1 : r2;
        for (FloatAngle r : rn)
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
    public static FloatAngle min(final FloatAngle r1, final FloatAngle r2)
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
    public static FloatAngle min(final FloatAngle r1, final FloatAngle r2, final FloatAngle... rn)
    {
        FloatAngle minr = r1.lt(r2) ? r1 : r2;
        for (FloatAngle r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatAngle representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatAngle
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAngle valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAngle: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatAngle: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AngleUnit unit = AngleUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Angle", unitString);
            return new FloatAngle(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatAngle from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FloatAngle based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAngle of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAngle: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatAngle: empty unitString");
        AngleUnit unit = AngleUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatAngle with unit %s", unitString);
        return new FloatAngle(value, unit);
    }

    /**
     * Calculate the division of FloatAngle and FloatAngle, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatAngle and FloatAngle
     */
    public final FloatDimensionless divide(final FloatAngle v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatAngle and FloatFrequency, which results in a FloatAngularVelocity scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatAngle and FloatFrequency
     */
    public final FloatAngularVelocity times(final FloatFrequency v)
    {
        return new FloatAngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of FloatAngle and FloatDuration, which results in a FloatAngularVelocity scalar.
     * @param v scalar
     * @return scalar as a division of FloatAngle and FloatDuration
     */
    public final FloatAngularVelocity divide(final FloatDuration v)
    {
        return new FloatAngularVelocity(this.si / v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of FloatAngle and FloatAngularVelocity, which results in a FloatDuration scalar.
     * @param v scalar
     * @return scalar as a division of FloatAngle and FloatAngularVelocity
     */
    public final FloatDuration divide(final FloatAngularVelocity v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
