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
 * Density is mass per unit volume of a substance, measured in kilograms per cubic meter (kg/m3).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Density extends Quantity.Relative<Density, Density.Unit>
{
    /** Constant with value zero. */
    public static final Density ZERO = Density.ofSi(0.0);

    /** Constant with value one. */
    public static final Density ONE = Density.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Density NaN = Density.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Density POSITIVE_INFINITY = Density.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Density NEGATIVE_INFINITY = Density.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final Density POS_MAXVALUE = Density.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final Density NEG_MAXVALUE = Density.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Density quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Density(final double value, final Density.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Density quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Density(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Density.Unit.class, abbreviation));
    }

    /**
     * Construct Density quantity.
     * @param value Scalar from which to construct this instance
     */
    public Density(final Density value)
    {
        super(value.si(), Density.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Density instance based on an SI value.
     * @param si the si value
     * @return the Density instance based on an SI value
     */
    public static Density ofSi(final double si)
    {
        return new Density(si, Density.Unit.SI);
    }

    @Override
    public Density instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return Density.Unit.SI_UNIT;
    }

    /**
     * Returns a Density representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Density
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Density valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Density based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Density of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of Density and Density, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of Density and Density
     */
    public final Dimensionless divide(final Density v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of Density and Volume, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Density and Volume
     */
    public final Mass times(final Volume v)
    {
        return new Mass(this.si() * v.si(), Mass.Unit.SI);
    }

    /**
     * Calculate the multiplication of Density and FlowVolume, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Density and FlowVolume
     */
    public final FlowMass times(final FlowVolume v)
    {
        return new FlowMass(this.si() * v.si(), FlowMass.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * Density.Unit encodes the units of density based on mass per volume.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Density.Unit>
    {
        /** The dimensions of the absorbed dose: kg/m3. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg/m3");

        /** kg/m^3. */
        public static final Density.Unit KG_PER_METER_3 =
                new Density.Unit("kg/m3", "kilogram per cubic meter", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final Density.Unit SI = KG_PER_METER_3;

        /** g/cm^3. */
        public static final Density.Unit GRAM_PER_CENTIMETER_3 =
                KG_PER_METER_3.deriveUnit("g/cm3", "gram per cubic centimeter", 1.0E3, UnitSystem.SI_DERIVED);

        /**
         * Create a new Density unit.
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
                return new Density.Unit(textualAbbreviations, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
