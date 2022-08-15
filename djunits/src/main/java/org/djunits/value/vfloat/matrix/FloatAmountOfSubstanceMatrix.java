package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.AmountOfSubstanceUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAmountOfSubstance;
import org.djunits.value.vfloat.vector.FloatAmountOfSubstanceVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatAmountOfSubstanceMatrix, a matrix of values with a AmountOfSubstanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAmountOfSubstanceMatrix extends AbstractFloatMatrixRel<AmountOfSubstanceUnit, FloatAmountOfSubstance,
        FloatAmountOfSubstanceVector, FloatAmountOfSubstanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit AmountOfSubstanceUnit; the unit
     */
    public FloatAmountOfSubstanceMatrix(final FloatMatrixData data, final AmountOfSubstanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAmountOfSubstance> getScalarClass()
    {
        return FloatAmountOfSubstance.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAmountOfSubstanceVector> getVectorClass()
    {
        return FloatAmountOfSubstanceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAmountOfSubstanceMatrix instantiateMatrix(final FloatMatrixData fmd, final AmountOfSubstanceUnit displayUnit)
    {
        return new FloatAmountOfSubstanceMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAmountOfSubstanceVector instantiateVector(final FloatVectorData fvd, final AmountOfSubstanceUnit displayUnit)
    {
        return new FloatAmountOfSubstanceVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAmountOfSubstance instantiateScalarSI(final float valueSI, final AmountOfSubstanceUnit displayUnit)
    {
        FloatAmountOfSubstance result = FloatAmountOfSubstance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
