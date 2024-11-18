package org.djunits.value.vfloat.matrix.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.Absolute;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.base.FloatScalarAbs;
import org.djunits.value.vfloat.scalar.base.FloatScalarRelWithAbs;
import org.djunits.value.vfloat.vector.base.FloatVectorAbs;
import org.djunits.value.vfloat.vector.base.FloatVectorRelWithAbs;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * AbstractMutableFloatMatrixRelWithAbs.java.
 * <p>
 * Copyright (c) 2019-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <AU> the absolute unit belonging to the relative unit
 * @param <A> the absolute scalar type belonging to the absolute matrix type
 * @param <AV> the absolute vector type belonging to the absolute matrix type
 * @param <AM> the (immutable or mutable) absolute matrix type
 * @param <RU> the relative unit belonging to the absolute unit
 * @param <R> the relative scalar type belonging to the relative matrix type
 * @param <RV> the relative vector type belonging to the relative matrix type
 * @param <RM> the relative (immutable or mutable) matrix type with this unit
 */
// @formatter:off
public abstract class FloatMatrixAbs<
        AU  extends AbsoluteLinearUnit<AU, RU>, 
        A   extends FloatScalarAbs<AU, A, RU, R>,
        AV  extends FloatVectorAbs<AU, A, AV, RU, R, RV>,
        AM  extends FloatMatrixAbs<AU, A, AV, AM, RU, R, RV, RM>, 
        RU  extends Unit<RU>,
        R   extends FloatScalarRelWithAbs<AU, A, RU, R>,
        RV  extends FloatVectorRelWithAbs<AU, A, AV, RU, R, RV>,
        RM  extends FloatMatrixRelWithAbs<AU, A, AV, AM, RU, R, RV, RM>>
        extends FloatMatrix<AU, A, AV, AM>
        implements Absolute<AU, AM, RU, RM>
// @formatter:on
{
    /** */
    private static final long serialVersionUID = 20190908L;

    /**
     * Construct a new Relative Mutable FloatMatrix.
     * @param data an internal data object
     * @param unit the unit
     */
    protected FloatMatrixAbs(final FloatMatrixData data, final AU unit)
    {
        super(data.copy(), unit);
    }

    @Override
    public AM plus(final RM increment) throws ValueRuntimeException
    {
        return instantiateMatrix(this.getData().plus(increment.getData()), getDisplayUnit());
    }

    @Override
    public AM minus(final RM decrement) throws ValueRuntimeException
    {
        return instantiateMatrix(this.getData().minus(decrement.getData()), getDisplayUnit());
    }

    @Override
    public RM minus(final AM decrement) throws ValueRuntimeException
    {
        return instantiateMatrixRel(this.getData().minus(decrement.getData()), decrement.getDisplayUnit().getRelativeUnit());
    }

    /**
     * Decrement all values of this matrix by the decrement. This only works if this matrix is mutable.
     * @param decrement the scalar by which to decrement all values
     * @return this modified vector
     * @throws ValueRuntimeException in case this vector is immutable
     */
    @SuppressWarnings("unchecked")
    public AM decrementBy(final R decrement)
    {
        checkCopyOnWrite();
        assign(FloatMathFunctions.DEC(decrement.si));
        return (AM) this;
    }

    /**
     * Decrement all values of this matrix by the decrement on a value by value basis. This only works if this matrix is
     * mutable.
     * @param decrement the matrix that contains the values by which to decrement the corresponding values
     * @return this modified matrix
     * @throws ValueRuntimeException in case this matrix is immutable or when the sizes of the matrices differ
     */
    @SuppressWarnings("unchecked")
    public AM decrementBy(final RM decrement)
    {
        checkCopyOnWrite();
        this.data.decrementBy(decrement.getData());
        return (AM) this;
    }

    /**
     * Instantiate a new relative matrix of the class of this absolute matrix. This can be used instead of the
     * FloatMatrix.instiantiate() methods in case another matrix of this absolute matrix class is known. The method is faster
     * than FloatMatrix.instantiate, and it will also work if the matrix is user-defined.
     * @param dmd the data used to instantiate the matrix
     * @param displayUnit the display unit of the relative matrix
     * @return a relative matrix of the correct type, belonging to this absolute matrix type
     */
    public abstract RM instantiateMatrixRel(FloatMatrixData dmd, RU displayUnit);

    /**
     * Instantiate a new relative vector of the class of this absolute matrix. This can be used instead of the
     * FloatVector.instiantiate() methods in case another matrix of this absolute matrix class is known. The method is faster
     * than FloatVector.instantiate, and it will also work if the matrix or vector is user-defined.
     * @param dvd the data used to instantiate the vector
     * @param displayUnit the display unit of the relative vector
     * @return a relative vector of the correct type, belonging to this absolute matrix type
     */
    public abstract RV instantiateVectorRel(FloatVectorData dvd, RU displayUnit);

    /**
     * Instantiate a new relative scalar for the class of this absolute matrix. This can be used instead of the
     * FloatScalar.instiantiate() methods in case a matrix of this class is known. The method is faster than
     * FloatScalar.instantiate, and it will also work if the matrix and/or scalar are user-defined.
     * @param valueSI the SI value of the relative scalar
     * @param displayUnit the unit in which the relative value will be displayed
     * @return a relative scalar of the correct type, belonging to this absolute matrix type
     */
    public abstract R instantiateScalarRelSI(float valueSI, RU displayUnit);

}
