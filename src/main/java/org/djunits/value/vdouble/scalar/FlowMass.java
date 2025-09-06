package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.MomentumUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FlowMass DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FlowMass extends DoubleScalarRel<FlowMassUnit, FlowMass>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final FlowMass ZERO = new FlowMass(0.0, FlowMassUnit.SI);

    /** Constant with value one. */
    public static final FlowMass ONE = new FlowMass(1.0, FlowMassUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FlowMass NaN = new FlowMass(Double.NaN, FlowMassUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FlowMass POSITIVE_INFINITY = new FlowMass(Double.POSITIVE_INFINITY, FlowMassUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FlowMass NEGATIVE_INFINITY = new FlowMass(Double.NEGATIVE_INFINITY, FlowMassUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FlowMass POS_MAXVALUE = new FlowMass(Double.MAX_VALUE, FlowMassUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FlowMass NEG_MAXVALUE = new FlowMass(-Double.MAX_VALUE, FlowMassUnit.SI);

    /**
     * Construct FlowMass scalar.
     * @param value the double value
     * @param unit unit for the double value
     */
    public FlowMass(final double value, final FlowMassUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FlowMass scalar.
     * @param value Scalar from which to construct this instance
     */
    public FlowMass(final FlowMass value)
    {
        super(value);
    }

    @Override
    public final FlowMass instantiateRel(final double value, final FlowMassUnit unit)
    {
        return new FlowMass(value, unit);
    }

    /**
     * Construct FlowMass scalar.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final FlowMass instantiateSI(final double value)
    {
        return new FlowMass(value, FlowMassUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FlowMass interpolate(final FlowMass zero, final FlowMass one, final double ratio)
    {
        return new FlowMass(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FlowMass max(final FlowMass r1, final FlowMass r2)
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
    public static FlowMass max(final FlowMass r1, final FlowMass r2, final FlowMass... rn)
    {
        FlowMass maxr = r1.gt(r2) ? r1 : r2;
        for (FlowMass r : rn)
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
    public static FlowMass min(final FlowMass r1, final FlowMass r2)
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
    public static FlowMass min(final FlowMass r1, final FlowMass r2, final FlowMass... rn)
    {
        FlowMass minr = r1.lt(r2) ? r1 : r2;
        for (FlowMass r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FlowMass representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FlowMass
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FlowMass valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FlowMass: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FlowMass: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            FlowMassUnit unit = FlowMassUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity FlowMass", unitString);
            return new FlowMass(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FlowMass from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FlowMass based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FlowMass of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FlowMass: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FlowMass: empty unitString");
        FlowMassUnit unit = FlowMassUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FlowMass with unit %s", unitString);
        return new FlowMass(value, unit);
    }

    /**
     * Calculate the division of FlowMass and FlowMass, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FlowMass and FlowMass
     */
    public final Dimensionless divide(final FlowMass v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FlowMass and Duration, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a multiplication of FlowMass and Duration
     */
    public final Mass times(final Duration v)
    {
        return new Mass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the division of FlowMass and Frequency, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a division of FlowMass and Frequency
     */
    public final Mass divide(final Frequency v)
    {
        return new Mass(this.si / v.si, MassUnit.SI);
    }

    /**
     * Calculate the division of FlowMass and Mass, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of FlowMass and Mass
     */
    public final Frequency divide(final Mass v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the multiplication of FlowMass and Speed, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of FlowMass and Speed
     */
    public final Force times(final Speed v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the division of FlowMass and FlowVolume, which results in a Density scalar.
     * @param v scalar
     * @return scalar as a division of FlowMass and FlowVolume
     */
    public final Density divide(final FlowVolume v)
    {
        return new Density(this.si / v.si, DensityUnit.SI);
    }

    /**
     * Calculate the division of FlowMass and Density, which results in a FlowVolume scalar.
     * @param v scalar
     * @return scalar as a division of FlowMass and Density
     */
    public final FlowVolume divide(final Density v)
    {
        return new FlowVolume(this.si / v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of FlowMass and Length, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a multiplication of FlowMass and Length
     */
    public final Momentum times(final Length v)
    {
        return new Momentum(this.si * v.si, MomentumUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
