package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.value.vdouble.scalar.ElectricalInductance;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double ElectricalInductanceVector, a vector of values with a ElectricalInductanceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalInductanceVector
        extends AbstractDoubleVectorRel<ElectricalInductanceUnit, ElectricalInductance, ElectricalInductanceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an ElectricalInductanceVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit ElectricalInductanceUnit; the display unit of the vector data
     */
    public ElectricalInductanceVector(final DoubleVectorData data, final ElectricalInductanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalInductance> getScalarClass()
    {
        return ElectricalInductance.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalInductanceVector instantiateVector(final DoubleVectorData dvd, final ElectricalInductanceUnit displayUnit)
    {
        return new ElectricalInductanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalInductance instantiateScalarSI(final double valueSI, final ElectricalInductanceUnit displayUnit)
    {
        ElectricalInductance result = ElectricalInductance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
