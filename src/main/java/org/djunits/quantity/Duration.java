package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Duration is the interval of time between two events, measured in seconds (s). This quantity encodes a <i>relative</i> amount
 * of time. The Time quantity encodes an absolute time instant.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */

public class Duration extends Quantity<Duration>
{
    /** Constant with value zero. */
    public static final Duration ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final Duration ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Duration NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Duration POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Duration NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Duration POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Duration NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Duration quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public Duration(final double value, final Duration.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a Duration quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public Duration(final double valueInUnit, final Duration.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a Duration instance based on an SI value.
     * @param si the si value
     * @return the Duration instance based on an SI value
     */
    public static Duration ofSi(final double si)
    {
        return new Duration(si, Duration.Unit.SI, true);
    }

    /**
     * Instantiate a Duration quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the Duration instance based on an SI value with the given display unit
     */
    public static Duration ofSi(final double siValue, final Duration.Unit displayUnit)
    {
        return new Duration(siValue, displayUnit, true);
    }

    @Override
    public Duration instantiateSi(final double siValue, final UnitInterface<Duration> displayUnit)
    {
        return new Duration(siValue, (Duration.Unit) displayUnit, true);
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
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Duration based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab Duration representation of the value in its unit
     */
    public static Duration of(final double valueInUnit, final Duration.Unit unit)
    {
        return new Duration(valueInUnit, unit);
    }

    /**
     * Returns a Duration based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Duration of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public Duration.Unit getDisplayUnit()
    {
        return (Duration.Unit) super.getDisplayUnit();
    }

    /**
     * Add an (absolute) time to this duration, and return a time. The unit of the return value will be the unit of this
     * duration, and the reference of the return value will be the reference belonging to the given time. <code>R.add(A)</code>
     * = unit of R and reference value of A.
     * @param time the absolute time to add
     * @return the absolute time plus this duration
     */
    public Time add(final Time time)
    {
        return new Time(new Duration(time.si() + si(), getDisplayUnit(), true), time.getReference());
    }

    /**
     * Calculate the division of Duration and Duration, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Duration and Duration
     */
    public Dimensionless divide(final Duration v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of Duration and ElectricCurrent, which results in a ElectricCharge scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricCurrent
     */
    public ElectricCharge multiply(final ElectricCurrent v)
    {
        return new ElectricCharge(this.si() * v.si(), ElectricCharge.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and FlowMass, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and FlowMass
     */
    public Mass multiply(final FlowMass v)
    {
        return new Mass(this.si() * v.si(), Mass.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and FlowVolume, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and FlowVolume
     */
    public Volume multiply(final FlowVolume v)
    {
        return new Volume(this.si() * v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and Acceleration, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and Acceleration
     */
    public Speed multiply(final Acceleration v)
    {
        return new Speed(this.si() * v.si(), Speed.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and Power, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and Power
     */
    public Energy multiply(final Power v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and Speed, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and Speed
     */
    public Length multiply(final Speed v)
    {
        return new Length(this.si() * v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and ElectricPotential, which results in a MagneticFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricPotential
     */
    public MagneticFlux multiply(final ElectricPotential v)
    {
        return new MagneticFlux(this.si() * v.si(), MagneticFlux.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and ElectricalResistance, which results in a ElectricalInductance scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricalResistance
     */
    public ElectricalInductance multiply(final ElectricalResistance v)
    {
        return new ElectricalInductance(this.si() * v.si(), ElectricalInductance.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and ElectricalConductance, which results in a ElectricalCapacitance scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricalConductance
     */
    public ElectricalCapacitance multiply(final ElectricalConductance v)
    {
        return new ElectricalCapacitance(this.si() * v.si(), ElectricalCapacitance.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and AngularVelocity, which results in a Angle scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and AngularVelocity
     */
    public Angle multiply(final AngularVelocity v)
    {
        return new Angle(this.si() * v.si(), Angle.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and AngularAcceleration, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and AngularAcceleration
     */
    public AngularVelocity multiply(final AngularAcceleration v)
    {
        return new AngularVelocity(this.si() * v.si(), AngularVelocity.Unit.SI);
    }

    @Override
    public Frequency reciprocal()
    {
        return Frequency.ofSi(1.0 / this.si());
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Duration.Unit encodes the units of relative time.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Duration>
    {
        /** The dimensions of duration: s. */
        public static final SIUnit SI_UNIT = SIUnit.of("s");

        /** second. */
        public static final Duration.Unit s =
                new Duration.Unit("s", "s", "second", IdentityScale.SCALE, UnitSystem.SI_BASE, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final Duration.Unit SI = (Unit) s.generateSiPrefixes(false, false);

        /** picosecond. */
        public static final Duration.Unit ps = Units.resolve(Duration.Unit.class, "ps");

        /** nanosecond. */
        public static final Duration.Unit ns = Units.resolve(Duration.Unit.class, "ns");

        /** microsecond. */
        public static final Duration.Unit mus = Units.resolve(Duration.Unit.class, "mus");

        /** millisecond. */
        public static final Duration.Unit ms = Units.resolve(Duration.Unit.class, "ms");

        /** minute. */
        public static final Duration.Unit min = s.deriveUnit("min", "minute", 60.0, UnitSystem.SI_ACCEPTED);

        /** hour. */
        public static final Duration.Unit h = min.deriveUnit("h", "hour", 60.0, UnitSystem.SI_ACCEPTED);

        /** day. */
        public static final Duration.Unit day = h.deriveUnit("day", "day", 24.0, UnitSystem.OTHER);

        /** week. */
        public static final Duration.Unit wk = day.deriveUnit("wk", "week", 7.0, UnitSystem.OTHER);

        /**
         * Create a new Duration unit.
         * @param id the id or main abbreviation of the unit
         * @param name the full name of the unit
         * @param scaleFactorToBaseUnit the scale factor of the unit to convert it TO the base (SI) unit
         * @param unitSystem the unit system such as SI or IMPERIAL
         */
        public Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
        {
            super(id, name, scaleFactorToBaseUnit, unitSystem);
        }

        /**
         * Return a derived unit for this unit, with textual abbreviation(s) and a display abbreviation.
         * @param textualAbbreviation the textual abbreviation of the unit, which doubles as the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert from this unit to the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         * @param siPrefix the SI Prefix of this unit
         */
        public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name, final Scale scale,
                final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem, siPrefix);
        }

        @Override
        public SIUnit siUnit()
        {
            return SI_UNIT;
        }

        @Override
        public Unit getBaseUnit()
        {
            return SI;
        }

        @Override
        public Duration ofSi(final double si, final UnitInterface<Duration> displayUnit)
        {
            return new Duration(si, (Unit) displayUnit, true);
        }

        @Override
        public Duration.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Duration.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

        @Override
        public Duration.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

    }

}
