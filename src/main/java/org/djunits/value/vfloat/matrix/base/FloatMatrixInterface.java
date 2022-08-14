package org.djunits.value.vfloat.matrix.base;

import org.djunits.unit.Unit;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.base.Matrix;
import org.djunits.value.vfloat.function.FloatFunction;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.base.FloatScalarInterface;
import org.djunits.value.vfloat.vector.base.FloatVectorInterface;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

/**
 * Interface for the FloatMatrix classes, specifically defining the methods that deal with float values.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 * @param <U> the unit
 * @param <S> the generic scalar type belonging to U
 * @param <V> the vector type belonging to the matrix type
 * @param <M> the generic matrix type
 */
public interface FloatMatrixInterface<U extends Unit<U>, S extends FloatScalarInterface<U, S>,
        V extends FloatVectorInterface<U, S, V>, M extends FloatMatrixInterface<U, S, V, M>> extends Matrix<U, S, V, M>
{
    /**
     * Retrieve the value stored at a specified row and column in the standard SI unit.
     * @param row int; row of the value to retrieve
     * @param column int; column of the value to retrieve
     * @return float; value at position row, column in the standard SI unit
     * @throws ValueRuntimeException when row or column out of range (row &lt; 0 or row &gt;= rows() or column &lt; 0 or column
     *             &gt;= columns())
     */
    float getSI(int row, int column) throws ValueRuntimeException;

    /**
     * Retrieve the value stored at a specified row and column in the original unit.
     * @param row int; row of the value to retrieve
     * @param column int; column of the value to retrieve
     * @return float; value at position row, column in the original unit
     * @throws ValueRuntimeException when row or column out of range (row &lt; 0 or row &gt;= rows() or column &lt; 0 or column
     *             &gt;= columns())
     */
    float getInUnit(int row, int column) throws ValueRuntimeException;

    /**
     * Retrieve the value stored at a specified row and column converted into a specified unit.
     * @param row int; row of the value to retrieve
     * @param column int; column of the value to retrieve
     * @param targetUnit U; the unit for the result
     * @return float; value at position row, column converted into the specified unit
     * @throws ValueRuntimeException when row or column out of range (row &lt; 0 or row &gt;= rows() or column &lt; 0 or column
     *             &gt;= columns())
     */
    float getInUnit(int row, int column, U targetUnit) throws ValueRuntimeException;

    /**
     * Retrieve a row from the matrix as an array of float.
     * @param row int; row of the values to retrieve
     * @return S[]; the row as a float array
     * @throws ValueRuntimeException in case row is out of bounds
     */
    float[] getRowSI(int row) throws ValueRuntimeException;

    /**
     * Retrieve a column from the matrix as an array of float.
     * @param column int; column of the values to retrieve
     * @return S[]; the column as a float array
     * @throws ValueRuntimeException in case column is out of bounds
     */
    float[] getColumnSI(int column) throws ValueRuntimeException;

    /**
     * Retrieve the main diagonal of the matrix as an array of float.
     * @return V; the main diagonal as a float array
     * @throws ValueRuntimeException in case the matrix is not square
     */
    float[] getDiagonalSI() throws ValueRuntimeException;

    /**
     * Create a dense float[][] array filled with the values in the standard SI unit.
     * @return float[][]; array of values in the standard SI unit
     */
    float[][] getValuesSI();

    /**
     * Create a dense float[][] array filled with the values in the original unit.
     * @return float[][]; the values in the original unit
     */
    float[][] getValuesInUnit();

    /**
     * Create a dense float[][] array filled with the values converted into a specified unit.
     * @param targetUnit U; the unit into which the values are converted for use
     * @return float[][]; the values converted into the specified unit
     */
    float[][] getValuesInUnit(U targetUnit);

    /**
     * Compute the determinant of the matrix, based on the SI values.
     * @return float; the determinant of the matrix
     * @throws ValueRuntimeException when matrix is neither sparse, nor dense, or not square
     */
    float determinantSI() throws ValueRuntimeException;

    /**
     * Set the value, specified in the standard SI unit, at the specified position.
     * @param row int; row of the value to set
     * @param column int; column of the value to set
     * @param valueSI float; the value, specified in the standard SI unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    void setSI(int row, int column, float valueSI) throws ValueRuntimeException;

    /**
     * Set the value, specified in the (current) display unit, at the specified position.
     * @param row int; row of the value to set
     * @param column int; column of the value to set
     * @param valueInUnit float; the value, specified in the (current) display unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    void setInUnit(int row, int column, float valueInUnit) throws ValueRuntimeException;

    /**
     * Set the value, specified in the <code>valueUnit</code>, at the specified position.
     * @param row int; row of the value to set
     * @param column int; column of the value to set
     * @param valueInUnit float; the value, specified in the (current) display unit
     * @param valueUnit U; the unit in which the <code>valueInUnit</code> is expressed
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    void setInUnit(int row, int column, float valueInUnit, U valueUnit) throws ValueRuntimeException;

    /**
     * Set the scalar value at the specified position.
     * @param row int; row of the value to set
     * @param column int; column of the value to set
     * @param value S; the value to set
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    void set(int row, int column, S value) throws ValueRuntimeException;

    /**
     * Execute a function on a cell by cell basis. Note: May be expensive when used on sparse data.
     * @param floatFunction FloatFunction; the function to apply
     * @return M; this updated matrix
     */
    M assign(FloatFunction floatFunction);

    /**
     * Instantiate a new matrix of the class of this matrix. This can be used instead of the FloatMatrix.instiantiate() methods
     * in case another matrix of this class is known. The method is faster than FloatMatrix.instantiate, and it will also work
     * if the matrix is user-defined.
     * @param fmd FloatMatrixData; the data used to instantiate the matrix
     * @param displayUnit U; the display unit of the matrix
     * @return V; a matrix of the correct type
     */
    M instantiateMatrix(FloatMatrixData fmd, U displayUnit);

    /**
     * Instantiate a new vector of the class of this matrix. This can be used instead of the FloatVector.instiantiate() methods
     * in case another matrix of this class is known. The method is faster than FloatVector.instantiate, and it will also work
     * if the matrix and/or vector are user-defined.
     * @param fvd FloatVectorData; the data used to instantiate the vector
     * @param displayUnit U; the display unit of the vector
     * @return V; a vector of the correct type
     */
    V instantiateVector(FloatVectorData fvd, U displayUnit);

    /**
     * Instantiate a new scalar for the class of this matrix. This can be used instead of the FloatScalar.instiantiate() methods
     * in case a matrix of this class is known. The method is faster than FloatScalar.instantiate, and it will also work if the
     * matrix and/or scalar are user-defined.
     * @param valueSI float; the SI value of the scalar
     * @param displayUnit U; the unit in which the value will be displayed
     * @return S; a scalar of the correct type, belonging to the matrix type
     */
    S instantiateScalarSI(float valueSI, U displayUnit);
}
