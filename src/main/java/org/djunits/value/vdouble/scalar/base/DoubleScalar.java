package org.djunits.value.vdouble.scalar.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.djunits.unit.AbsoluteLinearUnit;
import org.djunits.unit.SIUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.util.UnitRuntimeException;
import org.djunits.value.Absolute;
import org.djunits.value.base.Scalar;
import org.djunits.value.formatter.Format;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.scalar.SIScalar;

/**
 * The most basic abstract class for the DoubleScalar.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit
 * @param <S> the type
 */
public abstract class DoubleScalar<U extends Unit<U>, S extends DoubleScalar<U, S>> extends Scalar<U, S>
{
    /** */
    private static final long serialVersionUID = 20161015L;

    /** The value, stored in the standard SI unit. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public final double si;

    /**
     * Construct a new DoubleScalar.
     * @param unit U; the unit
     * @param si double; the si value to store
     */
    public DoubleScalar(final U unit, final double si)
    {
        super(unit);
        this.si = si;
    }

    /**
     * Retrieve the value in the underlying SI unit.
     * @return double
     */
    public final double getSI()
    {
        return this.si;
    }

    /**
     * Retrieve the value in the original unit.
     * @return double
     */
    public final double getInUnit()
    {
        return ValueUtil.expressAsUnit(getSI(), getDisplayUnit());
    }

    /**
     * Retrieve the value converted into some specified unit.
     * @param targetUnit U; the unit to convert the value into
     * @return double
     */
    public final double getInUnit(final U targetUnit)
    {
        return ValueUtil.expressAsUnit(getSI(), targetUnit);
    }

    /** {@inheritDoc} */
    @Override
    public final boolean lt(final S o)
    {
        return this.getSI() < o.getSI();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean le(final S o)
    {
        return this.getSI() <= o.getSI();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean gt(final S o)
    {
        return this.getSI() > o.getSI();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean ge(final S o)
    {
        return this.getSI() >= o.getSI();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean eq(final S o)
    {
        return this.getSI() == o.getSI();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean ne(final S o)
    {
        return this.getSI() != o.getSI();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean lt0()
    {
        return this.getSI() < 0.0;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean le0()
    {
        return this.getSI() <= 0.0;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean gt0()
    {
        return this.getSI() > 0.0;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean ge0()
    {
        return this.getSI() >= 0.0;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean eq0()
    {
        return this.getSI() == 0.0;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean ne0()
    {
        return this.getSI() != 0.0;
    }

    /** {@inheritDoc} */
    @Override
    public final int compareTo(final S o)
    {
        return Double.compare(this.getSI(), o.getSI());
    }

    /** {@inheritDoc} */
    @Override
    public int intValue()
    {
        return (int) this.getSI();
    }

    /** {@inheritDoc} */
    @Override
    public long longValue()
    {
        return (long) this.getSI();
    }

    /** {@inheritDoc} */
    @Override
    public float floatValue()
    {
        return (float) this.getSI();
    }

    /** {@inheritDoc} */
    @Override
    public double doubleValue()
    {
        return this.getSI();
    }

    /**********************************************************************************/
    /********************************* GENERIC METHODS ********************************/
    /**********************************************************************************/

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return toString(getDisplayUnit(), false, true);
    }

    /** {@inheritDoc} */
    @Override
    public String toString(final U displayUnit)
    {
        return toString(displayUnit, false, true);
    }

    /** {@inheritDoc} */
    @Override
    public String toString(final boolean verbose, final boolean withUnit)
    {
        return toString(getDisplayUnit(), verbose, withUnit);
    }

    /** {@inheritDoc} */
    @Override
    public String toString(final U displayUnit, final boolean verbose, final boolean withUnit)
    {
        StringBuffer buf = new StringBuffer();
        if (verbose)
        {
            buf.append(this instanceof Absolute ? "Abs " : "Rel ");
        }
        double d = ValueUtil.expressAsUnit(getSI(), displayUnit);
        buf.append(Format.format(d));
        if (withUnit)
        {
            buf.append(" "); // Insert one space as prescribed by SI writing conventions
            buf.append(displayUnit.getLocalizedDisplayAbbreviation());
        }
        return buf.toString();
    }

    /**
     * Format this DoubleScalar in SI unit using prefixes when possible. If the value is too small or too large,
     * e-notation and the plain SI unit are used.
     * @return String; formatted value of this DoubleScalar
     */
    public String toStringSIPrefixed()
    {
        return toStringSIPrefixed(-24, 26);
    }

    /**
     * Format this DoubleScalar in SI unit using prefixes when possible and within the specified size range. If the
     * value is too small or too large, e-notation and the plain SI unit are used.
     * @param smallestPower int; the smallest exponent value that will be written using an SI prefix
     * @param biggestPower int; the largest exponent value that will be written using an SI prefix
     * @return String; formatted value of this DoubleScalar
     */
    public String toStringSIPrefixed(final int smallestPower, final int biggestPower)
    {
        // Override this method for weights, nonlinear units and DimensionLess.
        if (!Double.isFinite(this.si))
        {
            return toString(getDisplayUnit().getStandardUnit());
        }
        // PK: I can't think of an easier way to figure out what the exponent will be; rounding of the mantissa to the available
        // width makes this hard; This feels like an expensive way.
        String check = String.format(this.si >= 0 ? "%10.8E" : "%10.7E", this.si);
        int exponent = Integer.parseInt(check.substring(check.indexOf("E") + 1));
        if (exponent < -24 || exponent < smallestPower || exponent > 24 + 2 || exponent > biggestPower)
        {
            // Out of SI prefix range; do not scale.
            return String.format(this.si >= 0 ? "%10.4E" : "%10.3E", this.si) + " "
                    + getDisplayUnit().getStandardUnit().getId();
        }
        Integer roundedExponent = (int) Math.ceil((exponent - 2.0) / 3) * 3;
        // System.out.print(String.format("exponent=%d; roundedExponent=%d ", exponent, roundedExponent));
        String key =
                SIPrefixes.FACTORS.get(roundedExponent).getDefaultTextualPrefix() + getDisplayUnit().getStandardUnit().getId();
        U displayUnit = getDisplayUnit().getQuantity().getUnitByAbbreviation(key);
        return toString(displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public String toTextualString()
    {
        return toTextualString(getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public String toTextualString(final U displayUnit)
    {
        double d = ValueUtil.expressAsUnit(getSI(), displayUnit);
        return format(d) + " " + displayUnit.getLocalizedTextualAbbreviation();
    }

    /** {@inheritDoc} */
    @Override
    public String toDisplayString()
    {
        return toDisplayString(getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public String toDisplayString(final U displayUnit)
    {
        double d = ValueUtil.expressAsUnit(getSI(), displayUnit);
        return format(d) + " " + displayUnit.getLocalizedDisplayAbbreviation();
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public int hashCode()
    {
        final int prime = 31;
        int result = getDisplayUnit().getStandardUnit().hashCode();
        long temp;
        temp = Double.doubleToLongBits(this.getSI());
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings({"checkstyle:designforextension", "checkstyle:needbraces", "unchecked"})
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DoubleScalar<U, S> other = (DoubleScalar<U, S>) obj;
        if (!getDisplayUnit().getStandardUnit().equals(other.getDisplayUnit().getStandardUnit()))
            return false;
        if (Double.doubleToLongBits(this.getSI()) != Double.doubleToLongBits(other.getSI()))
            return false;
        return true;
    }

    /**********************************************************************************/
    /********************************* STATIC METHODS *********************************/
    /**********************************************************************************/

    /** The cache to make the lookup of the constructor for a Scalar belonging to a unit faster. */
    private static final Map<Unit<?>, Constructor<? extends DoubleScalar<?, ?>>> CACHE = new HashMap<>();
    
    /**
     * Instantiate the DoubleScalar based on its unit. Rigid check on types by the compiler.
     * @param value double; the value
     * @param unit U; the unit in which the value is expressed
     * @return S; an instantiated DoubleScalar with the value expressed in the unit
     * @param <U> the unit
     * @param <S> the return type
     */
    public static <U extends Unit<U>, S extends DoubleScalar<U, S>> S instantiate(final double value, final U unit)
    {
        return instantiateAnonymous(value, unit);
    }

    /**
     * Instantiate the DoubleScalar with an SI value and add the displayUnit later. Rigid check on types by the compiler.
     * @param valueSI double; the SIvalue
     * @param displayUnit U; the unit in which the value will be displayed
     * @return S; an instantiated DoubleScalar with the SI value and the display unit
     * @param <U> the unit
     * @param <S> the return type
     */
    public static <U extends Unit<U>, S extends DoubleScalar<U, S>> S instantiateSI(final double valueSI,
            final U displayUnit)
    {
        S result = instantiateAnonymous(valueSI, displayUnit.getStandardUnit());
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /**
     * Instantiate the DoubleScalar based on its unit. Loose check for types on the compiler. This allows the unit to be
     * specified as a Unit&lt;?&gt; type.<br>
     * <b>Note</b> that it is possible to make mistakes with anonymous units.
     * @param value double; the value
     * @param unit Unit&lt;?&gt;; the unit in which the value is expressed
     * @return S; an instantiated DoubleScalar with the value expressed in the unit
     * @param <S> the return type
     */
    @SuppressWarnings("unchecked")
    public static <S extends DoubleScalar<?, S>> S instantiateAnonymous(final double value, final Unit<?> unit)
    {
        try
        {
            Constructor<? extends DoubleScalar<?, ?>> scalarConstructor = CACHE.get(unit);
            if (scalarConstructor == null)
            {
                if (!unit.getClass().getSimpleName().endsWith("Unit"))
                {
                    throw new ClassNotFoundException("Unit " + unit.getClass().getSimpleName()
                            + " name does noet end with 'Unit'. Cannot find corresponding scalar");
                }
                Class<? extends DoubleScalar<?, ?>> scalarClass;
                if (unit instanceof SIUnit)
                {
                    scalarClass = SIScalar.class;
                }
                else
                {
                    scalarClass = (Class<DoubleScalar<?, ?>>) Class
                            .forName("org.djunits.value.vdouble.scalar." + unit.getClass().getSimpleName().replace("Unit", ""));
                }
                scalarConstructor = scalarClass.getDeclaredConstructor(double.class, unit.getClass());
                CACHE.put(unit, scalarConstructor);
            }
            return (S) scalarConstructor.newInstance(value, unit);
        }
        catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception)
        {
            throw new UnitRuntimeException("Cannot instantiate DoubleScalar of unit " + unit.toString() + ". Reason: "
                    + exception.getMessage());
        }
    }

    /**
     * Add a Relative value to an Absolute value. Return a new instance of the value. The unit of the return value will be the
     * unit of the left argument.
     * @param left A, an absolute typed DoubleScalar; the left argument
     * @param right R, a relative typed DoubleScalar; the right argument
     * @param <AU> Unit; the absolute unit of the parameters and the result
     * @param <RU> Unit; the relative unit of the parameters and the result
     * @param <R> the relative type
     * @param <A> the corresponding absolute type
     * @return A; an absolute typed DoubleScalar; the sum of the values as an Absolute value
     */
    public static <AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>,
            R extends DoubleScalarRelWithAbs<AU, A, RU, R>,
            A extends DoubleScalarAbs<AU, A, RU, R>> A plus(final A left, final R right)
    {
        return left.plus(right);
    }

    /**
     * Add an Absolute value to a Relative value. Return a new instance of the value. The unit of the return value will be the
     * unit of the left argument.
     * @param left A, an absolute typed DoubleScalar; the left argument
     * @param right R, a relative typed DoubleScalar; the right argument
     * @param <AU> Unit; the absolute unit of the parameters and the result
     * @param <RU> Unit; the relative unit of the parameters and the result
     * @param <R> the relative type
     * @param <A> the corresponding absolute type
     * @return A; an absolute typed DoubleScalar; the sum of the values as an Absolute value
     */
    public static <AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>,
            R extends DoubleScalarRelWithAbs<AU, A, RU, R>,
            A extends DoubleScalarAbs<AU, A, RU, R>> A plus(final R left, final A right)
    {
        return right.plus(left);
    }

    /**
     * Add a Relative value to a Relative value. Return a new instance of the value. The unit of the return value will be the
     * unit of the left argument.
     * @param left R, a relative typed DoubleScalar; the left argument
     * @param right R, a relative typed DoubleScalar; the right argument
     * @param <U> Unit; the unit of the parameters and the result
     * @param <R> the relative type
     * @return R; a relative typed DoubleScalar; the sum of the values as a Relative value
     */
    public static <U extends Unit<U>, R extends DoubleScalarRel<U, R>> R plus(final R left, final R right)
    {
        return left.plus(right);
    }

    /**
     * Subtract a Relative value from an absolute value. Return a new instance of the value. The unit of the return value will
     * be the unit of the left argument.
     * @param left A, an absolute typed DoubleScalar; the left value
     * @param right R, a relative typed DoubleScalar; the right value
     * @param <AU> Unit; the absolute unit of the parameters and the result
     * @param <RU> Unit; the relative unit of the parameters and the result
     * @param <R> the relative type
     * @param <A> the corresponding absolute type
     * @return A; an absolute typed DoubleScalar; the resulting value as an absolute value
     */
    public static <AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>,
            R extends DoubleScalarRelWithAbs<AU, A, RU, R>,
            A extends DoubleScalarAbs<AU, A, RU, R>> A minus(final A left, final R right)
    {
        return left.minus(right);
    }

    /**
     * Subtract a relative value from a relative value. Return a new instance of the value. The unit of the value will be the
     * unit of the first argument.
     * @param left R, a relative typed DoubleScalar; the left value
     * @param right R, a relative typed DoubleScalar; the right value
     * @param <U> Unit; the unit of the parameters and the result
     * @param <R> the relative type
     * @return R; a relative typed DoubleScalar; the resulting value as a relative value
     */
    public static <U extends Unit<U>, R extends DoubleScalarRel<U, R>> R minus(final R left, final R right)
    {
        return left.minus(right);
    }

    /**
     * Subtract two absolute values. Return a new instance of a relative value of the difference. The unit of the value will be
     * the unit of the first argument.
     * @param left A, an absolute typed DoubleScalar; value 1
     * @param right A, an absolute typed DoubleScalar; value 2
     * @param <AU> Unit; the absolute unit of the parameters and the result
     * @param <RU> Unit; the relative unit of the parameters and the result
     * @param <R> the relative type
     * @param <A> the corresponding absolute type
     * @return R; a relative typed DoubleScalar; the difference of the two absolute values as a relative value
     */
    public static <AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>,
            R extends DoubleScalarRelWithAbs<AU, A, RU, R>,
            A extends DoubleScalarAbs<AU, A, RU, R>> R minus(final A left, final A right)
    {
        return left.minus(right);
    }

    /**
     * Multiply two values; the result is a new instance with a different (existing or generated) SI unit.
     * @param left DoubleScalarRel&lt;?, ?&gt;; the left operand
     * @param right DoubleScalarRel&lt;?, ?&gt;; the right operand
     * @return DoubleScalarRel&lt;SIUnit&gt;; the product of the two values
     */
    public static SIScalar multiply(final DoubleScalarRel<?, ?> left, final DoubleScalarRel<?, ?> right)
    {
        SIUnit targetUnit = Unit.lookupOrCreateUnitWithSIDimensions(left.getDisplayUnit().getQuantity().getSiDimensions()
                .plus(right.getDisplayUnit().getQuantity().getSiDimensions()));
        return new SIScalar(left.getSI() * right.getSI(), targetUnit);
    }

    /**
     * Divide two values; the result is a new instance with a different (existing or generated) SI unit.
     * @param left DoubleScalarRel&lt;?, ?&gt;; the left operand
     * @param right DoubleScalarRel&lt;?, ?&gt;; the right operand
     * @return DoubleScalarRel&lt;SIUnit&gt;; the ratio of the two values
     */
    public static SIScalar divide(final DoubleScalarRel<?, ?> left, final DoubleScalarRel<?, ?> right)
    {
        SIUnit targetUnit = Unit.lookupOrCreateUnitWithSIDimensions(left.getDisplayUnit().getQuantity().getSiDimensions()
                .minus(right.getDisplayUnit().getQuantity().getSiDimensions()));
        return new SIScalar(left.getSI() / right.getSI(), targetUnit);
    }

    /**
     * Interpolate between two values. Made to be able to call e.g., Area a = DoubleScalar.interpolate(a1, a2, 0.4);
     * @param zero R; the low value
     * @param one R; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @param <U> Unit; the unit of the parameters and the result
     * @param <R> the relative type
     * @return R; an Absolute Scalar at the <code>ratio</code> between <code>zero</code> and <code>one</code>
     */
    public static <U extends Unit<U>, R extends DoubleScalarRel<U, R>> R interpolate(final R zero, final R one,
            final double ratio)
    {
        return zero.instantiateRel(zero.getInUnit() * (1 - ratio) + one.getInUnit(zero.getDisplayUnit()) * ratio,
                zero.getDisplayUnit());
    }

    /**
     * Interpolate between two values. Made to be able to call e.g., Time t = DoubleScalar.interpolate(t1, t2, 0.4);
     * @param zero A; the low value
     * @param one A; the high value
     * @param ratio double; the ratio between 0 and 1, inclusive
     * @param <AU> Unit; the absolute unit of the parameters and the result
     * @param <RU> Unit; the relative unit of the parameters and the result
     * @param <R> the relative type
     * @param <A> the corresponding absolute type
     * @return R; a Relative Scalar at the <code>ratio</code> between <code>zero</code> and <code>one</code>
     */
    public static <AU extends AbsoluteLinearUnit<AU, RU>, RU extends Unit<RU>,
            R extends DoubleScalarRelWithAbs<AU, A, RU, R>,
            A extends DoubleScalarAbs<AU, A, RU, R>> A interpolate(final A zero, final A one, final double ratio)
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
    public static <U extends Unit<U>, T extends DoubleScalar<U, T>> T max(final T r1, final T r2)
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
    public static <U extends Unit<U>, T extends DoubleScalar<U, T>> T max(final T r1, final T r2, final T... rn)
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
    public static <U extends Unit<U>, T extends DoubleScalar<U, T>> T min(final T r1, final T r2)
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
    public static <U extends Unit<U>, T extends DoubleScalar<U, T>> T min(final T r1, final T r2, final T... rn)
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
