package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.IlluminanceUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Illuminance DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class Illuminance extends DoubleScalarRel<IlluminanceUnit, Illuminance>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Illuminance ZERO = new Illuminance(0.0, IlluminanceUnit.SI);

    /** Constant with value one. */
    public static final Illuminance ONE = new Illuminance(1.0, IlluminanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Illuminance NaN = new Illuminance(Double.NaN, IlluminanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Illuminance POSITIVE_INFINITY = new Illuminance(Double.POSITIVE_INFINITY, IlluminanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Illuminance NEGATIVE_INFINITY = new Illuminance(Double.NEGATIVE_INFINITY, IlluminanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Illuminance POS_MAXVALUE = new Illuminance(Double.MAX_VALUE, IlluminanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Illuminance NEG_MAXVALUE = new Illuminance(-Double.MAX_VALUE, IlluminanceUnit.SI);

    /**
     * Construct Illuminance scalar.
     * @param value the double value
     * @param unit unit for the double value
     */
    public Illuminance(final double value, final IlluminanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Illuminance scalar.
     * @param value Scalar from which to construct this instance
     */
    public Illuminance(final Illuminance value)
    {
        super(value);
    }

    @Override
    public final Illuminance instantiateRel(final double value, final IlluminanceUnit unit)
    {
        return new Illuminance(value, unit);
    }

    /**
     * Construct Illuminance scalar.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Illuminance instantiateSI(final double value)
    {
        return new Illuminance(value, IlluminanceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static Illuminance interpolate(final Illuminance zero, final Illuminance one, final double ratio)
    {
        return new Illuminance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Illuminance max(final Illuminance r1, final Illuminance r2)
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
    public static Illuminance max(final Illuminance r1, final Illuminance r2, final Illuminance... rn)
    {
        Illuminance maxr = r1.gt(r2) ? r1 : r2;
        for (Illuminance r : rn)
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
    public static Illuminance min(final Illuminance r1, final Illuminance r2)
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
    public static Illuminance min(final Illuminance r1, final Illuminance r2, final Illuminance... rn)
    {
        Illuminance minr = r1.lt(r2) ? r1 : r2;
        for (Illuminance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Illuminance representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Illuminance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Illuminance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Illuminance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Illuminance: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            IlluminanceUnit unit = IlluminanceUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Illuminance", unitString);
            return new Illuminance(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Illuminance from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Illuminance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Illuminance of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Illuminance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Illuminance: empty unitString");
        IlluminanceUnit unit = IlluminanceUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Illuminance with unit %s", unitString);
        return new Illuminance(value, unit);
    }

    /**
     * Calculate the division of Illuminance and Illuminance, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Illuminance and Illuminance
     */
    public final Dimensionless divide(final Illuminance v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Illuminance and Area, which results in a LuminousFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of Illuminance and Area
     */
    public final LuminousFlux times(final Area v)
    {
        return new LuminousFlux(this.si * v.si, LuminousFluxUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
