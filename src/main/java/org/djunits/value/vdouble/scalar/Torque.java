package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.PressureUnit;
import org.djunits.unit.TorqueUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Torque DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Torque extends AbstractDoubleScalarRel<TorqueUnit, Torque>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Torque ZERO = new Torque(0.0, TorqueUnit.SI);

    /** Constant with value one. */
    public static final Torque ONE = new Torque(1.0, TorqueUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Torque NaN = new Torque(Double.NaN, TorqueUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Torque POSITIVE_INFINITY = new Torque(Double.POSITIVE_INFINITY, TorqueUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Torque NEGATIVE_INFINITY = new Torque(Double.NEGATIVE_INFINITY, TorqueUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Torque POS_MAXVALUE = new Torque(Double.MAX_VALUE, TorqueUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Torque NEG_MAXVALUE = new Torque(-Double.MAX_VALUE, TorqueUnit.SI);

    /**
     * Construct Torque scalar.
     * @param value double; the double value
     * @param unit TorqueUnit; unit for the double value
     */
    public Torque(final double value, final TorqueUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Torque scalar.
     * @param value Torque; Scalar from which to construct this instance
     */
    public Torque(final Torque value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Torque instantiateRel(final double value, final TorqueUnit unit)
    {
        return new Torque(value, unit);
    }

    /**
     * Construct Torque scalar.
     * @param value double; the double value in SI units
     * @return Torque; the new scalar with the SI value
     */
    public static final Torque instantiateSI(final double value)
    {
        return new Torque(value, TorqueUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Torque; the low value
     * @param one Torque; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Torque; a Scalar at the ratio between
     */
    public static Torque interpolate(final Torque zero, final Torque one, final double ratio)
    {
        return new Torque(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Torque; the first scalar
     * @param r2 Torque; the second scalar
     * @return Torque; the maximum value of two relative scalars
     */
    public static Torque max(final Torque r1, final Torque r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Torque; the first scalar
     * @param r2 Torque; the second scalar
     * @param rn Torque...; the other scalars
     * @return Torque; the maximum value of more than two relative scalars
     */
    public static Torque max(final Torque r1, final Torque r2, final Torque... rn)
    {
        Torque maxr = r1.gt(r2) ? r1 : r2;
        for (Torque r : rn)
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
     * @param r1 Torque; the first scalar
     * @param r2 Torque; the second scalar
     * @return Torque; the minimum value of two relative scalars
     */
    public static Torque min(final Torque r1, final Torque r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Torque; the first scalar
     * @param r2 Torque; the second scalar
     * @param rn Torque...; the other scalars
     * @return Torque; the minimum value of more than two relative scalars
     */
    public static Torque min(final Torque r1, final Torque r2, final Torque... rn)
    {
        Torque minr = r1.lt(r2) ? r1 : r2;
        for (Torque r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Torque representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Torque
     * @return Torque; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Torque valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Torque: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Torque: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            TorqueUnit unit = TorqueUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Torque(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Torque from " + text);
    }

    /**
     * Returns a Torque based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Torque; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Torque of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Torque: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Torque: empty unitString");
        TorqueUnit unit = TorqueUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Torque(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Torque with unit " + unitString);
    }

    /**
     * Calculate the division of Torque and Torque, which results in a Dimensionless scalar.
     * @param v Torque; scalar
     * @return Dimensionless; scalar as a division of Torque and Torque
     */
    public final Dimensionless divide(final Torque v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of Torque and Force, which results in a Length scalar.
     * @param v Torque; scalar
     * @return Length; scalar as a division of Torque and Force
     */
    public final Length divide(final Force v)
    {
        return new Length(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the division of Torque and Length, which results in a Force scalar.
     * @param v Torque; scalar
     * @return Force; scalar as a division of Torque and Length
     */
    public final Force divide(final Length v)
    {
        return new Force(this.si / v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of Torque and LinearDensity, which results in a Force scalar.
     * @param v Torque; scalar
     * @return Force; scalar as a multiplication of Torque and LinearDensity
     */
    public final Force times(final LinearDensity v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the division of Torque and Duration, which results in a Power scalar.
     * @param v Torque; scalar
     * @return Power; scalar as a division of Torque and Duration
     */
    public final Power divide(final Duration v)
    {
        return new Power(this.si / v.si, PowerUnit.SI);
    }

    /**
     * Calculate the division of Torque and Power, which results in a Duration scalar.
     * @param v Torque; scalar
     * @return Duration; scalar as a division of Torque and Power
     */
    public final Duration divide(final Power v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of Torque and Frequency, which results in a Power scalar.
     * @param v Torque; scalar
     * @return Power; scalar as a multiplication of Torque and Frequency
     */
    public final Power times(final Frequency v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the division of Torque and Volume, which results in a Pressure scalar.
     * @param v Torque; scalar
     * @return Pressure; scalar as a division of Torque and Volume
     */
    public final Pressure divide(final Volume v)
    {
        return new Pressure(this.si / v.si, PressureUnit.SI);
    }

    /**
     * Calculate the division of Torque and Pressure, which results in a Volume scalar.
     * @param v Torque; scalar
     * @return Volume; scalar as a division of Torque and Pressure
     */
    public final Volume divide(final Pressure v)
    {
        return new Volume(this.si / v.si, VolumeUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
