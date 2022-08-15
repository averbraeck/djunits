package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.FrequencyUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatFrequency;
import org.djunits.value.vfloat.vector.FloatFrequencyVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatFrequencyMatrix, a matrix of values with a FrequencyUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatFrequencyMatrix
        extends AbstractFloatMatrixRel<FrequencyUnit, FloatFrequency, FloatFrequencyVector, FloatFrequencyMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit FrequencyUnit; the unit
     */
    public FloatFrequencyMatrix(final FloatMatrixData data, final FrequencyUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatFrequency> getScalarClass()
    {
        return FloatFrequency.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatFrequencyVector> getVectorClass()
    {
        return FloatFrequencyVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatFrequencyMatrix instantiateMatrix(final FloatMatrixData fmd, final FrequencyUnit displayUnit)
    {
        return new FloatFrequencyMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatFrequencyVector instantiateVector(final FloatVectorData fvd, final FrequencyUnit displayUnit)
    {
        return new FloatFrequencyVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatFrequency instantiateScalarSI(final float valueSI, final FrequencyUnit displayUnit)
    {
        FloatFrequency result = FloatFrequency.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
