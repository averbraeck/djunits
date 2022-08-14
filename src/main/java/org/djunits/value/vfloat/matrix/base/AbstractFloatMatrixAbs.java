package org.djunits.value.vfloat.matrix.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.Absolute;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.base.Matrix;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarAbs;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRelWithAbs;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorAbs;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRelWithAbs;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * AbstractMutableFloatMatrixRelWithAbs.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
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
public abstract class AbstractFloatMatrixAbs<
        AU  extends AbsoluteLinearUnit<AU, RU>, 
        A   extends AbstractFloatScalarAbs<AU, A, RU, R>,
        AV  extends AbstractFloatVectorAbs<AU, A, AV, RU, R, RV>,
        AM  extends AbstractFloatMatrixAbs<AU, A, AV, AM, RU, R, RV, RM>, 
        RU  extends Unit<RU>,
        R   extends AbstractFloatScalarRelWithAbs<AU, A, RU, R>,
        RV  extends AbstractFloatVectorRelWithAbs<AU, A, AV, RU, R, RV>,
        RM  extends AbstractFloatMatrixRelWithAbs<AU, A, AV, AM, RU, R, RV, RM>>
        extends AbstractFloatMatrix<AU, A, AV, AM>
        implements Matrix.Abs<AU, A, AV, AM, RU, R, RV, RM>, Absolute
// @formatter:on
{
    /** */
    private static final long serialVersionUID = 20190908L;

    /**
     * Construct a new Relative Mutable FloatMatrix.
     * @param data FloatMatrixData; an internal data object
     * @param unit AU; the unit
     */
    protected AbstractFloatMatrixAbs(final FloatMatrixData data, final AU unit)
    {
        super(data.copy(), unit);
    }

    /** {@inheritDoc} */
    @Override
    public AM plus(final RM increment) throws ValueRuntimeException
    {
        return instantiateMatrix(this.getData().plus(increment.getData()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public AM minus(final RM decrement) throws ValueRuntimeException
    {
        return instantiateMatrix(this.getData().minus(decrement.getData()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public RM minus(final AM decrement) throws ValueRuntimeException
    {
        return instantiateMatrixRel(this.getData().minus(decrement.getData()), decrement.getDisplayUnit().getRelativeUnit());
    }

    /**
     * Decrement all values of this matrix by the decrement. This only works if this matrix is mutable.
     * @param decrement R; the scalar by which to decrement all values
     * @return AM; this modified vector
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
     * @param decrement RM; the matrix that contains the values by which to decrement the corresponding values
     * @return AV; this modified matrix
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
     * @param dmd FloatMatrixData; the data used to instantiate the matrix
     * @param displayUnit RU; the display unit of the relative matrix
     * @return RM; a relative matrix of the correct type, belonging to this absolute matrix type
     */
    public abstract RM instantiateMatrixRel(FloatMatrixData dmd, RU displayUnit);

    /**
     * Instantiate a new relative vector of the class of this absolute matrix. This can be used instead of the
     * FloatVector.instiantiate() methods in case another matrix of this absolute matrix class is known. The method is faster
     * than FloatVector.instantiate, and it will also work if the matrix or vector is user-defined.
     * @param dvd FloatVectorData; the data used to instantiate the vector
     * @param displayUnit RU; the display unit of the relative vector
     * @return RV; a relative vector of the correct type, belonging to this absolute matrix type
     */
    public abstract RV instantiateVectorRel(FloatVectorData dvd, RU displayUnit);

    /**
     * Instantiate a new relative scalar for the class of this absolute matrix. This can be used instead of the
     * FloatScalar.instiantiate() methods in case a matrix of this class is known. The method is faster than
     * FloatScalar.instantiate, and it will also work if the matrix and/or scalar are user-defined.
     * @param valueSI float; the SI value of the relative scalar
     * @param displayUnit RU; the unit in which the relative value will be displayed
     * @return R; a relative scalar of the correct type, belonging to this absolute matrix type
     */
    public abstract R instantiateScalarRelSI(float valueSI, RU displayUnit);

}
