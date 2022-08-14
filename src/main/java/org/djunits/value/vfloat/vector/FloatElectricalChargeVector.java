package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.value.vfloat.scalar.FloatElectricalCharge;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatElectricalChargeVector, a vector of values with a ElectricalChargeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalChargeVector
        extends AbstractFloatVectorRel<ElectricalChargeUnit, FloatElectricalCharge, FloatElectricalChargeVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatElectricalChargeVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit ElectricalChargeUnit; the unit
     */
    public FloatElectricalChargeVector(final FloatVectorData data, final ElectricalChargeUnit unit)
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
