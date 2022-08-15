package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.MomentumUnit;
import org.djunits.value.vdouble.scalar.Momentum;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double MomentumVector, a vector of values with a MomentumUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class MomentumVector extends AbstractDoubleVectorRel<MomentumUnit, Momentum, MomentumVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an MomentumVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit MomentumUnit; the display unit of the vector data
     */
    public MomentumVector(final DoubleVectorData data, final MomentumUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Momentum> getScalarClass()
    {
        return Momentum.class;
    }

    /** {@inheritDoc} */
    @Override
    public MomentumVector instantiateVector(final DoubleVectorData dvd, final MomentumUnit displayUnit)
    {
        return new MomentumVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Momentum instantiateScalarSI(final double valueSI, final MomentumUnit displayUnit)
    {
        Momentum result = Momentum.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
