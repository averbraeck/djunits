package org.djunits.userdefined;

import org.djunits.value.vfloat.vector.base.FloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Float JerkVector, a vector of values with a JerkUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class FloatJerkVector extends FloatVectorRel<JerkUnit, FloatJerk, FloatJerkVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an JerkVector from an internal data object.
     * @param data an internal data object
     * @param displayUnit the unit in which the data will be displayed
     */
    public FloatJerkVector(final FloatVectorData data, final JerkUnit displayUnit)
    {
        super(data, displayUnit);
    }

    @Override
    public Class<FloatJerk> getScalarClass()
    {
        return FloatJerk.class;
    }

    @Override
    public FloatJerkVector instantiateVector(final FloatVectorData fvd, final JerkUnit displayUnit)
    {
        return new FloatJerkVector(fvd, displayUnit);
    }

    @Override
    public FloatJerk instantiateScalarSI(final float valueSI, final JerkUnit displayUnit)
    {
        FloatJerk result = new FloatJerk(valueSI, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
