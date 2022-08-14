package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.AccelerationUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAcceleration;
import org.djunits.value.vfloat.vector.FloatAccelerationVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatAccelerationMatrix, a matrix of values with a AccelerationUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAccelerationMatrix
        extends AbstractFloatMatrixRel<AccelerationUnit, FloatAcceleration, FloatAccelerationVector, FloatAccelerationMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit AccelerationUnit; the unit
     */
    public FloatAccelerationMatrix(final FloatMatrixData data, final AccelerationUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAcceleration> getScalarClass()
    {
        return FloatAcceleration.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAccelerationVector> getVectorClass()
    {
        return FloatAccelerationVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAccelerationMatrix instantiateMatrix(final FloatMatrixData fmd, final AccelerationUnit displayUnit)
    {
        return new FloatAccelerationMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAccelerationVector instantiateVector(final FloatVectorData fvd, final AccelerationUnit displayUnit)
    {
        return new FloatAccelerationVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAcceleration instantiateScalarSI(final float valueSI, final AccelerationUnit displayUnit)
    {
        FloatAcceleration result = FloatAcceleration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
