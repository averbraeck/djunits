package org.djunits.value.vfloat.scalar.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.RelWithAbs;

/**
 * The typed, abstract FloatScalarRelWithAbs class that forms the basis of the relative FloatScalars suck as Duration that have
 * an absolute equivalent such as Time.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <AU> the absolute unit
 * @param <A> the Absolute class for reference purposes
 * @param <RU> the relative unit
 * @param <R> the Relative class for reference purposes
 */
public abstract class FloatScalarRelWithAbs<AU extends AbsoluteLinearUnit<AU, RU>,
        A extends FloatScalarAbs<AU, A, RU, R>, RU extends Unit<RU>,
        R extends FloatScalarRelWithAbs<AU, A, RU, R>> extends FloatScalarRel<RU, R>
        implements RelWithAbs<AU, A, RU, R>
{
    /**  */
    private static final long serialVersionUID = 20150626L;

    /**
     * Construct a new Relative Immutable FloatScalar.
     * @param value float; the value of the new Relative Immutable FloatScalar
     * @param unit RU; the unit of the new Relative Immutable FloatScalar
     */
    public FloatScalarRelWithAbs(final float value, final RU unit)
    {
        super(value, unit);
    }

    /**
     * Construct a new Absolute Immutable FloatScalar of the right type. Each extending class must implement this method.
     * @param value float; the float value
     * @param unit AU; the absolute unit
     * @return A a new absolute instance of the FloatScalar of the right type
     */
    public abstract A instantiateAbs(float value, AU unit);

    /**
     * Construct a new Relative Immutable FloatScalar from an existing Relative Immutable FloatScalar.
     * @param value R, a relative typed FloatScalar; the reference
     */
    public FloatScalarRelWithAbs(final R value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public A plus(final A increment)
    {
        A result = instantiateAbs(this.getSI() + increment.getSI(), increment.getDisplayUnit().getStandardUnit());
        result.setDisplayUnit(increment.getDisplayUnit());
        return result;
    }

}
