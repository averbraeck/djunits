package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.PowerUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Power;
import org.djunits.value.vdouble.vector.PowerVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double PowerMatrix, a matrix of values with a PowerUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class PowerMatrix extends AbstractDoubleMatrixRel<PowerUnit, Power, PowerVector, PowerMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit PowerUnit; the unit
     */
    public PowerMatrix(final DoubleMatrixData data, final PowerUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Power> getScalarClass()
    {
        return Power.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<PowerVector> getVectorClass()
    {
        return PowerVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public PowerMatrix instantiateMatrix(final DoubleMatrixData dmd, final PowerUnit displayUnit)
    {
        return new PowerMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public PowerVector instantiateVector(final DoubleVectorData dvd, final PowerUnit displayUnit)
    {
        return new PowerVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Power instantiateScalarSI(final double valueSI, final PowerUnit displayUnit)
    {
        Power result = Power.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
