package org.djunits.quantity;

import org.djunits.quantity.def.AbsoluteQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.AbstractReference;
import org.djunits.unit.Units;
import org.djunits.unit.si.SIUnit;
import org.djutils.exceptions.Throw;

/**
 * AbsoluteTemperature is the absolute equivalent of Temperature, and represents a true temperature rather than a temperature
 * difference.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Temperature extends
        AbsoluteQuantity<Temperature, TemperatureDifference, TemperatureDifference.Unit, Temperature.Reference>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a AbsoluteTemperature quantity with a unit and a reference point.
     * @param value the temperature value, expressed in a temperature unit
     * @param unit the temperature unit in which the value is expressed, relative to the reference point
     * @param reference the reference point of this absolute temperature
     */
    public Temperature(final double value, final TemperatureDifference.Unit unit, final Reference reference)
    {
        super(new TemperatureDifference(value, unit), reference);
    }

    /**
     * Instantiate a AbsoluteTemperature quantity with a unit and the KELVIN reference point.
     * @param value the temperature value, expressed in a temperature unit
     * @param unit the temperature unit in which the value is expressed, relative to the reference point
     */
    public Temperature(final double value, final TemperatureDifference.Unit unit)
    {
        this(value, unit, Reference.KELVIN);
    }

    /**
     * Instantiate a AbsoluteTemperature quantity with a unit, expressed as a String, and a reference point.
     * @param value the temperature value, expressed in the unit, relative to the reference point
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     * @param reference the reference point of this absolute temperature
     */
    public Temperature(final double value, final String abbreviation, final Reference reference)
    {
        this(value, Units.resolve(TemperatureDifference.Unit.class, abbreviation), reference);
    }

    /**
     * Instantiate a AbsoluteTemperature quantity with a unit, expressed as a String, and the KELVIN reference point.
     * @param value the temperature value, expressed in the unit, relative to the reference point
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Temperature(final double value, final String abbreviation)
    {
        this(value, abbreviation, Reference.KELVIN);
    }

    /**
     * Instantiate a AbsoluteTemperature instance based on an temperature and a reference point.
     * @param temperature the temperature, relative to the reference point
     * @param reference the reference point of this absolute temperature
     */
    public Temperature(final TemperatureDifference temperature, final Reference reference)
    {
        super(temperature, reference);
    }

    /**
     * Instantiate a AbsoluteTemperature instance based on an temperature and the KELVIN reference point.
     * @param temperature the temperature, relative to the reference point
     */
    public Temperature(final TemperatureDifference temperature)
    {
        this(temperature, Reference.KELVIN);
    }

    /**
     * Return a AbsoluteTemperature instance based on an SI value and a reference point.
     * @param si the temperature si value, relative to the reference point
     * @param reference the reference point of this absolute temperature
     * @return the AbsoluteTemperature instance based on an SI value
     */
    public static Temperature ofSi(final double si, final Reference reference)
    {
        return new Temperature(si, TemperatureDifference.Unit.SI, reference);
    }

    /**
     * Return a AbsoluteTemperature instance based on an SI value and the KELVIN reference point.
     * @param si the temperature si value, relative to the reference point
     * @return the AbsoluteTemperature instance based on an SI value
     */
    public static Temperature ofSi(final double si)
    {
        return new Temperature(si, TemperatureDifference.Unit.SI, Reference.KELVIN);
    }

    @Override
    public Temperature instantiate(final TemperatureDifference temperature, final Reference reference)
    {
        return new Temperature(temperature, reference);
    }

    @Override
    public SIUnit siUnit()
    {
        return TemperatureDifference.Unit.SI_UNIT;
    }

    /**
     * Returns a AbsoluteTemperature representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a AbsoluteTemperature
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
     * Returns a AbsoluteTemperature representation of a textual representation of a value with a unit, and the KELVIN
     * reference. The String representation that can be parsed is the double value in the unit, followed by a localized or
     * English abbreviation of the unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a AbsoluteTemperature
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Temperature valueOf(final String text)
    {
        return new Temperature(Quantity.valueOf(text, TemperatureDifference.ZERO), Reference.KELVIN);
    }

    /**
     * Returns a AbsoluteTemperature based on a value and the textual representation of the unit, which can be localized.
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
     * Returns a AbsoluteTemperature based on a value and the textual representation of the unit, which can be localized. Use
     * the KELVIN reference.
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
        Throw.when(!getReference().equals(other.getReference()), IllegalArgumentException.class,
                "cannot subtract two absolute quantities with a different reference: %s <> %s", getReference().getId(),
                other.getReference().getId());
        return TemperatureDifference.ofSi(si() - other.si()).setDisplayUnit(getDisplayUnit());
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
        public static final Reference FAHRENHEIT = new Reference("FAHRENHEIT",
                "Fahrenheit scale temperature", TemperatureDifference.ofSi(273.15 - 32 / 1.8), KELVIN);

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
         * @return the AbsoluteTemperatureReference object
         */
        public static Reference get(final String id)
        {
            return (Reference) referenceMap.get(id);
        }
    }
}
