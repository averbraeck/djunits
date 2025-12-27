package org.djunits.quantity;

import java.util.List;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Mass is the amount of matter in an object, measured in kilograms (kg).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Mass extends Quantity.Relative<Mass, Mass.Unit>
{
    /** Constant with value zero. */
    public static final Mass ZERO = Mass.ofSi(0.0);

    /** Constant with value one. */
    public static final Mass ONE = Mass.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Mass NaN = Mass.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Mass POSITIVE_INFINITY = Mass.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Mass NEGATIVE_INFINITY = Mass.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Mass POS_MAXVALUE = Mass.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Mass NEG_MAXVALUE = Mass.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Mass quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Mass(final double value, final Mass.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Mass quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Mass(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Mass.Unit.class, abbreviation));
    }

    /**
     * Construct Mass quantity.
     * @param value Scalar from which to construct this instance
     */
    public Mass(final Mass value)
    {
        super(value.si(), Mass.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Mass instance based on an SI value.
     * @param si the si value
     * @return the Mass instance based on an SI value
     */
    public static Mass ofSi(final double si)
    {
        return new Mass(si, Mass.Unit.SI);
    }

    @Override
    public Mass instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Mass.Unit.SI_UNIT;
    }

    /**
     * Returns a Mass representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Mass
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Mass valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Mass based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Mass of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Mass and Mass, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Mass and Mass
     */
    public final Dimensionless divide(final Mass v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Mass.Unit encodes the unit of the amount of matter in an object.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Mass.Unit>
    {
        /** Constant for pound (lb). */
        public static final double CONST_LB = 0.45359237;
        
        /** Constant for ounce. */
        public static final double CONST_OUNCE = CONST_LB / 16.0;
        
        /** Constant for short ton. */
        public static final double CONST_TON_SHORT = 2000.0 * CONST_LB;
        
        /** Constant for long ton. */
        public static final double CONST_TON_LONG = 2240.0 * CONST_LB;

        /** The dimensions of mass: kg. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg");

        /** kilogram. */
        public static final Mass.Unit KILOGRAM = new Mass.Unit("kg", "kilogram", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final Mass.Unit SI = KILOGRAM.generateSiPrefixes(true, false);

        /**
         * Create a new Mass unit.
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
            return SI;
        }

        @Override
        public Unit deriveUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Mass.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
