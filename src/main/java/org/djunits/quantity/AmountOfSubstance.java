package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Amount of substance is the quantity representing the number of entities, measured in moles (mol).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AmountOfSubstance extends Quantity<AmountOfSubstance>
{
    /** Constant with value zero. */
    public static final AmountOfSubstance ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final AmountOfSubstance ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final AmountOfSubstance NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final AmountOfSubstance POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final AmountOfSubstance NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final AmountOfSubstance POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final AmountOfSubstance NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a AmountOfSubstance quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public AmountOfSubstance(final double value, final AmountOfSubstance.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a AmountOfSubstance quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public AmountOfSubstance(final double valueInUnit, final AmountOfSubstance.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a AmountOfSubstance instance based on an SI value.
     * @param si the si value
     * @return the AmountOfSubstance instance based on an SI value
     */
    public static AmountOfSubstance ofSi(final double si)
    {
        return new AmountOfSubstance(si, AmountOfSubstance.Unit.SI, true);
    }

    /**
     * Instantiate a AmountOfSubstance quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the AmountOfSubstance instance based on an SI value with the given display unit
     */
    public static AmountOfSubstance ofSi(final double siValue, final AmountOfSubstance.Unit displayUnit)
    {
        return new AmountOfSubstance(siValue, displayUnit, true);
    }

    @Override
    public AmountOfSubstance instantiateSi(final double siValue, final UnitInterface<AmountOfSubstance> displayUnit)
    {
        return new AmountOfSubstance(siValue, (AmountOfSubstance.Unit) displayUnit, true);
    }

    /**
     * Returns a AmountOfSubstance representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a AmountOfSubstance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static AmountOfSubstance valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a AmountOfSubstance based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab AmountOfSubstance representation of the value in its unit
     */
    public static AmountOfSubstance of(final double valueInUnit, final AmountOfSubstance.Unit unit)
    {
        return new AmountOfSubstance(valueInUnit, unit);
    }

    /**
     * Returns a AmountOfSubstance based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AmountOfSubstance of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public AmountOfSubstance.Unit getDisplayUnit()
    {
        return (AmountOfSubstance.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of AmountOfSubstance and AmountOfSubstance, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of AmountOfSubstance and AmountOfSubstance
     */
    public Dimensionless divide(final AmountOfSubstance v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the division of AmountOfSubstance and CatalyticActivity, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of AmountOfSubstance and CatalyticActivity
     */
    public Duration divide(final CatalyticActivity v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the division of AmountOfSubstance and Duration, which results in a CatalyticActivity scalar.
     * @param v scalar
     * @return scalar as a division of AmountOfSubstance and Duration
     */
    public CatalyticActivity divide(final Duration v)
    {
        return new CatalyticActivity(this.si() / v.si(), CatalyticActivity.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * AmountOfSubstance.Unit encodes the units of amount of substance (base unit is mol).
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<AmountOfSubstance>
    {
        /** The dimensions of AmountOfSubstance: mol. */
        public static final SIUnit SI_UNIT = SIUnit.of("mol");

        /** Mole. */
        public static final AmountOfSubstance.Unit mol = new AmountOfSubstance.Unit("mol", "mol", "mole", IdentityScale.SCALE,
                UnitSystem.SI_BASE, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final AmountOfSubstance.Unit SI = (Unit) mol.generateSiPrefixes(false, false);

        /** mmol. */
        public static final AmountOfSubstance.Unit mmol = Units.resolve(AmountOfSubstance.Unit.class, "mmol");

        /** &#181;mol. */
        public static final AmountOfSubstance.Unit mumol = Units.resolve(AmountOfSubstance.Unit.class, "mumol");

        /** nmol. */
        public static final AmountOfSubstance.Unit nmol = Units.resolve(AmountOfSubstance.Unit.class, "nmol");

        /**
         * Create a new AmountOfSubstance unit.
         * @param id the id or main abbreviation of the unit
         * @param name the full name of the unit
         * @param scaleFactorToBaseUnit the scale factor of the unit to convert it TO the base (SI) unit
         * @param unitSystem the unit system such as SI or IMPERIAL
         */
        public Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
        {
            super(id, name, scaleFactorToBaseUnit, unitSystem);
        }

        /**
         * Return a derived unit for this unit, with textual abbreviation(s) and a display abbreviation.
         * @param textualAbbreviation the textual abbreviation of the unit, which doubles as the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert from this unit to the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         * @param siPrefix the SI Prefix of this unit
         */
        public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name, final Scale scale,
                final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem, siPrefix);
        }

        @Override
        public SIUnit siUnit()
        {
            return SI_UNIT;
        }

        @Override
        public Unit getBaseUnit()
        {
            return SI;
        }

        @Override
        public AmountOfSubstance ofSi(final double si, final UnitInterface<AmountOfSubstance> displayUnit)
        {
            return new AmountOfSubstance(si, (Unit) displayUnit, true);
        }

        @Override
        public AmountOfSubstance.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

        @Override
        public AmountOfSubstance.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new AmountOfSubstance.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
