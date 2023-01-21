package org.djunits;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SerializableTest.java. <br>
 * <p>
 * Copyright (c) 2003-2023 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.<br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class SerializableTest
{
    /**
     * Test whether non-utility classes are serializable.
     * @throws URISyntaxException on I/O error
     * @throws IOException on I/O error
     * @throws ClassNotFoundException when class could not be resolved
     */
    // FIXME: @Test
    public void serializableTest() throws URISyntaxException, IOException, ClassNotFoundException
    {
        File classFolder = new File(SerializableTest.class.getResource("/").toURI());
        File projectFolder = classFolder.getParentFile().getParentFile();
        if (projectFolder.isDirectory() && projectFolder.getName().equals("djunits")
                && new File(projectFolder, "src/main/java").exists())
        {
            File sourcePathFile = new File(projectFolder, "src/main/java");
            for (File srcFolder : sourcePathFile.listFiles())
            {
                processDirOrFile(srcFolder);
            }
        }
    }

    /**
     * @param srcFolder File; folder to look for subfolders and/or java files
     * @throws IOException on i/o error
     * @throws ClassNotFoundException on error
     */
    private void processDirOrFile(final File srcFolder) throws IOException, ClassNotFoundException
    {
        if (srcFolder.isDirectory())
        {
            for (File subFile : srcFolder.listFiles())
            {
                if (subFile.isDirectory())
                {
                    processDirOrFile(subFile);
                }
                else if (subFile.getName().endsWith(".java") && !subFile.getName().startsWith("package-info"))
                {
                    processJavaFile(subFile);
                }
            }
        }
    }

    /**
     * @param javaFile File; java file to process
     * @throws IOException on error
     * @throws ClassNotFoundException on error
     */
    private void processJavaFile(final File javaFile) throws IOException, ClassNotFoundException
    {
        // system.out.println("Test Serializable in " + javaFile.toURI().getPath());
        List<String> lines = Files.readAllLines(Paths.get(javaFile.toURI()), StandardCharsets.UTF_8);

        // find the package
        String pack = null;
        for (String line : lines)
        {
            if (line.trim().startsWith("package "))
            {
                pack = line.trim().substring(8, line.trim().length() - 1);
                break;
            }
        }
        if (pack == null)
        {
            fail("Could not find package in Java file " + javaFile.toURI().getPath());
        }

        // find the class (TODO: how to deal with subclasses)
        String className = pack + "." + javaFile.getName();
        className = className.replaceAll("\\.java", "");
        Class<?> clazz = Class.forName(className);

        if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()))
        {
            // is there a public constructor? (non-utility class)
            List<Constructor<?>> constructors = new ArrayList<>();
            constructors.addAll(Arrays.asList(clazz.getDeclaredConstructors()));
            boolean utilityClass = true;
            for (Constructor<?> constructor : constructors)
            {
                if (Modifier.isPublic(constructor.getModifiers()) || Modifier.isProtected(constructor.getModifiers()))
                {
                    utilityClass = false;
                    break;
                }
            }

            if (!utilityClass)
            {
                // system.out.println(" -- Testing class " + className);

                // is the class Serializable?
                if (!Serializable.class.isAssignableFrom(clazz))
                {
                    fail("NOT SERIALIZABLE: class " + className);
                }
            }
        }
    }
}
