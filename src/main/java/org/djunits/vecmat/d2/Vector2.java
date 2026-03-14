package org.djunits.vecmat.d2;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.d1.Vector1;
import org.djunits.vecmat.def.Vector;
import org.djunits.vecmat.operations.VectorTransposable;
import org.djutils.exceptions.Throw;

/**
 * Vector2 implements a vector with two real-valued entries. The vector is immutable, except for the display unit, which can be
 * changed. Many of the method that have been defined already for a generic vector have been re-implemented for efficiency.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <V> the vector type (Row or Col)
 * @param <SI> the vector type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic vector type with generics &lt;?, ?&lt; for Hadamard operations
 */
public abstract class Vector2<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, V extends Vector2<Q, U, V, SI, H>,
        SI extends Vector2<SIQuantity, SIUnit, SI, ?, ?>, H extends Vector2<?, ?, ?, ?, ?>> extends Vector<Q, U, V, SI, H>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The x value in si-units. */
    private final double xSi;

    /** The y value in si-units. */
    private final double ySi;

    /**
     * Create a new Vector2 with a unit.
     * @param xInUnit the x-value expressed in the given unit
     * @param yInUnit the y-value expressed in the given unit
     * @param displayUnit the display unit to use
     */
    protected Vector2(final double xInUnit, final double yInUnit, final U displayUnit)
    {
        super(displayUnit);
        this.xSi = displayUnit.toBaseValue(xInUnit);
        this.ySi = displayUnit.toBaseValue(yInUnit);
    }

    /**
     * Return a column or row vector with x and y values in SI or BASE units.
     * @param xSiNew the x value in SI or BASE units
     * @param ySiNew the y value in SI or BASE units
     * @return a new column or row vector with adapted x and y values
     */
    protected abstract V instantiateSi(double xSiNew, double ySiNew);

    @Override
    public int size()
    {
        return 2;
    }

    @Override
    public double si(final int index) throws IndexOutOfBoundsException
    {
        return switch (index)
        {
            case 0 -> this.xSi;
            case 1 -> this.ySi;
            default -> throw new IndexOutOfBoundsException("Cannot retrieve Vector2[" + index + "]");
        };
    }

    @Override
    public Iterator<Q> iterator()
    {
        final double[] si = new double[] {this.xSi, this.ySi};
        final U frozenDisplayUnit = getDisplayUnit(); // capture once
        return Arrays.stream(si).mapToObj(v -> frozenDisplayUnit.ofSi(v).setDisplayUnit(frozenDisplayUnit)).iterator();
    }

    @Override
    public Q[] getScalarArray()
    {
        final Q qx = x();
        final Class<?> qClass = qx.getClass();
        @SuppressWarnings("unchecked")
        final Q[] out = (Q[]) Array.newInstance(qClass, 2);
        out[0] = qx;
        out[1] = y();
        return out;
    }

    @Override
    public V instantiateSi(final double[] siNew)
    {
        Throw.when(siNew.length != 2, IllegalArgumentException.class, "Size of new data for Vector2 != 2, but %d",
                siNew.length);
        return instantiateSi(siNew[0], siNew[1]);
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
     * Return the contents of the vector as an array.
     * @return the contents of the vector as an array
     */
    @Override
    public double[] si()
    {
        return new double[] {this.xSi, this.ySi};
    }

    /**
     * Return the x-value of the vector as a quantity with the correct unit.
     * @return the x-value of the vector as a quantity with the correct unit
     */
    public Q x()
    {
        return getDisplayUnit().ofSi(this.xSi).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the y-value of the vector as a quantity with the correct unit.
     * @return the y-value of the vector as a quantity with the correct unit
     */
    public Q y()
    {
        return getDisplayUnit().ofSi(this.ySi).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public V scaleBy(final double factor)
    {
        return instantiateSi(this.xSi * factor, this.ySi * factor);
    }

    @Override
    public V add(final V other)
    {
        return instantiateSi(this.xSi + other.xSi(), this.ySi + other.ySi());
    }

    @Override
    public V subtract(final V other)
    {
        return instantiateSi(this.xSi - other.xSi(), this.ySi - other.ySi());
    }

    @Override
    public V negate()
    {
        return instantiateSi(-this.xSi, -this.ySi);
    }

    @Override
    public V abs()
    {
        return instantiateSi(Math.abs(this.xSi), Math.abs(this.ySi));
    }

    @Override
    public Q normL1()
    {
        return getDisplayUnit().ofSi(Math.abs(this.xSi) + Math.abs(this.ySi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normL2()
    {
        return getDisplayUnit().ofSi(Math.sqrt(this.xSi * this.xSi + this.ySi * this.ySi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLp(final int p)
    {
        return getDisplayUnit().ofSi(Math.pow(Math.pow(Math.abs(this.xSi), p) + Math.pow(Math.abs(this.ySi), p), 1.0 / p))
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLinf()
    {
        return getDisplayUnit().ofSi(Math.abs(this.xSi) > Math.abs(this.ySi) ? Math.abs(this.xSi) : Math.abs(this.ySi))
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q mean()
    {
        return getDisplayUnit().ofSi((this.xSi + this.ySi) / 2.0).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q min()
    {
        return this.xSi < this.ySi ? x() : y();
    }

    @Override
    public Q max()
    {
        return this.xSi > this.ySi ? x() : y();
    }

    @Override
    public Q mode()
    {
        return max();
    }

    @Override
    public Q median()
    {
        return mean();
    }

    @Override
    public Q sum()
    {
        return getDisplayUnit().ofSi(this.xSi + this.ySi).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public V add(final Q increment)
    {
        return instantiateSi(this.xSi + increment.si(), this.ySi + increment.si());
    }

    @Override
    public V subtract(final Q decrement)
    {
        return instantiateSi(this.xSi - decrement.si(), this.ySi - decrement.si());
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
    @Override
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
        Vector2<?, ?, ?, ?, ?> other = (Vector2<?, ?, ?, ?, ?>) obj;
        return Double.doubleToLongBits(this.xSi) == Double.doubleToLongBits(other.xSi)
                && Double.doubleToLongBits(this.ySi) == Double.doubleToLongBits(other.ySi);
    }

    @Override
    public String toString(final U withUnit)
    {
        var s = new StringBuilder();
        s.append(isColumnVector() ? "Col" : "Row");
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
     * Vector2.Col implements a column vector with two real-valued entries. The vector is immutable, except for the display
     * unit, which can be changed.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public static class Col<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>>
            extends Vector2<Q, U, Col<Q, U>, Col<SIQuantity, SIUnit>, Col<?, ?>> implements VectorTransposable<Row<Q, U>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new 2 column vector with a unit.
         * @param xInUnit the x-value expressed in the given display unit
         * @param yInUnit the y-value expressed in the given display unit
         * @param displayUnit the display unit to use
         */
        public Col(final double xInUnit, final double yInUnit, final U displayUnit)
        {
            super(xInUnit, yInUnit, displayUnit);
        }

        /**
         * Create a Vector2 column vector without needing generics.
         * @param xInUnit the x-value expressed in the display unit
         * @param yInUnit the y-value expressed in the display unit
         * @param displayUnit the display unit to use
         * @return a new Vector2 with a unit
         * @param <Q> the quantity type
         * @param <U> the unit type
         */
        public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Vector2.Col<Q, U> of(final double xInUnit,
                final double yInUnit, final U displayUnit)
        {
            return new Vector2.Col<>(xInUnit, yInUnit, displayUnit);
        }

        @Override
        public boolean isColumnVector()
        {
            return true;
        }

        @Override
        public int rows()
        {
            return 2;
        }

        @Override
        public int cols()
        {
            return 1;
        }

        @Override
        protected Vector2.Col<Q, U> instantiateSi(final double xSi, final double ySi)
        {
            return new Vector2.Col<>(xSi, ySi, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector2.Col<SIQuantity, SIUnit> instantiateSi(final double[] siNew, final SIUnit siUnit)
        {
            Throw.when(siNew.length != 2, IllegalArgumentException.class, "Size of new data for Vector2 != 2, but %d",
                    siNew.length);
            return new Vector2.Col<SIQuantity, SIUnit>(siNew[0], siNew[1], siUnit);
        }

        @Override
        public double si(final int row, final int col) throws IndexOutOfBoundsException
        {
            checkRow(row);
            checkCol(col);
            return row == 0 ? xSi() : ySi();
        }

        @Override
        public Vector1<Q, U> getRowVector(final int row)
        {
            checkRow(row);
            return new Vector1<Q, U>(si(row, 0), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector1<Q, U> mgetRowVector(final int mRow)
        {
            mcheckRow(mRow);
            return new Vector1<Q, U>(msi(mRow, 1), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector2.Col<Q, U> getColumnVector(final int col)
        {
            checkCol(col);
            return instantiateSi(xSi(), ySi()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector2.Col<Q, U> mgetColumnVector(final int mCol)
        {
            mcheckCol(mCol);
            return instantiateSi(xSi(), ySi()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public double[] getRowSi(final int row)
        {
            checkRow(row);
            return new double[] {si(row, 0)};
        }

        @Override
        public double[] getColumnSi(final int col)
        {
            checkCol(col);
            return new double[] {xSi(), ySi()};
        }

        @Override
        public Vector2.Row<Q, U> transpose()
        {
            return new Vector2.Row<Q, U>(xSi(), ySi(), getDisplayUnit());
        }

        /**
         * Multiply this column vector with a row vector, resulting in a square matrix.
         * @param otherVec the row vector to multiply with
         * @return the resulting matrix from the multiplication
         */
        public Matrix2x2<SIQuantity, SIUnit> multiply(final Vector2.Row<?, ?> otherVec)
        {
            checkMultiply(otherVec);
            double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 2, 1, 2);
            return new Matrix2x2<SIQuantity, SIUnit>(resultData,
                    getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
        }

        @Override
        public Vector2.Col<SIQuantity, SIUnit> multiplyElements(final Quantity<?, ?> quantity)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
            return Vector2.Col.of(xSi() * quantity.si(), ySi() * quantity.si(), siUnit);
        }

        /**
         * Return the vector 'as' a vector with a known quantity, using a unit to express the result in. Throw a Runtime
         * exception when the SI units of this vector and the target vector do not match.
         * @param targetUnit the unit to convert the vector to
         * @return a quantity typed in the target vector class
         * @throws IllegalArgumentException when the units do not match
         * @param <TQ> target quantity type
         * @param <TU> target unit type
         */
        public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Vector2.Col<TQ, TU> as(final TU targetUnit)
                throws IllegalArgumentException
        {
            Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                    "Quantity.as(%s) called, but units do not match: %s <> %s", targetUnit,
                    getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
            return new Vector2.Col<TQ, TU>(xSi(), ySi(), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
        }

    }

    /**
     * Vector2.Row implements a row vector with two real-valued entries. The vector is immutable, except for the display unit,
     * which can be changed.
     * <p>
     * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.
     * See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
     * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
     * @author Alexander Verbraeck
     * @param <Q> the quantity type
     * @param <U> the unit type
     */
    public static class Row<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>>
            extends Vector2<Q, U, Row<Q, U>, Row<SIQuantity, SIUnit>, Row<?, ?>> implements VectorTransposable<Col<Q, U>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new 2 row vector with a unit.
         * @param xInUnit the x-value expressed in the given unit
         * @param yInUnit the y-value expressed in the given unit
         * @param displayUnit the display unit to use
         */
        public Row(final double xInUnit, final double yInUnit, final U displayUnit)
        {
            super(xInUnit, yInUnit, displayUnit);
        }

        /**
         * Create a Vector2 row vector without needing generics.
         * @param xInUnit the x-value expressed in the display unit
         * @param yInUnit the y-value expressed in the display unit
         * @param displayUnit the display unit to use
         * @return a new Vector2 with a unit
         * @param <Q> the quantity type
         * @param <U> the unit type
         */
        public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Vector2.Row<Q, U> of(final double xInUnit,
                final double yInUnit, final U displayUnit)
        {
            return new Vector2.Row<>(xInUnit, yInUnit, displayUnit);
        }

        @Override
        public boolean isColumnVector()
        {
            return false;
        }

        @Override
        public int rows()
        {
            return 1;
        }

        @Override
        public int cols()
        {
            return 2;
        }

        @Override
        protected Vector2.Row<Q, U> instantiateSi(final double xSi, final double ySi)
        {
            return new Vector2.Row<>(xSi, ySi, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector2.Row<SIQuantity, SIUnit> instantiateSi(final double[] siNew, final SIUnit siUnit)
        {
            Throw.when(siNew.length != 2, IllegalArgumentException.class, "Size of new data for Vector2 != 2, but %d",
                    siNew.length);
            return new Vector2.Row<SIQuantity, SIUnit>(siNew[0], siNew[1], siUnit);
        }

        @Override
        public double si(final int row, final int col) throws IndexOutOfBoundsException
        {
            checkRow(row);
            checkCol(col);
            return col == 0 ? xSi() : ySi();
        }

        @Override
        public Vector2.Row<Q, U> getRowVector(final int row)
        {
            checkRow(row);
            return instantiateSi(xSi(), ySi()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector2.Row<Q, U> mgetRowVector(final int mRow)
        {
            mcheckRow(mRow);
            return instantiateSi(xSi(), ySi()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector1<Q, U> getColumnVector(final int col)
        {
            checkCol(col);
            return new Vector1<Q, U>(si(0, col), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector1<Q, U> mgetColumnVector(final int mCol)
        {
            mcheckCol(mCol);
            return new Vector1<Q, U>(msi(1, mCol), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public double[] getRowSi(final int row)
        {
            checkRow(row);
            return new double[] {xSi(), ySi()};
        }

        @Override
        public double[] getColumnSi(final int col)
        {
            checkCol(col);
            return new double[] {si(0, col)};
        }

        @Override
        public Vector2.Col<Q, U> transpose()
        {
            return new Vector2.Col<Q, U>(xSi(), ySi(), getDisplayUnit());
        }

        @Override
        public Vector2.Row<SIQuantity, SIUnit> invertElements()
        {
            return new Vector2.Row<SIQuantity, SIUnit>(1.0 / xSi(), 1.0 / ySi(), getDisplayUnit().siUnit().invert());
        }

        @Override
        public Vector2.Row<SIQuantity, SIUnit> multiplyElements(final Vector2.Row<?, ?> other)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new Vector2.Row<SIQuantity, SIUnit>(xSi() * other.xSi(), ySi() * other.ySi(), siUnit);
        }

        @Override
        public Vector2.Row<SIQuantity, SIUnit> divideElements(final Vector2.Row<?, ?> other)
        {
            SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new Vector2.Row<SIQuantity, SIUnit>(xSi() / other.xSi(), ySi() / other.ySi(), siUnit);
        }

        /**
         * Multiply this row vector with a column vector, resulting in a scalar quantity.
         * @param otherVec the column vector to multiply with
         * @return the resulting matrix from the multiplication
         */
        public SIQuantity multiply(final Vector2.Col<?, ?> otherVec)
        {
            double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 1, 2, 1);
            return new SIQuantity(resultData[0], getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
        }

        /**
         * Multiply this row vector with a matrix, resulting in a column vector.
         * @param otherMat the matrix to multiply with
         * @return the resulting column vector from the multiplication
         */
        public Vector2.Col<SIQuantity, SIUnit> multiply(final Matrix2x2<?, ?> otherMat)
        {
            checkMultiply(otherMat);
            double[] resultData = MatrixMath.multiply(si(), otherMat.si(), 1, 2, 2);
            return new Vector2.Col<SIQuantity, SIUnit>(resultData[0], resultData[1],
                    getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
        }

        @Override
        public Vector2.Row<SIQuantity, SIUnit> multiplyElements(final Quantity<?, ?> quantity)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
            return Vector2.Row.of(xSi() * quantity.si(), ySi() * quantity.si(), siUnit);
        }

        /**
         * Return the vector 'as' a vector with a known quantity, using a unit to express the result in. Throw a Runtime
         * exception when the SI units of this vector and the target vector do not match.
         * @param targetUnit the unit to convert the vector to
         * @return a quantity typed in the target vector class
         * @throws IllegalArgumentException when the units do not match
         * @param <TQ> target quantity type
         * @param <TU> target unit type
         */
        public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Vector2.Row<TQ, TU> as(final TU targetUnit)
                throws IllegalArgumentException
        {
            Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                    "Quantity.as(%s) called, but units do not match: %s <> %s", targetUnit,
                    getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
            return new Vector2.Row<TQ, TU>(xSi(), ySi(), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
        }

    }

}
