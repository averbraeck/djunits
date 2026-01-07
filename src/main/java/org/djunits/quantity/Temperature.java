package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.OffsetLinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Temperature is a measure of thermal state or average kinetic energy of particles, measured in kelvins (K). Note that the
 * Temperature quantity is relative (it measures a difference between temperatures), whereas the AbsoluteTemperature quantity is
 * absolute.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Temperature extends Quantity<Temperature, Temperature.Unit>
{
    /** Constant with value zero. */
    public static final Temperature ZERO = Temperature.ofSi(0.0);

    /** Constant with value one. */
    public static final Temperature ONE = Temperature.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Temperature NaN = Temperature.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Temperature POSITIVE_INFINITY = Temperature.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Temperature NEGATIVE_INFINITY = Temperature.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Temperature POS_MAXVALUE = Temperature.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Temperature NEG_MAXVALUE = Temperature.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Temperature quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Temperature(final double value, final Temperature.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Temperature quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Temperature(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Temperature.Unit.class, abbreviation));
    }

    /**
     * Construct Temperature quantity.
     * @param value Scalar from which to construct this instance
     */
    public Temperature(final Temperature value)
    {
        super(value.si(), Temperature.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Temperature instance based on an SI value.
     * @param si the si value
     * @return the Temperature instance based on an SI value
     */
    public static Temperature ofSi(final double si)
    {
        return new Temperature(si, Temperature.Unit.SI);
    }

    @Override
    public Temperature instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Temperature.Unit.SI_UNIT;
    }

    /**
     * Returns a Temperature representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Temperature
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Temperature valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Temperature based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Temperature of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Temperature and Temperature, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Temperature and Temperature
     */
    public final Dimensionless divide(final Temperature v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Add an absolute temperature to this temperature (difference), and return an absolute temperature. The unit of the return
     * value will be the unit of this (relative) temperature.
     * @param absoluteTemperature the absolute temperature to add
     * @return the absolute temperature plus this temperature difference
     */
    public final AbsoluteTemperature add(final AbsoluteTemperature absoluteTemperature)
    {
        var abstemp = AbsoluteTemperature.ofSi(absoluteTemperature.si() + si());
        abstemp.setDisplayUnit(getDisplayUnit());
        return abstemp;
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Temperature.Unit encodes the units of (relative) temperature.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Temperature.Unit, Temperature>
    {
        /** The dimensions of temperature: K. */
        public static final SIUnit SI_UNIT = SIUnit.of("K");

        /** Kelvin. */
        public static final Temperature.Unit KELVIN = new Temperature.Unit("K", "kelvin", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final Temperature.Unit SI = KELVIN.generateSiPrefixes(false, false);

        /** Degree Celsius. */
        public static final Temperature.Unit DEGREE_CELSIUS = new Temperature.Unit("degC", "\u00B0C", "degree Celsius",
                new OffsetLinearScale(1.0, 273.15), UnitSystem.SI_DERIVED);

        /** Degree Fahrenheit. */
        public static final Temperature.Unit DEGREE_FAHRENHEIT = new Temperature.Unit("degF", "\u00B0F", "degree Fahrenheit",
                new OffsetLinearScale(5.0 / 9.0, 459.67), UnitSystem.OTHER);

        /** Degree Rankine. */
        public static final Temperature.Unit DEGREE_RANKINE = new Temperature.Unit("degR", "\u00B0R", "degree Rankine",
                new OffsetLinearScale(5.0 / 9.0, 0.0), UnitSystem.OTHER);

        /** Degree Reaumur. */
        public static final Temperature.Unit DEGREE_REAUMUR = new Temperature.Unit("degRe", "\u00B0R\u00E9", "degree Reaumur",
                new OffsetLinearScale(4.0 / 5.0, 273.15), UnitSystem.OTHER);

        /**
         * Create a new Temperature unit.
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
         * @param textualAbbreviation the textual abbreviation of the unit, which doubles as the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         */
        public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name, final Scale scale,
                final UnitSystem unitSystem)
        {
            super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem);
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
        public Temperature ofSi(final double si)
        {
            return Temperature.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new Temperature.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
