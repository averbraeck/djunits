package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Acceleration DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Acceleration extends AbstractDoubleScalarRel<AccelerationUnit, Acceleration>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Acceleration ZERO = new Acceleration(0.0, AccelerationUnit.SI);

    /** Constant with value one. */
    public static final Acceleration ONE = new Acceleration(1.0, AccelerationUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Acceleration NaN = new Acceleration(Double.NaN, AccelerationUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Acceleration POSITIVE_INFINITY = new Acceleration(Double.POSITIVE_INFINITY, AccelerationUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Acceleration NEGATIVE_INFINITY = new Acceleration(Double.NEGATIVE_INFINITY, AccelerationUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Acceleration POS_MAXVALUE = new Acceleration(Double.MAX_VALUE, AccelerationUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Acceleration NEG_MAXVALUE = new Acceleration(-Double.MAX_VALUE, AccelerationUnit.SI);

    /**
     * Construct Acceleration scalar.
     * @param value double; the double value
     * @param unit AccelerationUnit; unit for the double value
     */
    public Acceleration(final double value, final AccelerationUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Acceleration scalar.
     * @param value Acceleration; Scalar from which to construct this instance
     */
    public Acceleration(final Acceleration value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Acceleration instantiateRel(final double value, final AccelerationUnit unit)
    {
        return new Acceleration(value, unit);
    }

    /**
     * Construct Acceleration scalar.
     * @param value double; the double value in SI units
     * @return Acceleration; the new scalar with the SI value
     */
    public static final Acceleration instantiateSI(final double value)
    {
        return new Acceleration(value, AccelerationUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Acceleration; the low value
     * @param one Acceleration; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Acceleration; a Scalar at the ratio between
     */
    public static Acceleration interpolate(final Acceleration zero, final Acceleration one, final double ratio)
    {
        return new Acceleration(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Acceleration; the first scalar
     * @param r2 Acceleration; the second scalar
     * @return Acceleration; the maximum value of two relative scalars
     */
    public static Acceleration max(final Acceleration r1, final Acceleration r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Acceleration; the first scalar
     * @param r2 Acceleration; the second scalar
     * @param rn Acceleration...; the other scalars
     * @return Acceleration; the maximum value of more than two relative scalars
     */
    public static Acceleration max(final Acceleration r1, final Acceleration r2, final Acceleration... rn)
    {
        Acceleration maxr = r1.gt(r2) ? r1 : r2;
        for (Acceleration r : rn)
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
     * @param r1 Acceleration; the first scalar
     * @param r2 Acceleration; the second scalar
     * @return Acceleration; the minimum value of two relative scalars
     */
    public static Acceleration min(final Acceleration r1, final Acceleration r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Acceleration; the first scalar
     * @param r2 Acceleration; the second scalar
     * @param rn Acceleration...; the other scalars
     * @return Acceleration; the minimum value of more than two relative scalars
     */
    public static Acceleration min(final Acceleration r1, final Acceleration r2, final Acceleration... rn)
    {
        Acceleration minr = r1.lt(r2) ? r1 : r2;
        for (Acceleration r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Acceleration representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a Acceleration
     * @return Acceleration; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Acceleration valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Acceleration: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Acceleration: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AccelerationUnit unit = AccelerationUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Acceleration(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Acceleration from " + text);
    }

    /**
     * Returns a Acceleration based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Acceleration; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Acceleration of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Acceleration: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Acceleration: empty unitString");
        AccelerationUnit unit = AccelerationUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Acceleration(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Acceleration with unit " + unitString);
    }

    /**
     * Calculate the division of Acceleration and Acceleration, which results in a Dimensionless scalar.
     * @param v Acceleration; scalar
     * @return Dimensionless; scalar as a division of Acceleration and Acceleration
     */
    public final Dimensionless divide(final Acceleration v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Acceleration and Mass, which results in a Force scalar.
     * @param v Acceleration; scalar
     * @return Force; scalar as a multiplication of Acceleration and Mass
     */
    public final Force times(final Mass v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of Acceleration and Duration, which results in a Speed scalar.
     * @param v Acceleration; scalar
     * @return Speed; scalar as a multiplication of Acceleration and Duration
     */
    public final Speed times(final Duration v)
    {
        return new Speed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of Acceleration and Frequency, which results in a Speed scalar.
     * @param v Acceleration; scalar
     * @return Speed; scalar as a division of Acceleration and Frequency
     */
    public final Speed divide(final Frequency v)
    {
        return new Speed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of Acceleration and Speed, which results in a Frequency scalar.
     * @param v Acceleration; scalar
     * @return Frequency; scalar as a division of Acceleration and Speed
     */
    public final Frequency divide(final Speed v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the multiplication of Acceleration and Momentum, which results in a Power scalar.
     * @param v Acceleration; scalar
     * @return Power; scalar as a multiplication of Acceleration and Momentum
     */
    public final Power times(final Momentum v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
