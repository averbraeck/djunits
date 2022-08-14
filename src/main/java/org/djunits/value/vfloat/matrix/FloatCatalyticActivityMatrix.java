package org.djunits.value.vfloat.matrix;

import javax.annotation.Generated;

import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatCatalyticActivity;
import org.djunits.value.vfloat.vector.FloatCatalyticActivityVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable FloatFloatCatalyticActivityMatrix, a matrix of values with a CatalyticActivityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatCatalyticActivityMatrix extends AbstractFloatMatrixRel<CatalyticActivityUnit, FloatCatalyticActivity,
        FloatCatalyticActivityVector, FloatCatalyticActivityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit CatalyticActivityUnit; the unit
     */
    public FloatCatalyticActivityMatrix(final FloatMatrixData data, final CatalyticActivityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatCatalyticActivity> getScalarClass()
    {
        return FloatCatalyticActivity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatCatalyticActivityVector> getVectorClass()
    {
        return FloatCatalyticActivityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatCatalyticActivityMatrix instantiateMatrix(final FloatMatrixData fmd, final CatalyticActivityUnit displayUnit)
    {
        return new FloatCatalyticActivityMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatCatalyticActivityVector instantiateVector(final FloatVectorData fvd, final CatalyticActivityUnit displayUnit)
    {
        return new FloatCatalyticActivityVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatCatalyticActivity instantiateScalarSI(final float valueSI, final CatalyticActivityUnit displayUnit)
    {
        FloatCatalyticActivity result = FloatCatalyticActivity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
