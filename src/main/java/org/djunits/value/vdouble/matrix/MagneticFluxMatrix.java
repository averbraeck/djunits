package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.MagneticFluxUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.MagneticFlux;
import org.djunits.value.vdouble.vector.MagneticFluxVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double MagneticFluxMatrix, a matrix of values with a MagneticFluxUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class MagneticFluxMatrix
        extends AbstractDoubleMatrixRel<MagneticFluxUnit, MagneticFlux, MagneticFluxVector, MagneticFluxMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit MagneticFluxUnit; the unit
     */
    public MagneticFluxMatrix(final DoubleMatrixData data, final MagneticFluxUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<MagneticFlux> getScalarClass()
    {
        return MagneticFlux.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<MagneticFluxVector> getVectorClass()
    {
        return MagneticFluxVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public MagneticFluxMatrix instantiateMatrix(final DoubleMatrixData dmd, final MagneticFluxUnit displayUnit)
    {
        return new MagneticFluxMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public MagneticFluxVector instantiateVector(final DoubleVectorData dvd, final MagneticFluxUnit displayUnit)
    {
        return new MagneticFluxVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public MagneticFlux instantiateScalarSI(final double valueSI, final MagneticFluxUnit displayUnit)
    {
        MagneticFlux result = MagneticFlux.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
