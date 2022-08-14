package org.djunits.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * GenerateXSD makes an XSD file for all choices possible with the units, using the textual representations. <br>
 * <p>
 * Copyright (c) 2003-2018 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved.<br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class GenerateXSD
{
    /** The output folder of the writer -- will be written in Eclipse project-root/generated-code/org/djunits folder. */
    private static final String generatedCodeRelativePath = "/generated-code/";

    /**
     * The calculated absolute output path (root of the executable or Jar file). In case of an Eclipse run, ../../ is added to
     * the path to place the results in the root of the project, rather than in target/classes.
     */
    private static String absoluteRootPath;

    /**
     * @param args String[]; not used
     * @throws FileNotFoundException in case we cannot find the djunits project
     */
    public static void main(final String[] args) throws FileNotFoundException
    {
        makeAbsolutePath();
        makeXsd();
        // TODO: makeAdapters();
    }

    /** */
    private static void makeXsd()
    {
        try
        {
            File in = new File(absoluteRootPath + "../../djunits/src/main/resources/localeunit.properties");
            if (!in.exists())
            {
                throw new RuntimeException("cannot find file " + in.getPath());
            }
            BufferedReader br = new BufferedReader(new FileReader(in));

            File outPath = new File(absoluteRootPath + "/resources");
            outPath.mkdirs();
            PrintWriter pw = new PrintWriter(absoluteRootPath + "/resources/djunits.xsd");
            pw.write(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <xsd:schema targetNamespace=\"http://www.djunits.org/djunits\"\n");
            pw.write("  xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n");
            pw.write("  xmlns:djunits=\"http://www.djunits.org/djunits\"\n");
            pw.write("  xmlns=\"http://www.djunits.org/djunits\" elementFormDefault=\"qualified\">\n");
            pw.write("\n");
            pw.write("  <!-- ========================================================================================= -->\n");
            pw.write("  <!-- ==================================== DJUNITS UNIT TYPES ================================= -->\n");
            pw.write("  <!-- ========================================================================================= -->\n");
            pw.write("\n");

            String typeStr = "";
            String line = br.readLine();
            while (line != null)
            {
                if (line.startsWith("#"))
                {
                    line = br.readLine();
                    continue;
                }

                if (line.contains("="))
                {
                    typeStr = writeType(pw, line, typeStr);
                }
                line = br.readLine();
            }

            if (typeStr.length() > 0)
            {
                pw.write(")\"></xsd:pattern>\n" + "    </xsd:restriction>\n" + "  </xsd:simpleType>\n\n");
            }

            pw.write("  <!-- ========================================================================================= -->\n");
            pw.write("  <!-- ================================== DJUNITS SCALAR TYPES ================================= -->\n");
            pw.write("  <!-- ========================================================================================= -->\n");
            pw.write("\n");

            br.close();
            br = new BufferedReader(new FileReader(in));
            typeStr = "";
            line = br.readLine();
            while (line != null)
            {
                if (line.startsWith("#"))
                {
                    line = br.readLine();
                    continue;
                }

                if (line.contains("="))
                {
                    typeStr = writeScalar(pw, line, typeStr, false);
                }
                line = br.readLine();
            }

            if (typeStr.length() > 0)
            {
                pw.write(")\"></xsd:pattern>\n" + "    </xsd:restriction>\n" + "  </xsd:simpleType>\n\n");
            }

            pw.write("  <!-- ========================================================================================= -->\n");
            pw.write("  <!-- ============================= DJUNITS POSITIVE SCALAR TYPES ============================= -->\n");
            pw.write("  <!-- ========================================================================================= -->\n");
            pw.write("\n");

            br.close();
            br = new BufferedReader(new FileReader(in));
            typeStr = "";
            line = br.readLine();
            while (line != null)
            {
                if (line.startsWith("#"))
                {
                    line = br.readLine();
                    continue;
                }

                if (line.contains("="))
                {
                    typeStr = writeScalar(pw, line, typeStr, true);
                }
                line = br.readLine();
            }

            if (typeStr.length() > 0)
            {
                pw.write(")\"></xsd:pattern>\n" + "    </xsd:restriction>\n" + "  </xsd:simpleType>\n\n" + "</xsd:schema>\n");
            }
            pw.close();
            br.close();

            System.out.println("built: " + absoluteRootPath + "/resources/djunits.xsd");

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /** indicate first unit, so no |. */
    static boolean first;
     
    /**
     * Write a scalar with unit to the file, with or without [+-]?
     * @param pw PrintWriter; the file to write to
     * @param typeStrOrig String; the previous the unit we were looking at
     * @param line String; the line from the preoperties file
     * @param plus boolean; with or without [+-]?
     * @return the unit we are looking at
     */
    private static String writeScalar(final PrintWriter pw, final String line, final String typeStrOrig, final boolean plus)
    {
        /*-
        <xsd:simpleType name="SPEEDTYPE"> 
          <xsd:restriction base="xsd:string">
            <xsd:pattern value="\d+\.?\d*\s*(km/h|m/s|mi/h|ft/s)"></xsd:pattern>
          </xsd:restriction>
        </xsd:simpleType>
        */

        if (line.startsWith("Dimension"))
        {
            return typeStrOrig;
        }

        String typeStr = typeStrOrig;
        String key = line.split("=")[0].trim().split("\\.")[0];
        String val = line.split("=")[1].trim();
        String[] vals = val.split("\\|");
        if (!typeStr.equals(key))
        {
            // new type
            if (typeStr.length() > 0)
            {
                if (typeStr.startsWith("Dimension"))
                    pw.write("\"></xsd:pattern>\n" + "    </xsd:restriction>\n" + "  </xsd:simpleType>\n\n");
                else
                    pw.write(")\"></xsd:pattern>\n" + "    </xsd:restriction>\n" + "  </xsd:simpleType>\n\n");
            }
            typeStr = key;
            String type = key.toUpperCase() + "TYPE";
            if (plus)
            {
                type = "POSITIVE" + type;
            }
            pw.write("  <xsd:simpleType name=\"" + type + "\">\n");
            pw.write("     <xsd:restriction base=\"xsd:string\">\n");
            String plusmin = plus ? "" : "[+-]?";
            if (type.contains("DIMENSIONLESS"))
                pw.write("      <xsd:pattern value=\"" + plusmin + "[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?\\s*");
            else
                pw.write("      <xsd:pattern value=\"" + plusmin + "[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?\\s*(");
            first = true;
        }
        else
        {
            if (!first)
                pw.write("|");
            first = false;
            pw.write(escape(vals[0]));
            for (int i = 2; i < vals.length; i++)
                pw.write("|" + escape(vals[i]));
        }
        System.out.println(vals[0]);
        return typeStr;
    }

    /**
     * Write a unit type to the file.
     * @param pw PrintWriter; the file to write to
     * @param typeStrOrig String; the previous the unit we were looking at
     * @param line String; the line from the preoperties file
     * @return the unit we are looking at
     */
    private static String writeType(final PrintWriter pw, final String line, final String typeStrOrig)
    {
        /*-
          <xsd:simpleType name="SPEEDUNITTYPE">
            <xsd:restriction base="xsd:string">
              <xsd:pattern value="(m/s|km/h|mi/h|ft/s|kt|m/h|km/s|mi/min|in/min|ft/min|ft/h|mi/s|in/h|in/s)"></xsd:pattern>
            </xsd:restriction>
          </xsd:simpleType>
        */

        if (line.startsWith("Dimension"))
        {
            return typeStrOrig;
        }

        String typeStr = typeStrOrig;
        String key = line.split("=")[0].trim().split("\\.")[0];
        String val = line.split("=")[1].trim();
        String[] vals = val.split("\\|");
        if (!typeStr.equals(key))
        {
            // new type
            if (typeStr.length() > 0)
            {
                pw.write(")\"></xsd:pattern>\n" + "    </xsd:restriction>\n" + "  </xsd:simpleType>\n\n");
            }
            typeStr = key;
            String type = key.toUpperCase() + "UNITTYPE";
            pw.write("  <xsd:simpleType name=\"" + type + "\">\n");
            pw.write("     <xsd:restriction base=\"xsd:string\">\n");
            pw.write("      <xsd:pattern value=\"(");
            first = true;
        }
        else
        {
            if (!first)
                pw.write("|");
            first = false;
            pw.write(escape(vals[0]));
            for (int i = 2; i < vals.length; i++)
                pw.write("|" + escape(vals[i]));
        }
        System.out.println(vals[0]);
        return typeStr;
    }

    /** the characters to escape. */
    private static final String escape = "(){}.?|<>*-+'\\%^";

    /**
     * @param s String; the String to escape
     * @return the String with \ before escaped chars
     */
    private static String escape(final String s)
    {
        String out = "";
        for (char c : s.toCharArray())
        {
            if (escape.indexOf(c) >= 0)
                out += "\\";
            if (c == '"')
                out += "&quot;";
            else
                out += c;
        }
        return out.trim();
    }

    /**
     * Determine calculated absolute output path (root of the executable or Jar file). In case of an Eclipse run, ../../ is
     * added to the path to place the results in the root of the project, rather than in target/classes.<br>
     * See https://weblogs.java.net/blog/kohsuke/archive/2007/04/how_to_convert.html and
     * http://stackoverflow.com/questions/320542/how-to-get-the-path-of-a-running-jar-file and
     * http://stackoverflow.com/questions/3153337/get-current-working-directory-in-java
     * @throws FileNotFoundException in case file could not be found
     */
    private static void makeAbsolutePath() throws FileNotFoundException
    {
        URL mainURL = URLResource.getResource("/");
        String path;
        try
        {
            path = mainURL.toURI().getPath();
        }
        catch (URISyntaxException exception)
        {
            path = mainURL.getPath();
        }
        if (path.endsWith("/target/classes/"))
        {
            path = path.substring(0, path.length() - "/target/classes/".length());
        }
        path += generatedCodeRelativePath;
        if (!new File(path).exists())
        {
            new File(path).mkdirs();
        }
        absoluteRootPath = path;
        System.out.println("writing into: " + path);
    }
}
