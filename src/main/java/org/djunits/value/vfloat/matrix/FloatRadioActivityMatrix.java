package org.djunits.value.vfloat.matrix;

import org.djunits.unit.RadioActivityUnit;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatRadioActivity;
import org.djunits.value.vfloat.vector.FloatRadioActivityVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatRadioActivityMatrix, a matrix of values with a RadioActivityUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-04-30T13:59:27.633664900Z")
public class FloatRadioActivityMatrix extends
        FloatMatrixRel<RadioActivityUnit, FloatRadioActivity, FloatRadioActivityVector, FloatRadioActivityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data FloatMatrixData; an internal data object
     * @param unit RadioActivityUnit; the unit
     */
    public FloatRadioActivityMatrix(final FloatMatrixData data, final RadioActivityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatRadioActivity> getScalarClass()
    {
        return FloatRadioActivity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatRadioActivityVector> getVectorClass()
    {
        return FloatRadioActivityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatRadioActivityMatrix instantiateMatrix(final FloatMatrixData fmd, final RadioActivityUnit displayUnit)
    {
        return new FloatRadioActivityMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatRadioActivityVector instantiateVector(final FloatVectorData fvd, final RadioActivityUnit displayUnit)
    {
        return new FloatRadioActivityVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatRadioActivity instantiateScalarSI(final float valueSI, final RadioActivityUnit displayUnit)
    {
        FloatRadioActivity result = FloatRadioActivity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
