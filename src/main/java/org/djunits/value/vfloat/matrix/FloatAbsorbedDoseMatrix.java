package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.AbsorbedDoseUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAbsorbedDose;
import org.djunits.value.vfloat.vector.FloatAbsorbedDoseVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatAbsorbedDoseMatrix, a matrix of values with a AbsorbedDoseUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatAbsorbedDoseMatrix
        extends FloatMatrixRel<AbsorbedDoseUnit, FloatAbsorbedDose, FloatAbsorbedDoseVector, FloatAbsorbedDoseMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatAbsorbedDoseMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatAbsorbedDoseMatrix(final FloatMatrixData data, final AbsorbedDoseUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatAbsorbedDoseMatrix from a float[][] object. The float values are expressed in the displayUnit, and will
     * be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsorbedDoseMatrix(final float[][] data, final AbsorbedDoseUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatAbsorbedDoseMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatAbsorbedDoseMatrix(final float[][] data, final AbsorbedDoseUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAbsorbedDoseMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsorbedDoseMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, AbsorbedDoseUnit.SI, storageType);
    }

    /**
     * Construct a FloatAbsorbedDoseMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatAbsorbedDoseMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH AbsorbedDose[][] */

    /**
     * Construct a FloatAbsorbedDoseMatrix from an array of an array of FloatAbsorbedDose objects. The FloatAbsorbedDose values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsorbedDoseMatrix(final FloatAbsorbedDose[][] data, final AbsorbedDoseUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatAbsorbedDoseMatrix from an array of an array of FloatAbsorbedDose objects. The FloatAbsorbedDose values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatAbsorbedDoseMatrix(final FloatAbsorbedDose[][] data, final AbsorbedDoseUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAbsorbedDoseMatrix from an array of an array of FloatAbsorbedDose objects. The FloatAbsorbedDose values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsorbedDoseMatrix(final FloatAbsorbedDose[][] data, final StorageType storageType)
    {
        this(data, AbsorbedDoseUnit.SI, storageType);
    }

    /**
     * Construct a FloatAbsorbedDoseMatrix from an array of an array of FloatAbsorbedDose objects. The FloatAbsorbedDose values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FloatAbsorbedDoseMatrix(final FloatAbsorbedDose[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatAbsorbedDoseMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsorbedDoseMatrix(final Collection<FloatSparseValue<AbsorbedDoseUnit, FloatAbsorbedDose>> data,
            final AbsorbedDoseUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatAbsorbedDoseMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatAbsorbedDoseMatrix(final Collection<FloatSparseValue<AbsorbedDoseUnit, FloatAbsorbedDose>> data,
            final AbsorbedDoseUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatAbsorbedDoseMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsorbedDoseMatrix(final Collection<FloatSparseValue<AbsorbedDoseUnit, FloatAbsorbedDose>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, AbsorbedDoseUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatAbsorbedDoseMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatAbsorbedDoseMatrix(final Collection<FloatSparseValue<AbsorbedDoseUnit, FloatAbsorbedDose>> data, final int rows,
            final int cols)
    {
        this(data, AbsorbedDoseUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatAbsorbedDose> getScalarClass()
    {
        return FloatAbsorbedDose.class;
    }

    @Override
    public Class<FloatAbsorbedDoseVector> getVectorClass()
    {
        return FloatAbsorbedDoseVector.class;
    }

    @Override
    public FloatAbsorbedDoseMatrix instantiateMatrix(final FloatMatrixData fmd, final AbsorbedDoseUnit displayUnit)
    {
        return new FloatAbsorbedDoseMatrix(fmd, displayUnit);
    }

    @Override
    public FloatAbsorbedDoseVector instantiateVector(final FloatVectorData fvd, final AbsorbedDoseUnit displayUnit)
    {
        return new FloatAbsorbedDoseVector(fvd, displayUnit);
    }

    @Override
    public FloatAbsorbedDose instantiateScalarSI(final float valueSI, final AbsorbedDoseUnit displayUnit)
    {
        FloatAbsorbedDose result = FloatAbsorbedDose.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
