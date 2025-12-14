package org.djunits.old.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.ElectricalCapacitanceUnit;
import org.djunits.old.unit.ElectricalConductanceUnit;
import org.djunits.old.unit.ElectricalCurrentUnit;
import org.djunits.old.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatElectricalConductance FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatElectricalConductance extends FloatScalarRel<ElectricalConductanceUnit, FloatElectricalConductance>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatElectricalConductance ZERO = new FloatElectricalConductance(0.0f, ElectricalConductanceUnit.SI);

    /** Constant with value one. */
    public static final FloatElectricalConductance ONE = new FloatElectricalConductance(1.0f, ElectricalConductanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatElectricalConductance NaN =
            new FloatElectricalConductance(Float.NaN, ElectricalConductanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatElectricalConductance POSITIVE_INFINITY =
            new FloatElectricalConductance(Float.POSITIVE_INFINITY, ElectricalConductanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatElectricalConductance NEGATIVE_INFINITY =
            new FloatElectricalConductance(Float.NEGATIVE_INFINITY, ElectricalConductanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatElectricalConductance POS_MAXVALUE =
            new FloatElectricalConductance(Float.MAX_VALUE, ElectricalConductanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatElectricalConductance NEG_MAXVALUE =
            new FloatElectricalConductance(-Float.MAX_VALUE, ElectricalConductanceUnit.SI);

    /**
     * Construct FloatElectricalConductance scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatElectricalConductance(final float value, final ElectricalConductanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatElectricalConductance scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatElectricalConductance(final FloatElectricalConductance value)
    {
        super(value);
    }

    /**
     * Construct FloatElectricalConductance scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatElectricalConductance(final double value, final ElectricalConductanceUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatElectricalConductance instantiateRel(final float value, final ElectricalConductanceUnit unit)
    {
        return new FloatElectricalConductance(value, unit);
    }

    /**
     * Construct FloatElectricalConductance scalar based on an SI value.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatElectricalConductance ofSI(final float value)
    {
        return new FloatElectricalConductance(value, ElectricalConductanceUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatElectricalConductance at the given ratio between 0 and 1
     */
    public static FloatElectricalConductance interpolate(final FloatElectricalConductance zero,
            final FloatElectricalConductance one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatElectricalConductance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatElectricalConductance max(final FloatElectricalConductance r1, final FloatElectricalConductance r2)
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
    public static FloatElectricalConductance max(final FloatElectricalConductance r1, final FloatElectricalConductance r2,
            final FloatElectricalConductance... rn)
    {
        FloatElectricalConductance maxr = r1.gt(r2) ? r1 : r2;
        for (FloatElectricalConductance r : rn)
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
    public static FloatElectricalConductance min(final FloatElectricalConductance r1, final FloatElectricalConductance r2)
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
    public static FloatElectricalConductance min(final FloatElectricalConductance r1, final FloatElectricalConductance r2,
            final FloatElectricalConductance... rn)
    {
        FloatElectricalConductance minr = r1.lt(r2) ? r1 : r2;
        for (FloatElectricalConductance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatElectricalConductance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatElectricalConductance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatElectricalConductance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatElectricalConductance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalConductance: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ElectricalConductanceUnit unit = ElectricalConductanceUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity ElectricalConductance",
                    unitString);
            return new FloatElectricalConductance(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatElectricalConductance from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatElectricalConductance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatElectricalConductance of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatElectricalConductance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalConductance: empty unitString");
        ElectricalConductanceUnit unit = ElectricalConductanceUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatElectricalConductance with unit %s",
                unitString);
        return new FloatElectricalConductance(value, unit);
    }

    /**
     * Calculate the division of FloatElectricalConductance and FloatElectricalConductance, which results in a
     * FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatElectricalConductance and FloatElectricalConductance
     */
    public final FloatDimensionless divide(final FloatElectricalConductance v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalConductance and FloatElectricalResistance, which results in a
     * FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatElectricalConductance and FloatElectricalResistance
     */
    public final FloatDimensionless times(final FloatElectricalResistance v)
    {
        return new FloatDimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalConductance and FloatElectricalPotential, which results in a
     * FloatElectricalCurrent scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatElectricalConductance and FloatElectricalPotential
     */
    public final FloatElectricalCurrent times(final FloatElectricalPotential v)
    {
        return new FloatElectricalCurrent(this.si * v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalConductance and FloatDuration, which results in a
     * FloatElectricalCapacitance scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatElectricalConductance and FloatDuration
     */
    public final FloatElectricalCapacitance times(final FloatDuration v)
    {
        return new FloatElectricalCapacitance(this.si * v.si, ElectricalCapacitanceUnit.SI);
    }

    @Override
    public FloatElectricalResistance reciprocal()
    {
        return FloatElectricalResistance.ofSI(1.0f / this.si);
    }

    /**
     * Multiply two scalars that result in a scalar of type FloatElectricalConductance.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of FloatElectricalConductance
     */
    public static FloatElectricalConductance multiply(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(ElectricalConductanceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class,
                "Multiplying %s by %s does not result in instance of type FloatElectricalConductance",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatElectricalConductance(scalar1.si * scalar2.si, ElectricalConductanceUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type FloatElectricalConductance.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of FloatElectricalConductance
     */
    public static FloatElectricalConductance divide(final FloatScalarRel<?, ?> scalar1, final FloatScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(ElectricalConductanceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class,
                "Dividing %s by %s does not result in an instance of type FloatElectricalConductance",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new FloatElectricalConductance(scalar1.si / scalar2.si, ElectricalConductanceUnit.SI);
    }

}
