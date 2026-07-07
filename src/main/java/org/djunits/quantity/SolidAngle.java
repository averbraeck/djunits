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
 * Solid angle is the two-dimensional angle in three-dimensional space, measured in steradians (sr).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class SolidAngle extends Quantity<SolidAngle>
{
    /** Constant with value zero. */
    public static final SolidAngle ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final SolidAngle ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final SolidAngle NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final SolidAngle POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final SolidAngle NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final SolidAngle POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final SolidAngle NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a SolidAngle quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public SolidAngle(final double value, final SolidAngle.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a SolidAngle quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public SolidAngle(final double valueInUnit, final SolidAngle.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a SolidAngle instance based on an SI value.
     * @param si the si value
     * @return the SolidAngle instance based on an SI value
     */
    public static SolidAngle ofSi(final double si)
    {
        return new SolidAngle(si, SolidAngle.Unit.SI, true);
    }

    /**
     * Instantiate a SolidAngle quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the SolidAngle instance based on an SI value with the given display unit
     */
    public static SolidAngle ofSi(final double siValue, final SolidAngle.Unit displayUnit)
    {
        return new SolidAngle(siValue, displayUnit, true);
    }

    @Override
    public SolidAngle instantiateSi(final double siValue, final UnitInterface<SolidAngle> displayUnit)
    {
        return new SolidAngle(siValue, (SolidAngle.Unit) displayUnit, true);
    }

    /**
     * Returns a SolidAngle representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a SolidAngle
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static SolidAngle valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a SolidAngle based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab SolidAngle representation of the value in its unit
     */
    public static SolidAngle of(final double valueInUnit, final SolidAngle.Unit unit)
    {
        return new SolidAngle(valueInUnit, unit);
    }

    /**
     * Returns a SolidAngle based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SolidAngle of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public SolidAngle.Unit getDisplayUnit()
    {
        return (SolidAngle.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of SolidAngle and SolidAngle, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of SolidAngle and SolidAngle
     */
    public Dimensionless divide(final SolidAngle v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of SolidAngle and LuminousIntensity, which results in a LuminousFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of SolidAngle and LuminousIntensity
     */
    public LuminousFlux multiply(final LuminousIntensity v)
    {
        return new LuminousFlux(this.si() * v.si(), LuminousFlux.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * SolidAngle.Unit encodes the units of solid angles.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<SolidAngle>
    {
        /** The dimensions of the solid angle: sr. */
        public static final SIUnit SI_UNIT = SIUnit.of("sr");

        /** Steradian. */
        public static final SolidAngle.Unit sr = new SolidAngle.Unit("sr", "steradian", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final SolidAngle.Unit SI = sr;

        /** square degree. */
        public static final SolidAngle.Unit sq_deg = sr.deriveUnit("sq.deg", "sq.deg", "square degree",
                (Math.PI / 180.0) * (Math.PI / 180.0), UnitSystem.SI_DERIVED, null);

        /**
         * Create a new SolidAngle unit.
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
        public SolidAngle ofSi(final double si, final UnitInterface<SolidAngle> displayUnit)
        {
            return new SolidAngle(si, (Unit) displayUnit, true);
        }

        @Override
        public SolidAngle.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new SolidAngle.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
