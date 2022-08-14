package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ForceUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Force;
import org.djunits.value.vdouble.vector.ForceVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double ForceMatrix, a matrix of values with a ForceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ForceMatrix extends AbstractDoubleMatrixRel<ForceUnit, Force, ForceVector, ForceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit ForceUnit; the unit
     */
    public ForceMatrix(final DoubleMatrixData data, final ForceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Force> getScalarClass()
    {
        return Force.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<ForceVector> getVectorClass()
    {
        return ForceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public ForceMatrix instantiateMatrix(final DoubleMatrixData dmd, final ForceUnit displayUnit)
    {
        return new ForceMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ForceVector instantiateVector(final DoubleVectorData dvd, final ForceUnit displayUnit)
    {
        return new ForceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Force instantiateScalarSI(final double valueSI, final ForceUnit displayUnit)
    {
        Force result = Force.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
