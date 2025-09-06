package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRelWithAbs;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatTemperature;
import org.djunits.value.vfloat.vector.FloatAbsoluteTemperatureVector;
import org.djunits.value.vfloat.vector.FloatTemperatureVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatTemperature Matrix.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FloatTemperatureMatrix extends
        FloatMatrixRelWithAbs<AbsoluteTemperatureUnit, FloatAbsoluteTemperature, FloatAbsoluteTemperatureVector,
                FloatAbsoluteTemperatureMatrix, TemperatureUnit, FloatTemperature, FloatTemperatureVector,
                FloatTemperatureMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a FloatTemperatureMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatTemperatureMatrix(final FloatMatrixData data, final TemperatureUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatTemperatureMatrix from a float[][] object. The float values are expressed in the displayUnit, and will
     * be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatTemperatureMatrix(final float[][] data, final TemperatureUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatTemperatureMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatTemperatureMatrix(final float[][] data, final TemperatureUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatTemperatureMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatTemperatureMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, TemperatureUnit.SI, storageType);
    }

    /**
     * Construct a FloatTemperatureMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatTemperatureMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatTemperature[][] */

    /**
     * Construct a FloatTemperatureMatrix from an array of an array of FloatTemperature objects. The FloatTemperature values are
     * each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatTemperatureMatrix(final FloatTemperature[][] data, final TemperatureUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatTemperatureMatrix from an array of an array of FloatTemperature objects. The FloatTemperature values are
     * each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatTemperatureMatrix(final FloatTemperature[][] data, final TemperatureUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatTemperatureMatrix from an array of an array of FloatTemperature objects. The FloatTemperature values are
     * each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatTemperatureMatrix(final FloatTemperature[][] data, final StorageType storageType)
    {
        this(data, TemperatureUnit.SI, storageType);
    }

    /**
     * Construct a FloatTemperatureMatrix from an array of an array of FloatTemperature objects. The FloatTemperature values are
     * each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FloatTemperatureMatrix(final FloatTemperature[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatTemperatureMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatTemperatureMatrix(final Collection<FloatSparseValue<TemperatureUnit, FloatTemperature>> data,
            final TemperatureUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatTemperatureMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatTemperatureMatrix(final Collection<FloatSparseValue<TemperatureUnit, FloatTemperature>> data,
            final TemperatureUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatTemperatureMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatTemperatureMatrix(final Collection<FloatSparseValue<TemperatureUnit, FloatTemperature>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, TemperatureUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatTemperatureMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatTemperatureMatrix(final Collection<FloatSparseValue<TemperatureUnit, FloatTemperature>> data, final int rows,
            final int cols)
    {
        this(data, TemperatureUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatTemperature> getScalarClass()
    {
        return FloatTemperature.class;
    }

    @Override
    public Class<FloatTemperatureVector> getVectorClass()
    {
        return FloatTemperatureVector.class;
    }

    @Override
    public FloatTemperatureMatrix instantiateMatrix(final FloatMatrixData fmd, final TemperatureUnit displayUnit)
    {
        return new FloatTemperatureMatrix(fmd, displayUnit);
    }

    @Override
    public FloatTemperatureVector instantiateVector(final FloatVectorData fvd, final TemperatureUnit displayUnit)
    {
        return new FloatTemperatureVector(fvd, displayUnit);
    }

    @Override
    public FloatTemperature instantiateScalarSI(final float valueSI, final TemperatureUnit displayUnit)
    {
        FloatTemperature result = FloatTemperature.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public FloatAbsoluteTemperatureMatrix instantiateMatrixAbs(final FloatMatrixData fmd,
            final AbsoluteTemperatureUnit displayUnit)
    {
        return new FloatAbsoluteTemperatureMatrix(fmd, displayUnit);
    }

    @Override
    public FloatAbsoluteTemperatureVector instantiateVectorAbs(final FloatVectorData fvd,
            final AbsoluteTemperatureUnit displayUnit)
    {
        return new FloatAbsoluteTemperatureVector(fvd, displayUnit);
    }

    @Override
    public FloatAbsoluteTemperature instantiateScalarAbsSI(final float valueSI, final AbsoluteTemperatureUnit displayUnit)
    {
        FloatAbsoluteTemperature result = FloatAbsoluteTemperature.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
