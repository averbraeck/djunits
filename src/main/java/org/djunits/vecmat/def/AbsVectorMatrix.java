package org.djunits.vecmat.def;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.AbstractReference;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.util.ArrayMath;
import org.djunits.value.Value;
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
    private final AbstractReference<?, A, Q> reference;

    /**
     * Create a new vector or matrix of absolute values with a reference point.
     * @param relativeVecMat the underlying relative vector or matrix with SI values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsVectorMatrix(final VMQ relativeVecMat, final AbstractReference<?, A, Q> reference)
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
    public VMA instantiateSi(final double[] siNew, final AbstractReference<?, A, Q> newReference)
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
    public abstract VMA instantiate(VMQ relVecMat, AbstractReference<?, A, Q> newReference);

    /**
     * Return the underlying relative vector or matrix with SI values relative to the reference point.
     * @return the underlying relative vector or matrix with SI values relative to the reference point
     */
    protected VMQ getRelativeVecMat()
    {
        return this.relativeVecMat;
    }

    /**
     * Return the reference point for the absolute values.
     * @return the reference point for the absolute values
     */
    public AbstractReference<?, A, Q> getReference()
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
        return instantiateSi(ArrayMath.add(this.relativeVecMat.si(), increment.si()), getReference())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return a vector or matrix with entries that contain the value minus the decrement.
     * @param decrement the quantity by which to decrease the values of the vector or matrix
     * @return a vector or matrix with entries that are decremented by the given quantity
     */
    public VMA subtract(final Q decrement)
    {
        return instantiateSi(ArrayMath.add(this.relativeVecMat.si(), -decrement.si()), getReference())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return a vector or matrix with entries that contain the sum of the element and the increment.
     * @param other the vector or matrix that contains the values by which to increase the values of the vector or matrix
     * @return a vector or matrix with entries that are decremented by the given quantity
     */
    public VMA add(final VMQ other)
    {
        return instantiateSi(ArrayMath.add(this.relativeVecMat.si(), other.si()), getReference())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return a vector or matrix with entries that contain the value minus the decrement.
     * @param other the vector or matrix that contains the values by which to decrease the values of the vector or matrix
     * @return a vector or matrix with entries that are decremented by the given vector or matrix values
     */
    public VMA subtract(final VMQ other)
    {
        return instantiateSi(ArrayMath.subtract(this.relativeVecMat.si(), other.si()), getReference())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return a relative vector or matrix with entries that contain the absolute value minus the absolute decrement.
     * @param other the vector or matrix that contains the values by which to decrease the values of the vector or matrix
     * @return a vector or matrix with entries that are decremented by the given vector or matrix values
     */
    public VMQ subtract(final VMA other)
    {
        return this.relativeVecMat.instantiateSi(ArrayMath.subtract(this.relativeVecMat.si(), other.getRelativeVecMat().si()))
                .setDisplayUnit(getDisplayUnit());
    }

}
