package org.djunits.value;

import java.io.Serializable;

import org.djunits.Throw;
import org.djunits.unit.Unit;

/**
 * AbstractValue is a class to help construct Scalar, Vector, and Matrix classes.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the Unit of the value(s) in this AbstractValue. Used for setting, getting and displaying the value(s)
 * @param <T> the value type for this unit
 */
public abstract class AbstractValue<U extends Unit<U>, T extends Value<U, T>> implements Value<U, T>, Serializable
{
    /**  */
    private static final long serialVersionUID = 20150626L;

    /** The display unit of this AbstractValue. */
    private U displayUnit;

    /**
     * Construct a new AbstractValue.
     * @param displayUnit U; the unit of the new AbstractValue
     */
    protected AbstractValue(final U displayUnit)
    {
        Throw.whenNull(displayUnit, "display unit cannot be null");
        this.displayUnit = displayUnit;
    }

    /** {@inheritDoc} */
    @Override
    public final U getDisplayUnit()
    {
        return this.displayUnit;
    }

    /** {@inheritDoc} */
    @Override
    public void setDisplayUnit(final U newUnit)
    {
        Throw.whenNull(newUnit, "newUnit may not be null");
        this.displayUnit = newUnit;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isAbsolute()
    {
        return this instanceof Absolute;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isRelative()
    {
        return this instanceof Relative;
    }

    // No hashcode or equals -- has to be implemented on a deeper level

}
