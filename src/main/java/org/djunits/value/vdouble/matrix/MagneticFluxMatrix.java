package org.djunits.value.vdouble.matrix;

import java.util.Collection;

import org.djunits.unit.MagneticFluxUnit;
import org.djunits.value.storage.StorageType;
import org.djunits.value.vdouble.matrix.base.DoubleMatrixRel;
import org.djunits.value.vdouble.matrix.base.DoubleSparseValue;
import org.djunits.value.vdouble.matrix.data.DoubleMatrixData;
import org.djunits.value.vdouble.scalar.MagneticFlux;
import org.djunits.value.vdouble.vector.MagneticFluxVector;
import org.djunits.value.vdouble.vector.data.DoubleVectorData;

import jakarta.annotation.Generated;

/**
 * Immutable Double MagneticFluxMatrix, a matrix of values with a MagneticFluxUnit.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
@Generated(value = "org.djunits.generator.GenerateDJUNIT", date = "2025-09-06T12:29:15.080196400Z")
public class MagneticFluxMatrix extends DoubleMatrixRel<MagneticFluxUnit, MagneticFlux, MagneticFluxVector, MagneticFluxMatrix>

{
    /** */
    private static final long serialVersionUID = 20151109L;

    /**
     * Construct a MagneticFluxMatrix from an internal data object.
     * @param data the internal data object for the matrix
     * @param displayUnit the display unit of the matrix data
     */
    public MagneticFluxMatrix(final DoubleMatrixData data, final MagneticFluxUnit displayUnit)
    {
        super(data, displayUnit);
    }

    /* CONSTRUCTORS WITH double[][] */

    /**
     * Construct a MagneticFluxMatrix from a double[][] object. The double values are expressed in the displayUnit, and will be
     * printed using the displayUnit.
     * @param data the data for the matrix, expressed in the displayUnit
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxMatrix(final double[][] data, final MagneticFluxUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, displayUnit.getScale(), storageType), displayUnit);
    }

    /**
     * Construct a MagneticFluxMatrix from a double[][] object. The double values are expressed in the displayUnit. Assume that
     * the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the unit of the values in the data array, and display unit when printing
     */
    public MagneticFluxMatrix(final double[][] data, final MagneticFluxUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a MagneticFluxMatrix from a double[][] object with SI-unit values.
     * @param data the data for the matrix, in SI units
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxMatrix(final double[][] data, final StorageType storageType)
    {
        this(data, MagneticFluxUnit.SI, storageType);
    }

    /**
     * Construct a MagneticFluxMatrix from a double[][] object with SI-unit values. Assume that the StorageType is DENSE since
     * we offer the data as an array of an array.
     * @param data the data for the matrix, in SI units
     */
    public MagneticFluxMatrix(final double[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH MagneticFlux[][] */

    /**
     * Construct a MagneticFluxMatrix from an array of an array of MagneticFlux objects. The MagneticFlux values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxMatrix(final MagneticFlux[][] data, final MagneticFluxUnit displayUnit, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, storageType), displayUnit);
    }

    /**
     * Construct a MagneticFluxMatrix from an array of an array of MagneticFlux objects. The MagneticFlux values are each
     * expressed in their own unit, but will be internally stored as SI values, all expressed in the displayUnit when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the values when printing
     */
    public MagneticFluxMatrix(final MagneticFlux[][] data, final MagneticFluxUnit displayUnit)
    {
        this(data, displayUnit, StorageType.DENSE);
    }

    /**
     * Construct a MagneticFluxMatrix from an array of an array of MagneticFlux objects. The MagneticFlux values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * since we offer the data as an array of an array.
     * @param data the data for the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxMatrix(final MagneticFlux[][] data, final StorageType storageType)
    {
        this(data, MagneticFluxUnit.SI, storageType);
    }

    /**
     * Construct a MagneticFluxMatrix from an array of an array of MagneticFlux objects. The MagneticFlux values are each
     * expressed in their own unit, but will be internally stored as SI values, and expressed using SI units when printing.
     * Assume that the StorageType is DENSE since we offer the data as an array of an array.
     * @param data the data for the matrix
     */
    public MagneticFluxMatrix(final MagneticFlux[][] data)
    {
        this(data, StorageType.DENSE);
    }

    /* CONSTRUCTORS WITH Collection<DoubleSparseValue> */

    /**
     * Construct a MagneticFluxMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxMatrix(final Collection<DoubleSparseValue<MagneticFluxUnit, MagneticFlux>> data,
            final MagneticFluxUnit displayUnit, final int rows, final int cols, final StorageType storageType)
    {
        this(DoubleMatrixData.instantiate(data, rows, cols, storageType), displayUnit);
    }

    /**
     * Construct a MagneticFluxMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Assume the
     * storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param displayUnit the display unit of the matrix data, and the unit of the data points
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public MagneticFluxMatrix(final Collection<DoubleSparseValue<MagneticFluxUnit, MagneticFlux>> data,
            final MagneticFluxUnit displayUnit, final int rows, final int cols)
    {
        this(data, displayUnit, rows, cols, StorageType.SPARSE);
    }

    /**
     * Construct a MagneticFluxMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     * @param storageType the StorageType (SPARSE or DENSE) to use for constructing the Matrix
     */
    public MagneticFluxMatrix(final Collection<DoubleSparseValue<MagneticFluxUnit, MagneticFlux>> data, final int rows,
            final int cols, final StorageType storageType)
    {
        this(data, MagneticFluxUnit.SI, rows, cols, storageType);
    }

    /**
     * Construct a MagneticFluxMatrix from a (sparse) collection of DoubleSparseValue objects. The displayUnit indicates the
     * unit in which the values in the collection are expressed, as well as the unit in which they will be printed. Use the SI
     * unit or base unit as the displayUnit. Assume the storage type is SPARSE, since we offer the data as a collection.
     * @param data the data for the matrix
     * @param rows the number of rows of the matrix
     * @param cols the number of columns of the matrix
     */
    public MagneticFluxMatrix(final Collection<DoubleSparseValue<MagneticFluxUnit, MagneticFlux>> data, final int rows,
            final int cols)
    {
        this(data, MagneticFluxUnit.SI, rows, cols, StorageType.SPARSE);
    }

    @Override
    public Class<MagneticFlux> getScalarClass()
    {
        return MagneticFlux.class;
    }

    @Override
    public Class<MagneticFluxVector> getVectorClass()
    {
        return MagneticFluxVector.class;
    }

    @Override
    public MagneticFluxMatrix instantiateMatrix(final DoubleMatrixData dmd, final MagneticFluxUnit displayUnit)
    {
        return new MagneticFluxMatrix(dmd, displayUnit);
    }

    @Override
    public MagneticFluxVector instantiateVector(final DoubleVectorData dvd, final MagneticFluxUnit displayUnit)
    {
        return new MagneticFluxVector(dvd, displayUnit);
    }

    @Override
    public MagneticFlux instantiateScalarSI(final double valueSI, final MagneticFluxUnit displayUnit)
    {
        MagneticFlux result = MagneticFlux.ofSI(valueSI);
        result.setDisplayUnit(displayUnit);
        return result;
    }

}
