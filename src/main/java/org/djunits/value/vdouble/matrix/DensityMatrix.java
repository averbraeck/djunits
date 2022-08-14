package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.DensityUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Density;
import org.djunits.value.vdouble.vector.DensityVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double DensityMatrix, a matrix of values with a DensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class DensityMatrix extends AbstractDoubleMatrixRel<DensityUnit, Density, DensityVector, DensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit DensityUnit; the unit
     */
    public DensityMatrix(final DoubleMatrixData data, final DensityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Density> getScalarClass()
    {
        return Density.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<DensityVector> getVectorClass()
    {
        return DensityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public DensityMatrix instantiateMatrix(final DoubleMatrixData dmd, final DensityUnit displayUnit)
    {
        return new DensityMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public DensityVector instantiateVector(final DoubleVectorData dvd, final DensityUnit displayUnit)
    {
        return new DensityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Density instantiateScalarSI(final double valueSI, final DensityUnit displayUnit)
    {
        Density result = Density.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
