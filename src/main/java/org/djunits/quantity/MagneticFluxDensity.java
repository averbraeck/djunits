package org.djunits.quantity;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Unitless;
import org.djunits.unit.Units;
import org.djunits.unit.scale.IdentityScale;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;

/**
 * Magnetic flux density is the strength of the magnetic field per unit area, measured in teslas (T).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class MagneticFluxDensity extends Quantity<MagneticFluxDensity>
{
    /** Constant with value zero. */
    public static final MagneticFluxDensity ZERO = ofSi(0.0);

    /** Constant with value one. */
    public static final MagneticFluxDensity ONE = ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final MagneticFluxDensity NaN = ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final MagneticFluxDensity POSITIVE_INFINITY = ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final MagneticFluxDensity NEGATIVE_INFINITY = ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final MagneticFluxDensity POS_MAXVALUE = ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final MagneticFluxDensity NEG_MAXVALUE = ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a MagneticFluxDensity quantity with an SI or base value and a display unit.
     * @param value the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @param useSi use SI value when true, use value in unit when false
     */
    public MagneticFluxDensity(final double value, final MagneticFluxDensity.Unit displayUnit, final boolean useSi)
    {
        super(value, displayUnit, useSi);
    }

    /**
     * Instantiate a MagneticFluxDensity quantity expressed in the given unit.
     * @param valueInUnit the quantity value expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     */
    public MagneticFluxDensity(final double valueInUnit, final MagneticFluxDensity.Unit unit)
    {
        this(valueInUnit, unit, false);
    }

    /**
     * Return a MagneticFluxDensity instance based on an SI value.
     * @param si the si value
     * @return the MagneticFluxDensity instance based on an SI value
     */
    public static MagneticFluxDensity ofSi(final double si)
    {
        return new MagneticFluxDensity(si, MagneticFluxDensity.Unit.SI, true);
    }

    /**
     * Instantiate a MagneticFluxDensity quantity with an SI or base value and a display unit.
     * @param siValue the quantity value expressed in the SI or base unit
     * @param displayUnit the display unit to use
     * @return the MagneticFluxDensity instance based on an SI value with the given display unit
     */
    public static MagneticFluxDensity ofSi(final double siValue, final MagneticFluxDensity.Unit displayUnit)
    {
        return new MagneticFluxDensity(siValue, displayUnit, true);
    }

    @Override
    public MagneticFluxDensity instantiateSi(final double siValue, final UnitInterface<MagneticFluxDensity> displayUnit)
    {
        return new MagneticFluxDensity(siValue, (MagneticFluxDensity.Unit) displayUnit, true);
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
     * Returns a MagneticFluxDensity based on a value expressed in the unit.
     * @param valueInUnit the value, expressed in the given unit
     * @param unit the unit of the value, also acts as the display unit
     * @return ab MagneticFluxDensity representation of the value in its unit
     */
    public static MagneticFluxDensity of(final double valueInUnit, final MagneticFluxDensity.Unit unit)
    {
        return new MagneticFluxDensity(valueInUnit, unit);
    }

    /**
     * Returns a MagneticFluxDensity based on a value and the textual representation of the unit, which can be localized.
     * @param valueInUnit the value, expressed in the unit as given by unitString
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static MagneticFluxDensity of(final double valueInUnit, final String unitString)
    {
        return Quantity.of(valueInUnit, unitString, ZERO);
    }

    @Override
    public MagneticFluxDensity.Unit getDisplayUnit()
    {
        return (MagneticFluxDensity.Unit) super.getDisplayUnit();
    }

    /**
     * Calculate the division of MagneticFluxDensity and MagneticFluxDensity, which results in a Dimensionless quantity.
     * @param v quantity
     * @return quantity as a division of MagneticFluxDensity and MagneticFluxDensity
     */
    public final Dimensionless divide(final MagneticFluxDensity v)
    {
        return new Dimensionless(this.si() / v.si(), Unitless.BASE);
    }

    /**
     * Calculate the multiplication of MagneticFluxDensity and Area, which results in a MagneticFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of MagneticFluxDensity and Area
     */
    public final MagneticFlux multiply(final Area v)
    {
        return new MagneticFlux(this.si() * v.si(), MagneticFlux.Unit.SI);
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * MagneticFluxDensity.Unit encodes the units of strength of the magnetic field per unit area.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    @SuppressWarnings("checkstyle:constantname")
    public static class Unit extends AbstractUnit<MagneticFluxDensity>
    {
        /** The dimensions of the magnetic flux density: kg/s2A. */
        public static final SIUnit SI_UNIT = SIUnit.of("kg/s2A");

        /** Tesla. */
        public static final MagneticFluxDensity.Unit T = new MagneticFluxDensity.Unit("T", "T", "tesla", IdentityScale.SCALE,
                UnitSystem.SI_DERIVED, SIPrefixes.getSiPrefix(""));

        /** The SI or BASE unit. */
        public static final MagneticFluxDensity.Unit SI = (Unit) T.generateSiPrefixes(false, false);

        /** mT. */
        public static final MagneticFluxDensity.Unit mT = Units.resolve(MagneticFluxDensity.Unit.class, "mT");

        /** muT. */
        public static final MagneticFluxDensity.Unit muT = Units.resolve(MagneticFluxDensity.Unit.class, "muT");

        /** nT. */
        public static final MagneticFluxDensity.Unit nT = Units.resolve(MagneticFluxDensity.Unit.class, "nT");

        /** Gauss. */
        public static final MagneticFluxDensity.Unit G = T.deriveUnit("G", "G", "gauss", 1.0E-4, UnitSystem.CGS, null);

        /**
         * Create a new MagneticFluxDensity unit.
         * @param id the id or main abbreviation of the unit
         * @param name the full name of the unit
         * @param scaleFactorToBaseUnit the scale factor of the unit to convert it TO the base (SI) unit
         * @param unitSystem the unit system such as SI or IMPERIAL
         */
        public Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
        {
            super(id, name, scaleFactorToBaseUnit, unitSystem);
        }

        /**
         * Return a derived unit for this unit, with textual abbreviation(s) and a display abbreviation.
         * @param textualAbbreviation the textual abbreviation of the unit, which doubles as the id
         * @param displayAbbreviation the display abbreviation of the unit
         * @param name the full name of the unit
         * @param scale the scale to use to convert from this unit to the standard (e.g., SI, BASE) unit
         * @param unitSystem unit system, e.g. SI or Imperial
         * @param siPrefix the SI Prefix of this unit
         */
        public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name, final Scale scale,
                final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem, siPrefix);
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
        public MagneticFluxDensity ofSi(final double si, final UnitInterface<MagneticFluxDensity> displayUnit)
        {
            return new MagneticFluxDensity(si, (Unit) displayUnit, true);
        }

        @Override
        public MagneticFluxDensity.Unit deriveUnit(final String abbreviation, final String name, final double scaleFactor,
                final UnitSystem unitSystem)
        {
            return (Unit) super.deriveUnit(abbreviation, name, scaleFactor, unitSystem);
        }

        @Override
        public MagneticFluxDensity.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                final String name, final double scaleFactor, final UnitSystem unitSystem, final SIPrefix siPrefix)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new MagneticFluxDensity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem, siPrefix);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }

}
