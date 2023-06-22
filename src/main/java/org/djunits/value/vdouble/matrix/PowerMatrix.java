package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.PowerUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Power;
import org.djunits.value.vdouble.vector.PowerVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double PowerMatrix, a matrix of values with a PowerUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-06-22T21:38:04.910968700Z")
public class PowerMatrix extends DoubleMatrixRel<PowerUnit, Power, PowerVector, PowerMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a PowerMatrix from an internal data object.
     * @param data DoubleMatrixData; the internal data object for the matrix
     * @param displayUnit PowerUnit; the display unit of the matrix data
     */
    public PowerMatrix(final DoubleMatrixData data, final PowerUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a PowerMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be printed
     * using the displayUnit.
     * @param data double[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit PowerUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PowerMatrix(final double[][] data, final PowerUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a PowerMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data double[][]; the data for the matrix
     * @param displayUnit PowerUnit; the unit of the values in the data array, and display unit when printing
     */
    public PowerMatrix(final double[][] data, final PowerUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a PowerMatrix from a double[][] object with SI-unit values.
     * @param data double[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PowerMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, PowerUnit.SI, storageType);
    }

    /**
     * Construct a PowerMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since we offer
     * the data as an array of an array.
     * @param data double[][]; the data for the matrix, in SI units
     */
    public PowerMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Power[][] */

    /**
     * Construct a PowerMatrix from an array of an array of Power objects. The Power values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data Power[][]; the data for the matrix
     * @param displayUnit PowerUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PowerMatrix(final Power[][] data, final PowerUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a PowerMatrix from an array of an array of Power objects. The Power values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data Power[][]; the data for the matrix
     * @param displayUnit PowerUnit; the display unit of the values when printing
     */
    public PowerMatrix(final Power[][] data, final PowerUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a PowerMatrix from an array of an array of Power objects. The Power values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data as
     * an array of an array.
     * @param data Power[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PowerMatrix(final Power[][] data, final StorageType storageType)
    {
        this(data, PowerUnit.SI, storageType);
    }

    /**
     * Construct a PowerMatrix from an array of an array of Power objects. The Power values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType
     * is DENSE since we offer the data as an array of an array.
     * @param data Power[][]; the data for the matrix
     */
    public PowerMatrix(final Power[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a PowerMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit PowerUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PowerMatrix(final Collection<DoubleSparseValue<PowerUnit, Power>> data, final PowerUnit displayUnit, final int rows,
            final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a PowerMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the storage
     * type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit PowerUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public PowerMatrix(final Collection<DoubleSparseValue<PowerUnit, Power>> data, final PowerUnit displayUnit, final int rows,
            final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a PowerMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public PowerMatrix(final Collection<DoubleSparseValue<PowerUnit, Power>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, PowerUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a PowerMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public PowerMatrix(final Collection<DoubleSparseValue<PowerUnit, Power>> data, final int rows, final int cols)
    {
        this(data, PowerUnit.SI, rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Power> getScalarClass()
    {
        return Power.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<PowerVector> getVectorClass()
    {
        return PowerVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public PowerMatrix instantiateMatrix(final DoubleMatrixData dmd, final PowerUnit displayUnit)
    {
        return new PowerMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public PowerVector instantiateVector(final DoubleVectorData dvd, final PowerUnit displayUnit)
    {
        return new PowerVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Power instantiateScalarSI(final double valueSI, final PowerUnit displayUnit)
    {
        Power result = Power.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
