package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.LinearDensityUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.LinearDensity;
import org.djunits.value.vdouble.vector.LinearDensityVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double LinearDensityMatrix, a matrix of values with a LinearDensityUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class LinearDensityMatrix
        extends DoubleMatrixRel<LinearDensityUnit, LinearDensity, LinearDensityVector, LinearDensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a LinearDensityMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public LinearDensityMatrix(final DoubleMatrixData data, final LinearDensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a LinearDensityMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LinearDensityMatrix(final double[][] data, final LinearDensityUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a LinearDensityMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public LinearDensityMatrix(final double[][] data, final LinearDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a LinearDensityMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LinearDensityMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, LinearDensityUnit.SI, storageType);
    }

    /**
     * Construct a LinearDensityMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public LinearDensityMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH LinearDensity[][] */

    /**
     * Construct a LinearDensityMatrix from an array of an array of LinearDensity objects. The LinearDensity values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LinearDensityMatrix(final LinearDensity[][] data, final LinearDensityUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a LinearDensityMatrix from an array of an array of LinearDensity objects. The LinearDensity values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public LinearDensityMatrix(final LinearDensity[][] data, final LinearDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a LinearDensityMatrix from an array of an array of LinearDensity objects. The LinearDensity values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LinearDensityMatrix(final LinearDensity[][] data, final StorageType storageType)
    {
        this(data, LinearDensityUnit.SI, storageType);
    }

    /**
     * Construct a LinearDensityMatrix from an array of an array of LinearDensity objects. The LinearDensity values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public LinearDensityMatrix(final LinearDensity[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a LinearDensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LinearDensityMatrix(final Collection<DoubleSparseValue<LinearDensityUnit, LinearDensity>> data,
            final LinearDensityUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a LinearDensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public LinearDensityMatrix(final Collection<DoubleSparseValue<LinearDensityUnit, LinearDensity>> data,
            final LinearDensityUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a LinearDensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LinearDensityMatrix(final Collection<DoubleSparseValue<LinearDensityUnit, LinearDensity>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, LinearDensityUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a LinearDensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public LinearDensityMatrix(final Collection<DoubleSparseValue<LinearDensityUnit, LinearDensity>> data, final int rows,
            final int cols)
    {
        this(data, LinearDensityUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<LinearDensity> getScalarClass()
    {
        return LinearDensity.class;
    }

    @Override
    public Class<LinearDensityVector> getVectorClass()
    {
        return LinearDensityVector.class;
    }

    @Override
    public LinearDensityMatrix instantiateMatrix(final DoubleMatrixData dmd, final LinearDensityUnit displayUnit)
    {
        return new LinearDensityMatrix(dmd, displayUnit);
    }

    @Override
    public LinearDensityVector instantiateVector(final DoubleVectorData dvd, final LinearDensityUnit displayUnit)
    {
        return new LinearDensityVector(dvd, displayUnit);
    }

    @Override
    public LinearDensity instantiateScalarSI(final double valueSI, final LinearDensityUnit displayUnit)
    {
        LinearDensity result = LinearDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
