package org.djunits.quantity;

import org.djunits.quantity.def.AbsoluteQuantity;
import org.djunits.quantity.def.AbstractReference;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Units;
import org.djunits.unit.si.SIUnit;

/**
 * Position is the absolute equivalent of Length, and can, e.g., represent an absolute offset relative to a defined origin.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Position extends AbsoluteQuantity<Position, Length, Length.Unit, Position.Reference>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Position quantity with a unit and a reference point.
     * @param value the length value, expressed in a length unit
     * @param unit the length unit in which the value is expressed, relative to the reference point
     * @param reference the reference point of this position
     */
    public Position(final double value, final Length.Unit unit, final Reference reference)
    {
        super(new Length(value, unit), reference);
    }

    /**
     * Instantiate a Position quantity with a unit, expressed as a String, and a reference point.
     * @param value the length value, expressed in the unit, relative to the reference point
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     * @param reference the reference point of this position
     */
    public Position(final double value, final String abbreviation, final Reference reference)
    {
        this(value, Units.resolve(Length.Unit.class, abbreviation), reference);
    }

    /**
     * Instantiate a Position instance based on an length and a reference point.
     * @param length the length, relative to the reference point
     * @param reference the reference point of this position
     */
    public Position(final Length length, final Reference reference)
    {
        super(length, reference);
    }

    /**
     * Return a Position instance based on an SI value and a reference point.
     * @param si the length si value, relative to the reference point
     * @param reference the reference point of this position
     * @return the Position instance based on an SI value
     */
    public static Position ofSi(final double si, final Reference reference)
    {
        return new Position(si, Length.Unit.SI, reference);
    }

    @Override
    public Position instantiate(final Length length, final Reference reference)
    {
        return new Position(length, reference);
    }

    @Override
    public SIUnit siUnit()
    {
        return Length.Unit.SI_UNIT;
    }

    /**
     * Returns a Position representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Position
     * @param reference the reference point of this position
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Position valueOf(final String text, final Reference reference)
    {
        return new Position(Quantity.valueOf(text, Length.ZERO), reference);
    }

    /**
     * Returns a Position based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @param reference the reference point of this position
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Position of(final double value, final String unitString, final Reference reference)
    {
        return new Position(Quantity.of(value, unitString, Length.ZERO), reference);
    }

    @Override
    public Length subtract(final Position other)
    {
        var otherRef = other.relativeTo(getReference());
        return Length.ofSi(si() - otherRef.si()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Position add(final Length other)
    {
        return new Position(Length.ofSi(si() + other.si()).setDisplayUnit(getDisplayUnit()), getReference());
    }

    @Override
    public Position subtract(final Length other)
    {
        return new Position(Length.ofSi(si() - other.si()).setDisplayUnit(getDisplayUnit()), getReference());
    }

    /**
     * The reference class to define a reference point for the position. No references have been defined yet, since there is no
     * "natural" origin for a position that we can include here. User-defined origins van be easily added and used.
     */
    public static final class Reference extends AbstractReference<Reference, Length>
    {
        /**
         * Define a new reference point for the position.
         * @param id the id
         * @param name the name or explanation
         * @param offset the offset w.r.t. the offsetReference
         * @param offsetReference the reference to which the offset is relative
         */
        public Reference(final String id, final String name, final Length offset, final Reference offsetReference)
        {
            super(id, name, offset, offsetReference);
        }

        /**
         * Define a new reference point for the position.
         * @param id the id
         * @param name the name or explanation
         */
        public Reference(final String id, final String name)
        {
            this(id, name, null, null);
        }

        /**
         * Define a new reference point for the position.
         * @param id the id
         * @param name the name or explanation
         * @param offset the offset w.r.t. the offsetReference
         * @param offsetReference the reference to which the offset is relative
         */
        public static void add(final String id, final String name, final Length offset, final Reference offsetReference)
        {
            new Reference(id, name, offset, offsetReference);
        }

        /**
         * Define a new reference point for the position.
         * @param id the id
         * @param name the name or explanation
         */
        public static void add(final String id, final String name)
        {
            new Reference(id, name);
        }

        /**
         * Get a reference point for the position, based on its id. Return null when the id could not be found.
         * @param id the id
         * @return the PositionReference object
         */
        public static Reference get(final String id)
        {
            return (Reference) referenceMap.get(id);
        }
    }
}
