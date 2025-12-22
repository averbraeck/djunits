package org.djunits.quantity;

import java.util.List;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * AbsorbedDose (of ionizing radiation) quantity.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */

public class Position extends Quantity.Relative<Position, Position.Unit>
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
     * Instantiate a AbsorbedDose quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Position(final double value, final Position.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a AbsorbedDose quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Position(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Position.Unit.class, abbreviation));
    }

    /**
     * Construct AbsorbedDose quantity.
     * @param value Scalar from which to construct this instance
     */
    public Position(final Position value)
    {
        super(value.si(), Position.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a AbsorbedDose instance based on an SI value.
     * @param si the si value
     * @return the AbsorbedDose instance based on an SI value
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
     * Returns a AbsorbedDose representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a AbsorbedDose
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Position valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a AbsorbedDose based on a value and the textual representation of the unit, which can be localized.
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
     * Calculate the division of AbsorbedDose and AbsorbedDose, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of AbsorbedDose and AbsorbedDose
     */
    public final Dimensionless divide(final Position v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * AbsorbedDose.Unit encodes the units of absorbed dose (of ionizing radiation).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Position.Unit>
    {
        /** The dimensions of the absorbed dose: m2/s2 [rad, sr, kg, m, s, A, K, mol, cd]. */
        public static final SIUnit SI_UNIT = new SIUnit(new byte[] {0, 0, 0, 2, -2, 0, 0, 0, 0});

        /** Gray. */
        public static final Position.Unit GRAY = new Position.Unit("Gy", "gray", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Position.Unit SI = GRAY;

        /** mGy. */
        public static final Position.Unit MILLIGRAY =
                new Position.Unit("mGy", "milligray", 1.0E-3, UnitSystem.SI_DERIVED);

        /** &#181;Gy. */
        public static final Position.Unit MICROGRAY =
                new Position.Unit(List.of("muGy"), "\u03BCGy", "microgray", new LinearScale(1.0E-6), UnitSystem.SI_DERIVED);

        /** erg/g. */
        public static final Position.Unit ERG_PER_GRAM =
                new Position.Unit("erg/g", "erg per gram", 1.0E-4, UnitSystem.CGS);

        /** rad. */
        public static final Position.Unit RAD = new Position.Unit("rad", "rad", 1.0E-2, UnitSystem.CGS);

        /**
         * Create a new AbsorbedDose unit.
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
            return GRAY;
        }

        @Override
        public Unit deriveUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
        {
            return new Position.Unit(textualAbbreviations, displayAbbreviation, name, scale, unitSystem);
        }

    }
}
