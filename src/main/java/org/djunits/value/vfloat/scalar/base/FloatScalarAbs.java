package org.djunits.value.vfloat.scalar.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.Absolute;
import org.djunits.value.util.ValueUtil;

/**
 * The typed, abstract FloatScalarAbs class that forms the basis of all FloatScalar definitions and extensions.<br>
 * Note: A relative scalar class can implement the toAbs() method if it has an absolute equivalent.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <AU> the absolute unit
 * @param <A> the Absolute class for reference purposes
 * @param <RU> the relative unit
 * @param <R> the Relative class for reference purposes
 */
public abstract class FloatScalarAbs<AU extends AbsoluteLinearUnit<AU, RU>,
        A extends FloatScalarAbs<AU, A, RU, R>, RU extends Unit<RU>,
        R extends FloatScalarRelWithAbs<AU, A, RU, R>> extends FloatScalar<AU, A> implements Absolute<AU, A, RU, R>
{
    /**  */
    private static final long serialVersionUID = 20150626L;

    /**
     * Construct a new Absolute Immutable FloatScalar.
     * @param value the value of the new Absolute Immutable FloatScalar
     * @param unit the unit of the new Absolute Immutable FloatScalar
     */
    public FloatScalarAbs(final float value, final AU unit)
    {
        super(unit, unit.isBaseSIUnit() ? value : (float) ValueUtil.expressAsSIUnit(value, unit));
    }

    /**
     * Construct a new Absolute Immutable FloatScalar from an existing Absolute Immutable FloatScalar.
     * @param value the reference
     */
    public FloatScalarAbs(final A value)
    {
        super(value.getDisplayUnit(), value.getSI());
    }

    /**
     * Construct a new Relative Immutable FloatScalar of the right type. Each extending class must implement this method.
     * @param value the float value
     * @param unit the unit
     * @return R a new relative instance of the FloatScalar of the right type
     */
    public abstract R instantiateRel(float value, RU unit);

    /**
     * Construct a new Absolute Immutable FloatScalar of the right type. Each extending class must implement this method.
     * @param value the float value
     * @param unit the absolute unit
     * @return A a new absolute instance of the FloatScalar of the right type
     */
    public abstract A instantiateAbs(float value, AU unit);

    @Override
    public final A plus(final R increment)
    {
        AU targetUnit = getDisplayUnit();
        return instantiateAbs(getInUnit() + increment.getInUnit(targetUnit.getRelativeUnit()), targetUnit);
    }

    @Override
    public final A minus(final R decrement)
    {
        AU targetUnit = getDisplayUnit();
        return instantiateAbs(getInUnit() - decrement.getInUnit(targetUnit.getRelativeUnit()), targetUnit);
    }

    @Override
    public final R minus(final A decrement)
    {
        RU targetUnit = getDisplayUnit().getRelativeUnit();
        return instantiateRel(getInUnit() - decrement.getInUnit(getDisplayUnit()), targetUnit);
    }

    /**********************************************************************************/
    /********************************** MATH METHODS **********************************/
    /**********************************************************************************/

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public A abs()
    {
        return instantiateAbs(Math.abs(getInUnit()), getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public A ceil()
    {
        return instantiateAbs((float) Math.ceil(getInUnit()), getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public A floor()
    {
        return instantiateAbs((float) Math.floor(getInUnit()), getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public A neg()
    {
        return instantiateAbs(-getInUnit(), getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public A rint()
    {
        return instantiateAbs((float) Math.rint(getInUnit()), getDisplayUnit());
    }

}
