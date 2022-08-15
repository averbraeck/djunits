package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.AngularVelocityUnit;
import org.djunits.value.vfloat.scalar.FloatAngularVelocity;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatAngularVelocityVector, a vector of values with a AngularVelocityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAngularVelocityVector
        extends AbstractFloatVectorRel<AngularVelocityUnit, FloatAngularVelocity, FloatAngularVelocityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatAngularVelocityVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit AngularVelocityUnit; the unit
     */
    public FloatAngularVelocityVector(final FloatVectorData data, final AngularVelocityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAngularVelocity> getScalarClass()
    {
        return FloatAngularVelocity.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngularVelocityVector instantiateVector(final FloatVectorData fvd, final AngularVelocityUnit displayUnit)
    {
        return new FloatAngularVelocityVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngularVelocity instantiateScalarSI(final float valueSI, final AngularVelocityUnit displayUnit)
    {
        FloatAngularVelocity result = FloatAngularVelocity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
