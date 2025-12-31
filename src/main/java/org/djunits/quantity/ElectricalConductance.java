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
 * Electrical conductance measures the ease with which an electric current passes, and is expressed in siemens. Its reciprocal
 * quantity is electrical resistance (expressed in ohm).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class ElectricalConductance extends Quantity.Relative<ElectricalConductance, ElectricalConductance.Unit>
{
    /** Constant with value zero. */
    public static final ElectricalConductance ZERO = ElectricalConductance.ofSi(0.0);

    /** Constant with value one. */
    public static final ElectricalConductance ONE = ElectricalConductance.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricalConductance NaN = ElectricalConductance.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricalConductance POSITIVE_INFINITY = ElectricalConductance.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricalConductance NEGATIVE_INFINITY = ElectricalConductance.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final ElectricalConductance POS_MAXVALUE = ElectricalConductance.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricalConductance NEG_MAXVALUE = ElectricalConductance.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a ElectricalConductance quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public ElectricalConductance(final double value, final ElectricalConductance.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a ElectricalConductance quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public ElectricalConductance(final double value, final String abbreviation)
    {
        this(value, Units.resolve(ElectricalConductance.Unit.class, abbreviation));
    }

    /**
     * Construct ElectricalConductance quantity.
     * @param value Scalar from which to construct this instance
     */
    public ElectricalConductance(final ElectricalConductance value)
    {
        super(value.si(), ElectricalConductance.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a ElectricalConductance instance based on an SI value.
     * @param si the si value
     * @return the ElectricalConductance instance based on an SI value
     */
    public static ElectricalConductance ofSi(final double si)
    {
        return new ElectricalConductance(si, ElectricalConductance.Unit.SI);
    }

    @Override
    public ElectricalConductance instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return ElectricalConductance.Unit.SI_UNIT;
    }

    /**
     * Returns a ElectricalConductance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricalConductance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalConductance valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a ElectricalConductance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalConductance of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of ElectricalConductance and ElectricalConductance, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of ElectricalConductance and ElectricalConductance
     */
    public final Dimensionless divide(final ElectricalConductance v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of ElectricalConductance and ElectricalResistance, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalConductance and ElectricalResistance
     */
    public final Dimensionless multiply(final ElectricalResistance v)
    {
        return new Dimensionless(this.si() * v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of ElectricalConductance and ElectricPotential, which results in a ElectricCurrent scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalConductance and ElectricPotential
     */
    public final ElectricCurrent multiply(final ElectricPotential v)
    {
        return new ElectricCurrent(this.si() * v.si(), ElectricCurrent.Unit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalConductance and Duration, which results in a ElectricalCapacitance scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalConductance and Duration
     */
    public final ElectricalCapacitance multiply(final Duration v)
    {
        return new ElectricalCapacitance(this.si() * v.si(), ElectricalCapacitance.Unit.SI);
    }

    @Override
    public ElectricalResistance reciprocal()
    {
        return ElectricalResistance.ofSi(1.0 / this.si());
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * ElectricalConductance.Unit encodes the units of electrical conductance, and is expressed in Siemens.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<ElectricalConductance.Unit, ElectricalConductance>
    {
        /** The dimensions of electrical conductance: s3A2/kgm2. */
        public static final SIUnit SI_UNIT = SIUnit.of("s3A2/kgm2");

        /** Siemens. */
        public static final ElectricalConductance.Unit SIEMENS =
                new ElectricalConductance.Unit("S", "siemens", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final ElectricalConductance.Unit SI = SIEMENS.generateSiPrefixes(false, false);

        /** mS. */
        public static final ElectricalConductance.Unit MILLISIEMENS = Units.resolve(ElectricalConductance.Unit.class, "mS");

        /** muS. */
        public static final ElectricalConductance.Unit MICROSIEMENS = Units.resolve(ElectricalConductance.Unit.class, "muS");

        /** nS. */
        public static final ElectricalConductance.Unit NANOSIEMENS = Units.resolve(ElectricalConductance.Unit.class, "nS");

        /**
         * Create a new ElectricalConductance unit.
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
        public ElectricalConductance ofSi(final double si)
        {
            return ElectricalConductance.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new ElectricalConductance.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
