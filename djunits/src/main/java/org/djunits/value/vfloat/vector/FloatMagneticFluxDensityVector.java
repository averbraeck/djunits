package org.djunits.value.vfloat.vector;

import javax.annotation.Generated;

import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.value.vfloat.scalar.FloatMagneticFluxDensity;
import org.djunits.value.vfloat.vector.base.AbstractFloatVectorRel;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Immutable Float FloatMagneticFluxDensityVector, a vector of values with a MagneticFluxDensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatMagneticFluxDensityVector
        extends AbstractFloatVectorRel<MagneticFluxDensityUnit, FloatMagneticFluxDensity, FloatMagneticFluxDensityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FloatMagneticFluxDensityVector from an internal data object.
     * @param data FloatVectorData; an internal data object
     * @param unit MagneticFluxDensityUnit; the unit
     */
    public FloatMagneticFluxDensityVector(final FloatVectorData data, final MagneticFluxDensityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMagneticFluxDensity> getScalarClass()
    {
        return FloatMagneticFluxDensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatMagneticFluxDensityVector instantiateVector(final FloatVectorData fvd,
            final MagneticFluxDensityUnit displayUnit)
    {
        return new FloatMagneticFluxDensityVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMagneticFluxDensity instantiateScalarSI(final float valueSI, final MagneticFluxDensityUnit displayUnit)
    {
        FloatMagneticFluxDensity result = FloatMagneticFluxDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
