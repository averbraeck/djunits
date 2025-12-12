package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatElectricalPotential;
import org.djunits.value.vfloat.vector.FloatElectricalPotentialVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatElectricalPotentialMatrix, a matrix of values with a ElectricalPotentialUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FloatElectricalPotentialMatrix extends FloatMatrixRel<ElectricalPotentialUnit, FloatElectricalPotential,
        FloatElectricalPotentialVector, FloatElectricalPotentialMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatElectricalPotentialMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatElectricalPotentialMatrix(final FloatMatrixData data, final ElectricalPotentialUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatElectricalPotentialMatrix from a float[][] object. The float values are expressed in the displayUnit,
     * and will be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalPotentialMatrix(final float[][] data, final ElectricalPotentialUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatElectricalPotentialMatrix from a float[][] object. The float values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatElectricalPotentialMatrix(final float[][] data, final ElectricalPotentialUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatElectricalPotentialMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalPotentialMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, ElectricalPotentialUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalPotentialMatrix from a float[][] object with SI-unit values. Assume that the StorageType is
     * DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatElectricalPotentialMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH ElectricalPotential[][] */

    /**
     * Construct a FloatElectricalPotentialMatrix from an array of an array of FloatElectricalPotential objects. The
     * FloatElectricalPotential values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalPotentialMatrix(final FloatElectricalPotential[][] data, final ElectricalPotentialUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatElectricalPotentialMatrix from an array of an array of FloatElectricalPotential objects. The
     * FloatElectricalPotential values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing. Assume that the StorageType is DENSE since we offer the data as an array of
     * an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatElectricalPotentialMatrix(final FloatElectricalPotential[][] data, final ElectricalPotentialUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatElectricalPotentialMatrix from an array of an array of FloatElectricalPotential objects. The
     * FloatElectricalPotential values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalPotentialMatrix(final FloatElectricalPotential[][] data, final StorageType storageType)
    {
        this(data, ElectricalPotentialUnit.SI, storageType);
    }

    /**
     * Construct a FloatElectricalPotentialMatrix from an array of an array of FloatElectricalPotential objects. The
     * FloatElectricalPotential values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. Assume that the StorageType is DENSE since we offer the data as an array of an
     * array.
     * @param data the data for the matrix
     */
    public FloatElectricalPotentialMatrix(final FloatElectricalPotential[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatElectricalPotentialMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalPotentialMatrix(
            final Collection<FloatSparseValue<ElectricalPotentialUnit, FloatElectricalPotential>> data,
            final ElectricalPotentialUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatElectricalPotentialMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatElectricalPotentialMatrix(
            final Collection<FloatSparseValue<ElectricalPotentialUnit, FloatElectricalPotential>> data,
            final ElectricalPotentialUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatElectricalPotentialMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatElectricalPotentialMatrix(
            final Collection<FloatSparseValue<ElectricalPotentialUnit, FloatElectricalPotential>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, ElectricalPotentialUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatElectricalPotentialMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a
     * collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatElectricalPotentialMatrix(
            final Collection<FloatSparseValue<ElectricalPotentialUnit, FloatElectricalPotential>> data, final int rows,
            final int cols)
    {
        this(data, ElectricalPotentialUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatElectricalPotential> getScalarClass()
    {
        return FloatElectricalPotential.class;
    }

    @Override
    public Class<FloatElectricalPotentialVector> getVectorClass()
    {
        return FloatElectricalPotentialVector.class;
    }

    @Override
    public FloatElectricalPotentialMatrix instantiateMatrix(final FloatMatrixData fmd,
            final ElectricalPotentialUnit displayUnit)
    {
        return new FloatElectricalPotentialMatrix(fmd, displayUnit);
    }

    @Override
    public FloatElectricalPotentialVector instantiateVector(final FloatVectorData fvd,
            final ElectricalPotentialUnit displayUnit)
    {
        return new FloatElectricalPotentialVector(fvd, displayUnit);
    }

    @Override
    public FloatElectricalPotential instantiateScalarSI(final float valueSI, final ElectricalPotentialUnit displayUnit)
    {
        FloatElectricalPotential result = FloatElectricalPotential.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
