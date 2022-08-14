package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.SolidAngleUnit;
import org.djunits.value.vfloat.scalar.FloatSolidAngle;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatSolidAngleVector, a vector of values with a SolidAngleUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatSolidAngleVector extends AbstractFloatVectorRel<SolidAngleUnit, FloatSolidAngle, FloatSolidAngleVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatSolidAngleVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit SolidAngleUnit; the unit
     */
    public FloatSolidAngleVector(final FloatVectorData data, final SolidAngleUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatSolidAngle> getScalarClass()
    {
        return FloatSolidAngle.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatSolidAngleVector instantiateVector(final FloatVectorData fvd, final SolidAngleUnit displayUnit)
    {
        return new FloatSolidAngleVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSolidAngle instantiateScalarSI(final float valueSI, final SolidAngleUnit displayUnit)
    {
        FloatSolidAngle result = FloatSolidAngle.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
