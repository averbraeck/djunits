package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.AngularVelocityUnit;
import org.djunits.value.vdouble.scalar.AngularVelocity;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double AngularVelocityVector, a vector of values with a AngularVelocityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AngularVelocityVector extends AbstractDoubleVectorRel<AngularVelocityUnit, AngularVelocity, AngularVelocityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an AngularVelocityVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit AngularVelocityUnit; the display unit of the vector data
     */
    public AngularVelocityVector(final DoubleVectorData data, final AngularVelocityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<AngularVelocity> getScalarClass()
    {
        return AngularVelocity.class;
    }

    /** {@inheritDoc} */
    @Override
    public AngularVelocityVector instantiateVector(final DoubleVectorData dvd, final AngularVelocityUnit displayUnit)
    {
        return new AngularVelocityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AngularVelocity instantiateScalarSI(final double valueSI, final AngularVelocityUnit displayUnit)
    {
        AngularVelocity result = AngularVelocity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
