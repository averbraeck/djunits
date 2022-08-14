package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.MomentumUnit;
import org.djunits.value.vfloat.scalar.FloatMomentum;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatMomentumVector, a vector of values with a MomentumUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatMomentumVector extends AbstractFloatVectorRel<MomentumUnit, FloatMomentum, FloatMomentumVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatMomentumVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit MomentumUnit; the unit
     */
    public FloatMomentumVector(final FloatVectorData data, final MomentumUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMomentum> getScalarClass()
    {
        return FloatMomentum.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatMomentumVector instantiateVector(final FloatVectorData fvd, final MomentumUnit displayUnit)
    {
        return new FloatMomentumVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMomentum instantiateScalarSI(final float valueSI, final MomentumUnit displayUnit)
    {
        FloatMomentum result = FloatMomentum.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
