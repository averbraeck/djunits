package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatCatalyticActivity;
import org.djunits.value.vfloat.vector.FloatCatalyticActivityVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatCatalyticActivityMatrix, a matrix of values with a CatalyticActivityUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatCatalyticActivityMatrix extends FloatMatrixRel<CatalyticActivityUnit, FloatCatalyticActivity,
        FloatCatalyticActivityVector, FloatCatalyticActivityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatCatalyticActivityMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatCatalyticActivityMatrix(final FloatMatrixData data, final CatalyticActivityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatCatalyticActivityMatrix from a float[][] object. The float values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatCatalyticActivityMatrix(final float[][] data, final CatalyticActivityUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatCatalyticActivityMatrix from a float[][] object. The float values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatCatalyticActivityMatrix(final float[][] data, final CatalyticActivityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatCatalyticActivityMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatCatalyticActivityMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, CatalyticActivityUnit.SI, storageType);
    }

    /**
     * Construct a FloatCatalyticActivityMatrix from a float[][] object with SI-unit values. Assume that the StorageType is
     * DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatCatalyticActivityMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH CatalyticActivity[][] */

    /**
     * Construct a FloatCatalyticActivityMatrix from an array of an array of FloatCatalyticActivity objects. The
     * FloatCatalyticActivity values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatCatalyticActivityMatrix(final FloatCatalyticActivity[][] data, final CatalyticActivityUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatCatalyticActivityMatrix from an array of an array of FloatCatalyticActivity objects. The
     * FloatCatalyticActivity values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing. Assume that the StorageType is DENSE since we offer the data as an array of
     * an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatCatalyticActivityMatrix(final FloatCatalyticActivity[][] data, final CatalyticActivityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatCatalyticActivityMatrix from an array of an array of FloatCatalyticActivity objects. The
     * FloatCatalyticActivity values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatCatalyticActivityMatrix(final FloatCatalyticActivity[][] data, final StorageType storageType)
    {
        this(data, CatalyticActivityUnit.SI, storageType);
    }

    /**
     * Construct a FloatCatalyticActivityMatrix from an array of an array of FloatCatalyticActivity objects. The
     * FloatCatalyticActivity values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. Assume that the StorageType is DENSE since we offer the data as an array of an
     * array.
     * @param data the data for the matrix
     */
    public FloatCatalyticActivityMatrix(final FloatCatalyticActivity[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatCatalyticActivityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatCatalyticActivityMatrix(final Collection<FloatSparseValue<CatalyticActivityUnit, FloatCatalyticActivity>> data,
            final CatalyticActivityUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatCatalyticActivityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatCatalyticActivityMatrix(final Collection<FloatSparseValue<CatalyticActivityUnit, FloatCatalyticActivity>> data,
            final CatalyticActivityUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatCatalyticActivityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatCatalyticActivityMatrix(final Collection<FloatSparseValue<CatalyticActivityUnit, FloatCatalyticActivity>> data,
            final int rows, final int cols, final StorageType storageType)
    {
        this(data, CatalyticActivityUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatCatalyticActivityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a
     * collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatCatalyticActivityMatrix(final Collection<FloatSparseValue<CatalyticActivityUnit, FloatCatalyticActivity>> data,
            final int rows, final int cols)
    {
        this(data, CatalyticActivityUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatCatalyticActivity> getScalarClass()
    {
        return FloatCatalyticActivity.class;
    }

    @Override
    public Class<FloatCatalyticActivityVector> getVectorClass()
    {
        return FloatCatalyticActivityVector.class;
    }

    @Override
    public FloatCatalyticActivityMatrix instantiateMatrix(final FloatMatrixData fmd, final CatalyticActivityUnit displayUnit)
    {
        return new FloatCatalyticActivityMatrix(fmd, displayUnit);
    }

    @Override
    public FloatCatalyticActivityVector instantiateVector(final FloatVectorData fvd, final CatalyticActivityUnit displayUnit)
    {
        return new FloatCatalyticActivityVector(fvd, displayUnit);
    }

    @Override
    public FloatCatalyticActivity instantiateScalarSI(final float valueSI, final CatalyticActivityUnit displayUnit)
    {
        FloatCatalyticActivity result = FloatCatalyticActivity.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
