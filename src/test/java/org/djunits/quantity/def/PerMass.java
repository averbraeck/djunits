package org.djunits.quantity.def;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * PerMass is a test class for the per-kilo SI units.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class PerMass extends Quantity<PerMass>
{
    /** Constant with value zero. */
    public static final PerMass ZERO = PerMass.ofSi(0.0);

    /** Constant with value one. */
    public static final PerMass ONE = PerMass.ofSi(1.0);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a Mass quantity with a unit.
     * @param valueInUnit the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public PerMass(final double valueInUnit, final PerMass.Unit unit)
    {
        super(valueInUnit, unit);
    }

    /**
     * Instantiate a Mass quantity with a unit, expressed as a String.
     * @param valueInUnit the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public PerMass(final double valueInUnit, final String abbreviation)
    {
        this(valueInUnit, Units.resolve(PerMass.Unit.class, abbreviation));
    }

    /**
     * Construct Mass quantity.
     * @param value Scalar from which to construct this instance
     */
    public PerMass(final PerMass value)
    {
        super(value.si(), PerMass.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a Mass instance based on an SI value.
     * @param si the si value
     * @return the Mass instance based on an SI value
     */
    public static PerMass ofSi(final double si)
    {
        return new PerMass(si, PerMass.Unit.SI);
    }

    @Override
    public PerMass instantiateSi(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return PerMass.Unit.SI_UNIT;
    }

    /**
     * Returns a Mass representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Mass
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static PerMass valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a Mass based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static PerMass of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public PerMass.Unit getDisplayUnit()
    {
        return (PerMass.Unit) super.getDisplayUnit();
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * PerMass.Unit encodes the unit of the PerMass quantity.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<PerMass.Unit, PerMass>
    {
        /** The dimensions of mass: /kg. */
        public static final SIUnit SI_UNIT = SIUnit.of("/kg");

        /** per kilogram. */
        public static final PerMass.Unit per_kg = new PerMass.Unit("/kg", "per kilogram", 1.0, UnitSystem.SI_BASE);

        /** The SI or BASE unit. */
        public static final PerMass.Unit SI = per_kg.generateSiPrefixes(true, true);

        /** gram. */
        public static final PerMass.Unit per_g = Units.resolve(PerMass.Unit.class, "/g");

        /** microgram. */
        public static final PerMass.Unit per_mug = Units.resolve(PerMass.Unit.class, "/mug");

        /** milligram. */
        public static final PerMass.Unit per_mg = Units.resolve(PerMass.Unit.class, "/mg");

        /**
         * Create a new Mass unit.
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
        public PerMass ofSi(final double si)
        {
            return PerMass.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new PerMass.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
