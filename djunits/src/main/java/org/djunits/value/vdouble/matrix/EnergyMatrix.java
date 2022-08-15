package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.EnergyUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Energy;
import org.djunits.value.vdouble.vector.EnergyVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double EnergyMatrix, a matrix of values with a EnergyUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class EnergyMatrix extends AbstractDoubleMatrixRel<EnergyUnit, Energy, EnergyVector, EnergyMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit EnergyUnit; the unit
     */
    public EnergyMatrix(final DoubleMatrixData data, final EnergyUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Energy> getScalarClass()
    {
        return Energy.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<EnergyVector> getVectorClass()
    {
        return EnergyVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public EnergyMatrix instantiateMatrix(final DoubleMatrixData dmd, final EnergyUnit displayUnit)
    {
        return new EnergyMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public EnergyVector instantiateVector(final DoubleVectorData dvd, final EnergyUnit displayUnit)
    {
        return new EnergyVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Energy instantiateScalarSI(final double valueSI, final EnergyUnit displayUnit)
    {
        Energy result = Energy.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
