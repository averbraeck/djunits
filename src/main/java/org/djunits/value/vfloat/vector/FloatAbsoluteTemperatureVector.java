package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatTemperature;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorAbs;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Absolute FloatAbsoluteTemperature Vector.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAbsoluteTemperatureVector extends AbstractFloatVectorAbs<AbsoluteTemperatureUnit, FloatAbsoluteTemperature,
        FloatAbsoluteTemperatureVector, TemperatureUnit, FloatTemperature, FloatTemperatureVector>
{
    /** */
    private static final long serialVersionUID = 20151003L;

    /**
     * Construct a new Absative Immutable FloatAbsoluteTemperatureVector.
     * @param data FloatVectorData; an internal data object
     * @param unit AbsoluteTemperatureUnit; the unit
     */
    public FloatAbsoluteTemperatureVector(final FloatVectorData data, final AbsoluteTemperatureUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAbsoluteTemperature>

            getScalarClass()
    {
        return FloatAbsoluteTemperature.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsoluteTemperatureVector instantiateVector(final FloatVectorData fvd,
            final AbsoluteTemperatureUnit displayUnit)
    {
        return new FloatAbsoluteTemperatureVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsoluteTemperature instantiateScalarSI(final float valueSI, final AbsoluteTemperatureUnit displayUnit)
    {
        FloatAbsoluteTemperature result = FloatAbsoluteTemperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public FloatTemperatureVector instantiateVectorRel(final FloatVectorData fvd, final TemperatureUnit displayUnit)
    {
        return new FloatTemperatureVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatTemperature instantiateScalarRelSI(final float valueSI, final TemperatureUnit displayUnit)
    {
        FloatTemperature result = FloatTemperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
