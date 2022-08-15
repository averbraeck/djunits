package org.djunits.unit.scale;

/**
 * A Scale for standard, e.g. SI, units. Values in these units need no conversion.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class IdentityScale extends LinearScale
{
    /** */
    private static final long serialVersionUID = 20151011L;

    /** A ready-to-use instance of the standard scale (they are all the same...). */
    public static final IdentityScale SCALE = new IdentityScale();

    /**
     * Construct a standard Scale without a transformation.
     */
    private IdentityScale()
    {
        super(1.0);
    }

    /** {@inheritDoc} */
    @Override
    public double toStandardUnit(final double value)
    {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public double fromStandardUnit(final double value)
    {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "IdentityScale []";
    }

}
