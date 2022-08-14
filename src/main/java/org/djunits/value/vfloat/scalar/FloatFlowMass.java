package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.MomentumUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatFlowMass FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatFlowMass extends AbstractFloatScalarRel<FlowMassUnit, FloatFlowMass>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatFlowMass ZERO = new FloatFlowMass(0.0f, FlowMassUnit.SI);

    /** Constant with value one. */
    public static final FloatFlowMass ONE = new FloatFlowMass(1.0f, FlowMassUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatFlowMass NaN = new FloatFlowMass(Float.NaN, FlowMassUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatFlowMass POSITIVE_INFINITY = new FloatFlowMass(Float.POSITIVE_INFINITY, FlowMassUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatFlowMass NEGATIVE_INFINITY = new FloatFlowMass(Float.NEGATIVE_INFINITY, FlowMassUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatFlowMass POS_MAXVALUE = new FloatFlowMass(Float.MAX_VALUE, FlowMassUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatFlowMass NEG_MAXVALUE = new FloatFlowMass(-Float.MAX_VALUE, FlowMassUnit.SI);

    /**
     * Construct FloatFlowMass scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatFlowMass(final float value, final FlowMassUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatFlowMass scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatFlowMass(final FloatFlowMass value)
    {
        super(value);
    }

    /**
     * Construct FloatFlowMass scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatFlowMass(final double value, final FlowMassUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatFlowMass instantiateRel(final float value, final FlowMassUnit unit)
    {
        return new FloatFlowMass(value, unit);
    }

    /**
     * Construct FloatFlowMass scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatFlowMass instantiateSI(final float value)
    {
        return new FloatFlowMass(value, FlowMassUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatFlowMass interpolate(final FloatFlowMass zero, final FloatFlowMass one, final float ratio)
    {
        return new FloatFlowMass(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatFlowMass max(final FloatFlowMass r1, final FloatFlowMass r2)
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
    public static FloatFlowMass max(final FloatFlowMass r1, final FloatFlowMass r2, final FloatFlowMass... rn)
    {
        FloatFlowMass maxr = r1.gt(r2) ? r1 : r2;
        for (FloatFlowMass r : rn)
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
    public static FloatFlowMass min(final FloatFlowMass r1, final FloatFlowMass r2)
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
    public static FloatFlowMass min(final FloatFlowMass r1, final FloatFlowMass r2, final FloatFlowMass... rn)
    {
        FloatFlowMass minr = r1.lt(r2) ? r1 : r2;
        for (FloatFlowMass r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatFlowMass representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatFlowMass
     * @return FloatFlowMass; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatFlowMass valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatFlowMass: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatFlowMass: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            FlowMassUnit unit = FlowMassUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatFlowMass(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatFlowMass from " + text);
    }

    /**
     * Returns a FloatFlowMass based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatFlowMass; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatFlowMass of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatFlowMass: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatFlowMass: empty unitString");
        FlowMassUnit unit = FlowMassUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatFlowMass(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatFlowMass with unit " + unitString);
    }

    /**
     * Calculate the division of FloatFlowMass and FloatFlowMass, which results in a FloatDimensionless scalar.
     * @param v FloatFlowMass; scalar
     * @return FloatDimensionless; scalar as a division of FloatFlowMass and FloatFlowMass
     */
    public final FloatDimensionless divide(final FloatFlowMass v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFlowMass and FloatDuration, which results in a FloatMass scalar.
     * @param v FloatFlowMass; scalar
     * @return FloatMass; scalar as a multiplication of FloatFlowMass and FloatDuration
     */
    public final FloatMass times(final FloatDuration v)
    {
        return new FloatMass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowMass and FloatFrequency, which results in a FloatMass scalar.
     * @param v FloatFlowMass; scalar
     * @return FloatMass; scalar as a division of FloatFlowMass and FloatFrequency
     */
    public final FloatMass divide(final FloatFrequency v)
    {
        return new FloatMass(this.si / v.si, MassUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowMass and FloatMass, which results in a FloatFrequency scalar.
     * @param v FloatFlowMass; scalar
     * @return FloatFrequency; scalar as a division of FloatFlowMass and FloatMass
     */
    public final FloatFrequency divide(final FloatMass v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFlowMass and FloatSpeed, which results in a FloatForce scalar.
     * @param v FloatFlowMass; scalar
     * @return FloatForce; scalar as a multiplication of FloatFlowMass and FloatSpeed
     */
    public final FloatForce times(final FloatSpeed v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowMass and FloatFlowVolume, which results in a FloatDensity scalar.
     * @param v FloatFlowMass; scalar
     * @return FloatDensity; scalar as a division of FloatFlowMass and FloatFlowVolume
     */
    public final FloatDensity divide(final FloatFlowVolume v)
    {
        return new FloatDensity(this.si / v.si, DensityUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowMass and FloatDensity, which results in a FloatFlowVolume scalar.
     * @param v FloatFlowMass; scalar
     * @return FloatFlowVolume; scalar as a division of FloatFlowMass and FloatDensity
     */
    public final FloatFlowVolume divide(final FloatDensity v)
    {
        return new FloatFlowVolume(this.si / v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFlowMass and FloatLength, which results in a FloatMomentum scalar.
     * @param v FloatFlowMass; scalar
     * @return FloatMomentum; scalar as a multiplication of FloatFlowMass and FloatLength
     */
    public final FloatMomentum times(final FloatLength v)
    {
        return new FloatMomentum(this.si * v.si, MomentumUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
