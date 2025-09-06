package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the AngularVelocity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class AngularVelocity extends DoubleScalarRel<AngularVelocityUnit, AngularVelocity>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final AngularVelocity ZERO = new AngularVelocity(0.0, AngularVelocityUnit.SI);

    /** Constant with value one. */
    public static final AngularVelocity ONE = new AngularVelocity(1.0, AngularVelocityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final AngularVelocity NaN = new AngularVelocity(Double.NaN, AngularVelocityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final AngularVelocity POSITIVE_INFINITY =
            new AngularVelocity(Double.POSITIVE_INFINITY, AngularVelocityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final AngularVelocity NEGATIVE_INFINITY =
            new AngularVelocity(Double.NEGATIVE_INFINITY, AngularVelocityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final AngularVelocity POS_MAXVALUE = new AngularVelocity(Double.MAX_VALUE, AngularVelocityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final AngularVelocity NEG_MAXVALUE = new AngularVelocity(-Double.MAX_VALUE, AngularVelocityUnit.SI);

    /**
     * Construct AngularVelocity scalar.
     * @param value the double value
     * @param unit unit for the double value
     */
    public AngularVelocity(final double value, final AngularVelocityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct AngularVelocity scalar.
     * @param value Scalar from which to construct this instance
     */
    public AngularVelocity(final AngularVelocity value)
    {
        super(value);
    }

    @Override
    public final AngularVelocity instantiateRel(final double value, final AngularVelocityUnit unit)
    {
        return new AngularVelocity(value, unit);
    }

    /**
     * Construct AngularVelocity scalar.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final AngularVelocity ofSI(final double value)
    {
        return new AngularVelocity(value, AngularVelocityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static AngularVelocity interpolate(final AngularVelocity zero, final AngularVelocity one, final double ratio)
    {
        return new AngularVelocity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static AngularVelocity max(final AngularVelocity r1, final AngularVelocity r2)
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
    public static AngularVelocity max(final AngularVelocity r1, final AngularVelocity r2, final AngularVelocity... rn)
    {
        AngularVelocity maxr = r1.gt(r2) ? r1 : r2;
        for (AngularVelocity r : rn)
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
    public static AngularVelocity min(final AngularVelocity r1, final AngularVelocity r2)
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
    public static AngularVelocity min(final AngularVelocity r1, final AngularVelocity r2, final AngularVelocity... rn)
    {
        AngularVelocity minr = r1.lt(r2) ? r1 : r2;
        for (AngularVelocity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a AngularVelocity representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a AngularVelocity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static AngularVelocity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing AngularVelocity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing AngularVelocity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AngularVelocityUnit unit = AngularVelocityUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity AngularVelocity",
                    unitString);
            return new AngularVelocity(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing AngularVelocity from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a AngularVelocity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AngularVelocity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing AngularVelocity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing AngularVelocity: empty unitString");
        AngularVelocityUnit unit = AngularVelocityUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing AngularVelocity with unit %s", unitString);
        return new AngularVelocity(value, unit);
    }

    /**
     * Calculate the division of AngularVelocity and AngularVelocity, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of AngularVelocity and AngularVelocity
     */
    public final Dimensionless divide(final AngularVelocity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of AngularVelocity and Angle, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of AngularVelocity and Angle
     */
    public final Frequency divide(final Angle v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of AngularVelocity and Frequency, which results in a Angle scalar.
     * @param v scalar
     * @return scalar as a division of AngularVelocity and Frequency
     */
    public final Angle divide(final Frequency v)
    {
        return new Angle(this.si / v.si, AngleUnit.SI);
    }

    /**
     * Calculate the multiplication of AngularVelocity and Duration, which results in a Angle scalar.
     * @param v scalar
     * @return scalar as a multiplication of AngularVelocity and Duration
     */
    public final Angle times(final Duration v)
    {
        return new Angle(this.si * v.si, AngleUnit.SI);
    }

    /**
     * Calculate the division of AngularVelocity and Duration, which results in a AngularAcceleration scalar.
     * @param v scalar
     * @return scalar as a division of AngularVelocity and Duration
     */
    public final AngularAcceleration divide(final Duration v)
    {
        return new AngularAcceleration(this.si / v.si, AngularAccelerationUnit.SI);
    }

    /**
     * Calculate the division of AngularVelocity and AngularAcceleration, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of AngularVelocity and AngularAcceleration
     */
    public final Duration divide(final AngularAcceleration v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of AngularVelocity and Frequency, which results in a AngularAcceleration scalar.
     * @param v scalar
     * @return scalar as a multiplication of AngularVelocity and Frequency
     */
    public final AngularAcceleration times(final Frequency v)
    {
        return new AngularAcceleration(this.si * v.si, AngularAccelerationUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
