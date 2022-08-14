package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.value.vdouble.scalar.MagneticFluxDensity;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double MagneticFluxDensityVector, a vector of values with a MagneticFluxDensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class MagneticFluxDensityVector
        extends AbstractDoubleVectorRel<MagneticFluxDensityUnit, MagneticFluxDensity, MagneticFluxDensityVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an MagneticFluxDensityVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit MagneticFluxDensityUnit; the display unit of the vector data
     */
    public MagneticFluxDensityVector(final DoubleVectorData data, final MagneticFluxDensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<MagneticFluxDensity> getScalarClass()
    {
        return MagneticFluxDensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public MagneticFluxDensityVector instantiateVector(final DoubleVectorData dvd, final MagneticFluxDensityUnit displayUnit)
    {
        return new MagneticFluxDensityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public MagneticFluxDensity instantiateScalarSI(final double valueSI, final MagneticFluxDensityUnit displayUnit)
    {
        MagneticFluxDensity result = MagneticFluxDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
