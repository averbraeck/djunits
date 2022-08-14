package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.FlowVolumeUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatFlowVolume;
import org.djunits.value.vfloat.vector.FloatFlowVolumeVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatFlowVolumeMatrix, a matrix of values with a FlowVolumeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatFlowVolumeMatrix
        extends AbstractFloatMatrixRel<FlowVolumeUnit, FloatFlowVolume, FloatFlowVolumeVector, FloatFlowVolumeMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit FlowVolumeUnit; the unit
     */
    public FloatFlowVolumeMatrix(final FloatMatrixData data, final FlowVolumeUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatFlowVolume> getScalarClass()
    {
        return FloatFlowVolume.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatFlowVolumeVector> getVectorClass()
    {
        return FloatFlowVolumeVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatFlowVolumeMatrix instantiateMatrix(final FloatMatrixData fmd, final FlowVolumeUnit displayUnit)
    {
        return new FloatFlowVolumeMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatFlowVolumeVector instantiateVector(final FloatVectorData fvd, final FlowVolumeUnit displayUnit)
    {
        return new FloatFlowVolumeVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatFlowVolume instantiateScalarSI(final float valueSI, final FlowVolumeUnit displayUnit)
    {
        FloatFlowVolume result = FloatFlowVolume.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
