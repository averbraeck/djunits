package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the LuminousIntensity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class LuminousIntensity extends DoubleScalarRel<LuminousIntensityUnit, LuminousIntensity>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final LuminousIntensity ZERO = new LuminousIntensity(0.0, LuminousIntensityUnit.SI);

    /** Constant with value one. */
    public static final LuminousIntensity ONE = new LuminousIntensity(1.0, LuminousIntensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LuminousIntensity NaN = new LuminousIntensity(Double.NaN, LuminousIntensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LuminousIntensity POSITIVE_INFINITY =
            new LuminousIntensity(Double.POSITIVE_INFINITY, LuminousIntensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LuminousIntensity NEGATIVE_INFINITY =
            new LuminousIntensity(Double.NEGATIVE_INFINITY, LuminousIntensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final LuminousIntensity POS_MAXVALUE = new LuminousIntensity(Double.MAX_VALUE, LuminousIntensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final LuminousIntensity NEG_MAXVALUE = new LuminousIntensity(-Double.MAX_VALUE, LuminousIntensityUnit.SI);

    /**
     * Construct LuminousIntensity scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public LuminousIntensity(final double value, final LuminousIntensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct LuminousIntensity scalar.
     * @param value Scalar from which to construct this instance
     */
    public LuminousIntensity(final LuminousIntensity value)
    {
        super(value);
    }

    @Override
    public final LuminousIntensity instantiateRel(final double value, final LuminousIntensityUnit unit)
    {
        return new LuminousIntensity(value, unit);
    }

    /**
     * Construct LuminousIntensity scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final LuminousIntensity ofSI(final double value)
    {
        return new LuminousIntensity(value, LuminousIntensityUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a LuminousIntensity at the given ratio between 0 and 1
     */
    public static LuminousIntensity interpolate(final LuminousIntensity zero, final LuminousIntensity one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new LuminousIntensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static LuminousIntensity max(final LuminousIntensity r1, final LuminousIntensity r2)
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
    public static LuminousIntensity max(final LuminousIntensity r1, final LuminousIntensity r2, final LuminousIntensity... rn)
    {
        LuminousIntensity maxr = r1.gt(r2) ? r1 : r2;
        for (LuminousIntensity r : rn)
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
    public static LuminousIntensity min(final LuminousIntensity r1, final LuminousIntensity r2)
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
    public static LuminousIntensity min(final LuminousIntensity r1, final LuminousIntensity r2, final LuminousIntensity... rn)
    {
        LuminousIntensity minr = r1.lt(r2) ? r1 : r2;
        for (LuminousIntensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a LuminousIntensity representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a LuminousIntensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LuminousIntensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing LuminousIntensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing LuminousIntensity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            LuminousIntensityUnit unit = LuminousIntensityUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity LuminousIntensity",
                    unitString);
            return new LuminousIntensity(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing LuminousIntensity from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a LuminousIntensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LuminousIntensity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing LuminousIntensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing LuminousIntensity: empty unitString");
        LuminousIntensityUnit unit = LuminousIntensityUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing LuminousIntensity with unit %s", unitString);
        return new LuminousIntensity(value, unit);
    }

    /**
     * Calculate the division of LuminousIntensity and LuminousIntensity, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of LuminousIntensity and LuminousIntensity
     */
    public final Dimensionless divide(final LuminousIntensity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of LuminousIntensity and SolidAngle, which results in a LuminousFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of LuminousIntensity and SolidAngle
     */
    public final LuminousFlux times(final SolidAngle v)
    {
        return new LuminousFlux(this.si * v.si, LuminousFluxUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
