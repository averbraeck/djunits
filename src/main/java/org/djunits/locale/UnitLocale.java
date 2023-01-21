package org.djunits.locale;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.djunits.Throw;

/**
 * Localization object for language specific reporting of units.
 * <p>
 * Copyright (c) 2013-2023 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
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
    private transient ResourceBundle fallbackResourceBundle;

    /** fallback locale. */
    private final Locale fallbackLocale;

    /**
     * Create a Localization object.
     * @param prefix String; the prefix of the properties files to use.
     */
    public UnitLocale(final String prefix)
    {
        Throw.whenNull(prefix, "prefix cannot be null");
        this.bundleNamePrefix = prefix;
        this.fallbackLocale = new Locale("en");
    }

    /**
     * Retrieve a string from a locale bundle. If retrieval fails, try the fallbackLocale. If that ails as well, return the
     * value of key string, surrounded by exclamation marks. When the DefaultLocale has changed, load a new ResourceBundle.
     * @param key String; the key for the locale in the properties file
     * @return String; localized string, or, if a translation could not be found return the key surrounded by exclamation marks
     */
    public final String getString(final String key)
    {
        if (this.currentLocale == null || !this.currentLocale.equals(DefaultLocale.getLocale()))
        {
            if (DefaultLocale.getLocale() == null)
            {
                Locale.setDefault(new Locale("en"));
            }
            this.currentLocale = DefaultLocale.getLocale();
            Locale.setDefault(this.currentLocale);
            try
            {
                this.resourceBundle = ResourceBundle.getBundle("resources/locale/" + this.bundleNamePrefix, this.currentLocale);
            }
            catch (MissingResourceException e)
            {
                return '!' + key.substring(key.indexOf('.') + 1) + '!';
            }
        }
        if (null == this.resourceBundle)
        {
            // Failed to find the resourceBundle (on a previous call to getString)
            return '!' + key.substring(key.indexOf('.') + 1) + '!';
        }
        try
        {
            return this.resourceBundle.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key.substring(key.indexOf('.') + 1) + '!';
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "Localization [bundleNamePrefix=" + this.bundleNamePrefix + ", currentLocale=" + this.currentLocale
                + ", fallbackLocale=" + this.fallbackLocale + "]";
    }

}
