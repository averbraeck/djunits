package org.djunits.value.vfloat.matrix;

import jakarta.annotation.Generated;

import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatLuminousIntensity;
import org.djunits.value.vfloat.vector.FloatLuminousIntensityVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatLuminousIntensityMatrix, a matrix of values with a LuminousIntensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatLuminousIntensityMatrix extends AbstractFloatMatrixRel<LuminousIntensityUnit, FloatLuminousIntensity,
        FloatLuminousIntensityVector, FloatLuminousIntensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit LuminousIntensityUnit; the unit
     */
    public FloatLuminousIntensityMatrix(final FloatMatrixData data, final LuminousIntensityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatLuminousIntensity> getScalarClass()
    {
        return FloatLuminousIntensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatLuminousIntensityVector> getVectorClass()
    {
        return FloatLuminousIntensityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatLuminousIntensityMatrix instantiateMatrix(final FloatMatrixData fmd, final LuminousIntensityUnit displayUnit)
    {
        return new FloatLuminousIntensityMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatLuminousIntensityVector instantiateVector(final FloatVectorData fvd, final LuminousIntensityUnit displayUnit)
    {
        return new FloatLuminousIntensityVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatLuminousIntensity instantiateScalarSI(final float valueSI, final LuminousIntensityUnit displayUnit)
    {
        FloatLuminousIntensity result = FloatLuminousIntensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
