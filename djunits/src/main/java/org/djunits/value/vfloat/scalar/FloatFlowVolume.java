package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatFlowVolume FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatFlowVolume extends AbstractFloatScalarRel<FlowVolumeUnit, FloatFlowVolume>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatFlowVolume ZERO = new FloatFlowVolume(0.0f, FlowVolumeUnit.SI);

    /** Constant with value one. */
    public static final FloatFlowVolume ONE = new FloatFlowVolume(1.0f, FlowVolumeUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatFlowVolume NaN = new FloatFlowVolume(Float.NaN, FlowVolumeUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatFlowVolume POSITIVE_INFINITY = new FloatFlowVolume(Float.POSITIVE_INFINITY, FlowVolumeUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatFlowVolume NEGATIVE_INFINITY = new FloatFlowVolume(Float.NEGATIVE_INFINITY, FlowVolumeUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatFlowVolume POS_MAXVALUE = new FloatFlowVolume(Float.MAX_VALUE, FlowVolumeUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatFlowVolume NEG_MAXVALUE = new FloatFlowVolume(-Float.MAX_VALUE, FlowVolumeUnit.SI);

    /**
     * Construct FloatFlowVolume scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatFlowVolume(final float value, final FlowVolumeUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatFlowVolume scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatFlowVolume(final FloatFlowVolume value)
    {
        super(value);
    }

    /**
     * Construct FloatFlowVolume scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatFlowVolume(final double value, final FlowVolumeUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatFlowVolume instantiateRel(final float value, final FlowVolumeUnit unit)
    {
        return new FloatFlowVolume(value, unit);
    }

    /**
     * Construct FloatFlowVolume scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatFlowVolume instantiateSI(final float value)
    {
        return new FloatFlowVolume(value, FlowVolumeUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatFlowVolume interpolate(final FloatFlowVolume zero, final FloatFlowVolume one, final float ratio)
    {
        return new FloatFlowVolume(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatFlowVolume max(final FloatFlowVolume r1, final FloatFlowVolume r2)
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
    public static FloatFlowVolume max(final FloatFlowVolume r1, final FloatFlowVolume r2, final FloatFlowVolume... rn)
    {
        FloatFlowVolume maxr = r1.gt(r2) ? r1 : r2;
        for (FloatFlowVolume r : rn)
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
    public static FloatFlowVolume min(final FloatFlowVolume r1, final FloatFlowVolume r2)
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
    public static FloatFlowVolume min(final FloatFlowVolume r1, final FloatFlowVolume r2, final FloatFlowVolume... rn)
    {
        FloatFlowVolume minr = r1.lt(r2) ? r1 : r2;
        for (FloatFlowVolume r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatFlowVolume representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatFlowVolume
     * @return FloatFlowVolume; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatFlowVolume valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatFlowVolume: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatFlowVolume: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            FlowVolumeUnit unit = FlowVolumeUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatFlowVolume(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatFlowVolume from " + text);
    }

    /**
     * Returns a FloatFlowVolume based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatFlowVolume; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatFlowVolume of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatFlowVolume: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatFlowVolume: empty unitString");
        FlowVolumeUnit unit = FlowVolumeUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatFlowVolume(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatFlowVolume with unit " + unitString);
    }

    /**
     * Calculate the division of FloatFlowVolume and FloatFlowVolume, which results in a FloatDimensionless scalar.
     * @param v FloatFlowVolume; scalar
     * @return FloatDimensionless; scalar as a division of FloatFlowVolume and FloatFlowVolume
     */
    public final FloatDimensionless divide(final FloatFlowVolume v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFlowVolume and FloatDuration, which results in a FloatVolume scalar.
     * @param v FloatFlowVolume; scalar
     * @return FloatVolume; scalar as a multiplication of FloatFlowVolume and FloatDuration
     */
    public final FloatVolume times(final FloatDuration v)
    {
        return new FloatVolume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowVolume and FloatFrequency, which results in a FloatVolume scalar.
     * @param v FloatFlowVolume; scalar
     * @return FloatVolume; scalar as a division of FloatFlowVolume and FloatFrequency
     */
    public final FloatVolume divide(final FloatFrequency v)
    {
        return new FloatVolume(this.si / v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowVolume and FloatVolume, which results in a FloatFrequency scalar.
     * @param v FloatFlowVolume; scalar
     * @return FloatFrequency; scalar as a division of FloatFlowVolume and FloatVolume
     */
    public final FloatFrequency divide(final FloatVolume v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowVolume and FloatArea, which results in a FloatSpeed scalar.
     * @param v FloatFlowVolume; scalar
     * @return FloatSpeed; scalar as a division of FloatFlowVolume and FloatArea
     */
    public final FloatSpeed divide(final FloatArea v)
    {
        return new FloatSpeed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowVolume and FloatSpeed, which results in a FloatArea scalar.
     * @param v FloatFlowVolume; scalar
     * @return FloatArea; scalar as a division of FloatFlowVolume and FloatSpeed
     */
    public final FloatArea divide(final FloatSpeed v)
    {
        return new FloatArea(this.si / v.si, AreaUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFlowVolume and FloatDensity, which results in a FloatFlowMass scalar.
     * @param v FloatFlowVolume; scalar
     * @return FloatFlowMass; scalar as a multiplication of FloatFlowVolume and FloatDensity
     */
    public final FloatFlowMass times(final FloatDensity v)
    {
        return new FloatFlowMass(this.si * v.si, FlowMassUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
