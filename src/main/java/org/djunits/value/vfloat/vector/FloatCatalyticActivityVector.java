package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.value.vfloat.scalar.FloatCatalyticActivity;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatCatalyticActivityVector, a vector of values with a CatalyticActivityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatCatalyticActivityVector
        extends AbstractFloatVectorRel<CatalyticActivityUnit, FloatCatalyticActivity, FloatCatalyticActivityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatCatalyticActivityVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit CatalyticActivityUnit; the unit
     */
    public FloatCatalyticActivityVector(final FloatVectorData data, final CatalyticActivityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatCatalyticActivity> getScalarClass()
    {
        return FloatCatalyticActivity.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatCatalyticActivityVector instantiateVector(final FloatVectorData fvd, final CatalyticActivityUnit displayUnit)
    {
        return new FloatCatalyticActivityVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatCatalyticActivity instantiateScalarSI(final float valueSI, final CatalyticActivityUnit displayUnit)
    {
        FloatCatalyticActivity result = FloatCatalyticActivity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
