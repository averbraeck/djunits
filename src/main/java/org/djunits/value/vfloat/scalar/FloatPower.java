package org.djunits.value.vfloat.scalar;

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
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatPower FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatPower extends AbstractFloatScalarRel<PowerUnit, FloatPower>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatPower ZERO = new FloatPower(0.0f, PowerUnit.SI);

    /** Constant with value one. */
    public static final FloatPower ONE = new FloatPower(1.0f, PowerUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatPower NaN = new FloatPower(Float.NaN, PowerUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatPower POSITIVE_INFINITY = new FloatPower(Float.POSITIVE_INFINITY, PowerUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatPower NEGATIVE_INFINITY = new FloatPower(Float.NEGATIVE_INFINITY, PowerUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatPower POS_MAXVALUE = new FloatPower(Float.MAX_VALUE, PowerUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatPower NEG_MAXVALUE = new FloatPower(-Float.MAX_VALUE, PowerUnit.SI);

    /**
     * Construct FloatPower scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatPower(final float value, final PowerUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatPower scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatPower(final FloatPower value)
    {
        super(value);
    }

    /**
     * Construct FloatPower scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatPower(final double value, final PowerUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatPower instantiateRel(final float value, final PowerUnit unit)
    {
        return new FloatPower(value, unit);
    }

    /**
     * Construct FloatPower scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatPower instantiateSI(final float value)
    {
        return new FloatPower(value, PowerUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatPower interpolate(final FloatPower zero, final FloatPower one, final float ratio)
    {
        return new FloatPower(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatPower max(final FloatPower r1, final FloatPower r2)
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
    public static FloatPower max(final FloatPower r1, final FloatPower r2, final FloatPower... rn)
    {
        FloatPower maxr = r1.gt(r2) ? r1 : r2;
        for (FloatPower r : rn)
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
    public static FloatPower min(final FloatPower r1, final FloatPower r2)
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
    public static FloatPower min(final FloatPower r1, final FloatPower r2, final FloatPower... rn)
    {
        FloatPower minr = r1.lt(r2) ? r1 : r2;
        for (FloatPower r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatPower representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatPower
     * @return FloatPower; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatPower valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatPower: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatPower: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            PowerUnit unit = PowerUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatPower(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatPower from " + text);
    }

    /**
     * Returns a FloatPower based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatPower; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatPower of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatPower: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatPower: empty unitString");
        PowerUnit unit = PowerUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatPower(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatPower with unit " + unitString);
    }

    /**
     * Calculate the division of FloatPower and FloatPower, which results in a FloatDimensionless scalar.
     * @param v FloatPower; scalar
     * @return FloatDimensionless; scalar as a division of FloatPower and FloatPower
     */
    public final FloatDimensionless divide(final FloatPower v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatPower and FloatDuration, which results in a FloatEnergy scalar.
     * @param v FloatPower; scalar
     * @return FloatEnergy; scalar as a multiplication of FloatPower and FloatDuration
     */
    public final FloatEnergy times(final FloatDuration v)
    {
        return new FloatEnergy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of FloatPower and FloatFrequency, which results in a FloatEnergy scalar.
     * @param v FloatPower; scalar
     * @return FloatEnergy; scalar as a division of FloatPower and FloatFrequency
     */
    public final FloatEnergy divide(final FloatFrequency v)
    {
        return new FloatEnergy(this.si / v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of FloatPower and FloatEnergy, which results in a FloatFrequency scalar.
     * @param v FloatPower; scalar
     * @return FloatFrequency; scalar as a division of FloatPower and FloatEnergy
     */
    public final FloatFrequency divide(final FloatEnergy v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of FloatPower and FloatSpeed, which results in a FloatForce scalar.
     * @param v FloatPower; scalar
     * @return FloatForce; scalar as a division of FloatPower and FloatSpeed
     */
    public final FloatForce divide(final FloatSpeed v)
    {
        return new FloatForce(this.si / v.si, ForceUnit.SI);
    }

    /**
     * Calculate the division of FloatPower and FloatForce, which results in a FloatSpeed scalar.
     * @param v FloatPower; scalar
     * @return FloatSpeed; scalar as a division of FloatPower and FloatForce
     */
    public final FloatSpeed divide(final FloatForce v)
    {
        return new FloatSpeed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of FloatPower and FloatElectricalPotential, which results in a FloatElectricalCurrent scalar.
     * @param v FloatPower; scalar
     * @return FloatElectricalCurrent; scalar as a division of FloatPower and FloatElectricalPotential
     */
    public final FloatElectricalCurrent divide(final FloatElectricalPotential v)
    {
        return new FloatElectricalCurrent(this.si / v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the division of FloatPower and FloatElectricalCurrent, which results in a FloatElectricalPotential scalar.
     * @param v FloatPower; scalar
     * @return FloatElectricalPotential; scalar as a division of FloatPower and FloatElectricalCurrent
     */
    public final FloatElectricalPotential divide(final FloatElectricalCurrent v)
    {
        return new FloatElectricalPotential(this.si / v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the division of FloatPower and FloatAcceleration, which results in a FloatMomentum scalar.
     * @param v FloatPower; scalar
     * @return FloatMomentum; scalar as a division of FloatPower and FloatAcceleration
     */
    public final FloatMomentum divide(final FloatAcceleration v)
    {
        return new FloatMomentum(this.si / v.si, MomentumUnit.SI);
    }

    /**
     * Calculate the division of FloatPower and FloatMomentum, which results in a FloatAcceleration scalar.
     * @param v FloatPower; scalar
     * @return FloatAcceleration; scalar as a division of FloatPower and FloatMomentum
     */
    public final FloatAcceleration divide(final FloatMomentum v)
    {
        return new FloatAcceleration(this.si / v.si, AccelerationUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
