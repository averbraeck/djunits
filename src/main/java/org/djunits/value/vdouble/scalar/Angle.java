package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRelWithAbs;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Relative Angle DoubleScalar.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class Angle extends DoubleScalarRelWithAbs<DirectionUnit, Direction, AngleUnit, Angle>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Angle ZERO = new Angle(0.0, AngleUnit.SI);

    /** Constant with value one. */
    public static final Angle ONE = new Angle(1.0, AngleUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Angle NaN = new Angle(Double.NaN, AngleUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Angle POSITIVE_INFINITY = new Angle(Double.POSITIVE_INFINITY, AngleUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Angle NEGATIVE_INFINITY = new Angle(Double.NEGATIVE_INFINITY, AngleUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Angle POS_MAXVALUE = new Angle(Double.MAX_VALUE, AngleUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Angle NEG_MAXVALUE = new Angle(-Double.MAX_VALUE, AngleUnit.SI);

    /**
     * Construct Angle scalar.
     * @param value double value
     * @param unit unit for the double value
     */
    public Angle(final double value, final AngleUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Angle scalar.
     * @param value Scalar from which to construct this instance
     */
    public Angle(final Angle value)
    {
        super(value);
    }

    @Override
    public final Angle instantiateRel(final double value, final AngleUnit unit)
    {
        return new Angle(value, unit);
    }

    @Override
    public final Direction instantiateAbs(final double value, final DirectionUnit unit)
    {
        return new Direction(value, unit);
    }

    /**
     * Construct Angle scalar.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Angle instantiateSI(final double value)
    {
        return new Angle(value, AngleUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static Angle interpolate(final Angle zero, final Angle one, final double ratio)
    {
        return new Angle(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Angle max(final Angle r1, final Angle r2)
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
    public static Angle max(final Angle r1, final Angle r2, final Angle... rn)
    {
        Angle maxr = r1.gt(r2) ? r1 : r2;
        for (Angle r : rn)
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
    public static Angle min(final Angle r1, final Angle r2)
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
    public static Angle min(final Angle r1, final Angle r2, final Angle... rn)
    {
        Angle minr = r1.lt(r2) ? r1 : r2;
        for (Angle r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Angle representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Angle
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Angle valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Angle: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Angle: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AngleUnit unit = AngleUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit == null)
                throw new IllegalArgumentException("Unit " + unitString + " not found");
            return new Angle(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Angle from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Angle based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Angle of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Angle: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Angle: empty unitString");
        AngleUnit unit = AngleUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Angle(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Angle with unit " + unitString);
    }

    /**
     * Calculate the division of Angle and Angle, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Angle and Angle
     */
    public final Dimensionless divide(final Angle v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Angle and Frequency, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a multiplication of Angle and Frequency
     */
    public final AngularVelocity times(final Frequency v)
    {
        return new AngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of Angle and Duration, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a division of Angle and Duration
     */
    public final AngularVelocity divide(final Duration v)
    {
        return new AngularVelocity(this.si / v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of Angle and AngularVelocity, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Angle and AngularVelocity
     */
    public final Duration divide(final AngularVelocity v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
