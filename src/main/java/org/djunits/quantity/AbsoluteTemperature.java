package org.djunits.quantity;

import java.util.List;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.OffsetLinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.djutils.exceptions.Throw;

/**
 * The absolute temperature in Kelvin.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AbsoluteTemperature extends Quantity.Absolute<AbsoluteTemperature, AbsoluteTemperature.Unit>
{
    /** Constant with value zero. */
    public static final AbsoluteTemperature ZERO = AbsoluteTemperature.ofSi(0.0);

    /** Constant with value one. */
    public static final AbsoluteTemperature ONE = AbsoluteTemperature.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final AbsoluteTemperature NaN = AbsoluteTemperature.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final AbsoluteTemperature POSITIVE_INFINITY = AbsoluteTemperature.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final AbsoluteTemperature POS_MAXVALUE = AbsoluteTemperature.ofSi(Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a AbsoluteTemperature quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     * @throws IllegalStateException when the temperature is below absolute zero
     */
    public AbsoluteTemperature(final double value, final AbsoluteTemperature.Unit unit)
    {
        super(value, unit);
        Throw.when(si() < 0.0, IllegalStateException.class, "Absolute temperature is below absolute zero");
    }

    /**
     * Instantiate a AbsoluteTemperature quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public AbsoluteTemperature(final double value, final String abbreviation)
    {
        this(value, Units.resolve(AbsoluteTemperature.Unit.class, abbreviation));
    }

    /**
     * Construct AbsoluteTemperature quantity.
     * @param value Scalar from which to construct this instance
     */
    public AbsoluteTemperature(final AbsoluteTemperature value)
    {
        super(value.si(), AbsoluteTemperature.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a AbsoluteTemperature instance based on an SI value.
     * @param si the si value
     * @return the AbsoluteTemperature instance based on an SI value
     */
    public static AbsoluteTemperature ofSi(final double si)
    {
        return new AbsoluteTemperature(si, AbsoluteTemperature.Unit.SI);
    }

    @Override
    public AbsoluteTemperature instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return AbsoluteTemperature.Unit.SI_UNIT;
    }

    /**
     * Returns a AbsoluteTemperature representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a AbsoluteTemperature
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static AbsoluteTemperature valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a AbsoluteTemperature based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AbsoluteTemperature of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * AbsoluteTemperature.Unit encodes the units of absolute temperature (in Kelvin).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<AbsoluteTemperature.Unit>
    {
        /** The dimensions of AbsoluteTemperature: K. */
        public static final SIUnit SI_UNIT = SIUnit.of("K");

        /** The default unit for temperature is Kelvin. */
        public static final AbsoluteTemperature.Unit KELVIN =
                new AbsoluteTemperature.Unit(List.of("K", "kelvin"), "K", "kelvin", IdentityScale.SCALE, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final AbsoluteTemperature.Unit SI = KELVIN.generateSiPrefixes(false, false);

        /** Degree Celsius. */
        public static final AbsoluteTemperature.Unit DEGREE_CELSIUS = new AbsoluteTemperature.Unit(List.of("dgC", "degC", "C"),
                "\u00B0C", "degree Celsius", new OffsetLinearScale(1.0, 273.15), UnitSystem.SI_DERIVED);

        /** Degree Fahrenheit. */
        public static final AbsoluteTemperature.Unit DEGREE_FAHRENHEIT =
                new AbsoluteTemperature.Unit(List.of("dgF", "degF", "F"), "\u00B0F", "degree Fahrenheit",
                        new OffsetLinearScale(5.0 / 9.0, 459.67), UnitSystem.OTHER);

        /** Degree Rankine. */
        public static final AbsoluteTemperature.Unit DEGREE_RANKINE = new AbsoluteTemperature.Unit(List.of("dgR", "degR", "R"),
                "\u00B0R", "degree Rankine", new OffsetLinearScale(5.0 / 9.0, 0.0), UnitSystem.OTHER);

        /** Degree Reaumur. */
        public static final AbsoluteTemperature.Unit DEGREE_REAUMUR =
                new AbsoluteTemperature.Unit(List.of("dgRe", "degRe", "Re"), "\u00B0R\u00E9", "degree Reaumur",
                        new OffsetLinearScale(4.0 / 5.0, 273.15), UnitSystem.OTHER);

        /**
         * Create a new AbsoluteTemperature unit.
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
                return new AbsoluteTemperature.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
