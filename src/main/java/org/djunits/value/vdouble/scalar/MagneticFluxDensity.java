package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the MagneticFluxDensity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class MagneticFluxDensity extends DoubleScalarRel<MagneticFluxDensityUnit, MagneticFluxDensity>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final MagneticFluxDensity ZERO = new MagneticFluxDensity(0.0, MagneticFluxDensityUnit.SI);

    /** Constant with value one. */
    public static final MagneticFluxDensity ONE = new MagneticFluxDensity(1.0, MagneticFluxDensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final MagneticFluxDensity NaN = new MagneticFluxDensity(Double.NaN, MagneticFluxDensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final MagneticFluxDensity POSITIVE_INFINITY =
            new MagneticFluxDensity(Double.POSITIVE_INFINITY, MagneticFluxDensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final MagneticFluxDensity NEGATIVE_INFINITY =
            new MagneticFluxDensity(Double.NEGATIVE_INFINITY, MagneticFluxDensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final MagneticFluxDensity POS_MAXVALUE =
            new MagneticFluxDensity(Double.MAX_VALUE, MagneticFluxDensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final MagneticFluxDensity NEG_MAXVALUE =
            new MagneticFluxDensity(-Double.MAX_VALUE, MagneticFluxDensityUnit.SI);

    /**
     * Construct MagneticFluxDensity scalar.
     * @param value the double value
     * @param unit unit for the double value
     */
    public MagneticFluxDensity(final double value, final MagneticFluxDensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct MagneticFluxDensity scalar.
     * @param value Scalar from which to construct this instance
     */
    public MagneticFluxDensity(final MagneticFluxDensity value)
    {
        super(value);
    }

    @Override
    public final MagneticFluxDensity instantiateRel(final double value, final MagneticFluxDensityUnit unit)
    {
        return new MagneticFluxDensity(value, unit);
    }

    /**
     * Construct MagneticFluxDensity scalar.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final MagneticFluxDensity instantiateSI(final double value)
    {
        return new MagneticFluxDensity(value, MagneticFluxDensityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static MagneticFluxDensity interpolate(final MagneticFluxDensity zero, final MagneticFluxDensity one,
            final double ratio)
    {
        return new MagneticFluxDensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static MagneticFluxDensity max(final MagneticFluxDensity r1, final MagneticFluxDensity r2)
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
    public static MagneticFluxDensity max(final MagneticFluxDensity r1, final MagneticFluxDensity r2,
            final MagneticFluxDensity... rn)
    {
        MagneticFluxDensity maxr = r1.gt(r2) ? r1 : r2;
        for (MagneticFluxDensity r : rn)
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
    public static MagneticFluxDensity min(final MagneticFluxDensity r1, final MagneticFluxDensity r2)
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
    public static MagneticFluxDensity min(final MagneticFluxDensity r1, final MagneticFluxDensity r2,
            final MagneticFluxDensity... rn)
    {
        MagneticFluxDensity minr = r1.lt(r2) ? r1 : r2;
        for (MagneticFluxDensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a MagneticFluxDensity representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a MagneticFluxDensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static MagneticFluxDensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing MagneticFluxDensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing MagneticFluxDensity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            MagneticFluxDensityUnit unit = MagneticFluxDensityUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity MagneticFluxDensity",
                    unitString);
            return new MagneticFluxDensity(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing MagneticFluxDensity from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a MagneticFluxDensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static MagneticFluxDensity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing MagneticFluxDensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing MagneticFluxDensity: empty unitString");
        MagneticFluxDensityUnit unit = MagneticFluxDensityUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing MagneticFluxDensity with unit %s", unitString);
        return new MagneticFluxDensity(value, unit);
    }

    /**
     * Calculate the division of MagneticFluxDensity and MagneticFluxDensity, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of MagneticFluxDensity and MagneticFluxDensity
     */
    public final Dimensionless divide(final MagneticFluxDensity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of MagneticFluxDensity and Area, which results in a MagneticFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of MagneticFluxDensity and Area
     */
    public final MagneticFlux times(final Area v)
    {
        return new MagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
