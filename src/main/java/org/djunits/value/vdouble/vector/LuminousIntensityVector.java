package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.value.vdouble.scalar.LuminousIntensity;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double LuminousIntensityVector, a vector of values with a LuminousIntensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class LuminousIntensityVector
        extends AbstractDoubleVectorRel<LuminousIntensityUnit, LuminousIntensity, LuminousIntensityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an LuminousIntensityVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit LuminousIntensityUnit; the display unit of the vector data
     */
    public LuminousIntensityVector(final DoubleVectorData data, final LuminousIntensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<LuminousIntensity> getScalarClass()
    {
        return LuminousIntensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public LuminousIntensityVector instantiateVector(final DoubleVectorData dvd, final LuminousIntensityUnit displayUnit)
    {
        return new LuminousIntensityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public LuminousIntensity instantiateScalarSI(final double valueSI, final LuminousIntensityUnit displayUnit)
    {
        LuminousIntensity result = LuminousIntensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
