package org.djunits.vecmat;

/**
 * VectorTransposable enforces the contract on a vector to transpose the vector (swap rows and columns).<br>
 * <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
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
