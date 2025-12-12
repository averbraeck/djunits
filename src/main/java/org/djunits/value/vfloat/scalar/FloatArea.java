package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatArea FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatArea extends FloatScalarRel<AreaUnit, FloatArea>
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
     * Construct FloatArea scalar with a unit.
     * @param value the float value, expressed in the given unit
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
     * Construct FloatArea scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatArea(final double value, final AreaUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatArea instantiateRel(final float value, final AreaUnit unit)
    {
        return new FloatArea(value, unit);
    }

    /**
     * Construct FloatArea scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatArea ofSI(final float value)
    {
        return new FloatArea(value, AreaUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatArea at the given ratio between 0 and 1
     */
    public static FloatArea interpolate(final FloatArea zero, final FloatArea one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
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
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatArea
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatArea valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatArea: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatArea: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AreaUnit unit = AreaUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Area", unitString);
            return new FloatArea(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatArea from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FloatArea based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatArea of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatArea: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatArea: empty unitString");
        AreaUnit unit = AreaUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatArea with unit %s", unitString);
        return new FloatArea(value, unit);
    }

    /**
     * Calculate the division of FloatArea and FloatArea, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatArea and FloatArea
     */
    public final FloatDimensionless divide(final FloatArea v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatArea and FloatLength, which results in a FloatVolume scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatArea and FloatLength
     */
    public final FloatVolume times(final FloatLength v)
    {
        return new FloatVolume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of FloatArea and FloatLinearDensity, which results in a FloatVolume scalar.
     * @param v scalar
     * @return scalar as a division of FloatArea and FloatLinearDensity
     */
    public final FloatVolume divide(final FloatLinearDensity v)
    {
        return new FloatVolume(this.si / v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of FloatArea and FloatVolume, which results in a FloatLinearDensity scalar.
     * @param v scalar
     * @return scalar as a division of FloatArea and FloatVolume
     */
    public final FloatLinearDensity divide(final FloatVolume v)
    {
        return new FloatLinearDensity(this.si / v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the division of FloatArea and FloatLength, which results in a FloatLength scalar.
     * @param v scalar
     * @return scalar as a division of FloatArea and FloatLength
     */
    public final FloatLength divide(final FloatLength v)
    {
        return new FloatLength(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatArea and FloatLinearDensity, which results in a FloatLength scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatArea and FloatLinearDensity
     */
    public final FloatLength times(final FloatLinearDensity v)
    {
        return new FloatLength(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatArea and FloatSpeed, which results in a FloatFlowVolume scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatArea and FloatSpeed
     */
    public final FloatFlowVolume times(final FloatSpeed v)
    {
        return new FloatFlowVolume(this.si * v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatArea and FloatPressure, which results in a FloatForce scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatArea and FloatPressure
     */
    public final FloatForce times(final FloatPressure v)
    {
        return new FloatForce(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatArea and FloatIlluminance, which results in a FloatLuminousFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatArea and FloatIlluminance
     */
    public final FloatLuminousFlux times(final FloatIlluminance v)
    {
        return new FloatLuminousFlux(this.si * v.si, LuminousFluxUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatArea.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatArea
     */
    public static FloatArea multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(AreaUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FloatArea",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatArea(scalar1.si * scalar2.si, AreaUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatArea.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatArea
     */
    public static FloatArea divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(AreaUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FloatArea",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatArea(scalar1.si / scalar2.si, AreaUnit.SI);
    }

}
