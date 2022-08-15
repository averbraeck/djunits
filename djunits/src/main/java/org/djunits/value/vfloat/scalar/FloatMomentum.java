package org.djunits.value.vfloat.scalar;

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
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatMomentum FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatMomentum extends AbstractFloatScalarRel<MomentumUnit, FloatMomentum>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatMomentum ZERO = new FloatMomentum(0.0f, MomentumUnit.SI);

    /** Constant with value one. */
    public static final FloatMomentum ONE = new FloatMomentum(1.0f, MomentumUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatMomentum NaN = new FloatMomentum(Float.NaN, MomentumUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatMomentum POSITIVE_INFINITY = new FloatMomentum(Float.POSITIVE_INFINITY, MomentumUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatMomentum NEGATIVE_INFINITY = new FloatMomentum(Float.NEGATIVE_INFINITY, MomentumUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatMomentum POS_MAXVALUE = new FloatMomentum(Float.MAX_VALUE, MomentumUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatMomentum NEG_MAXVALUE = new FloatMomentum(-Float.MAX_VALUE, MomentumUnit.SI);

    /**
     * Construct FloatMomentum scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatMomentum(final float value, final MomentumUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatMomentum scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatMomentum(final FloatMomentum value)
    {
        super(value);
    }

    /**
     * Construct FloatMomentum scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatMomentum(final double value, final MomentumUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMomentum instantiateRel(final float value, final MomentumUnit unit)
    {
        return new FloatMomentum(value, unit);
    }

    /**
     * Construct FloatMomentum scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatMomentum instantiateSI(final float value)
    {
        return new FloatMomentum(value, MomentumUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatMomentum interpolate(final FloatMomentum zero, final FloatMomentum one, final float ratio)
    {
        return new FloatMomentum(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatMomentum max(final FloatMomentum r1, final FloatMomentum r2)
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
    public static FloatMomentum max(final FloatMomentum r1, final FloatMomentum r2, final FloatMomentum... rn)
    {
        FloatMomentum maxr = r1.gt(r2) ? r1 : r2;
        for (FloatMomentum r : rn)
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
    public static FloatMomentum min(final FloatMomentum r1, final FloatMomentum r2)
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
    public static FloatMomentum min(final FloatMomentum r1, final FloatMomentum r2, final FloatMomentum... rn)
    {
        FloatMomentum minr = r1.lt(r2) ? r1 : r2;
        for (FloatMomentum r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatMomentum representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatMomentum
     * @return FloatMomentum; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatMomentum valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatMomentum: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatMomentum: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            MomentumUnit unit = MomentumUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatMomentum(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatMomentum from " + text);
    }

    /**
     * Returns a FloatMomentum based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatMomentum; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatMomentum of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatMomentum: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatMomentum: empty unitString");
        MomentumUnit unit = MomentumUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatMomentum(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatMomentum with unit " + unitString);
    }

    /**
     * Calculate the division of FloatMomentum and FloatMomentum, which results in a FloatDimensionless scalar.
     * @param v FloatMomentum; scalar
     * @return FloatDimensionless; scalar as a division of FloatMomentum and FloatMomentum
     */
    public final FloatDimensionless divide(final FloatMomentum v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of FloatMomentum and FloatSpeed, which results in a FloatMass scalar.
     * @param v FloatMomentum; scalar
     * @return FloatMass; scalar as a division of FloatMomentum and FloatSpeed
     */
    public final FloatMass divide(final FloatSpeed v)
    {
        return new FloatMass(this.si / v.si, MassUnit.SI);
    }

    /**
     * Calculate the division of FloatMomentum and FloatMass, which results in a FloatSpeed scalar.
     * @param v FloatMomentum; scalar
     * @return FloatSpeed; scalar as a division of FloatMomentum and FloatMass
     */
    public final FloatSpeed divide(final FloatMass v)
    {
        return new FloatSpeed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of FloatMomentum and FloatLength, which results in a FloatFlowMass scalar.
     * @param v FloatMomentum; scalar
     * @return FloatFlowMass; scalar as a division of FloatMomentum and FloatLength
     */
    public final FloatFlowMass divide(final FloatLength v)
    {
        return new FloatFlowMass(this.si / v.si, FlowMassUnit.SI);
    }

    /**
     * Calculate the division of FloatMomentum and FloatFlowMass, which results in a FloatLength scalar.
     * @param v FloatMomentum; scalar
     * @return FloatLength; scalar as a division of FloatMomentum and FloatFlowMass
     */
    public final FloatLength divide(final FloatFlowMass v)
    {
        return new FloatLength(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatMomentum and FloatSpeed, which results in a FloatEnergy scalar.
     * @param v FloatMomentum; scalar
     * @return FloatEnergy; scalar as a multiplication of FloatMomentum and FloatSpeed
     */
    public final FloatEnergy times(final FloatSpeed v)
    {
        return new FloatEnergy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatMomentum and FloatAcceleration, which results in a FloatPower scalar.
     * @param v FloatMomentum; scalar
     * @return FloatPower; scalar as a multiplication of FloatMomentum and FloatAcceleration
     */
    public final FloatPower times(final FloatAcceleration v)
    {
        return new FloatPower(this.si * v.si, PowerUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
