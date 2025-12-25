package org.djunits.quantity;

import java.util.List;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.OffsetLinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Direction encodes an absolute angle, such as EAST (0 degrees) or North (90 degrees). A counter-clockwise rotation is used.
 * Several conversion factors have been taken from
 * <a href="http://en.wikipedia.org/wiki/Conversion_of_units">http://en.wikipedia.org/wiki/Conversion_of_units</a>. <br>
 * <br>
 * Note that the EAST and NORTH Directions are <b>counter</b>clockwise. * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Direction extends Quantity.Absolute<Direction, Direction.Unit>
{
    /** Constant with value zero. */
    public static final Direction ZERO = Direction.ofSi(0.0);

    /** Constant with value one. */
    public static final Direction ONE = Direction.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Direction NaN = Direction.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Direction POSITIVE_INFINITY = Direction.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Direction NEGATIVE_INFINITY = Direction.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Direction POS_MAXVALUE = Direction.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Direction NEG_MAXVALUE = Direction.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Direction quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Direction(final double value, final Direction.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Direction quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Direction(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Direction.Unit.class, abbreviation));
    }

    /**
     * Construct Direction quantity.
     * @param value Scalar from which to construct this instance
     */
    public Direction(final Direction value)
    {
        super(value.si(), Direction.Unit.DEFAULT);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Direction instance based on an SI value.
     * @param si the si value
     * @return the Direction instance based on an SI value
     */
    public static Direction ofSi(final double si)
    {
        return new Direction(si, Direction.Unit.DEFAULT);
    }

    @Override
    public Direction instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Direction.Unit.SI_UNIT;
    }

    /**
     * Returns a Direction representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Direction
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Direction valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Direction based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Direction of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Direction and Direction, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Direction and Direction
     */
    public Dimensionless divide(final Direction v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Add a (relative) angle to this (absolute) direction.
     * @param v the angle value
     * @return this direction, increased by the Angle value
     */
    public Direction plus(final Angle v)
    {
        Direction result = ofSi(si() + v.si());
        result.setDisplayUnit(getDisplayUnit());
        return result;
    }

    /**
     * Subtract a (relative) angle from this (absolute) direction.
     * @param v the angle value
     * @return this direction, recreased by the Angle value
     */
    public Direction minus(final Angle v)
    {
        Direction result = ofSi(si() - v.si());
        result.setDisplayUnit(getDisplayUnit());
        return result;
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Direction.Unit encodes the units for (absolute) directions.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Direction.Unit>
    {
        /** The dimensions of direction: rad. */
        public static final SIUnit SI_UNIT = SIUnit.of("rad");

        /** The unit for direction with East as the origin and radians as the displacement. */
        public static final Direction.Unit EAST_RADIAN = new Direction.Unit(List.of("rad(E)"), "rad(E)", "radians (East)",
                new OffsetLinearScale(1.0, 0.0), UnitSystem.OTHER);

        /** The default unit for direction is East_Radian. */
        public static final Direction.Unit DEFAULT = EAST_RADIAN;

        /** The unit for direction with East as the origin and degrees as the displacement. */
        public static final Direction.Unit EAST_DEGREE = new Direction.Unit(List.of("deg(E)"), "\u00b0(E)", "degrees (East)",
                new OffsetLinearScale(Math.PI / 180.0, 0.0), UnitSystem.OTHER);

        /** The unit for direction with North as the origin and radians as the displacement. */
        public static final Direction.Unit NORTH_RADIAN = new Direction.Unit(List.of("rad(N)"), "rad(N)", "radians (North)",
                new OffsetLinearScale(1.0, Math.PI / 2.0), UnitSystem.OTHER);

        /** The unit for direction with North as the origin and degrees as the displacement. */
        public static final Direction.Unit NORTH_DEGREE = new Direction.Unit(List.of("deg(N)"), "\u00b0(N)", "degrees (North)",
                new OffsetLinearScale(Math.PI / 180.0, 90.0), UnitSystem.OTHER);

        /**
         * Create a new Direction unit.
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
            return DEFAULT;
        }

        @Override
        public Unit deriveUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Direction.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
