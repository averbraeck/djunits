package org.djunits.userdefined;

import org.djunits.value.vfloat.scalar.FloatDimensionless;
import org.djunits.value.vfloat.scalar.FloatSIScalar;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * JerkScalar as a test of a user-defined scalar.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class FloatJerk extends AbstractFloatScalarRel<JerkUnit, FloatJerk>
{
    /** */
    private static final long serialVersionUID = 1L;

    /**
     * @param value the value expressed in the unit
     * @param unit the unit to use
     */
    public FloatJerk(final float value, final JerkUnit unit)
    {
        super(value, unit);
    }

    /**
     * @param value the value to instantiate / duplicate
     */
    public FloatJerk(final FloatJerk value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public FloatJerk instantiateRel(final float value, final JerkUnit unit)
    {
        return new FloatJerk(value, unit);
    }

    /**
     * Instantiate a new FloatJerk scalar with an SI value.
     * @param valueSI the SI value of the new Jerk
     * @return a new FloatJerk scalar
     */
    public static FloatJerk instantiateSI(final float valueSI)
    {
        return new FloatJerk(valueSI, JerkUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }
}
