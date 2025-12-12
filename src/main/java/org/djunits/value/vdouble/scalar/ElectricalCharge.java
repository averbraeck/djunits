package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the ElectricalCharge DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class ElectricalCharge extends DoubleScalarRel<ElectricalChargeUnit, ElectricalCharge>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final ElectricalCharge ZERO = new ElectricalCharge(0.0, ElectricalChargeUnit.SI);

    /** Constant with value one. */
    public static final ElectricalCharge ONE = new ElectricalCharge(1.0, ElectricalChargeUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricalCharge NaN = new ElectricalCharge(Double.NaN, ElectricalChargeUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricalCharge POSITIVE_INFINITY =
            new ElectricalCharge(Double.POSITIVE_INFINITY, ElectricalChargeUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricalCharge NEGATIVE_INFINITY =
            new ElectricalCharge(Double.NEGATIVE_INFINITY, ElectricalChargeUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final ElectricalCharge POS_MAXVALUE = new ElectricalCharge(Double.MAX_VALUE, ElectricalChargeUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricalCharge NEG_MAXVALUE = new ElectricalCharge(-Double.MAX_VALUE, ElectricalChargeUnit.SI);

    /**
     * Construct ElectricalCharge scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public ElectricalCharge(final double value, final ElectricalChargeUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct ElectricalCharge scalar.
     * @param value Scalar from which to construct this instance
     */
    public ElectricalCharge(final ElectricalCharge value)
    {
        super(value);
    }

    @Override
    public final ElectricalCharge instantiateRel(final double value, final ElectricalChargeUnit unit)
    {
        return new ElectricalCharge(value, unit);
    }

    /**
     * Construct ElectricalCharge scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final ElectricalCharge ofSI(final double value)
    {
        return new ElectricalCharge(value, ElectricalChargeUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a ElectricalCharge at the given ratio between 0 and 1
     */
    public static ElectricalCharge interpolate(final ElectricalCharge zero, final ElectricalCharge one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new ElectricalCharge(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static ElectricalCharge max(final ElectricalCharge r1, final ElectricalCharge r2)
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
    public static ElectricalCharge max(final ElectricalCharge r1, final ElectricalCharge r2, final ElectricalCharge... rn)
    {
        ElectricalCharge maxr = r1.gt(r2) ? r1 : r2;
        for (ElectricalCharge r : rn)
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
    public static ElectricalCharge min(final ElectricalCharge r1, final ElectricalCharge r2)
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
    public static ElectricalCharge min(final ElectricalCharge r1, final ElectricalCharge r2, final ElectricalCharge... rn)
    {
        ElectricalCharge minr = r1.lt(r2) ? r1 : r2;
        for (ElectricalCharge r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a ElectricalCharge representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricalCharge
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalCharge valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing ElectricalCharge: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing ElectricalCharge: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ElectricalChargeUnit unit = ElectricalChargeUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity ElectricalCharge",
                    unitString);
            return new ElectricalCharge(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing ElectricalCharge from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a ElectricalCharge based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalCharge of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing ElectricalCharge: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalCharge: empty unitString");
        ElectricalChargeUnit unit = ElectricalChargeUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing ElectricalCharge with unit %s", unitString);
        return new ElectricalCharge(value, unit);
    }

    /**
     * Calculate the division of ElectricalCharge and ElectricalCharge, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCharge and ElectricalCharge
     */
    public final Dimensionless divide(final ElectricalCharge v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCharge and Duration, which results in a ElectricalCurrent scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCharge and Duration
     */
    public final ElectricalCurrent divide(final Duration v)
    {
        return new ElectricalCurrent(this.si / v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCharge and ElectricalCurrent, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCharge and ElectricalCurrent
     */
    public final Duration divide(final ElectricalCurrent v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCharge and ElectricalPotential, which results in a ElectricalCapacitance scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCharge and ElectricalPotential
     */
    public final ElectricalCapacitance divide(final ElectricalPotential v)
    {
        return new ElectricalCapacitance(this.si / v.si, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCharge and ElectricalCapacitance, which results in a ElectricalPotential scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCharge and ElectricalCapacitance
     */
    public final ElectricalPotential divide(final ElectricalCapacitance v)
    {
        return new ElectricalPotential(this.si / v.si, ElectricalPotentialUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type ElectricalCharge.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of ElectricalCharge
     */
    public static ElectricalCharge multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(ElectricalChargeUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type ElectricalCharge",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new ElectricalCharge(scalar1.si * scalar2.si, ElectricalChargeUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type ElectricalCharge.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of ElectricalCharge
     */
    public static ElectricalCharge divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(ElectricalChargeUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type ElectricalCharge",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new ElectricalCharge(scalar1.si / scalar2.si, ElectricalChargeUnit.SI);
    }

}
