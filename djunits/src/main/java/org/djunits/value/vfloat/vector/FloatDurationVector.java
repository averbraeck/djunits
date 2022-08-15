package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.vfloat.scalar.FloatDuration;
import org.djunits.value.vfloat.scalar.FloatTime;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRelWithAbs;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Relative FloatDuration Vector.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatDurationVector extends
        AbstractFloatVectorRelWithAbs<TimeUnit, FloatTime, FloatTimeVector, DurationUnit, FloatDuration, FloatDurationVector>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a new Relative Immutable FloatDurationVector.
     * @param data FloatVectorData; an internal data object
     * @param unit DurationUnit; the unit
     */
    public FloatDurationVector(final FloatVectorData data, final DurationUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatDuration> getScalarClass()
    {
        return FloatDuration.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatDurationVector instantiateVector(final FloatVectorData fvd, final DurationUnit displayUnit)
    {
        return new FloatDurationVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDuration instantiateScalarSI(final float valueSI, final DurationUnit displayUnit)
    {
        FloatDuration result = FloatDuration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public FloatTimeVector instantiateVectorAbs(final FloatVectorData fvd, final TimeUnit displayUnit)
    {
        return new FloatTimeVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatTime instantiateScalarAbsSI(final float valueSI, final TimeUnit displayUnit)
    {
        FloatTime result = FloatTime.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
