package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatElectricalCurrent FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalCurrent extends AbstractFloatScalarRel<ElectricalCurrentUnit, FloatElectricalCurrent>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatElectricalCurrent ZERO = new FloatElectricalCurrent(0.0f, ElectricalCurrentUnit.SI);

    /** Constant with value one. */
    public static final FloatElectricalCurrent ONE = new FloatElectricalCurrent(1.0f, ElectricalCurrentUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatElectricalCurrent NaN = new FloatElectricalCurrent(Float.NaN, ElectricalCurrentUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatElectricalCurrent POSITIVE_INFINITY =
            new FloatElectricalCurrent(Float.POSITIVE_INFINITY, ElectricalCurrentUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatElectricalCurrent NEGATIVE_INFINITY =
            new FloatElectricalCurrent(Float.NEGATIVE_INFINITY, ElectricalCurrentUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatElectricalCurrent POS_MAXVALUE =
            new FloatElectricalCurrent(Float.MAX_VALUE, ElectricalCurrentUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatElectricalCurrent NEG_MAXVALUE =
            new FloatElectricalCurrent(-Float.MAX_VALUE, ElectricalCurrentUnit.SI);

    /**
     * Construct FloatElectricalCurrent scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatElectricalCurrent(final float value, final ElectricalCurrentUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatElectricalCurrent scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatElectricalCurrent(final FloatElectricalCurrent value)
    {
        super(value);
    }

    /**
     * Construct FloatElectricalCurrent scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatElectricalCurrent(final double value, final ElectricalCurrentUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatElectricalCurrent instantiateRel(final float value, final ElectricalCurrentUnit unit)
    {
        return new FloatElectricalCurrent(value, unit);
    }

    /**
     * Construct FloatElectricalCurrent scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatElectricalCurrent instantiateSI(final float value)
    {
        return new FloatElectricalCurrent(value, ElectricalCurrentUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatElectricalCurrent interpolate(final FloatElectricalCurrent zero, final FloatElectricalCurrent one,
            final float ratio)
    {
        return new FloatElectricalCurrent(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatElectricalCurrent max(final FloatElectricalCurrent r1, final FloatElectricalCurrent r2)
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
    public static FloatElectricalCurrent max(final FloatElectricalCurrent r1, final FloatElectricalCurrent r2,
            final FloatElectricalCurrent... rn)
    {
        FloatElectricalCurrent maxr = r1.gt(r2) ? r1 : r2;
        for (FloatElectricalCurrent r : rn)
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
    public static FloatElectricalCurrent min(final FloatElectricalCurrent r1, final FloatElectricalCurrent r2)
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
    public static FloatElectricalCurrent min(final FloatElectricalCurrent r1, final FloatElectricalCurrent r2,
            final FloatElectricalCurrent... rn)
    {
        FloatElectricalCurrent minr = r1.lt(r2) ? r1 : r2;
        for (FloatElectricalCurrent r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatElectricalCurrent representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatElectricalCurrent
     * @return FloatElectricalCurrent; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatElectricalCurrent valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatElectricalCurrent: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalCurrent: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            ElectricalCurrentUnit unit = ElectricalCurrentUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatElectricalCurrent(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatElectricalCurrent from " + text);
    }

    /**
     * Returns a FloatElectricalCurrent based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatElectricalCurrent; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatElectricalCurrent of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatElectricalCurrent: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalCurrent: empty unitString");
        ElectricalCurrentUnit unit = ElectricalCurrentUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatElectricalCurrent(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatElectricalCurrent with unit " + unitString);
    }

    /**
     * Calculate the division of FloatElectricalCurrent and FloatElectricalCurrent, which results in a FloatDimensionless
     * scalar.
     * @param v FloatElectricalCurrent; scalar
     * @return FloatDimensionless; scalar as a division of FloatElectricalCurrent and FloatElectricalCurrent
     */
    public final FloatDimensionless divide(final FloatElectricalCurrent v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalCurrent and FloatElectricalPotential, which results in a FloatPower
     * scalar.
     * @param v FloatElectricalCurrent; scalar
     * @return FloatPower; scalar as a multiplication of FloatElectricalCurrent and FloatElectricalPotential
     */
    public final FloatPower times(final FloatElectricalPotential v)
    {
        return new FloatPower(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalCurrent and FloatDuration, which results in a FloatElectricalCharge
     * scalar.
     * @param v FloatElectricalCurrent; scalar
     * @return FloatElectricalCharge; scalar as a multiplication of FloatElectricalCurrent and FloatDuration
     */
    public final FloatElectricalCharge times(final FloatDuration v)
    {
        return new FloatElectricalCharge(this.si * v.si, ElectricalChargeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalCurrent and FloatElectricalResistance, which results in a
     * FloatElectricalPotential scalar.
     * @param v FloatElectricalCurrent; scalar
     * @return FloatElectricalPotential; scalar as a multiplication of FloatElectricalCurrent and FloatElectricalResistance
     */
    public final FloatElectricalPotential times(final FloatElectricalResistance v)
    {
        return new FloatElectricalPotential(this.si * v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the division of FloatElectricalCurrent and FloatElectricalPotential, which results in a
     * FloatElectricalConductance scalar.
     * @param v FloatElectricalCurrent; scalar
     * @return FloatElectricalConductance; scalar as a division of FloatElectricalCurrent and FloatElectricalPotential
     */
    public final FloatElectricalConductance divide(final FloatElectricalPotential v)
    {
        return new FloatElectricalConductance(this.si / v.si, ElectricalConductanceUnit.SI);
    }

    /**
     * Calculate the division of FloatElectricalCurrent and FloatElectricalConductance, which results in a
     * FloatElectricalPotential scalar.
     * @param v FloatElectricalCurrent; scalar
     * @return FloatElectricalPotential; scalar as a division of FloatElectricalCurrent and FloatElectricalConductance
     */
    public final FloatElectricalPotential divide(final FloatElectricalConductance v)
    {
        return new FloatElectricalPotential(this.si / v.si, ElectricalPotentialUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
