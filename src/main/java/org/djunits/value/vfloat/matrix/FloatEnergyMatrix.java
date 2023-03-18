package org.djunits.value.vfloat.matrix;

import org.djunits.unit.EnergyUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatEnergy;
import org.djunits.value.vfloat.vector.FloatEnergyVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatEnergyMatrix, a matrix of values with a EnergyUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-01-21T20:18:25.227867Z")
public class FloatEnergyMatrix extends AbstractFloatMatrixRel<EnergyUnit, FloatEnergy, FloatEnergyVector, FloatEnergyMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit EnergyUnit; the unit
     */
    public FloatEnergyMatrix(final FloatMatrixData data, final EnergyUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatEnergy> getScalarClass()
    {
        return FloatEnergy.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatEnergyVector> getVectorClass()
    {
        return FloatEnergyVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatEnergyMatrix instantiateMatrix(final FloatMatrixData fmd, final EnergyUnit displayUnit)
    {
        return new FloatEnergyMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatEnergyVector instantiateVector(final FloatVectorData fvd, final EnergyUnit displayUnit)
    {
        return new FloatEnergyVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatEnergy instantiateScalarSI(final float valueSI, final EnergyUnit displayUnit)
    {
        FloatEnergy result = FloatEnergy.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
