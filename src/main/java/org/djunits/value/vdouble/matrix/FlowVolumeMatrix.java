package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.FlowVolumeUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.FlowVolume;
import org.djunits.value.vdouble.vector.FlowVolumeVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double FlowVolumeMatrix, a matrix of values with a FlowVolumeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FlowVolumeMatrix extends AbstractDoubleMatrixRel<FlowVolumeUnit, FlowVolume, FlowVolumeVector, FlowVolumeMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit FlowVolumeUnit; the unit
     */
    public FlowVolumeMatrix(final DoubleMatrixData data, final FlowVolumeUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FlowVolume> getScalarClass()
    {
        return FlowVolume.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FlowVolumeVector> getVectorClass()
    {
        return FlowVolumeVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FlowVolumeMatrix instantiateMatrix(final DoubleMatrixData dmd, final FlowVolumeUnit displayUnit)
    {
        return new FlowVolumeMatrix(dmd, displayUnit);
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
