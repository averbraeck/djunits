package org.djunits.old.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.old.unit.AngleUnit;
import org.djunits.old.unit.DirectionUnit;
import org.djunits.old.value.vfloat.scalar.base.FloatScalarAbs;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatDirection FloatScalar.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T15:16:28.380798Z")
public class FloatDirection extends FloatScalarAbs<DirectionUnit, FloatDirection, AngleUnit, FloatAngle>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatDirection ZERO = new FloatDirection(0.0f, DirectionUnit.DEFAULT);

    /**
     * Construct FloatDirection scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatDirection(final float value, final DirectionUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatDirection scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatDirection(final double value, final DirectionUnit unit)
    {
        super((float) value, unit);
    }

    /**
     * Construct FloatDirection scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatDirection(final FloatDirection value)
    {
        super(value);
    }

    @Override
    public final FloatDirection instantiateAbs(final float value, final DirectionUnit unit)
    {
        return new FloatDirection(value, unit);
    }

    @Override
    public final FloatAngle instantiateRel(final float value, final AngleUnit unit)
    {
        return new FloatAngle(value, unit);
    }

    /**
     * Construct FloatDirection scalar based on an BASE unit.
     * @param value the float value in BASE units
     * @return the new scalar with the BASE value
     */
    public static final FloatDirection ofSI(final float value)
    {
        return new FloatDirection(value, DirectionUnit.DEFAULT);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatDirection at the given ratio between 0 and 1
     */
    public static FloatDirection interpolate(final FloatDirection zero, final FloatDirection one, final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatDirection(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 the first scalar
     * @param a2 the second scalar
     * @return the maximum value of two absolute scalars
     */
    public static FloatDirection max(final FloatDirection a1, final FloatDirection a2)
    {
        return a1.gt(a2) ? a1 : a2;
    }

    /**
     * Return the maximum value of more than two absolute scalars.
     * @param a1 the first scalar
     * @param a2 the second scalar
     * @param an the other scalars
     * @return the maximum value of more than two absolute scalars
     */
    public static FloatDirection max(final FloatDirection a1, final FloatDirection a2, final FloatDirection... an)
    {
        FloatDirection maxa = a1.gt(a2) ? a1 : a2;
        for (FloatDirection a : an)
        {
            if (a.gt(maxa))
            {
                maxa = a;
            }
        }
        return maxa;
    }

    /**
     * Return the minimum value of two absolute scalars.
     * @param a1 the first scalar
     * @param a2 the second scalar
     * @return the minimum value of two absolute scalars
     */
    public static FloatDirection min(final FloatDirection a1, final FloatDirection a2)
    {
        return a1.lt(a2) ? a1 : a2;
    }

    /**
     * Return the minimum value of more than two absolute scalars.
     * @param a1 the first scalar
     * @param a2 the second scalar
     * @param an the other scalars
     * @return the minimum value of more than two absolute scalars
     */
    public static FloatDirection min(final FloatDirection a1, final FloatDirection a2, final FloatDirection... an)
    {
        FloatDirection mina = a1.lt(a2) ? a1 : a2;
        for (FloatDirection a : an)
        {
            if (a.lt(mina))
            {
                mina = a;
            }
        }
        return mina;
    }

    /**
     * Returns a FloatDirection representation of a textual representation of a value with a unit. The String representation
     * that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the unit. Spaces
     * are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatDirection
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatDirection valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatDirection: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing FloatDirection: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            DirectionUnit unit = DirectionUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity Direction", unitString);
            return new FloatDirection(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException(
                    "Error parsing FloatDirection from " + text + " using Locale " + Locale.getDefault(Locale.Category.FORMAT),
                    exception);
        }
    }

    /**
     * Returns a FloatDirection based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatDirection of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatDirection: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing FloatDirection: empty unitString");
        DirectionUnit unit = DirectionUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatDirection with unit %s", unitString);
        return new FloatDirection(value, unit);
    }

}
