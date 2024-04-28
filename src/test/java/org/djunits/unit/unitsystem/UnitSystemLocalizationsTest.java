package org.djunits.unit.unitsystem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.djunits.AvailableLocalizations;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * Copyright (c) 2013-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class UnitSystemLocalizationsTest
{
    /**
     * Check that all UnitSystems have valid a nameKey and a valid abbreviationKey and test those keys in all available
     * localizations.
     * @throws URISyntaxException on error
     */
    @Test
    public final void checkDefinedUnitSystems() throws URISyntaxException
    {
        List<UnitSystem> unitSystems = new ArrayList<UnitSystem>();
        Field[] fields = UnitSystem.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            // System.out.println("Field[" + i + "]: " + fields[i]);
            try
            {
                UnitSystem us = (UnitSystem) fields[i].get(null);
                // System.out.println("Prints like " + us);
                // System.out.println("nameKey: " + us.getNameKey());
                unitSystems.add(us);
            }
            catch (Exception e)
            {
                // That was not a UnitSystem
            }
        }
        ArrayList<String> errors = new ArrayList<String>();
        for (String localeName : AvailableLocalizations.availableLocalizations("localeunitsystem",
                new File(getClass().getResource("/resources").toURI().getPath()).getAbsolutePath()))
        {
            for (UnitSystem us : unitSystems)
            {
                String nameKey = us.getNameKey();
                assertTrue(null != nameKey, "nameKey is non null");
                assertTrue(nameKey.length() > 0, "Name key must be non-empty");
                String abbreviationKey = us.getAbbreviationKey();
                assertTrue(null != abbreviationKey, "abbreviationKey is non null");
                assertTrue(abbreviationKey.length() > 0, "Abbreviation key must be non-empty");
                String name = us.getName();
                String abbreviation = us.getAbbreviation();
                if (abbreviation.startsWith("!") && abbreviation.endsWith("!"))
                {
                    errors.add(String.format("Missing translation for abbreviation %s to %s\n", abbreviationKey, localeName));
                }
                if (name.startsWith("!") && name.endsWith("!"))
                {
                    errors.add(String.format("Missing translation for name %s to %s\n", nameKey, localeName));
                }
            }
        }
        for (String s : errors)
        {
            // system.out.println(s);
        }
        assertTrue(errors.isEmpty(), "Errors in UnitSystemLocalizations: " + errors);
    }

}
