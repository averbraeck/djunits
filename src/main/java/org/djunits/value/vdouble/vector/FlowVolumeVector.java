package org.djunits.value.vdouble.vector;

import org.djunits.unit.FlowVolumeUnit;
import org.djunits.value.vdouble.scalar.FlowVolume;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double FlowVolumeVector, a vector of values with a FlowVolumeUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-01-21T20:18:25.227867Z")
public class FlowVolumeVector extends AbstractDoubleVectorRel<FlowVolumeUnit, FlowVolume, FlowVolumeVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FlowVolumeVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit FlowVolumeUnit; the display unit of the vector data
     */
    public FlowVolumeVector(final DoubleVectorData data, final FlowVolumeUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FlowVolume> getScalarClass()
    {
        return FlowVolume.class;
    }

    /** {@inheritDoc} */
    @Override
    public FlowVolumeVector instantiateVector(final DoubleVectorData dvd, final FlowVolumeUnit displayUnit)
    {
        return new FlowVolumeVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FlowVolume instantiateScalarSI(final double valueSI, final FlowVolumeUnit displayUnit)
    {
        FlowVolume result = FlowVolume.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
