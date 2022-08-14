package org.djunits.value.vdouble.matrix.base;

import org.djunits.unit.Unit;
import org.djunits.value.ValueRuntimeException;
import org.djunits.value.base.Matrix;
import org.djunits.value.vdouble.function.DoubleFunction;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.base.DoubleScalarInterface;
import org.djunits.value.vdouble.vector.base.DoubleVectorInterface;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

/**
 * Interface for the DoubleMatrix classes, specifically defining the methods that deal with double values.
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
public interface DoubleMatrixInterface<U extends Unit<U>, S extends DoubleScalarInterface<U, S>,
        V extends DoubleVectorInterface<U, S, V>, M extends DoubleMatrixInterface<U, S, V, M>> extends Matrix<U, S, V, M>
{
    /**
     * Retrieve the value stored at a specified row and column in the standard SI unit.
     * @param row int; row of the value to retrieve
     * @param column int; column of the value to retrieve
     * @return double; value at position row, column in the standard SI unit
     * @throws ValueRuntimeException when row or column out of range (row &lt; 0 or row &gt;= rows() or column &lt; 0 or column
     *             &gt;= columns())
     */
    double getSI(int row, int column) throws ValueRuntimeException;

    /**
     * Retrieve the value stored at a specified row and column in the original unit.
     * @param row int; row of the value to retrieve
     * @param column int; column of the value to retrieve
     * @return double; value at position row, column in the original unit
     * @throws ValueRuntimeException when row or column out of range (row &lt; 0 or row &gt;= rows() or column &lt; 0 or column
     *             &gt;= columns())
     */
    double getInUnit(int row, int column) throws ValueRuntimeException;

    /**
     * Retrieve the value stored at a specified row and column converted into a specified unit.
     * @param row int; row of the value to retrieve
     * @param column int; column of the value to retrieve
     * @param targetUnit U; the unit for the result
     * @return double; value at position row, column converted into the specified unit
     * @throws ValueRuntimeException when row or column out of range (row &lt; 0 or row &gt;= rows() or column &lt; 0 or column
     *             &gt;= columns())
     */
    double getInUnit(int row, int column, U targetUnit) throws ValueRuntimeException;

    /**
     * Retrieve a row from the matrix as an array of double.
     * @param row int; row of the values to retrieve
     * @return S[]; the row as a double array
     * @throws ValueRuntimeException in case row is out of bounds
     */
    double[] getRowSI(int row) throws ValueRuntimeException;

    /**
     * Retrieve a column from the matrix as an array of double.
     * @param column int; column of the values to retrieve
     * @return S[]; the column as a double array
     * @throws ValueRuntimeException in case column is out of bounds
     */
    double[] getColumnSI(int column) throws ValueRuntimeException;

    /**
     * Retrieve the main diagonal of the matrix as an array of double.
     * @return V; the main diagonal as a double array
     * @throws ValueRuntimeException in case the matrix is not square
     */
    double[] getDiagonalSI() throws ValueRuntimeException;

    /**
     * Create a dense double[][] array filled with the values in the standard SI unit.
     * @return double[][]; array of values in the standard SI unit
     */
    double[][] getValuesSI();

    /**
     * Create a dense double[][] array filled with the values in the original unit.
     * @return double[][]; the values in the original unit
     */
    double[][] getValuesInUnit();

    /**
     * Create a dense double[][] array filled with the values converted into a specified unit.
     * @param targetUnit U; the unit into which the values are converted for use
     * @return double[][]; the values converted into the specified unit
     */
    double[][] getValuesInUnit(U targetUnit);

    /**
     * Compute the determinant of the matrix, based on the SI values in the matrix.
     * @return double; the determinant of the matrix
     * @throws ValueRuntimeException when matrix is neither sparse, nor dense, or not square
     */
    double determinantSI() throws ValueRuntimeException;

    /**
     * Set the value, specified in the standard SI unit, at the specified position.
     * @param row int; row of the value to set
     * @param column int; column of the value to set
     * @param valueSI double; the value, specified in the standard SI unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    void setSI(int row, int column, double valueSI) throws ValueRuntimeException;

    /**
     * Set the value, specified in the (current) display unit, at the specified position.
     * @param row int; row of the value to set
     * @param column int; column of the value to set
     * @param valueInUnit double; the value, specified in the (current) display unit
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    void setInUnit(int row, int column, double valueInUnit) throws ValueRuntimeException;

    /**
     * Set the value, specified in the <code>valueUnit</code>, at the specified position.
     * @param row int; row of the value to set
     * @param column int; column of the value to set
     * @param valueInUnit double; the value, specified in the (current) display unit
     * @param valueUnit U; the unit in which the <code>valueInUnit</code> is expressed
     * @throws ValueRuntimeException when index out of range (index &lt; 0 or index &gt;= size())
     */
    void setInUnit(int row, int column, double valueInUnit, U valueUnit) throws ValueRuntimeException;

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
     * @param doubleFunction DoubleFunction; the function to apply
     * @return M; this updated matrix
     */
    M assign(DoubleFunction doubleFunction);

    /**
     * Instantiate a new matrix of the class of this matrix. This can be used instead of the DoubleMatrix.instiantiate() methods
     * in case another matrix of this class is known. The method is faster than DoubleMatrix.instantiate, and it will also work
     * if the matrix is user-defined.
     * @param dmd DoubleMatrixData; the data used to instantiate the matrix
     * @param displayUnit U; the display unit of the matrix
     * @return V; a matrix of the correct type
     */
    M instantiateMatrix(DoubleMatrixData dmd, U displayUnit);

    /**
     * Instantiate a new vector of the class of this matrix. This can be used instead of the DoubleVector.instiantiate() methods
     * in case another matrix of this class is known. The method is faster than DoubleVector.instantiate, and it will also work
     * if the matrix and/or vector are user-defined.
     * @param dvd DoubleVectorData; the data used to instantiate the vector
     * @param displayUnit U; the display unit of the vector
     * @return V; a vector of the correct type
     */
    V instantiateVector(DoubleVectorData dvd, U displayUnit);

    /**
     * Instantiate a new scalar for the class of this matrix. This can be used instead of the DoubleScalar.instiantiate()
     * methods in case a matrix of this class is known. The method is faster than DoubleScalar.instantiate, and it will also
     * work if the matrix and/or scalar are user-defined.
     * @param valueSI double; the SI value of the scalar
     * @param displayUnit U; the unit in which the value will be displayed
     * @return S; a scalar of the correct type, belonging to the matrix type
     */
    S instantiateScalarSI(double valueSI, U displayUnit);

}
