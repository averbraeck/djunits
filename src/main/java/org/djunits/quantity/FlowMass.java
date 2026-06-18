package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Flow mass: The rate of mass passing through a surface per unit time, measured in kilograms per second (kg/s).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class FlowMass extends Quantity<FlowMass>
{
    /** Constant with value zero. */
    public static final FlowMass ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final FlowMass ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FlowMass NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FlowMass POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FlowMass NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final FlowMass POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final FlowMass NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a FlowMass quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public FlowMass(final double value, final FlowMass.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a FlowMass quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public FlowMass(final double valueInUnit, final FlowMass.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a FlowMass instance based on an SI value.
     * @param si the si value
     * @return the FlowMass instance based on an SI value
     */
    public static FlowMass ofSi(final double si)
    {
        return new FlowMass(si, FlowMass.Unit.SI, true);
    }

    /**
     * Instantiate a FlowMass quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the FlowMass instance based on an SI value with the given display unit
     */
    public static FlowMass ofSi(final double siValue, final FlowMass.Unit displayUnit)
    {
        return new FlowMass(siValue, displayUnit, true);
    }

    @Override
    public FlowMass instantiateSi(final double siValue, final UnitInterface<FlowMass> displayUnit)
    {
        return new FlowMass(siValue, (FlowMass.Unit) displayUnit, true);
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
     * Returns a FlowMass based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab FlowMass representation of the value in its unit
     */
    public static FlowMass of(final double valueInUnit, final FlowMass.Unit unit)
    {
        return new FlowMass(valueInUnit, unit);
    }

    /**
     * Returns a FlowMass based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FlowMass of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public FlowMass.Unit getDisplayUnit()
    {
        return (FlowMass.Unit) super.getDisplayUnit();
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
     * FlowMass.Unit encodes the units of mass flow.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<FlowMass>
    {
        /** The dimensions of flow mass: kg/s. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg/s");

        /** kg/s. */
        public static final FlowMass.Unit kg_s = new FlowMass.Unit("kg/s", "kilogram per second", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final FlowMass.Unit SI = kg_s;

        /** lb/s. */
        public static final FlowMass.Unit lb_s =
                kg_s.deriveUnit("lb/s", "lb/s", "pound per second", Mass.Unit.CONST_LB, UnitSystem.IMPERIAL, null);

        /**
         * Create a new FlowMass unit.
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
        public FlowMass ofSi(final double si, final UnitInterface<FlowMass> displayUnit)
        {
            return new FlowMass(si, (Unit) displayUnit, true);
        }

        @Override
        public FlowMass.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new FlowMass.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
