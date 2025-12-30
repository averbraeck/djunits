package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Force is an interaction that changes the motion of an object, measured in newtons (N).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Force extends Quantity.Relative<Force, Force.Unit>
{
    /** Constant with value zero. */
    public static final Force ZERO = Force.ofSi(0.0);

    /** Constant with value one. */
    public static final Force ONE = Force.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Force NaN = Force.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Force POSITIVE_INFINITY = Force.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Force NEGATIVE_INFINITY = Force.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Force POS_MAXVALUE = Force.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Force NEG_MAXVALUE = Force.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Force quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Force(final double value, final Force.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Force quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Force(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Force.Unit.class, abbreviation));
    }

    /**
     * Construct Force quantity.
     * @param value Scalar from which to construct this instance
     */
    public Force(final Force value)
    {
        super(value.si(), Force.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Force instance based on an SI value.
     * @param si the si value
     * @return the Force instance based on an SI value
     */
    public static Force ofSi(final double si)
    {
        return new Force(si, Force.Unit.SI);
    }

    @Override
    public Force instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Force.Unit.SI_UNIT;
    }

    /**
     * Returns a Force representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Force
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Force valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Force based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Force of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Force and Force, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Force and Force
     */
    public final Dimensionless divide(final Force v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Force and Length, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Force and Length
     */
    public final Energy times(final Length v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /**
     * Calculate the division of Force and LinearObjectDensity, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a division of Force and LinearObjectDensity
     */
    public final Energy divide(final LinearObjectDensity v)
    {
        return new Energy(this.si() / v.si(), Energy.Unit.SI);
    }

    /**
     * Calculate the division of Force and Energy, which results in a LinearObjectDensity scalar.
     * @param v scalar
     * @return scalar as a division of Force and Energy
     */
    public final LinearObjectDensity divide(final Energy v)
    {
        return new LinearObjectDensity(this.si() / v.si(), LinearObjectDensity.Unit.SI);
    }

    /**
     * Calculate the multiplication of Force and Speed, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of Force and Speed
     */
    public final Power times(final Speed v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the division of Force and Mass, which results in a Acceleration scalar.
     * @param v scalar
     * @return scalar as a division of Force and Mass
     */
    public final Acceleration divide(final Mass v)
    {
        return new Acceleration(this.si() / v.si(), Acceleration.Unit.SI);
    }

    /**
     * Calculate the division of Force and Acceleration, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a division of Force and Acceleration
     */
    public final Mass divide(final Acceleration v)
    {
        return new Mass(this.si() / v.si(), Mass.Unit.SI);
    }

    /**
     * Calculate the division of Force and Area, which results in a Pressure scalar.
     * @param v scalar
     * @return scalar as a division of Force and Area
     */
    public final Pressure divide(final Area v)
    {
        return new Pressure(this.si() / v.si(), Pressure.Unit.SI);
    }

    /**
     * Calculate the division of Force and Pressure, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a division of Force and Pressure
     */
    public final Area divide(final Pressure v)
    {
        return new Area(this.si() / v.si(), Area.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Force.Unit encodes the units of absorbed dose (of ionizing radiation).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Force.Unit, Force>
    {
        /** The dimensions of force: kgm/s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm/s2");

        /** Gray. */
        public static final Force.Unit NEWTON = new Force.Unit("N", "newton", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Force.Unit SI = NEWTON.generateSiPrefixes(false, false);

        /** Dyne. */
        public static final Force.Unit DYNE = NEWTON.deriveUnit("dyn", "dyne", 1E-5, UnitSystem.CGS);

        /** kilogram-force. */
        public static final Force.Unit KILOGRAM_FORCE =
                SI.deriveUnit("kgf", "kilogram-force", Acceleration.Unit.CONST_GRAVITY, UnitSystem.OTHER);

        /** ounce-force. */
        public static final Force.Unit OUNCE_FORCE = SI.deriveUnit("ozf", "ounce-force",
                Mass.Unit.CONST_OUNCE * Acceleration.Unit.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /** pound-force. */
        public static final Force.Unit POUND_FORCE =
                SI.deriveUnit("lbf", "pound-force", Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /** ton-force. */
        public static final Force.Unit SHORT_TON_FORCE = SI.deriveUnit("tnf", "ton-force",
                Mass.Unit.CONST_TON_SHORT * Acceleration.Unit.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /** sthene. */
        public static final Force.Unit STHENE = SI.deriveUnit("sn", "sthene", 1000.0, UnitSystem.MTS);

        /**
         * Create a new Force unit.
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
        public Force ofSi(final double si)
        {
            return Force.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Force.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
