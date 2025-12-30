package org.djunits.quantity;

import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Magnetic flux is the total magnetic field passing through a given area, measured in webers (Wb).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class MagneticFlux extends Quantity.Relative<MagneticFlux, MagneticFlux.Unit>
{
    /** Constant with value zero. */
    public static final MagneticFlux ZERO = MagneticFlux.ofSi(0.0);

    /** Constant with value one. */
    public static final MagneticFlux ONE = MagneticFlux.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final MagneticFlux NaN = MagneticFlux.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final MagneticFlux POSITIVE_INFINITY = MagneticFlux.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final MagneticFlux NEGATIVE_INFINITY = MagneticFlux.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final MagneticFlux POS_MAXVALUE = MagneticFlux.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final MagneticFlux NEG_MAXVALUE = MagneticFlux.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a MagneticFlux quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public MagneticFlux(final double value, final MagneticFlux.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a MagneticFlux quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public MagneticFlux(final double value, final String abbreviation)
    {
        this(value, Units.resolve(MagneticFlux.Unit.class, abbreviation));
    }

    /**
     * Construct MagneticFlux quantity.
     * @param value Scalar from which to construct this instance
     */
    public MagneticFlux(final MagneticFlux value)
    {
        super(value.si(), MagneticFlux.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a MagneticFlux instance based on an SI value.
     * @param si the si value
     * @return the MagneticFlux instance based on an SI value
     */
    public static MagneticFlux ofSi(final double si)
    {
        return new MagneticFlux(si, MagneticFlux.Unit.SI);
    }

    @Override
    public MagneticFlux instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return MagneticFlux.Unit.SI_UNIT;
    }

    /**
     * Returns a MagneticFlux representation of a textual representation of a value with a unit. The String representation that
     * can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a MagneticFlux
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static MagneticFlux valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a MagneticFlux based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static MagneticFlux of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Calculate the division of MagneticFlux and MagneticFlux, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of MagneticFlux and MagneticFlux
     */
    public final Dimensionless divide(final MagneticFlux v)
    {
        return new Dimensionless(this.si() / v.si(), Dimensionless.Unit.BASE);
    }

    /**
     * Calculate the division of MagneticFlux and ElectricPotential, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of MagneticFlux and ElectricPotential
     */
    public final Duration divide(final ElectricPotential v)
    {
        return new Duration(this.si() / v.si(), Duration.Unit.SI);
    }

    /**
     * Calculate the division of MagneticFlux and Duration, which results in a ElectricPotential scalar.
     * @param v scalar
     * @return scalar as a division of MagneticFlux and Duration
     */
    public final ElectricPotential divide(final Duration v)
    {
        return new ElectricPotential(this.si() / v.si(), ElectricPotential.Unit.SI);
    }

    /**
     * Calculate the division of MagneticFlux and Area, which results in a MagneticFluxDensity scalar.
     * @param v scalar
     * @return scalar as a division of MagneticFlux and Area
     */
    public final MagneticFluxDensity divide(final Area v)
    {
        return new MagneticFluxDensity(this.si() / v.si(), MagneticFluxDensity.Unit.SI);
    }

    /**
     * Calculate the division of MagneticFlux and MagneticFluxDensity, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a division of MagneticFlux and MagneticFluxDensity
     */
    public final Area divide(final MagneticFluxDensity v)
    {
        return new Area(this.si() / v.si(), Area.Unit.SI);
    }

    /**
     * Calculate the division of MagneticFlux and ElectricCurrent, which results in a ElectricalInductance scalar.
     * @param v scalar
     * @return scalar as a division of MagneticFlux and ElectricCurrent
     */
    public final ElectricalInductance divide(final ElectricCurrent v)
    {
        return new ElectricalInductance(this.si() / v.si(), ElectricalInductance.Unit.SI);
    }

    /**
     * Calculate the division of MagneticFlux and ElectricalInductance, which results in a ElectricCurrent scalar.
     * @param v scalar
     * @return scalar as a division of MagneticFlux and ElectricalInductance
     */
    public final ElectricCurrent divide(final ElectricalInductance v)
    {
        return new ElectricCurrent(this.si() / v.si(), ElectricCurrent.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * MagneticFlux.Unit encodes the units of total magnetic field passing through a given area.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<MagneticFlux.Unit, MagneticFlux>
    {
        /** The dimensions of the magnetic flux: kgm2/s2A. */
        public static final SIUnit SI_UNIT = SIUnit.of("kgm2/s2A");

        /** Weber. */
        public static final MagneticFlux.Unit WEBER = new MagneticFlux.Unit("Wb", "weber", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final MagneticFlux.Unit SI = WEBER.generateSiPrefixes(false, false);

        /** mWb. */
        public static final MagneticFlux.Unit MILLIWEBER = Units.resolve(MagneticFlux.Unit.class, "mWb");

        /** muWb. */
        public static final MagneticFlux.Unit MICROWEBER = Units.resolve(MagneticFlux.Unit.class, "muWb");

        /** nWb. */
        public static final MagneticFlux.Unit NANOWEBER = Units.resolve(MagneticFlux.Unit.class, "nWb");

        /** Maxwell. */
        public static final MagneticFlux.Unit MAXWELL = WEBER.deriveUnit("Mx", "Maxwell", 1.0E-8, UnitSystem.CGS);

        /**
         * Create a new MagneticFlux unit.
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
        public MagneticFlux ofSi(final double si)
        {
            return MagneticFlux.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new MagneticFlux.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
