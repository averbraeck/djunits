package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the ElectricalCurrent DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class ElectricalCurrent extends DoubleScalarRel<ElectricalCurrentUnit, ElectricalCurrent>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final ElectricalCurrent ZERO = new ElectricalCurrent(0.0, ElectricalCurrentUnit.SI);

    /** Constant with value one. */
    public static final ElectricalCurrent ONE = new ElectricalCurrent(1.0, ElectricalCurrentUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricalCurrent NaN = new ElectricalCurrent(Double.NaN, ElectricalCurrentUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricalCurrent POSITIVE_INFINITY =
            new ElectricalCurrent(Double.POSITIVE_INFINITY, ElectricalCurrentUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricalCurrent NEGATIVE_INFINITY =
            new ElectricalCurrent(Double.NEGATIVE_INFINITY, ElectricalCurrentUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final ElectricalCurrent POS_MAXVALUE = new ElectricalCurrent(Double.MAX_VALUE, ElectricalCurrentUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricalCurrent NEG_MAXVALUE = new ElectricalCurrent(-Double.MAX_VALUE, ElectricalCurrentUnit.SI);

    /**
     * Construct ElectricalCurrent scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public ElectricalCurrent(final double value, final ElectricalCurrentUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct ElectricalCurrent scalar.
     * @param value Scalar from which to construct this instance
     */
    public ElectricalCurrent(final ElectricalCurrent value)
    {
        super(value);
    }

    @Override
    public final ElectricalCurrent instantiateRel(final double value, final ElectricalCurrentUnit unit)
    {
        return new ElectricalCurrent(value, unit);
    }

    /**
     * Construct ElectricalCurrent scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final ElectricalCurrent ofSI(final double value)
    {
        return new ElectricalCurrent(value, ElectricalCurrentUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a ElectricalCurrent at the given ratio between 0 and 1
     */
    public static ElectricalCurrent interpolate(final ElectricalCurrent zero, final ElectricalCurrent one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new ElectricalCurrent(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static ElectricalCurrent max(final ElectricalCurrent r1, final ElectricalCurrent r2)
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
    public static ElectricalCurrent max(final ElectricalCurrent r1, final ElectricalCurrent r2, final ElectricalCurrent... rn)
    {
        ElectricalCurrent maxr = r1.gt(r2) ? r1 : r2;
        for (ElectricalCurrent r : rn)
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
    public static ElectricalCurrent min(final ElectricalCurrent r1, final ElectricalCurrent r2)
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
    public static ElectricalCurrent min(final ElectricalCurrent r1, final ElectricalCurrent r2, final ElectricalCurrent... rn)
    {
        ElectricalCurrent minr = r1.lt(r2) ? r1 : r2;
        for (ElectricalCurrent r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a ElectricalCurrent representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricalCurrent
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalCurrent valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing ElectricalCurrent: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing ElectricalCurrent: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ElectricalCurrentUnit unit = ElectricalCurrentUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity ElectricalCurrent",
                    unitString);
            return new ElectricalCurrent(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing ElectricalCurrent from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a ElectricalCurrent based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalCurrent of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing ElectricalCurrent: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalCurrent: empty unitString");
        ElectricalCurrentUnit unit = ElectricalCurrentUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing ElectricalCurrent with unit %s", unitString);
        return new ElectricalCurrent(value, unit);
    }

    /**
     * Calculate the division of ElectricalCurrent and ElectricalCurrent, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCurrent and ElectricalCurrent
     */
    public final Dimensionless divide(final ElectricalCurrent v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and ElectricalPotential, which results in a Power scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalCurrent and ElectricalPotential
     */
    public final Power times(final ElectricalPotential v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and Duration, which results in a ElectricalCharge scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalCurrent and Duration
     */
    public final ElectricalCharge times(final Duration v)
    {
        return new ElectricalCharge(this.si * v.si, ElectricalChargeUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and ElectricalResistance, which results in a ElectricalPotential
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalCurrent and ElectricalResistance
     */
    public final ElectricalPotential times(final ElectricalResistance v)
    {
        return new ElectricalPotential(this.si * v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCurrent and ElectricalPotential, which results in a ElectricalConductance scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCurrent and ElectricalPotential
     */
    public final ElectricalConductance divide(final ElectricalPotential v)
    {
        return new ElectricalConductance(this.si / v.si, ElectricalConductanceUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCurrent and ElectricalConductance, which results in a ElectricalPotential scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCurrent and ElectricalConductance
     */
    public final ElectricalPotential divide(final ElectricalConductance v)
    {
        return new ElectricalPotential(this.si / v.si, ElectricalPotentialUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return SIScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Multiply two scalars that result in a scalar of type ElectricalCurrent.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of ElectricalCurrent
     */
    public static ElectricalCurrent multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(ElectricalCurrentUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type ElectricalCurrent",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new ElectricalCurrent(scalar1.si * scalar2.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type ElectricalCurrent.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of ElectricalCurrent
     */
    public static ElectricalCurrent divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(ElectricalCurrentUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type ElectricalCurrent",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new ElectricalCurrent(scalar1.si / scalar2.si, ElectricalCurrentUnit.SI);
    }

}
