package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the ElectricalCapacitance DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalCapacitance extends AbstractDoubleScalarRel<ElectricalCapacitanceUnit, ElectricalCapacitance>
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
     * @param value double; the double value
     * @param unit ElectricalCapacitanceUnit; unit for the double value
     */
    public ElectricalCapacitance(final double value, final ElectricalCapacitanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct ElectricalCapacitance scalar.
     * @param value ElectricalCapacitance; Scalar from which to construct this instance
     */
    public ElectricalCapacitance(final ElectricalCapacitance value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final ElectricalCapacitance instantiateRel(final double value, final ElectricalCapacitanceUnit unit)
    {
        return new ElectricalCapacitance(value, unit);
    }

    /**
     * Construct ElectricalCapacitance scalar.
     * @param value double; the double value in SI units
     * @return ElectricalCapacitance; the new scalar with the SI value
     */
    public static final ElectricalCapacitance instantiateSI(final double value)
    {
        return new ElectricalCapacitance(value, ElectricalCapacitanceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero ElectricalCapacitance; the low value
     * @param one ElectricalCapacitance; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return ElectricalCapacitance; a Scalar at the ratio between
     */
    public static ElectricalCapacitance interpolate(final ElectricalCapacitance zero, final ElectricalCapacitance one,
            final double ratio)
    {
        return new ElectricalCapacitance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 ElectricalCapacitance; the first scalar
     * @param r2 ElectricalCapacitance; the second scalar
     * @return ElectricalCapacitance; the maximum value of two relative scalars
     */
    public static ElectricalCapacitance max(final ElectricalCapacitance r1, final ElectricalCapacitance r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 ElectricalCapacitance; the first scalar
     * @param r2 ElectricalCapacitance; the second scalar
     * @param rn ElectricalCapacitance...; the other scalars
     * @return ElectricalCapacitance; the maximum value of more than two relative scalars
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
     * @param r1 ElectricalCapacitance; the first scalar
     * @param r2 ElectricalCapacitance; the second scalar
     * @return ElectricalCapacitance; the minimum value of two relative scalars
     */
    public static ElectricalCapacitance min(final ElectricalCapacitance r1, final ElectricalCapacitance r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 ElectricalCapacitance; the first scalar
     * @param r2 ElectricalCapacitance; the second scalar
     * @param rn ElectricalCapacitance...; the other scalars
     * @return ElectricalCapacitance; the minimum value of more than two relative scalars
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
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a ElectricalCapacitance
     * @return ElectricalCapacitance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalCapacitance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing ElectricalCapacitance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalCapacitance: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            ElectricalCapacitanceUnit unit = ElectricalCapacitanceUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new ElectricalCapacitance(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing ElectricalCapacitance from " + text);
    }

    /**
     * Returns a ElectricalCapacitance based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return ElectricalCapacitance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalCapacitance of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing ElectricalCapacitance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalCapacitance: empty unitString");
        ElectricalCapacitanceUnit unit = ElectricalCapacitanceUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new ElectricalCapacitance(value, unit);
        }
        throw new IllegalArgumentException("Error parsing ElectricalCapacitance with unit " + unitString);
    }

    /**
     * Calculate the division of ElectricalCapacitance and ElectricalCapacitance, which results in a Dimensionless scalar.
     * @param v ElectricalCapacitance; scalar
     * @return Dimensionless; scalar as a division of ElectricalCapacitance and ElectricalCapacitance
     */
    public final Dimensionless divide(final ElectricalCapacitance v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCapacitance and ElectricalPotential, which results in a ElectricalCharge
     * scalar.
     * @param v ElectricalCapacitance; scalar
     * @return ElectricalCharge; scalar as a multiplication of ElectricalCapacitance and ElectricalPotential
     */
    public final ElectricalCharge times(final ElectricalPotential v)
    {
        return new ElectricalCharge(this.si * v.si, ElectricalChargeUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCapacitance and Duration, which results in a ElectricalConductance scalar.
     * @param v ElectricalCapacitance; scalar
     * @return ElectricalConductance; scalar as a division of ElectricalCapacitance and Duration
     */
    public final ElectricalConductance divide(final Duration v)
    {
        return new ElectricalConductance(this.si / v.si, ElectricalConductanceUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCapacitance and ElectricalConductance, which results in a Duration scalar.
     * @param v ElectricalCapacitance; scalar
     * @return Duration; scalar as a division of ElectricalCapacitance and ElectricalConductance
     */
    public final Duration divide(final ElectricalConductance v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
