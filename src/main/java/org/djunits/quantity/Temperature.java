package org.djunits.quantity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.djunits.quantity.def.AbsoluteQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
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
        AbsoluteQuantity<Temperature, TemperatureDifference, TemperatureDifference.Unit, Temperature.AbsoluteTemperatureReference>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a AbsoluteTemperature quantity with a unit and a reference point.
     * @param value the temperature value, expressed in a temperature unit
     * @param unit the temperature unit in which the value is expressed, relative to the reference point
     * @param reference the reference point of this absolute temperature
     */
    public Temperature(final double value, final TemperatureDifference.Unit unit, final AbsoluteTemperatureReference reference)
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
        this(value, unit, AbsoluteTemperatureReference.KELVIN);
    }

    /**
     * Instantiate a AbsoluteTemperature quantity with a unit, expressed as a String, and a reference point.
     * @param value the temperature value, expressed in the unit, relative to the reference point
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     * @param reference the reference point of this absolute temperature
     */
    public Temperature(final double value, final String abbreviation, final AbsoluteTemperatureReference reference)
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
        this(value, abbreviation, AbsoluteTemperatureReference.KELVIN);
    }

    /**
     * Instantiate a AbsoluteTemperature instance based on an temperature and a reference point.
     * @param temperature the temperature, relative to the reference point
     * @param reference the reference point of this absolute temperature
     */
    public Temperature(final TemperatureDifference temperature, final AbsoluteTemperatureReference reference)
    {
        super(temperature, reference);
    }

    /**
     * Instantiate a AbsoluteTemperature instance based on an temperature and the KELVIN reference point.
     * @param temperature the temperature, relative to the reference point
     */
    public Temperature(final TemperatureDifference temperature)
    {
        this(temperature, AbsoluteTemperatureReference.KELVIN);
    }

    /**
     * Return a AbsoluteTemperature instance based on an SI value and a reference point.
     * @param si the temperature si value, relative to the reference point
     * @param reference the reference point of this absolute temperature
     * @return the AbsoluteTemperature instance based on an SI value
     */
    public static Temperature ofSi(final double si, final AbsoluteTemperatureReference reference)
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
        return new Temperature(si, TemperatureDifference.Unit.SI, AbsoluteTemperatureReference.KELVIN);
    }

    @Override
    public Temperature instantiate(final TemperatureDifference temperature, final AbsoluteTemperatureReference reference)
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
    public static Temperature valueOf(final String text, final AbsoluteTemperatureReference reference)
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
        return new Temperature(Quantity.valueOf(text, TemperatureDifference.ZERO), AbsoluteTemperatureReference.KELVIN);
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
    public static Temperature of(final double value, final String unitString,
            final AbsoluteTemperatureReference reference)
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
        return new Temperature(Quantity.of(value, unitString, TemperatureDifference.ZERO), AbsoluteTemperatureReference.KELVIN);
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
    public static final class AbsoluteTemperatureReference implements Reference
    {
        /** the list of possible reference points to use. */
        private static Map<String, AbsoluteTemperatureReference> referenceList = new LinkedHashMap<>();

        /** Kelvin. */
        public static final AbsoluteTemperatureReference KELVIN =
                new AbsoluteTemperatureReference("KELVIN", "Kelvin scale temperature");

        /** The id. */
        private String id;

        /** The explanation. */
        private String name;

        /**
         * Define a new reference point for the absolute temperature.
         * @param id the id
         * @param name the name or explanation
         */
        private AbsoluteTemperatureReference(final String id, final String name)
        {
            this.id = id;
            this.name = name;
            referenceList.put(id, this);
        }

        /**
         * Define a new reference point for the absolute temperature.
         * @param id the id
         * @param name the name or explanation
         */
        public static void add(final String id, final String name)
        {
            new AbsoluteTemperatureReference(id, name);
        }

        /**
         * Get a reference point for the absolute temperature, based on its id. Return null when the id could not be found.
         * @param id the id
         * @return the AbsoluteTemperatureReference object
         */
        public static AbsoluteTemperatureReference get(final String id)
        {
            return referenceList.get(id);
        }

        @Override
        public String getId()
        {
            return this.id;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(this.id, this.name);
        }

        @SuppressWarnings("checkstyle:needbraces")
        @Override
        public boolean equals(final Object obj)
        {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            AbsoluteTemperatureReference other = (AbsoluteTemperatureReference) obj;
            return Objects.equals(this.id, other.id) && Objects.equals(this.name, other.name);
        }

        @Override
        public String toString()
        {
            return this.id;
        }
    }
}
