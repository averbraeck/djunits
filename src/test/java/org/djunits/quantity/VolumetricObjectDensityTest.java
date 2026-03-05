package org.djunits.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.djunits.unit.UnitRuntimeException;
import org.djunits.unit.Units;
import org.djunits.unit.scale.GradeScale;
import org.djunits.unit.system.UnitSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * VolumetricObjectDensityTest tests the VolumetricObjectDensity quantity class.<br>
 * <br>
 * This suite verifies:
 * <ul>
 * <li>Constructors, constants, SI behavior, copy constructor</li>
 * <li>Parsing success and all parsing error branches</li>
 * <li>All arithmetic operators:
 * <ul>
 * <li>VOD × Volume → Dimensionless</li>
 * <li>VOD × Area → LinearObjectDensity</li>
 * <li>VOD × Length → ArealObjectDensity</li>
 * <li>VOD ÷ LOD → ArealObjectDensity</li>
 * <li>VOD ÷ AOD → LinearObjectDensity</li>
 * <li>VOD ÷ VOD → Dimensionless</li>
 * </ul>
 * </li>
 * <li>reciprocal() → Volume</li>
 * <li>Unit derivation behaviour: linear success + non-linear exception path</li>
 * <li>Units registry sanity</li>
 * </ul>
 * Copyright (c) 2025-2026 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://djunits.org" target="_blank">https://djunits.org</a>. The DJUNITS project is
 * distributed under a <a href="https://djunits.org/docs/license.html" target="_blank">three-clause BSD-style license</a>.
 * @author Alexander Verbraeck (specifications); Test implementation by Copilot.
 */
class VolumetricObjectDensityTest
{
    /**
     * Set a deterministic locale.
     */
    @BeforeEach
    final void setup()
    {
        Locale.setDefault(Locale.US);
    }

    /**
     * Test constructors, constants, SI conversions, instantiate, siUnit(), copy.
     */
    @Test
    void testBasics()
    {
        VolumetricObjectDensity d0 = new VolumetricObjectDensity(0.0, VolumetricObjectDensity.Unit.per_m3);
        assertEquals(VolumetricObjectDensity.ZERO, d0);
        assertEquals(0.0, VolumetricObjectDensity.ZERO.si(), 1E-12);

        VolumetricObjectDensity d1 = new VolumetricObjectDensity(1.0, VolumetricObjectDensity.Unit.per_m3);
        assertEquals(VolumetricObjectDensity.ONE, d1);
        assertEquals(1.0, VolumetricObjectDensity.ONE.si(), 1E-12);

        // Constants
        assertTrue(Double.isNaN(VolumetricObjectDensity.NaN.si()));
        assertEquals(Double.POSITIVE_INFINITY, VolumetricObjectDensity.POSITIVE_INFINITY.si());
        assertEquals(Double.NEGATIVE_INFINITY, VolumetricObjectDensity.NEGATIVE_INFINITY.si());
        assertEquals(Double.MAX_VALUE, VolumetricObjectDensity.POS_MAXVALUE.si());
        assertEquals(-Double.MAX_VALUE, VolumetricObjectDensity.NEG_MAXVALUE.si());

        // Copy constructor
        VolumetricObjectDensity two = new VolumetricObjectDensity(2.0, VolumetricObjectDensity.Unit.per_m3);
        VolumetricObjectDensity copy = new VolumetricObjectDensity(two);
        assertEquals(two.si(), copy.si(), 1E-12);
        assertEquals(two.getDisplayUnit(), copy.getDisplayUnit());

        // Abbreviation constructor
        VolumetricObjectDensity s = new VolumetricObjectDensity(1.5, "/m3");
        assertEquals(1.5, s.si(), 1E-12);

        // valueOf
        VolumetricObjectDensity p1 = VolumetricObjectDensity.valueOf("2 /m3");
        assertEquals(2.0, p1.si(), 1E-12);

        // of
        VolumetricObjectDensity p2 = VolumetricObjectDensity.of(5.0, "/m3");
        assertEquals(5.0, p2.si(), 1E-12);

        // instantiate
        assertEquals(-10.1, s.instantiate(-10.1).si(), 1E-12);

        // siUnit
        assertEquals("1/m3", s.siUnit().toString(true, false));

        // ofSi
        VolumetricObjectDensity neg = VolumetricObjectDensity.ofSi(-3.0);
        assertEquals(-3.0, neg.si(), 1E-12);
    }

    /**
     * Test parsing error branches: null text, empty text, unknown unit, malformed number, null unitString, empty unitString,
     * unknown unitString.
     */
    @Test
    void testParsingErrors()
    {
        assertThrows(NullPointerException.class, () -> VolumetricObjectDensity.valueOf(null));
        assertThrows(IllegalArgumentException.class, () -> VolumetricObjectDensity.valueOf(""));
        assertThrows(IllegalArgumentException.class, () -> VolumetricObjectDensity.valueOf("10 blargh"));
        assertThrows(IllegalArgumentException.class, () -> VolumetricObjectDensity.valueOf("not-a-number /m3"));

        assertThrows(NullPointerException.class, () -> VolumetricObjectDensity.of(1.0, null));
        assertThrows(IllegalArgumentException.class, () -> VolumetricObjectDensity.of(1.0, ""));
        // Quantity.of() throws UnitRuntimeException for unknown unitString
        assertThrows(UnitRuntimeException.class, () -> VolumetricObjectDensity.of(1.0, "blargh"));
    }

    /**
     * Test all arithmetic operator methods.
     * <ul>
     * <li>multiply(Volume)</li>
     * <li>multiply(Area)</li>
     * <li>multiply(Length)</li>
     * <li>divide(LOD)</li>
     * <li>divide(AOD)</li>
     * <li>divide(VOD)</li>
     * <li>reciprocal()</li>
     * </ul>
     */
    @Test
    void testOperations()
    {
        // (1/m3) × m3 → Dimensionless
        var d1 = VolumetricObjectDensity.ofSi(2.0).multiply(Volume.ofSi(3.0));
        assertTrue(d1 instanceof Dimensionless);
        assertEquals(6.0, d1.si(), 1E-12);

        // (1/m3) × m2 → (1/m)
        var lod1 = VolumetricObjectDensity.ofSi(4.0).multiply(Area.ofSi(2.0));
        assertTrue(lod1 instanceof LinearObjectDensity);
        assertEquals(8.0, lod1.si(), 1E-12);

        // (1/m3) × m → (1/m2)
        var aod1 = VolumetricObjectDensity.ofSi(5.0).multiply(Length.ofSi(2.0));
        assertTrue(aod1 instanceof ArealObjectDensity);
        assertEquals(10.0, aod1.si(), 1E-12);

        // (1/m3) ÷ (1/m) → (1/m2)
        var aod2 = VolumetricObjectDensity.ofSi(12.0).divide(LinearObjectDensity.ofSi(3.0));
        assertTrue(aod2 instanceof ArealObjectDensity);
        assertEquals(4.0, aod2.si(), 1E-12);

        // (1/m3) ÷ (1/m2) → (1/m)
        var lod2 = VolumetricObjectDensity.ofSi(18.0).divide(ArealObjectDensity.ofSi(6.0));
        assertTrue(lod2 instanceof LinearObjectDensity);
        assertEquals(3.0, lod2.si(), 1E-12);

        // (1/m3) ÷ (1/m3) → 1
        var d2 = VolumetricObjectDensity.ofSi(9.0).divide(VolumetricObjectDensity.ofSi(3.0));
        assertTrue(d2 instanceof Dimensionless);
        assertEquals(3.0, d2.si(), 1E-12);

        // reciprocal → Volume
        var vol = VolumetricObjectDensity.ofSi(0.5).reciprocal();
        assertTrue(vol instanceof Volume);
        assertEquals(2.0, vol.si(), 1E-12);
    }

    /**
     * Test unit behavior.
     * <ul>
     * <li>Base/SI unit properties</li>
     * <li>Unit.ofSi delegation</li>
     * <li>Linear deriveUnit</li>
     * <li>Exception path for non-linear deriveUnit</li>
     * <li>Units.resolve sanity</li>
     * </ul>
     */
    @Test
    void testUnitBehavior()
    {
        // Base unit consistency
        assertEquals(VolumetricObjectDensity.Unit.SI_UNIT, VolumetricObjectDensity.Unit.per_m3.siUnit());
        assertEquals(VolumetricObjectDensity.Unit.SI, VolumetricObjectDensity.Unit.per_m3.getBaseUnit());

        // Unit.ofSi
        VolumetricObjectDensity v = VolumetricObjectDensity.Unit.per_m3.ofSi(2.5);
        assertEquals(2.5, v.si(), 1E-12);

        // Linear derive
        VolumetricObjectDensity.Unit twoPerM3 =
                VolumetricObjectDensity.Unit.per_m3.deriveUnit("2/m3", "two per cubic meter", 2.0, UnitSystem.OTHER);
        VolumetricObjectDensity x = new VolumetricObjectDensity(1.0, twoPerM3);
        assertEquals(2.0, x.si(), 1E-12);

        // Non-linear derive throws
        VolumetricObjectDensity.Unit bad =
                new VolumetricObjectDensity.Unit("g/m3", "g/m3", "grade-like density", new GradeScale(0.01), UnitSystem.OTHER);
        assertThrows(UnitRuntimeException.class, () ->
        { bad.deriveUnit("bad", "bad", "nonlinear derived", 3.0, UnitSystem.OTHER); });

        // Registry
        assertEquals(VolumetricObjectDensity.Unit.per_m3, Units.resolve(VolumetricObjectDensity.Unit.class, "/m3"));
    }
}
