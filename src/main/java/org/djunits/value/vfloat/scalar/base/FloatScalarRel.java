package org.djunits.value.vfloat.scalar.base;

import org.djunits.unit.Unit;
import org.djunits.value.Relative;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.FloatSIScalar;

/**
 * The typed, abstract FloatScalarRel class that forms the basis of all FloatScalar definitions and extensions.<br>
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
public abstract class FloatScalarRel<U extends Unit<U>, R extends FloatScalarRel<U, R>>
        extends FloatScalar<U, R> implements Relative<U, R>
{
    /**  */
    private static final long serialVersionUID = 20150626L;

    /**
     * Construct a new Relative Immutable FloatScalar.
     * @param value float; the value of the new Relative Immutable FloatScalar
     * @param unit U; the unit of the new Relative Immutable FloatScalar
     */
    public FloatScalarRel(final float value, final U unit)
    {
        super(unit, unit.isBaseSIUnit() ? value : (float) ValueUtil.expressAsSIUnit(value, unit));
    }

    /**
     * Construct a new Relative Immutable FloatScalar from an existing Relative Immutable FloatScalar.
     * @param value R, a relative typed FloatScalar; the reference
     */
    public FloatScalarRel(final R value)
    {
        super(value.getDisplayUnit(), value.getSI());
    }

    /**
     * Construct a new Relative Immutable FloatScalar of the right type. Each extending class must implement this method.
     * @param value float; the float value
     * @param unit U; the unit
     * @return R a new relative instance of the FloatScalar of the right type
     */
    public abstract R instantiateRel(float value, U unit);

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
     * @param otherScalar FloatScalarRel&lt;?, ?&gt;; the value by which this scalar is multiplied
     * @return FloatScalar&lt;?&gt;; a new scalar instance with correct SI dimensions
     */
    public FloatSIScalar times(final FloatScalarRel<?, ?> otherScalar)
    {
        return FloatScalar.multiply(this, otherScalar);
    }

    /**
     * Create the reciprocal of this scalar with the correct dimensions.
     * @return FloatScalar&lt;?&gt;; a new scalar instance with correct SI dimensions
     */
    public abstract FloatScalarRel<?, ?> reciprocal();

    /**
     * Divide this scalar by another scalar and create a new scalar.
     * @param otherScalar FloatScalarRel&lt;?, ?&gt;; the value by which this scalar is divided
     * @return FloatScalar&lt;?&gt;; a new scalar instance with correct SI dimensions
     */
    public FloatSIScalar divide(final FloatScalarRel<?, ?> otherScalar)
    {
        return FloatScalar.divide(this, otherScalar);
    }

    /**********************************************************************************/
    /********************************** MATH METHODS **********************************/
    /**********************************************************************************/

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public R abs()
    {
        return instantiateRel(Math.abs(getInUnit()), getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public R ceil()
    {
        return instantiateRel((float) Math.ceil(getInUnit()), getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public R floor()
    {
        return instantiateRel((float) Math.floor(getInUnit()), getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public R rint()
    {
        return instantiateRel((float) Math.rint(getInUnit()), getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public R neg()
    {
        return instantiateRel(-getInUnit(), getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public R times(final double constant)
    {
        return instantiateRel((float) (getInUnit() * constant), getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public R divide(final double constant)
    {
        return instantiateRel((float) (getInUnit() / constant), getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public R times(final float constant)
    {
        return instantiateRel(getInUnit() * constant, getDisplayUnit());
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public R divide(final float constant)
    {
        return instantiateRel(getInUnit() / constant, getDisplayUnit());
    }

}
