package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.value.vfloat.scalar.FloatLength;
import org.djunits.value.vfloat.scalar.FloatPosition;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRelWithAbs;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Relative FloatLength Vector.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatLengthVector extends AbstractFloatVectorRelWithAbs<PositionUnit, FloatPosition, FloatPositionVector,
        LengthUnit, FloatLength, FloatLengthVector>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a new Relative Immutable FloatLengthVector.
     * @param data FloatVectorData; an internal data object
     * @param unit LengthUnit; the unit
     */
    public FloatLengthVector(final FloatVectorData data, final LengthUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatLength> getScalarClass()
    {
        return FloatLength.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatLengthVector instantiateVector(final FloatVectorData fvd, final LengthUnit displayUnit)
    {
        return new FloatLengthVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatLength instantiateScalarSI(final float valueSI, final LengthUnit displayUnit)
    {
        FloatLength result = FloatLength.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public FloatPositionVector instantiateVectorAbs(final FloatVectorData fvd, final PositionUnit displayUnit)
    {
        return new FloatPositionVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatPosition instantiateScalarAbsSI(final float valueSI, final PositionUnit displayUnit)
    {
        FloatPosition result = FloatPosition.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
