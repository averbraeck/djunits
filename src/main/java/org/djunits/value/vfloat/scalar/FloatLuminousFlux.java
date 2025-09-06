package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.IlluminanceUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatLuminousFlux FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FloatLuminousFlux extends FloatScalarRel<LuminousFluxUnit, FloatLuminousFlux>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatLuminousFlux ZERO = new FloatLuminousFlux(0.0f, LuminousFluxUnit.SI);

    /** Constant with value one. */
    public static final FloatLuminousFlux ONE = new FloatLuminousFlux(1.0f, LuminousFluxUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatLuminousFlux NaN = new FloatLuminousFlux(Float.NaN, LuminousFluxUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatLuminousFlux POSITIVE_INFINITY =
            new FloatLuminousFlux(Float.POSITIVE_INFINITY, LuminousFluxUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatLuminousFlux NEGATIVE_INFINITY =
            new FloatLuminousFlux(Float.NEGATIVE_INFINITY, LuminousFluxUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatLuminousFlux POS_MAXVALUE = new FloatLuminousFlux(Float.MAX_VALUE, LuminousFluxUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatLuminousFlux NEG_MAXVALUE = new FloatLuminousFlux(-Float.MAX_VALUE, LuminousFluxUnit.SI);

    /**
     * Construct FloatLuminousFlux scalar.
     * @param value the float value
     * @param unit unit for the float value
     */
    public FloatLuminousFlux(final float value, final LuminousFluxUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatLuminousFlux scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatLuminousFlux(final FloatLuminousFlux value)
    {
        super(value);
    }

    /**
     * Construct FloatLuminousFlux scalar using a double value.
     * @param value the double value
     * @param unit unit for the resulting float value
     */
    public FloatLuminousFlux(final double value, final LuminousFluxUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatLuminousFlux instantiateRel(final float value, final LuminousFluxUnit unit)
    {
        return new FloatLuminousFlux(value, unit);
    }

    /**
     * Construct FloatLuminousFlux scalar.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatLuminousFlux instantiateSI(final float value)
    {
        return new FloatLuminousFlux(value, LuminousFluxUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatLuminousFlux interpolate(final FloatLuminousFlux zero, final FloatLuminousFlux one, final float ratio)
    {
        return new FloatLuminousFlux(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatLuminousFlux max(final FloatLuminousFlux r1, final FloatLuminousFlux r2)
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
    public static FloatLuminousFlux max(final FloatLuminousFlux r1, final FloatLuminousFlux r2, final FloatLuminousFlux... rn)
    {
        FloatLuminousFlux maxr = r1.gt(r2) ? r1 : r2;
        for (FloatLuminousFlux r : rn)
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
    public static FloatLuminousFlux min(final FloatLuminousFlux r1, final FloatLuminousFlux r2)
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
    public static FloatLuminousFlux min(final FloatLuminousFlux r1, final FloatLuminousFlux r2, final FloatLuminousFlux... rn)
    {
        FloatLuminousFlux minr = r1.lt(r2) ? r1 : r2;
        for (FloatLuminousFlux r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatLuminousFlux representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatLuminousFlux
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatLuminousFlux valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatLuminousFlux: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatLuminousFlux: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            LuminousFluxUnit unit = LuminousFluxUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity LuminousFlux", unitString);
            return new FloatLuminousFlux(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatLuminousFlux from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatLuminousFlux based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatLuminousFlux of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatLuminousFlux: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatLuminousFlux: empty unitString");
        LuminousFluxUnit unit = LuminousFluxUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatLuminousFlux with unit %s", unitString);
        return new FloatLuminousFlux(value, unit);
    }

    /**
     * Calculate the division of FloatLuminousFlux and FloatLuminousFlux, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatLuminousFlux and FloatLuminousFlux
     */
    public final FloatDimensionless divide(final FloatLuminousFlux v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of FloatLuminousFlux and FloatArea, which results in a FloatIlluminance scalar.
     * @param v scalar
     * @return scalar as a division of FloatLuminousFlux and FloatArea
     */
    public final FloatIlluminance divide(final FloatArea v)
    {
        return new FloatIlluminance(this.si / v.si, IlluminanceUnit.SI);
    }

    /**
     * Calculate the division of FloatLuminousFlux and FloatIlluminance, which results in a FloatArea scalar.
     * @param v scalar
     * @return scalar as a division of FloatLuminousFlux and FloatIlluminance
     */
    public final FloatArea divide(final FloatIlluminance v)
    {
        return new FloatArea(this.si / v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of FloatLuminousFlux and FloatLuminousIntensity, which results in a FloatSolidAngle scalar.
     * @param v scalar
     * @return scalar as a division of FloatLuminousFlux and FloatLuminousIntensity
     */
    public final FloatSolidAngle divide(final FloatLuminousIntensity v)
    {
        return new FloatSolidAngle(this.si / v.si, SolidAngleUnit.SI);
    }

    /**
     * Calculate the division of FloatLuminousFlux and FloatSolidAngle, which results in a FloatLuminousIntensity scalar.
     * @param v scalar
     * @return scalar as a division of FloatLuminousFlux and FloatSolidAngle
     */
    public final FloatLuminousIntensity divide(final FloatSolidAngle v)
    {
        return new FloatLuminousIntensity(this.si / v.si, LuminousIntensityUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
