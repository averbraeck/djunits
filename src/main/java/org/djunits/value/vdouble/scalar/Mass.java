package org.djunits.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.MomentumUnit;
import org.djunits.unit.VolumeUnit;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalarRel;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Mass DoubleScalar, which is relative by definition.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class Mass extends DoubleScalarRel<MassUnit, Mass>
{
    /** */
    private static final long serialVersionUID = 20150905L;

    /** Constant with value zero. */
    public static final Mass ZERO = new Mass(0.0, MassUnit.SI);

    /** Constant with value one. */
    public static final Mass ONE = new Mass(1.0, MassUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Mass NaN = new Mass(Double.NaN, MassUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Mass POSITIVE_INFINITY = new Mass(Double.POSITIVE_INFINITY, MassUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Mass NEGATIVE_INFINITY = new Mass(Double.NEGATIVE_INFINITY, MassUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Mass POS_MAXVALUE = new Mass(Double.MAX_VALUE, MassUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Mass NEG_MAXVALUE = new Mass(-Double.MAX_VALUE, MassUnit.SI);

    /**
     * Construct Mass scalar.
     * @param value the double value
     * @param unit unit for the double value
     */
    public Mass(final double value, final MassUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Mass scalar.
     * @param value Scalar from which to construct this instance
     */
    public Mass(final Mass value)
    {
        super(value);
    }

    @Override
    public final Mass instantiateRel(final double value, final MassUnit unit)
    {
        return new Mass(value, unit);
    }

    /**
     * Construct Mass scalar.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Mass instantiateSI(final double value)
    {
        return new Mass(value, MassUnit.SI);
    }

    /**
     * Interpolate between two values.
     * @param zero the low value
     * @param one the high value
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Scalar at the ratio between
     */
    public static Mass interpolate(final Mass zero, final Mass one, final double ratio)
    {
        return new Mass(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Mass max(final Mass r1, final Mass r2)
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
    public static Mass max(final Mass r1, final Mass r2, final Mass... rn)
    {
        Mass maxr = r1.gt(r2) ? r1 : r2;
        for (Mass r : rn)
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
    public static Mass min(final Mass r1, final Mass r2)
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
    public static Mass min(final Mass r1, final Mass r2, final Mass... rn)
    {
        Mass minr = r1.lt(r2) ? r1 : r2;
        for (Mass r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Mass representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Mass
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Mass valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Mass: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Mass: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            MassUnit unit = MassUnit.BASE.getUnitByAbbreviation(unitString);
            if (unit == null)
                throw new IllegalArgumentException("Unit " + unitString + " not found");
            return new Mass(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Mass from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Mass based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Mass of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Mass: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Mass: empty unitString");
        MassUnit unit = MassUnit.BASE.getUnitByAbbreviation(unitString);
        if (unit != null)
        {
            return new Mass(value, unit);
        }
        throw new IllegalArgumentException("Error parsing Mass with unit " + unitString);
    }

    @Override
    public String toStringSIPrefixed(final int smallestPower, final int biggestPower)
    {
        if (!Double.isFinite(this.si))
        {
            return toString(getDisplayUnit().getStandardUnit());
        }
        // PK: I can't think of an easier way to figure out what the exponent will be; rounding of the mantissa to the available
        // width makes this hard; This feels like an expensive way.
        String check = String.format(this.si >= 0 ? "%10.8E" : "%10.7E", this.si);
        int exponent = Integer.parseInt(check.substring(check.indexOf("E") + 1));
        if (exponent < -27 || exponent < smallestPower || exponent > 21 + 2 || exponent > biggestPower)
        {
            // Out of SI prefix range; do not scale.
            return String.format(this.si >= 0 ? "%10.4E" : "%10.3E", this.si) + " "
                    + getDisplayUnit().getStandardUnit().getId();
        }
        Integer roundedExponent = (int) Math.ceil((exponent - 2.0) / 3) * 3 + 3;
        // System.out.print(String.format("exponent=%d; roundedExponent=%d ", exponent, roundedExponent));
        String key = SIPrefixes.FACTORS.get(roundedExponent).getDefaultTextualPrefix() + "g";
        MassUnit displayUnit = getDisplayUnit().getQuantity().getUnitByAbbreviation(key);
        return toString(displayUnit);
    }

    /**
     * Calculate the division of Mass and Mass, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Mass and Mass
     */
    public final Dimensionless divide(final Mass v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the division of Mass and FlowMass, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Mass and FlowMass
     */
    public final Duration divide(final FlowMass v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the division of Mass and Duration, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a division of Mass and Duration
     */
    public final FlowMass divide(final Duration v)
    {
        return new FlowMass(this.si / v.si, FlowMassUnit.SI);
    }

    /**
     * Calculate the multiplication of Mass and Acceleration, which results in a Force scalar.
     * @param v scalar
     * @return scalar as a multiplication of Mass and Acceleration
     */
    public final Force times(final Acceleration v)
    {
        return new Force(this.si * v.si, ForceUnit.SI);
    }

    /**
     * Calculate the multiplication of Mass and Frequency, which results in a FlowMass scalar.
     * @param v scalar
     * @return scalar as a multiplication of Mass and Frequency
     */
    public final FlowMass times(final Frequency v)
    {
        return new FlowMass(this.si * v.si, FlowMassUnit.SI);
    }

    /**
     * Calculate the division of Mass and Density, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a division of Mass and Density
     */
    public final Volume divide(final Density v)
    {
        return new Volume(this.si / v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the division of Mass and Volume, which results in a Density scalar.
     * @param v scalar
     * @return scalar as a division of Mass and Volume
     */
    public final Density divide(final Volume v)
    {
        return new Density(this.si / v.si, DensityUnit.SI);
    }

    /**
     * Calculate the multiplication of Mass and Speed, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a multiplication of Mass and Speed
     */
    public final Momentum times(final Speed v)
    {
        return new Momentum(this.si * v.si, MomentumUnit.SI);
    }

    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

}
