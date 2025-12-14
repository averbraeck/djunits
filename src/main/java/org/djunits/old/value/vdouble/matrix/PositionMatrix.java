package org.djunits.old.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.old.unit.LengthUnit;
import org.djunits.old.unit.PositionUnit;
import org.djunits.old.value.storage.StorageType;
import org.djunits.old.value.vdouble.matrix.base.DoubleMatrixAbs;
import org.djunits.old.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.old.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.old.value.vdouble.scalar.Length;
import org.djunits.old.value.vdouble.scalar.Position;
import org.djunits.old.value.vdouble.vector.LengthVector;
import org.djunits.old.value.vdouble.vector.PositionVector;
import org.djunits.old.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Position Matrix.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class PositionMatrix extends
        DoubleMatrixAbs<PositionUnit, Position, PositionVector, PositionMatrix, LengthUnit, Length, LengthVector, LengthMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a PositionMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public PositionMatrix(final DoubleMatrixData data, final PositionUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a PositionMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PositionMatrix(final double[][] data, final PositionUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a PositionMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public PositionMatrix(final double[][] data, final PositionUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a PositionMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PositionMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, PositionUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a PositionMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public PositionMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Position[][] */

    /**
     * Construct a PositionMatrix from an array of an array of Position objects. The Position values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PositionMatrix(final Position[][] data, final PositionUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a PositionMatrix from an array of an array of Position objects. The Position values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public PositionMatrix(final Position[][] data, final PositionUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a PositionMatrix from an array of an array of Position objects. The Position values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data
     * as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PositionMatrix(final Position[][] data, final StorageType storageType)
    {
        this(data, PositionUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a PositionMatrix from an array of an array of Position objects. The Position values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public PositionMatrix(final Position[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a PositionMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PositionMatrix(final Collection<DoubleSparseValue<PositionUnit, Position>> data, final PositionUnit displayUnit,
            final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a PositionMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the storage
     * type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public PositionMatrix(final Collection<DoubleSparseValue<PositionUnit, Position>> data, final PositionUnit displayUnit,
            final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a PositionMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PositionMatrix(final Collection<DoubleSparseValue<PositionUnit, Position>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, PositionUnit.BASE.getStandardUnit(), rows, cols, storageType);
    }

    /**
     * Construct a PositionMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public PositionMatrix(final Collection<DoubleSparseValue<PositionUnit, Position>> data, final int rows, final int cols)
    {
        this(data, PositionUnit.BASE.getStandardUnit(), rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<Position> getScalarClass()
    {
        return Position.class;
    }

    @Override
    public Class<PositionVector> getVectorClass()
    {
        return PositionVector.class;
    }

    @Override
    public PositionMatrix instantiateMatrix(final DoubleMatrixData dmd, final PositionUnit displayUnit)
    {
        return new PositionMatrix(dmd, displayUnit);
    }

    @Override
    public PositionVector instantiateVector(final DoubleVectorData dvd, final PositionUnit displayUnit)
    {
        return new PositionVector(dvd, displayUnit);
    }

    @Override
    public Position instantiateScalarSI(final double valueSI, final PositionUnit displayUnit)
    {
        Position result = Position.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public LengthMatrix instantiateMatrixRel(final DoubleMatrixData dmd, final LengthUnit displayUnit)
    {
        return new LengthMatrix(dmd, displayUnit);
    }

    @Override
    public LengthVector instantiateVectorRel(final DoubleVectorData dvd, final LengthUnit displayUnit)
    {
        return new LengthVector(dvd, displayUnit);
    }

    @Override
    public Length instantiateScalarRelSI(final double valueSI, final LengthUnit displayUnit)
    {
        Length result = Length.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
