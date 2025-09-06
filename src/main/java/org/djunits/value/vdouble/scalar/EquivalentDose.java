package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.EquivalentDoseUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the EquivalentDose DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class EquivalentDose extends DoubleScalarRel<EquivalentDoseUnit, EquivalentDose>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final EquivalentDose ZERO = new EquivalentDose(0.0, EquivalentDoseUnit.SI);

    /** Constant with value one. */
    public static final EquivalentDose ONE = new EquivalentDose(1.0, EquivalentDoseUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final EquivalentDose NaN = new EquivalentDose(Double.NaN, EquivalentDoseUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final EquivalentDose POSITIVE_INFINITY = new EquivalentDose(Double.POSITIVE_INFINITY, EquivalentDoseUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final EquivalentDose NEGATIVE_INFINITY = new EquivalentDose(Double.NEGATIVE_INFINITY, EquivalentDoseUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final EquivalentDose POS_MAXVALUE = new EquivalentDose(Double.MAX_VALUE, EquivalentDoseUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final EquivalentDose NEG_MAXVALUE = new EquivalentDose(-Double.MAX_VALUE, EquivalentDoseUnit.SI);

    /**
     * Construct EquivalentDose scalar.
     * @param value the double value
     * @param unit unit for the double value
     */
    public EquivalentDose(final double value, final EquivalentDoseUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct EquivalentDose scalar.
     * @param value Scalar from which to construct this instance
     */
    public EquivalentDose(final EquivalentDose value)
    {
        super(value);
    }

    @Override
    public final EquivalentDose instantiateRel(final double value, final EquivalentDoseUnit unit)
    {
        return new EquivalentDose(value, unit);
    }

    /**
     * Construct EquivalentDose scalar.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final EquivalentDose instantiateSI(final double value)
    {
        return new EquivalentDose(value, EquivalentDoseUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static EquivalentDose interpolate(final EquivalentDose zero, final EquivalentDose one, final double ratio)
    {
        return new EquivalentDose(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static EquivalentDose max(final EquivalentDose r1, final EquivalentDose r2)
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
    public static EquivalentDose max(final EquivalentDose r1, final EquivalentDose r2, final EquivalentDose... rn)
    {
        EquivalentDose maxr = r1.gt(r2) ? r1 : r2;
        for (EquivalentDose r : rn)
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
    public static EquivalentDose min(final EquivalentDose r1, final EquivalentDose r2)
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
    public static EquivalentDose min(final EquivalentDose r1, final EquivalentDose r2, final EquivalentDose... rn)
    {
        EquivalentDose minr = r1.lt(r2) ? r1 : r2;
        for (EquivalentDose r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a EquivalentDose representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a EquivalentDose
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static EquivalentDose valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing EquivalentDose: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing EquivalentDose: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            EquivalentDoseUnit unit = EquivalentDoseUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity EquivalentDose",
                    unitString);
            return new EquivalentDose(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing EquivalentDose from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a EquivalentDose based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static EquivalentDose of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing EquivalentDose: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing EquivalentDose: empty unitString");
        EquivalentDoseUnit unit = EquivalentDoseUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing EquivalentDose with unit %s", unitString);
        return new EquivalentDose(value, unit);
    }

    /**
     * Calculate the division of EquivalentDose and EquivalentDose, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of EquivalentDose and EquivalentDose
     */
    public final Dimensionless divide(final EquivalentDose v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
