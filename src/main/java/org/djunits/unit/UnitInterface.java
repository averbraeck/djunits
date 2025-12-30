package org.djunits.unit;

import org.djunits.quantity.Quantity;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
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
 * @param <Q> the quantity type
 */
public interface UnitInterface<U extends UnitInterface<U, Q>, Q extends Quantity<Q, U>>
{
    /**
     * Return the id, which is the main abbreviation, of the unit.
     * @return the id (main abbreviation) of the unit
     */
    String getId();

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
     * Retrieve the display abbreviation, and apply localization when possible.
     * @return the (localized) display abbreviation
     */
    String getDisplayAbbreviation();

    /**
     * Retrieve the textual abbreviation, which doubles as the id of the unit. Apply localization when possible.
     * @return the (localized) textual abbreviation
     */
    String getTextualAbbreviation();

    /**
     * Return the name, which is the main written explanation, of the unit. Apply localization when possible.
     * @return the (localized) name of the unit
     */
    String getName();

    /**
     * Retrieve the stored display abbreviation, without localization.
     * @return the stored (non-localized) display abbreviation
     */
    String getStoredDisplayAbbreviation();

    /**
     * Retrieve the stored textual abbreviation, which doubles as the id of the unit. Do not apply localization.
     * @return the stored (non-localized) textual abbreviation
     */
    String getStoredTextualAbbreviation();

    /**
     * Return the name, which is the main written explanation, of the unit. Do not apply localization.
     * @return the stored (non-localized) name of the unit
     */
    String getStoredName();

    /**
     * Set the SI-prefix so it can be localized if necessary.
     * @param siPrefix the SI-prefix to set
     * @return the unit for method chaining
     */
    U setSiPrefix(SIPrefix siPrefix);

    /**
     * Set the SI-prefix so it can be localized if necessary. This method does NOT handle kilo-prefixes.
     * @param prefix the string-representation of the SI-prefix to set
     * @return the unit for method chaining
     */
    U setSiPrefix(String prefix);

    /**
     * Set the SI-prefix so it can be localized if necessary. This method handles kilo-prefixes.
     * @param prefix the string-representation of the SI-prefix to set
     * @return the unit for method chaining
     */
    U setSiPrefixKilo(String prefix);

    /**
     * Set the SI-prefix so it can be localized if necessary. This method handles per-unit prefixes.
     * @param prefix the string-representation of the SI-prefix to set
     * @return the unit for method chaining
     */
    U setSiPrefixPer(String prefix);

    /**
     * Return the SI-prefix so it can be localized if necessary.
     * @return the SI-prefix of this unit, or null when the unit has no SI-prefix
     */
    SIPrefix getSiPrefix();

    /**
     * Return an SI-quantity for this unit with a value.
     * @param si the value in SI or BASE units
     * @return an SI-quantity for this unit with the given si-value
     */
    Q ofSi(double si);

    /**
     * Return a quantity for this unit where the value is expressed in the current unit. When the unit is, e.g., kilometer, and
     * the value is 10.0, the quantity returned will be 10 km, internally stored as 10,000 m with a displayUnit in km.
     * @param value the value in the current unit
     * @return a quantity with the value in the current unit
     */
    @SuppressWarnings("unchecked")
    default Q quantityInUnit(final double value)
    {
        Q quantity = ofSi(getScale().toBaseValue(value));
        quantity.setDisplayUnit((U) this);
        return quantity;
    }
}
