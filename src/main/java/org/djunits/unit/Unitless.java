package org.djunits.unit;

import org.djunits.quantity.Dimensionless;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Unitless encodes a unit without dimensions, e.g., to encode a constant.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
 * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class Unitless extends AbstractUnit<Unitless, Dimensionless>
{
    /** The dimensions of the dimensionless quantity: 1 [rad, sr, kg, m, s, A, K, mol, cd]. */
    public static final SIUnit SI_UNIT = new SIUnit(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0});

    /** The SI or BASE unit. */
    public static final Unitless BASE = new Unitless(" ", " ", 1.0, UnitSystem.OTHER);

    /**
     * Create a new Dimensionless unit.
     * @param id the id or main abbreviation of the unit
     * @param name the full name of the unit
     * @param scaleFactorToBaseUnit the scale factor of the unit to convert it TO the base (SI) unit
     * @param unitSystem the unit system such as SI or IMPERIAL
     */
    public Unitless(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
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
    public Unitless(final String textualAbbreviation, final String displayAbbreviation, final String name,
            final Scale scale, final UnitSystem unitSystem)
    {
        super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem);
    }

    @Override
    public SIUnit siUnit()
    {
        return SI_UNIT;
    }

    @Override
    public Unitless getBaseUnit()
    {
        return BASE;
    }

    @Override
    public Dimensionless ofSi(final double si)
    {
        return Dimensionless.ofSi(si);
    }

    @Override
    public Unitless deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
            final double scaleFactor, final UnitSystem unitSystem)
    {
        if (getScale() instanceof LinearScale ls)
        {
            return new Unitless(textualAbbreviation, displayAbbreviation, name,
                    new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
        }
        throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
    }

}
