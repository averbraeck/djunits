package org.djunits.quantity;

import java.util.List;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Pressure is the force exerted per unit area, measured in pascals (Pa).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Pressure extends Quantity.Relative<Pressure, Pressure.Unit>
{
    /** Constant with value zero. */
    public static final Pressure ZERO = Pressure.ofSi(0.0);

    /** Constant with value one. */
    public static final Pressure ONE = Pressure.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Pressure NaN = Pressure.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Pressure POSITIVE_INFINITY = Pressure.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Pressure NEGATIVE_INFINITY = Pressure.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Pressure POS_MAXVALUE = Pressure.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Pressure NEG_MAXVALUE = Pressure.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Pressure quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Pressure(final double value, final Pressure.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Pressure quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Pressure(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Pressure.Unit.class, abbreviation));
    }

    /**
     * Construct Pressure quantity.
     * @param value Scalar from which to construct this instance
     */
    public Pressure(final Pressure value)
    {
        super(value.si(), Pressure.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Pressure instance based on an SI value.
     * @param si the si value
     * @return the Pressure instance based on an SI value
     */
    public static Pressure ofSi(final double si)
    {
        return new Pressure(si, Pressure.Unit.SI);
    }

    @Override
    public Pressure instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Pressure.Unit.SI_UNIT;
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
     * Returns a Pressure based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Pressure of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Pressure and Pressure, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Pressure and Pressure
     */
    public final Dimensionless divide(final Pressure v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Pressure and Area, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Pressure and Area
     */
    public final Force times(final Area v)
    {
        return new Force(this.si() * v.si(), Force.Unit.SI);
    }

    /**
     * Calculate the multiplication of Pressure and Volume, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Pressure and Volume
     */
    public final Energy times(final Volume v)
    {
        return new Energy(this.si() * v.si(), Energy.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Pressure.Unit encodes the units of force exerted per unit area.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Pressure.Unit>
    {
        /** The dimensions of pressure: kg/m.s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg/ms2");

        /** Pascal. */
        public static final Pressure.Unit PASCAL = new Pressure.Unit("Pa", "pascal", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Pressure.Unit SI = PASCAL.generateSiPrefixes(false, false);

        /** hectoPascal. */
        public static final Pressure.Unit HECTOPASCAL = Units.resolve(Pressure.Unit.class, "hPa");

        /** kiloPascal. */
        public static final Pressure.Unit KILOPASCAL = Units.resolve(Pressure.Unit.class, "kPa");

        /** standard atmosphere. */
        public static final Pressure.Unit ATMOSPHERE_STANDARD =
                PASCAL.deriveUnit("atm", "atmosphere (standard)", 101325.0, UnitSystem.OTHER);

        /** torr. */
        public static final Pressure.Unit TORR = ATMOSPHERE_STANDARD.deriveUnit("torr", "Torr", 1.0 / 760.0, UnitSystem.OTHER);

        /** technical atmosphere = kgf/cm2. */
        public static final Pressure.Unit ATMOSPHERE_TECHNICAL =
                PASCAL.deriveUnit("at", "atmosphere (technical)", Acceleration.Unit.CONST_GRAVITY / 1.0E-4, UnitSystem.OTHER);

        /** barye = dyne/cm2. */
        public static final Pressure.Unit BARYE = PASCAL.deriveUnit("Ba", "barye", 1.0E-5 / 1.0E-4, UnitSystem.CGS);

        /** bar. */
        public static final Pressure.Unit BAR = PASCAL.deriveUnit("bar", "bar", 1.0E5, UnitSystem.OTHER);

        /** millibar. */
        public static final Pressure.Unit MILLIBAR = BAR.deriveUnit("mbar", "millibar", 1.0E-3, UnitSystem.OTHER);

        /** cm Hg. */
        public static final Pressure.Unit CENTIMETER_MERCURY =
                PASCAL.deriveUnit("cmHg", "centimeter mercury", 1333.224, UnitSystem.OTHER);

        /** mm Hg. */
        public static final Pressure.Unit MILLIMETER_MERCURY =
                PASCAL.deriveUnit("mmHg", "millimeter mercury", 133.3224, UnitSystem.OTHER);

        /** foot Hg. */
        public static final Pressure.Unit FOOT_MERCURY =
                PASCAL.deriveUnit("ftHg", "foot mercury", 40.63666E3, UnitSystem.IMPERIAL);

        /** inch Hg. */
        public static final Pressure.Unit INCH_MERCURY =
                PASCAL.deriveUnit("inHg", "inch mercury", 3.386389E3, UnitSystem.IMPERIAL);

        /** kilogram-force per square millimeter. */
        public static final Pressure.Unit KGF_PER_SQUARE_MM = PASCAL.deriveUnit("kgf/mm2",
                "kilogram-force per square millimeter", Acceleration.Unit.CONST_GRAVITY / 1.0E-6, UnitSystem.OTHER);

        /** pound-force per square foot. */
        public static final Pressure.Unit POUND_PER_SQUARE_FOOT = PASCAL.deriveUnit("lbf/ft2", "pound-force per square foot",
                Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY / (Length.Unit.CONST_FT * Length.Unit.CONST_FT),
                UnitSystem.IMPERIAL);

        /** pound-force per square inch. */
        public static final Pressure.Unit POUND_PER_SQUARE_INCH = PASCAL.deriveUnit("lbf/in2", "pound-force per square inch",
                Mass.Unit.CONST_LB * Acceleration.Unit.CONST_GRAVITY / (Length.Unit.CONST_IN * Length.Unit.CONST_IN),
                UnitSystem.IMPERIAL);

        /** pieze. */
        public static final Pressure.Unit PIEZE = PASCAL.deriveUnit("pz", "pi\u00E8ze", 1000.0, UnitSystem.MTS);

        /**
         * Create a new Pressure unit.
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
         * @param textualAbbreviations the textual abbreviations of the unit, where the first one in the list is the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         */
        public Unit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
        {
            super(textualAbbreviations, displayAbbreviation, name, scale, unitSystem);
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
        public Unit deriveUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Pressure.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
