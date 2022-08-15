package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.SolidAngleUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatSolidAngle;
import org.djunits.value.vfloat.vector.FloatSolidAngleVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatSolidAngleMatrix, a matrix of values with a SolidAngleUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatSolidAngleMatrix
        extends AbstractFloatMatrixRel<SolidAngleUnit, FloatSolidAngle, FloatSolidAngleVector, FloatSolidAngleMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit SolidAngleUnit; the unit
     */
    public FloatSolidAngleMatrix(final FloatMatrixData data, final SolidAngleUnit unit)
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
    public Class<FloatSolidAngleVector> getVectorClass()
    {
        return FloatSolidAngleVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatSolidAngleMatrix instantiateMatrix(final FloatMatrixData fmd, final SolidAngleUnit displayUnit)
    {
        return new FloatSolidAngleMatrix(fmd, displayUnit);
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
