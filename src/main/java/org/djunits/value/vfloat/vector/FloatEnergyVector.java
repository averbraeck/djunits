package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.EnergyUnit;
import org.djunits.value.vfloat.scalar.FloatEnergy;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatEnergyVector, a vector of values with a EnergyUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatEnergyVector extends AbstractFloatVectorRel<EnergyUnit, FloatEnergy, FloatEnergyVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatEnergyVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit EnergyUnit; the unit
     */
    public FloatEnergyVector(final FloatVectorData data, final EnergyUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatEnergy> getScalarClass()
    {
        return FloatEnergy.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatEnergyVector instantiateVector(final FloatVectorData fvd, final EnergyUnit displayUnit)
    {
        return new FloatEnergyVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatEnergy instantiateScalarSI(final float valueSI, final EnergyUnit displayUnit)
    {
        FloatEnergy result = FloatEnergy.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
