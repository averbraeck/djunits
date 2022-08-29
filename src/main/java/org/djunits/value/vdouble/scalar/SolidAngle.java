package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import jakarta.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the SolidAngle DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class SolidAngle extends AbstractDoubleScalarRel<SolidAngleUnit, SolidAngle>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final SolidAngle ZERO = new SolidAngle(0.0, SolidAngleUnit.SI);

    /** Constant with value one. */
    public static final SolidAngle ONE = new SolidAngle(1.0, SolidAngleUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final SolidAngle NaN = new SolidAngle(Double.NaN, SolidAngleUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final SolidAngle POSITIVE_INFINITY = new SolidAngle(Double.POSITIVE_INFINITY, SolidAngleUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final SolidAngle NEGATIVE_INFINITY = new SolidAngle(Double.NEGATIVE_INFINITY, SolidAngleUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final SolidAngle POS_MAXVALUE = new SolidAngle(Double.MAX_VALUE, SolidAngleUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final SolidAngle NEG_MAXVALUE = new SolidAngle(-Double.MAX_VALUE, SolidAngleUnit.SI);

    /**
     * Construct SolidAngle scalar.
     * @param value double; the double value
     * @param unit SolidAngleUnit; unit for the double value
     */
    public SolidAngle(final double value, final SolidAngleUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct SolidAngle scalar.
     * @param value SolidAngle; Scalar from which to construct this instance
     */
    public SolidAngle(final SolidAngle value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final SolidAngle instantiateRel(final double value, final SolidAngleUnit unit)
    {
        return new SolidAngle(value, unit);
    }

    /**
     * Construct SolidAngle scalar.
     * @param value double; the double value in SI units
     * @return SolidAngle; the new scalar with the SI value
     */
    public static final SolidAngle instantiateSI(final double value)
    {
        return new SolidAngle(value, SolidAngleUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero SolidAngle; the low value
     * @param one SolidAngle; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return SolidAngle; a Scalar at the ratio between
     */
    public static SolidAngle interpolate(final SolidAngle zero, final SolidAngle one, final double ratio)
    {
        return new SolidAngle(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 SolidAngle; the first scalar
     * @param r2 SolidAngle; the second scalar
     * @return SolidAngle; the maximum value of two relative scalars
     */
    public static SolidAngle max(final SolidAngle r1, final SolidAngle r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 SolidAngle; the first scalar
     * @param r2 SolidAngle; the second scalar
     * @param rn SolidAngle...; the other scalars
     * @return SolidAngle; the maximum value of more than two relative scalars
     */
    public static SolidAngle max(final SolidAngle r1, final SolidAngle r2, final SolidAngle... rn)
    {
        SolidAngle maxr = r1.gt(r2) ? r1 : r2;
        for (SolidAngle r : rn)
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
     * @param r1 SolidAngle; the first scalar
     * @param r2 SolidAngle; the second scalar
     * @return SolidAngle; the minimum value of two relative scalars
     */
    public static SolidAngle min(final SolidAngle r1, final SolidAngle r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 SolidAngle; the first scalar
     * @param r2 SolidAngle; the second scalar
     * @param rn SolidAngle...; the other scalars
     * @return SolidAngle; the minimum value of more than two relative scalars
     */
    public static SolidAngle min(final SolidAngle r1, final SolidAngle r2, final SolidAngle... rn)
    {
        SolidAngle minr = r1.lt(r2) ? r1 : r2;
        for (SolidAngle r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a SolidAngle representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a SolidAngle
     * @return SolidAngle; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static SolidAngle valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing SolidAngle: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing SolidAngle: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            SolidAngleUnit unit = SolidAngleUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new SolidAngle(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing SolidAngle from " + text);
    }

    /**
     * Returns a SolidAngle based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return SolidAngle; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SolidAngle of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing SolidAngle: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing SolidAngle: empty unitString");
        SolidAngleUnit unit = SolidAngleUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new SolidAngle(value, unit);
        }
        throw new IllegalArgumentException("Error parsing SolidAngle with unit " + unitString);
    }

    /**
     * Calculate the division of SolidAngle and SolidAngle, which results in a Dimensionless scalar.
     * @param v SolidAngle; scalar
     * @return Dimensionless; scalar as a division of SolidAngle and SolidAngle
     */
    public final Dimensionless divide(final SolidAngle v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of SolidAngle and LuminousIntensity, which results in a LuminousFlux scalar.
     * @param v SolidAngle; scalar
     * @return LuminousFlux; scalar as a multiplication of SolidAngle and LuminousIntensity
     */
    public final LuminousFlux times(final LuminousIntensity v)
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
