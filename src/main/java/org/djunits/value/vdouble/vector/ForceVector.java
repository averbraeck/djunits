package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.ForceUnit;
import org.djunits.value.vdouble.scalar.Force;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double ForceVector, a vector of values with a ForceUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ForceVector extends AbstractDoubleVectorRel<ForceUnit, Force, ForceVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an ForceVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit ForceUnit; the display unit of the vector data
     */
    public ForceVector(final DoubleVectorData data, final ForceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Force> getScalarClass()
    {
        return Force.class;
    }

    /** {@inheritDoc} */
    @Override
    public ForceVector instantiateVector(final DoubleVectorData dvd, final ForceUnit displayUnit)
    {
        return new ForceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Force instantiateScalarSI(final double valueSI, final ForceUnit displayUnit)
    {
        Force result = Force.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
