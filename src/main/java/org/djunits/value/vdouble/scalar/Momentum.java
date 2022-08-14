package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.MomentumUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Momentum DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Momentum extends AbstractDoubleScalarRel<MomentumUnit, Momentum>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Momentum ZERO = new Momentum(0.0, MomentumUnit.SI);

    /** Constant with value one. */
    public static final Momentum ONE = new Momentum(1.0, MomentumUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Momentum NaN = new Momentum(Double.NaN, MomentumUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Momentum POSITIVE_INFINITY = new Momentum(Double.POSITIVE_INFINITY, MomentumUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Momentum NEGATIVE_INFINITY = new Momentum(Double.NEGATIVE_INFINITY, MomentumUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Momentum POS_MAXVALUE = new Momentum(Double.MAX_VALUE, MomentumUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Momentum NEG_MAXVALUE = new Momentum(-Double.MAX_VALUE, MomentumUnit.SI);

    /**
     * Construct Momentum scalar.
     * @param value double; the double value
     * @param unit MomentumUnit; unit for the double value
     */
    public Momentum(final double value, final MomentumUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Momentum scalar.
     * @param value Momentum; Scalar from which to construct this instance
     */
    public Momentum(final Momentum value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Momentum instantiateRel(final double value, final MomentumUnit unit)
    {
        return new Momentum(value, unit);
    }

    /**
     * Construct Momentum scalar.
     * @param value double; the double value in SI units
     * @return Momentum; the new scalar with the SI value
     */
    public static final Momentum instantiateSI(final double value)
    {
        return new Momentum(value, MomentumUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Momentum; the low value
     * @param one Momentum; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Momentum; a Scalar at the ratio between
     */
    public static Momentum interpolate(final Momentum zero, final Momentum one, final double ratio)
    {
        return new Momentum(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Momentum; the first scalar
     * @param r2 Momentum; the second scalar
     * @return Momentum; the maximum value of two relative scalars
     */
    public static Momentum max(final Momentum r1, final Momentum r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Momentum; the first scalar
     * @param r2 Momentum; the second scalar
     * @param rn Momentum...; the other scalars
     * @return Momentum; the maximum value of more than two relative scalars
     */
    public static Momentum max(final Momentum r1, final Momentum r2, final Momentum... rn)
    {
        Momentum maxr = r1.gt(r2) ? r1 : r2;
        for (Momentum r : rn)
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
     * @param r1 Momentum; the first scalar
     * @param r2 Momentum; the second scalar
     * @return Momentum; the minimum value of two relative scalars
     */
    public static Momentum min(final Momentum r1, final Momentum r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Momentum; the first scalar
     * @param r2 Momentum; the second scalar
     * @param rn Momentum...; the other scalars
     * @return Momentum; the minimum value of more than two relative scalars
     */
    public static Momentum min(final Momentum r1, final Momentum r2, final Momentum... rn)
    {
        Momentum minr = r1.lt(r2) ? r1 : r2;
        for (Momentum r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Momentum representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Momentum
     * @return Momentum; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Momentum valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Momentum: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Momentum: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            MomentumUnit unit = MomentumUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Momentum(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Momentum from " + text);
    }

    /**
     * Returns a Momentum based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Momentum; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Momentum of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Momentum: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Momentum: empty unitString");
        MomentumUnit unit = MomentumUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Momentum(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Momentum with unit " + unitString);
    }

    /**
     * Calculate the division of Momentum and Momentum, which results in a Dimensionless scalar.
     * @param v Momentum; scalar
     * @return Dimensionless; scalar as a division of Momentum and Momentum
     */
    public final Dimensionless divide(final Momentum v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of Momentum and Speed, which results in a Mass scalar.
     * @param v Momentum; scalar
     * @return Mass; scalar as a division of Momentum and Speed
     */
    public final Mass divide(final Speed v)
    {
        return new Mass(this.si / v.si, MassUnit.SI);
    }

    /**
     * Calculate the division of Momentum and Mass, which results in a Speed scalar.
     * @param v Momentum; scalar
     * @return Speed; scalar as a division of Momentum and Mass
     */
    public final Speed divide(final Mass v)
    {
        return new Speed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of Momentum and Length, which results in a FlowMass scalar.
     * @param v Momentum; scalar
     * @return FlowMass; scalar as a division of Momentum and Length
     */
    public final FlowMass divide(final Length v)
    {
        return new FlowMass(this.si / v.si, FlowMassUnit.SI);
    }

    /**
     * Calculate the division of Momentum and FlowMass, which results in a Length scalar.
     * @param v Momentum; scalar
     * @return Length; scalar as a division of Momentum and FlowMass
     */
    public final Length divide(final FlowMass v)
    {
        return new Length(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of Momentum and Speed, which results in a Energy scalar.
     * @param v Momentum; scalar
     * @return Energy; scalar as a multiplication of Momentum and Speed
     */
    public final Energy times(final Speed v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the multiplication of Momentum and Acceleration, which results in a Power scalar.
     * @param v Momentum; scalar
     * @return Power; scalar as a multiplication of Momentum and Acceleration
     */
    public final Power times(final Acceleration v)
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
