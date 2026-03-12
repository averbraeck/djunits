package org.djunits.vecmat;

import org.djunits.quantity.SIQuantity;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitInterface;
import org.djunits.unit.si.SIUnit;

/**
 * Matrix contains a number of standard operations on matrices of relative quantities.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <Q> the quantity type
 * @param <U> the unit type
 * @param <M> the 'SELF' matrix type
 * @param <SI> the matrix type with generics &lt;SIQuantity, SIUnit&lt;
 * @param <H> the generic matrix type with generics &lt;?, ?&lt; for Hadamard operations
  */
public abstract class Matrix<Q extends Quantity<Q, U>, U extends UnitInterface<U, Q>, M extends Matrix<Q, U, M, SI, H>,
        SI extends Matrix<SIQuantity, SIUnit, SI, ?, ?>, H extends Matrix<?, ?, ?, ?, ?>> extends VectorMatrix<Q, U, M, SI, H>
{
    /** */
    private static final long serialVersionUID = 600L;

    /**
     * Create a new matrix with a unit.
     * @param displayUnit the display unit to use
     */
    public Matrix(final U displayUnit)
    {
        super(displayUnit);
    }

    // TODO: mat x mat
}
