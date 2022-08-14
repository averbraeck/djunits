package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.MomentumUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Speed DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Speed extends AbstractDoubleScalarRel<SpeedUnit, Speed>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Speed ZERO = new Speed(0.0, SpeedUnit.SI);

    /** Constant with value one. */
    public static final Speed ONE = new Speed(1.0, SpeedUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Speed NaN = new Speed(Double.NaN, SpeedUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Speed POSITIVE_INFINITY = new Speed(Double.POSITIVE_INFINITY, SpeedUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Speed NEGATIVE_INFINITY = new Speed(Double.NEGATIVE_INFINITY, SpeedUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Speed POS_MAXVALUE = new Speed(Double.MAX_VALUE, SpeedUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Speed NEG_MAXVALUE = new Speed(-Double.MAX_VALUE, SpeedUnit.SI);

    /**
     * Construct Speed scalar.
     * @param value double; the double value
     * @param unit SpeedUnit; unit for the double value
     */
    public Speed(final double value, final SpeedUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Speed scalar.
     * @param value Speed; Scalar from which to construct this instance
     */
    public Speed(final Speed value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Speed instantiateRel(final double value, final SpeedUnit unit)
    {
        return new Speed(value, unit);
    }

    /**
     * Construct Speed scalar.
     * @param value double; the double value in SI units
     * @return Speed; the new scalar with the SI value
     */
    public static final Speed instantiateSI(final double value)
    {
        return new Speed(value, SpeedUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Speed; the low value
     * @param one Speed; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Speed; a Scalar at the ratio between
     */
    public static Speed interpolate(final Speed zero, final Speed one, final double ratio)
    {
        return new Speed(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Speed; the first scalar
     * @param r2 Speed; the second scalar
     * @return Speed; the maximum value of two relative scalars
     */
    public static Speed max(final Speed r1, final Speed r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Speed; the first scalar
     * @param r2 Speed; the second scalar
     * @param rn Speed...; the other scalars
     * @return Speed; the maximum value of more than two relative scalars
     */
    public static Speed max(final Speed r1, final Speed r2, final Speed... rn)
    {
        Speed maxr = r1.gt(r2) ? r1 : r2;
        for (Speed r : rn)
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
     * @param r1 Speed; the first scalar
     * @param r2 Speed; the second scalar
     * @return Speed; the minimum value of two relative scalars
     */
    public static Speed min(final Speed r1, final Speed r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Speed; the first scalar
     * @param r2 Speed; the second scalar
     * @param rn Speed...; the other scalars
     * @return Speed; the minimum value of more than two relative scalars
     */
    public static Speed min(final Speed r1, final Speed r2, final Speed... rn)
    {
        Speed minr = r1.lt(r2) ? r1 : r2;
        for (Speed r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Speed representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Speed
     * @return Speed; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Speed valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Speed: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Speed: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            SpeedUnit unit = SpeedUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Speed(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Speed from " + text);
    }

    /**
     * Returns a Speed based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Speed; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Speed of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Speed: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Speed: empty unitString");
        SpeedUnit unit = SpeedUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Speed(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Speed with unit " + unitString);
    }

    /**
     * Calculate the division of Speed and Speed, which results in a Dimensionless scalar.
     * @param v Speed; scalar
     * @return Dimensionless; scalar as a division of Speed and Speed
     */
    public final Dimensionless divide(final Speed v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Area, which results in a FlowVolume scalar.
     * @param v Speed; scalar
     * @return FlowVolume; scalar as a multiplication of Speed and Area
     */
    public final FlowVolume times(final Area v)
    {
        return new FlowVolume(this.si * v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Force, which results in a Power scalar.
     * @param v Speed; scalar
     * @return Power; scalar as a multiplication of Speed and Force
     */
    public final Power times(final Force v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Frequency, which results in a Acceleration scalar.
     * @param v Speed; scalar
     * @return Acceleration; scalar as a multiplication of Speed and Frequency
     */
    public final Acceleration times(final Frequency v)
    {
        return new Acceleration(this.si * v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the division of Speed and Length, which results in a Frequency scalar.
     * @param v Speed; scalar
     * @return Frequency; scalar as a division of Speed and Length
     */
    public final Frequency divide(final Length v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of Speed and Frequency, which results in a Length scalar.
     * @param v Speed; scalar
     * @return Length; scalar as a division of Speed and Frequency
     */
    public final Length divide(final Frequency v)
    {
        return new Length(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of Speed and LinearDensity, which results in a Frequency scalar.
     * @param v Speed; scalar
     * @return Frequency; scalar as a multiplication of Speed and LinearDensity
     */
    public final Frequency times(final LinearDensity v)
    {
        return new Frequency(this.si * v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Duration, which results in a Length scalar.
     * @param v Speed; scalar
     * @return Length; scalar as a multiplication of Speed and Duration
     */
    public final Length times(final Duration v)
    {
        return new Length(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the division of Speed and Duration, which results in a Acceleration scalar.
     * @param v Speed; scalar
     * @return Acceleration; scalar as a division of Speed and Duration
     */
    public final Acceleration divide(final Duration v)
    {
        return new Acceleration(this.si / v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the division of Speed and Acceleration, which results in a Duration scalar.
     * @param v Speed; scalar
     * @return Duration; scalar as a division of Speed and Acceleration
     */
    public final Duration divide(final Acceleration v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of Speed and FlowMass, which results in a Force scalar.
     * @param v Speed; scalar
     * @return Force; scalar as a multiplication of Speed and FlowMass
     */
    public final Force times(final FlowMass v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Mass, which results in a Momentum scalar.
     * @param v Speed; scalar
     * @return Momentum; scalar as a multiplication of Speed and Mass
     */
    public final Momentum times(final Mass v)
    {
        return new Momentum(this.si * v.si, MomentumUnit.SI);
    }

    /**
     * Calculate the multiplication of Speed and Momentum, which results in a Energy scalar.
     * @param v Speed; scalar
     * @return Energy; scalar as a multiplication of Speed and Momentum
     */
    public final Energy times(final Momentum v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
