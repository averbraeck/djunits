package org.djunits.value.vfloat.vector.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.RelWithAbs;
import org.djunits.value.vfloat.scalar.base.FloatScalarAbs;
import org.djunits.value.vfloat.scalar.base.FloatScalarRelWithAbs;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * AbstractMutableFloatVectorRelWithAbs.java.
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
public abstract class FloatVectorRelWithAbs<
        AU  extends AbsoluteLinearUnit<AU, RU>, 
        A   extends FloatScalarAbs<AU, A, RU, R>,
        AV  extends FloatVectorAbs<AU, A, AV, RU, R, RV>,
        RU  extends Unit<RU>,
        R   extends FloatScalarRelWithAbs<AU, A, RU, R>,
        RV  extends FloatVectorRelWithAbs<AU, A, AV, RU, R, RV>>
        extends FloatVectorRel<RU, R, RV>
        implements RelWithAbs<AU, AV, RU, RV>
// @formatter:on
{
    /** */
    private static final long serialVersionUID = 20190908L;

    /**
     * Construct a new Relative Mutable FloatVector.
     * @param data an internal data object
     * @param unit the unit
     */
    protected FloatVectorRelWithAbs(final FloatVectorData data, final RU unit)
    {
        // data will be copied in AbstractMutableFloatVectorRel
        super(data, unit);
    }

    @Override
    public AV plus(final AV increment)
    {
        return instantiateVectorAbs(this.getData().plus(increment.getData()), increment.getDisplayUnit().getStandardUnit());
    }

    /**
     * Instantiate a new absolute vector of the class of this relative vector. This can be used instead of the
     * FloatVector.instiantiate() methods in case another vector of this relative with absolute class is known. The method is
     * faster than FloatVector.instantiate, and it will also work if the vector is user-defined.
     * @param dvd the data used to instantiate the vector
     * @param displayUnit the display unit of the absolute vector
     * @return an absolute vector of the correct type, belonging to this relative vector type
     */
    public abstract AV instantiateVectorAbs(FloatVectorData dvd, AU displayUnit);

    /**
     * Instantiate a new absolute scalar for the class of this relative vector. This can be used instead of the
     * FloatScalar.instiantiate() methods in case a vector of this class is known. The method is faster than
     * FloatScalar.instantiate, and it will also work if the vector and/or scalar are user-defined.
     * @param valueSI the SI value of the absolute scalar
     * @param displayUnit the unit in which the absolute value will be displayed
     * @return an absolute scalar of the correct type, belonging to this relative vector type
     */
    public abstract A instantiateScalarAbsSI(float valueSI, AU displayUnit);

}
