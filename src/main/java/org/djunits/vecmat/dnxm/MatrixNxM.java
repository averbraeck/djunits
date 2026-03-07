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
import org.djunits.vecmat.dn.MatrixNxN;
import org.djunits.vecmat.dn.VectorN;
import org.djunits.vecmat.operations.Hadamard;
import org.djunits.vecmat.storage.DataGridSi;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djutils.exceptions.Throw;

/**
 * MatrixNxM implements a matrix with NxM real-valued entries. The matrix is immutable, except for the display unit, which can
 * be changed. Internal storage can be float or double, and dense or sparse. MatrixNxN and VectorN extend from this class.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
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
    public MatrixNxM(final DataGridSi<?> dataSi, final U displayUnit)
    {
        super(dataSi, displayUnit);
    }

    /**
     * Create a new MatrixNxM with a unit, based on a 1-dimensional double array.
     * @param valueArrayInUnit the matrix values {a11, a12, ..., a1M, aN2, ..., aNM} expressed in the display unit
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
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> MatrixNxM<Q, U> of(final double[] valueArrayInUnit,
            final int rows, final int cols, final U displayUnit)
    {
        Throw.whenNull(valueArrayInUnit, "valueArrayInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(rows <= 0, IllegalArgumentException.class, "rows <= 0");
        Throw.when(cols <= 0, IllegalArgumentException.class, "cols <= 0");
        Throw.when(rows * cols != valueArrayInUnit.length, IllegalArgumentException.class,
                "valueArrayInUnit does not contain the correct number of entries (%d x %d != %d)", rows, cols,
                valueArrayInUnit.length);
        double[] aSi = new double[rows * cols];
        for (int i = 0; i < valueArrayInUnit.length; i++)
            aSi[i] = displayUnit.toBaseValue(valueArrayInUnit[i]);
        return new MatrixNxM<Q, U>(new DenseDoubleDataSi(aSi, rows, cols), displayUnit);
    }

    /**
     * Create a new MatrixNxM with a unit, based on a 2-dimensional double grid.
     * @param valueGridInUnit the matrix values {{a11, a12, a1M}, ..., {aN1, aN2, aNM}} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new MatrixNxM with a unit
     * @throws IllegalArgumentException when valueGrid has 0 rows, or when the number of columns for one of the rows is not
     *             equal to the number of columns in another row
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> MatrixNxM<Q, U> of(final double[][] valueGridInUnit,
            final U displayUnit)
    {
        Throw.whenNull(valueGridInUnit, "valueGridInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        int rows = valueGridInUnit.length;
        Throw.when(rows == 0, IllegalArgumentException.class, "valueGridInUnit has 0 rows");
        int cols = valueGridInUnit[0].length;
        Throw.when(cols == 0, IllegalArgumentException.class, "row 0 in valueGridInUnit has 0 columns");
        double[] aSi = new double[rows * cols];
        for (int r = 0; r < rows; r++)
        {
            Throw.when(valueGridInUnit[r].length != cols, IllegalArgumentException.class,
                    "valueGridInUnit is not a NxM array; row %d has a length of %d, not %d", r, valueGridInUnit[r].length,
                    cols);
            for (int c = 0; c < cols; c++)
                aSi[cols * r + c] = displayUnit.toBaseValue(valueGridInUnit[r][c]);
        }
        return new MatrixNxM<Q, U>(new DenseDoubleDataSi(aSi, rows, cols), displayUnit);
    }

    /**
     * Create a new MatrixNxM with a unit, based on a 2-dimensional quantity grid.
     * @param quantityGrid the matrix values {{a11, a12, ..., a1M}, {aN2, ..., aNM}}, each with their own unit
     * @param displayUnit the display unit to use for the resulting matrix
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new MatrixNxM with a unit
     * @throws IllegalArgumentException when rows or cols is not positive, or when the number of entries in quantityGrid is not
     *             equal to rows*cols
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> MatrixNxM<Q, U> of(final Q[][] quantityGrid,
            final U displayUnit)
    {
        return new MatrixNxM<Q, U>(new DenseDoubleDataSi(quantityGrid), displayUnit);
    }

    @Override
    public MatrixNxM<Q, U> instantiateSi(final double[] siNew)
    {
        return new MatrixNxM<Q, U>(this.dataSi.instantiateNew(siNew), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public MatrixNxM<SIQuantity, SIUnit> invertElements()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new MatrixNxM<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.reciprocal(si())), siUnit);
    }

    @Override
    public MatrixNxM<SIQuantity, SIUnit> multiplyElements(final MatrixNxM<?, ?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new MatrixNxM<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.multiply(si(), other.si())), siUnit);
    }

    @Override
    public MatrixNxM<SIQuantity, SIUnit> divideElements(final MatrixNxM<?, ?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new MatrixNxM<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.divide(si(), other.si())), siUnit);
    }

    // ------------------------------ MATRIX MULTIPLICATION AND AS() --------------------------

    /**
     * Multiply this matrix with another matrix and return the resulting matrix, using the same underlying data storage type. If
     * this matrix has dimensions N x M, the other matrix must have dimensions M x P.
     * <p>
     * <strong>Implementation Note:</strong> Checking of the dimensions is done by
     * {@link MatrixMath#multiply(double[], double[], int, int, int)}.
     * @param otherMat the matrix to multiply with
     * @return the multiplication of this matrix with the other matrix
     * @throws IllegalArgumentException when the sizes of the matrices do not match for multiplication
     */
    public MatrixNxM<SIQuantity, SIUnit> multiply(final MatrixNxM<?, ?> otherMat)
    {
        double[] resultData = MatrixMath.multiply(si(), otherMat.si(), rows(), cols(), otherMat.cols());
        DataGridSi<?> resultDataGrid = this.dataSi.instantiateNew(resultData, rows(), otherMat.cols());
        return new MatrixNxM<SIQuantity, SIUnit>(resultDataGrid,
                getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a 2 x 2 matrix and return the resulting matrix, using the same underlying data storage type.
     * This matrix should have dimensions N x 2, to return an N x 2 matrix.
     * <p>
     * <strong>Implementation Note:</strong> Checking of the dimensions is done by
     * {@link MatrixMath#multiply(double[], double[], int, int, int)}.
     * @param otherMat the matrix to multiply with
     * @return the multiplication of this matrix with the other matrix.
     * @throws IllegalArgumentException when the sizes of the matrices do not match for multiplication
     */
    public MatrixNxM<SIQuantity, SIUnit> multiply(final Matrix2x2<?, ?> otherMat)
    {
        return new MatrixNxM<SIQuantity, SIUnit>(
                this.dataSi.instantiateNew(MatrixMath.multiply(si(), otherMat.si(), rows(), cols(), otherMat.cols())),
                getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a 3 x 3 matrix and return the resulting matrix, using the same underlying data storage type.
     * This matrix should have dimensions N x 3, to return an N x 3 matrix.
     * <p>
     * <strong>Implementation Note:</strong> Checking of the dimensions is done by
     * {@link MatrixMath#multiply(double[], double[], int, int, int)}.
     * @param otherMat the matrix to multiply with
     * @return the multiplication of this matrix with the other matrix.
     * @throws IllegalArgumentException when the sizes of the matrices do not match for multiplication
     */
    public MatrixNxM<SIQuantity, SIUnit> multiply(final Matrix3x3<?, ?> otherMat)
    {
        return new MatrixNxM<SIQuantity, SIUnit>(
                this.dataSi.instantiateNew(MatrixMath.multiply(si(), otherMat.si(), rows(), cols(), otherMat.cols())),
                getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix (N x M) with a square matrix (M x M), returning an (N x M) result when {@code M} equals
     * {@code otherMat.order()}.
     * <p>
     * <strong>Implementation Note:</strong> Checking of the dimensions is done by
     * {@link MatrixMath#multiply(double[], double[], int, int, int)}.
     * @param otherMat the square matrix to multiply with
     * @return the product with correct unit composition
     * @throws IllegalArgumentException when sizes do not conform to matrix multiplication rules
     */
    public MatrixNxM<SIQuantity, SIUnit> multiply(final MatrixNxN<?, ?> otherMat)
    {
        return new MatrixNxM<SIQuantity, SIUnit>(
                this.dataSi.instantiateNew(MatrixMath.multiply(si(), otherMat.si(), rows(), cols(), otherMat.cols())),
                getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this (N x 2) matrix with a column vector of size 2, resulting in a column vector of size N.
     * <p>
     * <strong>Implementation Note:</strong> Checking of the dimensions is done by
     * {@link MatrixMath#multiply(double[], double[], int, int, int)}.
     * @param colVec the 2-element column vector to multiply with
     * @return the column vector with N elements as the result of A·v
     * @throws IllegalArgumentException when sizes do not match
     */
    public VectorN.Col<SIQuantity, SIUnit> multiply(final Vector2.Col<?, ?> colVec)
    {
        final double[] result = MatrixMath.multiply(si(), colVec.si(), rows(), cols(), 1);
        final SIUnit u = getDisplayUnit().siUnit().plus(colVec.getDisplayUnit().siUnit());
        return new VectorN.Col<SIQuantity, SIUnit>(new DenseDoubleDataSi(result, rows(), 1), u);
    }

    /**
     * Multiply this (N x 3) matrix with a column vector of size 3, resulting in a column vector of size N.
     * <p>
     * <strong>Implementation Note:</strong> Checking of the dimensions is done by
     * {@link MatrixMath#multiply(double[], double[], int, int, int)}.
     * @param colVec the 3-element column vector to multiply with
     * @return the column vector with N elements as the result of A·v
     * @throws IllegalArgumentException when sizes do not match
     */
    public VectorN.Col<SIQuantity, SIUnit> multiply(final Vector3.Col<?, ?> colVec)
    {
        final double[] result = MatrixMath.multiply(si(), colVec.si(), rows(), cols(), 1);
        final SIUnit u = getDisplayUnit().siUnit().plus(colVec.getDisplayUnit().siUnit());
        return new VectorN.Col<SIQuantity, SIUnit>(new DenseDoubleDataSi(result, rows(), 1), u);
    }

    /**
     * Multiply this (N x M) matrix with a column vector of size M, resulting in a column vector of size N.
     * <p>
     * <strong>Implementation Note:</strong> Checking of the dimensions is done by
     * {@link MatrixMath#multiply(double[], double[], int, int, int)}.
     * @param colVec the M-element column vector to multiply with
     * @return the column vector with N elements as the result of A·v
     * @throws IllegalArgumentException when sizes do not match
     */
    public VectorN.Col<SIQuantity, SIUnit> multiply(final VectorN.Col<?, ?> colVec)
    {
        final double[] result = MatrixMath.multiply(si(), colVec.si(), rows(), cols(), 1);
        final SIUnit u = getDisplayUnit().siUnit().plus(colVec.getDisplayUnit().siUnit());
        return new VectorN.Col<SIQuantity, SIUnit>(new DenseDoubleDataSi(result, rows(), 1), u);
    }

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
        return new MatrixNxM<TQ, TU>(this.dataSi.instantiateNew(si()), targetUnit);
    }

    /**
     * Return this matrix typed as a {@link Matrix2x2} with the given unit. The shape must be 2 x 2.
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     * @param targetUnit unit for the returned matrix
     * @return a {@code Matrix2x2} with identical SI data and the specified display unit
     * @throws IllegalStateException if this matrix is not 2 x 2
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Matrix2x2<TQ, TU> asMatrix2x2(final TU targetUnit)
    {
        Throw.when(rows() != 2 || cols() != 2, IllegalStateException.class,
                "asMatrix2x2() called, but matrix is no 2x2 but %dx%d", rows(), cols());
        // Convert SI → target display values for the factory:
        final double[] disp = new double[4];
        final double[] data = si();
        for (int i = 0; i < 4; i++)
        {
            disp[i] = targetUnit.fromBaseValue(data[i]);
        }
        return Matrix2x2.of(disp, targetUnit);
    }

    /**
     * Return this matrix typed as a {@link Matrix3x3} with the given unit. The shape must be 3 x 3.
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     * @param targetUnit unit for the returned matrix
     * @return a {@code Matrix3x3} with identical SI data and the specified display unit
     * @throws IllegalStateException if this matrix is not 3 x 3
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Matrix3x3<TQ, TU> asMatrix3x3(final TU targetUnit)
    {
        Throw.when(rows() != 3 || cols() != 3, IllegalStateException.class,
                "asMatrix3x3() called, but matrix is no 3x3 but %dx%d", rows(), cols());
        final double[] disp = new double[9];
        final double[] data = si();
        for (int i = 0; i < 9; i++)
        {
            disp[i] = targetUnit.fromBaseValue(data[i]);
        }
        return Matrix3x3.of(disp, targetUnit);
    }

    /**
     * Return this matrix typed as a {@link MatrixNxN} with the given unit. The shape must be square.
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     * @param targetUnit unit for the returned matrix
     * @return a {@code MatrixNxN} with identical SI data and the specified display unit
     * @throws IllegalStateException if this matrix is not square
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> MatrixNxN<TQ, TU> asMatrixNxN(final TU targetUnit)
    {
        Throw.when(rows() != cols(), IllegalStateException.class, "asMatrixNxN() called, but matrix is no square but %dx%d",
                rows(), cols());
        return new MatrixNxN<TQ, TU>(new DenseDoubleDataSi(si(), rows(), cols()), targetUnit);
    }

    /**
     * Return this matrix as a 2-element column vector with the given unit. Shape must be 2 x 1.
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     * @param targetUnit display unit for the vector
     * @return a {@code Vector2.Col} with identical SI data and the specified display unit
     * @throws IllegalStateException if shape is not 2 x 1
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Vector2.Col<TQ, TU> asVector2Col(final TU targetUnit)
    {
        Throw.when(rows() != 2 || cols() != 1, IllegalStateException.class, "Matrix is not 2x1");
        // Vector2 constructors take display-unit values; convert SI → display.
        final double[] data = si();
        return new Vector2.Col<TQ, TU>(targetUnit.fromBaseValue(data[0]), targetUnit.fromBaseValue(data[1]), targetUnit);
    }

    /**
     * Return this matrix as a 3-element column vector with the given unit. Shape must be 3 x 1.
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     * @param targetUnit display unit for the vector
     * @return a {@code Vector3.Col} with identical SI data and the specified display unit
     * @throws IllegalStateException if shape is not 3 x 1
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Vector3.Col<TQ, TU> asVector3Col(final TU targetUnit)
    {
        Throw.when(rows() != 3 || cols() != 1, IllegalStateException.class, "Matrix is not 3x1");
        final double[] d = si();
        return new Vector3.Col<TQ, TU>(targetUnit.fromBaseValue(d[0]), targetUnit.fromBaseValue(d[1]),
                targetUnit.fromBaseValue(d[2]), targetUnit);
    }

    /**
     * Return this matrix as an N-element column vector with the given unit. Shape must be N x 1.
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     * @param targetUnit display unit for the vector
     * @return a {@code VectorN.Col} with identical SI data and the specified display unit
     * @throws IllegalStateException if {@code cols() != 1}
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> VectorN.Col<TQ, TU> asVectorNCol(final TU targetUnit)
    {
        Throw.when(cols() != 1, IllegalStateException.class, "Matrix is not Nx1");
        return new VectorN.Col<TQ, TU>(new DenseDoubleDataSi(si(), rows(), 1), targetUnit);
    }

    /**
     * Return this matrix as a 2-element row vector with the given unit. Shape must be 1 x 2.
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     * @param targetUnit display unit for the vector
     * @return a {@code Vector2.Row} with identical SI data and the specified display unit
     * @throws IllegalStateException if shape is not 1 x 2
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Vector2.Row<TQ, TU> asVector2Row(final TU targetUnit)
    {
        Throw.when(rows() != 1 || cols() != 2, IllegalStateException.class, "Matrix is not 1x2");
        final double[] d = si();
        return new Vector2.Row<TQ, TU>(targetUnit.fromBaseValue(d[0]), targetUnit.fromBaseValue(d[1]), targetUnit);
    }

    /**
     * Return this matrix as a 3-element row vector with the given unit. Shape must be 1 x 3.
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     * @param targetUnit display unit for the vector
     * @return a {@code Vector3.Row} with identical SI data and the specified display unit
     * @throws IllegalStateException if shape is not 1 x 3
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> Vector3.Row<TQ, TU> asVector3Row(final TU targetUnit)
    {
        Throw.when(rows() != 1 || cols() != 3, IllegalStateException.class, "Matrix is not 1x3");
        final double[] d = si();
        return new Vector3.Row<TQ, TU>(targetUnit.fromBaseValue(d[0]), targetUnit.fromBaseValue(d[1]),
                targetUnit.fromBaseValue(d[2]), targetUnit);
    }

    /**
     * Return this matrix as an N-element row vector with the given unit. Shape must be 1 x N.
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     * @param targetUnit display unit for the vector
     * @return a {@code VectorN.Row} with identical SI data and the specified display unit
     * @throws IllegalStateException if {@code rows() != 1}
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> VectorN.Row<TQ, TU> asVectorNRow(final TU targetUnit)
    {
        Throw.when(rows() != 1, IllegalStateException.class, "Matrix is not 1xN");
        return new VectorN.Row<TQ, TU>(new DenseDoubleDataSi(si(), 1, cols()), targetUnit);
    }

}
