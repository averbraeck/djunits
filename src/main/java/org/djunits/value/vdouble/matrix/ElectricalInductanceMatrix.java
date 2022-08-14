package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.ElectricalInductance;
import org.djunits.value.vdouble.vector.ElectricalInductanceVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double ElectricalInductanceMatrix, a matrix of values with a ElectricalInductanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalInductanceMatrix extends AbstractDoubleMatrixRel<ElectricalInductanceUnit, ElectricalInductance,
        ElectricalInductanceVector, ElectricalInductanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit ElectricalInductanceUnit; the unit
     */
    public ElectricalInductanceMatrix(final DoubleMatrixData data, final ElectricalInductanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalInductance> getScalarClass()
    {
        return ElectricalInductance.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalInductanceVector> getVectorClass()
    {
        return ElectricalInductanceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalInductanceMatrix instantiateMatrix(final DoubleMatrixData dmd, final ElectricalInductanceUnit displayUnit)
    {
        return new ElectricalInductanceMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalInductanceVector instantiateVector(final DoubleVectorData dvd, final ElectricalInductanceUnit displayUnit)
    {
        return new ElectricalInductanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalInductance instantiateScalarSI(final double valueSI, final ElectricalInductanceUnit displayUnit)
    {
        ElectricalInductance result = ElectricalInductance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
