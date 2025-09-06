package org.djunits.value.vfloat.scalar;

import java.util.Locale;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.vfloat.scalar.base.FloatScalarAbs;
import org.djutils.base.NumberParser;
import org.djutils.exceptions.Throw;

import jakarta.annotation.Generated;

/**
 * Easy access methods for the FloatAbsoluteTemperature FloatScalar.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatAbsoluteTemperature
        extends FloatScalarAbs<AbsoluteTemperatureUnit, FloatAbsoluteTemperature, TemperatureUnit, FloatTemperature>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /** Constant with value zero. */
    public static final FloatAbsoluteTemperature ZERO = new FloatAbsoluteTemperature(0.0f, AbsoluteTemperatureUnit.DEFAULT);

    /**
     * Construct FloatAbsoluteTemperature scalar with a unit.
     * @param value the float value, expressed in the given unit
     * @param unit unit for the float value
     */
    public FloatAbsoluteTemperature(final float value, final AbsoluteTemperatureUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct FloatAbsoluteTemperature scalar with a unit using a double value.
     * @param value the double value, expressed in the given unit
     * @param unit unit for the resulting float value
     */
    public FloatAbsoluteTemperature(final double value, final AbsoluteTemperatureUnit unit)
    {
        super((float) value, unit);
    }

    /**
     * Construct FloatAbsoluteTemperature scalar.
     * @param value Scalar from which to construct this instance
     */
    public FloatAbsoluteTemperature(final FloatAbsoluteTemperature value)
    {
        super(value);
    }

    @Override
    public final FloatAbsoluteTemperature instantiateAbs(final float value, final AbsoluteTemperatureUnit unit)
    {
        return new FloatAbsoluteTemperature(value, unit);
    }

    @Override
    public final FloatTemperature instantiateRel(final float value, final TemperatureUnit unit)
    {
        return new FloatTemperature(value, unit);
    }

    /**
     * Construct FloatAbsoluteTemperature scalar based on an BASE unit.
     * @param value the float value in BASE units
     * @return the new scalar with the BASE value
     */
    public static final FloatAbsoluteTemperature ofSI(final float value)
    {
        return new FloatAbsoluteTemperature(value, AbsoluteTemperatureUnit.DEFAULT);
    }

    /**
     * Interpolate between two values. Note that the first value does not have to be smaller than the second.
     * @param zero the value at a ratio of zero
     * @param one the value at a ratio of one
     * @param ratio the ratio between 0 and 1, inclusive
     * @return a FloatAbsoluteTemperature at the given ratio between 0 and 1
     */
    public static FloatAbsoluteTemperature interpolate(final FloatAbsoluteTemperature zero, final FloatAbsoluteTemperature one,
            final float ratio)
    {
        Throw.when(ratio < 0.0 || ratio > 1.0, IllegalArgumentException.class,
                "ratio for interpolation should be between 0 and 1, but is %f", ratio);
        return new FloatAbsoluteTemperature(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two absolute scalars.
     * @param a1 the first scalar
     * @param a2 the second scalar
     * @return the maximum value of two absolute scalars
     */
    public static FloatAbsoluteTemperature max(final FloatAbsoluteTemperature a1, final FloatAbsoluteTemperature a2)
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
    public static FloatAbsoluteTemperature max(final FloatAbsoluteTemperature a1, final FloatAbsoluteTemperature a2,
            final FloatAbsoluteTemperature... an)
    {
        FloatAbsoluteTemperature maxa = a1.gt(a2) ? a1 : a2;
        for (FloatAbsoluteTemperature a : an)
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
    public static FloatAbsoluteTemperature min(final FloatAbsoluteTemperature a1, final FloatAbsoluteTemperature a2)
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
    public static FloatAbsoluteTemperature min(final FloatAbsoluteTemperature a1, final FloatAbsoluteTemperature a2,
            final FloatAbsoluteTemperature... an)
    {
        FloatAbsoluteTemperature mina = a1.lt(a2) ? a1 : a2;
        for (FloatAbsoluteTemperature a : an)
        {
            if (a.lt(mina))
            {
                mina = a;
            }
        }
        return mina;
    }

    /**
     * Returns a FloatAbsoluteTemperature representation of a textual representation of a value with a unit. The String
     * representation that can be parsed is the double value in the unit, followed by a localized or English abbreviation of the
     * unit. Spaces are allowed, but not required, between the value and the unit.
     * @param text the textual representation to parse into a FloatAbsoluteTemperature
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static FloatAbsoluteTemperature valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing FloatAbsoluteTemperature: text to parse is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAbsoluteTemperature: empty text to parse");
        try
        {
            NumberParser numberParser = new NumberParser().lenient().trailing();
            float f = numberParser.parseFloat(text);
            String unitString = text.substring(numberParser.getTrailingPosition()).trim();
            AbsoluteTemperatureUnit unit = AbsoluteTemperatureUnit.BASE.getUnitByAbbreviation(unitString);
            Throw.when(unit == null, IllegalArgumentException.class, "Unit %s not found for quantity AbsoluteTemperature",
                    unitString);
            return new FloatAbsoluteTemperature(f, unit);
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing FloatAbsoluteTemperature from " + text + " using Locale "
                    + Locale.getDefault(Locale.Category.FORMAT), exception);
        }
    }

    /**
     * Returns a FloatAbsoluteTemperature based on a value and the textual representation of the unit, which can be localized.
     * @param value the value to use
     * @param unitString the textual representation of the unit
     * @return the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static FloatAbsoluteTemperature of(final float value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing FloatAbsoluteTemperature: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class,
                "Error parsing FloatAbsoluteTemperature: empty unitString");
        AbsoluteTemperatureUnit unit = AbsoluteTemperatureUnit.BASE.getUnitByAbbreviation(unitString);
        Throw.when(unit == null, IllegalArgumentException.class, "Error parsing FloatAbsoluteTemperature with unit %s",
                unitString);
        return new FloatAbsoluteTemperature(value, unit);
    }

}
