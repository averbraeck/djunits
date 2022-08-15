package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.VolumeUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Volume;
import org.djunits.value.vdouble.vector.VolumeVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double VolumeMatrix, a matrix of values with a VolumeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class VolumeMatrix extends AbstractDoubleMatrixRel<VolumeUnit, Volume, VolumeVector, VolumeMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit VolumeUnit; the unit
     */
    public VolumeMatrix(final DoubleMatrixData data, final VolumeUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Volume> getScalarClass()
    {
        return Volume.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<VolumeVector> getVectorClass()
    {
        return VolumeVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public VolumeMatrix instantiateMatrix(final DoubleMatrixData dmd, final VolumeUnit displayUnit)
    {
        return new VolumeMatrix(dmd, displayUnit);
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
