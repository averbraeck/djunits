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
 * Pressure is the force exerted per unit area, measured in pascals (Pa).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Pressure extends Quantity<Pressure>
{
    /** Constant with value zero. */
    public static final Pressure ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final Pressure ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Pressure NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Pressure POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Pressure NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Pressure POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Pressure NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Pressure quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public Pressure(final double value, final Pressure.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a Pressure quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public Pressure(final double valueInUnit, final Pressure.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a Pressure instance based on an SI value.
     * @param si the si value
     * @return the Pressure instance based on an SI value
     */
    public static Pressure ofSi(final double si)
    {
        return new Pressure(si, Pressure.Unit.SI, true);
    }

    /**
     * Instantiate a Pressure quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the Pressure instance based on an SI value with the given display unit
     */
    public static Pressure ofSi(final double siValue, final Pressure.Unit displayUnit)
    {
        return new Pressure(siValue, displayUnit, true);
    }

    @Override
    public Pressure instantiateSi(final double siValue, final UnitInterface<Pressure> displayUnit)
    {
        return new Pressure(siValue, (Pressure.Unit) displayUnit, true);
    }

    /**
     * Returns a Pressure representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Pressure
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Pressure valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Pressure based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab Pressure representation of the value in its unit
     */
    public static Pressure of(final double valueInUnit, final Pressure.Unit unit)
    {
        return new Pressure(valueInUnit, unit);
    }

    /**
     * Returns a Pressure based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Pressure of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public Pressure.Unit getDisplayUnit()
    {
        return (Pressure.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of Pressure and Pressure, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Pressure and Pressure
     */
    public Dimensionless divide(final Pressure v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of Pressure and Area, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Pressure and Area
     */
    public Force multiply(final Area v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Pressure and Volume, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Pressure and Volume
     */
    public Energy multiply(final Volume v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Pressure.Unit encodes the units of force exerted per unit area.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Pressure>
    {
        /** The dimensions of pressure: kg/m.s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg/ms2");

        /** Pascal. */
        public static final Pressure.Unit Pa =
                new Pressure.Unit("Pa", "Pa", "pascal", IdentityScale.SCALE, UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final Pressure.Unit SI = (Unit) Pa.generateSiPrefixes(false, false);

        /** hectopascal. */
        public static final Pressure.Unit hPa = Units.resolve(Pressure.Unit.class, "hPa");

        /** kilopascal. */
        public static final Pressure.Unit kPa = Units.resolve(Pressure.Unit.class, "kPa");

        /** standard atmosphere. */
        public static final Pressure.Unit atm = Pa.deriveUnit("atm", "atmosphere (standard)", 101325.0, UnitSystem.OTHER);

        /** torr. */
        public static final Pressure.Unit torr = atm.deriveUnit("torr", "Torr", 1.0 / 760.0, UnitSystem.OTHER);

        /** technical atmosphere = kgf/cm2. */
        public static final Pressure.Unit at =
                Pa.deriveUnit("at", "atmosphere (technical)", Acceleration.Unit.CONST_GRAVITY / 1.0E-4, UnitSystem.OTHER);

        /** barye = dyne/cm2. */
        public static final Pressure.Unit Ba = Pa.deriveUnit("Ba", "barye", 1.0E-5 / 1.0E-4, UnitSystem.CGS);

        /** bar. */
        public static final Pressure.Unit bar = Pa.deriveUnit("bar", "bar", 1.0E5, UnitSystem.OTHER);

        /** millibar. */
        public static final Pressure.Unit mbar = bar.deriveUnit("mbar", "millibar", 1.0E-3, UnitSystem.OTHER);

        /** cm Hg. */
        public static final Pressure.Unit cmHg = Pa.deriveUnit("cmHg", "centimeter mercury", 1333.224, UnitSystem.OTHER);

        /** mm Hg. */
        public static final Pressure.Unit mmHg = Pa.deriveUnit("mmHg", "millimeter mercury", 133.3224, UnitSystem.OTHER);

        /** foot Hg. */
        public static final Pressure.Unit ftHg = Pa.deriveUnit("ftHg", "foot mercury", 40.63666E3, UnitSystem.IMPERIAL);

        /** inch Hg. */
        public static final Pressure.Unit inHg = Pa.deriveUnit("inHg", "inch mercury", 3.386389E3, UnitSystem.IMPERIAL);

        /** kilogram-force per square millimeter. */
        public static final Pressure.Unit kgf_mm2 = Pa.deriveUnit("kgf/mm2", "kilogram-force per square millimeter",
                Acceleration.Unit.CONST_GRAVITY / 1.0E-6, UnitSystem.OTHER);

        /** pound-force per square foot. */
        public static final Pressure.Unit lbf_ft2 = Pa.deriveUnit("lbf/ft2", "pound-force per square foot",
                Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY / (Length.Unit.CONST_FT * Length.Unit.CONST_FT),
                UnitSystem.IMPERIAL);

        /** pound-force per square inch. */
        public static final Pressure.Unit lbf_in2 = Pa.deriveUnit("lbf/in2", "pound-force per square inch",
                Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY / (Length.Unit.CONST_IN * Length.Unit.CONST_IN),
                UnitSystem.IMPERIAL);

        /** psi = pound-force per square inch. */
        public static final Pressure.Unit psi = lbf_in2;

        /** pieze. */
        public static final Pressure.Unit pz = Pa.deriveUnit("pz", "pi\u00E8ze", 1000.0, UnitSystem.MTS);

        /**
         * Create a new Pressure unit.
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
        public Pressure ofSi(final double si, final UnitInterface<Pressure> displayUnit)
        {
            return new Pressure(si, (Unit) displayUnit, true);
        }

        @Override
        public Pressure.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Pressure.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

        @Override
        public Pressure.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

    }

}
