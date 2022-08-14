package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRelWithAbs;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAngle;
import org.djunits.value.vfloat.scalar.FloatDirection;
import org.djunits.value.vfloat.vector.FloatAngleVector;
import org.djunits.value.vfloat.vector.FloatDirectionVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatAngle Matrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAngleMatrix extends AbstractFloatMatrixRelWithAbs<DirectionUnit, FloatDirection, FloatDirectionVector,
        FloatDirectionMatrix, AngleUnit, FloatAngle, FloatAngleVector, FloatAngleMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit AngleUnit; the unit
     */
    public FloatAngleMatrix(final FloatMatrixData data, final AngleUnit unit)
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
    public Class<FloatAngleVector> getVectorClass()
    {
        return FloatAngleVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAngleMatrix instantiateMatrix(final FloatMatrixData fmd, final AngleUnit displayUnit)
    {
        return new FloatAngleMatrix(fmd, displayUnit);
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
    public FloatDirectionMatrix instantiateMatrixAbs(final FloatMatrixData fmd, final DirectionUnit displayUnit)
    {
        return new FloatDirectionMatrix(fmd, displayUnit);
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
