package org.djunits.value;

import java.io.Serializable;

import org.djunits.unit.Unit;
import org.djunits.value.function.ValueFunctions;

/**
 * Value is the generic interface for all Scalar, Vector and Matrix classes that forces implementation of a few unit- and
 * value-related methods.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit type
 * @param <T> the value type for this unit
 */
public interface Value<U extends Unit<U>, T extends Value<U, T>> extends ValueFunctions<U, T>, Serializable, Cloneable
{
    /**
     * Retrieve the unit of this Value.
     * @return U; the unit of this Value
     */
    U getDisplayUnit();

    /**
     * Set a new display unit for the value. Internally, the value will stay stored in the default or SI unit.
     * @param newUnit U; the new display unit of this Value
     */
    void setDisplayUnit(U newUnit);

    /**
     * Indicate whether this is an Absolute Value.
     * @return boolean
     */
    boolean isAbsolute();

    /**
     * Indicate whether this is a Relative Value.
     * @return boolean
     */
    boolean isRelative();

    /**
     * Concise description of this value.
     * @return a String with the value, non-verbose, with the unit attached.
     */
    @Override
    String toString();

    /**
     * Somewhat verbose description of this value with the values expressed in the specified unit.
     * @param displayUnit U; the unit into which the values are converted for display
     * @return String; printable string with the value contents expressed in the specified unit
     */
    String toString(U displayUnit);

    /**
     * Somewhat verbose description of this value with optional type and unit information.
     * @param verbose boolean; if true; include type info; if false; exclude type info
     * @param withUnit boolean; if true; include the unit; of false; exclude the unit
     * @return String; printable string with the value contents
     */
    String toString(boolean verbose, boolean withUnit);

    /**
     * Somewhat verbose description of this value with the values expressed in the specified unit.
     * @param displayUnit U; the unit into which the values are converted for display
     * @param verbose boolean; if true; include type info; if false; exclude type info
     * @param withUnit boolean; if true; include the unit; of false; exclude the unit
     * @return String; printable string with the value contents
     */
    String toString(U displayUnit, boolean verbose, boolean withUnit);
}
