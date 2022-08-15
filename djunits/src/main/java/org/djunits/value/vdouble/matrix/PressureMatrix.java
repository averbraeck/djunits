package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.PressureUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Pressure;
import org.djunits.value.vdouble.vector.PressureVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double PressureMatrix, a matrix of values with a PressureUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class PressureMatrix extends AbstractDoubleMatrixRel<PressureUnit, Pressure, PressureVector, PressureMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit PressureUnit; the unit
     */
    public PressureMatrix(final DoubleMatrixData data, final PressureUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Pressure> getScalarClass()
    {
        return Pressure.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<PressureVector> getVectorClass()
    {
        return PressureVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public PressureMatrix instantiateMatrix(final DoubleMatrixData dmd, final PressureUnit displayUnit)
    {
        return new PressureMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public PressureVector instantiateVector(final DoubleVectorData dvd, final PressureUnit displayUnit)
    {
        return new PressureVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Pressure instantiateScalarSI(final double valueSI, final PressureUnit displayUnit)
    {
        Pressure result = Pressure.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
