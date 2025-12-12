package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.PressureUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatPressure FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatPressure extends FloatScalarRel<PressureUnit, FloatPressure>
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
     * Construct FloatPressure scalar with a unit.
     * @param value the float value, expressed in the given unit
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
     * Construct FloatPressure scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatPressure(final double value, final PressureUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatPressure instantiateRel(final float value, final PressureUnit unit)
    {
        return new FloatPressure(value, unit);
    }

    /**
     * Construct FloatPressure scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatPressure ofSI(final float value)
    {
        return new FloatPressure(value, PressureUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatPressure at the given ratio between 0 and 1
     */
    public static FloatPressure interpolate(final FloatPressure zero, final FloatPressure one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
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
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatPressure
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatPressure valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatPressure: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatPressure: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            PressureUnit unit = PressureUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Pressure", unitString);
            return new FloatPressure(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatPressure from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FloatPressure based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatPressure of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatPressure: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatPressure: empty unitString");
        PressureUnit unit = PressureUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatPressure with unit %s", unitString);
        return new FloatPressure(value, unit);
    }

    /**
     * Calculate the division of FloatPressure and FloatPressure, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatPressure and FloatPressure
     */
    public final FloatDimensionless divide(final FloatPressure v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatPressure and FloatArea, which results in a FloatForce scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatPressure and FloatArea
     */
    public final FloatForce times(final FloatArea v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatPressure and FloatVolume, which results in a FloatEnergy scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatPressure and FloatVolume
     */
    public final FloatEnergy times(final FloatVolume v)
    {
        return new FloatEnergy(this.si * v.si, EnergyUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatPressure.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatPressure
     */
    public static FloatPressure multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(PressureUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FloatPressure",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatPressure(scalar1.si * scalar2.si, PressureUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatPressure.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatPressure
     */
    public static FloatPressure divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(PressureUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FloatPressure",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatPressure(scalar1.si / scalar2.si, PressureUnit.SI);
    }

}
