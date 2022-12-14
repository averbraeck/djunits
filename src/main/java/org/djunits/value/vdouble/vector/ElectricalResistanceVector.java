package org.djunits.value.vdouble.vector;

import jakarta.annotation.Generated;

import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.value.vdouble.scalar.ElectricalResistance;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double ElectricalResistanceVector, a vector of values with a ElectricalResistanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalResistanceVector
        extends AbstractDoubleVectorRel<ElectricalResistanceUnit, ElectricalResistance, ElectricalResistanceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an ElectricalResistanceVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit ElectricalResistanceUnit; the display unit of the vector data
     */
    public ElectricalResistanceVector(final DoubleVectorData data, final ElectricalResistanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalResistance> getScalarClass()
    {
        return ElectricalResistance.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalResistanceVector instantiateVector(final DoubleVectorData dvd, final ElectricalResistanceUnit displayUnit)
    {
        return new ElectricalResistanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalResistance instantiateScalarSI(final double valueSI, final ElectricalResistanceUnit displayUnit)
    {
        ElectricalResistance result = ElectricalResistance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
