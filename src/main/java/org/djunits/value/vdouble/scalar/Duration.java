package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.AngularVelocityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRelWithAbs;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Relative Duration DoubleScalar.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class Duration extends DoubleScalarRelWithAbs<TimeUnit, Time, DurationUnit, Duration>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Duration ZERO = new Duration(0.0, DurationUnit.SI);

    /** Constant with value one. */
    public static final Duration ONE = new Duration(1.0, DurationUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Duration NaN = new Duration(Double.NaN, DurationUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Duration POSITIVE_INFINITY = new Duration(Double.POSITIVE_INFINITY, DurationUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Duration NEGATIVE_INFINITY = new Duration(Double.NEGATIVE_INFINITY, DurationUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Duration POS_MAXVALUE = new Duration(Double.MAX_VALUE, DurationUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Duration NEG_MAXVALUE = new Duration(-Double.MAX_VALUE, DurationUnit.SI);

    /**
     * Construct Duration scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public Duration(final double value, final DurationUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Duration scalar.
     * @param value Scalar from which to construct this instance
     */
    public Duration(final Duration value)
    {
        super(value);
    }

    @Override
    public final Duration instantiateRel(final double value, final DurationUnit unit)
    {
        return new Duration(value, unit);
    }

    @Override
    public final Time instantiateAbs(final double value, final TimeUnit unit)
    {
        return new Time(value, unit);
    }

    /**
     * Construct Duration scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Duration ofSI(final double value)
    {
        return new Duration(value, DurationUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Duration at the given ratio between 0 and 1
     */
    public static Duration interpolate(final Duration zero, final Duration one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new Duration(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Duration max(final Duration r1, final Duration r2)
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
    public static Duration max(final Duration r1, final Duration r2, final Duration... rn)
    {
        Duration maxr = r1.gt(r2) ? r1 : r2;
        for (Duration r : rn)
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
    public static Duration min(final Duration r1, final Duration r2)
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
    public static Duration min(final Duration r1, final Duration r2, final Duration... rn)
    {
        Duration minr = r1.lt(r2) ? r1 : r2;
        for (Duration r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Duration representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Duration
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Duration valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Duration: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Duration: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            DurationUnit unit = DurationUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Duration", unitString);
            return new Duration(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Duration from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Duration based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Duration of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Duration: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Duration: empty unitString");
        DurationUnit unit = DurationUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Duration with unit %s", unitString);
        return new Duration(value, unit);
    }

    /**
     * Calculate the division of Duration and Duration, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Duration and Duration
     */
    public final Dimensionless divide(final Duration v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and Frequency, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and Frequency
     */
    public final Dimensionless times(final Frequency v)
    {
        return new Dimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and ElectricalCurrent, which results in a ElectricalCharge scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricalCurrent
     */
    public final ElectricalCharge times(final ElectricalCurrent v)
    {
        return new ElectricalCharge(this.si * v.si, ElectricalChargeUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and FlowMass, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and FlowMass
     */
    public final Mass times(final FlowMass v)
    {
        return new Mass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and FlowVolume, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and FlowVolume
     */
    public final Volume times(final FlowVolume v)
    {
        return new Volume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and Acceleration, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and Acceleration
     */
    public final Speed times(final Acceleration v)
    {
        return new Speed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and Power, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and Power
     */
    public final Energy times(final Power v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and Speed, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and Speed
     */
    public final Length times(final Speed v)
    {
        return new Length(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and ElectricalPotential, which results in a MagneticFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricalPotential
     */
    public final MagneticFlux times(final ElectricalPotential v)
    {
        return new MagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and ElectricalResistance, which results in a ElectricalInductance scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricalResistance
     */
    public final ElectricalInductance times(final ElectricalResistance v)
    {
        return new ElectricalInductance(this.si * v.si, ElectricalInductanceUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and ElectricalConductance, which results in a ElectricalCapacitance scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricalConductance
     */
    public final ElectricalCapacitance times(final ElectricalConductance v)
    {
        return new ElectricalCapacitance(this.si * v.si, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and AngularVelocity, which results in a Angle scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and AngularVelocity
     */
    public final Angle times(final AngularVelocity v)
    {
        return new Angle(this.si * v.si, AngleUnit.SI);
    }

    /**
     * Calculate the multiplication of Duration and AngularAcceleration, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and AngularAcceleration
     */
    public final AngularVelocity times(final AngularAcceleration v)
    {
        return new AngularVelocity(this.si * v.si, AngularVelocityUnit.SI);
    }

    @Override
    public Frequency reciprocal()
    {
        return Frequency.ofSI(1.0 / this.si);
    }

}
