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
 * Electrical resistence measures the opposition to the flow of an electric current, and is expressed in ohm. Its reciprocal
 * quantity is electrical conductance (expressed in siemens).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ElectricalResistance extends Quantity<ElectricalResistance>
{
    /** Constant with value zero. */
    public static final ElectricalResistance ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final ElectricalResistance ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricalResistance NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricalResistance POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricalResistance NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final ElectricalResistance POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricalResistance NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a ElectricalResistance quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public ElectricalResistance(final double value, final ElectricalResistance.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a ElectricalResistance quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public ElectricalResistance(final double valueInUnit, final ElectricalResistance.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a ElectricalResistance instance based on an SI value.
     * @param si the si value
     * @return the ElectricalResistance instance based on an SI value
     */
    public static ElectricalResistance ofSi(final double si)
    {
        return new ElectricalResistance(si, ElectricalResistance.Unit.SI, true);
    }

    /**
     * Instantiate a ElectricalResistance quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the ElectricalResistance instance based on an SI value with the given display unit
     */
    public static ElectricalResistance ofSi(final double siValue, final ElectricalResistance.Unit displayUnit)
    {
        return new ElectricalResistance(siValue, displayUnit, true);
    }

    @Override
    public ElectricalResistance instantiateSi(final double siValue, final UnitInterface<ElectricalResistance> displayUnit)
    {
        return new ElectricalResistance(siValue, (ElectricalResistance.Unit) displayUnit, true);
    }

    /**
     * Returns a ElectricalResistance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricalResistance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalResistance valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a ElectricalResistance based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab ElectricalResistance representation of the value in its unit
     */
    public static ElectricalResistance of(final double valueInUnit, final ElectricalResistance.Unit unit)
    {
        return new ElectricalResistance(valueInUnit, unit);
    }

    /**
     * Returns a ElectricalResistance based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalResistance of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public ElectricalResistance.Unit getDisplayUnit()
    {
        return (ElectricalResistance.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of ElectricalResistance and ElectricalResistance, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of ElectricalResistance and ElectricalResistance
     */
    public Dimensionless divide(final ElectricalResistance v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of ElectricalResistance and ElectricalConductance, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalResistance and ElectricalConductance
     */
    public Dimensionless multiply(final ElectricalConductance v)
    {
        return new Dimensionless(this.si() * v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of ElectricalResistance and ElectricCurrent, which results in a ElectricPotential scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalResistance and ElectricCurrent
     */
    public ElectricPotential multiply(final ElectricCurrent v)
    {
        return new ElectricPotential(this.si() * v.si(), ElectricPotential.Unit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalResistance and Duration, which results in a ElectricalInductance scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalResistance and Duration
     */
    public ElectricalInductance multiply(final Duration v)
    {
        return new ElectricalInductance(this.si() * v.si(), ElectricalInductance.Unit.SI);
    }

    @Override
    public ElectricalConductance reciprocal()
    {
        return ElectricalConductance.ofSi(1.0 / this.si());
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * ElectricalResistance.Unit encodes the opposition to the flow of electric current.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<ElectricalResistance>
    {
        /** The dimensions of the electrical resistance: kgm2/s3A2. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm2/s3A2");

        /** Ohm. */
        public static final ElectricalResistance.Unit ohm = new ElectricalResistance.Unit("ohm", "\u03A9", "ohm",
                IdentityScale.SCALE, UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final ElectricalResistance.Unit SI = (Unit) ohm.generateSiPrefixes(false, false);

        /** micro-ohm. */
        public static final ElectricalResistance.Unit muohm = Units.resolve(ElectricalResistance.Unit.class, "muohm");

        /** milli-ohm. */
        public static final ElectricalResistance.Unit mohm = Units.resolve(ElectricalResistance.Unit.class, "mohm");

        /** kilo-ohm. */
        public static final ElectricalResistance.Unit kohm = Units.resolve(ElectricalResistance.Unit.class, "kohm");

        /** mega-ohm. */
        public static final ElectricalResistance.Unit Mohm = Units.resolve(ElectricalResistance.Unit.class, "Mohm");

        /** giga-ohm. */
        public static final ElectricalResistance.Unit Gohm = Units.resolve(ElectricalResistance.Unit.class, "Gohm");

        /** ab-ohm. */
        public static final ElectricalResistance.Unit abohm =
                ohm.deriveUnit("abohm", "ab\u03A9", "abohm", 1.0E-9, UnitSystem.CGS_EMU, null);

        /** stat-ohm. */
        public static final ElectricalResistance.Unit statohm =
                ohm.deriveUnit("stohm", "st\u03A9", "statohm", 8.987551787E11, UnitSystem.CGS_EMU, null);

        /**
         * Create a new ElectricalResistance unit.
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
        public ElectricalResistance ofSi(final double si, final UnitInterface<ElectricalResistance> displayUnit)
        {
            return new ElectricalResistance(si, (Unit) displayUnit, true);
        }

        @Override
        public ElectricalResistance.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

        @Override
        public ElectricalResistance.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new ElectricalResistance.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
