package org.djunits.old.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.old.unit.LengthUnit;
import org.djunits.old.unit.PositionUnit;
import org.djunits.old.value.storage.StorageType;
import org.djunits.old.value.vfloat.matrix.base.FloatMatrixRelWithAbs;
import org.djunits.old.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.old.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.old.value.vfloat.scalar.FloatLength;
import org.djunits.old.value.vfloat.scalar.FloatPosition;
import org.djunits.old.value.vfloat.vector.FloatLengthVector;
import org.djunits.old.value.vfloat.vector.FloatPositionVector;
import org.djunits.old.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatLength Matrix.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatLengthMatrix extends FloatMatrixRelWithAbs<PositionUnit, FloatPosition, FloatPositionVector,
        FloatPositionMatrix, LengthUnit, FloatLength, FloatLengthVector, FloatLengthMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a FloatLengthMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatLengthMatrix(final FloatMatrixData data, final LengthUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatLengthMatrix from a float[][] object. The float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLengthMatrix(final float[][] data, final LengthUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatLengthMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatLengthMatrix(final float[][] data, final LengthUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatLengthMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLengthMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, LengthUnit.SI, storageType);
    }

    /**
     * Construct a FloatLengthMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatLengthMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatLength[][] */

    /**
     * Construct a FloatLengthMatrix from an array of an array of FloatLength objects. The FloatLength values are each expressed
     * in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLengthMatrix(final FloatLength[][] data, final LengthUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatLengthMatrix from an array of an array of FloatLength objects. The FloatLength values are each expressed
     * in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatLengthMatrix(final FloatLength[][] data, final LengthUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatLengthMatrix from an array of an array of FloatLength objects. The FloatLength values are each expressed
     * in their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLengthMatrix(final FloatLength[][] data, final StorageType storageType)
    {
        this(data, LengthUnit.SI, storageType);
    }

    /**
     * Construct a FloatLengthMatrix from an array of an array of FloatLength objects. The FloatLength values are each expressed
     * in their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FloatLengthMatrix(final FloatLength[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatLengthMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLengthMatrix(final Collection<FloatSparseValue<LengthUnit, FloatLength>> data, final LengthUnit displayUnit,
            final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatLengthMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatLengthMatrix(final Collection<FloatSparseValue<LengthUnit, FloatLength>> data, final LengthUnit displayUnit,
            final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatLengthMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLengthMatrix(final Collection<FloatSparseValue<LengthUnit, FloatLength>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, LengthUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatLengthMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatLengthMatrix(final Collection<FloatSparseValue<LengthUnit, FloatLength>> data, final int rows, final int cols)
    {
        this(data, LengthUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatLength> getScalarClass()
    {
        return FloatLength.class;
    }

    @Override
    public Class<FloatLengthVector> getVectorClass()
    {
        return FloatLengthVector.class;
    }

    @Override
    public FloatLengthMatrix instantiateMatrix(final FloatMatrixData fmd, final LengthUnit displayUnit)
    {
        return new FloatLengthMatrix(fmd, displayUnit);
    }

    @Override
    public FloatLengthVector instantiateVector(final FloatVectorData fvd, final LengthUnit displayUnit)
    {
        return new FloatLengthVector(fvd, displayUnit);
    }

    @Override
    public FloatLength instantiateScalarSI(final float valueSI, final LengthUnit displayUnit)
    {
        FloatLength result = FloatLength.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public FloatPositionMatrix instantiateMatrixAbs(final FloatMatrixData fmd, final PositionUnit displayUnit)
    {
        return new FloatPositionMatrix(fmd, displayUnit);
    }

    @Override
    public FloatPositionVector instantiateVectorAbs(final FloatVectorData fvd, final PositionUnit displayUnit)
    {
        return new FloatPositionVector(fvd, displayUnit);
    }

    @Override
    public FloatPosition instantiateScalarAbsSI(final float valueSI, final PositionUnit displayUnit)
    {
        FloatPosition result = FloatPosition.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
