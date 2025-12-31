package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Electric charge denotes the electrostatic attraction or repulsion in the presence of other matter with charge, and is
 * expressed in coulomb.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ElectricCharge extends Quantity.Relative<ElectricCharge, ElectricCharge.Unit>
{
    /** Constant with value zero. */
    public static final ElectricCharge ZERO = ElectricCharge.ofSi(0.0);

    /** Constant with value one. */
    public static final ElectricCharge ONE = ElectricCharge.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricCharge NaN = ElectricCharge.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricCharge POSITIVE_INFINITY = ElectricCharge.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricCharge NEGATIVE_INFINITY = ElectricCharge.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final ElectricCharge POS_MAXVALUE = ElectricCharge.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricCharge NEG_MAXVALUE = ElectricCharge.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a ElectricCharge quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public ElectricCharge(final double value, final ElectricCharge.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a ElectricCharge quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public ElectricCharge(final double value, final String abbreviation)
    {
        this(value, Units.resolve(ElectricCharge.Unit.class, abbreviation));
    }

    /**
     * Construct ElectricCharge quantity.
     * @param value Scalar from which to construct this instance
     */
    public ElectricCharge(final ElectricCharge value)
    {
        super(value.si(), ElectricCharge.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a ElectricCharge instance based on an SI value.
     * @param si the si value
     * @return the ElectricCharge instance based on an SI value
     */
    public static ElectricCharge ofSi(final double si)
    {
        return new ElectricCharge(si, ElectricCharge.Unit.SI);
    }

    @Override
    public ElectricCharge instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return ElectricCharge.Unit.SI_UNIT;
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
     * Returns a ElectricCharge based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricCharge of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of ElectricCharge and ElectricCharge, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of ElectricCharge and ElectricCharge
     */
    public final Dimensionless divide(final ElectricCharge v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the division of ElectricCharge and Duration, which results in a ElectricCurrent scalar.
     * @param v scalar
     * @return scalar as a division of ElectricCharge and Duration
     */
    public final ElectricCurrent divide(final Duration v)
    {
        return new ElectricCurrent(this.si() / v.si(), ElectricCurrent.Unit.SI);
    }

    /**
     * Calculate the division of ElectricCharge and ElectricCurrent, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of ElectricCharge and ElectricCurrent
     */
    public final Duration divide(final ElectricCurrent v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the division of ElectricCharge and ElectricPotential, which results in a ElectricalCapacitance scalar.
     * @param v scalar
     * @return scalar as a division of ElectricCharge and ElectricPotential
     */
    public final ElectricalCapacitance divide(final ElectricPotential v)
    {
        return new ElectricalCapacitance(this.si() / v.si(), ElectricalCapacitance.Unit.SI);
    }

    /**
     * Calculate the division of ElectricCharge and ElectricalCapacitance, which results in a ElectricPotential scalar.
     * @param v scalar
     * @return scalar as a division of ElectricCharge and ElectricalCapacitance
     */
    public final ElectricPotential divide(final ElectricalCapacitance v)
    {
        return new ElectricPotential(this.si() / v.si(), ElectricPotential.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * ElectricCharge.Unit is a unit of electric charge and is expressed in Coulomb.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<ElectricCharge.Unit, ElectricCharge>
    {
        /** The dimensions of electric charge, the Coulumb, is A.s. */
        public static final SIUnit SI_UNIT = SIUnit.of("As");

        /** Gray. */
        public static final ElectricCharge.Unit COULOMB = new ElectricCharge.Unit("C", "coulomb", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final ElectricCharge.Unit SI = COULOMB.generateSiPrefixes(false, false);

        /** milliCoulomb = mA.s. */
        public static final ElectricCharge.Unit MILLICOULOMB = Units.resolve(ElectricCharge.Unit.class, "mC");

        /** microCoulomb = muA.s. */
        public static final ElectricCharge.Unit MICROCOULOMB = Units.resolve(ElectricCharge.Unit.class, "muC");

        /** ampere hour. */
        public static final ElectricCharge.Unit AMPERE_HOUR =
                COULOMB.deriveUnit("Ah", "ampere hour", 3600.0, UnitSystem.SI_DERIVED);

        /** milliampere hour. */
        public static final ElectricCharge.Unit MILLIAMPERE_HOUR =
                AMPERE_HOUR.deriveUnit("mAh", "milliampere hour", 1E-3, UnitSystem.SI_DERIVED);

        /** milliampere second. */
        public static final ElectricCharge.Unit MILLIAMPERE_SECOND =
                MILLIAMPERE_HOUR.deriveUnit("mAs", "milliampere second", 1.0 / 3600.0, UnitSystem.SI_DERIVED);

        /** kiloampere hour. */
        public static final ElectricCharge.Unit KILOAMPERE_HOUR =
                AMPERE_HOUR.deriveUnit("kAh", "kiloampere hour", 1E3, UnitSystem.SI_DERIVED);

        /** megaampere hour. */
        public static final ElectricCharge.Unit MEGAAMPERE_HOUR =
                AMPERE_HOUR.deriveUnit("MAh", "megaampere hour", 1E6, UnitSystem.SI_DERIVED);

        /** Faraday. */
        public static final ElectricCharge.Unit FARADAY = COULOMB.deriveUnit("F", "faraday", 96485.3383, UnitSystem.OTHER);

        /** atomic unit of charge. This value is exact since the 2019 redefinition of the SI base units. */
        public static final ElectricCharge.Unit ATOMIC_UNIT =
                COULOMB.deriveUnit("e", "elementary unit of charge", 1.602176634E-19, UnitSystem.SI_ACCEPTED);

        /** statcoulomb (CGS ESU). */
        public static final ElectricCharge.Unit STATCOULOMB =
                COULOMB.deriveUnit("statC", "statcoulomb", 3.335641E-10, UnitSystem.CGS_ESU);

        /** franklin (CGS ESU). */
        public static final ElectricCharge.Unit FRANKLIN = STATCOULOMB.deriveUnit("Fr", "franklin", 1.0, UnitSystem.CGS_ESU);

        /** esu (CGS ESU). */
        public static final ElectricCharge.Unit ESU =
                STATCOULOMB.deriveUnit("esu", "electrostatic unit", 1.0, UnitSystem.CGS_ESU);

        /** abcoulomb (CGS EMU). */
        public static final ElectricCharge.Unit ABCOULOMB = COULOMB.deriveUnit("abC", "abcoulomb", 10.0, UnitSystem.CGS_EMU);

        /** emu (CGS EMU). */
        public static final ElectricCharge.Unit EMU =
                ABCOULOMB.deriveUnit("emu", "electromagnetic unit", 1.0, UnitSystem.CGS_EMU);

        /**
         * Create a new ElectricCharge unit.
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
        public ElectricCharge ofSi(final double si)
        {
            return ElectricCharge.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new ElectricCharge.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
