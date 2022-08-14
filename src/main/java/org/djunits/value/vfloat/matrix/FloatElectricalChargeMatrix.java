package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatElectricalCharge;
import org.djunits.value.vfloat.vector.FloatElectricalChargeVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatElectricalChargeMatrix, a matrix of values with a ElectricalChargeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalChargeMatrix extends AbstractFloatMatrixRel<ElectricalChargeUnit, FloatElectricalCharge,
        FloatElectricalChargeVector, FloatElectricalChargeMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit ElectricalChargeUnit; the unit
     */
    public FloatElectricalChargeMatrix(final FloatMatrixData data, final ElectricalChargeUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalCharge> getScalarClass()
    {
        return FloatElectricalCharge.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalChargeVector> getVectorClass()
    {
        return FloatElectricalChargeVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalChargeMatrix instantiateMatrix(final FloatMatrixData fmd, final ElectricalChargeUnit displayUnit)
    {
        return new FloatElectricalChargeMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalChargeVector instantiateVector(final FloatVectorData fvd, final ElectricalChargeUnit displayUnit)
    {
        return new FloatElectricalChargeVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalCharge instantiateScalarSI(final float valueSI, final ElectricalChargeUnit displayUnit)
    {
        FloatElectricalCharge result = FloatElectricalCharge.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
