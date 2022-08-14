package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the AngularAcceleration DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AngularAcceleration extends AbstractDoubleScalarRel<AngularAccelerationUnit, AngularAcceleration>
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
     * @param value double; the double value
     * @param unit AngularAccelerationUnit; unit for the double value
     */
    public AngularAcceleration(final double value, final AngularAccelerationUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct AngularAcceleration scalar.
     * @param value AngularAcceleration; Scalar from which to construct this instance
     */
    public AngularAcceleration(final AngularAcceleration value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final AngularAcceleration instantiateRel(final double value, final AngularAccelerationUnit unit)
    {
        return new AngularAcceleration(value, unit);
    }

    /**
     * Construct AngularAcceleration scalar.
     * @param value double; the double value in SI units
     * @return AngularAcceleration; the new scalar with the SI value
     */
    public static final AngularAcceleration instantiateSI(final double value)
    {
        return new AngularAcceleration(value, AngularAccelerationUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero AngularAcceleration; the low value
     * @param one AngularAcceleration; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return AngularAcceleration; a Scalar at the ratio between
     */
    public static AngularAcceleration interpolate(final AngularAcceleration zero, final AngularAcceleration one,
            final double ratio)
    {
        return new AngularAcceleration(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 AngularAcceleration; the first scalar
     * @param r2 AngularAcceleration; the second scalar
     * @return AngularAcceleration; the maximum value of two relative scalars
     */
    public static AngularAcceleration max(final AngularAcceleration r1, final AngularAcceleration r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 AngularAcceleration; the first scalar
     * @param r2 AngularAcceleration; the second scalar
     * @param rn AngularAcceleration...; the other scalars
     * @return AngularAcceleration; the maximum value of more than two relative scalars
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
     * @param r1 AngularAcceleration; the first scalar
     * @param r2 AngularAcceleration; the second scalar
     * @return AngularAcceleration; the minimum value of two relative scalars
     */
    public static AngularAcceleration min(final AngularAcceleration r1, final AngularAcceleration r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 AngularAcceleration; the first scalar
     * @param r2 AngularAcceleration; the second scalar
     * @param rn AngularAcceleration...; the other scalars
     * @return AngularAcceleration; the minimum value of more than two relative scalars
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
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a AngularAcceleration
     * @return AngularAcceleration; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static AngularAcceleration valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing AngularAcceleration: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing AngularAcceleration: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AngularAccelerationUnit unit = AngularAccelerationUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new AngularAcceleration(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing AngularAcceleration from " + text);
    }

    /**
     * Returns a AngularAcceleration based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return AngularAcceleration; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AngularAcceleration of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing AngularAcceleration: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing AngularAcceleration: empty unitString");
        AngularAccelerationUnit unit = AngularAccelerationUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new AngularAcceleration(value, unit);
        }
        throw new IllegalArgumentException("Error parsing AngularAcceleration with unit " + unitString);
    }

    /**
     * Calculate the division of AngularAcceleration and AngularAcceleration, which results in a Dimensionless scalar.
     * @param v AngularAcceleration; scalar
     * @return Dimensionless; scalar as a division of AngularAcceleration and AngularAcceleration
     */
    public final Dimensionless divide(final AngularAcceleration v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of AngularAcceleration and Duration, which results in a AngularVelocity scalar.
     * @param v AngularAcceleration; scalar
     * @return AngularVelocity; scalar as a multiplication of AngularAcceleration and Duration
     */
    public final AngularVelocity times(final Duration v)
    {
        return new AngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of AngularAcceleration and Frequency, which results in a AngularVelocity scalar.
     * @param v AngularAcceleration; scalar
     * @return AngularVelocity; scalar as a division of AngularAcceleration and Frequency
     */
    public final AngularVelocity divide(final Frequency v)
    {
        return new AngularVelocity(this.si / v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the division of AngularAcceleration and AngularVelocity, which results in a Frequency scalar.
     * @param v AngularAcceleration; scalar
     * @return Frequency; scalar as a division of AngularAcceleration and AngularVelocity
     */
    public final Frequency divide(final AngularVelocity v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
