package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.MagneticFluxDensityUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatMagneticFluxDensity;
import org.djunits.value.vfloat.vector.FloatMagneticFluxDensityVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatMagneticFluxDensityMatrix, a matrix of values with a MagneticFluxDensityUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatMagneticFluxDensityMatrix extends FloatMatrixRel<MagneticFluxDensityUnit, FloatMagneticFluxDensity,
        FloatMagneticFluxDensityVector, FloatMagneticFluxDensityMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatMagneticFluxDensityMatrix from an internal data object.
     * @param data FloatMatrixData; the internal data object for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the display unit of the matrix data
     */
    public FloatMagneticFluxDensityMatrix(final FloatMatrixData data, final MagneticFluxDensityUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatMagneticFluxDensityMatrix from a float[][] object. The float values are expressed in the displayUnit,
     * and will be printed using the displayUnit.
     * @param data float[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit MagneticFluxDensityUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxDensityMatrix(final float[][] data, final MagneticFluxDensityUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatMagneticFluxDensityMatrix from a float[][] object. The float values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatMagneticFluxDensityMatrix(final float[][] data, final MagneticFluxDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatMagneticFluxDensityMatrix from a float[][] object with SI-unit values.
     * @param data float[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxDensityMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, MagneticFluxDensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatMagneticFluxDensityMatrix from a float[][] object with SI-unit values. Assume that the StorageType is
     * DENSE since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix, in SI units
     */
    public FloatMagneticFluxDensityMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH MagneticFluxDensity[][] */

    /**
     * Construct a FloatMagneticFluxDensityMatrix from an array of an array of FloatMagneticFluxDensity objects. The
     * FloatMagneticFluxDensity values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing.
     * @param data FloatMagneticFluxDensity[][]; the data for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxDensityMatrix(final FloatMagneticFluxDensity[][] data, final MagneticFluxDensityUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatMagneticFluxDensityMatrix from an array of an array of FloatMagneticFluxDensity objects. The
     * FloatMagneticFluxDensity values are each expressed in their own unit, but will be internally stored as SI values, all
     * expressed in the displayUnit when printing. Assume that the StorageType is DENSE since we offer the data as an array of
     * an array.
     * @param data FloatMagneticFluxDensity[][]; the data for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the display unit of the values when printing
     */
    public FloatMagneticFluxDensityMatrix(final FloatMagneticFluxDensity[][] data, final MagneticFluxDensityUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatMagneticFluxDensityMatrix from an array of an array of FloatMagneticFluxDensity objects. The
     * FloatMagneticFluxDensity values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. since we offer the data as an array of an array.
     * @param data FloatMagneticFluxDensity[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxDensityMatrix(final FloatMagneticFluxDensity[][] data, final StorageType storageType)
    {
        this(data, MagneticFluxDensityUnit.SI, storageType);
    }

    /**
     * Construct a FloatMagneticFluxDensityMatrix from an array of an array of FloatMagneticFluxDensity objects. The
     * FloatMagneticFluxDensity values are each expressed in their own unit, but will be internally stored as SI values, and
     * expressed using SI units when printing. Assume that the StorageType is DENSE since we offer the data as an array of an
     * array.
     * @param data FloatMagneticFluxDensity[][]; the data for the matrix
     */
    public FloatMagneticFluxDensityMatrix(final FloatMagneticFluxDensity[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatMagneticFluxDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxDensityMatrix(
            final Collection<FloatSparseValue<MagneticFluxDensityUnit, FloatMagneticFluxDensity>> data,
            final MagneticFluxDensityUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatMagneticFluxDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit MagneticFluxDensityUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatMagneticFluxDensityMatrix(
            final Collection<FloatSparseValue<MagneticFluxDensityUnit, FloatMagneticFluxDensity>> data,
            final MagneticFluxDensityUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatMagneticFluxDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxDensityMatrix(
            final Collection<FloatSparseValue<MagneticFluxDensityUnit, FloatMagneticFluxDensity>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, MagneticFluxDensityUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatMagneticFluxDensityMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit
     * indicates the unit in which the values in the collection are expressed, as well as the unit in which they will be
     * printed. Use the SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a
     * collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatMagneticFluxDensityMatrix(
            final Collection<FloatSparseValue<MagneticFluxDensityUnit, FloatMagneticFluxDensity>> data, final int rows,
            final int cols)
    {
        this(data, MagneticFluxDensityUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatMagneticFluxDensity> getScalarClass()
    {
        return FloatMagneticFluxDensity.class;
    }

    @Override
    public Class<FloatMagneticFluxDensityVector> getVectorClass()
    {
        return FloatMagneticFluxDensityVector.class;
    }

    @Override
    public FloatMagneticFluxDensityMatrix instantiateMatrix(final FloatMatrixData fmd,
            final MagneticFluxDensityUnit displayUnit)
    {
        return new FloatMagneticFluxDensityMatrix(fmd, displayUnit);
    }

    @Override
    public FloatMagneticFluxDensityVector instantiateVector(final FloatVectorData fvd,
            final MagneticFluxDensityUnit displayUnit)
    {
        return new FloatMagneticFluxDensityVector(fvd, displayUnit);
    }

    @Override
    public FloatMagneticFluxDensity instantiateScalarSI(final float valueSI, final MagneticFluxDensityUnit displayUnit)
    {
        FloatMagneticFluxDensity result = FloatMagneticFluxDensity.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
