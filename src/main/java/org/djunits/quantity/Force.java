package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Force is an interaction that changes the motion of an object, measured in newtons (N).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Force extends Quantity<Force>
{
    /** Constant with value zero. */
    public static final Force ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final Force ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Force NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Force POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Force NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Force POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Force NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Force quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public Force(final double value, final Force.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a Force quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public Force(final double valueInUnit, final Force.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a Force instance based on an SI value.
     * @param si the si value
     * @return the Force instance based on an SI value
     */
    public static Force ofSi(final double si)
    {
        return new Force(si, Force.Unit.SI, true);
    }

    /**
     * Instantiate a Force quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the Force instance based on an SI value with the given display unit
     */
    public static Force ofSi(final double siValue, final Force.Unit displayUnit)
    {
        return new Force(siValue, displayUnit, true);
    }

    @Override
    public Force instantiateSi(final double siValue, final UnitInterface<Force> displayUnit)
    {
        return new Force(siValue, (Force.Unit) displayUnit, true);
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
     * Returns a Force based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab Force representation of the value in its unit
     */
    public static Force of(final double valueInUnit, final Force.Unit unit)
    {
        return new Force(valueInUnit, unit);
    }

    /**
     * Returns a Force based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Force of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public Force.Unit getDisplayUnit()
    {
        return (Force.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of Force and Force, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Force and Force
     */
    public final Dimensionless divide(final Force v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of Force and Length, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Force and Length
     */
    public final Energy multiply(final Length v)
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
    public final Power multiply(final Speed v)
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
     * Force.Unit encodes the units of force.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Force>
    {
        /** The dimensions of force: kgm/s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm/s2");

        /** Gray. */
        public static final Force.Unit N =
                new Force.Unit("N", "N", "newton", IdentityScale.SCALE, UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final Force.Unit SI = (Unit) N.generateSiPrefixes(false, false);

        /** Dyne. */
        public static final Force.Unit dyn = N.deriveUnit("dyn", "dyne", 1E-5, UnitSystem.CGS);

        /** kilogram-force. */
        public static final Force.Unit kgf =
                SI.deriveUnit("kgf", "kilogram-force", Acceleration.Unit.CONST_GRAVITY, UnitSystem.OTHER);

        /** ounce-force. */
        public static final Force.Unit ozf = SI.deriveUnit("ozf", "ounce-force",
                Mass.Unit.CONST_OUNCE * Acceleration.Unit.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /** pound-force. */
        public static final Force.Unit lbf =
                SI.deriveUnit("lbf", "pound-force", Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /** ton-force. */
        public static final Force.Unit tnf = SI.deriveUnit("tnf", "ton-force",
                Mass.Unit.CONST_TON_SHORT * Acceleration.Unit.CONST_GRAVITY, UnitSystem.IMPERIAL);

        /** sthene. */
        public static final Force.Unit sn = SI.deriveUnit("sn", "sthene", 1000.0, UnitSystem.MTS);

        /**
         * Create a new Force unit.
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
        public Force ofSi(final double si, final UnitInterface<Force> displayUnit)
        {
            return new Force(si, (Unit) displayUnit, true);
        }

        @Override
        public Force.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Force.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

        @Override
        public Force.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

    }

}
