package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.ElectricalCharge;
import org.djunits.value.vdouble.vector.ElectricalChargeVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double ElectricalChargeMatrix, a matrix of values with a ElectricalChargeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalChargeMatrix
        extends AbstractDoubleMatrixRel<ElectricalChargeUnit, ElectricalCharge, ElectricalChargeVector, ElectricalChargeMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit ElectricalChargeUnit; the unit
     */
    public ElectricalChargeMatrix(final DoubleMatrixData data, final ElectricalChargeUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalCharge> getScalarClass()
    {
        return ElectricalCharge.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalChargeVector> getVectorClass()
    {
        return ElectricalChargeVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalChargeMatrix instantiateMatrix(final DoubleMatrixData dmd, final ElectricalChargeUnit displayUnit)
    {
        return new ElectricalChargeMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalChargeVector instantiateVector(final DoubleVectorData dvd, final ElectricalChargeUnit displayUnit)
    {
        return new ElectricalChargeVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalCharge instantiateScalarSI(final double valueSI, final ElectricalChargeUnit displayUnit)
    {
        ElectricalCharge result = ElectricalCharge.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
