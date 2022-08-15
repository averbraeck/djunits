package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatElectricalCurrent;
import org.djunits.value.vfloat.vector.FloatElectricalCurrentVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatElectricalCurrentMatrix, a matrix of values with a ElectricalCurrentUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalCurrentMatrix extends AbstractFloatMatrixRel<ElectricalCurrentUnit, FloatElectricalCurrent,
        FloatElectricalCurrentVector, FloatElectricalCurrentMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit ElectricalCurrentUnit; the unit
     */
    public FloatElectricalCurrentMatrix(final FloatMatrixData data, final ElectricalCurrentUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalCurrent> getScalarClass()
    {
        return FloatElectricalCurrent.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalCurrentVector> getVectorClass()
    {
        return FloatElectricalCurrentVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalCurrentMatrix instantiateMatrix(final FloatMatrixData fmd, final ElectricalCurrentUnit displayUnit)
    {
        return new FloatElectricalCurrentMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalCurrentVector instantiateVector(final FloatVectorData fvd, final ElectricalCurrentUnit displayUnit)
    {
        return new FloatElectricalCurrentVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalCurrent instantiateScalarSI(final float valueSI, final ElectricalCurrentUnit displayUnit)
    {
        FloatElectricalCurrent result = FloatElectricalCurrent.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
