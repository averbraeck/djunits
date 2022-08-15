package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the LuminousIntensity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class LuminousIntensity extends AbstractDoubleScalarRel<LuminousIntensityUnit, LuminousIntensity>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final LuminousIntensity ZERO = new LuminousIntensity(0.0, LuminousIntensityUnit.SI);

    /** Constant with value one. */
    public static final LuminousIntensity ONE = new LuminousIntensity(1.0, LuminousIntensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LuminousIntensity NaN = new LuminousIntensity(Double.NaN, LuminousIntensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LuminousIntensity POSITIVE_INFINITY =
            new LuminousIntensity(Double.POSITIVE_INFINITY, LuminousIntensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LuminousIntensity NEGATIVE_INFINITY =
            new LuminousIntensity(Double.NEGATIVE_INFINITY, LuminousIntensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final LuminousIntensity POS_MAXVALUE = new LuminousIntensity(Double.MAX_VALUE, LuminousIntensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final LuminousIntensity NEG_MAXVALUE = new LuminousIntensity(-Double.MAX_VALUE, LuminousIntensityUnit.SI);

    /**
     * Construct LuminousIntensity scalar.
     * @param value double; the double value
     * @param unit LuminousIntensityUnit; unit for the double value
     */
    public LuminousIntensity(final double value, final LuminousIntensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct LuminousIntensity scalar.
     * @param value LuminousIntensity; Scalar from which to construct this instance
     */
    public LuminousIntensity(final LuminousIntensity value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final LuminousIntensity instantiateRel(final double value, final LuminousIntensityUnit unit)
    {
        return new LuminousIntensity(value, unit);
    }

    /**
     * Construct LuminousIntensity scalar.
     * @param value double; the double value in SI units
     * @return LuminousIntensity; the new scalar with the SI value
     */
    public static final LuminousIntensity instantiateSI(final double value)
    {
        return new LuminousIntensity(value, LuminousIntensityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero LuminousIntensity; the low value
     * @param one LuminousIntensity; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return LuminousIntensity; a Scalar at the ratio between
     */
    public static LuminousIntensity interpolate(final LuminousIntensity zero, final LuminousIntensity one, final double ratio)
    {
        return new LuminousIntensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 LuminousIntensity; the first scalar
     * @param r2 LuminousIntensity; the second scalar
     * @return LuminousIntensity; the maximum value of two relative scalars
     */
    public static LuminousIntensity max(final LuminousIntensity r1, final LuminousIntensity r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 LuminousIntensity; the first scalar
     * @param r2 LuminousIntensity; the second scalar
     * @param rn LuminousIntensity...; the other scalars
     * @return LuminousIntensity; the maximum value of more than two relative scalars
     */
    public static LuminousIntensity max(final LuminousIntensity r1, final LuminousIntensity r2, final LuminousIntensity... rn)
    {
        LuminousIntensity maxr = r1.gt(r2) ? r1 : r2;
        for (LuminousIntensity r : rn)
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
     * @param r1 LuminousIntensity; the first scalar
     * @param r2 LuminousIntensity; the second scalar
     * @return LuminousIntensity; the minimum value of two relative scalars
     */
    public static LuminousIntensity min(final LuminousIntensity r1, final LuminousIntensity r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 LuminousIntensity; the first scalar
     * @param r2 LuminousIntensity; the second scalar
     * @param rn LuminousIntensity...; the other scalars
     * @return LuminousIntensity; the minimum value of more than two relative scalars
     */
    public static LuminousIntensity min(final LuminousIntensity r1, final LuminousIntensity r2, final LuminousIntensity... rn)
    {
        LuminousIntensity minr = r1.lt(r2) ? r1 : r2;
        for (LuminousIntensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a LuminousIntensity representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a LuminousIntensity
     * @return LuminousIntensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LuminousIntensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing LuminousIntensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing LuminousIntensity: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            LuminousIntensityUnit unit = LuminousIntensityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new LuminousIntensity(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing LuminousIntensity from " + text);
    }

    /**
     * Returns a LuminousIntensity based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return LuminousIntensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LuminousIntensity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing LuminousIntensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing LuminousIntensity: empty unitString");
        LuminousIntensityUnit unit = LuminousIntensityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new LuminousIntensity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing LuminousIntensity with unit " + unitString);
    }

    /**
     * Calculate the division of LuminousIntensity and LuminousIntensity, which results in a Dimensionless scalar.
     * @param v LuminousIntensity; scalar
     * @return Dimensionless; scalar as a division of LuminousIntensity and LuminousIntensity
     */
    public final Dimensionless divide(final LuminousIntensity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of LuminousIntensity and SolidAngle, which results in a LuminousFlux scalar.
     * @param v LuminousIntensity; scalar
     * @return LuminousFlux; scalar as a multiplication of LuminousIntensity and SolidAngle
     */
    public final LuminousFlux times(final SolidAngle v)
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
