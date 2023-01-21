package org.djunits.value.vfloat.matrix;

import jakarta.annotation.Generated;

import org.djunits.unit.AreaUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatArea;
import org.djunits.value.vfloat.vector.FloatAreaVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatAreaMatrix, a matrix of values with a AreaUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAreaMatrix extends AbstractFloatMatrixRel<AreaUnit, FloatArea, FloatAreaVector, FloatAreaMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit AreaUnit; the unit
     */
    public FloatAreaMatrix(final FloatMatrixData data, final AreaUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatArea> getScalarClass()
    {
        return FloatArea.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAreaVector> getVectorClass()
    {
        return FloatAreaVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAreaMatrix instantiateMatrix(final FloatMatrixData fmd, final AreaUnit displayUnit)
    {
        return new FloatAreaMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAreaVector instantiateVector(final FloatVectorData fvd, final AreaUnit displayUnit)
    {
        return new FloatAreaVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatArea instantiateScalarSI(final float valueSI, final AreaUnit displayUnit)
    {
        FloatArea result = FloatArea.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
