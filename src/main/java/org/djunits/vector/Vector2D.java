package org.djunits.vector;

import org.djunits.old.unit.si.SIDimensions;
import org.djunits.quantity.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.value.Value;

/**
 * Vector2D implements a vector in the 2-dimensional plane. It has two real-valued entries. The vector is immutable. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public abstract class Vector2D<Q extends Quantity<Q, U>, U extends AbstractUnit<U, Q>> implements Value<U>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The si value. */
    private final double[] si;

    /** The display unit. */
    private U displayUnit;

    /**
     * @param value the value expressed in the display unit
     * @param displayUnit the display unit to use
     */
    public Vector2D(final double[] value, final U displayUnit)
    {
        this.si = new double[value.length];
        for (int i = 0; i < value.length; i++)
        {
            this.si[i] = displayUnit.toBaseValue(value[i]);
        }
        this.displayUnit = displayUnit;
    }

    /**
     * Return the display unit of this quantity.
     * @return the display unit of this quantity
     */
    @Override
    public U getDisplayUnit()
    {
        return this.displayUnit;
    }

    /**
     * Set a new display unit of this quantity.
     * @param displayUnit the new display unit of this quantity
     */
    @Override
    public void setDisplayUnit(final U displayUnit)
    {
        this.displayUnit = displayUnit;
    }

    /**
     * Return the SI dimensions of this quantity.
     * @return the SI dimensions of this quantity
     */
    public abstract SIDimensions siDimensions();

    /**
     * Return the value of an element of the vector.
     * @param index the index to retrieve the element for
     * @return the value of the element of the vector at the index
     */
    public Q get(final int index)
    {
        return instantiateScalarSi(si(index), getDisplayUnit());
    }

    /**
     * Return the SI value of an element of the vector.
     * @param index the index to retrieve the element for
     * @return the SI value of the element of the vector at the index
     */
    public double si(final int index)
    {
        return this.si[index];
    }

    /**
     * Instantiate a scalar of the correct type.
     * @param si the si value of the scalar to instantiate
     * @param displayUnit the display unit to use
     * @return the instantiated scalar
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public abstract Q instantiateScalarSi(double si, U displayUnit);

    @Override
    public String toString()
    {
        var s = new StringBuilder();
        for (int i = 0; i < this.si.length; i++)
        {
            if (!s.isEmpty())
            {
                s.append(' ');
            }
            s.append(getDisplayUnit().fromBaseValue(si(i)));
        }
        return "[" + s.toString() + "] " + getDisplayUnit().getId();
    }

}
