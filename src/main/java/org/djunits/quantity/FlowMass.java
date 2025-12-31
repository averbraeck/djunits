package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Flow mass: The rate of mass passing through a surface per unit time, measured in kilograms per second (kg/s).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class FlowMass extends Quantity<FlowMass, FlowMass.Unit>
{
    /** Constant with value zero. */
    public static final FlowMass ZERO = FlowMass.ofSi(0.0);

    /** Constant with value one. */
    public static final FlowMass ONE = FlowMass.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FlowMass NaN = FlowMass.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FlowMass POSITIVE_INFINITY = FlowMass.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FlowMass NEGATIVE_INFINITY = FlowMass.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final FlowMass POS_MAXVALUE = FlowMass.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final FlowMass NEG_MAXVALUE = FlowMass.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a FlowMass quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public FlowMass(final double value, final FlowMass.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a FlowMass quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public FlowMass(final double value, final String abbreviation)
    {
        this(value, Units.resolve(FlowMass.Unit.class, abbreviation));
    }

    /**
     * Construct FlowMass quantity.
     * @param value Scalar from which to construct this instance
     */
    public FlowMass(final FlowMass value)
    {
        super(value.si(), FlowMass.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a FlowMass instance based on an SI value.
     * @param si the si value
     * @return the FlowMass instance based on an SI value
     */
    public static FlowMass ofSi(final double si)
    {
        return new FlowMass(si, FlowMass.Unit.SI);
    }

    @Override
    public FlowMass instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return FlowMass.Unit.SI_UNIT;
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
        return Quantity.valueOf(text, ZERO);
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
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of FlowMass and FlowMass, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of FlowMass and FlowMass
     */
    public final Dimensionless divide(final FlowMass v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of FlowMass and Duration, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a multiplication of FlowMass and Duration
     */
    public final Mass multiply(final Duration v)
    {
        return new Mass(this.si() * v.si(), Mass.Unit.SI);
    }

    /**
     * Calculate the division of FlowMass and Frequency, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a division of FlowMass and Frequency
     */
    public final Mass divide(final Frequency v)
    {
        return new Mass(this.si() / v.si(), Mass.Unit.SI);
    }

    /**
     * Calculate the division of FlowMass and Mass, which results in a Frequency scalar.
     * @param v scalar
     * @return scalar as a division of FlowMass and Mass
     */
    public final Frequency divide(final Mass v)
    {
        return new Frequency(this.si() / v.si(), Frequency.Unit.SI);
    }

    /**
     * Calculate the multiplication of FlowMass and Speed, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of FlowMass and Speed
     */
    public final Force multiply(final Speed v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the division of FlowMass and FlowVolume, which results in a Density scalar.
     * @param v scalar
     * @return scalar as a division of FlowMass and FlowVolume
     */
    public final Density divide(final FlowVolume v)
    {
        return new Density(this.si() / v.si(), Density.Unit.SI);
    }

    /**
     * Calculate the division of FlowMass and Density, which results in a FlowVolume scalar.
     * @param v scalar
     * @return scalar as a division of FlowMass and Density
     */
    public final FlowVolume divide(final Density v)
    {
        return new FlowVolume(this.si() / v.si(), FlowVolume.Unit.SI);
    }

    /**
     * Calculate the multiplication of FlowMass and Length, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a multiplication of FlowMass and Length
     */
    public final Momentum multiply(final Length v)
    {
        return new Momentum(this.si() * v.si(), Momentum.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * FlowMass.Unit encodes the units of absorbed dose (of ionizing radiation).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<FlowMass.Unit, FlowMass>
    {
        /** The dimensions of flow mass: kg/s. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg/s");

        /** kg/s. */
        public static final FlowMass.Unit KILOGRAM_PER_SECOND =
                new FlowMass.Unit("kg/s", "kilogram per second", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final FlowMass.Unit SI = KILOGRAM_PER_SECOND;

        /** lb/s. */
        public static final FlowMass.Unit POUND_PER_SECOND =
                KILOGRAM_PER_SECOND.deriveUnit("lb/s", "pound per second", Mass.Unit.CONST_LB, UnitSystem.IMPERIAL);

        /**
         * Create a new FlowMass unit.
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

        /** {@inheritDoc} */
        @Override
        public FlowMass ofSi(final double si)
        {
            return FlowMass.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new FlowMass.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
