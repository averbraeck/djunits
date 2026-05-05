package org.djunits.vecmat.def;

import org.djunits.formatter.MatrixFormat;
import org.djunits.formatter.MatrixFormatter;
import org.djunits.quantity.def.AbsQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.quantity.def.Reference;
import org.djunits.unit.Unit;

/**
 * AbsMatrix contains a number of standard operations on matrices that contain absolute quantities.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <A> the absolute quantity type
 * @param <Q> the quantity type
 * @param <MA> the absolute matrix type
 * @param <MQ> the relative matrix type
 * @param <MAT> the type of the transposed version of the absolute matrix
 */
public abstract class AbsMatrix<A extends AbsQuantity<A, Q, ?>, Q extends Quantity<Q>, MA extends AbsMatrix<A, Q, MA, MQ, MAT>,
        MQ extends Matrix<Q, MQ, ?, ?, ?>, MAT extends AbsMatrix<A, Q, MAT, ?, MA>> extends AbsTable<A, Q, MA, MQ, MAT>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new matrix of absolute values with a reference point.
     * @param matrix the underlying relative matrix with SI values relative to the reference point
     * @param reference the reference point for the absolute values
     */
    public AbsMatrix(final MQ matrix, final Reference<?, A, Q> reference)
    {
        super(matrix, reference);
    }

    /* *********************************************************************************/
    /* ************************** STRING AND FORMATTING METHODS ************************/
    /* *********************************************************************************/

    /**
     * Concise description of this matrix.
     * @return a String with the matrix, with the unit attached.
     */
    @Override
    public String format()
    {
        return format(MatrixFormat.defaults());
    }

    /**
     * String representation of this matrix after applying the format.
     * @param format the format to apply for the matrix
     * @return a String representation of this matrix, formatted according to the given format
     */
    public String format(final MatrixFormat format)
    {
        return MatrixFormatter.format(this, format);
    }

    /**
     * String representation of this matrix, expressed in the specified unit.
     * @param targetUnit the unit into which the values of the matrix are converted for display
     * @return printable string with the matrix's values expressed in the specified unit
     */
    @Override
    public String format(final Unit<?, Q> targetUnit)
    {
        return format(MatrixFormat.defaults().setDisplayUnit(targetUnit));
    }

}
