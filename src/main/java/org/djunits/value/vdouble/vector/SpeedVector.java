package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.SpeedUnit;
import org.djunits.value.vdouble.scalar.Speed;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double SpeedVector, a vector of values with a SpeedUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class SpeedVector extends AbstractDoubleVectorRel<SpeedUnit, Speed, SpeedVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an SpeedVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit SpeedUnit; the display unit of the vector data
     */
    public SpeedVector(final DoubleVectorData data, final SpeedUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Speed> getScalarClass()
    {
        return Speed.class;
    }

    /** {@inheritDoc} */
    @Override
    public SpeedVector instantiateVector(final DoubleVectorData dvd, final SpeedUnit displayUnit)
    {
        return new SpeedVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Speed instantiateScalarSI(final double valueSI, final SpeedUnit displayUnit)
    {
        Speed result = Speed.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
