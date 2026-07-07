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
 * ElectricPotential is the difference in electric potential energy per unit of electric charge between two points in a static
 * electric field.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ElectricPotential extends Quantity<ElectricPotential>
{
    /** Constant with value zero. */
    public static final ElectricPotential ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final ElectricPotential ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricPotential NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricPotential POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricPotential NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final ElectricPotential POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricPotential NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a ElectricPotential quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public ElectricPotential(final double value, final ElectricPotential.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a ElectricPotential quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public ElectricPotential(final double valueInUnit, final ElectricPotential.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a ElectricPotential instance based on an SI value.
     * @param si the si value
     * @return the ElectricPotential instance based on an SI value
     */
    public static ElectricPotential ofSi(final double si)
    {
        return new ElectricPotential(si, ElectricPotential.Unit.SI, true);
    }

    /**
     * Instantiate a ElectricPotential quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the ElectricPotential instance based on an SI value with the given display unit
     */
    public static ElectricPotential ofSi(final double siValue, final ElectricPotential.Unit displayUnit)
    {
        return new ElectricPotential(siValue, displayUnit, true);
    }

    @Override
    public ElectricPotential instantiateSi(final double siValue, final UnitInterface<ElectricPotential> displayUnit)
    {
        return new ElectricPotential(siValue, (ElectricPotential.Unit) displayUnit, true);
    }

    /**
     * Returns a ElectricPotential representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricPotential
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricPotential valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a ElectricPotential based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab ElectricPotential representation of the value in its unit
     */
    public static ElectricPotential of(final double valueInUnit, final ElectricPotential.Unit unit)
    {
        return new ElectricPotential(valueInUnit, unit);
    }

    /**
     * Returns a ElectricPotential based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricPotential of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public ElectricPotential.Unit getDisplayUnit()
    {
        return (ElectricPotential.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of ElectricPotential and ElectricPotential, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of ElectricPotential and ElectricPotential
     */
    public Dimensionless divide(final ElectricPotential v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of ElectricPotential and ElectricCurrent, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricPotential and ElectricCurrent
     */
    public Power multiply(final ElectricCurrent v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the division of ElectricPotential and ElectricCurrent, which results in a ElectricalResistance scalar.
     * @param v scalar
     * @return scalar as a division of ElectricPotential and ElectricCurrent
     */
    public ElectricalResistance divide(final ElectricCurrent v)
    {
        return new ElectricalResistance(this.si() / v.si(), ElectricalResistance.Unit.SI);
    }

    /**
     * Calculate the division of ElectricPotential and ElectricalResistance, which results in a ElectricCurrent scalar.
     * @param v scalar
     * @return scalar as a division of ElectricPotential and ElectricalResistance
     */
    public ElectricCurrent divide(final ElectricalResistance v)
    {
        return new ElectricCurrent(this.si() / v.si(), ElectricCurrent.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * ElectricPotential.Unit encodes the units of electric potential (difference)
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<ElectricPotential>
    {
        /** The dimensions of the electric potential: kgm2/s3A. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm2/s3A");

        /** Gray. */
        public static final ElectricPotential.Unit V = new ElectricPotential.Unit("V", "V", "volt", IdentityScale.SCALE,
                UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final ElectricPotential.Unit SI = (Unit) V.generateSiPrefixes(false, false);

        /** microvolt. */
        public static final ElectricPotential.Unit muV = Units.resolve(ElectricPotential.Unit.class, "muV");

        /** millivolt. */
        public static final ElectricPotential.Unit mV = Units.resolve(ElectricPotential.Unit.class, "mV");

        /** kilovolt. */
        public static final ElectricPotential.Unit kV = Units.resolve(ElectricPotential.Unit.class, "kV");

        /** megavolt. */
        public static final ElectricPotential.Unit MV = Units.resolve(ElectricPotential.Unit.class, "MV");

        /** gigavolt. */
        public static final ElectricPotential.Unit GV = Units.resolve(ElectricPotential.Unit.class, "GV");

        /** statvolt. */
        public static final ElectricPotential.Unit statV =
                V.deriveUnit("statV", "statV", "statvolt", 299.792458, UnitSystem.CGS_ESU, null);

        /** abvolt. */
        public static final ElectricPotential.Unit abV = V.deriveUnit("abV", "abV", "abvolt", 1.0E-8, UnitSystem.CGS_EMU, null);

        /**
         * Create a new ElectricPotential unit.
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
        public ElectricPotential ofSi(final double si, final UnitInterface<ElectricPotential> displayUnit)
        {
            return new ElectricPotential(si, (Unit) displayUnit, true);
        }

        @Override
        public ElectricPotential.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

        @Override
        public ElectricPotential.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new ElectricPotential.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
