package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.value.vfloat.scalar.FloatElectricalPotential;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatElectricalPotentialVector, a vector of values with a ElectricalPotentialUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalPotentialVector
        extends AbstractFloatVectorRel<ElectricalPotentialUnit, FloatElectricalPotential, FloatElectricalPotentialVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatElectricalPotentialVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit ElectricalPotentialUnit; the unit
     */
    public FloatElectricalPotentialVector(final FloatVectorData data, final ElectricalPotentialUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalPotential> getScalarClass()
    {
        return FloatElectricalPotential.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalPotentialVector instantiateVector(final FloatVectorData fvd,
            final ElectricalPotentialUnit displayUnit)
    {
        return new FloatElectricalPotentialVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalPotential instantiateScalarSI(final float valueSI, final ElectricalPotentialUnit displayUnit)
    {
        FloatElectricalPotential result = FloatElectricalPotential.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
