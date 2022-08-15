package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.value.vfloat.scalar.FloatAngularAcceleration;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatAngularAccelerationVector, a vector of values with a AngularAccelerationUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAngularAccelerationVector
        extends AbstractFloatVectorRel<AngularAccelerationUnit, FloatAngularAcceleration, FloatAngularAccelerationVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatAngularAccelerationVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit AngularAccelerationUnit; the unit
     */
    public FloatAngularAccelerationVector(final FloatVectorData data, final AngularAccelerationUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAngularAcceleration> getScalarClass()
    {
        return FloatAngularAcceleration.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngularAccelerationVector instantiateVector(final FloatVectorData fvd,
            final AngularAccelerationUnit displayUnit)
    {
        return new FloatAngularAccelerationVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngularAcceleration instantiateScalarSI(final float valueSI, final AngularAccelerationUnit displayUnit)
    {
        FloatAngularAcceleration result = FloatAngularAcceleration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
