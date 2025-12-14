package org.djunits.old.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.ElectricalInductanceUnit;
import org.djunits.old.unit.ElectricalPotentialUnit;
import org.djunits.old.unit.ElectricalResistanceUnit;
import org.djunits.old.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatElectricalResistance FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatElectricalResistance extends FloatScalarRel<ElectricalResistanceUnit, FloatElectricalResistance>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatElectricalResistance ZERO = new FloatElectricalResistance(0.0f, ElectricalResistanceUnit.SI);

    /** Constant with value one. */
    public static final FloatElectricalResistance ONE = new FloatElectricalResistance(1.0f, ElectricalResistanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatElectricalResistance NaN = new FloatElectricalResistance(Float.NaN, ElectricalResistanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatElectricalResistance POSITIVE_INFINITY =
            new FloatElectricalResistance(Float.POSITIVE_INFINITY, ElectricalResistanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatElectricalResistance NEGATIVE_INFINITY =
            new FloatElectricalResistance(Float.NEGATIVE_INFINITY, ElectricalResistanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatElectricalResistance POS_MAXVALUE =
            new FloatElectricalResistance(Float.MAX_VALUE, ElectricalResistanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatElectricalResistance NEG_MAXVALUE =
            new FloatElectricalResistance(-Float.MAX_VALUE, ElectricalResistanceUnit.SI);

    /**
     * Construct FloatElectricalResistance scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatElectricalResistance(final float value, final ElectricalResistanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatElectricalResistance scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatElectricalResistance(final FloatElectricalResistance value)
    {
        super(value);
    }

    /**
     * Construct FloatElectricalResistance scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatElectricalResistance(final double value, final ElectricalResistanceUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatElectricalResistance instantiateRel(final float value, final ElectricalResistanceUnit unit)
    {
        return new FloatElectricalResistance(value, unit);
    }

    /**
     * Construct FloatElectricalResistance scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatElectricalResistance ofSI(final float value)
    {
        return new FloatElectricalResistance(value, ElectricalResistanceUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatElectricalResistance at the given ratio between 0 and 1
     */
    public static FloatElectricalResistance interpolate(final FloatElectricalResistance zero,
            final FloatElectricalResistance one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatElectricalResistance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatElectricalResistance max(final FloatElectricalResistance r1, final FloatElectricalResistance r2)
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
    public static FloatElectricalResistance max(final FloatElectricalResistance r1, final FloatElectricalResistance r2,
            final FloatElectricalResistance... rn)
    {
        FloatElectricalResistance maxr = r1.gt(r2) ? r1 : r2;
        for (FloatElectricalResistance r : rn)
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
    public static FloatElectricalResistance min(final FloatElectricalResistance r1, final FloatElectricalResistance r2)
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
    public static FloatElectricalResistance min(final FloatElectricalResistance r1, final FloatElectricalResistance r2,
            final FloatElectricalResistance... rn)
    {
        FloatElectricalResistance minr = r1.lt(r2) ? r1 : r2;
        for (FloatElectricalResistance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatElectricalResistance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatElectricalResistance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatElectricalResistance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatElectricalResistance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalResistance: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ElectricalResistanceUnit unit = ElectricalResistanceUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity ElectricalResistance",
                    unitString);
            return new FloatElectricalResistance(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatElectricalResistance from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatElectricalResistance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatElectricalResistance of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatElectricalResistance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalResistance: empty unitString");
        ElectricalResistanceUnit unit = ElectricalResistanceUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatElectricalResistance with unit %s",
                unitString);
        return new FloatElectricalResistance(value, unit);
    }

    /**
     * Calculate the division of FloatElectricalResistance and FloatElectricalResistance, which results in a FloatDimensionless
     * scalar.
     * @param v scalar
     * @return scalar as a division of FloatElectricalResistance and FloatElectricalResistance
     */
    public final FloatDimensionless divide(final FloatElectricalResistance v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalResistance and FloatElectricalConductance, which results in a
     * FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatElectricalResistance and FloatElectricalConductance
     */
    public final FloatDimensionless times(final FloatElectricalConductance v)
    {
        return new FloatDimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalResistance and FloatElectricalCurrent, which results in a
     * FloatElectricalPotential scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatElectricalResistance and FloatElectricalCurrent
     */
    public final FloatElectricalPotential times(final FloatElectricalCurrent v)
    {
        return new FloatElectricalPotential(this.si * v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalResistance and FloatDuration, which results in a FloatElectricalInductance
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatElectricalResistance and FloatDuration
     */
    public final FloatElectricalInductance times(final FloatDuration v)
    {
        return new FloatElectricalInductance(this.si * v.si, ElectricalInductanceUnit.SI);
    }

    @Override
    public FloatElectricalConductance reciprocal()
    {
        return FloatElectricalConductance.ofSI(1.0f / this.si);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatElectricalResistance.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatElectricalResistance
     */
    public static FloatElectricalResistance multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(ElectricalResistanceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class,
                "Multiplying %s by %s does not result in instance of type FloatElectricalResistance", scalar1.toDisplayString(),
                scalar2.toDisplayString());
        return new FloatElectricalResistance(scalar1.si * scalar2.si, ElectricalResistanceUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatElectricalResistance.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatElectricalResistance
     */
    public static FloatElectricalResistance divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(ElectricalResistanceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class,
                "Dividing %s by %s does not result in an instance of type FloatElectricalResistance", scalar1.toDisplayString(),
                scalar2.toDisplayString());
        return new FloatElectricalResistance(scalar1.si / scalar2.si, ElectricalResistanceUnit.SI);
    }

}
