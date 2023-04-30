package org.djunits.value.vdouble.vector;

import org.djunits.unit.IlluminanceUnit;
import org.djunits.value.vdouble.scalar.Illuminance;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double IlluminanceVector, a vector of values with a IlluminanceUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-04-30T13:59:27.633664900Z")
public class IlluminanceVector extends AbstractDoubleVectorRel<IlluminanceUnit, Illuminance, IlluminanceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an IlluminanceVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit IlluminanceUnit; the display unit of the vector data
     */
    public IlluminanceVector(final DoubleVectorData data, final IlluminanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Illuminance> getScalarClass()
    {
        return Illuminance.class;
    }

    /** {@inheritDoc} */
    @Override
    public IlluminanceVector instantiateVector(final DoubleVectorData dvd, final IlluminanceUnit displayUnit)
    {
        return new IlluminanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Illuminance instantiateScalarSI(final double valueSI, final IlluminanceUnit displayUnit)
    {
        Illuminance result = Illuminance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
