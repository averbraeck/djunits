package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the ElectricalCurrent DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalCurrent extends AbstractDoubleScalarRel<ElectricalCurrentUnit, ElectricalCurrent>
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
     * Construct ElectricalCurrent scalar.
     * @param value double; the double value
     * @param unit ElectricalCurrentUnit; unit for the double value
     */
    public ElectricalCurrent(final double value, final ElectricalCurrentUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct ElectricalCurrent scalar.
     * @param value ElectricalCurrent; Scalar from which to construct this instance
     */
    public ElectricalCurrent(final ElectricalCurrent value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final ElectricalCurrent instantiateRel(final double value, final ElectricalCurrentUnit unit)
    {
        return new ElectricalCurrent(value, unit);
    }

    /**
     * Construct ElectricalCurrent scalar.
     * @param value double; the double value in SI units
     * @return ElectricalCurrent; the new scalar with the SI value
     */
    public static final ElectricalCurrent instantiateSI(final double value)
    {
        return new ElectricalCurrent(value, ElectricalCurrentUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero ElectricalCurrent; the low value
     * @param one ElectricalCurrent; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return ElectricalCurrent; a Scalar at the ratio between
     */
    public static ElectricalCurrent interpolate(final ElectricalCurrent zero, final ElectricalCurrent one, final double ratio)
    {
        return new ElectricalCurrent(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 ElectricalCurrent; the first scalar
     * @param r2 ElectricalCurrent; the second scalar
     * @return ElectricalCurrent; the maximum value of two relative scalars
     */
    public static ElectricalCurrent max(final ElectricalCurrent r1, final ElectricalCurrent r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 ElectricalCurrent; the first scalar
     * @param r2 ElectricalCurrent; the second scalar
     * @param rn ElectricalCurrent...; the other scalars
     * @return ElectricalCurrent; the maximum value of more than two relative scalars
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
     * @param r1 ElectricalCurrent; the first scalar
     * @param r2 ElectricalCurrent; the second scalar
     * @return ElectricalCurrent; the minimum value of two relative scalars
     */
    public static ElectricalCurrent min(final ElectricalCurrent r1, final ElectricalCurrent r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 ElectricalCurrent; the first scalar
     * @param r2 ElectricalCurrent; the second scalar
     * @param rn ElectricalCurrent...; the other scalars
     * @return ElectricalCurrent; the minimum value of more than two relative scalars
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
     * that can be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are
     * allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a ElectricalCurrent
     * @return ElectricalCurrent; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalCurrent valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing ElectricalCurrent: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing ElectricalCurrent: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            ElectricalCurrentUnit unit = ElectricalCurrentUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new ElectricalCurrent(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing ElectricalCurrent from " + text);
    }

    /**
     * Returns a ElectricalCurrent based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return ElectricalCurrent; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalCurrent of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing ElectricalCurrent: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalCurrent: empty unitString");
        ElectricalCurrentUnit unit = ElectricalCurrentUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new ElectricalCurrent(value, unit);
        }
        throw new IllegalArgumentException("Error parsing ElectricalCurrent with unit " + unitString);
    }

    /**
     * Calculate the division of ElectricalCurrent and ElectricalCurrent, which results in a Dimensionless scalar.
     * @param v ElectricalCurrent; scalar
     * @return Dimensionless; scalar as a division of ElectricalCurrent and ElectricalCurrent
     */
    public final Dimensionless divide(final ElectricalCurrent v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and ElectricalPotential, which results in a Power scalar.
     * @param v ElectricalCurrent; scalar
     * @return Power; scalar as a multiplication of ElectricalCurrent and ElectricalPotential
     */
    public final Power times(final ElectricalPotential v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and Duration, which results in a ElectricalCharge scalar.
     * @param v ElectricalCurrent; scalar
     * @return ElectricalCharge; scalar as a multiplication of ElectricalCurrent and Duration
     */
    public final ElectricalCharge times(final Duration v)
    {
        return new ElectricalCharge(this.si * v.si, ElectricalChargeUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalCurrent and ElectricalResistance, which results in a ElectricalPotential
     * scalar.
     * @param v ElectricalCurrent; scalar
     * @return ElectricalPotential; scalar as a multiplication of ElectricalCurrent and ElectricalResistance
     */
    public final ElectricalPotential times(final ElectricalResistance v)
    {
        return new ElectricalPotential(this.si * v.si, ElectricalPotentialUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCurrent and ElectricalPotential, which results in a ElectricalConductance scalar.
     * @param v ElectricalCurrent; scalar
     * @return ElectricalConductance; scalar as a division of ElectricalCurrent and ElectricalPotential
     */
    public final ElectricalConductance divide(final ElectricalPotential v)
    {
        return new ElectricalConductance(this.si / v.si, ElectricalConductanceUnit.SI);
    }

    /**
     * Calculate the division of ElectricalCurrent and ElectricalConductance, which results in a ElectricalPotential scalar.
     * @param v ElectricalCurrent; scalar
     * @return ElectricalPotential; scalar as a division of ElectricalCurrent and ElectricalConductance
     */
    public final ElectricalPotential divide(final ElectricalConductance v)
    {
        return new ElectricalPotential(this.si / v.si, ElectricalPotentialUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
