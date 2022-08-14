package org.djunits.unit;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.djunits.Throw;
import org.djunits.unit.quantity.Quantities;
import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.OffsetLinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;
import org.djunits.unit.util.UnitRuntimeException;

/**
 * All units are internally <i>stored</i> relative to a standard unit with conversion factor. This means that e.g., a meter is
 * stored with conversion factor 1.0, whereas kilometer is stored with a conversion factor 1000.0. This means that if we want to
 * express a length meter in kilometers, we have to <i>divide</i> by the conversion factor.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit to reference the actual unit in return values
 */
public class Unit<U extends Unit<U>> implements Serializable, Cloneable
{
    /** */
    private static final long serialVersionUID = 20190818L;

    /** The id of the unit; has to be unique within the unit name. Used for, e.g., localization and retrieval. */
    private String id;

    /** The abbreviations in the default locale. All abbreviations an be used in the valueOf() and of() methods. */
    private Set<String> abbreviations;

    /** The default display abbreviation in the default locale for printing. Included in the abbreviations list. */
    private String defaultDisplayAbbreviation;

    /** The default textual abbreviation in the default locale for data entry. Included in the abbreviations list. */
    private String defaultTextualAbbreviation;

    /** The long name of the unit in the default locale. */
    private String name;

    /** The scale to use to convert between this unit and the standard (e.g., SI, BASE) unit. */
    private Scale scale;

    /** The unit system, e.g. SI or Imperial. */
    private UnitSystem unitSystem;

    /** Has the unit been automatically generated or not. */
    private boolean generated;

    /** Does the unit have the standard SI signature? */
    private boolean baseSIUnit;

    /**
     * The corresponding unit base that contains all registered units for the unit as well as SI dimension information. The base
     * unit of a unit base is null.
     */
    private Quantity<U> quantity;

    // TODO create a static that loads all unit classes in the registry

    /**
     * Initialize a blank unit that can be built through reflection with several 'setter' methods followed by calling the
     * build(...) method.
     */
    protected Unit()
    {
        //
    }

    /**
     * Build the unit, using the information of the provided builder class. First check rigorously if all necessary fields in
     * the builder have been set, and whether they are consistent and valid. The behavior is as follows: the defaultAbbreviation
     * and defaultTextualAbbreviation are added to the abbreviations set, if they are not yet already there. When the
     * defaultAbbreviation is set and the defaultTextualAbbreviation is not set, the defaultTextualAbbreviation gets the value
     * of defaultAbbreviation. The reverse also holds: When the defaultTextualAbbreviation is set and the defaultAbbreviation is
     * not set, the defaultAbbreviation gets the value of defaultTextualAbbreviation. When neither the
     * defaultTextualAbbreviation, nor the defaultAbbreviation are set, both get the value of the unitId provided in the
     * builder.
     * @param builder Builder&lt;U&gt;; Builder&lt;U&gt; the object that contains the information about the construction of the
     *            class
     * @return U; the constructed unit
     * @throws UnitRuntimeException when not all fields have been set
     */
    @SuppressWarnings("unchecked")
    public U build(final Builder<U> builder) throws UnitRuntimeException
    {
        // Check the validity
        String cName = getClass().getSimpleName();
        Throw.whenNull(builder.getId(), "Constructing unit %s: id cannot be null", cName);
        Throw.when(builder.getId().length() == 0, UnitRuntimeException.class, "Constructing unit %s: id.length cannot be 0",
                cName);
        String unitId = builder.getId();
        Throw.whenNull(builder.getQuantity(), "Constructing unit %s.%s: baseUnit cannot be null", cName, unitId);
        Throw.whenNull(builder.getName(), "Constructing unit %s.%s: name cannot be null", cName, unitId);
        Throw.when(builder.getName().length() == 0, UnitRuntimeException.class,
                "Constructing unit %s.%s: name.length cannot be 0", cName, unitId);
        Throw.whenNull(builder.getScale(), "Constructing unit %s.%s: scale cannot be null", cName, unitId);
        Throw.whenNull(builder.getUnitSystem(), "Constructing unit %s.%s: unitSystem cannot be null", cName, unitId);

        // set the key fields
        this.id = unitId;
        this.name = builder.getName();
        this.quantity = builder.getQuantity();
        this.unitSystem = builder.getUnitSystem();
        this.scale = builder.getScale();
        this.generated = builder.isGenerated();
        this.baseSIUnit = this.scale.isBaseSIScale();

        // Set and check the abbreviations
        if (builder.getDefaultDisplayAbbreviation() == null)
        {
            if (builder.getDefaultTextualAbbreviation() == null)
            {
                this.defaultDisplayAbbreviation = unitId;
            }
            else
            {
                this.defaultDisplayAbbreviation = builder.getDefaultTextualAbbreviation();
            }
        }
        else
        {
            this.defaultDisplayAbbreviation = builder.getDefaultDisplayAbbreviation();
        }
        if (builder.getDefaultTextualAbbreviation() == null)
        {
            this.defaultTextualAbbreviation = this.defaultDisplayAbbreviation;
        }
        else
        {
            this.defaultTextualAbbreviation = builder.getDefaultTextualAbbreviation();
        }
        this.abbreviations = new LinkedHashSet<>();
        this.abbreviations.add(this.defaultDisplayAbbreviation);
        this.abbreviations.add(this.defaultTextualAbbreviation);
        this.abbreviations.addAll(builder.getAdditionalAbbreviations());

        // See what SI prefixes have to be registered. If not specified: NONE.
        SIPrefixes siPrefixes = builder.getSiPrefixes() == null ? SIPrefixes.NONE : builder.getSiPrefixes();

        // Register the unit, possibly including all SI prefixes
        this.quantity.registerUnit((U) this, siPrefixes, builder.getSiPrefixPowerFactor());
        return (U) this;
    }

    /**
     * Create a scaled version of this unit with the same unit system but another SI prefix and scale.
     * @param siPrefix SIPrefix; the prefix for which to scale the unit
     * @param siPrefixPower double; the power factor of the SI prefixes, e.g. 2.0 for square meters and 3.0 for cubic meters.
     * @param automaticallyGenerated boolean; indicate whether the unit has been automatically generated
     * @return U; a scaled instance of this unit
     * @throws UnitRuntimeException when cloning fails
     */
    public U deriveSI(final SIPrefix siPrefix, final double siPrefixPower, final boolean automaticallyGenerated)
    {
        Throw.whenNull(siPrefix, "siPrefix cannot be null");
        try
        {
            U clone = clone();

            // Get values: combine all prefixes with all names / abbreviations
            String cloneId = siPrefix.getDefaultTextualPrefix() + clone.getId();
            String cloneName = clone.getName();
            if (cloneName.startsWith("square "))
            {
                cloneName = "square " + siPrefix.getPrefixName() + cloneName.substring(6);
            }
            else if (clone.getName().startsWith("cubic "))
            {
                cloneName = "cubic " + siPrefix.getPrefixName() + cloneName.substring(5);
            }
            else
            {
                cloneName = siPrefix.getPrefixName() + cloneName;
            }
            Set<String> cloneAbbreviations = new LinkedHashSet<>();
            for (String abbreviation : clone.getAbbreviations())
            {
                cloneAbbreviations.add(siPrefix.getDefaultTextualPrefix() + abbreviation);
            }
            String cloneDefaultAbbreviation = siPrefix.getDefaultDisplayPrefix() + clone.getDefaultDisplayAbbreviation();
            String cloneDefaultTextualAbbreviation = siPrefix.getDefaultTextualPrefix() + clone.getDefaultTextualAbbreviation();

            // Make a builder and set values
            Builder<U> builder = makeBuilder();
            builder.setId(cloneId);
            builder.setName(cloneName);
            builder.setQuantity(this.quantity);
            builder.setSiPrefixes(SIPrefixes.NONE, 1.0);
            builder.setDefaultDisplayAbbreviation(cloneDefaultAbbreviation);
            builder.setDefaultTextualAbbreviation(cloneDefaultTextualAbbreviation);
            builder.setAdditionalAbbreviations(cloneAbbreviations.toArray(new String[cloneAbbreviations.size()]));
            if (getScale() instanceof OffsetLinearScale)
            {
                builder.setScale(new OffsetLinearScale(
                        (siPrefixPower == 1.0 ? siPrefix.getFactor() : Math.pow(siPrefix.getFactor(), siPrefixPower))
                                * ((LinearScale) getScale()).getConversionFactorToStandardUnit(),
                        0.0));
            }
            else
            {
                builder.setScale(new LinearScale(
                        (siPrefixPower == 1.0 ? siPrefix.getFactor() : Math.pow(siPrefix.getFactor(), siPrefixPower))
                                * ((LinearScale) getScale()).getConversionFactorToStandardUnit()));
            }
            builder.setUnitSystem(this.unitSystem); // SI_BASE stays SI_BASE with prefix
            builder.setGenerated(automaticallyGenerated);

            return clone.build(builder);
        }
        catch (CloneNotSupportedException exception)
        {
            throw new UnitRuntimeException(exception);
        }
    }

    /**
     * Create a scaled version of this unit with the same unit system but another SI prefix and scale. This method is used for a
     * unit that is explicitly scaled with an SI prefix.
     * @param siPrefix SIPrefix; the prefix for which to scale the unit
     * @param siPrefixPower double; the power factor of the SI prefixes, e.g. 2.0 for square meters and 3.0 for cubic meters.
     * @return a scaled instance of this unit
     * @throws UnitRuntimeException when cloning fails
     */
    public U deriveSI(final SIPrefix siPrefix, final double siPrefixPower)
    {
        return deriveSI(siPrefix, siPrefixPower, false);
    }

    /**
     * Create a scaled version of this unit with the same unit system but another SI prefix and scale, where the "k" and "kilo"
     * abbreviations at the start will be replaced by the new information from the SIPrefix.
     * @param siPrefix SIPrefix; the prefix for which to scale the unit
     * @param siPrefixPower double; the power factor of the SI prefixes, e.g. 2.0 for square meters and 3.0 for cubic meters.
     * @param automaticallyGenerated boolean; indicate whether the unit has been automatically generated
     * @return U; a scaled instance of this unit
     * @throws UnitRuntimeException when cloning fails
     */
    public U deriveSIKilo(final SIPrefix siPrefix, final double siPrefixPower, final boolean automaticallyGenerated)
    {
        Throw.whenNull(siPrefix, "siPrefix cannot be null");
        Throw.when(!this.id.startsWith("k"), UnitRuntimeException.class, "deriving from a kilo-unit: id should start with 'k'");
        Throw.when(!this.defaultTextualAbbreviation.startsWith("k"), UnitRuntimeException.class,
                "deriving from a kilo-unit: defaultTextualAbbreviation should start with 'k'");
        Throw.when(!this.defaultDisplayAbbreviation.startsWith("k"), UnitRuntimeException.class,
                "deriving from a kilo-unit: defaultDisplayAbbreviation should start with 'k'");
        Throw.when(!this.name.startsWith("kilo"), UnitRuntimeException.class,
                "deriving from a kilo-unit: name should start with 'kilo'");
        for (String abbreviation : getAbbreviations())
        {
            Throw.when(!abbreviation.startsWith("k"), UnitRuntimeException.class,
                    "deriving from a kilo-unit: each abbreviation should start with 'k'");
        }

        try
        {
            U clone = clone();

            // get values: combine all prefixes with all names / abbreviations
            String cloneId = siPrefix.getDefaultTextualPrefix() + clone.getId().substring(1);
            String cloneName = siPrefix.getPrefixName() + clone.getName().substring(4);
            Set<String> cloneAbbreviations = new LinkedHashSet<>();
            for (String abbreviation : clone.getAbbreviations())
            {
                cloneAbbreviations.add(siPrefix.getDefaultTextualPrefix() + abbreviation.substring(1));
            }
            String cloneDefaultAbbreviation =
                    siPrefix.getDefaultDisplayPrefix() + clone.getDefaultDisplayAbbreviation().substring(1);
            String cloneDefaultTextualAbbreviation =
                    siPrefix.getDefaultTextualPrefix() + clone.getDefaultTextualAbbreviation().substring(1);

            // make a builder and set values
            Builder<U> builder = makeBuilder();
            builder.setId(cloneId);
            builder.setName(cloneName);
            builder.setQuantity(this.quantity);
            builder.setSiPrefixes(SIPrefixes.NONE, 1.0);
            builder.setDefaultDisplayAbbreviation(cloneDefaultAbbreviation);
            builder.setDefaultTextualAbbreviation(cloneDefaultTextualAbbreviation);
            builder.setAdditionalAbbreviations(cloneAbbreviations.toArray(new String[cloneAbbreviations.size()]));
            if (getScale() instanceof OffsetLinearScale)
            {
                builder.setScale(new OffsetLinearScale(
                        (siPrefixPower == 1.0 ? siPrefix.getFactor() : Math.pow(siPrefix.getFactor(), siPrefixPower))
                                * ((LinearScale) getScale()).getConversionFactorToStandardUnit(),
                        0.0));
            }
            else
            {
                builder.setScale(new LinearScale(
                        (siPrefixPower == 1.0 ? siPrefix.getFactor() : Math.pow(siPrefix.getFactor(), siPrefixPower))
                                * ((LinearScale) getScale()).getConversionFactorToStandardUnit()));
            }

            builder.setUnitSystem(this.unitSystem);
            builder.setGenerated(automaticallyGenerated);

            return clone.build(builder);
        }
        catch (CloneNotSupportedException exception)
        {
            throw new UnitRuntimeException(exception);
        }
    }

    /**
     * Create a scaled version of this unit with the same unit system but another SI prefix and scale. The "per" units scale in
     * the opposite direction as the normally scaled units. It will yield units like "/ms", "/mus", "/ns", etc.
     * @param siPrefix SIPrefix; the prefix for which to scale the unit
     * @param siPrefixPower double; the power factor of the SI prefixes, e.g. 2.0 for square meters and 3.0 for cubic meters.
     * @param automaticallyGenerated boolean; indicate whether the unit has been automatically generated
     * @return U; a scaled instance of this unit
     * @throws UnitRuntimeException when cloning fails
     */
    public U derivePerSI(final SIPrefix siPrefix, final double siPrefixPower, final boolean automaticallyGenerated)
    {
        Throw.whenNull(siPrefix, "siPrefix cannot be null");
        try
        {
            U clone = clone();

            // Get values: combine all prefixes with all names / abbreviations
            String cloneId = siPrefix.getDefaultTextualPrefix() + clone.getId().replace("1/", "").replace("/", "").trim();
            String cloneName =
                    siPrefix.getPrefixName() + clone.getName().replace("per", "").replace("1/", "").replace("/", "").trim();
            Set<String> cloneAbbreviations = new LinkedHashSet<>();
            for (String abbreviation : clone.getAbbreviations())
            {
                cloneAbbreviations
                        .add(siPrefix.getDefaultTextualPrefix() + abbreviation.replace("1/", "").replace("/", "").trim());
                cloneAbbreviations
                        .add("1" + siPrefix.getDefaultTextualPrefix() + abbreviation.replace("1/", "").replace("/", "").trim());
            }
            String cloneDefaultAbbreviation = siPrefix.getDefaultDisplayPrefix()
                    + clone.getDefaultDisplayAbbreviation().replace("1/", "").replace("/", "").trim();
            String cloneDefaultTextualAbbreviation = siPrefix.getDefaultTextualPrefix()
                    + clone.getDefaultTextualAbbreviation().replace("1/", "").replace("/", "").trim();

            // Make a builder and set values
            Builder<U> builder = makeBuilder();
            builder.setId(cloneId);
            builder.setName(cloneName);
            builder.setQuantity(this.quantity);
            builder.setSiPrefixes(SIPrefixes.NONE, 1.0);
            builder.setDefaultDisplayAbbreviation(cloneDefaultAbbreviation);
            builder.setDefaultTextualAbbreviation(cloneDefaultTextualAbbreviation);
            builder.setAdditionalAbbreviations(cloneAbbreviations.toArray(new String[cloneAbbreviations.size()]));
            if (getScale() instanceof OffsetLinearScale)
            {
                builder.setScale(new OffsetLinearScale(
                        (siPrefixPower == 1.0 ? siPrefix.getFactor() : Math.pow(siPrefix.getFactor(), siPrefixPower))
                                * ((LinearScale) getScale()).getConversionFactorToStandardUnit(),
                        0.0));
            }
            else
            {
                builder.setScale(new LinearScale(
                        (siPrefixPower == 1.0 ? siPrefix.getFactor() : Math.pow(siPrefix.getFactor(), siPrefixPower))
                                * ((LinearScale) getScale()).getConversionFactorToStandardUnit()));
            }
            builder.setUnitSystem(this.unitSystem); // SI_BASE stays SI_BASE with prefix
            builder.setGenerated(automaticallyGenerated);

            return clone.build(builder);
        }
        catch (CloneNotSupportedException exception)
        {
            throw new UnitRuntimeException(exception);
        }
    }

    /**
     * Create a linearly scaled version of this unit. The scale field will be filled with the correct scaleFactor. Note that the
     * unit that is used for derivation can already have a scaleFactor.
     * @param scaleFactor double; the linear scale factor of the unit
     * @param derivedId String; the new id of the derived unit
     * @param derivedName String; the new name of the derived unit
     * @param derivedUnitSystem UnitSystem; the unit system of the derived unit
     * @param derivedDefaultDisplayAbbreviation String; the default abbreviation to use in e.g, the toString() method. Can be
     *            null.
     * @param derivedDefaultTextualAbbreviation String; the default textual abbreviation to use in, e.g, typing. Can be null.
     * @param derivedAbbreviations String...; the other valid abbreviations for the unit, e.g. {"h", "hr", "hour"}. Can be left
     *            out.
     * @return U; a linearly scaled instance of this unit with new id, abbreviation, name, and unit system
     * @throws UnitRuntimeException when cloning fails
     */
    public U deriveLinear(final double scaleFactor, final String derivedId, final String derivedName,
            final UnitSystem derivedUnitSystem, final String derivedDefaultDisplayAbbreviation,
            final String derivedDefaultTextualAbbreviation, final String... derivedAbbreviations)
    {
        String cName = getClass().getSimpleName();
        Throw.whenNull(derivedId, "deriving unit from %s.%s; derivedId cannot be null", cName, this.id);
        Throw.whenNull(derivedName, "deriving unit from %s.%s; derivedName cannot be null", cName, this.id);
        Throw.whenNull(derivedUnitSystem, "deriving unit from %s.%s; derivedUnitSystem cannot be null", cName, this.id);
        if (!getScale().getClass().equals(LinearScale.class) && !getScale().getClass().equals(IdentityScale.class))
        {
            throw new UnitRuntimeException("Cannot derive from unit " + cName + "." + getId() + " with scale "
                    + getScale().getClass().getSimpleName() + ". Scale should be Linear");
        }

        try
        {
            U clone = clone();

            // make a builder and set values
            Builder<U> builder = makeBuilder();
            builder.setId(derivedId);
            builder.setName(derivedName);
            builder.setQuantity(this.quantity);
            builder.setSiPrefixes(SIPrefixes.NONE, 1.0);
            builder.setScale(new LinearScale(scaleFactor * ((LinearScale) getScale()).getConversionFactorToStandardUnit()));
            builder.setUnitSystem(derivedUnitSystem);
            builder.setDefaultDisplayAbbreviation(derivedDefaultDisplayAbbreviation);
            builder.setDefaultTextualAbbreviation(derivedDefaultTextualAbbreviation);
            if (derivedAbbreviations != null)
            {
                builder.setAdditionalAbbreviations(derivedAbbreviations);
            }

            return clone.build(builder);
        }
        catch (CloneNotSupportedException exception)
        {
            throw new UnitRuntimeException(exception);
        }
    }

    /**
     * Create a linearly scaled version of this unit. The scale field will be filled with the correct scaleFactor. Note that the
     * unit that is used for derivation can already have a scaleFactor.
     * @param scaleFactor double; the linear scale factor of the unit
     * @param derivedId String; the new id of the derived unit
     * @param derivedName String; the new name of the derived unit
     * @param derivedUnitSystem UnitSystem; the unit system of the derived unit
     * @return U; a linearly scaled instance of this unit with new id, abbreviation, name, and unit system
     * @throws UnitRuntimeException when cloning fails
     */
    public U deriveLinear(final double scaleFactor, final String derivedId, final String derivedName,
            final UnitSystem derivedUnitSystem)
    {
        return deriveLinear(scaleFactor, derivedId, derivedName, derivedUnitSystem, null, null);
    }

    /**
     * Create a linearly scaled version of this unit. The unitSystem will be copied. The scale field will be filled with the
     * correct scaleFactor. Note that the unit that is used for derivation can already have a scaleFactor.
     * @param scaleFactor double; the linear scale factor of the unit
     * @param derivedId String; the new id of the derived unit
     * @param derivedName String; the new name of the derived unit
     * @return U; a linearly scaled instance of this unit with new id, abbreviation, name, and unit system
     * @throws UnitRuntimeException when cloning fails
     */
    public U deriveLinear(final double scaleFactor, final String derivedId, final String derivedName)
    {
        return deriveLinear(scaleFactor, derivedId, derivedName, getUnitSystem());
    }

    /**
     * Create a Builder. Override at subclasses to create extended builder.
     * @return an instance of a builder.
     */
    public Builder<U> makeBuilder()
    {
        return new Builder<U>();
    }

    /**
     * Create or lookup a unit based on given SI dimensions. E.g., a unit with dimensions 1/s^2 or kg.m/s^2.
     * @param siDimensions SIDimensions; the vector with the dimensionality of the unit
     * @return SIUnit; an SIUnit object with the right dimensions
     */
    @SuppressWarnings("unchecked")
    public static SIUnit lookupOrCreateUnitWithSIDimensions(final SIDimensions siDimensions)
    {
        Throw.whenNull(siDimensions, "siDimensions cannot be null");

        Quantity<SIUnit> quantity = null;
        SIUnit unit = null;

        Set<Quantity<?>> baseUnitSet = Quantities.INSTANCE.getQuantities(siDimensions);
        for (Quantity<?> bu : baseUnitSet)
        {
            if (bu.getStandardUnit().getClass().equals(Unit.class))
            {
                quantity = (Quantity<SIUnit>) bu;
            }
        }

        if (quantity == null)
        {
            quantity = new Quantity<SIUnit>(siDimensions.toString(), siDimensions);
            Builder<SIUnit> builder = new Builder<>();
            builder.setId(siDimensions.toString(true, true));
            builder.setName(siDimensions.toString(true, true));
            builder.setQuantity(quantity);
            builder.setScale(IdentityScale.SCALE);
            builder.setGenerated(true);
            builder.setUnitSystem(UnitSystem.SI_DERIVED);
            builder.setSiPrefixes(SIPrefixes.NONE, 1.0);
            unit = new SIUnit();
            unit.build(builder); // it will be registered at the BaseUnit
        }
        else
        {
            unit = quantity.getStandardUnit();
        }

        return unit;
    }

    /**
     * Retrieve the unit id.
     * @return String; the unit id
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * Retrieve a safe copy of the unit abbreviations.
     * @return Set&lt;String&gt;; the unit abbreviations
     */
    public Set<String> getAbbreviations()
    {
        return new LinkedHashSet<>(this.abbreviations);
    }

    /**
     * Retrieve the default abbreviation.
     * @return String; the default abbreviation
     */
    public String getDefaultDisplayAbbreviation()
    {
        return this.defaultDisplayAbbreviation;
    }

    /**
     * Retrieve the default textual abbreviation.
     * @return String; the default textual abbreviation
     */
    public String getDefaultTextualAbbreviation()
    {
        return this.defaultTextualAbbreviation;
    }

    /**
     * Retrieve the name of this unit.
     * @return String; the name of this unit
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Retrieve the scale of this unit.
     * @return Scale; the scale of this unit
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

    /**
     * Retrieve the unit base of this unit.
     * @return BaseUnit&lt;U&gt;; the unit base of this unit. if this unit is itself a unit base; the returned value is
     *         <code>null</code>
     */
    public Quantity<U> getQuantity()
    {
        return this.quantity;
    }

    /**
     * Indicate whether is unit was automatically generated.
     * @return boolean; true if this unit has been automatically generate; false if it was not automatically generated
     */
    public boolean isGenerated()
    {
        return this.generated;
    }

    /**
     * Indicate whether this unit has the standard SI signature.
     * @return boolean; true if this unit has the standard SI signature; false if this unit does not have the standard SI
     *         signature
     */
    public boolean isBaseSIUnit()
    {
        return this.baseSIUnit;
    }

    /**
     * Retrieve the standard unit (SI Unit) belonging to this unit.
     * @return U; the standard unit (SI unit) belonging to this unit
     */
    public U getStandardUnit()
    {
        return getQuantity().getStandardUnit();
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public U clone() throws CloneNotSupportedException
    {
        return (U) super.clone();
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.abbreviations == null) ? 0 : this.abbreviations.hashCode());
        result = prime * result + ((this.quantity == null) ? 0 : this.quantity.hashCode());
        result = prime * result + ((this.defaultDisplayAbbreviation == null) ? 0 : this.defaultDisplayAbbreviation.hashCode());
        result = prime * result + ((this.defaultTextualAbbreviation == null) ? 0 : this.defaultTextualAbbreviation.hashCode());
        result = prime * result + (this.generated ? 1231 : 1237);
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.scale == null) ? 0 : this.scale.hashCode());
        result = prime * result + ((this.unitSystem == null) ? 0 : this.unitSystem.hashCode());
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
        Unit<?> other = (Unit<?>) obj;
        if (this.abbreviations == null)
        {
            if (other.abbreviations != null)
                return false;
        }
        else if (!this.abbreviations.equals(other.abbreviations))
            return false;
        if (this.quantity == null)
        {
            if (other.quantity != null)
                return false;
        }
        else if (!this.quantity.equals(other.quantity))
            return false;
        if (this.defaultDisplayAbbreviation == null)
        {
            if (other.defaultDisplayAbbreviation != null)
                return false;
        }
        else if (!this.defaultDisplayAbbreviation.equals(other.defaultDisplayAbbreviation))
            return false;
        if (this.defaultTextualAbbreviation == null)
        {
            if (other.defaultTextualAbbreviation != null)
                return false;
        }
        else if (!this.defaultTextualAbbreviation.equals(other.defaultTextualAbbreviation))
            return false;
        if (this.generated != other.generated)
            return false;
        if (this.id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.name == null)
        {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.scale == null)
        {
            if (other.scale != null)
                return false;
        }
        else if (!this.scale.equals(other.scale))
            return false;
        if (this.unitSystem == null)
        {
            if (other.unitSystem != null)
                return false;
        }
        else if (!this.unitSystem.equals(other.unitSystem))
            return false;
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return this.defaultDisplayAbbreviation;
    }

    /**
     * The class that contains the information to build a unit.
     * <p>
     * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
     * <p>
     * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
     * @param <U> the unit for which the builder contains the information.
     */
    public static class Builder<U extends Unit<U>> implements Serializable
    {
        /** ... */
        private static final long serialVersionUID = 20191018L;

        /** The id of the unit; has to be unique within the unit name. Used for, e.g., localization and retrieval. */
        private String id;

        /** The abbreviations in the default locale. All abbreviations an be used in the valueOf() and of() methods. */
        private Set<String> additionalAbbreviations = new LinkedHashSet<>();

        /** The default abbreviation in the default locale for printing. Included in the abbreviations list. */
        private String defaultDisplayAbbreviation;

        /** The default textual abbreviation in the default locale for data entry. Included in the abbreviations list. */
        private String defaultTextualAbbreviation;

        /** The full name of the unit in the default locale. */
        private String name;

        /** The scale to use to convert between this unit and the standard (e.g., SI, BASE) unit. */
        private Scale scale;

        /** The unit system, e.g. SI or Imperial. */
        private UnitSystem unitSystem;

        /** Whether or not the unit supports SI prefixes from "yotta" (y) to "yocto" (Y). */
        private SIPrefixes siPrefixes;

        /** The power factor of the SI prefixes, e.g. 2.0 for square meters and 3.0 for cubic meters. */
        private double siPrefixPower = 1.0;

        /** Whether the unit has been automatically generated or not. */
        private boolean generated = false;

        /**
         * The corresponding unit base that contains all registered units for the unit as well as SI dimension information. The
         * unit base should never be null.
         */
        private Quantity<U> quantity;

        /**
         * Empty constructor. Content is generated through chaining: new
         * Unit.Builder&lt;TypeUnit&gt;().setId("id").setName("name");
         */
        public Builder()
        {
            // Empty constructor. Content is generated through (chaining of) method calls
        }

        /**
         * Return whether SI prefixes, ranging from yotta (y) to yocto (Y), will be generated.
         * @return siPrefixes, NONE (e.g., for inch), ALL (e.g., for meter) or KILO (e.g., for kilometer)
         */
        public SIPrefixes getSiPrefixes()
        {
            return this.siPrefixes;
        }

        /**
         * Return the power factor of the SI prefixes, e.g. 2.0 for square meters and 3.0 for cubic meters.
         * @return siPrefixPower double; power factor of the SI prefixes, e.g. 2.0 for square meters and 3.0 for cubic meters
         */
        public double getSiPrefixPowerFactor()
        {
            return this.siPrefixPower;
        }

        /**
         * Set whether SI prefixes, ranging from yotta (y) to yocto (Y), are allowed. If not set; this property defaults to
         * <code>false</code>.
         * @param newSiPrefixes SIPrefixes; SIPrefixes set siPrefixes, NONE (e.g., for inch), ALL (e.g., for meter) or KILO
         *            (e.g., for kilometer)
         * @param power double; power factor of the SI prefixes, e.g. 2.0 for square meters and 3.0 for cubic meters
         * @return Builder; this builder instance that is being constructed (for method call chaining)
         */
        public Builder<U> setSiPrefixes(final SIPrefixes newSiPrefixes, final double power)
        {
            this.siPrefixes = newSiPrefixes;
            this.siPrefixPower = power;
            return this;
        }

        /**
         * Retrieve the id of the unit that this builder builds.
         * @return String; the id of the unit that this builder builds
         */
        public String getId()
        {
            return this.id;
        }

        /**
         * Set the id of the unit that this builder builds.
         * @param newId String; set the id of the unit that this builder builds (must be set; the default is <code>null</code>
         *            which is invalid)
         * @return Builder; this builder instance that is being constructed (for method call chaining)
         */
        public Builder<U> setId(final String newId)
        {
            this.id = newId;
            return this;
        }

        /**
         * Retrieve the additional abbreviations.
         * @return Set&lt;String&gt;; the additional abbreviations
         */
        public Set<String> getAdditionalAbbreviations()
        {
            return this.additionalAbbreviations;
        }

        /**
         * Set the additional abbreviations.
         * @param newAdditionalAbbreviations String...; the additional abbreviations
         * @return Builder; this builder instance that is being constructed (for method call chaining)
         */
        public Builder<U> setAdditionalAbbreviations(final String... newAdditionalAbbreviations)
        {
            this.additionalAbbreviations = new LinkedHashSet<>(Arrays.asList(newAdditionalAbbreviations)); // safe copy
            return this;
        }

        /**
         * Retrieve the default display abbreviation.
         * @return String; the default display abbreviation
         */
        public String getDefaultDisplayAbbreviation()
        {
            return this.defaultDisplayAbbreviation;
        }

        /**
         * Set the default display abbreviation.
         * @param newDefaultDisplayAbbreviation String; the default display abbreviation
         * @return Builder; this builder instance that is being constructed (for method call chaining)
         */
        public Builder<U> setDefaultDisplayAbbreviation(final String newDefaultDisplayAbbreviation)
        {
            this.defaultDisplayAbbreviation = newDefaultDisplayAbbreviation;
            return this;
        }

        /**
         * Retrieve the default textual abbreviation.
         * @return String; the default textual abbreviation
         */
        public String getDefaultTextualAbbreviation()
        {
            return this.defaultTextualAbbreviation;
        }

        /**
         * Set the default textual abbreviation.
         * @param newDefaultTextualAbbreviation String; the default textual abbreviation
         * @return Builder; this builder instance that is being constructed (for method call chaining)
         */
        public Builder<U> setDefaultTextualAbbreviation(final String newDefaultTextualAbbreviation)
        {
            this.defaultTextualAbbreviation = newDefaultTextualAbbreviation;
            return this;
        }

        /**
         * Return the name.
         * @return String; the name
         */
        public String getName()
        {
            return this.name;
        }

        /**
         * Set the name.
         * @param newName String; the name
         * @return Builder; this builder instance that is being constructed (for method call chaining)
         */
        public Builder<U> setName(final String newName)
        {
            this.name = newName;
            return this;
        }

        /**
         * Retrieve the scale.
         * @return Scale; the scale
         */
        public Scale getScale()
        {
            return this.scale;
        }

        /**
         * Set the scale.
         * @param newScale Scale; set the scale
         * @return Builder; this builder instance that is being constructed (for method call chaining)
         */
        public Builder<U> setScale(final Scale newScale)
        {
            this.scale = newScale;
            return this;
        }

        /**
         * Retrieve the unit system.
         * @return unitSystem UnitSystem; the unit system
         */
        public UnitSystem getUnitSystem()
        {
            return this.unitSystem;
        }

        /**
         * Set the unit system.
         * @param newUnitSystem UnitSystem; the unit system
         * @return Builder; this builder instance that is being constructed (for method call chaining)
         */
        public Builder<U> setUnitSystem(final UnitSystem newUnitSystem)
        {
            this.unitSystem = newUnitSystem;
            return this;
        }

        /**
         * Retrieve the generated flag.
         * @return generated Boolean; the generated flag
         */
        public boolean isGenerated()
        {
            return this.generated;
        }

        /**
         * Set the generated flag. Defaults to false. Should be set for units that are automatically generated.
         * @param newGenerated boolean; the value for the generated flag
         * @return Builder; this builder instance that is being constructed (for method call chaining)
         */
        public Builder<U> setGenerated(final boolean newGenerated)
        {
            this.generated = newGenerated;
            return this;
        }

        /**
         * Retrieve the unit base.
         * @return baseUnit BaseUnit&lt;U&gt;; the unit base
         */
        public Quantity<U> getQuantity()
        {
            return this.quantity;
        }

        /**
         * Set the unit base. Can never be null and has to be filled.
         * @param newQuantity Quantity&lt;U&gt;; the unit base
         * @return Builder; this builder instance that is being constructed (for method call chaining)
         */
        public Builder<U> setQuantity(final Quantity<U> newQuantity)
        {
            this.quantity = newQuantity;
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public String toString()
        {
            return "Builder [id=" + this.id + ", name=" + this.name + ", scale=" + this.scale + "]";
        }

    }

    /**
     * Find or create a unit for the given SI dimensions.
     * @param unitString String; the textual representation of the unit
     * @return SIUnit; the unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIUnit getUnit(final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing SIVector: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing SIVector: empty unitString");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return unit;
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing SIVector with unit " + unitString);
    }

}
