package org.djunits.old.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.EnergyUnit;
import org.djunits.old.unit.ForceUnit;
import org.djunits.old.unit.PressureUnit;
import org.djunits.old.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Pressure DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class Pressure extends DoubleScalarRel<PressureUnit, Pressure>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Pressure ZERO = new Pressure(0.0, PressureUnit.SI);

    /** Constant with value one. */
    public static final Pressure ONE = new Pressure(1.0, PressureUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Pressure NaN = new Pressure(Double.NaN, PressureUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Pressure POSITIVE_INFINITY = new Pressure(Double.POSITIVE_INFINITY, PressureUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Pressure NEGATIVE_INFINITY = new Pressure(Double.NEGATIVE_INFINITY, PressureUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Pressure POS_MAXVALUE = new Pressure(Double.MAX_VALUE, PressureUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Pressure NEG_MAXVALUE = new Pressure(-Double.MAX_VALUE, PressureUnit.SI);

    /**
     * Construct Pressure scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public Pressure(final double value, final PressureUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Pressure scalar.
     * @param value Scalar from which to construct this instance
     */
    public Pressure(final Pressure value)
    {
        super(value);
    }

    @Override
    public final Pressure instantiateRel(final double value, final PressureUnit unit)
    {
        return new Pressure(value, unit);
    }

    /**
     * Construct Pressure scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Pressure ofSI(final double value)
    {
        return new Pressure(value, PressureUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Pressure at the given ratio between 0 and 1
     */
    public static Pressure interpolate(final Pressure zero, final Pressure one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new Pressure(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Pressure max(final Pressure r1, final Pressure r2)
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
    public static Pressure max(final Pressure r1, final Pressure r2, final Pressure... rn)
    {
        Pressure maxr = r1.gt(r2) ? r1 : r2;
        for (Pressure r : rn)
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
    public static Pressure min(final Pressure r1, final Pressure r2)
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
    public static Pressure min(final Pressure r1, final Pressure r2, final Pressure... rn)
    {
        Pressure minr = r1.lt(r2) ? r1 : r2;
        for (Pressure r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Pressure representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Pressure
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Pressure valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Pressure: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Pressure: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            PressureUnit unit = PressureUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Pressure", unitString);
            return new Pressure(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Pressure from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Pressure based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Pressure of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Pressure: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Pressure: empty unitString");
        PressureUnit unit = PressureUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Pressure with unit %s", unitString);
        return new Pressure(value, unit);
    }

    /**
     * Calculate the division of Pressure and Pressure, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Pressure and Pressure
     */
    public final Dimensionless divide(final Pressure v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Pressure and Area, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Pressure and Area
     */
    public final Force times(final Area v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of Pressure and Volume, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Pressure and Volume
     */
    public final Energy times(final Volume v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type Pressure.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of Pressure
     */
    public static Pressure multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(PressureUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type Pressure",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Pressure(scalar1.si * scalar2.si, PressureUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type Pressure.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of Pressure
     */
    public static Pressure divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(PressureUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type Pressure",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Pressure(scalar1.si / scalar2.si, PressureUnit.SI);
    }

}
