package org.djunits.unit;

import java.util.List;

import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * UnitInterface defines the contract for a unit.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <U> the unit type
 */

public interface UnitInterface<U extends UnitInterface<U>>
{
    /**
     * Return the id, which is the main abbreviation, of the unit.
     * @return the id (main abbreviation) of the unit
     */
    String getId();

    /**
     * Return the name, which is the main written explanation, of the unit.
     * @return the name (main explanation) of the unit
     */
    String getName();

    /**
     * Retrieve the scale of this unit.
     * @return the scale of this unit
     */
    Scale getScale();

    /**
     * Retrieve the unit system of this unit.
     * @return unitSystem the unit system of this unit
     */
    UnitSystem getUnitSystem();

    /**
     * Return the SI unit for this unit.
     * @return the SI unit for this unit
     */
    SIUnit siUnit();

    /**
     * Convert a value expressed in this unit to its base (SI) value.
     * @param value the value expressed in this unit
     * @return the value converted to its SI value
     */
    default double toBaseValue(final double value)
    {
        return getScale().toBaseValue(value);
    }

    /**
     * Convert an SI value to a value expressed in this unit.
     * @param si the SI value
     * @return the value converted to this unit
     */
    default double fromBaseValue(final double si)
    {
        return getScale().fromBaseValue(si);
    }

    /**
     * Return the base unit for this unit.
     * @return the base unit for this unit
     */
    U getBaseUnit();

    /**
     * Retrieve a safe copy of the textual abbreviations.
     * @return the textual abbreviations
     */
    List<String> getTextualAbbreviations();

    /**
     * Retrieve the default display abbreviation.
     * @return the default display abbreviation
     */
    String getDisplayAbbreviation();

    /**
     * Retrieve the default textual abbreviation.
     * @return the default textual abbreviation
     */
    default String getDefaultTextualAbbreviation()
    {
        return getTextualAbbreviations().get(0);
    }

}
