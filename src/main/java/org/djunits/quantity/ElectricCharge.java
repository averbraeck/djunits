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
 * Electric charge denotes the electrostatic attraction or repulsion in the presence of other matter with charge, and is
 * expressed in coulomb.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ElectricCharge extends Quantity<ElectricCharge>
{
    /** Constant with value zero. */
    public static final ElectricCharge ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final ElectricCharge ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricCharge NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricCharge POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricCharge NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final ElectricCharge POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricCharge NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a ElectricCharge quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public ElectricCharge(final double value, final ElectricCharge.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a ElectricCharge quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public ElectricCharge(final double valueInUnit, final ElectricCharge.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a ElectricCharge instance based on an SI value.
     * @param si the si value
     * @return the ElectricCharge instance based on an SI value
     */
    public static ElectricCharge ofSi(final double si)
    {
        return new ElectricCharge(si, ElectricCharge.Unit.SI, true);
    }

    /**
     * Instantiate a ElectricCharge quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the ElectricCharge instance based on an SI value with the given display unit
     */
    public static ElectricCharge ofSi(final double siValue, final ElectricCharge.Unit displayUnit)
    {
        return new ElectricCharge(siValue, displayUnit, true);
    }

    @Override
    public ElectricCharge instantiateSi(final double siValue, final UnitInterface<ElectricCharge> displayUnit)
    {
        return new ElectricCharge(siValue, (ElectricCharge.Unit) displayUnit, true);
    }

    /**
     * Returns a ElectricCharge representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricCharge
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricCharge valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a ElectricCharge based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab ElectricCharge representation of the value in its unit
     */
    public static ElectricCharge of(final double valueInUnit, final ElectricCharge.Unit unit)
    {
        return new ElectricCharge(valueInUnit, unit);
    }

    /**
     * Returns a ElectricCharge based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricCharge of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public ElectricCharge.Unit getDisplayUnit()
    {
        return (ElectricCharge.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of ElectricCharge and ElectricCharge, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of ElectricCharge and ElectricCharge
     */
    public Dimensionless divide(final ElectricCharge v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the division of ElectricCharge and Duration, which results in a ElectricCurrent scalar.
     * @param v scalar
     * @return scalar as a division of ElectricCharge and Duration
     */
    public ElectricCurrent divide(final Duration v)
    {
        return new ElectricCurrent(this.si() / v.si(), ElectricCurrent.Unit.SI);
    }

    /**
     * Calculate the division of ElectricCharge and ElectricCurrent, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of ElectricCharge and ElectricCurrent
     */
    public Duration divide(final ElectricCurrent v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the division of ElectricCharge and ElectricPotential, which results in a ElectricalCapacitance scalar.
     * @param v scalar
     * @return scalar as a division of ElectricCharge and ElectricPotential
     */
    public ElectricalCapacitance divide(final ElectricPotential v)
    {
        return new ElectricalCapacitance(this.si() / v.si(), ElectricalCapacitance.Unit.SI);
    }

    /**
     * Calculate the division of ElectricCharge and ElectricalCapacitance, which results in a ElectricPotential scalar.
     * @param v scalar
     * @return scalar as a division of ElectricCharge and ElectricalCapacitance
     */
    public ElectricPotential divide(final ElectricalCapacitance v)
    {
        return new ElectricPotential(this.si() / v.si(), ElectricPotential.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * ElectricCharge.Unit is a unit of electric charge and is expressed in Coulomb.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<ElectricCharge>
    {
        /** The dimensions of electric charge, the Coulumb, is A.s. */
        public static final SIUnit SI_UNIT = SIUnit.of("As");

        /** Gray. */
        public static final ElectricCharge.Unit C = new ElectricCharge.Unit("C", "C", "coulomb", IdentityScale.SCALE,
                UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final ElectricCharge.Unit SI = (Unit) C.generateSiPrefixes(false, false);

        /** milliCoulomb = mA.s. */
        public static final ElectricCharge.Unit mC = Units.resolve(ElectricCharge.Unit.class, "mC");

        /** microCoulomb = muA.s. */
        public static final ElectricCharge.Unit muC = Units.resolve(ElectricCharge.Unit.class, "muC");

        /** ampere hour. */
        public static final ElectricCharge.Unit Ah = C.deriveUnit("Ah", "ampere hour", 3600.0, UnitSystem.SI_DERIVED);

        /** milliampere hour. */
        public static final ElectricCharge.Unit mAh = Ah.deriveUnit("mAh", "milliampere hour", 1E-3, UnitSystem.SI_DERIVED);

        /** milliampere second. */
        public static final ElectricCharge.Unit mAs =
                mAh.deriveUnit("mAs", "milliampere second", 1.0 / 3600.0, UnitSystem.SI_DERIVED);

        /** kiloampere hour. */
        public static final ElectricCharge.Unit kAh = Ah.deriveUnit("kAh", "kiloampere hour", 1E3, UnitSystem.SI_DERIVED);

        /** megaampere hour. */
        public static final ElectricCharge.Unit MAh = Ah.deriveUnit("MAh", "megaampere hour", 1E6, UnitSystem.SI_DERIVED);

        /** Faraday. */
        public static final ElectricCharge.Unit F = C.deriveUnit("F", "faraday", 96485.3383, UnitSystem.OTHER);

        /** atomic unit of charge. This value is exact since the 2019 redefinition of the SI base units. */
        public static final ElectricCharge.Unit e =
                C.deriveUnit("e", "elementary unit of charge", 1.602176634E-19, UnitSystem.SI_ACCEPTED);

        /** statcoulomb (CGS ESU). */
        public static final ElectricCharge.Unit statC = C.deriveUnit("statC", "statcoulomb", 3.335641E-10, UnitSystem.CGS_ESU);

        /** franklin (CGS ESU). */
        public static final ElectricCharge.Unit Fr = statC.deriveUnit("Fr", "franklin", 1.0, UnitSystem.CGS_ESU);

        /** esu (CGS ESU). */
        public static final ElectricCharge.Unit esu = statC.deriveUnit("esu", "electrostatic unit", 1.0, UnitSystem.CGS_ESU);

        /** abcoulomb (CGS EMU). */
        public static final ElectricCharge.Unit abC = C.deriveUnit("abC", "abcoulomb", 10.0, UnitSystem.CGS_EMU);

        /** emu (CGS EMU). */
        public static final ElectricCharge.Unit emu = abC.deriveUnit("emu", "electromagnetic unit", 1.0, UnitSystem.CGS_EMU);

        /**
         * Create a new ElectricCharge unit.
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
        public ElectricCharge ofSi(final double si, final UnitInterface<ElectricCharge> displayUnit)
        {
            return new ElectricCharge(si, (Unit) displayUnit, true);
        }

        @Override
        public ElectricCharge.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new ElectricCharge.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

        @Override
        public ElectricCharge.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

    }

}
