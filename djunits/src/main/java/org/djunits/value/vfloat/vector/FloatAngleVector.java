package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.value.vfloat.scalar.FloatAngle;
import org.djunits.value.vfloat.scalar.FloatDirection;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRelWithAbs;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Relative FloatAngle Vector.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAngleVector extends AbstractFloatVectorRelWithAbs<DirectionUnit, FloatDirection, FloatDirectionVector,
        AngleUnit, FloatAngle, FloatAngleVector>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a new Relative Immutable FloatAngleVector.
     * @param data FloatVectorData; an internal data object
     * @param unit AngleUnit; the unit
     */
    public FloatAngleVector(final FloatVectorData data, final AngleUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAngle> getScalarClass()
    {
        return FloatAngle.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngleVector instantiateVector(final FloatVectorData fvd, final AngleUnit displayUnit)
    {
        return new FloatAngleVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngle instantiateScalarSI(final float valueSI, final AngleUnit displayUnit)
    {
        FloatAngle result = FloatAngle.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public FloatDirectionVector instantiateVectorAbs(final FloatVectorData fvd, final DirectionUnit displayUnit)
    {
        return new FloatDirectionVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatDirection instantiateScalarAbsSI(final float valueSI, final DirectionUnit displayUnit)
    {
        FloatDirection result = FloatDirection.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
