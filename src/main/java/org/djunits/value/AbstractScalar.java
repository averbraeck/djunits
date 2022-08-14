package org.djunits.value;

import java.io.Serializable;

import org.djunits.Throw;
import org.djunits.unit.Unit;
import org.djunits.value.base.Scalar;

/**
 * AbstractScalar is a class to help construct Scalar classes. In contrast with AbstractScalar, it extends Number.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the Unit of the value(s) in this AbstractScalar. Used for setting, getting and displaying the value(s)
 * @param <S> the scalar type for this unit
 */
public abstract class AbstractScalar<U extends Unit<U>, S extends Scalar<U, S>> extends Number
        implements Scalar<U, S>, Serializable
{
    /**  */
    private static final long serialVersionUID = 20150626L;

    /** The display unit of this AbstractScalar. */
    private U displayUnit;

    /**
     * Construct a new AbstractScalar.
     * @param displayUnit U; the unit of the new AbstractScalar
     */
    protected AbstractScalar(final U displayUnit)
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
