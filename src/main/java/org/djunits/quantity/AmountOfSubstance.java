package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Amount of substance is the quantity representing the number of entities, measured in moles (mol).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class AmountOfSubstance extends Quantity.Relative<AmountOfSubstance, AmountOfSubstance.Unit>
{
    /** Constant with value zero. */
    public static final AmountOfSubstance ZERO = AmountOfSubstance.ofSi(0.0);

    /** Constant with value one. */
    public static final AmountOfSubstance ONE = AmountOfSubstance.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final AmountOfSubstance NaN = AmountOfSubstance.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final AmountOfSubstance POSITIVE_INFINITY = AmountOfSubstance.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final AmountOfSubstance NEGATIVE_INFINITY = AmountOfSubstance.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final AmountOfSubstance POS_MAXVALUE = AmountOfSubstance.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final AmountOfSubstance NEG_MAXVALUE = AmountOfSubstance.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a AmountOfSubstance quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public AmountOfSubstance(final double value, final AmountOfSubstance.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a AmountOfSubstance quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public AmountOfSubstance(final double value, final String abbreviation)
    {
        this(value, Units.resolve(AmountOfSubstance.Unit.class, abbreviation));
    }

    /**
     * Construct AmountOfSubstance quantity.
     * @param value Scalar from which to construct this instance
     */
    public AmountOfSubstance(final AmountOfSubstance value)
    {
        super(value.si(), AmountOfSubstance.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a AmountOfSubstance instance based on an SI value.
     * @param si the si value
     * @return the AmountOfSubstance instance based on an SI value
     */
    public static AmountOfSubstance ofSi(final double si)
    {
        return new AmountOfSubstance(si, AmountOfSubstance.Unit.SI);
    }

    @Override
    public AmountOfSubstance instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return AmountOfSubstance.Unit.SI_UNIT;
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
     * Returns a AmountOfSubstance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static AmountOfSubstance of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of AmountOfSubstance and AmountOfSubstance, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of AmountOfSubstance and AmountOfSubstance
     */
    public final Dimensionless divide(final AmountOfSubstance v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the division of AmountOfSubstance and CatalyticActivity, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of AmountOfSubstance and CatalyticActivity
     */
    public final Duration divide(final CatalyticActivity v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the division of AmountOfSubstance and Duration, which results in a CatalyticActivity scalar.
     * @param v scalar
     * @return scalar as a division of AmountOfSubstance and Duration
     */
    public final CatalyticActivity divide(final Duration v)
    {
        return new CatalyticActivity(this.si() / v.si(), CatalyticActivity.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * AmountOfSubstance.Unit encodes the units of amount of substance (base unit is mol).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<AmountOfSubstance.Unit, AmountOfSubstance>
    {
        /** The dimensions of AmountOfSubstance: mol. */
        public static final SIUnit SI_UNIT = SIUnit.of("mol");

        /** Mole. */
        public static final AmountOfSubstance.Unit MOLE = new AmountOfSubstance.Unit("mol", "mole", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final AmountOfSubstance.Unit SI = MOLE.generateSiPrefixes(false, false);

        /** mmol. */
        public static final AmountOfSubstance.Unit MILLIMOLE = Units.resolve(AmountOfSubstance.Unit.class, "mmol");

        /** &#181;mol. */
        public static final AmountOfSubstance.Unit MICROMOLE = Units.resolve(AmountOfSubstance.Unit.class, "mumol");

        /** nmol. */
        public static final AmountOfSubstance.Unit NANOMOLE = Units.resolve(AmountOfSubstance.Unit.class, "nmol");

        /**
         * Create a new AmountOfSubstance unit.
         * @param id the id or main abbreviation of the unit
         * @param name the full name of the unit
         * @param scaleFactorToBaseUnit the scale factor of the unit to convert it TO the base (SI) unit
         * @param unitSystem the unit system such as SI or IMPERIAL
         */
        public Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
        {
            super(id, name, new LinearScale(scaleFactorToBaseUnit), unitSystem);
        }

        /**
         * Return a derived unit for this unit, with textual abbreviation(s) and a display abbreviation.
         * @param textualAbbreviation the textual abbreviation of the unit, which doubles as the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         */
        public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name, final Scale scale,
                final UnitSystem unitSystem)
        {
            super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem);
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
        public AmountOfSubstance ofSi(final double si)
        {
            return AmountOfSubstance.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new AmountOfSubstance.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
