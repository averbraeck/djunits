package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.value.vfloat.scalar.FloatLuminousIntensity;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatLuminousIntensityVector, a vector of values with a LuminousIntensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatLuminousIntensityVector
        extends AbstractFloatVectorRel<LuminousIntensityUnit, FloatLuminousIntensity, FloatLuminousIntensityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatLuminousIntensityVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit LuminousIntensityUnit; the unit
     */
    public FloatLuminousIntensityVector(final FloatVectorData data, final LuminousIntensityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatLuminousIntensity> getScalarClass()
    {
        return FloatLuminousIntensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatLuminousIntensityVector instantiateVector(final FloatVectorData fvd, final LuminousIntensityUnit displayUnit)
    {
        return new FloatLuminousIntensityVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatLuminousIntensity instantiateScalarSI(final float valueSI, final LuminousIntensityUnit displayUnit)
    {
        FloatLuminousIntensity result = FloatLuminousIntensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
