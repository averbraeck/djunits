package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.DensityUnit;
import org.djunits.value.vdouble.scalar.Density;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double DensityVector, a vector of values with a DensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class DensityVector extends AbstractDoubleVectorRel<DensityUnit, Density, DensityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an DensityVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit DensityUnit; the display unit of the vector data
     */
    public DensityVector(final DoubleVectorData data, final DensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Density> getScalarClass()
    {
        return Density.class;
    }

    /** {@inheritDoc} */
    @Override
    public DensityVector instantiateVector(final DoubleVectorData dvd, final DensityUnit displayUnit)
    {
        return new DensityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Density instantiateScalarSI(final double valueSI, final DensityUnit displayUnit)
    {
        Density result = Density.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
