package org.djunits.vecmat.d2;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djunits.vecmat.def.SquareDenseMatrix;
import org.djutils.exceptions.Throw;

/**
 * Matrix2x2 implements a matrix with 2x2 real-valued entries. The matrix is immutable, except for the display unit, which can
 * be changed.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 */
public class Matrix2x2<Q extends Quantity<Q>> extends SquareDenseMatrix<Q, Matrix2x2<Q>, Matrix2x2<SIQuantity>, Matrix2x2<?>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new Matrix2x2 with a unit.
     * @param dataInUnit the matrix values {a11, a12, a21, a22} expressed in the unit
     * @param unit the unit of the data, also functions as display unit for the matrix
     */
    protected Matrix2x2(final double[] dataInUnit, final Unit<?, Q> unit)
    {
        super(dataInUnit, unit, 2);
    }

    @Override
    public Matrix2x2<Q> instantiateSi(final double[] siNew)
    {
        return new Matrix2x2<Q>(siNew, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Matrix2x2<SIQuantity> instantiateSi(final double[] siNew, final SIUnit siUnit)
    {
        return new Matrix2x2<SIQuantity>(siNew, siUnit);
    }

    @Override
    public Vector2.Row<Q> getRowVector(final int row)
    {
        checkRow(row);
        return new Vector2.Row<Q>(si(row, 0), si(row, 1), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector2.Row<Q> mgetRowVector(final int mRow)
    {
        mcheckRow(mRow);
        return new Vector2.Row<Q>(msi(mRow, 1), msi(mRow, 2), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector2.Col<Q> getColumnVector(final int col)
    {
        checkCol(col);
        return new Vector2.Col<Q>(si(0, col), si(1, col), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector2.Col<Q> mgetColumnVector(final int mCol)
    {
        mcheckCol(mCol);
        return new Vector2.Col<Q>(msi(1, mCol), msi(2, mCol), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector2.Col<Q> getDiagonalVector() throws IllegalStateException
    {
        return new Vector2.Col<Q>(si(0, 0), si(1, 1), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Matrix2x2<SIQuantity> inverse() throws NonInvertibleMatrixException
    {
        double[] invData = MatrixMath.inverse(si(), 2);
        return new Matrix2x2<SIQuantity>(invData, getDisplayUnit().siUnit().invert());
    }

    @Override
    public Matrix2x2<SIQuantity> adjugate()
    {
        double[] invData = MatrixMath.adjugate(si(), 2);
        return new Matrix2x2<SIQuantity>(invData, getDisplayUnit().siUnit().pow(order() - 1));
    }

    @Override
    public Matrix2x2<SIQuantity> invertEntries()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new Matrix2x2<SIQuantity>(ArrayMath.reciprocal(si()), siUnit);
    }

    @Override
    public Matrix2x2<SIQuantity> multiplyEntries(final Matrix2x2<?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new Matrix2x2<SIQuantity>(ArrayMath.multiply(si(), other.si()), siUnit);
    }

    @Override
    public Matrix2x2<SIQuantity> divideEntries(final Matrix2x2<?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new Matrix2x2<SIQuantity>(ArrayMath.divide(si(), other.si()), siUnit);
    }

    // ------------------------------ MATRIX MULTIPLICATION -------------------------------------

    /**
     * Multiply this matrix with another matrix using matrix multiplication and return the result.
     * @param otherMat the matrix to multiply with.
     * @return the matrix from the multiplication with the correct unit
     */
    public Matrix2x2<SIQuantity> multiply(final Matrix2x2<?> otherMat)
    {
        checkMultiply(otherMat);
        double[] resultData = MatrixMath.multiply(si(), otherMat.si(), 2, 2, 2);
        return new Matrix2x2<SIQuantity>(resultData, getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a column vector, resulting in a column vector.
     * @param otherVec the column vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public Vector2.Col<SIQuantity> multiply(final Vector2.Col<?> otherVec)
    {
        checkMultiply(otherVec);
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 2, 2, 1);
        return new Vector2.Col<SIQuantity>(resultData[0], resultData[1],
                getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    @Override
    public Matrix2x2<SIQuantity> multiplyEntries(final Quantity<?> quantity)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
        return new Matrix2x2<SIQuantity>(ArrayMath.scaleBy(si(), quantity.si()), siUnit);
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Create a new Matrix2x2 with a unit, based on a row-major array with values in the given unit.
     * @param dataInUnit the matrix values {a11, a12, a21, a22} expressed in the unit
     * @param unit the unit of the data, also used as the display unit
     * @param <Q> the quantity type
     * @return a new Matrix2x2 with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain 2x2 = 4 values
     */
    public static <Q extends Quantity<Q>> Matrix2x2<Q> of(final double[] dataInUnit, final Unit<?, Q> unit)
    {
        Throw.whenNull(dataInUnit, "dataInUnit");
        Throw.whenNull(unit, "unit");
        Throw.when(dataInUnit.length != 4, IllegalArgumentException.class, "Length of array != 4 but %d", dataInUnit.length);
        return new Matrix2x2<Q>(dataInUnit, unit);
    }

    /**
     * Create a Matrix2x2 without needing generics, based on a row-major array with SI-values.
     * @param dataSi the matrix values {a11, a12, a21, a22} as an array using SI units
     * @param displayUnit the display unit to use
     * @return a new Matrix2x2 with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when dataSi does not contain 2x2 = 4 values
     */
    public static <Q extends Quantity<Q>> Matrix2x2<Q> ofSi(final double[] dataSi, final Unit<?, Q> displayUnit)
    {
        Throw.whenNull(dataSi, "dataSi");
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(dataSi.length != 4, IllegalArgumentException.class, "Length of dataSi != 4 but %d", dataSi.length);
        return new Matrix2x2<>(dataSi, displayUnit.getBaseUnit()).setDisplayUnit(displayUnit);
    }

    /**
     * Create a Matrix2x2 without needing generics, based on a row-major array of quantities. The unit is taken from the first
     * quantity in the array.
     * @param data the matrix values {a11, a12, a21, a22} expressed as an array of quantities
     * @return a new Matrix2x2 with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when data does not contain 2x2 = 4 quantities
     */
    public static <Q extends Quantity<Q>> Matrix2x2<Q> of(final Q[] data)
    {
        Throw.whenNull(data, "data");
        Throw.when(data.length != 4, IllegalArgumentException.class, "Length of data != 4 but %d", data.length);
        double[] dataSi = new double[4];
        for (int i = 0; i < 4; i++)
        {
            Throw.whenNull(data[i], "data[%d] = null", i);
            dataSi[i] = data[i].si();
        }
        return new Matrix2x2<>(dataSi, data[0].getDisplayUnit().getBaseUnit()).setDisplayUnit(data[0].getDisplayUnit());
    }

    /**
     * Create a new Matrix2x2 with a unit, based on a 2-dimensional grid with SI-values.
     * @param gridSi the matrix values {{a11, a12}, {a21, a22}} expressed in the SI or base unit
     * @param displayUnit the unit of the data, which will also be used as the display unit
     * @param <Q> the quantity type
     * @return a new Matrix2x2 with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain 2x2 = 4 values
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> Matrix2x2<Q> ofSi(final double[][] gridSi, final Unit<?, Q> displayUnit)
    {
        Throw.whenNull(gridSi, "gridSi");
        Throw.whenNull(displayUnit, "displayUnit");
        Throw.when(gridSi.length != 2, IllegalArgumentException.class, "gridSi does not have 2 rows");
        double[] dataSi = new double[4];
        for (int r = 0; r < 2; r++)
        {
            Throw.whenNull(gridSi[r], "gridSi[%d][] = null", r);
            Throw.when(gridSi[r].length != 2, IllegalArgumentException.class, "gridSi is not a 2x2 array");
            for (int c = 0; c < 2; c++)
            {
                dataSi[r * 2 + c] = gridSi[r][c];
            }
        }
        return new Matrix2x2<>(dataSi, displayUnit.getBaseUnit()).setDisplayUnit(displayUnit);
    }

    /**
     * Create a new Matrix2x2 with a unit, based on a 2-dimensional grid with values in the given unit.
     * @param gridInUnit the matrix values {{a11, a12}, {a21, a22}} expressed in the unit
     * @param unit the unit of the values, also used as the display unit
     * @param <Q> the quantity type
     * @return a new Matrix2x2 with a unit
     * @throws IllegalArgumentException when dataInUnit does not contain 2x2 = 4 values
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> Matrix2x2<Q> of(final double[][] gridInUnit, final Unit<?, Q> unit)
    {
        Throw.whenNull(gridInUnit, "gridInUnit");
        Throw.whenNull(unit, "unit");
        Throw.when(gridInUnit.length != 2, IllegalArgumentException.class, "gridInUnit does not have 2 rows");
        double[] dataInUnit = new double[4];
        for (int r = 0; r < 2; r++)
        {
            Throw.whenNull(gridInUnit[r], "gridInUnit[%d][] = null", r);
            Throw.when(gridInUnit[r].length != 2, IllegalArgumentException.class, "gridInUnit is not a 2x2 array");
            for (int c = 0; c < 2; c++)
            {
                dataInUnit[r * 2 + c] = gridInUnit[r][c];
            }
        }
        return new Matrix2x2<>(dataInUnit, unit);
    }

    /**
     * Create a Matrix2x2 without needing generics, based on a 2-dimensional grid of quantities. The unit is taken from the
     * first quantity in the grid.
     * @param grid the matrix values {{a11, a12}, {a21, a22}} expressed as a 2-dimensional array of quantities
     * @return a new Matrix2x2 with a unit
     * @param <Q> the quantity type
     * @throws IllegalArgumentException when dataInUnit does not contain 2x2 = 4 quantities
     */
    public static <Q extends Quantity<Q>> Matrix2x2<Q> of(final Q[][] grid)
    {
        Throw.whenNull(grid, "grid");
        Throw.when(grid.length != 2, IllegalArgumentException.class, "grid does not have 2 rows");
        double[] dataSi = new double[4];
        for (int r = 0; r < 2; r++)
        {
            Throw.whenNull(grid[r], "grid[%d][] = null", r);
            Throw.when(grid[r].length != 2, IllegalArgumentException.class, "grid is not a 2x2 array");
            for (int c = 0; c < 2; c++)
            {
                Throw.whenNull(grid[r][c], "grid[%d][%d] = null", r, c);
                dataSi[r * 2 + c] = grid[r][c].si();
            }
        }
        return new Matrix2x2<>(dataSi, grid[0][0].getDisplayUnit().getBaseUnit()).setDisplayUnit(grid[0][0].getDisplayUnit());
    }

    // ------------------------------------------ AS METHODS ------------------------------------------

    /**
     * Return the matrix 'as' a matrix with a known quantity, using a unit to express the result in. Throw a Runtime exception
     * when the SI units of this vector and the target vector do not match.
     * @param targetUnit the unit to convert the matrix to
     * @return a matrix typed in the target matrix class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     */
    public <TQ extends Quantity<TQ>> Matrix2x2<TQ> as(final Unit<?, TQ> targetUnit) throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "Matrix2x2.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new Matrix2x2<TQ>(si(), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
    }

}
