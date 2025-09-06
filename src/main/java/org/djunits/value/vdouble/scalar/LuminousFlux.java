package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.IlluminanceUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the LuminousFlux DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class LuminousFlux extends DoubleScalarRel<LuminousFluxUnit, LuminousFlux>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final LuminousFlux ZERO = new LuminousFlux(0.0, LuminousFluxUnit.SI);

    /** Constant with value one. */
    public static final LuminousFlux ONE = new LuminousFlux(1.0, LuminousFluxUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LuminousFlux NaN = new LuminousFlux(Double.NaN, LuminousFluxUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LuminousFlux POSITIVE_INFINITY = new LuminousFlux(Double.POSITIVE_INFINITY, LuminousFluxUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LuminousFlux NEGATIVE_INFINITY = new LuminousFlux(Double.NEGATIVE_INFINITY, LuminousFluxUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final LuminousFlux POS_MAXVALUE = new LuminousFlux(Double.MAX_VALUE, LuminousFluxUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final LuminousFlux NEG_MAXVALUE = new LuminousFlux(-Double.MAX_VALUE, LuminousFluxUnit.SI);

    /**
     * Construct LuminousFlux scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public LuminousFlux(final double value, final LuminousFluxUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct LuminousFlux scalar.
     * @param value Scalar from which to construct this instance
     */
    public LuminousFlux(final LuminousFlux value)
    {
        super(value);
    }

    @Override
    public final LuminousFlux instantiateRel(final double value, final LuminousFluxUnit unit)
    {
        return new LuminousFlux(value, unit);
    }

    /**
     * Construct LuminousFlux scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final LuminousFlux ofSI(final double value)
    {
        return new LuminousFlux(value, LuminousFluxUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a LuminousFlux at the given ratio between 0 and 1
     */
    public static LuminousFlux interpolate(final LuminousFlux zero, final LuminousFlux one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new LuminousFlux(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static LuminousFlux max(final LuminousFlux r1, final LuminousFlux r2)
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
    public static LuminousFlux max(final LuminousFlux r1, final LuminousFlux r2, final LuminousFlux... rn)
    {
        LuminousFlux maxr = r1.gt(r2) ? r1 : r2;
        for (LuminousFlux r : rn)
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
    public static LuminousFlux min(final LuminousFlux r1, final LuminousFlux r2)
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
    public static LuminousFlux min(final LuminousFlux r1, final LuminousFlux r2, final LuminousFlux... rn)
    {
        LuminousFlux minr = r1.lt(r2) ? r1 : r2;
        for (LuminousFlux r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a LuminousFlux representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a LuminousFlux
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LuminousFlux valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing LuminousFlux: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing LuminousFlux: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            LuminousFluxUnit unit = LuminousFluxUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity LuminousFlux", unitString);
            return new LuminousFlux(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing LuminousFlux from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a LuminousFlux based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LuminousFlux of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing LuminousFlux: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing LuminousFlux: empty unitString");
        LuminousFluxUnit unit = LuminousFluxUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing LuminousFlux with unit %s", unitString);
        return new LuminousFlux(value, unit);
    }

    /**
     * Calculate the division of LuminousFlux and LuminousFlux, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and LuminousFlux
     */
    public final Dimensionless divide(final LuminousFlux v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and Area, which results in a Illuminance scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and Area
     */
    public final Illuminance divide(final Area v)
    {
        return new Illuminance(this.si / v.si, IlluminanceUnit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and Illuminance, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and Illuminance
     */
    public final Area divide(final Illuminance v)
    {
        return new Area(this.si / v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and LuminousIntensity, which results in a SolidAngle scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and LuminousIntensity
     */
    public final SolidAngle divide(final LuminousIntensity v)
    {
        return new SolidAngle(this.si / v.si, SolidAngleUnit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and SolidAngle, which results in a LuminousIntensity scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and SolidAngle
     */
    public final LuminousIntensity divide(final SolidAngle v)
    {
        return new LuminousIntensity(this.si / v.si, LuminousIntensityUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
