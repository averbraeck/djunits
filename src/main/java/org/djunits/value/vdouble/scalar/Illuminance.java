package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.IlluminanceUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Illuminance DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Illuminance extends AbstractDoubleScalarRel<IlluminanceUnit, Illuminance>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Illuminance ZERO = new Illuminance(0.0, IlluminanceUnit.SI);

    /** Constant with value one. */
    public static final Illuminance ONE = new Illuminance(1.0, IlluminanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Illuminance NaN = new Illuminance(Double.NaN, IlluminanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Illuminance POSITIVE_INFINITY = new Illuminance(Double.POSITIVE_INFINITY, IlluminanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Illuminance NEGATIVE_INFINITY = new Illuminance(Double.NEGATIVE_INFINITY, IlluminanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Illuminance POS_MAXVALUE = new Illuminance(Double.MAX_VALUE, IlluminanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Illuminance NEG_MAXVALUE = new Illuminance(-Double.MAX_VALUE, IlluminanceUnit.SI);

    /**
     * Construct Illuminance scalar.
     * @param value double; the double value
     * @param unit IlluminanceUnit; unit for the double value
     */
    public Illuminance(final double value, final IlluminanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Illuminance scalar.
     * @param value Illuminance; Scalar from which to construct this instance
     */
    public Illuminance(final Illuminance value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Illuminance instantiateRel(final double value, final IlluminanceUnit unit)
    {
        return new Illuminance(value, unit);
    }

    /**
     * Construct Illuminance scalar.
     * @param value double; the double value in SI units
     * @return Illuminance; the new scalar with the SI value
     */
    public static final Illuminance instantiateSI(final double value)
    {
        return new Illuminance(value, IlluminanceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Illuminance; the low value
     * @param one Illuminance; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Illuminance; a Scalar at the ratio between
     */
    public static Illuminance interpolate(final Illuminance zero, final Illuminance one, final double ratio)
    {
        return new Illuminance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Illuminance; the first scalar
     * @param r2 Illuminance; the second scalar
     * @return Illuminance; the maximum value of two relative scalars
     */
    public static Illuminance max(final Illuminance r1, final Illuminance r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Illuminance; the first scalar
     * @param r2 Illuminance; the second scalar
     * @param rn Illuminance...; the other scalars
     * @return Illuminance; the maximum value of more than two relative scalars
     */
    public static Illuminance max(final Illuminance r1, final Illuminance r2, final Illuminance... rn)
    {
        Illuminance maxr = r1.gt(r2) ? r1 : r2;
        for (Illuminance r : rn)
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
     * @param r1 Illuminance; the first scalar
     * @param r2 Illuminance; the second scalar
     * @return Illuminance; the minimum value of two relative scalars
     */
    public static Illuminance min(final Illuminance r1, final Illuminance r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Illuminance; the first scalar
     * @param r2 Illuminance; the second scalar
     * @param rn Illuminance...; the other scalars
     * @return Illuminance; the minimum value of more than two relative scalars
     */
    public static Illuminance min(final Illuminance r1, final Illuminance r2, final Illuminance... rn)
    {
        Illuminance minr = r1.lt(r2) ? r1 : r2;
        for (Illuminance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Illuminance representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a Illuminance
     * @return Illuminance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Illuminance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Illuminance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Illuminance: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            IlluminanceUnit unit = IlluminanceUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Illuminance(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Illuminance from " + text);
    }

    /**
     * Returns a Illuminance based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Illuminance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Illuminance of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Illuminance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Illuminance: empty unitString");
        IlluminanceUnit unit = IlluminanceUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Illuminance(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Illuminance with unit " + unitString);
    }

    /**
     * Calculate the division of Illuminance and Illuminance, which results in a Dimensionless scalar.
     * @param v Illuminance; scalar
     * @return Dimensionless; scalar as a division of Illuminance and Illuminance
     */
    public final Dimensionless divide(final Illuminance v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Illuminance and Area, which results in a LuminousFlux scalar.
     * @param v Illuminance; scalar
     * @return LuminousFlux; scalar as a multiplication of Illuminance and Area
     */
    public final LuminousFlux times(final Area v)
    {
        return new LuminousFlux(this.si * v.si, LuminousFluxUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
