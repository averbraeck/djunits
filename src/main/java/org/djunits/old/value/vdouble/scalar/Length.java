package org.djunits.old.value.vdouble.scalar;

import java.util.Locale;

import org.djunits.old.unit.AreaUnit;
import org.djunits.old.unit.DimensionlessUnit;
import org.djunits.old.unit.DurationUnit;
import org.djunits.old.unit.EnergyUnit;
import org.djunits.old.unit.LengthUnit;
import org.djunits.old.unit.LinearDensityUnit;
import org.djunits.old.unit.MomentumUnit;
import org.djunits.old.unit.PositionUnit;
import org.djunits.old.unit.SpeedUnit;
import org.djunits.old.unit.VolumeUnit;
import org.djunits.old.value.vdouble.scalar.base.DoubleScalarRel;
import org.djunits.old.value.vdouble.scalar.base.DoubleScalarRelWithAbs;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the Relative Length DoubleScalar.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class Length extends DoubleScalarRelWithAbs<PositionUnit, Position, LengthUnit, Length>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final Length ZERO = new Length(0.0, LengthUnit.SI);

    /** Constant with value one. */
    public static final Length ONE = new Length(1.0, LengthUnit.SI);

    /** Constant with value NaN. */
    @SuppressWarnings("checkstyle:constantname")
    public static final Length NaN = new Length(Double.NaN, LengthUnit.SI);

    /** Constant with value POSITIVE_INFINITY. */
    public static final Length POSITIVE_INFINITY = new Length(Double.POSITIVE_INFINITY, LengthUnit.SI);

    /** Constant with value NEGATIVE_INFINITY. */
    public static final Length NEGATIVE_INFINITY = new Length(Double.NEGATIVE_INFINITY, LengthUnit.SI);

    /** Constant with value MAX_VALUE. */
    public static final Length POS_MAXVALUE = new Length(Double.MAX_VALUE, LengthUnit.SI);

    /** Constant with value -MAX_VALUE. */
    public static final Length NEG_MAXVALUE = new Length(-Double.MAX_VALUE, LengthUnit.SI);

    /**
     * Construct Length scalar with a unit.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the double value
     */
    public Length(final double value, final LengthUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct Length scalar.
     * @param value Scalar from which to construct this instance
     */
    public Length(final Length value)
    {
        super(value);
    }

    @Override
    public final Length instantiateRel(final double value, final LengthUnit unit)
    {
        return new Length(value, unit);
    }

    @Override
    public final Position instantiateAbs(final double value, final PositionUnit unit)
    {
        return new Position(value, unit);
    }

    /**
     * Construct Length scalar based on an SI value.
     * @param value the double value in SI units
     * @return the new scalar with the SI value
     */
    public static final Length ofSI(final double value)
    {
        return new Length(value, LengthUnit.SI);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a Length at the given ratio between 0 and 1
     */
    public static Length interpolate(final Length zero, final Length one, final double ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new Length(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 the first scalar
     * @param r2 the second scalar
     * @return the maximum value of two relative scalars
     */
    public static Length max(final Length r1, final Length r2)
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
    public static Length max(final Length r1, final Length r2, final Length... rn)
    {
        Length maxr = r1.gt(r2) ? r1 : r2;
        for (Length r : rn)
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
    public static Length min(final Length r1, final Length r2)
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
    public static Length min(final Length r1, final Length r2, final Length... rn)
    {
        Length minr = r1.lt(r2) ? r1 : r2;
        for (Length r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /**
     * Returns a Length representation of a textual representation of a value with a unit. The String representation that can be
     * parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces are allowed,
     * but not required, between the value and the unit.
     * @param text the textual representation to parse into a Length
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static Length valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing Length: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing Length: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            double d = numberParser.parseDouble(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            LengthUnit unit = LengthUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Length", unitString);
            return new Length(d, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing Length from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a Length based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static Length of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing Length: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing Length: empty unitString");
        LengthUnit unit = LengthUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing Length with unit %s", unitString);
        return new Length(value, unit);
    }

    /**
     * Calculate the division of Length and Length, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a division of Length and Length
     */
    public final Dimensionless divide(final Length v)
    {
        return new Dimensionless(this.si / v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Length and LinearDensity, which results in a Dimensionless scalar.
     * @param v scalar
     * @return scalar as a multiplication of Length and LinearDensity
     */
    public final Dimensionless times(final LinearDensity v)
    {
        return new Dimensionless(this.si * v.si, DimensionlessUnit.SI);
    }

    /**
     * Calculate the multiplication of Length and Length, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a multiplication of Length and Length
     */
    public final Area times(final Length v)
    {
        return new Area(this.si * v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of Length and LinearDensity, which results in a Area scalar.
     * @param v scalar
     * @return scalar as a division of Length and LinearDensity
     */
    public final Area divide(final LinearDensity v)
    {
        return new Area(this.si / v.si, AreaUnit.SI);
    }

    /**
     * Calculate the division of Length and Area, which results in a LinearDensity scalar.
     * @param v scalar
     * @return scalar as a division of Length and Area
     */
    public final LinearDensity divide(final Area v)
    {
        return new LinearDensity(this.si / v.si, LinearDensityUnit.SI);
    }

    /**
     * Calculate the multiplication of Length and Area, which results in a Volume scalar.
     * @param v scalar
     * @return scalar as a multiplication of Length and Area
     */
    public final Volume times(final Area v)
    {
        return new Volume(this.si * v.si, VolumeUnit.SI);
    }

    /**
     * Calculate the multiplication of Length and Force, which results in a Energy scalar.
     * @param v scalar
     * @return scalar as a multiplication of Length and Force
     */
    public final Energy times(final Force v)
    {
        return new Energy(this.si * v.si, EnergyUnit.SI);
    }

    /**
     * Calculate the multiplication of Length and Frequency, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a multiplication of Length and Frequency
     */
    public final Speed times(final Frequency v)
    {
        return new Speed(this.si * v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of Length and Duration, which results in a Speed scalar.
     * @param v scalar
     * @return scalar as a division of Length and Duration
     */
    public final Speed divide(final Duration v)
    {
        return new Speed(this.si / v.si, SpeedUnit.SI);
    }

    /**
     * Calculate the division of Length and Speed, which results in a Duration scalar.
     * @param v scalar
     * @return scalar as a division of Length and Speed
     */
    public final Duration divide(final Speed v)
    {
        return new Duration(this.si / v.si, DurationUnit.SI);
    }

    /**
     * Calculate the multiplication of Length and FlowMass, which results in a Momentum scalar.
     * @param v scalar
     * @return scalar as a multiplication of Length and FlowMass
     */
    public final Momentum times(final FlowMass v)
    {
        return new Momentum(this.si * v.si, MomentumUnit.SI);
    }

    @Override
    public LinearDensity reciprocal()
    {
        return LinearDensity.ofSI(1.0 / this.si);
    }

    /**
     * Multiply two scalars that result in a scalar of type Length.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the multiplication of both scalars as an instance of Length
     */
    public static Length multiply(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(LengthUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Multiplying %s by %s does not result in instance of type Length",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Length(scalar1.si * scalar2.si, LengthUnit.SI);
    }

    /**
     * Divide two scalars that result in a scalar of type Length.
     * @param scalar1 the first scalar
     * @param scalar2 the second scalar
     * @return the division of scalar1 by scalar2 as an instance of Length
     */
    public static Length divide(final DoubleScalarRel<?, ?> scalar1, final DoubleScalarRel<?, ?> scalar2)
    {
        Throw.whenNull(scalar1, "scalar1 cannot be null");
        Throw.whenNull(scalar2, "scalar2 cannot be null");
        Throw.when(!scalar1.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(scalar2.getDisplayUnit().getQuantity().getSiDimensions()).equals(LengthUnit.BASE.getSiDimensions()),
                IllegalArgumentException.class, "Dividing %s by %s does not result in an instance of type Length",
                scalar1.toDisplayString(), scalar2.toDisplayString());
        return new Length(scalar1.si / scalar2.si, LengthUnit.SI);
    }

}
