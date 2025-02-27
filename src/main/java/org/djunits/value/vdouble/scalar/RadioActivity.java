package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.RadioActivityUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the RadioActivity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class RadioActivity extends DoubleScalarRel<RadioActivityUnit, RadioActivity>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final RadioActivity ZERO = new RadioActivity(0.0, RadioActivityUnit.SI);

    /** Constant with value one. */
    public static final RadioActivity ONE = new RadioActivity(1.0, RadioActivityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final RadioActivity NaN = new RadioActivity(Double.NaN, RadioActivityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final RadioActivity POSITIVE_INFINITY = new RadioActivity(Double.POSITIVE_INFINITY, RadioActivityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final RadioActivity NEGATIVE_INFINITY = new RadioActivity(Double.NEGATIVE_INFINITY, RadioActivityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final RadioActivity POS_MAXVALUE = new RadioActivity(Double.MAX_VALUE, RadioActivityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final RadioActivity NEG_MAXVALUE = new RadioActivity(-Double.MAX_VALUE, RadioActivityUnit.SI);

    /**
     * Construct RadioActivity scalar.
     * @param value the double value
     * @param unit unit for the double value
     */
    public RadioActivity(final double value, final RadioActivityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct RadioActivity scalar.
     * @param value Scalar from which to construct this instance
     */
    public RadioActivity(final RadioActivity value)
    {
        super(value);
    }

    @Override
    public final RadioActivity instantiateRel(final double value, final RadioActivityUnit unit)
    {
        return new RadioActivity(value, unit);
    }

    /**
     * Construct RadioActivity scalar.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final RadioActivity instantiateSI(final double value)
    {
        return new RadioActivity(value, RadioActivityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static RadioActivity interpolate(final RadioActivity zero, final RadioActivity one, final double ratio)
    {
        return new RadioActivity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static RadioActivity max(final RadioActivity r1, final RadioActivity r2)
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
    public static RadioActivity max(final RadioActivity r1, final RadioActivity r2, final RadioActivity... rn)
    {
        RadioActivity maxr = r1.gt(r2) ? r1 : r2;
        for (RadioActivity r : rn)
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
    public static RadioActivity min(final RadioActivity r1, final RadioActivity r2)
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
    public static RadioActivity min(final RadioActivity r1, final RadioActivity r2, final RadioActivity... rn)
    {
        RadioActivity minr = r1.lt(r2) ? r1 : r2;
        for (RadioActivity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a RadioActivity representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a RadioActivity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static RadioActivity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing RadioActivity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing RadioActivity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            RadioActivityUnit unit = RadioActivityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit == null)
                throw new IllegalArgumentException("Unit " + unitString + " not found");
            return new RadioActivity(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing RadioActivity from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a RadioActivity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static RadioActivity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing RadioActivity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing RadioActivity: empty unitString");
        RadioActivityUnit unit = RadioActivityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new RadioActivity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing RadioActivity with unit " + unitString);
    }

    /**
     * Calculate the division of RadioActivity and RadioActivity, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of RadioActivity and RadioActivity
     */
    public final Dimensionless divide(final RadioActivity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
