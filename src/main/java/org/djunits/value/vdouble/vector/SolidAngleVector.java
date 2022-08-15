package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.SolidAngleUnit;
import org.djunits.value.vdouble.scalar.SolidAngle;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double SolidAngleVector, a vector of values with a SolidAngleUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class SolidAngleVector extends AbstractDoubleVectorRel<SolidAngleUnit, SolidAngle, SolidAngleVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an SolidAngleVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit SolidAngleUnit; the display unit of the vector data
     */
    public SolidAngleVector(final DoubleVectorData data, final SolidAngleUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<SolidAngle> getScalarClass()
    {
        return SolidAngle.class;
    }

    /** {@inheritDoc} */
    @Override
    public SolidAngleVector instantiateVector(final DoubleVectorData dvd, final SolidAngleUnit displayUnit)
    {
        return new SolidAngleVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public SolidAngle instantiateScalarSI(final double valueSI, final SolidAngleUnit displayUnit)
    {
        SolidAngle result = SolidAngle.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
