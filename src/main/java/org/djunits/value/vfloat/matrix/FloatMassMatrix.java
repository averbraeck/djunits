package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.MassUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatMass;
import org.djunits.value.vfloat.vector.FloatMassVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatMassMatrix, a matrix of values with a MassUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatMassMatrix extends AbstractFloatMatrixRel<MassUnit, FloatMass, FloatMassVector, FloatMassMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit MassUnit; the unit
     */
    public FloatMassMatrix(final FloatMatrixData data, final MassUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMass> getScalarClass()
    {
        return FloatMass.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMassVector> getVectorClass()
    {
        return FloatMassVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatMassMatrix instantiateMatrix(final FloatMatrixData fmd, final MassUnit displayUnit)
    {
        return new FloatMassMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMassVector instantiateVector(final FloatVectorData fvd, final MassUnit displayUnit)
    {
        return new FloatMassVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMass instantiateScalarSI(final float valueSI, final MassUnit displayUnit)
    {
        FloatMass result = FloatMass.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
