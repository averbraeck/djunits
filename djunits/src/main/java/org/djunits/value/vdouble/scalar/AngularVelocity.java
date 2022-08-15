package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the AngularVelocity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AngularVelocity extends AbstractDoubleScalarRel<AngularVelocityUnit, AngularVelocity>
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
     * @param value double; the double value
     * @param unit AngularVelocityUnit; unit for the double value
     */
    public AngularVelocity(final double value, final AngularVelocityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct AngularVelocity scalar.
     * @param value AngularVelocity; Scalar from which to construct this instance
     */
    public AngularVelocity(final AngularVelocity value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final AngularVelocity instantiateRel(final double value, final AngularVelocityUnit unit)
    {
        return new AngularVelocity(value, unit);
    }

    /**
     * Construct AngularVelocity scalar.
     * @param value double; the double value in SI units
     * @return AngularVelocity; the new scalar with the SI value
     */
    public static final AngularVelocity instantiateSI(final double value)
    {
        return new AngularVelocity(value, AngularVelocityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero AngularVelocity; the low value
     * @param one AngularVelocity; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return AngularVelocity; a Scalar at the ratio between
     */
    public static AngularVelocity interpolate(final AngularVelocity zero, final AngularVelocity one, final double ratio)
    {
        return new AngularVelocity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 AngularVelocity; the first scalar
     * @param r2 AngularVelocity; the second scalar
     * @return AngularVelocity; the maximum value of two relative scalars
     */
    public static AngularVelocity max(final AngularVelocity r1, final AngularVelocity r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 AngularVelocity; the first scalar
     * @param r2 AngularVelocity; the second scalar
     * @param rn AngularVelocity...; the other scalars
     * @return AngularVelocity; the maximum value of more than two relative scalars
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
     * @param r1 AngularVelocity; the first scalar
     * @param r2 AngularVelocity; the second scalar
     * @return AngularVelocity; the minimum value of two relative scalars
     */
    public static AngularVelocity min(final AngularVelocity r1, final AngularVelocity r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 AngularVelocity; the first scalar
     * @param r2 AngularVelocity; the second scalar
     * @param rn AngularVelocity...; the other scalars
     * @return AngularVelocity; the minimum value of more than two relative scalars
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
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a AngularVelocity
     * @return AngularVelocity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static AngularVelocity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing AngularVelocity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing AngularVelocity: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AngularVelocityUnit unit = AngularVelocityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new AngularVelocity(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing AngularVelocity from " + text);
    }

    /**
     * Returns a AngularVelocity based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return AngularVelocity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AngularVelocity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing AngularVelocity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing AngularVelocity: empty unitString");
        AngularVelocityUnit unit = AngularVelocityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new AngularVelocity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing AngularVelocity with unit " + unitString);
    }

    /**
     * Calculate the division of AngularVelocity and AngularVelocity, which results in a Dimensionless scalar.
     * @param v AngularVelocity; scalar
     * @return Dimensionless; scalar as a division of AngularVelocity and AngularVelocity
     */
    public final Dimensionless divide(final AngularVelocity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of AngularVelocity and Angle, which results in a Frequency scalar.
     * @param v AngularVelocity; scalar
     * @return Frequency; scalar as a division of AngularVelocity and Angle
     */
    public final Frequency divide(final Angle v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of AngularVelocity and Frequency, which results in a Angle scalar.
     * @param v AngularVelocity; scalar
     * @return Angle; scalar as a division of AngularVelocity and Frequency
     */
    public final Angle divide(final Frequency v)
    {
        return new Angle(this.si / v.si, AngleUnit.SI);
    }

    /**
     * Calculate the multiplication of AngularVelocity and Duration, which results in a Angle scalar.
     * @param v AngularVelocity; scalar
     * @return Angle; scalar as a multiplication of AngularVelocity and Duration
     */
    public final Angle times(final Duration v)
    {
        return new Angle(this.si * v.si, AngleUnit.SI);
    }

    /**
     * Calculate the division of AngularVelocity and Duration, which results in a AngularAcceleration scalar.
     * @param v AngularVelocity; scalar
     * @return AngularAcceleration; scalar as a division of AngularVelocity and Duration
     */
    public final AngularAcceleration divide(final Duration v)
    {
        return new AngularAcceleration(this.si / v.si, AngularAccelerationUnit.SI);
    }

    /**
     * Calculate the division of AngularVelocity and AngularAcceleration, which results in a Duration scalar.
     * @param v AngularVelocity; scalar
     * @return Duration; scalar as a division of AngularVelocity and AngularAcceleration
     */
    public final Duration divide(final AngularAcceleration v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of AngularVelocity and Frequency, which results in a AngularAcceleration scalar.
     * @param v AngularVelocity; scalar
     * @return AngularAcceleration; scalar as a multiplication of AngularVelocity and Frequency
     */
    public final AngularAcceleration times(final Frequency v)
    {
        return new AngularAcceleration(this.si * v.si, AngularAccelerationUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
