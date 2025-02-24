package org.djunits.value.vdouble.matrix.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.RelWithAbs;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.base.DoubleScalarAbs;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRelWithAbs;
import org.djunits.value.vdouble.vector.base.DoubleVectorAbs;
import org.djunits.value.vdouble.vector.base.DoubleVectorRelWithAbs;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * AbstractMutableDoubleMatrixRelWithAbs.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
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
public abstract class DoubleMatrixRelWithAbs<
        AU  extends AbsoluteLinearUnit<AU, RU>, 
        A   extends DoubleScalarAbs<AU, A, RU, R>,
        AV  extends DoubleVectorAbs<AU, A, AV, RU, R, RV>,
        AM  extends DoubleMatrixAbs<AU, A, AV, AM, RU, R, RV, RM>, 
        RU  extends Unit<RU>,
        R   extends DoubleScalarRelWithAbs<AU, A, RU, R>,
        RV  extends DoubleVectorRelWithAbs<AU, A, AV, RU, R, RV>,
        RM  extends DoubleMatrixRelWithAbs<AU, A, AV, AM, RU, R, RV, RM>>
        extends DoubleMatrixRel<RU, R, RV, RM>
        implements RelWithAbs<AU, AM, RU, RM>
// @formatter:on
{
    /** */
    private static final long serialVersionUID = 20190908L;

    /**
     * Construct a new Relative Mutable DoubleMatrix.
     * @param data an internal data object
     * @param unit the unit
     */
    protected DoubleMatrixRelWithAbs(final DoubleMatrixData data, final RU unit)
    {
        // data will be copied in AbstractMutableDoubleMatrixRel
        super(data, unit);
    }

    @Override
    public AM plus(final AM increment)
    {
        return instantiateMatrixAbs(this.getData().plus(increment.getData()), increment.getDisplayUnit().getStandardUnit());
    }

    /**
     * Instantiate a new absolute matrix of the class of this relative matrix. This can be used instead of the
     * DoubleMatrix.instiantiate() methods in case another matrix of this relative with absolute class is known. The method is
     * faster than DoubleMatrix.instantiate, and it will also work if the matrix is user-defined.
     * @param dmd the data used to instantiate the matrix
     * @param displayUnit the display unit of the absolute matrix
     * @return an absolute matrix of the correct type, belonging to this relative matrix type
     */
    public abstract AM instantiateMatrixAbs(DoubleMatrixData dmd, AU displayUnit);

    /**
     * Instantiate a new absolute vector of the class of this relative matrix. This can be used instead of the
     * DoubleVector.instiantiate() methods in case another matrix of this relative with absolute class is known. The method is
     * faster than DoubleVector.instantiate, and it will also work if the matrix or vector is user-defined.
     * @param dvd the data used to instantiate the vector
     * @param displayUnit the display unit of the absolute vector
     * @return an absolute vector of the correct type, belonging to this relative matrix type
     */
    public abstract AV instantiateVectorAbs(DoubleVectorData dvd, AU displayUnit);

    /**
     * Instantiate a new absolute scalar for the class of this relative matrix. This can be used instead of the
     * DoubleScalar.instiantiate() methods in case a matrix of this class is known. The method is faster than
     * DoubleScalar.instantiate, and it will also work if the matrix and/or scalar are user-defined.
     * @param valueSI the SI value of the absolute scalar
     * @param displayUnit the unit in which the absolute value will be displayed
     * @return an absolute scalar of the correct type, belonging to this relative matrix type
     */
    public abstract A instantiateScalarAbsSI(double valueSI, AU displayUnit);

}
