package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Luminous intensity is the luminous flux emitted per unit solid angle, measured in candelas (cd).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class LuminousIntensity extends Quantity<LuminousIntensity>
{
    /** Constant with value zero. */
    public static final LuminousIntensity ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final LuminousIntensity ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LuminousIntensity NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LuminousIntensity POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LuminousIntensity NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final LuminousIntensity POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final LuminousIntensity NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a LuminousIntensity quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public LuminousIntensity(final double value, final LuminousIntensity.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a LuminousIntensity quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public LuminousIntensity(final double valueInUnit, final LuminousIntensity.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a LuminousIntensity instance based on an SI value.
     * @param si the si value
     * @return the LuminousIntensity instance based on an SI value
     */
    public static LuminousIntensity ofSi(final double si)
    {
        return new LuminousIntensity(si, LuminousIntensity.Unit.SI, true);
    }

    /**
     * Instantiate a LuminousIntensity quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the LuminousIntensity instance based on an SI value with the given display unit
     */
    public static LuminousIntensity ofSi(final double siValue, final LuminousIntensity.Unit displayUnit)
    {
        return new LuminousIntensity(siValue, displayUnit, true);
    }

    @Override
    public LuminousIntensity instantiateSi(final double siValue, final UnitInterface<LuminousIntensity> displayUnit)
    {
        return new LuminousIntensity(siValue, (LuminousIntensity.Unit) displayUnit, true);
    }

    /**
     * Returns a LuminousIntensity representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a LuminousIntensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LuminousIntensity valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a LuminousIntensity based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab LuminousIntensity representation of the value in its unit
     */
    public static LuminousIntensity of(final double valueInUnit, final LuminousIntensity.Unit unit)
    {
        return new LuminousIntensity(valueInUnit, unit);
    }

    /**
     * Returns a LuminousIntensity based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LuminousIntensity of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public LuminousIntensity.Unit getDisplayUnit()
    {
        return (LuminousIntensity.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of LuminousIntensity and LuminousIntensity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of LuminousIntensity and LuminousIntensity
     */
    public Dimensionless divide(final LuminousIntensity v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of LuminousIntensity and SolidAngle, which results in a LuminousFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of LuminousIntensity and SolidAngle
     */
    public LuminousFlux multiply(final SolidAngle v)
    {
        return new LuminousFlux(this.si() * v.si(), LuminousFlux.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * LuminousIntensity.Unit encodes the units of luminous flux emitted per unit solid angle.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<LuminousIntensity>
    {
        /** The dimensions of the luminous flux emitted per unit solid angle: cd. */
        public static final SIUnit SI_UNIT = SIUnit.of("cd");

        /** Candela. */
        public static final LuminousIntensity.Unit cd = new LuminousIntensity.Unit("cd", "cd", "candela", IdentityScale.SCALE,
                UnitSystem.SI_BASE, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final LuminousIntensity.Unit SI = (Unit) cd.generateSiPrefixes(false, false);

        /**
         * Create a new LuminousIntensity unit.
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
        public LuminousIntensity ofSi(final double si, final UnitInterface<LuminousIntensity> displayUnit)
        {
            return new LuminousIntensity(si, (Unit) displayUnit, true);
        }

        @Override
        public LuminousIntensity.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

        @Override
        public LuminousIntensity.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new LuminousIntensity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
