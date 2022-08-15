package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.value.vfloat.scalar.FloatElectricalInductance;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatElectricalInductanceVector, a vector of values with a ElectricalInductanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalInductanceVector
        extends AbstractFloatVectorRel<ElectricalInductanceUnit, FloatElectricalInductance, FloatElectricalInductanceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatElectricalInductanceVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit ElectricalInductanceUnit; the unit
     */
    public FloatElectricalInductanceVector(final FloatVectorData data, final ElectricalInductanceUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatElectricalInductance> getScalarClass()
    {
        return FloatElectricalInductance.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalInductanceVector instantiateVector(final FloatVectorData fvd,
            final ElectricalInductanceUnit displayUnit)
    {
        return new FloatElectricalInductanceVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalInductance instantiateScalarSI(final float valueSI, final ElectricalInductanceUnit displayUnit)
    {
        FloatElectricalInductance result = FloatElectricalInductance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
