package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.IlluminanceUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatIlluminance FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FloatIlluminance extends FloatScalarRel<IlluminanceUnit, FloatIlluminance>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatIlluminance ZERO = new FloatIlluminance(0.0f, IlluminanceUnit.SI);

    /** Constant with value one. */
    public static final FloatIlluminance ONE = new FloatIlluminance(1.0f, IlluminanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatIlluminance NaN = new FloatIlluminance(Float.NaN, IlluminanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatIlluminance POSITIVE_INFINITY = new FloatIlluminance(Float.POSITIVE_INFINITY, IlluminanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatIlluminance NEGATIVE_INFINITY = new FloatIlluminance(Float.NEGATIVE_INFINITY, IlluminanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatIlluminance POS_MAXVALUE = new FloatIlluminance(Float.MAX_VALUE, IlluminanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatIlluminance NEG_MAXVALUE = new FloatIlluminance(-Float.MAX_VALUE, IlluminanceUnit.SI);

    /**
     * Construct FloatIlluminance scalar.
     * @param value the float value
     * @param unit unit for the float value
     */
    public FloatIlluminance(final float value, final IlluminanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatIlluminance scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatIlluminance(final FloatIlluminance value)
    {
        super(value);
    }

    /**
     * Construct FloatIlluminance scalar using a double value.
     * @param value the double value
     * @param unit unit for the resulting float value
     */
    public FloatIlluminance(final double value, final IlluminanceUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatIlluminance instantiateRel(final float value, final IlluminanceUnit unit)
    {
        return new FloatIlluminance(value, unit);
    }

    /**
     * Construct FloatIlluminance scalar.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatIlluminance instantiateSI(final float value)
    {
        return new FloatIlluminance(value, IlluminanceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatIlluminance interpolate(final FloatIlluminance zero, final FloatIlluminance one, final float ratio)
    {
        return new FloatIlluminance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatIlluminance max(final FloatIlluminance r1, final FloatIlluminance r2)
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
    public static FloatIlluminance max(final FloatIlluminance r1, final FloatIlluminance r2, final FloatIlluminance... rn)
    {
        FloatIlluminance maxr = r1.gt(r2) ? r1 : r2;
        for (FloatIlluminance r : rn)
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
    public static FloatIlluminance min(final FloatIlluminance r1, final FloatIlluminance r2)
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
    public static FloatIlluminance min(final FloatIlluminance r1, final FloatIlluminance r2, final FloatIlluminance... rn)
    {
        FloatIlluminance minr = r1.lt(r2) ? r1 : r2;
        for (FloatIlluminance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatIlluminance representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatIlluminance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatIlluminance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatIlluminance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatIlluminance: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            IlluminanceUnit unit = IlluminanceUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Illuminance", unitString);
            return new FloatIlluminance(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatIlluminance from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatIlluminance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatIlluminance of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatIlluminance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatIlluminance: empty unitString");
        IlluminanceUnit unit = IlluminanceUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatIlluminance with unit %s", unitString);
        return new FloatIlluminance(value, unit);
    }

    /**
     * Calculate the division of FloatIlluminance and FloatIlluminance, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatIlluminance and FloatIlluminance
     */
    public final FloatDimensionless divide(final FloatIlluminance v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatIlluminance and FloatArea, which results in a FloatLuminousFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatIlluminance and FloatArea
     */
    public final FloatLuminousFlux times(final FloatArea v)
    {
        return new FloatLuminousFlux(this.si * v.si, LuminousFluxUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
