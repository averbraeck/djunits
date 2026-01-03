package org.djunits.unit.scale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.djunits.unit.UnitException;
import org.junit.jupiter.api.Test;

/**
 * ScaleTest tests the different types of conversion scales for units.
 * <p>
 * Copyright (c) 2019-2025 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://djunits.org/docs/license.html">DJUNITS License</a>
 * </p>
 * @author Alexander Verbraeck
 */
public class ScaleTest
{
    /**
     * Test the correct implementation of scales.
     * @throws UnitException on (unexpected) error
     */
    @Test
    public void testScale() throws UnitException
    {
        IdentityScale sscale = IdentityScale.SCALE;
        assertEquals(1.0, sscale.getScaleFactorToBaseUnit(), 0.0001);
        assertEquals(1.0, sscale.toBaseValue(1.0), 0.0001);
        assertEquals(1.0, sscale.fromBaseValue(1.0), 0.0001);
        assertEquals(2.5, sscale.toBaseValue(2.5), 0.0001);
        assertEquals("IdentityScale []", sscale.toString());

        IdentityScale s1 = IdentityScale.SCALE;
        IdentityScale s2 = IdentityScale.SCALE;
        assertEquals(s1, s1);
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
        assertFalse(s1.equals(null));

        LinearScale kiloscale = new LinearScale(1000.0); // kilo
        assertEquals(1000.0, kiloscale.getScaleFactorToBaseUnit(), 0.0001);
        assertEquals(1000.0, kiloscale.toBaseValue(1.0), 0.0001);
        assertEquals(1.0E-3, kiloscale.fromBaseValue(1.0), 0.0001);
        assertEquals(2500.0, kiloscale.toBaseValue(2.5), 0.0001);
        assertTrue(kiloscale.toString().contains("LinearScale"));
        assertTrue(kiloscale.toString().contains("1000.0"));

        LinearScale l1 = new LinearScale(123.0);
        LinearScale l2 = new LinearScale(123.0);
        assertEquals(l1, l1);
        assertEquals(l1, l2);
        assertEquals(l1.hashCode(), l2.hashCode());
        assertNotEquals(l1, kiloscale);
        assertNotEquals(l1, sscale);
        assertNotEquals(l1.hashCode(), kiloscale.hashCode());
        assertNotEquals(l1.hashCode(), sscale.hashCode());
        assertFalse(l1.equals(null));

        OffsetLinearScale cscale = new OffsetLinearScale(1.0, 273.15); // C-K
        assertEquals(1.0, cscale.getScaleFactorToBaseUnit(), 0.0001);
        assertEquals(273.15, cscale.getOffsetToBaseUnit(), 0.0001);
        assertEquals(273.15, cscale.toBaseValue(0.0), 0.0001);
        assertEquals(0.0, cscale.toBaseValue(-273.15), 0.0001);
        assertEquals(-273.15, cscale.fromBaseValue(0.0), 0.0001);
        assertEquals(0.0, cscale.fromBaseValue(273.15), 0.0001);
        assertTrue(cscale.toString().contains("OffsetLinearScale"));
        assertTrue(cscale.toString().contains("273.15"));
        assertTrue(cscale.toString().contains("1.0"));

        OffsetLinearScale o1 = new OffsetLinearScale(123.0, 456.0);
        OffsetLinearScale o2 = new OffsetLinearScale(123.0, 456.0);
        assertEquals(o1, o1);
        assertEquals(o1, o2);
        assertEquals(o1.hashCode(), o2.hashCode());
        assertNotEquals(o1, kiloscale);
        assertNotEquals(o1, sscale);
        assertNotEquals(o1, cscale);
        assertFalse(o1.equals(new OffsetLinearScale(123.0, 1.0)));
        assertFalse(o1.equals(new OffsetLinearScale(1.0, 456.0)));
        assertNotEquals(kiloscale, o2);
        assertNotEquals(sscale, o2);
        assertNotEquals(cscale, o2);
        assertNotEquals(o1.hashCode(), kiloscale.hashCode());
        assertNotEquals(o1.hashCode(), sscale.hashCode());
        assertNotEquals(o1.hashCode(), cscale.hashCode());
        assertFalse(o1.equals(null));

        GradeScale gscale = new GradeScale(1.0); // fraction -> angle
        assertEquals(1.0, gscale.getConversionFactorToGrade(), 0.0001);
        assertEquals(Math.PI / 4, gscale.toBaseValue(1), 0.0001);
        assertEquals(0.0, gscale.toBaseValue(0.0), 0.0001);
        assertEquals(0.1, gscale.fromBaseValue(Math.toRadians(5.71)), 0.01); // 10% = 5.71 degree
        assertEquals(0.0, gscale.fromBaseValue(0.0), 0.0001);
        assertTrue(gscale.toString().contains("GradeScale"));
        assertTrue(gscale.toString().contains("1.0"));

        GradeScale g1 = new GradeScale(0.01); // percent
        GradeScale g2 = new GradeScale(0.01); // percent
        assertEquals(g1, g1);
        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
        assertNotEquals(g1, kiloscale);
        assertNotEquals(g1, sscale);
        assertNotEquals(g1, cscale);
        assertNotEquals(g1, gscale);
        assertNotEquals(kiloscale, g2);
        assertNotEquals(sscale, g2);
        assertNotEquals(cscale, g2);
        assertNotEquals(g1.hashCode(), kiloscale.hashCode());
        assertNotEquals(g1.hashCode(), sscale.hashCode());
        assertNotEquals(g1.hashCode(), cscale.hashCode());
        assertNotEquals(g1.hashCode(), gscale.hashCode());
        assertFalse(g1.equals(null));
    }

    /**
     * Test the OffsetScale for correctness and reciprocity.
     * @throws UnitException on (unexpected) error
     */
    @Test
    public void testOffsetLinearScale() throws UnitException
    {
        OffsetLinearScale fahrenheitScale = new OffsetLinearScale(5.0 / 9.0, 459.67);
        assertEquals(255.372, fahrenheitScale.toBaseValue(0.0), 0.01); // 0 F = 255.372 K
        assertEquals(273.15, fahrenheitScale.toBaseValue(32.0), 0.01); // 32 F = 273.15 K
        assertEquals(0.0, fahrenheitScale.toBaseValue(-459.67), 0.01); // -459.67 F = 0 K
        assertEquals(0.0, fahrenheitScale.fromBaseValue(255.372), 0.01); // 0 F = 255.372 K
        assertEquals(32.0, fahrenheitScale.fromBaseValue(273.15), 0.01); // 32 F = 273.15 K
        assertEquals(-459.67, fahrenheitScale.fromBaseValue(0.0), 0.01); // -459.67 F = 0 K

        OffsetLinearScale std = new OffsetLinearScale(1.0, 0.0);
        OffsetLinearScale ols = new OffsetLinearScale(2.0, 10.0);

        assertEquals(123.0, std.fromBaseValue(std.toBaseValue(123.0)), 0.00001);
        assertEquals(123.0, std.toBaseValue(std.fromBaseValue(123.0)), 0.00001);

        assertEquals(123.0, ols.fromBaseValue(ols.toBaseValue(123.0)), 0.00001);
        assertEquals(123.0, ols.toBaseValue(ols.fromBaseValue(123.0)), 0.00001);
    }

    /**
     * Extra round-trip checks for IdentityScale and LinearScale.
     */
    @Test
    public void testIdentityAndLinearRoundTrip()
    {
        // Identity
        IdentityScale id = IdentityScale.SCALE;
        assertEquals(123.456, id.fromBaseValue(id.toBaseValue(123.456)), 1e-12);
        assertEquals(-987.0, id.fromBaseValue(id.toBaseValue(-987.0)), 1e-12);

        // Linear (kilo)
        LinearScale kilo = new LinearScale(1000.0);
        assertEquals(123.456, kilo.fromBaseValue(kilo.toBaseValue(123.456)), 1e-9);
        assertEquals(-1.25, kilo.fromBaseValue(kilo.toBaseValue(-1.25)), 1e-12);

        // Monotonicity and additivity for linear
        double a = 2.0, b = -3.5;
        assertEquals(kilo.toBaseValue(a + b), kilo.toBaseValue(a) + kilo.toBaseValue(b), 1e-12);
    }

    /**
     * Affine identity for OffsetLinearScale: toBase(a + Δ) = toBase(a) + scaleFactor * Δ.
     */
    @Test
    public void testOffsetLinearAffineIdentity()
    {
        OffsetLinearScale c = new OffsetLinearScale(1.0, 273.15); // °C to K
        double a = 20.0; // 20°C
        double delta = 3.5;
        double left = c.toBaseValue(a + delta);
        double right = c.toBaseValue(a) + c.getScaleFactorToBaseUnit() * delta;
        assertEquals(left, right, 1e-12);

        // Round-trip again with non-trivial scale+offset
        OffsetLinearScale ols = new OffsetLinearScale(2.0, 10.0);
        assertEquals(42.0, ols.fromBaseValue(ols.toBaseValue(42.0)), 1e-12);
    }

    /**
     * GradeScale: more trigonometric sanity tests and round-trips.
     */
    @Test
    public void testGradeScaleAdditional()
    {
        // k = 1.0 → grade 0.1 corresponds to atan(0.1) rad
        GradeScale unitGrade = new GradeScale(1.0);
        assertEquals(Math.atan(0.1), unitGrade.toBaseValue(0.1), 1e-12);
        assertEquals(-Math.atan(0.2), unitGrade.toBaseValue(-0.2), 1e-12);

        // Round-trips for a couple of angles
        double theta = Math.toRadians(30.0); // ≈ 0.523599 rad; grade = tan(theta) ≈ 0.57735
        assertEquals(theta, unitGrade.toBaseValue(unitGrade.fromBaseValue(theta)), 1e-12);

        // k = 0.01 → "percent grade": 10% should be grade=0.10 -> angle atan(0.10)
        GradeScale percent = new GradeScale(0.01);
        assertEquals(Math.atan(0.10), percent.toBaseValue(10.0), 1e-12); // 10%
        assertEquals(10.0, percent.fromBaseValue(Math.atan(0.10)), 1e-9);
    }

    /**
     * equals/hashCode: symmetry, transitivity, and inequality with close-but-different parameters.
     */
    @Test
    public void testEqualsHashCodeContracts()
    {
        LinearScale l1 = new LinearScale(123.0);
        LinearScale l2 = new LinearScale(123.0);
        LinearScale l3 = new LinearScale(123.0);
        LinearScale lDiff = new LinearScale(123.0001);

        // Reflexive
        assertTrue(l1.equals(l1));
        // Symmetric
        assertTrue(l1.equals(l2) && l2.equals(l1));
        // Transitive
        assertTrue(l1.equals(l2) && l2.equals(l3) && l1.equals(l3));
        // Consistent hash
        assertEquals(l1.hashCode(), l2.hashCode());

        // Negative
        assertNotEquals(l1, lDiff);
        assertNotEquals(l1.hashCode(), lDiff.hashCode());
        assertFalse(l1.equals(new Object()));
        assertFalse(l1.equals(null));

        // Cross-type inequality (already present elsewhere, add one more pairing)
        assertNotEquals(l1, IdentityScale.SCALE);
    }

    /**
     * Rendering should not mutate internal state (smoke check for all scales).
     */
    @Test
    public void testRenderingNoMutation()
    {
        IdentityScale id = IdentityScale.SCALE;
        assertTrue(id.toString().contains("IdentityScale"));
        // no state in IdentityScale, so this is just a smoke check

        LinearScale lin = new LinearScale(3.0);
        String t1 = lin.toString();
        String t2 = lin.toString();
        assertEquals(t1, t2);

        OffsetLinearScale offs = new OffsetLinearScale(2.0, -5.0);
        String u1 = offs.toString();
        String u2 = offs.toString();
        assertEquals(u1, u2);

        GradeScale grd = new GradeScale(0.01);
        String g1 = grd.toString();
        String g2 = grd.toString();
        assertEquals(g1, g2);
    }

    /**
     * Extremes and special doubles — adapt depending on the intended contract. If NaN/Infinite should be rejected, replace with
     * assertThrows.
     */
    @Test
    public void testSpecialDoubleBehavior()
    {
        LinearScale lin = new LinearScale(2.0);

        // Finite extremes should not overflow (but may reach Infinity for toBaseValue)
        assertTrue(Double.isInfinite(lin.toBaseValue(Double.MAX_VALUE)) || lin.toBaseValue(Double.MAX_VALUE) > 0);
        assertTrue(Double.isInfinite(lin.toBaseValue(-Double.MAX_VALUE)) || lin.toBaseValue(-Double.MAX_VALUE) < 0);

        // NaN passthrough — if undesired, make it assertThrows and guard in implementation
        assertTrue(Double.isNaN(lin.toBaseValue(Double.NaN)));
        assertTrue(Double.isNaN(lin.fromBaseValue(Double.NaN)));

        // Zero sign — not necessarily preserved; assert numeric equality
        assertEquals(0.0, lin.toBaseValue(0.0), 0.0);
        assertEquals(0.0, lin.fromBaseValue(0.0), 0.0);
    }

    /**
     * Test constructors against illegal arguments.
     */
    @Test
    public void testConstructorValidation()
    {
        // LinearScale factor must be positive and finite
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(0.0));
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(-1.0));
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(Double.POSITIVE_INFINITY));

        // Constructor validation for LinearScale(numerator, denominator)
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(0.0, 3600.0));
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(1000.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(-1000.0, 3600.0)); // now invalid
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(1000.0, -3600.0)); // now invalid
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(Double.NaN, 3600.0));
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(1000.0, Double.POSITIVE_INFINITY));

        // OffsetLinearScale: finite scale and offset; scale must be positive and finite; offset must be finite
        assertThrows(IllegalArgumentException.class, () -> new OffsetLinearScale(0.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new OffsetLinearScale(Double.NaN, 0.0));
        assertThrows(IllegalArgumentException.class, () -> new OffsetLinearScale(1.0, Double.NEGATIVE_INFINITY));
        assertThrows(IllegalArgumentException.class, () -> new OffsetLinearScale(1.0, Double.NaN));

        // GradeScale: reasonable factor (e.g., > 0 to avoid sign inversions in meaning)
        assertThrows(IllegalArgumentException.class, () -> new GradeScale(0.0));
        assertThrows(IllegalArgumentException.class, () -> new GradeScale(Double.NaN));
    }

    /**
     * Constructor validation for LinearScale(numerator, denominator).
     */
    @Test
    public void testLinearScaleNumDenConstructorValidation()
    {
        // Zero numerator -> IAE
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(0.0, 3600.0));

        // Zero denominator -> IAE
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(1000.0, 0.0));

        // Very large / very small but valid
        LinearScale huge = new LinearScale(1e308, 1e307);
        assertEquals(10.0, huge.getScaleFactorToBaseUnit(), 1e-12);

        LinearScale tiny = new LinearScale(1e-307, 1e-308);
        assertEquals(10.0, tiny.getScaleFactorToBaseUnit(), 1e-12);

        // Negative numerator or denominator is not allowed.
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(-1000.0, 3600.0));
        assertThrows(IllegalArgumentException.class, () -> new LinearScale(1000.0, -3600.0));

        // Theoretically, this works (although it is ugly)
        LinearScale bothNeg = new LinearScale(-1000.0, -3600.0);
        assertEquals(1000.0 / 3600.0, bothNeg.getScaleFactorToBaseUnit(), 1e-12);
    }

    /**
     * Correctness for LinearScale(numerator, denominator) using the km/h ↔ m/s example. A value in km/h multiplied by
     * (1000/3600) yields m/s.
     */
    @Test
    public void testLinearScaleNumDenCorrectness()
    {
        LinearScale kmPerHourToSI = new LinearScale(1000.0, 3600.0);
        assertEquals(1000.0 / 3600.0, kmPerHourToSI.getScaleFactorToBaseUnit(), 1e-12);

        // 90 km/h = 25 m/s
        assertEquals(25.0, kmPerHourToSI.toBaseValue(90.0), 1e-12);

        // Round-trip using the SAME scale
        double v = 13.37; // km/h
        double si = kmPerHourToSI.toBaseValue(v); // -> m/s
        double back = kmPerHourToSI.fromBaseValue(si);
        assertEquals(v, back, 1e-12);

        // Additivity for linear
        double a = 12.0, b = -7.5;
        assertEquals(kmPerHourToSI.toBaseValue(a + b), kmPerHourToSI.toBaseValue(a) + kmPerHourToSI.toBaseValue(b), 1e-12);
    }

    /**
     * isBaseScale() checks across scale classes.
     */
    @Test
    public void testIsBaseScale()
    {
        // IdentityScale is base
        assertTrue(IdentityScale.SCALE.isBaseScale());

        // LinearScale: base iff factor == 1
        assertTrue(new LinearScale(1.0).isBaseScale());
        assertFalse(new LinearScale(1000.0).isBaseScale());
        assertTrue(new LinearScale(3600.0, 3600.0).isBaseScale());
        assertFalse(new LinearScale(1000.0, 3600.0).isBaseScale());

        // OffsetLinearScale: base iff scale==1 and offset==0
        assertTrue(new OffsetLinearScale(1.0, 0.0).isBaseScale());
        assertFalse(new OffsetLinearScale(1.0, 273.15).isBaseScale());
        assertFalse(new OffsetLinearScale(2.0, 10.0).isBaseScale());

        // GradeScale: not base (mapping grade↔angle), unless you define it otherwise
        assertFalse(new GradeScale(0.01).isBaseScale());
        assertFalse(new GradeScale(1.0).isBaseScale());
    }

}
