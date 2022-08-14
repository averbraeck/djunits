package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.VolumeUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatVolume;
import org.djunits.value.vfloat.vector.FloatVolumeVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatVolumeMatrix, a matrix of values with a VolumeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatVolumeMatrix extends AbstractFloatMatrixRel<VolumeUnit, FloatVolume, FloatVolumeVector, FloatVolumeMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit VolumeUnit; the unit
     */
    public FloatVolumeMatrix(final FloatMatrixData data, final VolumeUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatVolume> getScalarClass()
    {
        return FloatVolume.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatVolumeVector> getVectorClass()
    {
        return FloatVolumeVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatVolumeMatrix instantiateMatrix(final FloatMatrixData fmd, final VolumeUnit displayUnit)
    {
        return new FloatVolumeMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatVolumeVector instantiateVector(final FloatVectorData fvd, final VolumeUnit displayUnit)
    {
        return new FloatVolumeVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatVolume instantiateScalarSI(final float valueSI, final VolumeUnit displayUnit)
    {
        FloatVolume result = FloatVolume.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
