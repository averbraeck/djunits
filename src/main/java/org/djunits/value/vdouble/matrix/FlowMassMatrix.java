package org.djunits.value.vdouble.matrix;

import org.djunits.unit.FlowMassUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.FlowMass;
import org.djunits.value.vdouble.vector.FlowMassVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double FlowMassMatrix, a matrix of values with a FlowMassUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-04-30T13:59:27.633664900Z")
public class FlowMassMatrix extends AbstractDoubleMatrixRel<FlowMassUnit, FlowMass, FlowMassVector, FlowMassMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit FlowMassUnit; the unit
     */
    public FlowMassMatrix(final DoubleMatrixData data, final FlowMassUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FlowMass> getScalarClass()
    {
        return FlowMass.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FlowMassVector> getVectorClass()
    {
        return FlowMassVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FlowMassMatrix instantiateMatrix(final DoubleMatrixData dmd, final FlowMassUnit displayUnit)
    {
        return new FlowMassMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FlowMassVector instantiateVector(final DoubleVectorData dvd, final FlowMassUnit displayUnit)
    {
        return new FlowMassVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FlowMass instantiateScalarSI(final double valueSI, final FlowMassUnit displayUnit)
    {
        FlowMass result = FlowMass.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
