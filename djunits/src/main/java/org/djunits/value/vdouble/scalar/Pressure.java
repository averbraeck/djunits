package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.PressureUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Pressure DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Pressure extends AbstractDoubleScalarRel<PressureUnit, Pressure>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Pressure ZERO = new Pressure(0.0, PressureUnit.SI);

    /** Constant with value one. */
    public static final Pressure ONE = new Pressure(1.0, PressureUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Pressure NaN = new Pressure(Double.NaN, PressureUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Pressure POSITIVE_INFINITY = new Pressure(Double.POSITIVE_INFINITY, PressureUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Pressure NEGATIVE_INFINITY = new Pressure(Double.NEGATIVE_INFINITY, PressureUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Pressure POS_MAXVALUE = new Pressure(Double.MAX_VALUE, PressureUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Pressure NEG_MAXVALUE = new Pressure(-Double.MAX_VALUE, PressureUnit.SI);

    /**
     * Construct Pressure scalar.
     * @param value double; the double value
     * @param unit PressureUnit; unit for the double value
     */
    public Pressure(final double value, final PressureUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Pressure scalar.
     * @param value Pressure; Scalar from which to construct this instance
     */
    public Pressure(final Pressure value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Pressure instantiateRel(final double value, final PressureUnit unit)
    {
        return new Pressure(value, unit);
    }

    /**
     * Construct Pressure scalar.
     * @param value double; the double value in SI units
     * @return Pressure; the new scalar with the SI value
     */
    public static final Pressure instantiateSI(final double value)
    {
        return new Pressure(value, PressureUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Pressure; the low value
     * @param one Pressure; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Pressure; a Scalar at the ratio between
     */
    public static Pressure interpolate(final Pressure zero, final Pressure one, final double ratio)
    {
        return new Pressure(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Pressure; the first scalar
     * @param r2 Pressure; the second scalar
     * @return Pressure; the maximum value of two relative scalars
     */
    public static Pressure max(final Pressure r1, final Pressure r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Pressure; the first scalar
     * @param r2 Pressure; the second scalar
     * @param rn Pressure...; the other scalars
     * @return Pressure; the maximum value of more than two relative scalars
     */
    public static Pressure max(final Pressure r1, final Pressure r2, final Pressure... rn)
    {
        Pressure maxr = r1.gt(r2) ? r1 : r2;
        for (Pressure r : rn)
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
     * @param r1 Pressure; the first scalar
     * @param r2 Pressure; the second scalar
     * @return Pressure; the minimum value of two relative scalars
     */
    public static Pressure min(final Pressure r1, final Pressure r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Pressure; the first scalar
     * @param r2 Pressure; the second scalar
     * @param rn Pressure...; the other scalars
     * @return Pressure; the minimum value of more than two relative scalars
     */
    public static Pressure min(final Pressure r1, final Pressure r2, final Pressure... rn)
    {
        Pressure minr = r1.lt(r2) ? r1 : r2;
        for (Pressure r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Pressure representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Pressure
     * @return Pressure; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Pressure valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Pressure: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Pressure: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            PressureUnit unit = PressureUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Pressure(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Pressure from " + text);
    }

    /**
     * Returns a Pressure based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Pressure; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Pressure of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Pressure: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Pressure: empty unitString");
        PressureUnit unit = PressureUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Pressure(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Pressure with unit " + unitString);
    }

    /**
     * Calculate the division of Pressure and Pressure, which results in a Dimensionless scalar.
     * @param v Pressure; scalar
     * @return Dimensionless; scalar as a division of Pressure and Pressure
     */
    public final Dimensionless divide(final Pressure v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Pressure and Area, which results in a Force scalar.
     * @param v Pressure; scalar
     * @return Force; scalar as a multiplication of Pressure and Area
     */
    public final Force times(final Area v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of Pressure and Volume, which results in a Energy scalar.
     * @param v Pressure; scalar
     * @return Energy; scalar as a multiplication of Pressure and Volume
     */
    public final Energy times(final Volume v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
