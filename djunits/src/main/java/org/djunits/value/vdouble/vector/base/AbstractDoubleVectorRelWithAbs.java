package org.djunits.value.vdouble.vector.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.base.Vector;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarAbs;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRelWithAbs;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * AbstractMutableDoubleVectorRelWithAbs.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <AU> the absolute unit belonging to the relative unit
 * @param <A> the absolute scalar type belonging to the absolute vector type
 * @param <AV> the (immutable or mutable) absolute vector type
 * @param <RU> the relative unit belonging to the absolute unit
 * @param <R> the relative scalar type belonging to the relative vector type
 * @param <RV> the relative (immutable or mutable) vector type with this unit
 */
// @formatter:off
public abstract class AbstractDoubleVectorRelWithAbs<
        AU  extends AbsoluteLinearUnit<AU, RU>, 
        A   extends AbstractDoubleScalarAbs<AU, A, RU, R>,
        AV  extends AbstractDoubleVectorAbs<AU, A, AV, RU, R, RV>,
        RU  extends Unit<RU>,
        R   extends AbstractDoubleScalarRelWithAbs<AU, A, RU, R>,
        RV  extends AbstractDoubleVectorRelWithAbs<AU, A, AV, RU, R, RV>>
        extends AbstractDoubleVectorRel<RU, R, RV>
        implements Vector.RelWithAbs<AU, A, AV, RU, R, RV>
// @formatter:on
{
    /** */
    private static final long serialVersionUID = 20190908L;

    /**
     * Construct a new Relative Mutable DoubleVector.
     * @param data DoubleVectorData; an internal data object
     * @param unit RU; the unit
     */
    protected AbstractDoubleVectorRelWithAbs(final DoubleVectorData data, final RU unit)
    {
        // data will be copied in AbstractMutableDoubleVectorRel
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public AV plus(final AV increment)
    {
        return instantiateVectorAbs(this.getData().plus(increment.getData()), increment.getDisplayUnit().getStandardUnit());
    }

    /**
     * Instantiate a new absolute vector of the class of this relative vector. This can be used instead of the
     * DoubleVector.instiantiate() methods in case another vector of this relative with absolute class is known. The method is
     * faster than DoubleVector.instantiate, and it will also work if the vector is user-defined.
     * @param dvd DoubleVectorData; the data used to instantiate the vector
     * @param displayUnit AU; the display unit of the absolute vector
     * @return AV; an absolute vector of the correct type, belonging to this relative vector type
     */
    public abstract AV instantiateVectorAbs(DoubleVectorData dvd, AU displayUnit);

    /**
     * Instantiate a new absolute scalar for the class of this relative vector. This can be used instead of the
     * DoubleScalar.instiantiate() methods in case a vector of this class is known. The method is faster than
     * DoubleScalar.instantiate, and it will also work if the vector and/or scalar are user-defined.
     * @param valueSI double; the SI value of the absolute scalar
     * @param displayUnit AU; the unit in which the absolute value will be displayed
     * @return A; an absolute scalar of the correct type, belonging to this relative vector type
     */
    public abstract A instantiateScalarAbsSI(double valueSI, AU displayUnit);
}
