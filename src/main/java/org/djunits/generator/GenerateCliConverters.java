package org.djunits.generator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

/**
 * GenerateCliConverters.java. <br>
 * <p>
 * Copyright (c) 2003-2018 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.<br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class GenerateCliConverters
{

    /**
     * @param args String[]; blank
     * @throws IOException on i/o error
     * @throws URISyntaxException on i/o error
     */
    public static void main(String[] args) throws IOException, URISyntaxException
    {
        String generationTime = Instant.now().toString();
        List<String> types = Files.readAllLines(Paths.get(URLResource.getResource("/resources/TYPES_REL.txt").toURI()));
        List<String> absRelTypes = Files.readAllLines(Paths.get(URLResource.getResource("/resources/TYPES_ABS_REL.txt").toURI()));
        for (String ar : absRelTypes)
        {
            String[] arParts = ar.split(",");
            types.add(arParts[0]);
            types.add(arParts[1]);
        }
        Collections.sort(types);

        // @formatter:off
        System.out.println("    /**\r\n" + 
            "     * Register all DJUNITS converters for a CommandLine.\n" + 
            "     * @param cmd String; the CommandLine for which the DJUNITS converters should be registered\n" + 
            "     */\n" + 
            "    @Generated(value = \"" + GenerateCliConverters.class.getName() + "\", date = \"" + generationTime + "\")\n" +
            "    public static void registerAll(final CommandLine cmd)\n" + 
            "    {"); 
        // @formatter:on
        for (String type : types)
        {
            System.out.println("        cmd.registerConverter(" + type + ".class, new " + type.toUpperCase() + "());");
        }
        System.out.println("    }\n");

        for (String type : types)
        {
            String an = type.startsWith("A") || type.startsWith("E") || type.startsWith("I") || type.startsWith("O")
                    || type.startsWith("U") ? "an" : "a";
            String spaced = "";
            if (type.toLowerCase().equals("anglesolid"))
            {
                spaced = "Solid Angle";
            }
            else
            {
                boolean first = true;
                for (char c : type.toCharArray())
                {
                    spaced += (Character.isUpperCase(c) && !first) ? " " + c : c;
                    first = false;
                }
            }
            String an2 = spaced.startsWith("A") || spaced.startsWith("E") || spaced.startsWith("I") || spaced.startsWith("O")
                    || spaced.startsWith("U") ? "an" : "a";
            // @formatter:off
            System.out.println("    /**\n" + 
                    "     * Convert " + an2 + " " + spaced.toLowerCase() + " String with unit on the command line to " + an + " " + type + " scalar.\n" + 
                    "     */\n" + 
                    "    public static class " + type.toUpperCase() + " implements ITypeConverter<" + type + ">\n" + 
                    "    {\n" + 
                    "        /** {@inheritDoc} */\n" + 
                    "        @Override\n" + 
                    "        @Generated(value = \"" + GenerateCliConverters.class.getName() + "\", date = \"" + generationTime + "\")\n" +
                    "        public " + type + " convert(final String value) throws Exception\n" + 
                    "        {\n" + 
                    "            return " + type + ".valueOf(value);\n" + 
                    "        }\n" + 
                    "    }\n");
            // @formatter:on
        }
    }
}
