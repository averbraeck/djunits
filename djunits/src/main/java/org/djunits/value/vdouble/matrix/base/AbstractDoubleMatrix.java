package org.djunits.value.vdouble.matrix.base;

import java.lang.reflect.Array;

import org.djunits.Throw;
import org.djunits.unit.Unit;
import org.djunits.value.Absolute;
import org.djunits.value.AbstractIndexedValue;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.formatter.Format;
import org.djunits.value.storage.StorageType;
import org.djunits.value.util.ValueUtil;
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.base.AbstractDoubleScalar;
import org.djunits.value.vdouble.scalar.base.DoubleScalar;
import org.djunits.value.vdouble.vector.base.AbstractDoubleVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * The most basic abstract class for the DoubleMatrix.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit
 * @param <S> the scalar with unit U
 * @param <V> the vector type belonging to the matrix type
 * @param <M> the generic matrix type
 */
public abstract class AbstractDoubleMatrix<U extends Unit<U>, S extends AbstractDoubleScalar<U, S>,
        V extends AbstractDoubleVector<U, S, V>, M extends AbstractDoubleMatrix<U, S, V, M>>
        extends AbstractIndexedValue<U, S, M, DoubleMatrixData> implements DoubleMatrixInterface<U, S, V, M>
{
    /** */
    private static final long serialVersionUID = 20161015L;

    /** The stored data as an object, can be sparse or dense. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected DoubleMatrixData data;

    /**
     * Construct a new DoubleMatrix.
     * @param data DoubleMatrixData; an internal data object
     * @param unit U; the unit
     */
    AbstractDoubleMatrix(final DoubleMatrixData data, final U unit)
    {
        super(unit);
        Throw.whenNull(data, "data cannot be null");
        this.data = data;
    }

    /** {@inheritDoc} */
    @Override
    protected final DoubleMatrixData getData()
    {
        return this.data;
    }

    /** {@inheritDoc} */
    @Override
    protected void setData(final DoubleMatrixData data)
    {
        this.data = data;
    }

    /** {@inheritDoc} */
    @Override
    public double getSI(final int row, final int column) throws ValueRuntimeException
    {
        checkIndex(row, column);
        return this.data.getSI(row, column);
    }

    /** {@inheritDoc} */
    @Override
    public double getInUnit(final int row, final int column) throws ValueRuntimeException
    {
        checkIndex(row, column);
        return ValueUtil.expressAsUnit(this.data.getSI(row, column), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public double getInUnit(final int row, final int column, final U targetUnit) throws ValueRuntimeException
    {
        checkIndex(row, column);
        return ValueUtil.expressAsUnit(this.data.getSI(row, column), targetUnit);
    }

    /** {@inheritDoc} */
    @Override
    public void setSI(final int row, final int column, final double valueSI) throws ValueRuntimeException
    {
        checkIndex(row, column);
        checkCopyOnWrite();
        this.data.setSI(row, column, valueSI);
    }

    /** {@inheritDoc} */
    @Override
    public void setInUnit(final int row, final int column, final double valueInUnit) throws ValueRuntimeException
    {
        setSI(row, column, ValueUtil.expressAsSIUnit(valueInUnit, getDisplayUnit()));
    }

    /** {@inheritDoc} */
    @Override
    public void setInUnit(final int row, final int column, final double valueInUnit, final U valueUnit)
            throws ValueRuntimeException
    {
        setSI(row, column, ValueUtil.expressAsSIUnit(valueInUnit, valueUnit));
    }

    /** {@inheritDoc} */
    @Override
    public void set(final int row, final int column, final S value) throws ValueRuntimeException
    {
        setSI(row, column, value.si);
    }

    /** {@inheritDoc} */
    @Override
    public double[] getRowSI(final int row) throws ValueRuntimeException
    {
        checkRowIndex(row);
        double[] result = new double[this.data.cols()];
        for (int col = 0; col < result.length; col++)
        {
            result[col] = this.data.getSI(row, col);
        }
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public double[] getColumnSI(final int column) throws ValueRuntimeException
    {
        checkColumnIndex(column);
        double[] result = new double[this.data.rows()];
        for (int row = 0; row < result.length; row++)
        {
            result[row] = this.data.getSI(row, column);
        }
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public double[] getDiagonalSI() throws ValueRuntimeException
    {
        checkSquare();
        double[] result = new double[this.data.rows()];
        for (int row = 0; row < result.length; row++)
        {
            result[row] = this.data.getSI(row, row);
        }
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public final double[][] getValuesSI()
    {
        return this.data.getDenseMatrixSI();
    }

    /** {@inheritDoc} */
    @Override
    public final double[][] getValuesInUnit()
    {
        return getValuesInUnit(getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public final double[][] getValuesInUnit(final U targetUnit)
    {
        double[][] values = getValuesSI();
        for (int i = values.length; --i >= 0;)
        {
            for (int j = values[i].length; --j >= 0;)
            {
                values[i][j] = ValueUtil.expressAsUnit(values[i][j], targetUnit);
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
        return DoubleScalar.instantiateSI(getSI(row, column), getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public V getRow(final int row) throws ValueRuntimeException
    {
        checkRowIndex(row);
        DoubleVectorData dvd =
                DoubleVectorData.instantiate(getRowSI(row), getDisplayUnit().getStandardUnit().getScale(), getStorageType());
        return instantiateVector(dvd, getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public V getColumn(final int column) throws ValueRuntimeException
    {
        checkColumnIndex(column);
        DoubleVectorData dvd = DoubleVectorData.instantiate(getColumnSI(column), getDisplayUnit().getStandardUnit().getScale(),
                getStorageType());
        return instantiateVector(dvd, getDisplayUnit());
    }

    /** {@inheritDoc} */
    @Override
    public V getDiagonal() throws ValueRuntimeException
    {
        checkSquare();
        DoubleVectorData dvd =
                DoubleVectorData.instantiate(getDiagonalSI(), getDisplayUnit().getStandardUnit().getScale(), getStorageType());
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

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public final M assign(final DoubleFunction doubleFunction)
    {
        checkCopyOnWrite();
        this.data.assign(doubleFunction);
        return (M) this;
    }

    /** {@inheritDoc} */
    @Override
    public final M abs()
    {
        return assign(DoubleMathFunctions.ABS);
    }

    /** {@inheritDoc} */
    @Override
    public final M ceil()
    {
        return assign(DoubleMathFunctions.CEIL);
    }

    /** {@inheritDoc} */
    @Override
    public final M floor()
    {
        return assign(DoubleMathFunctions.FLOOR);
    }

    /** {@inheritDoc} */
    @Override
    public final M neg()
    {
        return assign(DoubleMathFunctions.NEG);
    }

    /** {@inheritDoc} */
    @Override
    public final M rint()
    {
        return assign(DoubleMathFunctions.RINT);
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
                    double d = ValueUtil.expressAsUnit(getSI(row, col), displayUnit);
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
            buf.append(displayUnit.getDefaultDisplayAbbreviation());
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

    /** {@inheritDoc} */
    @Override
    public final double determinantSI() throws ValueRuntimeException
    {
        checkSquare();
        return det(getValuesSI());
    }

    /**
     * Calculate the determinant of an n x n matrix.
     * @param mat the matrix
     * @return the determinant using the co-factor formula
     */
    private static double det(final double[][] mat)
    {
        if (mat.length == 1)
        {
            return mat[0][0];
        }
        // det(A) = sum(j=1:n) (-1)^(i+j).a_ij.A_ij where A_ij is the matrix with row i and column j removed
        double det = 0.0;
        // possible optimization: pick the row or column with most zeros; here: pick row 0
        for (int col = 0; col < mat.length; col++)
        {
            double sgn = (col % 2 == 0) ? 1 : -1;
            double aij = mat[0][col];
            double[][] matAij = new double[mat.length - 1][];
            int r = 0;
            for (int row = 1; row < mat.length; row++)
            {
                matAij[r] = new double[matAij.length];
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
        AbstractDoubleMatrix<?, ?, ?, ?> other = (AbstractDoubleMatrix<?, ?, ?, ?>) obj;
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
