package org.djunits.value.base;

import org.djunits.unit.Unit;
import org.djunits.value.IndexedValue;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.storage.Storage;

/**
 * Matrix to distinguish a matrix from scalars and matrixs. A possible way to implement this interface is:
 * 
 * <pre>
 * class LengthMatrix implements Matrix&lt;LengthUnit, Length, LengthMatrix&gt;
 * </pre>
 * 
 * Copyright (c) 2019-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>. <br>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 * @param <U> the unit
 * @param <S> the scalar type belonging to the matrix type
 * @param <V> the corresponding vector type
 * @param <M> the matrix type with the given unit
 * @param <DV> the data storage type of the Vector
 * @param <DM> the data storage type of the Matrix
 */
public abstract class Matrix<U extends Unit<U>, S extends Scalar<U, S>, V extends Vector<U, S, V, DV>, DV extends Storage<DV>,
        M extends Matrix<U, S, V, DV, M, DM>, DM extends Storage<DM>> extends IndexedValue<U, S, M, DM>
{
    /** */
    private static final long serialVersionUID = 20230620L;

    /**
     * Construct a new Matrix.
     * @param displayUnit U; the unit of the new AbstractValue
     */
    public Matrix(final U displayUnit)
    {
        super(displayUnit);
    }

    /**
     * Retrieve the number of rows of the matrix.
     * @return int; the number of rows of the matrix
     */
    public abstract int rows();

    /**
     * Retrieve the number of columns of the matrix.
     * @return int; the number of columns of the matrix
     */
    public abstract int cols();

    /**
     * Return the class of the corresponding vector.
     * @return Class&lt;V&gt;; the class of the corresponding vector
     */
    public abstract Class<V> getVectorClass();

    /**
     * Retrieve a value from the matrix.
     * @param row int; row of the value to retrieve
     * @param column int; column of the value to retrieve
     * @return S; the value as a Scalar
     * @throws IndexOutOfBoundsException in case row or column is out of bounds
     */
    public abstract S get(int row, int column) throws IndexOutOfBoundsException;

    /**
     * Return the vector as a 2D-array of scalars.
     * @return S[][]; the vector as a 2D-array of scalars
     */
    public abstract S[][] getScalars();

    /**
     * Retrieve a row from the matrix as a vector.
     * @param row int; row of the values to retrieve
     * @return V; the row as a Vector
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    public abstract V getRow(int row) throws IndexOutOfBoundsException;

    /**
     * Retrieve a column from the matrix as a vector.
     * @param column int; column of the values to retrieve
     * @return V; the column as a Vector
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    public abstract V getColumn(int column) throws IndexOutOfBoundsException;

    /**
     * Retrieve the main diagonal of the matrix as a vector.
     * @return V; the main diagonal as a Vector
     * @throws ValueRuntimeException in case the matrix is not square
     */
    public abstract V getDiagonal() throws ValueRuntimeException;

    /**
     * Retrieve a row from the matrix as an array of scalars.
     * @param row int; row of the values to retrieve
     * @return S[]; the row as a Scalar array
     * @throws IndexOutOfBoundsException in case row is out of bounds
     */
    public abstract S[] getRowScalars(int row) throws IndexOutOfBoundsException;

    /**
     * Retrieve a column from the matrix as an array of scalars.
     * @param column int; column of the values to retrieve
     * @return S[]; the column as a Scalar array
     * @throws IndexOutOfBoundsException in case column is out of bounds
     */
    public abstract S[] getColumnScalars(int column) throws IndexOutOfBoundsException;

    /**
     * Retrieve the main diagonal of the matrix as an array of scalars.
     * @return V; the main diagonal as a Scalar array
     * @throws ValueRuntimeException in case the matrix is not square
     */
    public abstract S[] getDiagonalScalars() throws ValueRuntimeException;

}
