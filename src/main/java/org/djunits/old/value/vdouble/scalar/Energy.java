package org.djunits.old.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.DurationUnit;
import org.djunits.old.unit.EnergyUnit;
import org.djunits.old.unit.ForceUnit;
import org.djunits.old.unit.LengthUnit;
import org.djunits.old.unit.MomentumUnit;
import org.djunits.old.unit.PowerUnit;
import org.djunits.old.unit.PressureUnit;
import org.djunits.old.unit.SpeedUnit;
import org.djunits.old.unit.VolumeUnit;
import org.djunits.old.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Energy DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class Energy extends DoubleScalarRel<EnergyUnit, Energy>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Energy ZERO = new Energy(0.0, EnergyUnit.SI);

    /** Constant with value one. */
    public static final Energy ONE = new Energy(1.0, EnergyUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Energy NaN = new Energy(Double.NaN, EnergyUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Energy POSITIVE_INFINITY = new Energy(Double.POSITIVE_INFINITY, EnergyUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Energy NEGATIVE_INFINITY = new Energy(Double.NEGATIVE_INFINITY, EnergyUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Energy POS_MAXVALUE = new Energy(Double.MAX_VALUE, EnergyUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Energy NEG_MAXVALUE = new Energy(-Double.MAX_VALUE, EnergyUnit.SI);

    /**
     * Construct Energy scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public Energy(final double value, final EnergyUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Energy scalar.
     * @param value Scalar from which to construct this instance
     */
    public Energy(final Energy value)
    {
        super(value);
    }

    @Override
    public final Energy instantiateRel(final double value, final EnergyUnit unit)
    {
        return new Energy(value, unit);
    }

    /**
     * Construct Energy scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Energy ofSI(final double value)
    {
        return new Energy(value, EnergyUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Energy at the given ratio between 0 and 1
     */
    public static Energy interpolate(final Energy zero, final Energy one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new Energy(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Energy max(final Energy r1, final Energy r2)
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
    public static Energy max(final Energy r1, final Energy r2, final Energy... rn)
    {
        Energy maxr = r1.gt(r2) ? r1 : r2;
        for (Energy r : rn)
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
    public static Energy min(final Energy r1, final Energy r2)
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
    public static Energy min(final Energy r1, final Energy r2, final Energy... rn)
    {
        Energy minr = r1.lt(r2) ? r1 : r2;
        for (Energy r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Energy representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Energy
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Energy valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Energy: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Energy: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            EnergyUnit unit = EnergyUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Energy", unitString);
            return new Energy(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Energy from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Energy based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Energy of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Energy: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Energy: empty unitString");
        EnergyUnit unit = EnergyUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Energy with unit %s", unitString);
        return new Energy(value, unit);
    }

    /**
     * Calculate the division of Energy and Energy, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Energy
     */
    public final Dimensionless divide(final Energy v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of Energy and Force, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Force
     */
    public final Length divide(final Force v)
    {
        return new Length(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the division of Energy and Length, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Length
     */
    public final Force divide(final Length v)
    {
        return new Force(this.si / v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of Energy and LinearDensity, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Energy and LinearDensity
     */
    public final Force times(final LinearDensity v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the division of Energy and Duration, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Duration
     */
    public final Power divide(final Duration v)
    {
        return new Power(this.si / v.si, PowerUnit.SI);
    }

    /**
     * Calculate the division of Energy and Power, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Power
     */
    public final Duration divide(final Power v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the division of Energy and Volume, which results in a Pressure scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Volume
     */
    public final Pressure divide(final Volume v)
    {
        return new Pressure(this.si / v.si, PressureUnit.SI);
    }

    /**
     * Calculate the division of Energy and Pressure, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Pressure
     */
    public final Volume divide(final Pressure v)
    {
        return new Volume(this.si / v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of Energy and Frequency, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Energy and Frequency
     */
    public final Power times(final Frequency v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the division of Energy and Speed, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Speed
     */
    public final Momentum divide(final Speed v)
    {
        return new Momentum(this.si / v.si, MomentumUnit.SI);
    }

    /**
     * Calculate the division of Energy and Momentum, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a division of Energy and Momentum
     */
    public final Speed divide(final Momentum v)
    {
        return new Speed(this.si / v.si, SpeedUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type Energy.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of Energy
     */
    public static Energy multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(EnergyUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type Energy",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Energy(scalar1.si * scalar2.si, EnergyUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type Energy.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of Energy
     */
    public static Energy divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(EnergyUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type Energy",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Energy(scalar1.si / scalar2.si, EnergyUnit.SI);
    }

}
