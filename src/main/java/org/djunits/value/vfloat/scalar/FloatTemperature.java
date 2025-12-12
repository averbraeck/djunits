package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalarRelWithAbs;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatTemperature FloatScalar.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatTemperature
        extends FloatScalarRelWithAbs<AbsoluteTemperatureUnit, FloatAbsoluteTemperature, TemperatureUnit, FloatTemperature>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatTemperature ZERO = new FloatTemperature(0.0f, TemperatureUnit.SI);

    /** Constant with value one. */
    public static final FloatTemperature ONE = new FloatTemperature(1.0f, TemperatureUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatTemperature NaN = new FloatTemperature(Float.NaN, TemperatureUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatTemperature POSITIVE_INFINITY = new FloatTemperature(Float.POSITIVE_INFINITY, TemperatureUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatTemperature NEGATIVE_INFINITY = new FloatTemperature(Float.NEGATIVE_INFINITY, TemperatureUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatTemperature POS_MAXVALUE = new FloatTemperature(Float.MAX_VALUE, TemperatureUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatTemperature NEG_MAXVALUE = new FloatTemperature(-Float.MAX_VALUE, TemperatureUnit.SI);

    /**
     * Construct FloatTemperature scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatTemperature(final float value, final TemperatureUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatTemperature scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatTemperature(final FloatTemperature value)
    {
        super(value);
    }

    /**
     * Construct FloatTemperature scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatTemperature(final double value, final TemperatureUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatTemperature instantiateRel(final float value, final TemperatureUnit unit)
    {
        return new FloatTemperature(value, unit);
    }

    /**
     * Construct FloatTemperature scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatTemperature ofSI(final float value)
    {
        return new FloatTemperature(value, TemperatureUnit.SI);
    }

    @Override
    public final FloatAbsoluteTemperature instantiateAbs(final float value, final AbsoluteTemperatureUnit unit)
    {
        return new FloatAbsoluteTemperature(value, unit);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatTemperature at the given ratio between 0 and 1
     */
    public static FloatTemperature interpolate(final FloatTemperature zero, final FloatTemperature one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatTemperature(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatTemperature max(final FloatTemperature r1, final FloatTemperature r2)
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
    public static FloatTemperature max(final FloatTemperature r1, final FloatTemperature r2, final FloatTemperature... rn)
    {
        FloatTemperature maxr = r1.gt(r2) ? r1 : r2;
        for (FloatTemperature r : rn)
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
    public static FloatTemperature min(final FloatTemperature r1, final FloatTemperature r2)
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
    public static FloatTemperature min(final FloatTemperature r1, final FloatTemperature r2, final FloatTemperature... rn)
    {
        FloatTemperature minr = r1.lt(r2) ? r1 : r2;
        for (FloatTemperature r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatTemperature representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatTemperature
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatTemperature valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatTemperature: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatTemperature: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            TemperatureUnit unit = TemperatureUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Temperature", unitString);
            return new FloatTemperature(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatTemperature from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatTemperature based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatTemperature of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatTemperature: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatTemperature: empty unitString");
        TemperatureUnit unit = TemperatureUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatTemperature with unit %s", unitString);
        return new FloatTemperature(value, unit);
    }

    /**
     * Calculate the division of FloatTemperature and FloatTemperature, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatTemperature and FloatTemperature
     */
    public final FloatDimensionless divide(final FloatTemperature v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatTemperature.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatTemperature
     */
    public static FloatTemperature multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(TemperatureUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FloatTemperature",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatTemperature(scalar1.si * scalar2.si, TemperatureUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatTemperature.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatTemperature
     */
    public static FloatTemperature divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(TemperatureUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FloatTemperature",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatTemperature(scalar1.si / scalar2.si, TemperatureUnit.SI);
    }

}
