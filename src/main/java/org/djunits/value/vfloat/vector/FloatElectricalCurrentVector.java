package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.value.vfloat.scalar.FloatElectricalCurrent;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatElectricalCurrentVector, a vector of values with a ElectricalCurrentUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalCurrentVector
        extends AbstractFloatVectorRel<ElectricalCurrentUnit, FloatElectricalCurrent, FloatElectricalCurrentVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatElectricalCurrentVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit ElectricalCurrentUnit; the unit
     */
    public FloatElectricalCurrentVector(final FloatVectorData data, final ElectricalCurrentUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalCurrent> getScalarClass()
    {
        return FloatElectricalCurrent.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalCurrentVector instantiateVector(final FloatVectorData fvd, final ElectricalCurrentUnit displayUnit)
    {
        return new FloatElectricalCurrentVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalCurrent instantiateScalarSI(final float valueSI, final ElectricalCurrentUnit displayUnit)
    {
        FloatElectricalCurrent result = FloatElectricalCurrent.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
