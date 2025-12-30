package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Flow volume is the rate of volume passing through a surface per unit time, measured in cubic meters per second (m3/s).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class FlowVolume extends Quantity.Relative<FlowVolume, FlowVolume.Unit>
{
    /** Constant with value zero. */
    public static final FlowVolume ZERO = FlowVolume.ofSi(0.0);

    /** Constant with value one. */
    public static final FlowVolume ONE = FlowVolume.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FlowVolume NaN = FlowVolume.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FlowVolume POSITIVE_INFINITY = FlowVolume.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FlowVolume NEGATIVE_INFINITY = FlowVolume.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final FlowVolume POS_MAXVALUE = FlowVolume.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final FlowVolume NEG_MAXVALUE = FlowVolume.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a FlowVolume quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public FlowVolume(final double value, final FlowVolume.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a FlowVolume quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public FlowVolume(final double value, final String abbreviation)
    {
        this(value, Units.resolve(FlowVolume.Unit.class, abbreviation));
    }

    /**
     * Construct FlowVolume quantity.
     * @param value Scalar from which to construct this instance
     */
    public FlowVolume(final FlowVolume value)
    {
        super(value.si(), FlowVolume.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a FlowVolume instance based on an SI value.
     * @param si the si value
     * @return the FlowVolume instance based on an SI value
     */
    public static FlowVolume ofSi(final double si)
    {
        return new FlowVolume(si, FlowVolume.Unit.SI);
    }

    @Override
    public FlowVolume instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return FlowVolume.Unit.SI_UNIT;
    }

    /**
     * Returns a FlowVolume representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FlowVolume
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FlowVolume valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a FlowVolume based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FlowVolume of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of FlowVolume and FlowVolume, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of FlowVolume and FlowVolume
     */
    public final Dimensionless divide(final FlowVolume v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of FlowVolume and Duration, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a multiplication of FlowVolume and Duration
     */
    public final Volume times(final Duration v)
    {
        return new Volume(this.si() * v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the division of FlowVolume and Frequency, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a division of FlowVolume and Frequency
     */
    public final Volume divide(final Frequency v)
    {
        return new Volume(this.si() / v.si(), Volume.Unit.SI);
    }

    /**
     * Calculate the division of FlowVolume and Volume, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of FlowVolume and Volume
     */
    public final Frequency divide(final Volume v)
    {
        return new Frequency(this.si() / v.si(), Frequency.Unit.SI);
    }

    /**
     * Calculate the division of FlowVolume and Area, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a division of FlowVolume and Area
     */
    public final Speed divide(final Area v)
    {
        return new Speed(this.si() / v.si(), Speed.Unit.SI);
    }

    /**
     * Calculate the division of FlowVolume and Speed, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a division of FlowVolume and Speed
     */
    public final Area divide(final Speed v)
    {
        return new Area(this.si() / v.si(), Area.Unit.SI);
    }

    /**
     * Calculate the multiplication of FlowVolume and Density, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a multiplication of FlowVolume and Density
     */
    public final FlowMass times(final Density v)
    {
        return new FlowMass(this.si() * v.si(), FlowMass.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * FlowVolume.Unit encodes the units of absorbed dose (of ionizing radiation).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<FlowVolume.Unit, FlowVolume>
    {
        /** The dimensions of the flow volume is m3/s. */
        public static final SIUnit SI_UNIT = SIUnit.of("m3/s");

        /** m3/s. */
        public static final FlowVolume.Unit CUBIC_METER_PER_SECOND =
                new FlowVolume.Unit("m3/s", "cubic meter per second", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final FlowVolume.Unit SI = CUBIC_METER_PER_SECOND;

        /** m^3/min. */
        public static final FlowVolume.Unit CUBIC_METER_PER_MINUTE =
                CUBIC_METER_PER_SECOND.deriveUnit("m3/min", "cubic meter per minute", 1.0 / 60.0, UnitSystem.SI_ACCEPTED);

        /** m^3/hour. */
        public static final FlowVolume.Unit CUBIC_METER_PER_HOUR =
                CUBIC_METER_PER_SECOND.deriveUnit("m3/h", "cubic meter per hour", 1.0 / 3600.0, UnitSystem.SI_ACCEPTED);

        /** m^3/day. */
        public static final FlowVolume.Unit CUBIC_METER_PER_DAY =
                CUBIC_METER_PER_HOUR.deriveUnit("m3/day", "cubic meter per day", 1.0 / 24.0, UnitSystem.SI_ACCEPTED);

        /** L/s. */
        public static final FlowVolume.Unit LITER_PER_SECOND =
                CUBIC_METER_PER_SECOND.deriveUnit("L/s", "liter per second", 1E-3, UnitSystem.SI_ACCEPTED);

        /** L/min. */
        public static final FlowVolume.Unit LITER_PER_MINUTE =
                LITER_PER_SECOND.deriveUnit("L/min", "liter per minute", 1.0 / 60.0, UnitSystem.SI_ACCEPTED);

        /** L/hour. */
        public static final FlowVolume.Unit LITER_PER_HOUR =
                LITER_PER_SECOND.deriveUnit("L/h", "liter per hour", 1.0 / 3600.0, UnitSystem.SI_ACCEPTED);

        /** L/day. */
        public static final FlowVolume.Unit LITER_PER_DAY =
                LITER_PER_HOUR.deriveUnit("L/day", "liter per day", 1.0 / 24.0, UnitSystem.SI_ACCEPTED);

        /** ft^3/s. */
        public static final FlowVolume.Unit CUBIC_FOOT_PER_SECOND = CUBIC_METER_PER_SECOND.deriveUnit("ft3/s",
                "cubic foot per second", Volume.Unit.CONST_CUBIC_FOOT, UnitSystem.IMPERIAL);

        /** ft^3/min. */
        public static final FlowVolume.Unit CUBIC_FOOT_PER_MINUTE =
                CUBIC_FOOT_PER_SECOND.deriveUnit("ft3/min", "cubic foot per minute", 1.0 / 60.0, UnitSystem.IMPERIAL);

        /** in^3/s. */
        public static final FlowVolume.Unit CUBIC_INCH_PER_SECOND = CUBIC_METER_PER_SECOND.deriveUnit("in3/s",
                "cubic inch per second", Volume.Unit.CONST_CUBIC_INCH, UnitSystem.IMPERIAL);

        /** in^3/min. */
        public static final FlowVolume.Unit CUBIC_INCH_PER_MINUTE =
                CUBIC_INCH_PER_SECOND.deriveUnit("in3/min", "cubic inch per minute", 1.0 / 60.0, UnitSystem.IMPERIAL);

        /** gallon/s (US). */
        public static final FlowVolume.Unit GALLON_US_PER_SECOND = CUBIC_METER_PER_SECOND.deriveUnit("gal(US)/s",
                "US gallon per second", Volume.Unit.CONST_GALLON_US, UnitSystem.US_CUSTOMARY);

        /** gallon/min (US). */
        public static final FlowVolume.Unit GALLON_US_PER_MINUTE =
                GALLON_US_PER_SECOND.deriveUnit("gal(US)/min", "US gallon per minute", 1.0 / 60.0, UnitSystem.US_CUSTOMARY);

        /** gallon/hour (US). */
        public static final FlowVolume.Unit GALLON_US_PER_HOUR =
                GALLON_US_PER_SECOND.deriveUnit("gal(US)/h", "US gallon per hour", 1.0 / 3600.0, UnitSystem.US_CUSTOMARY);

        /** gallon/day (US). */
        public static final FlowVolume.Unit GALLON_US_PER_DAY =
                GALLON_US_PER_HOUR.deriveUnit("gal(US)/day", "US gallon per day", 1.0 / 24.0, UnitSystem.US_CUSTOMARY);

        /**
         * Create a new FlowVolume unit.
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
        public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name, final Scale scale,
                final UnitSystem unitSystem)
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
        public FlowVolume ofSi(final double si)
        {
            return FlowVolume.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new FlowVolume.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
