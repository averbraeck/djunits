package org.djunits.old.value.vfloat.vector.base;

import org.djunits.old.unit.SIUnit;
import org.djunits.old.unit.Unit;
import org.djunits.old.unit.util.UnitException;
import org.djunits.old.value.Relative;
import org.djunits.old.value.ValueRuntimeException;
import org.djunits.old.value.vfloat.function.FloatMathFunctions;
import org.djunits.old.value.vfloat.scalar.base.FloatScalarRel;
import org.djunits.old.value.vfloat.vector.FloatSIVector;
import org.djunits.old.value.vfloat.vector.data.FloatVectorData;

/**
 * AbstractMutableFloatVectorRel.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @param <U> the unit
 * @param <S> the scalar type belonging to the vector type
 * @param <RV> the relative vector type with this unit
 */
public abstract class FloatVectorRel<U extends Unit<U>, S extends FloatScalarRel<U, S>, RV extends FloatVectorRel<U, S, RV>>
        extends FloatVector<U, S, RV> implements Relative<U, RV>
{
    /** */
    private static final long serialVersionUID = 20190908L;

    /**
     * Construct a new Relative Mutable FloatVector.
     * @param data an internal data object
     * @param unit the unit
     */
    protected FloatVectorRel(final FloatVectorData data, final U unit)
    {
        super(data.copy(), unit);
    }

    /**
     * Compute the sum of all SI values of this vector.
     * @return the sum of all values of this vector with the same display unit as this vector
     */
    public final S zSum()
    {
        return instantiateScalarSI(getData().zSum(), getDisplayUnit());
    }

    @Override
    public final RV plus(final RV rel) throws ValueRuntimeException
    {
        return instantiateVector(this.getData().plus(rel.getData()), getDisplayUnit());
    }

    @Override
    public final RV minus(final RV rel) throws ValueRuntimeException
    {
        return instantiateVector(this.getData().minus(rel.getData()), getDisplayUnit());
    }

    /**
     * Increment all values of this vector by the increment. This only works if the vector is mutable.
     * @param increment the scalar by which to increment all values
     * @return this modified vector
     * @throws ValueRuntimeException in case this vector is immutable
     */
    @SuppressWarnings("unchecked")
    public RV incrementBy(final S increment)
    {
        checkCopyOnWrite();
        assign(FloatMathFunctions.INC(increment.si));
        return (RV) this;
    }

    /**
     * Increment all values of this vector by the increment on a value by value basis. This only works if this vector is
     * mutable.
     * @param increment the vector that contains the values by which to increment the corresponding values
     * @return this modified vector
     * @throws ValueRuntimeException in case this vector is immutable or when the sizes of the vectors differ
     */
    @SuppressWarnings("unchecked")
    public RV incrementBy(final RV increment)
    {
        checkCopyOnWrite();
        getData().incrementBy(increment.getData());
        return (RV) this;
    }

    /**
     * Decrement all values of this vector by the decrement. This only works if the vector is mutable.
     * @param decrement the scalar by which to decrement all values
     * @return this modified vector
     * @throws ValueRuntimeException in case this vector is immutable
     */
    @SuppressWarnings("unchecked")
    public RV decrementBy(final S decrement)
    {
        checkCopyOnWrite();
        assign(FloatMathFunctions.DEC(decrement.si));
        return (RV) this;
    }

    /**
     * Decrement all values of this vector by the decrement on a value by value basis. This only works if this vector is
     * mutable.
     * @param decrement the vector that contains the values by which to decrement the corresponding values
     * @return this modified vector
     * @throws ValueRuntimeException in case this vector is immutable or when the sizes of the vectors differ
     */
    @SuppressWarnings("unchecked")
    public RV decrementBy(final RV decrement)
    {
        checkCopyOnWrite();
        getData().decrementBy(decrement.getData());
        return (RV) this;
    }

    /**
     * Multiply all values of this vector by the multiplier. This only works if the vector is mutable.
     * @param multiplier the factor by which to multiply all values
     * @return this modified vector
     * @throws ValueRuntimeException in case the vector is immutable
     */
    public RV multiplyBy(final float multiplier)
    {
        return assign(FloatMathFunctions.MULT(multiplier));
    }

    /**
     * Divide all values of this vector by the divisor. This only works if the vector is mutable.
     * @param divisor the value by which to divide all values
     * @return this modified vector
     * @throws ValueRuntimeException in case the vector is immutable
     */
    public RV divideBy(final float divisor)
    {
        return assign(FloatMathFunctions.DIV(divisor));
    }

    /**
     * Multiply all values of this vector by the multiplier. This only works if the vector is mutable.
     * @param multiplier the factor by which to multiply all values
     * @return this modified vector
     * @throws ValueRuntimeException in case the vector is immutable
     */
    public RV multiplyBy(final double multiplier)
    {
        return assign(FloatMathFunctions.MULT((float) multiplier));
    }

    /**
     * Divide all values of this vector by the divisor. This only works if the vector is mutable.
     * @param divisor the value by which to divide all values
     * @return this modified vector
     * @throws ValueRuntimeException in case the vector is immutable
     */
    public RV divideBy(final double divisor)
    {
        return assign(FloatMathFunctions.DIV((float) divisor));
    }

    /**
     * Multiply a Relative value with this Relative value for a vector or matrix. The multiplication is done value by value and
     * store the result in a new Relative value. If both operands are dense, the result is a dense vector or matrix, otherwise
     * the result is a sparse vector or matrix.
     * @param rel the right operand, which can be any vector type
     * @return the multiplication of this vector and the operand
     * @throws ValueRuntimeException in case this vector or matrix and the operand have a different size
     * @throws UnitException on unit error
     * @param <UT> the unit type of the multiplier
     * @param <ST> the scalar type of the multiplier
     * @param <VT> the vector type of the multiplier
     */
    public final <UT extends Unit<UT>, ST extends FloatScalarRel<UT, ST>,
            VT extends FloatVectorRel<UT, ST, VT> & Relative<UT, VT>> FloatSIVector times(final VT rel)
                    throws ValueRuntimeException, UnitException
    {
        return new FloatSIVector(this.getData().times(rel.getData()), SIUnit.of(
                getDisplayUnit().getQuantity().getSiDimensions().plus(rel.getDisplayUnit().getQuantity().getSiDimensions())));
    }

    /**
     * Divide this Relative value by a Relative value for a vector or matrix. The division is done value by value and store the
     * result in a new Relative value. If both operands are dense, the result is a dense vector or matrix, otherwise the result
     * is a sparse vector or matrix.
     * @param rel the right operand, which can be any vector type
     * @return the division of this vector and the operand
     * @throws ValueRuntimeException in case this vector or matrix and the operand have a different size
     * @throws UnitException on unit error
     * @param <UT> the unit type of the multiplier
     * @param <ST> the scalar type of the multiplier
     * @param <VT> the vector type of the multiplier
     */
    public final <UT extends Unit<UT>, ST extends FloatScalarRel<UT, ST>,
            VT extends FloatVectorRel<UT, ST, VT> & Relative<UT, VT>> FloatSIVector divide(final VT rel)
                    throws ValueRuntimeException, UnitException
    {
        return new FloatSIVector(this.getData().divide(rel.getData()), SIUnit.of(
                getDisplayUnit().getQuantity().getSiDimensions().minus(rel.getDisplayUnit().getQuantity().getSiDimensions())));
    }

    @Override
    public RV times(final double multiplier)
    {
        return clone().mutable().assign(FloatMathFunctions.MULT((float) multiplier)).immutable();
    }

    @Override
    public RV divide(final double divisor)
    {
        return clone().mutable().assign(FloatMathFunctions.DIV((float) divisor)).immutable();
    }

    @Override
    public RV times(final float multiplier)
    {
        return times((double) multiplier);
    }

    @Override
    public RV divide(final float divisor)
    {
        return divide((double) divisor);
    }

}
