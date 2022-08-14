package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.AreaUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Area;
import org.djunits.value.vdouble.vector.AreaVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double AreaMatrix, a matrix of values with a AreaUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AreaMatrix extends AbstractDoubleMatrixRel<AreaUnit, Area, AreaVector, AreaMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit AreaUnit; the unit
     */
    public AreaMatrix(final DoubleMatrixData data, final AreaUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Area> getScalarClass()
    {
        return Area.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<AreaVector> getVectorClass()
    {
        return AreaVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public AreaMatrix instantiateMatrix(final DoubleMatrixData dmd, final AreaUnit displayUnit)
    {
        return new AreaMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AreaVector instantiateVector(final DoubleVectorData dvd, final AreaUnit displayUnit)
    {
        return new AreaVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Area instantiateScalarSI(final double valueSI, final AreaUnit displayUnit)
    {
        Area result = Area.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
