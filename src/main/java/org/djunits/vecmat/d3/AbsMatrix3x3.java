package org.djunits.vecmat.d3;

import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Unit;
import org.djunits.vecmat.def.AbsSquareMatrix;
import org.djutils.exceptions.Throw;

/**
 * AbsMatrix3x3 implements a matrix with 3x3 absolute quantities with a reference point. The matrix is immutable, except for the
 * display unit, which can be changed.
 * <p>
 * Copyright (c) 2026-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the corresponding relative quantity type
 */
public class AbsMatrix3x3<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>>
        extends AbsSquareMatrix<A, Q, AbsMatrix3x3<A, Q>, Matrix3x3<Q>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new AbsMatrix3x3 with a display unit and a reference point.
     * @param relativeMatrix the matrix values {a11} expressed in the displayUnit
     * @param reference the reference point for the absolute values
     */
    public AbsMatrix3x3(final Matrix3x3<Q> relativeMatrix, final Reference<?, A, Q> reference)
    {
        super(relativeMatrix, reference);
    }

    @Override
    public AbsMatrix3x3<A, Q> instantiate(final Matrix3x3<Q> relativeMatrix, final Reference<?, A, Q> reference)
    {
        return new AbsMatrix3x3<>(relativeMatrix, reference);
    }

    @Override
    public AbsVector3.Row<A, Q> getRowVector(final int row)
    {
        return new AbsVector3.Row<>(getRelativeVecMat().getRowVector(row), getReference());
    }

    @Override
    public AbsVector3.Row<A, Q> mgetRowVector(final int mRow)
    {
        return new AbsVector3.Row<>(getRelativeVecMat().mgetRowVector(mRow), getReference());
    }

    @Override
    public AbsVector3.Col<A, Q> getColumnVector(final int col)
    {
        return new AbsVector3.Col<>(getRelativeVecMat().getColumnVector(col), getReference());
    }

    @Override
    public AbsVector3.Col<A, Q> mgetColumnVector(final int mCol)
    {
        return new AbsVector3.Col<>(getRelativeVecMat().mgetColumnVector(mCol), getReference());
    }

    @Override
    public AbsVector3.Col<A, Q> getDiagonalVector()
    {
        return new AbsVector3.Col<>(getRelativeVecMat().getDiagonalVector(), getReference());
    }

    // ------------------------------------------ OF METHODS ------------------------------------------

    /**
     * Create an AbsMatrix3x3 without needing generics.
     * @param dataInUnit the matrix values {a11, a12, 13, ..., a31, a32, a33} expressed as an array in the display unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix3x3 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrix3x3<A, Q> of(
            final double[] dataInUnit, final Unit<?, Q> unit, final R reference)
    {
        return new AbsMatrix3x3<>(Matrix3x3.of(dataInUnit, unit), reference);
    }

    /**
     * Create an AbsMatrix3x3 without needing generics.
     * @param dataSi the matrix values {a11, a12, 13, ..., a31, a32, a33} expressed as an array in the SI units
     * @param displayUnit the display unit to use
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix3x3 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrix3x3<A, Q> ofSi(
            final double[] dataSi, final Unit<?, Q> displayUnit, final R reference)
    {
        return new AbsMatrix3x3<>(Matrix3x3.ofSi(dataSi, displayUnit), reference);
    }

    /**
     * Create an AbsMatrix3x3 without needing generics.
     * @param data the matrix values {a11, a12, 13, ..., a31, a32, a33} expressed as an array of quantities
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix3x3 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix3x3<A, Q> of(final Q[] data, final R reference)
    {
        return new AbsMatrix3x3<>(Matrix3x3.of(data), reference);
    }

    /**
     * Create an AbsMatrix3x3 without needing generics.
     * @param absData the {a11, a12, a13, ..., a31, a32, a33} values expressed as an array of absolute quantities
     * @return a new Matrix3x3 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix3x3<A, Q> of(final A[] absData)
    {
        Throw.whenNull(absData, "absData");
        Throw.when(absData.length != 9, IllegalArgumentException.class, "absData.length != 9");
        Throw.whenNull(absData[0], "absData[0]");
        Reference<?, A, Q> reference = absData[0].getReference();
        double[] dataSi = new double[9];
        for (int i = 0; i < 9; i++)
        {
            Throw.whenNull(absData[i], "absData[%d] = null", i);
            Throw.when(!reference.equals(absData[i].getReference()), IllegalArgumentException.class,
                    "absData[%d].reference %s != %s", i, absData[i].getReference().toString(), reference.toString());
            dataSi[i] = absData[i].si();
        }
        return new AbsMatrix3x3<>(Matrix3x3.ofSi(dataSi, absData[0].getDisplayUnit()), absData[0].getReference());
    }

    /**
     * Create an AbsMatrix3x3 with a unit, based on a 2-dimensional grid with SI-values.
     * @param gridSi the matrix values {{a11, a12, 13}, ..., {a31, a32, a33}} expressed in the SI or base unit
     * @param displayUnit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix3x3 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrix3x3<A, Q> ofSi(
            final double[][] gridSi, final Unit<?, Q> displayUnit, final R reference)
    {
        return new AbsMatrix3x3<>(Matrix3x3.ofSi(gridSi, displayUnit), reference);
    }

    /**
     * Create an AbsMatrix3x3 with a unit, based on a 2-dimensional grid.
     * @param gridInUnit the matrix values {{a11, a12, 13}, ..., {a31, a32, a33}} expressed in the unit
     * @param unit the unit of the data, which will also be used as the display unit
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix3x3 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>, R extends Reference<R, A, Q>> AbsMatrix3x3<A, Q> of(
            final double[][] gridInUnit, final Unit<?, Q> unit, final R reference)
    {
        return new AbsMatrix3x3<>(Matrix3x3.of(gridInUnit, unit), reference);
    }

    /**
     * Create an AbsMatrix3x3 without needing generics.
     * @param grid the matrix values {{a11, a12, 13}, ..., {a31, a32, a33}} expressed as an array of quantities
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix3x3 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix3x3<A, Q> of(final Q[][] grid, final R reference)
    {
        return new AbsMatrix3x3<>(Matrix3x3.of(grid), reference);
    }

    /**
     * Create an AbsMatrix3x3 without needing generics.
     * @param absGrid the {{a11, a12, a13}, ..., {a31, a32, a33}} values expressed as an grid [][] of absolute quantities
     * @return a new Matrix3x3 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix3x3<A, Q> of(final A[][] absGrid)
    {
        Throw.whenNull(absGrid, "absData");
        Throw.when(absGrid.length != 3, IllegalArgumentException.class, "absData.length != 3");
        Throw.whenNull(absGrid[0], "absData[0]");
        Throw.when(absGrid[0].length != 3, IllegalArgumentException.class, "absData[0].length != 3");
        Throw.whenNull(absGrid[0][0], "absData[0][0]");
        Reference<?, A, Q> reference = absGrid[0][0].getReference();
        double[] dataSi = new double[9];
        for (int r = 0; r < 3; r++)
        {
            Throw.whenNull(absGrid[r], "absData[%d] = null", r);
            Throw.when(absGrid[r].length != 3, IllegalArgumentException.class, "absData[%d].length != 3", r);
            for (int c = 0; c < 3; c++)
            {
                Throw.whenNull(absGrid[r][c], "absData[%d][%d] = null", r, c);
                Throw.when(!reference.equals(absGrid[r][c].getReference()), IllegalArgumentException.class,
                        "absData[%d][%d].reference %s != %s", r, c, absGrid[r][c].getReference().toString(),
                        reference.toString());
                dataSi[r * 3 + c] = absGrid[r][c].si();
            }
        }
        return new AbsMatrix3x3<>(Matrix3x3.ofSi(dataSi, absGrid[0][0].getDisplayUnit()), reference);
    }

    /**
     * Create an AbsMatrix3x3 without needing generics.
     * @param relativeMatrix the relative matrix with values relative to the reference point
     * @param reference the reference point for the absolute quantities
     * @return a new Matrix3x3 with a unit
     * @param <A> the absolute quantity type
     * @param <Q> the quantity type
     * @param <R> the reference type
     */
    public static <A extends AbsQuantity<A, Q, R>, Q extends Quantity<Q>,
            R extends Reference<R, A, Q>> AbsMatrix3x3<A, Q> of(final Matrix3x3<Q> relativeMatrix, final R reference)
    {
        return new AbsMatrix3x3<>(relativeMatrix, reference);
    }

}
