package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.AmountOfSubstanceUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.AmountOfSubstance;
import org.djunits.value.vdouble.vector.AmountOfSubstanceVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double AmountOfSubstanceMatrix, a matrix of values with a AmountOfSubstanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AmountOfSubstanceMatrix extends
        AbstractDoubleMatrixRel<AmountOfSubstanceUnit, AmountOfSubstance, AmountOfSubstanceVector, AmountOfSubstanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit AmountOfSubstanceUnit; the unit
     */
    public AmountOfSubstanceMatrix(final DoubleMatrixData data, final AmountOfSubstanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<AmountOfSubstance> getScalarClass()
    {
        return AmountOfSubstance.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<AmountOfSubstanceVector> getVectorClass()
    {
        return AmountOfSubstanceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public AmountOfSubstanceMatrix instantiateMatrix(final DoubleMatrixData dmd, final AmountOfSubstanceUnit displayUnit)
    {
        return new AmountOfSubstanceMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AmountOfSubstanceVector instantiateVector(final DoubleVectorData dvd, final AmountOfSubstanceUnit displayUnit)
    {
        return new AmountOfSubstanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AmountOfSubstance instantiateScalarSI(final double valueSI, final AmountOfSubstanceUnit displayUnit)
    {
        AmountOfSubstance result = AmountOfSubstance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
