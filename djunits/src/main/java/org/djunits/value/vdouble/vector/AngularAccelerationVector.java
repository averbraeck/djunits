package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.AngularAccelerationUnit;
import org.djunits.value.vdouble.scalar.AngularAcceleration;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double AngularAccelerationVector, a vector of values with a AngularAccelerationUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AngularAccelerationVector
        extends AbstractDoubleVectorRel<AngularAccelerationUnit, AngularAcceleration, AngularAccelerationVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an AngularAccelerationVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit AngularAccelerationUnit; the display unit of the vector data
     */
    public AngularAccelerationVector(final DoubleVectorData data, final AngularAccelerationUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<AngularAcceleration> getScalarClass()
    {
        return AngularAcceleration.class;
    }

    /** {@inheritDoc} */
    @Override
    public AngularAccelerationVector instantiateVector(final DoubleVectorData dvd, final AngularAccelerationUnit displayUnit)
    {
        return new AngularAccelerationVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AngularAcceleration instantiateScalarSI(final double valueSI, final AngularAccelerationUnit displayUnit)
    {
        AngularAcceleration result = AngularAcceleration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
