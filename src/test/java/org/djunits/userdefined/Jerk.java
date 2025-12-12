package org.djunits.userdefined;

import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;

/**
 * JerkScalar as a test of a user-defined scalar.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 */
public class Jerk extends DoubleScalarRel<JerkUnit, Jerk>
{
    /** */
    private static final long serialVersionUID = 1L;

    /**
     * @param value the value expressed in the unit
     * @param unit the unit to use
     */
    public Jerk(final double value, final JerkUnit unit)
    {
        super(value, unit);
    }

    /**
     * @param value the value to instantiate / duplicate
     */
    public Jerk(final Jerk value)
    {
        super(value);
    }

    @Override
    public Jerk instantiateRel(final double value, final JerkUnit unit)
    {
        return new Jerk(value, unit);
    }

    /**
     * Instantiate a new Jerk scalar with an SI value.
     * @param valueSI the SI value of the new Jerk
     * @return a new Jerk scalar
     */
    public static Jerk ofSI(final double valueSI)
    {
        return new Jerk(valueSI, JerkUnit.SI);
    }

}
