package org.djunits.old.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.old.unit.AreaUnit;
import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.DurationUnit;
import org.djunits.old.unit.ElectricalCurrentUnit;
import org.djunits.old.unit.ElectricalInductanceUnit;
import org.djunits.old.unit.ElectricalPotentialUnit;
import org.djunits.old.unit.MagneticFluxDensityUnit;
import org.djunits.old.unit.MagneticFluxUnit;
import org.djunits.old.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatMagneticFlux FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatMagneticFlux extends FloatScalarRel<MagneticFluxUnit, FloatMagneticFlux>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatMagneticFlux ZERO = new FloatMagneticFlux(0.0f, MagneticFluxUnit.SI);

    /** Constant with value one. */
    public static final FloatMagneticFlux ONE = new FloatMagneticFlux(1.0f, MagneticFluxUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatMagneticFlux NaN = new FloatMagneticFlux(Float.NaN, MagneticFluxUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatMagneticFlux POSITIVE_INFINITY =
            new FloatMagneticFlux(Float.POSITIVE_INFINITY, MagneticFluxUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatMagneticFlux NEGATIVE_INFINITY =
            new FloatMagneticFlux(Float.NEGATIVE_INFINITY, MagneticFluxUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatMagneticFlux POS_MAXVALUE = new FloatMagneticFlux(Float.MAX_VALUE, MagneticFluxUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatMagneticFlux NEG_MAXVALUE = new FloatMagneticFlux(-Float.MAX_VALUE, MagneticFluxUnit.SI);

    /**
     * Construct FloatMagneticFlux scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatMagneticFlux(final float value, final MagneticFluxUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatMagneticFlux scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatMagneticFlux(final FloatMagneticFlux value)
    {
        super(value);
    }

    /**
     * Construct FloatMagneticFlux scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatMagneticFlux(final double value, final MagneticFluxUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatMagneticFlux instantiateRel(final float value, final MagneticFluxUnit unit)
    {
        return new FloatMagneticFlux(value, unit);
    }

    /**
     * Construct FloatMagneticFlux scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatMagneticFlux ofSI(final float value)
    {
        return new FloatMagneticFlux(value, MagneticFluxUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatMagneticFlux at the given ratio between 0 and 1
     */
    public static FloatMagneticFlux interpolate(final FloatMagneticFlux zero, final FloatMagneticFlux one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatMagneticFlux(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatMagneticFlux max(final FloatMagneticFlux r1, final FloatMagneticFlux r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @param rn the other scalars
     * @return the maximum value of more than two relative scalars
     */
    public static FloatMagneticFlux max(final FloatMagneticFlux r1, final FloatMagneticFlux r2, final FloatMagneticFlux... rn)
    {
        FloatMagneticFlux maxr = r1.gt(r2) ? r1 : r2;
        for (FloatMagneticFlux r : rn)
        {
            if (r.gt(maxr))
            {
                maxr = r;
            }
        }
        return maxr;
    }

    /**
     * Return the minimum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the minimum value of two relative scalars
     */
    public static FloatMagneticFlux min(final FloatMagneticFlux r1, final FloatMagneticFlux r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @param rn the other scalars
     * @return the minimum value of more than two relative scalars
     */
    public static FloatMagneticFlux min(final FloatMagneticFlux r1, final FloatMagneticFlux r2, final FloatMagneticFlux... rn)
    {
        FloatMagneticFlux minr = r1.lt(r2) ? r1 : r2;
        for (FloatMagneticFlux r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatMagneticFlux representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatMagneticFlux
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatMagneticFlux valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatMagneticFlux: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatMagneticFlux: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            MagneticFluxUnit unit = MagneticFluxUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity MagneticFlux", unitString);
            return new FloatMagneticFlux(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatMagneticFlux from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatMagneticFlux based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatMagneticFlux of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatMagneticFlux: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatMagneticFlux: empty unitString");
        MagneticFluxUnit unit = MagneticFluxUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatMagneticFlux with unit %s", unitString);
        return new FloatMagneticFlux(value, unit);
    }

    /**
     * Calculate the division of FloatMagneticFlux and FloatMagneticFlux, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatMagneticFlux and FloatMagneticFlux
     */
    public final FloatDimensionless divide(final FloatMagneticFlux v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of FloatMagneticFlux and FloatElectricalPotential, which results in a FloatDuration scalar.
     * @param v scalar
     * @return scalar as a division of FloatMagneticFlux and FloatElectricalPotential
     */
    public final FloatDuration divide(final FloatElectricalPotential v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the division of FloatMagneticFlux and FloatDuration, which results in a FloatElectricalPotential scalar.
     * @param v scalar
     * @return scalar as a division of FloatMagneticFlux and FloatDuration
     */
    public final FloatElectricalPotential divide(final FloatDuration v)
    {
        return new FloatElectricalPotential(this.si / v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the division of FloatMagneticFlux and FloatArea, which results in a FloatMagneticFluxDensity scalar.
     * @param v scalar
     * @return scalar as a division of FloatMagneticFlux and FloatArea
     */
    public final FloatMagneticFluxDensity divide(final FloatArea v)
    {
        return new FloatMagneticFluxDensity(this.si / v.si, MagneticFluxDensityUnit.SI);
    }

    /**
     * Calculate the division of FloatMagneticFlux and FloatMagneticFluxDensity, which results in a FloatArea scalar.
     * @param v scalar
     * @return scalar as a division of FloatMagneticFlux and FloatMagneticFluxDensity
     */
    public final FloatArea divide(final FloatMagneticFluxDensity v)
    {
        return new FloatArea(this.si / v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of FloatMagneticFlux and FloatElectricalCurrent, which results in a FloatElectricalInductance
     * scalar.
     * @param v scalar
     * @return scalar as a division of FloatMagneticFlux and FloatElectricalCurrent
     */
    public final FloatElectricalInductance divide(final FloatElectricalCurrent v)
    {
        return new FloatElectricalInductance(this.si / v.si, ElectricalInductanceUnit.SI);
    }

    /**
     * Calculate the division of FloatMagneticFlux and FloatElectricalInductance, which results in a FloatElectricalCurrent
     * scalar.
     * @param v scalar
     * @return scalar as a division of FloatMagneticFlux and FloatElectricalInductance
     */
    public final FloatElectricalCurrent divide(final FloatElectricalInductance v)
    {
        return new FloatElectricalCurrent(this.si / v.si, ElectricalCurrentUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatSIScalar.divide(FloatDimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatMagneticFlux.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatMagneticFlux
     */
    public static FloatMagneticFlux multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(MagneticFluxUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type FloatMagneticFlux",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatMagneticFlux(scalar1.si * scalar2.si, MagneticFluxUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatMagneticFlux.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatMagneticFlux
     */
    public static FloatMagneticFlux divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(MagneticFluxUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type FloatMagneticFlux",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatMagneticFlux(scalar1.si / scalar2.si, MagneticFluxUnit.SI);
    }

}
