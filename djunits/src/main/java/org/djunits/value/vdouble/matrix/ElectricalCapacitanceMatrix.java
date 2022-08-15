package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.ElectricalCapacitance;
import org.djunits.value.vdouble.vector.ElectricalCapacitanceVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double ElectricalCapacitanceMatrix, a matrix of values with a ElectricalCapacitanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalCapacitanceMatrix extends AbstractDoubleMatrixRel<ElectricalCapacitanceUnit, ElectricalCapacitance,
        ElectricalCapacitanceVector, ElectricalCapacitanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit ElectricalCapacitanceUnit; the unit
     */
    public ElectricalCapacitanceMatrix(final DoubleMatrixData data, final ElectricalCapacitanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalCapacitance> getScalarClass()
    {
        return ElectricalCapacitance.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalCapacitanceVector> getVectorClass()
    {
        return ElectricalCapacitanceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalCapacitanceMatrix instantiateMatrix(final DoubleMatrixData dmd,
            final ElectricalCapacitanceUnit displayUnit)
    {
        return new ElectricalCapacitanceMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalCapacitanceVector instantiateVector(final DoubleVectorData dvd,
            final ElectricalCapacitanceUnit displayUnit)
    {
        return new ElectricalCapacitanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalCapacitance instantiateScalarSI(final double valueSI, final ElectricalCapacitanceUnit displayUnit)
    {
        ElectricalCapacitance result = ElectricalCapacitance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
