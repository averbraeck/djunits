package org.djunits.unit.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.djunits.unit.si.SIUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link UnitSystem}.
 * <p>
 * Validates the predefined static instances, constructor validation for user-defined systems, basic semantics (getters and
 * toString), and integration with {@link SIUnit#getUnitSystem()}.
 * </p>
 * Copyright (c) 2025-2025 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djutils.org" target="_blank">https://djutils.org</a>. The DJUTILS project is
 * distributed under a <a href="https://djutils.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck
 */
public final class UnitSystemTest
{
    /**
     * Discover all public static final fields of type {@link UnitSystem} on the class and return them.
     * @return list of predefined UnitSystem instances
     */
    private static List<UnitSystem> predefinedSystems()
    {
        List<UnitSystem> result = new ArrayList<>();
        for (Field f : UnitSystem.class.getDeclaredFields())
        {
            if (Modifier.isPublic(f.getModifiers()) && Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers())
                    && UnitSystem.class.isAssignableFrom(f.getType()))
            {
                try
                {
                    Object value = f.get(null);
                    if (value instanceof UnitSystem us)
                    {
                        result.add(us);
                    }
                }
                catch (IllegalAccessException e)
                {
                    fail("Unable to access UnitSystem field: " + f.getName());
                }
            }
        }
        return result;
    }

    /**
     * Predefined systems must be present, non-null, and have non-empty id and name. The toString() should be stable and
     * non-empty.
     */
    @Test
    @DisplayName("Predefined UnitSystems: existence and non-empty metadata")
    public void testPredefinedSystems()
    {
        List<UnitSystem> systems = predefinedSystems();
        assertFalse(systems.isEmpty(), "Expected at least one predefined UnitSystem");

        for (UnitSystem us : systems)
        {
            assertNotNull(us, "Predefined UnitSystem must not be null");

            // id/name must be non-empty (new design uses id+name rather than dedicated subclasses)
            String id = us.getId();
            String name = us.getName();
            assertNotNull(id, "id must not be null");
            assertNotNull(name, "name must not be null");
            assertFalse(id.isBlank(), "id must not be blank");
            assertFalse(name.isBlank(), "name must not be blank");

            // toString must be stable and non-empty
            String s1 = us.toString();
            String s2 = us.toString();
            assertNotNull(s1);
            assertFalse(s1.isBlank(), "toString must not be blank");
            assertEquals(s1, s2, "toString should be stable across calls");
        }

        // Sanity: all instances should be distinct singletons (different references)
        // (No registry enforces uniqueness of user-defined systems; predefined ones are distinct fields.)
        for (int i = 0; i < systems.size(); i++)
        {
            for (int j = i + 1; j < systems.size(); j++)
            {
                assertNotSame(systems.get(i), systems.get(j), "Predefined systems should be distinct instances");
            }
        }
    }

    /**
     * Constructor validation for user-defined systems.<br>
     * - null id/name → NullPointerException<br>
     * - empty/blank id/name → IllegalArgumentException<br>
     * - valid id/name → getters return the same, toString non-empty
     */
    @Test
    @DisplayName("User-defined UnitSystem: constructor validation and semantics")
    public void testUserDefinedConstructorValidation()
    {
        // Null checks
        assertThrows(NullPointerException.class, () -> new UnitSystem(null, "My System"));
        assertThrows(NullPointerException.class, () -> new UnitSystem("MY_SYS", null));

        // Empty / blank checks
        assertThrows(IllegalArgumentException.class, () -> new UnitSystem("", "My System"));
        assertThrows(IllegalArgumentException.class, () -> new UnitSystem("   ", "My System"));
        assertThrows(IllegalArgumentException.class, () -> new UnitSystem("MY_SYS", ""));
        assertThrows(IllegalArgumentException.class, () -> new UnitSystem("MY_SYS", "   "));

        // Happy path
        UnitSystem custom = new UnitSystem("MY_SYS", "My System");
        assertEquals("MY_SYS", custom.getId());
        assertEquals("My System", custom.getName());
        assertNotNull(custom.toString());
        assertFalse(custom.toString().isBlank());
    }

    /**
     * Equality/hashCode minimal contracts (identity-based unless overridden).<br>
     * - Reflexivity (x.equals(x))<br>
     * - Distinct user-defined instances are not the same reference; equals may or may not be overridden.
     */
    @Test
    @DisplayName("Equality/hashCode: reflexivity and distinct instances")
    public void testEqualityContracts()
    {
        UnitSystem a = new UnitSystem("SYS_A", "System A");
        UnitSystem b = new UnitSystem("SYS_A", "System A"); // same id/name, different instance

        assertTrue(a.equals(a), "Reflexivity must hold");
        // We do not assume structural equality; if equals is not overridden, this is false.
        // If equals is overridden to be structural, this may be true — either way, ensure no exceptions.
        boolean structuralEquality = a.equals(b);
        if (structuralEquality)
        {
            assertEquals(a.hashCode(), b.hashCode(), "Equal objects must have equal hashCode");
        }
        else
        {
            assertNotEquals(a, b, "Different instances with same id/name should not be equal if equals is identity-based");
        }

        assertFalse(a.equals(new Object()), "Different class must not be equal");
        assertFalse(a.equals(null), "Null must not be equal");
    }

    /**
     * Integration test: SIUnit.getUnitSystem() should report an SI-based system (e.g., SI_BASE).
     */
    @Test
    @DisplayName("Integration with SIUnit: getUnitSystem() returns SI_BASE")
    public void testIntegrationWithSIUnit()
    {
        SIUnit mps = SIUnit.of("m/s");
        UnitSystem system = mps.getUnitSystem();
        assertNotNull(system);
        assertEquals("SI", system.getId(), "SIUnit should report SI base unit system");
        assertFalse(system.getName().isBlank());
    }
}
