package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.MomentumUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatMass FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatMass extends AbstractFloatScalarRel<MassUnit, FloatMass>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatMass ZERO = new FloatMass(0.0f, MassUnit.SI);

    /** Constant with value one. */
    public static final FloatMass ONE = new FloatMass(1.0f, MassUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatMass NaN = new FloatMass(Float.NaN, MassUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatMass POSITIVE_INFINITY = new FloatMass(Float.POSITIVE_INFINITY, MassUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatMass NEGATIVE_INFINITY = new FloatMass(Float.NEGATIVE_INFINITY, MassUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatMass POS_MAXVALUE = new FloatMass(Float.MAX_VALUE, MassUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatMass NEG_MAXVALUE = new FloatMass(-Float.MAX_VALUE, MassUnit.SI);

    /**
     * Construct FloatMass scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatMass(final float value, final MassUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatMass scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatMass(final FloatMass value)
    {
        super(value);
    }

    /**
     * Construct FloatMass scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatMass(final double value, final MassUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatMass instantiateRel(final float value, final MassUnit unit)
    {
        return new FloatMass(value, unit);
    }

    /**
     * Construct FloatMass scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatMass instantiateSI(final float value)
    {
        return new FloatMass(value, MassUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatMass interpolate(final FloatMass zero, final FloatMass one, final float ratio)
    {
        return new FloatMass(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatMass max(final FloatMass r1, final FloatMass r2)
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
    public static FloatMass max(final FloatMass r1, final FloatMass r2, final FloatMass... rn)
    {
        FloatMass maxr = r1.gt(r2) ? r1 : r2;
        for (FloatMass r : rn)
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
    public static FloatMass min(final FloatMass r1, final FloatMass r2)
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
    public static FloatMass min(final FloatMass r1, final FloatMass r2, final FloatMass... rn)
    {
        FloatMass minr = r1.lt(r2) ? r1 : r2;
        for (FloatMass r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatMass representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatMass
     * @return FloatMass; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatMass valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatMass: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatMass: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            MassUnit unit = MassUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatMass(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatMass from " + text);
    }

    /**
     * Returns a FloatMass based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatMass; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatMass of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatMass: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatMass: empty unitString");
        MassUnit unit = MassUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatMass(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatMass with unit " + unitString);
    }

    /** {@inheritDoc} */
    @Override
    public String toStringSIPrefixed(final int smallestPower, final int biggestPower)
    {
        if (!Float.isFinite(this.si))
        {
            return toString(getDisplayUnit().getStandardUnit());
        }
        // PK: I can't think of an easier way to figure out what the exponent will be; rounding of the mantissa to the available
        // width makes this hard; This feels like an expensive way.
        String check = String.format(this.si >= 0 ? "%10.8E" : "%10.7E", this.si);
        int exponent = Integer.parseInt(check.substring(check.indexOf("E") + 1));
        if (exponent < -27 || exponent < smallestPower || exponent > 21 + 2 || exponent > biggestPower)
        {
            // Out of SI prefix range; do not scale.
            return String.format(this.si >= 0 ? "%10.4E" : "%10.3E", this.si) + " "
                    + getDisplayUnit().getStandardUnit().getId();
        }
        Integer roundedExponent = (int) Math.ceil((exponent - 2.0) / 3) * 3 + 3;
        // System.out.print(String.format("exponent=%d; roundedExponent=%d ", exponent, roundedExponent));
        String key = SIPrefixes.FACTORS.get(roundedExponent).getDefaultTextualPrefix() + "g";
        MassUnit displayUnit = getDisplayUnit().getQuantity().getUnitByAbbreviation(key);
        return toString(displayUnit);
    }

    /**
     * Calculate the division of FloatMass and FloatMass, which results in a FloatDimensionless scalar.
     * @param v FloatMass; scalar
     * @return FloatDimensionless; scalar as a division of FloatMass and FloatMass
     */
    public final FloatDimensionless divide(final FloatMass v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of FloatMass and FloatFlowMass, which results in a FloatDuration scalar.
     * @param v FloatMass; scalar
     * @return FloatDuration; scalar as a division of FloatMass and FloatFlowMass
     */
    public final FloatDuration divide(final FloatFlowMass v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the division of FloatMass and FloatDuration, which results in a FloatFlowMass scalar.
     * @param v FloatMass; scalar
     * @return FloatFlowMass; scalar as a division of FloatMass and FloatDuration
     */
    public final FloatFlowMass divide(final FloatDuration v)
    {
        return new FloatFlowMass(this.si / v.si, FlowMassUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatMass and FloatAcceleration, which results in a FloatForce scalar.
     * @param v FloatMass; scalar
     * @return FloatForce; scalar as a multiplication of FloatMass and FloatAcceleration
     */
    public final FloatForce times(final FloatAcceleration v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatMass and FloatFrequency, which results in a FloatFlowMass scalar.
     * @param v FloatMass; scalar
     * @return FloatFlowMass; scalar as a multiplication of FloatMass and FloatFrequency
     */
    public final FloatFlowMass times(final FloatFrequency v)
    {
        return new FloatFlowMass(this.si * v.si, FlowMassUnit.SI);
    }

    /**
     * Calculate the division of FloatMass and FloatDensity, which results in a FloatVolume scalar.
     * @param v FloatMass; scalar
     * @return FloatVolume; scalar as a division of FloatMass and FloatDensity
     */
    public final FloatVolume divide(final FloatDensity v)
    {
        return new FloatVolume(this.si / v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of FloatMass and FloatVolume, which results in a FloatDensity scalar.
     * @param v FloatMass; scalar
     * @return FloatDensity; scalar as a division of FloatMass and FloatVolume
     */
    public final FloatDensity divide(final FloatVolume v)
    {
        return new FloatDensity(this.si / v.si, DensityUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatMass and FloatSpeed, which results in a FloatMomentum scalar.
     * @param v FloatMass; scalar
     * @return FloatMomentum; scalar as a multiplication of FloatMass and FloatSpeed
     */
    public final FloatMomentum times(final FloatSpeed v)
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
