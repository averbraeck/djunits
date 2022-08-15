package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ForceUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatForce;
import org.djunits.value.vfloat.vector.FloatForceVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatForceMatrix, a matrix of values with a ForceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatForceMatrix extends AbstractFloatMatrixRel<ForceUnit, FloatForce, FloatForceVector, FloatForceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit ForceUnit; the unit
     */
    public FloatForceMatrix(final FloatMatrixData data, final ForceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatForce> getScalarClass()
    {
        return FloatForce.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatForceVector> getVectorClass()
    {
        return FloatForceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatForceMatrix instantiateMatrix(final FloatMatrixData fmd, final ForceUnit displayUnit)
    {
        return new FloatForceMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatForceVector instantiateVector(final FloatVectorData fvd, final ForceUnit displayUnit)
    {
        return new FloatForceVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatForce instantiateScalarSI(final float valueSI, final ForceUnit displayUnit)
    {
        FloatForce result = FloatForce.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
