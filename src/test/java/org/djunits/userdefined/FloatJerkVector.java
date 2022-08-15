package org.djunits.userdefined;

import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Float JerkVector, a vector of values with a JerkUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class FloatJerkVector extends AbstractFloatVectorRel<JerkUnit, FloatJerk, FloatJerkVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an JerkVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param displayUnit JerkUnit; the unit in which the data will be displayed
     */
    public FloatJerkVector(final FloatVectorData data, final JerkUnit displayUnit)
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
