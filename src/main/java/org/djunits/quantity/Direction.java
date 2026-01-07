package org.djunits.quantity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.djunits.quantity.def.AbsoluteQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Units;
import org.djunits.unit.si.SIUnit;
import org.djutils.exceptions.Throw;

/**
 * Direction is the absolute equivalent of Angle, and can, e.g., represent an angle relative to a defined "zero" angle such as
 * NORTH or EAST.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Direction extends AbsoluteQuantity<Direction, Angle, Angle.Unit, Direction.DirectionReference>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Direction quantity with a unit and a reference point.
     * @param value the angle value, expressed in a angle unit
     * @param unit the angle unit in which the value is expressed, relative to the reference point
     * @param reference the reference point of this direction
     */
    public Direction(final double value, final Angle.Unit unit, final DirectionReference reference)
    {
        super(new Angle(value, unit), reference);
    }

    /**
     * Instantiate a Direction quantity with a unit, expressed as a String, and a reference point.
     * @param value the angle value, expressed in the unit, relative to the reference point
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     * @param reference the reference point of this direction
     */
    public Direction(final double value, final String abbreviation, final DirectionReference reference)
    {
        this(value, Units.resolve(Angle.Unit.class, abbreviation), reference);
    }

    /**
     * Instantiate a Direction instance based on an angle and a reference point.
     * @param angle the angle, relative to the reference point
     * @param reference the reference point of this direction
     */
    public Direction(final Angle angle, final DirectionReference reference)
    {
        super(angle, reference);
    }

    /**
     * Return a Direction instance based on an SI value and a reference point.
     * @param si the angle si value, relative to the reference point
     * @param reference the reference point of this direction
     * @return the Direction instance based on an SI value
     */
    public static Direction ofSi(final double si, final DirectionReference reference)
    {
        return new Direction(si, Angle.Unit.SI, reference);
    }

    @Override
    public Direction instantiate(final Angle angle, final DirectionReference reference)
    {
        return new Direction(angle, reference);
    }

    @Override
    public SIUnit siUnit()
    {
        return Angle.Unit.SI_UNIT;
    }

    /**
     * Returns a Direction representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Direction
     * @param reference the reference point of this direction
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Direction valueOf(final String text, final DirectionReference reference)
    {
        return new Direction(Quantity.valueOf(text, Angle.ZERO), reference);
    }

    /**
     * Returns a Direction based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @param reference the reference point of this direction
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Direction of(final double value, final String unitString, final DirectionReference reference)
    {
        return new Direction(Quantity.of(value, unitString, Angle.ZERO), reference);
    }

    @Override
    public Angle subtract(final Direction other)
    {
        Throw.when(!getReference().equals(other.getReference()), IllegalArgumentException.class,
                "cannot subtract two absolute quantities with a different reference: %s <> %s", getReference().getId(),
                other.getReference().getId());
        return Angle.ofSi(si() - other.si()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Direction add(final Angle other)
    {
        return new Direction(Angle.ofSi(si() + other.si()).setDisplayUnit(getDisplayUnit()), getReference());
    }

    @Override
    public Direction subtract(final Angle other)
    {
        return new Direction(Angle.ofSi(si() - other.si()).setDisplayUnit(getDisplayUnit()), getReference());
    }

    /**
     * The reference class to define a reference point for the direction.
     */
    public static final class DirectionReference implements Reference
    {
        /** the list of possible reference points to use. */
        private static Map<String, DirectionReference> referenceList = new LinkedHashMap<>();

        /** North is zero. */
        public static final DirectionReference NORTH = new DirectionReference("NORTH", "North = 0 degrees (counter-clockwise)");

        /** East is zero. */
        public static final DirectionReference EAST = new DirectionReference("EAST", "East = 0 degrees (counter-clockwise)");

        /** The id. */
        private String id;

        /** The explanation. */
        private String name;

        /**
         * Define a new reference point for the direction.
         * @param id the id
         * @param name the name or explanation
         */
        private DirectionReference(final String id, final String name)
        {
            this.id = id;
            this.name = name;
            referenceList.put(id, this);
        }

        /**
         * Define a new reference point for the direction.
         * @param id the id
         * @param name the name or explanation
         */
        public static void add(final String id, final String name)
        {
            new DirectionReference(id, name);
        }

        /**
         * Get a reference point for the direction, based on its id. Return null when the id could not be found.
         * @param id the id
         * @return the DirectionReference object
         */
        public static DirectionReference get(final String id)
        {
            return referenceList.get(id);
        }

        @Override
        public String getId()
        {
            return this.id;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(this.id, this.name);
        }

        @SuppressWarnings("checkstyle:needbraces")
        @Override
        public boolean equals(final Object obj)
        {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DirectionReference other = (DirectionReference) obj;
            return Objects.equals(this.id, other.id) && Objects.equals(this.name, other.name);
        }

        @Override
        public String toString()
        {
            return this.id;
        }
    }
}
