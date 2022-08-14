package org.djunits.unit;

import org.djunits.unit.si.SIDimensions;
import org.djunits.unit.util.UnitException;

/**
 * SIUnit describes a unit with arbitrary SI dimensions for which no predefined unit exists.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class SIUnit extends Unit<SIUnit>
{
    /** */
    private static final long serialVersionUID = 20190829L;

    static
    {
        // make sure all predefined unit types get registered before we start using SIScalars.
        try
        {
            Class.forName("org.djunits.unit.util.UNITS");
        }
        catch (ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * Instantiate an SI unit 'of' a String.
     * @param siString String; the SI string, e.g., "kgm/s2"
     * @return the SIUnit based on the SI dimensionality
     * @throws UnitException when the SI string is not according to the rules
     */
    public static SIUnit of(final String siString) throws UnitException
    {
        return Unit.lookupOrCreateUnitWithSIDimensions(SIDimensions.of(siString));
    }

    /**
     * Instantiate an SI unit 'of' a SIDimensions.
     * @param siDimensions SIDimensions; the SI dimensions
     * @return the SIUnit based on the SI dimensionality
     * @throws UnitException when the SI string is not according to the rules
     */
    public static SIUnit of(final SIDimensions siDimensions) throws UnitException
    {
        return Unit.lookupOrCreateUnitWithSIDimensions(siDimensions);
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return getQuantity().getSiDimensions().toString(true, false);
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public int hashCode()
    {
        return getQuantity().getSiDimensions().hashCode();
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings({"checkstyle:designforextension", "checkstyle:needbraces"})
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SIUnit other = (SIUnit) obj;
        if (!getQuantity().getSiDimensions().equals(other.getQuantity().getSiDimensions()))
            return false;
        return true;
    }

}
