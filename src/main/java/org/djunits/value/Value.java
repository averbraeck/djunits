package org.djunits.value;

import java.io.Serializable;

import org.djunits.unit.Unit;

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
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit type
 * @param <T> the value type for this unit
 */
public interface Value<U extends Unit<U>, T extends Value<U, T>> extends Serializable, Cloneable
{
    /**
     * Retrieve the unit of this Value.
     * @return the unit of this Value
     */
    U getDisplayUnit();

    /**
     * Set a new display unit for the value. Internally, the value will stay stored in the default or SI unit.
     * @param newUnit the new display unit of this Value
     * @return the instance for method chaining
     */
    T setDisplayUnit(U newUnit);

    /**
     * Indicate whether this is an Absolute Value.
     * @return boolean
     */
    default boolean isAbsolute()
    {
        return this instanceof Absolute;
    }

    /**
     * Indicate whether this is a Relative Value.
     * @return boolean
     */
    default boolean isRelative()
    {
        return this instanceof Relative;
    }

    /**
     * Concise description of this value.
     * @return a String with the value, non-verbose, with the unit attached.
     */
    @Override
    String toString();

    /**
     * Somewhat verbose description of this value with the values expressed in the specified unit.
     * @param displayUnit the unit into which the values are converted for display
     * @return printable string with the value contents expressed in the specified unit
     */
    String toString(U displayUnit);

    /**
     * Somewhat verbose description of this value with optional type and unit information.
     * @param verbose if true; include type info; if false; exclude type info
     * @param withUnit if true; include the unit; of false; exclude the unit
     * @return printable string with the value contents
     */
    String toString(boolean verbose, boolean withUnit);

    /**
     * Somewhat verbose description of this value with the values expressed in the specified unit.
     * @param displayUnit the unit into which the values are converted for display
     * @param verbose if true; include type info; if false; exclude type info
     * @param withUnit if true; include the unit; of false; exclude the unit
     * @return printable string with the value contents
     */
    String toString(U displayUnit, boolean verbose, boolean withUnit);

    /**
     * Return a new Scalar/Vector/Matrix with absolute value(s).
     * @return a new R with absolute value(s)
     */
    T abs();

    /**
     * Return a new Scalar/Vector/Matrix with the nearest integer value(s) above the current value(s).
     * @return a new R with absolute value(s)
     */
    T ceil();

    /**
     * Return a new Scalar/Vector/Matrix with the nearest integer value(s) below the current value(s).
     * @return a new R with absolute value(s)
     */
    T floor();

    /**
     * Return a new Scalar/Vector/Matrix with negated value(s).
     * @return a new R with negated value(s)
     */
    T neg();

    /**
     * Return a new Scalar/Vector/Matrix with the nearest integer value(s). When the value is exactly in the middle between two
     * integer values, the even one is returned.
     * @return a new R with absolute value(s)
     */
    T rint();
}
