package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRel;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.MagneticFluxDensity;
import org.djunits.value.vdouble.vector.MagneticFluxDensityVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Double MagneticFluxDensityMatrix, a matrix of values with a MagneticFluxDensityUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class MagneticFluxDensityMatrix extends AbstractDoubleMatrixRel<MagneticFluxDensityUnit, MagneticFluxDensity,
        MagneticFluxDensityVector, MagneticFluxDensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit MagneticFluxDensityUnit; the unit
     */
    public MagneticFluxDensityMatrix(final DoubleMatrixData data, final MagneticFluxDensityUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<MagneticFluxDensity> getScalarClass()
    {
        return MagneticFluxDensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<MagneticFluxDensityVector> getVectorClass()
    {
        return MagneticFluxDensityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public MagneticFluxDensityMatrix instantiateMatrix(final DoubleMatrixData dmd, final MagneticFluxDensityUnit displayUnit)
    {
        return new MagneticFluxDensityMatrix(dmd, displayUnit);
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
