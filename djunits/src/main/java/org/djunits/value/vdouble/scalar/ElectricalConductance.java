package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;

/**
 * Easy access methods for the ElectricalConductance DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalConductance extends AbstractDoubleScalarRel<ElectricalConductanceUnit, ElectricalConductance>
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
     * Construct ElectricalConductance scalar.
     * @param value double; the double value
     * @param unit ElectricalConductanceUnit; unit for the double value
     */
    public ElectricalConductance(final double value, final ElectricalConductanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct ElectricalConductance scalar.
     * @param value ElectricalConductance; Scalar from which to construct this instance
     */
    public ElectricalConductance(final ElectricalConductance value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final ElectricalConductance instantiateRel(final double value, final ElectricalConductanceUnit unit)
    {
        return new ElectricalConductance(value, unit);
    }

    /**
     * Construct ElectricalConductance scalar.
     * @param value double; the double value in SI units
     * @return ElectricalConductance; the new scalar with the SI value
     */
    public static final ElectricalConductance instantiateSI(final double value)
    {
        return new ElectricalConductance(value, ElectricalConductanceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero ElectricalConductance; the low value
     * @param one ElectricalConductance; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return ElectricalConductance; a Scalar at the ratio between
     */
    public static ElectricalConductance interpolate(final ElectricalConductance zero, final ElectricalConductance one,
            final double ratio)
    {
        return new ElectricalConductance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 ElectricalConductance; the first scalar
     * @param r2 ElectricalConductance; the second scalar
     * @return ElectricalConductance; the maximum value of two relative scalars
     */
    public static ElectricalConductance max(final ElectricalConductance r1, final ElectricalConductance r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 ElectricalConductance; the first scalar
     * @param r2 ElectricalConductance; the second scalar
     * @param rn ElectricalConductance...; the other scalars
     * @return ElectricalConductance; the maximum value of more than two relative scalars
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
     * @param r1 ElectricalConductance; the first scalar
     * @param r2 ElectricalConductance; the second scalar
     * @return ElectricalConductance; the minimum value of two relative scalars
     */
    public static ElectricalConductance min(final ElectricalConductance r1, final ElectricalConductance r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 ElectricalConductance; the first scalar
     * @param r2 ElectricalConductance; the second scalar
     * @param rn ElectricalConductance...; the other scalars
     * @return ElectricalConductance; the minimum value of more than two relative scalars
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
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a ElectricalConductance
     * @return ElectricalConductance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalConductance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing ElectricalConductance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalConductance: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            ElectricalConductanceUnit unit = ElectricalConductanceUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new ElectricalConductance(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing ElectricalConductance from " + text);
    }

    /**
     * Returns a ElectricalConductance based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return ElectricalConductance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalConductance of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing ElectricalConductance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalConductance: empty unitString");
        ElectricalConductanceUnit unit = ElectricalConductanceUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new ElectricalConductance(value, unit);
        }
        throw new IllegalArgumentException("Error parsing ElectricalConductance with unit " + unitString);
    }

    /**
     * Calculate the division of ElectricalConductance and ElectricalConductance, which results in a Dimensionless scalar.
     * @param v ElectricalConductance; scalar
     * @return Dimensionless; scalar as a division of ElectricalConductance and ElectricalConductance
     */
    public final Dimensionless divide(final ElectricalConductance v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalConductance and ElectricalResistance, which results in a Dimensionless scalar.
     * @param v ElectricalConductance; scalar
     * @return Dimensionless; scalar as a multiplication of ElectricalConductance and ElectricalResistance
     */
    public final Dimensionless times(final ElectricalResistance v)
    {
        return new Dimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalConductance and ElectricalPotential, which results in a ElectricalCurrent
     * scalar.
     * @param v ElectricalConductance; scalar
     * @return ElectricalCurrent; scalar as a multiplication of ElectricalConductance and ElectricalPotential
     */
    public final ElectricalCurrent times(final ElectricalPotential v)
    {
        return new ElectricalCurrent(this.si * v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalConductance and Duration, which results in a ElectricalCapacitance scalar.
     * @param v ElectricalConductance; scalar
     * @return ElectricalCapacitance; scalar as a multiplication of ElectricalConductance and Duration
     */
    public final ElectricalCapacitance times(final Duration v)
    {
        return new ElectricalCapacitance(this.si * v.si, ElectricalCapacitanceUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalResistance reciprocal()
    {
        return ElectricalResistance.instantiateSI(1.0 / this.si);
    }

}
