package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixAbs;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatTemperature;
import org.djunits.value.vfloat.vector.FloatAbsoluteTemperatureVector;
import org.djunits.value.vfloat.vector.FloatTemperatureVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatAbsoluteTemperature Matrix.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatAbsoluteTemperatureMatrix extends
        FloatMatrixAbs<AbsoluteTemperatureUnit, FloatAbsoluteTemperature, FloatAbsoluteTemperatureVector,
                FloatAbsoluteTemperatureMatrix, TemperatureUnit, FloatTemperature, FloatTemperatureVector,
                FloatTemperatureMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a AbsoluteTemperatureMatrix from an internal data object.
     * @param data FloatMatrixData; the internal data object for the matrix
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the matrix data
     */
    public FloatAbsoluteTemperatureMatrix(final FloatMatrixData data, final AbsoluteTemperatureUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from a float[][] object. The float values are expressed in the displayUnit,
     * and will be printed using the displayUnit.
     * @param data float[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit AbsoluteTemperatureUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsoluteTemperatureMatrix(final float[][] data, final AbsoluteTemperatureUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from a float[][] object. The float values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix
     * @param displayUnit AbsoluteTemperatureUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatAbsoluteTemperatureMatrix(final float[][] data, final AbsoluteTemperatureUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from a float[][] object with SI-unit values.
     * @param data float[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsoluteTemperatureMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, AbsoluteTemperatureUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from a float[][] object with SI-unit values. Assume that the StorageType is
     * DENSE since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix, in SI units
     */
    public FloatAbsoluteTemperatureMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatAbsoluteTemperature[][] */

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from an array of an array of FloatAbsoluteTemperature objects. The
     * FloatAbsoluteTemperature values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing.
     * @param data FloatAbsoluteTemperature[][]; the data for the matrix
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsoluteTemperatureMatrix(final FloatAbsoluteTemperature[][] data, final AbsoluteTemperatureUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from an array of an array of FloatAbsoluteTemperature objects. The
     * FloatAbsoluteTemperature values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing. Assume that the StorageType is DENSE since we offer the data as an array of
     * an array.
     * @param data FloatAbsoluteTemperature[][]; the data for the matrix
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the values when printing
     */
    public FloatAbsoluteTemperatureMatrix(final FloatAbsoluteTemperature[][] data, final AbsoluteTemperatureUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from an array of an array of FloatAbsoluteTemperature objects. The
     * FloatAbsoluteTemperature values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. since we offer the data as an array of an array.
     * @param data FloatAbsoluteTemperature[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsoluteTemperatureMatrix(final FloatAbsoluteTemperature[][] data, final StorageType storageType)
    {
        this(data, AbsoluteTemperatureUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from an array of an array of FloatAbsoluteTemperature objects. The
     * FloatAbsoluteTemperature values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. Assume that the StorageType is DENSE since we offer the data as an array of an
     * array.
     * @param data FloatAbsoluteTemperature[][]; the data for the matrix
     */
    public FloatAbsoluteTemperatureMatrix(final FloatAbsoluteTemperature[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsoluteTemperatureMatrix(
            final Collection<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>> data,
            final AbsoluteTemperatureUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit AbsoluteTemperatureUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatAbsoluteTemperatureMatrix(
            final Collection<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>> data,
            final AbsoluteTemperatureUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAbsoluteTemperatureMatrix(
            final Collection<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, AbsoluteTemperatureUnit.BASE.getStandardUnit(), rows, cols, storageType);
    }

    /**
     * Construct a FloatAbsoluteTemperatureMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a
     * collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatAbsoluteTemperatureMatrix(
            final Collection<FloatSparseValue<AbsoluteTemperatureUnit, FloatAbsoluteTemperature>> data, final int rows,
            final int cols)
    {
        this(data, AbsoluteTemperatureUnit.BASE.getStandardUnit(), rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAbsoluteTemperature> getScalarClass()
    {
        return FloatAbsoluteTemperature.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatAbsoluteTemperatureVector> getVectorClass()
    {
        return FloatAbsoluteTemperatureVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsoluteTemperatureMatrix instantiateMatrix(final FloatMatrixData fmd,
            final AbsoluteTemperatureUnit displayUnit)
    {
        return new FloatAbsoluteTemperatureMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsoluteTemperatureVector instantiateVector(final FloatVectorData fvd,
            final AbsoluteTemperatureUnit displayUnit)
    {
        return new FloatAbsoluteTemperatureVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatAbsoluteTemperature instantiateScalarSI(final float valueSI, final AbsoluteTemperatureUnit displayUnit)
    {
        FloatAbsoluteTemperature result = FloatAbsoluteTemperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public FloatTemperatureMatrix instantiateMatrixRel(final FloatMatrixData fmd, final TemperatureUnit displayUnit)
    {
        return new FloatTemperatureMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatTemperatureVector instantiateVectorRel(final FloatVectorData fvd, final TemperatureUnit displayUnit)
    {
        return new FloatTemperatureVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatTemperature instantiateScalarRelSI(final float valueSI, final TemperatureUnit displayUnit)
    {
        FloatTemperature result = FloatTemperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
