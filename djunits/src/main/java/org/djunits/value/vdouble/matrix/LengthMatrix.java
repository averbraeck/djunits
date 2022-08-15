package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRelWithAbs;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.Position;
import org.djunits.value.vdouble.vector.LengthVector;
import org.djunits.value.vdouble.vector.PositionVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Length Matrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class LengthMatrix extends AbstractDoubleMatrixRelWithAbs<PositionUnit, Position, PositionVector, PositionMatrix,
        LengthUnit, Length, LengthVector, LengthMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit LengthUnit; the unit
     */
    public LengthMatrix(final DoubleMatrixData data, final LengthUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Length> getScalarClass()
    {
        return Length.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<LengthVector> getVectorClass()
    {
        return LengthVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public LengthMatrix instantiateMatrix(final DoubleMatrixData dmd, final LengthUnit displayUnit)
    {
        return new LengthMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public LengthVector instantiateVector(final DoubleVectorData dvd, final LengthUnit displayUnit)
    {
        return new LengthVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Length instantiateScalarSI(final double valueSI, final LengthUnit displayUnit)
    {
        Length result = Length.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public PositionMatrix instantiateMatrixAbs(final DoubleMatrixData dmd, final PositionUnit displayUnit)
    {
        return new PositionMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public PositionVector instantiateVectorAbs(final DoubleVectorData dvd, final PositionUnit displayUnit)
    {
        return new PositionVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Position instantiateScalarAbsSI(final double valueSI, final PositionUnit displayUnit)
    {
        Position result = Position.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
