package org.djunits.value.vfloat.vector;

import jakarta.annotation.Generated;

import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.value.vfloat.scalar.FloatElectricalResistance;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatElectricalResistanceVector, a vector of values with a ElectricalResistanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalResistanceVector
        extends AbstractFloatVectorRel<ElectricalResistanceUnit, FloatElectricalResistance, FloatElectricalResistanceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatElectricalResistanceVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit ElectricalResistanceUnit; the unit
     */
    public FloatElectricalResistanceVector(final FloatVectorData data, final ElectricalResistanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalResistance> getScalarClass()
    {
        return FloatElectricalResistance.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalResistanceVector instantiateVector(final FloatVectorData fvd,
            final ElectricalResistanceUnit displayUnit)
    {
        return new FloatElectricalResistanceVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalResistance instantiateScalarSI(final float valueSI, final ElectricalResistanceUnit displayUnit)
    {
        FloatElectricalResistance result = FloatElectricalResistance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
