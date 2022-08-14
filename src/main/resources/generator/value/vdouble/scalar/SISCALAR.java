package org.djunits.value.vdouble.scalar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;

import org.djunits.Throw;
import javax.annotation.Generated;

import org.djunits.unit.*;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.vdouble.scalar.*;
import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalarRel;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;

/**
 * Easy access methods for the generic Relative SI DoubleScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
 * All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "GenerateDJUNIT")
public class SIScalar extends AbstractDoubleScalarRel<SIUnit, SIScalar>
{
    /** */
    private static final long serialVersionUID = 20150901L;

    /**
     * Construct SI scalar.
     * @param value double; the double value
     * @param unit SIUnit; unit for the double value
     */
    public SIScalar(final double value, final SIUnit unit)
    {
        super(value, unit);
    }

    /**
     * Construct SI scalar.
     * @param value SIScalar; Scalar from which to construct this instance
     */
    public SIScalar(final SIScalar value)
    {
        super(value);
    }

    /** {@inheritDoc} */
    @Override
    public final SIScalar instantiateRel(final double value, final SIUnit unit)
    {
        return new SIScalar(value, unit);
    }

    /**
     * Construct SI scalar.
     * @param value double; the double value in SI units
     * @param unit SIUnit; the unit to use for the SI scalar
     * @return SIScalar; the new scalar with the SI value
     */
    public static final SIScalar instantiateSI(final double value, final SIUnit unit)
    {
        return new SIScalar(value, unit);
    }

    /**
     * Interpolate between two values.
     * @param zero SIScalar; the low value
     * @param one SIScalar; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @return SIScalar; a Scalar at the ratio between
     */
    public static SIScalar interpolate(final SIScalar zero, final SIScalar one, final double ratio)
    {
        return new SIScalar(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio, zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 SIScalar; the first scalar
     * @param r2 SIScalar; the second scalar
     * @return SIScalar; the maximum value of two relative scalars
     */
    public static SIScalar max(final SIScalar r1, final SIScalar r2)
    {
        return r1.gt(r2) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 SIScalar; the first scalar
     * @param r2 SIScalar; the second scalar
     * @param rn SIScalar...; the other scalars
     * @return SIScalar; the maximum value of more than two relative scalars
     */
    public static SIScalar max(final SIScalar r1, final SIScalar r2, final SIScalar... rn)
    {
        SIScalar maxr = r1.gt(r2) ? r1 : r2;
        for (SIScalar r : rn)
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
     * @param r1 SIScalar; the first scalar
     * @param r2 SIScalar; the second scalar
     * @return SIScalar; the minimum value of two relative scalars
     */
    public static SIScalar min(final SIScalar r1, final SIScalar r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 SIScalar; the first scalar
     * @param r2 SIScalar; the second scalar
     * @param rn SIScalar...; the other scalars
     * @return SIScalar; the minimum value of more than two relative scalars
     */
    public static SIScalar min(final SIScalar r1, final SIScalar r2, final SIScalar... rn)
    {
        SIScalar minr = r1.lt(r2) ? r1 : r2;
        for (SIScalar r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

    /** {@inheritDoc} */
    @Override
    public SIScalar reciprocal()
    {
        return DoubleScalar.divide(Dimensionless.ONE, this);
    }

    /**
     * Returns an SIScalar representation of a textual representation of a value with a unit. The String representation that can
     * be parsed is the double value in the unit, followed by the official abbreviation of the unit. Spaces are allowed, but not
     * required, between the value and the unit.
     * @param text String; the textual representation to parse into a SIScalar
     * @return SIScalar; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the text cannot be parsed
     * @throws NullPointerException when the text argument is null
     */
    public static SIScalar valueOf(final String text)
    {
        Throw.whenNull(text, "Error parsing SIScalar: unitString is null");
        Throw.when(text.length() == 0, IllegalArgumentException.class, "Error parsing SIScalar: empty unitString");
        Matcher matcher = ValueUtil.NUMBER_PATTERN.matcher(text);
        if (matcher.find())
        {
            int index = matcher.end();
            try
            {
                String unitString = text.substring(index).trim();
                String valueString = text.substring(0, index).trim();
                SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
                if (unit != null)
                {
                    double d = Double.parseDouble(valueString);
                    return new SIScalar(d, unit);
                }
            }
            catch (Exception exception)
            {
                throw new IllegalArgumentException("Error parsing SIScalar from " + text, exception);
            }
        }
        throw new IllegalArgumentException("Error parsing SIScalar from " + text);
    }

    /**
     * Returns an SIScalar based on a value and the textual representation of the unit.
     * @param value double; the value to use
     * @param unitString String; the textual representation of the unit
     * @return SIScalar; the Scalar representation of the value in its unit
     * @throws IllegalArgumentException when the unit cannot be parsed or is incorrect
     * @throws NullPointerException when the unitString argument is null
     */
    public static SIScalar of(final double value, final String unitString)
    {
        Throw.whenNull(unitString, "Error parsing SIScalar: unitString is null");
        Throw.when(unitString.length() == 0, IllegalArgumentException.class, "Error parsing SIScalar: empty unitString");
        try
        {
            SIUnit unit = Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(unitString));
            if (unit != null)
            {
                return new SIScalar(value, unit);
            }
        }
        catch (Exception exception)
        {
            throw new IllegalArgumentException("Error parsing SIUnit from " + unitString, exception);
        }
        throw new IllegalArgumentException("Error parsing SIScalar with unit " + unitString);
    }

    /**********************************************************************************/
    /******************************** 'CAST AS' METHODS *******************************/
    /**********************************************************************************/

    /**
     * Return the current scalar transformed to a scalar in the given unit. Of course the SI dimensionality has to match,
     * otherwise the scalar cannot be transformed. The compiler will check the alignment between the return value and the unit.
     * @param displayUnit KU; the unit in which the scalar needs to be expressed
     * @return S; the scalar that has been transformed into the right scalar type and unit
     * @param <U> the unit type
     * @param <S> the scalar type
     */
    public final <U extends Unit<U>, S extends AbstractDoubleScalarRel<U, S>> S as(final U displayUnit)
    {
        Throw.when(!(getDisplayUnit().getQuantity().getSiDimensions().equals(displayUnit.getQuantity().getSiDimensions())),
                UnitRuntimeException.class, "SIScalar with unit %s cannot be converted to a scalar with unit %s", getDisplayUnit(),
                displayUnit);
        S result = DoubleScalar.instantiate(this.si, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    %%ASMETHODS%%

}
