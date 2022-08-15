package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.AccelerationUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Acceleration;
import org.djunits.value.vdouble.vector.AccelerationVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double AccelerationMatrix, a matrix of values with a AccelerationUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AccelerationMatrix
        extends AbstractDoubleMatrixRel<AccelerationUnit, Acceleration, AccelerationVector, AccelerationMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit AccelerationUnit; the unit
     */
    public AccelerationMatrix(final DoubleMatrixData data, final AccelerationUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Acceleration> getScalarClass()
    {
        return Acceleration.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<AccelerationVector> getVectorClass()
    {
        return AccelerationVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public AccelerationMatrix instantiateMatrix(final DoubleMatrixData dmd, final AccelerationUnit displayUnit)
    {
        return new AccelerationMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AccelerationVector instantiateVector(final DoubleVectorData dvd, final AccelerationUnit displayUnit)
    {
        return new AccelerationVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Acceleration instantiateScalarSI(final double valueSI, final AccelerationUnit displayUnit)
    {
        Acceleration result = Acceleration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
