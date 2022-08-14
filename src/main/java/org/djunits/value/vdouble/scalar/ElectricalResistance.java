package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;

/**
 * Easy access methods for the ElectricalResistance DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalResistance extends AbstractDoubleScalarRel<ElectricalResistanceUnit, ElectricalResistance>
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
     * Construct ElectricalResistance scalar.
     * @param value double; the double value
     * @param unit ElectricalResistanceUnit; unit for the double value
     */
    public ElectricalResistance(final double value, final ElectricalResistanceUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct ElectricalResistance scalar.
     * @param value ElectricalResistance; Scalar from which to construct this instance
     */
    public ElectricalResistance(final ElectricalResistance value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final ElectricalResistance instantiateRel(final double value, final ElectricalResistanceUnit unit)
    {
        return new ElectricalResistance(value, unit);
    }

    /**
     * Construct ElectricalResistance scalar.
     * @param value double; the double value in SI units
     * @return ElectricalResistance; the new scalar with the SI value
     */
    public static final ElectricalResistance instantiateSI(final double value)
    {
        return new ElectricalResistance(value, ElectricalResistanceUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero ElectricalResistance; the low value
     * @param one ElectricalResistance; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return ElectricalResistance; a Scalar at the ratio between
     */
    public static ElectricalResistance interpolate(final ElectricalResistance zero, final ElectricalResistance one,
            final double ratio)
    {
        return new ElectricalResistance(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 ElectricalResistance; the first scalar
     * @param r2 ElectricalResistance; the second scalar
     * @return ElectricalResistance; the maximum value of two relative scalars
     */
    public static ElectricalResistance max(final ElectricalResistance r1, final ElectricalResistance r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 ElectricalResistance; the first scalar
     * @param r2 ElectricalResistance; the second scalar
     * @param rn ElectricalResistance...; the other scalars
     * @return ElectricalResistance; the maximum value of more than two relative scalars
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
     * @param r1 ElectricalResistance; the first scalar
     * @param r2 ElectricalResistance; the second scalar
     * @return ElectricalResistance; the minimum value of two relative scalars
     */
    public static ElectricalResistance min(final ElectricalResistance r1, final ElectricalResistance r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 ElectricalResistance; the first scalar
     * @param r2 ElectricalResistance; the second scalar
     * @param rn ElectricalResistance...; the other scalars
     * @return ElectricalResistance; the minimum value of more than two relative scalars
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
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a ElectricalResistance
     * @return ElectricalResistance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalResistance valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing ElectricalResistance: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalResistance: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            ElectricalResistanceUnit unit = ElectricalResistanceUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new ElectricalResistance(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing ElectricalResistance from " + text);
    }

    /**
     * Returns a ElectricalResistance based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return ElectricalResistance; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalResistance of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing ElectricalResistance: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalResistance: empty unitString");
        ElectricalResistanceUnit unit = ElectricalResistanceUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new ElectricalResistance(value, unit);
        }
        throw new IllegalArgumentException("Error parsing ElectricalResistance with unit " + unitString);
    }

    /**
     * Calculate the division of ElectricalResistance and ElectricalResistance, which results in a Dimensionless scalar.
     * @param v ElectricalResistance; scalar
     * @return Dimensionless; scalar as a division of ElectricalResistance and ElectricalResistance
     */
    public final Dimensionless divide(final ElectricalResistance v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalResistance and ElectricalConductance, which results in a Dimensionless scalar.
     * @param v ElectricalResistance; scalar
     * @return Dimensionless; scalar as a multiplication of ElectricalResistance and ElectricalConductance
     */
    public final Dimensionless times(final ElectricalConductance v)
    {
        return new Dimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalResistance and ElectricalCurrent, which results in a ElectricalPotential
     * scalar.
     * @param v ElectricalResistance; scalar
     * @return ElectricalPotential; scalar as a multiplication of ElectricalResistance and ElectricalCurrent
     */
    public final ElectricalPotential times(final ElectricalCurrent v)
    {
        return new ElectricalPotential(this.si * v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalResistance and Duration, which results in a ElectricalInductance scalar.
     * @param v ElectricalResistance; scalar
     * @return ElectricalInductance; scalar as a multiplication of ElectricalResistance and Duration
     */
    public final ElectricalInductance times(final Duration v)
    {
        return new ElectricalInductance(this.si * v.si, ElectricalInductanceUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalConductance reciprocal()
    {
        return ElectricalConductance.instantiateSI(1.0 / this.si);
    }

}
