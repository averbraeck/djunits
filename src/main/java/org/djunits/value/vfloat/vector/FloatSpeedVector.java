package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.SpeedUnit;
import org.djunits.value.vfloat.scalar.FloatSpeed;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatSpeedVector, a vector of values with a SpeedUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatSpeedVector extends AbstractFloatVectorRel<SpeedUnit, FloatSpeed, FloatSpeedVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatSpeedVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit SpeedUnit; the unit
     */
    public FloatSpeedVector(final FloatVectorData data, final SpeedUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatSpeed> getScalarClass()
    {
        return FloatSpeed.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatSpeedVector instantiateVector(final FloatVectorData fvd, final SpeedUnit displayUnit)
    {
        return new FloatSpeedVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSpeed instantiateScalarSI(final float valueSI, final SpeedUnit displayUnit)
    {
        FloatSpeed result = FloatSpeed.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
