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

    /**
     * Convert this vector or matrix to a {@link MatrixNxM}. The unerlying data MIGHT be shared between this object and the
     * MatrixNxM.
     * @return a {@code MatrixNxN} with identical SI data and display unit
     */
    public MatrixNxM<Q> asMatrixNxM()
    {
        return new MatrixNxM<Q>(new DenseDoubleDataSi(unsafeSiArray(), rows(), cols()), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this vector or matrix to a {@link QuantityTable}. The underlying data MIGHT be shared between this object and the
     * quantity table.
     * @return a {@code QuantityTable} with identical SI data and display unit
     */
    public QuantityTable<Q> asQuantityTable()
    {
        return new QuantityTable<Q>(new DenseDoubleDataSi(unsafeSiArray(), rows(), cols()), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
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
