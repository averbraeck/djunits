package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * The Position quantity is the absolute version of Length, and contains an origin.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Position extends Quantity.Absolute<Position, Position.Unit>
{
    /** Constant with value zero. */
    public static final Position ZERO = Position.ofSi(0.0);

    /** Constant with value one. */
    public static final Position ONE = Position.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Position NaN = Position.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Position POSITIVE_INFINITY = Position.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Position NEGATIVE_INFINITY = Position.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Position POS_MAXVALUE = Position.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Position NEG_MAXVALUE = Position.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Position quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Position(final double value, final Position.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Position quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Position(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Position.Unit.class, abbreviation));
    }

    /**
     * Construct Position quantity.
     * @param value Scalar from which to construct this instance
     */
    public Position(final Position value)
    {
        super(value.si(), Position.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Position instance based on an SI value.
     * @param si the si value
     * @return the Position instance based on an SI value
     */
    public static Position ofSi(final double si)
    {
        return new Position(si, Position.Unit.SI);
    }

    @Override
    public Position instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Position.Unit.SI_UNIT;
    }

    /**
     * Returns a Position representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Position
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Position valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Position based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Position of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Position and Position, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Position and Position
     */
    public final Dimensionless divide(final Position v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Position.Unit encodes the units of absolute position, with an origin.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Position.Unit>
    {
        /** The dimensions of position: m. */
        public static final SIUnit SI_UNIT = SIUnit.of("m");

        /** meter. */
        public static final Position.Unit METER = new Position.Unit("m", "meter", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Position.Unit SI = METER.generateSiPrefixes(false, false);

        /** decameter. */
        public static final Position.Unit DECAMETER = Units.resolve(Position.Unit.class, "dam");

        /** hectometer. */
        public static final Position.Unit HECTOMETER = Units.resolve(Position.Unit.class, "hm");

        /** kilometer. */
        public static final Position.Unit KILOMETER = Units.resolve(Position.Unit.class, "km");

        /** decimeter. */
        public static final Position.Unit DECIMETER = Units.resolve(Position.Unit.class, "dm");

        /** centimeter. */
        public static final Position.Unit CENTIMETER = Units.resolve(Position.Unit.class, "cm");

        /** millimeter. */
        public static final Position.Unit MILLIMETER = Units.resolve(Position.Unit.class, "mm");

        /** micrometer. */
        public static final Position.Unit MICROMETER = Units.resolve(Position.Unit.class, "mum");

        /** nanometer. */
        public static final Position.Unit NANOMETER = Units.resolve(Position.Unit.class, "nm");

        /** picometer. */
        public static final Position.Unit PICOMETER = Units.resolve(Position.Unit.class, "pm");

        /** attometer. */
        public static final Position.Unit ATTOMETER = Units.resolve(Position.Unit.class, "am");

        /** femtometer. */
        public static final Position.Unit FEMTOMETER = Units.resolve(Position.Unit.class, "fm");

        /** foot (international) = 0.3048 m = 1/3 yd = 12 inches. */
        public static final Position.Unit FOOT =
                new Position.Unit("ft", "ft", "foot", new LinearScale(Length.Unit.CONST_FT), UnitSystem.IMPERIAL);

        /** inch (international) = 2.54 cm = 1/36 yd = 1/12 ft. */
        public static final Position.Unit INCH =
                new Position.Unit("in", "in", "inch", new LinearScale(Length.Unit.CONST_IN), UnitSystem.IMPERIAL);

        /** yard (international) = 0.9144 m = 3 ft = 36 in. */
        public static final Position.Unit YARD =
                new Position.Unit("yd", "yd", "yard", new LinearScale(Length.Unit.CONST_YD), UnitSystem.IMPERIAL);

        /** mile (international) = 5280 ft = 1760 yd. */
        public static final Position.Unit MILE =
                new Position.Unit("mi", "mi", "mile", new LinearScale(Length.Unit.CONST_MI), UnitSystem.IMPERIAL);

        /** nautical mile (international) = 1852 m. */
        public static final Position.Unit NAUTICAL_MILE =
                new Position.Unit("NM", "Nautical Mile", Length.Unit.CONST_NM, UnitSystem.OTHER);

        /** Astronomical Unit = 149,597,870,700 m. */
        public static final Position.Unit ASTRONOMICAL_UNIT =
                new Position.Unit("AU", "Astronomical Unit", 149_597_870_700.0, UnitSystem.OTHER);

        /** Lightyear = 9,460,730,472,580,800 m. */
        public static final Position.Unit LIGHTYEAR =
                new Position.Unit("ly", "lightyear", 9_460_730_472_580_800.0, UnitSystem.OTHER);

        /** Parsec = AU * 648,000 / PI. */
        public static final Position.Unit PARSEC =
                new Position.Unit("Pc", "Parsec", 149_597_870_700.0 * 648_000.0 / Math.PI, UnitSystem.OTHER);

        /** Angstrom = 10^-10 m. */
        public static final Position.Unit ANGSTROM =
                new Position.Unit("A", "\u212B", "Angstrom", new LinearScale(1.0E-10), UnitSystem.OTHER);

        /**
         * Create a new Position unit.
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
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Position.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
