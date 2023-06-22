package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.ElectricalConductanceUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.ElectricalConductance;
import org.djunits.value.vdouble.vector.ElectricalConductanceVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double ElectricalConductanceMatrix, a matrix of values with a ElectricalConductanceUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-06-22T21:38:04.910968700Z")
public class ElectricalConductanceMatrix extends DoubleMatrixRel<ElectricalConductanceUnit, ElectricalConductance,
        ElectricalConductanceVector, ElectricalConductanceMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a ElectricalConductanceMatrix from an internal data object.
     * @param data DoubleMatrixData; the internal data object for the matrix
     * @param displayUnit ElectricalConductanceUnit; the display unit of the matrix data
     */
    public ElectricalConductanceMatrix(final DoubleMatrixData data, final ElectricalConductanceUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a ElectricalConductanceMatrix from a double[][] object. The double values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data double[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit ElectricalConductanceUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalConductanceMatrix(final double[][] data, final ElectricalConductanceUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a ElectricalConductanceMatrix from a double[][] object. The double values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data double[][]; the data for the matrix
     * @param displayUnit ElectricalConductanceUnit; the unit of the values in the data array, and display unit when printing
     */
    public ElectricalConductanceMatrix(final double[][] data, final ElectricalConductanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a ElectricalConductanceMatrix from a double[][] object with SI-unit values.
     * @param data double[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalConductanceMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, ElectricalConductanceUnit.SI, storageType);
    }

    /**
     * Construct a ElectricalConductanceMatrix from a double[][] object with SI-unit values. Assume that the StorageType is
     * DENSE since we offer the data as an array of an array.
     * @param data double[][]; the data for the matrix, in SI units
     */
    public ElectricalConductanceMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH ElectricalConductance[][] */

    /**
     * Construct a ElectricalConductanceMatrix from an array of an array of ElectricalConductance objects. The
     * ElectricalConductance values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing.
     * @param data ElectricalConductance[][]; the data for the matrix
     * @param displayUnit ElectricalConductanceUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalConductanceMatrix(final ElectricalConductance[][] data, final ElectricalConductanceUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a ElectricalConductanceMatrix from an array of an array of ElectricalConductance objects. The
     * ElectricalConductance values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing. Assume that the StorageType is DENSE since we offer the data as an array of
     * an array.
     * @param data ElectricalConductance[][]; the data for the matrix
     * @param displayUnit ElectricalConductanceUnit; the display unit of the values when printing
     */
    public ElectricalConductanceMatrix(final ElectricalConductance[][] data, final ElectricalConductanceUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a ElectricalConductanceMatrix from an array of an array of ElectricalConductance objects. The
     * ElectricalConductance values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. since we offer the data as an array of an array.
     * @param data ElectricalConductance[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalConductanceMatrix(final ElectricalConductance[][] data, final StorageType storageType)
    {
        this(data, ElectricalConductanceUnit.SI, storageType);
    }

    /**
     * Construct a ElectricalConductanceMatrix from an array of an array of ElectricalConductance objects. The
     * ElectricalConductance values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. Assume that the StorageType is DENSE since we offer the data as an array of an
     * array.
     * @param data ElectricalConductance[][]; the data for the matrix
     */
    public ElectricalConductanceMatrix(final ElectricalConductance[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a ElectricalConductanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit ElectricalConductanceUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalConductanceMatrix(
            final Collection<DoubleSparseValue<ElectricalConductanceUnit, ElectricalConductance>> data,
            final ElectricalConductanceUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a ElectricalConductanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit ElectricalConductanceUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public ElectricalConductanceMatrix(
            final Collection<DoubleSparseValue<ElectricalConductanceUnit, ElectricalConductance>> data,
            final ElectricalConductanceUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a ElectricalConductanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public ElectricalConductanceMatrix(
            final Collection<DoubleSparseValue<ElectricalConductanceUnit, ElectricalConductance>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, ElectricalConductanceUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a ElectricalConductanceMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a
     * collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public ElectricalConductanceMatrix(
            final Collection<DoubleSparseValue<ElectricalConductanceUnit, ElectricalConductance>> data, final int rows,
            final int cols)
    {
        this(data, ElectricalConductanceUnit.SI, rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalConductance> getScalarClass()
    {
        return ElectricalConductance.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<ElectricalConductanceVector> getVectorClass()
    {
        return ElectricalConductanceVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalConductanceMatrix instantiateMatrix(final DoubleMatrixData dmd,
            final ElectricalConductanceUnit displayUnit)
    {
        return new ElectricalConductanceMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalConductanceVector instantiateVector(final DoubleVectorData dvd,
            final ElectricalConductanceUnit displayUnit)
    {
        return new ElectricalConductanceVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public ElectricalConductance instantiateScalarSI(final double valueSI, final ElectricalConductanceUnit displayUnit)
    {
        ElectricalConductance result = ElectricalConductance.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
