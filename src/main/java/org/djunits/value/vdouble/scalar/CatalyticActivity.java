package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AmountOfSubstanceUnit;
import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the CatalyticActivity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class CatalyticActivity extends AbstractDoubleScalarRel<CatalyticActivityUnit, CatalyticActivity>
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
     * Construct CatalyticActivity scalar.
     * @param value double; the double value
     * @param unit CatalyticActivityUnit; unit for the double value
     */
    public CatalyticActivity(final double value, final CatalyticActivityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct CatalyticActivity scalar.
     * @param value CatalyticActivity; Scalar from which to construct this instance
     */
    public CatalyticActivity(final CatalyticActivity value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final CatalyticActivity instantiateRel(final double value, final CatalyticActivityUnit unit)
    {
        return new CatalyticActivity(value, unit);
    }

    /**
     * Construct CatalyticActivity scalar.
     * @param value double; the double value in SI units
     * @return CatalyticActivity; the new scalar with the SI value
     */
    public static final CatalyticActivity instantiateSI(final double value)
    {
        return new CatalyticActivity(value, CatalyticActivityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero CatalyticActivity; the low value
     * @param one CatalyticActivity; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return CatalyticActivity; a Scalar at the ratio between
     */
    public static CatalyticActivity interpolate(final CatalyticActivity zero, final CatalyticActivity one, final double ratio)
    {
        return new CatalyticActivity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 CatalyticActivity; the first scalar
     * @param r2 CatalyticActivity; the second scalar
     * @return CatalyticActivity; the maximum value of two relative scalars
     */
    public static CatalyticActivity max(final CatalyticActivity r1, final CatalyticActivity r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 CatalyticActivity; the first scalar
     * @param r2 CatalyticActivity; the second scalar
     * @param rn CatalyticActivity...; the other scalars
     * @return CatalyticActivity; the maximum value of more than two relative scalars
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
     * @param r1 CatalyticActivity; the first scalar
     * @param r2 CatalyticActivity; the second scalar
     * @return CatalyticActivity; the minimum value of two relative scalars
     */
    public static CatalyticActivity min(final CatalyticActivity r1, final CatalyticActivity r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 CatalyticActivity; the first scalar
     * @param r2 CatalyticActivity; the second scalar
     * @param rn CatalyticActivity...; the other scalars
     * @return CatalyticActivity; the minimum value of more than two relative scalars
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
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a CatalyticActivity
     * @return CatalyticActivity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static CatalyticActivity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing CatalyticActivity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing CatalyticActivity: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            CatalyticActivityUnit unit = CatalyticActivityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new CatalyticActivity(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing CatalyticActivity from " + text);
    }

    /**
     * Returns a CatalyticActivity based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return CatalyticActivity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static CatalyticActivity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing CatalyticActivity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing CatalyticActivity: empty unitString");
        CatalyticActivityUnit unit = CatalyticActivityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new CatalyticActivity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing CatalyticActivity with unit " + unitString);
    }

    /**
     * Calculate the division of CatalyticActivity and CatalyticActivity, which results in a Dimensionless scalar.
     * @param v CatalyticActivity; scalar
     * @return Dimensionless; scalar as a division of CatalyticActivity and CatalyticActivity
     */
    public final Dimensionless divide(final CatalyticActivity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of CatalyticActivity and Duration, which results in a AmountOfSubstance scalar.
     * @param v CatalyticActivity; scalar
     * @return AmountOfSubstance; scalar as a multiplication of CatalyticActivity and Duration
     */
    public final AmountOfSubstance times(final Duration v)
    {
        return new AmountOfSubstance(this.si * v.si, AmountOfSubstanceUnit.SI);
    }

    /**
     * Calculate the division of CatalyticActivity and AmountOfSubstance, which results in a Frequency scalar.
     * @param v CatalyticActivity; scalar
     * @return Frequency; scalar as a division of CatalyticActivity and AmountOfSubstance
     */
    public final Frequency divide(final AmountOfSubstance v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of CatalyticActivity and Frequency, which results in a AmountOfSubstance scalar.
     * @param v CatalyticActivity; scalar
     * @return AmountOfSubstance; scalar as a division of CatalyticActivity and Frequency
     */
    public final AmountOfSubstance divide(final Frequency v)
    {
        return new AmountOfSubstance(this.si / v.si, AmountOfSubstanceUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
