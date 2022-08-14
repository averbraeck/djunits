package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorAbs;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double TimeVector, a vector of values with a TimeUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class TimeVector extends AbstractDoubleVectorAbs<TimeUnit, Time, TimeVector, DurationUnit, Duration, DurationVector>
{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an TimeVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param unit TimeUnit; the display unit of the vector data
     */
    public TimeVector(final DoubleVectorData data, final TimeUnit unit)
    {
        super(data, unit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Time> getScalarClass()
    {
        return Time.class;
    }

    /** {@inheritDoc} */
    @Override
    public TimeVector instantiateVector(final DoubleVectorData dvd, final TimeUnit displayUnit)
    {
        return new TimeVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Time instantiateScalarSI(final double valueSI, final TimeUnit displayUnit)
    {
        Time result = Time.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public DurationVector instantiateVectorRel(final DoubleVectorData dvd, final DurationUnit displayUnit)
    {
        return new DurationVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Duration instantiateScalarRelSI(final double valueSI, final DurationUnit displayUnit)
    {
        Duration result = Duration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
