package org.djunits.quantity;

import java.util.Locale;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

/**
 * Duration is the interval of time between two events, measured in seconds (s). This quantity encodes a <i>relative</i> amount
 * of time. The Time quantity encodes an absolute time instant.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */

public class Duration extends Quantity.Relative<Duration, Duration.Unit>
{
    /** Constant with value zero. */
    public static final Duration ZERO = Duration.ofSi(0.0);

    /** Constant with value one. */
    public static final Duration ONE = Duration.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Duration NaN = Duration.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Duration POSITIVE_INFINITY = Duration.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Duration NEGATIVE_INFINITY = Duration.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Duration POS_MAXVALUE = Duration.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Duration NEG_MAXVALUE = Duration.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Duration quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Duration(final double value, final Duration.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Duration quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Duration(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Duration.Unit.class, abbreviation));
    }

    /**
     * Construct Duration quantity.
     * @param value Scalar from which to construct this instance
     */
    public Duration(final Duration value)
    {
        super(value.si(), Duration.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Duration instance based on an SI value.
     * @param si the si value
     * @return the Duration instance based on an SI value
     */
    public static Duration ofSi(final double si)
    {
        return new Duration(si, Duration.Unit.SI);
    }

    @Override
    public Duration instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Duration.Unit.SI_UNIT;
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
            Duration.Unit unit = Units.resolve(Duration.Unit.class, unitString);
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
        Duration.Unit unit = Units.resolve(Duration.Unit.class, unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Duration with unit %s", unitString);
        return new Duration(value, unit);
    }

    /**
     * Calculate the division of Duration and Duration, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Duration and Duration
     */
    public final Dimensionless divide(final Duration v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Duration and ElectricCurrent, which results in a ElectricCharge scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricCurrent
     */
    public final ElectricCharge times(final ElectricCurrent v)
    {
        return new ElectricCharge(this.si() * v.si(), ElectricCharge.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and FlowMass, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and FlowMass
     */
    public final Mass times(final FlowMass v)
    {
        return new Mass(this.si() * v.si(), Mass.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and FlowVolume, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and FlowVolume
     */
    public final Volume times(final FlowVolume v)
    {
        return new Volume(this.si() * v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and Acceleration, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and Acceleration
     */
    public final Speed times(final Acceleration v)
    {
        return new Speed(this.si() * v.si(), Speed.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and Power, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and Power
     */
    public final Energy times(final Power v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and Speed, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and Speed
     */
    public final Length times(final Speed v)
    {
        return new Length(this.si() * v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and ElectricPotential, which results in a MagneticFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricPotential
     */
    public final MagneticFlux times(final ElectricPotential v)
    {
        return new MagneticFlux(this.si() * v.si(), MagneticFlux.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and ElectricalResistance, which results in a ElectricalInductance scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricalResistance
     */
    public final ElectricalInductance times(final ElectricalResistance v)
    {
        return new ElectricalInductance(this.si() * v.si(), ElectricalInductance.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and ElectricalConductance, which results in a ElectricalCapacitance scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and ElectricalConductance
     */
    public final ElectricalCapacitance times(final ElectricalConductance v)
    {
        return new ElectricalCapacitance(this.si() * v.si(), ElectricalCapacitance.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and AngularVelocity, which results in a Angle scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and AngularVelocity
     */
    public final Angle times(final AngularVelocity v)
    {
        return new Angle(this.si() * v.si(), Angle.Unit.SI);
    }

    /**
     * Calculate the multiplication of Duration and AngularAcceleration, which results in a AngularVelocity scalar.
     * @param v scalar
     * @return scalar as a multiplication of Duration and AngularAcceleration
     */
    public final AngularVelocity times(final AngularAcceleration v)
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
     * Duration.Unit encodes the units of relative time.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Duration.Unit>
    {
        /** The dimensions of duration: s. */
        public static final SIUnit SI_UNIT = SIUnit.of("s");

        /** second. */
        public static final Duration.Unit SECOND = new Duration.Unit("s", "second", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final Duration.Unit SI = SECOND.generateSiPrefixes(false, false);

        /** picosecond. */
        public static final Duration.Unit PICOSECOND = Units.resolve(Duration.Unit.class, "ps");

        /** nanosecond. */
        public static final Duration.Unit NANOSECOND = Units.resolve(Duration.Unit.class, "ns");

        /** microsecond. */
        public static final Duration.Unit MICROSECOND = Units.resolve(Duration.Unit.class, "mus");

        /** millisecond. */
        public static final Duration.Unit MILLISECOND = Units.resolve(Duration.Unit.class, "ms");

        /** minute. */
        public static final Duration.Unit MINUTE = SECOND.deriveUnit("min", "minute", 60.0, UnitSystem.SI_ACCEPTED);

        /** hour. */
        public static final Duration.Unit HOUR = MINUTE.deriveUnit("h", "hour", 60.0, UnitSystem.SI_ACCEPTED);

        /** day. */
        public static final Duration.Unit DAY = HOUR.deriveUnit("day", "day", 24.0, UnitSystem.OTHER);

        /** week. */
        public static final Duration.Unit WEEK = DAY.deriveUnit("wk", "week", 7.0, UnitSystem.OTHER);

        /**
         * Create a new Duration unit.
         * @param id the id or main abbreviation of the unit
         * @param name the full name of the unit
         * @param scaleFactorToBaseUnit the scale factor of the unit to convert it TO the base (SI) unit
         * @param unitSystem the unit system such as SI or IMPERIAL
         */
        public Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
        {
            super(id, name, new LinearScale(scaleFactorToBaseUnit), unitSystem);
        }

        /**
         * Return a derived unit for this unit, with textual abbreviation(s) and a display abbreviation.
         * @param textualAbbreviation the textual abbreviation of the unit, which doubles as the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         */
        public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
        {
            super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem);
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
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Duration.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
