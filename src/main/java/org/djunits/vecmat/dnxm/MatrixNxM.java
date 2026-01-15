package org.djunits.vecmat.dnxm;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.DataGridMatrix;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.operations.Hadamard;
import org.djunits.vecmat.storage.DataGrid;
import org.djunits.vecmat.storage.DenseDoubleData;
import org.djutils.exceptions.Throw;

/**
 * MatrixNxM implements a matrix with NxM real-valued entries. The matrix is immutable, except for the display unit, which can
 * be changed. Internal storage can be float or double, and dense or sparse. MatrixNxN and VectorN extend from this class.<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public class MatrixNxM<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> extends DataGridMatrix<Q, U, MatrixNxM<Q, U>>
        implements Hadamard<MatrixNxM<?, ?>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new NxM Matrix with a unit, based on a DataGrid storage object that contains SI data.
     * @param dataSi the data of the matrix, in SI unit.
     * @param displayUnit the display unit to use
     * @throws IllegalArgumentException when the number of rows or columns does not have a positive value
     */
    public MatrixNxM(final DataGrid<?> dataSi, final U displayUnit)
    {
        super(dataSi, displayUnit);
    }

    /**
     * Create a new MatrixNxM with a unit, based on a 1-dimensional array.
     * @param valueArray the matrix values {a11, a12, ..., a1M, aN2, ..., aNM} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @param rows the number of rows in the valueArray
     * @param cols the number of columns in the valueArray
     * @return a new MatrixNxM with a unit
     * @throws IllegalArgumentException when rows or cols is not positive, or when the number of entries in valueArray is not
     *             equal to rows*cols
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> MatrixNxM<Q, U> of(final double[] valueArray,
            final int rows, final int cols, final U displayUnit)
    {
        Throw.whenNull(valueArray, "valueArray");
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(rows <= 0, IllegalArgumentException.class, "rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "cols <= 0");
        Throw.when(rows * cols != valueArray.length, IllegalArgumentException.class,
                "valueArray does not contain a the correct number of entries (%d x %d != %d)", rows, cols, valueArray.length);
        double[] aSi = new double[rows * cols];
        for (int i = 0; i < valueArray.length; i++)
            aSi[i] = displayUnit.toBaseValue(valueArray[i]);
        return new MatrixNxM<Q, U>(new DenseDoubleData(aSi, rows, cols), displayUnit);
    }

    /**
     * Create a new MatrixNxM with a unit, based on a 2-dimensional grid.
     * @param valueGrid the matrix values {{a11, a12, a1M}, ..., {aN1, N32, aNM}} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new MatrixNxM with a unit
     * @throws IllegalArgumentException when valueGrid has 0 rows, or when the number of columns for one of the rows is not
     *             equal to the number of columns in another row
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> MatrixNxM<Q, U> of(final double[][] valueGrid,
            final U displayUnit)
    {
        Throw.whenNull(valueGrid, "valueGrid");
        Throw.whenNull(displayUnit, "displayUnit");
        int rows = valueGrid.length;
        Throw.when(rows == 0, IllegalArgumentException.class, "valueGrid has 0 rows");
        int cols = valueGrid[0].length;
        Throw.when(cols == 0, IllegalArgumentException.class, "row 0 in valueGrid has 0 columns");
        double[] aSi = new double[rows * cols];
        for (int r = 0; r < rows; r++)
        {
            Throw.when(valueGrid[r].length != cols, IllegalArgumentException.class,
                    "valueGrid is not a NxM array; row %d has a length of %d, not %d", r, valueGrid[r].length, cols);
            for (int c = 0; c < cols; c++)
                aSi[cols * r + c] = displayUnit.toBaseValue(valueGrid[r][c]);
        }
        return new MatrixNxM<Q, U>(new DenseDoubleData(aSi, rows, cols), displayUnit);
    }

    @Override
    public MatrixNxM<Q, U> instantiate(final double[] siNew)
    {
        return new MatrixNxM<Q, U>(this.dataSi.instantiate(siNew), getDisplayUnit());
    }

    @Override
    public MatrixNxM<SIQuantity, SIUnit> invertElements()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new MatrixNxM<SIQuantity, SIUnit>(this.dataSi.instantiate(ArrayMath.reciprocal(si())), siUnit);
    }

    @Override
    public MatrixNxM<SIQuantity, SIUnit> multiplyElements(final MatrixNxM<?, ?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new MatrixNxM<SIQuantity, SIUnit>(this.dataSi.instantiate(ArrayMath.multiply(si(), other.si())), siUnit);
    }

    @Override
    public MatrixNxM<SIQuantity, SIUnit> divideElements(final MatrixNxM<?, ?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new MatrixNxM<SIQuantity, SIUnit>(this.dataSi.instantiate(ArrayMath.divide(si(), other.si())), siUnit);
    }

    // ------------------------------ MATRIX MULTIPLICATION AND AS() --------------------------

    /**
     * Multiply this matrix with another matrix and return the resulting matrix, using the same underlying data storage type. If
     * this matrix has dimensions N x M, the other matrix should have dimensions N x P.
     * @param otherMat the matrix to multiply with
     * @return the multiplication of this matrix with the other matrix.
     * @throws IllegalArgumentException when the sizes of the matrices do not match for multiplication
     * @implNote checking of the dimensions is done by MatrixMath.multiply()
     */
    public MatrixNxM<SIQuantity, SIUnit> multiply(final MatrixNxM<?, ?> otherMat)
    {
        return new MatrixNxM<SIQuantity, SIUnit>(
                this.dataSi.instantiate(MatrixMath.multiply(si(), otherMat.si(), rows(), cols(), otherMat.cols())),
                getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a 2 x 2 matrix and return the resulting matrix, using the same underlying data storage type.
     * This matrix should have dimensions N x 2, to return an N x 2 matrix.
     * @param otherMat the matrix to multiply with
     * @return the multiplication of this matrix with the other matrix.
     * @throws IllegalArgumentException when the sizes of the matrices do not match for multiplication
     * @implNote checking of the dimensions is done by MatrixMath.multiply()
     */
    public MatrixNxM<SIQuantity, SIUnit> multiply(final Matrix2x2<?, ?> otherMat)
    {
        return new MatrixNxM<SIQuantity, SIUnit>(
                this.dataSi.instantiate(MatrixMath.multiply(si(), otherMat.si(), rows(), cols(), otherMat.cols())),
                getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a 3 x 3 matrix and return the resulting matrix, using the same underlying data storage type.
     * This matrix should have dimensions N x 3, to return an N x 3 matrix.
     * @param otherMat the matrix to multiply with
     * @return the multiplication of this matrix with the other matrix.
     * @throws IllegalArgumentException when the sizes of the matrices do not match for multiplication
     * @implNote checking of the dimensions is done by MatrixMath.multiply()
     */
    public MatrixNxM<SIQuantity, SIUnit> multiply(final Matrix3x3<?, ?> otherMat)
    {
        return new MatrixNxM<SIQuantity, SIUnit>(
                this.dataSi.instantiate(MatrixMath.multiply(si(), otherMat.si(), rows(), cols(), otherMat.cols())),
                getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a column vector with 2 elements and return the resulting vector, using the same underlying data
     * storage type. This matrix should have dimensions N x 2, to return a column vector with N entries.
     * @param colVec the column vector to multiply with
     * @return the column vector with N elements as the result of multiplying this matrix with the column vector.
     * @throws IllegalArgumentException when the sizes of the matrices do not match for multiplication
     * @implNote checking of the dimensions is done by MatrixMath.multiply()
     */
    // TODO change return type to VectorN.Col
    public MatrixNxM<SIQuantity, SIUnit> multiply(final Vector2.Col<?, ?> colVec)
    {
        return new MatrixNxM<SIQuantity, SIUnit>(
                this.dataSi.instantiate(MatrixMath.multiply(si(), colVec.si(), rows(), cols(), 1)),
                getDisplayUnit().siUnit().plus(colVec.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a column vector with 3 elements and return the resulting vector, using the same underlying data
     * storage type. This matrix should have dimensions N x 3, to return a column vector with N entries.
     * @param colVec the column vector to multiply with
     * @return the column vector with N elements as the result of multiplying this matrix with the column vector.
     * @throws IllegalArgumentException when the sizes of the matrices do not match for multiplication
     * @implNote checking of the dimensions is done by MatrixMath.multiply()
     */
    // TODO change return type to VectorN.Col
    public MatrixNxM<SIQuantity, SIUnit> multiply(final Vector3.Col<?, ?> colVec)
    {
        return new MatrixNxM<SIQuantity, SIUnit>(
                this.dataSi.instantiate(MatrixMath.multiply(si(), colVec.si(), rows(), cols(), 1)),
                getDisplayUnit().siUnit().plus(colVec.getDisplayUnit().siUnit()));
    }

    // TODO add multiplication with VectorN.Col

    /**
     * Return the matrix 'as' a matrix with a known quantity, using a unit to express the result in. Throw a Runtime exception
     * when the SI units of this vector and the target vector do not match.
     * @param targetUnit the unit to convert the matrix to
     * @return a matrix typed in the target matrix class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> MatrixNxM<TQ, TU> as(final TU targetUnit)
            throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "MatrixNxM.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new MatrixNxM<TQ, TU>(this.dataSi.instantiate(si()), targetUnit);
    }

    // TODO add methods asMatrix2x2, asMatrix3x3, asMatrixNxN
    // TODO add methods asVector2.Col, asVector3.Col, asVectorN.Col, asVector2.Row, asVector3.Row, asVectorN.Row

}
