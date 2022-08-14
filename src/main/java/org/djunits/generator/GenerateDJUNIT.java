package org.djunits.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>.
 * </p>
 * @author <a href="https://www.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://www.tudelft.nl/staff/p.knoppers/">Peter Knoppers</a>
 */
public class GenerateDJUNIT
{
    /** The output folder of the writer -- will be written in Eclipse project-root/generated-code/org/djunits folder. */
    private static final String generatedCodeRelativePath = "/generated-code/org/djunits/";

    /** the generation time. */
    private static String generationTime;

    /**
     * The calculated absolute output path (root of the executable or Jar file). In case of an Eclipse run, ../../ is added to
     * the path to place the results in the root of the project, rather than in target/classes.
     */
    private static String absoluteRootPath;

    /** List of Abs + Rel types. */
    private static List<String[]> typesAbsRel = new ArrayList<>();

    /** List of Rel types. */
    private static List<String> typesRel = new ArrayList<>();

    /** Map of types to formulas. */
    private static Map<String, List<String>> formulas = new HashMap<>();

    /** Map of replacement tags to replacement string. */
    private static Map<String, String> replaceMap = new HashMap<>();

    /** Map of replacement tags to type for which the replacement has to be done. */
    private static Map<String, String> replaceType = new HashMap<>();

    /**
     * Read the types from the file /TYPES_ABS_REL.txt.
     * @throws IOException on I/O error
     */
    private static void readAbsRelTypes() throws IOException
    {
        URL typesURL = URLResource.getResource("/resources/TYPES_ABS_REL.txt");
        FileReader fileReader = new FileReader(new File(typesURL.getPath()));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null)
        {
            if (line.length() > 0)
            {
                typesAbsRel.add(line.split(","));
            }
        }
        bufferedReader.close();
    }

    /**
     * Read the types from the file /TYPES_REL.txt.
     * @throws IOException on I/O error
     */
    private static void readRelTypes() throws IOException
    {
        URL typesURL = URLResource.getResource("/resources/TYPES_REL.txt");
        FileReader fileReader = new FileReader(new File(typesURL.getPath()));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null)
        {
            if (line.length() > 0)
            {
                typesRel.add(line);
            }
        }
        bufferedReader.close();
    }

    /**
     * Read the formulas from the file /FORMULAS.txt.
     * @throws IOException on I/O error
     */
    private static void readFormulas() throws IOException
    {
        URL typesURL = URLResource.getResource("/resources/FORMULAS.txt");
        FileReader fileReader = new FileReader(new File(typesURL.getPath()));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String type = null;
        String line = null;
        List<String> flist = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null)
        {
            if (line.startsWith("%"))
            {
                if (type != null)
                {
                    formulas.put(type, flist);
                }
                type = line.replaceAll("%", "").trim();
                flist = new ArrayList<>();
            }
            else
            {
                if (line.trim().length() > 0)
                {
                    flist.add(line.trim());
                }
            }
        }
        formulas.put(type, flist);
        bufferedReader.close();
    }

    /**
     * Read the replacement strings from the file /REPLACE.txt.
     * @throws IOException on I/O error
     */
    private static void readReplace() throws IOException
    {
        URL typesURL = URLResource.getResource("/resources/REPLACE.txt");
        FileReader fileReader = new FileReader(new File(typesURL.getPath()));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String tag = null;
        String line = null;
        String type = null;
        String replacementLines = "";
        while ((line = bufferedReader.readLine()) != null)
        {
            if (line.startsWith("##"))
            {
                if (tag != null)
                {
                    replaceMap.put(tag, replacementLines);
                    replaceType.put(tag, type);
                    tag = null;
                }
                else
                {
                    tag = line.trim();
                    type = bufferedReader.readLine();
                    replacementLines = "";
                }
            }
            else
            {
                replacementLines += line + "\n";
            }
        }
        bufferedReader.close();
    }

    /****************************************************************************************************************/
    /********************************************* SCALAR ***********************************************************/
    /****************************************************************************************************************/

    /**
     * Insert formulas based on FORMULAS.txt into the %FORMULAS% marker within the Java file.
     * @param java String; the java file
     * @param errorType String; the type for error messaging
     * @param prefix String; e.g., Float for Float types, or blank for Double types
     * @param absolute boolean; to indicate it is an absolute type
     * @return the file with replacements
     */
    private static String formulas(final String java, final String errorType, final String prefix, final boolean absolute)
    {
        String ret = java;
        while (ret.contains("%FORMULAS%"))
        {
            int pos = ret.indexOf("%FORMULAS%");
            ret = ret.replaceFirst("%FORMULAS%", "");
            int end = ret.indexOf("%", pos);
            if (end == -1)
            {
                System.err.println("Closing % not found for %FORMULAS% in file for type " + errorType);
                return ret;
            }
            String type = ret.substring(pos, end);
            String pType = prefix + type;
            if (!formulas.containsKey(type))
            {
                System.err.println("Formulas in FORMULAS.txt does not contain entry for type " + errorType);
                return ret.substring(0, pos - 1) + ret.substring(pos + type.length() + 2, ret.length() - 1);
            }
            String fStr = "";
            String reciprocalType = null;
            for (String f : formulas.get(type))
            {
                String dm = f.startsWith("/") ? "division" : "multiplication";
                String method = f.startsWith("/") ? "divide" : "times";
                String mdsign = f.startsWith("/") ? "/" : "*";
                f = f.substring(1, f.length());
                String param = f.split("=")[0].trim();
                String result = f.split("=")[1].trim();
                String pParam = prefix + param;
                String pResult = prefix + result;

                fStr += "        /**\n";
                fStr += "         * Calculate the " + dm + " of " + pType + " and " + pParam + ", which results in a ";
                fStr += pResult + " scalar.\n";
                fStr += "         * @param v " + pType + "; scalar\n";
                fStr += "         * @return " + pResult + "; scalar as a " + dm + " of " + pType + " and " + pParam + "\n";
                fStr += "         */\n";
                fStr += "        public final " + pResult + " " + method;
                fStr += "(final " + pParam + " v)\n";
                fStr += "        {\n";
                fStr += "            return new " + pResult + "(this.si " + mdsign + " v.si, ";
                fStr += result + "Unit.SI);\n";
                fStr += "        }\n\n";

                if (result.equals("Dimensionless") && mdsign.equals("*"))
                    reciprocalType = param;
            }

            // reciprocal function
            if (!absolute)
            {
                fStr += "        /** {@inheritDoc} */\n";
                fStr += "        @Override\n";
                if (reciprocalType == null)
                {
                    fStr += "        public " + prefix + "SIScalar reciprocal()\n";
                    fStr += "        {\n";
                    String df = prefix.length() == 0 ? "Double" : "Float";
                    fStr += "            return " + df + "Scalar.divide(" + prefix + "Dimensionless.ONE, this);\n";
                }
                else
                {
                    fStr += "        public " + prefix + reciprocalType + " reciprocal()\n";
                    fStr += "        {\n";
                    String f = prefix.length() == 0 ? "" : "f";
                    fStr += "            return " + prefix + reciprocalType + ".instantiateSI(1.0" + f + " / this.si);\n";
                }
                fStr += "        }\n\n";
            }

            ret = ret.substring(0, pos - 1) + fStr + ret.substring(pos + type.length() + 1, ret.length() - 1);
        }
        return ret;
    }

    /**
     * Insert replacements based on REPLACCE.txt into the Java file.
     * @param java String; the file
     * @param type String; the type
     * @return the file with replacements
     */
    private static String replace(String java, final String type)
    {
        // replace the "replacement" tags
        for (String tag : replaceMap.keySet())
        {
            String replacement = (replaceType.get(tag).equals(type)) ? replaceMap.get(tag) : "";
            while (java.contains(tag))
            {
                java = java.replace(tag, replacement);
            }
        }

        // @Generated
        java = java.replace("@Generated(value = \"GenerateDJUNIT\")",
                "@Generated(value = \"" + GenerateDJUNIT.class.getName() + "\", date = \"" + generationTime + "\")");

        return java;
    }

    /**
     * Replace the %TypeAbs%, %TypeRel%, %TypeAbsUnit%, and %TypeRelUnit% tags in the java string.
     * @param in String; the original java string
     * @param type String[]; the types: [abs, rel, unit]
     * @return the java string with replacements
     */
    private static String replaceAbsRel(final String in, final String[] type)
    {
        String typeAbs = type[0];
        String typeRel = type[1];
        String typeAbsUnit = type[2];
        String typeRelUnit = type[3];
        String java = in.replaceAll("%TypeAbs%", typeAbs);
        java = java.replaceAll("%typeabs%", typeAbs.toLowerCase());
        java = java.replaceAll("%TYPEABS%", typeAbs.toUpperCase());
        java = java.replaceAll("%TypeRel%", typeRel);
        java = java.replaceAll("%typerel%", typeRel.toLowerCase());
        java = java.replaceAll("%TYPEREL%", typeRel.toUpperCase());
        java = java.replaceAll("%TypeAbsUnit%", typeAbsUnit);
        java = java.replaceAll("%typeabsunit%", typeAbsUnit.toLowerCase());
        java = java.replaceAll("%TYPEABSUNIT%", typeAbsUnit.toUpperCase());
        java = java.replaceAll("%TypeRelUnit%", typeRelUnit);
        java = java.replaceAll("%typerelunit%", typeRelUnit.toLowerCase());
        java = java.replaceAll("%TYPERELUNIT%", typeRelUnit.toUpperCase());
        return java;
    }

    /**
     * Generate all Abs + Rel classes in value.vdouble.scalar.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateDoubleScalarAbsRel() throws IOException, URISyntaxException
    {
        for (int i = 0; i <= 1; i++)
        {
            String relativePath = "value/vdouble/scalar/";
            URL scalarURL = URLResource.getResource(
                    "/resources/" + relativePath + ((i == 0) ? "DOUBLE_SCALAR_AR_ABS.java" : "DOUBLE_SCALAR_AR_REL.java"));
            String scalarJava = new String(Files.readAllBytes(Paths.get(scalarURL.toURI())));

            for (String[] type : typesAbsRel)
            {
                File outPath = new File(absoluteRootPath + relativePath);
                outPath.mkdirs();
                PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + type[i] + ".java");
                String java = new String(scalarJava);
                java = replaceAbsRel(java, type);
                java = formulas(java, "DoubleScalar => " + type[i], "", i == 0);
                java = replace(java, type[i]);
                out.print(java);
                out.close();
                System.out.println("built: " + absoluteRootPath + relativePath + type[i] + ".java");
            }
        }
    }

    /**
     * Generate all Rel classes in value.vdouble.scalar.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateDoubleScalarRel() throws IOException, URISyntaxException
    {
        String relativePath = "value/vdouble/scalar/";
        URL scalarURL = URLResource.getResource("/resources/" + relativePath + "DOUBLE_SCALAR_REL.java");
        String scalarJava = new String(Files.readAllBytes(Paths.get(scalarURL.toURI())));

        for (String type : typesRel)
        {
            File outPath = new File(absoluteRootPath + relativePath);
            outPath.mkdirs();
            PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + type + ".java");
            String java = new String(scalarJava);
            java = java.replaceAll("%Type%", type);
            java = java.replaceAll("%type%", type.toLowerCase());
            java = java.replaceAll("%TYPE%", type.toUpperCase());
            if (java.contains("class Dimensionless"))
            {
                java = java.replace("%DIMLESS%", "implements DimensionlessFunctions<DimensionlessUnit, Dimensionless>");
                URL dimlessURL = URLResource.getResource("/resources/" + relativePath + "DimlessFunctions.java");
                String dimlessFunctions = new String(Files.readAllBytes(Paths.get(dimlessURL.toURI())));
                int pos = java.indexOf("%FORMULAS%");
                java = java.substring(0, pos - 1) + dimlessFunctions + java.substring(pos, java.length() - 1);
            }
            java = java.replace("%DIMLESS%", "");
            java = formulas(java, "DoubleScalar => " + type, "", false);
            java = replace(java, type);
            out.print(java);
            out.close();
            System.out.println("built: " + absoluteRootPath + relativePath + type + ".java");
        }
    }

    /**
     * Generate all Abs + Rel classes in value.vfloat.scalar.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateFloatScalarAbsRel() throws IOException, URISyntaxException
    {
        for (int i = 0; i <= 1; i++)
        {
            String relativePath = "value/vfloat/scalar/";
            URL scalarURL = URLResource.getResource(
                    "/resources/" + relativePath + ((i == 0) ? "FLOAT_SCALAR_AR_ABS.java" : "FLOAT_SCALAR_AR_REL.java"));
            String scalarJava = new String(Files.readAllBytes(Paths.get(scalarURL.toURI())));

            for (String type[] : typesAbsRel)
            {
                String fType = "Float" + type[i];
                File outPath = new File(absoluteRootPath + relativePath);
                outPath.mkdirs();
                PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + fType + ".java");
                String java = new String(scalarJava);
                java = replaceAbsRel(java, type);
                java = formulas(java, "FloatScalar => " + type[i], "Float", i == 0);
                java = replace(java, type[i]);
                out.print(java);
                out.close();
                System.out.println("built: " + absoluteRootPath + relativePath + fType + ".java");
            }
        }
    }

    /**
     * Generate all Rel classes in value.vfloat.scalar.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateFloatScalarRel() throws IOException, URISyntaxException
    {
        String relativePath = "value/vfloat/scalar/";
        URL scalarURL = URLResource.getResource("/resources/" + relativePath + "FLOAT_SCALAR_REL.java");
        String scalarJava = new String(Files.readAllBytes(Paths.get(scalarURL.toURI())));

        for (String type : typesRel)
        {
            String fType = "Float" + type;
            File outPath = new File(absoluteRootPath + relativePath);
            outPath.mkdirs();
            PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + fType + ".java");
            String java = new String(scalarJava);
            java = java.replaceAll("%Type%", type);
            java = java.replaceAll("%type%", type.toLowerCase());
            java = java.replaceAll("%TYPE%", type.toUpperCase());
            if (java.contains("class FloatDimensionless"))
            {
                java = java.replace("%DIMLESS%", " implements DimensionlessFunctions<DimensionlessUnit, FloatDimensionless>");
                URL dimlessURL = URLResource.getResource("/resources/" + relativePath + "DimlessFunctions.java");
                String dimlessFunctions = new String(Files.readAllBytes(Paths.get(dimlessURL.toURI())));
                int pos = java.indexOf("%FORMULAS%");
                java = java.substring(0, pos - 1) + dimlessFunctions + java.substring(pos, java.length() - 1);
            }
            java = java.replace("%DIMLESS%", "");
            java = formulas(java, "FloatScalar => " + type, "Float", false);
            java = replace(java, type);
            out.print(java);
            out.close();
            System.out.println("built: " + absoluteRootPath + relativePath + fType + ".java");
        }
    }

    /****************************************************************************************************************/
    /************************************************ VECTOR ********************************************************/
    /****************************************************************************************************************/

    /**
     * Insert formulas based on FORMULAS.txt into the %FORMULAS% marker within the Java file.
     * @param java String; the java file
     * @param errorType String; the type for error messaging
     * @param prefix String; e.g., Float for Float types, or blank for Double types
     * @return the file with replacements
     */
    private static String formulasVector(final String java, final String errorType, final String prefix)
    {
        String ret = java;
        while (ret.contains("%FORMULAS%"))
        {
            int pos = ret.indexOf("%FORMULAS%");
            ret = ret.replaceFirst("%FORMULAS%", "");
            int end = ret.indexOf("%", pos);
            if (end == -1)
            {
                System.err.println("Closing % not found for %FORMULAS% in file for type " + errorType);
                return ret;
            }
            String type = ret.substring(pos, end);
            if (!formulas.containsKey(type))
            {
                System.err.println("Formulas in FORMULAS.txt does not contain entry for type " + errorType);
                return ret.substring(0, pos - 1) + ret.substring(pos + type.length() + 2, ret.length() - 1);
            }
            String fStr = "";
            ret = ret.substring(0, pos - 1) + fStr + ret.substring(pos + type.length() + 1, ret.length() - 1);
        }
        return ret;
    }

    /****************************************************************************************************************/
    /********************************************* DOUBLEVECTOR *****************************************************/
    /****************************************************************************************************************/

    /**
     * Generate all Abs + Rel classes in value.vdouble.vector.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateDoubleVectorAbsRel() throws IOException, URISyntaxException
    {
        for (int i = 0; i <= 1; i++)
        {
            String relativePath = "value/vdouble/vector/";
            URL vectorURL = URLResource.getResource(
                    "/resources/" + relativePath + ((i == 0) ? "DOUBLE_VECTOR_AR_ABS.java" : "DOUBLE_VECTOR_AR_REL.java"));
            String vectorJava = new String(Files.readAllBytes(Paths.get(vectorURL.toURI())));

            for (String type[] : typesAbsRel)
            {
                File outPath = new File(absoluteRootPath + relativePath);
                outPath.mkdirs();
                PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + type[i] + "Vector.java");
                String java = new String(vectorJava);
                java = replaceAbsRel(java, type);
                java = formulasVector(java, "DoubleVector => " + type[i], "");
                java = replace(java, type[i]);
                out.print(java);
                out.close();
                System.out.println("built: " + absoluteRootPath + relativePath + type[i] + "Vector.java");
            }
        }
    }

    /**
     * Generate all Rel classes in value.vdouble.vector.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateDoubleVectorRel() throws IOException, URISyntaxException
    {
        String relativePath = "value/vdouble/vector/";
        URL vectorURL = URLResource.getResource("/resources/" + relativePath + "DOUBLE_VECTOR_REL.java");
        String vectorJava = new String(Files.readAllBytes(Paths.get(vectorURL.toURI())));

        for (String type : typesRel)
        {
            File outPath = new File(absoluteRootPath + relativePath);
            outPath.mkdirs();
            PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + type + "Vector.java");
            String java = new String(vectorJava);
            java = java.replaceAll("%Type%", type);
            java = java.replaceAll("%type%", type.toLowerCase());
            java = java.replaceAll("%TYPE%", type.toUpperCase());
            if (java.contains("class DimensionlessVector"))
            {
                java = java.replace("%DIMLESS%",
                        " implements DoubleMathFunctions, DimensionlessFunctions<DimensionlessUnit, DimensionlessVector>");
                URL dimlessURL = URLResource.getResource("/resources/" + relativePath + "DimlessFunctions.java");
                String dimlessFunctions = new String(Files.readAllBytes(Paths.get(dimlessURL.toURI())));
                int pos = java.indexOf("%FORMULAS%");
                java = java.substring(0, pos - 1) + dimlessFunctions + java.substring(pos, java.length() - 1);
            }
            java = java.replace("%DIMLESS%", "");
            java = formulasVector(java, "DoubleVector => " + type, "");
            java = replace(java, type);
            out.print(java);
            out.close();
            System.out.println("built: " + absoluteRootPath + relativePath + type + "Vector.java");
        }
    }

    /****************************************************************************************************************/
    /********************************************** FLOATVECTOR *****************************************************/
    /****************************************************************************************************************/

    /**
     * Generate all Abs + Rel classes in value.vfloat.vector.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateFloatVectorAbsRel() throws IOException, URISyntaxException
    {
        for (int i = 0; i <= 1; i++)
        {
            String relativePath = "value/vfloat/vector/";
            URL vectorURL = URLResource.getResource(
                    "/resources/" + relativePath + ((i == 0) ? "FLOAT_VECTOR_AR_ABS.java" : "FLOAT_VECTOR_AR_REL.java"));
            String vectorJava = new String(Files.readAllBytes(Paths.get(vectorURL.toURI())));

            for (String type[] : typesAbsRel)
            {
                String fType = "Float" + type[i];
                File outPath = new File(absoluteRootPath + relativePath);
                outPath.mkdirs();
                PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + fType + "Vector.java");
                String java = new String(vectorJava);
                java = replaceAbsRel(java, type);
                java = formulasVector(java, "FloatVector => " + fType, "");
                java = replace(java, type[i]);
                out.print(java);
                out.close();
                System.out.println("built: " + absoluteRootPath + relativePath + fType + "Vector.java");
            }
        }
    }

    /**
     * Generate all Rel classes in value.vfloat.vector.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateFloatVectorRel() throws IOException, URISyntaxException
    {
        String relativePath = "value/vfloat/vector/";
        URL vectorURL = URLResource.getResource("/resources/" + relativePath + "FLOAT_VECTOR_REL.java");
        String vectorJava = new String(Files.readAllBytes(Paths.get(vectorURL.toURI())));

        for (String type : typesRel)
        {
            String fType = "Float" + type;
            File outPath = new File(absoluteRootPath + relativePath);
            outPath.mkdirs();
            PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + fType + "Vector.java");
            String java = new String(vectorJava);
            java = java.replaceAll("%Type%", type);
            java = java.replaceAll("%type%", type.toLowerCase());
            java = java.replaceAll("%TYPE%", type.toUpperCase());
            if (java.contains("class FloatDimensionlessVector"))
            {
                java = java.replace("%DIMLESS%",
                        " implements DimensionlessFunctions<DimensionlessUnit, FloatDimensionlessVector>");
                URL dimlessURL = URLResource.getResource("/resources/" + relativePath + "DimlessFunctions.java");
                String dimlessFunctions = new String(Files.readAllBytes(Paths.get(dimlessURL.toURI())));
                int pos = java.indexOf("%FORMULAS%");
                java = java.substring(0, pos - 1) + dimlessFunctions + java.substring(pos, java.length() - 1);
            }
            java = java.replace("%DIMLESS%", "");
            java = formulasVector(java, "FloatVector => " + fType, "");
            java = replace(java, type);
            out.print(java);
            out.close();
            System.out.println("built: " + absoluteRootPath + relativePath + fType + "Vector.java");
        }
    }

    /****************************************************************************************************************/
    /************************************************ MATRIX ********************************************************/
    /****************************************************************************************************************/

    /**
     * Insert formulas based on FORMULAS.txt into the %FORMULAS% marker within the Java file.
     * @param java String; the java file
     * @param errorType String; the type for error messaging
     * @param prefix String; e.g., Float for Float types, or blank for Double types
     * @return the file with replacements
     */
    private static String formulasMatrix(final String java, final String errorType, final String prefix)
    {
        String ret = java;
        while (ret.contains("%FORMULAS%"))
        {
            int pos = ret.indexOf("%FORMULAS%");
            ret = ret.replaceFirst("%FORMULAS%", "");
            int end = ret.indexOf("%", pos);
            if (end == -1)
            {
                System.err.println("Closing % not found for %FORMULAS% in file for type " + errorType);
                return ret;
            }
            String type = ret.substring(pos, end);
            if (!formulas.containsKey(type))
            {
                System.err.println("Formulas in FORMULAS.txt does not contain entry for type " + errorType);
                return ret.substring(0, pos - 1) + ret.substring(pos + type.length() + 2, ret.length() - 1);
            }
            String fStr = "";
            ret = ret.substring(0, pos - 1) + fStr + ret.substring(pos + type.length() + 1, ret.length() - 1);
        }
        return ret;
    }

    /****************************************************************************************************************/
    /********************************************* DOUBLEMATRIX *****************************************************/
    /****************************************************************************************************************/

    /**
     * Generate all Abs + Rel classes in value.vdouble.matrix.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateDoubleMatrixAbsRel() throws IOException, URISyntaxException
    {
        for (int i = 0; i <= 1; i++)
        {
            String relativePath = "value/vdouble/matrix/";
            URL matrixURL = URLResource.getResource(
                    "/resources/" + relativePath + ((i == 0) ? "DOUBLE_MATRIX_AR_ABS.java" : "DOUBLE_MATRIX_AR_REL.java"));
            String matrixJava = new String(Files.readAllBytes(Paths.get(matrixURL.toURI())));

            for (String[] type : typesAbsRel)
            {
                File outPath = new File(absoluteRootPath + relativePath);
                outPath.mkdirs();
                PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + type[i] + "Matrix.java");
                String java = new String(matrixJava);
                java = replaceAbsRel(java, type);
                java = formulasMatrix(java, "DoubleMatrix => " + type[i], "");
                java = replace(java, type[i]);
                out.print(java);
                out.close();
                System.out.println("built: " + absoluteRootPath + relativePath + type[i] + "Matrix.java");
            }
        }
    }

    /**
     * Generate all Rel classes in value.vdouble.matrix.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateDoubleMatrixRel() throws IOException, URISyntaxException
    {
        String relativePath = "value/vdouble/matrix/";
        URL matrixURL = URLResource.getResource("/resources/" + relativePath + "DOUBLE_MATRIX_REL.java");
        String matrixJava = new String(Files.readAllBytes(Paths.get(matrixURL.toURI())));

        for (String type : typesRel)
        {
            File outPath = new File(absoluteRootPath + relativePath);
            outPath.mkdirs();
            PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + type + "Matrix.java");
            String java = new String(matrixJava);
            java = java.replaceAll("%Type%", type);
            java = java.replaceAll("%type%", type.toLowerCase());
            java = java.replaceAll("%TYPE%", type.toUpperCase());
            if (java.contains("class DimensionlessMatrix"))
            {
                java = java.replace("%DIMLESS%", " implements DimensionlessFunctions<DimensionlessUnit, DimensionlessMatrix>");
                URL dimlessURL = URLResource.getResource("/resources/" + relativePath + "DimlessFunctions.java");
                String dimlessFunctions = new String(Files.readAllBytes(Paths.get(dimlessURL.toURI())));
                int pos = java.indexOf("%FORMULAS%");
                java = java.substring(0, pos - 1) + dimlessFunctions + java.substring(pos, java.length() - 1);
            }
            java = java.replace("%DIMLESS%", "");
            java = formulasMatrix(java, "DoubleMatrix => " + type, "");
            java = replace(java, type);
            out.print(java);
            out.close();
            System.out.println("built: " + absoluteRootPath + relativePath + type + "Matrix.java");
        }
    }

    /****************************************************************************************************************/
    /********************************************** FLOATMATRIX *****************************************************/
    /****************************************************************************************************************/

    /**
     * Generate all Abs + Rel classes in value.vfloat.matrix.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateFloatMatrixAbsRel() throws IOException, URISyntaxException
    {
        for (int i = 0; i <= 1; i++)
        {
            String relativePath = "value/vfloat/matrix/";
            URL matrixURL = URLResource.getResource(
                    "/resources/" + relativePath + ((i == 0) ? "FLOAT_MATRIX_AR_ABS.java" : "FLOAT_MATRIX_AR_REL.java"));
            String matrixJava = new String(Files.readAllBytes(Paths.get(matrixURL.toURI())));

            for (String type[] : typesAbsRel)
            {
                String fType = "Float" + type[i];
                File outPath = new File(absoluteRootPath + relativePath);
                outPath.mkdirs();
                PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + fType + "Matrix.java");
                String java = new String(matrixJava);
                java = replaceAbsRel(java, type);
                java = formulasMatrix(java, "FloatMatrix => " + fType, "");
                java = replace(java, type[i]);
                out.print(java);
                out.close();
                System.out.println("built: " + absoluteRootPath + relativePath + fType + "Matrix.java");
            }
        }
    }

    /**
     * Generate all Rel classes in value.vfloat.matrix.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateFloatMatrixRel() throws IOException, URISyntaxException
    {
        String relativePath = "value/vfloat/matrix/";
        URL matrixURL = URLResource.getResource("/resources/" + relativePath + "FLOAT_MATRIX_REL.java");
        String matrixJava = new String(Files.readAllBytes(Paths.get(matrixURL.toURI())));

        for (String type : typesRel)
        {
            String fType = "Float" + type;
            File outPath = new File(absoluteRootPath + relativePath);
            outPath.mkdirs();
            PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + fType + "Matrix.java");
            String java = new String(matrixJava);
            java = java.replaceAll("%Type%", type);
            java = java.replaceAll("%type%", type.toLowerCase());
            java = java.replaceAll("%TYPE%", type.toUpperCase());
            if (java.contains("class FloatDimensionlessMatrix"))
            {
                java = java.replace("%DIMLESS%",
                        " implements DimensionlessFunctions<DimensionlessUnit, FloatDimensionlessMatrix>");
                URL dimlessURL = URLResource.getResource("/resources/" + relativePath + "DimlessFunctions.java");
                String dimlessFunctions = new String(Files.readAllBytes(Paths.get(dimlessURL.toURI())));
                int pos = java.indexOf("%FORMULAS%");
                java = java.substring(0, pos - 1) + dimlessFunctions + java.substring(pos, java.length() - 1);
            }
            java = java.replace("%DIMLESS%", "");
            java = formulasMatrix(java, "FloatMatrix => " + fType, "");
            java = replace(java, type);
            out.print(java);
            out.close();
            System.out.println("built: " + absoluteRootPath + relativePath + fType + "Matrix.java");
        }
    }

    /****************************************************************************************************************/
    /********************************************* SISCALAR *********************************************************/
    /****************************************************************************************************************/

    /**
     * Generate SIScalar.java in value.vdouble.scalar.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateSIScalar() throws IOException, URISyntaxException
    {
        String relativePath = "value/vdouble/scalar/";
        URL siScalarURL = URLResource.getResource("/resources/" + relativePath + "SISCALAR.java");
        String siJava = new String(Files.readAllBytes(Paths.get(siScalarURL.toURI())));
        siJava = siJava.replace("@Generated(value = \"GenerateDJUNIT\")",
                "@Generated(value = \"" + GenerateDJUNIT.class.getName() + "\", date = \"" + generationTime + "\")");
        String asJava = new String(Files
                .readAllBytes(Paths.get(URLResource.getResource("/resources/" + relativePath + "SISCALAR_AS.java").toURI())));

        List<String> allRelTypes = new ArrayList<>(typesRel);
        for (String[] arType : typesAbsRel)
        {
            allRelTypes.add(arType[1]);
        }

        String asMethods = "";
        for (String type : allRelTypes)
        {
            String lc = type.toLowerCase();
            asMethods += asJava.replaceAll("%Type%", type).replaceAll("%type%", lc);
        }

        File outPath = new File(absoluteRootPath + relativePath);
        outPath.mkdirs();
        PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + "SIScalar.java");
        String java = siJava.replace("%%ASMETHODS%%", asMethods);
        out.print(java);
        out.close();
        System.out.println("built: " + absoluteRootPath + relativePath + "SIScalar.java");
    }

    /**
     * Generate SIScalar.java in value.vfloat.scalar.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateFloatSIScalar() throws IOException, URISyntaxException
    {
        String relativePath = "value/vfloat/scalar/";
        URL siScalarURL = URLResource.getResource("/resources/" + relativePath + "FLOATSISCALAR.java");
        String siJava = new String(Files.readAllBytes(Paths.get(siScalarURL.toURI())));
        siJava = siJava.replace("@Generated(value = \"GenerateDJUNIT\")",
                "@Generated(value = \"" + GenerateDJUNIT.class.getName() + "\", date = \"" + generationTime + "\")");
        String asJava = new String(Files.readAllBytes(
                Paths.get(URLResource.getResource("/resources/" + relativePath + "FLOATSISCALAR_AS.java").toURI())));

        List<String> allRelTypes = new ArrayList<>(typesRel);
        for (String[] arType : typesAbsRel)
        {
            allRelTypes.add(arType[1]);
        }

        String asMethods = "";
        for (String type : allRelTypes)
        {
            String lc = type.toLowerCase();
            asMethods += asJava.replaceAll("%Type%", type).replaceAll("%type%", lc);
        }

        File outPath = new File(absoluteRootPath + relativePath);
        outPath.mkdirs();
        PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + "FloatSIScalar.java");
        String java = siJava.replace("%%ASMETHODS%%", asMethods);
        out.print(java);
        out.close();
        System.out.println("built: " + absoluteRootPath + relativePath + "FloatSIScalar.java");
    }

    /****************************************************************************************************************/
    /********************************************* SIVECTOR *********************************************************/
    /****************************************************************************************************************/

    /**
     * Generate SIVector.java in value.vdouble.vector.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateSIVector() throws IOException, URISyntaxException
    {
        String relativePath = "value/vdouble/vector/";
        URL siVectorURL = URLResource.getResource("/resources/" + relativePath + "SIVECTOR.java");
        String siJava = new String(Files.readAllBytes(Paths.get(siVectorURL.toURI())));
        siJava = siJava.replace("@Generated(value = \"GenerateDJUNIT\")",
                "@Generated(value = \"" + GenerateDJUNIT.class.getName() + "\", date = \"" + generationTime + "\")");
        String asJava = new String(Files
                .readAllBytes(Paths.get(URLResource.getResource("/resources/" + relativePath + "SIVECTOR_AS.java").toURI())));

        List<String> allRelTypes = new ArrayList<>(typesRel);
        for (String[] arType : typesAbsRel)
        {
            allRelTypes.add(arType[1]);
        }

        String asMethods = "";
        for (String type : allRelTypes)
        {
            String lc = type.toLowerCase();
            asMethods += asJava.replaceAll("%Type%", type).replaceAll("%type%", lc);
        }

        File outPath = new File(absoluteRootPath + relativePath);
        outPath.mkdirs();
        PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + "SIVector.java");
        String java = siJava.replace("%%ASMETHODS%%", asMethods);
        out.print(java);
        out.close();
        System.out.println("built: " + absoluteRootPath + relativePath + "SIVector.java");
    }

    /**
     * Generate SIVector.java in value.vfloat.vector.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateFloatSIVector() throws IOException, URISyntaxException
    {
        String relativePath = "value/vfloat/vector/";
        URL siVectorURL = URLResource.getResource("/resources/" + relativePath + "FLOATSIVECTOR.java");
        String siJava = new String(Files.readAllBytes(Paths.get(siVectorURL.toURI())));
        siJava = siJava.replace("@Generated(value = \"GenerateDJUNIT\")",
                "@Generated(value = \"" + GenerateDJUNIT.class.getName() + "\", date = \"" + generationTime + "\")");
        String asJava = new String(Files.readAllBytes(
                Paths.get(URLResource.getResource("/resources/" + relativePath + "FLOATSIVECTOR_AS.java").toURI())));

        List<String> allRelTypes = new ArrayList<>(typesRel);
        for (String[] arType : typesAbsRel)
        {
            allRelTypes.add(arType[1]);
        }

        String asMethods = "";
        for (String type : allRelTypes)
        {
            String lc = type.toLowerCase();
            asMethods += asJava.replaceAll("%Type%", type).replaceAll("%type%", lc);
        }

        File outPath = new File(absoluteRootPath + relativePath);
        outPath.mkdirs();
        PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + "FloatSIVector.java");
        String java = siJava.replace("%%ASMETHODS%%", asMethods);
        out.print(java);
        out.close();
        System.out.println("built: " + absoluteRootPath + relativePath + "FloatSIVector.java");
    }

    /****************************************************************************************************************/
    /********************************************* SIMATRIX *********************************************************/
    /****************************************************************************************************************/

    /**
     * Generate SIMatrix.java in value.vdouble.matrix.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateSIMatrix() throws IOException, URISyntaxException
    {
        String relativePath = "value/vdouble/matrix/";
        URL siMatrixURL = URLResource.getResource("/resources/" + relativePath + "SIMATRIX.java");
        String siJava = new String(Files.readAllBytes(Paths.get(siMatrixURL.toURI())));
        siJava = siJava.replace("@Generated(value = \"GenerateDJUNIT\")",
                "@Generated(value = \"" + GenerateDJUNIT.class.getName() + "\", date = \"" + generationTime + "\")");
        String asJava = new String(Files
                .readAllBytes(Paths.get(URLResource.getResource("/resources/" + relativePath + "SIMATRIX_AS.java").toURI())));

        List<String> allRelTypes = new ArrayList<>(typesRel);
        for (String[] arType : typesAbsRel)
        {
            allRelTypes.add(arType[1]);
        }

        String asMethods = "";
        for (String type : allRelTypes)
        {
            String lc = type.toLowerCase();
            asMethods += asJava.replaceAll("%Type%", type).replaceAll("%type%", lc);
        }

        File outPath = new File(absoluteRootPath + relativePath);
        outPath.mkdirs();
        PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + "SIMatrix.java");
        String java = siJava.replace("%%ASMETHODS%%", asMethods);
        out.print(java);
        out.close();
        System.out.println("built: " + absoluteRootPath + relativePath + "SIMatrix.java");
    }

    /**
     * Generate SIMatrix.java in value.vfloat.matrix.
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    private static void generateFloatSIMatrix() throws IOException, URISyntaxException
    {
        String relativePath = "value/vfloat/matrix/";
        URL siMatrixURL = URLResource.getResource("/resources/" + relativePath + "FLOATSIMATRIX.java");
        String siJava = new String(Files.readAllBytes(Paths.get(siMatrixURL.toURI())));
        siJava = siJava.replace("@Generated(value = \"GenerateDJUNIT\")",
                "@Generated(value = \"" + GenerateDJUNIT.class.getName() + "\", date = \"" + generationTime + "\")");
        String asJava = new String(Files.readAllBytes(
                Paths.get(URLResource.getResource("/resources/" + relativePath + "FLOATSIMATRIX_AS.java").toURI())));

        List<String> allRelTypes = new ArrayList<>(typesRel);
        for (String[] arType : typesAbsRel)
        {
            allRelTypes.add(arType[1]);
        }

        String asMethods = "";
        for (String type : allRelTypes)
        {
            String lc = type.toLowerCase();
            asMethods += asJava.replaceAll("%Type%", type).replaceAll("%type%", lc);
        }

        File outPath = new File(absoluteRootPath + relativePath);
        outPath.mkdirs();
        PrintWriter out = new PrintWriter(absoluteRootPath + relativePath + "FloatSIMatrix.java");
        String java = siJava.replace("%%ASMETHODS%%", asMethods);
        out.print(java);
        out.close();
        System.out.println("built: " + absoluteRootPath + relativePath + "FloatSIMatrix.java");
    }

    /****************************************************************************************************************/
    /********************************************* GENERIC **********************************************************/
    /****************************************************************************************************************/

    /**
     * Determine calculated absolute output path (root of the executable or Jar file). In case of an Eclipse run, ../../ is
     * added to the path to place the results in the root of the project, rather than in target/classes.<br>
     * See https://weblogs.java.net/blog/kohsuke/archive/2007/04/how_to_convert.html and
     * http://stackoverflow.com/questions/320542/how-to-get-the-path-of-a-running-jar-file and
     * http://stackoverflow.com/questions/3153337/get-current-working-directory-in-java
     * @throws FileNotFoundException in case file could not be found
     */
    private static void makeAndCleanAbsolutePath() throws FileNotFoundException
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
        else
        {
            System.out.println("about to delete: " + path);
            // if (!deleteRecursive(new File(path)))
            // {
            // System.err.println("Could not empty directory " + path);
            // System.exit(-1);
            // }
        }
        absoluteRootPath = path;
        System.out.println("writing into: " + path);
    }

    /**
     * By default File#delete fails for non-empty directories, it works like "rm". We need something a little more brutal - this
     * does the equivalent of "rm -r". From: http://stackoverflow.com/questions/779519/delete-files-recursively-in-java. Note:
     * USE CAREFULLY.
     * @param path File; Root File Path
     * @return true iff the file and all sub files/directories have been removed
     * @throws FileNotFoundException on error (e.g., locked file)
     */
    public static boolean deleteRecursive(final File path) throws FileNotFoundException
    {
        if (!path.exists())
        {
            throw new FileNotFoundException(path.getAbsolutePath());
        }
        boolean ret = true;
        if (path.isDirectory())
        {
            for (File f : path.listFiles())
            {
                ret = ret && deleteRecursive(f);
            }
        }
        return ret && path.delete();
    }

    /**
     * @param args String[]; args, should be blank
     * @throws IOException on I/O error
     * @throws URISyntaxException when file could not be found on the file system
     */
    public static void main(final String[] args) throws IOException, URISyntaxException
    {
        generationTime = Instant.now().toString();

        makeAndCleanAbsolutePath();
        readAbsRelTypes();
        readRelTypes();
        readFormulas();
        readReplace();

        generateDoubleScalarAbsRel();
        generateDoubleScalarRel();
        generateFloatScalarAbsRel();
        generateFloatScalarRel();

        generateDoubleVectorAbsRel();
        generateDoubleVectorRel();
        generateFloatVectorAbsRel();
        generateFloatVectorRel();

        generateDoubleMatrixAbsRel();
        generateDoubleMatrixRel();
        generateFloatMatrixAbsRel();
        generateFloatMatrixRel();

        generateSIScalar();
        generateFloatSIScalar();

        generateSIVector();
        generateFloatSIVector();

        generateSIMatrix();
        generateFloatSIMatrix();
    }

}
