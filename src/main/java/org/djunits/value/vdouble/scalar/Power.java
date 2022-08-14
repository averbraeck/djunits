package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.MomentumUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Power DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Power extends AbstractDoubleScalarRel<PowerUnit, Power>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Power ZERO = new Power(0.0, PowerUnit.SI);

    /** Constant with value one. */
    public static final Power ONE = new Power(1.0, PowerUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Power NaN = new Power(Double.NaN, PowerUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Power POSITIVE_INFINITY = new Power(Double.POSITIVE_INFINITY, PowerUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Power NEGATIVE_INFINITY = new Power(Double.NEGATIVE_INFINITY, PowerUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Power POS_MAXVALUE = new Power(Double.MAX_VALUE, PowerUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Power NEG_MAXVALUE = new Power(-Double.MAX_VALUE, PowerUnit.SI);

    /**
     * Construct Power scalar.
     * @param value double; the double value
     * @param unit PowerUnit; unit for the double value
     */
    public Power(final double value, final PowerUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Power scalar.
     * @param value Power; Scalar from which to construct this instance
     */
    public Power(final Power value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Power instantiateRel(final double value, final PowerUnit unit)
    {
        return new Power(value, unit);
    }

    /**
     * Construct Power scalar.
     * @param value double; the double value in SI units
     * @return Power; the new scalar with the SI value
     */
    public static final Power instantiateSI(final double value)
    {
        return new Power(value, PowerUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Power; the low value
     * @param one Power; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Power; a Scalar at the ratio between
     */
    public static Power interpolate(final Power zero, final Power one, final double ratio)
    {
        return new Power(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Power; the first scalar
     * @param r2 Power; the second scalar
     * @return Power; the maximum value of two relative scalars
     */
    public static Power max(final Power r1, final Power r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Power; the first scalar
     * @param r2 Power; the second scalar
     * @param rn Power...; the other scalars
     * @return Power; the maximum value of more than two relative scalars
     */
    public static Power max(final Power r1, final Power r2, final Power... rn)
    {
        Power maxr = r1.gt(r2) ? r1 : r2;
        for (Power r : rn)
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
     * @param r1 Power; the first scalar
     * @param r2 Power; the second scalar
     * @return Power; the minimum value of two relative scalars
     */
    public static Power min(final Power r1, final Power r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Power; the first scalar
     * @param r2 Power; the second scalar
     * @param rn Power...; the other scalars
     * @return Power; the minimum value of more than two relative scalars
     */
    public static Power min(final Power r1, final Power r2, final Power... rn)
    {
        Power minr = r1.lt(r2) ? r1 : r2;
        for (Power r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Power representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Power
     * @return Power; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Power valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Power: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Power: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            PowerUnit unit = PowerUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Power(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Power from " + text);
    }

    /**
     * Returns a Power based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Power; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Power of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Power: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Power: empty unitString");
        PowerUnit unit = PowerUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Power(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Power with unit " + unitString);
    }

    /**
     * Calculate the division of Power and Power, which results in a Dimensionless scalar.
     * @param v Power; scalar
     * @return Dimensionless; scalar as a division of Power and Power
     */
    public final Dimensionless divide(final Power v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Power and Duration, which results in a Energy scalar.
     * @param v Power; scalar
     * @return Energy; scalar as a multiplication of Power and Duration
     */
    public final Energy times(final Duration v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of Power and Frequency, which results in a Energy scalar.
     * @param v Power; scalar
     * @return Energy; scalar as a division of Power and Frequency
     */
    public final Energy divide(final Frequency v)
    {
        return new Energy(this.si / v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of Power and Energy, which results in a Frequency scalar.
     * @param v Power; scalar
     * @return Frequency; scalar as a division of Power and Energy
     */
    public final Frequency divide(final Energy v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of Power and Speed, which results in a Force scalar.
     * @param v Power; scalar
     * @return Force; scalar as a division of Power and Speed
     */
    public final Force divide(final Speed v)
    {
        return new Force(this.si / v.si, ForceUnit.SI);
    }

    /**
     * Calculate the division of Power and Force, which results in a Speed scalar.
     * @param v Power; scalar
     * @return Speed; scalar as a division of Power and Force
     */
    public final Speed divide(final Force v)
    {
        return new Speed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of Power and ElectricalPotential, which results in a ElectricalCurrent scalar.
     * @param v Power; scalar
     * @return ElectricalCurrent; scalar as a division of Power and ElectricalPotential
     */
    public final ElectricalCurrent divide(final ElectricalPotential v)
    {
        return new ElectricalCurrent(this.si / v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the division of Power and ElectricalCurrent, which results in a ElectricalPotential scalar.
     * @param v Power; scalar
     * @return ElectricalPotential; scalar as a division of Power and ElectricalCurrent
     */
    public final ElectricalPotential divide(final ElectricalCurrent v)
    {
        return new ElectricalPotential(this.si / v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the division of Power and Acceleration, which results in a Momentum scalar.
     * @param v Power; scalar
     * @return Momentum; scalar as a division of Power and Acceleration
     */
    public final Momentum divide(final Acceleration v)
    {
        return new Momentum(this.si / v.si, MomentumUnit.SI);
    }

    /**
     * Calculate the division of Power and Momentum, which results in a Acceleration scalar.
     * @param v Power; scalar
     * @return Acceleration; scalar as a division of Power and Momentum
     */
    public final Acceleration divide(final Momentum v)
    {
        return new Acceleration(this.si / v.si, AccelerationUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
