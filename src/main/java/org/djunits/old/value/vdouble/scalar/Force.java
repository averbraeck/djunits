package org.djunits.old.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.old.unit.AccelerationUnit;
import org.djunits.old.unit.AreaUnit;
import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.EnergyUnit;
import org.djunits.old.unit.ForceUnit;
import org.djunits.old.unit.LinearDensityUnit;
import org.djunits.old.unit.MassUnit;
import org.djunits.old.unit.PowerUnit;
import org.djunits.old.unit.PressureUnit;
import org.djunits.old.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Force DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class Force extends DoubleScalarRel<ForceUnit, Force>
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
     * Construct Force scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public Force(final double value, final ForceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Force scalar.
     * @param value Scalar from which to construct this instance
     */
    public Force(final Force value)
    {
        super(value);
    }

    @Override
    public final Force instantiateRel(final double value, final ForceUnit unit)
    {
        return new Force(value, unit);
    }

    /**
     * Construct Force scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Force ofSI(final double value)
    {
        return new Force(value, ForceUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Force at the given ratio between 0 and 1
     */
    public static Force interpolate(final Force zero, final Force one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new Force(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Force max(final Force r1, final Force r2)
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
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the minimum value of two relative scalars
     */
    public static Force min(final Force r1, final Force r2)
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
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Force
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Force valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Force: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Force: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ForceUnit unit = ForceUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Force", unitString);
            return new Force(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Force from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Force based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Force of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Force: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Force: empty unitString");
        ForceUnit unit = ForceUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Force with unit %s", unitString);
        return new Force(value, unit);
    }

    /**
     * Calculate the division of Force and Force, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Force and Force
     */
    public final Dimensionless divide(final Force v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Force and Length, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Force and Length
     */
    public final Energy times(final Length v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of Force and LinearDensity, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a division of Force and LinearDensity
     */
    public final Energy divide(final LinearDensity v)
    {
        return new Energy(this.si / v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of Force and Energy, which results in a LinearDensity scalar.
     * @param v scalar
     * @return scalar as a division of Force and Energy
     */
    public final LinearDensity divide(final Energy v)
    {
        return new LinearDensity(this.si / v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the multiplication of Force and Speed, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Force and Speed
     */
    public final Power times(final Speed v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the division of Force and Mass, which results in a Acceleration scalar.
     * @param v scalar
     * @return scalar as a division of Force and Mass
     */
    public final Acceleration divide(final Mass v)
    {
        return new Acceleration(this.si / v.si, AccelerationUnit.SI);
    }

    /**
     * Calculate the division of Force and Acceleration, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a division of Force and Acceleration
     */
    public final Mass divide(final Acceleration v)
    {
        return new Mass(this.si / v.si, MassUnit.SI);
    }

    /**
     * Calculate the division of Force and Area, which results in a Pressure scalar.
     * @param v scalar
     * @return scalar as a division of Force and Area
     */
    public final Pressure divide(final Area v)
    {
        return new Pressure(this.si / v.si, PressureUnit.SI);
    }

    /**
     * Calculate the division of Force and Pressure, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a division of Force and Pressure
     */
    public final Area divide(final Pressure v)
    {
        return new Area(this.si / v.si, AreaUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type Force.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of Force
     */
    public static Force multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(ForceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type Force",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Force(scalar1.si * scalar2.si, ForceUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type Force.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of Force
     */
    public static Force divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(ForceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type Force",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Force(scalar1.si / scalar2.si, ForceUnit.SI);
    }

}
