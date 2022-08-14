package org.djunits.unit.quantity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.djunits.Throw;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.util.UnitException;
import org.djunits.unit.util.UnitRuntimeException;

/**
 * Quantity contains a map of all registered units belonging to this base. It also contains the SI 'fingerprint' of the unit.
 * The fingerprint is registered in the UnitTypes singleton where are unit types are registered.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit to reference the actual unit in return values
 */
public class Quantity<U extends Unit<U>> implements Serializable
{
    /** */
    private static final long serialVersionUID = 20190818L;

    /**
     * The SI dimensions of the unit. Also filled for e.g., imperial values with a conversion factor to an SIDimensions. When a
     * value has no SI dimensions, all 9 dimensions can be set to zero.
     */
    private final SIDimensions siDimensions;

    /** Name of the quantity. */
    private final String name;

    /** Derived units for this unit base, retrievable by id. The key is the unit id (e.g., "m"). */
    private final Map<String, U> unitsById = new LinkedHashMap<String, U>();

    /** Derived units for this unit base, retrievable by abbreviation. The key is the unit abbreviation (e.g., "kWh"). */
    private final Map<String, U> unitsByAbbreviation = new LinkedHashMap<String, U>();

    /** The standard unit belonging to this unit base. The first unit that gets registered is considered to be standard. */
    private U standardUnit = null;

    /**
     * Create a unit base with the SI dimensions.
     * @param name String; the quantity name (CamelCase)
     * @param siDimensions SIDimensions; the 9 dimensions of the unit, wrapped in an SIDimensions object
     * @throws NullPointerException when one of the arguments is null
     */
    public Quantity(final String name, final SIDimensions siDimensions)
    {
        Throw.whenNull(name, "name cannot be null");
        Throw.when(name.length() == 0, UnitRuntimeException.class, "name of unit cannot be empty");
        Throw.whenNull(siDimensions, "siDimensions cannot be null");
        this.name = name;
        this.siDimensions = siDimensions;
    }

    /**
     * Create a unit base with the SI dimensions as a String.
     * @param name String; the quantity name (CamelCase)
     * @param siString String; the 9 dimensions of the unit, represented as an SI string
     * @throws UnitRuntimeException when the String cannot be translated into an SIDimensions object
     * @throws NullPointerException when one of the arguments is null
     */
    public Quantity(final String name, final String siString) throws UnitRuntimeException
    {
        Throw.whenNull(name, "name cannot be null");
        Throw.when(name.length() == 1, UnitRuntimeException.class, "name of unit cannot be empty");
        Throw.whenNull(siString, "siString cannot be null");
        this.name = name;
        try
        {
            this.siDimensions = SIDimensions.of(siString);
        }
        catch (UnitException exception)
        {
            throw new UnitRuntimeException(exception);
        }
    }

    /**
     * Create a unit base with the SI dimensions, provided as a byte array.
     * @param name String; the quantity name (CamelCase)
     * @param siSignature byte[]; the 9 dimensions of the unit
     * @throws NullPointerException when one of the arguments is null
     */
    public Quantity(final String name, final byte[] siSignature)
    {
        this(name, new SIDimensions(siSignature));
    }

    /**
     * Register the unit in the map. If the unit supports SI prefixes from yocto to yotta, 20 additional abbreviations are
     * registered. When there is both a unit with an "SI prefix" and a separately registered unit, the most specific
     * specification will be registered in the map. As an example, when the LengthUnit "METER" is registered, all 20 units such
     * as the millimeter and the kilometer are registered as well. When earlier or later the "KILOMETER" is created as a
     * separate unit, the "km" lookup will result in the "KILOMETER" registration rather than in the "METER" registration with a
     * factor of 1000.
     * @param unit U; the unit to register in the map.
     * @param siPrefixes SIPrefixes; indicates whether and which SI prefixes should be generated.
     * @param siPrefixPower double; the power factor of the SI prefixes, e.g. 2.0 for square meters and 3.0 for cubic meters.
     */
    public void registerUnit(final U unit, final SIPrefixes siPrefixes, final double siPrefixPower)
    {
        Throw.whenNull(unit, "unit cannot be null");
        if (this.standardUnit == null)
        {
            this.standardUnit = unit; // The first unit that gets registered is considered to be standard
            Quantities.INSTANCE.register(this);
        }
        if (siPrefixes.equals(SIPrefixes.UNIT))
        {
            for (SIPrefix siPrefix : SIPrefixes.UNIT_PREFIXES.values())
            {
                unit.deriveSI(siPrefix, siPrefixPower, true); // true = automatically generated
                // the unit will register itself as a generated unit
            }
        }
        else if (siPrefixes.equals(SIPrefixes.UNIT_POS))
        {
            for (SIPrefix siPrefix : SIPrefixes.UNIT_POS_PREFIXES.values())
            {
                unit.deriveSI(siPrefix, siPrefixPower, true); // true = automatically generated
            }
        }
        else if (siPrefixes.equals(SIPrefixes.KILO))
        {
            for (SIPrefix siPrefix : SIPrefixes.KILO_PREFIXES.values())
            {
                unit.deriveSIKilo(siPrefix, siPrefixPower, true); // true = automatically generated
            }
        }
        else if (siPrefixes.equals(SIPrefixes.PER_UNIT))
        {
            for (SIPrefix siPrefix : SIPrefixes.PER_UNIT_PREFIXES.values())
            {
                unit.derivePerSI(siPrefix, siPrefixPower, true); // true = automatically generated
            }
        }

        // register the (generated) unit
        if (this.unitsById.containsKey(unit.getId()))
        {
            // if both are generated or both are not generated, give an error
            if (this.unitsById.get(unit.getId()).isGenerated() == unit.isGenerated())
            {
                throw new UnitRuntimeException("A unit with id " + unit.getId() + " has already been registered for unit type "
                        + unit.getClass().getSimpleName());
            }
            else
            {
                if (!unit.isGenerated())
                {
                    // if the new unit is explicit, register and overwrite the existing one
                    this.unitsById.put(unit.getId(), unit);
                }
                // otherwise, the new unit is generated, and the existing one was explicit: ignore the generated one
            }
        }
        else
        {
            // not registered yet
            this.unitsById.put(unit.getId(), unit);
        }

        // register the abbreviation(s) of the (generated) unit
        for (String abbreviation : unit.getAbbreviations())
        {
            if (this.unitsByAbbreviation.containsKey(abbreviation))
            {
                // if both are generated or both are not generated, give an exception
                if (getUnitByAbbreviation(abbreviation).isGenerated() == unit.isGenerated())
                {
                    throw new UnitRuntimeException("A unit with abbreviation " + abbreviation
                            + " has already been registered for unit type " + unit.getClass().getSimpleName());
                }
                else
                {
                    if (!unit.isGenerated())
                    {
                        // overwrite the automatically generated unit with the explicit one
                        this.unitsByAbbreviation.put(abbreviation, unit);
                    }
                    // otherwise, the new unit is generated, and the existing one was explicit: ignore the generated one
                }
            }
            else
            {
                // not registered yet
                this.unitsByAbbreviation.put(abbreviation, unit);
            }
        }
    }

    /**
     * Unregister a unit from the registry, e.g. after a Unit test, or to insert a replacement for an already existing unit.
     * @param unit U; the unit to unregister.
     */
    public void unregister(final U unit)
    {
        Throw.whenNull(unit, "null unit cannot be removed from the unit registry");
        if (this.unitsById.containsValue(unit))
        {
            this.unitsById.remove(unit.getId(), unit);
        }
        for (String abbreviation : unit.getAbbreviations())
        {
            if (this.unitsByAbbreviation.containsKey(abbreviation))
            {
                if (unit.equals(this.unitsByAbbreviation.get(abbreviation)))
                {
                    this.unitsByAbbreviation.remove(abbreviation, unit);
                }
            }
        }
    }

    /**
     * Retrieve the name of the quantity.
     * @return String; the name of the quantity
     */
    public final String getName()
    {
        return this.name;
    }

    /**
     * @return the siDimensions
     */
    public final SIDimensions getSiDimensions()
    {
        return this.siDimensions;
    }

    /**
     * Retrieve a unit by Id.
     * @param id String; the id to look up
     * @return the corresponding unit or null when it was not found
     */
    public U getUnitById(final String id)
    {
        return this.unitsById.get(id);
    }

    /**
     * Retrieve a unit by one of its abbreviations. First try whether the abbreviation itself is available. If not, look up the
     * unit without spaces, "." and "^" to map e.g., "kg.m/s^2" to "kgm/s2". If that fails, see if the unit is an SIDimensions
     * string. If not, return null.
     * @param abbreviation String; the abbreviation to look up
     * @return the corresponding unit or null when it was not found
     */
    public U getUnitByAbbreviation(final String abbreviation)
    {
        U unit = this.unitsByAbbreviation.get(abbreviation);
        if (unit == null)
        {
            unit = this.unitsByAbbreviation.get(abbreviation.replaceAll("[ .^]", ""));
        }
        if (unit == null)
        {
            try
            {
                SIDimensions dim = SIDimensions.of(abbreviation);
                if (dim != null && dim.equals(this.siDimensions))
                {
                    unit = this.standardUnit;
                }
            }
            catch (UnitException exception)
            {
                unit = null;
            }
        }
        return unit;
    }

    /**
     * Retrieve a unit by one of its abbreviations. First try whether the abbreviation itself is available. If not, try without
     * "." that might separate the units (e.g., "N.m"). If that fails, look up the unit without "." and "^" to map e.g.,
     * "kg.m/s^2" to "kgm/s2". If that fails, see if the unit is an SIDimensions string. If not, return null.
     * @param abbreviation String; the abbreviation to look up
     * @return the corresponding unit or null when it was not found
     */
    public U of(final String abbreviation)
    {
        return this.getUnitByAbbreviation(abbreviation);
    }

    /**
     * Retrieve a safe copy of the registryById.
     * @return Map&lt;String, U&gt;; a safe copy of the registryById
     */
    public Map<String, U> getUnitsById()
    {
        return new LinkedHashMap<>(this.unitsById);
    }

    /**
     * Return a safe copy of the registryByAbbreviation.
     * @return Map&lt;String, U&gt;; a safe copy of the registryByAbbreviation
     */
    public Map<String, U> getUnitsByAbbreviation()
    {
        return new LinkedHashMap<>(this.unitsByAbbreviation);
    }

    /**
     * Retrieve the standard unit for this unit base (usually the first registered unit).
     * @return U; the standardUnit for this unit base (usually the first registered unit)
     */
    public U getStandardUnit()
    {
        return this.standardUnit;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode()
    {
        // the hashCode of the standardUnit is not evaluated because of a loop to Quantity
        // the hashCode of the unitByAbbreviation.values() is not evaluated because of a loop to Quantity
        // the hashCode of the unitById.values() is not evaluated because of a loop to Quantity
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.siDimensions == null) ? 0 : this.siDimensions.hashCode());
        result = prime * result + ((this.standardUnit == null) ? 0 : this.standardUnit.getId().hashCode());
        result = prime * result + ((this.unitsByAbbreviation == null) ? 0 : this.unitsByAbbreviation.keySet().hashCode());
        result = prime * result + ((this.unitsById == null) ? 0 : this.unitsById.keySet().hashCode());
        return result;
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Quantity<?> other = (Quantity<?>) obj;
        if (this.siDimensions == null)
        {
            if (other.siDimensions != null)
                return false;
        }
        else if (!this.siDimensions.equals(other.siDimensions))
            return false;
        if (this.standardUnit == null)
        {
            if (other.standardUnit != null)
                return false;
        }
        // the standardUnit is not compared with equals() because of a loop to Quantity
        else if (!this.standardUnit.getId().equals(other.standardUnit.getId()))
            return false;
        if (this.unitsByAbbreviation == null)
        {
            if (other.unitsByAbbreviation != null)
                return false;
        }
        // the unitByAbbreviation is not compared with equals() because of a loop to Quantity
        else if (!this.unitsByAbbreviation.keySet().equals(other.unitsByAbbreviation.keySet()))
            return false;
        if (this.unitsById == null)
        {
            if (other.unitsById != null)
                return false;
        }
        // the unitById is not compared with equals() because of a loop to Quantity
        else if (!this.unitsById.keySet().equals(other.unitsById.keySet()))
            return false;
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "Quantity [standardUnit=" + this.standardUnit + ", name=" + this.name + ", siDimensions=" + this.siDimensions
                + "]";
    }

}
