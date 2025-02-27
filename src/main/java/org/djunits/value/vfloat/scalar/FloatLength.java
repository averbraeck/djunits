package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.unit.MomentumUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalarRelWithAbs;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatLength FloatScalar.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatLength extends FloatScalarRelWithAbs<PositionUnit, FloatPosition, LengthUnit, FloatLength>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatLength ZERO = new FloatLength(0.0f, LengthUnit.SI);

    /** Constant with value one. */
    public static final FloatLength ONE = new FloatLength(1.0f, LengthUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatLength NaN = new FloatLength(Float.NaN, LengthUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatLength POSITIVE_INFINITY = new FloatLength(Float.POSITIVE_INFINITY, LengthUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatLength NEGATIVE_INFINITY = new FloatLength(Float.NEGATIVE_INFINITY, LengthUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatLength POS_MAXVALUE = new FloatLength(Float.MAX_VALUE, LengthUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatLength NEG_MAXVALUE = new FloatLength(-Float.MAX_VALUE, LengthUnit.SI);

    /**
     * Construct FloatLength scalar.
     * @param value the float value
     * @param unit unit for the float value
     */
    public FloatLength(final float value, final LengthUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatLength scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatLength(final FloatLength value)
    {
        super(value);
    }

    /**
     * Construct FloatLength scalar using a double value.
     * @param value the double value
     * @param unit unit for the resulting float value
     */
    public FloatLength(final double value, final LengthUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatLength instantiateRel(final float value, final LengthUnit unit)
    {
        return new FloatLength(value, unit);
    }

    /**
     * Construct FloatLength scalar.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatLength instantiateSI(final float value)
    {
        return new FloatLength(value, LengthUnit.SI);
    }

    @Override
    public final FloatPosition instantiateAbs(final float value, final PositionUnit unit)
    {
        return new FloatPosition(value, unit);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatLength interpolate(final FloatLength zero, final FloatLength one, final float ratio)
    {
        return new FloatLength(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatLength max(final FloatLength r1, final FloatLength r2)
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
    public static FloatLength max(final FloatLength r1, final FloatLength r2, final FloatLength... rn)
    {
        FloatLength maxr = r1.gt(r2) ? r1 : r2;
        for (FloatLength r : rn)
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
    public static FloatLength min(final FloatLength r1, final FloatLength r2)
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
    public static FloatLength min(final FloatLength r1, final FloatLength r2, final FloatLength... rn)
    {
        FloatLength minr = r1.lt(r2) ? r1 : r2;
        for (FloatLength r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatLength representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatLength
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatLength valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatLength: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatLength: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            LengthUnit unit = LengthUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit == null)
                throw new IllegalArgumentException("Unit " + unitString + " not found");
            return new FloatLength(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatLength from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FloatLength based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatLength of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatLength: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatLength: empty unitString");
        LengthUnit unit = LengthUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatLength(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatLength with unit " + unitString);
    }

    /**
     * Calculate the division of FloatLength and FloatLength, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatLength and FloatLength
     */
    public final FloatDimensionless divide(final FloatLength v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatLength and FloatLinearDensity, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatLength and FloatLinearDensity
     */
    public final FloatDimensionless times(final FloatLinearDensity v)
    {
        return new FloatDimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatLength and FloatLength, which results in a FloatArea scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatLength and FloatLength
     */
    public final FloatArea times(final FloatLength v)
    {
        return new FloatArea(this.si * v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of FloatLength and FloatLinearDensity, which results in a FloatArea scalar.
     * @param v scalar
     * @return scalar as a division of FloatLength and FloatLinearDensity
     */
    public final FloatArea divide(final FloatLinearDensity v)
    {
        return new FloatArea(this.si / v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of FloatLength and FloatArea, which results in a FloatLinearDensity scalar.
     * @param v scalar
     * @return scalar as a division of FloatLength and FloatArea
     */
    public final FloatLinearDensity divide(final FloatArea v)
    {
        return new FloatLinearDensity(this.si / v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatLength and FloatArea, which results in a FloatVolume scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatLength and FloatArea
     */
    public final FloatVolume times(final FloatArea v)
    {
        return new FloatVolume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatLength and FloatForce, which results in a FloatEnergy scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatLength and FloatForce
     */
    public final FloatEnergy times(final FloatForce v)
    {
        return new FloatEnergy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatLength and FloatFrequency, which results in a FloatSpeed scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatLength and FloatFrequency
     */
    public final FloatSpeed times(final FloatFrequency v)
    {
        return new FloatSpeed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of FloatLength and FloatDuration, which results in a FloatSpeed scalar.
     * @param v scalar
     * @return scalar as a division of FloatLength and FloatDuration
     */
    public final FloatSpeed divide(final FloatDuration v)
    {
        return new FloatSpeed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of FloatLength and FloatSpeed, which results in a FloatDuration scalar.
     * @param v scalar
     * @return scalar as a division of FloatLength and FloatSpeed
     */
    public final FloatDuration divide(final FloatSpeed v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatLength and FloatFlowMass, which results in a FloatMomentum scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatLength and FloatFlowMass
     */
    public final FloatMomentum times(final FloatFlowMass v)
    {
        return new FloatMomentum(this.si * v.si, MomentumUnit.SI);
    }

    @Override
    public FloatLinearDensity reciprocal()
    {
        return FloatLinearDensity.instantiateSI(1.0f / this.si);
    }

}
