package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.MomentumUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatFlowMass FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatFlowMass extends FloatScalarRel<FlowMassUnit, FloatFlowMass>
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
     * Construct FloatFlowMass scalar with a unit.
     * @param value the float value, expressed in the given unit
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
     * Construct FloatFlowMass scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatFlowMass(final double value, final FlowMassUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatFlowMass instantiateRel(final float value, final FlowMassUnit unit)
    {
        return new FloatFlowMass(value, unit);
    }

    /**
     * Construct FloatFlowMass scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatFlowMass ofSI(final float value)
    {
        return new FloatFlowMass(value, FlowMassUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatFlowMass at the given ratio between 0 and 1
     */
    public static FloatFlowMass interpolate(final FloatFlowMass zero, final FloatFlowMass one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
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
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatFlowMass
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatFlowMass valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatFlowMass: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatFlowMass: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            FlowMassUnit unit = FlowMassUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity FlowMass", unitString);
            return new FloatFlowMass(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatFlowMass from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FloatFlowMass based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatFlowMass of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatFlowMass: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatFlowMass: empty unitString");
        FlowMassUnit unit = FlowMassUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatFlowMass with unit %s", unitString);
        return new FloatFlowMass(value, unit);
    }

    /**
     * Calculate the division of FloatFlowMass and FloatFlowMass, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatFlowMass and FloatFlowMass
     */
    public final FloatDimensionless divide(final FloatFlowMass v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFlowMass and FloatDuration, which results in a FloatMass scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatFlowMass and FloatDuration
     */
    public final FloatMass times(final FloatDuration v)
    {
        return new FloatMass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowMass and FloatFrequency, which results in a FloatMass scalar.
     * @param v scalar
     * @return scalar as a division of FloatFlowMass and FloatFrequency
     */
    public final FloatMass divide(final FloatFrequency v)
    {
        return new FloatMass(this.si / v.si, MassUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowMass and FloatMass, which results in a FloatFrequency scalar.
     * @param v scalar
     * @return scalar as a division of FloatFlowMass and FloatMass
     */
    public final FloatFrequency divide(final FloatMass v)
    {
        return new FloatFrequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFlowMass and FloatSpeed, which results in a FloatForce scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatFlowMass and FloatSpeed
     */
    public final FloatForce times(final FloatSpeed v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowMass and FloatFlowVolume, which results in a FloatDensity scalar.
     * @param v scalar
     * @return scalar as a division of FloatFlowMass and FloatFlowVolume
     */
    public final FloatDensity divide(final FloatFlowVolume v)
    {
        return new FloatDensity(this.si / v.si, DensityUnit.SI);
    }

    /**
     * Calculate the division of FloatFlowMass and FloatDensity, which results in a FloatFlowVolume scalar.
     * @param v scalar
     * @return scalar as a division of FloatFlowMass and FloatDensity
     */
    public final FloatFlowVolume divide(final FloatDensity v)
    {
        return new FloatFlowVolume(this.si / v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatFlowMass and FloatLength, which results in a FloatMomentum scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatFlowMass and FloatLength
     */
    public final FloatMomentum times(final FloatLength v)
    {
        return new FloatMomentum(this.si * v.si, MomentumUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatFlowMass.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatFlowMass
     */
    public static FloatFlowMass multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(FlowMassUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FloatFlowMass",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatFlowMass(scalar1.si * scalar2.si, FlowMassUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatFlowMass.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatFlowMass
     */
    public static FloatFlowMass divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(FlowMassUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FloatFlowMass",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatFlowMass(scalar1.si / scalar2.si, FlowMassUnit.SI);
    }

}
