package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.Temperature;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorAbs;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double AbsoluteTemperatureVector, a vector of values with a AbsoluteTemperatureUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class AbsoluteTemperatureVector extends AbstractDoubleVectorAbs<AbsoluteTemperatureUnit, AbsoluteTemperature,
        AbsoluteTemperatureVector, TemperatureUnit, Temperature, TemperatureVector>
{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an AbsoluteTemperatureVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param unit AbsoluteTemperatureUnit; the display unit of the vector data
     */
    public AbsoluteTemperatureVector(final DoubleVectorData data, final AbsoluteTemperatureUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<AbsoluteTemperature> getScalarClass()
    {
        return AbsoluteTemperature.class;
    }

    /** {@inheritDoc} */
    @Override
    public AbsoluteTemperatureVector instantiateVector(final DoubleVectorData dvd, final AbsoluteTemperatureUnit displayUnit)
    {
        return new AbsoluteTemperatureVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AbsoluteTemperature instantiateScalarSI(final double valueSI, final AbsoluteTemperatureUnit displayUnit)
    {
        AbsoluteTemperature result = AbsoluteTemperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public TemperatureVector instantiateVectorRel(final DoubleVectorData dvd, final TemperatureUnit displayUnit)
    {
        return new TemperatureVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Temperature instantiateScalarRelSI(final double valueSI, final TemperatureUnit displayUnit)
    {
        Temperature result = Temperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
