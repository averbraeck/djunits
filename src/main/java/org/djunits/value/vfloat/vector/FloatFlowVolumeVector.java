package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.FlowVolumeUnit;
import org.djunits.value.vfloat.scalar.FloatFlowVolume;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatFlowVolumeVector, a vector of values with a FlowVolumeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatFlowVolumeVector extends AbstractFloatVectorRel<FlowVolumeUnit, FloatFlowVolume, FloatFlowVolumeVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatFlowVolumeVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit FlowVolumeUnit; the unit
     */
    public FloatFlowVolumeVector(final FloatVectorData data, final FlowVolumeUnit unit)
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
