package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.PowerUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatPower;
import org.djunits.value.vfloat.vector.FloatPowerVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatPowerMatrix, a matrix of values with a PowerUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatPowerMatrix extends AbstractFloatMatrixRel<PowerUnit, FloatPower, FloatPowerVector, FloatPowerMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit PowerUnit; the unit
     */
    public FloatPowerMatrix(final FloatMatrixData data, final PowerUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatPower> getScalarClass()
    {
        return FloatPower.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatPowerVector> getVectorClass()
    {
        return FloatPowerVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatPowerMatrix instantiateMatrix(final FloatMatrixData fmd, final PowerUnit displayUnit)
    {
        return new FloatPowerMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatPowerVector instantiateVector(final FloatVectorData fvd, final PowerUnit displayUnit)
    {
        return new FloatPowerVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatPower instantiateScalarSI(final float valueSI, final PowerUnit displayUnit)
    {
        FloatPower result = FloatPower.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
