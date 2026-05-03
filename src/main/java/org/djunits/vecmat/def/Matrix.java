package org.djunits.vecmat.def;

import org.djunits.formatter.MatrixFormat;
import org.djunits.formatter.MatrixFormatter;
import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.Unit;
import org.djunits.unit.si.SIUnit;
import org.djunits.util.MatrixMath;
import org.djunits.vecmat.dnxm.MatrixNxM;
import org.djunits.vecmat.storage.DenseDoubleDataSi;
import org.djunits.vecmat.storage.DenseFloatDataSi;

/**
 * Matrix contains a number of standard operations on matrices of relative quantities.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <M> the 'SELF' matrix type
 * @param <SI> the matrix type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic matrix type with generics &lt;?, ?&lt; for Hadamard operations
 * @param <MT> the type of the transposed version of the matrix
 */
public abstract class Matrix<Q extends Quantity<Q>, M extends Matrix<Q, M, SI, H, MT>,
        SI extends Matrix<SIQuantity, SI, ?, ?, ?>, H extends Matrix<?, ?, ?, ?, ?>, MT extends Matrix<Q, MT, ?, ?, M>>
        extends Table<Q, M, SI, H, MT>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new matrix with a unit.
     * @param displayUnit the display unit to use
     */
    public Matrix(final Unit<?, Q> displayUnit)
    {
        super(displayUnit);
    }

    /**
     * Multiply this matrix with a MatrixNxM, resulting in a MatrixNxM. The multiplication is a (NxM) x (MxP) matrix
     * multiplication resulting in an (NxP) matrix.
     * @param matrix the matrix to multiply with
     * @return a MatrixNxM of an SIQuantity as the result of the matrix multiplication
     * @throws IllegalArgumentException when the number of columns of this matrix does not equal the number of rows of the
     *             matrix to multiply with
     */
    public MatrixNxM<SIQuantity> multiply(final MatrixNxM<?> matrix)
    {
        checkMultiply(matrix);
        double[] result = MatrixMath.multiply(unsafeSiArray(), matrix.unsafeSiArray(), rows(), cols(), matrix.cols());
        SIUnit siUnit = getDisplayUnit().siUnit().plus(matrix.getDisplayUnit().siUnit());
        if (matrix.getDataGrid().isDouble())
        {
            return new MatrixNxM<SIQuantity>(new DenseDoubleDataSi(result, rows(), matrix.cols()), siUnit);
        }
        return new MatrixNxM<SIQuantity>(new DenseFloatDataSi(result, rows(), matrix.cols()), siUnit);
    }

    /* *********************************************************************************/
    /* ************************** STRING AND FORMATTING METHODS ************************/
    /* *********************************************************************************/

    /**
     * Concise description of this matrix.
     * @return a String with the matrix, with the unit attached.
     */
    @Override
    public String toString()
    {
        return toString(MatrixFormat.defaults());
    }

    /**
     * String representation of this matrix after applying the format.
     * @param format the format to apply for the matrix
     * @return a String representation of this matrix, formatted according to the given format
     */
    public String toString(final MatrixFormat format)
    {
        return MatrixFormatter.format(this, format);
    }

    /**
     * String representation of this matrix, expressed in the specified unit.
     * @param targetUnit the unit into which the values of the matrix are converted for display
     * @return printable string with the matrix's values expressed in the specified unit
     */
    @Override
    public String toString(final Unit<?, Q> targetUnit)
    {
        return toString(MatrixFormat.defaults().setDisplayUnit(targetUnit));
    }

}
