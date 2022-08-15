package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.EquivalentDoseUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the EquivalentDose DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class EquivalentDose extends AbstractDoubleScalarRel<EquivalentDoseUnit, EquivalentDose>
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
     * @param value double; the double value
     * @param unit EquivalentDoseUnit; unit for the double value
     */
    public EquivalentDose(final double value, final EquivalentDoseUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct EquivalentDose scalar.
     * @param value EquivalentDose; Scalar from which to construct this instance
     */
    public EquivalentDose(final EquivalentDose value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final EquivalentDose instantiateRel(final double value, final EquivalentDoseUnit unit)
    {
        return new EquivalentDose(value, unit);
    }

    /**
     * Construct EquivalentDose scalar.
     * @param value double; the double value in SI units
     * @return EquivalentDose; the new scalar with the SI value
     */
    public static final EquivalentDose instantiateSI(final double value)
    {
        return new EquivalentDose(value, EquivalentDoseUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero EquivalentDose; the low value
     * @param one EquivalentDose; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return EquivalentDose; a Scalar at the ratio between
     */
    public static EquivalentDose interpolate(final EquivalentDose zero, final EquivalentDose one, final double ratio)
    {
        return new EquivalentDose(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 EquivalentDose; the first scalar
     * @param r2 EquivalentDose; the second scalar
     * @return EquivalentDose; the maximum value of two relative scalars
     */
    public static EquivalentDose max(final EquivalentDose r1, final EquivalentDose r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 EquivalentDose; the first scalar
     * @param r2 EquivalentDose; the second scalar
     * @param rn EquivalentDose...; the other scalars
     * @return EquivalentDose; the maximum value of more than two relative scalars
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
     * @param r1 EquivalentDose; the first scalar
     * @param r2 EquivalentDose; the second scalar
     * @return EquivalentDose; the minimum value of two relative scalars
     */
    public static EquivalentDose min(final EquivalentDose r1, final EquivalentDose r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 EquivalentDose; the first scalar
     * @param r2 EquivalentDose; the second scalar
     * @param rn EquivalentDose...; the other scalars
     * @return EquivalentDose; the minimum value of more than two relative scalars
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
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a EquivalentDose
     * @return EquivalentDose; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static EquivalentDose valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing EquivalentDose: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing EquivalentDose: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            EquivalentDoseUnit unit = EquivalentDoseUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new EquivalentDose(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing EquivalentDose from " + text);
    }

    /**
     * Returns a EquivalentDose based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return EquivalentDose; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static EquivalentDose of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing EquivalentDose: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing EquivalentDose: empty unitString");
        EquivalentDoseUnit unit = EquivalentDoseUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new EquivalentDose(value, unit);
        }
        throw new IllegalArgumentException("Error parsing EquivalentDose with unit " + unitString);
    }

    /**
     * Calculate the division of EquivalentDose and EquivalentDose, which results in a Dimensionless scalar.
     * @param v EquivalentDose; scalar
     * @return Dimensionless; scalar as a division of EquivalentDose and EquivalentDose
     */
    public final Dimensionless divide(final EquivalentDose v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
