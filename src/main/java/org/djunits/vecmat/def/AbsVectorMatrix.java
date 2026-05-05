package org.djunits.vecmat.def;

import java.util.Objects;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Unit;
import org.djunits.util.ArrayMath;
import org.djunits.value.Value;
import org.djunits.vecmat.d1.AbsMatrix1x1;
import org.djunits.vecmat.d1.AbsVector1;
import org.djunits.vecmat.d1.Matrix1x1;
import org.djunits.vecmat.d1.Vector1;
import org.djunits.vecmat.d2.AbsMatrix2x2;
import org.djunits.vecmat.d2.AbsVector2;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.AbsMatrix3x3;
import org.djunits.vecmat.d3.AbsVector3;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.dn.AbsMatrixNxN;
import org.djunits.vecmat.dn.AbsVectorN;
import org.djunits.vecmat.dn.MatrixNxN;
import org.djunits.vecmat.dn.VectorN;
import org.djunits.vecmat.dnxm.AbsMatrixNxM;
import org.djunits.vecmat.dnxm.MatrixNxM;
import org.djunits.vecmat.table.AbsQuantityTable;
import org.djunits.vecmat.table.QuantityTable;
import org.djutils.exceptions.Throw;

/**
 * AbsVectorMatrix contains a number of standard operations on vectors and matrices of absolute quantities.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the quantity type
 * @param <VMA> the absolute vector or matrix type
 * @param <VMQ> the relative vector or matrix type
 * @param <VMAT> the type of the transposed version of the absolute vector or matrix
 */
public abstract class AbsVectorMatrix<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>,
        VMA extends AbsVectorMatrix<A, Q, VMA, VMQ, VMAT>, VMQ extends VectorMatrix<Q, VMQ, ?, ?, ?>,
        VMAT extends AbsVectorMatrix<A, Q, VMAT, ?, VMA>> implements Value<VMA, Q>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The underlying relative vector or matrix with SI values relative to the reference point. */
    private final VMQ relativeVecMat;

    /** The reference point for the absolute values. */
    private final Reference<?, A, Q> reference;

    /**
     * Create a new vector or matrix of absolute values with a reference point.
     * @param relativeVecMat the underlying relative vector or matrix with SI values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsVectorMatrix(final VMQ relativeVecMat, final Reference<?, A, Q> reference)
    {
        Throw.whenNull(relativeVecMat, "relativeVecMat");
        Throw.whenNull(reference, "reference");
        this.relativeVecMat = relativeVecMat;
        this.reference = reference;
    }

    @Override
    public Unit<?, Q> getDisplayUnit()
    {
        return this.relativeVecMat.getDisplayUnit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public VMA setDisplayUnit(final Unit<?, Q> newUnit)
    {
        this.relativeVecMat.setDisplayUnit(newUnit);
        return (VMA) this;
    }

    /**
     * Return the number of rows.
     * @return the number of rows
     */
    public int rows()
    {
        return this.relativeVecMat.rows();
    }

    /**
     * Return the number of columns.
     * @return the number of columns
     */
    public int cols()
    {
        return this.relativeVecMat.cols();
    }

    /**
     * Return a new vector or matrix with the given SI or BASE values for the relative vector or matrix.
     * @param siNew the values for the new vector or matrix in row-major format
     * @param newReference the reference point for the relative SI values
     * @return a new matrix with the provided SI or BASE values
     */
    public VMA instantiateSi(final double[] siNew, final Reference<?, A, Q> newReference)
    {
        VMQ rel = this.relativeVecMat.instantiateSi(siNew).setDisplayUnit(getDisplayUnit());
        return instantiate(rel, newReference);
    }

    /**
     * Return a new vector or matrix with the given SI or BASE values for the relative vector or matrix.
     * @param relVecMat the underlying relative vector or matrix with SI values relative to the reference point
     * @param newReference the reference point for the relative SI values
     * @return a new matrix with the provided SI or BASE values
     */
    public abstract VMA instantiate(VMQ relVecMat, Reference<?, A, Q> newReference);

    /**
     * Return the underlying relative vector or matrix with SI values relative to the reference point.
     * @return the underlying relative vector or matrix with SI values relative to the reference point
     */
    public VMQ getRelativeVecMat()
    {
        return this.relativeVecMat;
    }

    /**
     * Return the reference point for the absolute values.
     * @return the reference point for the absolute values
     */
    public Reference<?, A, Q> getReference()
    {
        return this.reference;
    }

    /**
     * Return a transposed absolute vector or matrix, where rows and columns have been swapped.
     * @return a transposed absolute vector or matrix, where rows and columns have been swapped
     */
    public abstract VMAT transpose();

    @Override
    public boolean isRelative()
    {
        return false;
    }

    /**
     * Check if the 0-based row is within bounds.
     * @param row the 0-based row to check
     * @throws IndexOutOfBoundsException when row is out of bounds
     */
    protected void checkRow(final int row)
    {
        Throw.when(row < 0 || row >= rows(), IndexOutOfBoundsException.class, "Row %d out of bounds [0..%d]", row, rows() - 1);
    }

    /**
     * Check if the 0-based column is within bounds.
     * @param col the 0-based column to check
     * @throws IndexOutOfBoundsException when column is out of bounds
     */
    protected void checkCol(final int col)
    {
        Throw.when(col < 0 || col >= cols(), IndexOutOfBoundsException.class, "Column %d out of bounds [0..%d]", col,
                cols() - 1);
    }

    /**
     * Check if the 1-based row is within bounds.
     * @param mRow the 1-based row to check
     * @throws IndexOutOfBoundsException when row is out of bounds
     */
    protected void mcheckRow(final int mRow)
    {
        Throw.when(mRow < 1 || mRow > rows(), IndexOutOfBoundsException.class, "Row %d out of bounds [1..%d]", mRow, rows());
    }

    /**
     * Check if the 1-based column is within bounds.
     * @param mCol the 1-based column to check
     * @throws IndexOutOfBoundsException when column is out of bounds
     */
    protected void mcheckCol(final int mCol)
    {
        Throw.when(mCol < 1 || mCol > cols(), IndexOutOfBoundsException.class, "Column %d out of bounds [1..%d]", mCol, cols());
    }

    /**
     * Return the minimum value of the entries of the vector or matrix.
     * @return the minimum value of the entries of the vector or matrix
     */
    public A min()
    {
        return getReference().instantiate(getDisplayUnit().ofSi(this.relativeVecMat.min().si()))
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the maximum value of the entries of the vector or matrix.
     * @return the maximum value of the entries of the vector or matrix
     */
    public A max()
    {
        return getReference().instantiate(getDisplayUnit().ofSi(this.relativeVecMat.max().si()))
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the median value of the entries of the vector or matrix.
     * @return the median value of the entries of the vector or matrix
     */
    public A median()
    {
        return getReference().instantiate(getDisplayUnit().ofSi(this.relativeVecMat.median().si()))
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return a vector or matrix with entries that contain the sum of the element and the increment.
     * @param increment the quantity by which to increase the values of the vector or matrix
     * @return a vector or matrix with entries that are incremented by the given quantity
     */
    public VMA add(final Q increment)
    {
        return instantiateSi(ArrayMath.add(this.relativeVecMat.unsafeSiArray(), increment.si()), getReference())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return a vector or matrix with entries that contain the value minus the decrement.
     * @param decrement the quantity by which to decrease the values of the vector or matrix
     * @return a vector or matrix with entries that are decremented by the given quantity
     */
    public VMA subtract(final Q decrement)
    {
        return instantiateSi(ArrayMath.add(this.relativeVecMat.unsafeSiArray(), -decrement.si()), getReference())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return a vector or matrix with entries that contain the sum of the element and the increment.
     * @param other the vector or matrix that contains the values by which to increase the values of the vector or matrix
     * @return a vector or matrix with entries that are decremented by the given quantity
     */
    public VMA add(final VMQ other)
    {
        return instantiateSi(ArrayMath.add(this.relativeVecMat.unsafeSiArray(), other.unsafeSiArray()), getReference())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return a vector or matrix with entries that contain the value minus the decrement.
     * @param other the vector or matrix that contains the values by which to decrease the values of the vector or matrix
     * @return a vector or matrix with entries that are decremented by the given vector or matrix values
     */
    public VMA subtract(final VMQ other)
    {
        return instantiateSi(ArrayMath.subtract(this.relativeVecMat.unsafeSiArray(), other.unsafeSiArray()), getReference())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return a relative vector or matrix with entries that contain the absolute value minus the absolute decrement.
     * @param other the vector or matrix that contains the values by which to decrease the values of the vector or matrix
     * @return a vector or matrix with entries that are decremented by the given vector or matrix values
     */
    public VMQ subtract(final VMA other)
    {
        return this.relativeVecMat
                .instantiateSi(
                        ArrayMath.subtract(this.relativeVecMat.unsafeSiArray(), other.getRelativeVecMat().unsafeSiArray()))
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return a relative vector or matrix with entries that contain the absolute value minus the absolute decrement.
     * @param decrement the absolute quantity by which to decrease the values of the vector or matrix
     * @return a vector or matrix with entries that are decremented by the given decrement
     */
    public VMQ subtract(final A decrement)
    {
        return this.relativeVecMat.instantiateSi(ArrayMath.add(this.relativeVecMat.unsafeSiArray(), -decrement.si()))
                .setDisplayUnit(getDisplayUnit());
    }

    // ------------------------------------ AS() METHODS ------------------------------------

    /**
     * Convert this absolute vector or matrix to a {@link AbsMatrixNxM}. The underlying data MIGHT be shared between this object
     * and the new AbsMatrixNxM.
     * @return a {@code AbsMatrixNxN} with identical SI data, display unit, and reference point
     */
    public AbsMatrixNxM<A, Q> asAbsMatrixNxM()
    {
        return new AbsMatrixNxM<A, Q>(MatrixNxM.ofSi(getRelativeVecMat().unsafeSiArray(), rows(), cols(), getDisplayUnit()),
                getReference());
    }

    /**
     * Convert this absolute vector or matrix to a {@link AbsQuantityTable}. The underlying data MIGHT be shared between this
     * object and the new AbsQuantityTable.
     * @return a {@code AbsQuantityTable} with identical SI data, display unit, and reference point
     */
    public AbsQuantityTable<A, Q> asAbsQuantityTable()
    {
        return new AbsQuantityTable<A, Q>(
                QuantityTable.ofSi(getRelativeVecMat().unsafeSiArray(), rows(), cols(), getDisplayUnit()), getReference());
    }

    /**
     * Return this absolute vector, matrix or table as a 1-element column vector. Shape must be 1 x 1.
     * @return a {@code AbsVector1} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 1
     */
    public AbsVector1<A, Q> asAbsVector1()
    {
        Throw.when(rows() != 1 || cols() != 1, IllegalStateException.class, "Matrix is not 1x1");
        final double[] data = getRelativeVecMat().unsafeSiArray();
        return new AbsVector1<A, Q>(new Vector1<Q>(data[0], getDisplayUnit()), getReference());
    }

    /**
     * Return this absolute vector, matrix or table as a 2-element column vector. Shape must be 2 x 1.
     * @return a {@code AbsVector2.Col} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 2 x 1
     */
    public AbsVector2.Col<A, Q> asAbsVector2Col()
    {
        Throw.when(rows() != 2 || cols() != 1, IllegalStateException.class, "Matrix is not 2x1");
        final double[] data = getRelativeVecMat().unsafeSiArray();
        return new AbsVector2.Col<A, Q>(new Vector2.Col<Q>(data[0], data[1], getDisplayUnit()), getReference());
    }

    /**
     * Return this absolute vector, matrix or table as a 3-element column vector. Shape must be 3 x 1.
     * @return a {@code AbsVector3.Col} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 3 x 1
     */
    public AbsVector3.Col<A, Q> asAbsVector3Col()
    {
        Throw.when(rows() != 3 || cols() != 1, IllegalStateException.class, "Matrix is not 3x1");
        final double[] data = getRelativeVecMat().unsafeSiArray();
        return new AbsVector3.Col<A, Q>(new Vector3.Col<Q>(data[0], data[1], data[2], getDisplayUnit()), getReference());
    }

    /**
     * Convert this absolute vector, matrix or table to an N-element column vector. Shape must be N x 1. The underlying data
     * MIGHT be shared between this object and the AbsVectorN.Col.
     * @return a {@code AbsVectorN.Col} with identical SI data and display unit
     * @throws IllegalStateException if {@code cols() != 1}
     */
    public AbsVectorN.Col<A, Q> asAbsVectorNCol()
    {
        Throw.when(cols() != 1, IllegalStateException.class, "Matrix is not Nx1");
        return new AbsVectorN.Col<A, Q>(VectorN.Col.ofSi(getRelativeVecMat().unsafeSiArray(), getDisplayUnit()),
                getReference());
    }

    /**
     * Return this absolute vector, matrix or table as a 2-element row vector. Shape must be 1 x 2.
     * @return a {@code AbsVector2.Row} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 2
     */
    public AbsVector2.Row<A, Q> asAbsVector2Row()
    {
        Throw.when(rows() != 1 || cols() != 2, IllegalStateException.class, "Matrix is not 1x2");
        final double[] data = getRelativeVecMat().unsafeSiArray();
        return new AbsVector2.Row<A, Q>(new Vector2.Row<Q>(data[0], data[1], getDisplayUnit()), getReference());
    }

    /**
     * Return this absolute vector, matrix or table as a 3-element row vector. Shape must be 1 x 3.
     * @return a {@code AbsVector3.Row} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 3
     */
    public AbsVector3.Row<A, Q> asAbsVector3Row()
    {
        Throw.when(rows() != 1 || cols() != 3, IllegalStateException.class, "Matrix is not 1x3");
        final double[] data = getRelativeVecMat().unsafeSiArray();
        return new AbsVector3.Row<A, Q>(new Vector3.Row<Q>(data[0], data[1], data[2], getDisplayUnit()), getReference());
    }

    /**
     * Convert this absolute vector, matrix or table to an N-element row vector. Shape must be 1 x N. The underlying data MIGHT
     * be shared between this object and the AbsVectorN.Row.
     * @return a {@code AbsVectorN.Row} with identical SI data and display unit
     * @throws IllegalStateException if {@code rows() != 1}
     */
    public AbsVectorN.Row<A, Q> asAbsVectorNRow()
    {
        Throw.when(rows() != 1, IllegalStateException.class, "Matrix is not 1xN");
        return new AbsVectorN.Row<A, Q>(VectorN.Row.ofSi(getRelativeVecMat().unsafeSiArray(), getDisplayUnit()),
                getReference());
    }

    /**
     * Convert this absolute vector, matrix or table to a {@link AbsMatrix1x1}. The shape must be 1 x 1.
     * @return a {@code AbsMatrix1x1} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 1 x 1
     */
    public AbsMatrix1x1<A, Q> asAbsMatrix1x1()
    {
        Throw.when(rows() != 1 || cols() != 1, IllegalStateException.class,
                "asAbsMatrix1x1() called, but matrix is no 1x1 but %dx%d", rows(), cols());
        return new AbsMatrix1x1<A, Q>(Matrix1x1.ofSi(getRelativeVecMat().unsafeSiArray(), getDisplayUnit()), getReference());
    }

    /**
     * Convert this absolute vector, matrix or table to a {@link AbsMatrix2x2}. The shape must be 2 x 2.
     * @return a {@code AbsMatrix2x2} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 2 x 2
     */
    public AbsMatrix2x2<A, Q> asAbsMatrix2x2()
    {
        Throw.when(rows() != 2 || cols() != 2, IllegalStateException.class,
                "asAbsMatrix2x2() called, but matrix is no 2x2 but %dx%d", rows(), cols());
        return new AbsMatrix2x2<A, Q>(Matrix2x2.ofSi(getRelativeVecMat().unsafeSiArray(), getDisplayUnit()), getReference());
    }

    /**
     * Convert this absolute vector, matrix or table to a {@link AbsMatrix3x3}. The shape must be 3 x 3.
     * @return a {@code AbsMatrix3x3} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 3 x 3
     */
    public AbsMatrix3x3<A, Q> asAbsMatrix3x3()
    {
        Throw.when(rows() != 3 || cols() != 3, IllegalStateException.class,
                "asAbsMatrix3x3() called, but matrix is no 3x3 but %dx%d", rows(), cols());
        return new AbsMatrix3x3<A, Q>(Matrix3x3.ofSi(getRelativeVecMat().unsafeSiArray(), getDisplayUnit()), getReference());
    }

    /**
     * Convert this absolute vector, matrix or table to a {@link AbsMatrixNxN}. The shape must be square. The underlying data
     * MIGHT be shared between this object and the AbsMatrixNxN.
     * @return a {@code AbsMatrixNxN} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not square
     */
    public AbsMatrixNxN<A, Q> asAbsMatrixNxN()
    {
        Throw.when(rows() != cols(), IllegalStateException.class, "asAbsMatrixNxN() called, but matrix is no square but %dx%d",
                rows(), cols());
        return new AbsMatrixNxN<A, Q>(MatrixNxN.ofSi(getRelativeVecMat().unsafeSiArray(), getDisplayUnit()), getReference());
    }

    // ======================================= hashCode() and equals() ===============================================

    @Override
    public int hashCode()
    {
        return Objects.hash(this.reference, this.relativeVecMat);
    }

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbsVectorMatrix<?, ?, ?, ?, ?> other = (AbsVectorMatrix<?, ?, ?, ?, ?>) obj;
        return Objects.equals(this.reference, other.reference) && Objects.equals(this.relativeVecMat, other.relativeVecMat);
    }

    // -------------------------------- TOSTRING / FORMAT METHODS -------------------------------

    @Override
    public String toString()
    {
        return format();
    }
 
}
