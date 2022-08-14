package org.djunits.unit.quantity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.djunits.unit.si.SIDimensions;

/**
 * UnitTypes is a singleton where the BaseUnit SIDimensions 'fingerprints' are stored and mapped to the BaseUnits. It is
 * possible that more baseUnits have the same fingerprint. E.g., Energy and Torque.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public final class Quantities implements Serializable
{
    /** */
    private static final long serialVersionUID = 20190818L;

    /** The one instance. */
    public static final Quantities INSTANCE = new Quantities();

    /** The SI registry of the units. */
    private Map<SIDimensions, Set<Quantity<?>>> siRegistry = new LinkedHashMap<>();

    /** The name registry of the units. */
    private Map<String, Quantity<?>> registry = new LinkedHashMap<>();

    /**
     * Only called once to initialize a static final field.
     */
    private Quantities()
    {
        //
    }

    /**
     * Register the baseUnit in the UnitType registries.
     * @param quantity Quantity&lt;?&gt;; the quantity to register.
     */
    public void register(final Quantity<?> quantity)
    {
        this.registry.put(quantity.getStandardUnit().getClass().getSimpleName(), quantity);
        Set<Quantity<?>> siSet = this.siRegistry.get(quantity.getSiDimensions());
        if (siSet == null)
        {
            siSet = new LinkedHashSet<>();
            this.siRegistry.put(quantity.getSiDimensions(), siSet);
        }
        siSet.add(quantity);
    }

    /**
     * Unregister the baseUnit in the UnitType registries.
     * @param baseUnit Quantity&lt;?&gt;; the quantity to register.
     */
    public void unregister(final Quantity<?> baseUnit)
    {
        if (baseUnit.getStandardUnit() != null)
        {
            this.registry.remove(baseUnit.getStandardUnit().getClass().getSimpleName());
        }
    }

    /**
     * Retrieve a safe copy of the quantity set registered for an SI fingerprint.
     * @param siDimensions SIDimensions; the SI dimensions to search for
     * @return a safe copy of the baseUnit set registered for this SI dimensions fingerprint
     */
    public Set<Quantity<?>> getQuantities(final SIDimensions siDimensions)
    {
        Set<Quantity<?>> baseUnitsSet = new LinkedHashSet<>();
        if (this.siRegistry.containsKey(siDimensions))
        {
            baseUnitsSet.addAll(this.siRegistry.get(siDimensions));
        }
        return baseUnitsSet;
    }

    /**
     * Return the Quantity for a given name of a unit class, or null if it has not been registered.
     * @param unitClassName String; the unit class name to search for, e.g., "LengthUnit"
     * @return Quantity; the quantity belonging to the class, or null if not found
     */
    public Quantity<?> getQuantity(final String unitClassName)
    {
        return this.registry.get(unitClassName);
    }

    /**
     * @return a defensive copy of the registry
     */
    public Map<String, Quantity<?>> getRegistry()
    {
        return new LinkedHashMap<>(this.registry);
    }

    @Override
    public String toString()
    {
        return "UnitTypes [siRegistry=" + this.siRegistry + ", registry=" + this.registry + "]";
    }

}
