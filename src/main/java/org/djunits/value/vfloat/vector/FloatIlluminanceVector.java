package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.IlluminanceUnit;
import org.djunits.value.vfloat.scalar.FloatIlluminance;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatIlluminanceVector, a vector of values with a IlluminanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatIlluminanceVector extends AbstractFloatVectorRel<IlluminanceUnit, FloatIlluminance, FloatIlluminanceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatIlluminanceVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit IlluminanceUnit; the unit
     */
    public FloatIlluminanceVector(final FloatVectorData data, final IlluminanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatIlluminance> getScalarClass()
    {
        return FloatIlluminance.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatIlluminanceVector instantiateVector(final FloatVectorData fvd, final IlluminanceUnit displayUnit)
    {
        return new FloatIlluminanceVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatIlluminance instantiateScalarSI(final float valueSI, final IlluminanceUnit displayUnit)
    {
        FloatIlluminance result = FloatIlluminance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
