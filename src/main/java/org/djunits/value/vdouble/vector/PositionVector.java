package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.Position;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorAbs;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double PositionVector, a vector of values with a PositionUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class PositionVector
        extends AbstractDoubleVectorAbs<PositionUnit, Position, PositionVector, LengthUnit, Length, LengthVector>
{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an PositionVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param unit PositionUnit; the display unit of the vector data
     */
    public PositionVector(final DoubleVectorData data, final PositionUnit unit)
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
