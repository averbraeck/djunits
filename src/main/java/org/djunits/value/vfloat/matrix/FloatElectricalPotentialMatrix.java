package org.djunits.value.vfloat.matrix;

import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatElectricalPotential;
import org.djunits.value.vfloat.vector.FloatElectricalPotentialVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatElectricalPotentialMatrix, a matrix of values with a ElectricalPotentialUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-04-30T13:59:27.633664900Z")
public class FloatElectricalPotentialMatrix extends FloatMatrixRel<ElectricalPotentialUnit, FloatElectricalPotential,
        FloatElectricalPotentialVector, FloatElectricalPotentialMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit ElectricalPotentialUnit; the unit
     */
    public FloatElectricalPotentialMatrix(final FloatMatrixData data, final ElectricalPotentialUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalPotential> getScalarClass()
    {
        return FloatElectricalPotential.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalPotentialVector> getVectorClass()
    {
        return FloatElectricalPotentialVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalPotentialMatrix instantiateMatrix(final FloatMatrixData fmd,
            final ElectricalPotentialUnit displayUnit)
    {
        return new FloatElectricalPotentialMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalPotentialVector instantiateVector(final FloatVectorData fvd,
            final ElectricalPotentialUnit displayUnit)
    {
        return new FloatElectricalPotentialVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalPotential instantiateScalarSI(final float valueSI, final ElectricalPotentialUnit displayUnit)
    {
        FloatElectricalPotential result = FloatElectricalPotential.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
