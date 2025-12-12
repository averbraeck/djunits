package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.EnergyUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatEnergy;
import org.djunits.value.vfloat.vector.FloatEnergyVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatEnergyMatrix, a matrix of values with a EnergyUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatEnergyMatrix extends FloatMatrixRel<EnergyUnit, FloatEnergy, FloatEnergyVector, FloatEnergyMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatEnergyMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatEnergyMatrix(final FloatMatrixData data, final EnergyUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatEnergyMatrix from a float[][] object. The float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEnergyMatrix(final float[][] data, final EnergyUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatEnergyMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatEnergyMatrix(final float[][] data, final EnergyUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatEnergyMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEnergyMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, EnergyUnit.SI, storageType);
    }

    /**
     * Construct a FloatEnergyMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatEnergyMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Energy[][] */

    /**
     * Construct a FloatEnergyMatrix from an array of an array of FloatEnergy objects. The FloatEnergy values are each expressed
     * in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEnergyMatrix(final FloatEnergy[][] data, final EnergyUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatEnergyMatrix from an array of an array of FloatEnergy objects. The FloatEnergy values are each expressed
     * in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatEnergyMatrix(final FloatEnergy[][] data, final EnergyUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatEnergyMatrix from an array of an array of FloatEnergy objects. The FloatEnergy values are each expressed
     * in their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEnergyMatrix(final FloatEnergy[][] data, final StorageType storageType)
    {
        this(data, EnergyUnit.SI, storageType);
    }

    /**
     * Construct a FloatEnergyMatrix from an array of an array of FloatEnergy objects. The FloatEnergy values are each expressed
     * in their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FloatEnergyMatrix(final FloatEnergy[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatEnergyMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEnergyMatrix(final Collection<FloatSparseValue<EnergyUnit, FloatEnergy>> data, final EnergyUnit displayUnit,
            final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatEnergyMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatEnergyMatrix(final Collection<FloatSparseValue<EnergyUnit, FloatEnergy>> data, final EnergyUnit displayUnit,
            final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatEnergyMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatEnergyMatrix(final Collection<FloatSparseValue<EnergyUnit, FloatEnergy>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, EnergyUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatEnergyMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatEnergyMatrix(final Collection<FloatSparseValue<EnergyUnit, FloatEnergy>> data, final int rows, final int cols)
    {
        this(data, EnergyUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatEnergy> getScalarClass()
    {
        return FloatEnergy.class;
    }

    @Override
    public Class<FloatEnergyVector> getVectorClass()
    {
        return FloatEnergyVector.class;
    }

    @Override
    public FloatEnergyMatrix instantiateMatrix(final FloatMatrixData fmd, final EnergyUnit displayUnit)
    {
        return new FloatEnergyMatrix(fmd, displayUnit);
    }

    @Override
    public FloatEnergyVector instantiateVector(final FloatVectorData fvd, final EnergyUnit displayUnit)
    {
        return new FloatEnergyVector(fvd, displayUnit);
    }

    @Override
    public FloatEnergy instantiateScalarSI(final float valueSI, final EnergyUnit displayUnit)
    {
        FloatEnergy result = FloatEnergy.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
