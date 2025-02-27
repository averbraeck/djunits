package org.djunits.unit.unitsystem;

import java.io.Serializable;

import org.djunits.locale.UnitLocale;

/**
 * Systems of Units such as SI, including SI-derived; cgs (centimeter-gram-second).
 * <p>
 * Copyright (c) 2015-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public abstract class UnitSystem implements Serializable
{
    /** */
    private static final long serialVersionUID = 20140606L;

    /** CGS: centimeter-gram-second system. */
    public static final CGS CGS;

    /** CGS_ESU: centimeter-gram-second system, electrostatic units. */
    public static final CGS_ESU CGS_ESU;

    /** CGS_EMU: centimeter-gram-second system, electromagnetic units. */
    public static final CGS_EMU CGS_EMU;

    /** Imperial system. */
    public static final Imperial IMPERIAL;

    /** MTS: meter-tonne-second system. */
    public static final MTS MTS;

    /** Other (or no) system. */
    public static final Other OTHER;

    /** SI units, accepted for use in addition to SI. */
    public static final SIAccepted SI_ACCEPTED;

    /** SI base units: temperature, time, length, mass, luminous intensity, amount of substance and electric current. */
    public static final SIBase SI_BASE;

    /** SI derived units, by combining SI-base elements (and quantifiers such as milli or kilo). */
    public static final SIDerived SI_DERIVED;

    /** US additions to the Imperial system. */
    public static final USCustomary US_CUSTOMARY;

    /** AU: Atomic Unit system. */
    public static final AU AU;

    static
    {
        CGS = new CGS("UnitSystem.CGS", "UnitSystem.centimeter-gram-second_system");
        CGS_ESU = new CGS_ESU("UnitSystem.CGS_(ESU)", "UnitSystem.centimeter-gram-second_system,_electrostatic_units");
        CGS_EMU = new CGS_EMU("UnitSystem.CGS_(EMU)", "UnitSystem.centimeter-gram-second_system,_electromagnetic_units");
        IMPERIAL = new Imperial("UnitSystem.Imperial", "UnitSystem.Imperial_system");
        MTS = new MTS("UnitSystem.MTS", "UnitSystem.meter-tonne-second_system");
        OTHER = new Other("UnitSystem.Other", "UnitSystem.other_system");
        SI_ACCEPTED = new SIAccepted("UnitSystem.SI_accepted", "UnitSystem.International_System_of_Units_(Accepted_Unit)");
        SI_BASE = new SIBase("UnitSystem.SI", "UnitSystem.International_System_of_Units_(Base_Unit)");
        SI_DERIVED = new SIDerived("UnitSystem.SI_derived", "UnitSystem.International_System_of_Units_(Derived_Unit)");
        US_CUSTOMARY = new USCustomary("UnitSystem.US_customary", "UnitSystem.US_customary_system");
        AU = new AU("UnitSystem.AU", "UnitSystem.Atomic_Unit_system");
    }

    /** The abbreviation of the unit system, such as cgs. */
    private final String abbreviationKey;

    /** The name of the unit system, such as centimeter-gram-second. */
    private final String nameKey;

    /** Localization information. */
    private static UnitLocale localization = new UnitLocale("unitsystem");

    /**
     * Construct a new UnitSystem.
     * @param abbreviationKey the abbreviation of the unit system, such as cgs
     * @param nameKey the name of the unit system, such as centimeter-gram-second
     */
    protected UnitSystem(final String abbreviationKey, final String nameKey)
    {
        this.abbreviationKey = abbreviationKey;
        this.nameKey = nameKey;
    }

    /**
     * Retrieve the name of this UnitSystem.
     * @return the name of this UnitSystem, e.g. centimeter-gram-second
     */
    public final String getName()
    {
        return localization.getString(this.nameKey);
    }

    /**
     * Retrieve the name key of this UnitSystem.
     * @return the name key of this UnitSystem, e.g. CGS.centimeter-gram-second
     */
    public final String getNameKey()
    {
        return this.nameKey;
    }

    /**
     * Retrieve the abbreviation of this UnitSystem.
     * @return the abbreviation of this UnitSystem, e.g., CGS.cgs
     */
    public final String getAbbreviation()
    {
        return localization.getString(this.abbreviationKey);
    }

    /**
     * Retrieve the abbreviation key of this UnitSystem.
     * @return the abbreviation key of this UnitSystem, e.g. cgs
     */
    public final String getAbbreviationKey()
    {
        return this.abbreviationKey;
    }

    @Override
    public String toString()
    {
        return "UnitSystem [abbreviationKey=" + this.abbreviationKey + ", nameKey=" + this.nameKey + "]";
    }

}
