package org.djunits.value.vfloat.matrix.base;

import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.util.UnitException;
import org.djunits.value.Relative;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.matrix.FloatSIMatrix;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djunits.value.vfloat.vector.base.FloatVector;
import org.djunits.value.vfloat.vector.base.FloatVectorRel;

/**
 * FloatMatrixRel.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit
 * @param <S> the scalar type belonging to the matrix type
 * @param <RV> the relative vector type belonging to the relative matrix type
 * @param <RM> the relative matrix type with this unit
 */
public abstract class FloatMatrixRel<U extends Unit<U>, S extends FloatScalarRel<U, S>,
        RV extends FloatVectorRel<U, S, RV>, RM extends FloatMatrixRel<U, S, RV, RM>>
        extends FloatMatrix<U, S, RV, RM> implements Relative<U, RM>
{
    /** */
    private static final long serialVersionUID = 20190908L;

    /**
     * Construct a new Relative Mutable FloatMatrix.
     * @param data an internal data object
     * @param unit the unit
     */
    protected FloatMatrixRel(final FloatMatrixData data, final U unit)
    {
        super(data.copy(), unit);
    }

    /**
     * Compute the sum of all SI values of this matrix.
     * @return the sum of all values of this matrix with the same display unit as this matrix
     */
    public final S zSum()
    {
        return instantiateScalarSI(this.data.zSum(), getDisplayUnit());
    }

    @Override
    public final RM plus(final RM rel) throws ValueRuntimeException
    {
        return instantiateMatrix(this.getData().plus(rel.getData()), getDisplayUnit());
    }

    @Override
    public final RM minus(final RM rel) throws ValueRuntimeException
    {
        return instantiateMatrix(this.getData().minus(rel.getData()), getDisplayUnit());
    }

    /**
     * Increment all values of this matrix by the increment. This only works if the matrix is mutable.
     * @param increment the scalar by which to increment all values
     * @return this modified matrix
     * @throws ValueRuntimeException in case this matrix is immutable
     */
    @SuppressWarnings("unchecked")
    public RM incrementBy(final S increment)
    {
        checkCopyOnWrite();
        assign(FloatMathFunctions.INC(increment.si));
        return (RM) this;
    }

    /**
     * Increment all values of this matrix by the increment on a value by value basis. This only works if this matrix is
     * mutable.
     * @param increment the matrix that contains the values by which to increment the corresponding values
     * @return this modified matrix
     * @throws ValueRuntimeException in case this matrix is immutable or when the sizes of the matrices differ
     */
    @SuppressWarnings("unchecked")
    public RM incrementBy(final RM increment)
    {
        checkCopyOnWrite();
        this.data.incrementBy(increment.getData());
        return (RM) this;
    }

    /**
     * Decrement all values of this matrix by the decrement. This only works if this matrix is mutable.
     * @param decrement the scalar by which to decrement all values
     * @return this modified matrix
     * @throws ValueRuntimeException in case this matrix is immutable
     */
    @SuppressWarnings("unchecked")
    public RM decrementBy(final S decrement)
    {
        checkCopyOnWrite();
        assign(FloatMathFunctions.DEC(decrement.si));
        return (RM) this;
    }

    /**
     * Decrement this Relative matrix by another Relative matrix. The operation is done value by value. This is only allowed if
     * this matrix is mutable.
     * @param decrement the matrix that contains the values by which to decrement the corresponding values
     * @return this modified matrix
     * @throws ValueRuntimeException in case this matrix is immutable or when the sizes of the matrices differ
     */
    @SuppressWarnings("unchecked")
    public final RM decrementBy(final RM decrement)
    {
        checkCopyOnWrite();
        this.data.decrementBy(decrement.getData());
        return (RM) this;
    }

    /**
     * Multiply all values of this matrix by the multiplier. This only works if the matrix is mutable.
     * @param multiplier the factor by which to multiply all values
     * @return this modified matrix
     * @throws ValueRuntimeException in case the matrix is immutable
     */
    public RM multiplyBy(final float multiplier)
    {
        return assign(FloatMathFunctions.MULT(multiplier));
    }

    /**
     * Divide all values of this matrix by the divisor. This only works if the matrix is mutable.
     * @param divisor the value by which to divide all values
     * @return this modified matrix
     * @throws ValueRuntimeException in case the matrix is immutable
     */
    public RM divideBy(final float divisor)
    {
        return assign(FloatMathFunctions.DIV(divisor));
    }
    
    /**
     * Multiply all values of this matrix by the multiplier. This only works if the matrix is mutable.
     * @param multiplier the factor by which to multiply all values
     * @return this modified matrix
     * @throws ValueRuntimeException in case the matrix is immutable
     */
    public RM multiplyBy(final double multiplier)
    {
        return assign(FloatMathFunctions.MULT((float) multiplier));
    }

    /**
     * Divide all values of this matrix by the divisor. This only works if the matrix is mutable.
     * @param divisor the value by which to divide all values
     * @return this modified matrix
     * @throws ValueRuntimeException in case the matrix is immutable
     */
    public RM divideBy(final double divisor)
    {
        return assign(FloatMathFunctions.DIV((float) divisor));
    }

    /**
     * Multiply a Relative value with this Relative value for a matrix or matrix. The multiplication is done value by value and
     * store the result in a new Relative value. If both operands are dense, the result is a dense matrix or matrix, otherwise
     * the result is a sparse matrix or matrix.
     * @param rel the right operand, which can be any matrix type
     * @return the multiplication of this matrix and the operand
     * @throws ValueRuntimeException in case this matrix or matrix and the operand have a different size
     * @throws UnitException on unit error
     * @param <UT> the unit type of the multiplier
     * @param <ST> the scalar type of the multiplier
     * @param <VT> the vector type of the multiplier
     * @param <MT> the matrix type of the multiplier
     */
    public final <UT extends Unit<UT>, ST extends FloatScalar<UT, ST>, VT extends FloatVector<UT, ST, VT>,
            MT extends FloatMatrix<UT, ST, VT, MT> & Relative<UT, MT>> FloatSIMatrix times(final MT rel)
                    throws ValueRuntimeException, UnitException
    {
        return new FloatSIMatrix(this.getData().times(rel.getData()), SIUnit.of(
                getDisplayUnit().getQuantity().getSiDimensions().plus(rel.getDisplayUnit().getQuantity().getSiDimensions())));
    }

    @Override
    public final RM times(final float multiplier)
    {
        RM result = clone().mutable();
        result.assign(FloatMathFunctions.MULT(multiplier));
        return result.immutable();
    }

    @Override
    public final RM times(final double multiplier)
    {
        return times((float) multiplier);
    }

    /**
     * Divide this Relative matrix by another Relative matrix. The operation is done value by value and store the result is
     * stored in a new Relative matrix. If both operands are dense, the result is a dense matrix, otherwise the result is a
     * sparse matrix. TODO discuss dense or sparseness of result.
     * @param rel the right operand, which can be any matrix type
     * @return the division of this matrix and the operand
     * @throws ValueRuntimeException in case this matrix or matrix and the operand have a different size
     * @throws UnitException on unit error
     * @param <UT> the unit type of the multiplier
     * @param <ST> the scalar type of the multiplier
     * @param <VT> the vector type of the multiplier
     * @param <MT> the matrix type of the multiplier
     */
    public final <UT extends Unit<UT>, ST extends FloatScalar<UT, ST>, VT extends FloatVector<UT, ST, VT>,
            MT extends FloatMatrix<UT, ST, VT, MT> & Relative<UT, MT>> FloatSIMatrix divide(final MT rel)
                    throws ValueRuntimeException, UnitException
    {
        return new FloatSIMatrix(this.getData().divide(rel.getData()), SIUnit.of(
                getDisplayUnit().getQuantity().getSiDimensions().minus(rel.getDisplayUnit().getQuantity().getSiDimensions())));
    }

    @Override
    public final RM divide(final double divisor)
    {
        return divide((float) divisor);
    }

    @Override
    public final RM divide(final float divisor)
    {
        RM result = clone().mutable();
        result.assign(FloatMathFunctions.DIV(divisor));
        return result.immutable();
    }

}
