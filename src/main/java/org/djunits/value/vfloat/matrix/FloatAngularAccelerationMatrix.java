package org.djunits.value.vfloat.matrix;

import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAngularAcceleration;
import org.djunits.value.vfloat.vector.FloatAngularAccelerationVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatAngularAccelerationMatrix, a matrix of values with a AngularAccelerationUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-01-21T20:18:25.227867Z")
public class FloatAngularAccelerationMatrix extends AbstractFloatMatrixRel<AngularAccelerationUnit, FloatAngularAcceleration,
        FloatAngularAccelerationVector, FloatAngularAccelerationMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit AngularAccelerationUnit; the unit
     */
    public FloatAngularAccelerationMatrix(final FloatMatrixData data, final AngularAccelerationUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAngularAcceleration> getScalarClass()
    {
        return FloatAngularAcceleration.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAngularAccelerationVector> getVectorClass()
    {
        return FloatAngularAccelerationVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngularAccelerationMatrix instantiateMatrix(final FloatMatrixData fmd,
            final AngularAccelerationUnit displayUnit)
    {
        return new FloatAngularAccelerationMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngularAccelerationVector instantiateVector(final FloatVectorData fvd,
            final AngularAccelerationUnit displayUnit)
    {
        return new FloatAngularAccelerationVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngularAcceleration instantiateScalarSI(final float valueSI, final AngularAccelerationUnit displayUnit)
    {
        FloatAngularAcceleration result = FloatAngularAcceleration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
