package org.djunits.old.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.old.unit.AccelerationUnit;
import org.djunits.old.unit.AreaUnit;
import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.EnergyUnit;
import org.djunits.old.unit.ForceUnit;
import org.djunits.old.unit.LinearDensityUnit;
import org.djunits.old.unit.MassUnit;
import org.djunits.old.unit.PowerUnit;
import org.djunits.old.unit.PressureUnit;
import org.djunits.old.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatForce FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatForce extends FloatScalarRel<ForceUnit, FloatForce>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatForce ZERO = new FloatForce(0.0f, ForceUnit.SI);

    /** Constant with value one. */
    public static final FloatForce ONE = new FloatForce(1.0f, ForceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatForce NaN = new FloatForce(Float.NaN, ForceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatForce POSITIVE_INFINITY = new FloatForce(Float.POSITIVE_INFINITY, ForceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatForce NEGATIVE_INFINITY = new FloatForce(Float.NEGATIVE_INFINITY, ForceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatForce POS_MAXVALUE = new FloatForce(Float.MAX_VALUE, ForceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatForce NEG_MAXVALUE = new FloatForce(-Float.MAX_VALUE, ForceUnit.SI);

    /**
     * Construct FloatForce scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatForce(final float value, final ForceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatForce scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatForce(final FloatForce value)
    {
        super(value);
    }

    /**
     * Construct FloatForce scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatForce(final double value, final ForceUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatForce instantiateRel(final float value, final ForceUnit unit)
    {
        return new FloatForce(value, unit);
    }

    /**
     * Construct FloatForce scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatForce ofSI(final float value)
    {
        return new FloatForce(value, ForceUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatForce at the given ratio between 0 and 1
     */
    public static FloatForce interpolate(final FloatForce zero, final FloatForce one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatForce(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatForce max(final FloatForce r1, final FloatForce r2)
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
    public static FloatForce max(final FloatForce r1, final FloatForce r2, final FloatForce... rn)
    {
        FloatForce maxr = r1.gt(r2) ? r1 : r2;
        for (FloatForce r : rn)
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
    public static FloatForce min(final FloatForce r1, final FloatForce r2)
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
    public static FloatForce min(final FloatForce r1, final FloatForce r2, final FloatForce... rn)
    {
        FloatForce minr = r1.lt(r2) ? r1 : r2;
        for (FloatForce r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatForce representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatForce
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatForce valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatForce: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatForce: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ForceUnit unit = ForceUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Force", unitString);
            return new FloatForce(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatForce from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FloatForce based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatForce of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatForce: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatForce: empty unitString");
        ForceUnit unit = ForceUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatForce with unit %s", unitString);
        return new FloatForce(value, unit);
    }

    /**
     * Calculate the division of FloatForce and FloatForce, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatForce and FloatForce
     */
    public final FloatDimensionless divide(final FloatForce v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatForce and FloatLength, which results in a FloatEnergy scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatForce and FloatLength
     */
    public final FloatEnergy times(final FloatLength v)
    {
        return new FloatEnergy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of FloatForce and FloatLinearDensity, which results in a FloatEnergy scalar.
     * @param v scalar
     * @return scalar as a division of FloatForce and FloatLinearDensity
     */
    public final FloatEnergy divide(final FloatLinearDensity v)
    {
        return new FloatEnergy(this.si / v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of FloatForce and FloatEnergy, which results in a FloatLinearDensity scalar.
     * @param v scalar
     * @return scalar as a division of FloatForce and FloatEnergy
     */
    public final FloatLinearDensity divide(final FloatEnergy v)
    {
        return new FloatLinearDensity(this.si / v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatForce and FloatSpeed, which results in a FloatPower scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatForce and FloatSpeed
     */
    public final FloatPower times(final FloatSpeed v)
    {
        return new FloatPower(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the division of FloatForce and FloatMass, which results in a FloatAcceleration scalar.
     * @param v scalar
     * @return scalar as a division of FloatForce and FloatMass
     */
    public final FloatAcceleration divide(final FloatMass v)
    {
        return new FloatAcceleration(this.si / v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the division of FloatForce and FloatAcceleration, which results in a FloatMass scalar.
     * @param v scalar
     * @return scalar as a division of FloatForce and FloatAcceleration
     */
    public final FloatMass divide(final FloatAcceleration v)
    {
        return new FloatMass(this.si / v.si, MassUnit.SI);
    }

    /**
     * Calculate the division of FloatForce and FloatArea, which results in a FloatPressure scalar.
     * @param v scalar
     * @return scalar as a division of FloatForce and FloatArea
     */
    public final FloatPressure divide(final FloatArea v)
    {
        return new FloatPressure(this.si / v.si, PressureUnit.SI);
    }

    /**
     * Calculate the division of FloatForce and FloatPressure, which results in a FloatArea scalar.
     * @param v scalar
     * @return scalar as a division of FloatForce and FloatPressure
     */
    public final FloatArea divide(final FloatPressure v)
    {
        return new FloatArea(this.si / v.si, AreaUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatForce.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatForce
     */
    public static FloatForce multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(ForceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FloatForce",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatForce(scalar1.si * scalar2.si, ForceUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatForce.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatForce
     */
    public static FloatForce divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(ForceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FloatForce",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatForce(scalar1.si / scalar2.si, ForceUnit.SI);
    }

}
