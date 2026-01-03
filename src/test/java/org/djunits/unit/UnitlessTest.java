package org.djunits.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.quantity.Dimensionless;
import org.djunits.unit.scale.LinearScale;
import org.djunits.unit.scale.Scale;
import org.djunits.unit.si.SIUnit;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Unitless}. Ensures full coverage across constructors, constants, SI mapping, derivation behavior
 * (linear vs. non-linear), and basic object contracts. <br>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public class UnitlessTest
{
    /**
     * Verify constants and base behavior: {@link Unitless#SI_UNIT}, {@link Unitless#BASE}, IDs/names/displays, system, and
     * scale factor.
     */
    @Test
    public void testConstantsAndBaseBehavior()
    {
        // SI_UNIT must exist (dimensionless SI tuple).
        SIUnit si = Unitless.SI_UNIT;
        assertNotNull(si, "SI_UNIT must not be null");

        // BASE must exist and return itself as base unit.
        Unitless base = Unitless.BASE;
        assertNotNull(base, "BASE must not be null");
        assertSame(base, base.getBaseUnit(), "BASE should return itself as base unit");

        // BASE textual properties are single spaces (by design in Unitless).
        assertEquals(" ", base.getStoredTextualAbbreviation());
        assertEquals(" ", base.getStoredDisplayAbbreviation());
        assertEquals(" ", base.getStoredName());

        // Unit system and scale on BASE.
        assertEquals(UnitSystem.OTHER, base.getUnitSystem(), "BASE unit system should be OTHER");
        assertTrue(base.getScale() instanceof LinearScale, "BASE scale should be LinearScale");
        LinearScale ls = (LinearScale) base.getScale();
        assertEquals(1.0, ls.getScaleFactorToBaseUnit(), 1e-12);
        assertTrue(ls.isBaseScale(), "BASE LinearScale should be base scale (factor == 1.0)");

        // Simple round-trip through the scale (identity behavior at factor 1.0).
        assertEquals(123.456, ls.fromBaseValue(ls.toBaseValue(123.456)), 1e-12);
    }

    /**
     * Verify the primary constructor {@link Unitless#Unitless(String, String, double, UnitSystem)} sets id/name/system and
     * creates a {@link LinearScale} with the requested positive factor.
     */
    @Test
    public void testPrimaryConstructorLinearScale()
    {
        Unitless percent = new Unitless("%", "percent", 0.01, UnitSystem.OTHER);

        // Primary constructor uses 'id' also as display abbreviation (via AbstractUnit).
        assertEquals("%", percent.getStoredTextualAbbreviation());
        assertEquals("%", percent.getStoredDisplayAbbreviation());
        assertEquals("percent", percent.getStoredName());
        assertEquals(UnitSystem.OTHER, percent.getUnitSystem());

        assertTrue(percent.getScale() instanceof LinearScale);
        LinearScale ls = (LinearScale) percent.getScale();
        assertEquals(0.01, ls.getScaleFactorToBaseUnit(), 1e-12);
        assertFalse(ls.isBaseScale(), "Percent scale (0.01) is not base scale");

        // 100% -> 1.0 SI ; 1.0 SI -> 100%
        assertEquals(1.0, ls.toBaseValue(100.0), 1e-12);
        assertEquals(100.0, ls.fromBaseValue(1.0), 1e-12);
    }

    /**
     * Verify the secondary constructor {@link Unitless#Unitless(String, String, String, Scale, UnitSystem)} accepts an explicit
     * scale and sets fields properly. Also test null-scale rejection.
     */
    @Test
    public void testSecondaryConstructorAndNulls()
    {
        Unitless twoX = new Unitless("2x", "2x", "double-x", new LinearScale(2.0), UnitSystem.SI_DERIVED);
        assertEquals("2x", twoX.getStoredTextualAbbreviation());
        assertEquals("2x", twoX.getStoredDisplayAbbreviation());
        assertEquals("double-x", twoX.getStoredName());
        assertEquals(UnitSystem.SI_DERIVED, twoX.getUnitSystem());
        assertTrue(twoX.getScale() instanceof LinearScale);
        assertEquals(2.0, ((LinearScale) twoX.getScale()).getScaleFactorToBaseUnit(), 1e-12);

        // Null scale should be rejected by AbstractUnit preconditions.
        assertThrows(NullPointerException.class, () -> new Unitless("id", "disp", "nm", null, UnitSystem.OTHER),
                "Null scale should not be accepted");
    }

    /**
     * Verify SI mapping: {@link Unitless#siUnit()} and {@link Unitless#ofSi(double)}.
     */
    @Test
    public void testSiUnitAndOfSi()
    {
        assertSame(Unitless.SI_UNIT, Unitless.BASE.siUnit(), "siUnit() must return Unitless.SI_UNIT");
        Dimensionless d = Unitless.BASE.ofSi(Math.PI);
        assertNotNull(d);
        assertEquals(Math.PI, d.si(), 1e-12);
    }

    /**
     * Verify {@link Unitless#deriveUnit(String, String, String, double, UnitSystem)} on a unit with linear scale composes
     * factors multiplicatively.
     */
    @Test
    public void testDeriveUnitLinearScale()
    {
        Unitless base = Unitless.BASE;
        Unitless permille = base.deriveUnit("permille", "\u2030", "permille", 0.001, UnitSystem.OTHER);
        assertNotNull(permille);
        assertEquals("permille", permille.getStoredTextualAbbreviation());
        assertEquals("\u2030", permille.getStoredDisplayAbbreviation());
        assertEquals("permille", permille.getStoredName());
        assertEquals(UnitSystem.OTHER, permille.getUnitSystem());
        assertTrue(permille.getScale() instanceof LinearScale);

        LinearScale pls = (LinearScale) permille.getScale();
        assertEquals(1.0 * 0.001, pls.getScaleFactorToBaseUnit(), 1e-12);
        assertEquals(1.0, pls.toBaseValue(1000.0), 1e-12);

        // Compose on an already scaled Unitless: 0.01 (percent) × 2 -> 0.02.
        Unitless percent = new Unitless("%", "percent", 0.01, UnitSystem.OTHER);
        Unitless twoPercent = percent.deriveUnit("2%", "2%", "two percent", 2.0, UnitSystem.OTHER);
        assertTrue(twoPercent.getScale() instanceof LinearScale);
        assertEquals(0.02, ((LinearScale) twoPercent.getScale()).getScaleFactorToBaseUnit(), 1e-12);
    }

    /**
     * Verify {@code deriveUnit(...)} rejects invalid factors (0, &lt; 0, non-finite) via {@link LinearScale}'s constructor.
     * This ensures Unitless derives only with valid linear scales.
     */
    @Test
    public void testDeriveUnitInvalidFactors()
    {
        Unitless base = Unitless.BASE;
        assertThrows(IllegalArgumentException.class,
                () -> base.deriveUnit("zero", "zero", "zero-scale", 0.0, UnitSystem.OTHER));
        assertThrows(IllegalArgumentException.class,
                () -> base.deriveUnit("neg", "neg", "negative-scale", -2.0, UnitSystem.OTHER));
        assertThrows(IllegalArgumentException.class,
                () -> base.deriveUnit("nan", "nan", "nan-scale", Double.NaN, UnitSystem.OTHER));
        assertThrows(IllegalArgumentException.class,
                () -> base.deriveUnit("inf", "inf", "inf-scale", Double.POSITIVE_INFINITY, UnitSystem.OTHER));
        assertThrows(IllegalArgumentException.class,
                () -> base.deriveUnit("ninf", "ninf", "ninf-scale", Double.NEGATIVE_INFINITY, UnitSystem.OTHER));
    }

    /**
     * Verify {@code deriveUnit(...)} throws {@link UnitRuntimeException} when this unit does not have a {@link LinearScale}. We
     * construct a Unitless with a non-linear-like Scale to hit the exception branch.
     */
    @Test
    public void testDeriveUnitThrowsForNonLinearScale()
    {
        // Fake scale implementing Scale but not LinearScale; conversions are identity-like.
        class FakeScale implements Scale
        {
            /** */
            private static final long serialVersionUID = 1L;

            @Override
            public double toBaseValue(final double value)
            {
                return value;
            }

            @Override
            public double fromBaseValue(final double value)
            {
                return value;
            }

            @Override
            public boolean isBaseScale()
            {
                return true;
            }

            @Override
            public String toString()
            {
                return "FakeScale{}";
            }

            @Override
            public int hashCode()
            {
                return 17;
            }

            @Override
            public boolean equals(final Object obj)
            {
                return obj instanceof FakeScale;
            }
        }

        Unitless special = new Unitless("id", "disp", "not-linear", new FakeScale(), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () -> special.deriveUnit("x", "x", "x", 10.0, UnitSystem.OTHER),
                "Derivation must fail for non-linear scale");
    }

    /**
     * Basic object contract checks for equals, hashCode, and toString.
     */
    @Test
    public void testEqualsHashCodeToString()
    {
        Unitless base = Unitless.BASE;

        // Self-equality and hashCode consistency.
        assertEquals(base, base);
        assertEquals(base.hashCode(), base.hashCode());

        // Non-equality with other instances (different identity or fields).
        Unitless other = new Unitless("id", "id", 1.0, UnitSystem.OTHER);
        assertNotEquals(base, other);

        // Defensive equals with null and foreign type.
        assertNotEquals(base, null);
        assertNotEquals(base, new Object());

        // toString should be informative; in this case it prints the abbreviation which happens to be blank.
        String s = base.toString();
        assertNotNull(s);
        assertTrue(s.isBlank(), "toString of the unitless is blank");
    }
}
