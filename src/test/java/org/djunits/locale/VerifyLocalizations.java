package org.djunits.locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.djunits.unit.DurationUnit;
import org.junit.Test;

/**
 * Verify that all localizations contain all keys.
 * <p>
 * @author <a href="https://www.tudelft.nl/p.knoppers">Peter Knoppers</a>
 */
public class VerifyLocalizations
{

    /**
     * Replace unicode escapes by the corresponding character. Based on
     * https://stackoverflow.com/questions/37502058/replace-unicode-escapes-with-the-corresponding-character
     * @param in String; the input string to process
     * @return String the processed input string
     */
    static String fixUnicodeEscapes(final String in)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < in.length(); i++)
        {
            if (in.length() >= i + 6 && in.substring(i, i + 2).equals("\\u"))
            {
                result.append(Character.toChars(Integer.parseInt(in.substring(i + 2, i + 6), 16)));
                i += 5;
            }
            else
            {
                result.append(in.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     * Verify that all localizations have the same set of keys as the localeunit.properties file and check that a string using
     * each of the acceptable unit names is correctly parsed.
     * @throws IOException when a file could not be read
     * @throws ClassNotFoundException ...
     * @throws SecurityException ...
     * @throws NoSuchMethodException ...
     * @throws InvocationTargetException ...
     * @throws IllegalArgumentException ...
     * @throws IllegalAccessException ...
     * @throws URISyntaxException ...
     */
    @Test
    public void verifyLocalizations() throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, URISyntaxException
    {
        String pathToResources = new File(getClass().getResource("/resources/locale").toURI().getPath()).getAbsolutePath();
        String referenceFileName = "unit_en.properties";
        // system.out.println(pathToResources + "/" + referenceFileName);
        // Parse the localeunit.properties file and store all the keys
        List<String> reference =
                Files.readAllLines(Paths.get(pathToResources + "/" + referenceFileName), StandardCharsets.ISO_8859_1);
        // Replace unicode escapes by the corresponding character
        for (int index = 0; index < reference.size(); index++)
        {
            reference.set(index, fixUnicodeEscapes(reference.get(index)));
        }
        Map<String, Integer> keys = new HashMap<>();
        Map<String, String> values = new HashMap<>();
        for (int lineNo = 0; lineNo < reference.size(); lineNo++)
        {
            String line = reference.get(lineNo);
            if (line.startsWith("#"))
            {
                continue;
            }
            String[] parts = line.split("\\=");
            assertTrue(referenceFileName + "(" + (lineNo + 1) + ") should contain a equals sign", parts.length > 1);
            String key = parts[0].trim();
            keys.put(parts[0].trim(), (lineNo + 1));
            if (parts.length > 1)
            {
                values.put(key, parts[1]);
            }
        }
        // system.out.println("Collected " + keys.size() + " keys from reference file " + referenceFileName);
        // system.out.println("Verifying that the valueOf method of each unit correctly parses all writing styles into a Scalar");
        for (String key : new TreeSet<>(keys.keySet()))
        {
            int positionOfPoint = key.indexOf('.');
            if (positionOfPoint < 0)
            {
                continue;
            }
            String className = key.substring(0, positionOfPoint);
            double testValue = 123.456;
            String abbreviationsString = values.get(key);
            String[] abbreviations = abbreviationsString.split("\\|");
            for (int index = 0; index < abbreviations.length; index++)
            {
                if (index == 1)
                {
                    continue;
                }
                String input = testValue + " " + abbreviations[index].trim();
                // System.out.print(String.format("%-30s: %-20s", key, input));
                Class<?> c = Class.forName("org.djunits.value.vdouble.scalar." + className);
                Method valueOf = c.getMethod("valueOf", String.class);
                Object result = valueOf.invoke(null, input);
                // system.out.println(" got parsed into " + result);
            }
        }
        List<Path> paths = new ArrayList<>();
        Files.list(new File(pathToResources).toPath()).forEach(path -> paths.add(path));
        for (Path path : paths)
        {
            // System.out.println(path);
            if (path.getFileName().toString().startsWith("unit_")
                    && path.getFileName().toString().endsWith(".properties"))
            {
                // system.out.println("Verifying locale file " + path.getFileName().toString());
                Set<String> missingKeys = new TreeSet<>(keys.keySet());
                Set<String> foundKeys = new HashSet<>();
                int lineNo = 0;
                int errors = 0;
                for (String line : Files.readAllLines(path, StandardCharsets.ISO_8859_1))
                {
                    lineNo++;
                    line = fixUnicodeEscapes(line);
                    if (line.startsWith("#"))
                    {
                        continue;
                    }
                    String[] parts = line.split("\\=");
                    if (parts.length < 2)
                    {
                        System.err.println(path.getFileName() + "(" + (lineNo) + ") does not contain an equals sign");
                        errors++;
                        continue;
                    }
                    String key = parts[0].trim();
                    if (!keys.containsKey(key))
                    {
                        System.err.println(path.getFileName() + "(" + lineNo + "): key " + key + " is not a valid key");
                        errors++;
                    }
                    if (foundKeys.contains(key))
                    {
                        System.err.println(path.getFileName() + "(" + lineNo + "): duplicate key " + key);
                        errors++;
                    }
                    missingKeys.remove(key);
                    foundKeys.add(key);
                }
                for (String key : missingKeys)
                {
                    System.err.println("file " + path.getFileName() + " misses key " + key);
                    errors++;
                }
                assertEquals("No errors in file " + path.getFileName(), 0, errors);
            }
        }
    }

    
    /**
     * Check that all UnitSystems have valid a nameKey and a valid abbreviationKey and test those keys in all available
     * localizations.
     * @throws URISyntaxException on error
     */
    @Test
    public final void checkUnitSystemsLocale() throws URISyntaxException
    {
        Locale.setDefault(Locale.US);
        assertEquals("Duration", DurationUnit.MINUTE.getLocalizedName());
        assertEquals("h", DurationUnit.HOUR.getLocalizedDisplayAbbreviation());
        assertEquals("hour", DurationUnit.HOUR.getLocalizedTextualAbbreviation());
        Locale.setDefault(new Locale("nl", "NL"));
        assertEquals("Tijdsduur", DurationUnit.MINUTE.getLocalizedName());
        assertEquals("u", DurationUnit.HOUR.getLocalizedDisplayAbbreviation());
        assertEquals("uur", DurationUnit.HOUR.getLocalizedTextualAbbreviation());
    }

}
