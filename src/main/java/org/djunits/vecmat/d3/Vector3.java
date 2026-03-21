package org.djunits.vecmat.d3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.Math2;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.d1.Vector1;
import org.djunits.vecmat.def.Vector;
import org.djunits.vecmat.operations.VectorTransposable;
import org.djutils.exceptions.Throw;

/**
 * Vector3 implements a vector with three real-valued entries. The vector is immutable, except for the display unit, which can
 * be changed. Many of the method that have been defined already for a generic vector have been re-implemented for efficiency.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <V> the vector type (Col or Row)
 * @param <SI> the vector type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic vector type with generics &lt;?, ?&lt; for Hadamard operations
 */
public abstract class Vector3<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, V extends Vector3<Q, U, V, SI, H>,
        SI extends Vector3<SIQuantity, SIUnit, SI, ?, ?>, H extends Vector3<?, ?, ?, ?, ?>> extends Vector<Q, U, V, SI, H>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The x value in si-units. */
    private final double xSi;

    /** The y value in si-units. */
    private final double ySi;

    /** The z value in si-units. */
    private final double zSi;

    /**
     * Create a new Vector3 with a unit.
     * @param xInUnit the x-value expressed in the display unit
     * @param yInUnit the y-value expressed in the display unit
     * @param zInUnit the z-value expressed in the display unit
     * @param displayUnit the display unit to use
     */
    protected Vector3(final double xInUnit, final double yInUnit, final double zInUnit, final U displayUnit)
    {
        super(displayUnit);
        this.xSi = displayUnit.toBaseValue(xInUnit);
        this.ySi = displayUnit.toBaseValue(yInUnit);
        this.zSi = displayUnit.toBaseValue(zInUnit);
    }

    /**
     * Return a new column or row vector with adapted x, y and z values.
     * @param xSiNew the x value to use
     * @param ySiNew the y value to use
     * @param zSiNew the z value to use
     * @return a new column or row vector with adapted x, y and z values
     */
    protected abstract V instantiateSi(double xSiNew, double ySiNew, double zSiNew);

    @Override
    public V instantiateSi(final double[] siNew)
    {
        Throw.when(siNew.length != 3, IllegalArgumentException.class, "Size of new data for Vector3 != 3, but %d",
                siNew.length);
        return instantiateSi(siNew[0], siNew[1], siNew[2]);
    }

    @Override
    public int size()
    {
        return 3;
    }

    @Override
    public double si(final int index) throws IndexOutOfBoundsException
    {
        return switch (index)
        {
            case 0 -> this.xSi;
            case 1 -> this.ySi;
            case 2 -> this.zSi;
            default -> throw new IndexOutOfBoundsException("Cannot retrieve Vector3[" + index + "]");
        };
    }

    @Override
    public Iterator<Q> iterator()
    {
        final double[] si = new double[] {this.xSi, this.ySi, this.zSi};
        final U frozenDisplayUnit = getDisplayUnit(); // capture once
        return Arrays.stream(si).mapToObj(v -> frozenDisplayUnit.ofSi(v).setDisplayUnit(frozenDisplayUnit)).iterator();
    }

    @Override
    public Q[] getScalarArray()
    {
        final Q qx = x();
        final Class<?> qClass = qx.getClass();
        @SuppressWarnings("unchecked")
        final Q[] out = (Q[]) Array.newInstance(qClass, 3);
        out[0] = qx;
        out[1] = y();
        out[2] = z();
        return out;
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
     * Return the contents of the vector as an array.
     * @return the contents of the vector as an array
     */
    @Override
    public double[] si()
    {
        return new double[] {this.xSi, this.ySi, this.zSi};
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

    /**
     * Return the z-value of the vector as a quantity with the correct unit.
     * @return the z-value of the vector as a quantity with the correct unit
     */
    public Q z()
    {
        return getDisplayUnit().ofSi(this.zSi).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public int nonZeroCount()
    {
        int n = this.xSi == 0.0 ? 0 : 1;
        n += this.ySi == 0.0 ? 0 : 1;
        n += this.zSi == 0.0 ? 0 : 1;
        return n;
    }

    @Override
    public V scaleBy(final double factor)
    {
        return instantiateSi(this.xSi * factor, this.ySi * factor, this.zSi * factor);
    }

    @Override
    public V add(final V other)
    {
        return instantiateSi(this.xSi + other.xSi(), this.ySi + other.ySi(), this.zSi + other.zSi());
    }

    @Override
    public V subtract(final V other)
    {
        return instantiateSi(this.xSi - other.xSi(), this.ySi - other.ySi(), this.zSi - other.zSi());
    }

    @Override
    public V negate()
    {
        return instantiateSi(-this.xSi, -this.ySi, -this.zSi);
    }

    @Override
    public V abs()
    {
        return instantiateSi(Math.abs(this.xSi), Math.abs(this.ySi), Math.abs(this.zSi));
    }

    @Override
    public Q normL1()
    {
        return getDisplayUnit().ofSi(Math.abs(this.xSi) + Math.abs(this.ySi) + Math.abs(this.zSi))
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normL2()
    {
        return getDisplayUnit().ofSi(Math.sqrt(this.xSi * this.xSi + this.ySi * this.ySi + this.zSi * this.zSi))
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLp(final int p)
    {
        return getDisplayUnit().ofSi(Math.pow(
                Math.pow(Math.abs(this.xSi), p) + Math.pow(Math.abs(this.ySi), p) + Math.pow(Math.abs(this.zSi), p), 1.0 / p))
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q normLinf()
    {
        return getDisplayUnit().ofSi(Math2.maxAbs(this.xSi, this.ySi, this.zSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q mean()
    {
        return getDisplayUnit().ofSi((this.xSi + this.ySi + this.zSi) / 3.0).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q min()
    {
        return getDisplayUnit().ofSi(Math2.min(this.xSi, this.ySi, this.zSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q max()
    {
        return getDisplayUnit().ofSi(Math2.max(this.xSi, this.ySi, this.zSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q median()
    {
        return getDisplayUnit().ofSi(Math2.median(this.xSi, this.ySi, this.zSi)).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Q sum()
    {
        return getDisplayUnit().ofSi(this.xSi + this.ySi + this.zSi).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public V add(final Q increment)
    {
        return instantiateSi(this.xSi + increment.si(), this.ySi + increment.si(), this.zSi + increment.si());
    }

    @Override
    public V subtract(final Q decrement)
    {
        return instantiateSi(this.xSi - decrement.si(), this.ySi - decrement.si(), this.zSi - decrement.si());
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
        Vector3<?, ?, ?, ?, ?> other = (Vector3<?, ?, ?, ?, ?>) obj;
        return Double.doubleToLongBits(this.xSi) == Double.doubleToLongBits(other.xSi)
                && Double.doubleToLongBits(this.ySi) == Double.doubleToLongBits(other.ySi)
                && Double.doubleToLongBits(this.zSi) == Double.doubleToLongBits(other.zSi);
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
     * Vector3.Col implements a column vector with three real-valued entries. The vector is immutable, except for the display
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
            extends Vector3<Q, U, Col<Q, U>, Col<SIQuantity, SIUnit>, Col<?, ?>> implements VectorTransposable<Row<Q, U>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new 3 column vector with a unit.
         * @param xInUnit the x-value expressed in the display unit
         * @param yInUnit the y-value expressed in the display unit
         * @param zInUnit the z-value expressed in the display unit
         * @param displayUnit the display unit to use
         */
        public Col(final double xInUnit, final double yInUnit, final double zInUnit, final U displayUnit)
        {
            super(xInUnit, yInUnit, zInUnit, displayUnit);
        }

        /**
         * Create a Vector3 column vector without needing generics.
         * @param xInUnit the x-value expressed in the display unit
         * @param yInUnit the y-value expressed in the display unit
         * @param zInUnit the z-value expressed in the display unit
         * @param displayUnit the display unit to use
         * @return a new Vector3 with a unit
         * @param <Q> the quantity type
         * @param <U> the unit type
         */
        public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Vector3.Col<Q, U> of(final double xInUnit,
                final double yInUnit, final double zInUnit, final U displayUnit)
        {
            return new Vector3.Col<>(xInUnit, yInUnit, zInUnit, displayUnit);
        }

        @Override
        public boolean isColumnVector()
        {
            return true;
        }

        @Override
        public int rows()
        {
            return 3;
        }

        @Override
        public int cols()
        {
            return 1;
        }

        @Override
        protected Vector3.Col<Q, U> instantiateSi(final double xSi, final double ySi, final double zSi)
        {
            return new Vector3.Col<>(xSi, ySi, zSi, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector3.Col<SIQuantity, SIUnit> instantiateSi(final double[] siNew, final SIUnit siUnit)
        {
            Throw.when(siNew.length != 3, IllegalArgumentException.class, "Size of new data for Vector2 != 2, but %d",
                    siNew.length);
            return new Vector3.Col<SIQuantity, SIUnit>(siNew[0], siNew[1], siNew[2], siUnit);
        }

        @Override
        public double si(final int row, final int col) throws IndexOutOfBoundsException
        {
            checkRow(row);
            checkCol(col);
            return row == 0 ? xSi() : row == 1 ? ySi() : zSi();
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
        public Vector3.Col<Q, U> getColumnVector(final int col)
        {
            checkCol(col);
            return instantiateSi(xSi(), ySi(), zSi()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector3.Col<Q, U> mgetColumnVector(final int mCol)
        {
            mcheckCol(mCol);
            return instantiateSi(xSi(), ySi(), zSi()).setDisplayUnit(getDisplayUnit());
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
            return new double[] {xSi(), ySi(), zSi()};
        }

        @Override
        public Vector3.Row<Q, U> transpose()
        {
            return new Vector3.Row<Q, U>(xSi(), ySi(), zSi(), getDisplayUnit());
        }

        @Override
        public Vector3.Col<SIQuantity, SIUnit> invertEntries()
        {
            return new Vector3.Col<SIQuantity, SIUnit>(1.0 / xSi(), 1.0 / ySi(), 1.0 / zSi(),
                    getDisplayUnit().siUnit().invert());
        }

        @Override
        public Vector3.Col<SIQuantity, SIUnit> multiplyEntries(final Vector3.Col<?, ?> other)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new Vector3.Col<SIQuantity, SIUnit>(xSi() * other.xSi(), ySi() * other.ySi(), zSi() * other.zSi(), siUnit);
        }

        @Override
        public Vector3.Col<SIQuantity, SIUnit> divideEntries(final Vector3.Col<?, ?> other)
        {
            SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new Vector3.Col<SIQuantity, SIUnit>(xSi() / other.xSi(), ySi() / other.ySi(), zSi() / other.zSi(), siUnit);
        }

        /**
         * Multiply this column vector with a row vector, resulting in a square matrix.
         * @param otherVec the row vector to multiply with
         * @return the resulting matrix from the multiplication
         */
        public Matrix3x3<SIQuantity, SIUnit> multiply(final Vector3.Row<?, ?> otherVec)
        {
            checkMultiply(otherVec);
            double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 3, 1, 3);
            return new Matrix3x3<SIQuantity, SIUnit>(resultData,
                    getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
        }

        @Override
        public Vector3.Col<SIQuantity, SIUnit> multiplyEntries(final Quantity<?, ?> quantity)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
            return new Vector3.Col<SIQuantity, SIUnit>(xSi() * quantity.si(), ySi() * quantity.si(), zSi() * quantity.si(),
                    siUnit);
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
        public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Vector3.Col<TQ, TU> as(final TU targetUnit)
                throws IllegalArgumentException
        {
            Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                    "Quantity.as(%s) called, but units do not match: %s <> %s", targetUnit,
                    getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
            return new Vector3.Col<TQ, TU>(xSi(), ySi(), zSi(), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
        }

    }

    /**
     * Vector3.Row implements a row vector with three real-valued entries. The vector is immutable, except for the display unit,
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
            extends Vector3<Q, U, Row<Q, U>, Row<SIQuantity, SIUnit>, Row<?, ?>> implements VectorTransposable<Col<Q, U>>
    {
        /** */
        private static final long serialVersionUID = 600L;

        /**
         * Create a new 3 row vector with a unit.
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
         * Create a Vector3 row vector without needing generics.
         * @param xInUnit the x-value expressed in the display unit
         * @param yInUnit the y-value expressed in the display unit
         * @param zInUnit the z-value expressed in the display unit
         * @param displayUnit the display unit to use
         * @return a new Vector3 with a unit
         * @param <Q> the quantity type
         * @param <U> the unit type
         */
        public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> Vector3.Row<Q, U> of(final double xInUnit,
                final double yInUnit, final double zInUnit, final U displayUnit)
        {
            return new Vector3.Row<>(xInUnit, yInUnit, zInUnit, displayUnit);
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
            return 3;
        }

        @Override
        protected Vector3.Row<Q, U> instantiateSi(final double xSi, final double ySi, final double zSi)
        {
            return new Vector3.Row<>(xSi, ySi, zSi, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector3.Row<SIQuantity, SIUnit> instantiateSi(final double[] siNew, final SIUnit siUnit)
        {
            Throw.when(siNew.length != 3, IllegalArgumentException.class, "Size of new data for Vector2 != 2, but %d",
                    siNew.length);
            return new Vector3.Row<SIQuantity, SIUnit>(siNew[0], siNew[1], siNew[2], siUnit);
        }

        @Override
        public double si(final int row, final int col) throws IndexOutOfBoundsException
        {
            checkRow(row);
            checkCol(col);
            return col == 0 ? xSi() : col == 1 ? ySi() : zSi();
        }

        @Override
        public Vector3.Row<Q, U> getRowVector(final int row)
        {
            checkRow(row);
            return instantiateSi(xSi(), ySi(), zSi()).setDisplayUnit(getDisplayUnit());
        }

        @Override
        public Vector3.Row<Q, U> mgetRowVector(final int mRow)
        {
            mcheckRow(mRow);
            return instantiateSi(xSi(), ySi(), zSi()).setDisplayUnit(getDisplayUnit());
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
            return new double[] {xSi(), ySi(), zSi()};
        }

        @Override
        public double[] getColumnSi(final int col)
        {
            checkCol(col);
            return new double[] {si(0, col)};
        }

        @Override
        public Vector3.Col<Q, U> transpose()
        {
            return new Vector3.Col<Q, U>(xSi(), ySi(), zSi(), getDisplayUnit());
        }

        @Override
        public Vector3.Row<SIQuantity, SIUnit> invertEntries()
        {
            return new Vector3.Row<SIQuantity, SIUnit>(1.0 / xSi(), 1.0 / ySi(), 1.0 / zSi(),
                    getDisplayUnit().siUnit().invert());
        }

        @Override
        public Vector3.Row<SIQuantity, SIUnit> multiplyEntries(final Vector3.Row<?, ?> other)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new Vector3.Row<SIQuantity, SIUnit>(xSi() * other.xSi(), ySi() * other.ySi(), zSi() * other.zSi(), siUnit);
        }

        @Override
        public Vector3.Row<SIQuantity, SIUnit> divideEntries(final Vector3.Row<?, ?> other)
        {
            SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
            return new Vector3.Row<SIQuantity, SIUnit>(xSi() / other.xSi(), ySi() / other.ySi(), zSi() / other.zSi(), siUnit);
        }

        /**
         * Multiply this row vector with a column vector, resulting in a scalar quantity.
         * @param otherVec the column vector to multiply with
         * @return the resulting matrix from the multiplication
         */
        public SIQuantity multiply(final Vector3.Col<?, ?> otherVec)
        {
            double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 1, 3, 1);
            return new SIQuantity(resultData[0], getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
        }

        /**
         * Multiply this row vector with a matrix, resulting in a column vector.
         * @param otherMat the matrix to multiply with
         * @return the resulting column vector from the multiplication
         */
        public Vector3.Col<SIQuantity, SIUnit> multiply(final Matrix3x3<?, ?> otherMat)
        {
            checkMultiply(otherMat);
            double[] resultData = MatrixMath.multiply(si(), otherMat.si(), 1, 3, 3);
            return new Vector3.Col<SIQuantity, SIUnit>(resultData[0], resultData[1], resultData[2],
                    getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
        }

        @Override
        public Vector3.Row<SIQuantity, SIUnit> multiplyEntries(final Quantity<?, ?> quantity)
        {
            SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
            return new Vector3.Row<SIQuantity, SIUnit>(xSi() * quantity.si(), ySi() * quantity.si(), zSi() * quantity.si(),
                    siUnit);
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
        public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Vector3.Row<TQ, TU> as(final TU targetUnit)
                throws IllegalArgumentException
        {
            Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                    "Quantity.as(%s) called, but units do not match: %s <> %s", targetUnit,
                    getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
            return new Vector3.Row<TQ, TU>(xSi(), ySi(), zSi(), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
        }

    }

}
