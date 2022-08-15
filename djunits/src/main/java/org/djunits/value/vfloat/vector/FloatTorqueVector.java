package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.TorqueUnit;
import org.djunits.value.vfloat.scalar.FloatTorque;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatTorqueVector, a vector of values with a TorqueUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatTorqueVector extends AbstractFloatVectorRel<TorqueUnit, FloatTorque, FloatTorqueVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatTorqueVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit TorqueUnit; the unit
     */
    public FloatTorqueVector(final FloatVectorData data, final TorqueUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatTorque> getScalarClass()
    {
        return FloatTorque.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatTorqueVector instantiateVector(final FloatVectorData fvd, final TorqueUnit displayUnit)
    {
        return new FloatTorqueVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatTorque instantiateScalarSI(final float valueSI, final TorqueUnit displayUnit)
    {
        FloatTorque result = FloatTorque.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
