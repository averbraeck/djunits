package org.djunits.value.vdouble.scalar.base;

import org.djunits.unit.Unit;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.value.Absolute;
import org.djunits.value.AbstractScalar;
import org.djunits.value.formatter.Format;
import org.djunits.value.util.ValueUtil;

/**
 * The most basic abstract class for the DoubleScalar.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit
 * @param <S> the type
 */
public abstract class AbstractDoubleScalar<U extends Unit<U>, S extends AbstractDoubleScalar<U, S>> extends AbstractScalar<U, S>
        implements DoubleScalarInterface<U, S>
{
    /** */
    private static final long serialVersionUID = 20161015L;

    /** The value, stored in the standard SI unit. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public final double si;

    /**
     * Construct a new AbstractDoubleScalar.
     * @param unit U; the unit
     * @param si double; the si value to store
     */
    AbstractDoubleScalar(final U unit, final double si)
    {
        super(unit);
        this.si = si;
    }

    /** {@inheritDoc} */
    @Override
    public final double getSI()
    {
        return this.si;
    }

    /** {@inheritDoc} */
    @Override
    public final double getInUnit()
    {
        return ValueUtil.expressAsUnit(getSI(), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
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
            buf.append(displayUnit.getDefaultDisplayAbbreviation());
        }
        return buf.toString();
    }

    /**
     * Format this AbstractDoubleScalar in SI unit using prefixes when possible. If the value is too small or too large,
     * e-notation and the plain SI unit are used.
     * @return String; formatted value of this AbstractDoubleScalar
     */
    public String toStringSIPrefixed()
    {
        return toStringSIPrefixed(-24, 26);
    }

    /**
     * Format this AbstractDoubleScalar in SI unit using prefixes when possible and within the specified size range. If the
     * value is too small or too large, e-notation and the plain SI unit are used.
     * @param smallestPower int; the smallest exponent value that will be written using an SI prefix
     * @param biggestPower int; the largest exponent value that will be written using an SI prefix
     * @return String; formatted value of this AbstractDoubleScalar
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
        return d + " " + displayUnit.getDefaultTextualAbbreviation();
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
        return d + " " + displayUnit.getDefaultDisplayAbbreviation();
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
        AbstractDoubleScalar<U, S> other = (AbstractDoubleScalar<U, S>) obj;
        if (!getDisplayUnit().getStandardUnit().equals(other.getDisplayUnit().getStandardUnit()))
            return false;
        if (Double.doubleToLongBits(this.getSI()) != Double.doubleToLongBits(other.getSI()))
            return false;
        return true;
    }

}
