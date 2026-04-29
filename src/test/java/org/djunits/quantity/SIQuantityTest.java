package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.quantity.def.Quantity;
import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.si.SIUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SIQuantity}. This class focuses on the SI-agnostic scalar whose display unit is any {@link SIUnit}. The
 * tests cover:
 * <ul>
 * <li>All constructors (value+unit, value+string-abbreviation, copy constructor)</li>
 * <li>{@link SIQuantity#instantiateSi(double)} and {@link SIQuantity#siUnit()}</li>
 * <li>Parsing helpers: {@link SIQuantity#valueOf(String)} and {@link SIQuantity#of(double, String)}</li>
 * <li>Formatting and pretty name ({@link Quantity#getName()}) behavior for the "SIQuantity" camel-case class name</li>
 * <li>Fluent {@code setDisplayUnit} behavior and equals/hashCode semantics</li>
 * <li>Representative arithmetic inherited from {@link Quantity}: multiply/divide/reciprocal producing {@link SIQuantity}</li>
 * </ul>
 * <p>
 * <strong>Out of scope:</strong> we do not re-test {@link SIUnit} parsing/formatting logic in depth—those are covered by the
 * dedicated {@code SIUnit} test suite. Here we only verify interactions required by {@code SIQuantity}.
 * <p>
 * <strong>Locale pinning:</strong> The tests pin {@code Locale.Category.FORMAT} to {@link Locale#US} for deterministic number
 * parsing/formatting and restore it afterwards.
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
public class SIQuantityTest
{
    /** Saved system locale for the FORMAT category to restore after the suite. */
    private static Locale savedLocale;

    /**
     * Pin the default {@link Locale} for predictable formatting/parsing behavior across environments.
     */
    @BeforeAll
    static void pinLocale()
    {
        savedLocale = Locale.getDefault(Locale.Category.FORMAT);
        Locale.setDefault(Locale.US);
    }

    /**
     * Restore the default {@link Locale} for the FORMAT category after all tests complete.
     */
    @AfterAll
    static void restoreLocale()
    {
        Locale.setDefault(Locale.Category.FORMAT, savedLocale);
    }

    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    /**
     * Verifies the primary constructor {@code new SIQuantity(double, SIUnit)}.
     * <ul>
     * <li>Accepts a valid non-null {@link SIUnit} (e.g., {@code m}) and stores SI and display unit consistently</li>
     * <li>Rejects {@code null} unit (exception thrown by the {@link Quantity} base constructor)</li>
     * </ul>
     */
    @Test
    void constructorWithSIUnit()
    {
        SIUnit m = SIUnit.of("m");
        SIQuantity q = new SIQuantity(12.5, m);
        assertEquals(12.5, q.si(), 1e-12, "SI value must match for identity scale");
        assertSame(m, q.getDisplayUnit(), "Display unit must be the one passed to the constructor");

        assertThrows(NullPointerException.class, () -> new SIQuantity(1.0, (SIUnit) null),
                "Null display unit must be rejected");
    }

    /**
     * Verifies the string-abbreviation constructor {@code new SIQuantity(double, String)}.
     * <ul>
     * <li>Resolves a valid SI unit string (e.g., {@code "kg"})</li>
     * <li>Builds a correctly-typed SIQuantity with consistent SI and display unit</li>
     * </ul>
     */
    @Test
    void constructorWithStringAbbreviation()
    {
        SIQuantity mass = new SIQuantity(2.0, "kg");
        assertEquals(2.0, mass.si(), 1e-12);
        assertEquals(SIUnit.of("kg"), mass.getDisplayUnit(), "Resolved SIUnit must match the abbreviation");
    }

    /**
     * Verifies the copy constructor {@code new SIQuantity(SIQuantity)} produces an equal instance: same SI value and same
     * display unit.
     */
    @Test
    void copyConstructor()
    {
        SIQuantity source = new SIQuantity(3.0, SIUnit.of("m/s2"));
        SIQuantity copy = new SIQuantity(source);
        assertEquals(source, copy, "Copy must be equal to source");
        assertEquals(source.hashCode(), copy.hashCode(), "Equal objects must share hashCode");
        assertSame(source.getDisplayUnit(), copy.getDisplayUnit(), "Display unit object is preserved");
    }

    // ----------------------------------------------------------------------
    // API specifics: instantiate, siUnit, getName
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link SIQuantity#instantiateSi(double)} creates a new instance with the same display unit as the receiver at call
     * time (i.e., reflects the current {@code getDisplayUnit()} value).
     */
    @Test
    void instantiateUsesCurrentDisplayUnit()
    {
        SIQuantity base = new SIQuantity(1.0, SIUnit.of("m"));
        assertEquals(SIUnit.of("m"), base.getDisplayUnit());

        // Change the display unit; instantiate should pick up the new one.
        base.setDisplayUnit(SIUnit.of("s"));
        SIQuantity inst = base.instantiateSi(42.0);
        assertEquals(42.0, inst.si(), 1e-12);
        assertEquals(SIUnit.of("s"), inst.getDisplayUnit(), "instantiate must use the current display unit of the receiver");
    }

    /**
     * Verifies {@link SIQuantity#siUnit()} returns the current display unit (for SIQuantity this is its own SI unit).
     */
    @Test
    void siUnitReturnsCurrentUnit()
    {
        SIUnit accel = SIUnit.of("m/s2");
        SIQuantity a = new SIQuantity(9.81, accel);
        assertSame(accel, a.siUnit(), "For SIQuantity, siUnit() must return the display unit");
        a.setDisplayUnit(SIUnit.of("kg"));
        assertEquals(SIUnit.of("kg"), a.siUnit(), "siUnit() tracks the current display unit");
    }

    /**
     * Verifies the "pretty name" behavior inherited from {@link Quantity#getName()} for the class name {@code SIQuantity}
     * (camel case with internal capitals).
     * <p>
     * Expected: {@code "SIQuantity"} becomes {@code "S i quantity"}.
         */
    @Test
    void getNamePrettyFormatting()
    {
        SIQuantity q = new SIQuantity(1.0, SIUnit.of("m"));
        assertEquals("S i quantity", q.getName(), "Camel-cased 'SIQuantity' should format as 'S i quantity'");
    }

    // ----------------------------------------------------------------------
    // Parsing helpers: valueOf / of
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link SIQuantity#valueOf(String)} happy path and error handling.
     * <ul>
     * <li>Parses {@code "12.5 m"} and {@code " 12.5   m "} (lenient spacing)</li>
     * <li>Rejects {@code null} text, empty text, and unknown unit</li>
     * </ul>
     */
    @Test
    void valueOfParsesAndValidates()
    {
        SIQuantity a = SIQuantity.valueOf("12.5 m");
        assertEquals(12.5, a.si(), 1e-12);
        assertEquals(SIUnit.of("m"), a.getDisplayUnit());

        SIQuantity b = SIQuantity.valueOf("   12.5   m   ");
        assertEquals(12.5, b.si(), 1e-12);
        assertEquals(SIUnit.of("m"), b.getDisplayUnit());

        assertThrows(NullPointerException.class, () -> SIQuantity.valueOf(null));
        assertThrows(IllegalArgumentException.class, () -> SIQuantity.valueOf(""));
        assertThrows(IllegalArgumentException.class, () -> SIQuantity.valueOf("10 ???"), "Unknown unit must fail resolution");
    }

    /**
     * Verifies {@link SIQuantity#of(double, String)} happy path and error handling.
     * <ul>
     * <li>Parses a known SI unit string (e.g., {@code "kg"})</li>
     * <li>Rejects {@code null} unit string, empty unit string, and unknown unit</li>
     * </ul>
     */
    @Test
    void ofParsesAndValidates()
    {
        SIQuantity mass = SIQuantity.of(5.0, "kg");
        assertEquals(5.0, mass.si(), 1e-12);
        assertEquals(SIUnit.of("kg"), mass.getDisplayUnit());

        assertThrows(NullPointerException.class, () -> SIQuantity.of(1.0, null));
        assertThrows(IllegalArgumentException.class, () -> SIQuantity.of(1.0, ""));
        assertThrows(UnitRuntimeException.class, () -> SIQuantity.of(1.0, "???"));
    }

    // ----------------------------------------------------------------------
    // Formatting & toString variants (delegated from Quantity)
    // ----------------------------------------------------------------------

    /**
     * Verifies representative {@link Quantity#toString()} variants for an {@link SIQuantity}: default form, explicit target
     * unit, and unit suppression.
     */
    @Test
    void toStringVariants()
    {
        SIQuantity q = new SIQuantity(1500.0, SIUnit.of("m"));
        // default: "display unit"
        String def = q.toString();
        assertTrue(def.endsWith(" m"), "Default toString must end with the display unit");

        // explicit unit
        String asKg = q.toString(SIUnit.of("kg"));
        assertFalse(asKg.endsWith(" kg"));
        assertTrue(asKg.endsWith(" m"));
    }

    // ----------------------------------------------------------------------
    // setDisplayUnit fluency and equals/hashCode semantics
    // ----------------------------------------------------------------------

    /**
     * Verifies fluent {@link Quantity#setDisplayUnit(org.djunits.unit.Unit)} returns {@code this} and that
     * equals/hashCode include both SI bits and the display unit.
     */
    @Test
    void displayUnitFluentAndEquality()
    {
        SIQuantity q = new SIQuantity(1.0, SIUnit.of("m"));
        SIQuantity same = q.setDisplayUnit(SIUnit.of("kg"));
        assertSame(q, same, "setDisplayUnit must be fluent for Q=SIQuantity");
        assertEquals(SIUnit.of("kg"), q.getDisplayUnit());

        SIQuantity a = new SIQuantity(10.0, SIUnit.of("m/s2"));
        SIQuantity b = new SIQuantity(10.0, SIUnit.of("m/s2"));
        SIQuantity c = new SIQuantity(10.0, SIUnit.of("kg"));
        assertEquals(a, b, "Same SI value and same unit must be equal");
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c, "Different display unit must render quantities unequal");
    }

    // ----------------------------------------------------------------------
    // Arithmetic inherited from Quantity producing SIQuantity
    // ----------------------------------------------------------------------

    /**
     * Verifies {@link Quantity#multiply(Quantity)}, {@link Quantity#divide(Quantity)}, and {@link Quantity#reciprocal()} when
     * applied to {@link SIQuantity} produce correct SI values (the unit algebra is validated in {@code SIUnit} tests).
     */
    @Test
    void arithmeticProducesCorrectSIValues()
    {
        // Multiply: (3 m) * (4 kg) = 12 [m*kg]
        SIQuantity m = new SIQuantity(3.0, SIUnit.of("m"));
        SIQuantity kg = new SIQuantity(4.0, SIUnit.of("kg"));
        SIQuantity product = m.multiply(kg);
        assertEquals(12.0, product.si(), 1e-12);

        // Divide: (10 m) / (4 s) = 2.5 [m/s]
        SIQuantity tenM = new SIQuantity(10.0, SIUnit.of("m"));
        SIQuantity fourS = new SIQuantity(4.0, SIUnit.of("s"));
        SIQuantity quotient = tenM.divide(fourS);
        assertEquals(2.5, quotient.si(), 1e-12);

        // Reciprocal: 1 / (2 s) = 0.5 [1/s]
        SIQuantity twoS = new SIQuantity(2.0, SIUnit.of("s"));
        SIQuantity inv = (SIQuantity) twoS.reciprocal();
        assertEquals(0.5, inv.si(), 1e-12);
    }
}
