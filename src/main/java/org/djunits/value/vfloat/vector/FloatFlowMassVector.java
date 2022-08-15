package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.FlowMassUnit;
import org.djunits.value.vfloat.scalar.FloatFlowMass;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatFlowMassVector, a vector of values with a FlowMassUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatFlowMassVector extends AbstractFloatVectorRel<FlowMassUnit, FloatFlowMass, FloatFlowMassVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatFlowMassVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit FlowMassUnit; the unit
     */
    public FloatFlowMassVector(final FloatVectorData data, final FlowMassUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatFlowMass> getScalarClass()
    {
        return FloatFlowMass.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatFlowMassVector instantiateVector(final FloatVectorData fvd, final FlowMassUnit displayUnit)
    {
        return new FloatFlowMassVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatFlowMass instantiateScalarSI(final float valueSI, final FlowMassUnit displayUnit)
    {
        FloatFlowMass result = FloatFlowMass.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
