package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AmountOfSubstanceUnit;
import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the AmountOfSubstance DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AmountOfSubstance extends AbstractDoubleScalarRel<AmountOfSubstanceUnit, AmountOfSubstance>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final AmountOfSubstance ZERO = new AmountOfSubstance(0.0, AmountOfSubstanceUnit.SI);

    /** Constant with value one. */
    public static final AmountOfSubstance ONE = new AmountOfSubstance(1.0, AmountOfSubstanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final AmountOfSubstance NaN = new AmountOfSubstance(Double.NaN, AmountOfSubstanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final AmountOfSubstance POSITIVE_INFINITY =
            new AmountOfSubstance(Double.POSITIVE_INFINITY, AmountOfSubstanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final AmountOfSubstance NEGATIVE_INFINITY =
            new AmountOfSubstance(Double.NEGATIVE_INFINITY, AmountOfSubstanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final AmountOfSubstance POS_MAXVALUE = new AmountOfSubstance(Double.MAX_VALUE, AmountOfSubstanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final AmountOfSubstance NEG_MAXVALUE = new AmountOfSubstance(-Double.MAX_VALUE, AmountOfSubstanceUnit.SI);

    /**
     * Construct AmountOfSubstance scalar.
     * @param value double; the double value
     * @param unit AmountOfSubstanceUnit; unit for the double value
     */
    public AmountOfSubstance(final double value, final AmountOfSubstanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct AmountOfSubstance scalar.
     * @param value AmountOfSubstance; Scalar from which to construct this instance
     */
    public AmountOfSubstance(final AmountOfSubstance value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final AmountOfSubstance instantiateRel(final double value, final AmountOfSubstanceUnit unit)
    {
        return new AmountOfSubstance(value, unit);
    }

    /**
     * Construct AmountOfSubstance scalar.
     * @param value double; the double value in SI units
     * @return AmountOfSubstance; the new scalar with the SI value
     */
    public static final AmountOfSubstance instantiateSI(final double value)
    {
        return new AmountOfSubstance(value, AmountOfSubstanceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero AmountOfSubstance; the low value
     * @param one AmountOfSubstance; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return AmountOfSubstance; a Scalar at the ratio between
     */
    public static AmountOfSubstance interpolate(final AmountOfSubstance zero, final AmountOfSubstance one, final double ratio)
    {
        return new AmountOfSubstance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 AmountOfSubstance; the first scalar
     * @param r2 AmountOfSubstance; the second scalar
     * @return AmountOfSubstance; the maximum value of two relative scalars
     */
    public static AmountOfSubstance max(final AmountOfSubstance r1, final AmountOfSubstance r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 AmountOfSubstance; the first scalar
     * @param r2 AmountOfSubstance; the second scalar
     * @param rn AmountOfSubstance...; the other scalars
     * @return AmountOfSubstance; the maximum value of more than two relative scalars
     */
    public static AmountOfSubstance max(final AmountOfSubstance r1, final AmountOfSubstance r2, final AmountOfSubstance... rn)
    {
        AmountOfSubstance maxr = r1.gt(r2) ? r1 : r2;
        for (AmountOfSubstance r : rn)
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
     * @param r1 AmountOfSubstance; the first scalar
     * @param r2 AmountOfSubstance; the second scalar
     * @return AmountOfSubstance; the minimum value of two relative scalars
     */
    public static AmountOfSubstance min(final AmountOfSubstance r1, final AmountOfSubstance r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 AmountOfSubstance; the first scalar
     * @param r2 AmountOfSubstance; the second scalar
     * @param rn AmountOfSubstance...; the other scalars
     * @return AmountOfSubstance; the minimum value of more than two relative scalars
     */
    public static AmountOfSubstance min(final AmountOfSubstance r1, final AmountOfSubstance r2, final AmountOfSubstance... rn)
    {
        AmountOfSubstance minr = r1.lt(r2) ? r1 : r2;
        for (AmountOfSubstance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a AmountOfSubstance representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a AmountOfSubstance
     * @return AmountOfSubstance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static AmountOfSubstance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing AmountOfSubstance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing AmountOfSubstance: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AmountOfSubstanceUnit unit = AmountOfSubstanceUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new AmountOfSubstance(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing AmountOfSubstance from " + text);
    }

    /**
     * Returns a AmountOfSubstance based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return AmountOfSubstance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AmountOfSubstance of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing AmountOfSubstance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing AmountOfSubstance: empty unitString");
        AmountOfSubstanceUnit unit = AmountOfSubstanceUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new AmountOfSubstance(value, unit);
        }
        throw new IllegalArgumentException("Error parsing AmountOfSubstance with unit " + unitString);
    }

    /**
     * Calculate the division of AmountOfSubstance and AmountOfSubstance, which results in a Dimensionless scalar.
     * @param v AmountOfSubstance; scalar
     * @return Dimensionless; scalar as a division of AmountOfSubstance and AmountOfSubstance
     */
    public final Dimensionless divide(final AmountOfSubstance v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of AmountOfSubstance and CatalyticActivity, which results in a Duration scalar.
     * @param v AmountOfSubstance; scalar
     * @return Duration; scalar as a division of AmountOfSubstance and CatalyticActivity
     */
    public final Duration divide(final CatalyticActivity v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the division of AmountOfSubstance and Duration, which results in a CatalyticActivity scalar.
     * @param v AmountOfSubstance; scalar
     * @return CatalyticActivity; scalar as a division of AmountOfSubstance and Duration
     */
    public final CatalyticActivity divide(final Duration v)
    {
        return new CatalyticActivity(this.si / v.si, CatalyticActivityUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
