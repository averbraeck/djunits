package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.MagneticFluxUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatMagneticFlux;
import org.djunits.value.vfloat.vector.FloatMagneticFluxVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatMagneticFluxMatrix, a matrix of values with a MagneticFluxUnit.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-06-22T21:38:04.910968700Z")
public class FloatMagneticFluxMatrix
        extends FloatMatrixRel<MagneticFluxUnit, FloatMagneticFlux, FloatMagneticFluxVector, FloatMagneticFluxMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatMagneticFluxMatrix from an internal data object.
     * @param data FloatMatrixData; the internal data object for the matrix
     * @param displayUnit MagneticFluxUnit; the display unit of the matrix data
     */
    public FloatMagneticFluxMatrix(final FloatMatrixData data, final MagneticFluxUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatMagneticFluxMatrix from a float[][] object. The float values are expressed in the displayUnit, and will
     * be printed using the displayUnit.
     * @param data float[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit MagneticFluxUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxMatrix(final float[][] data, final MagneticFluxUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatMagneticFluxMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix
     * @param displayUnit MagneticFluxUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatMagneticFluxMatrix(final float[][] data, final MagneticFluxUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatMagneticFluxMatrix from a float[][] object with SI-unit values.
     * @param data float[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, MagneticFluxUnit.SI, storageType);
    }

    /**
     * Construct a FloatMagneticFluxMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix, in SI units
     */
    public FloatMagneticFluxMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH MagneticFlux[][] */

    /**
     * Construct a FloatMagneticFluxMatrix from an array of an array of FloatMagneticFlux objects. The FloatMagneticFlux values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data FloatMagneticFlux[][]; the data for the matrix
     * @param displayUnit MagneticFluxUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxMatrix(final FloatMagneticFlux[][] data, final MagneticFluxUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatMagneticFluxMatrix from an array of an array of FloatMagneticFlux objects. The FloatMagneticFlux values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatMagneticFlux[][]; the data for the matrix
     * @param displayUnit MagneticFluxUnit; the display unit of the values when printing
     */
    public FloatMagneticFluxMatrix(final FloatMagneticFlux[][] data, final MagneticFluxUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatMagneticFluxMatrix from an array of an array of FloatMagneticFlux objects. The FloatMagneticFlux values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. since we offer the data as an array of an array.
     * @param data FloatMagneticFlux[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxMatrix(final FloatMagneticFlux[][] data, final StorageType storageType)
    {
        this(data, MagneticFluxUnit.SI, storageType);
    }

    /**
     * Construct a FloatMagneticFluxMatrix from an array of an array of FloatMagneticFlux objects. The FloatMagneticFlux values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatMagneticFlux[][]; the data for the matrix
     */
    public FloatMagneticFluxMatrix(final FloatMagneticFlux[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatMagneticFluxMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit MagneticFluxUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxMatrix(final Collection<FloatSparseValue<MagneticFluxUnit, FloatMagneticFlux>> data,
            final MagneticFluxUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatMagneticFluxMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit MagneticFluxUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatMagneticFluxMatrix(final Collection<FloatSparseValue<MagneticFluxUnit, FloatMagneticFlux>> data,
            final MagneticFluxUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatMagneticFluxMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatMagneticFluxMatrix(final Collection<FloatSparseValue<MagneticFluxUnit, FloatMagneticFlux>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, MagneticFluxUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatMagneticFluxMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatMagneticFluxMatrix(final Collection<FloatSparseValue<MagneticFluxUnit, FloatMagneticFlux>> data, final int rows,
            final int cols)
    {
        this(data, MagneticFluxUnit.SI, rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMagneticFlux> getScalarClass()
    {
        return FloatMagneticFlux.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<FloatMagneticFluxVector> getVectorClass()
    {
        return FloatMagneticFluxVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public FloatMagneticFluxMatrix instantiateMatrix(final FloatMatrixData fmd, final MagneticFluxUnit displayUnit)
    {
        return new FloatMagneticFluxMatrix(fmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMagneticFluxVector instantiateVector(final FloatVectorData fvd, final MagneticFluxUnit displayUnit)
    {
        return new FloatMagneticFluxVector(fvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public FloatMagneticFlux instantiateScalarSI(final float valueSI, final MagneticFluxUnit displayUnit)
    {
        FloatMagneticFlux result = FloatMagneticFlux.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
