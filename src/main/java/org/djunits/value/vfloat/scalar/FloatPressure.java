package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.PressureUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatPressure FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatPressure extends AbstractFloatScalarRel<PressureUnit, FloatPressure>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatPressure ZERO = new FloatPressure(0.0f, PressureUnit.SI);

    /** Constant with value one. */
    public static final FloatPressure ONE = new FloatPressure(1.0f, PressureUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatPressure NaN = new FloatPressure(Float.NaN, PressureUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatPressure POSITIVE_INFINITY = new FloatPressure(Float.POSITIVE_INFINITY, PressureUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatPressure NEGATIVE_INFINITY = new FloatPressure(Float.NEGATIVE_INFINITY, PressureUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatPressure POS_MAXVALUE = new FloatPressure(Float.MAX_VALUE, PressureUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatPressure NEG_MAXVALUE = new FloatPressure(-Float.MAX_VALUE, PressureUnit.SI);

    /**
     * Construct FloatPressure scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatPressure(final float value, final PressureUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatPressure scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatPressure(final FloatPressure value)
    {
        super(value);
    }

    /**
     * Construct FloatPressure scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatPressure(final double value, final PressureUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatPressure instantiateRel(final float value, final PressureUnit unit)
    {
        return new FloatPressure(value, unit);
    }

    /**
     * Construct FloatPressure scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatPressure instantiateSI(final float value)
    {
        return new FloatPressure(value, PressureUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatPressure interpolate(final FloatPressure zero, final FloatPressure one, final float ratio)
    {
        return new FloatPressure(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatPressure max(final FloatPressure r1, final FloatPressure r2)
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
    public static FloatPressure max(final FloatPressure r1, final FloatPressure r2, final FloatPressure... rn)
    {
        FloatPressure maxr = r1.gt(r2) ? r1 : r2;
        for (FloatPressure r : rn)
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
    public static FloatPressure min(final FloatPressure r1, final FloatPressure r2)
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
    public static FloatPressure min(final FloatPressure r1, final FloatPressure r2, final FloatPressure... rn)
    {
        FloatPressure minr = r1.lt(r2) ? r1 : r2;
        for (FloatPressure r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatPressure representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatPressure
     * @return FloatPressure; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatPressure valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatPressure: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatPressure: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            PressureUnit unit = PressureUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatPressure(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatPressure from " + text);
    }

    /**
     * Returns a FloatPressure based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatPressure; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatPressure of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatPressure: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatPressure: empty unitString");
        PressureUnit unit = PressureUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatPressure(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatPressure with unit " + unitString);
    }

    /**
     * Calculate the division of FloatPressure and FloatPressure, which results in a FloatDimensionless scalar.
     * @param v FloatPressure; scalar
     * @return FloatDimensionless; scalar as a division of FloatPressure and FloatPressure
     */
    public final FloatDimensionless divide(final FloatPressure v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatPressure and FloatArea, which results in a FloatForce scalar.
     * @param v FloatPressure; scalar
     * @return FloatForce; scalar as a multiplication of FloatPressure and FloatArea
     */
    public final FloatForce times(final FloatArea v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatPressure and FloatVolume, which results in a FloatEnergy scalar.
     * @param v FloatPressure; scalar
     * @return FloatEnergy; scalar as a multiplication of FloatPressure and FloatVolume
     */
    public final FloatEnergy times(final FloatVolume v)
    {
        return new FloatEnergy(this.si * v.si, EnergyUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
