package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.SpeedUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Speed;
import org.djunits.value.vdouble.vector.SpeedVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double SpeedMatrix, a matrix of values with a SpeedUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class SpeedMatrix extends DoubleMatrixRel<SpeedUnit, Speed, SpeedVector, SpeedMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a SpeedMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public SpeedMatrix(final DoubleMatrixData data, final SpeedUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a SpeedMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be printed
     * using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public SpeedMatrix(final double[][] data, final SpeedUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a SpeedMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public SpeedMatrix(final double[][] data, final SpeedUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a SpeedMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public SpeedMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, SpeedUnit.SI, storageType);
    }

    /**
     * Construct a SpeedMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since we offer
     * the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public SpeedMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Speed[][] */

    /**
     * Construct a SpeedMatrix from an array of an array of Speed objects. The Speed values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public SpeedMatrix(final Speed[][] data, final SpeedUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a SpeedMatrix from an array of an array of Speed objects. The Speed values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public SpeedMatrix(final Speed[][] data, final SpeedUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a SpeedMatrix from an array of an array of Speed objects. The Speed values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data as
     * an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public SpeedMatrix(final Speed[][] data, final StorageType storageType)
    {
        this(data, SpeedUnit.SI, storageType);
    }

    /**
     * Construct a SpeedMatrix from an array of an array of Speed objects. The Speed values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType
     * is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public SpeedMatrix(final Speed[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a SpeedMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public SpeedMatrix(final Collection<DoubleSparseValue<SpeedUnit, Speed>> data, final SpeedUnit displayUnit, final int rows,
            final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a SpeedMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the storage
     * type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public SpeedMatrix(final Collection<DoubleSparseValue<SpeedUnit, Speed>> data, final SpeedUnit displayUnit, final int rows,
            final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a SpeedMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public SpeedMatrix(final Collection<DoubleSparseValue<SpeedUnit, Speed>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, SpeedUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a SpeedMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public SpeedMatrix(final Collection<DoubleSparseValue<SpeedUnit, Speed>> data, final int rows, final int cols)
    {
        this(data, SpeedUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<Speed> getScalarClass()
    {
        return Speed.class;
    }

    @Override
    public Class<SpeedVector> getVectorClass()
    {
        return SpeedVector.class;
    }

    @Override
    public SpeedMatrix instantiateMatrix(final DoubleMatrixData dmd, final SpeedUnit displayUnit)
    {
        return new SpeedMatrix(dmd, displayUnit);
    }

    @Override
    public SpeedVector instantiateVector(final DoubleVectorData dvd, final SpeedUnit displayUnit)
    {
        return new SpeedVector(dvd, displayUnit);
    }

    @Override
    public Speed instantiateScalarSI(final double valueSI, final SpeedUnit displayUnit)
    {
        Speed result = Speed.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
