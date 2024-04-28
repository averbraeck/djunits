package org.djunits.value.vfloat.matrix.base;

import java.lang.reflect.Array;

import org.djunits.unit.Unit;
import org.djunits.value.Absolute;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.base.Matrix;
import org.djunits.value.formatter.Format;
import org.djunits.value.storage.StorageType;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.function.FloatMathFunctions;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.base.FloatScalar;
import org.djunits.value.vfloat.vector.base.FloatVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;
import org.djutils.exceptions.Throw;

/**
 * FloatMatrix utility methods, e.g., for creating FloatMatrixs from different types of data.
 * <p>
 * Copyright (c) 2015-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit
 * @param <S> the scalar with unit U
 * @param <V> the vector type belonging to the matrix type
 * @param <M> the generic matrix type
 */
public abstract class FloatMatrix<U extends Unit<U>, S extends FloatScalar<U, S>, V extends FloatVector<U, S, V>,
        M extends FloatMatrix<U, S, V, M>> extends Matrix<U, S, V, FloatVectorData, M, FloatMatrixData>
{
    /** */
    private static final long serialVersionUID = 20161015L;

    /** The stored data as an object, can be sparse or dense. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected FloatMatrixData data;

    /**
     * Construct a new FloatMatrix.
     * @param data FloatMatrixData; an internal data object
     * @param unit U; the unit
     */
    public FloatMatrix(final FloatMatrixData data, final U unit)
    {
        super(unit);
        Throw.whenNull(data, "data cannot be null");
        this.data = data;
    }

    /**
     * Instantiate a new matrix of the class of this matrix. This can be used instead of the FloatMatrix.instiantiate() methods
     * in case another matrix of this class is known. The method is faster than FloatMatrix.instantiate, and it will also work
     * if the matrix is user-defined.
     * @param fmd FloatMatrixData; the data used to instantiate the matrix
     * @param displayUnit U; the display unit of the matrix
     * @return V; a matrix of the correct type
     */
    public abstract M instantiateMatrix(FloatMatrixData fmd, U displayUnit);

    /**
     * Instantiate a new vector of the class of this matrix. This can be used instead of the FloatVector.instiantiate() methods
     * in case another matrix of this class is known. The method is faster than FloatVector.instantiate, and it will also work
     * if the matrix and/or vector are user-defined.
     * @param fvd FloatVectorData; the data used to instantiate the vector
     * @param displayUnit U; the display unit of the vector
     * @return V; a vector of the correct type
     */
    public abstract V instantiateVector(FloatVectorData fvd, U displayUnit);

    /**
     * Instantiate a new scalar for the class of this matrix. This can be used instead of the FloatScalar.instiantiate() methods
     * in case a matrix of this class is known. The method is faster than FloatScalar.instantiate, and it will also work if the
     * matrix and/or scalar are user-defined.
     * @param valueSI float; the SI value of the scalar
     * @param displayUnit U; the unit in which the value will be displayed
     * @return S; a scalar of the correct type, belonging to the matrix type
     */
    public abstract S instantiateScalarSI(float valueSI, U displayUnit);

    /** {@inheritDoc} */
    @Override
    protected final FloatMatrixData getData()
    {
        return this.data;
    }

    /** {@inheritDoc} */
    @Override
    protected void setData(final FloatMatrixData data)
    {
        this.data = data;
    }

    /**
     * Retrieve the value stored at a specified row and column in the standard SI unit.
     * @param row int; row of the value to retrieve
     * @param column int; column of the value to retrieve
     * @return float; value at position row, column in the standard SI unit
     * @throws ValueRuntimeException when row or column out of range (row &lt; 0 or row &gt;= rows() or column &lt; 0 or column
     *             &gt;= columns())
     */
    public float getSI(final int row, final int column) throws ValueRuntimeException
    {
        checkIndex(row, column);
        return this.data.getSI(row, column);
    }

    /**
     * Retrieve the value stored at a specified row and column in the original unit.
     * @param row int; row of the value to retrieve
     * @param column int; column of the value to retrieve
     * @return float; value at position row, column in the original unit
     * @throws ValueRuntimeException when row or column out of range (row &lt; 0 or row &gt;= rows() or column &lt; 0 or column
     *             &gt;= columns())
     */
    public float getInUnit(final int row, final int column) throws ValueRuntimeException
    {
        checkIndex(row, column);
        return (float) ValueUtil.expressAsUnit(this.data.getSI(row, column), getDisplayUnit());
    }

    /**
     * Retrieve the value stored at a specified row and column converted into a specified unit.
     * @param row int; row of the value to retrieve
     * @param column int; column of the value to retrieve
     * @param targetUnit U; the unit for the result
     * @return float; value at position row, column converted into the specified unit
     * @throws ValueRuntimeException when row or column out of range (row &lt; 0 or row &gt;= rows() or column &lt; 0 or column
     *             &gt;= columns())
     */
    public float getInUnit(final int row, final int column, final U targetUnit) throws ValueRuntimeException
    {
        checkIndex(row, column);
        return (float) ValueUtil.expressAsUnit(this.data.getSI(row, column), targetUnit);
    }

    /**
     * Set the value, specified in the standard SI unit, at the specified position.
     * @param row int; row of the value to set
     * @param column int; column of the value to set
     * @param valueSI float; the value, specified in the standard SI unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void setSI(final int row, final int column, final float valueSI) throws ValueRuntimeException
    {
        checkIndex(row, column);
        checkCopyOnWrite();
        this.data.setSI(row, column, valueSI);
    }

    /**
     * Set the value, specified in the (current) display unit, at the specified position.
     * @param row int; row of the value to set
     * @param column int; column of the value to set
     * @param valueInUnit float; the value, specified in the (current) display unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void setInUnit(final int row, final int column, final float valueInUnit) throws ValueRuntimeException
    {
        setSI(row, column, (float) ValueUtil.expressAsSIUnit(valueInUnit, getDisplayUnit()));
    }

    /**
     * Set the value, specified in the <code>valueUnit</code>, at the specified position.
     * @param row int; row of the value to set
     * @param column int; column of the value to set
     * @param valueInUnit float; the value, specified in the (current) display unit
     * @param valueUnit U; the unit in which the <code>valueInUnit</code> is expressed
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void setInUnit(final int row, final int column, final float valueInUnit, final U valueUnit)
            throws ValueRuntimeException
    {
        setSI(row, column, (float) ValueUtil.expressAsSIUnit(valueInUnit, valueUnit));
    }

    /**
     * Set the scalar value at the specified position.
     * @param row int; row of the value to set
     * @param column int; column of the value to set
     * @param value S; the value to set
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    public void set(final int row, final int column, final S value) throws ValueRuntimeException
    {
        setSI(row, column, value.si);
    }

    /**
     * Retrieve a row from the matrix as an array of float.
     * @param row int; row of the values to retrieve
     * @return S[]; the row as a float array
     * @throws ValueRuntimeException in case row is out of bounds
     */
    public float[] getRowSI(final int row) throws ValueRuntimeException
    {
        checkRowIndex(row);
        float[] result = new float[this.data.cols()];
        for (int col = 0; col < result.length; col++)
        {
            result[col] = this.data.getSI(row, col);
        }
        return result;
    }

    /**
     * Retrieve a column from the matrix as an array of float.
     * @param column int; column of the values to retrieve
     * @return S[]; the column as a float array
     * @throws ValueRuntimeException in case column is out of bounds
     */
    public float[] getColumnSI(final int column) throws ValueRuntimeException
    {
        checkColumnIndex(column);
        float[] result = new float[this.data.rows()];
        for (int row = 0; row < result.length; row++)
        {
            result[row] = this.data.getSI(row, column);
        }
        return result;
    }

    /**
     * Retrieve the main diagonal of the matrix as an array of float.
     * @return V; the main diagonal as a float array
     * @throws ValueRuntimeException in case the matrix is not square
     */
    public float[] getDiagonalSI() throws ValueRuntimeException
    {
        checkSquare();
        float[] result = new float[this.data.rows()];
        for (int row = 0; row < result.length; row++)
        {
            result[row] = this.data.getSI(row, row);
        }
        return result;
    }

    /**
     * Create a dense float[][] array filled with the values in the standard SI unit.
     * @return float[][]; array of values in the standard SI unit
     */
    public final float[][] getValuesSI()
    {
        return this.data.getDenseMatrixSI();
    }

    /**
     * Create a dense float[][] array filled with the values in the original unit.
     * @return float[][]; the values in the original unit
     */
    public final float[][] getValuesInUnit()
    {
        return getValuesInUnit(getDisplayUnit());
    }

    /**
     * Create a dense float[][] array filled with the values converted into a specified unit.
     * @param targetUnit U; the unit into which the values are converted for use
     * @return float[][]; the values converted into the specified unit
     */
    public final float[][] getValuesInUnit(final U targetUnit)
    {
        float[][] values = getValuesSI();
        for (int i = values.length; --i >= 0;)
        {
            for (int j = values[i].length; --j >= 0;)
            {
                values[i][j] = (float) ValueUtil.expressAsUnit(values[i][j], targetUnit);
            }
        }
        return values;
    }

    /** {@inheritDoc} */
    @Override
    public int rows()
    {
        return this.data.rows();
    }

    /** {@inheritDoc} */
    @Override
    public int cols()
    {
        return this.data.cols();
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public S[][] getScalars()
    {
        S[][] array = (S[][]) Array.newInstance(getScalarClass(), rows(), cols());
        for (int i = 0; i < rows(); i++)
        {
            S[] row = (S[]) Array.newInstance(getScalarClass(), cols());
            array[i] = row;
            for (int j = 0; j < cols(); j++)
            {
                row[j] = get(i, j);
            }
        }
        return array;
    }

    /** {@inheritDoc} */
    @Override
    public S get(final int row, final int column) throws ValueRuntimeException
    {
        checkIndex(row, column);
        return FloatScalar.instantiateSI(getSI(row, column), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public V getRow(final int row) throws ValueRuntimeException
    {
        checkRowIndex(row);
        FloatVectorData dvd =
                FloatVectorData.instantiate(getRowSI(row), getDisplayUnit().getStandardUnit().getScale(), getStorageType());
        return instantiateVector(dvd, getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public V getColumn(final int column) throws ValueRuntimeException
    {
        checkColumnIndex(column);
        FloatVectorData dvd = FloatVectorData.instantiate(getColumnSI(column), getDisplayUnit().getStandardUnit().getScale(),
                getStorageType());
        return instantiateVector(dvd, getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public V getDiagonal() throws ValueRuntimeException
    {
        checkSquare();
        FloatVectorData dvd =
                FloatVectorData.instantiate(getDiagonalSI(), getDisplayUnit().getStandardUnit().getScale(), getStorageType());
        return instantiateVector(dvd, getDisplayUnit());

    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public S[] getRowScalars(final int row) throws ValueRuntimeException
    {
        checkRowIndex(row);
        S[] array = (S[]) Array.newInstance(getScalarClass(), cols());
        for (int col = 0; col < cols(); col++)
        {
            array[col] = get(row, col);
        }
        return array;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public S[] getColumnScalars(final int col) throws ValueRuntimeException
    {
        checkColumnIndex(col);
        S[] array = (S[]) Array.newInstance(getScalarClass(), rows());
        for (int row = 0; row < rows(); row++)
        {
            array[row] = get(row, col);
        }
        return array;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public S[] getDiagonalScalars() throws ValueRuntimeException
    {
        checkSquare();
        S[] array = (S[]) Array.newInstance(getScalarClass(), rows());
        for (int row = 0; row < rows(); row++)
        {
            array[row] = get(row, row);
        }
        return array;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public M toSparse()
    {
        M result;
        if (getStorageType().equals(StorageType.SPARSE))
        {
            result = (M) this;
            result.setDisplayUnit(getDisplayUnit());
        }
        else
        {
            result = instantiateMatrix(this.data.toSparse(), getDisplayUnit());
        }
        result.setDisplayUnit(getDisplayUnit());
        return result;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public M toDense()
    {
        M result;
        if (getStorageType().equals(StorageType.DENSE))
        {
            result = (M) this;
            result.setDisplayUnit(getDisplayUnit());
        }
        else
        {
            result = instantiateMatrix(this.data.toDense(), getDisplayUnit());
        }
        return result;
    }

    /**
     * Execute a function on a cell by cell basis. Note: May be expensive when used on sparse data.
     * @param floatFunction FloatFunction; the function to apply
     * @return M; this updated matrix
     */
    @SuppressWarnings("unchecked")
    public final M assign(final FloatFunction floatFunction)
    {
        checkCopyOnWrite();
        this.data.assign(floatFunction);
        return (M) this;
    }

    /** {@inheritDoc} */
    @Override
    public final M abs()
    {
        return assign(FloatMathFunctions.ABS);
    }

    /** {@inheritDoc} */
    @Override
    public final M ceil()
    {
        return assign(FloatMathFunctions.CEIL);
    }

    /** {@inheritDoc} */
    @Override
    public final M floor()
    {
        return assign(FloatMathFunctions.FLOOR);
    }

    /** {@inheritDoc} */
    @Override
    public final M neg()
    {
        return assign(FloatMathFunctions.NEG);
    }

    /** {@inheritDoc} */
    @Override
    public final M rint()
    {
        return assign(FloatMathFunctions.RINT);
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return toString(getDisplayUnit(), false, true);
    }

    /** {@inheritDoc} */
    @Override
    public String toString(final U displayUnit)
    {
        return toString(displayUnit, false, true);
    }

    /** {@inheritDoc} */
    @Override
    public String toString(final boolean verbose, final boolean withUnit)
    {
        return toString(getDisplayUnit(), verbose, withUnit);
    }

    /** {@inheritDoc} */
    @Override
    public String toString(final U displayUnit, final boolean verbose, final boolean withUnit)
    {
        StringBuffer buf = new StringBuffer();
        if (verbose)
        {
            String ab = this instanceof Absolute ? "Abs " : "Rel ";
            String ds = this.data.isDense() ? "Dense  " : this.data.isSparse() ? "Sparse " : "?????? ";
            if (isMutable())
            {
                buf.append("Mutable   " + ab + ds);
            }
            else
            {
                buf.append("Immutable " + ab + ds);
            }
        }
        for (int row = 0; row < rows(); row++)
        {
            buf.append("\r\n\t");
            for (int col = 0; col < cols(); col++)
            {
                try
                {
                    float d = (float) ValueUtil.expressAsUnit(getSI(row, col), displayUnit);
                    buf.append(" " + Format.format(d));
                }
                catch (ValueRuntimeException ve)
                {
                    buf.append(" " + "********************".substring(0, Format.DEFAULTSIZE));
                }
            }
        }
        buf.append("\n");
        if (withUnit)
        {
            buf.append(displayUnit.getLocalizedDisplayAbbreviation());
        }
        return buf.toString();
    }

    /**
     * Check that provided row and column indices are valid.
     * @param row int; the row value to check
     * @param col int; the column value to check
     * @throws ValueRuntimeException when row or column is invalid
     */
    protected final void checkIndex(final int row, final int col) throws ValueRuntimeException
    {
        if (row < 0 || row >= rows() || col < 0 || col >= cols())
        {
            throw new ValueRuntimeException("index out of range (valid range is 0.." + (rows() - 1) + ", 0.." + (cols() - 1)
                    + ", got " + row + ", " + col + ")");
        }
    }

    /**
     * Check that provided row index is valid.
     * @param row int; the row value to check
     * @throws ValueRuntimeException when row is invalid
     */
    protected final void checkRowIndex(final int row) throws ValueRuntimeException
    {
        if (row < 0 || row >= rows())
        {
            throw new ValueRuntimeException("row index out of range (valid range is 0.." + (rows() - 1) + ", got " + row + ")");
        }
    }

    /**
     * Check that provided column index is valid.
     * @param col int; the column value to check
     * @throws ValueRuntimeException when row is invalid
     */
    protected final void checkColumnIndex(final int col) throws ValueRuntimeException
    {
        if (col < 0 || col >= cols())
        {
            throw new ValueRuntimeException(
                    "column index out of range (valid range is 0.." + (cols() - 1) + ", got " + col + ")");
        }
    }

    /**
     * Check that the matrix is square.
     * @throws ValueRuntimeException when matrix is not square
     */
    protected final void checkSquare() throws ValueRuntimeException
    {
        Throw.when(rows() != cols(), ValueRuntimeException.class, "Matrix is not square, rows=%d, cols=%d", rows(), cols());
    }

    /**
     * Compute the determinant of the matrix, based on the SI values.
     * @return float; the determinant of the matrix
     * @throws ValueRuntimeException when matrix is neither sparse, nor dense, or not square
     */
    public final float determinantSI() throws ValueRuntimeException
    {
        checkSquare();
        return det(getValuesSI());
    }

    /**
     * Calculate the determinant of an n x n matrix.
     * @param mat the matrix
     * @return the determinant using the co-factor formula
     */
    private static float det(final float[][] mat)
    {
        if (mat.length == 1)
        {
            return mat[0][0];
        }
        // det(A) = sum(j=1:n) (-1)^(i+j).a_ij.A_ij where A_ij is the matrix with row i and column j removed
        float det = 0.0f;
        // possible optimization: pick the row or column with most zeros; here: pick row 0
        for (int col = 0; col < mat.length; col++)
        {
            float sgn = (col % 2 == 0) ? 1 : -1;
            float aij = mat[0][col];
            float[][] matAij = new float[mat.length - 1][];
            int r = 0;
            for (int row = 1; row < mat.length; row++)
            {
                matAij[r] = new float[matAij.length];
                int c = 0;
                for (int j = 0; j < mat.length; j++)
                {
                    if (j != col)
                    {
                        matAij[r][c++] = mat[row][j];
                    }
                }
                r++;
            }
            det += sgn * aij * det(matAij);
        }
        return det;
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public int hashCode()
    {
        final int prime = 31;
        int result = getDisplayUnit().getStandardUnit().hashCode();
        result = prime * result + ((this.data == null) ? 0 : this.data.hashCode());
        return result;
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings({"checkstyle:designforextension", "checkstyle:needbraces"})
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FloatMatrix<?, ?, ?, ?> other = (FloatMatrix<?, ?, ?, ?>) obj;
        if (!getDisplayUnit().getStandardUnit().equals(other.getDisplayUnit().getStandardUnit()))
            return false;
        if (this.data == null)
        {
            if (other.data != null)
                return false;
        }
        else if (!this.data.equals(other.data))
            return false;
        return true;
    }

}
