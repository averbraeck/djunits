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
 * Inductance is the tendency of an electrical conductor to oppose a change in the electric current flowing through it.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ElectricalInductance extends Quantity<ElectricalInductance>
{
    /** Constant with value zero. */
    public static final ElectricalInductance ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final ElectricalInductance ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricalInductance NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricalInductance POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricalInductance NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final ElectricalInductance POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricalInductance NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a ElectricalInductance quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public ElectricalInductance(final double value, final ElectricalInductance.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a ElectricalInductance quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public ElectricalInductance(final double valueInUnit, final ElectricalInductance.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a ElectricalInductance instance based on an SI value.
     * @param si the si value
     * @return the ElectricalInductance instance based on an SI value
     */
    public static ElectricalInductance ofSi(final double si)
    {
        return new ElectricalInductance(si, ElectricalInductance.Unit.SI, true);
    }

    /**
     * Instantiate a ElectricalInductance quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the ElectricalInductance instance based on an SI value with the given display unit
     */
    public static ElectricalInductance ofSi(final double siValue, final ElectricalInductance.Unit displayUnit)
    {
        return new ElectricalInductance(siValue, displayUnit, true);
    }

    @Override
    public ElectricalInductance instantiateSi(final double siValue, final UnitInterface<ElectricalInductance> displayUnit)
    {
        return new ElectricalInductance(siValue, (ElectricalInductance.Unit) displayUnit, true);
    }

    /**
     * Returns a ElectricalInductance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricalInductance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalInductance valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a ElectricalInductance based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab ElectricalInductance representation of the value in its unit
     */
    public static ElectricalInductance of(final double valueInUnit, final ElectricalInductance.Unit unit)
    {
        return new ElectricalInductance(valueInUnit, unit);
    }

    /**
     * Returns a ElectricalInductance based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalInductance of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public ElectricalInductance.Unit getDisplayUnit()
    {
        return (ElectricalInductance.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of ElectricalInductance and ElectricalInductance, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of ElectricalInductance and ElectricalInductance
     */
    public final Dimensionless divide(final ElectricalInductance v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * ElectricalInductance.Unit encodes the units of electromagnetic inductance.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<ElectricalInductance>
    {
        /** The dimensions of electromagnetic induction: kgm2/s2A2. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm2/s2A2");

        /** henry. */
        public static final ElectricalInductance.Unit H = new ElectricalInductance.Unit("H", "H", "henry", IdentityScale.SCALE,
                UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final ElectricalInductance.Unit SI = (Unit) H.generateSiPrefixes(false, false);

        /**
         * Create a new ElectricalInductance unit.
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
        public ElectricalInductance ofSi(final double si, final UnitInterface<ElectricalInductance> displayUnit)
        {
            return new ElectricalInductance(si, (Unit) displayUnit, true);
        }

        @Override
        public ElectricalInductance.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new ElectricalInductance.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
