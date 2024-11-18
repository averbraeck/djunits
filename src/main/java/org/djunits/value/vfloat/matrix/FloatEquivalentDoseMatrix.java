package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.EquivalentDoseUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatEquivalentDose;
import org.djunits.value.vfloat.vector.FloatEquivalentDoseVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatEquivalentDoseMatrix, a matrix of values with a EquivalentDoseUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatEquivalentDoseMatrix
        extends FloatMatrixRel<EquivalentDoseUnit, FloatEquivalentDose, FloatEquivalentDoseVector, FloatEquivalentDoseMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatEquivalentDoseMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatEquivalentDoseMatrix(final FloatMatrixData data, final EquivalentDoseUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatEquivalentDoseMatrix from a float[][] object. The float values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEquivalentDoseMatrix(final float[][] data, final EquivalentDoseUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatEquivalentDoseMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatEquivalentDoseMatrix(final float[][] data, final EquivalentDoseUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatEquivalentDoseMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEquivalentDoseMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, EquivalentDoseUnit.SI, storageType);
    }

    /**
     * Construct a FloatEquivalentDoseMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatEquivalentDoseMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH EquivalentDose[][] */

    /**
     * Construct a FloatEquivalentDoseMatrix from an array of an array of FloatEquivalentDose objects. The FloatEquivalentDose
     * values are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit
     * when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEquivalentDoseMatrix(final FloatEquivalentDose[][] data, final EquivalentDoseUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatEquivalentDoseMatrix from an array of an array of FloatEquivalentDose objects. The FloatEquivalentDose
     * values are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit
     * when printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatEquivalentDoseMatrix(final FloatEquivalentDose[][] data, final EquivalentDoseUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatEquivalentDoseMatrix from an array of an array of FloatEquivalentDose objects. The FloatEquivalentDose
     * values are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units
     * when printing. since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEquivalentDoseMatrix(final FloatEquivalentDose[][] data, final StorageType storageType)
    {
        this(data, EquivalentDoseUnit.SI, storageType);
    }

    /**
     * Construct a FloatEquivalentDoseMatrix from an array of an array of FloatEquivalentDose objects. The FloatEquivalentDose
     * values are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units
     * when printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FloatEquivalentDoseMatrix(final FloatEquivalentDose[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatEquivalentDoseMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEquivalentDoseMatrix(final Collection<FloatSparseValue<EquivalentDoseUnit, FloatEquivalentDose>> data,
            final EquivalentDoseUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatEquivalentDoseMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume
     * the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatEquivalentDoseMatrix(final Collection<FloatSparseValue<EquivalentDoseUnit, FloatEquivalentDose>> data,
            final EquivalentDoseUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatEquivalentDoseMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEquivalentDoseMatrix(final Collection<FloatSparseValue<EquivalentDoseUnit, FloatEquivalentDose>> data,
            final int rows, final int cols, final StorageType storageType)
    {
        this(data, EquivalentDoseUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatEquivalentDoseMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatEquivalentDoseMatrix(final Collection<FloatSparseValue<EquivalentDoseUnit, FloatEquivalentDose>> data,
            final int rows, final int cols)
    {
        this(data, EquivalentDoseUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatEquivalentDose> getScalarClass()
    {
        return FloatEquivalentDose.class;
    }

    @Override
    public Class<FloatEquivalentDoseVector> getVectorClass()
    {
        return FloatEquivalentDoseVector.class;
    }

    @Override
    public FloatEquivalentDoseMatrix instantiateMatrix(final FloatMatrixData fmd, final EquivalentDoseUnit displayUnit)
    {
        return new FloatEquivalentDoseMatrix(fmd, displayUnit);
    }

    @Override
    public FloatEquivalentDoseVector instantiateVector(final FloatVectorData fvd, final EquivalentDoseUnit displayUnit)
    {
        return new FloatEquivalentDoseVector(fvd, displayUnit);
    }

    @Override
    public FloatEquivalentDose instantiateScalarSI(final float valueSI, final EquivalentDoseUnit displayUnit)
    {
        FloatEquivalentDose result = FloatEquivalentDose.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
