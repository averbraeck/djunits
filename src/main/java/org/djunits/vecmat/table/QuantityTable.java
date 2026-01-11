package org.djunits.vecmat.table;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.vecmat.Matrix;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.dn.MatrixNxN;
import org.djunits.vecmat.dnxm.MatrixNxM;
import org.djunits.vecmat.operations.Hadamard;
import org.djunits.vecmat.storage.DataGrid;
import org.djutils.exceptions.Throw;

/**
 * QuantityTable is a two-dimensonal table with quantities. The QuantityTable allows for Hadamard (element-wise) operations, but
 * not for vector/matrix operations. A QuantityTable can be transformed to a MatrixNxM or vice versa. <br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public class QuantityTable<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> extends Matrix<Q, U, QuantityTable<Q, U>>
        implements Hadamard<QuantityTable<?, ?>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /** The data of the matrix, in SI unit. */
    private final DataGrid<?> dataSi;

    /**
     * Create a new NxM Matrix with a unit, based on a DataGrid storage object that contains SI data.
     * @param dataSi the data of the matrix, in SI unit.
     * @param displayUnit the display unit to use
     */
    public QuantityTable(final DataGrid<?> dataSi, final U displayUnit)
    {
        super(displayUnit, dataSi.rows(), dataSi.cols());
        this.dataSi = dataSi;
    }

    @Override
    public QuantityTable<Q, U> instantiate(final double[] siNew)
    {
        return new QuantityTable<Q, U>(this.dataSi.instantiate(siNew), getDisplayUnit());
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
    public QuantityTable<?, ?> invertElements()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new QuantityTable<SIQuantity, SIUnit>(this.dataSi.instantiate(ArrayMath.reciprocal(si())), siUnit);
    }

    @Override
    public QuantityTable<?, ?> multiplyElements(final QuantityTable<?, ?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new QuantityTable<SIQuantity, SIUnit>(this.dataSi.instantiate(ArrayMath.multiply(si(), other.si())), siUnit);
    }

    @Override
    public QuantityTable<?, ?> divideElements(final QuantityTable<?, ?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new QuantityTable<SIQuantity, SIUnit>(this.dataSi.instantiate(ArrayMath.divide(si(), other.si())), siUnit);
    }

    // --------------------------------------- AS() FUNCTIONS ---------------------------------

    /**
     * Return the matrix 'as' a matrix with a known quantity, using a unit to express the result in. Throw a Runtime exception
     * when the SI units of this vector and the target vector do not match.
     * @param targetUnit the unit to convert the matrix to
     * @return a matrix typed in the target matrix class
     * @throws IllegalArgumentException when the units do not match
     * @param <TQ> target quantity type
     * @param <TU> target unit type
     */
    public <TQ extends Quantity<TQ, TU>, TU extends UnitInterface<TU, TQ>> QuantityTable<TQ, TU> as(final TU targetUnit)
            throws IllegalArgumentException
    {
        Throw.when(!getDisplayUnit().siUnit().equals(targetUnit.siUnit()), IllegalArgumentException.class,
                "QuantityTable.as(%s) called, but units do not match: %s <> %s", targetUnit,
                getDisplayUnit().siUnit().getDisplayAbbreviation(), targetUnit.siUnit().getDisplayAbbreviation());
        return new QuantityTable<TQ, TU>(this.dataSi.instantiate(si()), targetUnit);
    }

    /**
     * Return the matrix as a strongly-typed 2x2 matrix.
     * @return the 2x2 matrix
     * @throws IllegalStateException when the current matrix is not a 2x2 matrix
     */
    public Matrix2x2<Q, U> asMatrix2x2()
    {
        Throw.when(rows() != 2 || cols() != 2, IllegalStateException.class,
                "asMatrix2x2() called, but matrix is no 2x2 but %dx%d", rows(), cols());
        return Matrix2x2.of(si(), getDisplayUnit());
    }

    /**
     * Return the matrix as a strongly-typed 3x3 matrix.
     * @return the 3x3 matrix
     * @throws IllegalStateException when the current matrix is not a 3x3 matrix
     */
    public Matrix3x3<Q, U> asMatrix3x3()
    {
        Throw.when(rows() != 3 || cols() != 3, IllegalStateException.class,
                "asMatrix3x3() called, but matrix is no 3x3 but %dx%d", rows(), cols());
        return Matrix3x3.of(si(), getDisplayUnit());
    }

    /**
     * Return the matrix as a strongly-typed NxN matrix.
     * @return the NxN matrix
     * @throws IllegalStateException when the current matrix is not an NxN matrix
     */
    public MatrixNxN<Q, U> asMatrixNxN()
    {
        Throw.when(rows() != cols(), IllegalStateException.class, "asMatrixNxN() called, but matrix is no square but %dx%d",
                rows(), cols());
        return MatrixNxN.of(si(), getDisplayUnit());
    }

    /**
     * Return the matrix as a strongly-typed NxM matrix.
     * @return the NxM matrix
     */
    public MatrixNxM<Q, U> asMatrixNxM()
    {
        return MatrixNxM.of(si(), rows(), cols(), getDisplayUnit());
    }

}
