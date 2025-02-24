package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatLuminousIntensity FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatLuminousIntensity extends FloatScalarRel<LuminousIntensityUnit, FloatLuminousIntensity>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatLuminousIntensity ZERO = new FloatLuminousIntensity(0.0f, LuminousIntensityUnit.SI);

    /** Constant with value one. */
    public static final FloatLuminousIntensity ONE = new FloatLuminousIntensity(1.0f, LuminousIntensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatLuminousIntensity NaN = new FloatLuminousIntensity(Float.NaN, LuminousIntensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatLuminousIntensity POSITIVE_INFINITY =
            new FloatLuminousIntensity(Float.POSITIVE_INFINITY, LuminousIntensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatLuminousIntensity NEGATIVE_INFINITY =
            new FloatLuminousIntensity(Float.NEGATIVE_INFINITY, LuminousIntensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatLuminousIntensity POS_MAXVALUE =
            new FloatLuminousIntensity(Float.MAX_VALUE, LuminousIntensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatLuminousIntensity NEG_MAXVALUE =
            new FloatLuminousIntensity(-Float.MAX_VALUE, LuminousIntensityUnit.SI);

    /**
     * Construct FloatLuminousIntensity scalar.
     * @param value the float value
     * @param unit unit for the float value
     */
    public FloatLuminousIntensity(final float value, final LuminousIntensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatLuminousIntensity scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatLuminousIntensity(final FloatLuminousIntensity value)
    {
        super(value);
    }

    /**
     * Construct FloatLuminousIntensity scalar using a double value.
     * @param value the double value
     * @param unit unit for the resulting float value
     */
    public FloatLuminousIntensity(final double value, final LuminousIntensityUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatLuminousIntensity instantiateRel(final float value, final LuminousIntensityUnit unit)
    {
        return new FloatLuminousIntensity(value, unit);
    }

    /**
     * Construct FloatLuminousIntensity scalar.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatLuminousIntensity instantiateSI(final float value)
    {
        return new FloatLuminousIntensity(value, LuminousIntensityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatLuminousIntensity interpolate(final FloatLuminousIntensity zero, final FloatLuminousIntensity one,
            final float ratio)
    {
        return new FloatLuminousIntensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatLuminousIntensity max(final FloatLuminousIntensity r1, final FloatLuminousIntensity r2)
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
    public static FloatLuminousIntensity max(final FloatLuminousIntensity r1, final FloatLuminousIntensity r2,
            final FloatLuminousIntensity... rn)
    {
        FloatLuminousIntensity maxr = r1.gt(r2) ? r1 : r2;
        for (FloatLuminousIntensity r : rn)
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
    public static FloatLuminousIntensity min(final FloatLuminousIntensity r1, final FloatLuminousIntensity r2)
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
    public static FloatLuminousIntensity min(final FloatLuminousIntensity r1, final FloatLuminousIntensity r2,
            final FloatLuminousIntensity... rn)
    {
        FloatLuminousIntensity minr = r1.lt(r2) ? r1 : r2;
        for (FloatLuminousIntensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatLuminousIntensity representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatLuminousIntensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatLuminousIntensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatLuminousIntensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatLuminousIntensity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            LuminousIntensityUnit unit = LuminousIntensityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit == null)
                throw new IllegalArgumentException("Unit " + unitString + " not found");
            return new FloatLuminousIntensity(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatLuminousIntensity from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatLuminousIntensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatLuminousIntensity of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatLuminousIntensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatLuminousIntensity: empty unitString");
        LuminousIntensityUnit unit = LuminousIntensityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatLuminousIntensity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatLuminousIntensity with unit " + unitString);
    }

    /**
     * Calculate the division of FloatLuminousIntensity and FloatLuminousIntensity, which results in a FloatDimensionless
     * scalar.
     * @param v scalar
     * @return scalar as a division of FloatLuminousIntensity and FloatLuminousIntensity
     */
    public final FloatDimensionless divide(final FloatLuminousIntensity v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatLuminousIntensity and FloatSolidAngle, which results in a FloatLuminousFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatLuminousIntensity and FloatSolidAngle
     */
    public final FloatLuminousFlux times(final FloatSolidAngle v)
    {
        return new FloatLuminousFlux(this.si * v.si, LuminousFluxUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
