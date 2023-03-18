package org.djunits.value.vfloat.vector;

import org.djunits.unit.PressureUnit;
import org.djunits.value.vfloat.scalar.FloatPressure;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Float FloatPressureVector, a vector of values with a PressureUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-01-21T20:18:25.227867Z")
public class FloatPressureVector extends AbstractFloatVectorRel<PressureUnit, FloatPressure, FloatPressureVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatPressureVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit PressureUnit; the unit
     */
    public FloatPressureVector(final FloatVectorData data, final PressureUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatPressure> getScalarClass()
    {
        return FloatPressure.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatPressureVector instantiateVector(final FloatVectorData fvd, final PressureUnit displayUnit)
    {
        return new FloatPressureVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatPressure instantiateScalarSI(final float valueSI, final PressureUnit displayUnit)
    {
        FloatPressure result = FloatPressure.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
