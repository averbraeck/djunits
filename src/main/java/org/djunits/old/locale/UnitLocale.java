package org.djunits.old.locale;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.djutils.exceptions.Throw;

/**
 * Localization object for language specific reporting of units.
 * <p>
 * Copyright (c) 2013-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author Alexander Verbraeck
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class UnitLocale implements Serializable
{
    /** */
    private static final long serialVersionUID = 20200118L;

    /** filename without .properties, to be found in the /resources folder. */
    private final String bundleNamePrefix;

    /** the resource bundle. */
    private transient ResourceBundle resourceBundle;

    /** current locale. */
    private Locale currentLocale = null;

    /** the fallback resource bundle. */
    private final transient ResourceBundle fallbackResourceBundle;

    /** fallback locale. */
    private final Locale fallbackLocale;

    /**
     * Create a localization object for units and unit systems. The prefix is right now either "unit" or "unitsystem".
     * @param prefix the prefix of the properties files to use.
     */
    public UnitLocale(final String prefix)
    {
        Throw.whenNull(prefix, "prefix cannot be null");
        this.bundleNamePrefix = prefix;
        this.fallbackLocale = new Locale("en");
        this.fallbackResourceBundle = ResourceBundle.getBundle("resources/locale/" + prefix, this.fallbackLocale);
    }

    /**
     * Reload the resource bundle if necessary.
     */
    public void checkReload()
    {
        if (this.currentLocale == null || !this.currentLocale.equals(Locale.getDefault(Locale.Category.DISPLAY)))
        {
            this.currentLocale = Locale.getDefault(Locale.Category.DISPLAY);
            try
            {
                this.resourceBundle = ResourceBundle.getBundle("resources/locale/" + this.bundleNamePrefix, this.currentLocale);
            }
            catch (MissingResourceException e)
            {
                this.resourceBundle = null;
            }
        }
    }

    /**
     * Retrieve a string from a resource bundle. If retrieval fails, try the fallbackLocale. If that fails as well, return the
     * value of key string, surrounded by exclamation marks. When the DefaultLocale has changed, load a new ResourceBundle.
     * @param key the key for the locale in the currently valid resource bundle
     * @return localized string, or, if the key could not be found, the key surrounded by exclamation marks
     */
    public final String getString(final String key)
    {
        checkReload();
        if (null == this.resourceBundle)
        {
            // Failed to find the resourceBundle (on a previous call to getString)
            return getFallbackString(key);
        }
        try
        {
            return this.resourceBundle.getString(key);
        }
        catch (MissingResourceException e)
        {
            return getFallbackString(key);
        }
    }

    /**
     * Retrieve a string from the falback bundle. If retrieval fails, return the value of key string, surrounded by exclamation
     * marks.
     * @param key the key for the fallback locale to look up in the resource bundle
     * @return localized string, or, if the key could not be found, the key surrounded by exclamation marks
     */
    public final String getFallbackString(final String key)
    {
        try
        {
            return this.fallbackResourceBundle.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key.substring(key.indexOf('.') + 1) + '!';
        }
    }

    @Override
    public String toString()
    {
        return "Localization [bundleNamePrefix=" + this.bundleNamePrefix + ", currentLocale=" + this.currentLocale
                + ", fallbackLocale=" + this.fallbackLocale + "]";
    }

}
