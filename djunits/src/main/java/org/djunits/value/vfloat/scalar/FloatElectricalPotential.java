package org.djunits.value.vfloat.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.scalar.base.AbstractFloatScalarRel;
import org.djunits.value.vfloat.scalar.base.FloatScalar;

/**
 * Easy access methods for the FloatElectricalPotential FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class FloatElectricalPotential extends AbstractFloatScalarRel<ElectricalPotentialUnit, FloatElectricalPotential>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatElectricalPotential ZERO = new FloatElectricalPotential(0.0f, ElectricalPotentialUnit.SI);

    /** Constant with value one. */
    public static final FloatElectricalPotential ONE = new FloatElectricalPotential(1.0f, ElectricalPotentialUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatElectricalPotential NaN = new FloatElectricalPotential(Float.NaN, ElectricalPotentialUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatElectricalPotential POSITIVE_INFINITY =
            new FloatElectricalPotential(Float.POSITIVE_INFINITY, ElectricalPotentialUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatElectricalPotential NEGATIVE_INFINITY =
            new FloatElectricalPotential(Float.NEGATIVE_INFINITY, ElectricalPotentialUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatElectricalPotential POS_MAXVALUE =
            new FloatElectricalPotential(Float.MAX_VALUE, ElectricalPotentialUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatElectricalPotential NEG_MAXVALUE =
            new FloatElectricalPotential(-Float.MAX_VALUE, ElectricalPotentialUnit.SI);

    /**
     * Construct FloatElectricalPotential scalar.
     * @param value float; the float value
     * @param unit unit for the float value
     */
    public FloatElectricalPotential(final float value, final ElectricalPotentialUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatElectricalPotential scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatElectricalPotential(final FloatElectricalPotential value)
    {
        super(value);
    }

    /**
     * Construct FloatElectricalPotential scalar using a double value.
     * @param value double; the double value
     * @param unit unit for the resulting float value
     */
    public FloatElectricalPotential(final double value, final ElectricalPotentialUnit unit)
    {
        super((float) value, unit);
    }

    /** {@inheritDoc} */
    @Override
    public final FloatElectricalPotential instantiateRel(final float value, final ElectricalPotentialUnit unit)
    {
        return new FloatElectricalPotential(value, unit);
    }

    /**
     * Construct FloatElectricalPotential scalar.
     * @param value float; the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatElectricalPotential instantiateSI(final float value)
    {
        return new FloatElectricalPotential(value, ElectricalPotentialUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatElectricalPotential interpolate(final FloatElectricalPotential zero, final FloatElectricalPotential one,
            final float ratio)
    {
        return new FloatElectricalPotential(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatElectricalPotential max(final FloatElectricalPotential r1, final FloatElectricalPotential r2)
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
    public static FloatElectricalPotential max(final FloatElectricalPotential r1, final FloatElectricalPotential r2,
            final FloatElectricalPotential... rn)
    {
        FloatElectricalPotential maxr = r1.gt(r2) ? r1 : r2;
        for (FloatElectricalPotential r : rn)
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
    public static FloatElectricalPotential min(final FloatElectricalPotential r1, final FloatElectricalPotential r2)
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
    public static FloatElectricalPotential min(final FloatElectricalPotential r1, final FloatElectricalPotential r2,
            final FloatElectricalPotential... rn)
    {
        FloatElectricalPotential minr = r1.lt(r2) ? r1 : r2;
        for (FloatElectricalPotential r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatElectricalPotential representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a FloatElectricalPotential
     * @return FloatElectricalPotential; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatElectricalPotential valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatElectricalPotential: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalPotential: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            ElectricalPotentialUnit unit = ElectricalPotentialUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                float f = Float.parseFloat(valueString);
                return new FloatElectricalPotential(f, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing FloatElectricalPotential from " + text);
    }

    /**
     * Returns a FloatElectricalPotential based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return FloatElectricalPotential; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatElectricalPotential of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatElectricalPotential: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalPotential: empty unitString");
        ElectricalPotentialUnit unit = ElectricalPotentialUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatElectricalPotential(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatElectricalPotential with unit " + unitString);
    }

    /**
     * Calculate the division of FloatElectricalPotential and FloatElectricalPotential, which results in a FloatDimensionless
     * scalar.
     * @param v FloatElectricalPotential; scalar
     * @return FloatDimensionless; scalar as a division of FloatElectricalPotential and FloatElectricalPotential
     */
    public final FloatDimensionless divide(final FloatElectricalPotential v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalPotential and FloatElectricalCurrent, which results in a FloatPower
     * scalar.
     * @param v FloatElectricalPotential; scalar
     * @return FloatPower; scalar as a multiplication of FloatElectricalPotential and FloatElectricalCurrent
     */
    public final FloatPower times(final FloatElectricalCurrent v)
    {
        return new FloatPower(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the division of FloatElectricalPotential and FloatElectricalCurrent, which results in a
     * FloatElectricalResistance scalar.
     * @param v FloatElectricalPotential; scalar
     * @return FloatElectricalResistance; scalar as a division of FloatElectricalPotential and FloatElectricalCurrent
     */
    public final FloatElectricalResistance divide(final FloatElectricalCurrent v)
    {
        return new FloatElectricalResistance(this.si / v.si, ElectricalResistanceUnit.SI);
    }

    /**
     * Calculate the division of FloatElectricalPotential and FloatElectricalResistance, which results in a
     * FloatElectricalCurrent scalar.
     * @param v FloatElectricalPotential; scalar
     * @return FloatElectricalCurrent; scalar as a division of FloatElectricalPotential and FloatElectricalResistance
     */
    public final FloatElectricalCurrent divide(final FloatElectricalResistance v)
    {
        return new FloatElectricalCurrent(this.si / v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalPotential and FloatDuration, which results in a FloatMagneticFlux scalar.
     * @param v FloatElectricalPotential; scalar
     * @return FloatMagneticFlux; scalar as a multiplication of FloatElectricalPotential and FloatDuration
     */
    public final FloatMagneticFlux times(final FloatDuration v)
    {
        return new FloatMagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
