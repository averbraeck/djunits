package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.value.vfloat.scalar.FloatAngle;
import org.djunits.value.vfloat.scalar.FloatDirection;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorAbs;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Absolute FloatDirection Vector.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatDirectionVector extends
        AbstractFloatVectorAbs<DirectionUnit, FloatDirection, FloatDirectionVector, AngleUnit, FloatAngle, FloatAngleVector>
{
    /** */
    private static final long serialVersionUID = 20151003L;

    /**
     * Construct a new Absative Immutable FloatDirectionVector.
     * @param data FloatVectorData; an internal data object
     * @param unit DirectionUnit; the unit
     */
    public FloatDirectionVector(final FloatVectorData data, final DirectionUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatDirection>

            getScalarClass()
    {
        return FloatDirection.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatDirectionVector instantiateVector(final FloatVectorData fvd, final DirectionUnit displayUnit)
    {
        return new FloatDirectionVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDirection instantiateScalarSI(final float valueSI, final DirectionUnit displayUnit)
    {
        FloatDirection result = FloatDirection.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngleVector instantiateVectorRel(final FloatVectorData fvd, final AngleUnit displayUnit)
    {
        return new FloatAngleVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngle instantiateScalarRelSI(final float valueSI, final AngleUnit displayUnit)
    {
        FloatAngle result = FloatAngle.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
