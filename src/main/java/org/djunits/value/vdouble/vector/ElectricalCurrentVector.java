package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.value.vdouble.scalar.ElectricalCurrent;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double ElectricalCurrentVector, a vector of values with a ElectricalCurrentUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalCurrentVector
        extends AbstractDoubleVectorRel<ElectricalCurrentUnit, ElectricalCurrent, ElectricalCurrentVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an ElectricalCurrentVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit ElectricalCurrentUnit; the display unit of the vector data
     */
    public ElectricalCurrentVector(final DoubleVectorData data, final ElectricalCurrentUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalCurrent> getScalarClass()
    {
        return ElectricalCurrent.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalCurrentVector instantiateVector(final DoubleVectorData dvd, final ElectricalCurrentUnit displayUnit)
    {
        return new ElectricalCurrentVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalCurrent instantiateScalarSI(final double valueSI, final ElectricalCurrentUnit displayUnit)
    {
        ElectricalCurrent result = ElectricalCurrent.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
