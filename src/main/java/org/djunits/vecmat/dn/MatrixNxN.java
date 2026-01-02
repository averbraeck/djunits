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
import org.djutils.exceptions.Throw;

/**
 * MatrixNxN implements a square matrix with NxN real-valued entries. The matrix is immutable, except for the display unit,
 * which can be changed. Internal storage can be float or double, and dense or sparse. This class extends MatrixNxM into a
 * square matrix.<br>
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
     * Create a new NxM Matrix with a unit, based on a DataGrid storage object that contains SI data.
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

}
