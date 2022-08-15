package org.djunits.value.vdouble.scalar.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.util.ValueUtil;

/**
 * The typed, abstract DoubleScalarAbs class that forms the basis of all DoubleScalar definitions and extensions.<br>
 * Note: A relative scalar class can implement the toAbs() method if it has an absolute equivalent.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <AU> the absolute unit
 * @param <A> the Absolute class for reference purposes
 * @param <RU> the relative unit
 * @param <R> the Relative class for reference purposes
 */
public abstract class AbstractDoubleScalarAbs<AU extends AbsoluteLinearUnit<AU, RU>,
        A extends AbstractDoubleScalarAbs<AU, A, RU, R>, RU extends Unit<RU>,
        R extends AbstractDoubleScalarRelWithAbs<AU, A, RU, R>> extends AbstractDoubleScalar<AU, A>
        implements DoubleScalarInterface.Abs<AU, A, RU, R>
{
    /**  */
    private static final long serialVersionUID = 20150626L;

    /**
     * Construct a new Absolute Immutable DoubleScalar.
     * @param value double; the value of the new Absolute Immutable DoubleScalar
     * @param unit AU; the unit of the new Absolute Immutable DoubleScalar
     */
    public AbstractDoubleScalarAbs(final double value, final AU unit)
    {
        super(unit, unit.isBaseSIUnit() ? value : ValueUtil.expressAsSIUnit(value, unit));
    }

    /**
     * Construct a new Absolute Immutable DoubleScalar from an existing Absolute Immutable DoubleScalar.
     * @param value A, an absolute typed DoubleScalar; the reference
     */
    public AbstractDoubleScalarAbs(final A value)
    {
        super(value.getDisplayUnit(), value.getSI());
    }

    /** {@inheritDoc} */
    @Override
    public final A plus(final R increment)
    {
        AU targetUnit = getDisplayUnit();
        return instantiateAbs(getInUnit() + increment.getInUnit(targetUnit.getRelativeUnit()), targetUnit);
    }

    /** {@inheritDoc} */
    @Override
    public final A minus(final R decrement)
    {
        AU targetUnit = getDisplayUnit();
        return instantiateAbs(getInUnit() - decrement.getInUnit(targetUnit.getRelativeUnit()), targetUnit);
    }

    /** {@inheritDoc} */
    @Override
    public final R minus(final A decrement)
    {
        RU targetUnit = getDisplayUnit().getRelativeUnit();
        return instantiateRel(getInUnit() - decrement.getInUnit(getDisplayUnit()), targetUnit);
    }

    /**********************************************************************************/
    /********************************** MATH METHODS **********************************/
    /**********************************************************************************/

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public A abs()
    {
        return instantiateAbs(Math.abs(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public A ceil()
    {
        return instantiateAbs(Math.ceil(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public A floor()
    {
        return instantiateAbs(Math.floor(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public A neg()
    {
        return instantiateAbs(-getInUnit(), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public A rint()
    {
        return instantiateAbs(Math.rint(getInUnit()), getDisplayUnit());
    }

}
