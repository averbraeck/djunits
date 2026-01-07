package org.djunits.vecmat.dn;

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
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
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
     * @param valueArray the matrix values {a11, a12, ..., aN1, aN32, ..., aNN} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new MatrixNxN with a unit
     * @throws IllegalArgumentException when the number of entries in the valueArray is not a perfect square
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> MatrixNxN<Q, U> of(final double[] valueArray,
            final U displayUnit)
    {
        Throw.whenNull(valueArray, "valueArray");
        Throw.whenNull(displayUnit, "displayUnit");
        int len = valueArray.length;
        // This check works for any (positive) int. The largest perfect square fitting in an int is 46340^2 = 2,147,395,600,
        // slightly below Integer.MAX_VALUE = 2,147,483,647. All integers up to that value have square roots <= 46340, which
        // easily fit in the IEEE 754 double mantissa (53 bits), so perfect squares have exact square roots in double.
        // Therefore, (int) Math.sqrt(n) is guaranteed correct for any 32-bit integer when checking perfect squares. The
        // complexity of this check is O(1).
        int n = (int) Math.sqrt(len);
        Throw.when(len != n * n, IllegalArgumentException.class, "valueArray does not contain a square number of entries (%d)",
                len);
        double[] aSi = new double[valueArray.length];
        for (int i = 0; i < valueArray.length; i++)
            aSi[i] = displayUnit.toBaseValue(valueArray[i]);
        return new MatrixNxN<Q, U>(new DenseDoubleData(aSi, n, n), displayUnit);
    }

    /**
     * Create a new MatrixNxN with a unit, based on a 2-dimensional grid.
     * @param valueGrid the matrix values {{a11, a12, a13}, ..., {a31, a32, a33}} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new MatrixNxN with a unit
     * @throws IllegalArgumentException when valueGrid has 0 rows, or when the number of columns for one of the rows is not
     *             equal to the number of rows
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> MatrixNxN<Q, U> of(final double[][] valueGrid,
            final U displayUnit)
    {
        Throw.whenNull(valueGrid, "valueGrid");
        Throw.whenNull(displayUnit, "displayUnit");
        int n = valueGrid.length;
        Throw.when(n == 0, IllegalArgumentException.class, "valueGrid has 0 rows");
        double[] aSi = new double[n * n];
        for (int r = 0; r < valueGrid.length; r++)
        {
            Throw.when(valueGrid[r].length != n, IllegalArgumentException.class,
                    "valueGrid is not a NxN array; row %d has a length of %d, not %d", r, valueGrid[r].length, n);
            for (int c = 0; c < n; c++)
                aSi[n * r + c] = displayUnit.toBaseValue(valueGrid[r][c]);
        }
        return new MatrixNxN<Q, U>(new DenseDoubleData(aSi, n, n), displayUnit);
    }

    @Override
    public MatrixNxN<Q, U> instantiate(final double[] siNew)
    {
        return new MatrixNxN<Q, U>(this.dataSi.instantiate(siNew), getDisplayUnit());
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
        return this.dataSi.get(row + 1, col + 1);
    }

    @Override
    public MatrixNxN<SIQuantity, SIUnit> inverse() throws NonInvertibleMatrixException
    {
        double[] invData = MatrixMath.inverse(si(), 3);
        return new MatrixNxN<SIQuantity, SIUnit>(this.dataSi.instantiate(invData), getDisplayUnit().siUnit().invert());
    }

    @Override
    public MatrixNxN<SIQuantity, SIUnit> adjugate()
    {
        double[] invData = MatrixMath.adjugate(si(), 3);
        return new MatrixNxN<SIQuantity, SIUnit>(this.dataSi.instantiate(invData), getDisplayUnit().siUnit().pow(order() - 1));
    }

    @Override
    public MatrixNxN<SIQuantity, SIUnit> invertElements()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new MatrixNxN<SIQuantity, SIUnit>(this.dataSi.instantiate(ArrayMath.reciprocal(si())), siUnit);
    }

    @Override
    public MatrixNxN<SIQuantity, SIUnit> multiplyElements(final MatrixNxN<?, ?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new MatrixNxN<SIQuantity, SIUnit>(this.dataSi.instantiate(ArrayMath.multiply(si(), other.si())), siUnit);
    }

    @Override
    public MatrixNxN<SIQuantity, SIUnit> divideElements(final MatrixNxN<?, ?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new MatrixNxN<SIQuantity, SIUnit>(this.dataSi.instantiate(ArrayMath.divide(si(), other.si())), siUnit);
    }

    // ------------------------------ MATRIX MULTIPLICATION AND AS() --------------------------

    // TODO matrix multiplication and as()
}
