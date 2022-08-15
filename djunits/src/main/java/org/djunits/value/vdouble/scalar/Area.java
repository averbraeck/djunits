package org.djunits.value.vdouble.scalar;

import java.util.regex.Matcher;

import javax.annotation.Generated;

import org.djunits.Throw;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.unit.LuminousFluxUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the Area DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2022-03-14T11:14:15.180987200Z")
public class Area extends AbstractDoubleScalarRel<AreaUnit, Area>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Area ZERO = new Area(0.0, AreaUnit.SI);

    /** Constant with value one. */
    public static final Area ONE = new Area(1.0, AreaUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Area NaN = new Area(Double.NaN, AreaUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Area POSITIVE_INFINITY = new Area(Double.POSITIVE_INFINITY, AreaUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Area NEGATIVE_INFINITY = new Area(Double.NEGATIVE_INFINITY, AreaUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Area POS_MAXVALUE = new Area(Double.MAX_VALUE, AreaUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Area NEG_MAXVALUE = new Area(-Double.MAX_VALUE, AreaUnit.SI);

    /**
     * Construct Area scalar.
     * @param value double; the double value
     * @param unit AreaUnit; unit for the double value
     */
    public Area(final double value, final AreaUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Area scalar.
     * @param value Area; Scalar from which to construct this instance
     */
    public Area(final Area value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final Area instantiateRel(final double value, final AreaUnit unit)
    {
        return new Area(value, unit);
    }

    /**
     * Construct Area scalar.
     * @param value double; the double value in SI units
     * @return Area; the new scalar with the SI value
     */
    public static final Area instantiateSI(final double value)
    {
        return new Area(value, AreaUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero Area; the low value
     * @param one Area; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return Area; a Scalar at the ratio between
     */
    public static Area interpolate(final Area zero, final Area one, final double ratio)
    {
        return new Area(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 Area; the first scalar
     * @param r2 Area; the second scalar
     * @return Area; the maximum value of two relative scalars
     */
    public static Area max(final Area r1, final Area r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 Area; the first scalar
     * @param r2 Area; the second scalar
     * @param rn Area...; the other scalars
     * @return Area; the maximum value of more than two relative scalars
     */
    public static Area max(final Area r1, final Area r2, final Area... rn)
    {
        Area maxr = r1.gt(r2) ? r1 : r2;
        for (Area r : rn)
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
     * @param r1 Area; the first scalar
     * @param r2 Area; the second scalar
     * @return Area; the minimum value of two relative scalars
     */
    public static Area min(final Area r1, final Area r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 Area; the first scalar
     * @param r2 Area; the second scalar
     * @param rn Area...; the other scalars
     * @return Area; the minimum value of more than two relative scalars
     */
    public static Area min(final Area r1, final Area r2, final Area... rn)
    {
        Area minr = r1.lt(r2) ? r1 : r2;
        for (Area r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Area representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a Area
     * @return Area; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Area valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Area: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Area: empty text to parse");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            String unitString = text.substring(index).trim();
            String valueString = text.substring(0, index).trim();
            AreaUnit unit = AreaUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit != null)
            {
                double d = Double.parseDouble(valueString);
                return new Area(d, unit);
            }
        }
        throw new IllegalArgumentException("Error parsing Area from " + text);
    }

    /**
     * Returns a Area based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return Area; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Area of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Area: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Area: empty unitString");
        AreaUnit unit = AreaUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Area(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Area with unit " + unitString);
    }

    /**
     * Calculate the division of Area and Area, which results in a Dimensionless scalar.
     * @param v Area; scalar
     * @return Dimensionless; scalar as a division of Area and Area
     */
    public final Dimensionless divide(final Area v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Area and Length, which results in a Volume scalar.
     * @param v Area; scalar
     * @return Volume; scalar as a multiplication of Area and Length
     */
    public final Volume times(final Length v)
    {
        return new Volume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of Area and LinearDensity, which results in a Volume scalar.
     * @param v Area; scalar
     * @return Volume; scalar as a division of Area and LinearDensity
     */
    public final Volume divide(final LinearDensity v)
    {
        return new Volume(this.si / v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of Area and Volume, which results in a LinearDensity scalar.
     * @param v Area; scalar
     * @return LinearDensity; scalar as a division of Area and Volume
     */
    public final LinearDensity divide(final Volume v)
    {
        return new LinearDensity(this.si / v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the division of Area and Length, which results in a Length scalar.
     * @param v Area; scalar
     * @return Length; scalar as a division of Area and Length
     */
    public final Length divide(final Length v)
    {
        return new Length(this.si / v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of Area and LinearDensity, which results in a Length scalar.
     * @param v Area; scalar
     * @return Length; scalar as a multiplication of Area and LinearDensity
     */
    public final Length times(final LinearDensity v)
    {
        return new Length(this.si * v.si, LengthUnit.SI);
    }

    /**
     * Calculate the multiplication of Area and Speed, which results in a FlowVolume scalar.
     * @param v Area; scalar
     * @return FlowVolume; scalar as a multiplication of Area and Speed
     */
    public final FlowVolume times(final Speed v)
    {
        return new FlowVolume(this.si * v.si, FlowVolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of Area and Pressure, which results in a Force scalar.
     * @param v Area; scalar
     * @return Force; scalar as a multiplication of Area and Pressure
     */
    public final Force times(final Pressure v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of Area and Illuminance, which results in a LuminousFlux scalar.
     * @param v Area; scalar
     * @return LuminousFlux; scalar as a multiplication of Area and Illuminance
     */
    public final LuminousFlux times(final Illuminance v)
    {
        return new LuminousFlux(this.si * v.si, LuminousFluxUnit.SI);
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
