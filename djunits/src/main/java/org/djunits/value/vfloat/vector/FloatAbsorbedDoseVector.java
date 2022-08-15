package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.AbsorbedDoseUnit;
import org.djunits.value.vfloat.scalar.FloatAbsorbedDose;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatAbsorbedDoseVector, a vector of values with a AbsorbedDoseUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatAbsorbedDoseVector
        extends AbstractFloatVectorRel<AbsorbedDoseUnit, FloatAbsorbedDose, FloatAbsorbedDoseVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatAbsorbedDoseVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit AbsorbedDoseUnit; the unit
     */
    public FloatAbsorbedDoseVector(final FloatVectorData data, final AbsorbedDoseUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAbsorbedDose> getScalarClass()
    {
        return FloatAbsorbedDose.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsorbedDoseVector instantiateVector(final FloatVectorData fvd, final AbsorbedDoseUnit displayUnit)
    {
        return new FloatAbsorbedDoseVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsorbedDose instantiateScalarSI(final float valueSI, final AbsorbedDoseUnit displayUnit)
    {
        FloatAbsorbedDose result = FloatAbsorbedDose.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
