package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.value.vdouble.scalar.ElectricalCapacitance;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double ElectricalCapacitanceVector, a vector of values with a ElectricalCapacitanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalCapacitanceVector
        extends AbstractDoubleVectorRel<ElectricalCapacitanceUnit, ElectricalCapacitance, ElectricalCapacitanceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an ElectricalCapacitanceVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit ElectricalCapacitanceUnit; the display unit of the vector data
     */
    public ElectricalCapacitanceVector(final DoubleVectorData data, final ElectricalCapacitanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalCapacitance> getScalarClass()
    {
        return ElectricalCapacitance.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalCapacitanceVector instantiateVector(final DoubleVectorData dvd,
            final ElectricalCapacitanceUnit displayUnit)
    {
        return new ElectricalCapacitanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalCapacitance instantiateScalarSI(final double valueSI, final ElectricalCapacitanceUnit displayUnit)
    {
        ElectricalCapacitance result = ElectricalCapacitance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
