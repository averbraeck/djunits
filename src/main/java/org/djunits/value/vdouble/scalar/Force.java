package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.PressureUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Force DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Force extends AbstractDoubleScalarRel<ForceUnit, Force>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Force ZERO = new Force(0.0, ForceUnit.SI);

    /** Constant with value one. */
    public static final Force ONE = new Force(1.0, ForceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Force NaN = new Force(Double.NaN, ForceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Force POSITIVE_INFINITY = new Force(Double.POSITIVE_INFINITY, ForceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Force NEGATIVE_INFINITY = new Force(Double.NEGATIVE_INFINITY, ForceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Force POS_MAXVALUE = new Force(Double.MAX_VALUE, ForceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Force NEG_MAXVALUE = new Force(-Double.MAX_VALUE, ForceUnit.SI);

    /**
     * Construct Force scalar.
     * @param value double; the double value
     * @param unit ForceUnit; unit for the double value
     */
    public Force(final double value, final ForceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Force scalar.
     * @param value Force; Scalar from which to construct this instance
     */
    public Force(final Force value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Force instantiateRel(final double value, final ForceUnit unit)
    {
        return new Force(value, unit);
    }

    /**
     * Construct Force scalar.
     * @param value double; the double value in SI units
     * @return Force; the new scalar with the SI value
     */
    public static final Force instantiateSI(final double value)
    {
        return new Force(value, ForceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Force; the low value
     * @param one Force; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Force; a Scalar at the ratio between
     */
    public static Force interpolate(final Force zero, final Force one, final double ratio)
    {
        return new Force(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Force; the first scalar
     * @param r2 Force; the second scalar
     * @return Force; the maximum value of two relative scalars
     */
    public static Force max(final Force r1, final Force r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Force; the first scalar
     * @param r2 Force; the second scalar
     * @param rn Force...; the other scalars
     * @return Force; the maximum value of more than two relative scalars
     */
    public static Force max(final Force r1, final Force r2, final Force... rn)
    {
        Force maxr = r1.gt(r2) ? r1 : r2;
        for (Force r : rn)
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
     * @param r1 Force; the first scalar
     * @param r2 Force; the second scalar
     * @return Force; the minimum value of two relative scalars
     */
    public static Force min(final Force r1, final Force r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Force; the first scalar
     * @param r2 Force; the second scalar
     * @param rn Force...; the other scalars
     * @return Force; the minimum value of more than two relative scalars
     */
    public static Force min(final Force r1, final Force r2, final Force... rn)
    {
        Force minr = r1.lt(r2) ? r1 : r2;
        for (Force r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Force representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Force
     * @return Force; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Force valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Force: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Force: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            ForceUnit unit = ForceUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Force(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Force from " + text);
    }

    /**
     * Returns a Force based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Force; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Force of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Force: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Force: empty unitString");
        ForceUnit unit = ForceUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Force(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Force with unit " + unitString);
    }

    /**
     * Calculate the division of Force and Force, which results in a Dimensionless scalar.
     * @param v Force; scalar
     * @return Dimensionless; scalar as a division of Force and Force
     */
    public final Dimensionless divide(final Force v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Force and Length, which results in a Energy scalar.
     * @param v Force; scalar
     * @return Energy; scalar as a multiplication of Force and Length
     */
    public final Energy times(final Length v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of Force and LinearDensity, which results in a Energy scalar.
     * @param v Force; scalar
     * @return Energy; scalar as a division of Force and LinearDensity
     */
    public final Energy divide(final LinearDensity v)
    {
        return new Energy(this.si / v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of Force and Energy, which results in a LinearDensity scalar.
     * @param v Force; scalar
     * @return LinearDensity; scalar as a division of Force and Energy
     */
    public final LinearDensity divide(final Energy v)
    {
        return new LinearDensity(this.si / v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the multiplication of Force and Speed, which results in a Power scalar.
     * @param v Force; scalar
     * @return Power; scalar as a multiplication of Force and Speed
     */
    public final Power times(final Speed v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the division of Force and Mass, which results in a Acceleration scalar.
     * @param v Force; scalar
     * @return Acceleration; scalar as a division of Force and Mass
     */
    public final Acceleration divide(final Mass v)
    {
        return new Acceleration(this.si / v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the division of Force and Acceleration, which results in a Mass scalar.
     * @param v Force; scalar
     * @return Mass; scalar as a division of Force and Acceleration
     */
    public final Mass divide(final Acceleration v)
    {
        return new Mass(this.si / v.si, MassUnit.SI);
    }

    /**
     * Calculate the division of Force and Area, which results in a Pressure scalar.
     * @param v Force; scalar
     * @return Pressure; scalar as a division of Force and Area
     */
    public final Pressure divide(final Area v)
    {
        return new Pressure(this.si / v.si, PressureUnit.SI);
    }

    /**
     * Calculate the division of Force and Pressure, which results in a Area scalar.
     * @param v Force; scalar
     * @return Area; scalar as a division of Force and Pressure
     */
    public final Area divide(final Pressure v)
    {
        return new Area(this.si / v.si, AreaUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
