package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.PowerUnit;
import org.djunits.value.vdouble.scalar.Power;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double PowerVector, a vector of values with a PowerUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class PowerVector extends AbstractDoubleVectorRel<PowerUnit, Power, PowerVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an PowerVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit PowerUnit; the display unit of the vector data
     */
    public PowerVector(final DoubleVectorData data, final PowerUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Power> getScalarClass()
    {
        return Power.class;
    }

    /** {@inheritDoc} */
    @Override
    public PowerVector instantiateVector(final DoubleVectorData dvd, final PowerUnit displayUnit)
    {
        return new PowerVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Power instantiateScalarSI(final double valueSI, final PowerUnit displayUnit)
    {
        Power result = Power.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
