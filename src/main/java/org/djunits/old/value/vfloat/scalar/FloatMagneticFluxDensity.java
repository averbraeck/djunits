package org.djunits.old.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.MagneticFluxDensityUnit;
import org.djunits.old.unit.MagneticFluxUnit;
import org.djunits.old.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatMagneticFluxDensity FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatMagneticFluxDensity extends FloatScalarRel<MagneticFluxDensityUnit, FloatMagneticFluxDensity>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatMagneticFluxDensity ZERO = new FloatMagneticFluxDensity(0.0f, MagneticFluxDensityUnit.SI);

    /** Constant with value one. */
    public static final FloatMagneticFluxDensity ONE = new FloatMagneticFluxDensity(1.0f, MagneticFluxDensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatMagneticFluxDensity NaN = new FloatMagneticFluxDensity(Float.NaN, MagneticFluxDensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatMagneticFluxDensity POSITIVE_INFINITY =
            new FloatMagneticFluxDensity(Float.POSITIVE_INFINITY, MagneticFluxDensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatMagneticFluxDensity NEGATIVE_INFINITY =
            new FloatMagneticFluxDensity(Float.NEGATIVE_INFINITY, MagneticFluxDensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatMagneticFluxDensity POS_MAXVALUE =
            new FloatMagneticFluxDensity(Float.MAX_VALUE, MagneticFluxDensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatMagneticFluxDensity NEG_MAXVALUE =
            new FloatMagneticFluxDensity(-Float.MAX_VALUE, MagneticFluxDensityUnit.SI);

    /**
     * Construct FloatMagneticFluxDensity scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatMagneticFluxDensity(final float value, final MagneticFluxDensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatMagneticFluxDensity scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatMagneticFluxDensity(final FloatMagneticFluxDensity value)
    {
        super(value);
    }

    /**
     * Construct FloatMagneticFluxDensity scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatMagneticFluxDensity(final double value, final MagneticFluxDensityUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatMagneticFluxDensity instantiateRel(final float value, final MagneticFluxDensityUnit unit)
    {
        return new FloatMagneticFluxDensity(value, unit);
    }

    /**
     * Construct FloatMagneticFluxDensity scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatMagneticFluxDensity ofSI(final float value)
    {
        return new FloatMagneticFluxDensity(value, MagneticFluxDensityUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatMagneticFluxDensity at the given ratio between 0 and 1
     */
    public static FloatMagneticFluxDensity interpolate(final FloatMagneticFluxDensity zero, final FloatMagneticFluxDensity one,
            final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatMagneticFluxDensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatMagneticFluxDensity max(final FloatMagneticFluxDensity r1, final FloatMagneticFluxDensity r2)
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
    public static FloatMagneticFluxDensity max(final FloatMagneticFluxDensity r1, final FloatMagneticFluxDensity r2,
            final FloatMagneticFluxDensity... rn)
    {
        FloatMagneticFluxDensity maxr = r1.gt(r2) ? r1 : r2;
        for (FloatMagneticFluxDensity r : rn)
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
    public static FloatMagneticFluxDensity min(final FloatMagneticFluxDensity r1, final FloatMagneticFluxDensity r2)
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
    public static FloatMagneticFluxDensity min(final FloatMagneticFluxDensity r1, final FloatMagneticFluxDensity r2,
            final FloatMagneticFluxDensity... rn)
    {
        FloatMagneticFluxDensity minr = r1.lt(r2) ? r1 : r2;
        for (FloatMagneticFluxDensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatMagneticFluxDensity representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatMagneticFluxDensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatMagneticFluxDensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatMagneticFluxDensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatMagneticFluxDensity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            MagneticFluxDensityUnit unit = MagneticFluxDensityUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity MagneticFluxDensity",
                    unitString);
            return new FloatMagneticFluxDensity(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatMagneticFluxDensity from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatMagneticFluxDensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatMagneticFluxDensity of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatMagneticFluxDensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatMagneticFluxDensity: empty unitString");
        MagneticFluxDensityUnit unit = MagneticFluxDensityUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatMagneticFluxDensity with unit %s",
                unitString);
        return new FloatMagneticFluxDensity(value, unit);
    }

    /**
     * Calculate the division of FloatMagneticFluxDensity and FloatMagneticFluxDensity, which results in a FloatDimensionless
     * scalar.
     * @param v scalar
     * @return scalar as a division of FloatMagneticFluxDensity and FloatMagneticFluxDensity
     */
    public final FloatDimensionless divide(final FloatMagneticFluxDensity v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatMagneticFluxDensity and FloatArea, which results in a FloatMagneticFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatMagneticFluxDensity and FloatArea
     */
    public final FloatMagneticFlux times(final FloatArea v)
    {
        return new FloatMagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatMagneticFluxDensity.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatMagneticFluxDensity
     */
    public static FloatMagneticFluxDensity multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(MagneticFluxDensityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class,
                "Multiplying %s by %s does not result in instance of type FloatMagneticFluxDensity", scalar1.toDisplayString(),
                scalar2.toDisplayString());
        return new FloatMagneticFluxDensity(scalar1.si * scalar2.si, MagneticFluxDensityUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatMagneticFluxDensity.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatMagneticFluxDensity
     */
    public static FloatMagneticFluxDensity divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(MagneticFluxDensityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class,
                "Dividing %s by %s does not result in an instance of type FloatMagneticFluxDensity", scalar1.toDisplayString(),
                scalar2.toDisplayString());
        return new FloatMagneticFluxDensity(scalar1.si / scalar2.si, MagneticFluxDensityUnit.SI);
    }

}
