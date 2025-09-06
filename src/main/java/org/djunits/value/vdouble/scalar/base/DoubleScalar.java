package org.djunits.value.vdouble.scalar.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

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
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
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
     * @param value the value to store, expressed in the given unit
     * @param unit the unit
     */
    public DoubleScalar(final double value, final U unit)
    {
        super(unit);
        this.si = unit.isBaseSIUnit() ? value : ValueUtil.expressAsSIUnit(value, unit);
    }

    /**
     * Construct a new DoubleScalar.
     * @param value the value to duplicate
     */
    public DoubleScalar(final S value)
    {
        super(value.getDisplayUnit());
        this.si = value.getSI();
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
     * @param targetUnit the unit to convert the value into
     * @return double
     */
    public final double getInUnit(final U targetUnit)
    {
        return ValueUtil.expressAsUnit(getSI(), targetUnit);
    }

    @Override
    public final boolean lt(final S o)
    {
        return this.getSI() < o.getSI();
    }

    @Override
    public final boolean le(final S o)
    {
        return this.getSI() <= o.getSI();
    }

    @Override
    public final boolean gt(final S o)
    {
        return this.getSI() > o.getSI();
    }

    @Override
    public final boolean ge(final S o)
    {
        return this.getSI() >= o.getSI();
    }

    @Override
    public final boolean eq(final S o)
    {
        return this.getSI() == o.getSI();
    }

    @Override
    public final boolean ne(final S o)
    {
        return this.getSI() != o.getSI();
    }

    @Override
    public final boolean lt0()
    {
        return this.getSI() < 0.0;
    }

    @Override
    public final boolean le0()
    {
        return this.getSI() <= 0.0;
    }

    @Override
    public final boolean gt0()
    {
        return this.getSI() > 0.0;
    }

    @Override
    public final boolean ge0()
    {
        return this.getSI() >= 0.0;
    }

    @Override
    public final boolean eq0()
    {
        return this.getSI() == 0.0;
    }

    @Override
    public final boolean ne0()
    {
        return this.getSI() != 0.0;
    }

    @Override
    public final int compareTo(final S o)
    {
        return Double.compare(this.getSI(), o.getSI());
    }

    @Override
    public int intValue()
    {
        return (int) this.getSI();
    }

    @Override
    public long longValue()
    {
        return (long) this.getSI();
    }

    @Override
    public float floatValue()
    {
        return (float) this.getSI();
    }

    @Override
    public double doubleValue()
    {
        return this.getSI();
    }
    
    /**********************************************************************************/
    /********************************* GENERIC METHODS ********************************/
    /**********************************************************************************/

    @Override
    public String toString()
    {
        return toString(getDisplayUnit(), false, true);
    }

    @Override
    public String toString(final U displayUnit)
    {
        return toString(displayUnit, false, true);
    }

    @Override
    public String toString(final boolean verbose, final boolean withUnit)
    {
        return toString(getDisplayUnit(), verbose, withUnit);
    }

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
     * Format this DoubleScalar in SI unit using prefixes when possible. If the value is too small or too large, e-notation and
     * the plain SI unit are used.
     * @return formatted value of this DoubleScalar
     */
    public String toStringSIPrefixed()
    {
        return toStringSIPrefixed(-24, 26);
    }

    /**
     * Format this DoubleScalar in SI unit using prefixes when possible and within the specified size range. If the value is too
     * small or too large, e-notation and the plain SI unit are used.
     * @param smallestPower the smallest exponent value that will be written using an SI prefix
     * @param biggestPower the largest exponent value that will be written using an SI prefix
     * @return formatted value of this DoubleScalar
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

    @Override
    public String toTextualString()
    {
        return toTextualString(getDisplayUnit());
    }

    @Override
    public String toTextualString(final U displayUnit)
    {
        double d = ValueUtil.expressAsUnit(getSI(), displayUnit);
        return format(d) + " " + displayUnit.getLocalizedTextualAbbreviation();
    }

    @Override
    public String toDisplayString()
    {
        return toDisplayString(getDisplayUnit());
    }

    @Override
    public String toDisplayString(final U displayUnit)
    {
        double d = ValueUtil.expressAsUnit(getSI(), displayUnit);
        return format(d) + " " + displayUnit.getLocalizedDisplayAbbreviation();
    }

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
     * @param value the value
     * @param unit the unit in which the value is expressed
     * @return an instantiated DoubleScalar with the value expressed in the unit
     * @param <U> the unit
     * @param <S> the return type
     */
    public static <U extends Unit<U>, S extends DoubleScalar<U, S>> S instantiate(final double value, final U unit)
    {
        return instantiateAnonymous(value, unit);
    }

    /**
     * Instantiate the DoubleScalar based on its unit. Loose check for types on the compiler. This allows the unit to be
     * specified as a Unit&lt;?&gt; type.<br>
     * <b>Note</b> that it is possible to make mistakes with anonymous units.
     * @param value the value
     * @param unit the unit in which the value is expressed
     * @return an instantiated DoubleScalar with the value expressed in the unit
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
            throw new UnitRuntimeException(
                    "Cannot instantiate DoubleScalar of unit " + unit.toString() + ". Reason: " + exception.getMessage());
        }
    }

}
