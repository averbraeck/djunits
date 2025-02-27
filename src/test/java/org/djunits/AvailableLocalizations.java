package org.djunits;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public final class AvailableLocalizations
{
    /**
     * This class should never be instantiated.
     */
    private AvailableLocalizations()
    {
        // no instantiation
    }

    /**
     * Build a list of locale names that are available for a given prefix.
     * @param prefix the prefix of the localization file names
     * @param path the path to the resource directory to scan
     * @return the list of matching locale names (which <b>always</b> starts with "en", even though there
     *         may not be such a localization file)
     */
    public static List<String> availableLocalizations(final String prefix, final String path)
    {
        final String tail = ".properties";
        File[] propertiesFiles = new File(path).listFiles(new FileFilter()
        {
            @Override
            public boolean accept(final File pathname)
            {
                String name = pathname.getName();
                return name.endsWith(tail) && name.startsWith(prefix + "_");
            }
        });
        List<String> locales = new ArrayList<String>();
        locales.add("en");
        for (File f : propertiesFiles)
        {
            String localeName = f.getName().substring(prefix.length() + 1);
            localeName = localeName.substring(0, 2);
            locales.add(localeName);
        }
        return locales;
    }

}
