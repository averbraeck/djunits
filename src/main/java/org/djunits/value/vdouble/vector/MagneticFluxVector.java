package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.MagneticFluxUnit;
import org.djunits.value.vdouble.scalar.MagneticFlux;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRel;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double MagneticFluxVector, a vector of values with a MagneticFluxUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class MagneticFluxVector extends AbstractDoubleVectorRel<MagneticFluxUnit, MagneticFlux, MagneticFluxVector>

{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an MagneticFluxVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit MagneticFluxUnit; the display unit of the vector data
     */
    public MagneticFluxVector(final DoubleVectorData data, final MagneticFluxUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<MagneticFlux> getScalarClass()
    {
        return MagneticFlux.class;
    }

    /** {@inheritDoc} */
    @Override
    public MagneticFluxVector instantiateVector(final DoubleVectorData dvd, final MagneticFluxUnit displayUnit)
    {
        return new MagneticFluxVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public MagneticFlux instantiateScalarSI(final double valueSI, final MagneticFluxUnit displayUnit)
    {
        MagneticFlux result = MagneticFlux.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
