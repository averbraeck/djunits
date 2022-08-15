package org.djunits.generator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

/**
 * The URLResource class helps to resolve a file location in a project, JAR, or folder. The static methods return a URL of the
 * file location that was found, or null in case it was not found. This class originates from the DSOL simulation project.
 * <p>
 * Copyright (c) 2002-2022 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.<br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class URLResource
{
    /**
     * constructs a new URLResource.
     */
    private URLResource()
    {
        // unreachable code
    }

    /**
     * Resolves a resource for name.
     * @param name String; the name to search for
     * @return the resolved URL
     */
    public static URL getResource(final String name)
    {
        try
        {
            File file = new File(name);

            if (name.startsWith("/"))
            {
                URL url = URLResource.class.getResource(name);
                if (url != null)
                {
                    return url;
                }
                url = Thread.currentThread().getContextClassLoader().getResource(name.substring(1));
                if (url != null)
                {
                    return url;
                }
                if (file.exists())
                {
                    return new URL("file:" + name);
                }
            }
            else if (name.startsWith("\\") || name.contains("\\")) // added the second part
            {
                if (file.exists())
                {
                    return new URL("file:" + name);
                }
            }
            else if (file.exists())
            {
                return new URL("file:" + name);
            }
            else
            {
                if (name.indexOf("@") == -1)
                {
                    return new URL(name);
                }
                // we need authentication
                String temp = name.substring(name.indexOf("//") + 2);
                String userName = temp.substring(0, temp.indexOf(":"));
                String password = temp.substring(temp.indexOf(":") + 1);
                password = password.substring(0, password.indexOf("@"));
                String url = name.substring(0, name.indexOf("//") + 2);
                url = url + name.substring(name.indexOf("@") + 1);
                Authenticator.setDefault(new PasswordAuthenticator(userName, password));
                return new URL(url);
            }
        }
        catch (Exception exception)
        {
            exception = null;
            // We neglect exceptions since we return null
        }
        return null;
    }

    /**
     * Resolves a resource for name. For relative names, base is used to resolve to an absolute name. If name is absolute, base
     * is ignored.
     * @param name String; the name to search for
     * @param base String; the base for relative paths
     * @return the resolved URL
     */
    public static URL getResource(final String name, final String base)
    {
        URL url = null;

        // case complete URL
        try
        {
            url = new URL(name);
        }
        catch (MalformedURLException ex)
        {
            // neglect exception -- just trying
        }

        // absolute or relative case
        if (url == null)
        {
            String completeName = name;
            if (!name.startsWith(File.separator) && !name.startsWith("/") && base != null)
            {
                String baseDir = "";
                int i = base.lastIndexOf(File.separator);
                if (i == -1 && !File.separator.equals("/"))
                {
                    i = base.lastIndexOf("/");
                }
                if (i != -1)
                {
                    baseDir = base.substring(0, i + 1);
                }
                completeName = baseDir + name;
            }

            // case base = URL
            try
            {
                url = new URL(completeName);
                if (url.getProtocol().equalsIgnoreCase("file"))
                {
                    File file = new File(url.getPath());
                    if (!file.exists())
                    {
                        url = null;
                    }
                }
            }
            catch (MalformedURLException ex)
            {
                url = getResourceOrFile(completeName);
            }

            // just try plain name if that's still another option
            if (url == null && !name.equalsIgnoreCase(completeName))
            {
                url = getResourceOrFile(name);
            }

        }

        // handle authentication
        if (url != null && url.getUserInfo() != null)
        {
            String ui = url.getUserInfo();
            String userName = ui.substring(0, ui.indexOf(":"));
            String password = ui.substring(ui.indexOf(":") + 1);
            Authenticator.setDefault(new PasswordAuthenticator(userName, password));
        }

        return url;
    }

    /**
     * Resolves a resource for a path.
     * @param path String; the path to search for
     * @return the resolved URL to the path
     */
    private static URL getResourceOrFile(String path)
    {
        URL url = null;

        // resource
        if (url == null)
        {
            url = URLResource.class.getResource(path);
        }

        // thread context resource
        if (url == null)
        {
            url = Thread.currentThread().getContextClassLoader().getResource(path.substring(1));
        }

        // file
        if (url == null)
        {
            File file = new File(path);
            if (file.exists())
            {
                try
                {
                    url = new URL("file:" + file.getCanonicalPath());
                }
                catch (IOException ex)
                {
                    // ignore -- if not found, we return null
                }
            }
        }

        return url;
    }

    /**
     * returns the resource as stream.
     * @param name String; the name of the resource
     * @return the inputStream
     */
    public static InputStream getResourceAsStream(final String name)
    {
        try
        {
            URL url = URLResource.getResource(name);
            if (url == null)
            {
                return null;
            }
            return url.openStream();
        }
        catch (Exception exception)
        {
            return null;
        }
    }

    /**
     * A Private password authenticator.
     */
    private static class PasswordAuthenticator extends Authenticator
    {
        /** my user name. */
        private String userName = null;

        /** my password. */
        private String password = null;

        /**
         * constructs a new PasswordAuthenticator.
         * @param userName String; my userName
         * @param password String; my passWord
         */
        public PasswordAuthenticator(final String userName, final String password)
        {
            this.userName = userName;
            this.password = password;
        }

        /** {@inheritDoc} */
        @Override
        protected PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(this.userName, this.password.toCharArray());
        }
    }

}
