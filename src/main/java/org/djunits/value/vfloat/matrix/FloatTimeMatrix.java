package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixAbs;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatDuration;
import org.djunits.value.vfloat.scalar.FloatTime;
import org.djunits.value.vfloat.vector.FloatDurationVector;
import org.djunits.value.vfloat.vector.FloatTimeVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatTime Matrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatTimeMatrix extends AbstractFloatMatrixAbs<TimeUnit, FloatTime, FloatTimeVector, FloatTimeMatrix, DurationUnit,
        FloatDuration, FloatDurationVector, FloatDurationMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit TimeUnit; the unit
     */
    public FloatTimeMatrix(final FloatMatrixData data, final TimeUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatTime> getScalarClass()
    {
        return FloatTime.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatTimeVector> getVectorClass()
    {
        return FloatTimeVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatTimeMatrix instantiateMatrix(final FloatMatrixData fmd, final TimeUnit displayUnit)
    {
        return new FloatTimeMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatTimeVector instantiateVector(final FloatVectorData fvd, final TimeUnit displayUnit)
    {
        return new FloatTimeVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatTime instantiateScalarSI(final float valueSI, final TimeUnit displayUnit)
    {
        FloatTime result = FloatTime.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public FloatDurationMatrix instantiateMatrixRel(final FloatMatrixData fmd, final DurationUnit displayUnit)
    {
        return new FloatDurationMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDurationVector instantiateVectorRel(final FloatVectorData fvd, final DurationUnit displayUnit)
    {
        return new FloatDurationVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDuration instantiateScalarRelSI(final float valueSI, final DurationUnit displayUnit)
    {
        FloatDuration result = FloatDuration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
