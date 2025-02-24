package org.djunits.value.vdouble.scalar.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.Absolute;
import org.djunits.value.util.ValueUtil;

/**
 * The typed, abstract DoubleScalarAbs class that forms the basis of all DoubleScalar definitions and extensions.<br>
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
public abstract class DoubleScalarAbs<AU extends AbsoluteLinearUnit<AU, RU>,
        A extends DoubleScalarAbs<AU, A, RU, R>, RU extends Unit<RU>,
        R extends DoubleScalarRelWithAbs<AU, A, RU, R>> extends DoubleScalar<AU, A>
        implements Absolute<AU, A, RU, R>
{
    /**  */
    private static final long serialVersionUID = 20150626L;

    /**
     * Construct a new Absolute Immutable DoubleScalar.
     * @param value the value of the new Absolute Immutable DoubleScalar
     * @param unit the unit of the new Absolute Immutable DoubleScalar
     */
    public DoubleScalarAbs(final double value, final AU unit)
    {
        super(unit, unit.isBaseSIUnit() ? value : ValueUtil.expressAsSIUnit(value, unit));
    }

    /**
     * Construct a new Absolute Immutable DoubleScalar from an existing Absolute Immutable DoubleScalar.
     * @param value the reference
     */
    public DoubleScalarAbs(final A value)
    {
        super(value.getDisplayUnit(), value.getSI());
    }

    /**
     * Construct a new Relative Immutable DoubleScalar of the right type. Each extending class must implement this method.
     * @param value the double value
     * @param unit the unit
     * @return R a new relative instance of the DoubleScalar of the right type
     */
    public abstract R instantiateRel(double value, RU unit);

    /**
     * Construct a new Absolute Immutable DoubleScalar of the right type. Each extending class must implement this method.
     * @param value the double value
     * @param unit the absolute unit
     * @return A a new absolute instance of the DoubleScalar of the right type
     */
    public abstract A instantiateAbs(double value, AU unit);
    
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
    public A abs()
    {
        return instantiateAbs(Math.abs(getInUnit()), getDisplayUnit());
    }

    @Override
    public A ceil()
    {
        return instantiateAbs(Math.ceil(getInUnit()), getDisplayUnit());
    }

    @Override
    public A floor()
    {
        return instantiateAbs(Math.floor(getInUnit()), getDisplayUnit());
    }

    @Override
    public A neg()
    {
        return instantiateAbs(-getInUnit(), getDisplayUnit());
    }

    @Override
    public A rint()
    {
        return instantiateAbs(Math.rint(getInUnit()), getDisplayUnit());
    }

}
