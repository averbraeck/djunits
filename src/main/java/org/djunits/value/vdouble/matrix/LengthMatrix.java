package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRelWithAbs;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.Position;
import org.djunits.value.vdouble.vector.LengthVector;
import org.djunits.value.vdouble.vector.PositionVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Length Matrix.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class LengthMatrix extends DoubleMatrixRelWithAbs<PositionUnit, Position, PositionVector, PositionMatrix, LengthUnit,
        Length, LengthVector, LengthMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a LengthMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public LengthMatrix(final DoubleMatrixData data, final LengthUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a LengthMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LengthMatrix(final double[][] data, final LengthUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a LengthMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public LengthMatrix(final double[][] data, final LengthUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a LengthMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LengthMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, LengthUnit.SI, storageType);
    }

    /**
     * Construct a LengthMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public LengthMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Length[][] */

    /**
     * Construct a LengthMatrix from an array of an array of Length objects. The Length values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LengthMatrix(final Length[][] data, final LengthUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a LengthMatrix from an array of an array of Length objects. The Length values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public LengthMatrix(final Length[][] data, final LengthUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a LengthMatrix from an array of an array of Length objects. The Length values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data as
     * an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LengthMatrix(final Length[][] data, final StorageType storageType)
    {
        this(data, LengthUnit.SI, storageType);
    }

    /**
     * Construct a LengthMatrix from an array of an array of Length objects. The Length values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType
     * is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public LengthMatrix(final Length[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a LengthMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LengthMatrix(final Collection<DoubleSparseValue<LengthUnit, Length>> data, final LengthUnit displayUnit,
            final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a LengthMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the storage
     * type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public LengthMatrix(final Collection<DoubleSparseValue<LengthUnit, Length>> data, final LengthUnit displayUnit,
            final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a LengthMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LengthMatrix(final Collection<DoubleSparseValue<LengthUnit, Length>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, LengthUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a LengthMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public LengthMatrix(final Collection<DoubleSparseValue<LengthUnit, Length>> data, final int rows, final int cols)
    {
        this(data, LengthUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<Length> getScalarClass()
    {
        return Length.class;
    }

    @Override
    public Class<LengthVector> getVectorClass()
    {
        return LengthVector.class;
    }

    @Override
    public LengthMatrix instantiateMatrix(final DoubleMatrixData dmd, final LengthUnit displayUnit)
    {
        return new LengthMatrix(dmd, displayUnit);
    }

    @Override
    public LengthVector instantiateVector(final DoubleVectorData dvd, final LengthUnit displayUnit)
    {
        return new LengthVector(dvd, displayUnit);
    }

    @Override
    public Length instantiateScalarSI(final double valueSI, final LengthUnit displayUnit)
    {
        Length result = Length.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public PositionMatrix instantiateMatrixAbs(final DoubleMatrixData dmd, final PositionUnit displayUnit)
    {
        return new PositionMatrix(dmd, displayUnit);
    }

    @Override
    public PositionVector instantiateVectorAbs(final DoubleVectorData dvd, final PositionUnit displayUnit)
    {
        return new PositionVector(dvd, displayUnit);
    }

    @Override
    public Position instantiateScalarAbsSI(final double valueSI, final PositionUnit displayUnit)
    {
        Position result = Position.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
