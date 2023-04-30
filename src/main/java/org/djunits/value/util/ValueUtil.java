package org.djunits.value.util;

import org.djunits.unit.Unit;
import org.djutils.exceptions.Throw;

/**
 * ValueUtil implements a couple of unit-related static methods.
 * <p>
 * Copyright (c) 2015-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public final class ValueUtil
{
    /**
     * This class shall never be instantiated.
     */
    private ValueUtil()
    {
        // Prevent instantiation of this class
    }

    /**
     * Convert a value in a given unit into the equivalent in the standard SI unit.
     * @param value double; the value to convert into the standard SI unit
     * @param unit Unit&lt;?&gt;; the unit of the given value
     * @return double; the value in the standard SI unit
     */
    public static double expressAsSIUnit(final double value, final Unit<?> unit)
    {
        Throw.whenNull(unit, "expressAsSIUnit: unit may not be null");
        return unit.getScale().toStandardUnit(value);
    }

    /**
     * Convert a value from the standard SI unit into a compatible unit.
     * @param siValue double; the given value in the standard SI unit
     * @param targetUnit Unit&lt;?&gt;; the unit to convert the value into
     * @return double; the value in the targetUnit
     */
    public static double expressAsUnit(final double siValue, final Unit<?> targetUnit)
    {
        Throw.whenNull(targetUnit, "expressAsUnit: targetUnit may not be null");
        return targetUnit.getScale().fromStandardUnit(siValue);
    }

}
