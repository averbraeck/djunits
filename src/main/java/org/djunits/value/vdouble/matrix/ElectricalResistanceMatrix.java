package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.ElectricalResistance;
import org.djunits.value.vdouble.vector.ElectricalResistanceVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double ElectricalResistanceMatrix, a matrix of values with a ElectricalResistanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalResistanceMatrix extends AbstractDoubleMatrixRel<ElectricalResistanceUnit, ElectricalResistance,
        ElectricalResistanceVector, ElectricalResistanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit ElectricalResistanceUnit; the unit
     */
    public ElectricalResistanceMatrix(final DoubleMatrixData data, final ElectricalResistanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalResistance> getScalarClass()
    {
        return ElectricalResistance.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalResistanceVector> getVectorClass()
    {
        return ElectricalResistanceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalResistanceMatrix instantiateMatrix(final DoubleMatrixData dmd, final ElectricalResistanceUnit displayUnit)
    {
        return new ElectricalResistanceMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalResistanceVector instantiateVector(final DoubleVectorData dvd, final ElectricalResistanceUnit displayUnit)
    {
        return new ElectricalResistanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalResistance instantiateScalarSI(final double valueSI, final ElectricalResistanceUnit displayUnit)
    {
        ElectricalResistance result = ElectricalResistance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
