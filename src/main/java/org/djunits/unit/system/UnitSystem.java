package org.djunits.unit.system;

import java.util.Objects;

import org.djutils.exceptions.Throw;

/**
 * Systems of Units such as SI, including SI-derived; cgs (centimeter-gram-second).
 * <p>
 * Copyright (c) 2015-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author Alexander Verbraeck
 */
public class UnitSystem
{
    /** CGS: centimeter-gram-second system. */
    public static final UnitSystem CGS = new UnitSystem("CGS", "centimeter-gram-second system");

    /** CGS ESU: centimeter-gram-second system, electrostatic units. */
    public static final UnitSystem CGS_ESU = new UnitSystem("CGS-ESU", "centimeter-gram-second system, electrostatic units");

    /** CGS_EMU: centimeter-gram-second system, electromagnetic units. */
    public static final UnitSystem CGS_EMU = new UnitSystem("CGS-EMU", "centimeter-gram-second system, electromagnetic units");

    /** Imperial system. */
    public static final UnitSystem IMPERIAL = new UnitSystem("Imperial", "Imperial system");

    /** MTS: meter-tonne-second system. */
    public static final UnitSystem MTS = new UnitSystem("MTS", "meter-tonne-second system");

    /** Other (or no) system. */
    public static final UnitSystem OTHER = new UnitSystem("Other", "other system");

    /** SI units, accepted for use in addition to SI. */
    public static final UnitSystem SI_ACCEPTED = new UnitSystem("SI-accepted", "International System of Units (Accepted Unit)");

    /** SI base units: temperature, time, length, mass, luminous intensity, amount of substance and electric current. */
    public static final UnitSystem SI_BASE = new UnitSystem("SI", "International System of Units (Base Unit)");

    /** SI derived units, by combining SI-base elements (and quantifiers such as milli or kilo). */
    public static final UnitSystem SI_DERIVED = new UnitSystem("SI-derived", "International System of Units (Derived Unit)");

    /** US additions to the Imperial system. */
    public static final UnitSystem US_CUSTOMARY = new UnitSystem("US-customary", "US customary system");

    /** AU: Atomic Unit system. */
    public static final UnitSystem AU = new UnitSystem("AU", "Atomic Unit system");

    /** The abbreviation of the unit system, such as cgs. */
    private final String id;

    /** The name of the unit system, such as centimeter-gram-second. */
    private final String name;

    /**
     * Construct a new UnitSystem.
     * @param id the abbreviation of the unit system, such as cgs
     * @param name the name of the unit system, such as centimeter-gram-second
     */
    public UnitSystem(final String id, final String name)
    {
        Throw.whenNull(id, "id");
        Throw.whenNull(name, "name");
        Throw.when(id.strip().length() == 0, IllegalArgumentException.class, "id cannot be empty");
        Throw.when(name.strip().length() == 0, IllegalArgumentException.class, "name cannot be empty");
        this.id = id.strip();
        this.name = name.strip();
    }

    /**
     * Retrieve the name of this UnitSystem.
     * @return the name of this UnitSystem, e.g. centimeter-gram-second
     */
    public final String getName()
    {
        return this.name;
    }

    /**
     * Retrieve the abbreviation of this UnitSystem.
     * @return the abbreviation of this UnitSystem, e.g., CGS.cgs
     */
    public final String getId()
    {
        return this.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id, this.name);
    }

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
        UnitSystem other = (UnitSystem) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.name, other.name);
    }

    @Override
    public String toString()
    {
        return "UnitSystem [id=" + this.id + ", name=" + this.name + "]";
    }

}
