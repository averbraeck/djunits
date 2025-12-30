package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Equivalent dose is a measure of radiation exposure accounting for biological effect, expressed in sieverts (Sv).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class EquivalentDose extends Quantity.Relative<EquivalentDose, EquivalentDose.Unit>
{
    /** Constant with value zero. */
    public static final EquivalentDose ZERO = EquivalentDose.ofSi(0.0);

    /** Constant with value one. */
    public static final EquivalentDose ONE = EquivalentDose.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final EquivalentDose NaN = EquivalentDose.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final EquivalentDose POSITIVE_INFINITY = EquivalentDose.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final EquivalentDose NEGATIVE_INFINITY = EquivalentDose.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final EquivalentDose POS_MAXVALUE = EquivalentDose.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final EquivalentDose NEG_MAXVALUE = EquivalentDose.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a EquivalentDose quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public EquivalentDose(final double value, final EquivalentDose.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a EquivalentDose quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public EquivalentDose(final double value, final String abbreviation)
    {
        this(value, Units.resolve(EquivalentDose.Unit.class, abbreviation));
    }

    /**
     * Construct EquivalentDose quantity.
     * @param value Scalar from which to construct this instance
     */
    public EquivalentDose(final EquivalentDose value)
    {
        super(value.si(), EquivalentDose.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a EquivalentDose instance based on an SI value.
     * @param si the si value
     * @return the EquivalentDose instance based on an SI value
     */
    public static EquivalentDose ofSi(final double si)
    {
        return new EquivalentDose(si, EquivalentDose.Unit.SI);
    }

    @Override
    public EquivalentDose instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return EquivalentDose.Unit.SI_UNIT;
    }

    /**
     * Returns a EquivalentDose representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a EquivalentDose
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static EquivalentDose valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a EquivalentDose based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static EquivalentDose of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of EquivalentDose and EquivalentDose, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of EquivalentDose and EquivalentDose
     */
    public final Dimensionless divide(final EquivalentDose v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * EquivalentDose.Unit encodes the unit of radiation exposure.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<EquivalentDose.Unit, EquivalentDose>
    {
        /** The dimensions of the equivalent dose: m2/s2. */
        public static final SIUnit SI_UNIT = SIUnit.of("m2/s2");

        /** Sievert. */
        public static final EquivalentDose.Unit SIEVERT = new EquivalentDose.Unit("Sv", "sievert", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final EquivalentDose.Unit SI = SIEVERT.generateSiPrefixes(false, false);

        /** mSv. */
        public static final EquivalentDose.Unit MILLISIEVERT = Units.resolve(EquivalentDose.Unit.class, "mSv");

        /** &#181;Sv. */
        public static final EquivalentDose.Unit MICROSIEVERT = Units.resolve(EquivalentDose.Unit.class, "muSv");

        /** rem. (stands for röntgen equivalent man). */
        public static final EquivalentDose.Unit REM = SIEVERT.deriveUnit("rem", "rem", 0.01, UnitSystem.CGS);

        /**
         * Create a new EquivalentDose unit.
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
        public EquivalentDose ofSi(final double si)
        {
            return EquivalentDose.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new EquivalentDose.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
