package org.djunits.userdefined;

import org.djunits.value.vfloat.matrix.base.AbstractFloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float JerkMatrix, a matrix of values with a JerkUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class FloatJerkMatrix extends AbstractFloatMatrixRel<JerkUnit, FloatJerk, FloatJerkVector, FloatJerkMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param displayUnit JerkUnit; the unit in which the data will be displayed
     */
    public FloatJerkMatrix(final FloatMatrixData data, final JerkUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatJerk> getScalarClass()
    {
        return FloatJerk.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatJerkVector> getVectorClass()
    {
        return FloatJerkVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatJerkMatrix instantiateMatrix(final FloatMatrixData fmd, final JerkUnit displayUnit)
    {
        return new FloatJerkMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatJerkVector instantiateVector(final FloatVectorData fvd, final JerkUnit displayUnit)
    {
        return new FloatJerkVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatJerk instantiateScalarSI(final float valueSI, final JerkUnit displayUnit)
    {
        FloatJerk result = new FloatJerk(valueSI, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
