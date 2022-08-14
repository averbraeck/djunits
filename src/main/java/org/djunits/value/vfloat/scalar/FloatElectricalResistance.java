package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;

/**
 * Easy access methods for the FloatElectricalResistance FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalResistance extends AbstractFloatScalarRel<ElectricalResistanceUnit, FloatElectricalResistance>
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
     * Construct FloatElectricalResistance scalar.
     * @param value float; the float value
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
     * Construct FloatElectricalResistance scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatElectricalResistance(final double value, final ElectricalResistanceUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatElectricalResistance instantiateRel(final float value, final ElectricalResistanceUnit unit)
    {
        return new FloatElectricalResistance(value, unit);
    }

    /**
     * Construct FloatElectricalResistance scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatElectricalResistance instantiateSI(final float value)
    {
        return new FloatElectricalResistance(value, ElectricalResistanceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatElectricalResistance interpolate(final FloatElectricalResistance zero,
            final FloatElectricalResistance one, final float ratio)
    {
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
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatElectricalResistance
     * @return FloatElectricalResistance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatElectricalResistance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatElectricalResistance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalResistance: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            ElectricalResistanceUnit unit = ElectricalResistanceUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatElectricalResistance(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatElectricalResistance from " + text);
    }

    /**
     * Returns a FloatElectricalResistance based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatElectricalResistance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatElectricalResistance of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatElectricalResistance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalResistance: empty unitString");
        ElectricalResistanceUnit unit = ElectricalResistanceUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatElectricalResistance(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatElectricalResistance with unit " + unitString);
    }

    /**
     * Calculate the division of FloatElectricalResistance and FloatElectricalResistance, which results in a FloatDimensionless
     * scalar.
     * @param v FloatElectricalResistance; scalar
     * @return FloatDimensionless; scalar as a division of FloatElectricalResistance and FloatElectricalResistance
     */
    public final FloatDimensionless divide(final FloatElectricalResistance v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalResistance and FloatElectricalConductance, which results in a
     * FloatDimensionless scalar.
     * @param v FloatElectricalResistance; scalar
     * @return FloatDimensionless; scalar as a multiplication of FloatElectricalResistance and FloatElectricalConductance
     */
    public final FloatDimensionless times(final FloatElectricalConductance v)
    {
        return new FloatDimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalResistance and FloatElectricalCurrent, which results in a
     * FloatElectricalPotential scalar.
     * @param v FloatElectricalResistance; scalar
     * @return FloatElectricalPotential; scalar as a multiplication of FloatElectricalResistance and FloatElectricalCurrent
     */
    public final FloatElectricalPotential times(final FloatElectricalCurrent v)
    {
        return new FloatElectricalPotential(this.si * v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalResistance and FloatDuration, which results in a FloatElectricalInductance
     * scalar.
     * @param v FloatElectricalResistance; scalar
     * @return FloatElectricalInductance; scalar as a multiplication of FloatElectricalResistance and FloatDuration
     */
    public final FloatElectricalInductance times(final FloatDuration v)
    {
        return new FloatElectricalInductance(this.si * v.si, ElectricalInductanceUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatElectricalConductance reciprocal()
    {
        return FloatElectricalConductance.instantiateSI(1.0f / this.si);
    }

}
