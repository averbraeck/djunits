package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.MomentumUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatMomentum;
import org.djunits.value.vfloat.vector.FloatMomentumVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatMomentumMatrix, a matrix of values with a MomentumUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatMomentumMatrix
        extends AbstractFloatMatrixRel<MomentumUnit, FloatMomentum, FloatMomentumVector, FloatMomentumMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit MomentumUnit; the unit
     */
    public FloatMomentumMatrix(final FloatMatrixData data, final MomentumUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMomentum> getScalarClass()
    {
        return FloatMomentum.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMomentumVector> getVectorClass()
    {
        return FloatMomentumVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatMomentumMatrix instantiateMatrix(final FloatMatrixData fmd, final MomentumUnit displayUnit)
    {
        return new FloatMomentumMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMomentumVector instantiateVector(final FloatVectorData fvd, final MomentumUnit displayUnit)
    {
        return new FloatMomentumVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMomentum instantiateScalarSI(final float valueSI, final MomentumUnit displayUnit)
    {
        FloatMomentum result = FloatMomentum.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
