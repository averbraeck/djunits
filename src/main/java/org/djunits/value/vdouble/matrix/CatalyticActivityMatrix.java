package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.CatalyticActivity;
import org.djunits.value.vdouble.vector.CatalyticActivityVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double CatalyticActivityMatrix, a matrix of values with a CatalyticActivityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class CatalyticActivityMatrix extends
        AbstractDoubleMatrixRel<CatalyticActivityUnit, CatalyticActivity, CatalyticActivityVector, CatalyticActivityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit CatalyticActivityUnit; the unit
     */
    public CatalyticActivityMatrix(final DoubleMatrixData data, final CatalyticActivityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<CatalyticActivity> getScalarClass()
    {
        return CatalyticActivity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<CatalyticActivityVector> getVectorClass()
    {
        return CatalyticActivityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public CatalyticActivityMatrix instantiateMatrix(final DoubleMatrixData dmd, final CatalyticActivityUnit displayUnit)
    {
        return new CatalyticActivityMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public CatalyticActivityVector instantiateVector(final DoubleVectorData dvd, final CatalyticActivityUnit displayUnit)
    {
        return new CatalyticActivityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public CatalyticActivity instantiateScalarSI(final double valueSI, final CatalyticActivityUnit displayUnit)
    {
        CatalyticActivity result = CatalyticActivity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
