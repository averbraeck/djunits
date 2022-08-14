package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.DensityUnit;
import org.djunits.value.vfloat.scalar.FloatDensity;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatDensityVector, a vector of values with a DensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatDensityVector extends AbstractFloatVectorRel<DensityUnit, FloatDensity, FloatDensityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatDensityVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit DensityUnit; the unit
     */
    public FloatDensityVector(final FloatVectorData data, final DensityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatDensity> getScalarClass()
    {
        return FloatDensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatDensityVector instantiateVector(final FloatVectorData fvd, final DensityUnit displayUnit)
    {
        return new FloatDensityVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDensity instantiateScalarSI(final float valueSI, final DensityUnit displayUnit)
    {
        FloatDensity result = FloatDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
