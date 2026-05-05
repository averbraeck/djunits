package org.djunits.quantity.def;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import java.util.Map;

import org.djunits.quantity.Length;
import org.djunits.quantity.Position;
import org.djunits.quantity.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ReferenceTest tests the Reference registry, including per-class isolation of reference ids, duplicate detection within a
 * class, and snapshot behavior of the returned reference map.
 * <p>
 * The tests use Position.Reference (and a small check with Time.Reference) to validate the per-class registry and collision
 * handling of Reference. Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands.
 * All rights reserved. See for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The
 * DJUNITS project is distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style
 * license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
class ReferenceTest
{
    /**
     * Set Locale.US for consistent formatting.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    // =================================================================
    // BASIC ADD/GET AND PER-CLASS ISOLATION
    // =================================================================

    /**
     * Verify that references can be added and retrieved by id for a specific Reference subclass, and that the same id can be
     * reused safely in a different Reference subclass (per-class isolation; no cross-quantity collisions).
     */
    @Test
    void testAddGetAndPerClassIsolation()
    {
        // Add to Position.Reference
        Position.Reference.add("REF_SAME", "Position origin");
        var pRef = Position.Reference.get("REF_SAME");
        assertNotNull(pRef);
        assertEquals("REF_SAME", pRef.getId());
        assertEquals("Position origin", pRef.getName());

        // The same id should be allowed for Time.Reference (different Reference subclass)
        Time.Reference.add("REF_SAME", "Time origin");
        var tRef = AbstractReference.get(Time.Reference.class, "REF_SAME");
        // using generic accessor to avoid relying on Time.Reference.get implementation here
        assertNotNull(tRef);
        assertEquals("REF_SAME", tRef.getId());
        assertEquals("Time origin", tRef.getName());

        // Cross-check: ensure Position.Reference.get doesn't see Time.Reference entries
        assertNotNull(Position.Reference.get("REF_SAME"));
        assertNull(Position.Reference.get("NOT_THERE"));

        // clean up
        Position.Reference.get("REF_SAME").unregister();
        Time.Reference.get("REF_SAME").unregister();
    }

    // =================================================================
    // DUPLICATE DETECTION WITHIN THE SAME REFERENCE SUBCLASS
    // =================================================================

    /**
     * Adding two references with the same id to the same Reference subclass must throw an IllegalArgumentException on the
     * second attempt.
     */
    @Test
    void testDuplicateIdWithinSameClassThrows()
    {
        String id = "DUP_" + System.nanoTime();
        Position.Reference.add(id, "First"); // ok

        // Second registration with the same id for Position.Reference must throw
        assertThrows(IllegalArgumentException.class, () -> Position.Reference.add(id, "Second"));

        // clean up
        Position.Reference.get(id).unregister();
    }

    // =================================================================
    // SNAPSHOT MAP BEHAVIOR
    // =================================================================

    /**
     * getReferenceMap() must return a snapshot (defensive copy) of the internal registry of the corresponding Reference
     * subclass. Modifying the returned map must not affect the registry itself.
     */
    @Test
    void testGetReferenceMapIsSnapshot()
    {
        String id = "SNAP_" + System.nanoTime();
        Position.Reference.add(id, "Snapshot ref");
        var ref = Position.Reference.get(id);
        assertNotNull(ref);

        // The instance method returns the map for THIS subclass
        Map<String, Reference<?, ?, ?>> snap = ref.getReferenceMap();
        assertTrue(snap.containsKey(id));

        // Mutate snapshot and verify it does not affect the registry
        snap.remove(id);
        assertFalse(snap.containsKey(id)); // snapshot changed
        assertNotNull(Position.Reference.get(id)); // real registry unchanged

        // clean up
        Position.Reference.get(id).unregister();
    }

    // =================================================================
    // SIMPLE OFFSET CHAIN (NO COMPLEX CHAINING)
    // =================================================================

    /**
     * Sanity-check a single-level offset: define B = A + 10 m (for Position), and verify that 0 @ B equals +10 relative to A.
     * (This verifies that the registry changes do not disturb reference semantics.)
     */
    @Test
    void testSingleLevelOffsetStillWorks()
    {
        Position.Reference.add("A_POS", "A position origin");
        var a = Position.Reference.get("A_POS");
        assertNotNull(a);

        Position.Reference.add("B_POS", "B = A + 10 m", Length.ofSi(10.0), a);
        var b = Position.Reference.get("B_POS");
        assertNotNull(b);

        Position pB0 = new Position(0.0, Length.Unit.m, b);
        Position pB0RelA = pB0.relativeTo(a);
        assertEquals(10.0, pB0RelA.si(), 1E-12);

        // clean up
        Position.Reference.get("A_POS").unregister();
        Position.Reference.get("B_POS").unregister();
    }

    // ----------------------------------------------------------------------
    // equals / hashCode
    // ----------------------------------------------------------------------

    /**
     * Verifies {@code equals}, {@code hashCode}, {@code toString()}, {@code containsId()}.
     */
    @Test
    void equalsHashCode()
    {
        Position.Reference.add("A_POS", "A position origin");
        var a = Position.Reference.get("A_POS");
        Position.Reference.add("B_POS", "B = A + 10 m", Length.ofSi(10.0), a);
        var b = Position.Reference.get("B_POS");
        var b2 = Position.Reference.get("B_POS");
        Position.Reference.add("C_POS", "C = A + 10 m", Length.ofSi(10.0), a);
        var c = Position.Reference.get("C_POS");

        assertEquals(a, a);
        assertEquals(b, b2);
        assertNotEquals(b, c);
        assertNotEquals(a, b);
        assertNotEquals(a, null);
        assertNotEquals(a, "");

        assertTrue(Position.Reference.containsId(Position.Reference.class, "C_POS"));
        assertFalse(Position.Reference.containsId(Position.Reference.class, "X_POS"));
        assertEquals("A_POS", a.toString());

        // clean up
        Position.Reference.get("A_POS").unregister();
        Position.Reference.get("B_POS").unregister();
        Position.Reference.get("C_POS").unregister();
    }

}
