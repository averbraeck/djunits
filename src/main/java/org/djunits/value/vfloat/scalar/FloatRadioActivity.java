package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.RadioActivityUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatRadioActivity FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatRadioActivity extends FloatScalarRel<RadioActivityUnit, FloatRadioActivity>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatRadioActivity ZERO = new FloatRadioActivity(0.0f, RadioActivityUnit.SI);

    /** Constant with value one. */
    public static final FloatRadioActivity ONE = new FloatRadioActivity(1.0f, RadioActivityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatRadioActivity NaN = new FloatRadioActivity(Float.NaN, RadioActivityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatRadioActivity POSITIVE_INFINITY =
            new FloatRadioActivity(Float.POSITIVE_INFINITY, RadioActivityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatRadioActivity NEGATIVE_INFINITY =
            new FloatRadioActivity(Float.NEGATIVE_INFINITY, RadioActivityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatRadioActivity POS_MAXVALUE = new FloatRadioActivity(Float.MAX_VALUE, RadioActivityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatRadioActivity NEG_MAXVALUE = new FloatRadioActivity(-Float.MAX_VALUE, RadioActivityUnit.SI);

    /**
     * Construct FloatRadioActivity scalar.
     * @param value the float value
     * @param unit unit for the float value
     */
    public FloatRadioActivity(final float value, final RadioActivityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatRadioActivity scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatRadioActivity(final FloatRadioActivity value)
    {
        super(value);
    }

    /**
     * Construct FloatRadioActivity scalar using a double value.
     * @param value the double value
     * @param unit unit for the resulting float value
     */
    public FloatRadioActivity(final double value, final RadioActivityUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatRadioActivity instantiateRel(final float value, final RadioActivityUnit unit)
    {
        return new FloatRadioActivity(value, unit);
    }

    /**
     * Construct FloatRadioActivity scalar.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatRadioActivity instantiateSI(final float value)
    {
        return new FloatRadioActivity(value, RadioActivityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatRadioActivity interpolate(final FloatRadioActivity zero, final FloatRadioActivity one, final float ratio)
    {
        return new FloatRadioActivity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatRadioActivity max(final FloatRadioActivity r1, final FloatRadioActivity r2)
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
    public static FloatRadioActivity max(final FloatRadioActivity r1, final FloatRadioActivity r2,
            final FloatRadioActivity... rn)
    {
        FloatRadioActivity maxr = r1.gt(r2) ? r1 : r2;
        for (FloatRadioActivity r : rn)
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
    public static FloatRadioActivity min(final FloatRadioActivity r1, final FloatRadioActivity r2)
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
    public static FloatRadioActivity min(final FloatRadioActivity r1, final FloatRadioActivity r2,
            final FloatRadioActivity... rn)
    {
        FloatRadioActivity minr = r1.lt(r2) ? r1 : r2;
        for (FloatRadioActivity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatRadioActivity representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatRadioActivity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatRadioActivity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatRadioActivity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatRadioActivity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            RadioActivityUnit unit = RadioActivityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit == null)
                throw new IllegalArgumentException("Unit " + unitString + " not found");
            return new FloatRadioActivity(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatRadioActivity from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatRadioActivity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatRadioActivity of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatRadioActivity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatRadioActivity: empty unitString");
        RadioActivityUnit unit = RadioActivityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatRadioActivity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatRadioActivity with unit " + unitString);
    }

    /**
     * Calculate the division of FloatRadioActivity and FloatRadioActivity, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatRadioActivity and FloatRadioActivity
     */
    public final FloatDimensionless divide(final FloatRadioActivity v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
