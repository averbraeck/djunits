package org.djunits.vecmat2d;

import java.util.Objects;

import org.djunits.quantity.Quantity;
import org.djunits.unit.AbstractUnit;
import org.djunits.unit.UnitInterface;
import org.djunits.value.Value;

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
        implements Value<U>
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
     * Create a Vector2D without needing generics.
     * @param x the x-value expressed in the display unit
     * @param y the y-value expressed in the display unit
     * @param displayUnit the display unit to use
     * @param columnVector true if this vector is a column vector
     * @return a new Vector2D with a unit
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @param <V> the vector type
     */
    @SuppressWarnings("unchecked")
    public static <Q extends Quantity<Q, U>, U extends AbstractUnit<U, Q>, V extends Vector2D<Q, U, V>> V of(final double x,
            final double y, final U displayUnit, final boolean columnVector)
    {
        if (columnVector)
        {
            return (V) new Vector2D.Col<>(displayUnit.toBaseValue(x), displayUnit.toBaseValue(y), displayUnit);
        }
        return (V) new Vector2D.Row<>(displayUnit.toBaseValue(x), displayUnit.toBaseValue(y), displayUnit);
    }

    /**
     * Create a column Vector2D without needing generics.
     * @param x the x-value expressed in the display unit
     * @param y the y-value expressed in the display unit
     * @param displayUnit the display unit to use
     * @return a new column Vector2D with a unit
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @param <V> the vector type
     */
    public static <Q extends Quantity<Q, U>, U extends AbstractUnit<U, Q>,
            V extends Vector2D<Q, U, V>> Vector2D<Q, U, V> of(final double x, final double y, final U displayUnit)
    {
        return Vector2D.of(x, y, displayUnit, true);
    }

    /**
     * Return a new column or row vector with adapted x and y values.
     * @param xSiNew the x value to use
     * @param ySiNew the y value to use
     * @return a new column or row vector with adapted x and y values
     */
    protected abstract Vector2D<Q, U, V> instantiate(double xSiNew, double ySiNew);

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

    /**
     * Return a transposed version of this vector.
     * @return a transposed version of this vector
     */
    public abstract Vector2D<Q, U, ?> transpose();

    /**
     * Return a scaled version of this vector.
     * @param factor the factor by which to scale the vector
     * @return a scaled version of this vector
     */
    public Vector2D<Q, U, V> scale(final double factor)
    {
        return instantiate(this.xSi * factor, this.ySi * factor);
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
        s.append(",");
        s.append(withUnit.fromBaseValue(this.ySi));
        s.append(withUnit.getDisplayAbbreviation());
        s.append("] ");
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
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new 2D column vector with a unit.
         * @param xSi the x-value expressed in SI or BASE units
         * @param ySi the y-value expressed in SI or BASE units
         * @param displayUnit the display unit to use
         */
        protected Col(final double xSi, final double ySi, final U displayUnit)
        {
            super(xSi, ySi, displayUnit, true);
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
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new 2D row vector with a unit.
         * @param xSi the x-value expressed in SI or BASE units
         * @param ySi the y-value expressed in SI or BASE units
         * @param displayUnit the display unit to use
         */
        protected Row(final double xSi, final double ySi, final U displayUnit)
        {
            super(xSi, ySi, displayUnit, true);
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
