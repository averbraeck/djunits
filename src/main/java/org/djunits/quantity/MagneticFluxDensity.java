package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Magnetic flux density is the strength of the magnetic field per unit area, measured in teslas (T).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class MagneticFluxDensity extends Quantity.Relative<MagneticFluxDensity, MagneticFluxDensity.Unit>
{
    /** Constant with value zero. */
    public static final MagneticFluxDensity ZERO = MagneticFluxDensity.ofSi(0.0);

    /** Constant with value one. */
    public static final MagneticFluxDensity ONE = MagneticFluxDensity.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final MagneticFluxDensity NaN = MagneticFluxDensity.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final MagneticFluxDensity POSITIVE_INFINITY = MagneticFluxDensity.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final MagneticFluxDensity NEGATIVE_INFINITY = MagneticFluxDensity.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final MagneticFluxDensity POS_MAXVALUE = MagneticFluxDensity.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final MagneticFluxDensity NEG_MAXVALUE = MagneticFluxDensity.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 500L;

    /**
     * Instantiate a MagneticFluxDensity quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public MagneticFluxDensity(final double value, final MagneticFluxDensity.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a MagneticFluxDensity quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public MagneticFluxDensity(final double value, final String abbreviation)
    {
        this(value, Units.resolve(MagneticFluxDensity.Unit.class, abbreviation));
    }

    /**
     * Construct MagneticFluxDensity quantity.
     * @param value Scalar from which to construct this instance
     */
    public MagneticFluxDensity(final MagneticFluxDensity value)
    {
        super(value.si(), MagneticFluxDensity.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a MagneticFluxDensity instance based on an SI value.
     * @param si the si value
     * @return the MagneticFluxDensity instance based on an SI value
     */
    public static MagneticFluxDensity ofSi(final double si)
    {
        return new MagneticFluxDensity(si, MagneticFluxDensity.Unit.SI);
    }

    @Override
    public MagneticFluxDensity instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return MagneticFluxDensity.Unit.SI_UNIT;
    }

    /**
     * Returns a MagneticFluxDensity representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a MagneticFluxDensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static MagneticFluxDensity valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a MagneticFluxDensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static MagneticFluxDensity of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of MagneticFluxDensity and MagneticFluxDensity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of MagneticFluxDensity and MagneticFluxDensity
     */
    public final Dimensionless divide(final MagneticFluxDensity v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the multiplication of MagneticFluxDensity and Area, which results in a MagneticFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of MagneticFluxDensity and Area
     */
    public final MagneticFlux times(final Area v)
    {
        return new MagneticFlux(this.si() * v.si(), MagneticFlux.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * MagneticFluxDensity.Unit encodes the units of strength of the magnetic field per unit area.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<MagneticFluxDensity.Unit, MagneticFluxDensity>
    {
        /** The dimensions of the magnetic flux density: kg/s2A. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg/s2A");

        /** Gray. */
        public static final MagneticFluxDensity.Unit TESLA =
                new MagneticFluxDensity.Unit("T", "tesla", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final MagneticFluxDensity.Unit SI = TESLA.generateSiPrefixes(false, false);

        /** mT. */
        public static final MagneticFluxDensity.Unit MILLITESLA = Units.resolve(MagneticFluxDensity.Unit.class, "mT");

        /** muT. */
        public static final MagneticFluxDensity.Unit MICROTESLA = Units.resolve(MagneticFluxDensity.Unit.class, "muT");

        /** nT. */
        public static final MagneticFluxDensity.Unit NANOTESLA = Units.resolve(MagneticFluxDensity.Unit.class, "nT");

        /** Gauss. */
        public static final MagneticFluxDensity.Unit GAUSS = TESLA.deriveUnit("G", "gauss", 1.0E-4, UnitSystem.CGS);

        /**
         * Create a new MagneticFluxDensity unit.
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
        public MagneticFluxDensity ofSi(final double si)
        {
            return MagneticFluxDensity.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new MagneticFluxDensity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
