package org.djunits.unit;

import java.io.Serializable;

import org.djunits.Throw;
import org.djunits.unit.quantity.Quantity;
import org.djunits.unit.scale.OffsetLinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.unitsystem.UnitSystem;
import org.djunits.unit.util.UnitRuntimeException;

/**
 * The AbsoluteUnit class indicates that a unit is absolute and has a "zero" point. The relative unit base will be set correctly
 * for each derived unit, as this is one of the fields that will be cloned to create a derived unit.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <AU> the specific unit
 * @param <RU> the corresponding relative unit
 */
public abstract class AbsoluteLinearUnit<AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>> extends Unit<AU>
        implements Serializable
{
    /** */
    private static final long serialVersionUID = 20190826L;

    /** The relative unit belonging to this unit. */
    private Quantity<RU> relativeQuantity;

    /** The corresponding relative unit with the same unit scale factor. */
    private RU relativeUnit;

    /**
     * Return the corresponding relative unit base.
     * @return Unit&lt;RU&gt;; the the corresponding relative unit base
     */
    public Quantity<RU> getRelativeQuantity()
    {
        return this.relativeQuantity;
    }

    /**
     * Return the corresponding relative unit.
     * @return Unit&lt;RU&gt;; the the corresponding relative unit
     */
    public RU getRelativeUnit()
    {
        return this.relativeUnit;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public AU build(final Unit.Builder<AU> builder) throws UnitRuntimeException
    {
        if (builder instanceof Builder)
        {
            this.relativeUnit = ((Builder<AU, RU>) builder).getRelativeUnit();
        }
        else
        {
            throw new UnitRuntimeException("Trying to build an absolute unit with a non-absolute builder");
        }
        Throw.whenNull(this.relativeUnit, "Relative unit for unit " + builder.getId() + " cannot be null");
        this.relativeQuantity = this.relativeUnit.getQuantity();
        AU unit = super.build(builder);
        return unit;
    }

    /**
     * Create a linearly scaled version of this unit with an offset. The scale field will be filled with the correct
     * scaleFactor. Note that the unit that is used for derivation is currently not allowed to already have a scaleFactor and an
     * offset. <br>
     * When this has to be implemented later, it might work as follows: when we have an original scale with offset o1 and
     * scalefactor f1, the calculation to the unit base is valueSI = (value1 + o1) * f1. So the offset is expressed in the
     * "unit" of the value. If we combine a second scale factor for a derived unit with offset o2 and scalefactor f2, we need to
     * calculate the ultimate scale to the base (si) unit. The factor then becomes: <code>value1 = (value2 + o2) * f2</code>,
     * and therefore <code>valueSI =
     * ((value2 + o2) * f2 + o1) * f1</code>, which can also be written as
     * <code>value2 * f1 * f2 + f1 * f2 * o2 + f1 * o1</code>.
     * @param scaleFactor double; the linear scale factor of the unit
     * @param offset double; the offset to use for the scale
     * @param derivedRelativeUnit RU; the corresponding relative unit with the same scale factor
     * @param derivedId String; the new id of the derived unit
     * @param derivedName String; the new name of the derived unit
     * @param derivedUnitSystem UnitSystem; the unit system of the derived unit
     * @param derivedDefaultDisplayAbbreviation String; the default abbreviation to use in e.g, the toString() method. Can be
     *            null.
     * @param derivedDefaultTextualAbbreviation String; the default textual abbreviation to use in, e.g, typing. Can be null.
     * @param derivedAbbreviations String...; String... any other valid abbreviations for the unit, e.g. {"h", "hr", "hour"}.
     *            Can be empty.
     * @return AU; a linearly scaled instance of this unit with new id, abbreviation, name, and unit system
     * @throws UnitRuntimeException when cloning fails
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public AU deriveLinearOffset(final double scaleFactor, final double offset, final RU derivedRelativeUnit,
            final String derivedId, final String derivedName, final UnitSystem derivedUnitSystem,
            final String derivedDefaultDisplayAbbreviation, final String derivedDefaultTextualAbbreviation,
            final String... derivedAbbreviations)
    {
        String cName = getClass().getSimpleName();
        Throw.whenNull(derivedId, "deriving unit from %s.%s; derivedId cannot be null", cName, getId());
        Throw.whenNull(derivedName, "deriving unit from %s.%s; derivedName cannot be null", cName, getId());
        Throw.whenNull(derivedUnitSystem, "deriving unit from %s.%s; derivedUnitSystem cannot be null", cName, getId());
        if (getScale().getOffsetToStandardUnit() != 0.0 || getScale().getConversionFactorToStandardUnit() != 1.0)
        {
            throw new UnitRuntimeException("Cannot derive from unit " + cName + "." + getId() + " with scale " + getScale()
                    + ". Scale should have no offset, and scale factor 1.0");
        }

        try
        {
            AU clone = clone();

            // make a builder and set values
            Builder<AU, RU> builder = makeBuilder();
            builder.setId(derivedId);
            builder.setName(derivedName);
            builder.setRelativeUnit(derivedRelativeUnit);
            builder.setQuantity(getQuantity());
            builder.setSiPrefixes(SIPrefixes.NONE, 1.0);
            builder.setScale(new OffsetLinearScale(scaleFactor, offset));
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
     * Create a linearly scaled version of this unit with an offset. The scale field will be filled with the correct
     * scaleFactor. Note that the unit that is used for derivation can already have a scaleFactor.
     * @param scaleFactor double; the linear scale factor of the unit
     * @param offset double; the offset to use for the scale
     * @param derivedRelativeUnit RU; the corresponding relative unit with the same scale factor
     * @param derivedId String; the new id of the derived unit
     * @param derivedName String; the new name of the derived unit
     * @param derivedUnitSystem UnitSystem; the unit system of the derived unit
     * @return AU; a linearly scaled instance of this unit with new id, abbreviation, name, and unit system
     * @throws UnitRuntimeException when cloning fails
     */
    public AU deriveLinearOffset(final double scaleFactor, final double offset, final RU derivedRelativeUnit,
            final String derivedId, final String derivedName, final UnitSystem derivedUnitSystem)
    {
        return deriveLinearOffset(scaleFactor, offset, derivedRelativeUnit, derivedId, derivedName, derivedUnitSystem, null,
                null);
    }

    /**
     * Create a linearly scaled version of this unit with an offset. The unitSystem will be copied. The scale field will be
     * filled with the correct scaleFactor. Note that the unit that is used for derivation can already have a scaleFactor.
     * @param scaleFactor double; the linear scale factor of the unit
     * @param offset double; the offset to use for the scale
     * @param derivedRelativeUnit RU; the corresponding relative unit with the same scale factor
     * @param derivedId String; the new id of the derived unit
     * @param derivedName String; the new name of the derived unit
     * @return AU; a linearly scaled instance of this unit with new id, abbreviation, name, and unit system
     * @throws UnitRuntimeException when cloning fails
     */
    public AU deriveLinearOffset(final double scaleFactor, final double offset, final RU derivedRelativeUnit,
            final String derivedId, final String derivedName)
    {
        return deriveLinearOffset(scaleFactor, offset, derivedRelativeUnit, derivedId, derivedName, getUnitSystem());
    }

    /** {@inheritDoc} */
    @Override
    public Builder<AU, RU> makeBuilder()
    {
        Builder<AU, RU> builder = new Builder<AU, RU>();
        builder.setRelativeUnit(getRelativeUnit());
        return builder;
    }

    /** {@inheritDoc} */
    @Override
    public OffsetLinearScale getScale()
    {
        return (OffsetLinearScale) super.getScale();
    }

    /**
     * Builder for the AbsoluteUnit. This builder forces a relative unit base to be set for the absolute unit as well.
     * Furthermore, it forces an OffsetLinearScale rather than a linear scale.
     * <p>
     * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
     * <p>
     * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
     * @param <AU> the specific unit for which this is the builder
     * @param <RU> the corresponding relative unit
     */
    public static class Builder<AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>> extends Unit.Builder<AU>
    {
        /** ... */
        private static final long serialVersionUID = 20191018L;

        /** The relative unit belonging to this unit. */
        private RU relativeUnit;

        /** {@inheritDoc} */
        @Override
        public OffsetLinearScale getScale()
        {
            return (OffsetLinearScale) super.getScale();
        }

        /** {@inheritDoc} */
        @Override
        public Builder<AU, RU> setScale(final Scale scale)
        {
            Throw.when(!(scale instanceof OffsetLinearScale), UnitRuntimeException.class,
                    "scale for an absolute unit (id=" + getId() + ") should be an offset scale");
            super.setScale(scale);
            return this;
        }

        /**
         * Retrieve the corresponding relative unit that has the same conversion factor.
         * @return Unit&lt;?&gt;; the the corresponding relative unit with the same conversion factor
         */
        public RU getRelativeUnit()
        {
            return this.relativeUnit;
        }

        /**
         * Sets the corresponding relative unit that has the same conversion factor.
         * @param newRelativeUnit RU; the the corresponding relative unit with the same conversion factor
         * @return Builder; this builder instance that is being constructed (for method call chaining)
         */
        public Builder<AU, RU> setRelativeUnit(final RU newRelativeUnit)
        {
            Throw.whenNull(newRelativeUnit, "Relative unit for unit id " + getId() + " cannot be null");
            this.relativeUnit = newRelativeUnit;
            return this;
        }

        // Other setters will have to be overridden to make them return this builder rather than the Unit.Builder.

        /** {@inheritDoc} */
        @Override
        public Builder<AU, RU> setSiPrefixes(final SIPrefixes siPrefixes, final double power)
        {
            super.setSiPrefixes(siPrefixes, power);
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public Builder<AU, RU> setId(final String id)
        {
            super.setId(id);
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public Builder<AU, RU> setAdditionalAbbreviations(final String... abbreviations)
        {
            super.setAdditionalAbbreviations(abbreviations);
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public Builder<AU, RU> setDefaultDisplayAbbreviation(final String defaultDisplayAbbreviation)
        {
            super.setDefaultDisplayAbbreviation(defaultDisplayAbbreviation);
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public Builder<AU, RU> setDefaultTextualAbbreviation(final String defaultTextualAbbreviation)
        {
            super.setDefaultTextualAbbreviation(defaultTextualAbbreviation);
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public Builder<AU, RU> setName(final String name)
        {
            super.setName(name);
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public Builder<AU, RU> setUnitSystem(final UnitSystem unitSystem)
        {
            super.setUnitSystem(unitSystem);
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public Builder<AU, RU> setGenerated(final boolean generated)
        {
            super.setGenerated(generated);
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public Builder<AU, RU> setQuantity(final Quantity<AU> baseUnit)
        {
            super.setQuantity(baseUnit);
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public String toString()
        {
            return "Builder [relativeUnit=" + this.relativeUnit + ", getId()=" + this.getId() + ", getName()=" + this.getName()
                    + ", getQuantity()=" + this.getQuantity() + "]";
        }

    }

}
