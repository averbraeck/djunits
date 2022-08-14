package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.IlluminanceUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Illuminance;
import org.djunits.value.vdouble.vector.IlluminanceVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double IlluminanceMatrix, a matrix of values with a IlluminanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class IlluminanceMatrix
        extends AbstractDoubleMatrixRel<IlluminanceUnit, Illuminance, IlluminanceVector, IlluminanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit IlluminanceUnit; the unit
     */
    public IlluminanceMatrix(final DoubleMatrixData data, final IlluminanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Illuminance> getScalarClass()
    {
        return Illuminance.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<IlluminanceVector> getVectorClass()
    {
        return IlluminanceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public IlluminanceMatrix instantiateMatrix(final DoubleMatrixData dmd, final IlluminanceUnit displayUnit)
    {
        return new IlluminanceMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public IlluminanceVector instantiateVector(final DoubleVectorData dvd, final IlluminanceUnit displayUnit)
    {
        return new IlluminanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Illuminance instantiateScalarSI(final double valueSI, final IlluminanceUnit displayUnit)
    {
        Illuminance result = Illuminance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
