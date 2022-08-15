package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.AngularAcceleration;
import org.djunits.value.vdouble.vector.AngularAccelerationVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double AngularAccelerationMatrix, a matrix of values with a AngularAccelerationUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AngularAccelerationMatrix extends AbstractDoubleMatrixRel<AngularAccelerationUnit, AngularAcceleration,
        AngularAccelerationVector, AngularAccelerationMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit AngularAccelerationUnit; the unit
     */
    public AngularAccelerationMatrix(final DoubleMatrixData data, final AngularAccelerationUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<AngularAcceleration> getScalarClass()
    {
        return AngularAcceleration.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<AngularAccelerationVector> getVectorClass()
    {
        return AngularAccelerationVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public AngularAccelerationMatrix instantiateMatrix(final DoubleMatrixData dmd, final AngularAccelerationUnit displayUnit)
    {
        return new AngularAccelerationMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AngularAccelerationVector instantiateVector(final DoubleVectorData dvd, final AngularAccelerationUnit displayUnit)
    {
        return new AngularAccelerationVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AngularAcceleration instantiateScalarSI(final double valueSI, final AngularAccelerationUnit displayUnit)
    {
        AngularAcceleration result = AngularAcceleration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
