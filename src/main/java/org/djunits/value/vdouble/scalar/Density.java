package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.MassUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Density DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class Density extends DoubleScalarRel<DensityUnit, Density>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Density ZERO = new Density(0.0, DensityUnit.SI);

    /** Constant with value one. */
    public static final Density ONE = new Density(1.0, DensityUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Density NaN = new Density(Double.NaN, DensityUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Density POSITIVE_INFINITY = new Density(Double.POSITIVE_INFINITY, DensityUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Density NEGATIVE_INFINITY = new Density(Double.NEGATIVE_INFINITY, DensityUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Density POS_MAXVALUE = new Density(Double.MAX_VALUE, DensityUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Density NEG_MAXVALUE = new Density(-Double.MAX_VALUE, DensityUnit.SI);

    /**
     * Construct Density scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public Density(final double value, final DensityUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Density scalar.
     * @param value Scalar from which to construct this instance
     */
    public Density(final Density value)
    {
        super(value);
    }

    @Override
    public final Density instantiateRel(final double value, final DensityUnit unit)
    {
        return new Density(value, unit);
    }

    /**
     * Construct Density scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Density ofSI(final double value)
    {
        return new Density(value, DensityUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Density at the given ratio between 0 and 1
     */
    public static Density interpolate(final Density zero, final Density one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new Density(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Density max(final Density r1, final Density r2)
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
    public static Density max(final Density r1, final Density r2, final Density... rn)
    {
        Density maxr = r1.gt(r2) ? r1 : r2;
        for (Density r : rn)
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
    public static Density min(final Density r1, final Density r2)
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
    public static Density min(final Density r1, final Density r2, final Density... rn)
    {
        Density minr = r1.lt(r2) ? r1 : r2;
        for (Density r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Density representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a Density
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Density valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Density: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Density: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            DensityUnit unit = DensityUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Density", unitString);
            return new Density(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Density from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Density based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Density of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Density: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Density: empty unitString");
        DensityUnit unit = DensityUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Density with unit %s", unitString);
        return new Density(value, unit);
    }

    /**
     * Calculate the division of Density and Density, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Density and Density
     */
    public final Dimensionless divide(final Density v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Density and Volume, which results in a Mass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Density and Volume
     */
    public final Mass times(final Volume v)
    {
        return new Mass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the multiplication of Density and FlowVolume, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Density and FlowVolume
     */
    public final FlowMass times(final FlowVolume v)
    {
        return new FlowMass(this.si * v.si, FlowMassUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type Density.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of Density
     */
    public static Density multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(DensityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type Density",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Density(scalar1.si * scalar2.si, DensityUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type Density.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of Density
     */
    public static Density divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(DensityUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type Density",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Density(scalar1.si / scalar2.si, DensityUnit.SI);
    }

}
