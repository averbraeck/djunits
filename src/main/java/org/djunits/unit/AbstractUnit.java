package org.djunits.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.djunits.old.unit.si.SIDimensions;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.system.UnitSystem;
import org.djutils.exceptions.Throw;

/**
 * The AbstractUnit is the parent class of all units, and encodes the common behavior of the units. All units are internally
 * <i>stored</i> relative to a standard unit with conversion factor. This means that e.g., a meter is stored with conversion
 * factor 1.0, and kilometer is stored with a conversion factor 1000.0. This means that if we want to express a length meter in
 * kilometers, we have to <i>divide</i> by the conversion factor.
 * <p>
 * The conversion to and from the base unit is left to a Scale. Many scales are linear (e.g., converting dm, cm, and mm to
 * meters), but there are also non-linear scales such as the percentage for an angle, where 90 degrees equals an infinite
 * percentage.
 * </p>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <U> the unit type
 */
public abstract class AbstractUnit<U extends AbstractUnit<U>>
{
    /** The textual abbreviations of the unit, where the first one is the id. */
    private final List<String> textualAbbreviations = new ArrayList<>();

    /** The symbolic representation of the unit, which is the default for display. */
    private final String displayAbbreviation;

    /** The full name of the unit. */
    private final String name;

    /** The scale to use to convert between this unit and the standard (e.g., SI, BASE) unit. */
    private final Scale scale;

    /** The unit system, e.g. SI or Imperial. */
    private final UnitSystem unitSystem;

    /**
     * Create a new unit, where the textual abbreviation is the same as the display abbreviation.
     * @param id the id or main abbreviation of the unit
     * @param name the full name of the unit
     * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
     * @param unitSystem unit system, e.g. SI or Imperial
     */
    public AbstractUnit(final String id, final String name, final Scale scale, final UnitSystem unitSystem)
    {
        this(List.of(id), id, name, scale, unitSystem);
    }

    /**
     * Create a new unit, with textual abbreviation(s) and a display abbreviation.
     * @param textualAbbreviations the textual abbreviations of the unit, where the first one in the list is the id
     * @param displayAbbreviation the display abbreviation of the unit
     * @param name the full name of the unit
     * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
     * @param unitSystem unit system, e.g. SI or Imperial
     */
    public AbstractUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
            final Scale scale, final UnitSystem unitSystem)
    {
        // Check the validity
        String cName = Units.unitClassName(getClass());
        Throw.whenNull(textualAbbreviations, "Constructing unit %s: textualAbbreviations cannot be null", cName);
        Throw.whenNull(displayAbbreviation, "Constructing unit %s: displayAbbreviation cannot be null", cName);
        Throw.when(textualAbbreviations.size() == 0, UnitRuntimeException.class,
                "Constructing unit %s: textualAbbreviations cannot be empty", cName);
        for (int i = 0; i < textualAbbreviations.size(); i++)
        {
            Throw.whenNull(textualAbbreviations.get(i), "Constructing unit %s: textualAbbreviations[%d] cannot be null", cName,
                    i);
            Throw.when(textualAbbreviations.get(i).length() == 0, UnitRuntimeException.class,
                    "Constructing unit %s: textualAbbreviations[%d].length cannot be 0", cName, i);
        }
        String id = textualAbbreviations.get(0);
        Throw.whenNull(name, "Constructing unit %s.%s: name cannot be null", cName, id);
        Throw.when(name.length() == 0, UnitRuntimeException.class, "Constructing unit %s.%s: name.length cannot be 0", cName,
                id);
        Throw.whenNull(scale, "Constructing unit %s.%s: scale cannot be null", cName, id);
        Throw.whenNull(unitSystem, "Constructing unit %s.%s: unitSystem cannot be null", cName, id);

        // Build the unit
        this.displayAbbreviation = displayAbbreviation;
        this.textualAbbreviations.addAll(textualAbbreviations);
        this.name = name;
        this.scale = scale;
        this.unitSystem = unitSystem;

        // Register the unit
        Units.register(this);
    }

    /**
     * Generate and register the units with all SI-prefixes.
     * @param kilo whether the base unit already has a "kilo" in its abbreviation/name, such as the kilogram
     * @param perUnit whether it is a "per unit" such as "per meter"
     * @return the unit for method chaining
     */
    @SuppressWarnings("unchecked")
    public U generateSiPrefixes(final boolean kilo, final boolean perUnit)
    {
        String cName = getClass().getSimpleName();
        Throw.when(!getScale().isBaseScale(), UnitRuntimeException.class,
                "SI prefixes generation for unit %s only applicable to unit with base scale", cName);
        Throw.when(kilo && !perUnit && !getId().startsWith("k"), UnitRuntimeException.class,
                "SI prefixes generated for kilo class for unit %s, but abbreviation %s does not start with a 'k'", cName,
                getId());
        Throw.when(kilo && perUnit && !getId().startsWith("/k"), UnitRuntimeException.class,
                "SI prefixes generated for per-kilo class for unit %s, but abbreviation %s does not start with '/k'", cName,
                getId());
        Throw.when(kilo && !perUnit && !getName().startsWith("kilo"), UnitRuntimeException.class,
                "SI prefixes generated for kilo class for unit %s, but name %s does not start with 'kilo'", cName, getName());
        Throw.when(kilo && perUnit && !getName().startsWith("per kilo"), UnitRuntimeException.class,
                "SI prefixes generated for kilo class for unit %s, but name %s does not start with 'per kilo'", cName,
                getName());
        Throw.when(perUnit && !getId().startsWith("/"), UnitRuntimeException.class,
                "SI prefixes generated for 'per' class for unit %s, but abbreviation %s does not start with '/'", cName,
                getId());
        Throw.when(perUnit && !getName().startsWith("per "), UnitRuntimeException.class,
                "SI prefixes generated for 'per' class for unit %s, but name %s does not start with 'per '", cName, getName());

        if (!kilo)
        {
            if (!perUnit)
            {
                for (SIPrefix sip : SIPrefixes.UNIT_PREFIXES.values())
                {
                    deriveUnit(List.of(sip.getDefaultTextualPrefix() + getDefaultTextualAbbreviation()),
                            sip.getDefaultDisplayPrefix() + getDisplayAbbreviation(), sip.getPrefixName() + getName(),
                            new LinearScale(sip.getFactor()), getUnitSystem());
                }
            }
            else
            {
                for (SIPrefix sip : SIPrefixes.PER_UNIT_PREFIXES.values())
                {
                    deriveUnit(List.of(sip.getDefaultTextualPrefix() + getDefaultTextualAbbreviation().substring(1)),
                            sip.getDefaultDisplayPrefix() + getDisplayAbbreviation().substring(1),
                            sip.getPrefixName() + getName().substring(4), new LinearScale(sip.getFactor()), getUnitSystem());
                }
            }
        }
        else
        {
            if (!perUnit)
            {
                for (SIPrefix sip : SIPrefixes.KILO_PREFIXES.values())
                {
                    deriveUnit(List.of(sip.getDefaultTextualPrefix() + getDefaultTextualAbbreviation()),
                            sip.getDefaultDisplayPrefix() + getDisplayAbbreviation(), sip.getPrefixName() + getName(),
                            new LinearScale(sip.getFactor()), getUnitSystem());
                }
            }
            else
            {
                for (SIPrefix sip : SIPrefixes.PER_KILO_PREFIXES.values())
                {
                    deriveUnit(List.of(sip.getDefaultTextualPrefix() + getDefaultTextualAbbreviation().substring(1)),
                            sip.getDefaultDisplayPrefix() + getDisplayAbbreviation().substring(1),
                            sip.getPrefixName() + getName().substring(8), new LinearScale(sip.getFactor()), getUnitSystem());
                }
            }
        }
        return (U) this;
    }

    /**
     * Return the SI dimensions of this unit.
     * @return the SI dimensions of this unit
     */
    public abstract SIDimensions siDimensions();

    /**
     * Convert a value expressed in this unit to its base (SI) value.
     * @param value the value expressed in this unit
     * @return the value converted to its SI value
     */
    public double toBaseValue(final double value)
    {
        return this.scale.toBaseValue(value);
    }

    /**
     * Convert an SI value to a value expressed in this unit.
     * @param si the SI value
     * @return the value converted to this unit
     */
    public double fromBaseValue(final double si)
    {
        return this.scale.fromBaseValue(si);
    }

    /**
     * Return the base unit for this unit.
     * @return the base unit for this unit
     */
    public abstract U baseUnit();

    /**
     * Return a derived unit for this unit, where the textual abbreviation is the same as the display abbreviation.
     * @param id the id or main abbreviation of the unit
     * @param name the full name of the unit
     * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
     * @param unitSystem unit system, e.g. SI or Imperial
     * @return a derived unit for this unit
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public U deriveUnit(final String id, final String name, final Scale scale, final UnitSystem unitSystem)
    {
        return deriveUnit(List.of(id), id, name, scale, unitSystem);
    }

    /**
     * Return a derived unit for this unit, with textual abbreviation(s) and a display abbreviation.
     * @param textualAbbreviations the textual abbreviations of the unit, where the first one in the list is the id
     * @param displayAbbreviation the display abbreviation of the unit
     * @param name the full name of the unit
     * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
     * @param unitSystem unit system, e.g. SI or Imperial
     * @return a derived unit for this unit
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public abstract U deriveUnit(List<String> textualAbbreviations, String displayAbbreviation, String name, Scale scale,
            UnitSystem unitSystem);

    /**
     * Return the id, which is the main abbreviation, of the unit.
     * @return the id (main abbreviation) of the unit
     */
    public String getId()
    {
        return this.textualAbbreviations.get(0);
    }

    /**
     * Retrieve a safe copy of the textual abbreviations.
     * @return the textual abbreviations
     */
    public List<String> getTextualAbbreviations()
    {
        return new ArrayList<>(this.textualAbbreviations);
    }

    /**
     * Retrieve the default display abbreviation.
     * @return the default display abbreviation
     */
    public String getDisplayAbbreviation()
    {
        return this.displayAbbreviation;
    }

    /**
     * Retrieve the default textual abbreviation.
     * @return the default textual abbreviation
     */
    public String getDefaultTextualAbbreviation()
    {
        return this.textualAbbreviations.get(0);
    }

    /**
     * Return the name, which is the main written explanation, of the unit.
     * @return the name (main explanation) of the unit
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Retrieve the scale of this unit.
     * @return the scale of this unit
     */
    public Scale getScale()
    {
        return this.scale;
    }

    /**
     * Retrieve the unit system of this unit.
     * @return unitSystem the unit system of this unit
     */
    public UnitSystem getUnitSystem()
    {
        return this.unitSystem;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.displayAbbreviation, this.name, this.scale, this.textualAbbreviations, this.unitSystem);
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
        AbstractUnit<?> other = (AbstractUnit<?>) obj;
        return Objects.equals(this.displayAbbreviation, other.displayAbbreviation) && Objects.equals(this.name, other.name)
                && Objects.equals(this.scale, other.scale)
                && Objects.equals(this.textualAbbreviations, other.textualAbbreviations)
                && Objects.equals(this.unitSystem, other.unitSystem);
    }

    @Override
    public String toString()
    {
        return this.displayAbbreviation;
    }
}
