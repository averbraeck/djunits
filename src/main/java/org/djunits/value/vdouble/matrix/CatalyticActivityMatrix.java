package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.CatalyticActivityUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.CatalyticActivity;
import org.djunits.value.vdouble.vector.CatalyticActivityVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double CatalyticActivityMatrix, a matrix of values with a CatalyticActivityUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class CatalyticActivityMatrix
        extends DoubleMatrixRel<CatalyticActivityUnit, CatalyticActivity, CatalyticActivityVector, CatalyticActivityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a CatalyticActivityMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public CatalyticActivityMatrix(final DoubleMatrixData data, final CatalyticActivityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a CatalyticActivityMatrix from a double[][] object. The double values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public CatalyticActivityMatrix(final double[][] data, final CatalyticActivityUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a CatalyticActivityMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public CatalyticActivityMatrix(final double[][] data, final CatalyticActivityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a CatalyticActivityMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public CatalyticActivityMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, CatalyticActivityUnit.SI, storageType);
    }

    /**
     * Construct a CatalyticActivityMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public CatalyticActivityMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH CatalyticActivity[][] */

    /**
     * Construct a CatalyticActivityMatrix from an array of an array of CatalyticActivity objects. The CatalyticActivity values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public CatalyticActivityMatrix(final CatalyticActivity[][] data, final CatalyticActivityUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a CatalyticActivityMatrix from an array of an array of CatalyticActivity objects. The CatalyticActivity values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public CatalyticActivityMatrix(final CatalyticActivity[][] data, final CatalyticActivityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a CatalyticActivityMatrix from an array of an array of CatalyticActivity objects. The CatalyticActivity values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public CatalyticActivityMatrix(final CatalyticActivity[][] data, final StorageType storageType)
    {
        this(data, CatalyticActivityUnit.SI, storageType);
    }

    /**
     * Construct a CatalyticActivityMatrix from an array of an array of CatalyticActivity objects. The CatalyticActivity values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public CatalyticActivityMatrix(final CatalyticActivity[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a CatalyticActivityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public CatalyticActivityMatrix(final Collection<DoubleSparseValue<CatalyticActivityUnit, CatalyticActivity>> data,
            final CatalyticActivityUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a CatalyticActivityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume
     * the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public CatalyticActivityMatrix(final Collection<DoubleSparseValue<CatalyticActivityUnit, CatalyticActivity>> data,
            final CatalyticActivityUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a CatalyticActivityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public CatalyticActivityMatrix(final Collection<DoubleSparseValue<CatalyticActivityUnit, CatalyticActivity>> data,
            final int rows, final int cols, final StorageType storageType)
    {
        this(data, CatalyticActivityUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a CatalyticActivityMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public CatalyticActivityMatrix(final Collection<DoubleSparseValue<CatalyticActivityUnit, CatalyticActivity>> data,
            final int rows, final int cols)
    {
        this(data, CatalyticActivityUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<CatalyticActivity> getScalarClass()
    {
        return CatalyticActivity.class;
    }

    @Override
    public Class<CatalyticActivityVector> getVectorClass()
    {
        return CatalyticActivityVector.class;
    }

    @Override
    public CatalyticActivityMatrix instantiateMatrix(final DoubleMatrixData dmd, final CatalyticActivityUnit displayUnit)
    {
        return new CatalyticActivityMatrix(dmd, displayUnit);
    }

    @Override
    public CatalyticActivityVector instantiateVector(final DoubleVectorData dvd, final CatalyticActivityUnit displayUnit)
    {
        return new CatalyticActivityVector(dvd, displayUnit);
    }

    @Override
    public CatalyticActivity instantiateScalarSI(final double valueSI, final CatalyticActivityUnit displayUnit)
    {
        CatalyticActivity result = CatalyticActivity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
