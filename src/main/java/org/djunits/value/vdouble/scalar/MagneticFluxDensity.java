package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the MagneticFluxDensity DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class MagneticFluxDensity extends DoubleScalarRel<MagneticFluxDensityUnit, MagneticFluxDensity>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final MagneticFluxDensity ZERO = new MagneticFluxDensity(0.0, MagneticFluxDensityUnit.SI);

    /** Constant with value one. */
    public static final MagneticFluxDensity ONE = new MagneticFluxDensity(1.0, MagneticFluxDensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final MagneticFluxDensity NaN = new MagneticFluxDensity(Double.NaN, MagneticFluxDensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final MagneticFluxDensity POSITIVE_INFINITY =
            new MagneticFluxDensity(Double.POSITIVE_INFINITY, MagneticFluxDensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final MagneticFluxDensity NEGATIVE_INFINITY =
            new MagneticFluxDensity(Double.NEGATIVE_INFINITY, MagneticFluxDensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final MagneticFluxDensity POS_MAXVALUE =
            new MagneticFluxDensity(Double.MAX_VALUE, MagneticFluxDensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final MagneticFluxDensity NEG_MAXVALUE =
            new MagneticFluxDensity(-Double.MAX_VALUE, MagneticFluxDensityUnit.SI);

    /**
     * Construct MagneticFluxDensity scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public MagneticFluxDensity(final double value, final MagneticFluxDensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct MagneticFluxDensity scalar.
     * @param value Scalar from which to construct this instance
     */
    public MagneticFluxDensity(final MagneticFluxDensity value)
    {
        super(value);
    }

    @Override
    public final MagneticFluxDensity instantiateRel(final double value, final MagneticFluxDensityUnit unit)
    {
        return new MagneticFluxDensity(value, unit);
    }

    /**
     * Construct MagneticFluxDensity scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final MagneticFluxDensity ofSI(final double value)
    {
        return new MagneticFluxDensity(value, MagneticFluxDensityUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a MagneticFluxDensity at the given ratio between 0 and 1
     */
    public static MagneticFluxDensity interpolate(final MagneticFluxDensity zero, final MagneticFluxDensity one,
            final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new MagneticFluxDensity(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static MagneticFluxDensity max(final MagneticFluxDensity r1, final MagneticFluxDensity r2)
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
    public static MagneticFluxDensity max(final MagneticFluxDensity r1, final MagneticFluxDensity r2,
            final MagneticFluxDensity... rn)
    {
        MagneticFluxDensity maxr = r1.gt(r2) ? r1 : r2;
        for (MagneticFluxDensity r : rn)
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
    public static MagneticFluxDensity min(final MagneticFluxDensity r1, final MagneticFluxDensity r2)
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
    public static MagneticFluxDensity min(final MagneticFluxDensity r1, final MagneticFluxDensity r2,
            final MagneticFluxDensity... rn)
    {
        MagneticFluxDensity minr = r1.lt(r2) ? r1 : r2;
        for (MagneticFluxDensity r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a MagneticFluxDensity representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a MagneticFluxDensity
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static MagneticFluxDensity valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing MagneticFluxDensity: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing MagneticFluxDensity: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            MagneticFluxDensityUnit unit = MagneticFluxDensityUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity MagneticFluxDensity",
                    unitString);
            return new MagneticFluxDensity(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing MagneticFluxDensity from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a MagneticFluxDensity based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static MagneticFluxDensity of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing MagneticFluxDensity: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing MagneticFluxDensity: empty unitString");
        MagneticFluxDensityUnit unit = MagneticFluxDensityUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing MagneticFluxDensity with unit %s", unitString);
        return new MagneticFluxDensity(value, unit);
    }

    /**
     * Calculate the division of MagneticFluxDensity and MagneticFluxDensity, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of MagneticFluxDensity and MagneticFluxDensity
     */
    public final Dimensionless divide(final MagneticFluxDensity v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of MagneticFluxDensity and Area, which results in a MagneticFlux scalar.
     * @param v scalar
     * @return scalar as a multiplication of MagneticFluxDensity and Area
     */
    public final MagneticFlux times(final Area v)
    {
        return new MagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type MagneticFluxDensity.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of MagneticFluxDensity
     */
    public static MagneticFluxDensity multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(MagneticFluxDensityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type MagneticFluxDensity",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new MagneticFluxDensity(scalar1.si * scalar2.si, MagneticFluxDensityUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type MagneticFluxDensity.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of MagneticFluxDensity
     */
    public static MagneticFluxDensity divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(MagneticFluxDensityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type MagneticFluxDensity",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new MagneticFluxDensity(scalar1.si / scalar2.si, MagneticFluxDensityUnit.SI);
    }

}
