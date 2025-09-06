package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.IlluminanceUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Illuminance;
import org.djunits.value.vdouble.vector.IlluminanceVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double IlluminanceMatrix, a matrix of values with a IlluminanceUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class IlluminanceMatrix extends DoubleMatrixRel<IlluminanceUnit, Illuminance, IlluminanceVector, IlluminanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a IlluminanceMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public IlluminanceMatrix(final DoubleMatrixData data, final IlluminanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a IlluminanceMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public IlluminanceMatrix(final double[][] data, final IlluminanceUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a IlluminanceMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public IlluminanceMatrix(final double[][] data, final IlluminanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a IlluminanceMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public IlluminanceMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, IlluminanceUnit.SI, storageType);
    }

    /**
     * Construct a IlluminanceMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public IlluminanceMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Illuminance[][] */

    /**
     * Construct a IlluminanceMatrix from an array of an array of Illuminance objects. The Illuminance values are each expressed
     * in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public IlluminanceMatrix(final Illuminance[][] data, final IlluminanceUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a IlluminanceMatrix from an array of an array of Illuminance objects. The Illuminance values are each expressed
     * in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public IlluminanceMatrix(final Illuminance[][] data, final IlluminanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a IlluminanceMatrix from an array of an array of Illuminance objects. The Illuminance values are each expressed
     * in their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public IlluminanceMatrix(final Illuminance[][] data, final StorageType storageType)
    {
        this(data, IlluminanceUnit.SI, storageType);
    }

    /**
     * Construct a IlluminanceMatrix from an array of an array of Illuminance objects. The Illuminance values are each expressed
     * in their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public IlluminanceMatrix(final Illuminance[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a IlluminanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public IlluminanceMatrix(final Collection<DoubleSparseValue<IlluminanceUnit, Illuminance>> data,
            final IlluminanceUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a IlluminanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public IlluminanceMatrix(final Collection<DoubleSparseValue<IlluminanceUnit, Illuminance>> data,
            final IlluminanceUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a IlluminanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public IlluminanceMatrix(final Collection<DoubleSparseValue<IlluminanceUnit, Illuminance>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, IlluminanceUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a IlluminanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public IlluminanceMatrix(final Collection<DoubleSparseValue<IlluminanceUnit, Illuminance>> data, final int rows,
            final int cols)
    {
        this(data, IlluminanceUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<Illuminance> getScalarClass()
    {
        return Illuminance.class;
    }

    @Override
    public Class<IlluminanceVector> getVectorClass()
    {
        return IlluminanceVector.class;
    }

    @Override
    public IlluminanceMatrix instantiateMatrix(final DoubleMatrixData dmd, final IlluminanceUnit displayUnit)
    {
        return new IlluminanceMatrix(dmd, displayUnit);
    }

    @Override
    public IlluminanceVector instantiateVector(final DoubleVectorData dvd, final IlluminanceUnit displayUnit)
    {
        return new IlluminanceVector(dvd, displayUnit);
    }

    @Override
    public Illuminance instantiateScalarSI(final double valueSI, final IlluminanceUnit displayUnit)
    {
        Illuminance result = Illuminance.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
