package org.djunits.value.vfloat.matrix;

import org.djunits.unit.FlowMassUnit;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatFlowMass;
import org.djunits.value.vfloat.vector.FloatFlowMassVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatFlowMassMatrix, a matrix of values with a FlowMassUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-04-30T13:59:27.633664900Z")
public class FloatFlowMassMatrix
        extends FloatMatrixRel<FlowMassUnit, FloatFlowMass, FloatFlowMassVector, FloatFlowMassMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit FlowMassUnit; the unit
     */
    public FloatFlowMassMatrix(final FloatMatrixData data, final FlowMassUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatFlowMass> getScalarClass()
    {
        return FloatFlowMass.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatFlowMassVector> getVectorClass()
    {
        return FloatFlowMassVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatFlowMassMatrix instantiateMatrix(final FloatMatrixData fmd, final FlowMassUnit displayUnit)
    {
        return new FloatFlowMassMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatFlowMassVector instantiateVector(final FloatVectorData fvd, final FlowMassUnit displayUnit)
    {
        return new FloatFlowMassVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatFlowMass instantiateScalarSI(final float valueSI, final FlowMassUnit displayUnit)
    {
        FloatFlowMass result = FloatFlowMass.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
