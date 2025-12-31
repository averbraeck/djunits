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
 * Volumetric object density counts the number of objects per unit of volume, measured in number per cubic meter (/m3).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class VolumetricObjectDensity extends Quantity.Relative<VolumetricObjectDensity, VolumetricObjectDensity.Unit>
{
    /** Constant with value zero. */
    public static final VolumetricObjectDensity ZERO = VolumetricObjectDensity.ofSi(0.0);

    /** Constant with value one. */
    public static final VolumetricObjectDensity ONE = VolumetricObjectDensity.ofSi(1.0);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final VolumetricObjectDensity NaN = VolumetricObjectDensity.ofSi(Double.NaN);

    /** Constant with value POSITIVE_INFINITY. */
    public static final VolumetricObjectDensity POSITIVE_INFINITY = VolumetricObjectDensity.ofSi(Double.POSITIVE_INFINITY);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final VolumetricObjectDensity NEGATIVE_INFINITY = VolumetricObjectDensity.ofSi(Double.NEGATIVE_INFINITY);

    /** Constant with value MAX_VALUE. */
    public static final VolumetricObjectDensity POS_MAXVALUE = VolumetricObjectDensity.ofSi(Double.MAX_VALUE);

    /** Constant with value -MAX_VALUE. */
    public static final VolumetricObjectDensity NEG_MAXVALUE = VolumetricObjectDensity.ofSi(-Double.MAX_VALUE);

    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Instantiate a VolumetricObjectDensity quantity with a unit.
     * @param value the value, expressed in the unit
     * @param unit the unit in which the value is expressed
     */
    public VolumetricObjectDensity(final double value, final VolumetricObjectDensity.Unit unit)
    {
        super(value, unit);
    }

    /**
     * Instantiate a VolumetricObjectDensity quantity with a unit, expressed as a String.
     * @param value the value, expressed in the unit
     * @param abbreviation the String abbreviation of the unit in which the value is expressed
     */
    public VolumetricObjectDensity(final double value, final String abbreviation)
    {
        this(value, Units.resolve(VolumetricObjectDensity.Unit.class, abbreviation));
    }

    /**
     * Construct VolumetricObjectDensity quantity.
     * @param value Scalar from which to construct this instance
     */
    public VolumetricObjectDensity(final VolumetricObjectDensity value)
    {
        super(value.si(), VolumetricObjectDensity.Unit.SI);
        setDisplayUnit(value.getDisplayUnit());
    }

    /**
     * Return a VolumetricObjectDensity instance based on an SI value.
     * @param si the si value
     * @return the VolumetricObjectDensity instance based on an SI value
     */
    public static VolumetricObjectDensity ofSi(final double si)
    {
        return new VolumetricObjectDensity(si, VolumetricObjectDensity.Unit.SI);
    }

    @Override
    public VolumetricObjectDensity instantiate(final double si)
    {
        return ofSi(si);
    }

    @Override
    public SIUnit siUnit()
    {
        return VolumetricObjectDensity.Unit.SI_UNIT;
    }

    /**
     * Returns a VolumetricObjectDensity representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a VolumetricObjectDensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static VolumetricObjectDensity valueOf(final String text)
    {
        return Quantity.valueOf(text, ZERO);
    }

    /**
     * Returns a VolumetricObjectDensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static VolumetricObjectDensity of(final double value, final String unitString)
    {
        return Quantity.of(value, unitString, ZERO);
    }

    /**
     * Multiplies this volumetric object density by a volume to yield a dimensionless count.
     * <p>
     * Formula: (1/m³) × m³ = 1.
     * </p>
     * @param volume the volume multiplier; must not be {@code null}.
     * @return the resulting dimensionless count in SI (1).
     * @throws NullPointerException if {@code volume} is {@code null}.
     */
    public final Dimensionless multiply(final Volume volume)
    {
        return new Dimensionless(this.si() * volume.si(), Unitless.BASE);
    }

    /**
     * Multiplies this volumetric object density by an area to yield a linear object density.
     * <p>
     * Formula: (1/m³) × m² = 1/m.
     * </p>
     * @param area the area multiplier; must not be {@code null}.
     * @return the resulting linear object density in SI (1/m).
     * @throws NullPointerException if {@code area} is {@code null}.
     */
    public final LinearObjectDensity multiply(final Area area)
    {
        return new LinearObjectDensity(this.si() * area.si(), LinearObjectDensity.Unit.SI);
    }

    /**
     * Multiplies this volumetric object density by a length to yield an areal object density.
     * <p>
     * Formula: (1/m³) × m = 1/m².
     * </p>
     * @param length the length multiplier; must not be {@code null}.
     * @return the resulting areal object density in SI (1/m²).
     * @throws NullPointerException if {@code length} is {@code null}.
     */
    public final ArealObjectDensity multiply(final Length length)
    {
        return new ArealObjectDensity(this.si() * length.si(), ArealObjectDensity.Unit.SI);
    }

    /**
     * Divides this volumetric object density by a linear object density to yield an areal object density.
     * <p>
     * Formula: (1/m³) / (1/m) = 1/m².
     * </p>
     * @param lod the linear object density divisor; must not be {@code null}.
     * @return the resulting areal object density in SI (1/m²).
     * @throws NullPointerException if {@code lod} is {@code null}.
     */
    public final ArealObjectDensity divide(final LinearObjectDensity lod)
    {
        return new ArealObjectDensity(this.si() / lod.si(), ArealObjectDensity.Unit.SI);
    }

    /**
     * Divides this volumetric object density by an areal object density to yield a linear object density.
     * <p>
     * Formula: (1/m³) / (1/m²) = 1/m.
     * </p>
     * @param aod the areal object density divisor; must not be {@code null}.
     * @return the resulting linear object density in SI (1/m).
     * @throws NullPointerException if {@code aod} is {@code null}.
     */
    public final LinearObjectDensity divide(final ArealObjectDensity aod)
    {
        return new LinearObjectDensity(this.si() / aod.si(), LinearObjectDensity.Unit.SI);
    }

    /**
     * Divides this volumetric object density by another volumetric object density to yield a dimensionless ratio.
     * <p>
     * Formula: (1/m³) / (1/m³) = 1.
     * </p>
     * @param other the volumetric object density divisor; must not be {@code null}.
     * @return the resulting dimensionless ratio in SI (1).
     * @throws NullPointerException if {@code other} is {@code null}.
     */
    public final Dimensionless divide(final VolumetricObjectDensity other)
    {
        return new Dimensionless(this.si() / other.si(), Unitless.BASE);
    }

    @Override
    public Volume reciprocal()
    {
        return Volume.ofSi(1.0 / this.si());
    }

    /******************************************************************************************************/
    /********************************************** UNIT CLASS ********************************************/
    /******************************************************************************************************/

    /**
     * VolumetricObjectDensity.Unit encodes the unit for number of objects per unit of volume.<br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     */
    public static class Unit extends AbstractUnit<VolumetricObjectDensity.Unit, VolumetricObjectDensity>
    {
        /** The dimensions of the number of objects per unit of volume: per cubic meter (/m3). */
        public static final SIUnit SI_UNIT = SIUnit.of("/m3");

        /** per meter. */
        public static final VolumetricObjectDensity.Unit PER_CUBIC_METER =
                new VolumetricObjectDensity.Unit("/m3", "per cubic meter", 1.0, UnitSystem.SI_DERIVED);

        /** The SI or BASE unit. */
        public static final VolumetricObjectDensity.Unit SI = PER_CUBIC_METER;

        /**
         * Create a new VolumetricObjectDensity unit.
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
        public VolumetricObjectDensity ofSi(final double si)
        {
            return VolumetricObjectDensity.ofSi(si);
        }

        @Override
        public Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                final double scaleFactor, final UnitSystem unitSystem)
        {
            if (getScale() instanceof LinearScale ls)
            {
                return new VolumetricObjectDensity.Unit(textualAbbreviation, displayAbbreviation, name,
                        new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
            }
            throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
        }

    }
}
