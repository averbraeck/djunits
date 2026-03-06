package org.djunits.vecmat.dn;

import java.util.Objects;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.Matrix;
import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djunits.vecmat.operations.Hadamard;
import org.djunits.vecmat.operations.SquareMatrixOps;
import org.djunits.vecmat.storage.DataGrid;
import org.djunits.vecmat.storage.DenseDoubleData;
import org.djutils.exceptions.Throw;

/**
 * MatrixNxN implements a square matrix with NxN real-valued entries. The matrix is immutable, except for the display unit,
 * which can be changed. Internal storage can be float or double, and dense or sparse. <br>
 * <br>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public class MatrixNxN<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> extends Matrix<Q, U, MatrixNxN<Q, U>>
        implements SquareMatrixOps<Q, U, MatrixNxN<Q, U>>, Hadamard<MatrixNxN<?, ?>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The data of the matrix, in SI unit. */
    private final DataGrid<?> dataSi;

    /**
     * Create a new NxN Matrix with a unit, based on a DataGrid storage object that contains SI data.
     * @param dataSi the data of the matrix, in SI unit.
     * @param displayUnit the display unit to use
     * @throws IllegalArgumentException when the number of rows or columns does not have a positive value
     */
    public MatrixNxN(final DataGrid<?> dataSi, final U displayUnit)
    {
        super(displayUnit, dataSi.rows(), dataSi.cols());
        Throw.whenNull(dataSi, "dataSi");
        Throw.when(dataSi.rows() != dataSi.cols(), IllegalArgumentException.class, "Data for the NxN matrix is not square");
        this.dataSi = dataSi;
    }

    /**
     * Create a new MatrixNxN with a unit, based on a 1-dimensional array.
     * @param valueArrayInUnit the matrix values {a11, a12, ..., aN1, aN32, ..., aNN} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new MatrixNxN with a unit
     * @throws IllegalArgumentException when the number of entries in the valueArray is not a perfect square
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> MatrixNxN<Q, U> of(final double[] valueArrayInUnit,
            final U displayUnit)
    {
        Throw.whenNull(valueArrayInUnit, "valueArrayInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        int len = valueArrayInUnit.length;
        // This check works for any (positive) int. The largest perfect square fitting in an int is 46340^2 = 2,147,395,600,
        // slightly below Integer.MAX_VALUE = 2,147,483,647. All integers up to that value have square roots <= 46340, which
        // easily fit in the IEEE 754 double mantissa (53 bits), so perfect squares have exact square roots in double.
        // Therefore, (int) Math.sqrt(n) is guaranteed correct for any 32-bit integer when checking perfect squares. The
        // complexity of this check is O(1).
        int n = (int) Math.sqrt(len);
        Throw.when(len != n * n, IllegalArgumentException.class,
                "valueArrayInUnit does not contain a square number of entries (%d)", len);
        double[] aSi = new double[valueArrayInUnit.length];
        for (int i = 0; i < valueArrayInUnit.length; i++)
            aSi[i] = displayUnit.toBaseValue(valueArrayInUnit[i]);
        return new MatrixNxN<Q, U>(new DenseDoubleData(aSi, n, n), displayUnit);
    }

    /**
     * Create a new MatrixNxN with a unit, based on a 2-dimensional grid.
     * @param valueGridInUnit the matrix values {{a11, a12, a13}, ..., {a31, a32, a33}} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new MatrixNxN with a unit
     * @throws IllegalArgumentException when valueGrid has 0 rows, or when the number of columns for one of the rows is not
     *             equal to the number of rows
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> MatrixNxN<Q, U> of(final double[][] valueGridInUnit,
            final U displayUnit)
    {
        Throw.whenNull(valueGridInUnit, "valueGridInUnit");
        Throw.whenNull(displayUnit, "displayUnit");
        int n = valueGridInUnit.length;
        Throw.when(n == 0, IllegalArgumentException.class, "valueGridInUnit has 0 rows");
        double[] aSi = new double[n * n];
        for (int r = 0; r < valueGridInUnit.length; r++)
        {
            Throw.when(valueGridInUnit[r].length != n, IllegalArgumentException.class,
                    "valueGridInUnit is not a NxN array; row %d has a length of %d, not %d", r, valueGridInUnit[r].length, n);
            for (int c = 0; c < n; c++)
                aSi[n * r + c] = displayUnit.toBaseValue(valueGridInUnit[r][c]);
        }
        return new MatrixNxN<Q, U>(new DenseDoubleData(aSi, n, n), displayUnit);
    }

    @Override
    public MatrixNxN<Q, U> instantiateSi(final double[] siNew)
    {
        return new MatrixNxN<Q, U>(this.dataSi.instantiateNew(siNew), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public double[] si()
    {
        return this.dataSi.getDataArray();
    }

    @Override
    public double si(final int row, final int col)
    {
        // internal storage is 0-based, user access is 1-based
        return this.dataSi.get(row - 1, col - 1);
    }

    @Override
    public MatrixNxN<SIQuantity, SIUnit> inverse() throws NonInvertibleMatrixException
    {
        double[] invData = MatrixMath.inverse(si(), order());
        return new MatrixNxN<SIQuantity, SIUnit>(this.dataSi.instantiateNew(invData), getDisplayUnit().siUnit().invert());
    }

    @Override
    public MatrixNxN<SIQuantity, SIUnit> adjugate()
    {
        double[] invData = MatrixMath.adjugate(si(), order());
        return new MatrixNxN<SIQuantity, SIUnit>(this.dataSi.instantiateNew(invData),
                getDisplayUnit().siUnit().pow(order() - 1));
    }

    @Override
    public MatrixNxN<SIQuantity, SIUnit> invertElements()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new MatrixNxN<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.reciprocal(si())), siUnit);
    }

    @Override
    public MatrixNxN<SIQuantity, SIUnit> multiplyElements(final MatrixNxN<?, ?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new MatrixNxN<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.multiply(si(), other.si())), siUnit);
    }

    @Override
    public MatrixNxN<SIQuantity, SIUnit> divideElements(final MatrixNxN<?, ?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new MatrixNxN<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.divide(si(), other.si())), siUnit);
    }

    // ------------------------------ MATRIX MULTIPLICATION AND AS() --------------------------

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(this.dataSi);
        return result;
    }

    @Override
    @SuppressWarnings("checkstyle:needbraces")
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        MatrixNxN<?, ?> other = (MatrixNxN<?, ?>) obj;
        return Objects.equals(this.dataSi, other.dataSi);
    }

    /**
     * Multiply this matrix with another matrix using matrix multiplication and return the result.
     * <p>
     * The unit of the result is the SI-unit “sum” of this matrix and the other matrix (i.e., {@code U.plus(V)} on the
     * underlying {@link SIUnit}s).
         * @param otherMat the right-hand matrix to multiply with
     * @return the product matrix with the correct SI unit
     */
    public MatrixNxN<SIQuantity, SIUnit> multiply(final MatrixNxN<?, ?> otherMat)
    {
        final int n = order();
        final double[] resultData = MatrixMath.multiply(si(), otherMat.si(), n, n, n);
        final SIUnit resultUnit = getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit());
        return new MatrixNxN<SIQuantity, SIUnit>(this.dataSi.instantiateNew(resultData), resultUnit);
    }

    /**
     * Multiply this matrix with a column vector, resulting in a column vector.
     * <p>
     * The unit of the result is the SI-unit “sum” of this matrix and the vector (i.e., {@code U.plus(V)} on the underlying
     * {@link SIUnit}s).
         * @param otherVec the column vector to multiply with (size {@code N})
     * @return the resulting column vector from the multiplication
     * @throws IllegalArgumentException if the vector size does not equal {@code order()}
     */
    public VectorN.Col<SIQuantity, SIUnit> multiply(final VectorN.Col<?, ?> otherVec)
    {
        final int n = order();
        // Defensive check in case VectorN.Col#getData shape is inconsistent
        if (otherVec.size() != n)
        {
            throw new IllegalArgumentException("Vector size " + otherVec.size() + " != matrix order " + n);
        }
        final double[] resultData = MatrixMath.multiply(si(), otherVec.si(), n, n, 1);
        final SIUnit resultUnit = getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit());
        return new VectorN.Col<SIQuantity, SIUnit>(new DenseDoubleData(resultData, n, 1), resultUnit);
    }

    /**
     * Return the matrix "as" a matrix with a known quantity, using a unit to express the result in.
     * <p>
     * The SI units of this matrix and the target unit must match; otherwise an {@link IllegalArgumentException} is thrown. The
     * returned matrix shares the SI values but has the specified display unit.
         * @param <TQ> target quantity type
     * @param <TU> target unit type
     * @param targetUnit the unit to convert the matrix to
     * @return a matrix typed in the target quantity with the specified display unit
     * @throws IllegalArgumentException when the units do not match
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> MatrixNxN<TQ, TU> as(final TU targetUnit)
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "MatrixNxN.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new MatrixNxN<TQ, TU>(this.dataSi.instantiateNew(si()), targetUnit);
    }

}
