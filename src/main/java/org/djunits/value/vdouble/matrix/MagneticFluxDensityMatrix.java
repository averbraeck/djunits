package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.MagneticFluxDensity;
import org.djunits.value.vdouble.vector.MagneticFluxDensityVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double MagneticFluxDensityMatrix, a matrix of values with a MagneticFluxDensityUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class MagneticFluxDensityMatrix extends
        DoubleMatrixRel<MagneticFluxDensityUnit, MagneticFluxDensity, MagneticFluxDensityVector, MagneticFluxDensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a MagneticFluxDensityMatrix from an internal data object.
     * @param data DoubleMatrixData; the internal data object for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the display unit of the matrix data
     */
    public MagneticFluxDensityMatrix(final DoubleMatrixData data, final MagneticFluxDensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a MagneticFluxDensityMatrix from a double[][] object. The double values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data double[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit MagneticFluxDensityUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxDensityMatrix(final double[][] data, final MagneticFluxDensityUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a MagneticFluxDensityMatrix from a double[][] object. The double values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data double[][]; the data for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the unit of the values in the data array, and display unit when printing
     */
    public MagneticFluxDensityMatrix(final double[][] data, final MagneticFluxDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a MagneticFluxDensityMatrix from a double[][] object with SI-unit values.
     * @param data double[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxDensityMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, MagneticFluxDensityUnit.SI, storageType);
    }

    /**
     * Construct a MagneticFluxDensityMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array of an array.
     * @param data double[][]; the data for the matrix, in SI units
     */
    public MagneticFluxDensityMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH MagneticFluxDensity[][] */

    /**
     * Construct a MagneticFluxDensityMatrix from an array of an array of MagneticFluxDensity objects. The MagneticFluxDensity
     * values are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit
     * when printing.
     * @param data MagneticFluxDensity[][]; the data for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxDensityMatrix(final MagneticFluxDensity[][] data, final MagneticFluxDensityUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a MagneticFluxDensityMatrix from an array of an array of MagneticFluxDensity objects. The MagneticFluxDensity
     * values are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit
     * when printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data MagneticFluxDensity[][]; the data for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the display unit of the values when printing
     */
    public MagneticFluxDensityMatrix(final MagneticFluxDensity[][] data, final MagneticFluxDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a MagneticFluxDensityMatrix from an array of an array of MagneticFluxDensity objects. The MagneticFluxDensity
     * values are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units
     * when printing. since we offer the data as an array of an array.
     * @param data MagneticFluxDensity[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxDensityMatrix(final MagneticFluxDensity[][] data, final StorageType storageType)
    {
        this(data, MagneticFluxDensityUnit.SI, storageType);
    }

    /**
     * Construct a MagneticFluxDensityMatrix from an array of an array of MagneticFluxDensity objects. The MagneticFluxDensity
     * values are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units
     * when printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data MagneticFluxDensity[][]; the data for the matrix
     */
    public MagneticFluxDensityMatrix(final MagneticFluxDensity[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a MagneticFluxDensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxDensityMatrix(final Collection<DoubleSparseValue<MagneticFluxDensityUnit, MagneticFluxDensity>> data,
            final MagneticFluxDensityUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a MagneticFluxDensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume
     * the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public MagneticFluxDensityMatrix(final Collection<DoubleSparseValue<MagneticFluxDensityUnit, MagneticFluxDensity>> data,
            final MagneticFluxDensityUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a MagneticFluxDensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxDensityMatrix(final Collection<DoubleSparseValue<MagneticFluxDensityUnit, MagneticFluxDensity>> data,
            final int rows, final int cols, final StorageType storageType)
    {
        this(data, MagneticFluxDensityUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a MagneticFluxDensityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public MagneticFluxDensityMatrix(final Collection<DoubleSparseValue<MagneticFluxDensityUnit, MagneticFluxDensity>> data,
            final int rows, final int cols)
    {
        this(data, MagneticFluxDensityUnit.SI, rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<MagneticFluxDensity> getScalarClass()
    {
        return MagneticFluxDensity.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<MagneticFluxDensityVector> getVectorClass()
    {
        return MagneticFluxDensityVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public MagneticFluxDensityMatrix instantiateMatrix(final DoubleMatrixData dmd, final MagneticFluxDensityUnit displayUnit)
    {
        return new MagneticFluxDensityMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public MagneticFluxDensityVector instantiateVector(final DoubleVectorData dvd, final MagneticFluxDensityUnit displayUnit)
    {
        return new MagneticFluxDensityVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public MagneticFluxDensity instantiateScalarSI(final double valueSI, final MagneticFluxDensityUnit displayUnit)
    {
        MagneticFluxDensity result = MagneticFluxDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
