package org.djunits.value.vfloat.matrix;

import java.util.Collection;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vfloat.matrix.base.FloatMatrixRelWithAbs;
import org.djunits.value.vfloat.matrix.base.FloatSparseValue;
import org.djunits.value.vfloat.matrix.data.FloatMatrixData;
import org.djunits.value.vfloat.scalar.FloatAngle;
import org.djunits.value.vfloat.scalar.FloatDirection;
import org.djunits.value.vfloat.vector.FloatAngleVector;
import org.djunits.value.vfloat.vector.FloatDirectionVector;
import org.djunits.value.vfloat.vector.data.FloatVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable FloatAngle Matrix.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class FloatAngleMatrix extends FloatMatrixRelWithAbs<DirectionUnit, FloatDirection, FloatDirectionVector,
        FloatDirectionMatrix, AngleUnit, FloatAngle, FloatAngleVector, FloatAngleMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a FloatAngleMatrix from an internal data object.
     * @param data FloatMatrixData; the internal data object for the matrix
     * @param displayUnit AngleUnit; the display unit of the matrix data
     */
    public FloatAngleMatrix(final FloatMatrixData data, final AngleUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH float[][] */

    /**
     * Construct a FloatAngleMatrix from a float[][] object. The float values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data float[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit AngleUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAngleMatrix(final float[][] data, final AngleUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a FloatAngleMatrix from a float[][] object. The float values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data float[][]; the data for the matrix
     * @param displayUnit AngleUnit; the unit of the values in the data array, and display unit when printing
     */
    public FloatAngleMatrix(final float[][] data, final AngleUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAngleMatrix from a float[][] object with SI-unit values.
     * @param data float[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAngleMatrix(final float[][] data, final StorageType storageType)
    {
        this(data, AngleUnit.SI, storageType);
    }

    /**
     * Construct a FloatAngleMatrix from a float[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data float[][]; the data for the matrix, in SI units
     */
    public FloatAngleMatrix(final float[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH FloatAngle[][] */

    /**
     * Construct a FloatAngleMatrix from an array of an array of FloatAngle objects. The FloatAngle values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data FloatAngle[][]; the data for the matrix
     * @param displayUnit AngleUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAngleMatrix(final FloatAngle[][] data, final AngleUnit displayUnit, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a FloatAngleMatrix from an array of an array of FloatAngle objects. The FloatAngle values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatAngle[][]; the data for the matrix
     * @param displayUnit AngleUnit; the display unit of the values when printing
     */
    public FloatAngleMatrix(final FloatAngle[][] data, final AngleUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a FloatAngleMatrix from an array of an array of FloatAngle objects. The FloatAngle values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array of an array.
     * @param data FloatAngle[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAngleMatrix(final FloatAngle[][] data, final StorageType storageType)
    {
        this(data, AngleUnit.SI, storageType);
    }

    /**
     * Construct a FloatAngleMatrix from an array of an array of FloatAngle objects. The FloatAngle values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data FloatAngle[][]; the data for the matrix
     */
    public FloatAngleMatrix(final FloatAngle[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<FloatSparseValue> */

    /**
     * Construct a FloatAngleMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit AngleUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAngleMatrix(final Collection<FloatSparseValue<AngleUnit, FloatAngle>> data, final AngleUnit displayUnit,
            final int rows, final int cols, final StorageType storageType)
    {
        this(FloatMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a FloatAngleMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param displayUnit AngleUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatAngleMatrix(final Collection<FloatSparseValue<AngleUnit, FloatAngle>> data, final AngleUnit displayUnit,
            final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a FloatAngleMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public FloatAngleMatrix(final Collection<FloatSparseValue<AngleUnit, FloatAngle>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, AngleUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a FloatAngleMatrix from a (sparse) collection of FloatSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;FloatSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public FloatAngleMatrix(final Collection<FloatSparseValue<AngleUnit, FloatAngle>> data, final int rows, final int cols)
    {
        this(data, AngleUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<FloatAngle> getScalarClass()
    {
        return FloatAngle.class;
    }

    @Override
    public Class<FloatAngleVector> getVectorClass()
    {
        return FloatAngleVector.class;
    }

    @Override
    public FloatAngleMatrix instantiateMatrix(final FloatMatrixData fmd, final AngleUnit displayUnit)
    {
        return new FloatAngleMatrix(fmd, displayUnit);
    }

    @Override
    public FloatAngleVector instantiateVector(final FloatVectorData fvd, final AngleUnit displayUnit)
    {
        return new FloatAngleVector(fvd, displayUnit);
    }

    @Override
    public FloatAngle instantiateScalarSI(final float valueSI, final AngleUnit displayUnit)
    {
        FloatAngle result = FloatAngle.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public FloatDirectionMatrix instantiateMatrixAbs(final FloatMatrixData fmd, final DirectionUnit displayUnit)
    {
        return new FloatDirectionMatrix(fmd, displayUnit);
    }

    @Override
    public FloatDirectionVector instantiateVectorAbs(final FloatVectorData fvd, final DirectionUnit displayUnit)
    {
        return new FloatDirectionVector(fvd, displayUnit);
    }

    @Override
    public FloatDirection instantiateScalarAbsSI(final float valueSI, final DirectionUnit displayUnit)
    {
        FloatDirection result = FloatDirection.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
