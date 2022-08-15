package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatElectricalConductance;
import org.djunits.value.vfloat.vector.FloatElectricalConductanceVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatElectricalConductanceMatrix, a matrix of values with a ElectricalConductanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalConductanceMatrix extends AbstractFloatMatrixRel<ElectricalConductanceUnit,
        FloatElectricalConductance, FloatElectricalConductanceVector, FloatElectricalConductanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit ElectricalConductanceUnit; the unit
     */
    public FloatElectricalConductanceMatrix(final FloatMatrixData data, final ElectricalConductanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalConductance> getScalarClass()
    {
        return FloatElectricalConductance.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalConductanceVector> getVectorClass()
    {
        return FloatElectricalConductanceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalConductanceMatrix instantiateMatrix(final FloatMatrixData fmd,
            final ElectricalConductanceUnit displayUnit)
    {
        return new FloatElectricalConductanceMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalConductanceVector instantiateVector(final FloatVectorData fvd,
            final ElectricalConductanceUnit displayUnit)
    {
        return new FloatElectricalConductanceVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalConductance instantiateScalarSI(final float valueSI, final ElectricalConductanceUnit displayUnit)
    {
        FloatElectricalConductance result = FloatElectricalConductance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
