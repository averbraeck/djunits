package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.ElectricalInductanceUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatElectricalInductance;
import org.djunits.value.vfloat.vector.FloatElectricalInductanceVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatElectricalInductanceMatrix, a matrix of values with a ElectricalInductanceUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FloatElectricalInductanceMatrix extends FloatMatrixRel<ElectricalInductanceUnit, FloatElectricalInductance,
        FloatElectricalInductanceVector, FloatElectricalInductanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatElectricalInductanceMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatElectricalInductanceMatrix(final FloatMatrixData data, final ElectricalInductanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatElectricalInductanceMatrix from a float[][] object. The float values are expressed in the displayUnit,
     * and will be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalInductanceMatrix(final float[][] data, final ElectricalInductanceUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatElectricalInductanceMatrix from a float[][] object. The float values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatElectricalInductanceMatrix(final float[][] data, final ElectricalInductanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatElectricalInductanceMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalInductanceMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, ElectricalInductanceUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalInductanceMatrix from a float[][] object with SI-unit values. Assume that the StorageType is
     * DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatElectricalInductanceMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH ElectricalInductance[][] */

    /**
     * Construct a FloatElectricalInductanceMatrix from an array of an array of FloatElectricalInductance objects. The
     * FloatElectricalInductance values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalInductanceMatrix(final FloatElectricalInductance[][] data, final ElectricalInductanceUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatElectricalInductanceMatrix from an array of an array of FloatElectricalInductance objects. The
     * FloatElectricalInductance values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing. Assume that the StorageType is DENSE since we offer the data as an array of
     * an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatElectricalInductanceMatrix(final FloatElectricalInductance[][] data, final ElectricalInductanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatElectricalInductanceMatrix from an array of an array of FloatElectricalInductance objects. The
     * FloatElectricalInductance values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalInductanceMatrix(final FloatElectricalInductance[][] data, final StorageType storageType)
    {
        this(data, ElectricalInductanceUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalInductanceMatrix from an array of an array of FloatElectricalInductance objects. The
     * FloatElectricalInductance values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. Assume that the StorageType is DENSE since we offer the data as an array of an
     * array.
     * @param data the data for the matrix
     */
    public FloatElectricalInductanceMatrix(final FloatElectricalInductance[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatElectricalInductanceMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalInductanceMatrix(
            final Collection<FloatSparseValue<ElectricalInductanceUnit, FloatElectricalInductance>> data,
            final ElectricalInductanceUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatElectricalInductanceMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatElectricalInductanceMatrix(
            final Collection<FloatSparseValue<ElectricalInductanceUnit, FloatElectricalInductance>> data,
            final ElectricalInductanceUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatElectricalInductanceMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalInductanceMatrix(
            final Collection<FloatSparseValue<ElectricalInductanceUnit, FloatElectricalInductance>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, ElectricalInductanceUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatElectricalInductanceMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a
     * collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatElectricalInductanceMatrix(
            final Collection<FloatSparseValue<ElectricalInductanceUnit, FloatElectricalInductance>> data, final int rows,
            final int cols)
    {
        this(data, ElectricalInductanceUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatElectricalInductance> getScalarClass()
    {
        return FloatElectricalInductance.class;
    }

    @Override
    public Class<FloatElectricalInductanceVector> getVectorClass()
    {
        return FloatElectricalInductanceVector.class;
    }

    @Override
    public FloatElectricalInductanceMatrix instantiateMatrix(final FloatMatrixData fmd,
            final ElectricalInductanceUnit displayUnit)
    {
        return new FloatElectricalInductanceMatrix(fmd, displayUnit);
    }

    @Override
    public FloatElectricalInductanceVector instantiateVector(final FloatVectorData fvd,
            final ElectricalInductanceUnit displayUnit)
    {
        return new FloatElectricalInductanceVector(fvd, displayUnit);
    }

    @Override
    public FloatElectricalInductance instantiateScalarSI(final float valueSI, final ElectricalInductanceUnit displayUnit)
    {
        FloatElectricalInductance result = FloatElectricalInductance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
