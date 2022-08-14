package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.PressureUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatPressure;
import org.djunits.value.vfloat.vector.FloatPressureVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatPressureMatrix, a matrix of values with a PressureUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatPressureMatrix
        extends AbstractFloatMatrixRel<PressureUnit, FloatPressure, FloatPressureVector, FloatPressureMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit PressureUnit; the unit
     */
    public FloatPressureMatrix(final FloatMatrixData data, final PressureUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatPressure> getScalarClass()
    {
        return FloatPressure.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatPressureVector> getVectorClass()
    {
        return FloatPressureVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatPressureMatrix instantiateMatrix(final FloatMatrixData fmd, final PressureUnit displayUnit)
    {
        return new FloatPressureMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatPressureVector instantiateVector(final FloatVectorData fvd, final PressureUnit displayUnit)
    {
        return new FloatPressureVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatPressure instantiateScalarSI(final float valueSI, final PressureUnit displayUnit)
    {
        FloatPressure result = FloatPressure.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
