package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatElectricalInductance;
import org.djunits.value.vfloat.vector.FloatElectricalInductanceVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatElectricalInductanceMatrix, a matrix of values with a ElectricalInductanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalInductanceMatrix extends AbstractFloatMatrixRel<ElectricalInductanceUnit, FloatElectricalInductance,
        FloatElectricalInductanceVector, FloatElectricalInductanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit ElectricalInductanceUnit; the unit
     */
    public FloatElectricalInductanceMatrix(final FloatMatrixData data, final ElectricalInductanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalInductance> getScalarClass()
    {
        return FloatElectricalInductance.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalInductanceVector> getVectorClass()
    {
        return FloatElectricalInductanceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalInductanceMatrix instantiateMatrix(final FloatMatrixData fmd,
            final ElectricalInductanceUnit displayUnit)
    {
        return new FloatElectricalInductanceMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalInductanceVector instantiateVector(final FloatVectorData fvd,
            final ElectricalInductanceUnit displayUnit)
    {
        return new FloatElectricalInductanceVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalInductance instantiateScalarSI(final float valueSI, final ElectricalInductanceUnit displayUnit)
    {
        FloatElectricalInductance result = FloatElectricalInductance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
