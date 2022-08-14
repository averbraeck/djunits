package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.LuminousFluxUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatLuminousFlux;
import org.djunits.value.vfloat.vector.FloatLuminousFluxVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatLuminousFluxMatrix, a matrix of values with a LuminousFluxUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatLuminousFluxMatrix
        extends AbstractFloatMatrixRel<LuminousFluxUnit, FloatLuminousFlux, FloatLuminousFluxVector, FloatLuminousFluxMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit LuminousFluxUnit; the unit
     */
    public FloatLuminousFluxMatrix(final FloatMatrixData data, final LuminousFluxUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatLuminousFlux> getScalarClass()
    {
        return FloatLuminousFlux.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatLuminousFluxVector> getVectorClass()
    {
        return FloatLuminousFluxVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatLuminousFluxMatrix instantiateMatrix(final FloatMatrixData fmd, final LuminousFluxUnit displayUnit)
    {
        return new FloatLuminousFluxMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatLuminousFluxVector instantiateVector(final FloatVectorData fvd, final LuminousFluxUnit displayUnit)
    {
        return new FloatLuminousFluxVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatLuminousFlux instantiateScalarSI(final float valueSI, final LuminousFluxUnit displayUnit)
    {
        FloatLuminousFlux result = FloatLuminousFlux.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
