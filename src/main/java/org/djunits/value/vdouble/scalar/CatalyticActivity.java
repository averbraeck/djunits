package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.AmountOfSubstanceUnit;
import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the CatalyticActivity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class CatalyticActivity extends DoubleScalarRel<CatalyticActivityUnit, CatalyticActivity>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final CatalyticActivity ZERO = new CatalyticActivity(0.0, CatalyticActivityUnit.SI);

    /** Constant with value one. */
    public static final CatalyticActivity ONE = new CatalyticActivity(1.0, CatalyticActivityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final CatalyticActivity NaN = new CatalyticActivity(Double.NaN, CatalyticActivityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final CatalyticActivity POSITIVE_INFINITY =
            new CatalyticActivity(Double.POSITIVE_INFINITY, CatalyticActivityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final CatalyticActivity NEGATIVE_INFINITY =
            new CatalyticActivity(Double.NEGATIVE_INFINITY, CatalyticActivityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final CatalyticActivity POS_MAXVALUE = new CatalyticActivity(Double.MAX_VALUE, CatalyticActivityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final CatalyticActivity NEG_MAXVALUE = new CatalyticActivity(-Double.MAX_VALUE, CatalyticActivityUnit.SI);

    /**
     * Construct CatalyticActivity scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public CatalyticActivity(final double value, final CatalyticActivityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct CatalyticActivity scalar.
     * @param value Scalar from which to construct this instance
     */
    public CatalyticActivity(final CatalyticActivity value)
    {
        super(value);
    }

    @Override
    public final CatalyticActivity instantiateRel(final double value, final CatalyticActivityUnit unit)
    {
        return new CatalyticActivity(value, unit);
    }

    /**
     * Construct CatalyticActivity scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final CatalyticActivity ofSI(final double value)
    {
        return new CatalyticActivity(value, CatalyticActivityUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a CatalyticActivity at the given ratio between 0 and 1
     */
    public static CatalyticActivity interpolate(final CatalyticActivity zero, final CatalyticActivity one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new CatalyticActivity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static CatalyticActivity max(final CatalyticActivity r1, final CatalyticActivity r2)
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
    public static CatalyticActivity max(final CatalyticActivity r1, final CatalyticActivity r2, final CatalyticActivity... rn)
    {
        CatalyticActivity maxr = r1.gt(r2) ? r1 : r2;
        for (CatalyticActivity r : rn)
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
    public static CatalyticActivity min(final CatalyticActivity r1, final CatalyticActivity r2)
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
    public static CatalyticActivity min(final CatalyticActivity r1, final CatalyticActivity r2, final CatalyticActivity... rn)
    {
        CatalyticActivity minr = r1.lt(r2) ? r1 : r2;
        for (CatalyticActivity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a CatalyticActivity representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a CatalyticActivity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static CatalyticActivity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing CatalyticActivity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing CatalyticActivity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            CatalyticActivityUnit unit = CatalyticActivityUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity CatalyticActivity",
                    unitString);
            return new CatalyticActivity(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing CatalyticActivity from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a CatalyticActivity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static CatalyticActivity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing CatalyticActivity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing CatalyticActivity: empty unitString");
        CatalyticActivityUnit unit = CatalyticActivityUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing CatalyticActivity with unit %s", unitString);
        return new CatalyticActivity(value, unit);
    }

    /**
     * Calculate the division of CatalyticActivity and CatalyticActivity, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of CatalyticActivity and CatalyticActivity
     */
    public final Dimensionless divide(final CatalyticActivity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of CatalyticActivity and Duration, which results in a AmountOfSubstance scalar.
     * @param v scalar
     * @return scalar as a multiplication of CatalyticActivity and Duration
     */
    public final AmountOfSubstance times(final Duration v)
    {
        return new AmountOfSubstance(this.si * v.si, AmountOfSubstanceUnit.SI);
    }

    /**
     * Calculate the division of CatalyticActivity and AmountOfSubstance, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of CatalyticActivity and AmountOfSubstance
     */
    public final Frequency divide(final AmountOfSubstance v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of CatalyticActivity and Frequency, which results in a AmountOfSubstance scalar.
     * @param v scalar
     * @return scalar as a division of CatalyticActivity and Frequency
     */
    public final AmountOfSubstance divide(final Frequency v)
    {
        return new AmountOfSubstance(this.si / v.si, AmountOfSubstanceUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type CatalyticActivity.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of CatalyticActivity
     */
    public static CatalyticActivity multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(CatalyticActivityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type CatalyticActivity",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new CatalyticActivity(scalar1.si * scalar2.si, CatalyticActivityUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type CatalyticActivity.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of CatalyticActivity
     */
    public static CatalyticActivity divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(CatalyticActivityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type CatalyticActivity",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new CatalyticActivity(scalar1.si / scalar2.si, CatalyticActivityUnit.SI);
    }

}
