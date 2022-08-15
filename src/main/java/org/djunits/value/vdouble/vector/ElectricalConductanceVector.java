package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.value.vdouble.scalar.ElectricalConductance;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double ElectricalConductanceVector, a vector of values with a ElectricalConductanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalConductanceVector
        extends AbstractDoubleVectorRel<ElectricalConductanceUnit, ElectricalConductance, ElectricalConductanceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an ElectricalConductanceVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit ElectricalConductanceUnit; the display unit of the vector data
     */
    public ElectricalConductanceVector(final DoubleVectorData data, final ElectricalConductanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalConductance> getScalarClass()
    {
        return ElectricalConductance.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalConductanceVector instantiateVector(final DoubleVectorData dvd,
            final ElectricalConductanceUnit displayUnit)
    {
        return new ElectricalConductanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalConductance instantiateScalarSI(final double valueSI, final ElectricalConductanceUnit displayUnit)
    {
        ElectricalConductance result = ElectricalConductance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
