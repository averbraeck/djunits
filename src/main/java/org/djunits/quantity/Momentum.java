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
 * Momentum is the product of an object's mass and velocity, measured in kilogram meters per second (kgm/s).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Momentum extends Quantity<Momentum, Momentum.Unit>
{
    /** Constant with value zero. */
    public static final Momentum ZERO = Momentum.ofSi(0.0);

    /** Constant with value one. */
    public static final Momentum ONE = Momentum.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Momentum NaN = Momentum.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Momentum POSITIVE_INFINITY = Momentum.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Momentum NEGATIVE_INFINITY = Momentum.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Momentum POS_MAXVALUE = Momentum.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Momentum NEG_MAXVALUE = Momentum.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Momentum quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Momentum(final double value, final Momentum.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Momentum quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Momentum(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Momentum.Unit.class, abbreviation));
    }

    /**
     * Construct Momentum quantity.
     * @param value Scalar from which to construct this instance
     */
    public Momentum(final Momentum value)
    {
        super(value.si(), Momentum.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Momentum instance based on an SI value.
     * @param si the si value
     * @return the Momentum instance based on an SI value
     */
    public static Momentum ofSi(final double si)
    {
        return new Momentum(si, Momentum.Unit.SI);
    }

    @Override
    public Momentum instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Momentum.Unit.SI_UNIT;
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
     * Returns a Momentum based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Momentum of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Momentum and Momentum, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Momentum and Momentum
     */
    public final Dimensionless divide(final Momentum v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the division of Momentum and Speed, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a division of Momentum and Speed
     */
    public final Mass divide(final Speed v)
    {
        return new Mass(this.si() / v.si(), Mass.Unit.SI);
    }

    /**
     * Calculate the division of Momentum and Mass, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a division of Momentum and Mass
     */
    public final Speed divide(final Mass v)
    {
        return new Speed(this.si() / v.si(), Speed.Unit.SI);
    }

    /**
     * Calculate the division of Momentum and Length, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a division of Momentum and Length
     */
    public final FlowMass divide(final Length v)
    {
        return new FlowMass(this.si() / v.si(), FlowMass.Unit.SI);
    }

    /**
     * Calculate the division of Momentum and FlowMass, which results in a Length scalar.
     * @param v scalar
     * @return scalar as a division of Momentum and FlowMass
     */
    public final Length divide(final FlowMass v)
    {
        return new Length(this.si() / v.si(), Length.Unit.SI);
    }

    /**
     * Calculate the multiplication of Momentum and Speed, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Momentum and Speed
     */
    public final Energy multiply(final Speed v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /**
     * Calculate the multiplication of Momentum and Acceleration, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Momentum and Acceleration
     */
    public final Power multiply(final Acceleration v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Momentum.Unit encodes a unit for the product of an object's mass and velocity.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Momentum.Unit, Momentum>
    {
        /** The dimensions of momentum: kgm/s. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm/s");

        /** kgm/s. */
        public static final Momentum.Unit KILOGRAM_METER_PER_SECOND =
                new Momentum.Unit("kgm/s", "kilogram meter per second", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Momentum.Unit SI = KILOGRAM_METER_PER_SECOND;

        /**
         * Create a new Momentum unit.
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
        public Momentum ofSi(final double si)
        {
            return Momentum.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Momentum.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
