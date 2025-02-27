package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.LengthUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixAbs;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatLength;
import org.djunits.value.vfloat.scalar.FloatPosition;
import org.djunits.value.vfloat.vector.FloatLengthVector;
import org.djunits.value.vfloat.vector.FloatPositionVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatPosition Matrix.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatPositionMatrix extends FloatMatrixAbs<PositionUnit, FloatPosition, FloatPositionVector, FloatPositionMatrix,
        LengthUnit, FloatLength, FloatLengthVector, FloatLengthMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a PositionMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatPositionMatrix(final FloatMatrixData data, final PositionUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatPositionMatrix from a float[][] object. The float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPositionMatrix(final float[][] data, final PositionUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatPositionMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatPositionMatrix(final float[][] data, final PositionUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatPositionMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPositionMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, PositionUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a FloatPositionMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatPositionMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatPosition[][] */

    /**
     * Construct a FloatPositionMatrix from an array of an array of FloatPosition objects. The FloatPosition values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPositionMatrix(final FloatPosition[][] data, final PositionUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatPositionMatrix from an array of an array of FloatPosition objects. The FloatPosition values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatPositionMatrix(final FloatPosition[][] data, final PositionUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatPositionMatrix from an array of an array of FloatPosition objects. The FloatPosition values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPositionMatrix(final FloatPosition[][] data, final StorageType storageType)
    {
        this(data, PositionUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a FloatPositionMatrix from an array of an array of FloatPosition objects. The FloatPosition values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FloatPositionMatrix(final FloatPosition[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatPositionMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPositionMatrix(final Collection<FloatSparseValue<PositionUnit, FloatPosition>> data,
            final PositionUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatPositionMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatPositionMatrix(final Collection<FloatSparseValue<PositionUnit, FloatPosition>> data,
            final PositionUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatPositionMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatPositionMatrix(final Collection<FloatSparseValue<PositionUnit, FloatPosition>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, PositionUnit.BASE.getStandardUnit(), rows, cols, storageType);
    }

    /**
     * Construct a FloatPositionMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatPositionMatrix(final Collection<FloatSparseValue<PositionUnit, FloatPosition>> data, final int rows,
            final int cols)
    {
        this(data, PositionUnit.BASE.getStandardUnit(), rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatPosition> getScalarClass()
    {
        return FloatPosition.class;
    }

    @Override
    public Class<FloatPositionVector> getVectorClass()
    {
        return FloatPositionVector.class;
    }

    @Override
    public FloatPositionMatrix instantiateMatrix(final FloatMatrixData fmd, final PositionUnit displayUnit)
    {
        return new FloatPositionMatrix(fmd, displayUnit);
    }

    @Override
    public FloatPositionVector instantiateVector(final FloatVectorData fvd, final PositionUnit displayUnit)
    {
        return new FloatPositionVector(fvd, displayUnit);
    }

    @Override
    public FloatPosition instantiateScalarSI(final float valueSI, final PositionUnit displayUnit)
    {
        FloatPosition result = FloatPosition.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public FloatLengthMatrix instantiateMatrixRel(final FloatMatrixData fmd, final LengthUnit displayUnit)
    {
        return new FloatLengthMatrix(fmd, displayUnit);
    }

    @Override
    public FloatLengthVector instantiateVectorRel(final FloatVectorData fvd, final LengthUnit displayUnit)
    {
        return new FloatLengthVector(fvd, displayUnit);
    }

    @Override
    public FloatLength instantiateScalarRelSI(final float valueSI, final LengthUnit displayUnit)
    {
        FloatLength result = FloatLength.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
