package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.ElectricalConductance;
import org.djunits.value.vdouble.vector.ElectricalConductanceVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double ElectricalConductanceMatrix, a matrix of values with a ElectricalConductanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalConductanceMatrix extends AbstractDoubleMatrixRel<ElectricalConductanceUnit, ElectricalConductance,
        ElectricalConductanceVector, ElectricalConductanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit ElectricalConductanceUnit; the unit
     */
    public ElectricalConductanceMatrix(final DoubleMatrixData data, final ElectricalConductanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalConductance> getScalarClass()
    {
        return ElectricalConductance.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalConductanceVector> getVectorClass()
    {
        return ElectricalConductanceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalConductanceMatrix instantiateMatrix(final DoubleMatrixData dmd,
            final ElectricalConductanceUnit displayUnit)
    {
        return new ElectricalConductanceMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalConductanceVector instantiateVector(final DoubleVectorData dvd,
            final ElectricalConductanceUnit displayUnit)
    {
        return new ElectricalConductanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalConductance instantiateScalarSI(final double valueSI, final ElectricalConductanceUnit displayUnit)
    {
        ElectricalConductance result = ElectricalConductance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
