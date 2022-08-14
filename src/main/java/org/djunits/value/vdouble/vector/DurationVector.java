package org.djunits.value.vdouble.vector;

import javax.annotation.Generated;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVectorRelWithAbs;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Double DurationVector, a vector of values with a DurationUnit.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class DurationVector
        extends AbstractDoubleVectorRelWithAbs<TimeUnit, Time, TimeVector, DurationUnit, Duration, DurationVector>
{
    /** */
    private static final long serialVersionUID = 20190905L;

    /**
     * Construct an DurationVector from an internal data object.
     * @param data DoubleVectorData; the internal data object for the vector data
     * @param displayUnit DurationUnit; the display unit of the vector data
     */
    public DurationVector(final DoubleVectorData data, final DurationUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Duration> getScalarClass()
    {
        return Duration.class;
    }

    /** {@inheritDoc} */
    @Override
    public DurationVector instantiateVector(final DoubleVectorData dvd, final DurationUnit displayUnit)
    {
        return new DurationVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Duration instantiateScalarSI(final double valueSI, final DurationUnit displayUnit)
    {
        Duration result = Duration.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public TimeVector instantiateVectorAbs(final DoubleVectorData dvd, final TimeUnit displayUnit)
    {
        return new TimeVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Time instantiateScalarAbsSI(final double valueSI, final TimeUnit displayUnit)
    {
        Time result = Time.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
