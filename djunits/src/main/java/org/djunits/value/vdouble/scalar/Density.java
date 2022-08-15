package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.MassUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Density DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Density extends AbstractDoubleScalarRel<DensityUnit, Density>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Density ZERO = new Density(0.0, DensityUnit.SI);

    /** Constant with value one. */
    public static final Density ONE = new Density(1.0, DensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Density NaN = new Density(Double.NaN, DensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Density POSITIVE_INFINITY = new Density(Double.POSITIVE_INFINITY, DensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Density NEGATIVE_INFINITY = new Density(Double.NEGATIVE_INFINITY, DensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Density POS_MAXVALUE = new Density(Double.MAX_VALUE, DensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Density NEG_MAXVALUE = new Density(-Double.MAX_VALUE, DensityUnit.SI);

    /**
     * Construct Density scalar.
     * @param value double; the double value
     * @param unit DensityUnit; unit for the double value
     */
    public Density(final double value, final DensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Density scalar.
     * @param value Density; Scalar from which to construct this instance
     */
    public Density(final Density value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Density instantiateRel(final double value, final DensityUnit unit)
    {
        return new Density(value, unit);
    }

    /**
     * Construct Density scalar.
     * @param value double; the double value in SI units
     * @return Density; the new scalar with the SI value
     */
    public static final Density instantiateSI(final double value)
    {
        return new Density(value, DensityUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Density; the low value
     * @param one Density; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Density; a Scalar at the ratio between
     */
    public static Density interpolate(final Density zero, final Density one, final double ratio)
    {
        return new Density(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Density; the first scalar
     * @param r2 Density; the second scalar
     * @return Density; the maximum value of two relative scalars
     */
    public static Density max(final Density r1, final Density r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Density; the first scalar
     * @param r2 Density; the second scalar
     * @param rn Density...; the other scalars
     * @return Density; the maximum value of more than two relative scalars
     */
    public static Density max(final Density r1, final Density r2, final Density... rn)
    {
        Density maxr = r1.gt(r2) ? r1 : r2;
        for (Density r : rn)
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
     * @param r1 Density; the first scalar
     * @param r2 Density; the second scalar
     * @return Density; the minimum value of two relative scalars
     */
    public static Density min(final Density r1, final Density r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Density; the first scalar
     * @param r2 Density; the second scalar
     * @param rn Density...; the other scalars
     * @return Density; the minimum value of more than two relative scalars
     */
    public static Density min(final Density r1, final Density r2, final Density... rn)
    {
        Density minr = r1.lt(r2) ? r1 : r2;
        for (Density r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Density representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Density
     * @return Density; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Density valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Density: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Density: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            DensityUnit unit = DensityUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Density(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Density from " + text);
    }

    /**
     * Returns a Density based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Density; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Density of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Density: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Density: empty unitString");
        DensityUnit unit = DensityUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Density(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Density with unit " + unitString);
    }

    /**
     * Calculate the division of Density and Density, which results in a Dimensionless scalar.
     * @param v Density; scalar
     * @return Dimensionless; scalar as a division of Density and Density
     */
    public final Dimensionless divide(final Density v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Density and Volume, which results in a Mass scalar.
     * @param v Density; scalar
     * @return Mass; scalar as a multiplication of Density and Volume
     */
    public final Mass times(final Volume v)
    {
        return new Mass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the multiplication of Density and FlowVolume, which results in a FlowMass scalar.
     * @param v Density; scalar
     * @return FlowMass; scalar as a multiplication of Density and FlowVolume
     */
    public final FlowMass times(final FlowVolume v)
    {
        return new FlowMass(this.si * v.si, FlowMassUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
