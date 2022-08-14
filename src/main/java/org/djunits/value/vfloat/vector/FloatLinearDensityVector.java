package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.LinearDensityUnit;
import org.djunits.value.vfloat.scalar.FloatLinearDensity;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatLinearDensityVector, a vector of values with a LinearDensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatLinearDensityVector
        extends AbstractFloatVectorRel<LinearDensityUnit, FloatLinearDensity, FloatLinearDensityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatLinearDensityVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit LinearDensityUnit; the unit
     */
    public FloatLinearDensityVector(final FloatVectorData data, final LinearDensityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatLinearDensity> getScalarClass()
    {
        return FloatLinearDensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatLinearDensityVector instantiateVector(final FloatVectorData fvd, final LinearDensityUnit displayUnit)
    {
        return new FloatLinearDensityVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatLinearDensity instantiateScalarSI(final float valueSI, final LinearDensityUnit displayUnit)
    {
        FloatLinearDensity result = FloatLinearDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
