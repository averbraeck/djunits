package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.MomentumUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Momentum;
import org.djunits.value.vdouble.vector.MomentumVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double MomentumMatrix, a matrix of values with a MomentumUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class MomentumMatrix extends DoubleMatrixRel<MomentumUnit, Momentum, MomentumVector, MomentumMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a MomentumMatrix from an internal data object.
     * @param data DoubleMatrixData; the internal data object for the matrix
     * @param displayUnit MomentumUnit; the display unit of the matrix data
     */
    public MomentumMatrix(final DoubleMatrixData data, final MomentumUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a MomentumMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data double[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit MomentumUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MomentumMatrix(final double[][] data, final MomentumUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a MomentumMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data double[][]; the data for the matrix
     * @param displayUnit MomentumUnit; the unit of the values in the data array, and display unit when printing
     */
    public MomentumMatrix(final double[][] data, final MomentumUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a MomentumMatrix from a double[][] object with SI-unit values.
     * @param data double[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MomentumMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, MomentumUnit.SI, storageType);
    }

    /**
     * Construct a MomentumMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data double[][]; the data for the matrix, in SI units
     */
    public MomentumMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Momentum[][] */

    /**
     * Construct a MomentumMatrix from an array of an array of Momentum objects. The Momentum values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data Momentum[][]; the data for the matrix
     * @param displayUnit MomentumUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MomentumMatrix(final Momentum[][] data, final MomentumUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a MomentumMatrix from an array of an array of Momentum objects. The Momentum values are each expressed in their
     * own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data Momentum[][]; the data for the matrix
     * @param displayUnit MomentumUnit; the display unit of the values when printing
     */
    public MomentumMatrix(final Momentum[][] data, final MomentumUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a MomentumMatrix from an array of an array of Momentum objects. The Momentum values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data
     * as an array of an array.
     * @param data Momentum[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MomentumMatrix(final Momentum[][] data, final StorageType storageType)
    {
        this(data, MomentumUnit.SI, storageType);
    }

    /**
     * Construct a MomentumMatrix from an array of an array of Momentum objects. The Momentum values are each expressed in their
     * own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data Momentum[][]; the data for the matrix
     */
    public MomentumMatrix(final Momentum[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a MomentumMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit MomentumUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MomentumMatrix(final Collection<DoubleSparseValue<MomentumUnit, Momentum>> data, final MomentumUnit displayUnit,
            final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a MomentumMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the storage
     * type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit MomentumUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public MomentumMatrix(final Collection<DoubleSparseValue<MomentumUnit, Momentum>> data, final MomentumUnit displayUnit,
            final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a MomentumMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MomentumMatrix(final Collection<DoubleSparseValue<MomentumUnit, Momentum>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, MomentumUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a MomentumMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public MomentumMatrix(final Collection<DoubleSparseValue<MomentumUnit, Momentum>> data, final int rows, final int cols)
    {
        this(data, MomentumUnit.SI, rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Momentum> getScalarClass()
    {
        return Momentum.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<MomentumVector> getVectorClass()
    {
        return MomentumVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public MomentumMatrix instantiateMatrix(final DoubleMatrixData dmd, final MomentumUnit displayUnit)
    {
        return new MomentumMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public MomentumVector instantiateVector(final DoubleVectorData dvd, final MomentumUnit displayUnit)
    {
        return new MomentumVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Momentum instantiateScalarSI(final double valueSI, final MomentumUnit displayUnit)
    {
        Momentum result = Momentum.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
