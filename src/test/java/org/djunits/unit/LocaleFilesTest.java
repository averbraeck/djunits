package org.djunits.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * Validates all locale property bundles for formatting rules and ensures abbreviations resolve in their locale. <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class LocaleFilesTest
{
    /** Locales present in resources/locale as provided. */
    private static final List<Locale> LOCALES = Arrays.asList(//
            new Locale("de"), //
            new Locale("en", "GB"), //
            new Locale("es"), //
            new Locale("fr"), //
            new Locale("it"), //
            new Locale("ja"), //
            new Locale("ko"), //
            new Locale("nl"), //
            new Locale("pt"), //
            new Locale("zh"), //
            new Locale("zh", "TW") //
    );

    /** Allowed key patterns in locale property files (updated to allow dots in UnitKey and suffix at end). */
    private static final List<Pattern> ALLOWED_KEY_PATTERNS = Arrays.asList(
            // unitsystem.<System>.abbr|name
            Pattern.compile("^unitsystem\\.[A-Za-z][A-Za-z0-9_]*\\.(abbr|name)$"),

            // si.prefix.<sym>.(abbr|name|display)
            // Allow letters and Greek µ; symbol part typically doesn't include dots in your files.
            Pattern.compile("^si\\.prefix\\.[A-Za-zµ]+\\.(abbr|name|display)$"),

            // quantity.<Quantity>.name
            Pattern.compile("^quantity\\.[A-Za-z][A-Za-z0-9_]*\\.name$"),

            // unit.<Quantity>.<UnitKey>.(abbr|name|display)
            // - <Quantity>: letters/digits/underscores
            // - <UnitKey>: ANY characters, including spaces, (, ), /, ., up to the last dot before suffix.
            Pattern.compile("^unit\\.[A-Za-z][A-Za-z0-9_]*\\..+\\.(abbr|name|display)$"),

            // new.<Quantity>.<NewKey>.(unit|abbr|name|display)
            // Same approach as unit.*, but suffix includes 'unit' as well.
            Pattern.compile("^new\\.[A-Za-z][A-Za-z0-9_]*\\.[^\\s]+\\.(unit|abbr|name|display)$"));

    /**
     * Validate that all keys in each locale bundle match one of the allowed patterns. Also ensure abbr values are non-blank
     * when present.
     */
    @Test
    public void testAllLocaleFilesKeyFormat()
    {
        for (Locale locale : LOCALES)
        {
            ResourceBundle bundle = Units.bundle(locale);
            assertNotNull(bundle, "Bundle should load for locale: " + locale);

            for (String key : bundle.keySet())
            {
                boolean matchesAny = ALLOWED_KEY_PATTERNS.stream().anyMatch(p -> p.matcher(key).matches());
                if (!matchesAny)
                {
                    fail("Key does not match any allowed pattern in locale " + locale + ": " + key);
                }

                // For *.abbr entries, ensure value is non-blank after stripping.
                if (key.endsWith(".abbr"))
                {
                    String token = bundle.getString(key);
                    assertNotNull(token, "abbr value must exist for key: " + key);
                    assertFalse(token.strip().isBlank(), "abbr must not be blank (key: " + key + ")");
                }
            }
        }
    }

    /**
     * For every locale, ensure each 'unit. &lt;Q&gt;.&lt;K&gt;.abbr' token resolves to the registered US unit for that (Q,K)
     * pair when that pair exists in the US map. Also check that localizedUnitTextualAbbr(locale, Q, K) returns the same token.
     * Stores and restores default locale per iteration.
     * @param <U> A generic unit type to use in the method
     */
    @Test
    public <U extends UnitInterface<U, ?>> void testLocalizedAbbreviationsResolveForAllLocales()
    {
        Map<String, Map<String, UnitInterface<?, ?>>> usMap = Units.registeredUnits();
        assertNotNull(usMap);
        Locale originalDefault = Locale.getDefault();

        for (Locale locale : LOCALES)
        {
            try
            {
                // Use this locale for resolve() by setting it as default and refreshing translate map.
                Locale.setDefault(locale);
                ResourceBundle bundle = Units.bundle(locale);
                assertNotNull(bundle, "Bundle should load for locale: " + locale);

                for (String key : bundle.keySet())
                {
                    if (!key.startsWith("unit.") || !key.endsWith(".abbr"))
                    {
                        continue;
                    }

                    // Parse unit.<Q>.<K>.abbr
                    String afterUnit = key.substring("unit.".length());
                    int dotIndex = afterUnit.indexOf('.');
                    if (dotIndex <= 0)
                    {
                        fail("Malformed 'unit.' key: " + key);
                    }
                    String quantity = afterUnit.substring(0, dotIndex);
                    String unitKey = afterUnit.substring(dotIndex + 1, afterUnit.length() - ".abbr".length());

                    // Only test resolve if (Q,K) exists in US registered map.
                    if (!usMap.containsKey(quantity) || !usMap.get(quantity).containsKey(unitKey))
                    {
                        // It’s allowed that a locale declares units for quantities you haven't registered yet;
                        // the Units.readTranslateMap will log and skip those. Skip check here.
                        continue;
                    }

                    UnitInterface<?, ?> expectedUnit = usMap.get(quantity).get(unitKey);
                    assertNotNull(expectedUnit);

                    String localizedToken = bundle.getString(key);
                    assertNotNull(localizedToken);
                    localizedToken = localizedToken.strip();
                    assertFalse(localizedToken.isBlank(), "Localized abbr must not be blank (key: " + key + ")");

                    // Resolve localized token to unit and compare instances.
                    Class<?> unitClass = expectedUnit.getClass();
                    @SuppressWarnings("unchecked")
                    U resolved = Units.resolve((Class<U>) unitClass, localizedToken);
                    assertNotNull(resolved, "Resolve must return a unit for token: " + localizedToken);
                    // For nested Unit classes the instances are singletons; instance equality should hold.
                    // If your implementation returns equivalent instances, we can still check textual abbreviation equality.
                    assertTrue(
                            expectedUnit == resolved || expectedUnit.getStoredTextualAbbreviation()
                                    .equals(resolved.getStoredTextualAbbreviation()),
                            "Resolved unit should match expected for " + quantity + "." + unitKey + " token=" + localizedToken);

                    // Check localized lookup API returns the same token for textual abbreviation.
                    String apiLocalizedToken = Units.localizedUnitTextualAbbr(locale, quantity, unitKey);
                    assertEquals(localizedToken, apiLocalizedToken,
                            "localizedUnitTextualAbbr should return the bundle token for " + quantity + "." + unitKey);
                }
            }
            finally
            {
                Locale.setDefault(originalDefault);
            }
        }
    }
}
