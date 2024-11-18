package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.ElectricalCapacitanceUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.ElectricalCapacitance;
import org.djunits.value.vdouble.vector.ElectricalCapacitanceVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double ElectricalCapacitanceMatrix, a matrix of values with a ElectricalCapacitanceUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class ElectricalCapacitanceMatrix extends DoubleMatrixRel<ElectricalCapacitanceUnit, ElectricalCapacitance,
        ElectricalCapacitanceVector, ElectricalCapacitanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a ElectricalCapacitanceMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public ElectricalCapacitanceMatrix(final DoubleMatrixData data, final ElectricalCapacitanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a ElectricalCapacitanceMatrix from a double[][] object. The double values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalCapacitanceMatrix(final double[][] data, final ElectricalCapacitanceUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a ElectricalCapacitanceMatrix from a double[][] object. The double values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public ElectricalCapacitanceMatrix(final double[][] data, final ElectricalCapacitanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a ElectricalCapacitanceMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalCapacitanceMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, ElectricalCapacitanceUnit.SI, storageType);
    }

    /**
     * Construct a ElectricalCapacitanceMatrix from a double[][] object with SI-unit values. Assume that the StorageType is
     * DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public ElectricalCapacitanceMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH ElectricalCapacitance[][] */

    /**
     * Construct a ElectricalCapacitanceMatrix from an array of an array of ElectricalCapacitance objects. The
     * ElectricalCapacitance values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalCapacitanceMatrix(final ElectricalCapacitance[][] data, final ElectricalCapacitanceUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a ElectricalCapacitanceMatrix from an array of an array of ElectricalCapacitance objects. The
     * ElectricalCapacitance values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing. Assume that the StorageType is DENSE since we offer the data as an array of
     * an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public ElectricalCapacitanceMatrix(final ElectricalCapacitance[][] data, final ElectricalCapacitanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a ElectricalCapacitanceMatrix from an array of an array of ElectricalCapacitance objects. The
     * ElectricalCapacitance values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalCapacitanceMatrix(final ElectricalCapacitance[][] data, final StorageType storageType)
    {
        this(data, ElectricalCapacitanceUnit.SI, storageType);
    }

    /**
     * Construct a ElectricalCapacitanceMatrix from an array of an array of ElectricalCapacitance objects. The
     * ElectricalCapacitance values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. Assume that the StorageType is DENSE since we offer the data as an array of an
     * array.
     * @param data the data for the matrix
     */
    public ElectricalCapacitanceMatrix(final ElectricalCapacitance[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a ElectricalCapacitanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalCapacitanceMatrix(
            final Collection<DoubleSparseValue<ElectricalCapacitanceUnit, ElectricalCapacitance>> data,
            final ElectricalCapacitanceUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a ElectricalCapacitanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public ElectricalCapacitanceMatrix(
            final Collection<DoubleSparseValue<ElectricalCapacitanceUnit, ElectricalCapacitance>> data,
            final ElectricalCapacitanceUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a ElectricalCapacitanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalCapacitanceMatrix(
            final Collection<DoubleSparseValue<ElectricalCapacitanceUnit, ElectricalCapacitance>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, ElectricalCapacitanceUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a ElectricalCapacitanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a
     * collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public ElectricalCapacitanceMatrix(
            final Collection<DoubleSparseValue<ElectricalCapacitanceUnit, ElectricalCapacitance>> data, final int rows,
            final int cols)
    {
        this(data, ElectricalCapacitanceUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<ElectricalCapacitance> getScalarClass()
    {
        return ElectricalCapacitance.class;
    }

    @Override
    public Class<ElectricalCapacitanceVector> getVectorClass()
    {
        return ElectricalCapacitanceVector.class;
    }

    @Override
    public ElectricalCapacitanceMatrix instantiateMatrix(final DoubleMatrixData dmd,
            final ElectricalCapacitanceUnit displayUnit)
    {
        return new ElectricalCapacitanceMatrix(dmd, displayUnit);
    }

    @Override
    public ElectricalCapacitanceVector instantiateVector(final DoubleVectorData dvd,
            final ElectricalCapacitanceUnit displayUnit)
    {
        return new ElectricalCapacitanceVector(dvd, displayUnit);
    }

    @Override
    public ElectricalCapacitance instantiateScalarSI(final double valueSI, final ElectricalCapacitanceUnit displayUnit)
    {
        ElectricalCapacitance result = ElectricalCapacitance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
