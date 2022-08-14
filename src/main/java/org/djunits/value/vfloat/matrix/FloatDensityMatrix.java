package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.DensityUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatDensity;
import org.djunits.value.vfloat.vector.FloatDensityVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatDensityMatrix, a matrix of values with a DensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatDensityMatrix
        extends AbstractFloatMatrixRel<DensityUnit, FloatDensity, FloatDensityVector, FloatDensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit DensityUnit; the unit
     */
    public FloatDensityMatrix(final FloatMatrixData data, final DensityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatDensity> getScalarClass()
    {
        return FloatDensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatDensityVector> getVectorClass()
    {
        return FloatDensityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatDensityMatrix instantiateMatrix(final FloatMatrixData fmd, final DensityUnit displayUnit)
    {
        return new FloatDensityMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDensityVector instantiateVector(final FloatVectorData fvd, final DensityUnit displayUnit)
    {
        return new FloatDensityVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDensity instantiateScalarSI(final float valueSI, final DensityUnit displayUnit)
    {
        FloatDensity result = FloatDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
