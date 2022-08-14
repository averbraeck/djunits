package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatArea FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatArea extends AbstractFloatScalarRel<AreaUnit, FloatArea>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatArea ZERO = new FloatArea(0.0f, AreaUnit.SI);

    /** Constant with value one. */
    public static final FloatArea ONE = new FloatArea(1.0f, AreaUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatArea NaN = new FloatArea(Float.NaN, AreaUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatArea POSITIVE_INFINITY = new FloatArea(Float.POSITIVE_INFINITY, AreaUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatArea NEGATIVE_INFINITY = new FloatArea(Float.NEGATIVE_INFINITY, AreaUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatArea POS_MAXVALUE = new FloatArea(Float.MAX_VALUE, AreaUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatArea NEG_MAXVALUE = new FloatArea(-Float.MAX_VALUE, AreaUnit.SI);

    /**
     * Construct FloatArea scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatArea(final float value, final AreaUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatArea scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatArea(final FloatArea value)
    {
        super(value);
    }

    /**
     * Construct FloatArea scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatArea(final double value, final AreaUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatArea instantiateRel(final float value, final AreaUnit unit)
    {
        return new FloatArea(value, unit);
    }

    /**
     * Construct FloatArea scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatArea instantiateSI(final float value)
    {
        return new FloatArea(value, AreaUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatArea interpolate(final FloatArea zero, final FloatArea one, final float ratio)
    {
        return new FloatArea(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatArea max(final FloatArea r1, final FloatArea r2)
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
    public static FloatArea max(final FloatArea r1, final FloatArea r2, final FloatArea... rn)
    {
        FloatArea maxr = r1.gt(r2) ? r1 : r2;
        for (FloatArea r : rn)
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
    public static FloatArea min(final FloatArea r1, final FloatArea r2)
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
    public static FloatArea min(final FloatArea r1, final FloatArea r2, final FloatArea... rn)
    {
        FloatArea minr = r1.lt(r2) ? r1 : r2;
        for (FloatArea r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatArea representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatArea
     * @return FloatArea; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatArea valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatArea: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatArea: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AreaUnit unit = AreaUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatArea(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatArea from " + text);
    }

    /**
     * Returns a FloatArea based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatArea; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatArea of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatArea: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatArea: empty unitString");
        AreaUnit unit = AreaUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatArea(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatArea with unit " + unitString);
    }

    /**
     * Calculate the division of FloatArea and FloatArea, which results in a FloatDimensionless scalar.
     * @param v FloatArea; scalar
     * @return FloatDimensionless; scalar as a division of FloatArea and FloatArea
     */
    public final FloatDimensionless divide(final FloatArea v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatArea and FloatLength, which results in a FloatVolume scalar.
     * @param v FloatArea; scalar
     * @return FloatVolume; scalar as a multiplication of FloatArea and FloatLength
     */
    public final FloatVolume times(final FloatLength v)
    {
        return new FloatVolume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of FloatArea and FloatLinearDensity, which results in a FloatVolume scalar.
     * @param v FloatArea; scalar
     * @return FloatVolume; scalar as a division of FloatArea and FloatLinearDensity
     */
    public final FloatVolume divide(final FloatLinearDensity v)
    {
        return new FloatVolume(this.si / v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of FloatArea and FloatVolume, which results in a FloatLinearDensity scalar.
     * @param v FloatArea; scalar
     * @return FloatLinearDensity; scalar as a division of FloatArea and FloatVolume
     */
    public final FloatLinearDensity divide(final FloatVolume v)
    {
        return new FloatLinearDensity(this.si / v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the division of FloatArea and FloatLength, which results in a FloatLength scalar.
     * @param v FloatArea; scalar
     * @return FloatLength; scalar as a division of FloatArea and FloatLength
     */
    public final FloatLength divide(final FloatLength v)
    {
        return new FloatLength(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatArea and FloatLinearDensity, which results in a FloatLength scalar.
     * @param v FloatArea; scalar
     * @return FloatLength; scalar as a multiplication of FloatArea and FloatLinearDensity
     */
    public final FloatLength times(final FloatLinearDensity v)
    {
        return new FloatLength(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatArea and FloatSpeed, which results in a FloatFlowVolume scalar.
     * @param v FloatArea; scalar
     * @return FloatFlowVolume; scalar as a multiplication of FloatArea and FloatSpeed
     */
    public final FloatFlowVolume times(final FloatSpeed v)
    {
        return new FloatFlowVolume(this.si * v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatArea and FloatPressure, which results in a FloatForce scalar.
     * @param v FloatArea; scalar
     * @return FloatForce; scalar as a multiplication of FloatArea and FloatPressure
     */
    public final FloatForce times(final FloatPressure v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatArea and FloatIlluminance, which results in a FloatLuminousFlux scalar.
     * @param v FloatArea; scalar
     * @return FloatLuminousFlux; scalar as a multiplication of FloatArea and FloatIlluminance
     */
    public final FloatLuminousFlux times(final FloatIlluminance v)
    {
        return new FloatLuminousFlux(this.si * v.si, LuminousFluxUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
