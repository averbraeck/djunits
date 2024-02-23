package org.djunits.value.vdouble.scalar.base;

import org.djunits.unit.Unit;
import org.djunits.value.Relative;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.SIScalar;

/**
 * The typed, abstract DoubleScalarRel class that forms the basis of all DoubleScalar definitions and extensions.<br>
 * Note: A relative scalar class can implement the toAbs() method if it has an absolute equivalent.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit
 * @param <R> the Relative class for reference purposes
 */
public abstract class DoubleScalarRel<U extends Unit<U>, R extends DoubleScalarRel<U, R>>
        extends DoubleScalar<U, R> implements Relative<U, R>
{
    /**  */
    private static final long serialVersionUID = 20150626L;

    /**
     * Construct a new Relative Immutable DoubleScalar.
     * @param value double; the value of the new Relative Immutable DoubleScalar
     * @param unit U; the unit of the new Relative Immutable DoubleScalar
     */
    public DoubleScalarRel(final double value, final U unit)
    {
        super(unit, unit.isBaseSIUnit() ? value : ValueUtil.expressAsSIUnit(value, unit));
    }

    /**
     * Construct a new Relative Immutable DoubleScalar from an existing Relative Immutable DoubleScalar.
     * @param value R, a relative typed DoubleScalar; the reference
     */
    public DoubleScalarRel(final R value)
    {
        super(value.getDisplayUnit(), value.getSI());
    }

    /**
     * Construct a new Relative Immutable DoubleScalar of the right type. Each extending class must implement this method.
     * @param value double; the double value
     * @param unit U; the unit
     * @return R a new relative instance of the DoubleScalar of the right type
     */
    public abstract R instantiateRel(double value, U unit);

    /** {@inheritDoc} */
    @Override
    public final R plus(final R increment)
    {
        if (getDisplayUnit().isBaseSIUnit())
        {
            return instantiateRel(this.getSI() + increment.getSI(), getDisplayUnit().getStandardUnit());
        }
        return getDisplayUnit().equals(increment.getDisplayUnit())
                ? instantiateRel(getInUnit() + increment.getInUnit(), getDisplayUnit())
                : instantiateRel(this.getSI() + increment.getSI(), getDisplayUnit().getStandardUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final R minus(final R decrement)
    {
        if (getDisplayUnit().isBaseSIUnit())
        {
            return instantiateRel(this.getSI() - decrement.getSI(), getDisplayUnit().getStandardUnit());
        }
        return getDisplayUnit().equals(decrement.getDisplayUnit())
                ? instantiateRel(getInUnit() - decrement.getInUnit(), getDisplayUnit())
                : instantiateRel(this.getSI() - decrement.getSI(), getDisplayUnit().getStandardUnit());
    }

    /**
     * Multiply this scalar by another scalar and create a new scalar.
     * @param otherScalar DoubleScalarRel&lt;?, ?&gt;; the value by which this scalar is multiplied
     * @return DoubleScalar&lt;?&gt;; a new scalar instance with correct SI dimensions
     */
    public SIScalar times(final DoubleScalarRel<?, ?> otherScalar)
    {
        return DoubleScalar.multiply(this, otherScalar);
    }

    /**
     * Create the reciprocal of this scalar with the correct dimensions.
     * @return DoubleScalar&lt;?&gt;; a new scalar instance with correct SI dimensions
     */
    public abstract DoubleScalarRel<?, ?> reciprocal();

    /**
     * Divide this scalar by another scalar and create a new scalar.
     * @param otherScalar DoubleScalarRel&lt;?, ?&gt;; the value by which this scalar is divided
     * @return DoubleScalar&lt;?&gt;; a new scalar instance with correct SI dimensions
     */
    public SIScalar divide(final DoubleScalarRel<?, ?> otherScalar)
    {
        return DoubleScalar.divide(this, otherScalar);
    }

    /**********************************************************************************/
    /********************************** MATH METHODS **********************************/
    /**********************************************************************************/

    /** {@inheritDoc} */
    @Override
    public R abs()
    {
        return instantiateRel(Math.abs(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public R ceil()
    {
        return instantiateRel(Math.ceil(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public R floor()
    {
        return instantiateRel(Math.floor(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public R rint()
    {
        return instantiateRel(Math.rint(getInUnit()), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public R neg()
    {
        return instantiateRel(-getInUnit(), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public R times(final double constant)
    {
        return instantiateRel(getInUnit() * constant, getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public R divide(final double constant)
    {
        return instantiateRel(getInUnit() / constant, getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public R times(final float constant)
    {
        return instantiateRel(getInUnit() * constant, getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public R divide(final float constant)
    {
        return instantiateRel(getInUnit() / constant, getDisplayUnit());
    }

}
