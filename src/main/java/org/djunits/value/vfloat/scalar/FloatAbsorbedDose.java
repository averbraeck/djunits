package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AbsorbedDoseUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatAbsorbedDose FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatAbsorbedDose extends FloatScalarRel<AbsorbedDoseUnit, FloatAbsorbedDose>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatAbsorbedDose ZERO = new FloatAbsorbedDose(0.0f, AbsorbedDoseUnit.SI);

    /** Constant with value one. */
    public static final FloatAbsorbedDose ONE = new FloatAbsorbedDose(1.0f, AbsorbedDoseUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatAbsorbedDose NaN = new FloatAbsorbedDose(Float.NaN, AbsorbedDoseUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatAbsorbedDose POSITIVE_INFINITY =
            new FloatAbsorbedDose(Float.POSITIVE_INFINITY, AbsorbedDoseUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatAbsorbedDose NEGATIVE_INFINITY =
            new FloatAbsorbedDose(Float.NEGATIVE_INFINITY, AbsorbedDoseUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatAbsorbedDose POS_MAXVALUE = new FloatAbsorbedDose(Float.MAX_VALUE, AbsorbedDoseUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatAbsorbedDose NEG_MAXVALUE = new FloatAbsorbedDose(-Float.MAX_VALUE, AbsorbedDoseUnit.SI);

    /**
     * Construct FloatAbsorbedDose scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatAbsorbedDose(final float value, final AbsorbedDoseUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatAbsorbedDose scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatAbsorbedDose(final FloatAbsorbedDose value)
    {
        super(value);
    }

    /**
     * Construct FloatAbsorbedDose scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatAbsorbedDose(final double value, final AbsorbedDoseUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatAbsorbedDose instantiateRel(final float value, final AbsorbedDoseUnit unit)
    {
        return new FloatAbsorbedDose(value, unit);
    }

    /**
     * Construct FloatAbsorbedDose scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatAbsorbedDose ofSI(final float value)
    {
        return new FloatAbsorbedDose(value, AbsorbedDoseUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatAbsorbedDose at the given ratio between 0 and 1
     */
    public static FloatAbsorbedDose interpolate(final FloatAbsorbedDose zero, final FloatAbsorbedDose one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatAbsorbedDose(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatAbsorbedDose max(final FloatAbsorbedDose r1, final FloatAbsorbedDose r2)
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
    public static FloatAbsorbedDose max(final FloatAbsorbedDose r1, final FloatAbsorbedDose r2, final FloatAbsorbedDose... rn)
    {
        FloatAbsorbedDose maxr = r1.gt(r2) ? r1 : r2;
        for (FloatAbsorbedDose r : rn)
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
    public static FloatAbsorbedDose min(final FloatAbsorbedDose r1, final FloatAbsorbedDose r2)
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
    public static FloatAbsorbedDose min(final FloatAbsorbedDose r1, final FloatAbsorbedDose r2, final FloatAbsorbedDose... rn)
    {
        FloatAbsorbedDose minr = r1.lt(r2) ? r1 : r2;
        for (FloatAbsorbedDose r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatAbsorbedDose representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatAbsorbedDose
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAbsorbedDose valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAbsorbedDose: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatAbsorbedDose: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AbsorbedDoseUnit unit = AbsorbedDoseUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity AbsorbedDose", unitString);
            return new FloatAbsorbedDose(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatAbsorbedDose from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatAbsorbedDose based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAbsorbedDose of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAbsorbedDose: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAbsorbedDose: empty unitString");
        AbsorbedDoseUnit unit = AbsorbedDoseUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatAbsorbedDose with unit %s", unitString);
        return new FloatAbsorbedDose(value, unit);
    }

    /**
     * Calculate the division of FloatAbsorbedDose and FloatAbsorbedDose, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatAbsorbedDose and FloatAbsorbedDose
     */
    public final FloatDimensionless divide(final FloatAbsorbedDose v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
