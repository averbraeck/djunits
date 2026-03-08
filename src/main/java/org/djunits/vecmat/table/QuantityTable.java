package org.djunits.vecmat.table;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.ArrayMath;
import org.djunits.vecmat.DataGridMatrix;
import org.djunits.vecmat.d2.Matrix2x2;
import org.djunits.vecmat.d3.Matrix3x3;
import org.djunits.vecmat.dn.MatrixNxN;
import org.djunits.vecmat.dnxm.MatrixNxM;
import org.djunits.vecmat.operations.Hadamard;
import org.djunits.vecmat.storage.DataGridSi;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djutils.exceptions.Throw;

/**
 * QuantityTable is a two-dimensonal table with quantities. The QuantityTable allows for Hadamard (element-wise) operations, but
 * not for vector/matrix operations. A QuantityTable can be transformed to a MatrixNxM or vice versa.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 */
public class QuantityTable<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> extends
        DataGridMatrix<Q, U, QuantityTable<Q, U>> implements Hadamard<QuantityTable<?, ?>, QuantityTable<SIQuantity, SIUnit>>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new NxM QuantityTable with a unit, based on a DataGrid storage object. This constructor assumes dataSi stores SI
     * values. Note: NO safe copy is made.
     * @param dataSi the data of the matrix, in SI unit.
     * @param displayUnit the display unit to use
     */
    public QuantityTable(final DataGridSi<?> dataSi, final U displayUnit)
    {
        super(dataSi, displayUnit);
    }

    /**
     * Create a new NxM QuantityTable with a unit, based on a 1-dimensional double array.
     * @param valueArrayInUnit the matrix values {a11, a12, ..., a1M, aN2, ..., aNM} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @param rows the number of rows in the valueArray
     * @param cols the number of columns in the valueArray
     * @return a new NxM QuantityTable with a unit
     * @throws IllegalArgumentException when rows or cols is not positive, or when the number of entries in valueArray is not
     *             equal to rows*cols
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> QuantityTable<Q, U> of(
            final double[] valueArrayInUnit, final int rows, final int cols, final U displayUnit)
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
        return new QuantityTable<Q, U>(new DenseDoubleDataSi(aSi, rows, cols), displayUnit);
    }

    /**
     * Create a new NxM QuantityTable with a unit, based on a 2-dimensional double grid.
     * @param valueGridInUnit the matrix values {{a11, a12, a1M}, ..., {aN1, aN2, aNM}} expressed in the display unit
     * @param displayUnit the display unit to use
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new NxM QuantityTable with a unit
     * @throws IllegalArgumentException when valueGrid has 0 rows, or when the number of columns for one of the rows is not
     *             equal to the number of columns in another row
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>,
            U extends UnitInterface<U, Q>> QuantityTable<Q, U> of(final double[][] valueGridInUnit, final U displayUnit)
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
        return new QuantityTable<Q, U>(new DenseDoubleDataSi(aSi, rows, cols), displayUnit);
    }

    /**
     * Create a new NxM QuantityTable with a unit, based on a 2-dimensional quantity grid.
     * @param quantityGrid the matrix values {{a11, a12, ..., a1M}, {aN2, ..., aNM}}, each with their own unit
     * @param displayUnit the display unit to use for the resulting matrix
     * @param <Q> the quantity type
     * @param <U> the unit type
     * @return a new NxM QuantityTable with a unit
     * @throws IllegalArgumentException when rows or cols is not positive, or when the number of entries in quantityGrid is not
     *             equal to rows*cols
     */
    @SuppressWarnings("checkstyle:needbraces")
    public static <Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>> QuantityTable<Q, U> of(final Q[][] quantityGrid,
            final U displayUnit)
    {
        return new QuantityTable<Q, U>(new DenseDoubleDataSi(quantityGrid), displayUnit);
    }

    @Override
    public QuantityTable<Q, U> instantiateSi(final double[] siNew)
    {
        return new QuantityTable<Q, U>(this.dataSi.instantiateNew(siNew), getDisplayUnit().getBaseUnit())
                .setDisplayUnit(getDisplayUnit());
    }

    @Override
    public QuantityTable<SIQuantity, SIUnit> invertElements()
    {
        SIUnit siUnit = getDisplayUnit().siUnit().invert();
        return new QuantityTable<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.reciprocal(si())), siUnit);
    }

    @Override
    public QuantityTable<SIQuantity, SIUnit> multiplyElements(final QuantityTable<?, ?> other)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new QuantityTable<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.multiply(si(), other.si())), siUnit);
    }

    @Override
    public QuantityTable<SIQuantity, SIUnit> divideElements(final QuantityTable<?, ?> other)
    {
        SIUnit siUnit = SIUnit.subtract(getDisplayUnit().siUnit(), other.getDisplayUnit().siUnit());
        return new QuantityTable<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.divide(si(), other.si())), siUnit);
    }

    @Override
    public QuantityTable<SIQuantity, SIUnit> multiplyElements(final Quantity<?, ?> quantity)
    {
        SIUnit siUnit = SIUnit.add(getDisplayUnit().siUnit(), quantity.getDisplayUnit().siUnit());
        return new QuantityTable<SIQuantity, SIUnit>(this.dataSi.instantiateNew(ArrayMath.scaleBy(si(), quantity.si())),
                siUnit);
    }

    // --------------------------------------- AS() FUNCTIONS ---------------------------------

    /**
     * Return the QuantityTable 'as' a QuantityTable with a known quantity, using a unit to express the result in. Throw a
     * Runtime exception when the SI units of this vector and the target vector do not match.
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
        return new QuantityTable<TQ, TU>(this.dataSi.instantiateNew(si()), targetUnit.getBaseUnit()).setDisplayUnit(targetUnit);
    }

    /**
     * Return the QuantityTable as a strongly-typed 2x2 matrix.
     * @return the 2x2 matrix
     * @throws IllegalStateException when the current matrix is not a 2x2 matrix
     */
    public Matrix2x2<Q, U> asMatrix2x2()
    {
        Throw.when(rows() != 2 || cols() != 2, IllegalStateException.class,
                "asMatrix2x2() called, but matrix is no 2x2 but %dx%d", rows(), cols());
        return Matrix2x2.of(si(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the QuantityTable as a strongly-typed 3x3 matrix.
     * @return the 3x3 matrix
     * @throws IllegalStateException when the current matrix is not a 3x3 matrix
     */
    public Matrix3x3<Q, U> asMatrix3x3()
    {
        Throw.when(rows() != 3 || cols() != 3, IllegalStateException.class,
                "asMatrix3x3() called, but matrix is no 3x3 but %dx%d", rows(), cols());
        return Matrix3x3.of(si(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the QuantityTable as a strongly-typed NxN matrix.
     * @return the NxN matrix
     * @throws IllegalStateException when the current matrix is not an NxN matrix
     */
    public MatrixNxN<Q, U> asMatrixNxN()
    {
        Throw.when(rows() != cols(), IllegalStateException.class, "asMatrixNxN() called, but matrix is no square but %dx%d",
                rows(), cols());
        return MatrixNxN.of(si(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

    /**
     * Return the QuantityTable as a strongly-typed NxM matrix.
     * @return the NxM matrix
     */
    public MatrixNxM<Q, U> asMatrixNxM()
    {
        return MatrixNxM.of(si(), rows(), cols(), getDisplayUnit().getBaseUnit()).setDisplayUnit(getDisplayUnit());
    }

}
