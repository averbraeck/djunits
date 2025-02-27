package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatElectricalCharge FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatElectricalCharge extends FloatScalarRel<ElectricalChargeUnit, FloatElectricalCharge>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatElectricalCharge ZERO = new FloatElectricalCharge(0.0f, ElectricalChargeUnit.SI);

    /** Constant with value one. */
    public static final FloatElectricalCharge ONE = new FloatElectricalCharge(1.0f, ElectricalChargeUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatElectricalCharge NaN = new FloatElectricalCharge(Float.NaN, ElectricalChargeUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatElectricalCharge POSITIVE_INFINITY =
            new FloatElectricalCharge(Float.POSITIVE_INFINITY, ElectricalChargeUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatElectricalCharge NEGATIVE_INFINITY =
            new FloatElectricalCharge(Float.NEGATIVE_INFINITY, ElectricalChargeUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatElectricalCharge POS_MAXVALUE =
            new FloatElectricalCharge(Float.MAX_VALUE, ElectricalChargeUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatElectricalCharge NEG_MAXVALUE =
            new FloatElectricalCharge(-Float.MAX_VALUE, ElectricalChargeUnit.SI);

    /**
     * Construct FloatElectricalCharge scalar.
     * @param value the float value
     * @param unit unit for the float value
     */
    public FloatElectricalCharge(final float value, final ElectricalChargeUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatElectricalCharge scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatElectricalCharge(final FloatElectricalCharge value)
    {
        super(value);
    }

    /**
     * Construct FloatElectricalCharge scalar using a double value.
     * @param value the double value
     * @param unit unit for the resulting float value
     */
    public FloatElectricalCharge(final double value, final ElectricalChargeUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatElectricalCharge instantiateRel(final float value, final ElectricalChargeUnit unit)
    {
        return new FloatElectricalCharge(value, unit);
    }

    /**
     * Construct FloatElectricalCharge scalar.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatElectricalCharge instantiateSI(final float value)
    {
        return new FloatElectricalCharge(value, ElectricalChargeUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatElectricalCharge interpolate(final FloatElectricalCharge zero, final FloatElectricalCharge one,
            final float ratio)
    {
        return new FloatElectricalCharge(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatElectricalCharge max(final FloatElectricalCharge r1, final FloatElectricalCharge r2)
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
    public static FloatElectricalCharge max(final FloatElectricalCharge r1, final FloatElectricalCharge r2,
            final FloatElectricalCharge... rn)
    {
        FloatElectricalCharge maxr = r1.gt(r2) ? r1 : r2;
        for (FloatElectricalCharge r : rn)
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
    public static FloatElectricalCharge min(final FloatElectricalCharge r1, final FloatElectricalCharge r2)
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
    public static FloatElectricalCharge min(final FloatElectricalCharge r1, final FloatElectricalCharge r2,
            final FloatElectricalCharge... rn)
    {
        FloatElectricalCharge minr = r1.lt(r2) ? r1 : r2;
        for (FloatElectricalCharge r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatElectricalCharge representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatElectricalCharge
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatElectricalCharge valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatElectricalCharge: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalCharge: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ElectricalChargeUnit unit = ElectricalChargeUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit == null)
                throw new IllegalArgumentException("Unit " + unitString + " not found");
            return new FloatElectricalCharge(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatElectricalCharge from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatElectricalCharge based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatElectricalCharge of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatElectricalCharge: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalCharge: empty unitString");
        ElectricalChargeUnit unit = ElectricalChargeUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatElectricalCharge(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatElectricalCharge with unit " + unitString);
    }

    /**
     * Calculate the division of FloatElectricalCharge and FloatElectricalCharge, which results in a FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatElectricalCharge and FloatElectricalCharge
     */
    public final FloatDimensionless divide(final FloatElectricalCharge v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of FloatElectricalCharge and FloatDuration, which results in a FloatElectricalCurrent scalar.
     * @param v scalar
     * @return scalar as a division of FloatElectricalCharge and FloatDuration
     */
    public final FloatElectricalCurrent divide(final FloatDuration v)
    {
        return new FloatElectricalCurrent(this.si / v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the division of FloatElectricalCharge and FloatElectricalCurrent, which results in a FloatDuration scalar.
     * @param v scalar
     * @return scalar as a division of FloatElectricalCharge and FloatElectricalCurrent
     */
    public final FloatDuration divide(final FloatElectricalCurrent v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the division of FloatElectricalCharge and FloatElectricalPotential, which results in a
     * FloatElectricalCapacitance scalar.
     * @param v scalar
     * @return scalar as a division of FloatElectricalCharge and FloatElectricalPotential
     */
    public final FloatElectricalCapacitance divide(final FloatElectricalPotential v)
    {
        return new FloatElectricalCapacitance(this.si / v.si, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Calculate the division of FloatElectricalCharge and FloatElectricalCapacitance, which results in a
     * FloatElectricalPotential scalar.
     * @param v scalar
     * @return scalar as a division of FloatElectricalCharge and FloatElectricalCapacitance
     */
    public final FloatElectricalPotential divide(final FloatElectricalCapacitance v)
    {
        return new FloatElectricalPotential(this.si / v.si, ElectricalPotentialUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
