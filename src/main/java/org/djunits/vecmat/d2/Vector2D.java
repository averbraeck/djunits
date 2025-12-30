package org.djunits.vecmat.d2;

import java.util.Objects;

import org.djunits.quantity.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.value.Additive;
import org.djunits.value.Scalable;
import org.djunits.value.Value;
import org.djunits.vecmat.Normed;
import org.djunits.vecmat.VectorTransposable;

/**
 * Vector2D implements a vector with two real-valued entries. The vector is immutable, except for the display unit, which can be
 * changed. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <V> the vector type
 */
public abstract class Vector2D<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, V extends Vector2D<Q, U, V>>
        implements Value<U>, Additive<V>, Scalable<V>, Normed<Q, U>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The x value in si-units. */
    private final double xSi;

    /** The y value in si-units. */
    private final double ySi;

    /** The display unit. */
    private U displayUnit;

    /**
     * Create a new Vector2D with a unit.
     * @param xSi the x-value expressed in SI or BASE units
     * @param ySi the y-value expressed in SI or BASE units
     * @param displayUnit the display unit to use
     * @param columnVector true if this vector is a column vector
     */
    protected Vector2D(final double xSi, final double ySi, final U displayUnit, final boolean columnVector)
    {
        this.xSi = xSi;
        this.ySi = ySi;
        this.displayUnit = displayUnit;
    }

    /**
     * Return a new column or row vector with adapted x and y values.
     * @param xSiNew the x value to use
     * @param ySiNew the y value to use
     * @return a new column or row vector with adapted x and y values
     */
    protected abstract V instantiate(double xSiNew, double ySiNew);

    /**
     * Return the display unit of this vector.
     * @return the display unit of this vector
     */
    @Override
    public U getDisplayUnit()
    {
        return this.displayUnit;
    }

    /**
     * Set a new display unit of this vector.
     * @param displayUnit the new display unit of this vector
     */
    @Override
    public void setDisplayUnit(final U displayUnit)
    {
        this.displayUnit = displayUnit;
    }

    /**
     * Return the x-value of the vector in SI or BASE units.
     * @return the x-value of the vector in SI or BASE units
     */
    public double xSi()
    {
        return this.xSi;
    }

    /**
     * Return the y-value of the vector in SI or BASE units.
     * @return the y-value of the vector in SI or BASE units
     */
    public double ySi()
    {
        return this.ySi;
    }

    /**
     * Return the x-value of the vector as a quantity with the correct unit.
     * @return the x-value of the vector as a quantity with the correct unit
     */
    public Q x()
    {
        Q value = this.displayUnit.ofSi(this.xSi);
        value.setDisplayUnit(this.displayUnit);
        return value;
    }

    /**
     * Return the y-value of the vector as a quantity with the correct unit.
     * @return the y-value of the vector as a quantity with the correct unit
     */
    public Q y()
    {
        Q value = this.displayUnit.ofSi(this.ySi);
        value.setDisplayUnit(this.displayUnit);
        return value;
    }

    @Override
    public V scale(final double factor)
    {
        return instantiate(this.xSi * factor, this.ySi * factor);
    }

    @Override
    public V plus(final V other)
    {
        return instantiate(this.xSi + other.xSi(), this.ySi + other.ySi());
    }

    @Override
    public V minus(final V other)
    {
        return instantiate(this.xSi - other.xSi(), this.ySi - other.ySi());
    }

    @Override
    public V negate()
    {
        return instantiate(-this.xSi, -this.ySi);
    }

    @Override
    public V abs()
    {
        return instantiate(Math.abs(this.xSi), Math.abs(this.ySi));
    }

    @Override
    public Q normL1()
    {
        var norm = this.displayUnit.ofSi((Math.abs(this.xSi) + Math.abs(this.ySi)) / 2.0);
        norm.setDisplayUnit(getDisplayUnit());
        return norm;
    }

    @Override
    public Q normL2()
    {
        var norm = this.displayUnit.ofSi(Math.sqrt(this.xSi * this.xSi + this.ySi * this.ySi));
        norm.setDisplayUnit(getDisplayUnit());
        return norm;
    }

    @Override
    public Q normLp(final int p)
    {
        var norm = this.displayUnit.ofSi(Math.pow(Math.pow(this.xSi, p) + Math.pow(this.ySi, p), 1.0 / p));
        norm.setDisplayUnit(getDisplayUnit());
        return norm;
    }

    @Override
    public Q normLinf()
    {
        var norm = this.displayUnit.ofSi(Math.abs(this.xSi) > Math.abs(this.ySi) ? Math.abs(this.xSi) : Math.abs(this.ySi));
        norm.setDisplayUnit(getDisplayUnit());
        return norm;
    }

    @Override
    public boolean isRelative()
    {
        return x().isRelative();
    }

    /**
     * Return whether this vector is a column vector.
     * @return whether this vector is a column vector
     */
    public abstract boolean isColumnVector();

    @Override
    public int hashCode()
    {
        return Objects.hash(this.xSi, this.ySi);
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2D<?, ?, ?> other = (Vector2D<?, ?, ?>) obj;
        return Double.doubleToLongBits(this.xSi) == Double.doubleToLongBits(other.xSi)
                && Double.doubleToLongBits(this.ySi) == Double.doubleToLongBits(other.ySi);
    }

    @Override
    public String toString(final U withUnit)
    {
        var s = new StringBuilder();
        s.append("[");
        s.append(withUnit.fromBaseValue(this.xSi));
        s.append(", ");
        s.append(withUnit.fromBaseValue(this.ySi));
        s.append("] ");
        s.append(withUnit.getDisplayAbbreviation());
        return s.toString();
    }

    @Override
    public String toString()
    {
        return toString(getDisplayUnit());
    }

    /**
     * Vector2D.Col implements a column vector with two real-valued entries. The vector is immutable, except for the display
     * unit, which can be changed. <br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public static class Col<Q extends Quantity<Q, U>, U extends AbstractUnit<U, Q>> extends Vector2D<Q, U, Col<Q, U>>
            implements VectorTransposable<Row<Q, U>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new 2D column vector with a unit.
         * @param xSi the x-value expressed in SI or BASE units
         * @param ySi the y-value expressed in SI or BASE units
         * @param displayUnit the display unit to use
         */
        public Col(final double xSi, final double ySi, final U displayUnit)
        {
            super(xSi, ySi, displayUnit, true);
        }

        /**
         * Create a Vector2D column vector without needing generics.
         * @param x the x-value expressed in the display unit
         * @param y the y-value expressed in the display unit
         * @param displayUnit the display unit to use
         * @return a new Vector2D with a unit
         * @param <Q> the quantity type
         * @param <U> the unit type
         */
        public static <Q extends Quantity<Q, U>, U extends AbstractUnit<U, Q>> Vector2D.Col<Q, U> of(final double x,
                final double y, final U displayUnit)
        {
            return new Vector2D.Col<>(displayUnit.toBaseValue(x), displayUnit.toBaseValue(y), displayUnit);
        }

        @Override
        public boolean isColumnVector()
        {
            return true;
        }

        @Override
        protected Vector2D.Col<Q, U> instantiate(final double xSi, final double ySi)
        {
            return new Vector2D.Col<>(xSi, ySi, getDisplayUnit());
        }

        @Override
        public Vector2D.Row<Q, U> transpose()
        {
            return new Vector2D.Row<Q, U>(xSi(), ySi(), getDisplayUnit());
        }

    }

    /**
     * Vector2D.Row implements a row vector with two real-valued entries. The vector is immutable, except for the display unit,
     * which can be changed. <br>
     * <br>
     * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
     * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public static class Row<Q extends Quantity<Q, U>, U extends AbstractUnit<U, Q>> extends Vector2D<Q, U, Row<Q, U>>
            implements VectorTransposable<Col<Q, U>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new 2D row vector with a unit.
         * @param xSi the x-value expressed in SI or BASE units
         * @param ySi the y-value expressed in SI or BASE units
         * @param displayUnit the display unit to use
         */
        public Row(final double xSi, final double ySi, final U displayUnit)
        {
            super(xSi, ySi, displayUnit, true);
        }

        /**
         * Create a Vector2D row vector without needing generics.
         * @param x the x-value expressed in the display unit
         * @param y the y-value expressed in the display unit
         * @param displayUnit the display unit to use
         * @return a new Vector2D with a unit
         * @param <Q> the quantity type
         * @param <U> the unit type
         */
        public static <Q extends Quantity<Q, U>, U extends AbstractUnit<U, Q>> Vector2D.Row<Q, U> of(final double x,
                final double y, final U displayUnit)
        {
            return new Vector2D.Row<>(displayUnit.toBaseValue(x), displayUnit.toBaseValue(y), displayUnit);
        }

        @Override
        public boolean isColumnVector()
        {
            return false;
        }

        @Override
        protected Vector2D.Row<Q, U> instantiate(final double xSi, final double ySi)
        {
            return new Vector2D.Row<>(xSi, ySi, getDisplayUnit());
        }

        @Override
        public Vector2D.Col<Q, U> transpose()
        {
            return new Vector2D.Col<Q, U>(xSi(), ySi(), getDisplayUnit());
        }

    }

}
