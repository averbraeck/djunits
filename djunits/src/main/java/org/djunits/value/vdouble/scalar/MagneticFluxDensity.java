package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the MagneticFluxDensity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class MagneticFluxDensity extends AbstractDoubleScalarRel<MagneticFluxDensityUnit, MagneticFluxDensity>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final MagneticFluxDensity ZERO = new MagneticFluxDensity(0.0, MagneticFluxDensityUnit.SI);

    /** Constant with value one. */
    public static final MagneticFluxDensity ONE = new MagneticFluxDensity(1.0, MagneticFluxDensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final MagneticFluxDensity NaN = new MagneticFluxDensity(Double.NaN, MagneticFluxDensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final MagneticFluxDensity POSITIVE_INFINITY =
            new MagneticFluxDensity(Double.POSITIVE_INFINITY, MagneticFluxDensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final MagneticFluxDensity NEGATIVE_INFINITY =
            new MagneticFluxDensity(Double.NEGATIVE_INFINITY, MagneticFluxDensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final MagneticFluxDensity POS_MAXVALUE =
            new MagneticFluxDensity(Double.MAX_VALUE, MagneticFluxDensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final MagneticFluxDensity NEG_MAXVALUE =
            new MagneticFluxDensity(-Double.MAX_VALUE, MagneticFluxDensityUnit.SI);

    /**
     * Construct MagneticFluxDensity scalar.
     * @param value double; the double value
     * @param unit MagneticFluxDensityUnit; unit for the double value
     */
    public MagneticFluxDensity(final double value, final MagneticFluxDensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct MagneticFluxDensity scalar.
     * @param value MagneticFluxDensity; Scalar from which to construct this instance
     */
    public MagneticFluxDensity(final MagneticFluxDensity value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final MagneticFluxDensity instantiateRel(final double value, final MagneticFluxDensityUnit unit)
    {
        return new MagneticFluxDensity(value, unit);
    }

    /**
     * Construct MagneticFluxDensity scalar.
     * @param value double; the double value in SI units
     * @return MagneticFluxDensity; the new scalar with the SI value
     */
    public static final MagneticFluxDensity instantiateSI(final double value)
    {
        return new MagneticFluxDensity(value, MagneticFluxDensityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero MagneticFluxDensity; the low value
     * @param one MagneticFluxDensity; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return MagneticFluxDensity; a Scalar at the ratio between
     */
    public static MagneticFluxDensity interpolate(final MagneticFluxDensity zero, final MagneticFluxDensity one,
            final double ratio)
    {
        return new MagneticFluxDensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 MagneticFluxDensity; the first scalar
     * @param r2 MagneticFluxDensity; the second scalar
     * @return MagneticFluxDensity; the maximum value of two relative scalars
     */
    public static MagneticFluxDensity max(final MagneticFluxDensity r1, final MagneticFluxDensity r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 MagneticFluxDensity; the first scalar
     * @param r2 MagneticFluxDensity; the second scalar
     * @param rn MagneticFluxDensity...; the other scalars
     * @return MagneticFluxDensity; the maximum value of more than two relative scalars
     */
    public static MagneticFluxDensity max(final MagneticFluxDensity r1, final MagneticFluxDensity r2,
            final MagneticFluxDensity... rn)
    {
        MagneticFluxDensity maxr = r1.gt(r2) ? r1 : r2;
        for (MagneticFluxDensity r : rn)
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
     * @param r1 MagneticFluxDensity; the first scalar
     * @param r2 MagneticFluxDensity; the second scalar
     * @return MagneticFluxDensity; the minimum value of two relative scalars
     */
    public static MagneticFluxDensity min(final MagneticFluxDensity r1, final MagneticFluxDensity r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 MagneticFluxDensity; the first scalar
     * @param r2 MagneticFluxDensity; the second scalar
     * @param rn MagneticFluxDensity...; the other scalars
     * @return MagneticFluxDensity; the minimum value of more than two relative scalars
     */
    public static MagneticFluxDensity min(final MagneticFluxDensity r1, final MagneticFluxDensity r2,
            final MagneticFluxDensity... rn)
    {
        MagneticFluxDensity minr = r1.lt(r2) ? r1 : r2;
        for (MagneticFluxDensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a MagneticFluxDensity representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a MagneticFluxDensity
     * @return MagneticFluxDensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static MagneticFluxDensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing MagneticFluxDensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing MagneticFluxDensity: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            MagneticFluxDensityUnit unit = MagneticFluxDensityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new MagneticFluxDensity(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing MagneticFluxDensity from " + text);
    }

    /**
     * Returns a MagneticFluxDensity based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return MagneticFluxDensity; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static MagneticFluxDensity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing MagneticFluxDensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing MagneticFluxDensity: empty unitString");
        MagneticFluxDensityUnit unit = MagneticFluxDensityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new MagneticFluxDensity(value, unit);
        }
        throw new IllegalArgumentException("Error parsing MagneticFluxDensity with unit " + unitString);
    }

    /**
     * Calculate the division of MagneticFluxDensity and MagneticFluxDensity, which results in a Dimensionless scalar.
     * @param v MagneticFluxDensity; scalar
     * @return Dimensionless; scalar as a division of MagneticFluxDensity and MagneticFluxDensity
     */
    public final Dimensionless divide(final MagneticFluxDensity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of MagneticFluxDensity and Area, which results in a MagneticFlux scalar.
     * @param v MagneticFluxDensity; scalar
     * @return MagneticFlux; scalar as a multiplication of MagneticFluxDensity and Area
     */
    public final MagneticFlux times(final Area v)
    {
        return new MagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
