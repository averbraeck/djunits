package org.djunits.value.vdouble.vector;

import org.djunits.unit.EnergyUnit;
import org.djunits.value.vdouble.scalar.Energy;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Double EnergyVector, a vector of values with a EnergyUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-04-30T13:59:27.633664900Z")
public class EnergyVector extends AbstractDoubleVectorRel<EnergyUnit, Energy, EnergyVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an EnergyVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit EnergyUnit; the display unit of the vector data
     */
    public EnergyVector(final DoubleVectorData data, final EnergyUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Energy> getScalarClass()
    {
        return Energy.class;
    }

    /** {@inheritDoc} */
    @Override
    public EnergyVector instantiateVector(final DoubleVectorData dvd, final EnergyUnit displayUnit)
    {
        return new EnergyVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Energy instantiateScalarSI(final double valueSI, final EnergyUnit displayUnit)
    {
        Energy result = Energy.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
