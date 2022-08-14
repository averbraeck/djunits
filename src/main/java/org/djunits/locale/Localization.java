package org.djunits.locale;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Localization object for language specific reporting of units.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class Localization implements Serializable
{
    /** */
    private static final long serialVersionUID = 20200118L;

    /** filename without .properties, to be found in the /resources folder. */
    private final String bundleNamePrefix;

    /** the resource bundle. */
    private transient ResourceBundle resourceBundle;

    /** current locale. */
    private Locale currentLocale = null;

    /** the default resource bundle. */
    private transient ResourceBundle defaultResourceBundle;

    /** default locale. */
    private Locale defaultLocale = null;

    /**
     * Create a Localization object.
     * @param prefix String; the prefix of the properties files to use.
     */
    public Localization(final String prefix)
    {
        this.bundleNamePrefix = prefix;
        getString("xyz"); // initialize the default locale
    }

    /**
     * Retrieve a string from a locale bundle. If retrieval fails the value of key string, surrounded by exclamation marks is
     * returned.
     * @param key String; the key for the locale in the properties file
     * @return String; localized string, or, if a translation could not be found return the key surrounded by exclamation marks
     */
    public final String getString(final String key)
    {
        if (this.currentLocale == null || !this.currentLocale.equals(DefaultLocale.getLocale()))
        {
            if (DefaultLocale.getLocale() == null)
            {
                DefaultLocale.setLocale(new Locale("en"));
            }
            this.currentLocale = DefaultLocale.getLocale();
            Locale.setDefault(this.currentLocale);
            try
            {
                this.resourceBundle = ResourceBundle.getBundle("resources/" + this.bundleNamePrefix, this.currentLocale);
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

    /**
     * Retrieve a string from the default locale bundle. If retrieval fails the value of key string, surrounded by exclamation
     * marks is returned.
     * @param key String; the key for the locale in the properties file
     * @return String; localized string, or, if a translation could not be found return the key surrounded by exclamation marks
     */
    public final String getDefaultString(final String key)
    {
        if (this.defaultLocale == null)
        {
            this.defaultLocale = new Locale("en");
            try
            {
                this.defaultResourceBundle = ResourceBundle.getBundle("resources/" + this.bundleNamePrefix, this.defaultLocale);
            }
            catch (MissingResourceException e)
            {
                return '!' + key.substring(key.indexOf('.') + 1) + '!';
            }
        }
        if (null == this.defaultResourceBundle)
        {
            // Failed to find the resourceBundle (on a previous call to getString)
            return '!' + key.substring(key.indexOf('.') + 1) + '!';
        }
        try
        {
            return this.defaultResourceBundle.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key.substring(key.indexOf('.') + 1) + '!';
        }
    }

    /**
     * Return whether the current locale is the default (English) locale.
     * @return boolean; true if the current locale is the default; false if the current locale is not the default
     */
    public boolean isDefault()
    {
        if (this.currentLocale == null || this.defaultLocale == null || !this.currentLocale.equals(this.defaultLocale)
                || !this.currentLocale.equals(DefaultLocale.getLocale()))
        {
            return false;
        }
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "Localization [bundleNamePrefix=" + this.bundleNamePrefix + ", resourceBundle=" + this.resourceBundle
                + ", currentLocale=" + this.currentLocale + ", defaultResourceBundle=" + this.defaultResourceBundle
                + ", defaultLocale=" + this.defaultLocale + "]";
    }

}
