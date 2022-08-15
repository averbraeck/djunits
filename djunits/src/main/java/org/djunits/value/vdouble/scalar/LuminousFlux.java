package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.IlluminanceUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.unit.SolidAngleUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the LuminousFlux DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class LuminousFlux extends AbstractDoubleScalarRel<LuminousFluxUnit, LuminousFlux>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final LuminousFlux ZERO = new LuminousFlux(0.0, LuminousFluxUnit.SI);

    /** Constant with value one. */
    public static final LuminousFlux ONE = new LuminousFlux(1.0, LuminousFluxUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LuminousFlux NaN = new LuminousFlux(Double.NaN, LuminousFluxUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LuminousFlux POSITIVE_INFINITY = new LuminousFlux(Double.POSITIVE_INFINITY, LuminousFluxUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LuminousFlux NEGATIVE_INFINITY = new LuminousFlux(Double.NEGATIVE_INFINITY, LuminousFluxUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final LuminousFlux POS_MAXVALUE = new LuminousFlux(Double.MAX_VALUE, LuminousFluxUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final LuminousFlux NEG_MAXVALUE = new LuminousFlux(-Double.MAX_VALUE, LuminousFluxUnit.SI);

    /**
     * Construct LuminousFlux scalar.
     * @param value double; the double value
     * @param unit LuminousFluxUnit; unit for the double value
     */
    public LuminousFlux(final double value, final LuminousFluxUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct LuminousFlux scalar.
     * @param value LuminousFlux; Scalar from which to construct this instance
     */
    public LuminousFlux(final LuminousFlux value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final LuminousFlux instantiateRel(final double value, final LuminousFluxUnit unit)
    {
        return new LuminousFlux(value, unit);
    }

    /**
     * Construct LuminousFlux scalar.
     * @param value double; the double value in SI units
     * @return LuminousFlux; the new scalar with the SI value
     */
    public static final LuminousFlux instantiateSI(final double value)
    {
        return new LuminousFlux(value, LuminousFluxUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero LuminousFlux; the low value
     * @param one LuminousFlux; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return LuminousFlux; a Scalar at the ratio between
     */
    public static LuminousFlux interpolate(final LuminousFlux zero, final LuminousFlux one, final double ratio)
    {
        return new LuminousFlux(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 LuminousFlux; the first scalar
     * @param r2 LuminousFlux; the second scalar
     * @return LuminousFlux; the maximum value of two relative scalars
     */
    public static LuminousFlux max(final LuminousFlux r1, final LuminousFlux r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 LuminousFlux; the first scalar
     * @param r2 LuminousFlux; the second scalar
     * @param rn LuminousFlux...; the other scalars
     * @return LuminousFlux; the maximum value of more than two relative scalars
     */
    public static LuminousFlux max(final LuminousFlux r1, final LuminousFlux r2, final LuminousFlux... rn)
    {
        LuminousFlux maxr = r1.gt(r2) ? r1 : r2;
        for (LuminousFlux r : rn)
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
     * @param r1 LuminousFlux; the first scalar
     * @param r2 LuminousFlux; the second scalar
     * @return LuminousFlux; the minimum value of two relative scalars
     */
    public static LuminousFlux min(final LuminousFlux r1, final LuminousFlux r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 LuminousFlux; the first scalar
     * @param r2 LuminousFlux; the second scalar
     * @param rn LuminousFlux...; the other scalars
     * @return LuminousFlux; the minimum value of more than two relative scalars
     */
    public static LuminousFlux min(final LuminousFlux r1, final LuminousFlux r2, final LuminousFlux... rn)
    {
        LuminousFlux minr = r1.lt(r2) ? r1 : r2;
        for (LuminousFlux r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a LuminousFlux representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but
     * not required, between the value and the unit.
     * @param text String; the textual representation to parse into a LuminousFlux
     * @return LuminousFlux; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LuminousFlux valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing LuminousFlux: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing LuminousFlux: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            LuminousFluxUnit unit = LuminousFluxUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new LuminousFlux(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing LuminousFlux from " + text);
    }

    /**
     * Returns a LuminousFlux based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return LuminousFlux; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LuminousFlux of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing LuminousFlux: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing LuminousFlux: empty unitString");
        LuminousFluxUnit unit = LuminousFluxUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new LuminousFlux(value, unit);
        }
        throw new IllegalArgumentException("Error parsing LuminousFlux with unit " + unitString);
    }

    /**
     * Calculate the division of LuminousFlux and LuminousFlux, which results in a Dimensionless scalar.
     * @param v LuminousFlux; scalar
     * @return Dimensionless; scalar as a division of LuminousFlux and LuminousFlux
     */
    public final Dimensionless divide(final LuminousFlux v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and Area, which results in a Illuminance scalar.
     * @param v LuminousFlux; scalar
     * @return Illuminance; scalar as a division of LuminousFlux and Area
     */
    public final Illuminance divide(final Area v)
    {
        return new Illuminance(this.si / v.si, IlluminanceUnit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and Illuminance, which results in a Area scalar.
     * @param v LuminousFlux; scalar
     * @return Area; scalar as a division of LuminousFlux and Illuminance
     */
    public final Area divide(final Illuminance v)
    {
        return new Area(this.si / v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and LuminousIntensity, which results in a SolidAngle scalar.
     * @param v LuminousFlux; scalar
     * @return SolidAngle; scalar as a division of LuminousFlux and LuminousIntensity
     */
    public final SolidAngle divide(final LuminousIntensity v)
    {
        return new SolidAngle(this.si / v.si, SolidAngleUnit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and SolidAngle, which results in a LuminousIntensity scalar.
     * @param v LuminousFlux; scalar
     * @return LuminousIntensity; scalar as a division of LuminousFlux and SolidAngle
     */
    public final LuminousIntensity divide(final SolidAngle v)
    {
        return new LuminousIntensity(this.si / v.si, LuminousIntensityUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
