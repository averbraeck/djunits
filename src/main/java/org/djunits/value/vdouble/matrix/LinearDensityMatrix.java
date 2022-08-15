package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.LinearDensityUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.LinearDensity;
import org.djunits.value.vdouble.vector.LinearDensityVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double LinearDensityMatrix, a matrix of values with a LinearDensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class LinearDensityMatrix
        extends AbstractDoubleMatrixRel<LinearDensityUnit, LinearDensity, LinearDensityVector, LinearDensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit LinearDensityUnit; the unit
     */
    public LinearDensityMatrix(final DoubleMatrixData data, final LinearDensityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<LinearDensity> getScalarClass()
    {
        return LinearDensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<LinearDensityVector> getVectorClass()
    {
        return LinearDensityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public LinearDensityMatrix instantiateMatrix(final DoubleMatrixData dmd, final LinearDensityUnit displayUnit)
    {
        return new LinearDensityMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public LinearDensityVector instantiateVector(final DoubleVectorData dvd, final LinearDensityUnit displayUnit)
    {
        return new LinearDensityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public LinearDensity instantiateScalarSI(final double valueSI, final LinearDensityUnit displayUnit)
    {
        LinearDensity result = LinearDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
