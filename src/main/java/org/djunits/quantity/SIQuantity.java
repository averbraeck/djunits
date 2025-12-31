package org.djunits.quantity;

import java.util.Locale;

import org.djunits.unit.Units;
import org.djunits.unit.si.SIUnit;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

/**
 * SI quantity, with arbitrary SI unit. The class has <code>as...</code> methods such as <code>asDuration(...)</code>, which
 * will succeed if the SI unit of the quantity is a duration unit, i.e., 's'.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class SIQuantity extends Quantity.Relative<SIQuantity, SIUnit>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a SI quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public SIQuantity(final double value, final SIUnit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a SI quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public SIQuantity(final double value, final String abbreviation)
    {
        this(value, Units.resolve(SIUnit.class, abbreviation));
    }

    /**
     * Construct SI quantity.
     * @param value Scalar from which to construct this instance
     */
    public SIQuantity(final SIQuantity value)
    {
        super(value.si(), value.getDisplayUnit());
    }

    @Override
    public SIQuantity instantiate(final double si)
    {
        return new SIQuantity(si, getDisplayUnit());
    }

    @Override
    public SIUnit siUnit()
    {
        return getDisplayUnit();
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
            SIUnit unit = Units.resolve(SIUnit.class, unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity SI", unitString);
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
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIQuantity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing SI quantity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing SI quantity: empty unitString");
        SIUnit unit = Units.resolve(SIUnit.class, unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing SI quantity with unit %s", unitString);
        return new SIQuantity(value, unit);
    }

}
