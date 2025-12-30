package org.djunits.value;

import java.io.Serializable;

import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;

/**
 * Value is the generic interface for all Scalar, Vector and Matrix classes that forces implementation of a few unit- and
 * value-related methods. The Value interface defines a limited number of functions of Math to be implemented for absolute
 * values. Note: a lot of standard Math functions are <i>not</i> implemented, as they don't make sense with the units. E.g., a
 * cubic root of a Volume should give a Length, and not another volume... Trigoniometric functions should not give back the same
 * unit either. The abs() function is not included here, as abs() only makes sense for relative vales. What is the absolute
 * value of 14 January 2016? Therefore the set of functions that the interface forces to implement is rather limited, and
 * certainly not the entire range of java.lang.Math functions.
 * <p>
 * Copyright (c) 2015-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 * @param <U> the unit type
 * @param <SELF> the 'own' type for fluent design
 */
public interface Value<U extends UnitInterface<U, ?>, SELF extends Value<U, SELF>> extends Serializable
{
    /**
     * Retrieve the unit of this Value.
     * @return the unit of this Value
     */
    U getDisplayUnit();

    /**
     * Set a new display unit for the value. Internally, the value will not changed since it is stored in a base unit.
     * @param newUnit the new display unit of this value
     * @return 'this' for fluent design
     */
    SELF setDisplayUnit(U newUnit);

    /**
     * Set a new display unit for the value. Internally, the value will not changed since it is stored in a base unit.
     * @param newUnitString the textual representation of the new display unit of this value
     * @return 'this' for fluent design
     * @throws UnitRuntimeException when the unit did not exist, or the abbreviation was not registered
     */
    default SELF setDisplayUnit(final String newUnitString)
    {
        @SuppressWarnings("unchecked")
        U newUnit = (U) Units.resolve(getDisplayUnit().getClass(), newUnitString);
        return setDisplayUnit(newUnit);
    }

    /**
     * Indicate whether this is an Absolute Value.
     * @return whether this is an Absolute Value
     */
    default boolean isAbsolute()
    {
        return !isRelative();
    }

    /**
     * Indicate whether this is a Relative Value.
     * @return whether this is a Relative Value
     */
    boolean isRelative();

    /**
     * Return a concise description of this value.
     * @return a String with the value contents, without the type, with the unit attached.
     */
    @Override
    String toString();

    /**
     * Return a concise description of this value with the values expressed in the specified unit.
     * @param displayUnit the unit into which the values are converted for display
     * @return a String with the value contents expressed in the specified unit
     */
    String toString(U displayUnit);

}
