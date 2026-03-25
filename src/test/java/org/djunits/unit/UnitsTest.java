package org.djunits.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.djunits.quantity.Frequency;
import org.djunits.quantity.Length;
import org.djunits.quantity.Power;
import org.djunits.quantity.Speed;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Units} utility covering US parsing, localization lookups, bundle loading, nested-class naming, and
 * safe-copy behavior.
 * <p>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class UnitsTest
{
    /**
     * Verify US-locale resolution of common unit abbreviations via {@link Units#resolve(Class, String)}. Stores and restores
     * the original default locale.
     * @param <U> a n anonymous unit type
     */
    @SuppressWarnings("unchecked")
    @Test
    public <U extends Unit<U, ?>> void testResolveUsLocaleParsing()
    {
        Locale original = Locale.getDefault();
        try
        {
            Locale.setDefault(Locale.US);

            // Known US abbreviations should resolve without error.
            Unit<?, ?> kmh = Units.resolve(Speed.Unit.class, "km/h");
            assertNotNull(kmh);
            assertEquals("km/h", kmh.getStoredTextualAbbreviation());

            Unit<?, ?> mih = Units.resolve(Speed.Unit.class, "mi/h");
            assertNotNull(mih);
            assertEquals("mi/h", mih.getStoredTextualAbbreviation());

            Unit<?, ?> kt = Units.resolve(Speed.Unit.class, "kt");
            assertNotNull(kt);
            assertEquals("kt", kt.getStoredTextualAbbreviation());

            Unit<?, ?> cm = Units.resolve(Length.Unit.class, "cm");
            assertNotNull(cm);
            assertEquals("cm", cm.getStoredTextualAbbreviation());

            // Edge cases: null arguments and unknown abbreviations, non-unit class.
            assertThrows(NullPointerException.class, () -> Units.resolve(null, "m"));
            assertThrows(NullPointerException.class, () -> Units.resolve(Length.Unit.class, null));
            assertThrows(UnitRuntimeException.class, () -> Units.resolve(Length.Unit.class, "unknown-token"));
            Object o = new String("m");
            assertThrows(IllegalArgumentException.class, () -> Units.resolve((Class<U>) (o.getClass()), "m"));
        }
        finally
        {
            Locale.setDefault(original);
        }
    }

    /**
     * Verify localized resolution for French and German where abbreviations differ from US. - FR:
     * {@code unit.Frequency.rpm.abbr = tr/min} - DE: {@code unit.Power.hp(M).abbr = PS}, {@code unit.Speed.kt.abbr = kn}
     */
    @Test
    public void testLocalizedResolveFrAndDe()
    {
        Locale original = Locale.getDefault();
        try
        {
            // --- French ---
            Locale.setDefault(Locale.FRANCE);
            // assertDoesNotThrow(Units::readTranslateMap); // ensure translate map is refreshed

            Unit<?, ?> rpmFr = Units.resolve(Frequency.Unit.class, "tr/min");
            assertNotNull(rpmFr);
            // The underlying US-stored key is "rpm"; stored abbreviation should be US key.
            assertEquals("rpm", rpmFr.getStoredTextualAbbreviation());

            // --- German ---
            Locale.setDefault(Locale.GERMANY);
            // assertDoesNotThrow(Units::readTranslateMap);

            Power.Unit hpMetricDe = Units.resolve(Power.Unit.class, "PS");
            assertNotNull(hpMetricDe);
            // German localized token maps to US unit key "hp(M)"
            assertEquals("hp(M)", hpMetricDe.getStoredTextualAbbreviation());

            Speed.Unit knotDe = Units.resolve(Speed.Unit.class, "kn");
            assertNotNull(knotDe);
            // German localized token maps to US key "kt"
            assertEquals("kt", knotDe.getStoredTextualAbbreviation());
        }
        finally
        {
            Locale.setDefault(original);
        }
    }

    /**
     * Verify localized display and name lookup and fallback behavior. - Present localized display (e.g., DE Ångström has
     * display U+212B) - Fallback to stored display/name when not present in the bundle.
     */
    @Test
    public void testLocalizedDisplayAndName()
    {
        Locale original = Locale.getDefault();
        try
        {
            Locale.setDefault(Locale.GERMANY);

            // Present localized display (Ångström)
            String aDisplayDe = Units.localizedUnitDisplayAbbr(Locale.getDefault(), "Length", "A");
            assertEquals("\u212B", aDisplayDe);

            String aNameDe = Units.localizedUnitName(Locale.getDefault(), "Length", "A");
            assertNotNull(aNameDe); // "Ångström"

            // Fallback behavior: cm has no dedicated display in many locales; should fall back to stored display "cm".
            String cmDisplayDe = Units.localizedUnitDisplayAbbr(Locale.getDefault(), "Length", "cm");
            assertEquals("cm", cmDisplayDe);

            String cmNameDe = Units.localizedUnitName(Locale.getDefault(), "Length", "cm");
            // Localized name present (e.g., "Zentimeter"), but even if missing it should fallback to stored US name.
            assertNotNull(cmNameDe);
        }
        finally
        {
            Locale.setDefault(original);
        }
    }

    /**
     * Verify {@link Units#unitClassName(Class)} returns nested names (e.g., "Length.Unit") rather than just "Unit", and trims
     * package name.
     */
    @Test
    public void testUnitClassNameForNested()
    {
        String nested = Units.unitClassName(Length.Unit.class);
        assertEquals("Length.Unit", nested);

        String nestedSpeed = Units.unitClassName(Speed.Unit.class);
        assertEquals("Speed.Unit", nestedSpeed);
    }

    /**
     * Verify {@link Units#localizedQuantityName(Locale, String)} falls back to the provided default when the key is missing for
     * a given locale.
     */
    @Test
    public void testLocalizedQuantityNameFallback()
    {
        Locale original = Locale.getDefault();
        try
        {
            Locale bogus = new Locale("xx", "XX");
            Locale.setDefault(bogus);

            // Using a bogus locale; key may not exist; must fallback to provided quantityName.
            String qName = Units.localizedQuantityName(Locale.getDefault(), "NonExistingQuantity");
            assertEquals("NonExistingQuantity", qName);
        }
        finally
        {
            Locale.setDefault(original);
        }
    }

    /**
     * Verify {@link Units#registeredUnits()} returns a safe top-level copy: mutating the returned map does not affect the
     * internal UNIT_MAP.
     */
    @Test
    public void testRegisteredUnitsSafeTopLevelCopy()
    {
        Map<String, Map<String, Unit<?, ?>>> snapshot = Units.registeredUnits();
        assertNotNull(snapshot);
        assertNotEquals(0, snapshot.size());

        // Mutate the top-level map and verify internal state continues to resolve known entries.
        snapshot.put("BogusQuantity", new LinkedHashMap<>());
        Unit<?, ?> meter = Units.resolve(Length.Unit.class, "m");
        assertNotNull(meter);
    }

    /**
     * Verify the UTF-8 ResourceBundle loader can read localized files and retrieve non-ASCII content.
     */
    @Test
    public void testBundleUtf8ControlLoadsFiles()
    {
        ResourceBundle fr = Units.bundle(Locale.FRANCE);
        assertNotNull(fr);

        // Ångström display for Length in FR should be U+212B.
        String displayFr = fr.getString("unit.Length.A.display");
        assertEquals("\u212B", displayFr);

        ResourceBundle de = Units.bundle(Locale.GERMANY);
        assertNotNull(de);
        String hpMetricAbbrDe = de.getString("unit.Power.hp(M).abbr");
        assertEquals("PS", hpMetricAbbrDe);
    }

    /**
     * Test register/unregister and retrieving localication for unknown quantities.
     */
    @Test
    public void testRegisterUnregister()
    {
        Length.Unit lu = Length.Unit.m.deriveUnit("two", "two", 2.0, UnitSystem.OTHER);
        assertEquals(lu, Units.resolve(Length.Unit.class, "two"));
        assertNotNull(Units.localizedUnitName(Locale.US, "Length", "two"));
        assertNotNull(Units.localizedUnitDisplayAbbr(Locale.US, "Length", "two"));
        assertNotNull(Units.localizedUnitTextualAbbr(Locale.US, "Length", "two"));
        Units.unregister(lu);
        assertThrows(UnitRuntimeException.class, () -> Units.resolve(Length.Unit.class, "two"),
                "resolving removed unit should result in exception");

        var noRegisterUnit = new NoRegisterUnit();
        assertNotNull(Units.localizedUnitName(Locale.US, "xx", "yy"));
        assertNotNull(Units.localizedUnitDisplayAbbr(Locale.US, "xx", "yy"));
        assertNotNull(Units.localizedUnitTextualAbbr(Locale.US, "xx", "yy"));

        QUnit qu = new QUnit();
        Units.register(qu);
        assertNotNull(Units.localizedQuantityName((Class<? extends Unit<?, ?>>) QUnit.class));
        assertNotNull(Units.localizedUnitName(Locale.US, "UnitsTest.QUnit", "xx"));
        assertNotNull(Units.localizedUnitDisplayAbbr(Locale.US, "UnitsTest.QUnit", "xx"));
        assertNotNull(Units.localizedUnitTextualAbbr(Locale.US, "UnitsTest.QUnit", "xx"));
        Units.unregister(qu);

        // resolving a non-registered unit should lead to an exception
        assertThrows(UnitRuntimeException.class, () -> Units.resolve(NoRegisterUnit.class, "no"),
                "resolving non-registered unit should result in exception");
        // unregistering a non-registered unit should not lead to an exception
        Units.unregister(noRegisterUnit);
    }

    /** UnitInterface class that does not register itself. */
    @SuppressWarnings("rawtypes")
    static class NoRegisterUnit implements Unit
    {
        @Override
        public String getId()
        {
            return null;
        }

        @Override
        public Scale getScale()
        {
            return null;
        }

        @Override
        public UnitSystem getUnitSystem()
        {
            return null;
        }

        @Override
        public SIUnit siUnit()
        {
            return null;
        }

        @Override
        public Unit getBaseUnit()
        {
            return null;
        }

        @Override
        public String getDisplayAbbreviation()
        {
            return null;
        }

        @Override
        public String getTextualAbbreviation()
        {
            return null;
        }

        @Override
        public String getName()
        {
            return null;
        }

        @Override
        public String getStoredDisplayAbbreviation()
        {
            return null;
        }

        @Override
        public String getStoredTextualAbbreviation()
        {
            return null;
        }

        @Override
        public String getStoredName()
        {
            return null;
        }

        @Override
        public Unit setSiPrefix(final SIPrefix siPrefix)
        {
            return null;
        }

        @Override
        public Unit setSiPrefix(final String prefix)
        {
            return null;
        }

        @Override
        public Unit setSiPrefixKilo(final String prefix)
        {
            return null;
        }

        @Override
        public Unit setSiPrefixPer(final String prefix)
        {
            return null;
        }

        @Override
        public SIPrefix getSiPrefix()
        {
            return null;
        }

        @Override
        public Quantity ofSi(final double si)
        {
            return null;
        }
    };

    /** Quantity Q. */
    static class Q extends Quantity<Q>
    {
        /** */
        private static final long serialVersionUID = 1L;

        /**
         * @param value the Q value
         * @param displayUnit the unit
         */
        Q(final double value, final QUnit displayUnit)
        {
            super(value, displayUnit);
        }

        @Override
        public Q instantiate(final double siValue)
        {
            return new Q(siValue, QUnit.DEFAULT);
        }
    }

    /** UnitInterface Length class that does not register itself. */
    static class QUnit implements Unit<QUnit, Q>
    {
        /** */
        public static final QUnit DEFAULT = new QUnit();

        @Override
        public String getId()
        {
            return "Q";
        }

        @Override
        public Scale getScale()
        {
            return null;
        }

        @Override
        public UnitSystem getUnitSystem()
        {
            return null;
        }

        @Override
        public SIUnit siUnit()
        {
            return null;
        }

        @Override
        public QUnit getBaseUnit()
        {
            return null;
        }

        @Override
        public String getDisplayAbbreviation()
        {
            return "Q";
        }

        @Override
        public String getTextualAbbreviation()
        {
            return "Q";
        }

        @Override
        public String getName()
        {
            return "Q";
        }

        @Override
        public String getStoredDisplayAbbreviation()
        {
            return "Q";
        }

        @Override
        public String getStoredTextualAbbreviation()
        {
            return "Q";
        }

        @Override
        public String getStoredName()
        {
            return "Q";
        }

        @Override
        public QUnit setSiPrefix(final SIPrefix siPrefix)
        {
            return null;
        }

        @Override
        public QUnit setSiPrefix(final String prefix)
        {
            return null;
        }

        @Override
        public QUnit setSiPrefixKilo(final String prefix)
        {
            return null;
        }

        @Override
        public QUnit setSiPrefixPer(final String prefix)
        {
            return null;
        }

        @Override
        public SIPrefix getSiPrefix()
        {
            return null;
        }

        @Override
        public Q ofSi(final double si)
        {
            return null;
        }
    }

}
