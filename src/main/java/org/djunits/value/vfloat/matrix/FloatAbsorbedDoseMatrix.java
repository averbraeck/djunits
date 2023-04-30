package org.djunits.value.vfloat.matrix;

import org.djunits.unit.AbsorbedDoseUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAbsorbedDose;
import org.djunits.value.vfloat.vector.FloatAbsorbedDoseVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatAbsorbedDoseMatrix, a matrix of values with a AbsorbedDoseUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-04-30T13:59:27.633664900Z")
public class FloatAbsorbedDoseMatrix
        extends AbstractFloatMatrixRel<AbsorbedDoseUnit, FloatAbsorbedDose, FloatAbsorbedDoseVector, FloatAbsorbedDoseMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit AbsorbedDoseUnit; the unit
     */
    public FloatAbsorbedDoseMatrix(final FloatMatrixData data, final AbsorbedDoseUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAbsorbedDose> getScalarClass()
    {
        return FloatAbsorbedDose.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAbsorbedDoseVector> getVectorClass()
    {
        return FloatAbsorbedDoseVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsorbedDoseMatrix instantiateMatrix(final FloatMatrixData fmd, final AbsorbedDoseUnit displayUnit)
    {
        return new FloatAbsorbedDoseMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsorbedDoseVector instantiateVector(final FloatVectorData fvd, final AbsorbedDoseUnit displayUnit)
    {
        return new FloatAbsorbedDoseVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsorbedDose instantiateScalarSI(final float valueSI, final AbsorbedDoseUnit displayUnit)
    {
        FloatAbsorbedDose result = FloatAbsorbedDose.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
