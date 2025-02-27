package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.scalar.base.FloatScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatElectricalInductance FloatScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatElectricalInductance extends FloatScalarRel<ElectricalInductanceUnit, FloatElectricalInductance>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatElectricalInductance ZERO = new FloatElectricalInductance(0.0f, ElectricalInductanceUnit.SI);

    /** Constant with value one. */
    public static final FloatElectricalInductance ONE = new FloatElectricalInductance(1.0f, ElectricalInductanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final FloatElectricalInductance NaN = new FloatElectricalInductance(Float.NaN, ElectricalInductanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final FloatElectricalInductance POSITIVE_INFINITY =
            new FloatElectricalInductance(Float.POSITIVE_INFINITY, ElectricalInductanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final FloatElectricalInductance NEGATIVE_INFINITY =
            new FloatElectricalInductance(Float.NEGATIVE_INFINITY, ElectricalInductanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final FloatElectricalInductance POS_MAXVALUE =
            new FloatElectricalInductance(Float.MAX_VALUE, ElectricalInductanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final FloatElectricalInductance NEG_MAXVALUE =
            new FloatElectricalInductance(-Float.MAX_VALUE, ElectricalInductanceUnit.SI);

    /**
     * Construct FloatElectricalInductance scalar.
     * @param value the float value
     * @param unit unit for the float value
     */
    public FloatElectricalInductance(final float value, final ElectricalInductanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatElectricalInductance scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatElectricalInductance(final FloatElectricalInductance value)
    {
        super(value);
    }

    /**
     * Construct FloatElectricalInductance scalar using a double value.
     * @param value the double value
     * @param unit unit for the resulting float value
     */
    public FloatElectricalInductance(final double value, final ElectricalInductanceUnit unit)
    {
        super((float) value, unit);
    }

    @Override
    public final FloatElectricalInductance instantiateRel(final float value, final ElectricalInductanceUnit unit)
    {
        return new FloatElectricalInductance(value, unit);
    }

    /**
     * Construct FloatElectricalInductance scalar.
     * @param value the float value in SI units
     * @return the new scalar with the SI value
     */
    public static final FloatElectricalInductance instantiateSI(final float value)
    {
        return new FloatElectricalInductance(value, ElectricalInductanceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static FloatElectricalInductance interpolate(final FloatElectricalInductance zero,
            final FloatElectricalInductance one, final float ratio)
    {
        return new FloatElectricalInductance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static FloatElectricalInductance max(final FloatElectricalInductance r1, final FloatElectricalInductance r2)
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
    public static FloatElectricalInductance max(final FloatElectricalInductance r1, final FloatElectricalInductance r2,
            final FloatElectricalInductance... rn)
    {
        FloatElectricalInductance maxr = r1.gt(r2) ? r1 : r2;
        for (FloatElectricalInductance r : rn)
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
    public static FloatElectricalInductance min(final FloatElectricalInductance r1, final FloatElectricalInductance r2)
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
    public static FloatElectricalInductance min(final FloatElectricalInductance r1, final FloatElectricalInductance r2,
            final FloatElectricalInductance... rn)
    {
        FloatElectricalInductance minr = r1.lt(r2) ? r1 : r2;
        for (FloatElectricalInductance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a FloatElectricalInductance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatElectricalInductance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatElectricalInductance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatElectricalInductance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalInductance: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ElectricalInductanceUnit unit = ElectricalInductanceUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit == null)
                throw new IllegalArgumentException("Unit " + unitString + " not found");
            return new FloatElectricalInductance(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatElectricalInductance from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatElectricalInductance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatElectricalInductance of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatElectricalInductance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatElectricalInductance: empty unitString");
        ElectricalInductanceUnit unit = ElectricalInductanceUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new FloatElectricalInductance(value, unit);
        }
        throw new IllegalArgumentException("Error parsing FloatElectricalInductance with unit " + unitString);
    }

    /**
     * Calculate the division of FloatElectricalInductance and FloatElectricalInductance, which results in a FloatDimensionless
     * scalar.
     * @param v scalar
     * @return scalar as a division of FloatElectricalInductance and FloatElectricalInductance
     */
    public final FloatDimensionless divide(final FloatElectricalInductance v)
    {
        return new FloatDimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of FloatElectricalInductance and FloatElectricalCurrent, which results in a
     * FloatMagneticFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of FloatElectricalInductance and FloatElectricalCurrent
     */
    public final FloatMagneticFlux times(final FloatElectricalCurrent v)
    {
        return new FloatMagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    @Override
    public FloatSIScalar reciprocal()
    {
        return FloatScalar.divide(FloatDimensionless.ONE, this);
    }

}
