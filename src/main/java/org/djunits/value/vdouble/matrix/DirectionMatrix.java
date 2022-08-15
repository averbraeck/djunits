package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixAbs;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Angle;
import org.djunits.value.vdouble.scalar.Direction;
import org.djunits.value.vdouble.vector.AngleVector;
import org.djunits.value.vdouble.vector.DirectionVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Direction Matrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class DirectionMatrix extends AbstractDoubleMatrixAbs<DirectionUnit, Direction, DirectionVector, DirectionMatrix,
        AngleUnit, Angle, AngleVector, AngleMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit DirectionUnit; the unit
     */
    public DirectionMatrix(final DoubleMatrixData data, final DirectionUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Direction> getScalarClass()
    {
        return Direction.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<DirectionVector> getVectorClass()
    {
        return DirectionVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public DirectionMatrix instantiateMatrix(final DoubleMatrixData dmd, final DirectionUnit displayUnit)
    {
        return new DirectionMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public DirectionVector instantiateVector(final DoubleVectorData dvd, final DirectionUnit displayUnit)
    {
        return new DirectionVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Direction instantiateScalarSI(final double valueSI, final DirectionUnit displayUnit)
    {
        Direction result = Direction.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public AngleMatrix instantiateMatrixRel(final DoubleMatrixData dmd, final AngleUnit displayUnit)
    {
        return new AngleMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AngleVector instantiateVectorRel(final DoubleVectorData dvd, final AngleUnit displayUnit)
    {
        return new AngleVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Angle instantiateScalarRelSI(final double valueSI, final AngleUnit displayUnit)
    {
        Angle result = Angle.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
