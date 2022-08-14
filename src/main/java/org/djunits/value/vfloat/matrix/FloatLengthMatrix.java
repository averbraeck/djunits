package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRelWithAbs;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatLength;
import org.djunits.value.vfloat.scalar.FloatPosition;
import org.djunits.value.vfloat.vector.FloatLengthVector;
import org.djunits.value.vfloat.vector.FloatPositionVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatLength Matrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatLengthMatrix extends AbstractFloatMatrixRelWithAbs<PositionUnit, FloatPosition, FloatPositionVector,
        FloatPositionMatrix, LengthUnit, FloatLength, FloatLengthVector, FloatLengthMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit LengthUnit; the unit
     */
    public FloatLengthMatrix(final FloatMatrixData data, final LengthUnit unit)
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
    public Class<FloatLengthVector> getVectorClass()
    {
        return FloatLengthVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatLengthMatrix instantiateMatrix(final FloatMatrixData fmd, final LengthUnit displayUnit)
    {
        return new FloatLengthMatrix(fmd, displayUnit);
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
    public FloatPositionMatrix instantiateMatrixAbs(final FloatMatrixData fmd, final PositionUnit displayUnit)
    {
        return new FloatPositionMatrix(fmd, displayUnit);
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
