package org.djunits.old.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.old.unit.AccelerationUnit;
import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.ForceUnit;
import org.djunits.old.unit.FrequencyUnit;
import org.djunits.old.unit.PowerUnit;
import org.djunits.old.unit.SpeedUnit;
import org.djunits.old.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Acceleration DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class Acceleration extends DoubleScalarRel<AccelerationUnit, Acceleration>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Acceleration ZERO = new Acceleration(0.0, AccelerationUnit.SI);

    /** Constant with value one. */
    public static final Acceleration ONE = new Acceleration(1.0, AccelerationUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Acceleration NaN = new Acceleration(Double.NaN, AccelerationUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Acceleration POSITIVE_INFINITY = new Acceleration(Double.POSITIVE_INFINITY, AccelerationUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Acceleration NEGATIVE_INFINITY = new Acceleration(Double.NEGATIVE_INFINITY, AccelerationUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Acceleration POS_MAXVALUE = new Acceleration(Double.MAX_VALUE, AccelerationUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Acceleration NEG_MAXVALUE = new Acceleration(-Double.MAX_VALUE, AccelerationUnit.SI);

    /**
     * Construct Acceleration scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public Acceleration(final double value, final AccelerationUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Acceleration scalar.
     * @param value Scalar from which to construct this instance
     */
    public Acceleration(final Acceleration value)
    {
        super(value);
    }

    @Override
    public final Acceleration instantiateRel(final double value, final AccelerationUnit unit)
    {
        return new Acceleration(value, unit);
    }

    /**
     * Construct Acceleration scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Acceleration ofSI(final double value)
    {
        return new Acceleration(value, AccelerationUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Acceleration at the given ratio between 0 and 1
     */
    public static Acceleration interpolate(final Acceleration zero, final Acceleration one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new Acceleration(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Acceleration max(final Acceleration r1, final Acceleration r2)
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
    public static Acceleration max(final Acceleration r1, final Acceleration r2, final Acceleration... rn)
    {
        Acceleration maxr = r1.gt(r2) ? r1 : r2;
        for (Acceleration r : rn)
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
    public static Acceleration min(final Acceleration r1, final Acceleration r2)
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
    public static Acceleration min(final Acceleration r1, final Acceleration r2, final Acceleration... rn)
    {
        Acceleration minr = r1.lt(r2) ? r1 : r2;
        for (Acceleration r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Acceleration representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Acceleration
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Acceleration valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Acceleration: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Acceleration: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AccelerationUnit unit = AccelerationUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Acceleration", unitString);
            return new Acceleration(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Acceleration from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Acceleration based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Acceleration of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Acceleration: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Acceleration: empty unitString");
        AccelerationUnit unit = AccelerationUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Acceleration with unit %s", unitString);
        return new Acceleration(value, unit);
    }

    /**
     * Calculate the division of Acceleration and Acceleration, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Acceleration and Acceleration
     */
    public final Dimensionless divide(final Acceleration v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Acceleration and Mass, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Acceleration and Mass
     */
    public final Force times(final Mass v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of Acceleration and Duration, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a multiplication of Acceleration and Duration
     */
    public final Speed times(final Duration v)
    {
        return new Speed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of Acceleration and Frequency, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a division of Acceleration and Frequency
     */
    public final Speed divide(final Frequency v)
    {
        return new Speed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of Acceleration and Speed, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of Acceleration and Speed
     */
    public final Frequency divide(final Speed v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the multiplication of Acceleration and Momentum, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Acceleration and Momentum
     */
    public final Power times(final Momentum v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type Acceleration.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of Acceleration
     */
    public static Acceleration multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(AccelerationUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type Acceleration",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Acceleration(scalar1.si * scalar2.si, AccelerationUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type Acceleration.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of Acceleration
     */
    public static Acceleration divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(AccelerationUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type Acceleration",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Acceleration(scalar1.si / scalar2.si, AccelerationUnit.SI);
    }

}
