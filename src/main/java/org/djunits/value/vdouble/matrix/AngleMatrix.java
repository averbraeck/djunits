package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRelWithAbs;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Angle;
import org.djunits.value.vdouble.scalar.Direction;
import org.djunits.value.vdouble.vector.AngleVector;
import org.djunits.value.vdouble.vector.DirectionVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Angle Matrix.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T11:42:31.564730700Z")
public class AngleMatrix extends DoubleMatrixRelWithAbs<DirectionUnit, Direction, DirectionVector, DirectionMatrix, AngleUnit,
        Angle, AngleVector, AngleMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a AngleMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public AngleMatrix(final DoubleMatrixData data, final AngleUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a AngleMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be printed
     * using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AngleMatrix(final double[][] data, final AngleUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a AngleMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public AngleMatrix(final double[][] data, final AngleUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a AngleMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AngleMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, AngleUnit.SI, storageType);
    }

    /**
     * Construct a AngleMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since we offer
     * the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public AngleMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Angle[][] */

    /**
     * Construct a AngleMatrix from an array of an array of Angle objects. The Angle values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AngleMatrix(final Angle[][] data, final AngleUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a AngleMatrix from an array of an array of Angle objects. The Angle values are each expressed in their own
     * unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public AngleMatrix(final Angle[][] data, final AngleUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a AngleMatrix from an array of an array of Angle objects. The Angle values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer the data as
     * an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AngleMatrix(final Angle[][] data, final StorageType storageType)
    {
        this(data, AngleUnit.SI, storageType);
    }

    /**
     * Construct a AngleMatrix from an array of an array of Angle objects. The Angle values are each expressed in their own
     * unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the StorageType
     * is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public AngleMatrix(final Angle[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a AngleMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AngleMatrix(final Collection<DoubleSparseValue<AngleUnit, Angle>> data, final AngleUnit displayUnit, final int rows,
            final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a AngleMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the storage
     * type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public AngleMatrix(final Collection<DoubleSparseValue<AngleUnit, Angle>> data, final AngleUnit displayUnit, final int rows,
            final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a AngleMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public AngleMatrix(final Collection<DoubleSparseValue<AngleUnit, Angle>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, AngleUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a AngleMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit in
     * which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit or
     * base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public AngleMatrix(final Collection<DoubleSparseValue<AngleUnit, Angle>> data, final int rows, final int cols)
    {
        this(data, AngleUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<Angle> getScalarClass()
    {
        return Angle.class;
    }

    @Override
    public Class<AngleVector> getVectorClass()
    {
        return AngleVector.class;
    }

    @Override
    public AngleMatrix instantiateMatrix(final DoubleMatrixData dmd, final AngleUnit displayUnit)
    {
        return new AngleMatrix(dmd, displayUnit);
    }

    @Override
    public AngleVector instantiateVector(final DoubleVectorData dvd, final AngleUnit displayUnit)
    {
        return new AngleVector(dvd, displayUnit);
    }

    @Override
    public Angle instantiateScalarSI(final double valueSI, final AngleUnit displayUnit)
    {
        Angle result = Angle.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public DirectionMatrix instantiateMatrixAbs(final DoubleMatrixData dmd, final DirectionUnit displayUnit)
    {
        return new DirectionMatrix(dmd, displayUnit);
    }

    @Override
    public DirectionVector instantiateVectorAbs(final DoubleVectorData dvd, final DirectionUnit displayUnit)
    {
        return new DirectionVector(dvd, displayUnit);
    }

    @Override
    public Direction instantiateScalarAbsSI(final double valueSI, final DirectionUnit displayUnit)
    {
        Direction result = Direction.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
