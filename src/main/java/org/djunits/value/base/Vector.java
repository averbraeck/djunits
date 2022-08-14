package org.djunits.value.base;

import java.util.Iterator;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.Absolute;
import org.djunits.value.IndexedValue;
import org.djunits.value.Relative;
import org.djunits.value.ValueRuntimeException;

/**
 * Vector to distinguish a vector from vectors and matrices. A possible way to implement this interface is:
 * 
 * <pre>
 * class LengthVector implements Vector&lt;LengthUnit, Length, LengthVector&gt;
 * </pre>
 * 
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>. <br>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit
 * @param <S> the scalar type belonging to the vector type
 * @param <V> the vector type with the given unit
 */
public interface Vector<U extends Unit<U>, S extends Scalar<U, S>, V extends Vector<U, S, V>>
        extends IndexedValue<U, S, V>, Iterable<S>
{
    /**
     * Retrieve the size of the vector.
     * @return int; the size of the vector
     */
    int size();

    /**
     * Retrieve a value from the vector.
     * @param index int; the index to retrieve the value at
     * @return S; the value as a Scalar
     * @throws ValueRuntimeException in case index is out of bounds
     */
    S get(int index) throws ValueRuntimeException;

    /**
     * Return the vector as an array of scalars.
     * @return S[]; the vector as an array of scalars
     */
    S[] getScalars();

    /**
     * Create and return an iterator over the scalars in this vector in proper sequence.
     * @return Iterator&lt;S&gt;; an iterator over the scalars in this vector in proper sequence
     */
    @Override
    Iterator<S> iterator();

    /**
     * Methods for Relative Vector. A possible way to implement this interface is:
     * 
     * <pre>
     *   class AreaVector implements Vector.Rel&lt;AreaUnit, Area, AreaVector&gt;
     * </pre>
     * 
     * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>. <br>
     * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
     * @param <U> the unit
     * @param <S> the scalar type belonging to the vector type
     * @param <RV> the relative vector type with this unit
     */
    interface Rel<U extends Unit<U>, S extends Scalar<U, S>, RV extends Vector.Rel<U, S, RV>>
            extends Vector<U, S, RV>, Relative<U, RV>
    {
        /**
         * Add a relative vector to this relative mutable vector. A new vector is returned. When the vector itself needs to be
         * changed, use the increaseBy(V) method instead. The addition is done value by value and the result is stored in a new
         * vector. If both operands are sparse, the result is a sparse vector, otherwise the result is a dense vector.
         * @param increment RV; the relative vector (mutable or immutable, sparse or dense) to add
         * @return RMV; the sum of this vector and the operand as a new relative, mutable vector
         * @throws ValueRuntimeException in case this vector and the operand have a different size
         */
        RV plus(RV increment) throws ValueRuntimeException;

        /**
         * Subtract a relative vector from this relative mutable vector. The display unit of the result is the display unit of
         * this absolute vector. The subtraction is done value by value and the result is stored in a new vector. If both
         * operands are sparse, the result is a sparse vector, otherwise the result is a dense vector.
         * @param decrement RV; the value to subtract
         * @return RMV; the difference of this vector and the operand as a new relative, mutable vector
         * @throws ValueRuntimeException in case this vector and the operand have a different size
         */
        RV minus(RV decrement) throws ValueRuntimeException;

        /**
         * Multiply all values of this vector by the multiplier. This only works if the vector is mutable.
         * @param multiplier double; the factor by which to multiply all values
         * @return V; this modified vector
         * @throws ValueRuntimeException in case the vector is immutable
         */
        RV multiplyBy(double multiplier);

        /**
         * Divide all values of this vector by the divisor. This only works if the vector is mutable.
         * @param divisor double; the value by which to divide all values
         * @return V; this modified vector
         * @throws ValueRuntimeException in case the vector is immutable
         */
        RV divideBy(double divisor);
    }

    /**
     * Additional methods for Relative Vector that has a corresponding Absolute Vector. An example is the relative vector Length
     * that has a corresponding absolute vector Position. A possible way to implement this interface is:
     * 
     * <pre>
     * class LengthVector implements Vector.RelWithAbs&lt;
     *     PositionUnit, Position, PositionVector, LengthUnit, Length, LengthVector&gt;
     * </pre>
     * 
     * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>. <br>
     * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
     * @param <AU> the absolute unit belonging to the relative unit
     * @param <A> the absolute scalar type belonging to the absolute vector type
     * @param <AV> the absolute vector type
     * @param <RU> the relative unit belonging to the absolute unit
     * @param <R> the relative scalar type belonging to the relative vector type
     * @param <RV> the relative vector type with this unit
     */
    interface RelWithAbs<AU extends AbsoluteLinearUnit<AU, RU>, A extends Scalar<AU, A>,
            AV extends Vector.Abs<AU, A, AV, RU, R, RV>, RU extends Unit<RU>, R extends Scalar<RU, R>,
            RV extends Vector.RelWithAbs<AU, A, AV, RU, R, RV>> extends Rel<RU, R, RV>
    {
        /**
         * Add an absolute vector to this relative vector. A new vector is returned. When the vector itself needs to be changed,
         * use the increaseBy(V) method instead. The addition is done value by value and the result is stored in a new vector.
         * If both operands are sparse, the result is a sparse vector, otherwise the result is a dense vector.
         * @param increment AV; the absolute vector (mutable or immutable, sparse or dense) to add to this relative vector
         * @return AMV; the sum of this vector and the operand as a new absolute, mutable vector
         * @throws ValueRuntimeException in case this vector and the operand have a different size
         */
        AV plus(AV increment);
    }

    /**
     * Methods for Absolute Vector. An example is the absolute vector Position that has a corresponding relative vector Length.
     * A possible way to implement this interface is:
     * 
     * <pre>
     * class PositionVector implements Vector.Abs&lt;
     *     PositionUnit, Position, PositionVector, LengthUnit, Length, LengthVector&gt;
     * </pre>
     * 
     * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>. <br>
     * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
     * @param <AU> the absolute unit belonging to the relative unit
     * @param <A> the absolute scalar type belonging to the absolute vector type
     * @param <AV> the absolute vector type
     * @param <RU> the relative unit belonging to the absolute unit
     * @param <R> the relative scalar type belonging to the relative vector type
     * @param <RV> the relative vector type with this unit
     */
    interface Abs<AU extends AbsoluteLinearUnit<AU, RU>, A extends Scalar<AU, A>, AV extends Vector.Abs<AU, A, AV, RU, R, RV>,
            RU extends Unit<RU>, R extends Scalar<RU, R>, RV extends Vector.RelWithAbs<AU, A, AV, RU, R, RV>>
            extends Vector<AU, A, AV>, Absolute
    {
        /**
         * Add a relative vector to this absolute vector. A new absolute vector is returned. The display unit of the new vector
         * is the display unit of this absolute vector. The addition is done value by value and the result is stored in a new
         * vector. If both operands are sparse, the result is a sparse vector, otherwise the result is a dense vector.
         * @param increment RV; the relative vector (mutable or immutable, sparse or dense) to add to this absolute vector
         * @return AIV; the sum of this value and the operand as a new absolute, immutable vector
         * @throws ValueRuntimeException in case this vector and the operand have a different size
         */
        AV plus(RV increment) throws ValueRuntimeException;

        /**
         * Subtract a relative vector from this absolute vector. A new absolute vector is returned. The display unit of the new
         * vector is the display unit of this absolute vector. The subtraction is done value by value and the result is stored
         * in a new vector. If both operands are sparse, the result is a sparse vector, otherwise the result is a dense vector.
         * @param decrement RV; the relative vector (mutable or immutable, sparse or dense) to subtract from this absolute
         *            vector
         * @return AIV; the difference of this value and the operand as a new absolute, immutable vector
         * @throws ValueRuntimeException in case this vector and the operand have a different size
         */
        AV minus(RV decrement) throws ValueRuntimeException;

        /**
         * Subtract an absolute vector from this absolute vector. A new relative vector is returned. The display unit of the new
         * vector is the display unit of relative counterpart of the display unit of this absolute vector. The subtraction is
         * done value by value and the result is stored in a new vector. If both operands are sparse, the result is a sparse
         * vector, otherwise the result is a dense vector.
         * @param decrement AV; the absolute vector (mutable or immutable, sparse or dense) to subtract from this absolute
         *            vector
         * @return RIV; the difference of this value and the operand as a new relative, immutable vector
         * @throws ValueRuntimeException in case this vector and the operand have a different size
         */
        RV minus(AV decrement) throws ValueRuntimeException;
    }

}
