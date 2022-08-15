package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;

/**
 * Easy access methods for the Frequency DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Frequency extends AbstractDoubleScalarRel<FrequencyUnit, Frequency>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Frequency ZERO = new Frequency(0.0, FrequencyUnit.SI);

    /** Constant with value one. */
    public static final Frequency ONE = new Frequency(1.0, FrequencyUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Frequency NaN = new Frequency(Double.NaN, FrequencyUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Frequency POSITIVE_INFINITY = new Frequency(Double.POSITIVE_INFINITY, FrequencyUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Frequency NEGATIVE_INFINITY = new Frequency(Double.NEGATIVE_INFINITY, FrequencyUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Frequency POS_MAXVALUE = new Frequency(Double.MAX_VALUE, FrequencyUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Frequency NEG_MAXVALUE = new Frequency(-Double.MAX_VALUE, FrequencyUnit.SI);

    /**
     * Construct Frequency scalar.
     * @param value double; the double value
     * @param unit FrequencyUnit; unit for the double value
     */
    public Frequency(final double value, final FrequencyUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Frequency scalar.
     * @param value Frequency; Scalar from which to construct this instance
     */
    public Frequency(final Frequency value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Frequency instantiateRel(final double value, final FrequencyUnit unit)
    {
        return new Frequency(value, unit);
    }

    /**
     * Construct Frequency scalar.
     * @param value double; the double value in SI units
     * @return Frequency; the new scalar with the SI value
     */
    public static final Frequency instantiateSI(final double value)
    {
        return new Frequency(value, FrequencyUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Frequency; the low value
     * @param one Frequency; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Frequency; a Scalar at the ratio between
     */
    public static Frequency interpolate(final Frequency zero, final Frequency one, final double ratio)
    {
        return new Frequency(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Frequency; the first scalar
     * @param r2 Frequency; the second scalar
     * @return Frequency; the maximum value of two relative scalars
     */
    public static Frequency max(final Frequency r1, final Frequency r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Frequency; the first scalar
     * @param r2 Frequency; the second scalar
     * @param rn Frequency...; the other scalars
     * @return Frequency; the maximum value of more than two relative scalars
     */
    public static Frequency max(final Frequency r1, final Frequency r2, final Frequency... rn)
    {
        Frequency maxr = r1.gt(r2) ? r1 : r2;
        for (Frequency r : rn)
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
     * @param r1 Frequency; the first scalar
     * @param r2 Frequency; the second scalar
     * @return Frequency; the minimum value of two relative scalars
     */
    public static Frequency min(final Frequency r1, final Frequency r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Frequency; the first scalar
     * @param r2 Frequency; the second scalar
     * @param rn Frequency...; the other scalars
     * @return Frequency; the minimum value of more than two relative scalars
     */
    public static Frequency min(final Frequency r1, final Frequency r2, final Frequency... rn)
    {
        Frequency minr = r1.lt(r2) ? r1 : r2;
        for (Frequency r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Frequency representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Frequency
     * @return Frequency; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Frequency valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Frequency: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Frequency: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            FrequencyUnit unit = FrequencyUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Frequency(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Frequency from " + text);
    }

    /**
     * Returns a Frequency based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Frequency; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Frequency of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Frequency: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Frequency: empty unitString");
        FrequencyUnit unit = FrequencyUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Frequency(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Frequency with unit " + unitString);
    }

    /**
     * Calculate the division of Frequency and Frequency, which results in a Dimensionless scalar.
     * @param v Frequency; scalar
     * @return Dimensionless; scalar as a division of Frequency and Frequency
     */
    public final Dimensionless divide(final Frequency v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Frequency and Duration, which results in a Dimensionless scalar.
     * @param v Frequency; scalar
     * @return Dimensionless; scalar as a multiplication of Frequency and Duration
     */
    public final Dimensionless times(final Duration v)
    {
        return new Dimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Frequency and Length, which results in a Speed scalar.
     * @param v Frequency; scalar
     * @return Speed; scalar as a multiplication of Frequency and Length
     */
    public final Speed times(final Length v)
    {
        return new Speed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the multiplication of Frequency and Speed, which results in a Acceleration scalar.
     * @param v Frequency; scalar
     * @return Acceleration; scalar as a multiplication of Frequency and Speed
     */
    public final Acceleration times(final Speed v)
    {
        return new Acceleration(this.si * v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the multiplication of Frequency and Energy, which results in a Power scalar.
     * @param v Frequency; scalar
     * @return Power; scalar as a multiplication of Frequency and Energy
     */
    public final Power times(final Energy v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the multiplication of Frequency and Angle, which results in a AngularVelocity scalar.
     * @param v Frequency; scalar
     * @return AngularVelocity; scalar as a multiplication of Frequency and Angle
     */
    public final AngularVelocity times(final Angle v)
    {
        return new AngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    /**
     * Calculate the multiplication of Frequency and AngularVelocity, which results in a AngularAcceleration scalar.
     * @param v Frequency; scalar
     * @return AngularAcceleration; scalar as a multiplication of Frequency and AngularVelocity
     */
    public final AngularAcceleration times(final AngularVelocity v)
    {
        return new AngularAcceleration(this.si * v.si, AngularAccelerationUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public Duration reciprocal()
    {
        return Duration.instantiateSI(1.0 / this.si);
    }

}
