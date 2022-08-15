package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.MagneticFluxUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatMagneticFlux;
import org.djunits.value.vfloat.vector.FloatMagneticFluxVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatMagneticFluxMatrix, a matrix of values with a MagneticFluxUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatMagneticFluxMatrix
        extends AbstractFloatMatrixRel<MagneticFluxUnit, FloatMagneticFlux, FloatMagneticFluxVector, FloatMagneticFluxMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit MagneticFluxUnit; the unit
     */
    public FloatMagneticFluxMatrix(final FloatMatrixData data, final MagneticFluxUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMagneticFlux> getScalarClass()
    {
        return FloatMagneticFlux.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMagneticFluxVector> getVectorClass()
    {
        return FloatMagneticFluxVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatMagneticFluxMatrix instantiateMatrix(final FloatMatrixData fmd, final MagneticFluxUnit displayUnit)
    {
        return new FloatMagneticFluxMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMagneticFluxVector instantiateVector(final FloatVectorData fvd, final MagneticFluxUnit displayUnit)
    {
        return new FloatMagneticFluxVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMagneticFlux instantiateScalarSI(final float valueSI, final MagneticFluxUnit displayUnit)
    {
        FloatMagneticFlux result = FloatMagneticFlux.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
