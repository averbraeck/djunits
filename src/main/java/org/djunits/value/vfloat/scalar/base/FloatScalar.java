package org.djunits.value.vfloat.scalar.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.vfloat.scalar.FloatSIScalar;

/**
 * Static methods to create and operate on FloatScalars.
 * <p>
 * Copyright (c) 2015-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public abstract class FloatScalar
{
    /**********************************************************************************/
    /********************************* STATIC METHODS *********************************/
    /**********************************************************************************/

    /** The cache to make the lookup of the constructor for a Scalar belonging to a unit faster. */
    private static final Map<Unit<?>, Constructor<? extends FloatScalarInterface<?, ?>>> CACHE = new HashMap<>();

    /** Do not instantiate. */
    private FloatScalar()
    {
        // Utility class.
    }

    /**
     * Instantiate the FloatScalar based on its unit. Rigid check on types by the compiler.
     * @param value float; the value
     * @param unit U; the unit in which the value is expressed
     * @return S; an instantiated FloatScalar with the value expressed in the unit
     * @param <U> the unit
     * @param <S> the return type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>> S instantiate(final float value, final U unit)
    {
        return instantiateAnonymous(value, unit);
    }

    /**
     * Instantiate the FloatScalar with an SI value and add the displayUnit later. Rigid check on types by the compiler.
     * @param valueSI float; the SIvalue
     * @param displayUnit U; the unit in which the value will be displayed
     * @return S; an instantiated FloatScalar with the SI value and the display unit
     * @param <U> the unit
     * @param <S> the return type
     */
    public static <U extends Unit<U>, S extends FloatScalarInterface<U, S>> S instantiateSI(final float valueSI,
            final U displayUnit)
    {
        S result = instantiateAnonymous(valueSI, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Instantiate the FloatScalar based on its unit. Loose check for types on the compiler. This allows the unit to be
     * specified as a Unit&lt;?&gt; type.<br>
     * <b>Note</b> that it is possible to make mistakes with anonymous units.
     * @param value float; the value
     * @param unit Unit&lt;?&gt;; the unit in which the value is expressed
     * @return S; an instantiated FloatScalar with the value expressed in the unit
     * @param <S> the return type
     */
    @SuppressWarnings("unchecked")
    public static <S extends FloatScalarInterface<?, S>> S instantiateAnonymous(final float value, final Unit<?> unit)
    {
        try
        {
            Constructor<? extends FloatScalarInterface<?, ?>> scalarConstructor = CACHE.get(unit);
            if (scalarConstructor == null)
            {
                if (!unit.getClass().getSimpleName().endsWith("Unit"))
                {
                    throw new ClassNotFoundException("Unit " + unit.getClass().getSimpleName()
                            + " name does noet end with 'Unit'. Cannot find corresponding scalar");
                }
                Class<? extends FloatScalarInterface<?, ?>> scalarClass;
                if (unit instanceof SIUnit)
                {
                    scalarClass = FloatSIScalar.class;
                }
                else
                {
                    scalarClass = (Class<FloatScalarInterface<?, ?>>) Class.forName(
                            "org.djunits.value.vfloat.scalar.Float" + unit.getClass().getSimpleName().replace("Unit", ""));
                }
                scalarConstructor = scalarClass.getDeclaredConstructor(float.class, unit.getClass());
                CACHE.put(unit, scalarConstructor);
            }
            return (S) scalarConstructor.newInstance(value, unit);
        }
        catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception)
        {
            throw new UnitRuntimeException("Cannot instantiate FloatScalarInterface of unit " + unit.toString() + ". Reason: "
                    + exception.getMessage());
        }
    }

    /**
     * Add a Relative value to an Absolute value. Return a new instance of the value. The unit of the return value will be the
     * unit of the left argument.
     * @param left A, an absolute typed FloatScalar; the left argument
     * @param right R, a relative typed FloatScalar; the right argument
     * @param <AU> Unit; the absolute unit of the parameters and the result
     * @param <RU> Unit; the relative unit of the parameters and the result
     * @param <R> the relative type
     * @param <A> the corresponding absolute type
     * @return A; an absolute typed FloatScalar; the sum of the values as an Absolute value
     */
    public static <AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>,
            R extends FloatScalarInterface.RelWithAbs<AU, A, RU, R>,
            A extends FloatScalarInterface.Abs<AU, A, RU, R>> A plus(final A left, final R right)
    {
        return left.plus(right);
    }

    /**
     * Add an Absolute value to a Relative value. Return a new instance of the value. The unit of the return value will be the
     * unit of the left argument.
     * @param left A, an absolute typed FloatScalar; the left argument
     * @param right R, a relative typed FloatScalar; the right argument
     * @param <AU> Unit; the absolute unit of the parameters and the result
     * @param <RU> Unit; the relative unit of the parameters and the result
     * @param <R> the relative type
     * @param <A> the corresponding absolute type
     * @return A; an absolute typed FloatScalar; the sum of the values as an Absolute value
     */
    public static <AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>,
            R extends FloatScalarInterface.RelWithAbs<AU, A, RU, R>,
            A extends FloatScalarInterface.Abs<AU, A, RU, R>> A plus(final R left, final A right)
    {
        return right.plus(left);
    }

    /**
     * Add a Relative value to a Relative value. Return a new instance of the value. The unit of the return value will be the
     * unit of the left argument.
     * @param left R, a relative typed FloatScalar; the left argument
     * @param right R, a relative typed FloatScalar; the right argument
     * @param <U> Unit; the unit of the parameters and the result
     * @param <R> the relative type
     * @return R; a relative typed FloatScalar; the sum of the values as a Relative value
     */
    public static <U extends Unit<U>, R extends FloatScalarInterface.Rel<U, R>> R plus(final R left, final R right)
    {
        return left.plus(right);
    }

    /**
     * Subtract a Relative value from an absolute value. Return a new instance of the value. The unit of the return value will
     * be the unit of the left argument.
     * @param left A, an absolute typed FloatScalar; the left value
     * @param right R, a relative typed FloatScalar; the right value
     * @param <AU> Unit; the absolute unit of the parameters and the result
     * @param <RU> Unit; the relative unit of the parameters and the result
     * @param <R> the relative type
     * @param <A> the corresponding absolute type
     * @return A; an absolute typed FloatScalar; the resulting value as an absolute value
     */
    public static <AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>,
            R extends FloatScalarInterface.RelWithAbs<AU, A, RU, R>,
            A extends FloatScalarInterface.Abs<AU, A, RU, R>> A minus(final A left, final R right)
    {
        return left.minus(right);
    }

    /**
     * Subtract a relative value from a relative value. Return a new instance of the value. The unit of the value will be the
     * unit of the first argument.
     * @param left R, a relative typed FloatScalar; the left value
     * @param right R, a relative typed FloatScalar; the right value
     * @param <U> Unit; the unit of the parameters and the result
     * @param <R> the relative type
     * @return R; a relative typed FloatScalar; the resulting value as a relative value
     */
    public static <U extends Unit<U>, R extends FloatScalarInterface.Rel<U, R>> R minus(final R left, final R right)
    {
        return left.minus(right);
    }

    /**
     * Subtract two absolute values. Return a new instance of a relative value of the difference. The unit of the value will be
     * the unit of the first argument.
     * @param left A, an absolute typed FloatScalar; value 1
     * @param right A, an absolute typed FloatScalar; value 2
     * @param <AU> Unit; the absolute unit of the parameters and the result
     * @param <RU> Unit; the relative unit of the parameters and the result
     * @param <R> the relative type
     * @param <A> the corresponding absolute type
     * @return R; a relative typed FloatScalar; the difference of the two absolute values as a relative value
     */
    public static <AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>,
            R extends FloatScalarInterface.RelWithAbs<AU, A, RU, R>,
            A extends FloatScalarInterface.Abs<AU, A, RU, R>> R minus(final A left, final A right)
    {
        return left.minus(right);
    }

    /**
     * Multiply two values; the result is a new instance with a different (existing or generated) SI unit.
     * @param left FloatScalarInterface.Rel&lt;?, ?&gt;; the left operand
     * @param right FloatScalarInterface.Rel&lt;?, ?&gt;; the right operand
     * @return FloatScalar.Rel&lt;SIUnit&gt;; the product of the two values
     */
    public static FloatSIScalar multiply(final FloatScalarInterface.Rel<?, ?> left, final FloatScalarInterface.Rel<?, ?> right)
    {
        SIUnit targetUnit = Unit.lookupOrCreateUnitWithSIDimensions(left.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(right.getDisplayUnit().getQuantity().getSiDimensions()));
        return new FloatSIScalar(left.getSI() * right.getSI(), targetUnit);
    }

    /**
     * Divide two values; the result is a new instance with a different (existing or generated) SI unit.
     * @param left FloatScalarInterface.Rel&lt;?, ?&gt;; the left operand
     * @param right FloatScalarInterface.Rel&lt;?, ?&gt;; the right operand
     * @return FloatScalar.Rel&lt;SIUnit&gt;; the ratio of the two values
     */
    public static FloatSIScalar divide(final FloatScalarInterface.Rel<?, ?> left, final FloatScalarInterface.Rel<?, ?> right)
    {
        SIUnit targetUnit = Unit.lookupOrCreateUnitWithSIDimensions(left.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(right.getDisplayUnit().getQuantity().getSiDimensions()));
        return new FloatSIScalar(left.getSI() / right.getSI(), targetUnit);
    }

    /**
     * Interpolate between two values. Made to be able to call e.g., Area a = FloatScalar.interpolate(a1, a2, 0.4);
     * @param zero R; the low value
     * @param one R; the high value
     * @param ratio float; the ratio between 0 and 1, inclusive
     * @param <U> Unit; the unit of the parameters and the result
     * @param <R> the relative type
     * @return R; an Absolute Scalar at the <code>ratio</code> between <code>zero</code> and <code>one</code>
     */
    public static <U extends Unit<U>, R extends FloatScalarInterface.Rel<U, R>> R interpolate(final R zero, final R one,
            final float ratio)
    {
        return zero.instantiateRel(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Interpolate between two values. Made to be able to call e.g., Time t = FloatScalar.interpolate(t1, t2, 0.4);
     * @param zero A; the low value
     * @param one A; the high value
     * @param ratio float; the ratio between 0 and 1, inclusive
     * @param <AU> Unit; the absolute unit of the parameters and the result
     * @param <RU> Unit; the relative unit of the parameters and the result
     * @param <R> the relative type
     * @param <A> the corresponding absolute type
     * @return R; a Relative Scalar at the <code>ratio</code> between <code>zero</code> and <code>one</code>
     */
    public static <AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>,
            R extends FloatScalarInterface.RelWithAbs<AU, A, RU, R>,
            A extends FloatScalarInterface.Abs<AU, A, RU, R>> A interpolate(final A zero, final A one, final float ratio)
    {
        return zero.instantiateAbs(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Return the maximum value of two relative scalars.
     * @param r1 T; the first scalar
     * @param r2 T; the second scalar
     * @param <U> Unit; the unit of the parameters and the result
     * @param <T> the argument and result type
     * @return T; the maximum value of two relative scalars
     */
    public static <U extends Unit<U>, T extends FloatScalarInterface<U, T>> T max(final T r1, final T r2)
    {
        return (r1.gt(r2)) ? r1 : r2;
    }

    /**
     * Return the maximum value of more than two relative scalars.
     * @param r1 T; the first scalar
     * @param r2 T; the second scalar
     * @param rn T...; the other scalars
     * @param <U> Unit; the unit of the parameters and the result
     * @param <T> the argument and result type
     * @return T; the maximum value of more than two relative scalars
     */
    @SafeVarargs
    public static <U extends Unit<U>, T extends FloatScalarInterface<U, T>> T max(final T r1, final T r2, final T... rn)
    {
        T maxr = (r1.gt(r2)) ? r1 : r2;
        for (T r : rn)
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
     * @param r1 T; the first scalar
     * @param r2 T; the second scalar
     * @param <U> Unit; the unit of the parameters and the result
     * @param <T> the argument and result type
     * @return T; the minimum value of two relative scalars
     */
    public static <U extends Unit<U>, T extends FloatScalarInterface<U, T>> T min(final T r1, final T r2)
    {
        return r1.lt(r2) ? r1 : r2;
    }

    /**
     * Return the minimum value of more than two relative scalars.
     * @param r1 T; the first scalar
     * @param r2 T; the second scalar
     * @param rn T...; the other scalars
     * @param <U> Unit; the unit of the parameters and the result
     * @param <T> the argument and result type
     * @return T; the minimum value of more than two relative scalars
     */
    @SafeVarargs
    public static <U extends Unit<U>, T extends FloatScalarInterface<U, T>> T min(final T r1, final T r2, final T... rn)
    {
        T minr = r1.lt(r2) ? r1 : r2;
        for (T r : rn)
        {
            if (r.lt(minr))
            {
                minr = r;
            }
        }
        return minr;
    }

}
