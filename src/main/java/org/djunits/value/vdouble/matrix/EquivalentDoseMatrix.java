package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.EquivalentDoseUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.EquivalentDose;
import org.djunits.value.vdouble.vector.EquivalentDoseVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double EquivalentDoseMatrix, a matrix of values with a EquivalentDoseUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class EquivalentDoseMatrix
        extends AbstractDoubleMatrixRel<EquivalentDoseUnit, EquivalentDose, EquivalentDoseVector, EquivalentDoseMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit EquivalentDoseUnit; the unit
     */
    public EquivalentDoseMatrix(final DoubleMatrixData data, final EquivalentDoseUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<EquivalentDose> getScalarClass()
    {
        return EquivalentDose.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<EquivalentDoseVector> getVectorClass()
    {
        return EquivalentDoseVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public EquivalentDoseMatrix instantiateMatrix(final DoubleMatrixData dmd, final EquivalentDoseUnit displayUnit)
    {
        return new EquivalentDoseMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public EquivalentDoseVector instantiateVector(final DoubleVectorData dvd, final EquivalentDoseUnit displayUnit)
    {
        return new EquivalentDoseVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public EquivalentDose instantiateScalarSI(final double valueSI, final EquivalentDoseUnit displayUnit)
    {
        EquivalentDose result = EquivalentDose.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
