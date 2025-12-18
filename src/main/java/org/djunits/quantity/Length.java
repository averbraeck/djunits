package org.djunits.quantity;

import java.util.List;

import org.djunits.old.unit.si.SIDimensions;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.system.UnitSystem;

/**
 * Length.java.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */

public class Length extends Quantity<Length, Length.Unit>
{
    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a Length quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public Length(final double value, final Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a Length quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public Length(final double value, final String abbreviation)
    {
        this(value, Units.resolve(Length.Unit.class, abbreviation));
    }

    @Override
    public SIDimensions siDimensions()
    {
        return Unit.SI_DIMENSIONS;
    }

    /**
     * Return a Length instance based on an SI value.
     * @param si the si value
     * @return the Length instance based on an SI value
     */
    public static Length ofSi(final double si)
    {
        return new Length(si, Units.m);
    }

    /**
     * Length.Unit encodes the length unit.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<Length.Unit>
    {
        /** The dimensions of the length: rad, sr, kg, m, s, A, K, mol, cd. */
        public static final SIDimensions SI_DIMENSIONS = new SIDimensions(new byte[] {0, 0, 0, 1, 0, 0, 0, 0, 0});

        /**
         * Create a new length unit.
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
        public SIDimensions siDimensions()
        {
            return SI_DIMENSIONS;
        }

        @Override
        public Unit baseUnit()
        {
            return Units.m;
        }

        @Override
        public Unit deriveUnit(final List<String> textualAbbreviations, final String displayAbbreviation, final String name,
                final Scale scale, final UnitSystem unitSystem)
        {
            return new Length.Unit(textualAbbreviations, displayAbbreviation, name, scale, unitSystem);
        }

    }
}
