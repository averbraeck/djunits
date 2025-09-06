package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.DimensionlessUnit;
import org.djunits.value.function.DimensionlessFunctions;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.function.DoubleMathFunctions;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.Dimensionless;
import org.djunits.value.vdouble.vector.DimensionlessVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double DimensionlessMatrix, a matrix of values with a DimensionlessUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T07:51:33.095478900Z")
public class DimensionlessMatrix
        extends DoubleMatrixRel<DimensionlessUnit, Dimensionless, DimensionlessVector, DimensionlessMatrix>
        implements DimensionlessFunctions<DimensionlessUnit, DimensionlessMatrix>
{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a DimensionlessMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public DimensionlessMatrix(final DoubleMatrixData data, final DimensionlessUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a DimensionlessMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DimensionlessMatrix(final double[][] data, final DimensionlessUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a DimensionlessMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public DimensionlessMatrix(final double[][] data, final DimensionlessUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a DimensionlessMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DimensionlessMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, DimensionlessUnit.SI, storageType);
    }

    /**
     * Construct a DimensionlessMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public DimensionlessMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Dimensionless[][] */

    /**
     * Construct a DimensionlessMatrix from an array of an array of Dimensionless objects. The Dimensionless values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DimensionlessMatrix(final Dimensionless[][] data, final DimensionlessUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a DimensionlessMatrix from an array of an array of Dimensionless objects. The Dimensionless values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public DimensionlessMatrix(final Dimensionless[][] data, final DimensionlessUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a DimensionlessMatrix from an array of an array of Dimensionless objects. The Dimensionless values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DimensionlessMatrix(final Dimensionless[][] data, final StorageType storageType)
    {
        this(data, DimensionlessUnit.SI, storageType);
    }

    /**
     * Construct a DimensionlessMatrix from an array of an array of Dimensionless objects. The Dimensionless values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public DimensionlessMatrix(final Dimensionless[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a DimensionlessMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DimensionlessMatrix(final Collection<DoubleSparseValue<DimensionlessUnit, Dimensionless>> data,
            final DimensionlessUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a DimensionlessMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public DimensionlessMatrix(final Collection<DoubleSparseValue<DimensionlessUnit, Dimensionless>> data,
            final DimensionlessUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a DimensionlessMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public DimensionlessMatrix(final Collection<DoubleSparseValue<DimensionlessUnit, Dimensionless>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, DimensionlessUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a DimensionlessMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public DimensionlessMatrix(final Collection<DoubleSparseValue<DimensionlessUnit, Dimensionless>> data, final int rows,
            final int cols)
    {
        this(data, DimensionlessUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<Dimensionless> getScalarClass()
    {
        return Dimensionless.class;
    }

    @Override
    public Class<DimensionlessVector> getVectorClass()
    {
        return DimensionlessVector.class;
    }

    @Override
    public DimensionlessMatrix instantiateMatrix(final DoubleMatrixData dmd, final DimensionlessUnit displayUnit)
    {
        return new DimensionlessMatrix(dmd, displayUnit);
    }

    @Override
    public DimensionlessVector instantiateVector(final DoubleVectorData dvd, final DimensionlessUnit displayUnit)
    {
        return new DimensionlessVector(dvd, displayUnit);
    }

    @Override
    public Dimensionless instantiateScalarSI(final double valueSI, final DimensionlessUnit displayUnit)
    {
        Dimensionless result = Dimensionless.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

    @Override
    public final DimensionlessMatrix acos()
    {
        assign(DoubleMathFunctions.ACOS);
        return this;
    }

    @Override
    public final DimensionlessMatrix asin()
    {
        assign(DoubleMathFunctions.ASIN);
        return this;
    }

    @Override
    public final DimensionlessMatrix atan()
    {
        assign(DoubleMathFunctions.ATAN);
        return this;
    }

    @Override
    public final DimensionlessMatrix cbrt()
    {
        assign(DoubleMathFunctions.CBRT);
        return this;
    }

    @Override
    public final DimensionlessMatrix cos()
    {
        assign(DoubleMathFunctions.COS);
        return this;
    }

    @Override
    public final DimensionlessMatrix cosh()
    {
        assign(DoubleMathFunctions.COSH);
        return this;
    }

    @Override
    public final DimensionlessMatrix exp()
    {
        assign(DoubleMathFunctions.EXP);
        return this;
    }

    @Override
    public final DimensionlessMatrix expm1()
    {
        assign(DoubleMathFunctions.EXPM1);
        return this;
    }

    @Override
    public final DimensionlessMatrix log()
    {
        assign(DoubleMathFunctions.LOG);
        return this;
    }

    @Override
    public final DimensionlessMatrix log10()
    {
        assign(DoubleMathFunctions.LOG10);
        return this;
    }

    @Override
    public final DimensionlessMatrix log1p()
    {
        assign(DoubleMathFunctions.LOG1P);
        return this;
    }

    @Override
    public final DimensionlessMatrix pow(final double x)
    {
        assign(DoubleMathFunctions.POW((float) x));
        return this;
    }

    @Override
    public final DimensionlessMatrix signum()
    {
        assign(DoubleMathFunctions.SIGNUM);
        return this;
    }

    @Override
    public final DimensionlessMatrix sin()
    {
        assign(DoubleMathFunctions.SIN);
        return this;
    }

    @Override
    public final DimensionlessMatrix sinh()
    {
        assign(DoubleMathFunctions.SINH);
        return this;
    }

    @Override
    public final DimensionlessMatrix sqrt()
    {
        assign(DoubleMathFunctions.SQRT);
        return this;
    }

    @Override
    public final DimensionlessMatrix tan()
    {
        assign(DoubleMathFunctions.TAN);
        return this;
    }

    @Override
    public final DimensionlessMatrix tanh()
    {
        assign(DoubleMathFunctions.TANH);
        return this;
    }

    @Override
    public final DimensionlessMatrix inv()
    {
        assign(DoubleMathFunctions.INV);
        return this;
    }

}
