package org.djunits.quantity;

import java.util.List;
import java.util.Locale;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

/**
 * Dimensionless quantity.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Dimensionless extends Quantity.Relative<Dimensionless, Dimensionless.Unit>
{
    /** Constant with value zero. */
    public static final Dimensionless ZERO = Dimensionless.ofSi(0.0);

    /** Constant with value one. */
    public static final Dimensionless ONE = Dimensionless.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Dimensionless NaN = Dimensionless.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Dimensionless POSITIVE_INFINITY = Dimensionless.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Dimensionless NEGATIVE_INFINITY = Dimensionless.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Dimensionless POS_MAXVALUE = Dimensionless.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Dimensionless NEG_MAXVALUE = Dimensionless.ofSi(-Double.MAX_VALUE);
    
    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Dimensionless quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Dimensionless(final double value, final Dimensionless.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Dimensionless quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Dimensionless(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Dimensionless.Unit.class, abbreviation));
    }

    /**
     * Construct Dimensionless quantity.
     * @param value Scalar from which to construct this instance
     */
    public Dimensionless(final Dimensionless value)
    {
        super(value.si(), Dimensionless.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Dimensionless instance based on an SI value.
     * @param si the si value
     * @return the Dimensionless instance based on an SI value
     */
    public static Dimensionless ofSi(final double si)
    {
        return new Dimensionless(si, Dimensionless.Unit.SI);
    }

    @Override
    public Dimensionless instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Dimensionless.Unit.SI_UNIT;
    }

    /**
     * Returns a Dimensionless representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Dimensionless
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Dimensionless valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Dimensionless: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Dimensionless: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            Dimensionless.Unit unit = Units.resolve(Dimensionless.Unit.class, unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Dimensionless", unitString);
            return new Dimensionless(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Dimensionless from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Dimensionless based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Dimensionless of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Dimensionless: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Dimensionless: empty unitString");
        Dimensionless.Unit unit = Units.resolve(Dimensionless.Unit.class, unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Dimensionless with unit %s", unitString);
        return new Dimensionless(value, unit);
    }

    /**
     * Calculate the division of Dimensionless and Dimensionless, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Dimensionless and Dimensionless
     */
    public final Dimensionless divide(final Dimensionless v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Dimensionless.Unit encodes the units of absorbed dose (of ionizing radiation).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Dimensionless.Unit>
    {
        /** The dimensions of the dimensionless quantity: 1 [rad, sr, kg, m, s, A, K, mol, cd]. */
        public static final SIUnit SI_UNIT = new SIUnit(new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0});

        /** The SI or BASE unit. */
        public static final Dimensionless.Unit SI = Units.one;
        
        /**
         * Create a new Dimensionless unit.
         * @param id the id or main abbreviation of the unit
         * @param name the full name of the unit
         * @param scaleFactorToBaseUnit the scale factor of the unit to convert it TO the base (SI) unit
         * @param unitSystem the unit system such as SI or IMPERIAL
         */
        public Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
        {
            super(id, name, new LinearScale(scaleFactorToBaseUnit), unitSystem);
        }

        /**
         * Return a derived unit for this unit, with textual abbreviation(s) and a display abbreviation.
         * @param textualAbbreviations the textual abbreviations of the unit, where the first one in the list is the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         */
        public Unit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
        {
            super(textualAbbreviations, displayAbbreviation, name, scale, unitSystem);
        }

        @Override
        public SIUnit siUnit()
        {
            return SI_UNIT;
        }

        @Override
        public Unit getBaseUnit()
        {
            return Units.one;
        }

        @Override
        public Unit deriveUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
        {
            return new Dimensionless.Unit(textualAbbreviations, displayAbbreviation, name, scale, unitSystem);
        }

    }
}
