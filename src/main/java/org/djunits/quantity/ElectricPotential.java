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
 * ElectricPotential is the difference in electric potential energy per unit of electric charge between two points in a static
 * electric field.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ElectricPotential extends Quantity.Relative<ElectricPotential, ElectricPotential.Unit>
{
    /** Constant with value zero. */
    public static final ElectricPotential ZERO = ElectricPotential.ofSi(0.0);

    /** Constant with value one. */
    public static final ElectricPotential ONE = ElectricPotential.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricPotential NaN = ElectricPotential.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricPotential POSITIVE_INFINITY = ElectricPotential.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricPotential NEGATIVE_INFINITY = ElectricPotential.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final ElectricPotential POS_MAXVALUE = ElectricPotential.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricPotential NEG_MAXVALUE = ElectricPotential.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a ElectricPotential quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public ElectricPotential(final double value, final ElectricPotential.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a ElectricPotential quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public ElectricPotential(final double value, final String abbreviation)
    {
        this(value, Units.resolve(ElectricPotential.Unit.class, abbreviation));
    }

    /**
     * Construct ElectricPotential quantity.
     * @param value Scalar from which to construct this instance
     */
    public ElectricPotential(final ElectricPotential value)
    {
        super(value.si(), ElectricPotential.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a ElectricPotential instance based on an SI value.
     * @param si the si value
     * @return the ElectricPotential instance based on an SI value
     */
    public static ElectricPotential ofSi(final double si)
    {
        return new ElectricPotential(si, ElectricPotential.Unit.SI);
    }

    @Override
    public ElectricPotential instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return ElectricPotential.Unit.SI_UNIT;
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
     * Returns a ElectricPotential based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricPotential of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of ElectricPotential and ElectricPotential, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of ElectricPotential and ElectricPotential
     */
    public final Dimensionless divide(final ElectricPotential v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of ElectricPotential and ElectricCurrent, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricPotential and ElectricCurrent
     */
    public final Power multiply(final ElectricCurrent v)
    {
        return new Power(this.si() * v.si(), Power.Unit.SI);
    }

    /**
     * Calculate the division of ElectricPotential and ElectricCurrent, which results in a ElectricalResistance scalar.
     * @param v scalar
     * @return scalar as a division of ElectricPotential and ElectricCurrent
     */
    public final ElectricalResistance divide(final ElectricCurrent v)
    {
        return new ElectricalResistance(this.si() / v.si(), ElectricalResistance.Unit.SI);
    }

    /**
     * Calculate the division of ElectricPotential and ElectricalResistance, which results in a ElectricCurrent scalar.
     * @param v scalar
     * @return scalar as a division of ElectricPotential and ElectricalResistance
     */
    public final ElectricCurrent divide(final ElectricalResistance v)
    {
        return new ElectricCurrent(this.si() / v.si(), ElectricCurrent.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * ElectricPotential.Unit encodes the units of electric potential (difference)<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<ElectricPotential.Unit, ElectricPotential>
    {
        /** The dimensions of the electric potential: kgm2/s3A. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm2/s3A");

        /** Gray. */
        public static final ElectricPotential.Unit VOLT = new ElectricPotential.Unit("V", "volt", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final ElectricPotential.Unit SI = VOLT.generateSiPrefixes(false, false);

        /** microvolt. */
        public static final ElectricPotential.Unit MICROVOLT = Units.resolve(ElectricPotential.Unit.class, "muV");

        /** millivolt. */
        public static final ElectricPotential.Unit MILLIVOLT = Units.resolve(ElectricPotential.Unit.class, "mV");

        /** kilovolt. */
        public static final ElectricPotential.Unit KILOVOLT = Units.resolve(ElectricPotential.Unit.class, "kV");

        /** megavolt. */
        public static final ElectricPotential.Unit MEGAVOLT = Units.resolve(ElectricPotential.Unit.class, "MV");

        /** gigavolt. */
        public static final ElectricPotential.Unit GIGAVOLT = Units.resolve(ElectricPotential.Unit.class, "GV");

        /** statvolt. */
        public static final ElectricPotential.Unit STATVOLT =
                VOLT.deriveUnit("stV", "statvolt", 299.792458, UnitSystem.CGS_ESU);

        /** abvolt. */
        public static final ElectricPotential.Unit ABVOLT = VOLT.deriveUnit("abV", "abvolt", 1.0E-8, UnitSystem.CGS_EMU);

        /**
         * Create a new ElectricPotential unit.
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
        public ElectricPotential ofSi(final double si)
        {
            return ElectricPotential.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new ElectricPotential.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
