package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.FlowMassUnit;
import org.djunits.value.vdouble.scalar.FlowMass;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double FlowMassVector, a vector of values with a FlowMassUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FlowMassVector extends AbstractDoubleVectorRel<FlowMassUnit, FlowMass, FlowMassVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an FlowMassVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit FlowMassUnit; the display unit of the vector data
     */
    public FlowMassVector(final DoubleVectorData data, final FlowMassUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FlowMass> getScalarClass()
    {
        return FlowMass.class;
    }

    /** {@inheritDoc} */
    @Override
    public FlowMassVector instantiateVector(final DoubleVectorData dvd, final FlowMassUnit displayUnit)
    {
        return new FlowMassVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FlowMass instantiateScalarSI(final double valueSI, final FlowMassUnit displayUnit)
    {
        FlowMass result = FlowMass.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
