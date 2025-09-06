package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the ElectricalConductance DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class ElectricalConductance extends DoubleScalarRel<ElectricalConductanceUnit, ElectricalConductance>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final ElectricalConductance ZERO = new ElectricalConductance(0.0, ElectricalConductanceUnit.SI);

    /** Constant with value one. */
    public static final ElectricalConductance ONE = new ElectricalConductance(1.0, ElectricalConductanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricalConductance NaN = new ElectricalConductance(Double.NaN, ElectricalConductanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricalConductance POSITIVE_INFINITY =
            new ElectricalConductance(Double.POSITIVE_INFINITY, ElectricalConductanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricalConductance NEGATIVE_INFINITY =
            new ElectricalConductance(Double.NEGATIVE_INFINITY, ElectricalConductanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final ElectricalConductance POS_MAXVALUE =
            new ElectricalConductance(Double.MAX_VALUE, ElectricalConductanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricalConductance NEG_MAXVALUE =
            new ElectricalConductance(-Double.MAX_VALUE, ElectricalConductanceUnit.SI);

    /**
     * Construct ElectricalConductance scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public ElectricalConductance(final double value, final ElectricalConductanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct ElectricalConductance scalar.
     * @param value Scalar from which to construct this instance
     */
    public ElectricalConductance(final ElectricalConductance value)
    {
        super(value);
    }

    @Override
    public final ElectricalConductance instantiateRel(final double value, final ElectricalConductanceUnit unit)
    {
        return new ElectricalConductance(value, unit);
    }

    /**
     * Construct ElectricalConductance scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final ElectricalConductance ofSI(final double value)
    {
        return new ElectricalConductance(value, ElectricalConductanceUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a ElectricalConductance at the given ratio between 0 and 1
     */
    public static ElectricalConductance interpolate(final ElectricalConductance zero, final ElectricalConductance one,
            final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new ElectricalConductance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static ElectricalConductance max(final ElectricalConductance r1, final ElectricalConductance r2)
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
    public static ElectricalConductance max(final ElectricalConductance r1, final ElectricalConductance r2,
            final ElectricalConductance... rn)
    {
        ElectricalConductance maxr = r1.gt(r2) ? r1 : r2;
        for (ElectricalConductance r : rn)
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
    public static ElectricalConductance min(final ElectricalConductance r1, final ElectricalConductance r2)
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
    public static ElectricalConductance min(final ElectricalConductance r1, final ElectricalConductance r2,
            final ElectricalConductance... rn)
    {
        ElectricalConductance minr = r1.lt(r2) ? r1 : r2;
        for (ElectricalConductance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a ElectricalConductance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricalConductance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalConductance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing ElectricalConductance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalConductance: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ElectricalConductanceUnit unit = ElectricalConductanceUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity ElectricalConductance",
                    unitString);
            return new ElectricalConductance(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing ElectricalConductance from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a ElectricalConductance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalConductance of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing ElectricalConductance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalConductance: empty unitString");
        ElectricalConductanceUnit unit = ElectricalConductanceUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing ElectricalConductance with unit %s",
                unitString);
        return new ElectricalConductance(value, unit);
    }

    /**
     * Calculate the division of ElectricalConductance and ElectricalConductance, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalConductance and ElectricalConductance
     */
    public final Dimensionless divide(final ElectricalConductance v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalConductance and ElectricalResistance, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalConductance and ElectricalResistance
     */
    public final Dimensionless times(final ElectricalResistance v)
    {
        return new Dimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalConductance and ElectricalPotential, which results in a ElectricalCurrent
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalConductance and ElectricalPotential
     */
    public final ElectricalCurrent times(final ElectricalPotential v)
    {
        return new ElectricalCurrent(this.si * v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalConductance and Duration, which results in a ElectricalCapacitance scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalConductance and Duration
     */
    public final ElectricalCapacitance times(final Duration v)
    {
        return new ElectricalCapacitance(this.si * v.si, ElectricalCapacitanceUnit.SI);
    }

    @Override
    public ElectricalResistance reciprocal()
    {
        return ElectricalResistance.ofSI(1.0 / this.si);
    }

}
