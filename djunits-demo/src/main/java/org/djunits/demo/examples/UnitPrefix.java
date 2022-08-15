package org.djunits.demo.examples;

import java.util.Map;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.VolumeUnit;

/**
 * UnitPrefix.java.
 * <p>
 * Copyright (c) 2019-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * <p>
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public final class UnitPrefix
{
    /** */
    private UnitPrefix()
    {
        // utility class
    }

    /**
     * @param args String[]; not used
     */
    public static void main(final String[] args)
    {
        print(LengthUnit.BASE.getUnitsByAbbreviation());
        print(AreaUnit.BASE.getUnitsByAbbreviation());
        print(VolumeUnit.BASE.getUnitsByAbbreviation());
        print(ElectricalChargeUnit.BASE.getUnitsByAbbreviation());
        print(MassUnit.BASE.getUnitsByAbbreviation());
        print(LinearDensityUnit.BASE.getUnitsByAbbreviation());
        print(FrequencyUnit.BASE.getUnitsByAbbreviation());
    }

    /**
     * @param unitMap Map&lt;String,? extends Unit&lt;?&gt;&gt;; the map to print
     */
    private static void print(final Map<String, ? extends Unit<?>> unitMap)
    {
        System.out.println();
        for (String ab : unitMap.keySet())
        {
            Unit<?> unit = unitMap.get(ab);
            System.out.println((ab + "        ").substring(0, 8) + (unit.getId() + "        ").substring(0, 8) + "  "
                    + (unit.getName() + "                        ").substring(0, 24) + "   "
                    + unit.getScale().toStandardUnit(1.0) + "   " + unit.getAbbreviations());
        }
    }
}
