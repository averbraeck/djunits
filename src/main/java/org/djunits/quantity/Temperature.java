package org.djunits.quantity;

import org.djunits.quantity.def.AbsoluteQuantity;
import org.djunits.quantity.def.AbstractReference;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Temperature is the absolute equivalent of Temperature, and represents a true temperature rather than a temperature
 * difference.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Temperature extends AbsoluteQuantity<Temperature, TemperatureDifference, Temperature.Unit, Temperature.Reference>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Temperature quantity with a unit and a reference point.
     * @param value the temperature value, expressed in a temperature unit
     * @param unit the temperature unit in which the value is expressed, relative to the reference point
     * @param reference the reference point of this absolute temperature
     */
    public Temperature(final double value, final Temperature.Unit unit, final Reference reference)
    {
        super(new TemperatureDifference(value, unit), reference);
    }

    /**
     * Instantiate a Temperature quantity with a unit and the KELVIN reference point.
     * @param value the temperature value, expressed in a temperature unit
     * @param unit the temperature unit in which the value is expressed, relative to the reference point
     */
    public Temperature(final double value, final Temperature.Unit unit)
    {
        this(value, unit, unit.getReference());
    }

    /**
     * Instantiate a Temperature quantity with a unit, expressed as a String, and a reference point.
     * @param value the temperature value, expressed in the unit, relative to the reference point
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     * @param reference the reference point of this absolute temperature
     */
    public Temperature(final double value, final String abbreviation, final Reference reference)
    {
        this(value, Units.resolve(Temperature.Unit.class, abbreviation), reference);
    }

    /**
     * Instantiate a Temperature quantity with a unit, expressed as a String, and the reference point belonging to the unit.
     * @param value the temperature value, expressed in the unit, relative to the reference point
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Temperature(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Temperature.Unit.class, abbreviation),
                Units.resolve(Temperature.Unit.class, abbreviation).getReference());
    }

    /**
     * Instantiate a Temperature instance based on an temperature and a reference point.
     * @param temperature the temperature, relative to the reference point
     * @param reference the reference point of this absolute temperature
     */
    public Temperature(final TemperatureDifference temperature, final Reference reference)
    {
        super(temperature, reference);
    }

    /**
     * Instantiate a Temperature instance based on an temperature and the KELVIN reference point.
     * @param temperature the temperature, relative to the reference point
     */
    public Temperature(final TemperatureDifference temperature)
    {
        this(temperature, Reference.KELVIN);
    }

    /**
     * Return a Temperature instance based on an SI value and a reference point.
     * @param si the temperature si value, relative to the reference point
     * @param reference the reference point of this absolute temperature
     * @return the Temperature instance based on an SI value
     */
    public static Temperature ofSi(final double si, final Reference reference)
    {
        return new Temperature(si, Temperature.Unit.SI, reference);
    }

    /**
     * Return a Temperature instance based on an SI value and the KELVIN reference point.
     * @param si the temperature si value, relative to the reference point
     * @return the Temperature instance based on an SI value
     */
    public static Temperature ofSi(final double si)
    {
        return new Temperature(si, Temperature.Unit.SI, Reference.KELVIN);
    }

    @Override
    public Temperature instantiate(final TemperatureDifference temperature, final Reference reference)
    {
        return new Temperature(temperature, reference);
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
     * @param reference the reference point of this absolute temperature
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Temperature valueOf(final String text, final Reference reference)
    {
        return new Temperature(Quantity.valueOf(text, TemperatureDifference.ZERO), reference);
    }

    /**
     * Returns a Temperature representation of a textual representation of a value with a unit, and the KELVIN reference. The
     * String representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation
     * of the unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Temperature
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Temperature valueOf(final String text)
    {
        return new Temperature(Quantity.valueOf(text, TemperatureDifference.ZERO), Reference.KELVIN);
    }

    /**
     * Returns a Temperature based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @param reference the reference point of this absolute temperature
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Temperature of(final double value, final String unitString, final Reference reference)
    {
        return new Temperature(Quantity.of(value, unitString, TemperatureDifference.ZERO), reference);
    }

    /**
     * Returns a Temperature based on a value and the textual representation of the unit, which can be localized. Use the KELVIN
     * reference.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Temperature of(final double value, final String unitString)
    {
        return new Temperature(Quantity.of(value, unitString, TemperatureDifference.ZERO), Reference.KELVIN);
    }

    @Override
    public TemperatureDifference subtract(final Temperature other)
    {
        var otherRef = other.relativeTo(getReference());
        return TemperatureDifference.ofSi(si() - otherRef.si()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Temperature add(final TemperatureDifference other)
    {
        return new Temperature(TemperatureDifference.ofSi(si() + other.si()).setDisplayUnit(getDisplayUnit()), getReference());
    }

    @Override
    public Temperature subtract(final TemperatureDifference other)
    {
        return new Temperature(TemperatureDifference.ofSi(si() - other.si()).setDisplayUnit(getDisplayUnit()), getReference());
    }

    /**
     * The reference class to define a reference point for the absolute temperature.
     */
    public static final class Reference extends AbstractReference<Reference, TemperatureDifference>
    {
        /** Kelvin. */
        public static final Reference KELVIN =
                new Reference("KELVIN", "Kelvin scale temperature", TemperatureDifference.ZERO, null);

        /** Celsius. */
        public static final Reference CELSIUS =
                new Reference("CELSIUS", "Celsius scale temperature", TemperatureDifference.ofSi(273.15), KELVIN);

        /** Fahrenheit. */
        public static final Reference FAHRENHEIT = new Reference("FAHRENHEIT", "Fahrenheit scale temperature",
                TemperatureDifference.ofSi(273.15 - 32 / 1.8), KELVIN);

        /**
         * Define a new reference point for an absolute temperature.
         * @param id the id
         * @param name the name or explanation
         * @param offset the offset w.r.t. offsetReference
         * @param offsetReference the reference to which the offset is relative
         */
        public Reference(final String id, final String name, final TemperatureDifference offset,
                final Reference offsetReference)
        {
            super(id, name, offset, offsetReference);
        }

        /**
         * Define a new reference point for the absolute temperature, with an offset to 0 kelvin.
         * @param id the id
         * @param name the name or explanation
         * @param offset the offset w.r.t. offsetReference
         */
        public Reference(final String id, final String name, final TemperatureDifference offset)
        {
            super(id, name, offset, KELVIN);
        }

        /**
         * Define a new reference point for the absolute temperature, with an offset to 0 kelvin.
         * @param id the id
         * @param name the name or explanation
         * @param offset the offset of this scale relative to 0 kelvin
         */
        public static void add(final String id, final String name, final TemperatureDifference offset)
        {
            new Reference(id, name, offset);
        }

        /**
         * Get a reference point for the absolute temperature, based on its id. Return null when the id could not be found.
         * @param id the id
         * @return the Temperature.Reference object
         */
        public static Reference get(final String id)
        {
            return (Reference) referenceMap.get(id);
        }
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Temperature.Unit encodes the units of relative and absolute temperature. Note that he reference is not initialized
     * immediately for the units, since Reference needs working units to initialize itself. Therefore, references will be
     * allocated when an reference is retrieved for the first time.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<Temperature.Unit, TemperatureDifference>
    {
        /** The dimensions of temperature: K. */
        public static final SIUnit SI_UNIT = SIUnit.of("K");

        /** Kelvin. */
        public static final Temperature.Unit K = new Temperature.Unit("K", "kelvin", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final Temperature.Unit SI = K.generateSiPrefixes(false, false);

        /** Degree Celsius. */
        public static final Temperature.Unit degC =
                new Temperature.Unit("degC", "\u00B0C", "degree Celsius", new LinearScale(1.0), UnitSystem.SI_DERIVED);

        /** Degree Fahrenheit. */
        public static final Temperature.Unit degF =
                new Temperature.Unit("degF", "\u00B0F", "degree Fahrenheit", new LinearScale(5.0 / 9.0), UnitSystem.OTHER);

        /** Degree Rankine. */
        public static final Temperature.Unit degR =
                new Temperature.Unit("degR", "\u00B0R", "degree Rankine", new LinearScale(5.0 / 9.0), UnitSystem.OTHER);

        /** Degree Reaumur. */
        public static final Temperature.Unit degRe =
                new Temperature.Unit("degRe", "\u00B0R\u00E9", "degree Reaumur", new LinearScale(4.0 / 5.0), UnitSystem.OTHER);

        /** the default reference for this unit (used for absolute quantities). */
        private Reference reference;

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

        /**
         * Create a new Temperature unit with a given reference.
         * @param id the id or main abbreviation of the unit
         * @param name the full name of the unit
         * @param scaleFactorToBaseUnit the scale factor of the unit to convert it TO the base (SI) unit
         * @param unitSystem the unit system such as SI or IMPERIAL
         * @param reference the default reference for this unit
         */
        protected Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem,
                final Reference reference)
        {
            super(id, name, new LinearScale(scaleFactorToBaseUnit), unitSystem);
            this.reference = reference;
        }

        /**
         * Return a derived unit for this unit, with textual abbreviation(s) and a display abbreviation, and a given reference.
         * @param textualAbbreviation the textual abbreviation of the unit, which doubles as the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         * @param reference the default reference for this unit
         */
        protected Unit(final String textualAbbreviation, final String displayAbbreviation, final String name, final Scale scale,
                final UnitSystem unitSystem, final Reference reference)
        {
            super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem);
            this.reference = reference;
        }

        /**
         * Return the reference for an absolute temperature.
         * @return the reference for an absolute temperature
         */
        protected Reference getReference()
        {
            // reference may be null because at initialization, Unit needs Reference and Reference needs Unit
            if (this.reference == null)
            {
                K.reference = Reference.KELVIN;
                degC.reference = Reference.CELSIUS;
                degF.reference = Reference.FAHRENHEIT;
                degR.reference = Reference.KELVIN;
                degRe.reference = Reference.CELSIUS;
            }
            return this.reference;
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
                return new Temperature.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
