package org.djunits.vecmat.operations;

/**
 * VectorTransposable enforces the contract on a vector to transpose the vector (swap rows and columns).
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 * @param <RC> the resulting row/column type
 */
public interface VectorTransposable<RC>
{
    /**
     * Transpose the vector (swap rows and columns).
     * @return the transposed vector
     */
    RC transpose();
}
