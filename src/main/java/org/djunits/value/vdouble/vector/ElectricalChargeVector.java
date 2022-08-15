package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.value.vdouble.scalar.ElectricalCharge;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double ElectricalChargeVector, a vector of values with a ElectricalChargeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalChargeVector
        extends AbstractDoubleVectorRel<ElectricalChargeUnit, ElectricalCharge, ElectricalChargeVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an ElectricalChargeVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit ElectricalChargeUnit; the display unit of the vector data
     */
    public ElectricalChargeVector(final DoubleVectorData data, final ElectricalChargeUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalCharge> getScalarClass()
    {
        return ElectricalCharge.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalChargeVector instantiateVector(final DoubleVectorData dvd, final ElectricalChargeUnit displayUnit)
    {
        return new ElectricalChargeVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalCharge instantiateScalarSI(final double valueSI, final ElectricalChargeUnit displayUnit)
    {
        ElectricalCharge result = ElectricalCharge.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
