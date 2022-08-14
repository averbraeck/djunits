package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.EquivalentDoseUnit;
import org.djunits.value.vdouble.scalar.EquivalentDose;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double EquivalentDoseVector, a vector of values with a EquivalentDoseUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class EquivalentDoseVector extends AbstractDoubleVectorRel<EquivalentDoseUnit, EquivalentDose, EquivalentDoseVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an EquivalentDoseVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit EquivalentDoseUnit; the display unit of the vector data
     */
    public EquivalentDoseVector(final DoubleVectorData data, final EquivalentDoseUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<EquivalentDose> getScalarClass()
    {
        return EquivalentDose.class;
    }

    /** {@inheritDoc} */
    @Override
    public EquivalentDoseVector instantiateVector(final DoubleVectorData dvd, final EquivalentDoseUnit displayUnit)
    {
        return new EquivalentDoseVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public EquivalentDose instantiateScalarSI(final double valueSI, final EquivalentDoseUnit displayUnit)
    {
        EquivalentDose result = EquivalentDose.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
