package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.SpeedUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Speed;
import org.djunits.value.vdouble.vector.SpeedVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double SpeedMatrix, a matrix of values with a SpeedUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class SpeedMatrix extends AbstractDoubleMatrixRel<SpeedUnit, Speed, SpeedVector, SpeedMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit SpeedUnit; the unit
     */
    public SpeedMatrix(final DoubleMatrixData data, final SpeedUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Speed> getScalarClass()
    {
        return Speed.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<SpeedVector> getVectorClass()
    {
        return SpeedVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public SpeedMatrix instantiateMatrix(final DoubleMatrixData dmd, final SpeedUnit displayUnit)
    {
        return new SpeedMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public SpeedVector instantiateVector(final DoubleVectorData dvd, final SpeedUnit displayUnit)
    {
        return new SpeedVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Speed instantiateScalarSI(final double valueSI, final SpeedUnit displayUnit)
    {
        Speed result = Speed.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
