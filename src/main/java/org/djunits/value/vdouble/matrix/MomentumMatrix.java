package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.MomentumUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Momentum;
import org.djunits.value.vdouble.vector.MomentumVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double MomentumMatrix, a matrix of values with a MomentumUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class MomentumMatrix extends AbstractDoubleMatrixRel<MomentumUnit, Momentum, MomentumVector, MomentumMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit MomentumUnit; the unit
     */
    public MomentumMatrix(final DoubleMatrixData data, final MomentumUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Momentum> getScalarClass()
    {
        return Momentum.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<MomentumVector> getVectorClass()
    {
        return MomentumVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public MomentumMatrix instantiateMatrix(final DoubleMatrixData dmd, final MomentumUnit displayUnit)
    {
        return new MomentumMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public MomentumVector instantiateVector(final DoubleVectorData dvd, final MomentumUnit displayUnit)
    {
        return new MomentumVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Momentum instantiateScalarSI(final double valueSI, final MomentumUnit displayUnit)
    {
        Momentum result = Momentum.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
