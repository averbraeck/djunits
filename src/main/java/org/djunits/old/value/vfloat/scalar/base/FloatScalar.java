package org.djunits.old.value.vfloat.scalar.base;

import org.djunits.old.unit.Unit;
import org.djunits.old.unit.si.SIPrefixes;
import org.djunits.old.value.Absolute;
import org.djunits.old.value.base.Scalar;
import org.djunits.old.value.formatter.Format;
import org.djunits.old.value.util.ValueUtil;

/**
 * Static methods to create and operate on FloatScalars.
 * <p>
 * Copyright (c) 2015-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 * @param <U> the unit
 * @param <S> the type
 */
public abstract class FloatScalar<U extends Unit<U>, S extends FloatScalar<U, S>> extends Scalar<U, S>
{
    /** */
    private static final long serialVersionUID = 20161015L;

    /** The value, stored in the standard SI unit. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public final float si;

    /**
     * Construct a new FloatScalar.
     * @param value the value to store, expressed in the given unit
     * @param unit the unit
     */
    public FloatScalar(final float value, final U unit)
    {
        super(unit);
        this.si = unit.isBaseSIUnit() ? value : (float) ValueUtil.expressAsSIUnit(value, unit);
    }

    /**
     * Construct a new FloatScalar.
     * @param value the value to duplicate
     */
    public FloatScalar(final S value)
    {
        super(value.getDisplayUnit());
        this.si = value.getSI();
    }

    /**
     * Return the value in the underlying SI unit.
     * @return the value in the underlying SI unit
     */
    public final float getSI()
    {
        return this.si;
    }

    /**
     * Retrieve the value in the original unit.
     * @return float
     */
    public final float getInUnit()
    {
        return (float) ValueUtil.expressAsUnit(getSI(), getDisplayUnit());
    }

    /**
     * Retrieve the value converted into some specified unit.
     * @param targetUnit the unit to convert the value into
     * @return float
     */
    public final float getInUnit(final U targetUnit)
    {
        return (float) ValueUtil.expressAsUnit(getSI(), targetUnit);
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
        return Float.compare(this.getSI(), o.getSI());
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
        return this.getSI();
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
        float d = (float) ValueUtil.expressAsUnit(getSI(), displayUnit);
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
        return toStringSIPrefixed(-30, 32);
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
        if (exponent < -30 || exponent < smallestPower || exponent > 30 + 2 || exponent > biggestPower)
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
        float f = (float) ValueUtil.expressAsUnit(getSI(), displayUnit);
        return format(f) + " " + displayUnit.getLocalizedTextualAbbreviation();
    }

    @Override
    public String toDisplayString()
    {
        return toDisplayString(getDisplayUnit());
    }

    @Override
    public String toDisplayString(final U displayUnit)
    {
        float f = (float) ValueUtil.expressAsUnit(getSI(), displayUnit);
        return format(f) + " " + displayUnit.getLocalizedDisplayAbbreviation();
    }

    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public int hashCode()
    {
        final int prime = 31;
        int result = getDisplayUnit().getStandardUnit().hashCode();
        long temp;
        temp = Float.floatToIntBits(this.getSI());
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
        FloatScalar<U, S> other = (FloatScalar<U, S>) obj;
        if (!getDisplayUnit().getStandardUnit().equals(other.getDisplayUnit().getStandardUnit()))
            return false;
        if (Float.floatToIntBits(this.getSI()) != Float.floatToIntBits(other.getSI()))
            return false;
        return true;
    }

}
