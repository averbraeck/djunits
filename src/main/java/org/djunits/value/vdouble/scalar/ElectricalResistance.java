package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the ElectricalResistance DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class ElectricalResistance extends DoubleScalarRel<ElectricalResistanceUnit, ElectricalResistance>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final ElectricalResistance ZERO = new ElectricalResistance(0.0, ElectricalResistanceUnit.SI);

    /** Constant with value one. */
    public static final ElectricalResistance ONE = new ElectricalResistance(1.0, ElectricalResistanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricalResistance NaN = new ElectricalResistance(Double.NaN, ElectricalResistanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricalResistance POSITIVE_INFINITY =
            new ElectricalResistance(Double.POSITIVE_INFINITY, ElectricalResistanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricalResistance NEGATIVE_INFINITY =
            new ElectricalResistance(Double.NEGATIVE_INFINITY, ElectricalResistanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final ElectricalResistance POS_MAXVALUE =
            new ElectricalResistance(Double.MAX_VALUE, ElectricalResistanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricalResistance NEG_MAXVALUE =
            new ElectricalResistance(-Double.MAX_VALUE, ElectricalResistanceUnit.SI);

    /**
     * Construct ElectricalResistance scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public ElectricalResistance(final double value, final ElectricalResistanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct ElectricalResistance scalar.
     * @param value Scalar from which to construct this instance
     */
    public ElectricalResistance(final ElectricalResistance value)
    {
        super(value);
    }

    @Override
    public final ElectricalResistance instantiateRel(final double value, final ElectricalResistanceUnit unit)
    {
        return new ElectricalResistance(value, unit);
    }

    /**
     * Construct ElectricalResistance scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final ElectricalResistance ofSI(final double value)
    {
        return new ElectricalResistance(value, ElectricalResistanceUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a ElectricalResistance at the given ratio between 0 and 1
     */
    public static ElectricalResistance interpolate(final ElectricalResistance zero, final ElectricalResistance one,
            final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new ElectricalResistance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static ElectricalResistance max(final ElectricalResistance r1, final ElectricalResistance r2)
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
    public static ElectricalResistance max(final ElectricalResistance r1, final ElectricalResistance r2,
            final ElectricalResistance... rn)
    {
        ElectricalResistance maxr = r1.gt(r2) ? r1 : r2;
        for (ElectricalResistance r : rn)
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
    public static ElectricalResistance min(final ElectricalResistance r1, final ElectricalResistance r2)
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
    public static ElectricalResistance min(final ElectricalResistance r1, final ElectricalResistance r2,
            final ElectricalResistance... rn)
    {
        ElectricalResistance minr = r1.lt(r2) ? r1 : r2;
        for (ElectricalResistance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a ElectricalResistance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricalResistance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalResistance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing ElectricalResistance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalResistance: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ElectricalResistanceUnit unit = ElectricalResistanceUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity ElectricalResistance",
                    unitString);
            return new ElectricalResistance(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing ElectricalResistance from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a ElectricalResistance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalResistance of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing ElectricalResistance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalResistance: empty unitString");
        ElectricalResistanceUnit unit = ElectricalResistanceUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing ElectricalResistance with unit %s", unitString);
        return new ElectricalResistance(value, unit);
    }

    /**
     * Calculate the division of ElectricalResistance and ElectricalResistance, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalResistance and ElectricalResistance
     */
    public final Dimensionless divide(final ElectricalResistance v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalResistance and ElectricalConductance, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalResistance and ElectricalConductance
     */
    public final Dimensionless times(final ElectricalConductance v)
    {
        return new Dimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalResistance and ElectricalCurrent, which results in a ElectricalPotential
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalResistance and ElectricalCurrent
     */
    public final ElectricalPotential times(final ElectricalCurrent v)
    {
        return new ElectricalPotential(this.si * v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalResistance and Duration, which results in a ElectricalInductance scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalResistance and Duration
     */
    public final ElectricalInductance times(final Duration v)
    {
        return new ElectricalInductance(this.si * v.si, ElectricalInductanceUnit.SI);
    }

    @Override
    public ElectricalConductance reciprocal()
    {
        return ElectricalConductance.ofSI(1.0 / this.si);
    }

    /**
     * Multiply two scalars that result in a scalar of type ElectricalResistance.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of ElectricalResistance
     */
    public static ElectricalResistance multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(ElectricalResistanceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type ElectricalResistance",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new ElectricalResistance(scalar1.si * scalar2.si, ElectricalResistanceUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type ElectricalResistance.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of ElectricalResistance
     */
    public static ElectricalResistance divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(
                !scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                        .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions())
                        .equals(ElectricalResistanceUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type ElectricalResistance",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new ElectricalResistance(scalar1.si / scalar2.si, ElectricalResistanceUnit.SI);
    }

}
