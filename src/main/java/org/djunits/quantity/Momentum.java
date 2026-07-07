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
 * Momentum is the product of an object's mass and velocity, measured in kilogram meters per second (kgm/s).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Momentum extends Quantity<Momentum>
{
    /** Constant with value zero. */
    public static final Momentum ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final Momentum ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Momentum NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Momentum POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Momentum NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Momentum POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Momentum NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Momentum quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public Momentum(final double value, final Momentum.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a Momentum quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public Momentum(final double valueInUnit, final Momentum.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a Momentum instance based on an SI value.
     * @param si the si value
     * @return the Momentum instance based on an SI value
     */
    public static Momentum ofSi(final double si)
    {
        return new Momentum(si, Momentum.Unit.SI, true);
    }

    /**
     * Instantiate a Momentum quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the Momentum instance based on an SI value with the given display unit
     */
    public static Momentum ofSi(final double siValue, final Momentum.Unit displayUnit)
    {
        return new Momentum(siValue, displayUnit, true);
    }

    @Override
    public Momentum instantiateSi(final double siValue, final UnitInterface<Momentum> displayUnit)
    {
        return new Momentum(siValue, (Momentum.Unit) displayUnit, true);
    }

    /**
     * Returns a Momentum representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Momentum
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Momentum valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Momentum based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab Momentum representation of the value in its unit
     */
    public static Momentum of(final double valueInUnit, final Momentum.Unit unit)
    {
        return new Momentum(valueInUnit, unit);
    }

    /**
     * Returns a Momentum based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Momentum of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public Momentum.Unit getDisplayUnit()
    {
        return (Momentum.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of Momentum and Momentum, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Momentum and Momentum
     */
    public Dimensionless divide(final Momentum v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the division of Momentum and Speed, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a division of Momentum and Speed
     */
    public Mass divide(final Speed v)
    {
        return new Mass(this.si() / v.si(), Mass.Unit.SI);
    }

    /**
     * Calculate the division of Momentum and Mass, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a division of Momentum and Mass
     */
    public Speed divide(final Mass v)
    {
        return new Speed(this.si() / v.si(), Speed.Unit.SI);
    }

    /**
     * Calculate the division of Momentum and Length, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a division of Momentum and Length
     */
    public FlowMass divide(final Length v)
    {
        return new FlowMass(this.si() / v.si(), FlowMass.Unit.SI);
    }

    /**
     * Calculate the division of Momentum and FlowMass, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Momentum and FlowMass
     */
    public Length divide(final FlowMass v)
    {
        return new Length(this.si() / v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the multiplication of Momentum and Speed, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Momentum and Speed
     */
    public Energy multiply(final Speed v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /**
     * Calculate the multiplication of Momentum and Acceleration, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Momentum and Acceleration
     */
    public Power multiply(final Acceleration v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Momentum.Unit encodes a unit for the product of an object's mass and velocity.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Momentum>
    {
        /** The dimensions of momentum: kgm/s. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm/s");

        /** kgm/s. */
        public static final Momentum.Unit kgm_s =
                new Momentum.Unit("kgm/s", "kilogram meter per second", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Momentum.Unit SI = kgm_s;

        /**
         * Create a new Momentum unit.
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
        public Momentum ofSi(final double si, final UnitInterface<Momentum> displayUnit)
        {
            return new Momentum(si, (Unit) displayUnit, true);
        }

        @Override
        public Momentum.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Momentum.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
