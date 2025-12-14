package org.djunits.old.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.old.unit.AccelerationUnit;
import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.ElectricalCurrentUnit;
import org.djunits.old.unit.ElectricalPotentialUnit;
import org.djunits.old.unit.EnergyUnit;
import org.djunits.old.unit.ForceUnit;
import org.djunits.old.unit.FrequencyUnit;
import org.djunits.old.unit.MomentumUnit;
import org.djunits.old.unit.PowerUnit;
import org.djunits.old.unit.SpeedUnit;
import org.djunits.old.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Power DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class Power extends DoubleScalarRel<PowerUnit, Power>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Power ZERO = new Power(0.0, PowerUnit.SI);

    /** Constant with value one. */
    public static final Power ONE = new Power(1.0, PowerUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Power NaN = new Power(Double.NaN, PowerUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Power POSITIVE_INFINITY = new Power(Double.POSITIVE_INFINITY, PowerUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Power NEGATIVE_INFINITY = new Power(Double.NEGATIVE_INFINITY, PowerUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Power POS_MAXVALUE = new Power(Double.MAX_VALUE, PowerUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Power NEG_MAXVALUE = new Power(-Double.MAX_VALUE, PowerUnit.SI);

    /**
     * Construct Power scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public Power(final double value, final PowerUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Power scalar.
     * @param value Scalar from which to construct this instance
     */
    public Power(final Power value)
    {
        super(value);
    }

    @Override
    public final Power instantiateRel(final double value, final PowerUnit unit)
    {
        return new Power(value, unit);
    }

    /**
     * Construct Power scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Power ofSI(final double value)
    {
        return new Power(value, PowerUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Power at the given ratio between 0 and 1
     */
    public static Power interpolate(final Power zero, final Power one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new Power(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Power max(final Power r1, final Power r2)
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
    public static Power max(final Power r1, final Power r2, final Power... rn)
    {
        Power maxr = r1.gt(r2) ? r1 : r2;
        for (Power r : rn)
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
    public static Power min(final Power r1, final Power r2)
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
    public static Power min(final Power r1, final Power r2, final Power... rn)
    {
        Power minr = r1.lt(r2) ? r1 : r2;
        for (Power r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Power representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Power
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Power valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Power: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Power: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            PowerUnit unit = PowerUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Power", unitString);
            return new Power(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Power from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Power based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Power of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Power: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Power: empty unitString");
        PowerUnit unit = PowerUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Power with unit %s", unitString);
        return new Power(value, unit);
    }

    /**
     * Calculate the division of Power and Power, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Power and Power
     */
    public final Dimensionless divide(final Power v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Power and Duration, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Power and Duration
     */
    public final Energy times(final Duration v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of Power and Frequency, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a division of Power and Frequency
     */
    public final Energy divide(final Frequency v)
    {
        return new Energy(this.si / v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of Power and Energy, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of Power and Energy
     */
    public final Frequency divide(final Energy v)
    {
        return new Frequency(this.si / v.si, FrequencyUnit.SI);
    }

    /**
     * Calculate the division of Power and Speed, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a division of Power and Speed
     */
    public final Force divide(final Speed v)
    {
        return new Force(this.si / v.si, ForceUnit.SI);
    }

    /**
     * Calculate the division of Power and Force, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a division of Power and Force
     */
    public final Speed divide(final Force v)
    {
        return new Speed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of Power and ElectricalPotential, which results in a ElectricalCurrent scalar.
     * @param v scalar
     * @return scalar as a division of Power and ElectricalPotential
     */
    public final ElectricalCurrent divide(final ElectricalPotential v)
    {
        return new ElectricalCurrent(this.si / v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the division of Power and ElectricalCurrent, which results in a ElectricalPotential scalar.
     * @param v scalar
     * @return scalar as a division of Power and ElectricalCurrent
     */
    public final ElectricalPotential divide(final ElectricalCurrent v)
    {
        return new ElectricalPotential(this.si / v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the division of Power and Acceleration, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a division of Power and Acceleration
     */
    public final Momentum divide(final Acceleration v)
    {
        return new Momentum(this.si / v.si, MomentumUnit.SI);
    }

    /**
     * Calculate the division of Power and Momentum, which results in a Acceleration scalar.
     * @param v scalar
     * @return scalar as a division of Power and Momentum
     */
    public final Acceleration divide(final Momentum v)
    {
        return new Acceleration(this.si / v.si, AccelerationUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type Power.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of Power
     */
    public static Power multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(PowerUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type Power",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Power(scalar1.si * scalar2.si, PowerUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type Power.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of Power
     */
    public static Power divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(PowerUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type Power",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Power(scalar1.si / scalar2.si, PowerUnit.SI);
    }

}
