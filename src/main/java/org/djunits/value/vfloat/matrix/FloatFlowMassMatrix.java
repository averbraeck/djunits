package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.FlowMassUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRel;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatFlowMass;
import org.djunits.value.vfloat.vector.FloatFlowMassVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatFloatFlowMassMatrix, a matrix of values with a FlowMassUnit.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatFlowMassMatrix extends FloatMatrixRel<FlowMassUnit, FloatFlowMass, FloatFlowMassVector, FloatFlowMassMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a FloatFlowMassMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public FloatFlowMassMatrix(final FloatMatrixData data, final FlowMassUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatFlowMassMatrix from a float[][] object. The float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFlowMassMatrix(final float[][] data, final FlowMassUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatFlowMassMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public FloatFlowMassMatrix(final float[][] data, final FlowMassUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatFlowMassMatrix from a float[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFlowMassMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, FlowMassUnit.SI, storageType);
    }

    /**
     * Construct a FloatFlowMassMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public FloatFlowMassMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FlowMass[][] */

    /**
     * Construct a FloatFlowMassMatrix from an array of an array of FloatFlowMass objects. The FloatFlowMass values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFlowMassMatrix(final FloatFlowMass[][] data, final FlowMassUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatFlowMassMatrix from an array of an array of FloatFlowMass objects. The FloatFlowMass values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public FloatFlowMassMatrix(final FloatFlowMass[][] data, final FlowMassUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatFlowMassMatrix from an array of an array of FloatFlowMass objects. The FloatFlowMass values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFlowMassMatrix(final FloatFlowMass[][] data, final StorageType storageType)
    {
        this(data, FlowMassUnit.SI, storageType);
    }

    /**
     * Construct a FloatFlowMassMatrix from an array of an array of FloatFlowMass objects. The FloatFlowMass values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public FloatFlowMassMatrix(final FloatFlowMass[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatFlowMassMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFlowMassMatrix(final Collection<FloatSparseValue<FlowMassUnit, FloatFlowMass>> data,
            final FlowMassUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatFlowMassMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatFlowMassMatrix(final Collection<FloatSparseValue<FlowMassUnit, FloatFlowMass>> data,
            final FlowMassUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatFlowMassMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatFlowMassMatrix(final Collection<FloatSparseValue<FlowMassUnit, FloatFlowMass>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, FlowMassUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatFlowMassMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public FloatFlowMassMatrix(final Collection<FloatSparseValue<FlowMassUnit, FloatFlowMass>> data, final int rows,
            final int cols)
    {
        this(data, FlowMassUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatFlowMass> getScalarClass()
    {
        return FloatFlowMass.class;
    }

    @Override
    public Class<FloatFlowMassVector> getVectorClass()
    {
        return FloatFlowMassVector.class;
    }

    @Override
    public FloatFlowMassMatrix instantiateMatrix(final FloatMatrixData fmd, final FlowMassUnit displayUnit)
    {
        return new FloatFlowMassMatrix(fmd, displayUnit);
    }

    @Override
    public FloatFlowMassVector instantiateVector(final FloatVectorData fvd, final FlowMassUnit displayUnit)
    {
        return new FloatFlowMassVector(fvd, displayUnit);
    }

    @Override
    public FloatFlowMass instantiateScalarSI(final float valueSI, final FlowMassUnit displayUnit)
    {
        FloatFlowMass result = FloatFlowMass.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
