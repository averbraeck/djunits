package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.unit.MagneticFluxUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the ElectricalPotential DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class ElectricalPotential extends AbstractDoubleScalarRel<ElectricalPotentialUnit, ElectricalPotential>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final ElectricalPotential ZERO = new ElectricalPotential(0.0, ElectricalPotentialUnit.SI);

    /** Constant with value one. */
    public static final ElectricalPotential ONE = new ElectricalPotential(1.0, ElectricalPotentialUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final ElectricalPotential NaN = new ElectricalPotential(Double.NaN, ElectricalPotentialUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final ElectricalPotential POSITIVE_INFINITY =
            new ElectricalPotential(Double.POSITIVE_INFINITY, ElectricalPotentialUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final ElectricalPotential NEGATIVE_INFINITY =
            new ElectricalPotential(Double.NEGATIVE_INFINITY, ElectricalPotentialUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final ElectricalPotential POS_MAXVALUE =
            new ElectricalPotential(Double.MAX_VALUE, ElectricalPotentialUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final ElectricalPotential NEG_MAXVALUE =
            new ElectricalPotential(-Double.MAX_VALUE, ElectricalPotentialUnit.SI);

    /**
     * Construct ElectricalPotential scalar.
     * @param value double; the double value
     * @param unit ElectricalPotentialUnit; unit for the double value
     */
    public ElectricalPotential(final double value, final ElectricalPotentialUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct ElectricalPotential scalar.
     * @param value ElectricalPotential; Scalar from which to construct this instance
     */
    public ElectricalPotential(final ElectricalPotential value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final ElectricalPotential instantiateRel(final double value, final ElectricalPotentialUnit unit)
    {
        return new ElectricalPotential(value, unit);
    }

    /**
     * Construct ElectricalPotential scalar.
     * @param value double; the double value in SI units
     * @return ElectricalPotential; the new scalar with the SI value
     */
    public static final ElectricalPotential instantiateSI(final double value)
    {
        return new ElectricalPotential(value, ElectricalPotentialUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero ElectricalPotential; the low value
     * @param one ElectricalPotential; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return ElectricalPotential; a Scalar at the ratio between
     */
    public static ElectricalPotential interpolate(final ElectricalPotential zero, final ElectricalPotential one,
            final double ratio)
    {
        return new ElectricalPotential(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 ElectricalPotential; the first scalar
     * @param r2 ElectricalPotential; the second scalar
     * @return ElectricalPotential; the maximum value of two relative scalars
     */
    public static ElectricalPotential max(final ElectricalPotential r1, final ElectricalPotential r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 ElectricalPotential; the first scalar
     * @param r2 ElectricalPotential; the second scalar
     * @param rn ElectricalPotential...; the other scalars
     * @return ElectricalPotential; the maximum value of more than two relative scalars
     */
    public static ElectricalPotential max(final ElectricalPotential r1, final ElectricalPotential r2,
            final ElectricalPotential... rn)
    {
        ElectricalPotential maxr = r1.gt(r2) ? r1 : r2;
        for (ElectricalPotential r : rn)
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
     * @param r1 ElectricalPotential; the first scalar
     * @param r2 ElectricalPotential; the second scalar
     * @return ElectricalPotential; the minimum value of two relative scalars
     */
    public static ElectricalPotential min(final ElectricalPotential r1, final ElectricalPotential r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 ElectricalPotential; the first scalar
     * @param r2 ElectricalPotential; the second scalar
     * @param rn ElectricalPotential...; the other scalars
     * @return ElectricalPotential; the minimum value of more than two relative scalars
     */
    public static ElectricalPotential min(final ElectricalPotential r1, final ElectricalPotential r2,
            final ElectricalPotential... rn)
    {
        ElectricalPotential minr = r1.lt(r2) ? r1 : r2;
        for (ElectricalPotential r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a ElectricalPotential representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by the official abbreviation of the unit.
     * Spaces are allowed, but not required, between the value and the unit.
     * @param text String; the textual representation to parse into a ElectricalPotential
     * @return ElectricalPotential; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static ElectricalPotential valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing ElectricalPotential: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalPotential: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            ElectricalPotentialUnit unit = ElectricalPotentialUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new ElectricalPotential(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing ElectricalPotential from " + text);
    }

    /**
     * Returns a ElectricalPotential based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return ElectricalPotential; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static ElectricalPotential of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing ElectricalPotential: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing ElectricalPotential: empty unitString");
        ElectricalPotentialUnit unit = ElectricalPotentialUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new ElectricalPotential(value, unit);
        }
        throw new IllegalArgumentException("Error parsing ElectricalPotential with unit " + unitString);
    }

    /**
     * Calculate the division of ElectricalPotential and ElectricalPotential, which results in a Dimensionless scalar.
     * @param v ElectricalPotential; scalar
     * @return Dimensionless; scalar as a division of ElectricalPotential and ElectricalPotential
     */
    public final Dimensionless divide(final ElectricalPotential v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalPotential and ElectricalCurrent, which results in a Power scalar.
     * @param v ElectricalPotential; scalar
     * @return Power; scalar as a multiplication of ElectricalPotential and ElectricalCurrent
     */
    public final Power times(final ElectricalCurrent v)
    {
        return new Power(this.si * v.si, PowerUnit.SI);
    }

    /**
     * Calculate the division of ElectricalPotential and ElectricalCurrent, which results in a ElectricalResistance scalar.
     * @param v ElectricalPotential; scalar
     * @return ElectricalResistance; scalar as a division of ElectricalPotential and ElectricalCurrent
     */
    public final ElectricalResistance divide(final ElectricalCurrent v)
    {
        return new ElectricalResistance(this.si / v.si, ElectricalResistanceUnit.SI);
    }

    /**
     * Calculate the division of ElectricalPotential and ElectricalResistance, which results in a ElectricalCurrent scalar.
     * @param v ElectricalPotential; scalar
     * @return ElectricalCurrent; scalar as a division of ElectricalPotential and ElectricalResistance
     */
    public final ElectricalCurrent divide(final ElectricalResistance v)
    {
        return new ElectricalCurrent(this.si / v.si, ElectricalCurrentUnit.SI);
    }

    /**
     * Calculate the multiplication of ElectricalPotential and Duration, which results in a MagneticFlux scalar.
     * @param v ElectricalPotential; scalar
     * @return MagneticFlux; scalar as a multiplication of ElectricalPotential and Duration
     */
    public final MagneticFlux times(final Duration v)
    {
        return new MagneticFlux(this.si * v.si, MagneticFluxUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
