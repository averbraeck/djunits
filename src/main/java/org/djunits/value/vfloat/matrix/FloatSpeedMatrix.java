package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.SpeedUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatSpeed;
import org.djunits.value.vfloat.vector.FloatSpeedVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatSpeedMatrix, a matrix of values with a SpeedUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatSpeedMatrix extends FloatMatrixRel<SpeedUnit, FloatSpeed, FloatSpeedVector, FloatSpeedMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatSpeedMatrix from an internal data object.
     * @param data FloatMatrixData; the internal data object for the matrix
     * @param displayUnit SpeedUnit; the display unit of the matrix data
     */
    public FloatSpeedMatrix(final FloatMatrixData data, final SpeedUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatSpeedMatrix from a float[][] object. The float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data float[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit SpeedUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatSpeedMatrix(final float[][] data, final SpeedUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatSpeedMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix
     * @param displayUnit SpeedUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatSpeedMatrix(final float[][] data, final SpeedUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatSpeedMatrix from a float[][] object with SI-unit values.
     * @param data float[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatSpeedMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, SpeedUnit.SI, storageType);
    }

    /**
     * Construct a FloatSpeedMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data float[][]; the data for the matrix, in SI units
     */
    public FloatSpeedMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Speed[][] */

    /**
     * Construct a FloatSpeedMatrix from an array of an array of FloatSpeed objects. The FloatSpeed values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data FloatSpeed[][]; the data for the matrix
     * @param displayUnit SpeedUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatSpeedMatrix(final FloatSpeed[][] data, final SpeedUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatSpeedMatrix from an array of an array of FloatSpeed objects. The FloatSpeed values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatSpeed[][]; the data for the matrix
     * @param displayUnit SpeedUnit; the display unit of the values when printing
     */
    public FloatSpeedMatrix(final FloatSpeed[][] data, final SpeedUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatSpeedMatrix from an array of an array of FloatSpeed objects. The FloatSpeed values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array of an array.
     * @param data FloatSpeed[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatSpeedMatrix(final FloatSpeed[][] data, final StorageType storageType)
    {
        this(data, SpeedUnit.SI, storageType);
    }

    /**
     * Construct a FloatSpeedMatrix from an array of an array of FloatSpeed objects. The FloatSpeed values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatSpeed[][]; the data for the matrix
     */
    public FloatSpeedMatrix(final FloatSpeed[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatSpeedMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit SpeedUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatSpeedMatrix(final Collection<FloatSparseValue<SpeedUnit, FloatSpeed>> data, final SpeedUnit displayUnit,
            final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatSpeedMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit SpeedUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatSpeedMatrix(final Collection<FloatSparseValue<SpeedUnit, FloatSpeed>> data, final SpeedUnit displayUnit,
            final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatSpeedMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatSpeedMatrix(final Collection<FloatSparseValue<SpeedUnit, FloatSpeed>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, SpeedUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatSpeedMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatSpeedMatrix(final Collection<FloatSparseValue<SpeedUnit, FloatSpeed>> data, final int rows, final int cols)
    {
        this(data, SpeedUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatSpeed> getScalarClass()
    {
        return FloatSpeed.class;
    }

    @Override
    public Class<FloatSpeedVector> getVectorClass()
    {
        return FloatSpeedVector.class;
    }

    @Override
    public FloatSpeedMatrix instantiateMatrix(final FloatMatrixData fmd, final SpeedUnit displayUnit)
    {
        return new FloatSpeedMatrix(fmd, displayUnit);
    }

    @Override
    public FloatSpeedVector instantiateVector(final FloatVectorData fvd, final SpeedUnit displayUnit)
    {
        return new FloatSpeedVector(fvd, displayUnit);
    }

    @Override
    public FloatSpeed instantiateScalarSI(final float valueSI, final SpeedUnit displayUnit)
    {
        FloatSpeed result = FloatSpeed.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
