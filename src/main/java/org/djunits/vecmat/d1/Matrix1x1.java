package org.djunits.vecmat.d1;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.NonInvertibleMatrixException;
import org.djunits.vecmat.d2.Vector2;
import org.djunits.vecmat.d3.Vector3;
import org.djunits.vecmat.def.SquareDenseMatrix;
import org.djunits.vecmat.dn.VectorN;
import org.djutils.exceptions.Throw;

/**
 * Matrix1x1 implements a matrix with 1x1 real-valued entries. The matrix is immutable, except for the display unit, which can
 * be changed.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 */
public class Matrix1x1<Q extends Quantity<Q>> extends SquareDenseMatrix<Q, Matrix1x1<Q>, Matrix1x1<SIQuantity>, Matrix1x1<?>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new Matrix1x1 with a unit.
     * @param dataInUnit the matrix values {a11} expressed in the unit
     * @param unit the unit of the data, which will also be used as the display unit
     */
    protected Matrix1x1(final double[] dataInUnit, final Unit<?, Q> unit)
    {
        super(dataInUnit, unit, 1);
    }

    @Override
    public Matrix1x1<Q> instantiateSi(final double[] siNew)
    {
        return new Matrix1x1<Q>(siNew, getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Matrix1x1<SIQuantity> instantiateSi(final double[] siNew, final SIUnit siUnit)
    {
        return new Matrix1x1<SIQuantity>(siNew, siUnit);
    }

    @Override
    public Vector1<Q> getRowVector(final int row)
    {
        checkRow(row);
        return new Vector1<>(si(0, 0), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q> mgetRowVector(final int mRow)
    {
        mcheckRow(mRow);
        return new Vector1<>(si(0, 0), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q> getColumnVector(final int col)
    {
        checkCol(col);
        return new Vector1<>(si(0, 0), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q> mgetColumnVector(final int mCol)
    {
        mcheckCol(mCol);
        return new Vector1<>(si(0, 0), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Vector1<Q> getDiagonalVector() throws IllegalStateException
    {
        return new Vector1<>(si(0, 0), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    @Override
    public Matrix1x1<SIQuantity> inverse() throws NonInvertibleMatrixException
    {
        double[] invData = MatrixMath.inverse(si(), 1);
        return new Matrix1x1<SIQuantity>(invData, getDisplayUnit().siUnit().invert());
    }

    @Override
    public Matrix1x1<SIQuantity> adjugate()
    {
        double[] invData = MatrixMath.adjugate(si(), 1);
        return new Matrix1x1<SIQuantity>(invData, getDisplayUnit().siUnit().pow(order() - 1));
    }

    @Override
    public Matrix1x1<SIQuantity> invertEntries()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new Matrix1x1<SIQuantity>(ArrayMath.reciprocal(si()), siUnit);
    }

    @Override
    public Matrix1x1<SIQuantity> multiplyEntries(final Matrix1x1<?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new Matrix1x1<SIQuantity>(ArrayMath.multiply(si(), other.si()), siUnit);
    }

    @Override
    public Matrix1x1<SIQuantity> divideEntries(final Matrix1x1<?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new Matrix1x1<SIQuantity>(ArrayMath.divide(si(), other.si()), siUnit);
    }

    // --------------------------------- MATRIX MULTIPLICATION ----------------------------------------

    /**
     * Multiply this matrix with another matrix using matrix multiplication and return the result.
     * @param otherMat the matrix to multiply with.
     * @return the matrix from the multiplication with the correct unit
     */
    public Matrix1x1<SIQuantity> multiply(final Matrix1x1<?> otherMat)
    {
        checkMultiply(otherMat);
        double[] resultData = MatrixMath.multiply(si(), otherMat.si(), 1, 1, 1);
        return new Matrix1x1<SIQuantity>(resultData, getDisplayUnit().siUnit().plus(otherMat.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a column vector, resulting in a column vector.
     * @param otherVec the column vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public Vector1<SIQuantity> multiply(final Vector1<?> otherVec)
    {
        checkMultiply(otherVec);
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 1, 1, 1);
        return new Vector1<SIQuantity>(resultData[0], getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a row vector, resulting in a row vector.
     * @param otherVec the row vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public Vector2.Row<SIQuantity> multiply(final Vector2.Row<?> otherVec)
    {
        checkMultiply(otherVec);
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 1, 1, 2);
        return new Vector2.Row<SIQuantity>(resultData[0], resultData[1],
                getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a row vector, resulting in a row vector.
     * @param otherVec the row vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public Vector3.Row<SIQuantity> multiply(final Vector3.Row<?> otherVec)
    {
        checkMultiply(otherVec);
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 1, 1, 3);
        return new Vector3.Row<SIQuantity>(resultData[0], resultData[1], resultData[2],
                getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    /**
     * Multiply this matrix with a row vector, resulting in a row vector.
     * @param otherVec the row vector to multiply with
     * @return the resulting vector from the multiplication
     */
    public VectorN.Row<SIQuantity> multiply(final VectorN.Row<?> otherVec)
    {
        checkMultiply(otherVec);
        double[] resultData = MatrixMath.multiply(si(), otherVec.si(), 1, 1, otherVec.cols());
        return VectorN.Row.of(resultData, getDisplayUnit().siUnit().plus(otherVec.getDisplayUnit().siUnit()));
    }

    @Override
    public Matrix1x1<SIQuantity> multiplyEntries(final Quantity<?> quantity)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
        return new Matrix1x1<SIQuantity>(ArrayMath.scaleBy(si(), quantity.si()), siUnit);
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Create a Matrix1x1 without needing generics.
     * @param xInUnit the a11-value expressed in the display unit
     * @param displayUnit the display unit to use
     * @return a new Matrix1x1 with a unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Matrix1x1<Q> of(final double xInUnit, final Unit<?, Q> displayUnit)
    {
        return new Matrix1x1<>(new double[] {xInUnit}, displayUnit);
    }

    /**
     * Create a Matrix1x1 without needing generics.
     * @param data the a11-value expressed as a quantity
     * @return a new Matrix1x1 with a unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Matrix1x1<Q> of(final Q data)
    {
        Throw.whenNull(data, "data");
        return new Matrix1x1<>(new double[] {data.si()}, data.getDisplayUnit().getBaseUnit())
                .setDisplayUnit(data.getDisplayUnit());
    }

    /**
     * Create a Matrix1x1 without needing generics.
     * @param dataInUnit the a11-value expressed as an array in the display unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @return a new Matrix1x1 with a unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Matrix1x1<Q> of(final double[] dataInUnit, final Unit<?, Q> unit)
    {
        Throw.whenNull(dataInUnit, "dataInUnit");
        Throw.when(dataInUnit.length != 1, IllegalArgumentException.class, "Length of dataInUnit != 1 but %d",
                dataInUnit.length);
        return new Matrix1x1<>(dataInUnit, unit);
    }

    /**
     * Create a Matrix1x1 without needing generics.
     * @param dataSi the a11-value expressed as an array in the SI units
     * @param displayUnit the display unit to use
     * @return a new Matrix1x1 with a unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Matrix1x1<Q> ofSi(final double[] dataSi, final Unit<?, Q> displayUnit)
    {
        Throw.whenNull(dataSi, "dataSi");
        Throw.when(dataSi.length != 1, IllegalArgumentException.class, "Length of dataSi != 1 but %d", dataSi.length);
        return new Matrix1x1<>(dataSi, displayUnit.getBaseUnit()).setDisplayUnit(displayUnit);
    }

    /**
     * Create a Matrix1x1 without needing generics.
     * @param data the matrix values {a11} expressed as an array of quantities
     * @return a new Matrix1x1 with a unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Matrix1x1<Q> of(final Q[] data)
    {
        Throw.whenNull(data, "data");
        Throw.when(data.length != 1, IllegalArgumentException.class, "Length of data != 1 but %d", data.length);
        return new Matrix1x1<>(new double[] {data[0].si()}, data[0].getDisplayUnit().getBaseUnit())
                .setDisplayUnit(data[0].getDisplayUnit());
    }

    /**
     * Create a new Matrix1x1 with a unit, based on a 2-dimensional grid with SI-values.
     * @param gridSi the matrix values {{a11}} expressed in the SI or base unit
     * @param displayUnit the unit of the data, which will also be used as the display unit
     * @param <Q> the quantity type
     * @return a new Matrix1x1 with a unit
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> Matrix1x1<Q> ofSi(final double[][] gridSi, final Unit<?, Q> displayUnit)
    {
        Throw.whenNull(gridSi, "gridSi");
        Throw.when(gridSi.length != 1 || gridSi[0].length != 1, IllegalArgumentException.class, "gridSi is not a 1x1 array");
        return new Matrix1x1<Q>(new double[] {gridSi[0][0]}, displayUnit.getBaseUnit()).setDisplayUnit(displayUnit);
    }

    /**
     * Create a new Matrix1x1 with a unit, based on a 2-dimensional grid.
     * @param gridInUnit the matrix values {{a11}} expressed in the unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @param <Q> the quantity type
     * @return a new Matrix1x1 with a unit
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q>> Matrix1x1<Q> of(final double[][] gridInUnit, final Unit<?, Q> unit)
    {
        Throw.whenNull(gridInUnit, "gridInUnit");
        Throw.when(gridInUnit.length != 1 || gridInUnit[0].length != 1, IllegalArgumentException.class,
                "gridInUnit is not a 1x1 array");
        return new Matrix1x1<Q>(new double[] {gridInUnit[0][0]}, unit);
    }

    /**
     * Create a Matrix1x1 without needing generics.
     * @param grid the matrix values {{a11}} expressed as an array of quantities
     * @return a new Matrix1x1 with a unit
     * @param <Q> the quantity type
     */
    public static <Q extends Quantity<Q>> Matrix1x1<Q> of(final Q[][] grid)
    {
        Throw.whenNull(grid, "grid");
        Throw.when(grid.length != 1, IllegalArgumentException.class, "Length of grid != 1 but %d", grid.length);
        Throw.when(grid[0].length != 1, IllegalArgumentException.class, "Length of grid[0] != 1 but %d", grid.length);
        return new Matrix1x1<>(new double[] {grid[0][0].si()}, grid[0][0].getDisplayUnit().getBaseUnit())
                .setDisplayUnit(grid[0][0].getDisplayUnit());
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
    public <TQ extends Quantity<TQ>> Matrix1x1<TQ> as(final Unit<?, TQ> targetUnit) throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "Matrix1x1.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new Matrix1x1<TQ>(si(), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
    }

}
