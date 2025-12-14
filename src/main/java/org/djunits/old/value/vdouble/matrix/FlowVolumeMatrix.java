package org.djunits.old.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.old.unit.FlowVolumeUnit;
import org.djunits.old.value.storage.StorageType;
import org.djunits.old.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.old.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.old.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.old.value.vdouble.scalar.FlowVolume;
import org.djunits.old.value.vdouble.vector.FlowVolumeVector;
import org.djunits.old.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double FlowVolumeMatrix, a matrix of values with a FlowVolumeUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class FlowVolumeMatrix extends DoubleMatrixRel<FlowVolumeUnit, FlowVolume, FlowVolumeVector, FlowVolumeMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FlowVolumeMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FlowVolumeMatrix(final DoubleMatrixData data, final FlowVolumeUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a FlowVolumeMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FlowVolumeMatrix(final double[][] data, final FlowVolumeUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FlowVolumeMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FlowVolumeMatrix(final double[][] data, final FlowVolumeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FlowVolumeMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FlowVolumeMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, FlowVolumeUnit.SI, storageType);
    }

    /**
     * Construct a FlowVolumeMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FlowVolumeMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FlowVolume[][] */

    /**
     * Construct a FlowVolumeMatrix from an array of an array of FlowVolume objects. The FlowVolume values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FlowVolumeMatrix(final FlowVolume[][] data, final FlowVolumeUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FlowVolumeMatrix from an array of an array of FlowVolume objects. The FlowVolume values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FlowVolumeMatrix(final FlowVolume[][] data, final FlowVolumeUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FlowVolumeMatrix from an array of an array of FlowVolume objects. The FlowVolume values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FlowVolumeMatrix(final FlowVolume[][] data, final StorageType storageType)
    {
        this(data, FlowVolumeUnit.SI, storageType);
    }

    /**
     * Construct a FlowVolumeMatrix from an array of an array of FlowVolume objects. The FlowVolume values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FlowVolumeMatrix(final FlowVolume[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a FlowVolumeMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FlowVolumeMatrix(final Collection<DoubleSparseValue<FlowVolumeUnit, FlowVolume>> data,
            final FlowVolumeUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FlowVolumeMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FlowVolumeMatrix(final Collection<DoubleSparseValue<FlowVolumeUnit, FlowVolume>> data,
            final FlowVolumeUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FlowVolumeMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FlowVolumeMatrix(final Collection<DoubleSparseValue<FlowVolumeUnit, FlowVolume>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, FlowVolumeUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FlowVolumeMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FlowVolumeMatrix(final Collection<DoubleSparseValue<FlowVolumeUnit, FlowVolume>> data, final int rows,
            final int cols)
    {
        this(data, FlowVolumeUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FlowVolume> getScalarClass()
    {
        return FlowVolume.class;
    }

    @Override
    public Class<FlowVolumeVector> getVectorClass()
    {
        return FlowVolumeVector.class;
    }

    @Override
    public FlowVolumeMatrix instantiateMatrix(final DoubleMatrixData dmd, final FlowVolumeUnit displayUnit)
    {
        return new FlowVolumeMatrix(dmd, displayUnit);
    }

    @Override
    public FlowVolumeVector instantiateVector(final DoubleVectorData dvd, final FlowVolumeUnit displayUnit)
    {
        return new FlowVolumeVector(dvd, displayUnit);
    }

    @Override
    public FlowVolume instantiateScalarSI(final double valueSI, final FlowVolumeUnit displayUnit)
    {
        FlowVolume result = FlowVolume.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
