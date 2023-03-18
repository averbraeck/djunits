package org.djunits.value.vfloat.matrix;

import org.djunits.unit.SpeedUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatSpeed;
import org.djunits.value.vfloat.vector.FloatSpeedVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatSpeedMatrix, a matrix of values with a SpeedUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-01-21T20:18:25.227867Z")
public class FloatSpeedMatrix extends AbstractFloatMatrixRel<SpeedUnit, FloatSpeed, FloatSpeedVector, FloatSpeedMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit SpeedUnit; the unit
     */
    public FloatSpeedMatrix(final FloatMatrixData data, final SpeedUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatSpeed> getScalarClass()
    {
        return FloatSpeed.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatSpeedVector> getVectorClass()
    {
        return FloatSpeedVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatSpeedMatrix instantiateMatrix(final FloatMatrixData fmd, final SpeedUnit displayUnit)
    {
        return new FloatSpeedMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSpeedVector instantiateVector(final FloatVectorData fvd, final SpeedUnit displayUnit)
    {
        return new FloatSpeedVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSpeed instantiateScalarSI(final float valueSI, final SpeedUnit displayUnit)
    {
        FloatSpeed result = FloatSpeed.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
