package org.djunits.vecmat.def;

import org.djunits.quantity.Dimensionless;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.util.Math2;
import org.djunits.value.Additive;
import org.djunits.value.Scalable;
import org.djunits.value.Value;
import org.djunits.vecmat.d1.Matrix1x1;
import org.djunits.vecmat.d1.Vector1;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.dn.MatrixNxN;
import org.djunits.vecmat.dn.VectorN;
import org.djunits.vecmat.dnxm.MatrixNxM;
import org.djunits.vecmat.operations.Hadamard;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djunits.vecmat.table.QuantityTable;
import org.djutils.exceptions.Throw;

/**
 * VectorMatrix contains a number of standard operations on vectors and matrices of relative quantities.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <VM> the 'SELF' vector or matrix type
 * @param <SI> the vector or matrix type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic vector or matrix type with generics &lt;?, ?&lt; for Hadamard operations
 * @param <VMT> the type of the transposed version of the vector or matrix
 */
public abstract class VectorMatrix<Q extends Quantity<Q>, VM extends VectorMatrix<Q, VM, SI, H, VMT>,
        SI extends VectorMatrix<SIQuantity, SI, ?, ?, ?>, H extends VectorMatrix<?, ?, ?, ?, ?>,
        VMT extends VectorMatrix<Q, VMT, ?, ?, VM>> implements Value<VM, Q>, Scalable<VM>, Additive<VM>, Hadamard<H, SI>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The display unit. */
    private Unit<?, Q> displayUnit;

    /**
     * Create a new vector or matrix with a unit.
     * @param displayUnit the display unit to use
     */
    public VectorMatrix(final Unit<?, Q> displayUnit)
    {
        Throw.whenNull(displayUnit, "displayUnit");
        this.displayUnit = displayUnit;
    }

    @Override
    public Unit<?, Q> getDisplayUnit()
    {
        return this.displayUnit;
    }

    @SuppressWarnings("unchecked")
    @Override
    public VM setDisplayUnit(final Unit<?, Q> newUnit)
    {
        Throw.whenNull(newUnit, "newUnit");
        this.displayUnit = newUnit;
        return (VM) this;
    }

    /**
     * Return the number of rows.
     * @return the number of rows
     */
    public abstract int rows();

    /**
     * Return the number of columns.
     * @return the number of columns
     */
    public abstract int cols();

    /**
     * Return a new vector or matrix with the given SI or BASE values.
     * @param siNew the values for the new vector or matrix in row-major format
     * @return a new matrix with the provided SI or BASE values
     */
    public abstract VM instantiateSi(double[] siNew);

    /**
     * Return a new vector or matrix in SI-units with the given SI or BASE values.
     * @param siNew the values for the new vector or matrix in row-major format
     * @param siUnit the new unit for the new vector or matrix
     * @return a new matrix with the provided SI or BASE values
     */
    public abstract SI instantiateSi(double[] siNew, SIUnit siUnit);

    /**
     * Return a row-major array of SI-values for this matrix or vector. This is guaranteed to be a safe copy.
     * @return the row-major array of SI-values (safe copy)
     */
    public abstract double[] getSiArray();

    /**
     * Return a row-major possibly UNSAFE array of SI-values for this matrix or vector. The method might give access to the
     * underlying data structure, so treat the data carefully.
     * @return the row-major array of SI-values (safe copy)
     */
    public abstract double[] unsafeSiArray();

    /**
     * Return a transposed vector or matrix, where rows and columns have been swapped.
     * @return a transposed vector or matrix, where rows and columns have been swapped
     */
    public abstract VMT transpose();

    @Override
    public boolean isRelative()
    {
        return getDisplayUnit().ofSi(0.0).isRelative();
    }

    /**
     * Return the mean value of the entries of the vector or matrix.
     * @return the mean value of the entries of the vector or matrix
     */
    public Q mean()
    {
        double[] siArray = unsafeSiArray();
        return getDisplayUnit().ofSi(Math2.sum(siArray) / siArray.length).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the minimum value of the entries of the vector or matrix.
     * @return the minimum value of the entries of the vector or matrix
     */
    public Q min()
    {
        return getDisplayUnit().ofSi(Math2.min(unsafeSiArray())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the maximum value of the entries of the vector or matrix.
     * @return the maximum value of the entries of the vector or matrix
     */
    public Q max()
    {
        return getDisplayUnit().ofSi(Math2.max(unsafeSiArray())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the median value of the entries of the vector or matrix.
     * @return the median value of the entries of the vector or matrix
     */
    public Q median()
    {
        return getDisplayUnit().ofSi(Math2.median(unsafeSiArray())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the sum of the values of the entries of the vector or matrix.
     * @return the sum of the values of the entries of the vector or matrix
     */
    public Q sum()
    {
        return getDisplayUnit().ofSi(Math2.sum(unsafeSiArray())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the a vector or matrix with entries that contain the sum of the element and the increment.
     * @param increment the quantity by which to increase the values of the vector or matrix
     * @return a vector or matrix with entries that are incremented by the given quantity
     */
    public VM add(final Q increment)
    {
        return instantiateSi(ArrayMath.add(unsafeSiArray(), increment.si()));
    }

    /**
     * Return the a vector or matrix with entries that contain the value minus the decrement.
     * @param decrement the quantity by which to decrease the values of the vector or matrix
     * @return a vector or matrix with entries that are decremented by the given quantity
     */
    public VM subtract(final Q decrement)
    {
        return instantiateSi(ArrayMath.add(unsafeSiArray(), -decrement.si()));
    }

    @Override
    public VM add(final VM other)
    {
        return instantiateSi(ArrayMath.add(unsafeSiArray(), other.unsafeSiArray()));
    }

    @Override
    public VM subtract(final VM other)
    {
        return instantiateSi(ArrayMath.subtract(unsafeSiArray(), other.unsafeSiArray()));
    }

    @Override
    public VM negate()
    {
        return scaleBy(-1.0);
    }

    @Override
    public VM abs()
    {
        return instantiateSi(ArrayMath.abs(unsafeSiArray()));
    }

    @Override
    public VM scaleBy(final double factor)
    {
        return instantiateSi(ArrayMath.scaleBy(unsafeSiArray(), factor));
    }

    /**
     * Return the number of non-zero entries in the vector, matrix or table. Note that NaN and Infinity count as a non-zero
     * element. The value -0.0 counts as 0.0.
     * @return the number of non-zero entries in the vector, matrix or table
     */
    public abstract int nonZeroCount();

    /**
     * Return the number of non-zero entries in the vector, matrix or table. Note that NaN and Infinity count as a non-zero
     * element. The value -0.0 counts as 0.0. the acronym 'nnz' stands for 'number of non-zero entries'.
     * @return the number of non-zero entries in the vector, matrix or table
     */
    public int nnz()
    {
        return nonZeroCount();
    }

    /**
     * Multiply the entries of this vector, matrix or table by the given quantity. This is actually a Hadamard operation, but
     * since it is equivalent to a scaleBy operation, it is included in this interface.
     * @param quantity the scalar quantity to multiply by
     * @return a vector, matrix or table where the entries have been multiplied by the given quantity
     */
    public VM multiplyElements(final Dimensionless quantity)
    {
        return scaleBy(quantity.si());
    }

    /**
     * Multiply the entries of this vector, matrix or table by the given quantity. This is actually a Hadamard operation, but
     * since it is equivalent to a scaleBy operation, it is included in this interface.
     * @param quantity the scalar quantity to multiply by
     * @return a vector, matrix or table where the entries have been multiplied by the given quantity
     */
    public VM divideElements(final Dimensionless quantity)
    {
        return scaleBy(1.0 / quantity.si());
    }

    @Override
    public SI invertEntries()
    {
        return (SI) instantiateSi(ArrayMath.reciprocal(unsafeSiArray()), getDisplayUnit().siUnit().invert());
    }

    @Override
    public SI multiplyEntries(final H other)
    {
        return (SI) instantiateSi(ArrayMath.multiply(unsafeSiArray(), other.unsafeSiArray()),
                getDisplayUnit().siUnit().plus(other.getDisplayUnit().siUnit()));
    }

    @Override
    public SI divideEntries(final H other)
    {
        return (SI) instantiateSi(ArrayMath.divide(unsafeSiArray(), other.unsafeSiArray()),
                getDisplayUnit().siUnit().minus(other.getDisplayUnit().siUnit()));
    }

    @Override
    public SI multiplyEntries(final Quantity<?> quantity)
    {
        return (SI) instantiateSi(ArrayMath.scaleBy(unsafeSiArray(), quantity.si()),
                getDisplayUnit().siUnit().plus(quantity.getDisplayUnit().siUnit()));
    }

    // ------------------------------------ AS() METHODS ------------------------------------

    /**
     * Convert this vector or matrix to a {@link MatrixNxM}. The underlying data MIGHT be shared between this object and the
     * MatrixNxM.
     * @return a {@code MatrixNxN} with identical SI data and display unit
     */
    public MatrixNxM<Q> asMatrixNxM()
    {
        return new MatrixNxM<Q>(new DenseDoubleDataSi(unsafeSiArray(), rows(), cols()), getDisplayUnit());
    }

    /**
     * Convert this vector or matrix to a {@link QuantityTable}. The underlying data MIGHT be shared between this object and the
     * quantity table.
     * @return a {@code QuantityTable} with identical SI data and display unit
     */
    public QuantityTable<Q> asQuantityTable()
    {
        return new QuantityTable<Q>(new DenseDoubleDataSi(unsafeSiArray(), rows(), cols()), getDisplayUnit());
    }

    /**
     * Return this vector, matrix or table as a 1-element column vector. Shape must be 1 x 1.
     * @return a {@code Vector1} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 1
     */
    public Vector1<Q> asVector1()
    {
        Throw.when(rows() != 1 || cols() != 1, IllegalStateException.class, "Matrix is not 1x1");
        final double[] data = unsafeSiArray();
        return new Vector1<Q>(data[0], getDisplayUnit());
    }

    /**
     * Return this vector, matrix or table as a 2-element column vector. Shape must be 2 x 1.
     * @return a {@code Vector2.Col} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 2 x 1
     */
    public Vector2.Col<Q> asVector2Col()
    {
        Throw.when(rows() != 2 || cols() != 1, IllegalStateException.class, "Matrix is not 2x1");
        final double[] data = unsafeSiArray();
        return new Vector2.Col<Q>(data[0], data[1], getDisplayUnit());
    }

    /**
     * Return this vector, matrix or table as a 3-element column vector. Shape must be 3 x 1.
     * @return a {@code Vector3.Col} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 3 x 1
     */
    public Vector3.Col<Q> asVector3Col()
    {
        Throw.when(rows() != 3 || cols() != 1, IllegalStateException.class, "Matrix is not 3x1");
        final double[] data = unsafeSiArray();
        return new Vector3.Col<Q>(data[0], data[1], data[2], getDisplayUnit());
    }

    /**
     * Convert this vector, matrix or table to an N-element column vector. Shape must be N x 1. The underlying data MIGHT be
     * shared between this object and the VectorN.Col.
     * @return a {@code VectorN.Col} with identical SI data and display unit
     * @throws IllegalStateException if {@code cols() != 1}
     */
    public VectorN.Col<Q> asVectorNCol()
    {
        Throw.when(cols() != 1, IllegalStateException.class, "Matrix is not Nx1");
        return VectorN.Col.ofSi(unsafeSiArray(), getDisplayUnit());
    }

    /**
     * Return this vector, matrix or table as a 2-element row vector. Shape must be 1 x 2.
     * @return a {@code Vector2.Row} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 2
     */
    public Vector2.Row<Q> asVector2Row()
    {
        Throw.when(rows() != 1 || cols() != 2, IllegalStateException.class, "Matrix is not 1x2");
        final double[] data = unsafeSiArray();
        return new Vector2.Row<Q>(data[0], data[1], getDisplayUnit());
    }

    /**
     * Return this vector, matrix or table as a 3-element row vector. Shape must be 1 x 3.
     * @return a {@code Vector3.Row} with identical SI data and display unit
     * @throws IllegalStateException if shape is not 1 x 3
     */
    public Vector3.Row<Q> asVector3Row()
    {
        Throw.when(rows() != 1 || cols() != 3, IllegalStateException.class, "Matrix is not 1x3");
        final double[] data = unsafeSiArray();
        return new Vector3.Row<Q>(data[0], data[1], data[2], getDisplayUnit());
    }

    /**
     * Convert this vector, matrix or table to an N-element row vector. Shape must be 1 x N. The underlying data MIGHT be shared
     * between this object and the VectorN.Row.
     * @return a {@code VectorN.Row} with identical SI data and display unit
     * @throws IllegalStateException if {@code rows() != 1}
     */
    public VectorN.Row<Q> asVectorNRow()
    {
        Throw.when(rows() != 1, IllegalStateException.class, "Matrix is not 1xN");
        return VectorN.Row.ofSi(unsafeSiArray(), getDisplayUnit());
    }

    /**
     * Convert this vector, matrix or table to a {@link Matrix1x1}. The shape must be 1 x 1.
     * @return a {@code Matrix1x1} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 1 x 1
     */
    public Matrix1x1<Q> asMatrix1x1()
    {
        Throw.when(rows() != 1 || cols() != 1, IllegalStateException.class,
                "asMatrix1x1() called, but matrix is no 1x1 but %dx%d", rows(), cols());
        return Matrix1x1.ofSi(unsafeSiArray(), getDisplayUnit());
    }

    /**
     * Convert this vector, matrix or table to a {@link Matrix2x2}. The shape must be 2 x 2.
     * @return a {@code Matrix2x2} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 2 x 2
     */
    public Matrix2x2<Q> asMatrix2x2()
    {
        Throw.when(rows() != 2 || cols() != 2, IllegalStateException.class,
                "asMatrix2x2() called, but matrix is no 2x2 but %dx%d", rows(), cols());
        return Matrix2x2.ofSi(unsafeSiArray(), getDisplayUnit());
    }

    /**
     * Convert this vector, matrix or table to a {@link Matrix3x3}. The shape must be 3 x 3.
     * @return a {@code Matrix3x3} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not 3 x 3
     */
    public Matrix3x3<Q> asMatrix3x3()
    {
        Throw.when(rows() != 3 || cols() != 3, IllegalStateException.class,
                "asMatrix3x3() called, but matrix is no 3x3 but %dx%d", rows(), cols());
        return Matrix3x3.ofSi(unsafeSiArray(), getDisplayUnit());
    }

    /**
     * Convert this vector, matrix or table to a {@link MatrixNxN}. The shape must be square. The underlying data MIGHT be
     * shared between this object and the MatrixNxN.
     * @return a {@code MatrixNxN} with identical SI data and display unit
     * @throws IllegalStateException if this matrix is not square
     */
    public MatrixNxN<Q> asMatrixNxN()
    {
        Throw.when(rows() != cols(), IllegalStateException.class, "asMatrixNxN() called, but matrix is no square but %dx%d",
                rows(), cols());
        return new MatrixNxN<Q>(new DenseDoubleDataSi(unsafeSiArray(), rows(), cols()), getDisplayUnit());
    }

    // ------------------------------------ HELPER METHODS ------------------------------------

    /**
     * Check if the multiplication with the other matrix is valid. A valid matrix multiplication is (M x N) x (N x P).
     * @param matrix the other matrix
     * @throws IllegalArgumentException when this.cols() != other.rows()
     */
    protected void checkMultiply(final Matrix<?, ?, ?, ?, ?> matrix)
    {
        Throw.whenNull(matrix, "matrix");
        Throw.when(cols() != matrix.rows(), IllegalArgumentException.class,
                "Matrix multiplication (M x N) x (N x P): this.cols (%d) != matrix.rows (%d)", cols(), matrix.rows());
    }

    /**
     * Check if the multiplication with the other matrix is valid. A valid matrix multiplication is (M x N) x (N x P).
     * @param vector the other matrix
     * @throws IllegalArgumentException when this.cols() != other.rows()
     */
    protected void checkMultiply(final Vector<?, ?, ?, ?, ?> vector)
    {
        Throw.whenNull(vector, "matrix");
        Throw.when(cols() != vector.rows(), IllegalArgumentException.class,
                "Matrix multiplication (M x N) x (N x P): this.cols (%d) != vector.rows (%d)", cols(), vector.rows());
    }

}
