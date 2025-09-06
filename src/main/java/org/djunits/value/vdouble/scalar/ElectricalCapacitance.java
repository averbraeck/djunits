package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the ElectricalCapacitance DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T11:42:31.564730700Z")
public class ElectricalCapacitance extends DoubleScalarRel<ElectricalCapacitanceUnit, ElectricalCapacitance>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final ElectricalCapacitance ZERO = new ElectricalCapacitance(0.0, ElectricalCapacitanceUnit.SI);

    /** Constant with value one. */
    public static final ElectricalCapacitance ONE = new ElectricalCapacitance(1.0, ElectricalCapacitanceUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricalCapacitance NaN = new ElectricalCapacitance(Double.NaN, ElectricalCapacitanceUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricalCapacitance POSITIVE_INFINITY =
            new ElectricalCapacitance(Double.POSITIVE_INFINITY, ElectricalCapacitanceUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricalCapacitance NEGATIVE_INFINITY =
            new ElectricalCapacitance(Double.NEGATIVE_INFINITY, ElectricalCapacitanceUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final ElectricalCapacitance POS_MAXVALUE =
            new ElectricalCapacitance(Double.MAX_VALUE, ElectricalCapacitanceUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricalCapacitance NEG_MAXVALUE =
            new ElectricalCapacitance(-Double.MAX_VALUE, ElectricalCapacitanceUnit.SI);

    /**
     * Construct ElectricalCapacitance scalar.
     * @param value the double value
     * @param unit unit for the double value
     */
    public ElectricalCapacitance(final double value, final ElectricalCapacitanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct ElectricalCapacitance scalar.
     * @param value Scalar from which to construct this instance
     */
    public ElectricalCapacitance(final ElectricalCapacitance value)
    {
        super(value);
    }

    @Override
    public final ElectricalCapacitance instantiateRel(final double value, final ElectricalCapacitanceUnit unit)
    {
        return new ElectricalCapacitance(value, unit);
    }

    /**
     * Construct ElectricalCapacitance scalar.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final ElectricalCapacitance ofSI(final double value)
    {
        return new ElectricalCapacitance(value, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static ElectricalCapacitance interpolate(final ElectricalCapacitance zero, final ElectricalCapacitance one,
            final double ratio)
    {
        return new ElectricalCapacitance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static ElectricalCapacitance max(final ElectricalCapacitance r1, final ElectricalCapacitance r2)
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
    public static ElectricalCapacitance max(final ElectricalCapacitance r1, final ElectricalCapacitance r2,
            final ElectricalCapacitance... rn)
    {
        ElectricalCapacitance maxr = r1.gt(r2) ? r1 : r2;
        for (ElectricalCapacitance r : rn)
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
    public static ElectricalCapacitance min(final ElectricalCapacitance r1, final ElectricalCapacitance r2)
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
    public static ElectricalCapacitance min(final ElectricalCapacitance r1, final ElectricalCapacitance r2,
            final ElectricalCapacitance... rn)
    {
        ElectricalCapacitance minr = r1.lt(r2) ? r1 : r2;
        for (ElectricalCapacitance r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a ElectricalCapacitance representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a ElectricalCapacitance
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalCapacitance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing ElectricalCapacitance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalCapacitance: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            ElectricalCapacitanceUnit unit = ElectricalCapacitanceUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity ElectricalCapacitance",
                    unitString);
            return new ElectricalCapacitance(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing ElectricalCapacitance from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a ElectricalCapacitance based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalCapacitance of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing ElectricalCapacitance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalCapacitance: empty unitString");
        ElectricalCapacitanceUnit unit = ElectricalCapacitanceUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing ElectricalCapacitance with unit %s",
                unitString);
        return new ElectricalCapacitance(value, unit);
    }

    /**
     * Calculate the division of ElectricalCapacitance and ElectricalCapacitance, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCapacitance and ElectricalCapacitance
     */
    public final Dimensionless divide(final ElectricalCapacitance v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCapacitance and ElectricalPotential, which results in a ElectricalCharge
     * scalar.
     * @param v scalar
     * @return scalar as a multiplication of ElectricalCapacitance and ElectricalPotential
     */
    public final ElectricalCharge times(final ElectricalPotential v)
    {
        return new ElectricalCharge(this.si * v.si, ElectricalChargeUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCapacitance and Duration, which results in a ElectricalConductance scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCapacitance and Duration
     */
    public final ElectricalConductance divide(final Duration v)
    {
        return new ElectricalConductance(this.si / v.si, ElectricalConductanceUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCapacitance and ElectricalConductance, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of ElectricalCapacitance and ElectricalConductance
     */
    public final Duration divide(final ElectricalConductance v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
