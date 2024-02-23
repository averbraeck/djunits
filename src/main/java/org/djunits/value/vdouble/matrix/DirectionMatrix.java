package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.AngleUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixAbs;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Angle;
import org.djunits.value.vdouble.scalar.Direction;
import org.djunits.value.vdouble.vector.AngleVector;
import org.djunits.value.vdouble.vector.DirectionVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Direction Matrix.
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2023-07-23T14:06:38.224104100Z")
public class DirectionMatrix extends
        DoubleMatrixAbs<DirectionUnit, Direction, DirectionVector, DirectionMatrix, AngleUnit, Angle, AngleVector, AngleMatrix>
{
    /** */
    private static final long serialVersionUID = 20151006L;

    /**
     * Construct a DirectionMatrix from an internal data object.
     * @param data DoubleMatrixData; the internal data object for the matrix
     * @param displayUnit DirectionUnit; the display unit of the matrix data
     */
    public DirectionMatrix(final DoubleMatrixData data, final DirectionUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a DirectionMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data double[][]; the data for the matrix, expressed in the displayUnit
     * @param displayUnit DirectionUnit; the unit of the values in the data array, and display unit when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DirectionMatrix(final double[][] data, final DirectionUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a DirectionMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data double[][]; the data for the matrix
     * @param displayUnit DirectionUnit; the unit of the values in the data array, and display unit when printing
     */
    public DirectionMatrix(final double[][] data, final DirectionUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a DirectionMatrix from a double[][] object with SI-unit values.
     * @param data double[][]; the data for the matrix, in SI units
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DirectionMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, DirectionUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a DirectionMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since we
     * offer the data as an array of an array.
     * @param data double[][]; the data for the matrix, in SI units
     */
    public DirectionMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Direction[][] */

    /**
     * Construct a DirectionMatrix from an array of an array of Direction objects. The Direction values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data Direction[][]; the data for the matrix
     * @param displayUnit DirectionUnit; the display unit of the values when printing
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DirectionMatrix(final Direction[][] data, final DirectionUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a DirectionMatrix from an array of an array of Direction objects. The Direction values are each expressed in
     * their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data Direction[][]; the data for the matrix
     * @param displayUnit DirectionUnit; the display unit of the values when printing
     */
    public DirectionMatrix(final Direction[][] data, final DirectionUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a DirectionMatrix from an array of an array of Direction objects. The Direction values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. since we offer
     * the data as an array of an array.
     * @param data Direction[][]; the data for the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DirectionMatrix(final Direction[][] data, final StorageType storageType)
    {
        this(data, DirectionUnit.BASE.getStandardUnit(), storageType);
    }

    /**
     * Construct a DirectionMatrix from an array of an array of Direction objects. The Direction values are each expressed in
     * their own unit, but will be internally stored as SI values, and expressed using SI units when printing. Assume that the
     * StorageType is DENSE since we offer the data as an array of an array.
     * @param data Direction[][]; the data for the matrix
     */
    public DirectionMatrix(final Direction[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a DirectionMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit DirectionUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DirectionMatrix(final Collection<DoubleSparseValue<DirectionUnit, Direction>> data, final DirectionUnit displayUnit,
            final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a DirectionMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param displayUnit DirectionUnit; the display unit of the matrix data, and the unit of the data points
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public DirectionMatrix(final Collection<DoubleSparseValue<DirectionUnit, Direction>> data, final DirectionUnit displayUnit,
            final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a DirectionMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     * @param storageType StorageType; the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DirectionMatrix(final Collection<DoubleSparseValue<DirectionUnit, Direction>> data, final int rows, final int cols,
            final StorageType storageType)
    {
        this(data, DirectionUnit.BASE.getStandardUnit(), rows, cols, storageType);
    }

    /**
     * Construct a DirectionMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the unit
     * in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI unit
     * or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data Collection&lt;DoubleSparseValue&gt;; the data for the matrix
     * @param rows int; the number of rows of the matrix
     * @param cols int; the number of columns of the matrix
     */
    public DirectionMatrix(final Collection<DoubleSparseValue<DirectionUnit, Direction>> data, final int rows, final int cols)
    {
        this(data, DirectionUnit.BASE.getStandardUnit(), rows, cols, StorageType.SPARSE);
    }

    /** {@inheritDoc} */
    @Override
    public Class<Direction> getScalarClass()
    {
        return Direction.class;
    }

    /** {@inheritDoc} */
    @Override
    public Class<DirectionVector> getVectorClass()
    {
        return DirectionVector.class;
    }

    /** {@inheritDoc} */
    @Override
    public DirectionMatrix instantiateMatrix(final DoubleMatrixData dmd, final DirectionUnit displayUnit)
    {
        return new DirectionMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public DirectionVector instantiateVector(final DoubleVectorData dvd, final DirectionUnit displayUnit)
    {
        return new DirectionVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Direction instantiateScalarSI(final double valueSI, final DirectionUnit displayUnit)
    {
        Direction result = Direction.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public AngleMatrix instantiateMatrixRel(final DoubleMatrixData dmd, final AngleUnit displayUnit)
    {
        return new AngleMatrix(dmd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public AngleVector instantiateVectorRel(final DoubleVectorData dvd, final AngleUnit displayUnit)
    {
        return new AngleVector(dvd, displayUnit);
    }

    /** {@inheritDoc} */
    @Override
    public Angle instantiateScalarRelSI(final double valueSI, final AngleUnit displayUnit)
    {
        Angle result = Angle.instantiateSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
