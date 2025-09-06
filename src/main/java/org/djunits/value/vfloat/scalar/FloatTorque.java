package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.PressureUnit;
import org.djunits.unit.TorqueUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatTorque FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatTorque extends FloatScalarRel<TorqueUnit, FloatTorque>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatTorque ZERO = new FloatTorque(0.0f, TorqueUnit.SI);

    /** Constant with value one. */
    public static final FloatTorque ONE = new FloatTorque(1.0f, TorqueUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatTorque NaN = new FloatTorque(Float.NaN, TorqueUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatTorque POSITIVE_INFINITY = new FloatTorque(Float.POSITIVE_INFINITY, TorqueUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatTorque NEGATIVE_INFINITY = new FloatTorque(Float.NEGATIVE_INFINITY, TorqueUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatTorque POS_MAXVALUE = new FloatTorque(Float.MAX_VALUE, TorqueUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatTorque NEG_MAXVALUE = new FloatTorque(-Float.MAX_VALUE, TorqueUnit.SI);

    /**
     * Construct FloatTorque scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatTorque(final float value, final TorqueUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatTorque scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatTorque(final FloatTorque value)
    {
        super(value);
    }

    /**
     * Construct FloatTorque scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatTorque(final double value, final TorqueUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatTorque instantiateRel(final float value, final TorqueUnit unit)
    {
        return new FloatTorque(value, unit);
    }

    /**
     * Construct FloatTorque scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatTorque ofSI(final float value)
    {
        return new FloatTorque(value, TorqueUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatTorque at the given ratio between 0 and 1
     */
    public static FloatTorque interpolate(final FloatTorque zero, final FloatTorque one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatTorque(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatTorque max(final FloatTorque r1, final FloatTorque r2)
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
    public static FloatTorque max(final FloatTorque r1, final FloatTorque r2, final FloatTorque... rn)
    {
        FloatTorque maxr = r1.gt(r2) ? r1 : r2;
        for (FloatTorque r : rn)
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
    public static FloatTorque min(final FloatTorque r1, final FloatTorque r2)
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
    public static FloatTorque min(final FloatTorque r1, final FloatTorque r2, final FloatTorque... rn)
    {
        FloatTorque minr = r1.lt(r2) ? r1 : r2;
        for (FloatTorque r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatTorque representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatTorque
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatTorque valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatTorque: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatTorque: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            TorqueUnit unit = TorqueUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Torque", unitString);
            return new FloatTorque(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatTorque from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FloatTorque based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatTorque of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatTorque: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatTorque: empty unitString");
        TorqueUnit unit = TorqueUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatTorque with unit %s", unitString);
        return new FloatTorque(value, unit);
    }

    /**
     * Calculate the division of FloatTorque and FloatTorque, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatTorque and FloatTorque
     */
    public final FloatDimensionless divide(final FloatTorque v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of FloatTorque and FloatForce, which results in a FloatLength scalar.
     * @param v scalar
     * @return scalar as a division of FloatTorque and FloatForce
     */
    public final FloatLength divide(final FloatForce v)
    {
        return new FloatLength(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the division of FloatTorque and FloatLength, which results in a FloatForce scalar.
     * @param v scalar
     * @return scalar as a division of FloatTorque and FloatLength
     */
    public final FloatForce divide(final FloatLength v)
    {
        return new FloatForce(this.si / v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatTorque and FloatLinearDensity, which results in a FloatForce scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatTorque and FloatLinearDensity
     */
    public final FloatForce times(final FloatLinearDensity v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the division of FloatTorque and FloatDuration, which results in a FloatPower scalar.
     * @param v scalar
     * @return scalar as a division of FloatTorque and FloatDuration
     */
    public final FloatPower divide(final FloatDuration v)
    {
        return new FloatPower(this.si / v.si, PowerUnit.SI);
    }

    /**
     * Calculate the division of FloatTorque and FloatPower, which results in a FloatDuration scalar.
     * @param v scalar
     * @return scalar as a division of FloatTorque and FloatPower
     */
    public final FloatDuration divide(final FloatPower v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatTorque and FloatFrequency, which results in a FloatPower scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatTorque and FloatFrequency
     */
    public final FloatPower times(final FloatFrequency v)
    {
        return new FloatPower(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the division of FloatTorque and FloatVolume, which results in a FloatPressure scalar.
     * @param v scalar
     * @return scalar as a division of FloatTorque and FloatVolume
     */
    public final FloatPressure divide(final FloatVolume v)
    {
        return new FloatPressure(this.si / v.si, PressureUnit.SI);
    }

    /**
     * Calculate the division of FloatTorque and FloatPressure, which results in a FloatVolume scalar.
     * @param v scalar
     * @return scalar as a division of FloatTorque and FloatPressure
     */
    public final FloatVolume divide(final FloatPressure v)
    {
        return new FloatVolume(this.si / v.si, VolumeUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
