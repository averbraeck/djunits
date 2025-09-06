package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.LuminousFluxUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatLuminousFlux;
import org.djunits.value.vfloat.vector.FloatLuminousFluxVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatLuminousFluxMatrix, a matrix of values with a LuminousFluxUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class FloatLuminousFluxMatrix
        extends FloatMatrixRel<LuminousFluxUnit, FloatLuminousFlux, FloatLuminousFluxVector, FloatLuminousFluxMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatLuminousFluxMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatLuminousFluxMatrix(final FloatMatrixData data, final LuminousFluxUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatLuminousFluxMatrix from a float[][] object. The float values are expressed in the displayUnit, and will
     * be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousFluxMatrix(final float[][] data, final LuminousFluxUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatLuminousFluxMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume
     * that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatLuminousFluxMatrix(final float[][] data, final LuminousFluxUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatLuminousFluxMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousFluxMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, LuminousFluxUnit.SI, storageType);
    }

    /**
     * Construct a FloatLuminousFluxMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatLuminousFluxMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH LuminousFlux[][] */

    /**
     * Construct a FloatLuminousFluxMatrix from an array of an array of FloatLuminousFlux objects. The FloatLuminousFlux values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousFluxMatrix(final FloatLuminousFlux[][] data, final LuminousFluxUnit displayUnit,
            final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatLuminousFluxMatrix from an array of an array of FloatLuminousFlux objects. The FloatLuminousFlux values
     * are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatLuminousFluxMatrix(final FloatLuminousFlux[][] data, final LuminousFluxUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatLuminousFluxMatrix from an array of an array of FloatLuminousFlux objects. The FloatLuminousFlux values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousFluxMatrix(final FloatLuminousFlux[][] data, final StorageType storageType)
    {
        this(data, LuminousFluxUnit.SI, storageType);
    }

    /**
     * Construct a FloatLuminousFluxMatrix from an array of an array of FloatLuminousFlux objects. The FloatLuminousFlux values
     * are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when
     * printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FloatLuminousFluxMatrix(final FloatLuminousFlux[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatLuminousFluxMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousFluxMatrix(final Collection<FloatSparseValue<LuminousFluxUnit, FloatLuminousFlux>> data,
            final LuminousFluxUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatLuminousFluxMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatLuminousFluxMatrix(final Collection<FloatSparseValue<LuminousFluxUnit, FloatLuminousFlux>> data,
            final LuminousFluxUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatLuminousFluxMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatLuminousFluxMatrix(final Collection<FloatSparseValue<LuminousFluxUnit, FloatLuminousFlux>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, LuminousFluxUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatLuminousFluxMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatLuminousFluxMatrix(final Collection<FloatSparseValue<LuminousFluxUnit, FloatLuminousFlux>> data, final int rows,
            final int cols)
    {
        this(data, LuminousFluxUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatLuminousFlux> getScalarClass()
    {
        return FloatLuminousFlux.class;
    }

    @Override
    public Class<FloatLuminousFluxVector> getVectorClass()
    {
        return FloatLuminousFluxVector.class;
    }

    @Override
    public FloatLuminousFluxMatrix instantiateMatrix(final FloatMatrixData fmd, final LuminousFluxUnit displayUnit)
    {
        return new FloatLuminousFluxMatrix(fmd, displayUnit);
    }

    @Override
    public FloatLuminousFluxVector instantiateVector(final FloatVectorData fvd, final LuminousFluxUnit displayUnit)
    {
        return new FloatLuminousFluxVector(fvd, displayUnit);
    }

    @Override
    public FloatLuminousFlux instantiateScalarSI(final float valueSI, final LuminousFluxUnit displayUnit)
    {
        FloatLuminousFlux result = FloatLuminousFlux.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
