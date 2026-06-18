package org.djunits.quantity;

import java.util.Locale;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

/**
 * SI quantity, with arbitrary SI unit. The class has <code>as...</code> methods such as <code>asDuration(...)</code>, which
 * will succeed if the SI unit of the quantity is a duration unit, i.e., 's'.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class SIQuantity extends Quantity<SIQuantity>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a SIQuantity quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public SIQuantity(final double value, final SIUnit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a SIQuantity quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public SIQuantity(final double valueInUnit, final SIUnit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a SIQuantity instance based on an SI value.
     * @param si the si value
     * @return the SIQuantity instance based on an SI value
     */
    public static SIQuantity ofSi(final double si)
    {
        return new SIQuantity(si, SIUnit.DIMLESS, true);
    }

    /**
     * Instantiate a SIQuantity quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the SIQuantity instance based on an SI value with the given display unit
     */
    public static SIQuantity ofSi(final double siValue, final SIUnit displayUnit)
    {
        return new SIQuantity(siValue, displayUnit, true);
    }

    @Override
    public SIQuantity instantiateSi(final double siValue, final UnitInterface<SIQuantity> displayUnit)
    {
        return new SIQuantity(siValue, (SIUnit) displayUnit, true);
    }

    /**
     * Returns a SIQuantity based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab SIQuantity representation of the value in its unit
     */
    public static SIQuantity of(final double valueInUnit, final SIUnit unit)
    {
        return new SIQuantity(valueInUnit, unit);
    }

    @Override
    public SIUnit getDisplayUnit()
    {
        return (SIUnit) super.getDisplayUnit();
    }

    /**
     * Returns an SI quantity representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a SI
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static SIQuantity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing SI quantity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing SI quantity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            SIUnit unit = SIUnit.of(unitString);
            return new SIQuantity(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing SI quantity from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns an SI quantity based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIQuantity of(final double valueInUnit, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing SI quantity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing SI quantity: empty unitString");
        SIUnit unit = SIUnit.of(unitString);
        return new SIQuantity(valueInUnit, unit);
    }

}
