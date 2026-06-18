package org.djunits.unit;

import java.util.Objects;

import org.djunits.quantity.def.Quantity;
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
 * percentage. Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights
 * reserved. See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS
 * project is distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style
 * license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type belonging to this unit
 */
public abstract class AbstractUnit<Q extends Quantity<Q>> implements UnitInterface<Q>
{
    /** The textual abbreviation of the unit, which is also the id. */
    private final String textualAbbreviation;

    /** The symbolic representation of the unit, which is the default for display. */
    private final String displayAbbreviation;

    /** The full name of the unit. */
    private final String name;

    /** The scale to use to convert between this unit and the standard (e.g., SI, BASE) unit. */
    private final Scale scale;

    /** The unit system, e.g. SI or Imperial. */
    private final UnitSystem unitSystem;

    /** The SI-prefix, if any, to allow localization of the SI-prefix. */
    private final SIPrefix siPrefix;

    /**
     * Create a new unit, where the textual abbreviation is the same as the display abbreviation.
     * @param textualAbbreviation the textual abbreviation of the unit, which also serves as the id
     * @param name the full name of the unit
     * @param scaleFactorToBaseUnit the linear scale factor to use to convert between this unit and the standard (e.g., SI,
     *            BASE) unit
     * @param unitSystem unit system, e.g. SI or Imperial
     */
    public AbstractUnit(final String textualAbbreviation, final String name, final double scaleFactorToBaseUnit,
            final UnitSystem unitSystem)
    {
        this(textualAbbreviation, textualAbbreviation, name, new LinearScale(scaleFactorToBaseUnit), unitSystem, null);
    }

    /**
     * Create a new unit, with textual abbreviation(s) and a display abbreviation.
     * @param textualAbbreviation the textual abbreviation of the unit, which also serves as the id
     * @param displayAbbreviation the display abbreviation of the unit
     * @param name the full name of the unit
     * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
     * @param unitSystem unit system, e.g. SI or Imperial
     * @param siPrefix the SI Prefix of this unit, can be null
     */
    public AbstractUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
            final Scale scale, final UnitSystem unitSystem, final SIPrefix siPrefix)
    {
        // Check the validity
        String cName = Units.unitClassName(getClass());
        Throw.whenNull(textualAbbreviation, "Constructing unit %s: textualAbbreviation cannot be null", cName);
        Throw.whenNull(displayAbbreviation, "Constructing unit %s: displayAbbreviation cannot be null", cName);
        Throw.when(textualAbbreviation.length() == 0, UnitRuntimeException.class,
                "Constructing unit %s: textualAbbreviation string cannot be empty", cName);
        Throw.whenNull(name, "Constructing unit %s.%s: name cannot be null", cName, textualAbbreviation);
        Throw.when(name.length() == 0, UnitRuntimeException.class, "Constructing unit %s.%s: name.length cannot be 0", cName,
                textualAbbreviation);
        Throw.whenNull(scale, "Constructing unit %s.%s: scale cannot be null", cName, textualAbbreviation);
        Throw.whenNull(unitSystem, "Constructing unit %s.%s: unitSystem cannot be null", cName, textualAbbreviation);

        // Build the unit
        this.displayAbbreviation = displayAbbreviation;
        this.textualAbbreviation = textualAbbreviation;
        this.name = name;
        this.scale = scale;
        this.unitSystem = unitSystem;
        this.siPrefix = siPrefix;

        // Register the unit
        Units.register(this);
    }

    /**
     * Generate and register the units with all SI-prefixes.
     * @param kilo whether the base unit already has a "kilo" in its abbreviation/name, such as the kilogram
     * @param perUnit whether it is a "per unit" such as "per meter"
     * @return the unit for method chaining
     */
    public UnitInterface<Q> generateSiPrefixes(final boolean kilo, final boolean perUnit)
    {
        String cName = getClass().getSimpleName();
        Throw.when(!getScale().isIdentityScale(), UnitRuntimeException.class,
                "SI prefixes generation for unit %s only applicable to unit with base scale", cName);
        Throw.when(kilo && !perUnit && !getId().startsWith("k"), UnitRuntimeException.class,
                "SI prefixes generated for kilo class for unit %s, but abbreviation %s does not start with a 'k'", cName,
                getId());
        Throw.when(kilo && perUnit && !getId().startsWith("/k"), UnitRuntimeException.class,
                "SI prefixes generated for per-kilo class for unit %s, but abbreviation %s does not start with '/k'", cName,
                getId());
        Throw.when(kilo && !perUnit && !getStoredName().startsWith("kilo"), UnitRuntimeException.class,
                "SI prefixes generated for kilo class for unit %s, but name %s does not start with 'kilo'", cName,
                getStoredName());
        Throw.when(kilo && perUnit && !getStoredName().startsWith("per kilo"), UnitRuntimeException.class,
                "SI prefixes generated for kilo class for unit %s, but name %s does not start with 'per kilo'", cName,
                getStoredName());
        Throw.when(perUnit && !getId().startsWith("/"), UnitRuntimeException.class,
                "SI prefixes generated for 'per' class for unit %s, but abbreviation %s does not start with '/'", cName,
                getId());
        Throw.when(perUnit && !getStoredName().startsWith("per "), UnitRuntimeException.class,
                "SI prefixes generated for 'per' class for unit %s, but name %s does not start with 'per '", cName,
                getStoredName());

        if (!kilo)
        {
            if (!perUnit)
            {
                for (SIPrefix sip : SIPrefixes.UNIT_PREFIXES.values())
                {
                    if (sip.getFactor() != 1.0)
                    {
                        deriveUnit(sip.getDefaultTextualPrefix() + getStoredTextualAbbreviation(),
                                sip.getDefaultDisplayPrefix() + getStoredDisplayAbbreviation(),
                                sip.getPrefixName() + getStoredName(), sip.getFactor(), getUnitSystem(), sip);
                    }
                }
            }
            else
            {
                for (SIPrefix sip : SIPrefixes.PER_UNIT_PREFIXES.values())
                {
                    if (sip.getFactor() != 1.0)
                    {
                        deriveUnit(sip.getDefaultTextualPrefix() + getStoredTextualAbbreviation().substring(1),
                                sip.getDefaultDisplayPrefix() + getStoredDisplayAbbreviation().substring(1),
                                sip.getPrefixName() + getStoredName().substring(4), sip.getFactor(), getUnitSystem(), sip);
                    }
                }
            }
        }
        else
        {
            if (!perUnit)
            {
                for (SIPrefix sip : SIPrefixes.KILO_PREFIXES.values())
                {
                    if (sip.getFactor() != 1.0)
                    {
                        deriveUnit(sip.getDefaultTextualPrefix() + getStoredTextualAbbreviation().substring(1),
                                sip.getDefaultDisplayPrefix() + getStoredDisplayAbbreviation().substring(1),
                                sip.getPrefixName() + getStoredName().substring(4), sip.getFactor(), getUnitSystem(), sip);
                    }
                }
            }
            else
            {
                for (SIPrefix sip : SIPrefixes.PER_KILO_PREFIXES.values())
                {
                    if (sip.getFactor() != 1.0)
                    {

                        deriveUnit(sip.getDefaultTextualPrefix() + getStoredTextualAbbreviation().substring(2),
                                sip.getDefaultDisplayPrefix() + getStoredDisplayAbbreviation().substring(2),
                                sip.getPrefixName() + getStoredName().substring(8), sip.getFactor(), getUnitSystem(), sip);
                    }
                }
            }
        }
        return this;
    }

    /**
     * Return a linearly scaled derived unit for this unit, with textual abbreviation(s) and a display abbreviation.
     * @param textualAbbreviation the textual abbreviation of the unit, which doubles as the id
     * @param displayAbbreviation the display abbreviation of the unit
     * @param name the full name of the unit
     * @param scaleFactor the (linear) scale factor to multiply with the current (linear) scale factor
     * @param unitSystem unit system, e.g. SI or Imperial
     * @param siPrefix the SI prefix of this unit
     * @return a derived unit for this unit
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public abstract UnitInterface<Q> deriveUnit(String textualAbbreviation, String displayAbbreviation, String name,
            double scaleFactor, UnitSystem unitSystem, SIPrefix siPrefix);

    /**
     * Return a linearly scaled derived unit for this unit, with an abbreviation and scale factor.
     * @param abbreviation the textual abbreviation of the unit, which doubles as the id
     * @param name the full name of the unit
     * @param scaleFactor the (linear) scale factor to multiply with the current (linear) scale factor
     * @param unitSystem unit system, e.g. SI or Imperial
     * @return a derived unit for this unit
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public UnitInterface<Q> deriveUnit(final String abbreviation, final String name, final double scaleFactor,
            final UnitSystem unitSystem)
    {
        return deriveUnit(abbreviation, abbreviation, name, scaleFactor, unitSystem, null);
    }

    @Override
    public String getId()
    {
        return this.textualAbbreviation;
    }

    @Override
    public String getStoredTextualAbbreviation()
    {
        return this.textualAbbreviation;
    }

    @Override
    public String getTextualAbbreviation()
    {
        return Units.localizedUnitTextualAbbr(getClass(), getId());
    }

    @Override
    public String getStoredDisplayAbbreviation()
    {
        return this.displayAbbreviation;
    }

    @Override
    public String getDisplayAbbreviation()
    {
        return Units.localizedUnitDisplayAbbr(getClass(), getId());
    }

    @Override
    public String getStoredName()
    {
        return this.name;
    }

    @Override
    public String getName()
    {
        return Units.localizedUnitName(getClass(), getId());
    }

    @Override
    public Scale getScale()
    {
        return this.scale;
    }

    @Override
    public UnitSystem getUnitSystem()
    {
        return this.unitSystem;
    }

    @Override
    public SIPrefix getSiPrefix()
    {
        return this.siPrefix;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.displayAbbreviation, this.name, this.scale, this.textualAbbreviation, this.unitSystem);
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
                && Objects.equals(this.textualAbbreviation, other.textualAbbreviation)
                && Objects.equals(this.unitSystem, other.unitSystem);
    }

    @Override
    public String toString()
    {
        return this.displayAbbreviation;
    }
}
