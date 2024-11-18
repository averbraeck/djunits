package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixAbs;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.Temperature;
import org.djunits.value.vdouble.vector.AbsoluteTemperatureVector;
import org.djunits.value.vdouble.vector.TemperatureVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable AbsoluteTemperature Matrix.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class AbsoluteTemperatureMatrix
        extends DoubleMatrixAbs<AbsoluteTemperatureUnit, AbsoluteTemperature, AbsoluteTemperatureVector,
                AbsoluteTemperatureMatrix, TemperatureUnit, Temperature, TemperatureVector, TemperatureMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a AbsoluteTemperatureMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public AbsoluteTemperatureMatrix(final DoubleMatrixData data, final AbsoluteTemperatureUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a AbsoluteTemperatureMatrix from a double[][] object. The double values are expressed in the displayUnit, and
     * will be printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AbsoluteTemperatureMatrix(final double[][] data, final AbsoluteTemperatureUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a AbsoluteTemperatureMatrix from a double[][] object. The double values are expressed in the displayUnit.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public AbsoluteTemperatureMatrix(final double[][] data, final AbsoluteTemperatureUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a AbsoluteTemperatureMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AbsoluteTemperatureMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, AbsoluteTemperatureUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a AbsoluteTemperatureMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE
     * since we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public AbsoluteTemperatureMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH AbsoluteTemperature[][] */

    /**
     * Construct a AbsoluteTemperatureMatrix from an array of an array of AbsoluteTemperature objects. The AbsoluteTemperature
     * values are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit
     * when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AbsoluteTemperatureMatrix(final AbsoluteTemperature[][] data, final AbsoluteTemperatureUnit displayUnit,
            final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a AbsoluteTemperatureMatrix from an array of an array of AbsoluteTemperature objects. The AbsoluteTemperature
     * values are each expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit
     * when printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public AbsoluteTemperatureMatrix(final AbsoluteTemperature[][] data, final AbsoluteTemperatureUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a AbsoluteTemperatureMatrix from an array of an array of AbsoluteTemperature objects. The AbsoluteTemperature
     * values are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units
     * when printing. since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AbsoluteTemperatureMatrix(final AbsoluteTemperature[][] data, final StorageType storageType)
    {
        this(data, AbsoluteTemperatureUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a AbsoluteTemperatureMatrix from an array of an array of AbsoluteTemperature objects. The AbsoluteTemperature
     * values are each expressed in their own unit, but will be internally stored as SI values, and expressed using SI units
     * when printing. Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public AbsoluteTemperatureMatrix(final AbsoluteTemperature[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a AbsoluteTemperatureMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AbsoluteTemperatureMatrix(final Collection<DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>> data,
            final AbsoluteTemperatureUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a AbsoluteTemperatureMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume
     * the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public AbsoluteTemperatureMatrix(final Collection<DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>> data,
            final AbsoluteTemperatureUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a AbsoluteTemperatureMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AbsoluteTemperatureMatrix(final Collection<DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>> data,
            final int rows, final int cols, final StorageType storageType)
    {
        this(data, AbsoluteTemperatureUnit.BASE.getStandardUnit(), rows, cols, storageType);
    }

    /**
     * Construct a AbsoluteTemperatureMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates
     * the unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the
     * SI unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public AbsoluteTemperatureMatrix(final Collection<DoubleSparseValue<AbsoluteTemperatureUnit, AbsoluteTemperature>> data,
            final int rows, final int cols)
    {
        this(data, AbsoluteTemperatureUnit.BASE.getStandardUnit(), rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<AbsoluteTemperature> getScalarClass()
    {
        return AbsoluteTemperature.class;
    }

    @Override
    public Class<AbsoluteTemperatureVector> getVectorClass()
    {
        return AbsoluteTemperatureVector.class;
    }

    @Override
    public AbsoluteTemperatureMatrix instantiateMatrix(final DoubleMatrixData dmd, final AbsoluteTemperatureUnit displayUnit)
    {
        return new AbsoluteTemperatureMatrix(dmd, displayUnit);
    }

    @Override
    public AbsoluteTemperatureVector instantiateVector(final DoubleVectorData dvd, final AbsoluteTemperatureUnit displayUnit)
    {
        return new AbsoluteTemperatureVector(dvd, displayUnit);
    }

    @Override
    public AbsoluteTemperature instantiateScalarSI(final double valueSI, final AbsoluteTemperatureUnit displayUnit)
    {
        AbsoluteTemperature result = AbsoluteTemperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public TemperatureMatrix instantiateMatrixRel(final DoubleMatrixData dmd, final TemperatureUnit displayUnit)
    {
        return new TemperatureMatrix(dmd, displayUnit);
    }

    @Override
    public TemperatureVector instantiateVectorRel(final DoubleVectorData dvd, final TemperatureUnit displayUnit)
    {
        return new TemperatureVector(dvd, displayUnit);
    }

    @Override
    public Temperature instantiateScalarRelSI(final double valueSI, final TemperatureUnit displayUnit)
    {
        Temperature result = Temperature.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
