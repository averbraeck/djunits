package org.djunits.quantity;

import java.util.List;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * ElectricalCapacitance denotes the ability of an object to store electric charge, and is expressed in farad.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ElectricalCapacitance extends Quantity.Relative<ElectricalCapacitance, ElectricalCapacitance.Unit>
{
    /** Constant with value zero. */
    public static final ElectricalCapacitance ZERO = ElectricalCapacitance.ofSi(0.0);

    /** Constant with value one. */
    public static final ElectricalCapacitance ONE = ElectricalCapacitance.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricalCapacitance NaN = ElectricalCapacitance.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricalCapacitance POSITIVE_INFINITY = ElectricalCapacitance.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricalCapacitance NEGATIVE_INFINITY = ElectricalCapacitance.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final ElectricalCapacitance POS_MAXVALUE = ElectricalCapacitance.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricalCapacitance NEG_MAXVALUE = ElectricalCapacitance.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a ElectricalCapacitance quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public ElectricalCapacitance(final double value, final ElectricalCapacitance.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a ElectricalCapacitance quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public ElectricalCapacitance(final double value, final String abbreviation)
    {
        this(value, Units.resolve(ElectricalCapacitance.Unit.class, abbreviation));
    }

    /**
     * Construct ElectricalCapacitance quantity.
     * @param value Scalar from which to construct this instance
     */
    public ElectricalCapacitance(final ElectricalCapacitance value)
    {
        super(value.si(), ElectricalCapacitance.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a ElectricalCapacitance instance based on an SI value.
     * @param si the si value
     * @return the ElectricalCapacitance instance based on an SI value
     */
    public static ElectricalCapacitance ofSi(final double si)
    {
        return new ElectricalCapacitance(si, ElectricalCapacitance.Unit.SI);
    }

    @Override
    public ElectricalCapacitance instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return ElectricalCapacitance.Unit.SI_UNIT;
    }

    /**
     * Returns a ElectricalCapacitance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricalCapacitance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalCapacitance valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a ElectricalCapacitance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalCapacitance of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of ElectricalCapacitance and ElectricalCapacitance, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of ElectricalCapacitance and ElectricalCapacitance
     */
    public final Dimensionless divide(final ElectricalCapacitance v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of ElectricalCapacitance and ElectricPotential, which results in a ElectricCharge scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalCapacitance and ElectricPotential
     */
    public final ElectricCharge times(final ElectricPotential v)
    {
        return new ElectricCharge(this.si() * v.si(), ElectricCharge.Unit.SI);
    }

    /**
     * Calculate the division of ElectricalCapacitance and Duration, which results in a ElectricalConductance scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCapacitance and Duration
     */
    public final ElectricalConductance divide(final Duration v)
    {
        return new ElectricalConductance(this.si() / v.si(), ElectricalConductance.Unit.SI);
    }

    /**
     * Calculate the division of ElectricalCapacitance and ElectricalConductance, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCapacitance and ElectricalConductance
     */
    public final Duration divide(final ElectricalConductance v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * ElectricalCapacitance.Unit encodes the units of capacitance (in farad).<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<ElectricalCapacitance.Unit>
    {
        /** The dimensions of electrical capacitance: s^4.A^2/kg.m^2. */
        public static final SIUnit SI_UNIT = SIUnit.of("s4A2/kgm2");

        /** farad. */
        public static final ElectricalCapacitance.Unit FARAD =
                new ElectricalCapacitance.Unit("F", "farad", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final ElectricalCapacitance.Unit SI = FARAD.generateSiPrefixes(false, false);

        /** mF. */
        public static final ElectricalCapacitance.Unit MILLIFARAD = Units.resolve(ElectricalCapacitance.Unit.class, "mF");

        /** uF. */
        public static final ElectricalCapacitance.Unit MICROFARAD = Units.resolve(ElectricalCapacitance.Unit.class, "muF");

        /** nF. */
        public static final ElectricalCapacitance.Unit NANOFARAD = Units.resolve(ElectricalCapacitance.Unit.class, "nF");

        /** pF. */
        public static final ElectricalCapacitance.Unit PICOFARAD = Units.resolve(ElectricalCapacitance.Unit.class, "pF");

        /**
         * Create a new ElectricalCapacitance unit.
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
         * @param textualAbbreviations the textual abbreviations of the unit, where the first one in the list is the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert between this unit and the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         */
        public Unit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
        {
            super(textualAbbreviations, displayAbbreviation, name, scale, unitSystem);
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
        public Unit deriveUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new ElectricalCapacitance.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
