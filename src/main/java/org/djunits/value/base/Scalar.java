package org.djunits.value.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.Absolute;
import org.djunits.value.Relative;
import org.djunits.value.Value;

/**
 * Scalar to distinguish a scalar from vectors and matrices.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit
 * @param <S> the scalar type with the given unit
 */
public interface Scalar<U extends Unit<U>, S extends Scalar<U, S>> extends Value<U, S>, Comparable<S>
{
    /**
     * Test if this DoubleScalar is less than another DoubleScalar.
     * @param o T, a relative typed DoubleScalar; the right hand side operand of the comparison
     * @return boolean; true if this is less than other; false if this is not less than other
     */
    boolean lt(S o);

    /**
     * Test if this DoubleScalar is less than or equal to another DoubleScalar.
     * @param o T, a relative typed DoubleScalar; the right hand side operand of the comparison
     * @return boolean
     */
    boolean le(S o);

    /**
     * Test if this DoubleScalar is greater than or equal to a DoubleScalar.
     * @param o T, a relative typed DoubleScalar; the right hand side operand of the comparison
     * @return boolean; true if this is greater than or equal to other; false if this is not greater than or equal to other
     */
    boolean gt(S o);

    /**
     * Test if this DoubleScalar is greater than a DoubleScalar.
     * @param o T, a relative typed DoubleScalar; the right hand side operand of the comparison
     * @return boolean; true if this is greater than other; false if this is not greater than other
     */
    boolean ge(S o);

    /**
     * Test if this DoubleScalar is equal to a DoubleScalar.
     * @param o T, a relative typed DoubleScalar; the right hand side operand of the comparison
     * @return boolean; true if this is equal to other; false if this is not equal to other
     */
    boolean eq(S o);

    /**
     * Test if this DoubleScalar is not equal to a DoubleScalar.
     * @param o T, a relative typed DoubleScalar; the right hand side operand of the comparison
     * @return boolean; true if this is not equal to other; false if this is equal to other
     */
    boolean ne(S o);

    /**
     * Test if this DoubleScalar is less than 0.0.
     * @return boolean; true if this is less than 0.0; false if this is not less than 0.0
     */
    boolean lt0();

    /**
     * Test if this DoubleScalar is less than or equal to 0.0.
     * @return boolean; true if this is less than or equal to 0.0; false if this is not less than or equal to 0.0
     */
    boolean le0();

    /**
     * Test if this DoubleScalar is greater than or equal to 0.0.
     * @return boolean; true if this is greater than or equal to 0.0; false if this is not greater than or equal to 0.0
     */
    boolean gt0();

    /**
     * Test if this DoubleScalar is greater than 0.0.
     * @return boolean; true if this is greater than 0.0; false if this is not greater than 0.0
     */
    boolean ge0();

    /**
     * Test if this DoubleScalar is equal to 0.0.
     * @return boolean; true if this is equal to 0.0; false if this is not equal to 0.0
     */
    boolean eq0();

    /**
     * Test if this DoubleScalar is not equal to 0.0.
     * @return boolean; true if this is not equal to 0.0; false if this is equal to 0.0
     */
    boolean ne0();

    /**
     * Concise textual representation of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @return a String with the value with the default textual representation of the unit attached.
     */
    String toTextualString();

    /**
     * Concise textual representation of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @param displayUnit U; the display unit for the value
     * @return a String with the value with the default textual representation of the provided unit attached.
     */
    String toTextualString(U displayUnit);

    /**
     * Concise display description of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @return a String with the value with the default display representation of the unit attached.
     */
    String toDisplayString();

    /**
     * Concise display description of this value, without the engineering formatting, so without trailing zeroes. A space is
     * added between the number and the unit.
     * @param displayUnit U; the display unit for the value
     * @return a String with the value with the default display representation of the provided unit attached.
     */
    String toDisplayString(U displayUnit);

    /**
     * Methods for Relative Scalar.
     * <p>
     * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
     * <p>
     * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
     * @param <U> the unit
     * @param <R> the relative scalar
     */
    interface Rel<U extends Unit<U>, R extends Scalar.Rel<U, R>> extends Scalar<U, R>, Relative<U, R>
    {
        /**
         * Add a Relative value to this Relative value. A new value is returned due to immutability.
         * @param increment R; the value to add
         * @return R; the sum of this value and the operand as a new object
         */
        R plus(R increment);

        /**
         * Subtract a Relative value from this Relative value. A new value is returned due to immutability.
         * @param decrement R; the value to subtract
         * @return R; the difference of this value and the operand
         */
        R minus(R decrement);
    }

    /**
     * Additional methods for Relative Scalar that has a corresponding Absolute Scalar. An example is the relative scalar Length
     * that has a corresponding absolute scalar Position.
     * <p>
     * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
     * <p>
     * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
     * @param <AU> the absolute unit belonging to the absoluteunit
     * @param <A> the absolute scalar belonging to the relative scalar
     * @param <RU> the absolute unit belonging to the relative unit
     * @param <R> the relative scalar belonging to the absolute scalar
     */
    interface RelWithAbs<AU extends AbsoluteLinearUnit<AU, RU>, A extends Scalar.Abs<AU, A, RU, R>, RU extends Unit<RU>,
            R extends Scalar.RelWithAbs<AU, A, RU, R>> extends Scalar.Rel<RU, R>
    {
        /**
         * Add an Absolute value to this relative value. A new value is returned due to immutability. The unit of the result is
         * the unit of the absolute operand.
         * @param abs A; A the right operand
         * @return A; the sum of this value and the operand
         */
        A plus(A abs);
    }

    /**
     * Methods for Absolute Scalar.
     * <p>
     * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
     * <p>
     * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
     * @param <AU> the absolute unit belonging to the absoluteunit
     * @param <A> the absolute scalar belonging to the relative scalar
     * @param <RU> the absolute unit belonging to the relative unit
     * @param <R> the relative scalar belonging to the absolute scalar
     */
    interface Abs<AU extends AbsoluteLinearUnit<AU, RU>, A extends Scalar.Abs<AU, A, RU, R>, RU extends Unit<RU>,
            R extends Scalar.RelWithAbs<AU, A, RU, R>> extends Scalar<AU, A>, Absolute
    {
        /**
         * Add a Relative value to this Absolute value. A new value is returned due to immutability.
         * @param rel R; R the right operand
         * @return A; the sum of this value and the operand
         */
        A plus(R rel);

        /**
         * Subtract a Relative value from this Absolute value. A new value is returned due to immutability.
         * @param rel R; R the right operand
         * @return A; the subtraction of this value and the operand
         */
        A minus(R rel);

        /**
         * Subtract an Absolute value from this Absolute value, resulting in a Relative value. A new value is returned due to
         * immutability.
         * @param abs A; A the right operand
         * @return R; the subtraction of this value and the operand
         */
        R minus(A abs);
    }
}
