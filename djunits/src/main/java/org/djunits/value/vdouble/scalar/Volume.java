package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Volume DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Volume extends AbstractDoubleScalarRel<VolumeUnit, Volume>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Volume ZERO = new Volume(0.0, VolumeUnit.SI);

    /** Constant with value one. */
    public static final Volume ONE = new Volume(1.0, VolumeUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Volume NaN = new Volume(Double.NaN, VolumeUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Volume POSITIVE_INFINITY = new Volume(Double.POSITIVE_INFINITY, VolumeUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Volume NEGATIVE_INFINITY = new Volume(Double.NEGATIVE_INFINITY, VolumeUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Volume POS_MAXVALUE = new Volume(Double.MAX_VALUE, VolumeUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Volume NEG_MAXVALUE = new Volume(-Double.MAX_VALUE, VolumeUnit.SI);

    /**
     * Construct Volume scalar.
     * @param value double; the double value
     * @param unit VolumeUnit; unit for the double value
     */
    public Volume(final double value, final VolumeUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Volume scalar.
     * @param value Volume; Scalar from which to construct this instance
     */
    public Volume(final Volume value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Volume instantiateRel(final double value, final VolumeUnit unit)
    {
        return new Volume(value, unit);
    }

    /**
     * Construct Volume scalar.
     * @param value double; the double value in SI units
     * @return Volume; the new scalar with the SI value
     */
    public static final Volume instantiateSI(final double value)
    {
        return new Volume(value, VolumeUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Volume; the low value
     * @param one Volume; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Volume; a Scalar at the ratio between
     */
    public static Volume interpolate(final Volume zero, final Volume one, final double ratio)
    {
        return new Volume(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Volume; the first scalar
     * @param r2 Volume; the second scalar
     * @return Volume; the maximum value of two relative scalars
     */
    public static Volume max(final Volume r1, final Volume r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Volume; the first scalar
     * @param r2 Volume; the second scalar
     * @param rn Volume...; the other scalars
     * @return Volume; the maximum value of more than two relative scalars
     */
    public static Volume max(final Volume r1, final Volume r2, final Volume... rn)
    {
        Volume maxr = r1.gt(r2) ? r1 : r2;
        for (Volume r : rn)
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
     * @param r1 Volume; the first scalar
     * @param r2 Volume; the second scalar
     * @return Volume; the minimum value of two relative scalars
     */
    public static Volume min(final Volume r1, final Volume r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Volume; the first scalar
     * @param r2 Volume; the second scalar
     * @param rn Volume...; the other scalars
     * @return Volume; the minimum value of more than two relative scalars
     */
    public static Volume min(final Volume r1, final Volume r2, final Volume... rn)
    {
        Volume minr = r1.lt(r2) ? r1 : r2;
        for (Volume r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Volume representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Volume
     * @return Volume; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Volume valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Volume: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Volume: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            VolumeUnit unit = VolumeUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Volume(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Volume from " + text);
    }

    /**
     * Returns a Volume based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Volume; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Volume of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Volume: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Volume: empty unitString");
        VolumeUnit unit = VolumeUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Volume(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Volume with unit " + unitString);
    }

    /**
     * Calculate the division of Volume and Volume, which results in a Dimensionless scalar.
     * @param v Volume; scalar
     * @return Dimensionless; scalar as a division of Volume and Volume
     */
    public final Dimensionless divide(final Volume v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Volume and Density, which results in a Mass scalar.
     * @param v Volume; scalar
     * @return Mass; scalar as a multiplication of Volume and Density
     */
    public final Mass times(final Density v)
    {
        return new Mass(this.si * v.si, MassUnit.SI);
    }

    /**
     * Calculate the multiplication of Volume and Pressure, which results in a Energy scalar.
     * @param v Volume; scalar
     * @return Energy; scalar as a multiplication of Volume and Pressure
     */
    public final Energy times(final Pressure v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the division of Volume and Length, which results in a Area scalar.
     * @param v Volume; scalar
     * @return Area; scalar as a division of Volume and Length
     */
    public final Area divide(final Length v)
    {
        return new Area(this.si / v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of Volume and Area, which results in a Length scalar.
     * @param v Volume; scalar
     * @return Length; scalar as a division of Volume and Area
     */
    public final Length divide(final Area v)
    {
        return new Length(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of Volume and LinearDensity, which results in a Area scalar.
     * @param v Volume; scalar
     * @return Area; scalar as a multiplication of Volume and LinearDensity
     */
    public final Area times(final LinearDensity v)
    {
        return new Area(this.si * v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of Volume and Duration, which results in a FlowVolume scalar.
     * @param v Volume; scalar
     * @return FlowVolume; scalar as a division of Volume and Duration
     */
    public final FlowVolume divide(final Duration v)
    {
        return new FlowVolume(this.si / v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the division of Volume and FlowVolume, which results in a Duration scalar.
     * @param v Volume; scalar
     * @return Duration; scalar as a division of Volume and FlowVolume
     */
    public final Duration divide(final FlowVolume v)
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
