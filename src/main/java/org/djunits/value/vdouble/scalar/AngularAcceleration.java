package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the AngularAcceleration DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class AngularAcceleration extends DoubleScalarRel<AngularAccelerationUnit, AngularAcceleration>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final AngularAcceleration ZERO = new AngularAcceleration(0.0, AngularAccelerationUnit.SI);

    /** Constant with value one. */
    public static final AngularAcceleration ONE = new AngularAcceleration(1.0, AngularAccelerationUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final AngularAcceleration NaN = new AngularAcceleration(Double.NaN, AngularAccelerationUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final AngularAcceleration POSITIVE_INFINITY =
            new AngularAcceleration(Double.POSITIVE_INFINITY, AngularAccelerationUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final AngularAcceleration NEGATIVE_INFINITY =
            new AngularAcceleration(Double.NEGATIVE_INFINITY, AngularAccelerationUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final AngularAcceleration POS_MAXVALUE =
            new AngularAcceleration(Double.MAX_VALUE, AngularAccelerationUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final AngularAcceleration NEG_MAXVALUE =
            new AngularAcceleration(-Double.MAX_VALUE, AngularAccelerationUnit.SI);

    /**
     * Construct AngularAcceleration scalar.
     * @param value the double value
     * @param unit unit for the double value
     */
    public AngularAcceleration(final double value, final AngularAccelerationUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct AngularAcceleration scalar.
     * @param value Scalar from which to construct this instance
     */
    public AngularAcceleration(final AngularAcceleration value)
    {
        super(value);
    }

    @Override
    public final AngularAcceleration instantiateRel(final double value, final AngularAccelerationUnit unit)
    {
        return new AngularAcceleration(value, unit);
    }

    /**
     * Construct AngularAcceleration scalar.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final AngularAcceleration ofSI(final double value)
    {
        return new AngularAcceleration(value, AngularAccelerationUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static AngularAcceleration interpolate(final AngularAcceleration zero, final AngularAcceleration one,
            final double ratio)
    {
        return new AngularAcceleration(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static AngularAcceleration max(final AngularAcceleration r1, final AngularAcceleration r2)
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
    public static AngularAcceleration max(final AngularAcceleration r1, final AngularAcceleration r2,
            final AngularAcceleration... rn)
    {
        AngularAcceleration maxr = r1.gt(r2) ? r1 : r2;
        for (AngularAcceleration r : rn)
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
    public static AngularAcceleration min(final AngularAcceleration r1, final AngularAcceleration r2)
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
    public static AngularAcceleration min(final AngularAcceleration r1, final AngularAcceleration r2,
            final AngularAcceleration... rn)
    {
        AngularAcceleration minr = r1.lt(r2) ? r1 : r2;
        for (AngularAcceleration r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a AngularAcceleration representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a AngularAcceleration
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static AngularAcceleration valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing AngularAcceleration: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing AngularAcceleration: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AngularAccelerationUnit unit = AngularAccelerationUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity AngularAcceleration",
                    unitString);
            return new AngularAcceleration(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing AngularAcceleration from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a AngularAcceleration based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AngularAcceleration of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing AngularAcceleration: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing AngularAcceleration: empty unitString");
        AngularAccelerationUnit unit = AngularAccelerationUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing AngularAcceleration with unit %s", unitString);
        return new AngularAcceleration(value, unit);
    }

    /**
     * Calculate the division of AngularAcceleration and AngularAcceleration, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of AngularAcceleration and AngularAcceleration
     */
    public final Dimensionless divide(final AngularAcceleration v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of AngularAcceleration and Duration, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a multiplication of AngularAcceleration and Duration
     */
    public final AngularVelocity times(final Duration v)
    {
        return new AngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of AngularAcceleration and Frequency, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a division of AngularAcceleration and Frequency
     */
    public final AngularVelocity divide(final Frequency v)
    {
        return new AngularVelocity(this.si / v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of AngularAcceleration and AngularVelocity, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of AngularAcceleration and AngularVelocity
     */
    public final Frequency divide(final AngularVelocity v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
