package org.djunits.value.vdouble.matrix;

import javax.annotation.Generated;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.vdouble.matrix.base.AbstractDoubleMatrixRelWithAbs;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.Temperature;
import org.djunits.value.vdouble.vector.AbsoluteTemperatureVector;
import org.djunits.value.vdouble.vector.TemperatureVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Immutable Temperature Matrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class TemperatureMatrix
        extends AbstractDoubleMatrixRelWithAbs<AbsoluteTemperatureUnit, AbsoluteTemperature, AbsoluteTemperatureVector,
                AbsoluteTemperatureMatrix, TemperatureUnit, Temperature, TemperatureVector, TemperatureMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * @param data DoubleMatrixData; an internal data object
     * @param unit TemperatureUnit; the unit
     */
    public TemperatureMatrix(final DoubleMatrixData data, final TemperatureUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Temperature> getScalarClass()
    {
        return Temperature.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<TemperatureVector> getVectorClass()
    {
        return TemperatureVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public TemperatureMatrix instantiateMatrix(final DoubleMatrixData dmd, final TemperatureUnit displayUnit)
    {
        return new TemperatureMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public TemperatureVector instantiateVector(final DoubleVectorData dvd, final TemperatureUnit displayUnit)
    {
        return new TemperatureVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Temperature instantiateScalarSI(final double valueSI, final TemperatureUnit displayUnit)
    {
        Temperature result = Temperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public AbsoluteTemperatureMatrix instantiateMatrixAbs(final DoubleMatrixData dmd, final AbsoluteTemperatureUnit displayUnit)
    {
        return new AbsoluteTemperatureMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AbsoluteTemperatureVector instantiateVectorAbs(final DoubleVectorData dvd, final AbsoluteTemperatureUnit displayUnit)
    {
        return new AbsoluteTemperatureVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AbsoluteTemperature instantiateScalarAbsSI(final double valueSI, final AbsoluteTemperatureUnit displayUnit)
    {
        AbsoluteTemperature result = AbsoluteTemperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
