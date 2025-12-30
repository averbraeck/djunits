package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * ElectricCurrent is the net rate of flow of electric charge through a surface.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ElectricCurrent extends Quantity.Relative<ElectricCurrent, ElectricCurrent.Unit>
{
    /** Constant with value zero. */
    public static final ElectricCurrent ZERO = ElectricCurrent.ofSi(0.0);

    /** Constant with value one. */
    public static final ElectricCurrent ONE = ElectricCurrent.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricCurrent NaN = ElectricCurrent.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricCurrent POSITIVE_INFINITY = ElectricCurrent.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricCurrent NEGATIVE_INFINITY = ElectricCurrent.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final ElectricCurrent POS_MAXVALUE = ElectricCurrent.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricCurrent NEG_MAXVALUE = ElectricCurrent.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a ElectricCurrent quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public ElectricCurrent(final double value, final ElectricCurrent.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a ElectricCurrent quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public ElectricCurrent(final double value, final String abbreviation)
    {
        this(value, Units.resolve(ElectricCurrent.Unit.class, abbreviation));
    }

    /**
     * Construct ElectricCurrent quantity.
     * @param value Scalar from which to construct this instance
     */
    public ElectricCurrent(final ElectricCurrent value)
    {
        super(value.si(), ElectricCurrent.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a ElectricCurrent instance based on an SI value.
     * @param si the si value
     * @return the ElectricCurrent instance based on an SI value
     */
    public static ElectricCurrent ofSi(final double si)
    {
        return new ElectricCurrent(si, ElectricCurrent.Unit.SI);
    }

    @Override
    public ElectricCurrent instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return ElectricCurrent.Unit.SI_UNIT;
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
     * Returns a ElectricCurrent based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricCurrent of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of ElectricCurrent and ElectricCurrent, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of ElectricCurrent and ElectricCurrent
     */
    public final Dimensionless divide(final ElectricCurrent v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and ElectricalPotential, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalCurrent and ElectricalPotential
     */
    public final Power times(final ElectricPotential v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and Duration, which results in a ElectricalCharge scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalCurrent and Duration
     */
    public final ElectricCharge times(final Duration v)
    {
        return new ElectricCharge(this.si() * v.si(), ElectricCharge.Unit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and ElectricalResistance, which results in a ElectricalPotential
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalCurrent and ElectricalResistance
     */
    public final ElectricPotential times(final ElectricalResistance v)
    {
        return new ElectricPotential(this.si() * v.si(), ElectricPotential.Unit.SI);
    }

    /**
     * Calculate the division of ElectricalCurrent and ElectricalPotential, which results in a ElectricalConductance scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCurrent and ElectricalPotential
     */
    public final ElectricalConductance divide(final ElectricPotential v)
    {
        return new ElectricalConductance(this.si() / v.si(), ElectricalConductance.Unit.SI);
    }

    /**
     * Calculate the division of ElectricalCurrent and ElectricalConductance, which results in a ElectricalPotential scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCurrent and ElectricalConductance
     */
    public final ElectricPotential divide(final ElectricalConductance v)
    {
        return new ElectricPotential(this.si() / v.si(), ElectricPotential.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * ElectricCurrent.Unit encodes the units of electric current (A).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<ElectricCurrent.Unit, ElectricCurrent>
    {
        /** The dimensions of electric current: A. */
        public static final SIUnit SI_UNIT = SIUnit.of("A");

        /** ampere. */
        public static final ElectricCurrent.Unit AMPERE = new ElectricCurrent.Unit("A", "ampere", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final ElectricCurrent.Unit SI = AMPERE.generateSiPrefixes(false, false);

        /** microampere. */
        public static final ElectricCurrent.Unit MICROAMPERE = Units.resolve(ElectricCurrent.Unit.class, "muA");

        /** milliampere. */
        public static final ElectricCurrent.Unit MILLIAMPERE = Units.resolve(ElectricCurrent.Unit.class, "mA");

        /** kiloampere. */
        public static final ElectricCurrent.Unit KILOAMPERE = Units.resolve(ElectricCurrent.Unit.class, "kA");

        /** megaampere. */
        public static final ElectricCurrent.Unit MEGAAMPERE = Units.resolve(ElectricCurrent.Unit.class, "MA");

        /** statampere (GCS ESU). */
        public static final ElectricCurrent.Unit STATAMPERE =
                AMPERE.deriveUnit("statA", "statampere", 3.335641E-10, UnitSystem.CGS_ESU);

        /** abampere (GCS EMU). */
        public static final ElectricCurrent.Unit ABAMPERE = AMPERE.deriveUnit("abA", "abampere", 10.0, UnitSystem.CGS_EMU);

        /**
         * Create a new ElectricCurrent unit.
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
        public ElectricCurrent ofSi(final double si)
        {
            return ElectricCurrent.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new ElectricCurrent.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
