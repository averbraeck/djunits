package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.RadioActivityUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.RadioActivity;
import org.djunits.value.vdouble.vector.RadioActivityVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double RadioActivityMatrix, a matrix of values with a RadioActivityUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author Alexander Verbraeck
 * @author Peter Knoppers
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class RadioActivityMatrix
        extends DoubleMatrixRel<RadioActivityUnit, RadioActivity, RadioActivityVector, RadioActivityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a RadioActivityMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public RadioActivityMatrix(final DoubleMatrixData data, final RadioActivityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a RadioActivityMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public RadioActivityMatrix(final double[][] data, final RadioActivityUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a RadioActivityMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public RadioActivityMatrix(final double[][] data, final RadioActivityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a RadioActivityMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public RadioActivityMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, RadioActivityUnit.SI, storageType);
    }

    /**
     * Construct a RadioActivityMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public RadioActivityMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH RadioActivity[][] */

    /**
     * Construct a RadioActivityMatrix from an array of an array of RadioActivity objects. The RadioActivity values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public RadioActivityMatrix(final RadioActivity[][] data, final RadioActivityUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a RadioActivityMatrix from an array of an array of RadioActivity objects. The RadioActivity values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public RadioActivityMatrix(final RadioActivity[][] data, final RadioActivityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a RadioActivityMatrix from an array of an array of RadioActivity objects. The RadioActivity values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public RadioActivityMatrix(final RadioActivity[][] data, final StorageType storageType)
    {
        this(data, RadioActivityUnit.SI, storageType);
    }

    /**
     * Construct a RadioActivityMatrix from an array of an array of RadioActivity objects. The RadioActivity values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public RadioActivityMatrix(final RadioActivity[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a RadioActivityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public RadioActivityMatrix(final Collection<DoubleSparseValue<RadioActivityUnit, RadioActivity>> data,
            final RadioActivityUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a RadioActivityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public RadioActivityMatrix(final Collection<DoubleSparseValue<RadioActivityUnit, RadioActivity>> data,
            final RadioActivityUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a RadioActivityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public RadioActivityMatrix(final Collection<DoubleSparseValue<RadioActivityUnit, RadioActivity>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, RadioActivityUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a RadioActivityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public RadioActivityMatrix(final Collection<DoubleSparseValue<RadioActivityUnit, RadioActivity>> data, final int rows,
            final int cols)
    {
        this(data, RadioActivityUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<RadioActivity> getScalarClass()
    {
        return RadioActivity.class;
    }

    @Override
    public Class<RadioActivityVector> getVectorClass()
    {
        return RadioActivityVector.class;
    }

    @Override
    public RadioActivityMatrix instantiateMatrix(final DoubleMatrixData dmd, final RadioActivityUnit displayUnit)
    {
        return new RadioActivityMatrix(dmd, displayUnit);
    }

    @Override
    public RadioActivityVector instantiateVector(final DoubleVectorData dvd, final RadioActivityUnit displayUnit)
    {
        return new RadioActivityVector(dvd, displayUnit);
    }

    @Override
    public RadioActivity instantiateScalarSI(final double valueSI, final RadioActivityUnit displayUnit)
    {
        RadioActivity result = RadioActivity.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
