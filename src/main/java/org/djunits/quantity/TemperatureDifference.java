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
public class TemperatureDifference extends Quantity<TemperatureDifference, TemperatureDifference.Unit>
{
    /** Constant with value zero. */
    public static final TemperatureDifference ZERO = TemperatureDifference.ofSi(0.0);

    /** Constant with value one. */
    public static final TemperatureDifference ONE = TemperatureDifference.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final TemperatureDifference NaN = TemperatureDifference.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final TemperatureDifference POSITIVE_INFINITY = TemperatureDifference.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final TemperatureDifference NEGATIVE_INFINITY = TemperatureDifference.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final TemperatureDifference POS_MAXVALUE = TemperatureDifference.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final TemperatureDifference NEG_MAXVALUE = TemperatureDifference.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Temperature quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public TemperatureDifference(final double value, final TemperatureDifference.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Temperature quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public TemperatureDifference(final double value, final String abbreviation)
    {
        this(value, Units.resolve(TemperatureDifference.Unit.class, abbreviation));
    }

    /**
     * Construct Temperature quantity.
     * @param value Scalar from which to construct this instance
     */
    public TemperatureDifference(final TemperatureDifference value)
    {
        super(value.si(), TemperatureDifference.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Temperature instance based on an SI value.
     * @param si the si value
     * @return the Temperature instance based on an SI value
     */
    public static TemperatureDifference ofSi(final double si)
    {
        return new TemperatureDifference(si, TemperatureDifference.Unit.SI);
    }

    @Override
    public TemperatureDifference instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return TemperatureDifference.Unit.SI_UNIT;
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
    public static TemperatureDifference valueOf(final String text)
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
    public static TemperatureDifference of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Temperature and Temperature, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Temperature and Temperature
     */
    public final Dimensionless divide(final TemperatureDifference v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Add an absolute temperature to this temperature (difference), and return an absolute temperature. The unit of the return
     * value will be the unit of this (relative) temperature.
     * @param absoluteTemperature the absolute temperature to add
     * @return the absolute temperature plus this temperature difference
     */
    public final Temperature add(final Temperature absoluteTemperature)
    {
        var abstemp = Temperature.ofSi(absoluteTemperature.si() + si());
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
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<TemperatureDifference.Unit, TemperatureDifference>
    {
        /** The dimensions of temperature: K. */
        public static final SIUnit SI_UNIT = SIUnit.of("K");

        /** Kelvin. */
        public static final TemperatureDifference.Unit K = new TemperatureDifference.Unit("K", "kelvin", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final TemperatureDifference.Unit SI = K.generateSiPrefixes(false, false);

        /** Degree Celsius. */
        public static final TemperatureDifference.Unit degC = new TemperatureDifference.Unit("degC", "\u00B0C", "degree Celsius",
                new OffsetLinearScale(1.0, 273.15), UnitSystem.SI_DERIVED);

        /** Degree Fahrenheit. */
        public static final TemperatureDifference.Unit degF = new TemperatureDifference.Unit("degF", "\u00B0F", "degree Fahrenheit",
                new OffsetLinearScale(5.0 / 9.0, 459.67), UnitSystem.OTHER);

        /** Degree Rankine. */
        public static final TemperatureDifference.Unit degR = new TemperatureDifference.Unit("degR", "\u00B0R", "degree Rankine",
                new OffsetLinearScale(5.0 / 9.0, 0.0), UnitSystem.OTHER);

        /** Degree Reaumur. */
        public static final TemperatureDifference.Unit degRe = new TemperatureDifference.Unit("degRe", "\u00B0R\u00E9", "degree Reaumur",
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
        public TemperatureDifference ofSi(final double si)
        {
            return TemperatureDifference.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new TemperatureDifference.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
