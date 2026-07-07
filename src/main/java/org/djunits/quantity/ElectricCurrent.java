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
 * ElectricCurrent is the net rate of flow of electric charge through a surface.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ElectricCurrent extends Quantity<ElectricCurrent>
{
    /** Constant with value zero. */
    public static final ElectricCurrent ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final ElectricCurrent ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricCurrent NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricCurrent POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricCurrent NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final ElectricCurrent POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricCurrent NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a ElectricCurrent quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public ElectricCurrent(final double value, final ElectricCurrent.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a ElectricCurrent quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public ElectricCurrent(final double valueInUnit, final ElectricCurrent.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a ElectricCurrent instance based on an SI value.
     * @param si the si value
     * @return the ElectricCurrent instance based on an SI value
     */
    public static ElectricCurrent ofSi(final double si)
    {
        return new ElectricCurrent(si, ElectricCurrent.Unit.SI, true);
    }

    /**
     * Instantiate a ElectricCurrent quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the ElectricCurrent instance based on an SI value with the given display unit
     */
    public static ElectricCurrent ofSi(final double siValue, final ElectricCurrent.Unit displayUnit)
    {
        return new ElectricCurrent(siValue, displayUnit, true);
    }

    @Override
    public ElectricCurrent instantiateSi(final double siValue, final UnitInterface<ElectricCurrent> displayUnit)
    {
        return new ElectricCurrent(siValue, (ElectricCurrent.Unit) displayUnit, true);
    }

    /**
     * Returns a ElectricCurrent representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricCurrent
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricCurrent valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a ElectricCurrent based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab ElectricCurrent representation of the value in its unit
     */
    public static ElectricCurrent of(final double valueInUnit, final ElectricCurrent.Unit unit)
    {
        return new ElectricCurrent(valueInUnit, unit);
    }

    /**
     * Returns a ElectricCurrent based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricCurrent of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public ElectricCurrent.Unit getDisplayUnit()
    {
        return (ElectricCurrent.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of ElectricCurrent and ElectricCurrent, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of ElectricCurrent and ElectricCurrent
     */
    public Dimensionless divide(final ElectricCurrent v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and ElectricalPotential, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalCurrent and ElectricalPotential
     */
    public Power multiply(final ElectricPotential v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and Duration, which results in a ElectricalCharge scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalCurrent and Duration
     */
    public ElectricCharge multiply(final Duration v)
    {
        return new ElectricCharge(this.si() * v.si(), ElectricCharge.Unit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and ElectricalResistance, which results in a ElectricalPotential
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalCurrent and ElectricalResistance
     */
    public ElectricPotential multiply(final ElectricalResistance v)
    {
        return new ElectricPotential(this.si() * v.si(), ElectricPotential.Unit.SI);
    }

    /**
     * Calculate the division of ElectricalCurrent and ElectricalPotential, which results in a ElectricalConductance scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCurrent and ElectricalPotential
     */
    public ElectricalConductance divide(final ElectricPotential v)
    {
        return new ElectricalConductance(this.si() / v.si(), ElectricalConductance.Unit.SI);
    }

    /**
     * Calculate the division of ElectricalCurrent and ElectricalConductance, which results in a ElectricalPotential scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCurrent and ElectricalConductance
     */
    public ElectricPotential divide(final ElectricalConductance v)
    {
        return new ElectricPotential(this.si() / v.si(), ElectricPotential.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * ElectricCurrent.Unit encodes the units of electric current (A).
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<ElectricCurrent>
    {
        /** The dimensions of electric current: A. */
        public static final SIUnit SI_UNIT = SIUnit.of("A");

        /** ampere. */
        public static final ElectricCurrent.Unit A = new ElectricCurrent.Unit("A", "A", "ampere", IdentityScale.SCALE,
                UnitSystem.SI_BASE, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final ElectricCurrent.Unit SI = (Unit) A.generateSiPrefixes(false, false);

        /** microampere. */
        public static final ElectricCurrent.Unit muA = Units.resolve(ElectricCurrent.Unit.class, "muA");

        /** milliampere. */
        public static final ElectricCurrent.Unit mA = Units.resolve(ElectricCurrent.Unit.class, "mA");

        /** kiloampere. */
        public static final ElectricCurrent.Unit kA = Units.resolve(ElectricCurrent.Unit.class, "kA");

        /** megaampere. */
        public static final ElectricCurrent.Unit MA = Units.resolve(ElectricCurrent.Unit.class, "MA");

        /** statampere (GCS ESU). */
        public static final ElectricCurrent.Unit statA =
                A.deriveUnit("statA", "statA", "statampere", 3.335641E-10, UnitSystem.CGS_ESU, null);

        /** abampere (GCS EMU). */
        public static final ElectricCurrent.Unit abA = A.deriveUnit("abA", "abA", "abampere", 10.0, UnitSystem.CGS_EMU, null);

        /**
         * Create a new ElectricCurrent unit.
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
        public ElectricCurrent ofSi(final double si, final UnitInterface<ElectricCurrent> displayUnit)
        {
            return new ElectricCurrent(si, (Unit) displayUnit, true);
        }

        @Override
        public ElectricCurrent.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

        @Override
        public ElectricCurrent.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new ElectricCurrent.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
