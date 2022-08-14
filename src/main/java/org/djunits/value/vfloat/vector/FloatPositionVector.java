package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.value.vfloat.scalar.FloatLength;
import org.djunits.value.vfloat.scalar.FloatPosition;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorAbs;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Absolute FloatPosition Vector.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatPositionVector extends
        AbstractFloatVectorAbs<PositionUnit, FloatPosition, FloatPositionVector, LengthUnit, FloatLength, FloatLengthVector>
{
    /** */
    private static final long serialVersionUID = 20151003L;

    /**
     * Construct a new Absative Immutable FloatPositionVector.
     * @param data FloatVectorData; an internal data object
     * @param unit PositionUnit; the unit
     */
    public FloatPositionVector(final FloatVectorData data, final PositionUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatPosition>

            getScalarClass()
    {
        return FloatPosition.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatPositionVector instantiateVector(final FloatVectorData fvd, final PositionUnit displayUnit)
    {
        return new FloatPositionVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatPosition instantiateScalarSI(final float valueSI, final PositionUnit displayUnit)
    {
        FloatPosition result = FloatPosition.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public FloatLengthVector instantiateVectorRel(final FloatVectorData fvd, final LengthUnit displayUnit)
    {
        return new FloatLengthVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatLength instantiateScalarRelSI(final float valueSI, final LengthUnit displayUnit)
    {
        FloatLength result = FloatLength.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
