package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixAbs;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.Position;
import org.djunits.value.vdouble.vector.LengthVector;
import org.djunits.value.vdouble.vector.PositionVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Position Matrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class PositionMatrix extends AbstractDoubleMatrixAbs<PositionUnit, Position, PositionVector, PositionMatrix, LengthUnit,
        Length, LengthVector, LengthMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit PositionUnit; the unit
     */
    public PositionMatrix(final DoubleMatrixData data, final PositionUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Position> getScalarClass()
    {
        return Position.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<PositionVector> getVectorClass()
    {
        return PositionVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public PositionMatrix instantiateMatrix(final DoubleMatrixData dmd, final PositionUnit displayUnit)
    {
        return new PositionMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public PositionVector instantiateVector(final DoubleVectorData dvd, final PositionUnit displayUnit)
    {
        return new PositionVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Position instantiateScalarSI(final double valueSI, final PositionUnit displayUnit)
    {
        Position result = Position.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public LengthMatrix instantiateMatrixRel(final DoubleMatrixData dmd, final LengthUnit displayUnit)
    {
        return new LengthMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public LengthVector instantiateVectorRel(final DoubleVectorData dvd, final LengthUnit displayUnit)
    {
        return new LengthVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Length instantiateScalarRelSI(final double valueSI, final LengthUnit displayUnit)
    {
        Length result = Length.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
