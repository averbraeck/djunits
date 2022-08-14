package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.VolumeUnit;
import org.djunits.value.vdouble.scalar.Volume;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double VolumeVector, a vector of values with a VolumeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class VolumeVector extends AbstractDoubleVectorRel<VolumeUnit, Volume, VolumeVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an VolumeVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit VolumeUnit; the display unit of the vector data
     */
    public VolumeVector(final DoubleVectorData data, final VolumeUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Volume> getScalarClass()
    {
        return Volume.class;
    }

    /** {@inheritDoc} */
    @Override
    public VolumeVector instantiateVector(final DoubleVectorData dvd, final VolumeUnit displayUnit)
    {
        return new VolumeVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Volume instantiateScalarSI(final double valueSI, final VolumeUnit displayUnit)
    {
        Volume result = Volume.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
