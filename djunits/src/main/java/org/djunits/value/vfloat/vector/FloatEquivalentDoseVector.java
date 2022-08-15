package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.EquivalentDoseUnit;
import org.djunits.value.vfloat.scalar.FloatEquivalentDose;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatEquivalentDoseVector, a vector of values with a EquivalentDoseUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatEquivalentDoseVector
        extends AbstractFloatVectorRel<EquivalentDoseUnit, FloatEquivalentDose, FloatEquivalentDoseVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatEquivalentDoseVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit EquivalentDoseUnit; the unit
     */
    public FloatEquivalentDoseVector(final FloatVectorData data, final EquivalentDoseUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatEquivalentDose> getScalarClass()
    {
        return FloatEquivalentDose.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatEquivalentDoseVector instantiateVector(final FloatVectorData fvd, final EquivalentDoseUnit displayUnit)
    {
        return new FloatEquivalentDoseVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatEquivalentDose instantiateScalarSI(final float valueSI, final EquivalentDoseUnit displayUnit)
    {
        FloatEquivalentDose result = FloatEquivalentDose.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
