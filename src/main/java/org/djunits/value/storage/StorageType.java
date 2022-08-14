package org.djunits.value.storage;

/**
 * Possible ways to store vectors and matrices, e.g. DENSE or SPARSE.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public enum StorageType
{
    /**
     * Dense storage. Use Dense to store small quantities of values, or values where zero is not common, or larger access times
     * are unacceptable, or memory availability is high.
     */
    DENSE,

    /**
     * Sparse storage. Use Sparse to store large quantities of values when most are zero and larger access times are acceptable
     * and memory availability is low. Changing a zero value to a non-zero value is CPU-time-expensive
     */
    SPARSE;

}
