package org.djunits.vecmat.d3;

import java.util.Objects;

import org.djunits.quantity.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.util.Math2;
import org.djunits.value.Additive;
import org.djunits.value.Scalable;
import org.djunits.value.Value;
import org.djunits.vecmat.Normed;
import org.djunits.vecmat.VecMathOps;
import org.djunits.vecmat.VectorTransposable;

/**
 * Vector3D implements a vector with three real-valued entries. The vector is immutable, except for the display unit, which can
 * be changed. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <V> the vector type
 */
public abstract class Vector3D<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, V extends Vector3D<Q, U, V>>
        implements Value<U, V>, Additive<V>, Scalable<V>, Normed<Q, U>, VecMathOps<Q, U, V>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The x value in si-units. */
    private final double xSi;

    /** The y value in si-units. */
    private final double ySi;

    /** The z value in si-units. */
    private final double zSi;

    /** The display unit. */
    private U displayUnit;

    /**
     * Create a new Vector3D with a unit.
     * @param xSi the x-value expressed in SI or BASE units
     * @param ySi the y-value expressed in SI or BASE units
     * @param zSi the z-value expressed in SI or BASE units
     * @param displayUnit the display unit to use
     */
    protected Vector3D(final double xSi, final double ySi, final double zSi, final U displayUnit)
    {
        this.xSi = xSi;
        this.ySi = ySi;
        this.zSi = zSi;
        this.displayUnit = displayUnit;
    }

    /**
     * Return a new column or row vector with adapted x, y and z values.
     * @param xSiNew the x value to use
     * @param ySiNew the y value to use
     * @param zSiNew the z value to use
     * @return a new column or row vector with adapted x, y and z values
     */
    protected abstract V instantiate(double xSiNew, double ySiNew, double zSiNew);

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
     * @param newUnit the new display unit of this vector
     */
    @SuppressWarnings("unchecked")
    @Override
    public V setDisplayUnit(final U newUnit)
    {
        this.displayUnit = newUnit;
        return (V) this;
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
     * Return the z-value of the vector in SI or BASE units.
     * @return the z-value of the vector in SI or BASE units
     */
    public double zSi()
    {
        return this.zSi;
    }

    /**
     * Return the x-value of the vector as a quantity with the correct unit.
     * @return the x-value of the vector as a quantity with the correct unit
     */
    public Q x()
    {
        return this.displayUnit.ofSi(this.xSi).setDisplayUnit(this.displayUnit);
    }

    /**
     * Return the y-value of the vector as a quantity with the correct unit.
     * @return the y-value of the vector as a quantity with the correct unit
     */
    public Q y()
    {
        return this.displayUnit.ofSi(this.ySi).setDisplayUnit(this.displayUnit);
    }

    /**
     * Return the z-value of the vector as a quantity with the correct unit.
     * @return the z-value of the vector as a quantity with the correct unit
     */
    public Q z()
    {
        return this.displayUnit.ofSi(this.zSi).setDisplayUnit(this.displayUnit);
    }

    @Override
    public V scale(final double factor)
    {
        return instantiate(this.xSi * factor, this.ySi * factor, this.zSi * factor);
    }

    @Override
    public V plus(final V other)
    {
        return instantiate(this.xSi + other.xSi(), this.ySi + other.ySi(), this.zSi + other.zSi());
    }

    @Override
    public V minus(final V other)
    {
        return instantiate(this.xSi - other.xSi(), this.ySi - other.ySi(), this.zSi - other.zSi());
    }

    @Override
    public V negate()
    {
        return instantiate(-this.xSi, -this.ySi, -this.zSi);
    }

    @Override
    public V abs()
    {
        return instantiate(Math.abs(this.xSi), Math.abs(this.ySi), Math.abs(this.zSi));
    }

    @Override
    public Q normL1()
    {
        return this.displayUnit.ofSi((Math.abs(this.xSi) + Math.abs(this.ySi) + Math.abs(this.zSi)) / 3.0)
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normL2()
    {
        return this.displayUnit.ofSi(Math.sqrt(this.xSi * this.xSi + this.ySi * this.ySi + this.zSi * this.zSi))
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLp(final int p)
    {
        return this.displayUnit.ofSi(Math.pow(Math.pow(this.xSi, p) + Math.pow(this.ySi, p) + Math.pow(this.zSi, p), 1.0 / p))
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLinf()
    {
        return this.displayUnit.ofSi(Math2.absmax(this.xSi, this.ySi, this.zSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q mean()
    {
        return this.displayUnit.ofSi((this.xSi + this.ySi + this.zSi) / 3.0).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q min()
    {
        return this.displayUnit.ofSi(Math2.min(this.xSi, this.ySi, this.zSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q max()
    {
        return this.displayUnit.ofSi(Math2.max(this.xSi, this.ySi, this.zSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q mode()
    {
        return max();
    }

    @Override
    public Q median()
    {
        return this.displayUnit.ofSi(Math2.median(this.xSi, this.ySi, this.zSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q sum()
    {
        return this.displayUnit.ofSi(this.xSi + this.ySi + this.zSi).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public V plus(final Q increment)
    {
        return instantiate(this.xSi + increment.si(), this.ySi + increment.si(), this.zSi + increment.si());
    }

    @Override
    public V minus(final Q decrement)
    {
        return instantiate(this.xSi - decrement.si(), this.ySi - decrement.si(), this.zSi - decrement.si());
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
        return Objects.hash(this.xSi, this.ySi, this.zSi);
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
        Vector3D<?, ?, ?> other = (Vector3D<?, ?, ?>) obj;
        return Double.doubleToLongBits(this.xSi) == Double.doubleToLongBits(other.xSi)
                && Double.doubleToLongBits(this.ySi) == Double.doubleToLongBits(other.ySi)
                && Double.doubleToLongBits(this.zSi) == Double.doubleToLongBits(other.zSi);
    }

    @Override
    public String toString(final U withUnit)
    {
        var s = new StringBuilder();
        s.append("[");
        s.append(withUnit.fromBaseValue(this.xSi));
        s.append(", ");
        s.append(withUnit.fromBaseValue(this.ySi));
        s.append(", ");
        s.append(withUnit.fromBaseValue(this.zSi));
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
    public static class Col<Q extends Quantity<Q, U>, U extends AbstractUnit<U, Q>> extends Vector3D<Q, U, Col<Q, U>>
            implements VectorTransposable<Row<Q, U>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new 2D column vector with a unit.
         * @param xSi the x-value expressed in SI or BASE units
         * @param ySi the y-value expressed in SI or BASE units
         * @param zSi the z-value expressed in SI or BASE units
         * @param displayUnit the display unit to use
         */
        public Col(final double xSi, final double ySi, final double zSi, final U displayUnit)
        {
            super(xSi, ySi, zSi, displayUnit);
        }

        /**
         * Create a Vector2D column vector without needing generics.
         * @param x the x-value expressed in the display unit
         * @param y the y-value expressed in the display unit
         * @param z the z-value expressed in the display unit
         * @param displayUnit the display unit to use
         * @return a new Vector2D with a unit
         * @param <Q> the quantity type
         * @param <U> the unit type
         */
        public static <Q extends Quantity<Q, U>, U extends AbstractUnit<U, Q>> Vector3D.Col<Q, U> of(final double x,
                final double y, final double z, final U displayUnit)
        {
            return new Vector3D.Col<>(displayUnit.toBaseValue(x), displayUnit.toBaseValue(y), displayUnit.toBaseValue(z),
                    displayUnit);
        }

        @Override
        public boolean isColumnVector()
        {
            return true;
        }

        @Override
        protected Vector3D.Col<Q, U> instantiate(final double xSi, final double ySi, final double zSi)
        {
            return new Vector3D.Col<>(xSi, ySi, zSi, getDisplayUnit());
        }

        @Override
        public Vector3D.Row<Q, U> transpose()
        {
            return new Vector3D.Row<Q, U>(xSi(), ySi(), zSi(), getDisplayUnit());
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
    public static class Row<Q extends Quantity<Q, U>, U extends AbstractUnit<U, Q>> extends Vector3D<Q, U, Row<Q, U>>
            implements VectorTransposable<Col<Q, U>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new 2D row vector with a unit.
         * @param xSi the x-value expressed in SI or BASE units
         * @param ySi the y-value expressed in SI or BASE units
         * @param zSi the z-value expressed in SI or BASE units
         * @param displayUnit the display unit to use
         */
        public Row(final double xSi, final double ySi, final double zSi, final U displayUnit)
        {
            super(xSi, ySi, zSi, displayUnit);
        }

        /**
         * Create a Vector2D row vector without needing generics.
         * @param x the x-value expressed in the display unit
         * @param y the y-value expressed in the display unit
         * @param z the z-value expressed in the display unit
         * @param displayUnit the display unit to use
         * @return a new Vector2D with a unit
         * @param <Q> the quantity type
         * @param <U> the unit type
         */
        public static <Q extends Quantity<Q, U>, U extends AbstractUnit<U, Q>> Vector3D.Row<Q, U> of(final double x,
                final double y, final double z, final U displayUnit)
        {
            return new Vector3D.Row<>(displayUnit.toBaseValue(x), displayUnit.toBaseValue(y), displayUnit.toBaseValue(z),
                    displayUnit);
        }

        @Override
        public boolean isColumnVector()
        {
            return false;
        }

        @Override
        protected Vector3D.Row<Q, U> instantiate(final double xSi, final double ySi, final double zSi)
        {
            return new Vector3D.Row<>(xSi, ySi, zSi, getDisplayUnit());
        }

        @Override
        public Vector3D.Col<Q, U> transpose()
        {
            return new Vector3D.Col<Q, U>(xSi(), ySi(), zSi(), getDisplayUnit());
        }

    }

}
