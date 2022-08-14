package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.PressureUnit;
import org.djunits.value.vdouble.scalar.Pressure;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double PressureVector, a vector of values with a PressureUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class PressureVector extends AbstractDoubleVectorRel<PressureUnit, Pressure, PressureVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an PressureVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit PressureUnit; the display unit of the vector data
     */
    public PressureVector(final DoubleVectorData data, final PressureUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Pressure> getScalarClass()
    {
        return Pressure.class;
    }

    /** {@inheritDoc} */
    @Override
    public PressureVector instantiateVector(final DoubleVectorData dvd, final PressureUnit displayUnit)
    {
        return new PressureVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Pressure instantiateScalarSI(final double valueSI, final PressureUnit displayUnit)
    {
        Pressure result = Pressure.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
