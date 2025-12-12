package org.djunits.value.vdouble.scalar.base;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.Unit;
import org.djunits.value.RelWithAbs;

/**
 * The typed, abstract DoubleScalarRelWithAbs class that forms the basis of the relative DoubleScalars suck as Duration that
 * have an absolute equivalent such as Time.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 * @param <AU> the absolute unit
 * @param <A> the Absolute class for reference purposes
 * @param <RU> the relative unit
 * @param <R> the Relative class for reference purposes
 */
public abstract class DoubleScalarRelWithAbs<AU extends AbsoluteLinearUnit<AU, RU>, A extends DoubleScalarAbs<AU, A, RU, R>,
        RU extends Unit<RU>, R extends DoubleScalarRelWithAbs<AU, A, RU, R>> extends DoubleScalarRel<RU, R>
        implements RelWithAbs<AU, A, RU, R>
{
    /**  */
    private static final long serialVersionUID = 20150626L;

    /**
     * Construct a new Relative Immutable DoubleScalar.
     * @param value the value of the new Relative Immutable DoubleScalar
     * @param unit the unit of the new Relative Immutable DoubleScalar
     */
    public DoubleScalarRelWithAbs(final double value, final RU unit)
    {
        super(value, unit);
    }

    /**
     * Construct a new Relative Immutable DoubleScalar from an existing Relative Immutable DoubleScalar.
     * @param value the reference
     */
    public DoubleScalarRelWithAbs(final R value)
    {
        super(value);
    }

    /**
     * Construct a new Absolute Immutable DoubleScalar of the right type. Each extending class must implement this method.
     * @param value the double value
     * @param unit the absolute unit
     * @return A a new absolute instance of the DoubleScalar of the right type
     */
    public abstract A instantiateAbs(double value, AU unit);

    @Override
    public A plus(final A increment)
    {
        A result = instantiateAbs(this.getSI() + increment.getSI(), increment.getDisplayUnit().getStandardUnit());
        result.setDisplayUnit(increment.getDisplayUnit());
        return result;
    }

}
