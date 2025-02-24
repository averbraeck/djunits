package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatElectricalCapacitance FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatElectricalCapacitance extends FloatScalarRel<ElectricalCapacitanceUnit, FloatElectricalCapacitance>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatElectricalCapacitance ZERO = new FloatElectricalCapacitance(0.0f, ElectricalCapacitanceUnit.SI);

    /** Constant with value one. */
    public static final FloatElectricalCapacitance ONE = new FloatElectricalCapacitance(1.0f, ElectricalCapacitanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatElectricalCapacitance NaN =
            new FloatElectricalCapacitance(Float.NaN, ElectricalCapacitanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatElectricalCapacitance POSITIVE_INFINITY =
            new FloatElectricalCapacitance(Float.POSITIVE_INFINITY, ElectricalCapacitanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatElectricalCapacitance NEGATIVE_INFINITY =
            new FloatElectricalCapacitance(Float.NEGATIVE_INFINITY, ElectricalCapacitanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatElectricalCapacitance POS_MAXVALUE =
            new FloatElectricalCapacitance(Float.MAX_VALUE, ElectricalCapacitanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatElectricalCapacitance NEG_MAXVALUE =
            new FloatElectricalCapacitance(-Float.MAX_VALUE, ElectricalCapacitanceUnit.SI);

    /**
     * Construct FloatElectricalCapacitance scalar.
     * @param value the float value
     * @param unit unit for the float value
     */
    public FloatElectricalCapacitance(final float value, final ElectricalCapacitanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatElectricalCapacitance scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatElectricalCapacitance(final FloatElectricalCapacitance value)
    {
        super(value);
    }

    /**
     * Construct FloatElectricalCapacitance scalar using a double value.
     * @param value the double value
     * @param unit unit for the resulting float value
     */
    public FloatElectricalCapacitance(final double value, final ElectricalCapacitanceUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatElectricalCapacitance instantiateRel(final float value, final ElectricalCapacitanceUnit unit)
    {
        return new FloatElectricalCapacitance(value, unit);
    }

    /**
     * Construct FloatElectricalCapacitance scalar.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatElectricalCapacitance instantiateSI(final float value)
    {
        return new FloatElectricalCapacitance(value, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatElectricalCapacitance interpolate(final FloatElectricalCapacitance zero,
            final FloatElectricalCapacitance one, final float ratio)
    {
        return new FloatElectricalCapacitance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatElectricalCapacitance max(final FloatElectricalCapacitance r1, final FloatElectricalCapacitance r2)
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
    public static FloatElectricalCapacitance max(final FloatElectricalCapacitance r1, final FloatElectricalCapacitance r2,
            final FloatElectricalCapacitance... rn)
    {
        FloatElectricalCapacitance maxr = r1.gt(r2) ? r1 : r2;
        for (FloatElectricalCapacitance r : rn)
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
    public static FloatElectricalCapacitance min(final FloatElectricalCapacitance r1, final FloatElectricalCapacitance r2)
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
    public static FloatElectricalCapacitance min(final FloatElectricalCapacitance r1, final FloatElectricalCapacitance r2,
            final FloatElectricalCapacitance... rn)
    {
        FloatElectricalCapacitance minr = r1.lt(r2) ? r1 : r2;
        for (FloatElectricalCapacitance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatElectricalCapacitance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatElectricalCapacitance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatElectricalCapacitance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatElectricalCapacitance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalCapacitance: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ElectricalCapacitanceUnit unit = ElectricalCapacitanceUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit == null)
                throw new IllegalArgumentException("Unit " + unitString + " not found");
            return new FloatElectricalCapacitance(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatElectricalCapacitance from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatElectricalCapacitance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatElectricalCapacitance of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatElectricalCapacitance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalCapacitance: empty unitString");
        ElectricalCapacitanceUnit unit = ElectricalCapacitanceUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatElectricalCapacitance(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatElectricalCapacitance with unit " + unitString);
    }

    /**
     * Calculate the division of FloatElectricalCapacitance and FloatElectricalCapacitance, which results in a
     * FloatDimensionless scalar.
     * @param v scalar
     * @return scalar as a division of FloatElectricalCapacitance and FloatElectricalCapacitance
     */
    public final FloatDimensionless divide(final FloatElectricalCapacitance v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalCapacitance and FloatElectricalPotential, which results in a
     * FloatElectricalCharge scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatElectricalCapacitance and FloatElectricalPotential
     */
    public final FloatElectricalCharge times(final FloatElectricalPotential v)
    {
        return new FloatElectricalCharge(this.si * v.si, ElectricalChargeUnit.SI);
    }

    /**
     * Calculate the division of FloatElectricalCapacitance and FloatDuration, which results in a FloatElectricalConductance
     * scalar.
     * @param v scalar
     * @return scalar as a division of FloatElectricalCapacitance and FloatDuration
     */
    public final FloatElectricalConductance divide(final FloatDuration v)
    {
        return new FloatElectricalConductance(this.si / v.si, ElectricalConductanceUnit.SI);
    }

    /**
     * Calculate the division of FloatElectricalCapacitance and FloatElectricalConductance, which results in a FloatDuration
     * scalar.
     * @param v scalar
     * @return scalar as a division of FloatElectricalCapacitance and FloatElectricalConductance
     */
    public final FloatDuration divide(final FloatElectricalConductance v)
    {
        return new FloatDuration(this.si / v.si, DurationUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
