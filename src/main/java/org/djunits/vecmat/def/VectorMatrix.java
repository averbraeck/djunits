package org.djunits.vecmat.def;

import java.lang.reflect.Array;

import org.djunits.formatter.Format;
import org.djunits.quantity.Dimensionless;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
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
 * @param <U> the unit type
 * @param <VM> the 'SELF' vector or matrix type
 * @param <SI> the vector or matrix type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic vector or matrix type with generics &lt;?, ?&lt; for Hadamard operations
 */
public abstract class VectorMatrix<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>,
        VM extends VectorMatrix<Q, U, VM, SI, H>, SI extends VectorMatrix<SIQuantity, SIUnit, SI, ?, ?>,
        H extends VectorMatrix<?, ?, ?, ?, ?>> implements Value<U, VM>, Scalable<VM>, Additive<VM>, Hadamard<H, SI>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The display unit. */
    private U displayUnit;

    /**
     * Create a new vector or matrix with a unit.
     * @param displayUnit the display unit to use
     */
    public VectorMatrix(final U displayUnit)
    {
        Throw.whenNull(displayUnit, "displayUnit");
        this.displayUnit = displayUnit;
    }

    @Override
    public U getDisplayUnit()
    {
        return this.displayUnit;
    }

    @SuppressWarnings("unchecked")
    @Override
    public VM setDisplayUnit(final U newUnit)
    {
        Throw.whenNull(this.displayUnit, "displayUnit");
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
     * Return a row-major array of SI-values for this matrix or vector. Note that this is NOT a safe copy.
     * @return the row-major array of SI-values
     */
    public abstract double[] si();

    /**
     * Return the si-value at position (row, col), where both row and col are 0-based values.
     * @param row the row (0-based)
     * @param col the column (0-based)
     * @return the si-value at position (row, col)
     * @throws IndexOutOfBoundsException when row or col &lt; 0 or larger than number of rows/columns - 1.
     */
    public abstract double si(int row, int col) throws IndexOutOfBoundsException;

    /**
     * Return the si-value at position (row, col), where both row and col are 1-based values.
     * @param mrow the row (1-based)
     * @param mcol the column (1-based)
     * @return the si-value at position (row, col)
     * @throws IndexOutOfBoundsException when row or col &lt; 1 or larger than number of rows/columns.
     */
    public double msi(final int mrow, final int mcol) throws IndexOutOfBoundsException
    {
        return si(mrow - 1, mcol - 1);
    }

    /**
     * Return the quantity at position (row, col), where both row and col are 0-based values.
     * @param row the row (0-based)
     * @param col the column (0-based)
     * @return the quantity at position (row, col)
     * @throws IndexOutOfBoundsException when row or col &lt; 0 or larger than number of rows/columns - 1.
     */
    public Q get(final int row, final int col) throws IndexOutOfBoundsException
    {
        return getDisplayUnit().ofSi(si(row, col)).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the quantity at position (row, col), where both row and col are 1-based values.
     * @param mrow the row (1-based)
     * @param mcol the column (1-based)
     * @return the quantity at position (row, col)
     * @throws IndexOutOfBoundsException when row or col &lt; 1 or larger than number of rows/columns.
     */
    public Q mget(final int mrow, final int mcol) throws IndexOutOfBoundsException
    {
        return getDisplayUnit().ofSi(msi(mrow, mcol)).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the vector or matrix as a 2D array of scalars.
     * @return a new Q[rows()][cols()] array; entry [i-1][j-1] contains get(i, j).
     */
    @SuppressWarnings("unchecked") // cast from Array.newInstance(...) to Q[][]
    public Q[][] getScalarGrid()
    {
        // Determine the runtime type of Q using the first cell; constructors guarantee rows, cols >= 0.
        final Q first = get(0, 0);
        final Class<?> qClass = first.getClass();

        // Allocate a Q[rows()][cols()] array and fill it.
        final Q[][] out = (Q[][]) Array.newInstance(qClass, rows(), cols());
        for (int i = 0; i < rows(); i++)
        {
            for (int j = 0; j < cols(); j++)
            {
                out[i][j] = get(i, j);
            }
        }
        return out;
    }

    /**
     * Return a quantity row (0-based) from the vector or matrix. Note that the specific vector to return can be tightened by
     * the implementing class.
     * @param row the row number to retrieve (0-based)
     * @return a row vector with the data at the given row
     */
    public abstract Vector<Q, U, ?, ?, ?> getRowVector(int row);

    /**
     * Return a quantity row (1-based) from the vector or matrix. Note that the specific vector to return can be tightened by
     * the implementing class.
     * @param mrow the row number to retrieve (1-based)
     * @return a row vector with the data at the given row
     */
    public abstract Vector<Q, U, ?, ?, ?> mgetRowVector(int mrow);

    /**
     * Return a quantity column (0-based) from the vector or matrix. Note that the specific vector to return can be tightened by
     * the implementing class.
     * @param col the column number to retrieve (0-based)
     * @return a column vector with the data at the given column
     */
    public abstract Vector<Q, U, ?, ?, ?> getColumnVector(int col);

    /**
     * Return a quantity column (1-based) from the vector or matrix. Note that the specific vector to return can be tightened by
     * the implementing class.
     * @param mcol the column number to retrieve (1-based)
     * @return a column vector with the data at the given column
     */
    public abstract Vector<Q, U, ?, ?, ?> mgetColumnVector(int mcol);

    /**
     * Return an array with SI-values for the given row (0-based) from the vector or matrix.
     * @param row the row number to retrieve (0-based)
     * @return an array with SI-values with the data at the given row
     */
    public abstract double[] getRowSi(int row);

    /**
     * Return an array with SI-values for the given row (1-based) from the vector or matrix.
     * @param mrow the row number to retrieve (1-based)
     * @return an array with SI-values with the data at the given row
     */
    public double[] mgetRowSi(final int mrow)
    {
        mcheckRow(mrow);
        return getRowSi(mrow - 1);
    }

    /**
     * Return an array with SI-values for the given column (0-based) from the vector or matrix.
     * @param col the column number to retrieve (0-based)
     * @return an array with SI-values with the data at the given column
     */
    public abstract double[] getColumnSi(int col);

    /**
     * Return an array with SI-values for the given column (1-based) from the vector or matrix.
     * @param mcol the column number to retrieve (1-based)
     * @return an array with SI-values with the data at the given column
     */
    public double[] mgetColumnSi(final int mcol)
    {
        mcheckCol(mcol);
        return getColumnSi(mcol - 1);
    }

    @Override
    public boolean isRelative()
    {
        return get(0, 0).isRelative();
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
     * @param mrow the 1-based row to check
     * @throws IndexOutOfBoundsException when row is out of bounds
     */
    protected void mcheckRow(final int mrow)
    {
        Throw.when(mrow < 1 || mrow > rows(), IndexOutOfBoundsException.class, "Row %d out of bounds [1..%d]", mrow, rows());
    }

    /**
     * Check if the 1-based column is within bounds.
     * @param mcol the 1-based column to check
     * @throws IndexOutOfBoundsException when column is out of bounds
     */
    protected void mcheckCol(final int mcol)
    {
        Throw.when(mcol < 1 || mcol > cols(), IndexOutOfBoundsException.class, "Column %d out of bounds [1..%d]", mcol, cols());
    }

    /**
     * Retrieve a row (0-based) from the matrix as an array of scalars.
     * @param row row of the values to retrieve (0-based)
     * @return the row as a Scalar array
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    @SuppressWarnings("unchecked")
    public Q[] getRowScalars(final int row) throws IndexOutOfBoundsException
    {
        checkRow(row);

        // Build a Q[] of length cols() using the runtime class of the first element
        Q first = get(row, 0);
        Q[] out = (Q[]) Array.newInstance(first.getClass(), cols());
        for (int c = 0; c < cols(); c++)
        {
            out[c] = get(row, c);
        }
        return out;
    }

    /**
     * Retrieve a row (1-based) from the matrix as an array of scalars.
     * @param mrow row of the values to retrieve (1-based)
     * @return the row as a Scalar array
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    public Q[] mgetRowScalars(final int mrow) throws IndexOutOfBoundsException
    {
        mcheckRow(mrow);
        return getRowScalars(mrow - 1);
    }

    /**
     * Retrieve a column (0-based) from the matrix as an array of scalars.
     * @param col column of the values to retrieve (0-based)
     * @return the column as a Scalar array
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    @SuppressWarnings("unchecked")
    public Q[] getColumnScalars(final int col) throws IndexOutOfBoundsException
    {
        checkCol(col);

        Q first = get(0, col);
        Q[] out = (Q[]) Array.newInstance(first.getClass(), rows());
        for (int r = 0; r < rows(); r++)
        {
            out[r] = get(r, col);
        }
        return out;
    }

    /**
     * Retrieve a column (1-based) from the matrix as an array of scalars.
     * @param mcol column of the values to retrieve (1-based)
     * @return the column as a Scalar array
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    public Q[] mgetColumnScalars(final int mcol) throws IndexOutOfBoundsException
    {
        mcheckCol(mcol);
        return getColumnScalars(mcol - 1);
    }

    /**
     * Return the mean value of the elements of the vector or matrix.
     * @return the mean value of the elements of the vector or matrix
     */
    public Q mean()
    {
        return getDisplayUnit().ofSi(sum().si() / si().length).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the minimum value of the elements of the vector or matrix.
     * @return the minimum value of the elements of the vector or matrix
     */
    public Q min()
    {
        return getDisplayUnit().ofSi(Math2.min(si())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the maximum value of the elements of the vector or matrix.
     * @return the maximum value of the elements of the vector or matrix
     */
    public Q max()
    {
        return getDisplayUnit().ofSi(Math2.max(si())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the largest value of the elements of the vector or matrix.
     * @return the largest value of the elements of the vector or matrix
     */
    public Q mode()
    {
        return max();
    }

    /**
     * Return the median value of the elements of the vector or matrix.
     * @return the median value of the elements of the vector or matrix
     */
    public Q median()
    {
        return getDisplayUnit().ofSi(Math2.median(si())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the sum of the values of the elements of the vector or matrix.
     * @return the sum of the values of the elements of the vector or matrix
     */
    public Q sum()
    {
        return getDisplayUnit().ofSi(Math2.sum(si())).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the a vector or matrix with entries that contain the sum of the element and the increment.
     * @param increment the quantity by which to increase the values of the vector or matrix
     * @return a vector or matrix with elements that are incremented by the given quantity
     */
    public VM add(final Q increment)
    {
        return instantiateSi(ArrayMath.add(si(), increment.si()));
    }

    /**
     * Return the a vector or matrix with entries that contain the value minus the decrement.
     * @param decrement the quantity by which to decrease the values of the vector or matrix
     * @return a vector or matrix with elements that are decremented by the given quantity
     */
    public VM subtract(final Q decrement)
    {
        return instantiateSi(ArrayMath.add(si(), -decrement.si()));
    }

    @Override
    public VM add(final VM other)
    {
        return instantiateSi(ArrayMath.add(si(), other.si()));
    }

    @Override
    public VM subtract(final VM other)
    {
        return instantiateSi(ArrayMath.subtract(si(), other.si()));
    }

    @Override
    public VM negate()
    {
        return scaleBy(-1.0);
    }

    @Override
    public VM abs()
    {
        return instantiateSi(ArrayMath.abs(si()));
    }

    @Override
    public VM scaleBy(final double factor)
    {
        return instantiateSi(ArrayMath.scaleBy(si(), factor));
    }

    /**
     * Multiply the elements of this vector, matrix or table by the given quantity. This is actually a Hadamard operation, but
     * since it is equivalent to a scaleBy operation, it is included in this interface.
     * @param quantity the scalar quantity to multiply by
     * @return a vector, matrix or table where the elements have been multiplied by the given quantity
     */
    public VM multiplyElements(final Dimensionless quantity)
    {
        return scaleBy(quantity.si());
    }

    /**
     * Multiply the elements of this vector, matrix or table by the given quantity. This is actually a Hadamard operation, but
     * since it is equivalent to a scaleBy operation, it is included in this interface.
     * @param quantity the scalar quantity to multiply by
     * @return a vector, matrix or table where the elements have been multiplied by the given quantity
     */
    public VM divideElements(final Dimensionless quantity)
    {
        return scaleBy(1.0 / quantity.si());
    }

    @Override
    public SI invertElements()
    {
        return (SI) instantiateSi(ArrayMath.reciprocal(si()), getDisplayUnit().siUnit().invert());
    }

    @Override
    public SI multiplyElements(final H other)
    {
        return (SI) instantiateSi(ArrayMath.multiply(si(), other.si()),
                getDisplayUnit().siUnit().plus(other.getDisplayUnit().siUnit()));
    }

    @Override
    public SI divideElements(final H other)
    {
        return (SI) instantiateSi(ArrayMath.divide(si(), other.si()),
                getDisplayUnit().siUnit().minus(other.getDisplayUnit().siUnit()));
    }

    @Override
    public SI multiplyElements(final Quantity<?, ?> quantity)
    {
        return (SI) instantiateSi(ArrayMath.scaleBy(si(), quantity.si()),
                getDisplayUnit().siUnit().plus(quantity.getDisplayUnit().siUnit()));
    }

    /**
     * Convert this vector or matrix to a {@link MatrixNxM}.
     * @return a {@code MatrixNxN} with identical SI data and display unit
     */
    public MatrixNxM<Q, U> asMatrixNxM()
    {
        return new MatrixNxM<Q, U>(new DenseDoubleDataSi(si(), rows(), cols()), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    /**
     * Convert this vector or matrix to a {@link QuantityTable}.
     * @return a {@code QuantityTable} with identical SI data and display unit
     */
    public QuantityTable<Q, U> asQuantityTable()
    {
        return new QuantityTable<Q, U>(new DenseDoubleDataSi(si(), rows(), cols()), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    @SuppressWarnings("checkstyle:needbraces")
    @Override
    public String toString(final U withUnit)
    {
        var s = new StringBuilder();
        for (int r = 0; r < rows(); r++)
        {
            s.append(r == 0 ? "[" : "\n ");
            for (int c = 0; c < cols(); c++)
            {
                if (c > 0)
                    s.append(", ");
                s.append(Format.format(withUnit.fromBaseValue(si(r, c))));
            }
        }
        s.append("] ");
        s.append(withUnit.getDisplayAbbreviation());
        return s.toString();
    }

    @Override
    public String toString()
    {
        return toString(getDisplayUnit());
    }

}
