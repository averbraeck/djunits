package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.VolumeUnit;
import org.djunits.value.vfloat.scalar.FloatVolume;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatVolumeVector, a vector of values with a VolumeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatVolumeVector extends AbstractFloatVectorRel<VolumeUnit, FloatVolume, FloatVolumeVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatVolumeVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit VolumeUnit; the unit
     */
    public FloatVolumeVector(final FloatVectorData data, final VolumeUnit unit)
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
