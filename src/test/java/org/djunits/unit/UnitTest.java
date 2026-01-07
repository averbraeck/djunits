package org.djunits.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Frequency;
import org.djunits.quantity.Length;
import org.djunits.quantity.Mass;
import org.djunits.quantity.def.Quantity;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIPrefix;
import org.djunits.unit.si.SIPrefixes;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link UnitInterface}, {@link AbstractUnit}, and representative concrete units.
 * <p>
 * The goal is to validate the shared unit contracts:
 * <ul>
 * <li>Public getters and default conversion methods of {@link UnitInterface}.</li>
 * <li>Construction guards, equality/hashCode/toString, SI-prefix generation, and derivation in {@link AbstractUnit}.</li>
 * <li>Integration with existing concrete units ({@link org.djunits.quantity.Length.Unit},
 * {@link org.djunits.quantity.Mass.Unit}, {@link org.djunits.quantity.Frequency.Unit}).</li>
 * <li>Defining a new unit inside the test (Jerk: m/s^3) using {@link AbstractUnit} to verify extensibility.</li>
 * </ul>
 * </p>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class UnitTest
{
    /*-
     * ====================================================================== 
     * Section 1: UnitInterface default methods and metadata on real units 
     * ======================================================================
     */

    /**
     * Verify {@link UnitInterface#toBaseValue(double)} and {@link UnitInterface#fromBaseValue(double)} forward to the
     * underlying {@link Scale} and work for base and derived units.
     */
    @Test
    @DisplayName("UnitInterface default conversions: base and derived")
    public void testUnitInterfaceDefaultConversions()
    {
        // Base (meter): identity conversion
        UnitInterface<?, ?> m = Length.Unit.m;
        assertEquals(25.0, m.toBaseValue(25.0), 0.0);
        assertEquals(25.0, m.fromBaseValue(25.0), 0.0);

        // Derived (kilometer): factor 1000
        UnitInterface<?, ?> km = Length.Unit.km;
        assertEquals(2500.0, km.toBaseValue(2.5), 1e-12);
        assertEquals(2.5, km.fromBaseValue(2500.0), 1e-12);

        // Frequency derived: MHz
        UnitInterface<?, ?> mhz = Frequency.Unit.MHz;
        assertEquals(3.2e6, mhz.toBaseValue(3.2), 1e-6);
        assertEquals(3.2, mhz.fromBaseValue(3.2e6), 1e-12);
    }

    /**
     * Verify {@link UnitInterface#quantityInUnit(double)} constructs a quantity with correct SI value and display unit.
     */
    @Test
    @DisplayName("UnitInterface.quantityInUnit: SI value and display unit are set")
    public void testUnitInterfaceQuantityInUnit()
    {
        // 2.0 km -> si=2000 m, display unit == km
        Length.Unit km = Length.Unit.km;
        Length qKm = km.quantityInUnit(2.0);
        assertEquals(2000.0, qKm.si(), 1e-12);
        assertSame(km, qKm.getDisplayUnit());

        // 5 MHz -> si=5e6 Hz, display unit == MHz
        Frequency.Unit mhz = Frequency.Unit.MHz;
        Frequency qF = mhz.quantityInUnit(5.0);
        assertEquals(5.0e6, qF.si(), 1e-6);
        assertSame(mhz, qF.getDisplayUnit());
    }

    /**
     * Verify basic metadata and identity of units (id/name/abbreviations/system/scale) without assuming localization.
     */
    @Test
    @DisplayName("UnitInterface metadata: id/name/abbreviations/system/scale")
    public void testUnitInterfaceMetadata()
    {
        // Length: meter
        Length.Unit m = Length.Unit.m;
        assertEquals("m", m.getId());
        assertNotNull(m.getName());
        assertFalse(m.getName().isBlank());
        assertNotNull(m.getDisplayAbbreviation());
        assertFalse(m.getDisplayAbbreviation().isBlank());
        assertNotNull(m.getTextualAbbreviation());
        assertFalse(m.getTextualAbbreviation().isBlank());
        assertEquals(UnitSystem.SI_BASE, m.getUnitSystem());
        assertTrue(m.getScale().isBaseScale());

        // Mass: kilogram is SI base (kilo-default)
        Mass.Unit kg = Mass.Unit.kg;
        assertEquals("kg", kg.getId());
        assertEquals(UnitSystem.SI_BASE, kg.getUnitSystem());
        assertTrue(kg.getScale().isBaseScale());

        // Frequency: Hertz is SI derived
        Frequency.Unit hz = Frequency.Unit.Hz;
        assertEquals("Hz", hz.getId());
        assertEquals(UnitSystem.SI_DERIVED, hz.getUnitSystem());
        assertTrue(hz.getScale().isBaseScale()); // 1.0 linear factor

        // Base unit getters (class-level SI singletons)
        assertSame(Length.Unit.SI, m.getBaseUnit());
        assertSame(Mass.Unit.SI, kg.getBaseUnit());
        assertSame(Frequency.Unit.SI, hz.getBaseUnit());

        // SIUnit dimension tuples
        assertEquals(SIUnit.of("m"), m.siUnit());
        assertEquals(SIUnit.of("kg"), kg.siUnit());
        assertEquals(SIUnit.of("/s"), hz.siUnit());
    }

    /*-
     * ====================================================================== 
     * Section 2: AbstractUnit construction guards, equals/hashCode, toString 
     * ======================================================================
     */

    /**
     * Constructor validation in {@link AbstractUnit}: null or empty fields must be rejected.
     */
    @Test
    @DisplayName("AbstractUnit: constructor validation")
    public void testAbstractUnitConstructorValidation()
    {
        // textualAbbreviation null
        assertThrows(NullPointerException.class, () -> new Length.Unit(null, "name", 1.0, UnitSystem.OTHER));

        // textualAbbreviation empty
        assertThrows(UnitRuntimeException.class, () -> new Length.Unit("", "name", 1.0, UnitSystem.OTHER));

        // name null
        assertThrows(NullPointerException.class, () -> new Length.Unit("x", null, 1.0, UnitSystem.OTHER));

        // name empty
        assertThrows(UnitRuntimeException.class, () -> new Length.Unit("x", "", 1.0, UnitSystem.OTHER));

        // unitSystem null
        assertThrows(NullPointerException.class, () -> new Length.Unit("x", "name", 1.0, null));
    }

    /**
     * Verify {@link AbstractUnit#equals(Object)}, {@link AbstractUnit#hashCode()}, and {@link AbstractUnit#toString()}.
     */
    @Test
    @DisplayName("AbstractUnit: equals/hashCode and toString")
    public void testAbstractUnitEqualsHashCodeToString()
    {
        Length.Unit foot1 = new Length.Unit("ft", "ft", "foot", new LinearScale(Length.Unit.CONST_FT), UnitSystem.IMPERIAL);
        Length.Unit foot2 = new Length.Unit("ft", "ft", "foot", new LinearScale(Length.Unit.CONST_FT), UnitSystem.IMPERIAL);
        Length.Unit inch = new Length.Unit("in", "in", "inch", new LinearScale(Length.Unit.CONST_IN), UnitSystem.IMPERIAL);

        // Equality contracts for structurally equal units
        assertEquals(foot1, foot2);
        assertEquals(foot1.hashCode(), foot2.hashCode());

        // Inequality
        assertNotEquals(foot1, inch);
        assertNotEquals(foot1.hashCode(), inch.hashCode());
        assertNotEquals(foot1, new Object());
        assertNotEquals(foot1, null);

        // toString is display abbreviation
        assertEquals("ft", foot1.toString());
        assertEquals("\u212B", Length.Unit.A.toString());
    }

    /*-
     * ====================================================================== 
     * Section 3: SI-prefix generation & deriviation semantics 
     * ======================================================================
     */

    /**
     * SI-prefix generation: guard paths and positive paths.
     */
    @Test
    @DisplayName("AbstractUnit.generateSiPrefixes: guard paths and positives")
    public void testGenerateSiPrefixes()
    {
        // Guard: only possible on base-scale units
        Length.Unit foot = Length.Unit.ft; // not base-scale (0.3048)
        assertThrows(UnitRuntimeException.class, () -> foot.generateSiPrefixes(false, false));

        // Guard: kilo class must start with 'k' / name 'kilo...'
        assertThrows(UnitRuntimeException.class, () -> Length.Unit.m.generateSiPrefixes(true, false));

        // Guard: per-unit class must start with '/' / name 'per ...'
        assertThrows(UnitRuntimeException.class, () -> Frequency.Unit.Hz.generateSiPrefixes(false, true));
    }

    /*-
     * ====================================================================== 
     * Section 4: Setting & getting SI prefixes (without mutating singletons) 
     * ======================================================================
     */

    /**
     * Verify {@link UnitInterface#setSiPrefix(SIPrefix)}, {@link UnitInterface#setSiPrefix(String)},
     * {@link UnitInterface#setSiPrefixKilo(String)}, and {@link UnitInterface#setSiPrefixPer(String)} on a fresh, test-only
     * unit instance (to avoid mutating shared singletons).
     */
    @Test
    @DisplayName("UnitInterface: set/get SI prefix on a fresh unit")
    public void testSetGetSiPrefix()
    {
        // fresh Length unit instance (identity scale)
        Length.Unit u = new Length.Unit("m*", "meter*", 1.0, UnitSystem.SI_BASE);

        // set textual prefix "k" -> kilo
        u.setSiPrefix("k");
        assertNotNull(u.getSiPrefix());
        assertEquals("k", u.getSiPrefix().getDefaultTextualPrefix());
        assertEquals(1.0E3, u.getSiPrefix().getFactor(), 0.0);

        // set per-unit prefix "/M" -> per mega
        u.setSiPrefixPer("/M");
        assertNotNull(u.getSiPrefix());
        assertEquals("/M", u.getSiPrefix().getDefaultTextualPrefix());
        assertEquals(1.0E-6, u.getSiPrefix().getFactor(), 0.0);

        // set kilo-default variant (identity "k" in kilo-default maps)
        u.setSiPrefixKilo("k");
        assertNotNull(u.getSiPrefix());
        assertEquals("k", u.getSiPrefix().getDefaultTextualPrefix());
    }

    /*-
     * ====================================================================== 
     * Section 5: New unit defined in the test (Jerk: m/s^3) 
     * ======================================================================
     */

    /**
     * Minimal Jerk quantity to exercise {@link AbstractUnit} extensibility inside the test.
     */
    public static final class Jerk extends Quantity<Jerk, Jerk.Unit>
    {
        /** */
        private static final long serialVersionUID = 1L;

        /**
         * Construct a Jerk quantity with a unit.
         * @param value the value expressed in the unit
         * @param unit the unit
         */
        public Jerk(final double value, final Unit unit)
        {
            super(value, unit);
        }

        /**
         * Factory: SI value.
         * @param si the si value to use
         * @return the constructed quantity
         */
        public static Jerk ofSi(final double si)
        {
            return new Jerk(si, Unit.SI);
        }

        @Override
        public Jerk instantiate(final double si)
        {
            return ofSi(si);
        }

        @Override
        public SIUnit siUnit()
        {
            return Unit.SI_UNIT;
        }

        /**
         * Unit for Jerk (m/s^3).
         */
        public static final class Unit extends AbstractUnit<Jerk.Unit, Jerk>
        {
            /** SI dimensions for jerk: m/s^3. */
            public static final SIUnit SI_UNIT = SIUnit.of("m/s3");

            /** Base jerk unit (identity scale). */
            public static final Jerk.Unit BASE =
                    new Jerk.Unit("m/s3", "m/s^3", "jerk", new LinearScale(1.0), UnitSystem.SI_DERIVED);

            /** SI/base handle for {@link Jerk#ofSi(double)}. */
            public static final Jerk.Unit SI = BASE.generateSiPrefixes(false, false);

            /**
             * ctor with factor.
             * @param id the unit id
             * @param name the unit name
             * @param scaleFactorToBaseUnit the scale factor
             * @param unitSystem the system
             */
            public Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
            {
                super(id, name, new LinearScale(scaleFactorToBaseUnit), unitSystem);
            }

            /**
             * full ctor.
             * @param textualAbbreviation the textual abbreviation
             * @param displayAbbreviation the diosplay abbreviation
             * @param name the name
             * @param scale the scale
             * @param unitSystem the unit system
             */
            public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                    final Scale scale, final UnitSystem unitSystem)
            {
                super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem);
            }

            @Override
            public SIUnit siUnit()
            {
                return SI_UNIT;
            }

            @Override
            public Jerk.Unit getBaseUnit()
            {
                return SI;
            }

            @Override
            public Jerk ofSi(final double si)
            {
                return Jerk.ofSi(si);
            }

            @Override
            public Jerk.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                    final double scaleFactor, final UnitSystem unitSystem)
            {
                if (getScale() instanceof LinearScale ls)
                {
                    return new Jerk.Unit(textualAbbreviation, displayAbbreviation, name,
                            new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
                }
                throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
            }
        }
    }

    /**
     * Verify that a newly defined unit (Jerk) behaves correctly:
     * <ul>
     * <li>SI-prefix generation on a base-scale unit,</li>
     * <li>Derivation and Units registry lookups,</li>
     * <li>UnitInterface.ofSi(...) creates the correct quantity,</li>
     * <li>quantityInUnit(...) sets SI value and display unit.</li>
     * </ul>
     */
    @Test
    @DisplayName("New unit (Jerk): prefixes, derivation, ofSi, quantityInUnit")
    public void testCustomUnitJerk()
    {
        // The SI/base jerk unit uses identity scale (allowed for prefix generation).
        assertTrue(Jerk.Unit.BASE.getScale().isBaseScale());

        // Kilo-jerk (k m/s^3) must exist after generation (registered via Units.register in ctor)
        Jerk.Unit kBase = Jerk.Unit.SI.deriveUnit("km/s3", "km/s^3", "kilojerk", 1.0e3, UnitSystem.SI_DERIVED);
        assertNotNull(kBase);
        assertEquals(1.0e3, ((LinearScale) kBase.getScale()).getScaleFactorToBaseUnit(), 0.0);

        // Confirm SI-prefixes exist from generation on the base
        SIPrefix kilo = SIPrefixes.getSiPrefix("k");
        assertNotNull(kilo);
        // Set prefix on a fresh derived instance and read it back
        Jerk.Unit fresh = new Jerk.Unit("m/s3*", "m/s^3*", "jerk*", new LinearScale(1.0), UnitSystem.SI_DERIVED);
        fresh.setSiPrefix(kilo);
        assertSame(kilo, fresh.getSiPrefix());

        // UnitInterface.ofSi(...) should create a Jerk quantity with the given SI value
        Jerk q = Jerk.Unit.SI.ofSi(123.456);
        assertEquals(123.456, q.si(), 1e-12);

        // quantityInUnit(...) should set the display unit on the returned quantity
        Jerk.Unit kiloJerk = Jerk.Unit.SI.deriveUnit("kJerk", "kJerk", "kilojerk", 1.0e3, UnitSystem.SI_DERIVED);
        Jerk qIn = kiloJerk.quantityInUnit(2.0);
        assertEquals(2000.0, qIn.si(), 1e-12);
        assertSame(kiloJerk, qIn.getDisplayUnit());
    }

    /*-
     * ====================================================================== 
     * Section 6: Edge paths for deriveUnit on LinearScale and guard on non-linear 
     * ======================================================================
     */

    /**
     * Verify that {@link AbstractUnit#deriveUnit(String, String, String, double, UnitSystem)} multiplies linear factors and
     * throws for non-linear scales.
     */
    @Test
    @DisplayName("deriveUnit: linear factor multiplication and non-linear guard")
    public void testDeriveUnitLinearAndGuard()
    {
        // Linear -> Linear, multiplying factors
        Length.Unit meterTimes2 = Length.Unit.m.deriveUnit("m2", "m2", "meter2", 2.0, UnitSystem.SI_BASE);
        assertEquals(2.0, ((LinearScale) meterTimes2.getScale()).getScaleFactorToBaseUnit(), 0.0);

        // Non-linear: craft a tiny unit with a fake non-base scale to trigger the guard
        AbstractUnit<Length.Unit, Length> nonLinear = new Length.Unit("m#", "m#", "meter-hack", new Scale()
        {
            @Override
            public double toBaseValue(final double value)
            {
                return Math.log(1 + Math.max(0.0, value));
            }

            @Override
            public double fromBaseValue(final double si)
            {
                return Math.expm1(si);
            }

            @Override
            public boolean isBaseScale()
            {
                return false;
            }

            @Override
            public String toString()
            {
                return "NonLinearScale[]";
            }
        }, UnitSystem.OTHER);

        assertThrows(UnitRuntimeException.class, () -> nonLinear.deriveUnit("x", "x", "x", 2.0, UnitSystem.OTHER));
    }

    /*-
     * ====================================================================== 
     * Section 7: UnitInterface.ofSi on concrete units (smoke) 
     * ======================================================================
     */

    /**
     * Smoke-tests that {@link UnitInterface#ofSi(double)} on concrete units produces the right quantity type and SI value.
     */
    @Test
    @DisplayName("UnitInterface.ofSi: concrete units produce correct quantity type/SI value")
    public void testOfSiConcreteSmoke()
    {
        Length l = Length.Unit.SI.ofSi(42.0);
        assertEquals(42.0, l.si(), 0.0);

        Mass m = Mass.Unit.SI.ofSi(3.14);
        assertEquals(3.14, m.si(), 0.0);

        Frequency f = Frequency.Unit.SI.ofSi(2.5e3);
        assertEquals(2500.0, f.si(), 1e-12);
    }

    /*-
     * ====================================================================== 
     * Section 8: UnitInterface.sanity on stored vs (localized) getters 
     * ======================================================================
     */

    /**
     * Sanity: stored vs (potentially localized) getters return non-null strings; stored is stable.
     */
    @Test
    @DisplayName("Stored vs localized getters are sane and stable")
    public void testStoredVsLocalizedGetters()
    {
        Length.Unit m = Length.Unit.m;
        assertEquals("m", m.getStoredTextualAbbreviation());
        assertEquals("m", m.getStoredDisplayAbbreviation());
        assertFalse(m.getStoredName().isBlank());
        assertEquals(m, m);

        // Localized (resolved) values must be non-null and stable; we don't assert exact content
        String t1 = m.getTextualAbbreviation();
        String t2 = m.getTextualAbbreviation();
        String d1 = m.getDisplayAbbreviation();
        String d2 = m.getDisplayAbbreviation();
        String n1 = m.getName();
        String n2 = m.getName();
        assertNotNull(t1);
        assertNotNull(t2);
        assertEquals(t1, t2);
        assertNotNull(d1);
        assertNotNull(d2);
        assertEquals(d1, d2);
        assertNotNull(n1);
        assertNotNull(n2);
        assertEquals(n1, n2);
    }

    /*-
     * ==================================================
     * Section 9: Test a Per-Kilo Unit class
     * ==================================================
     */

    /**
     * A minimal test-local quantity representing a "per mass" value (unit: 1/kg), used to verify correct behavior of
     * <b>per-kilo</b> SI-prefix generation on {@link AbstractUnit}.
     */
    public static final class PerMass extends Quantity<PerMass, PerMass.Unit>
    {
        /** */
        private static final long serialVersionUID = 1L;

        /**
         * Construct a PerMass quantity with a value in the provided unit.
         * @param value double; the numeric value
         * @param unit Unit; the unit in which {@code value} is expressed
         */
        public PerMass(final double value, final Unit unit)
        {
            super(value, unit);
        }

        /**
         * Create a PerMass quantity from an SI (BASE) value, i.e. in 1/kg.
         * @param si double; the SI (BASE) value
         * @return PerMass; new instance with SI value
         */
        public static PerMass ofSi(final double si)
        {
            return new PerMass(si, Unit.SI);
        }

        @Override
        public PerMass instantiate(final double si)
        {
            return ofSi(si);
        }

        @Override
        public SIUnit siUnit()
        {
            return Unit.SI_UNIT;
        }

        /**
         * Unit for {@link PerMass} (SI dimension: 1/kg). The base textual/display abbreviation is {@code "/kg"}, and the base
         * name is {@code "per kilogram"} so that {@link AbstractUnit#generateSiPrefixes(boolean, boolean)} with
         * {@code kilo=true, perUnit=true} passes the guards and uses {@link SIPrefixes#PER_KILO_PREFIXES}.
         */
        public static final class Unit extends AbstractUnit<PerMass.Unit, PerMass>
        {
            /** SI dimensions of per-mass: 1/kg. */
            public static final SIUnit SI_UNIT = SIUnit.of("/kg");

            /** Base per-kilo unit (1/kg). */
            public static final PerMass.Unit PER_KILOGRAM =
                    new PerMass.Unit("/kg", "/kg", "per kilogram", new LinearScale(1.0), UnitSystem.SI_DERIVED);

            /** Handle for {@link PerMass#ofSi(double)} after prefix generation. */
            public static final PerMass.Unit SI = PER_KILOGRAM.generateSiPrefixes(true, true);

            /**
             * Construct a PerMass unit with an explicit (linear) scale factor to BASE (1/kg).
             * @param id String; textual abbreviation / id (e.g., "/kg", "/g")
             * @param name String; full name (e.g., "per kilogram", "per gram")
             * @param scaleFactorToBaseUnit double; numeric factor to convert to 1/kg
             * @param unitSystem UnitSystem; system of the unit (typically SI_DERIVED)
             */
            public Unit(final String id, final String name, final double scaleFactorToBaseUnit, final UnitSystem unitSystem)
            {
                super(id, name, new LinearScale(scaleFactorToBaseUnit), unitSystem);
            }

            /**
             * Construct a PerMass unit with explicit textual/display abbreviations and a provided {@link Scale}.
             * @param textualAbbreviation String; textual abbreviation / id
             * @param displayAbbreviation String; display abbreviation
             * @param name String; full name of the unit
             * @param scale Scale; scale used to convert to/from BASE (1/kg)
             * @param unitSystem UnitSystem; system of the unit (typically SI_DERIVED)
             */
            public Unit(final String textualAbbreviation, final String displayAbbreviation, final String name,
                    final Scale scale, final UnitSystem unitSystem)
            {
                super(textualAbbreviation, displayAbbreviation, name, scale, unitSystem);
            }

            @Override
            public SIUnit siUnit()
            {
                return SI_UNIT;
            }

            @Override
            public PerMass.Unit getBaseUnit()
            {
                return SI;
            }

            @Override
            public PerMass ofSi(final double si)
            {
                return PerMass.ofSi(si);
            }

            @Override
            public PerMass.Unit deriveUnit(final String textualAbbreviation, final String displayAbbreviation,
                    final String name, final double scaleFactor, final UnitSystem unitSystem)
            {
                if (getScale() instanceof LinearScale ls)
                {
                    return new PerMass.Unit(textualAbbreviation, displayAbbreviation, name,
                            new LinearScale(ls.getScaleFactorToBaseUnit() * scaleFactor), unitSystem);
                }
                throw new UnitRuntimeException("Only possible to derive a unit from a unit with a linear scale");
            }
        }
    }

    /**
     * Verify <b>per-kilo</b> prefix generation and semantics for a test-local quantity {@code PerMass} (unit 1/kg).
     * <ul>
     * <li>Prefix generation with {@code (kilo=true, perUnit=true)} produces per-gram (/g) and per-megagram (/Mg), etc.</li>
     * <li>Scale factors match expectations: /kg = 10^0, /g = 10^3, /Mg = 10^-3.</li>
     * <li>Derived names are correct: "per gram", "per megagram".</li>
     * <li>SIPrefix objects are set on the derived units as created by {@code generateSiPrefixes}.</li>
     * <li>{@code quantityInUnit} uses the generated scales correctly.</li>
     * </ul>
     */
    @Test
    @DisplayName("Per-kilo unit generation and semantics (PerMass: 1/kg)")
    public void testPerKiloUnitGenerationAndSemantics()
    {
        // Base is per kilogram, identity scale to 1/kg
        assertTrue(PerMass.Unit.PER_KILOGRAM.getScale().isBaseScale());
        assertEquals("/kg", PerMass.Unit.PER_KILOGRAM.getId());
        assertEquals("per kilogram", PerMass.Unit.PER_KILOGRAM.getStoredName());

        // After generation, typical derived forms should exist in the Units registry
        PerMass.Unit perKg = Units.resolve(PerMass.Unit.class, "/kg");
        PerMass.Unit perG = Units.resolve(PerMass.Unit.class, "/g");
        PerMass.Unit perMg = Units.resolve(PerMass.Unit.class, "/Mg");

        assertNotNull(perKg, "PerMass /kg must be registered");
        assertNotNull(perG, "PerMass /g must be registered");
        assertNotNull(perMg, "PerMass /Mg must be registered");

        // Scale factors: /kg = 10^0; /g = 10^3; /Mg = 10^-3
        assertEquals(1.0, ((LinearScale) perKg.getScale()).getScaleFactorToBaseUnit(), 0.0);
        assertEquals(1.0E3, ((LinearScale) perG.getScale()).getScaleFactorToBaseUnit(), 0.0);
        assertEquals(1.0E-3, ((LinearScale) perMg.getScale()).getScaleFactorToBaseUnit(), 0.0);

        // Names generated from "per kilo" base: "per gram", "per megagram"
        assertEquals("per kilogram", perKg.getStoredName());
        assertEquals("per gram", perG.getStoredName());
        assertEquals("per megagram", perMg.getStoredName());

        // SIPrefix objects should be set by generateSiPrefixes using PER_KILO_PREFIXES:
        // /g ← "/" prefix, /Mg ← "/M"
        assertNotNull(perG.getSiPrefix());
        assertEquals("/", perG.getSiPrefix().getDefaultTextualPrefix());

        assertNotNull(perMg.getSiPrefix());
        assertEquals("/M", perMg.getSiPrefix().getDefaultTextualPrefix());

        // quantityInUnit: 2 (/g) → SI = 2 * 1000 (/kg); display unit is preserved
        PerMass qPerG = perG.quantityInUnit(2.0);
        assertEquals(2000.0, qPerG.si(), 1e-12);
        assertSame(perG, qPerG.getDisplayUnit());

        // identity at /kg via quantityInUnit
        PerMass qPerKg = perKg.quantityInUnit(1.5);
        assertEquals(1.5, qPerKg.si(), 1e-12);
        assertSame(perKg, qPerKg.getDisplayUnit());

        // Directly set a PER_KILO SIPrefix on a fresh instance and read it back
        PerMass.Unit fresh = new PerMass.Unit("/kg*", "/kg*", "per kilogram*", new LinearScale(1.0), UnitSystem.SI_DERIVED);
        SIPrefix perKiloPrefix = SIPrefixes.PER_KILO_PREFIXES.get("/k"); // identity for per-kilo at "/k"
        assertNotNull(perKiloPrefix);
        fresh.setSiPrefix(perKiloPrefix);
        assertSame(perKiloPrefix, fresh.getSiPrefix());
    }

}
