package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.value.vdouble.scalar.ElectricalPotential;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double ElectricalPotentialVector, a vector of values with a ElectricalPotentialUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalPotentialVector
        extends AbstractDoubleVectorRel<ElectricalPotentialUnit, ElectricalPotential, ElectricalPotentialVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an ElectricalPotentialVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit ElectricalPotentialUnit; the display unit of the vector data
     */
    public ElectricalPotentialVector(final DoubleVectorData data, final ElectricalPotentialUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalPotential> getScalarClass()
    {
        return ElectricalPotential.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalPotentialVector instantiateVector(final DoubleVectorData dvd, final ElectricalPotentialUnit displayUnit)
    {
        return new ElectricalPotentialVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalPotential instantiateScalarSI(final double valueSI, final ElectricalPotentialUnit displayUnit)
    {
        ElectricalPotential result = ElectricalPotential.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
