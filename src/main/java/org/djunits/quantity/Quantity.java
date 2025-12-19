package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.si.SIDimensions;

/**
 * Quantity is an abstract class that stores the basic information about a quantity. A physical quantity can be expressed as a
 * value, which is the combination of a numerical value and a unit of measurement. The type of physical quantity is encoded in
 * the class (Length, Speed, Area, etc.) with its associated (base) unit of measurement, whereas the numerical value is stored
 * in the si field. Additionally, each quantity has a displayUnit that gives the preference for the (scaled) display of the
 * quantity, e.g., in a toString() method.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public abstract class Quantity<Q extends Quantity<Q, U>, U extends AbstractUnit<U>> extends Number
{
    /** */
    private static final long serialVersionUID = 500L;

    /** The si value. */
    private final double si;

    /** The display unit. */
    private U displayUnit;

    /**
     * Instantiate a quantity with an si value and a display unit.
     * @param value the value expressed in the display unit
     * @param displayUnit the display unit to use
     */
    public Quantity(final double value, final U displayUnit)
    {
        this.si = displayUnit.toBaseValue(value);
        this.displayUnit = displayUnit;
    }

    /**
     * Return the SI dimensions of this quantity.
     * @return the SI dimensions of this quantity
     */
    public abstract SIDimensions siDimensions();

    /**
     * Return the SI value of the quantity.
     * @return the SI value of the quantity
     */
    public double si()
    {
        return this.si;
    }

    @Override
    public double doubleValue()
    {
        return si();
    }

    @Override
    public int intValue()
    {
        return (int) Math.round(si());
    }

    @Override
    public long longValue()
    {
        return Math.round(si());
    }

    @Override
    public float floatValue()
    {
        return (float) si();
    }

    /**
     * Return the display unit of this quantity.
     * @return the display unit of this quantity
     */
    public U getDisplayUnit()
    {
        return this.displayUnit;
    }

    /**
     * Set a new display unit of this quantity.
     * @param displayUnit the new display unit of this quantity
     */
    public void setDisplayUnit(final U displayUnit)
    {
        this.displayUnit = displayUnit;
    }

    @Override
    public String toString()
    {
        return getDisplayUnit().fromBaseValue(this.si) + " " + getDisplayUnit().getDisplayAbbreviation();
    }

}
