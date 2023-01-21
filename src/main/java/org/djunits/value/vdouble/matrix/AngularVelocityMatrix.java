package org.djunits.value.vdouble.matrix;

import jakarta.annotation.Generated;

import org.djunits.unit.AngularVelocityUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.AngularVelocity;
import org.djunits.value.vdouble.vector.AngularVelocityVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double AngularVelocityMatrix, a matrix of values with a AngularVelocityUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AngularVelocityMatrix
        extends AbstractDoubleMatrixRel<AngularVelocityUnit, AngularVelocity, AngularVelocityVector, AngularVelocityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit AngularVelocityUnit; the unit
     */
    public AngularVelocityMatrix(final DoubleMatrixData data, final AngularVelocityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<AngularVelocity> getScalarClass()
    {
        return AngularVelocity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<AngularVelocityVector> getVectorClass()
    {
        return AngularVelocityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public AngularVelocityMatrix instantiateMatrix(final DoubleMatrixData dmd, final AngularVelocityUnit displayUnit)
    {
        return new AngularVelocityMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AngularVelocityVector instantiateVector(final DoubleVectorData dvd, final AngularVelocityUnit displayUnit)
    {
        return new AngularVelocityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AngularVelocity instantiateScalarSI(final double valueSI, final AngularVelocityUnit displayUnit)
    {
        AngularVelocity result = AngularVelocity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
