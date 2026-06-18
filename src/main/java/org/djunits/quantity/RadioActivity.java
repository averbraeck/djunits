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
 * Radioactivity is the rate of nuclear decay events, measured in becquerels (Bq).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class RadioActivity extends Quantity<RadioActivity>
{
    /** Constant with value zero. */
    public static final RadioActivity ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final RadioActivity ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final RadioActivity NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final RadioActivity POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final RadioActivity NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final RadioActivity POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final RadioActivity NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a RadioActivity quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public RadioActivity(final double value, final RadioActivity.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a RadioActivity quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public RadioActivity(final double valueInUnit, final RadioActivity.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a RadioActivity instance based on an SI value.
     * @param si the si value
     * @return the RadioActivity instance based on an SI value
     */
    public static RadioActivity ofSi(final double si)
    {
        return new RadioActivity(si, RadioActivity.Unit.SI, true);
    }

    /**
     * Instantiate a RadioActivity quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the RadioActivity instance based on an SI value with the given display unit
     */
    public static RadioActivity ofSi(final double siValue, final RadioActivity.Unit displayUnit)
    {
        return new RadioActivity(siValue, displayUnit, true);
    }

    @Override
    public RadioActivity instantiateSi(final double siValue, final UnitInterface<RadioActivity> displayUnit)
    {
        return new RadioActivity(siValue, (RadioActivity.Unit) displayUnit, true);
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
     * Returns a RadioActivity based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab RadioActivity representation of the value in its unit
     */
    public static RadioActivity of(final double valueInUnit, final RadioActivity.Unit unit)
    {
        return new RadioActivity(valueInUnit, unit);
    }

    /**
     * Returns a RadioActivity based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static RadioActivity of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public RadioActivity.Unit getDisplayUnit()
    {
        return (RadioActivity.Unit) super.getDisplayUnit();
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
     * RadioActivity.Unit encodes the units of radioactivity.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<RadioActivity>
    {
        /** The dimensions of radioactivity: /s. */
        public static final SIUnit SI_UNIT = SIUnit.of("/s");

        /** Becquerel. */
        public static final RadioActivity.Unit Bq = new RadioActivity.Unit("Bq", "Bq", "becquerel", IdentityScale.SCALE,
                UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final RadioActivity.Unit SI = (Unit) Bq.generateSiPrefixes(false, false);

        /** kBq. */
        public static final RadioActivity.Unit kBq = Units.resolve(RadioActivity.Unit.class, "kBq");

        /** MBq. */
        public static final RadioActivity.Unit MBq = Units.resolve(RadioActivity.Unit.class, "MBq");

        /** GBq. */
        public static final RadioActivity.Unit GBq = Units.resolve(RadioActivity.Unit.class, "GBq");

        /** TBq. */
        public static final RadioActivity.Unit TBq = Units.resolve(RadioActivity.Unit.class, "TBq");

        /** Curie. */
        public static final RadioActivity.Unit Ci = Bq.deriveUnit("Ci", "curie", 3.7E10, UnitSystem.OTHER);

        /** milliCurie. */
        public static final RadioActivity.Unit mCi = Ci.deriveUnit("mCi", "millicurie", 1.0E-3, UnitSystem.OTHER);

        /** microCurie. */
        public static final RadioActivity.Unit muCi =
                Ci.deriveUnit("muCi", "\u03BCCi", "microcurie", 1.0E-6, UnitSystem.OTHER, null);

        /** nanoCurie. */
        public static final RadioActivity.Unit nCi = Ci.deriveUnit("nCi", "nanocurie", 1.0E-9, UnitSystem.OTHER);

        /** Rutherford. */
        public static final RadioActivity.Unit Rd = Bq.deriveUnit("Rd", "rutherford", 1.0E6, UnitSystem.OTHER);

        /**
         * Create a new RadioActivity unit.
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
        public RadioActivity ofSi(final double si, final UnitInterface<RadioActivity> displayUnit)
        {
            return new RadioActivity(si, (Unit) displayUnit, true);
        }

        @Override
        public RadioActivity.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new RadioActivity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

        @Override
        public RadioActivity.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

    }

}
