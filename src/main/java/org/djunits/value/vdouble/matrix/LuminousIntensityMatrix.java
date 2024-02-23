package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.LuminousIntensityUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.LuminousIntensity;
import org.djunits.value.vdouble.vector.LuminousIntensityVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double LuminousIntensityMatrix, a matrix of values with a LuminousIntensityUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class LuminousIntensityMatrix
        extends DoubleMatrixRel<LuminousIntensityUnit, LuminousIntensity, LuminousIntensityVector, LuminousIntensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a LuminousIntensityMatrix from an internal data object.
     * @param data DoubleMatrixData; the internal data object for the matrix
     * @param displayUnit LuminousIntensityUnit; the display unit of the matrix data
     */
    public LuminousIntensityMatrix(final DoubleMatrixData data, final LuminousIntensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a LuminousIntensityMatrix from a double[][] object. The double values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data double[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit LuminousIntensityUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LuminousIntensityMatrix(final double[][] data, final LuminousIntensityUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a LuminousIntensityMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data double[][]; the data for the matrix
     * @param displayUnit LuminousIntensityUnit; the unit of the values in the data array, and display unit when printing
     */
    public LuminousIntensityMatrix(final double[][] data, final LuminousIntensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a LuminousIntensityMatrix from a double[][] object with SI-unit values.
     * @param data double[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LuminousIntensityMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, LuminousIntensityUnit.SI, storageType);
    }

    /**
     * Construct a LuminousIntensityMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array of an array.
     * @param data double[][]; the data for the matrix, in SI units
     */
    public LuminousIntensityMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH LuminousIntensity[][] */

    /**
     * Construct a LuminousIntensityMatrix from an array of an array of LuminousIntensity objects. The LuminousIntensity values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data LuminousIntensity[][]; the data for the matrix
     * @param displayUnit LuminousIntensityUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LuminousIntensityMatrix(final LuminousIntensity[][] data, final LuminousIntensityUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a LuminousIntensityMatrix from an array of an array of LuminousIntensity objects. The LuminousIntensity values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data LuminousIntensity[][]; the data for the matrix
     * @param displayUnit LuminousIntensityUnit; the display unit of the values when printing
     */
    public LuminousIntensityMatrix(final LuminousIntensity[][] data, final LuminousIntensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a LuminousIntensityMatrix from an array of an array of LuminousIntensity objects. The LuminousIntensity values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. since we offer the data as an array of an array.
     * @param data LuminousIntensity[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LuminousIntensityMatrix(final LuminousIntensity[][] data, final StorageType storageType)
    {
        this(data, LuminousIntensityUnit.SI, storageType);
    }

    /**
     * Construct a LuminousIntensityMatrix from an array of an array of LuminousIntensity objects. The LuminousIntensity values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data LuminousIntensity[][]; the data for the matrix
     */
    public LuminousIntensityMatrix(final LuminousIntensity[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a LuminousIntensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit LuminousIntensityUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LuminousIntensityMatrix(final Collection<DoubleSparseValue<LuminousIntensityUnit, LuminousIntensity>> data,
            final LuminousIntensityUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a LuminousIntensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume
     * the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit LuminousIntensityUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public LuminousIntensityMatrix(final Collection<DoubleSparseValue<LuminousIntensityUnit, LuminousIntensity>> data,
            final LuminousIntensityUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a LuminousIntensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public LuminousIntensityMatrix(final Collection<DoubleSparseValue<LuminousIntensityUnit, LuminousIntensity>> data,
            final int rows, final int cols, final StorageType storageType)
    {
        this(data, LuminousIntensityUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a LuminousIntensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public LuminousIntensityMatrix(final Collection<DoubleSparseValue<LuminousIntensityUnit, LuminousIntensity>> data,
            final int rows, final int cols)
    {
        this(data, LuminousIntensityUnit.SI, rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<LuminousIntensity> getScalarClass()
    {
        return LuminousIntensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<LuminousIntensityVector> getVectorClass()
    {
        return LuminousIntensityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public LuminousIntensityMatrix instantiateMatrix(final DoubleMatrixData dmd, final LuminousIntensityUnit displayUnit)
    {
        return new LuminousIntensityMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public LuminousIntensityVector instantiateVector(final DoubleVectorData dvd, final LuminousIntensityUnit displayUnit)
    {
        return new LuminousIntensityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public LuminousIntensity instantiateScalarSI(final double valueSI, final LuminousIntensityUnit displayUnit)
    {
        LuminousIntensity result = LuminousIntensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
