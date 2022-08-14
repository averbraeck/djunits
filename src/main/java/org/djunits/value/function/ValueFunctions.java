package org.djunits.value.function;

import java.io.Serializable;

import org.djunits.unit.Unit;
import org.djunits.value.Value;

/**
 * Interface to force a limited number of functions of Math to be implemented for absolute values. Note: a lot of standard Math
 * functions are <i>not</i> implemented, as they don't make sense with the units. E.g., a cubic root of a Volume should give a
 * Length, and not another volume... Trigoniometric functions should not give back the same unit either. The abs() function is
 * not included here, as abs() only makes sense for relative vales. What is the absolute value of 14 January 2016? Therefore the
 * set of functions that the interface forces to implement is rather limited, and certainly not the entire range of
 * java.lang.Math functions.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit of the values
 * @param <T> the type of the value that the functions operate on
 */
public interface ValueFunctions<U extends Unit<U>, T extends Value<U, T>> extends Serializable
{
    /**
     * Return a new Scalar/Vector/Matrix with absolute value(s).
     * @return R; a new R with absolute value(s)
     */
    T abs();

    /**
     * Return a new Scalar/Vector/Matrix with the nearest integer value(s) above the current value(s).
     * @return R; a new R with absolute value(s)
     */
    T ceil();

    /**
     * Return a new Scalar/Vector/Matrix with the nearest integer value(s) below the current value(s).
     * @return R; a new R with absolute value(s)
     */
    T floor();

    /**
     * Return a new Scalar/Vector/Matrix with negated value(s).
     * @return R; a new R with negated value(s)
     */
    T neg();

    /**
     * Return a new Scalar/Vector/Matrix with the nearest integer value(s). When the value is exactly in the middle between two
     * integer values, the even one is returned.
     * @return R; a new R with absolute value(s)
     */
    T rint();
}
