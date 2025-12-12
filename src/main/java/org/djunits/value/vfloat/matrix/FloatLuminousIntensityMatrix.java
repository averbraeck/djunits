package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatLuminousIntensity;
import org.djunits.value.vfloat.vector.FloatLuminousIntensityVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatLuminousIntensityMatrix, a matrix of values with a LuminousIntensityUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatLuminousIntensityMatrix extends FloatMatrixRel<LuminousIntensityUnit, FloatLuminousIntensity,
        FloatLuminousIntensityVector, FloatLuminousIntensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatLuminousIntensityMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatLuminousIntensityMatrix(final FloatMatrixData data, final LuminousIntensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatLuminousIntensityMatrix from a float[][] object. The float values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousIntensityMatrix(final float[][] data, final LuminousIntensityUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatLuminousIntensityMatrix from a float[][] object. The float values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatLuminousIntensityMatrix(final float[][] data, final LuminousIntensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatLuminousIntensityMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousIntensityMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, LuminousIntensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatLuminousIntensityMatrix from a float[][] object with SI-unit values. Assume that the StorageType is
     * DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatLuminousIntensityMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH LuminousIntensity[][] */

    /**
     * Construct a FloatLuminousIntensityMatrix from an array of an array of FloatLuminousIntensity objects. The
     * FloatLuminousIntensity values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousIntensityMatrix(final FloatLuminousIntensity[][] data, final LuminousIntensityUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatLuminousIntensityMatrix from an array of an array of FloatLuminousIntensity objects. The
     * FloatLuminousIntensity values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing. Assume that the StorageType is DENSE since we offer the data as an array of
     * an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatLuminousIntensityMatrix(final FloatLuminousIntensity[][] data, final LuminousIntensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatLuminousIntensityMatrix from an array of an array of FloatLuminousIntensity objects. The
     * FloatLuminousIntensity values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousIntensityMatrix(final FloatLuminousIntensity[][] data, final StorageType storageType)
    {
        this(data, LuminousIntensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatLuminousIntensityMatrix from an array of an array of FloatLuminousIntensity objects. The
     * FloatLuminousIntensity values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. Assume that the StorageType is DENSE since we offer the data as an array of an
     * array.
     * @param data the data for the matrix
     */
    public FloatLuminousIntensityMatrix(final FloatLuminousIntensity[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatLuminousIntensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousIntensityMatrix(final Collection<FloatSparseValue<LuminousIntensityUnit, FloatLuminousIntensity>> data,
            final LuminousIntensityUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatLuminousIntensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatLuminousIntensityMatrix(final Collection<FloatSparseValue<LuminousIntensityUnit, FloatLuminousIntensity>> data,
            final LuminousIntensityUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatLuminousIntensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousIntensityMatrix(final Collection<FloatSparseValue<LuminousIntensityUnit, FloatLuminousIntensity>> data,
            final int rows, final int cols, final StorageType storageType)
    {
        this(data, LuminousIntensityUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatLuminousIntensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a
     * collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatLuminousIntensityMatrix(final Collection<FloatSparseValue<LuminousIntensityUnit, FloatLuminousIntensity>> data,
            final int rows, final int cols)
    {
        this(data, LuminousIntensityUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatLuminousIntensity> getScalarClass()
    {
        return FloatLuminousIntensity.class;
    }

    @Override
    public Class<FloatLuminousIntensityVector> getVectorClass()
    {
        return FloatLuminousIntensityVector.class;
    }

    @Override
    public FloatLuminousIntensityMatrix instantiateMatrix(final FloatMatrixData fmd, final LuminousIntensityUnit displayUnit)
    {
        return new FloatLuminousIntensityMatrix(fmd, displayUnit);
    }

    @Override
    public FloatLuminousIntensityVector instantiateVector(final FloatVectorData fvd, final LuminousIntensityUnit displayUnit)
    {
        return new FloatLuminousIntensityVector(fvd, displayUnit);
    }

    @Override
    public FloatLuminousIntensity instantiateScalarSI(final float valueSI, final LuminousIntensityUnit displayUnit)
    {
        FloatLuminousIntensity result = FloatLuminousIntensity.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
