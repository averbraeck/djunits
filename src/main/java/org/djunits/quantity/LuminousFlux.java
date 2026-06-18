package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Luminous flux is the total perceived power of light emitted by a source, measured in lumens (lm).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class LuminousFlux extends Quantity<LuminousFlux>
{
    /** Constant with value zero. */
    public static final LuminousFlux ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final LuminousFlux ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final LuminousFlux NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final LuminousFlux POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final LuminousFlux NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final LuminousFlux POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final LuminousFlux NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a LuminousFlux quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public LuminousFlux(final double value, final LuminousFlux.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a LuminousFlux quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public LuminousFlux(final double valueInUnit, final LuminousFlux.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a LuminousFlux instance based on an SI value.
     * @param si the si value
     * @return the LuminousFlux instance based on an SI value
     */
    public static LuminousFlux ofSi(final double si)
    {
        return new LuminousFlux(si, LuminousFlux.Unit.SI, true);
    }

    /**
     * Instantiate a LuminousFlux quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the LuminousFlux instance based on an SI value with the given display unit
     */
    public static LuminousFlux ofSi(final double siValue, final LuminousFlux.Unit displayUnit)
    {
        return new LuminousFlux(siValue, displayUnit, true);
    }

    @Override
    public LuminousFlux instantiateSi(final double siValue, final UnitInterface<LuminousFlux> displayUnit)
    {
        return new LuminousFlux(siValue, (LuminousFlux.Unit) displayUnit, true);
    }

    /**
     * Returns a LuminousFlux representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a LuminousFlux
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static LuminousFlux valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a LuminousFlux based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab LuminousFlux representation of the value in its unit
     */
    public static LuminousFlux of(final double valueInUnit, final LuminousFlux.Unit unit)
    {
        return new LuminousFlux(valueInUnit, unit);
    }

    /**
     * Returns a LuminousFlux based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static LuminousFlux of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public LuminousFlux.Unit getDisplayUnit()
    {
        return (LuminousFlux.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of LuminousFlux and LuminousFlux, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of LuminousFlux and LuminousFlux
     */
    public final Dimensionless divide(final LuminousFlux v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the division of LuminousFlux and Area, which results in a Illuminance scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and Area
     */
    public final Illuminance divide(final Area v)
    {
        return new Illuminance(this.si() / v.si(), Illuminance.Unit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and Illuminance, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and Illuminance
     */
    public final Area divide(final Illuminance v)
    {
        return new Area(this.si() / v.si(), Area.Unit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and LuminousIntensity, which results in a SolidAngle scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and LuminousIntensity
     */
    public final SolidAngle divide(final LuminousIntensity v)
    {
        return new SolidAngle(this.si() / v.si(), SolidAngle.Unit.SI);
    }

    /**
     * Calculate the division of LuminousFlux and SolidAngle, which results in a LuminousIntensity scalar.
     * @param v scalar
     * @return scalar as a division of LuminousFlux and SolidAngle
     */
    public final LuminousIntensity divide(final SolidAngle v)
    {
        return new LuminousIntensity(this.si() / v.si(), LuminousIntensity.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * LuminousFlux.Unit encodes the units of total perceived power of light emitted by a source.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<LuminousFlux>
    {
        /** The dimensions of luminous flux: cd.sr. */
        public static final SIUnit SI_UNIT = SIUnit.of("cdsr");

        /** Lumen. */
        public static final LuminousFlux.Unit lm = new LuminousFlux.Unit("lm", "lumen", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final LuminousFlux.Unit SI = lm;

        /**
         * Create a new LuminousFlux unit.
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
        public LuminousFlux ofSi(final double si, final UnitInterface<LuminousFlux> displayUnit)
        {
            return new LuminousFlux(si, (Unit) displayUnit, true);
        }

        @Override
        public LuminousFlux.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new LuminousFlux.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
