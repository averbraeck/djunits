package org.djunits.value.vdouble.vector.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.Absolute;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.scalar.base.DoubleScalarAbs;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRelWithAbs;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * AbstractMutableDoubleVectorRelWithAbs.java.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <AU> the absolute unit belonging to the relative unit
 * @param <A> the absolute scalar type belonging to the absolute vector type
 * @param <AV> the (immutable or mutable) absolute vector type
 * @param <RU> the relative unit belonging to the absolute unit
 * @param <R> the relative scalar type belonging to the relative vector type
 * @param <RV> the relative (immutable or mutable) vector type with this unit
 */
// @formatter:off
public abstract class DoubleVectorAbs<
        AU  extends AbsoluteLinearUnit<AU, RU>, 
        A   extends DoubleScalarAbs<AU, A, RU, R>,
        AV  extends DoubleVectorAbs<AU, A, AV, RU, R, RV>, 
        RU  extends Unit<RU>,
        R   extends DoubleScalarRelWithAbs<AU, A, RU, R>,
        RV  extends DoubleVectorRelWithAbs<AU, A, AV, RU, R, RV>>
        extends DoubleVector<AU, A, AV>
        implements Absolute<AU, AV, RU, RV>
// @formatter:on
{
    /** */
    private static final long serialVersionUID = 20190908L;

    /**
     * Construct a new Relative Mutable DoubleVector.
     * @param data an internal data object
     * @param unit the unit
     */
    protected DoubleVectorAbs(final DoubleVectorData data, final AU unit)
    {
        super(data.copy(), unit);
    }

    @Override
    public AV plus(final RV increment) throws ValueRuntimeException
    {
        return instantiateVector(this.getData().plus(increment.getData()), getDisplayUnit());
    }

    @Override
    public AV minus(final RV decrement) throws ValueRuntimeException
    {
        return instantiateVector(this.getData().minus(decrement.getData()), getDisplayUnit());
    }

    @Override
    public RV minus(final AV decrement) throws ValueRuntimeException
    {
        return instantiateVectorRel(this.getData().minus(decrement.getData()), decrement.getDisplayUnit().getRelativeUnit());
    }

    /**
     * Decrement all values of this vector by the decrement. This only works if this vector is mutable.
     * @param decrement the scalar by which to decrement all values
     * @return this modified vector
     * @throws ValueRuntimeException in case this vector is immutable
     */
    @SuppressWarnings("unchecked")
    public AV decrementBy(final R decrement)
    {
        checkCopyOnWrite();
        assign(DoubleMathFunctions.DEC(decrement.si));
        return (AV) this;
    }

    /**
     * Decrement all values of this vector by the decrement on a value by value basis. This only works if this vector is
     * mutable.
     * @param decrement the vector that contains the values by which to decrement the corresponding values
     * @return this modified vector
     * @throws ValueRuntimeException in case this vector is immutable, when the sizes of the vectors differ, or
     *             <code>decrement</code> is null
     */
    @SuppressWarnings("unchecked")
    public AV decrementBy(final RV decrement)
    {
        checkCopyOnWrite();
        getData().decrementBy(decrement.getData());
        return (AV) this;
    }

    /**
     * Instantiate a new relative vector of the class of this absolute vector. This can be used instead of the
     * DoubleVector.instiantiate() methods in case another vector of this absolute vector class is known. The method is faster
     * than DoubleVector.instantiate, and it will also work if the vector is user-defined.
     * @param dvd the data used to instantiate the vector
     * @param displayUnit the display unit of the relative vector
     * @return a relative vector of the correct type, belonging to this absolute vector type
     */
    public abstract RV instantiateVectorRel(DoubleVectorData dvd, RU displayUnit);

    /**
     * Instantiate a new relative scalar for the class of this absolute vector. This can be used instead of the
     * DoubleScalar.instiantiate() methods in case a vector of this class is known. The method is faster than
     * DoubleScalar.instantiate, and it will also work if the vector and/or scalar are user-defined.
     * @param valueSI the SI value of the relative scalar
     * @param displayUunit the unit in which the relative value will be displayed
     * @return a relative scalar of the correct type, belonging to this absolute vector type
     */
    public abstract R instantiateScalarRelSI(double valueSI, RU displayUunit);

}
