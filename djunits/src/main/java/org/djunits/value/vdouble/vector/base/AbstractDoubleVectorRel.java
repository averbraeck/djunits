package org.djunits.value.vdouble.vector.base;

import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.util.UnitException;
import org.djunits.value.Relative;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.base.Vector;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.vector.SIVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * AbstractMutableDoubleVectorRel.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit
 * @param <S> the scalar type belonging to the vector type
 * @param <RV> the relative vector type with this unit
 */
public abstract class AbstractDoubleVectorRel<U extends Unit<U>, S extends AbstractDoubleScalarRel<U, S>,
        RV extends AbstractDoubleVectorRel<U, S, RV>> extends AbstractDoubleVector<U, S, RV>
        implements Vector.Rel<U, S, RV>, Relative<U, RV>
{
    /** */
    private static final long serialVersionUID = 20190908L;

    /**
     * Construct a new Relative Mutable DoubleVector.
     * @param data DoubleVectorData; an internal data object
     * @param unit U; the unit
     */
    protected AbstractDoubleVectorRel(final DoubleVectorData data, final U unit)
    {
        super(data.copy(), unit);
    }

    /**
     * Compute the sum of all SI values of this vector.
     * @return S; the sum of all SI values of this vector with the same display unit as this vector
     */
    public final S zSum()
    {
        return instantiateScalarSI(getData().zSum(), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final RV plus(final RV rel) throws ValueRuntimeException
    {
        return instantiateVector(this.getData().plus(rel.getData()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final RV minus(final RV rel) throws ValueRuntimeException
    {
        return instantiateVector(this.getData().minus(rel.getData()), getDisplayUnit());
    }

    /**
     * Increment all values of this vector by the increment. This only works if the vector is mutable.
     * @param increment S; the scalar by which to increment all values
     * @return RV; this modified vector
     * @throws ValueRuntimeException in case this vector is immutable
     */
    @SuppressWarnings("unchecked")
    public RV incrementBy(final S increment)
    {
        checkCopyOnWrite();
        assign(DoubleMathFunctions.INC(increment.si));
        return (RV) this;
    }

    /**
     * Increment all values of this vector by the increment on a value by value basis. This only works if this vector is
     * mutable.
     * @param increment RV; the vector that contains the values by which to increment the corresponding values
     * @return RV; this modified vector
     * @throws ValueRuntimeException in case this vector is immutable, when the sizes of the vectors differ, or
     *             <code>increment</code> is null
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
     * @param decrement S; the scalar by which to decrement all values
     * @return RV; this modified vector
     * @throws ValueRuntimeException in case this vector is immutable
     */
    @SuppressWarnings("unchecked")
    public RV decrementBy(final S decrement)
    {
        checkCopyOnWrite();
        assign(DoubleMathFunctions.DEC(decrement.si));
        return (RV) this;
    }

    /**
     * Decrement all values of this vector by the decrement on a value by value basis. This only works if this vector is
     * mutable.
     * @param decrement RV; the vector that contains the values by which to decrement the corresponding values
     * @return RV; this modified vector
     * @throws ValueRuntimeException in case this vector is immutable, when the sizes of the vectors differ, or
     *             <code>decrement</code> is null
     */
    @SuppressWarnings("unchecked")
    public RV decrementBy(final RV decrement)
    {
        checkCopyOnWrite();
        getData().decrementBy(decrement.getData());
        return (RV) this;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public RV multiplyBy(final double multiplier)
    {
        checkCopyOnWrite();
        getData().multiplyBy(multiplier);
        return (RV) this;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public RV divideBy(final double divisor)
    {
        checkCopyOnWrite();
        getData().divideBy(divisor);
        return (RV) this;
    }

    /**
     * Multiply a Relative value with this Relative value for a vector or matrix. The multiplication is done value by value and
     * store the result in a new Relative value. If both operands are dense, the result is a dense vector or matrix, otherwise
     * the result is a sparse vector or matrix.
     * @param rel VT; the right operand, which can be any vector type
     * @return SIVector; the multiplication of this vector and the operand
     * @throws ValueRuntimeException in case this vector or matrix and the operand have a different size
     * @throws UnitException on unit error
     * @param <UT> the unit type of the multiplier
     * @param <ST> the scalar type of the multiplier
     * @param <VT> the vector type of the multiplier
     */
    public final <UT extends Unit<UT>, ST extends AbstractDoubleScalarRel<UT, ST>,
            VT extends AbstractDoubleVectorRel<UT, ST, VT> & Relative<UT, VT>> SIVector times(final VT rel)
                    throws ValueRuntimeException, UnitException
    {
        checkSize(rel);
        return new SIVector(this.getData().times(rel.getData()), SIUnit.of(
                getDisplayUnit().getQuantity().getSiDimensions().plus(rel.getDisplayUnit().getQuantity().getSiDimensions())));
    }

    /**
     * Divide this Relative value by a Relative value for a vector or matrix. The division is done value by value and store the
     * result in a new Relative value. If both operands are dense, the result is a dense vector or matrix, otherwise the result
     * is a sparse vector or matrix.
     * @param rel VT; the right operand, which can be any vector type
     * @return SIVector; the division of this vector and the operand
     * @throws ValueRuntimeException in case this vector or matrix and the operand have a different size
     * @throws UnitException on unit error
     * @param <UT> the unit type of the multiplier
     * @param <ST> the scalar type of the multiplier
     * @param <VT> the vector type of the multiplier
     */
    public final <UT extends Unit<UT>, ST extends AbstractDoubleScalarRel<UT, ST>,
            VT extends AbstractDoubleVectorRel<UT, ST, VT>> SIVector divide(final VT rel)
                    throws ValueRuntimeException, UnitException
    {
        checkSize(rel);
        return new SIVector(this.getData().divide(rel.getData()), SIUnit.of(
                getDisplayUnit().getQuantity().getSiDimensions().minus(rel.getDisplayUnit().getQuantity().getSiDimensions())));
    }

    /** {@inheritDoc} */
    @Override
    public RV times(final double multiplier)
    {
        return clone().mutable().assign(DoubleMathFunctions.MULT(multiplier)).immutable();
    }

    /** {@inheritDoc} */
    @Override
    public RV divide(final double divisor)
    {
        return clone().mutable().assign(DoubleMathFunctions.DIV(divisor)).immutable();
    }

    /** {@inheritDoc} */
    @Override
    public RV times(final float multiplier)
    {
        return times((double) multiplier);
    }

    /** {@inheritDoc} */
    @Override
    public RV divide(final float divisor)
    {
        return divide((double) divisor);
    }

}
