package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Radioactivity is the rate of nuclear decay events, measured in becquerels (Bq).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class RadioActivity extends Quantity<RadioActivity, RadioActivity.Unit>
{
    /** Constant with value zero. */
    public static final RadioActivity ZERO = RadioActivity.ofSi(0.0);

    /** Constant with value one. */
    public static final RadioActivity ONE = RadioActivity.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final RadioActivity NaN = RadioActivity.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final RadioActivity POSITIVE_INFINITY = RadioActivity.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final RadioActivity NEGATIVE_INFINITY = RadioActivity.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final RadioActivity POS_MAXVALUE = RadioActivity.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final RadioActivity NEG_MAXVALUE = RadioActivity.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a RadioActivity quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public RadioActivity(final double value, final RadioActivity.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a RadioActivity quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public RadioActivity(final double value, final String abbreviation)
    {
        this(value, Units.resolve(RadioActivity.Unit.class, abbreviation));
    }

    /**
     * Construct RadioActivity quantity.
     * @param value Scalar from which to construct this instance
     */
    public RadioActivity(final RadioActivity value)
    {
        super(value.si(), RadioActivity.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a RadioActivity instance based on an SI value.
     * @param si the si value
     * @return the RadioActivity instance based on an SI value
     */
    public static RadioActivity ofSi(final double si)
    {
        return new RadioActivity(si, RadioActivity.Unit.SI);
    }

    @Override
    public RadioActivity instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return RadioActivity.Unit.SI_UNIT;
    }

    /**
     * Returns a RadioActivity representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a RadioActivity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static RadioActivity valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a RadioActivity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static RadioActivity of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of RadioActivity and RadioActivity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of RadioActivity and RadioActivity
     */
    public final Dimensionless divide(final RadioActivity v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * RadioActivity.Unit encodes the units of absorbed dose (of ionizing radiation).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<RadioActivity.Unit, RadioActivity>
    {
        /** The dimensions of radioactivity: /s. */
        public static final SIUnit SI_UNIT = SIUnit.of("/s");

        /** Becquerel. */
        public static final RadioActivity.Unit BECQUEREL =
                new RadioActivity.Unit("Bq", "becquerel", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final RadioActivity.Unit SI = BECQUEREL.generateSiPrefixes(false, false);

        /** kBq. */
        public static final RadioActivity.Unit KILOBECQUEREL = Units.resolve(RadioActivity.Unit.class, "kBq");

        /** MBq. */
        public static final RadioActivity.Unit MEGABECQUEREL = Units.resolve(RadioActivity.Unit.class, "MBq");

        /** GBq. */
        public static final RadioActivity.Unit GIGABECQUEREL = Units.resolve(RadioActivity.Unit.class, "GBq");

        /** TBq. */
        public static final RadioActivity.Unit TERABECQUEREL = Units.resolve(RadioActivity.Unit.class, "TBq");

        /** Curie. */
        public static final RadioActivity.Unit CURIE = BECQUEREL.deriveUnit("Ci", "curie", 3.7E10, UnitSystem.OTHER);

        /** milliCurie. */
        public static final RadioActivity.Unit MILLICURIE = CURIE.deriveUnit("mCi", "millicurie", 1.0E-3, UnitSystem.OTHER);

        /** microCurie. */
        public static final RadioActivity.Unit MICROCURIE =
                CURIE.deriveUnit("muCi", "\u03BCCi", "microcurie", 1.0E-6, UnitSystem.OTHER);

        /** nanoCurie. */
        public static final RadioActivity.Unit NANOCURIE = CURIE.deriveUnit("nCi", "nanocurie", 1.0E-9, UnitSystem.OTHER);

        /** Rutherford. */
        public static final RadioActivity.Unit RUTHERFORD = BECQUEREL.deriveUnit("Rd", "rutherford", 1.0E6, UnitSystem.OTHER);

        /**
         * Create a new RadioActivity unit.
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
        public RadioActivity ofSi(final double si)
        {
            return RadioActivity.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new RadioActivity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
