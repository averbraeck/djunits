package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.ElectricalPotential;
import org.djunits.value.vdouble.vector.ElectricalPotentialVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double ElectricalPotentialMatrix, a matrix of values with a ElectricalPotentialUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalPotentialMatrix extends AbstractDoubleMatrixRel<ElectricalPotentialUnit, ElectricalPotential,
        ElectricalPotentialVector, ElectricalPotentialMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit ElectricalPotentialUnit; the unit
     */
    public ElectricalPotentialMatrix(final DoubleMatrixData data, final ElectricalPotentialUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalPotential> getScalarClass()
    {
        return ElectricalPotential.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalPotentialVector> getVectorClass()
    {
        return ElectricalPotentialVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalPotentialMatrix instantiateMatrix(final DoubleMatrixData dmd, final ElectricalPotentialUnit displayUnit)
    {
        return new ElectricalPotentialMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalPotentialVector instantiateVector(final DoubleVectorData dvd, final ElectricalPotentialUnit displayUnit)
    {
        return new ElectricalPotentialVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalPotential instantiateScalarSI(final double valueSI, final ElectricalPotentialUnit displayUnit)
    {
        ElectricalPotential result = ElectricalPotential.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
